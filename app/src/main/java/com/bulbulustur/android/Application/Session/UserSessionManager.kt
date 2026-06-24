package com.bulbulustur.android.Application.Session

import com.bulbulustur.android.Application.Datastore.UserPreferenceDataStore
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import com.bulbulustur.android.businesslayer.Core.Enums.EThemeMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserSessionManager(
    private val userPreferenceDataStore: UserPreferenceDataStore,
    private val coroutineScope: CoroutineScope
) {

    val State: StateFlow<UserSessionState> =
        userPreferenceDataStore.SessionState.stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = UserSessionState()
        )

    fun SetThemeMode(
        themeMode: EThemeMode
    ) {
        coroutineScope.launch {
            userPreferenceDataStore.SetThemeMode(
                themeMode = themeMode
            )
        }
    }

    fun SetLanguage(
        language: EApplicationLanguage
    ) {
        coroutineScope.launch {
            userPreferenceDataStore.SetLanguage(
                language = language
            )
        }
    }

    fun Reset() {
        coroutineScope.launch {
            userPreferenceDataStore.Reset()
        }
    }
}