package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.runtime.Composable

@Composable
fun ProfileProfessionScreen(
    value: String,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileTextValueScreen(
        title = BBLocalization.Current.Get(key = "5b1fbdba-1161-4716-966e-e40a815df70f", fallback = "Meslek"),
        description = BBLocalization.Current.Get(key = "57524fef-7052-4c59-ab05-935ea26c0ce3", fallback = "Meslek bilginizi girin."),
        label = BBLocalization.Current.Get(key = "5b1fbdba-1161-4716-966e-e40a815df70f", fallback = "Meslek"),
        value = value,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onValueChange = onValueChange,
        onSaveClick = onSaveClick
    )
}
