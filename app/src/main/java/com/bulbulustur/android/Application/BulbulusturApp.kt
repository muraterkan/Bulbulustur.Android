package com.bulbulustur.android.Application

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bulbulustur.android.Application.Controllers.LogonController
import com.bulbulustur.android.Application.Datastore.UserPreferenceDataStore
import com.bulbulustur.android.Application.Localization.BBLocalizationProvider
import com.bulbulustur.android.Application.Localization.LocalizationManager
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Graph.accountGraph
import com.bulbulustur.android.Application.Navigation.Graph.companyGraph
import com.bulbulustur.android.Application.Navigation.Graph.logonGraph
import com.bulbulustur.android.Application.Navigation.Graph.messageGraph
import com.bulbulustur.android.Application.Navigation.Graph.orderGraph
import com.bulbulustur.android.Application.Navigation.Graph.retailGraph
import com.bulbulustur.android.Application.Navigation.Graph.settingsGraph
import com.bulbulustur.android.Application.Navigation.Graph.splashGraph
import com.bulbulustur.android.Application.Navigation.Graph.wholesaleGraph
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Navigation.Routes.SplashRoutes
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Views.Shared.Components.BuyerModeSheet
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.Enums.EBuyerMode
import com.bulbulustur.android.businesslayer.Core.Repository.AuthenticationRepository
import com.bulbulustur.android.businesslayer.Core.Repository.LocalizationRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberTempRepository
import com.bulbulustur.android.businesslayer.Core.Security.SecureTokenStore
import com.bulbulustur.android.businesslayer.Core.Util.Execute.ExecuteService
import com.bulbulustur.android.businesslayer.Core.Repository.AddressCityRepository
import com.bulbulustur.android.businesslayer.Core.Repository.AddressCountryRepository
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCityRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryRepository

@Composable
fun BulbulusturApp(
    appLinkUrl: String? = null,
    onAppLinkConsumed: () -> Unit = {}
) {
    val context =
        LocalContext.current

    val coroutineScope =
        rememberCoroutineScope()

    val userPreferenceDataStore =
        remember(
            context
        ) {
            UserPreferenceDataStore(
                context =
                    context.applicationContext
            )
        }

    val secureTokenStore =
        remember(
            context
        ) {
            SecureTokenStore(
                context =
                    context.applicationContext
            )
        }

    val userSessionManager =
        remember(
            userPreferenceDataStore,
            secureTokenStore,
            coroutineScope
        ) {
            UserSessionManager(
                userPreferenceDataStore =
                    userPreferenceDataStore,
                secureTokenStore =
                    secureTokenStore,
                coroutineScope =
                    coroutineScope
            )
        }

    val localizationRepository =
        remember {
            LocalizationRepository()
        }

    val localizationManager =
        remember(
            localizationRepository,
            coroutineScope
        ) {
            LocalizationManager(
                localizationRepository =
                    localizationRepository,
                coroutineScope =
                    coroutineScope
            )
        }

    val sessionState by
    userSessionManager.State.collectAsState()

    val localizationState by
    localizationManager.State.collectAsState()

    LaunchedEffect(
        sessionState.IsInitialized,
        sessionState.Language
    ) {
        if (
            sessionState.IsInitialized
        ) {
            localizationManager.Load(
                language =
                    sessionState.Language
            )
        }
    }

    BbTheme(
        themeMode =
            sessionState.ThemeMode
    ) {
        BBLocalizationProvider(
            state =
                localizationState
        ) {
            Surface(
                modifier =
                    Modifier.fillMaxSize(),
                color =
                    MaterialTheme.colorScheme.background
            ) {
                if (
                    sessionState.IsInitialized &&
                    localizationState.IsInitialized &&
                    !sessionState.IsAuthenticationInitializing
                ) {
                    BulbulusturApplicationContent(
                        sessionState =
                            sessionState,
                        userSessionManager =
                            userSessionManager,
                        appLinkUrl =
                            appLinkUrl,
                        onAppLinkConsumed =
                            onAppLinkConsumed
                    )
                }
            }
        }
    }
}

