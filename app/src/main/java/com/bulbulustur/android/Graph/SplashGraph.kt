package com.bulbulustur.android.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.BulbulusturNavigator
import com.bulbulustur.android.SplashRoutes
import com.bulbulustur.android.Views.Home.ModeSelectionScreen

fun NavGraphBuilder.splashGraph(
    navigator: BulbulusturNavigator
) {
    composable(SplashRoutes.ModeSelection) {
        ModeSelectionScreen(
            onRetailClick = {
                navigator.navigateFromModeSelectionToRetail()
            },
            onWholesaleClick = {
                navigator.navigateFromModeSelectionToWholesale()
            }
        )
    }
}
