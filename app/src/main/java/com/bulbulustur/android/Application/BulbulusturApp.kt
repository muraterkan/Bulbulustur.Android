package com.bulbulustur.android.Application

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import com.bulbulustur.android.Application.Datastore.UserPreferenceDataStore
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Graph.accountGraph
import com.bulbulustur.android.Application.Navigation.Graph.companyGraph
import com.bulbulustur.android.Application.Navigation.Graph.messageGraph
import com.bulbulustur.android.Application.Navigation.Graph.orderGraph
import com.bulbulustur.android.Application.Navigation.Graph.retailGraph
import com.bulbulustur.android.Application.Navigation.Graph.settingsGraph
import com.bulbulustur.android.Application.Navigation.Graph.splashGraph
import com.bulbulustur.android.Application.Navigation.Graph.wholesaleGraph
import com.bulbulustur.android.Application.Navigation.Routes.SplashRoutes
import com.bulbulustur.android.Application.Navigation.Routes.logonGraph
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.Application.Views.Shared.Components.BuyerModeSheet
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.Enums.EBuyerMode
import com.bulbulustur.android.Application.Session.UserSessionState

@Composable
fun BulbulusturApp() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val userPreferenceDataStore = remember(context) {
        UserPreferenceDataStore(
            context = context.applicationContext
        )
    }

    val userSessionManager = remember(
        userPreferenceDataStore,
        coroutineScope
    ) {
        UserSessionManager(
            userPreferenceDataStore = userPreferenceDataStore,
            coroutineScope = coroutineScope
        )
    }

    val sessionState by userSessionManager.State.collectAsState()

    BbTheme(
        themeMode = sessionState.ThemeMode
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (sessionState.IsInitialized) {
                BulbulusturApplicationContent(
                    sessionState = sessionState,
                    userSessionManager = userSessionManager
                )
            }
        }
    }
}

@Composable
private fun BulbulusturApplicationContent(
    sessionState: UserSessionState,
    userSessionManager: UserSessionManager
) {
    val navController = rememberNavController()

    var showBuyerModeSheet by remember {
        mutableStateOf(false)
    }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val currentBuyerMode = when {
        currentRoute?.startsWith("wholesale/") == true -> {
            EBuyerMode.Wholesale
        }

        else -> {
            EBuyerMode.Retail
        }
    }

    val appNavigator = remember(navController) {
        BulbulusturNavigator(
            navController = navController,
            openBuyerModeSheet = {
                showBuyerModeSheet = true
            },
            closeBuyerModeSheet = {
                showBuyerModeSheet = false
            }
        )
    }

    NavHost(
        navController = navController,
        startDestination = SplashRoutes.ModeSelection
    ) {
        splashGraph(appNavigator)
        logonGraph(navController)
        messageGraph(appNavigator)
        retailGraph(appNavigator)
        wholesaleGraph(appNavigator)
        companyGraph(appNavigator)
        orderGraph(appNavigator)
        accountGraph(appNavigator)
        settingsGraph(
            navigator = appNavigator,
            sessionState = sessionState,
            userSessionManager = userSessionManager
        )
    }

    if (showBuyerModeSheet) {
        BuyerModeSheet(
            currentMode = currentBuyerMode,
            onDismissRequest = {
                showBuyerModeSheet = false
            },
            onRetailClick = {
                appNavigator.navigateToRetailHome()
            },
            onWholesaleClick = {
                appNavigator.navigateToWholesaleHome()
            },
            onRfqClick = {
                appNavigator.navigateToWholesaleRfqCreate()
            }
        )
    }
}