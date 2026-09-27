package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileChildrenPreferenceScreen(
    options: List<ProfileAppearanceSelectionOption>,
    selectedId: Int?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onSelected: (Int) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileAppearanceSelectionScreen(
        title = "Çocuk Tercihi",
        description = "Çocuk sahibi olma konusundaki tercihinizi seçin.",
        options = options,
        selectedId = selectedId,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
