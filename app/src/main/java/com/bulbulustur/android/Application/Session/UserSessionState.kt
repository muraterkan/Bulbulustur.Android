package com.bulbulustur.android.Application.Session

import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import com.bulbulustur.android.businesslayer.Core.Enums.EThemeMode

data class UserSessionState(
    val IsInitialized: Boolean = false,
    val ThemeMode: EThemeMode = EThemeMode.System,
    val Language: EApplicationLanguage = EApplicationLanguage.Turkish
)