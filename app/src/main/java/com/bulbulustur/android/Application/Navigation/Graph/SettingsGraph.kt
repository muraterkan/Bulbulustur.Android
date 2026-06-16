package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.SettingsRoutes
import com.bulbulustur.android.Views.Account.AppearanceSettingsScreen
import com.bulbulustur.android.Views.Account.LanguageSettingsScreen
import com.bulbulustur.android.Views.Account.LegalPoliciesScreen
import com.bulbulustur.android.Views.Account.AccountSettingsScreen
import com.bulbulustur.android.Views.Account.LegalPolicyDetailScreen

fun NavGraphBuilder.settingsGraph(
    navigator: BulbulusturNavigator
) {

    composable(SettingsRoutes.Home) {
        AccountSettingsScreen(
            onBackClick = {
                navigator.back()
            },
            onLanguageClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Language
                )
            },
            onAppearanceClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Appearance
                )
            },
            onLegalPoliciesClick = {
                navigator.navController.navigate(
                    SettingsRoutes.LegalPolicies
                )
            }
        )
    }

    composable(SettingsRoutes.Language) {
        LanguageSettingsScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.Appearance) {
        AppearanceSettingsScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.LegalPolicies) {
        LegalPoliciesScreen(
            onBackClick = {
                navigator.back()
            },
            onPolicyClick = { item ->
                navigator.navController.navigate(
                    SettingsRoutes.legalPolicyDetail(item.key)
                )
            }
        )
    }

    composable(
        route = SettingsRoutes.LegalPolicyDetail,
        arguments = listOf(
            navArgument("policyKey") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->

        val policyKey = backStackEntry.arguments
            ?.getString("policyKey")
            .orEmpty()

        LegalPolicyDetailScreen(
            policyKey = policyKey,
            onBackClick = {
                navigator.back()
            },
            onOpenWebClick = {
            }
        )
    }
}
