package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.RfqRoutes
import com.bulbulustur.android.Application.Navigation.WholesaleRoutes
import com.bulbulustur.android.Areas.b2b.Views.WholesaleCategoryHomeScreen
import com.bulbulustur.android.Areas.b2b.Views.WholesaleHomeScreen
import com.bulbulustur.android.Areas.b2b.Views.WholesaleProductDetailScreen
import com.bulbulustur.android.Areas.b2b.Views.ProductListScreen as WholesaleProductListScreen
import com.bulbulustur.android.Areas.b2b.Views.CategoryDetailScreen as WholesaleCategoryDetailScreen
import com.bulbulustur.android.Areas.b2b.Views.Rfq.RfqCreateScreen
import com.bulbulustur.android.Areas.b2b.Views.Rfq.RfqListScreen

fun NavGraphBuilder.wholesaleGraph(
    navigator: BulbulusturNavigator
) {

    composable(WholesaleRoutes.Home) {
        WholesaleHomeScreen(
            onMenuClick = {
                navigator.navigateToWholesaleCategories()
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onQuotationRequestsClick = {
                navigator.navigateToWholesaleOffers()
            }
        )
    }

    composable(WholesaleRoutes.CategoryHome) {
        WholesaleCategoryHomeScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(WholesaleRoutes.CategoryDetail) {
        WholesaleCategoryDetailScreen()
    }

    composable(WholesaleRoutes.ProductList) {
        WholesaleProductListScreen(
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            }
        )
    }

    composable(WholesaleRoutes.ProductDetail) {
        WholesaleProductDetailScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(RfqRoutes.List) {
        RfqListScreen(
            onBackClick = {
                navigator.back()
            },
            onCreateRfqClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            }
        )
    }

    composable(RfqRoutes.Create) {
        RfqCreateScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }
}