package com.bulbulustur.android.Application.Localization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun BBLocalizationProvider(
    state: LocalizationState,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalBbLocalization provides state,
        content = content
    )
}