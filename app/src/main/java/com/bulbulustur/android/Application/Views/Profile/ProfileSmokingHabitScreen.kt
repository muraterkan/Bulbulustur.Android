package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileSmokingHabitScreen(
    options: List<ProfileAppearanceSelectionOption>,
    selectedId: Int?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onSelected: (Int) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileAppearanceSelectionScreen(
        title = "Sigara",
        description = "Sigara kullanım alışkanlığınızı seçin.",
        options = options,
        selectedId = selectedId,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
