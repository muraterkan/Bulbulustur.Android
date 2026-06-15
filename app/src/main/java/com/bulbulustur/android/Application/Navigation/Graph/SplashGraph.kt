package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.SplashRoutes
import com.bulbulustur.android.Features.splash.ModeSelectionScreen

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