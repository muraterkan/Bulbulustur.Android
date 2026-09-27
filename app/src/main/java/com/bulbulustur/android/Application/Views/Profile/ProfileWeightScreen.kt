package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileWeightScreen(
    value: String,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileNumberValueScreen(
        title = "Kilo",
        description = "Kilo bilginizi kilogram olarak girin.",
        label = "Kilo",
        suffix = "kg",
        value = value,
        minimumValue = 30,
        maximumValue = 300,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onValueChange = onValueChange,
        onSaveClick = onSaveClick
    )
}
