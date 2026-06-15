package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.RetailRoutes
import com.bulbulustur.android.Features.Areas.b2c.RetailHomeScreen
import com.bulbulustur.android.Features.Areas.b2c.ProductDetailScreen
import com.bulbulustur.android.Features.Areas.b2c.ProductListScreen as RetailProductListScreen
import com.bulbulustur.android.Features.Areas.b2c.RetailCategoryHomeScreen
import com.bulbulustur.android.Features.Areas.b2c.CampaignListScreen
import com.bulbulustur.android.Features.Areas.b2c.CampaignDetailScreen

fun NavGraphBuilder.retailGraph(
    navigator: BulbulusturNavigator
) {

    composable(RetailRoutes.Home) {
        RetailHomeScreen(
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onBasketClick = {
                navigator.navigateToRetailBasket()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            }
        )
    }

    composable(RetailRoutes.CategoryHome) {
        RetailCategoryHomeScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(RetailRoutes.ProductList) {
        RetailProductListScreen(
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onBasketClick = {
                navigator.navigateToRetailBasket()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            }
        )
    }

    composable(RetailRoutes.ProductDetail) {
        ProductDetailScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(RetailRoutes.CampaignList) {
        CampaignListScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(RetailRoutes.CampaignDetail) {
        CampaignDetailScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }
}