package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Controllers.SettingsController
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.SplashRoutes
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Views.Home.ModeSelectionScreen

fun NavGraphBuilder.splashGraph(
    navigator: BulbulusturNavigator,
    sessionState: UserSessionState,
    settingsController: SettingsController,
    userSessionManager: UserSessionManager
) {
    composable(SplashRoutes.ModeSelection) {
        val settingsState by settingsController.State.collectAsState()
        val languageId = sessionState.Language.Id

        LaunchedEffect(languageId) {
            settingsController.GetLanguages(languageId)
        }

        ModeSelectionScreen(
            languages = settingsState.Languages,
            selectedLanguageId = languageId,
            isLanguageLoading = settingsState.IsLoadingLanguages,
            languageErrorMessage = settingsState.LanguageResult
                ?.takeIf { !it.Success }
                ?.Message,
            onLanguageSelected = { selectedLanguageId, selectedLanguageCode ->
                userSessionManager.SetLanguage(
                    languageId = selectedLanguageId,
                    languageCode = selectedLanguageCode
                )
            },
            onRetailClick = {
                navigator.navigateFromModeSelectionToRetail()
            },
            onWholesaleClick = {
                navigator.navigateFromModeSelectionToWholesale()
            },
            onRfqClick = {
                navigator.navigateToWholesaleRfqCreate()
            }
        )
    }
}