package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Controllers.AccountController
import com.bulbulustur.android.Application.Controllers.SettingsController
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
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalContext
import com.bulbulustur.android.Application.Config.LegalPolicyUrls

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
            "tr" -> "Türkçe"
            "en" -> "English"
            else -> sessionState.Language.Code.uppercase()
        }

        val themeName = when (sessionState.ThemeMode.name) {
            "System" -> "Sistem teması"
            "Light" -> "Açık tema"
            "Dark" -> "Koyu tema"
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
                navigator.navController.navigate(
                    AccountRoutes.Security
                )
            },
            onPrivacyClick = {
                navigator.navController.navigate(
                    SettingsRoutes.LegalPolicies
                )
            },
            onPermissionsClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Communication
                )
            },
            onHelpCenterClick = {
                uriHandler.openUri(LegalPolicyUrls.HelpCenter)
            },
            onLanguageClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Language
                )
            },
            onAppearanceClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Appearance
                )
            },
            onRegionClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Region
                )
            },
            onCurrencyClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Currency
                )
            },
            onCommunicationPreferenceClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Communication
                )
            },
            onLegalPoliciesClick = {
                navigator.navController.navigate(
                    SettingsRoutes.LegalPolicies
                )
            },
            onAboutThisAppClick = {
                navigator.navController.navigate(
                    SettingsRoutes.AboutThisApp
                )
            }
        )
    }

    composable(SettingsRoutes.Language) {
        val settingsState by settingsController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

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
            onLanguageSelected = { selectedLanguage ->
                when (selectedLanguage.SystemDescLanguageId) {
                    1 -> userSessionManager.SetLanguage(
                        EApplicationLanguage.Turkish
                    )

                    2 -> userSessionManager.SetLanguage(
                        EApplicationLanguage.English
                    )
                }
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

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

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

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

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

    composable(SettingsRoutes.Communication) {
        val accountState by accountController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        LaunchedEffect(languageId, sessionState.MemberId) {
            accountController.GetMember(
                languageId = languageId,
                memberId = sessionState.MemberId
            )
        }

        CommunicationPreferenceScreen(
            member = accountState.MemberUpdateResult?.Data,
            isLoading = accountState.IsLoading &&
                    accountState.CurrentAction == "GetMember",
            isSaving = accountState.IsContactPreferenceSaving,
            errorMessage = accountState.ErrorMessage,
            isSaved = accountState.IsContactPreferenceSaved,
            onBackClick = {
                navigator.back()
            },
            onSaveClick = { emailAllowed, smsAllowed, phoneAllowed ->
                accountController.SetContactPreference(
                    memberId = sessionState.MemberId,
                    emailPreference = if (emailAllowed) 1 else 0,
                    smsPreference = if (smsAllowed) 1 else 0,
                    phonePreference = if (phoneAllowed) 1 else 0
                )
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
                navigator.navController.navigate(
                    SettingsRoutes.SystemStatus
                )
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
