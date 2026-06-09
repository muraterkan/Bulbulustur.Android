package com.bulbulustur.android.features.account.settings

import androidx.compose.runtime.Composable
import com.bulbulustur.android.features.account.components.AccountPageScaffold

@Composable
fun RegionSettingsScreen(
    onBackClick: () -> Unit = {}
) {
    AccountPageScaffold(
        title = "Ülke ve Bölge",
        kicker = "Bölgesel Ayarlar",
        description = "Ülke, bölge ve yerel tercihlerinizi buradan yönetin.",
        backButtonText = "Ayarlara Dön",
        onBackClick = onBackClick
    ) {
    }
}