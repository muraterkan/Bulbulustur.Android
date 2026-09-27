package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileHeightScreen(
    value: String,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileNumberValueScreen(
        title = "Boy",
        description = "Boy bilginizi santimetre olarak girin.",
        label = "Boy",
        suffix = "cm",
        value = value,
        minimumValue = 100,
        maximumValue = 250,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onValueChange = onValueChange,
        onSaveClick = onSaveClick
    )
}
