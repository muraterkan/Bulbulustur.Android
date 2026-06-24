/*
 * Bulbulustur Android Native Buyer App
 * Design System / Theme
 *
 * This file maps the static Bulbulustur color palette into MaterialTheme.colorScheme.
 *
 * Rules:
 * - BBColors is the static palette layer derived from Bulbulustur Web Main CSS.
 * - BbTheme maps BBColors into Material3 color schemes for System, Light and Dark modes.
 * - Feature screens should use MaterialTheme.colorScheme instead of direct BBColors for theme-aware colors.
 * - Direct BBColors usage is valid here because this file defines the theme mapping.
 */

package com.bulbulustur.android.Application.wwwroot.Theme

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.Enums.EThemeMode

private val BbLightColorScheme = lightColorScheme(
    primary = BBColors.Yellow.Yellow500,
    onPrimary = BBColors.Gray.Gray900,

    secondary = BBColors.Gray.Gray200,
    onSecondary = BBColors.Gray.Gray800,

    tertiary = BBColors.Purple.Purple500,
    onTertiary = BBColors.White,

    background = BBColors.White,
    onBackground = BBColors.Gray.Gray900,

    surface = BBColors.White,
    onSurface = BBColors.Gray.Gray900,

    surfaceVariant = BBColors.Gray.Gray100,
    onSurfaceVariant = BBColors.Gray.Gray700,

    outline = BBColors.Gray.Gray300,
    outlineVariant = BBColors.Gray.Gray200,

    error = BBColors.Red.Red500,
    onError = BBColors.White,

    primaryContainer = BBColors.Yellow.Yellow100,
    onPrimaryContainer = BBColors.Gray.Gray900,

    secondaryContainer = BBColors.Gray.Gray100,
    onSecondaryContainer = BBColors.Gray.Gray800,

    tertiaryContainer = BBColors.Purple.Purple50,
    onTertiaryContainer = BBColors.Purple.Purple700,

    errorContainer = BBColors.Red.Red50,
    onErrorContainer = BBColors.Red.Red700,

    inverseSurface = BBColors.Coal.Coal500,
    inverseOnSurface = BBColors.White,
    inversePrimary = BBColors.Yellow.Yellow400,

    surfaceTint = BBColors.Yellow.Yellow500,
    scrim = BBColors.Black
)

private val BbDarkColorScheme = darkColorScheme(
    primary = BBColors.Yellow.Yellow500,
    onPrimary = BBColors.Coal.Coal900,

    secondary = BBColors.Coal.Coal300,
    onSecondary = BBColors.Gray.Gray50,

    tertiary = BBColors.Purple.Purple400,
    onTertiary = BBColors.Coal.Coal900,

    background = BBColors.Coal.Coal500,
    onBackground = BBColors.Gray.Gray50,

    surface = BBColors.Coal.Coal300,
    onSurface = BBColors.Gray.Gray50,

    surfaceVariant = BBColors.Coal.Coal400,
    onSurfaceVariant = BBColors.Gray.Gray300,

    outline = BBColors.Coal.Coal100,
    outlineVariant = BBColors.Coal.Coal200,

    error = BBColors.Red.Red400,
    onError = BBColors.Coal.Coal900,

    primaryContainer = BBColors.Yellow.Yellow700,
    onPrimaryContainer = BBColors.Coal.Coal900,

    secondaryContainer = BBColors.Coal.Coal200,
    onSecondaryContainer = BBColors.Gray.Gray50,

    tertiaryContainer = BBColors.Purple.Purple800,
    onTertiaryContainer = BBColors.Purple.Purple100,

    errorContainer = BBColors.Red.Red800,
    onErrorContainer = BBColors.Red.Red100,

    inverseSurface = BBColors.Gray.Gray50,
    inverseOnSurface = BBColors.Coal.Coal700,
    inversePrimary = BBColors.Yellow.Yellow600,

    surfaceTint = BBColors.Yellow.Yellow500,
    scrim = BBColors.Black
)

@Composable
fun BbTheme(
    themeMode: EThemeMode = EThemeMode.System,
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val resolvedThemeMode = when (themeMode) {
        EThemeMode.System -> {
            if (isSystemInDarkTheme()) {
                EThemeMode.Dark
            } else {
                EThemeMode.Light
            }
        }

        EThemeMode.Light -> EThemeMode.Light
        EThemeMode.Dark -> EThemeMode.Dark
    }

    val colorScheme = getBbColorScheme(
        themeMode = resolvedThemeMode,
        useDynamicColor = useDynamicColor,
        context = context
    )

    ApplyBbSystemBars(
        colorScheme = colorScheme,
        themeMode = resolvedThemeMode
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = BbTypography,
        content = content
    )
}

private fun getBbColorScheme(
    themeMode: EThemeMode,
    useDynamicColor: Boolean,
    context: Context
): ColorScheme {
    if (useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        return when (themeMode) {
            EThemeMode.System,
            EThemeMode.Light -> dynamicLightColorScheme(context)

            EThemeMode.Dark -> dynamicDarkColorScheme(context)
        }
    }

    return when (themeMode) {
        EThemeMode.System,
        EThemeMode.Light -> BbLightColorScheme

        EThemeMode.Dark -> BbDarkColorScheme
    }
}

@Composable
private fun ApplyBbSystemBars(
    colorScheme: ColorScheme,
    themeMode: EThemeMode
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

        insetsController.isAppearanceLightStatusBars = themeMode == EThemeMode.Light
        insetsController.isAppearanceLightNavigationBars = themeMode == EThemeMode.Light
    }
}