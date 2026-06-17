/*
 * Bulbulustur Android Native Buyer App
 * Design System / Alpha Tokens
 *
 * This file is the single source of truth for opacity and alpha values.
 *
 * Rules:
 * - Feature screens should not use raw alpha values such as 0.45f, 0.65f, 0.12f.
 * - Use BbAlpha tokens for disabled, muted, overlay, pressed and scrim states.
 * - Alpha tokens are theme-independent and can be shared with future iOS / SwiftUI design tokens.
 */

package com.bulbulustur.android.Application.wwwroot.DesignTokens

object BBAlpha {

    /*
     * Disabled states
     */

    const val DisabledContainer: Float = 0.45f
    const val DisabledContent: Float = 0.65f
    const val DisabledLabel: Float = 0.55f
    const val DisabledIcon: Float = 0.55f
    const val DisabledBorder: Float = 0.40f

    /*
     * Text / content emphasis
     */

    const val Full: Float = 1.00f
    const val High: Float = 0.88f
    const val Muted: Float = 0.72f
    const val Subtle: Float = 0.56f
    const val Faint: Float = 0.32f
    const val Ghost: Float = 0.18f

    /*
     * Interaction states
     */

    const val Pressed: Float = 0.88f
    const val Hovered: Float = 0.92f
    const val Focused: Float = 0.96f

    /*
     * Overlay / surface layers
     */

    const val OverlayLight: Float = 0.08f
    const val Overlay: Float = 0.12f
    const val OverlayStrong: Float = 0.18f
    const val OverlayHeavy: Float = 0.24f

    /*
     * Scrim / modal background
     */

    const val ScrimLight: Float = 0.32f
    const val Scrim: Float = 0.48f
    const val ScrimStrong: Float = 0.64f
}
