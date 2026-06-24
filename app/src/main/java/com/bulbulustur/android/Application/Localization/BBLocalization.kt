package com.bulbulustur.android.Application.Localization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalBbLocalization =
    staticCompositionLocalOf {
        LocalizationState(
            IsInitialized = true
        )
    }

object BBLocalization {

    val Current: LocalizationState
        @Composable
        @ReadOnlyComposable
        get() = LocalBbLocalization.current
}