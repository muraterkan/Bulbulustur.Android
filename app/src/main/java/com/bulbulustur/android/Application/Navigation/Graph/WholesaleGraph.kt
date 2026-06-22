package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Areas.b2b.Views.Category.CategoryDetailScreen as WholesaleCategoryDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Category.WholesaleCategoryHomeScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Home.WholesaleHomeScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.ProductListScreen as WholesaleProductListScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.WholesaleProductDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqCreateScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqListScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqOfferDetailScreen
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.RfqRoutes
import com.bulbulustur.android.Application.Navigation.Routes.WholesaleRoutes

fun NavGraphBuilder.wholesaleGraph(
    navigator: BulbulusturNavigator
) {

    composable(
        route = WholesaleRoutes.Home
    ) {
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
                navigator.navController.navigate(
                    RfqRoutes.List
                )
            }
        )
    }

    composable(
        route = WholesaleRoutes.CategoryHome
    ) {
        WholesaleCategoryHomeScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = WholesaleRoutes.CategoryDetail
    ) {
        WholesaleCategoryDetailScreen()
    }

    composable(
        route = WholesaleRoutes.ProductList
    ) {
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

    composable(
        route = WholesaleRoutes.ProductDetail
    ) {
        WholesaleProductDetailScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = RfqRoutes.List
    ) {
        RfqListScreen(
            onBackClick = {
                navigator.back()
            },
            onDiscoverWholesaleClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.ProductList
                )
            },
            onOffersClick = { buyerRequestId ->
                navigator.navController.navigate(
                    RfqRoutes.detail(
                        buyerRequestId = buyerRequestId
                    )
                )
            },
            onDetailClick = { buyerRequestId ->
                navigator.navController.navigate(
                    RfqRoutes.detail(
                        buyerRequestId = buyerRequestId
                    )
                )
            },
            onDeleteClick = {
                // API entegrasyonunda silme işlemi burada bağlanacak.
            },
            onCreateRfqClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onHomeClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.Home
                )
            },
            onMenuClick = {
                navigator.navigateToWholesaleCategories()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onBasketClick = {
                navigator.navController.navigate(
                    RfqRoutes.List
                )
            },
            onAccountClick = {
                navigator.navigateToAccount()
            }
        )
    }

    composable(
        route = RfqRoutes.Create
    ) {
        RfqCreateScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = RfqRoutes.Detail,
        arguments = listOf(
            navArgument(
                name = RfqRoutes.ArgBuyerRequestId
            ) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->

        val buyerRequestId = backStackEntry.arguments
            ?.getInt(RfqRoutes.ArgBuyerRequestId)
            ?: return@composable

        RfqDetailScreen(
            buyerRequestId = buyerRequestId,
            onBackClick = {
                navigator.back()
            },
            onOfferClick = { sendedOfferId ->
                navigator.navController.navigate(
                    RfqRoutes.offerDetail(
                        buyerRequestId = buyerRequestId,
                        sendedOfferId = sendedOfferId
                    )
                )
            },
            onCreateRfqClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            }
        )
    }

    composable(
        route = RfqRoutes.OfferDetail,
        arguments = listOf(
            navArgument(
                name = RfqRoutes.ArgBuyerRequestId
            ) {
                type = NavType.IntType
            },
            navArgument(
                name = RfqRoutes.ArgSendedOfferId
            ) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->

        val buyerRequestId = backStackEntry.arguments
            ?.getInt(RfqRoutes.ArgBuyerRequestId)
            ?: return@composable

        val sendedOfferId = backStackEntry.arguments
            ?.getInt(RfqRoutes.ArgSendedOfferId)
            ?: return@composable

        RfqOfferDetailScreen(
            buyerRequestId = buyerRequestId,
            sendedOfferId = sendedOfferId,
            onBackClick = {
                navigator.back()
            },
            onSellerClick = {
                // Satıcı detay route'u bağlandığında eklenecek.
            },
            onMessageClick = {
                navigator.navigateToInbox()
            }
        )
    }
}