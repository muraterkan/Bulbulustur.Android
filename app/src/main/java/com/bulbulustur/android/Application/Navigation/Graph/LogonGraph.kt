package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import com.bulbulustur.android.Application.Views.Logon.SetNewPasswordScreen
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.bulbulustur.android.Application.Authentication.GoogleCredentialResult
import com.bulbulustur.android.Application.Authentication.GoogleCredentialService
import com.bulbulustur.android.R
import kotlinx.coroutines.launch

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

        val context =
            LocalContext.current

        val coroutineScope =
            rememberCoroutineScope()

        val googleCredentialService =
            remember(
                context
            ) {
                GoogleCredentialService(
                    context =
                        context
                )
            }

        var isGoogleCredentialLoading by
        remember {
            mutableStateOf(
                false
            )
        }

        val googleWebClientId =
            context.getString(
                R.string.google_web_client_id
            )

        val languageId =
            when (
                sessionState.Language
            ) {
                EApplicationLanguage.Turkish ->
                    1

                EApplicationLanguage.English ->
                    2
            }

        LaunchedEffect(
            logonState.IsLoginSuccessful
        ) {
            if (
                !logonState.IsLoginSuccessful
            ) {
                return@LaunchedEffect
            }

            logonController.ConsumeLoginSuccess()

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

        LoginScreen(
            isLoading =
                logonState.IsLoading ||
                        isGoogleCredentialLoading,
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
                if (
                    !isGoogleCredentialLoading &&
                    !logonState.IsLoading
                ) {
                    logonController.ClearError()

                    isGoogleCredentialLoading =
                        true

                    coroutineScope.launch {
                        try {
                            val credentialResult =
                                googleCredentialService.SignIn(
                                    serverClientId =
                                        googleWebClientId
                                )

                            when (
                                credentialResult
                            ) {
                                is GoogleCredentialResult.Success -> {
                                    logonController.GoogleLoginPost(
                                        idToken =
                                            credentialResult.IdToken,
                                        languageId =
                                            languageId
                                    )
                                }

                                is GoogleCredentialResult.Failure -> {
                                    logonController.SetGoogleLoginError(
                                        message =
                                            credentialResult.Message
                                    )
                                }

                                GoogleCredentialResult.Cancelled -> {
                                    // Kullanıcı Google hesap
                                    // seçimini iptal etti.
                                }
                            }
                        } finally {
                            isGoogleCredentialLoading =
                                false
                        }
                    }
                }
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
        val logonState by
        logonController.State.collectAsState()

        val languageId =
            when (sessionState.Language) {
                EApplicationLanguage.Turkish -> 1
                EApplicationLanguage.English -> 2
            }

        LaunchedEffect(
            Unit
        ) {
            logonController.ForgotPassword()
        }

        ForgotPasswordScreen(
            isLoading =
                logonState.IsSendingForgotPasswordLink,
            errorMessage =
                logonState.ErrorMessage,
            successMessage =
                logonState.ForgotPasswordMessage,
            onSendResetLinkClick = { email ->
                logonController.ForgotPasswordPost(
                    email =
                        email,
                    languageId =
                        languageId
                )
            },
            onInputChanged = {
                logonController.ClearForgotPasswordFeedback()
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
            LogonRoutes.SetNewPassword,
        arguments =
            listOf(
                navArgument(
                    LogonRoutes.SetNewPasswordArgument
                ) {
                    type =
                        NavType.StringType
                }
            )
    ) { backStackEntry ->
        val logonState by
        logonController.State.collectAsState()

        val languageId =
            when (sessionState.Language) {
                EApplicationLanguage.Turkish -> 1
                EApplicationLanguage.English -> 2
            }

        val activationCode =
            backStackEntry.arguments
                ?.getString(
                    LogonRoutes.SetNewPasswordArgument
                )
                ?.trim()
                .orEmpty()

        LaunchedEffect(
            activationCode
        ) {
            if (activationCode.isBlank()) {
                navController.navigate(
                    LogonRoutes.Expired
                ) {
                    popUpTo(
                        LogonRoutes.SetNewPassword
                    ) {
                        inclusive =
                            true
                    }

                    launchSingleTop =
                        true
                }

                return@LaunchedEffect
            }

            logonController.SetNewPassword()
        }

        LaunchedEffect(
            logonState.IsSetNewPasswordSuccessful
        ) {
            if (!logonState.IsSetNewPasswordSuccessful) {
                return@LaunchedEffect
            }

            logonController.ConsumeSetNewPasswordSuccess()

            navController.navigate(
                LogonRoutes.Logon
            ) {
                popUpTo(
                    LogonRoutes.SetNewPassword
                ) {
                    inclusive =
                        true
                }

                launchSingleTop =
                    true
            }
        }

        SetNewPasswordScreen(
            isLoading =
                logonState.IsUpdatingPassword,
            errorMessage =
                logonState.ErrorMessage,
            onUpdatePasswordClick = {
                    newPassword,
                    reNewPassword ->

                logonController.SetNewPasswordPost(
                    activationCode =
                        activationCode,
                    newPassword =
                        newPassword,
                    reNewPassword =
                        reNewPassword,
                    languageId =
                        languageId
                )
            },
            onInputChanged = {
                logonController.ClearSetNewPasswordFeedback()
            },
            onBackToLogonClick = {
                navController.navigate(
                    LogonRoutes.Logon
                ) {
                    popUpTo(
                        LogonRoutes.SetNewPassword
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