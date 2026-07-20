package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileTattooScreen(
    selectedValue: Boolean?,
    onBackClick: () -> Unit,
    onSelected: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileBooleanPreferenceScreen(
        title = "Dövme",
        description = "Dövme bilginizi seçin.",
        selectedValue = selectedValue,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
