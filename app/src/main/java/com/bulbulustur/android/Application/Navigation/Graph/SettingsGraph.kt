package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.SettingsRoutes
import com.bulbulustur.android.Application.Views.Account.AboutThisAppScreen
import com.bulbulustur.android.Application.Views.Account.AccountSettingsScreen
import com.bulbulustur.android.Application.Views.Account.AppearanceSettingsScreen
import com.bulbulustur.android.Application.Views.Account.CommunicationPreferenceScreen
import com.bulbulustur.android.Application.Views.Account.CurrencySettingsScreen
import com.bulbulustur.android.Application.Views.Account.LanguageSettingsScreen
import com.bulbulustur.android.Application.Views.Account.LegalPoliciesScreen
import com.bulbulustur.android.Application.Views.Account.LegalPolicyDetailScreen
import com.bulbulustur.android.Application.Views.Account.RegionSettingsScreen

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
            onRegionClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Region
                )
            },
            onCurrencyClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Currency
                )
            },
            onCommunicationPreferenceClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Communication
                )
            },
            onLegalPoliciesClick = {
                navigator.navController.navigate(
                    SettingsRoutes.LegalPolicies
                )
            },
            onAboutThisAppClick = {
                navigator.navController.navigate(
                    SettingsRoutes.AboutThisApp
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

    composable(SettingsRoutes.Region) {
        RegionSettingsScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.Currency) {
        CurrencySettingsScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.Communication) {
        CommunicationPreferenceScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(SettingsRoutes.AboutThisApp) {
        AboutThisAppScreen(
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
                    SettingsRoutes.legalPolicyDetail(
                        policyKey = item.key
                    )
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