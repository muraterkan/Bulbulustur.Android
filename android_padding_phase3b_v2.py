#!/usr/bin/env python3
from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

ROOT = Path("app/src/main/java/com/bulbulustur/android")

SHARED_FILE = ROOT / "Application/Views/Shared/Components/_PageSection.kt"

TARGETS = [
    ROOT / "Application/Areas/b2b/Views/Product/LastPriceRequestScreen.kt",
    ROOT / "Application/Views/Profile/ProfileCompletionScreen.kt",
    ROOT / "Application/Views/Profile/ProfileLanguageFormScreen.kt",
    ROOT / "Application/Views/Profile/ProfileLanguageListScreen.kt",
]

IMPORT_ITEM = "import com.bulbulustur.android.Application.Views.Shared.Components.bbPageItem"
IMPORT_ITEMS = "import com.bulbulustur.android.Application.Views.Shared.Components.bbPageItems"

SHARED_CODE = """package com.bulbulustur.android.Application.Views.Shared.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun BbPageSection(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = BBSpacing.PageHorizontal),
        content = content
    )
}

fun LazyListScope.bbPageItem(
    key: Any? = null,
    contentType: Any? = null,
    content: @Composable LazyItemScope.() -> Unit
) {
    item(
        key = key,
        contentType = contentType
    ) {
        val lazyItemScope = this

        BbPageSection {
            content(lazyItemScope)
        }
    }
}

inline fun <T> LazyListScope.bbPageItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) {
    items(
        items = items,
        key = key,
        contentType = contentType
    ) { item ->
        val lazyItemScope = this

        BbPageSection {
            itemContent(lazyItemScope, item)
        }
    }
}
"""

def read(path: Path) -> str:
    return path.read_text(encoding="utf-8-sig")

def write(path: Path, text: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(text, encoding="utf-8", newline="\n")

def add_import(text: str, import_line: str) -> str:
    if import_line in text:
        return text

    package_end = text.find("\n")
    if package_end < 0:
        raise ValueError("package line not found")

    insert_at = package_end + 1
    return text[:insert_at] + "\n" + import_line + "\n" + text[insert_at:]

def normalize_root_content_padding(text: str) -> tuple[str, bool]:
    changed = False

    old = "horizontal = BBSpacing.PageHorizontal"
    if old in text:
        text = text.replace(old, "horizontal = BBSpacing.None", 1)
        changed = True
        return text, changed

    old_start = "start = BBSpacing.PageHorizontal"
    old_end = "end = BBSpacing.PageHorizontal"

    if old_start in text and old_end in text:
        text = text.replace(old_start, "start = BBSpacing.None", 1)
        text = text.replace(old_end, "end = BBSpacing.None", 1)
        changed = True

    return text, changed

def replace_root_lazy_items(text: str) -> tuple[str, int, int]:
    # These four target files have one root LazyColumn each.
    # We intentionally only replace LazyListScope calls at normal indentation,
    # and never parse/rewrap Kotlin lambda bodies.
    item_count = 0
    items_count = 0

    lines = text.splitlines()

    for i, line in enumerate(lines):
        stripped = line.lstrip()
        indent = line[:len(line) - len(stripped)]

        if re.match(r"^item\s*\{", stripped):
            lines[i] = indent + re.sub(r"^item\b", "bbPageItem", stripped, count=1)
            item_count += 1
            continue

        if re.match(r"^items\s*\(", stripped):
            lines[i] = indent + re.sub(r"^items\b", "bbPageItems", stripped, count=1)
            items_count += 1

    return "\n".join(lines) + "\n", item_count, items_count

def migrate_target(path: Path, apply: bool) -> bool:
    text = read(path)

    if "bbPageItem {" in text or "bbPageItems(" in text:
        print(f"ALREADY MIGRATED: {path}")
        return False

    text, padding_changed = normalize_root_content_padding(text)

    if not padding_changed:
        print(f"SKIP: {path} :: root PageHorizontal contentPadding not found")
        return False

    text, item_count, items_count = replace_root_lazy_items(text)

    if item_count + items_count == 0:
        print(f"SKIP: {path} :: no LazyListScope item/items calls found")
        return False

    text = add_import(text, IMPORT_ITEM)

    if items_count > 0:
        text = add_import(text, IMPORT_ITEMS)

    text = "\n".join(line.rstrip() for line in text.splitlines()) + "\n"

    print(
        ("UPDATE" if apply else "WOULD UPDATE")
        + f": {path} :: bbPageItem={item_count}, bbPageItems={items_count}"
    )

    if apply:
        write(path, text)

    return True

def main() -> int:
    parser = argparse.ArgumentParser(
        description="Phase 3B v2: edge-to-edge LazyColumns using explicit BbPageSection-backed lazy item helpers."
    )
    parser.add_argument("repo", nargs="?", default=".")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    repo = Path(args.repo).resolve()

    if not (repo / "app").exists():
        print(f"ERROR: Android repo root not found: {repo}", file=sys.stderr)
        return 2

    shared_path = repo / SHARED_FILE

    if shared_path.exists():
        existing = read(shared_path)
        if existing.strip() != SHARED_CODE.strip():
            print(f"STOP: shared file already exists with different content: {SHARED_FILE}")
            return 3
        print(f"ALREADY EXISTS: {SHARED_FILE}")
    else:
        print(("CREATE" if args.apply else "WOULD CREATE") + f": {SHARED_FILE}")
        if args.apply:
            write(shared_path, SHARED_CODE)

    changed = 0

    for rel in TARGETS:
        path = repo / rel

        if not path.exists():
            print(f"NOT FOUND: {rel}")
            continue

        try:
            if migrate_target(path, args.apply):
                changed += 1
        except Exception as ex:
            print(f"ERROR: {rel} :: {ex}")
            return 4

    print()

    if args.apply:
        print(f"Applied Phase 3B v2 to {changed} target file(s).")
        print("Run: git diff --check")
        print("Run: .\\gradlew.bat :app:compileDebugKotlin")
    else:
        print(f"Dry run: {changed} target file(s) are eligible.")
        print("No files were modified.")

    return 0

if __name__ == "__main__":
    raise SystemExit(main())
