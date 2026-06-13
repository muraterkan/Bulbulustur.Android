/*
 * Bulbulustur Android Native Buyer App
 * Design System / Color Tokens
 *
 * This file is the mobile color palette source derived from Bulbulustur Web Main CSS.
 *
 * Rules:
 * - Raw Color(...) values are allowed here because this file defines the palette.
 * - BbColors is the static palette layer.
 * - BbTheme maps these palette values into MaterialTheme.colorScheme for Light, Navy and Dark themes.
 * - Feature screens should prefer MaterialTheme.colorScheme for theme-aware UI colors.
 * - Use BbColors directly only for fixed brand/status colors or inside design system/theme/component definitions.
 */

package com.bulbulustur.android.ui.theme

import androidx.compose.ui.graphics.Color

object BbColors {

    /*
     * Special colors
     */

    val Transparent = Color.Transparent
    val Unspecified = Color.Unspecified

    /*
     * Base colors
     */

    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)

    /*
     * Brand aliases
     * Kept for compatibility with the existing Android codebase.
     */

    val Primary = Color(0xFFFED700)
    val PrimarySoft = Color(0xFFFFF8CC)

    /*
     * Status aliases
     * Static status colors. Use directly only when status identity must stay stable across themes.
     */

    val Success = Color(0xFF22C55E)
    val Warning = Color(0xFFF97316)
    val Danger = Color(0xFFEF4444)
    val Info = Color(0xFF2563EB)

    /*
     * Surface aliases
     * Prefer MaterialTheme.colorScheme.surface/background in feature screens.
     */

    val Surface = Color(0xFFFFFFFF)
    val SurfaceSoft = Color(0xFFFAFBFC)
    val SurfaceMuted = Color(0xFFF4F6F8)
    val SurfaceElevated = Color(0xFFFFFFFF)

    /*
     * Text aliases
     * Prefer MaterialTheme.colorScheme.onSurface/onSurfaceVariant in feature screens.
     */

    val TextStrong = Color(0xFF0F1A33)
    val TextMuted = Color(0xFF6B7894)
    val TextSubtle = Color(0xFF445066)

    /*
     * Border aliases
     * Prefer MaterialTheme.colorScheme.outline/outlineVariant in feature screens.
     */

    val Border = Color(0xFFE8EDF3)
    val BorderStrong = Color(0xFFD5DDE8)

    /*
     * Yellow / Brand scale
     */

    object Yellow {
        val Yellow50 = Color(0xFFFFFDF0)
        val Yellow100 = Color(0xFFFFF8CC)
        val Yellow200 = Color(0xFFFFF099)
        val Yellow300 = Color(0xFFFFE866)
        val Yellow400 = Color(0xFFFFDF33)
        val Yellow500 = Color(0xFFFED700)
        val Yellow600 = Color(0xFFE6C300)
        val Yellow700 = Color(0xFFB89400)
        val Yellow800 = Color(0xFF806700)
        val Yellow900 = Color(0xFF4D3E00)
    }

    /*
     * Navy scale
     */

    object Navy {
        val Navy50 = Color(0xFFEDF4FB)
        val Navy100 = Color(0xFFD8E7F5)
        val Navy200 = Color(0xFFB6CDE3)
        val Navy300 = Color(0xFF8AAAC8)
        val Navy400 = Color(0xFF587C9E)
        val Navy500 = Color(0xFF17324F)
        val Navy600 = Color(0xFF142B45)
        val Navy700 = Color(0xFF0F2238)
        val Navy800 = Color(0xFF0B1C30)
        val Navy900 = Color(0xFF081626)
    }

    /*
     * Gray scale
     */

    object Gray {
        val Gray50 = Color(0xFFFAFBFC)
        val Gray100 = Color(0xFFF4F6F8)
        val Gray200 = Color(0xFFE8EDF3)
        val Gray300 = Color(0xFFD5DDE8)
        val Gray400 = Color(0xFFB9C2D3)
        val Gray500 = Color(0xFF8A96A8)
        val Gray600 = Color(0xFF6B7894)
        val Gray700 = Color(0xFF445066)
        val Gray800 = Color(0xFF232D44)
        val Gray900 = Color(0xFF0F1A33)
    }

    /*
     * Coal / Dark scale
     */

    object Coal {
        val Coal50 = Color(0xFF252A35)
        val Coal100 = Color(0xFF1D222C)
        val Coal200 = Color(0xFF161A22)
        val Coal300 = Color(0xFF13161E)
        val Coal400 = Color(0xFF0F1219)
        val Coal500 = Color(0xFF0C0F15)
        val Coal600 = Color(0xFF090B10)
        val Coal700 = Color(0xFF06080C)
        val Coal800 = Color(0xFF030406)
        val Coal900 = Color(0xFF000000)
    }

    /*
     * Ink / Navy dark scale
     */

    object Ink {
        val Ink50 = Color(0xFF233446)
        val Ink100 = Color(0xFF1C2B3C)
        val Ink200 = Color(0xFF172433)
        val Ink300 = Color(0xFF131E2A)
        val Ink400 = Color(0xFF101923)
        val Ink500 = Color(0xFF0D141E)
        val Ink600 = Color(0xFF0A0F17)
        val Ink700 = Color(0xFF070B11)
        val Ink800 = Color(0xFF05080C)
        val Ink900 = Color(0xFF030507)
    }

    /*
     * Blue scale
     */

    object Blue {
        val Blue50 = Color(0xFFEFF6FF)
        val Blue100 = Color(0xFFDBEAFE)
        val Blue200 = Color(0xFFBFDBFE)
        val Blue300 = Color(0xFF93C5FD)
        val Blue400 = Color(0xFF60A5FA)
        val Blue500 = Color(0xFF2563EB)
        val Blue600 = Color(0xFF1D4ED8)
        val Blue700 = Color(0xFF1E40AF)
        val Blue800 = Color(0xFF1E3A8A)
        val Blue900 = Color(0xFF172554)
    }

    /*
     * Orange scale
     */

    object Orange {
        val Orange50 = Color(0xFFFFF7ED)
        val Orange100 = Color(0xFFFFEDD5)
        val Orange200 = Color(0xFFFED7AA)
        val Orange300 = Color(0xFFFDBA74)
        val Orange400 = Color(0xFFFB923C)
        val Orange500 = Color(0xFFF97316)
        val Orange600 = Color(0xFFEA580C)
        val Orange700 = Color(0xFFC2410C)
        val Orange800 = Color(0xFF9A3412)
        val Orange900 = Color(0xFF7C2D12)
    }

    /*
     * Purple scale
     */

    object Purple {
        val Purple50 = Color(0xFFF5F3FF)
        val Purple100 = Color(0xFFEDE9FE)
        val Purple200 = Color(0xFFDDD6FE)
        val Purple300 = Color(0xFFC4B5FD)
        val Purple400 = Color(0xFFA78BFA)
        val Purple500 = Color(0xFF8B5CF6)
        val Purple600 = Color(0xFF7C3AED)
        val Purple700 = Color(0xFF6D28D9)
        val Purple800 = Color(0xFF5B21B6)
        val Purple900 = Color(0xFF4C1D95)
    }

    /*
     * Pink scale
     */

    object Pink {
        val Pink50 = Color(0xFFFDF2F8)
        val Pink100 = Color(0xFFFCE7F3)
        val Pink200 = Color(0xFFFBCFE8)
        val Pink300 = Color(0xFFF9A8D4)
        val Pink400 = Color(0xFFF472B6)
        val Pink500 = Color(0xFFEC4899)
        val Pink600 = Color(0xFFDB2777)
        val Pink700 = Color(0xFFBE185D)
        val Pink800 = Color(0xFF9D174D)
        val Pink900 = Color(0xFF831843)
    }

    /*
     * Green scale
     */

    object Green {
        val Green50 = Color(0xFFF0FDF4)
        val Green100 = Color(0xFFDCFCE7)
        val Green200 = Color(0xFFBBF7D0)
        val Green300 = Color(0xFF86EFAC)
        val Green400 = Color(0xFF4ADE80)
        val Green500 = Color(0xFF22C55E)
        val Green600 = Color(0xFF16A34A)
        val Green700 = Color(0xFF15803D)
        val Green800 = Color(0xFF166534)
        val Green900 = Color(0xFF14532D)
    }

    /*
     * Turquoise scale
     */

    object Turquoise {
        val Turquoise50 = Color(0xFFECFEFF)
        val Turquoise100 = Color(0xFFCFFAFE)
        val Turquoise200 = Color(0xFFA5F3FC)
        val Turquoise300 = Color(0xFF67E8F9)
        val Turquoise400 = Color(0xFF22D3EE)
        val Turquoise500 = Color(0xFF06B6D4)
        val Turquoise600 = Color(0xFF0891B2)
        val Turquoise700 = Color(0xFF0E7490)
        val Turquoise800 = Color(0xFF155E75)
        val Turquoise900 = Color(0xFF164E63)
    }

    /*
     * Red scale
     */

    object Red {
        val Red50 = Color(0xFFFEF2F2)
        val Red100 = Color(0xFFFEE2E2)
        val Red200 = Color(0xFFFECACA)
        val Red300 = Color(0xFFFCA5A5)
        val Red400 = Color(0xFFF87171)
        val Red500 = Color(0xFFEF4444)
        val Red600 = Color(0xFFDC2626)
        val Red700 = Color(0xFFB91C1C)
        val Red800 = Color(0xFF991B1B)
        val Red900 = Color(0xFF7F1D1D)
    }

    /*
     * Beige scale
     */

    object Beige {
        val Beige50 = Color(0xFFFBFBFA)
        val Beige100 = Color(0xFFF7F5F0)
        val Beige200 = Color(0xFFF1EDE4)
        val Beige300 = Color(0xFFE6DFD1)
        val Beige400 = Color(0xFFD6CAB3)
        val Beige500 = Color(0xFFC2B294)
        val Beige600 = Color(0xFFA89676)
        val Beige700 = Color(0xFF8A795D)
        val Beige800 = Color(0xFF665944)
        val Beige900 = Color(0xFF40382B)
    }
}