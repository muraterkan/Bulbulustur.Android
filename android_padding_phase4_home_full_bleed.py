#!/usr/bin/env python3
from __future__ import annotations

import argparse
import sys
from pathlib import Path

ROOT = Path("app/src/main/java/com/bulbulustur/android")

FILES = {
    "retail_home": ROOT / "Application/Areas/b2c/Views/Home/RetailHomeScreen.kt",
    "wholesale_home": ROOT / "Application/Areas/b2b/Views/Home/WholesaleHomeScreen.kt",
    "campaigns": ROOT / "Application/Areas/b2c/Views/Components/_CampaignBanners.kt",
    "deals": ROOT / "Application/Areas/b2c/Views/Components/_B2CDealsOfTheDay.kt",
    "retail_special": ROOT / "Application/Areas/b2c/Views/Components/_B2CHomepageSpecialContents.kt",
    "wholesale_featured": ROOT / "Application/Areas/b2b/Views/Components/_HomepageFeaturedProducts.kt",
    "wholesale_special": ROOT / "Application/Areas/b2b/Views/Components/_HomepageSpecialContents.kt",
}

IMPORT_PAGE_ITEM = "import com.bulbulustur.android.Application.Views.Shared.Components.bbPageItem"
IMPORT_PAGE_SECTION = "import com.bulbulustur.android.Application.Views.Shared.Components.BbPageSection"

def read(path: Path) -> str:
    return path.read_text(encoding="utf-8-sig")

def write(path: Path, text: str) -> None:
    path.write_text("\n".join(line.rstrip() for line in text.splitlines()) + "\n", encoding="utf-8", newline="\n")

def add_import(text: str, line: str) -> str:
    if line in text:
        return text
    package_end = text.find("\n")
    if package_end < 0:
        raise ValueError("package line not found")
    return text[:package_end + 1] + "\n" + line + "\n" + text[package_end + 1:]

def replace_exact(text: str, old: str, new: str, label: str) -> str:
    count = text.count(old)
    if count != 1:
        raise ValueError(f"{label}: expected exactly 1 match, got {count}")
    return text.replace(old, new, 1)

def migrate_retail_home(text: str) -> str:
    text = add_import(text, IMPORT_PAGE_ITEM)

    text = replace_exact(
        text,
        """contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottomCompact
            ),""",
        """contentPadding = PaddingValues(
                start = BBSpacing.None,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.None,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottomCompact
            ),""",
        "retail root contentPadding"
    )

    for old, new, label in [
        ("item {\n                RetailHomeHeroCard()", "bbPageItem {\n                RetailHomeHeroCard()", "retail hero"),
        ("item {\n                RetailHomeTrustStrip()", "bbPageItem {\n                RetailHomeTrustStrip()", "retail trust"),
        ("item {\n                BbSectionHeader(", "bbPageItem {\n                BbSectionHeader(", "retail store section header"),
    ]:
        text = replace_exact(text, old, new, label)

    return text

def migrate_wholesale_home(text: str) -> str:
    text = add_import(text, IMPORT_PAGE_ITEM)

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
        "wholesale root contentPadding"
    )

    for old, new, label in [
        ("item {\n                WholesaleHeroCard(", "bbPageItem {\n                WholesaleHeroCard(", "wholesale hero"),
        ("item {\n                WholesaleTrustRail()", "bbPageItem {\n                WholesaleTrustRail()", "wholesale trust"),
        ("item {\n                WholesaleSectionTitle(", "bbPageItem {\n                WholesaleSectionTitle(", "wholesale section title"),
        ("item {\n                WholesaleActionRow(", "bbPageItem {\n                WholesaleActionRow(", "wholesale action row"),
    ]:
        text = replace_exact(text, old, new, label)

    return text

def wrap_modifier_with_page_padding(text: str, exact_modifier: str, label: str) -> str:
    return replace_exact(
        text,
        exact_modifier,
        exact_modifier.replace(
            "Modifier.fillMaxWidth()",
            "Modifier\n                .fillMaxWidth()\n                .padding(horizontal = BBSpacing.PageHorizontal)"
        ),
        label
    )

def migrate_campaigns(text: str) -> str:
    # Header padded; LazyRow remains genuinely edge-to-edge.
    old = """Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,"""
    new = """Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BBSpacing.PageHorizontal),
            verticalAlignment = Alignment.Bottom,"""
    return replace_exact(text, old, new, "campaign header")

def migrate_deals(text: str) -> str:
    old = """Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,"""
    new = """Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BBSpacing.PageHorizontal),
            horizontalArrangement = Arrangement.SpaceBetween,"""
    return replace_exact(text, old, new, "deals header")

def migrate_retail_special(text: str) -> str:
    old = """Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text("""
    new = """Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BBSpacing.PageHorizontal),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text("""
    return replace_exact(text, old, new, "retail special header")

def migrate_wholesale_featured(text: str) -> str:
    old = """Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,"""
    new = """Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BBSpacing.PageHorizontal),
        verticalAlignment = Alignment.Bottom,"""
    text = replace_exact(text, old, new, "wholesale featured header")
    if "import androidx.compose.foundation.layout.padding" not in text:
        text = text.replace(
            "import androidx.compose.foundation.layout.fillMaxWidth",
            "import androidx.compose.foundation.layout.fillMaxWidth\nimport androidx.compose.foundation.layout.padding",
            1
        )
    return text

def migrate_wholesale_special(text: str) -> str:
    old = """Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text("""
    new = """Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BBSpacing.PageHorizontal),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text("""
    text = replace_exact(text, old, new, "wholesale special header")
    if "import androidx.compose.foundation.layout.padding" not in text:
        text = text.replace(
            "import androidx.compose.foundation.layout.fillMaxWidth",
            "import androidx.compose.foundation.layout.fillMaxWidth\nimport androidx.compose.foundation.layout.padding",
            1
        )
    return text

MIGRATORS = {
    "retail_home": migrate_retail_home,
    "wholesale_home": migrate_wholesale_home,
    "campaigns": migrate_campaigns,
    "deals": migrate_deals,
    "retail_special": migrate_retail_special,
    "wholesale_featured": migrate_wholesale_featured,
    "wholesale_special": migrate_wholesale_special,
}

def main() -> int:
    parser = argparse.ArgumentParser(description="Phase 4: make B2C/B2B home slider sections full-bleed while ordinary content remains explicitly padded.")
    parser.add_argument("repo", nargs="?", default=".")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    repo = Path(args.repo).resolve()
    if not (repo / "app").exists():
        print(f"ERROR: Android repo root not found: {repo}", file=sys.stderr)
        return 2

    pending = []

    for key, rel in FILES.items():
        path = repo / rel
        if not path.exists():
            print(f"NOT FOUND: {rel}")
            return 3

        original = read(path)

        try:
            migrated = MIGRATORS[key](original)
        except Exception as ex:
            print(f"STOP: {rel} :: {ex}")
            return 4

        if migrated == original:
            print(f"NO CHANGE: {rel}")
            continue

        pending.append((path, migrated))
        print(("UPDATE" if args.apply else "WOULD UPDATE") + f": {rel}")

    if args.apply:
        for path, migrated in pending:
            write(path, migrated)

    print()
    if args.apply:
        print(f"Applied Phase 4 to {len(pending)} file(s).")
        print("Run: git diff --check")
        print("Run: .\\gradlew.bat :app:compileDebugKotlin")
    else:
        print(f"Dry run: {len(pending)} file(s) are eligible.")
        print("No files were modified.")

    return 0

if __name__ == "__main__":
    raise SystemExit(main())
