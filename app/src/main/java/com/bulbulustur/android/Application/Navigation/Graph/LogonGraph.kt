package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Authentication.GoogleCredentialResult
import com.bulbulustur.android.Application.Authentication.GoogleCredentialService
import com.bulbulustur.android.Application.Controllers.LogonController
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeController
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeEvent
import com.bulbulustur.android.Application.Views.Logon.ExpiredScreen
import com.bulbulustur.android.Application.Views.Logon.FirstDoorScreen
import com.bulbulustur.android.Application.Views.Logon.ForgotPasswordScreen
import com.bulbulustur.android.Application.Views.Logon.LoginScreen
import com.bulbulustur.android.Application.Views.Logon.RegisterFinalScreen
import com.bulbulustur.android.Application.Views.Logon.RegisterFinalState
import com.bulbulustur.android.Application.Views.Logon.RegisterStartScreen
import com.bulbulustur.android.Application.Views.Logon.SetNewPasswordScreen
import com.bulbulustur.android.R
import com.bulbulustur.android.businesslayer.Core.Model.MemberRegisterModel
import kotlinx.coroutines.launch
import androidx.compose.ui.res.stringResource

fun NavGraphBuilder.logonGraph(
    navController: NavHostController,
    sessionState: UserSessionState,
    logonController: LogonController,
    addressCascadeController: AddressCascadeController
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

        val googleWebClientId = stringResource(R.string.google_web_client_id)

        val languageId = sessionState.Language.Id

        LaunchedEffect(
            logonState.IsLoginSuccessful
        ) {
            if (!logonState.IsLoginSuccessful) {
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

        val languageId = sessionState.Language.Id

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

        val languageId = sessionState.Language.Id

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

        val languageId = sessionState.Language.Id

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

        val addressCascadeState by
        addressCascadeController.State.collectAsState()

        val uuid =
            backStackEntry.arguments
                ?.getString(
                    LogonRoutes.RegisterActivationArgument
                )
                ?.trim()
                .orEmpty()

        val languageId = sessionState.Language.Id

        LaunchedEffect(
            uuid,
            languageId
        ) {
            if (uuid.isBlank()) {
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

                return@LaunchedEffect
            }

            logonController.ClearMemberTemp()

            addressCascadeController.OnEvent(
                AddressCascadeEvent.Clear
            )

            logonController.GetMemberTempByActivationCode(
                activationCode =
                    uuid,
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

        LaunchedEffect(
            logonState.MemberTemp?.Email,
            languageId
        ) {
            val memberTemp =
                logonState.MemberTemp

            if (
                memberTemp != null &&
                !addressCascadeState.IsInitialized
            ) {
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SetInitialSelection(
                        Selection =
                            addressCascadeState.Selection,
                        LanguageId =
                            languageId
                    )
                )
            }
        }

        LaunchedEffect(
            logonState.IsRegisterSuccessful
        ) {
            if (!logonState.IsRegisterSuccessful) {
                return@LaunchedEffect
            }

            addressCascadeController.OnEvent(
                AddressCascadeEvent.Clear
            )

            navController.navigate(
                LogonRoutes.RegisterFinal
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
                    addressCascadeState =
                        addressCascadeState,
                    isRegisterLoading =
                        logonState.IsRegistering,
                    registerErrorMessage =
                        logonState.ErrorMessage,
                    onCountrySelected = { countryId ->
                        addressCascadeController.OnEvent(
                            AddressCascadeEvent.SelectCountry(
                                CountryId =
                                    countryId,
                                LanguageId =
                                    languageId
                            )
                        )
                    },
                    onCountryStateSelected = { countryStateId ->
                        addressCascadeController.OnEvent(
                            AddressCascadeEvent.SelectCountryState(
                                CountryStateId =
                                    countryStateId,
                                LanguageId =
                                    languageId
                            )
                        )
                    },
                    onCountryDepartmentSelected = { countryDepartmentId ->
                        addressCascadeController.OnEvent(
                            AddressCascadeEvent.SelectCountryDepartment(
                                CountryDepartmentId =
                                    countryDepartmentId,
                                LanguageId =
                                    languageId
                            )
                        )
                    },
                    onCitySelected = { cityId ->
                        addressCascadeController.OnEvent(
                            AddressCascadeEvent.SelectCity(
                                CityId =
                                    cityId,
                                LanguageId =
                                    languageId
                            )
                        )
                    },
                    onDistrictSelected = { districtId ->
                        addressCascadeController.OnEvent(
                            AddressCascadeEvent.SelectDistrict(
                                DistrictId =
                                    districtId
                            )
                        )
                    },
                    onContinueClick = { form ->
                        logonController.RegisterPost(
                            model =
                                MemberRegisterModel(
                                    Email =
                                        form.Email,
                                    Name =
                                        form.Name,
                                    Surname =
                                        form.Surname,
                                    Password =
                                        form.Password,
                                    PasswordAgain =
                                        form.PasswordAgain,
                                    ActivationCode =
                                        memberTemp.ActivationCode,
                                    CountryId =
                                        form.CountryId,
                                    CountryStateId =
                                        form.CountryStateId,
                                    CountryDepartmentId =
                                        form.CountryDepartmentId,
                                    CityId =
                                        form.CityId,
                                    DistrictId =
                                        form.DistrictId,
                                    LoginProvider =
                                        "Email",
                                    Uuid =
                                        uuid,
                                    MemberSecureKey =
                                        "",
                                    LanguageId =
                                        languageId
                                ),
                            languageId =
                                languageId
                        )
                    },
                    onBackToLogonClick = {
                        logonController.ClearMemberTemp()

                        addressCascadeController.OnEvent(
                            AddressCascadeEvent.Clear
                        )

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
            LogonRoutes.RegisterFinal
    ) {
        val logonState by
        logonController.State.collectAsState()

        val isRegistrationCompleted =
            logonState.RegisteredMember != null

        RegisterFinalScreen(
            email =
                logonState.RegisteredEmail.ifBlank {
                    logonState.FirstDoorEmail
                },
            finalState =
                if (isRegistrationCompleted) {
                    RegisterFinalState.Completed
                } else {
                    RegisterFinalState.WaitingEmailVerification
                },
            onGoToLogonClick = {
                logonController.ConsumeRegisterSuccess()

                navController.navigate(
                    LogonRoutes.Logon
                ) {
                    popUpTo(
                        LogonRoutes.RegisterFinal
                    ) {
                        inclusive =
                            true
                    }

                    launchSingleTop =
                        true
                }
            },
            onResendVerificationClick = {
                val email =
                    logonState.FirstDoorEmail

                if (email.isNotBlank()) {
                    val languageId = sessionState.Language.Id

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

                addressCascadeController.OnEvent(
                    AddressCascadeEvent.Clear
                )

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

                addressCascadeController.OnEvent(
                    AddressCascadeEvent.Clear
                )

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