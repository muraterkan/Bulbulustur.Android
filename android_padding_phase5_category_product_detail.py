#!/usr/bin/env python3
from __future__ import annotations

import argparse
import sys
from pathlib import Path

ROOT = Path("app/src/main/java/com/bulbulustur/android")

CATEGORY_FILES = [
    ROOT / "Application/Areas/b2c/Views/Category/CategoryLevel1Screen.kt",
    ROOT / "Application/Areas/b2c/Views/Category/CategoryLevel2Screen.kt",
]

PRODUCT_DETAIL_FILES = [
    ROOT / "Application/Areas/b2c/Views/Product/ProductDetailScreen.kt",
    ROOT / "Application/Areas/b2b/Views/Product/WholesaleProductDetailScreen.kt",
]

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

def replace_exact(text: str, old: str, new: str, label: str) -> str:
    count = text.count(old)
    if count != 1:
        raise ValueError(f"{label}: expected 1 match, got {count}")
    return text.replace(old, new, 1)

def migrate_category(text: str) -> str:
    text = add_import(text, IMPORT_ITEM)
    text = add_import(text, IMPORT_ITEMS)

    text = replace_exact(
        text,
        """contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),""",
        """contentPadding = PaddingValues(
                start = BBSpacing.None,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.None,
                bottom = BBSpacing.PageBottom
            ),""",
        "category root contentPadding"
    )

    # Ordinary padded content.
    text = replace_exact(
        text,
        "item {\n                CategoryDetailHero(",
        "bbPageItem {\n                CategoryDetailHero(",
        "category hero"
    )

    text = replace_exact(
        text,
        "item {\n                    CategorySubCategorySectionHeader()",
        "bbPageItem {\n                    CategorySubCategorySectionHeader()",
        "subcategory header"
    )

    text = replace_exact(
        text,
        """items(
                    items = validChildCategories,""",
        """bbPageItems(
                    items = validChildCategories,""",
        "subcategory rows"
    )

    # Intentionally left as raw item {} => edge-to-edge:
    # CategoryShowcaseSection
    # CategoryDealsOfTheDaySection
    # CategorySponsoredFeaturedSection
    # CategoryBrands

    return text

def validate_product_detail(path: Path, text: str) -> None:
    if "HorizontalPager(" not in text:
        raise ValueError(f"{path.name}: HorizontalPager not found")

    # Product detail roots already have no global PageHorizontal wrapper.
    # Gallery/pager is full width and ordinary cards own their own PageHorizontal.
    if path.name == "ProductDetailScreen.kt":
        required = [
            "RetailProductDetailGallery(",
            "RetailProductTitleCard(",
            "RetailProductDetailVariantCard(",
            "RetailProductPriceCard(",
        ]
    else:
        required = [
            "WholesaleProductDetailGallery(",
            "WholesaleProductTitleCard(",
            "WholesaleTradeSummaryCard(",
        ]

    missing = [x for x in required if x not in text]
    if missing:
        raise ValueError(f"{path.name}: missing expected structures: {missing}")

def main() -> int:
    parser = argparse.ArgumentParser(
        description="Phase 5: category root padding -> explicit padded rows, while showcases remain full-bleed; verify product detail galleries are already edge-to-edge."
    )
    parser.add_argument("repo", nargs="?", default=".")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    repo = Path(args.repo).resolve()
    if not (repo / "app").exists():
        print(f"ERROR: Android repo root not found: {repo}", file=sys.stderr)
        return 2

    pending = []

    for rel in CATEGORY_FILES:
        path = repo / rel
        if not path.exists():
            print(f"NOT FOUND: {rel}")
            return 3

        original = read(path)

        if "bbPageItems(" in original and "bbPageItem {" in original:
            print(f"ALREADY MIGRATED: {rel}")
            continue

        try:
            migrated = migrate_category(original)
        except Exception as ex:
            print(f"STOP: {rel} :: {ex}")
            return 4

        pending.append((path, migrated))
        print(("UPDATE" if args.apply else "WOULD UPDATE") + f": {rel}")

    print()
    print("PRODUCT DETAIL CONTRACT CHECK")
    print("-----------------------------")

    for rel in PRODUCT_DETAIL_FILES:
        path = repo / rel
        if not path.exists():
            print(f"NOT FOUND: {rel}")
            return 5

        text = read(path)
        try:
            validate_product_detail(path, text)
        except Exception as ex:
            print(f"STOP: {rel} :: {ex}")
            return 6

        print(f"OK: {rel} :: gallery/pager already edge-to-edge; content cards own PageHorizontal")

    if args.apply:
        for path, migrated in pending:
            write(path, migrated)

    print()
    if args.apply:
        print(f"Applied Phase 5 to {len(pending)} category file(s).")
        print("Product detail files were verified only; no unnecessary changes were made.")
        print("Run: git diff --check")
        print("Run: .\\gradlew.bat :app:compileDebugKotlin")
    else:
        print(f"Dry run: {len(pending)} category file(s) are eligible.")
        print("No files were modified.")

    return 0

if __name__ == "__main__":
    raise SystemExit(main())
