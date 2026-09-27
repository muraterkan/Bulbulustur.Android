#!/usr/bin/env python3
from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

ROOT = Path("app/src/main/java")
IMPORT_ITEM = "import com.bulbulustur.android.Application.Views.Shared.Components.bbPageItem"
IMPORT_ITEMS = "import com.bulbulustur.android.Application.Views.Shared.Components.bbPageItems"

def read(path: Path) -> str:
    return path.read_text(encoding="utf-8-sig")

def write(path: Path, text: str) -> None:
    path.write_text("\n".join(line.rstrip() for line in text.splitlines()) + "\n", encoding="utf-8", newline="\n")

def add_import(text: str, import_line: str) -> str:
    if import_line in text:
        return text
    package_end = text.find("\n")
    if package_end < 0:
        raise ValueError("package line not found")
    return text[:package_end + 1] + "\n" + import_line + "\n" + text[package_end + 1:]

def match_pair(text: str, start: int, open_ch: str, close_ch: str) -> int:
    depth = 0
    i = start
    in_string = False
    quote = ""
    escape = False
    line_comment = False
    block_comment = False

    while i < len(text):
        ch = text[i]
        nxt = text[i + 1] if i + 1 < len(text) else ""

        if line_comment:
            if ch == "\n":
                line_comment = False
            i += 1
            continue

        if block_comment:
            if ch == "*" and nxt == "/":
                block_comment = False
                i += 2
                continue
            i += 1
            continue

        if in_string:
            if escape:
                escape = False
            elif ch == "\\":
                escape = True
            elif ch == quote:
                in_string = False
            i += 1
            continue

        if ch == "/" and nxt == "/":
            line_comment = True
            i += 2
            continue

        if ch == "/" and nxt == "*":
            block_comment = True
            i += 2
            continue

        if ch in ('"', "'"):
            in_string = True
            quote = ch
            i += 1
            continue

        if ch == open_ch:
            depth += 1
        elif ch == close_ch:
            depth -= 1
            if depth == 0:
                return i

        i += 1

    raise ValueError(f"Unmatched {open_ch}{close_ch} at {start}")

def find_lazy_columns(text: str):
    result = []
    start = 0

    while True:
        pos = text.find("LazyColumn(", start)
        if pos < 0:
            break

        paren_open = text.find("(", pos)
        paren_close = match_pair(text, paren_open, "(", ")")
        brace_open = text.find("{", paren_close)

        if brace_open < 0:
            start = paren_close + 1
            continue

        brace_close = match_pair(text, brace_open, "{", "}")
        result.append((pos, paren_open, paren_close, brace_open, brace_close))
        start = brace_close + 1

    return result

def horizontal_padding_in_args(args: str) -> bool:
    if "contentPadding" not in args:
        return False
    return (
        "horizontal = BBSpacing.PageHorizontal" in args
        or "start = BBSpacing.PageHorizontal" in args
        or "end = BBSpacing.PageHorizontal" in args
    )

def remove_horizontal_padding(args: str) -> str:
    args = args.replace("horizontal = BBSpacing.PageHorizontal", "horizontal = BBSpacing.None")
    args = args.replace("start = BBSpacing.PageHorizontal", "start = BBSpacing.None")
    args = args.replace("end = BBSpacing.PageHorizontal", "end = BBSpacing.None")
    return args

def top_level_lazy_calls(body: str):
    """
    Return token spans for top-level LazyListScope calls inside one LazyColumn body.
    Only depth 0 in the body is touched, so nested LazyRow/LazyColumn item calls are ignored.
    """
    calls = []
    i = 0
    brace = paren = bracket = 0
    in_string = False
    quote = ""
    escape = False
    line_comment = False
    block_comment = False

    while i < len(body):
        ch = body[i]
        nxt = body[i + 1] if i + 1 < len(body) else ""

        if line_comment:
            if ch == "\n":
                line_comment = False
            i += 1
            continue

        if block_comment:
            if ch == "*" and nxt == "/":
                block_comment = False
                i += 2
                continue
            i += 1
            continue

        if in_string:
            if escape:
                escape = False
            elif ch == "\\":
                escape = True
            elif ch == quote:
                in_string = False
            i += 1
            continue

        if ch == "/" and nxt == "/":
            line_comment = True
            i += 2
            continue

        if ch == "/" and nxt == "*":
            block_comment = True
            i += 2
            continue

        if ch in ('"', "'"):
            in_string = True
            quote = ch
            i += 1
            continue

        if ch == "{":
            brace += 1
            i += 1
            continue
        if ch == "}":
            brace -= 1
            i += 1
            continue
        if ch == "(":
            paren += 1
            i += 1
            continue
        if ch == ")":
            paren -= 1
            i += 1
            continue
        if ch == "[":
            bracket += 1
            i += 1
            continue
        if ch == "]":
            bracket -= 1
            i += 1
            continue

        if brace == 0 and paren == 0 and bracket == 0 and (ch.isalpha() or ch == "_"):
            j = i + 1
            while j < len(body) and (body[j].isalnum() or body[j] == "_"):
                j += 1
            token = body[i:j]
            if token in ("item", "items", "stickyHeader", "itemsIndexed"):
                calls.append((i, j, token))
            i = j
            continue

        i += 1

    return calls

