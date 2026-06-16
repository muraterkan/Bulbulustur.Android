package com.bulbulustur.android

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
import com.bulbulustur.android.Graph.accountGraph
import com.bulbulustur.android.Graph.companyGraph
import com.bulbulustur.android.Graph.messageGraph
import com.bulbulustur.android.Graph.orderGraph
import com.bulbulustur.android.Graph.retailGraph
import com.bulbulustur.android.Graph.settingsGraph
import com.bulbulustur.android.Graph.splashGraph
import com.bulbulustur.android.Graph.wholesaleGraph
import com.bulbulustur.android.Views.Logon.logonGraph
import com.bulbulustur.android.Views.Shared.BuyerMode
import com.bulbulustur.android.Views.Shared.BuyerModeSheet
import com.bulbulustur.android.wwwroot.theme.BbTheme

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