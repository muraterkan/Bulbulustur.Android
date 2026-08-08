package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Localization.BBLocalization

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
        title = BBLocalization.Current.Get(key = "0c9548f7-abe3-4c0b-9b3c-db52d490e8ed", fallback = "İş Unvanı"),
        description = BBLocalization.Current.Get(key = "3739dde5-123d-410e-aa5d-9ad732c44d3c", fallback = "Çalışma hayatınızdaki iş unvanınızı girin."),
        label = BBLocalization.Current.Get(key = "0c9548f7-abe3-4c0b-9b3c-db52d490e8ed", fallback = "İş Unvanı"),
        value = value,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onValueChange = onValueChange,
        onSaveClick = onSaveClick
    )
}
