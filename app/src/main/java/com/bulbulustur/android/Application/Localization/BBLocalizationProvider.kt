package com.bulbulustur.android.Application.Localization

import androidx.compose.runtime.Composable

@Composable
fun BBLocalizationProvider(
    state: LocalizationState,
    content: @Composable () -> Unit
) {
    BBLocalization.Bind(state)
    content()
}