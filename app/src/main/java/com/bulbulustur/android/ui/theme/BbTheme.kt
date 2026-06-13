/*
 * Bulbulustur Android Native Buyer App
 * Design System / Theme
 *
 * This file maps the static Bulbulustur color palette into MaterialTheme.colorScheme.
 *
 * Rules:
 * - BbColors is the static palette layer derived from Bulbulustur Web Main CSS.
 * - BbTheme maps BbColors into Material3 color schemes for Light, Navy and Dark modes.
 * - Feature screens should use MaterialTheme.colorScheme instead of direct BbColors for theme-aware colors.
 * - Direct BbColors usage is valid here because this file defines the theme mapping.
 */

package com.bulbulustur.android.ui.theme

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val BbLightColorScheme = lightColorScheme(
    primary = BbColors.Yellow.Yellow500,
    onPrimary = BbColors.Gray.Gray900,

    secondary = BbColors.Gray.Gray200,
    onSecondary = BbColors.Gray.Gray800,

    tertiary = BbColors.Purple.Purple500,
    onTertiary = BbColors.White,

    background = BbColors.White,
    onBackground = BbColors.Gray.Gray900,

    surface = BbColors.White,
    onSurface = BbColors.Gray.Gray900,

    surfaceVariant = BbColors.Gray.Gray100,
    onSurfaceVariant = BbColors.Gray.Gray700,

    outline = BbColors.Gray.Gray300,
    outlineVariant = BbColors.Gray.Gray200,

    error = BbColors.Red.Red500,
    onError = BbColors.White,

    primaryContainer = BbColors.Yellow.Yellow100,
    onPrimaryContainer = BbColors.Gray.Gray900,

    secondaryContainer = BbColors.Gray.Gray100,
    onSecondaryContainer = BbColors.Gray.Gray800,

    tertiaryContainer = BbColors.Purple.Purple50,
    onTertiaryContainer = BbColors.Purple.Purple700,

    errorContainer = BbColors.Red.Red50,
    onErrorContainer = BbColors.Red.Red700,

    inverseSurface = BbColors.Coal.Coal500,
    inverseOnSurface = BbColors.White,
    inversePrimary = BbColors.Yellow.Yellow400,

    surfaceTint = BbColors.Yellow.Yellow500,
    scrim = BbColors.Black
)

private val BbNavyColorScheme = darkColorScheme(
    primary = BbColors.Yellow.Yellow500,
    onPrimary = BbColors.Ink.Ink900,

    secondary = BbColors.Ink.Ink300,
    onSecondary = BbColors.Gray.Gray50,

    tertiary = BbColors.Purple.Purple400,
    onTertiary = BbColors.Ink.Ink900,

    background = BbColors.Ink.Ink500,
    onBackground = BbColors.Gray.Gray50,

    surface = BbColors.Ink.Ink400,
    onSurface = BbColors.Gray.Gray50,

    surfaceVariant = BbColors.Ink.Ink300,
    onSurfaceVariant = BbColors.Gray.Gray300,

    outline = BbColors.Ink.Ink100,
    outlineVariant = BbColors.Ink.Ink200,

    error = BbColors.Red.Red400,
    onError = BbColors.Ink.Ink900,

    primaryContainer = BbColors.Yellow.Yellow700,
    onPrimaryContainer = BbColors.Ink.Ink900,

    secondaryContainer = BbColors.Ink.Ink200,
    onSecondaryContainer = BbColors.Gray.Gray50,

    tertiaryContainer = BbColors.Purple.Purple800,
    onTertiaryContainer = BbColors.Purple.Purple100,

    errorContainer = BbColors.Red.Red800,
    onErrorContainer = BbColors.Red.Red100,

    inverseSurface = BbColors.Gray.Gray50,
    inverseOnSurface = BbColors.Ink.Ink700,
    inversePrimary = BbColors.Yellow.Yellow600,

    surfaceTint = BbColors.Yellow.Yellow500,
    scrim = BbColors.Black
)

private val BbDarkColorScheme = darkColorScheme(
    primary = BbColors.Yellow.Yellow500,
    onPrimary = BbColors.Coal.Coal900,

    secondary = BbColors.Coal.Coal300,
    onSecondary = BbColors.Gray.Gray50,

    tertiary = BbColors.Purple.Purple400,
    onTertiary = BbColors.Coal.Coal900,

    background = BbColors.Coal.Coal500,
    onBackground = BbColors.Gray.Gray50,

    surface = BbColors.Coal.Coal300,
    onSurface = BbColors.Gray.Gray50,

    surfaceVariant = BbColors.Coal.Coal400,
    onSurfaceVariant = BbColors.Gray.Gray300,

    outline = BbColors.Coal.Coal100,
    outlineVariant = BbColors.Coal.Coal200,

    error = BbColors.Red.Red400,
    onError = BbColors.Coal.Coal900,

    primaryContainer = BbColors.Yellow.Yellow700,
    onPrimaryContainer = BbColors.Coal.Coal900,

    secondaryContainer = BbColors.Coal.Coal200,
    onSecondaryContainer = BbColors.Gray.Gray50,

    tertiaryContainer = BbColors.Purple.Purple800,
    onTertiaryContainer = BbColors.Purple.Purple100,

    errorContainer = BbColors.Red.Red800,
    onErrorContainer = BbColors.Red.Red100,

    inverseSurface = BbColors.Gray.Gray50,
    inverseOnSurface = BbColors.Coal.Coal700,
    inversePrimary = BbColors.Yellow.Yellow600,

    surfaceTint = BbColors.Yellow.Yellow500,
    scrim = BbColors.Black
)

@Composable
fun BbTheme(
    themeMode: BbThemeMode = BbThemeMode.Light,
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = getBbColorScheme(
        themeMode = themeMode,
        useDynamicColor = useDynamicColor,
        context = context
    )

    ApplyBbSystemBars(
        colorScheme = colorScheme,
        themeMode = themeMode
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = BbTypography,
        content = content
    )
}

private fun getBbColorScheme(
    themeMode: BbThemeMode,
    useDynamicColor: Boolean,
    context: Context
): ColorScheme {
    if (useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        return when (themeMode) {
            BbThemeMode.Light -> dynamicLightColorScheme(context)
            BbThemeMode.Navy,
            BbThemeMode.Dark -> dynamicDarkColorScheme(context)
        }
    }

    return when (themeMode) {
        BbThemeMode.Light -> BbLightColorScheme
        BbThemeMode.Navy -> BbNavyColorScheme
        BbThemeMode.Dark -> BbDarkColorScheme
    }
}

@Composable
private fun ApplyBbSystemBars(
    colorScheme: ColorScheme,
    themeMode: BbThemeMode
) {
    val view = LocalView.current

    if (view.isInEditMode) {
        return
    }

    val activity = view.context as? Activity ?: return
    val window = activity.window

    SideEffect {
        val insetsController = WindowCompat.getInsetsController(window, view)

        window.statusBarColor = colorScheme.background.toArgb()
        window.navigationBarColor = colorScheme.background.toArgb()

        insetsController.isAppearanceLightStatusBars = themeMode == BbThemeMode.Light
        insetsController.isAppearanceLightNavigationBars = themeMode == BbThemeMode.Light
    }
}