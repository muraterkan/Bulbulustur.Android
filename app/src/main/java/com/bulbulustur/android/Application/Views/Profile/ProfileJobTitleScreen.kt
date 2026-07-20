package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileJobTitleScreen(
    value: String,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileTextValueScreen(
        title = "İş Unvanı",
        description = "Çalışma hayatınızdaki iş unvanınızı girin.",
        label = "İş Unvanı",
        value = value,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onValueChange = onValueChange,
        onSaveClick = onSaveClick
    )
}
