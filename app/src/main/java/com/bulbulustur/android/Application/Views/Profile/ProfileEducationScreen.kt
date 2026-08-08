package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.runtime.Composable

@Composable
fun ProfileEducationScreen(
    options: List<ProfileAppearanceSelectionOption>,
    selectedId: Int?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onSelected: (Int) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileAppearanceSelectionScreen(
        title = BBLocalization.Current.Get(key = "daf0f071-a537-4ed3-a935-e461cacb51c5", fallback = "Eğitim Düzeyi"),
        description = BBLocalization.Current.Get(key = "92bc3338-d919-40e2-b865-e6590458389a", fallback = "Tamamladığınız veya devam ettiğiniz eğitim düzeyini seçin."),
        options = options,
        selectedId = selectedId,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
