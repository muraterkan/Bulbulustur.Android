package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileArmpitHairPreferenceScreen(
    selectedValue: Boolean?,
    onBackClick: () -> Unit,
    onSelected: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileBooleanPreferenceScreen(
        title = "Koltuk Altı Kılı Tercihi",
        description = "Koltuk altı kıllarını çekici bulup bulmadığınızı seçin.",
        selectedValue = selectedValue,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
