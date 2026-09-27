package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileReligionScreen(
    options: List<ProfileAppearanceSelectionOption>,
    selectedId: Int?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onSelected: (Int) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileAppearanceSelectionScreen(
        title = "İnanç",
        description = "İnanç bilginizi seçin.",
        options = options,
        selectedId = selectedId,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
