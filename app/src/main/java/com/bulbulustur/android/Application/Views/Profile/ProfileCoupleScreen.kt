package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileCoupleScreen(
    selectedValue: Boolean?,
    onBackClick: () -> Unit,
    onSelected: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {
    ProfileBooleanPreferenceScreen(
        title = "Çift Profili",
        description = "Profilinizi çift profili olarak kullanıp kullanmadığınızı seçin.",
        selectedValue = selectedValue,
        onBackClick = onBackClick,
        onSelected = onSelected,
        onSaveClick = onSaveClick
    )
}
