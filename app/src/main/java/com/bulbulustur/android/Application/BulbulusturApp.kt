package com.bulbulustur.android.Application

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bulbulustur.android.Application.Navigation.SplashRoutes
import com.bulbulustur.android.Application.Navigation.Graph.accountGraph
import com.bulbulustur.android.Application.Navigation.Graph.companyGraph
import com.bulbulustur.android.Application.Navigation.Graph.messageGraph
import com.bulbulustur.android.Application.Navigation.Graph.orderGraph
import com.bulbulustur.android.Application.Navigation.Graph.retailGraph
import com.bulbulustur.android.Application.Navigation.Graph.settingsGraph
import com.bulbulustur.android.Application.Navigation.Graph.splashGraph
import com.bulbulustur.android.Application.Navigation.Graph.wholesaleGraph
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Features.logon.logonGraph
import com.bulbulustur.android.Ui.shell.BuyerMode
import com.bulbulustur.android.Ui.shell.BuyerModeSheet
import com.bulbulustur.android.Ui.theme.BbTheme

@Composable
fun BulbulusturApp() {
    BbTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()

            var showBuyerModeSheet by remember {
                mutableStateOf(false)
            }

            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route

            val currentBuyerMode = when {
                currentRoute?.startsWith("wholesale/") == true -> BuyerMode.Wholesale
                else -> BuyerMode.Retail
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
                settingsGraph(appNavigator)
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
    }
}