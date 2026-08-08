#!/usr/bin/env bash
set -Eeuo pipefail

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
GRADLE_FILE="$PROJECT_DIR/app/build.gradle.kts"
REMOTE="origin"
BRANCH="main"
TAG_PREFIX="v"

cd "$PROJECT_DIR"

fail() {
    echo
    echo "HATA: $1"
    exit 1
}

[ -f "$GRADLE_FILE" ] || fail "Gradle dosyası bulunamadı: $GRADLE_FILE"
git rev-parse --is-inside-work-tree >/dev/null 2>&1 || fail "Bu klasör Git repository değil."
git remote get-url "$REMOTE" >/dev/null 2>&1 || fail "'$REMOTE' remote bulunamadı."

CURRENT_BRANCH="$(git branch --show-current)"
[ "$CURRENT_BRANCH" = "$BRANCH" ] || fail "Aktif branch '$CURRENT_BRANCH'. Release için '$BRANCH' branch'ine geç."

OLD_CODE="$(grep -oE 'versionCode[[:space:]]*=[[:space:]]*[0-9]+' "$GRADLE_FILE" | head -n1 | grep -oE '[0-9]+$')"
OLD_VERSION="$(grep -oE 'versionName[[:space:]]*=[[:space:]]*"[^"]+"' "$GRADLE_FILE" | head -n1 | sed -E 's/.*"([^"]+)".*/\1/')"

[ -n "$OLD_CODE" ] || fail "Mevcut versionCode okunamadı."
[ -n "$OLD_VERSION" ] || fail "Mevcut versionName okunamadı."

if [[ ! "$OLD_VERSION" =~ ^([0-9]+)\.([0-9]+)\.([0-9]+)$ ]]; then
    fail "versionName beklenen X.Y.Z formatında değil: $OLD_VERSION"
fi

MAJOR="${BASH_REMATCH[1]}"
MINOR="${BASH_REMATCH[2]}"
PATCH="${BASH_REMATCH[3]}"

NEW_CODE=$((OLD_CODE + 1))
NEW_PATCH=$((PATCH + 1))
NEW_VERSION="${MAJOR}.${MINOR}.${NEW_PATCH}"
NEW_TAG="${TAG_PREFIX}${NEW_VERSION}"

if git rev-parse "$NEW_TAG" >/dev/null 2>&1; then
    fail "Yerel tag zaten mevcut: $NEW_TAG"
fi

if git ls-remote --exit-code --tags "$REMOTE" "refs/tags/$NEW_TAG" >/dev/null 2>&1; then
    fail "Remote tag zaten mevcut: $NEW_TAG"
fi

echo "================ BULBULUSTUR RELEASE ================"
echo "Eski sürüm : $OLD_VERSION / $OLD_CODE"
echo "Yeni sürüm : $NEW_VERSION / $NEW_CODE"
echo "Tag        : $NEW_TAG"
echo "Branch     : $BRANCH"
echo "Remote     : $(git remote get-url "$REMOTE")"

BACKUP_FILE="$(mktemp)"
cp "$GRADLE_FILE" "$BACKUP_FILE"
COMMITTED=0

cleanup() {
    local exit_code=$?

    if [ "$exit_code" -ne 0 ] && [ "$COMMITTED" -eq 0 ] && [ -f "$BACKUP_FILE" ]; then
        cp "$BACKUP_FILE" "$GRADLE_FILE"
        echo
        echo "Release commit edilmeden başarısız oldu; sürüm numarası geri alındı."
    fi

    rm -f "$BACKUP_FILE"
    exit "$exit_code"
}

trap cleanup EXIT

perl -0pi -e \
"s/versionCode\\s*=\\s*${OLD_CODE}/versionCode = ${NEW_CODE}/; s/versionName\\s*=\\s*\"\\Q${OLD_VERSION}\\E\"/versionName = \"${NEW_VERSION}\"/" \
"$GRADLE_FILE"

echo
echo "================ SÜRÜM KONTROLÜ ================"
grep -nE 'versionCode|versionName' "$GRADLE_FILE"

echo
echo "================ COMPILE ================"
./gradlew :app:compileDebugKotlin

echo
echo "================ ASSEMBLE DEBUG ================"
./gradlew :app:assembleDebug

APK="$PROJECT_DIR/app/build/outputs/apk/debug/app-debug.apk"
[ -f "$APK" ] || fail "APK bulunamadı: $APK"

echo
echo "================ APK ================"
ls -lh "$APK"

echo
echo "================ GIT STAGE ================"

git add -A -- \
    . \
    ':(exclude).idea/**' \
    ':(exclude)**/*.before-*' \
    ':(exclude)**/*.bak' \
    ':(exclude)**/*.backup' \
    ':(exclude)**/*.tmp' \
    ':(exclude)**/*.temp' \
    ':(exclude)**/*.orig' \
    ':(exclude)**/*~'

if git diff --cached --quiet; then
    fail "Commit edilecek değişiklik bulunamadı."
fi

echo
echo "================ COMMIT EDİLECEKLER ================"
git status --short

echo
echo "================ COMMIT ================"
git commit -m "chore(android): release v${NEW_VERSION}"
COMMITTED=1

echo
echo "================ TAG ================"
git tag -a "$NEW_TAG" -m "Bulbulustur Android v${NEW_VERSION}"

echo
echo "================ PUSH MAIN ================"
git push "$REMOTE" "$BRANCH"

echo
echo "================ PUSH TAG ================"
git push "$REMOTE" "$NEW_TAG"

rm -f "$BACKUP_FILE"
trap - EXIT

echo
echo "================ RELEASE TAMAMLANDI ================"
echo "Sürüm : $NEW_VERSION"
echo "Code  : $NEW_CODE"
echo "Tag   : $NEW_TAG"
echo "APK   : $APK"

echo
echo "================ SON KONTROL ================"
git status --short
git log -1 --oneline --decorate

echo
echo "================ REMOTE KONTROL ================"
git ls-remote --heads --tags "$REMOTE" |
grep -E "refs/heads/${BRANCH}$|refs/tags/${NEW_TAG}(\^\{\})?$"
