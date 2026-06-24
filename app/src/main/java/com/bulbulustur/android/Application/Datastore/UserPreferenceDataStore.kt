package com.bulbulustur.android.Application.Datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import com.bulbulustur.android.businesslayer.Core.Enums.EThemeMode
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private const val UserPreferenceDataStoreName = "bulbulustur_user_preferences"

private val Context.UserPreferenceDataStoreInstance: DataStore<Preferences> by preferencesDataStore(
    name = UserPreferenceDataStoreName
)

class UserPreferenceDataStore(
    context: Context
) {

    private val DataStore: DataStore<Preferences> =
        context.applicationContext.UserPreferenceDataStoreInstance

    val SessionState: Flow<UserSessionState> =
        DataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                UserSessionState(
                    IsInitialized = true,
                    ThemeMode = preferences.ReadThemeMode(),
                    Language = preferences.ReadLanguage()
                )
            }

    suspend fun SetThemeMode(
        themeMode: EThemeMode
    ) {
        DataStore.edit { preferences ->
            preferences[PreferenceKeys.ThemeMode] = themeMode.name
        }
    }

    suspend fun SetLanguage(
        language: EApplicationLanguage
    ) {
        DataStore.edit { preferences ->
            preferences[PreferenceKeys.LanguageCode] = language.Code
        }
    }

    suspend fun Reset() {
        DataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private fun Preferences.ReadThemeMode(): EThemeMode {
        val storedValue = this[PreferenceKeys.ThemeMode]

        return storedValue
            ?.let { value ->
                runCatching {
                    EThemeMode.valueOf(value)
                }.getOrNull()
            }
            ?: EThemeMode.System
    }

    private fun Preferences.ReadLanguage(): EApplicationLanguage {
        val storedCode = this[PreferenceKeys.LanguageCode]

        return EApplicationLanguage.FromCode(storedCode)
    }

    private object PreferenceKeys {

        val ThemeMode = stringPreferencesKey(
            name = "theme_mode"
        )

        val LanguageCode = stringPreferencesKey(
            name = "language_code"
        )
    }
}