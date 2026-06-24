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
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.CompanyRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RfqRoutes
import com.bulbulustur.android.Application.Navigation.Routes.WholesaleRoutes

fun NavGraphBuilder.wholesaleGraph(
    navigator: BulbulusturNavigator
) {
    composable(
        route = WholesaleRoutes.Home
    ) {
        WholesaleHomeScreen(
            onSearchClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.Search
                )
            },
            onMenuClick = {
                navigator.navigateToWholesaleCategories()
            },
            onCategoryClick = {
                navigator.navigateToWholesaleCategories()
            },
            onProductListClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.ProductList
                )
            },
            onProductDetailClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.ProductDetail
                )
            },
            onRfqListClick = {
                navigator.navController.navigate(
                    RfqRoutes.List
                )
            },
            onRfqCreateClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onLastPriceRequestClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onSampleRequestClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onCustomizationRequestClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onFavoriteClick = {
                navigator.navController.navigate(
                    AccountRoutes.Favorites
                )
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            }
        )
    }

    composable(
        route = WholesaleRoutes.CategoryHome
    ) {
        WholesaleCategoryHomeScreen(
            onBackClick = {
                navigator.back()
            },
            onSearchClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.Search
                )
            },
            onMenuClick = {
                navigator.navigateToWholesaleCategories()
            },
            onFavoriteClick = {
                navigator.navController.navigate(
                    AccountRoutes.Favorites
                )
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onHomeClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.Home
                ) {
                    launchSingleTop = true
                }
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
            },
            onProductListClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.ProductList
                )
            },
            onSubCategoryClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.categoryDetail(
                        categoryId = 1
                    )
                )
            },
            onCompanyListClick = {
                navigator.navController.navigate(
                    CompanyRoutes.CompanyList
                )
            },
            onRfqClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onLastPriceRequestClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onSampleRequestClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onCustomizationRequestClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            }
        )
    }

    composable(
        route = WholesaleRoutes.CategoryDetail,
        arguments = listOf(
            navArgument(
                name = WholesaleRoutes.ArgCategoryId
            ) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val categoryId = backStackEntry.arguments
            ?.getInt(
                WholesaleRoutes.ArgCategoryId
            )
            ?: return@composable

        WholesaleCategoryDetailScreen(
            categoryId = categoryId,
            onBackClick = {
                navigator.back()
            },
            onSubCategoryClick = { subCategoryId ->
                navigator.navController.navigate(
                    WholesaleRoutes.categoryDetail(
                        categoryId = subCategoryId
                    )
                )
            },
            onProductListClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.ProductList
                )
            },
            onCompanyListClick = {
                navigator.navController.navigate(
                    CompanyRoutes.CompanyList
                )
            },
            onRfqCreateClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onPopularProductGroupClick = { _, _ ->
                navigator.navController.navigate(
                    WholesaleRoutes.ProductList
                )
            }
        )
    }

    composable(
        route = WholesaleRoutes.ProductList
    ) {
        WholesaleProductListScreen(
            onBackClick = {
                navigator.back()
            },
            onSearchClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.Search
                )
            },
            onFavoriteClick = {
                navigator.navController.navigate(
                    AccountRoutes.Favorites
                )
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onHomeClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.Home
                ) {
                    launchSingleTop = true
                }
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
            },
            onProductDetailClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.ProductDetail
                )
            },
            onProductFavoriteClick = {
                // API entegrasyonunda favori işlemi bağlanacak.
            },
            onRfqClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
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
                // API entegrasyonunda silme işlemi bağlanacak.
            },
            onCreateRfqClick = {
                navigator.navController.navigate(
                    RfqRoutes.Create
                )
            },
            onHomeClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.Home
                ) {
                    launchSingleTop = true
                }
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
                ) {
                    launchSingleTop = true
                }
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
            ?.getInt(
                RfqRoutes.ArgBuyerRequestId
            )
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
            ?.getInt(
                RfqRoutes.ArgBuyerRequestId
            )
            ?: return@composable

        val sendedOfferId = backStackEntry.arguments
            ?.getInt(
                RfqRoutes.ArgSendedOfferId
            )
            ?: return@composable

        RfqOfferDetailScreen(
            buyerRequestId = buyerRequestId,
            sendedOfferId = sendedOfferId,
            onBackClick = {
                navigator.back()
            },
            onSellerClick = {
                navigator.navController.navigate(
                    CompanyRoutes.CompanyDetail
                )
            },
            onMessageClick = {
                navigator.navigateToInbox()
            }
        )
    }
}
