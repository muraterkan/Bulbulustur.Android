package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Controllers.LogonController
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Views.Logon.ExpiredScreen
import com.bulbulustur.android.Application.Views.Logon.FirstDoorScreen
import com.bulbulustur.android.Application.Views.Logon.FirstDoorType
import com.bulbulustur.android.Application.Views.Logon.ForgotPasswordScreen
import com.bulbulustur.android.Application.Views.Logon.LoginScreen
import com.bulbulustur.android.Application.Views.Logon.RegisterFinalScreen
import com.bulbulustur.android.Application.Views.Logon.RegisterStartScreen
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage

fun NavGraphBuilder.logonGraph(
    navController: NavHostController,
    sessionState: UserSessionState,
    logonController: LogonController
) {
    composable(
        route = LogonRoutes.Logon
    ) {
        val logonState by
        logonController.State.collectAsState()

        val languageId =
            when (sessionState.Language) {
                EApplicationLanguage.Turkish -> 1
                EApplicationLanguage.English -> 2
            }

        LaunchedEffect(
            logonState.IsLoginSuccessful
        ) {
            if (!logonState.IsLoginSuccessful) {
                return@LaunchedEffect
            }

            logonController.ConsumeLoginSuccess()

            val returnedToPreviousScreen =
                navController.popBackStack()

            if (!returnedToPreviousScreen) {
                navController.navigate(
                    RetailRoutes.Home
                ) {
                    popUpTo(
                        LogonRoutes.Logon
                    ) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }
        }

        LoginScreen(
            isLoading =
                logonState.IsLoading,
            errorMessage =
                logonState.ErrorMessage,
            onLogonClick = {
                    email,
                    password ->

                logonController.LoginPost(
                    email = email,
                    password = password,
                    languageId = languageId
                )
            },
            onInputChanged = {
                logonController.ClearError()
            },
            onForgotPasswordClick = {
                navController.navigate(
                    LogonRoutes.ForgotPassword
                )
            },
            onRegisterClick = {
                navController.navigate(
                    LogonRoutes.FirstDoor
                )
            },
            onGoogleClick = {
            },
            onFacebookClick = {
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route = LogonRoutes.ForgotPassword
    ) {
        ForgotPasswordScreen(
            onSendResetLinkClick = {
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route = LogonRoutes.FirstDoor
    ) {
        FirstDoorScreen(
            onContinueClick = { selectedDoor ->
                when (selectedDoor) {
                    FirstDoorType.IndividualBuyer,
                    FirstDoorType.CompanyBuyer -> {
                        navController.navigate(
                            LogonRoutes.RegisterStart
                        )
                    }

                    FirstDoorType.ExistingAccount -> {
                        navController.popBackStack(
                            route = LogonRoutes.Logon,
                            inclusive = false
                        )
                    }
                }
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route = LogonRoutes.RegisterStart
    ) {
        RegisterStartScreen(
            onContinueClick = {
                navController.navigate(
                    LogonRoutes.RegisterFinal
                )
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route = LogonRoutes.RegisterFinal
    ) {
        RegisterFinalScreen(
            onGoToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onResendVerificationClick = {
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route = LogonRoutes.Expired
    ) {
        ExpiredScreen(
            onSendAgainClick = {
            },
            onGoToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onLanguageClick = {
            }
        )
    }
}