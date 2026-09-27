package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileBodyHairPreferenceScreen(
    selectedValue: Boolean?,
    onBackClick: () -> Unit,
    onSelected: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileBooleanPreferenceScreen(
        title = "Vücut Kılı Tercihi",
        description = "Vücut kıllarını çekici bulup bulmadığınızı seçin.",
        selectedValue = selectedValue,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