@Composable
private fun BulbulusturApplicationContent(
    sessionState: UserSessionState,
    userSessionManager: UserSessionManager,
    appLinkUrl: String?,
    onAppLinkConsumed: () -> Unit
) {
    val navController =
        rememberNavController()

    var showBuyerModeSheet by
    remember {
        mutableStateOf(
            false
        )
    }

    val executeService =
        remember {
            ExecuteService()
        }

    val authenticationRepository =
        remember {
            AuthenticationRepository()
        }

    val memberTempRepository =
        remember {
            MemberTempRepository()
        }

    val addressCountryRepository =
        remember {
            AddressCountryRepository()
        }

    val addressCityRepository =
        remember {
            AddressCityRepository()
        }

    val logonController =
        remember(
            executeService,
            authenticationRepository,
            memberTempRepository,
            addressCountryRepository,
            addressCityRepository,
            userSessionManager
        ){
            LogonController(
                executeService =
                    executeService,
                authenticationRepository =
                    authenticationRepository,
                memberTempRepository =
                    memberTempRepository,
                addressCountryRepository =
                    addressCountryRepository,
                addressCityRepository =
                    addressCityRepository,
                userSessionManager =
                    userSessionManager
            )
        }

    val currentBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        currentBackStackEntry
            ?.destination
            ?.route

    val currentBuyerMode =
        when {
            currentRoute
                ?.startsWith(
                    "wholesale/"
                ) == true -> {

                EBuyerMode.Wholesale
            }

            else -> {
                EBuyerMode.Retail
            }
        }

    val appNavigator =
        remember(
            navController
        ) {
            BulbulusturNavigator(
                navController =
                    navController,
                openBuyerModeSheet = {
                    showBuyerModeSheet =
                        true
                },
                closeBuyerModeSheet = {
                    showBuyerModeSheet =
                        false
                }
            )
        }

    LaunchedEffect(
        appLinkUrl
    ) {
        val incomingUrl =
            appLinkUrl
                ?: return@LaunchedEffect

        val uri =
            runCatching {
                Uri.parse(
                    incomingUrl
                )
            }.getOrNull()

        val isSupportedAppLink =
            uri?.scheme.equals(
                "https",
                ignoreCase =
                    true
            ) &&
                    uri?.host.equals(
                        "www.bulbulustur.com",
                        ignoreCase =
                            true
                    )

        if (!isSupportedAppLink) {
            onAppLinkConsumed()

            return@LaunchedEffect
        }

        val activationCode =
            uri
                ?.getQueryParameter(
                    "uuid"
                )
                ?.trim()
                .orEmpty()

        if (activationCode.isBlank()) {
            navController.navigate(
                LogonRoutes.Expired
            ) {
                launchSingleTop =
                    true
            }

            onAppLinkConsumed()

            return@LaunchedEffect
        }

        when (
            uri?.path
                ?.lowercase()
        ) {
            "/logon/register" -> {
                navController.navigate(
                    LogonRoutes.CreateRegisterActivationRoute(
                        activationCode =
                            activationCode
                    )
                ) {
                    launchSingleTop =
                        true
                }
            }

            "/logon/setnewpassword" -> {
                navController.navigate(
                    LogonRoutes.CreateSetNewPasswordRoute(
                        activationCode =
                            activationCode
                    )
                ) {
                    launchSingleTop =
                        true
                }
            }

            else -> {
                onAppLinkConsumed()

                return@LaunchedEffect
            }
        }

        onAppLinkConsumed()
    }

    NavHost(
        navController =
            navController,
        startDestination =
            SplashRoutes.ModeSelection
    ) {
        splashGraph(
            navigator =
                appNavigator
        )

        logonGraph(
            navController =
                navController,
            sessionState =
                sessionState,
            logonController =
                logonController
        )

        messageGraph(
            navigator =
                appNavigator
        )

        retailGraph(
            navigator =
                appNavigator
        )

        wholesaleGraph(
            navigator =
                appNavigator
        )

        companyGraph(
            navigator =
                appNavigator
        )

        orderGraph(
            navigator =
                appNavigator
        )

        accountGraph(
            navigator =
                appNavigator,
            sessionState =
                sessionState,
            logonController =
                logonController
        )

        settingsGraph(
            navigator =
                appNavigator,
            sessionState =
                sessionState,
            userSessionManager =
                userSessionManager
        )
    }

    if (
        showBuyerModeSheet
    ) {
        BuyerModeSheet(
            currentMode =
                currentBuyerMode,
            onDismissRequest = {
                showBuyerModeSheet =
                    false
            },
            onRetailClick = {
                appNavigator
                    .navigateToRetailHome()
            },
            onWholesaleClick = {
                appNavigator
                    .navigateToWholesaleHome()
            },
            onRfqClick = {
                appNavigator
                    .navigateToWholesaleRfqCreate()
            }
        )
    }
}