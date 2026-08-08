package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Config.LegalPolicyUrls
import com.bulbulustur.android.Application.Controllers.AccountController
import com.bulbulustur.android.Application.Controllers.SettingsController
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.SettingsRoutes
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Views.Account.AboutThisAppScreen
import com.bulbulustur.android.Application.Views.Account.AccountSettingsScreen
import com.bulbulustur.android.Application.Views.Account.AppearanceSettingsScreen
import com.bulbulustur.android.Application.Views.Account.CommunicationPreferenceScreen
import com.bulbulustur.android.Application.Views.Account.CurrencySettingsScreen
import com.bulbulustur.android.Application.Views.Account.LanguageSettingsScreen
import com.bulbulustur.android.Application.Views.Account.LegalPoliciesScreen
import com.bulbulustur.android.Application.Views.Account.RegionSettingsScreen
import com.bulbulustur.android.Application.Views.Account.SystemStatusScreen

fun NavGraphBuilder.settingsGraph(
    navigator: BulbulusturNavigator,
    sessionState: UserSessionState,
    userSessionManager: UserSessionManager,
    accountController: AccountController,
    settingsController: SettingsController
) {
    composable(SettingsRoutes.Home) {
        val uriHandler = LocalUriHandler.current

        val languageName = when (sessionState.Language.Code.lowercase()) {
            "tr" -> BBLocalization.Current.Get(
                key = "0917b779-9fd5-4c09-a77a-7561824c9d2c",
                fallback = "Türkçe"
            )

            "en" -> "English"
            "de" -> "Deutsch"
            "fr" -> "Français"
            "es" -> "Español"
            "it" -> "Italiano"
            "pt" -> "Português"
            "nl" -> "Nederlands"
            "pl" -> "Polski"
            "ru" -> "Русский"
            "ar" -> "العربية"
            else -> sessionState.Language.Code.uppercase()
        }

        val themeName = when (sessionState.ThemeMode.name) {
            "System" -> BBLocalization.Current.Get(
                key = "67364c0d-6d37-47a1-ae5b-9629865d1cae",
                fallback = "Sistem teması"
            )

            "Light" -> BBLocalization.Current.Get(
                key = "fc164907-c843-432c-b165-3e7f93715f57",
                fallback = "Açık tema"
            )

            "Dark" -> BBLocalization.Current.Get(
                key = "5d279884-96b2-4549-b7f1-5d47493c3a59",
                fallback = "Koyu tema"
            )

            else -> sessionState.ThemeMode.name
        }

        AccountSettingsScreen(
            languageName = languageName,
            themeName = themeName,
            countryName = sessionState.CountryName,
            currencyCode = sessionState.CurrencyCode,
            onBackClick = {
                navigator.back()
            },
            onAccountSecurityClick = {
                navigator.navController.navigate(AccountRoutes.Security)
            },
            onPrivacyClick = {
                navigator.navController.navigate(SettingsRoutes.LegalPolicies)
            },
            onPermissionsClick = {},
            onHelpCenterClick = {
                uriHandler.openUri(LegalPolicyUrls.HelpCenter)
            },
            onLanguageClick = {
                navigator.navController.navigate(SettingsRoutes.Language)
            },
            onAppearanceClick = {
                navigator.navController.navigate(SettingsRoutes.Appearance)
            },
            onRegionClick = {
                navigator.navController.navigate(SettingsRoutes.Region)
            },
            onCurrencyClick = {
                navigator.navController.navigate(SettingsRoutes.Currency)
            },
            onCommunicationPreferenceClick = {
                navigator.navController.navigate(SettingsRoutes.Communication)
            },
            onLegalPoliciesClick = {
                navigator.navController.navigate(SettingsRoutes.LegalPolicies)
            },
            onAboutThisAppClick = {
                navigator.navController.navigate(SettingsRoutes.AboutThisApp)
            }
        )
    }

    composable(SettingsRoutes.Communication) {
        val accountState by accountController.State.collectAsState()
        val languageId = sessionState.Language.Id

        LaunchedEffect(languageId, sessionState.MemberId) {
            accountController.GetAccountPreferences(
                languageId = languageId,
                memberId = sessionState.MemberId
            )
        }

        CommunicationPreferenceScreen(
            preferences = accountState.Preferences,
            isLoading = accountState.IsLoading && (
                    accountState.CurrentAction == "GetAccountPreferences" ||
                            accountState.CurrentAction == "SaveAccountPreference"
                    ),
            errorMessage = accountState.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onPreferenceChanged = { preference, preferenceValue ->
                accountController.SaveAccountPreference(
                    memberId = sessionState.MemberId,
                    preference = preference,
                    preferenceValue = preferenceValue,
                    onSuccess = {
                        accountController.GetAccountPreferences(
                            languageId = languageId,
                            memberId = sessionState.MemberId
                        )
                    }
                )
            },
            onRetryClick = {
                accountController.GetAccountPreferences(
                    languageId = languageId,
                    memberId = sessionState.MemberId
                )
            }
        )
    }

    composable(SettingsRoutes.Language) {
        val settingsState by settingsController.State.collectAsState()
        val languageId = sessionState.Language.Id

        LaunchedEffect(languageId) {
            settingsController.GetLanguages(languageId)
        }

        LanguageSettingsScreen(
            languages = settingsState.Languages,
            selectedLanguageId = languageId,
            isLoading = settingsState.IsLoadingLanguages,
            errorMessage = settingsState.LanguageResult
                ?.takeIf { !it.Success }
                ?.Message,
            onLanguageSelected = { selectedLanguageId, selectedLanguageCode ->
                userSessionManager.SetLanguage(
                    languageId = selectedLanguageId,
                    languageCode = selectedLanguageCode
                )
            },
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.Appearance) {
        AppearanceSettingsScreen(
            selectedTheme = sessionState.ThemeMode,
            onThemeSelected = userSessionManager::SetThemeMode,
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.Region) {
        val settingsState by settingsController.State.collectAsState()
        val languageId = sessionState.Language.Id

        LaunchedEffect(languageId) {
            settingsController.GetCountries(languageId)
        }

        RegionSettingsScreen(
            countries = settingsState.Countries,
            selectedCountryId = sessionState.CountryId,
            isLoading = settingsState.IsLoadingCountries,
            errorMessage = settingsState.CountryResult
                ?.takeIf { !it.Success }
                ?.Message,
            onCountrySelected = { country ->
                userSessionManager.SetCountry(
                    countryId = country.AddressCountryId,
                    countryName = country.Content,
                    countryCode = country.IsoShortCode.ifBlank {
                        country.Code
                    }
                )
            },
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.Currency) {
        val settingsState by settingsController.State.collectAsState()
        val languageId = sessionState.Language.Id

        LaunchedEffect(languageId) {
            settingsController.GetCurrencies(languageId)
        }

        CurrencySettingsScreen(
            currencies = settingsState.Currencies,
            selectedCurrencyId = sessionState.CurrencyId,
            selectedCurrencyCode = sessionState.CurrencyCode,
            isLoading = settingsState.IsLoadingCurrencies,
            errorMessage = settingsState.CurrencyResult
                ?.takeIf { !it.Success }
                ?.Message,
            onCurrencySelected = { currency ->
                userSessionManager.SetCurrency(
                    currencyId = currency.SystemDescCurrencyId,
                    currencyCode = currency.IsoCode,
                    currencyName = currency.Content,
                    currencySymbol = currency.CurrencySymbol
                )
            },
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.AboutThisApp) {
        val context = LocalContext.current

        AboutThisAppScreen(
            onBackClick = {
                navigator.back()
            },
            onSystemStatusClick = {
                navigator.navController.navigate(SettingsRoutes.SystemStatus)
            },
            onClearCacheClick = {
                settingsController.ClearApplicationCache(
                    context = context.applicationContext
                )
            }
        )
    }

    composable(SettingsRoutes.SystemStatus) {
        val settingsState by settingsController.State.collectAsState()
        val uriHandler = LocalUriHandler.current

        LaunchedEffect(Unit) {
            settingsController.GetStatusOverview()
        }

        SystemStatusScreen(
            overview = settingsState.StatusOverview,
            isLoading = settingsState.IsLoadingStatus,
            errorMessage = settingsState.StatusOverviewResult
                ?.takeIf { !it.Success }
                ?.Message,
            onBackClick = {
                navigator.back()
            },
            onRetryClick = {
                settingsController.RefreshStatusOverview()
            },
            onOpenStatusPageClick = {
                uriHandler.openUri("https://status.bulbulustur.com")
            }
        )
    }

    composable(SettingsRoutes.LegalPolicies) {
        val uriHandler = LocalUriHandler.current

        LegalPoliciesScreen(
            onBackClick = {
                navigator.back()
            },
            onPolicyClick = { item ->
                uriHandler.openUri(item.url)
            }
        )
    }
}