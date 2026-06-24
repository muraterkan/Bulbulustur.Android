package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.SplashRoutes
import com.bulbulustur.android.Application.Views.Home.ModeSelectionScreen

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

