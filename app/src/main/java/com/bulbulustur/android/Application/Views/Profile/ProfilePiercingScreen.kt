package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfilePiercingScreen(
    selectedValue: Boolean?,
    onBackClick: () -> Unit,
    onSelected: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileBooleanPreferenceScreen(
        title = "Piercing",
        description = "Piercing bilginizi seçin.",
        selectedValue = selectedValue,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
