package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Controllers.LogonController
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Views.Logon.ExpiredScreen
import com.bulbulustur.android.Application.Views.Logon.FirstDoorScreen
import com.bulbulustur.android.Application.Views.Logon.ForgotPasswordScreen
import com.bulbulustur.android.Application.Views.Logon.LoginScreen
import com.bulbulustur.android.Application.Views.Logon.RegisterFinalScreen
import com.bulbulustur.android.Application.Views.Logon.RegisterFinalState
import com.bulbulustur.android.Application.Views.Logon.RegisterStartScreen
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage

fun NavGraphBuilder.logonGraph(
    navController: NavHostController,
    sessionState: UserSessionState,
    logonController: LogonController
) {
    composable(
        route =
            LogonRoutes.Logon
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
                        inclusive =
                            true
                    }

                    launchSingleTop =
                        true
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
                    email =
                        email,
                    password =
                        password,
                    languageId =
                        languageId
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
        route =
            LogonRoutes.ForgotPassword
    ) {
        ForgotPasswordScreen(
            onSendResetLinkClick = {
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route =
                        LogonRoutes.Logon,
                    inclusive =
                        false
                )
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route =
            LogonRoutes.FirstDoor
    ) {
        val logonState by
        logonController.State.collectAsState()

        val languageId =
            when (sessionState.Language) {
                EApplicationLanguage.Turkish -> 1
                EApplicationLanguage.English -> 2
            }

        LaunchedEffect(
            logonState.IsFirstDoorSuccessful
        ) {
            if (!logonState.IsFirstDoorSuccessful) {
                return@LaunchedEffect
            }

            logonController.ConsumeFirstDoorSuccess()

            navController.navigate(
                LogonRoutes.RegisterFinal
            ) {
                launchSingleTop =
                    true
            }
        }

        FirstDoorScreen(
            isLoading =
                logonState.IsSendingFirstDoorEmail,
            errorMessage =
                logonState.ErrorMessage,
            onContinueClick = { email ->
                logonController.FirstDoorPost(
                    email =
                        email,
                    languageId =
                        languageId
                )
            },
            onInputChanged = {
                logonController.ClearError()
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route =
                        LogonRoutes.Logon,
                    inclusive =
                        false
                )
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route =
            LogonRoutes.RegisterActivation,
        arguments =
            listOf(
                navArgument(
                    LogonRoutes.RegisterActivationArgument
                ) {
                    type =
                        NavType.StringType
                }
            )
    ) { backStackEntry ->
        val logonState by
        logonController.State.collectAsState()

        val activationCode =
            backStackEntry.arguments
                ?.getString(
                    LogonRoutes.RegisterActivationArgument
                )
                .orEmpty()

        val languageId =
            when (sessionState.Language) {
                EApplicationLanguage.Turkish -> 1
                EApplicationLanguage.English -> 2
            }

        LaunchedEffect(
            activationCode,
            languageId
        ) {
            logonController.ClearMemberTemp()

            logonController.GetMemberTempByActivationCode(
                activationCode =
                    activationCode,
                languageId =
                    languageId
            )

            logonController.LoadCountries(
                languageId =
                    languageId
            )
        }

        LaunchedEffect(
            logonState.CurrentAction,
            logonState.IsMemberTempLoading,
            logonState.IsMemberTempLoaded,
            logonState.ErrorMessage
        ) {
            val memberTempRequestFailed =
                logonState.CurrentAction ==
                        "GetMemberTempByActivationCode" &&
                        !logonState.IsMemberTempLoading &&
                        !logonState.IsMemberTempLoaded &&
                        !logonState.ErrorMessage.isNullOrBlank()

            if (!memberTempRequestFailed) {
                return@LaunchedEffect
            }

            navController.navigate(
                LogonRoutes.Expired
            ) {
                popUpTo(
                    LogonRoutes.RegisterActivation
                ) {
                    inclusive =
                        true
                }

                launchSingleTop =
                    true
            }
        }

        val memberTemp =
            logonState.MemberTemp

        when {
            logonState.IsMemberTempLoading -> {
                Box(
                    modifier =
                        Modifier.fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            logonState.IsMemberTempLoaded &&
                    memberTemp != null -> {

                RegisterStartScreen(
                    verifiedEmail =
                        memberTemp.Email,
                    countries =
                        logonState.Countries,
                    cities =
                        logonState.Cities,
                    selectedCountryId =
                        logonState.SelectedCountryId,
                    selectedCityId =
                        logonState.SelectedCityId,
                    isCountriesLoading =
                        logonState.IsCountriesLoading,
                    isCitiesLoading =
                        logonState.IsCitiesLoading,
                    countryError =
                        logonState.CountryError,
                    cityError =
                        logonState.CityError,
                    onCountrySelected = { countryId ->
                        logonController.SelectCountry(
                            countryId =
                                countryId
                        )
                    },
                    onCitySelected = { cityId ->
                        logonController.SelectCity(
                            cityId =
                                cityId
                        )
                    },
                    onContinueClick = {
                        // Register API bağlantısı bir sonraki aşamada.
                    },
                    onBackToLogonClick = {
                        logonController.ClearMemberTemp()

                        navController.navigate(
                            LogonRoutes.Logon
                        ) {
                            popUpTo(
                                LogonRoutes.RegisterActivation
                            ) {
                                inclusive =
                                    true
                            }

                            launchSingleTop =
                                true
                        }
                    },
                    onLanguageClick = {
                    }
                )
            }

            else -> {
                Box(
                    modifier =
                        Modifier.fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    composable(
        route =
            LogonRoutes.RegisterStart
    ) {
        RegisterStartScreen(
            onContinueClick = {
                navController.navigate(
                    LogonRoutes.RegisterFinal
                )
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route =
                        LogonRoutes.Logon,
                    inclusive =
                        false
                )
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route =
            LogonRoutes.RegisterFinal
    ) {
        val logonState by
        logonController.State.collectAsState()

        RegisterFinalScreen(
            email =
                logonState.FirstDoorEmail,
            finalState =
                RegisterFinalState.WaitingEmailVerification,
            onGoToLogonClick = {
                navController.popBackStack(
                    route =
                        LogonRoutes.Logon,
                    inclusive =
                        false
                )
            },
            onResendVerificationClick = {
                val email =
                    logonState.FirstDoorEmail

                if (email.isNotBlank()) {
                    val languageId =
                        when (sessionState.Language) {
                            EApplicationLanguage.Turkish -> 1
                            EApplicationLanguage.English -> 2
                        }

                    logonController.FirstDoorPost(
                        email =
                            email,
                        languageId =
                            languageId
                    )
                }
            },
            onLanguageClick = {
            }
        )
    }

    composable(
        route =
            LogonRoutes.Expired
    ) {
        ExpiredScreen(
            onSendAgainClick = {
                logonController.ClearMemberTemp()

                navController.navigate(
                    LogonRoutes.FirstDoor
                ) {
                    popUpTo(
                        LogonRoutes.Expired
                    ) {
                        inclusive =
                            true
                    }

                    launchSingleTop =
                        true
                }
            },
            onGoToLogonClick = {
                logonController.ClearMemberTemp()

                navController.navigate(
                    LogonRoutes.Logon
                ) {
                    popUpTo(
                        LogonRoutes.Expired
                    ) {
                        inclusive =
                            true
                    }

                    launchSingleTop =
                        true
                }
            },
            onLanguageClick = {
            }
        )
    }
}