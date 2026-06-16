/*
 * Bulbulustur Android Native Buyer App
 * Design System / Theme Mode
 *
 * This file defines the public theme modes supported by the app.
 *
 * V1 decision:
 * - The app supports two active modes: Light and Dark.
 * - Navy / Soft Dark palette values may remain in BbColors as static palette tokens,
 *   but Navy is not exposed as an active app theme mode in V1.
 *
 * Rules:
 * - Feature screens must not branch directly on theme mode for styling.
 * - Feature screens should use MaterialTheme.colorScheme for theme-aware colors.
 * - BbThemeMode should only be used by theme selection, app settings and BbTheme.
 */

package com.bulbulustur.android.wwwroot.theme

enum class BbThemeMode {
    Light,
    Dark
}