def migrate_text(text: str):
    lazy_columns = find_lazy_columns(text)
    if not lazy_columns:
        return text, 0, 0, []

    replacements = []
    total_item = 0
    total_items = 0
    unsupported = []

    for pos, paren_open, paren_close, brace_open, brace_close in lazy_columns:
        args = text[pos:paren_close + 1]

        if not horizontal_padding_in_args(args):
            continue

        new_args = remove_horizontal_padding(args)
        replacements.append((pos, paren_close + 1, new_args))

        body_start = brace_open + 1
        body = text[body_start:brace_close]

        calls = top_level_lazy_calls(body)

        for a, b, token in calls:
            absolute_a = body_start + a
            absolute_b = body_start + b

            if token == "item":
                replacements.append((absolute_a, absolute_b, "bbPageItem"))
                total_item += 1
            elif token == "items":
                replacements.append((absolute_a, absolute_b, "bbPageItems"))
                total_items += 1
            else:
                unsupported.append(token)

    if not replacements:
        return text, 0, 0, unsupported

    # Apply from end to start so offsets remain valid.
    for a, b, value in sorted(replacements, key=lambda x: x[0], reverse=True):
        text = text[:a] + value + text[b:]

    if total_item > 0:
        text = add_import(text, IMPORT_ITEM)
    if total_items > 0:
        text = add_import(text, IMPORT_ITEMS)

    return text, total_item, total_items, unsupported

def main() -> int:
    parser = argparse.ArgumentParser(
        description="Broad LazyColumn migration: remove root PageHorizontal contentPadding and pad every top-level item/items via bbPageItem/bbPageItems."
    )
    parser.add_argument("repo", nargs="?", default=".")
    parser.add_argument("--apply", action="store_true")
    parser.add_argument("--limit", type=int, default=0, help="Optional maximum number of files to modify (0 = unlimited).")
    args = parser.parse_args()

    repo = Path(args.repo).resolve()
    root = repo / ROOT

    if not root.exists():
        print(f"ERROR: Kotlin root not found: {root}", file=sys.stderr)
        return 2

    pending = []
    skipped_unsupported = []

    for path in sorted(root.rglob("*.kt")):
        original = read(path)

        try:
            migrated, item_count, items_count, unsupported = migrate_text(original)
        except Exception as ex:
            print(f"STOP: {path.relative_to(repo)} :: {ex}")
            return 3

        if unsupported:
            skipped_unsupported.append((path.relative_to(repo), sorted(set(unsupported))))
            continue

        if migrated == original:
            continue

        pending.append((path, migrated, item_count, items_count))

        if args.limit > 0 and len(pending) >= args.limit:
            break

    for path, _, item_count, items_count in pending:
        print(
            ("UPDATE" if args.apply else "WOULD UPDATE")
            + f": {path.relative_to(repo)} :: bbPageItem={item_count}, bbPageItems={items_count}"
        )

    if skipped_unsupported:
        print()
        print("SKIPPED UNSUPPORTED LAZY DSL")
        print("----------------------------")
        for path, tokens in skipped_unsupported:
            print(f"{path} :: {','.join(tokens)}")

    if args.apply:
        for path, migrated, _, _ in pending:
            write(path, migrated)

    print()
    if args.apply:
        print(f"Applied broad padding migration to {len(pending)} file(s).")
        print("All migrated top-level LazyColumn items are padded for now.")
        print("Later, convert selected bbPageItem/bbPageItems back to item/items for full-bleed sliders/rails.")
        print("Run: git diff --check")
        print("Run: .\\gradlew.bat :app:compileDebugKotlin")
    else:
        print(f"Dry run: {len(pending)} file(s) are eligible.")
        print("No files were modified.")
        print("Tip: use --limit 20 for a smaller first batch if desired.")

    return 0

if __name__ == "__main__":
    raise SystemExit(main())
