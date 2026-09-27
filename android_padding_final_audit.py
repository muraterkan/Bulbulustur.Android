#!/usr/bin/env python3
from __future__ import annotations

import argparse
import csv
import re
import sys
from pathlib import Path

KOTLIN_ROOT = Path("app/src/main/java")

ROOT_PATTERNS = (
    "contentPadding = PaddingValues(",
    "contentPadding = androidx.compose.foundation.layout.PaddingValues(",
    ".padding(",
)

def read(path: Path) -> str:
    return path.read_text(encoding="utf-8-sig")

def screen_names(text: str) -> list[str]:
    return re.findall(r"\bfun\s+([A-Za-z0-9_]*Screen)\s*\(", text)

def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("repo", nargs="?", default=".")
    parser.add_argument("--report", default="layout-padding-final-audit.csv")
    args = parser.parse_args()

    repo = Path(args.repo).resolve()
    root = repo / KOTLIN_ROOT

    if not root.exists():
        print(f"ERROR: Kotlin root not found: {root}", file=sys.stderr)
        return 2

    rows = []

    for path in sorted(root.rglob("*.kt")):
        text = read(path)
        screens = screen_names(text)
        if not screens:
            continue

        page_horizontal_count = text.count("BBSpacing.PageHorizontal")
        page_none_count = text.count("BBSpacing.None")
        bb_page_item_count = text.count("bbPageItem")
        bb_page_items_count = text.count("bbPageItems")
        page_section_count = text.count("BbPageSection")

        if page_horizontal_count == 0 and bb_page_item_count == 0 and bb_page_items_count == 0:
            continue

        status = "REVIEW"

        if bb_page_item_count > 0 or bb_page_items_count > 0:
            status = "SECTIONIZED_LAZY"
        elif "contentPadding" in text and "BBSpacing.PageHorizontal" in text:
            status = "ROOT_LAZY_PADDING"
        elif "BBSpacing.PageHorizontal" in text:
            status = "EXPLICIT_PAGE_PADDING"

        rows.append({
            "path": str(path.relative_to(repo)),
            "screens": ";".join(screens),
            "status": status,
            "page_horizontal_count": page_horizontal_count,
            "page_none_count": page_none_count,
            "bb_page_item_count": bb_page_item_count,
            "bb_page_items_count": bb_page_items_count,
            "page_section_count": page_section_count,
        })

    report = repo / args.report
    with report.open("w", encoding="utf-8", newline="") as f:
        fields = list(rows[0].keys()) if rows else [
            "path","screens","status","page_horizontal_count","page_none_count",
            "bb_page_item_count","bb_page_items_count","page_section_count"
        ]
        writer = csv.DictWriter(f, fieldnames=fields)
        writer.writeheader()
        writer.writerows(rows)

    counts = {}
    for row in rows:
        counts[row["status"]] = counts.get(row["status"], 0) + 1

    print(f"REPORT: {report}")
    print()
    print("FINAL PADDING AUDIT")
    print("-------------------")
    for key in sorted(counts):
        print(f"{key}: {counts[key]}")

    for status in ("ROOT_LAZY_PADDING", "EXPLICIT_PAGE_PADDING", "SECTIONIZED_LAZY"):
        selected = [r for r in rows if r["status"] == status]
        print()
        print(status)
        print("-" * len(status))
        for row in selected:
            print(row["path"])

    return 0

if __name__ == "__main__":
    raise SystemExit(main())
