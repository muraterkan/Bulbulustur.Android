package com.bulbulustur.android.Application.Datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
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
                    Language = preferences.ReadLanguage(),
                    CountryId = preferences[PreferenceKeys.CountryId] ?: 0,
                    CountryName = preferences[PreferenceKeys.CountryName] ?: "Türkiye",
                    CountryCode = preferences[PreferenceKeys.CountryCode] ?: "TR",
                    CurrencyId = preferences[PreferenceKeys.CurrencyId] ?: 0,
                    CurrencyCode = preferences[PreferenceKeys.CurrencyCode] ?: "TRY",
                    CurrencyName = preferences[PreferenceKeys.CurrencyName] ?: "Türk Lirası",
                    CurrencySymbol = preferences[PreferenceKeys.CurrencySymbol] ?: "₺"
                )
            }

    suspend fun SetThemeMode(themeMode: EThemeMode) {
        DataStore.edit { preferences ->
            preferences[PreferenceKeys.ThemeMode] = themeMode.name
        }
    }

    suspend fun SetLanguage(language: EApplicationLanguage) {
        DataStore.edit { preferences ->
            preferences[PreferenceKeys.LanguageCode] = language.Code
        }
    }

    suspend fun SetCountry(countryId: Int, countryName: String, countryCode: String) {
        DataStore.edit { preferences ->
            preferences[PreferenceKeys.CountryId] = countryId
            preferences[PreferenceKeys.CountryName] = countryName
            preferences[PreferenceKeys.CountryCode] = countryCode
        }
    }

    suspend fun SetCurrency(
        currencyId: Int,
        currencyCode: String,
        currencyName: String,
        currencySymbol: String
    ) {
        DataStore.edit { preferences ->
            preferences[PreferenceKeys.CurrencyId] = currencyId
            preferences[PreferenceKeys.CurrencyCode] = currencyCode
            preferences[PreferenceKeys.CurrencyName] = currencyName
            preferences[PreferenceKeys.CurrencySymbol] = currencySymbol
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

        val ThemeMode = stringPreferencesKey("theme_mode")
        val LanguageCode = stringPreferencesKey("language_code")

        val CountryId = intPreferencesKey("country_id")
        val CountryName = stringPreferencesKey("country_name")
        val CountryCode = stringPreferencesKey("country_code")

        val CurrencyId = intPreferencesKey("currency_id")
        val CurrencyCode = stringPreferencesKey("currency_code")
        val CurrencyName = stringPreferencesKey("currency_name")
        val CurrencySymbol = stringPreferencesKey("currency_symbol")
    }
}