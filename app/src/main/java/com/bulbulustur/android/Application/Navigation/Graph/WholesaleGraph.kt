package com.bulbulustur.android.Application.Navigation.Graph

import com.bulbulustur.android.Application.Areas.b2b.Controllers.HomeController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Areas.b2b.Controllers.RfqController
import com.bulbulustur.android.Application.Areas.b2b.Views.Category.CategoryDetailScreen as WholesaleCategoryDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Category.WholesaleCategoryHomeScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Home.WholesaleHomeScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.WholesaleProductListScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.WholesaleProductDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqCreateScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqListScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqOfferDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqOffersScreen
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.CompanyRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RfqRoutes
import com.bulbulustur.android.Application.Navigation.Routes.WholesaleRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductControllerState
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes

fun NavGraphBuilder.wholesaleGraph(
    navigator: BulbulusturNavigator,
    sessionState: UserSessionState,
    productController: ProductController,
    rfqController: RfqController
){
    composable(route = WholesaleRoutes.Home) {
        WholesaleHomeScreen(
            onSearchClick = {
                navigator.navController.navigate(WholesaleRoutes.Search)
            },
            onMenuClick = {
                navigator.navigateToWholesaleCategories()
            },
            onCategoryClick = {
                navigator.navigateToWholesaleCategories()
            },
            onProductListClick = {
                navigator.navController.navigate(WholesaleRoutes.ProductList)
            },
            onProductDetailClick = { productId ->
                navigator.navController.navigate(
                    WholesaleRoutes.productDetail(productId)
                )
            },
            onRfqListClick = {
                navigator.navController.navigate(RfqRoutes.List)
            },
            onRfqCreateClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onLastPriceRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onSampleRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onCustomizationRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onFavoriteClick = {
                navigator.navController.navigate(AccountRoutes.Favorites)
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            }
        )
    }

    composable(route = WholesaleRoutes.CategoryHome) {
        WholesaleCategoryHomeScreen(
            onBackClick = {
                navigator.back()
            },
            onSearchClick = {
                navigator.navController.navigate(WholesaleRoutes.Search)
            },
            onMenuClick = {
                navigator.navigateToWholesaleCategories()
            },
            onFavoriteClick = {
                navigator.navController.navigate(AccountRoutes.Favorites)
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onHomeClick = {
                navigator.navController.navigate(WholesaleRoutes.Home) {
                    launchSingleTop = true
                }
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onBasketClick = {
                navigator.navController.navigate(RfqRoutes.List)
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onProductListClick = {
                navigator.navController.navigate(WholesaleRoutes.ProductList)
            },
            onSubCategoryClick = {
                navigator.navController.navigate(
                    WholesaleRoutes.categoryDetail(categoryId = 1)
                )
            },
            onCompanyListClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyList)
            },
            onRfqClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onLastPriceRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onSampleRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onCustomizationRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            }
        )
    }

    composable(
        route = WholesaleRoutes.CategoryDetail,
        arguments = listOf(
            navArgument(WholesaleRoutes.ArgCategoryId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val categoryId = backStackEntry.arguments
            ?.getInt(WholesaleRoutes.ArgCategoryId)
            ?: return@composable

        WholesaleCategoryDetailScreen(
            categoryId = categoryId,
            onBackClick = {
                navigator.back()
            },
            onSubCategoryClick = { subCategoryId ->
                navigator.navController.navigate(
                    WholesaleRoutes.categoryDetail(categoryId = subCategoryId)
                )
            },
            onProductListClick = {
                navigator.navController.navigate(WholesaleRoutes.ProductList)
            },
            onCompanyListClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyList)
            },
            onRfqCreateClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onPopularProductGroupClick = { _, _ ->
                navigator.navController.navigate(WholesaleRoutes.ProductList)
            }
        )
    }

    composable(route = WholesaleRoutes.ProductList) {
        WholesaleProductListScreen(
            onBackClick = {
                navigator.back()
            },
            onSearchClick = {
                navigator.navController.navigate(WholesaleRoutes.Search)
            },
            onFavoriteClick = {
                navigator.navController.navigate(AccountRoutes.Favorites)
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onHomeClick = {
                navigator.navController.navigate(WholesaleRoutes.Home) {
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
                navigator.navController.navigate(RfqRoutes.List)
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onProductDetailClick = { productId ->
                navigator.navController.navigate(
                    WholesaleRoutes.productDetail(productId)
                )
            },
            onProductFavoriteClick = {
            },
            onRfqClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            }
        )
    }

    composable(
        route = WholesaleRoutes.ProductDetailRoute,
        arguments = listOf(
            navArgument(WholesaleRoutes.ArgProductId) {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    ) { backStackEntry ->
        val productId = backStackEntry.arguments
            ?.getInt(WholesaleRoutes.ArgProductId)
            ?: 0

        val productState by productController.State.collectAsState()

        LaunchedEffect(
            productId,
            sessionState.Language.Id
        ) {
            if (productId <= 0 || sessionState.Language.Id <= 0) {
                return@LaunchedEffect
            }

            productController.ClearProductDetail()

            productController.Detail(
                languageId = sessionState.Language.Id,
                wholesaleProductId = productId
            )

            productController.GetProductRelatedsAsync(
                languageId = sessionState.Language.Id,
                wholesaleProductId = productId,
                count = 10
            )
        }

        val productCategoryId = productState.ProductDetailResult?.Data?.ProductCategoryId ?: 0

        LaunchedEffect(
            productCategoryId,
            sessionState.Language.Id
        ) {
            if (productCategoryId <= 0 || sessionState.Language.Id <= 0) {
                return@LaunchedEffect
            }

            productController.RelatedCategories(
                languageId = sessionState.Language.Id,
                productCategoryId = productCategoryId
            )
        }


        

        WholesaleProductDetailScreen(
            State = productState,
            productId = productId,
            onBackClick = {
                navigator.back()
            },
            onSearchClick = {
                navigator.navController.navigate(WholesaleRoutes.Search)
            },
            onFavoriteClick = {
                navigator.navController.navigate(AccountRoutes.Favorites)
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onLastPriceRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onSampleRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onCustomizationRequestClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onCompanyClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyDetail)
            },
            onCompanyProductsClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyProducts)
            },
            onRelatedProductClick = { product ->
                if (product.id > 0) {
                    navigator.navController.navigate(
                        WholesaleRoutes.productDetail(product.id)
                    )
                }
            },
            onCompanyBestSellerProductClick = { product ->
                if (product.id > 0) {
                    navigator.navController.navigate(
                        WholesaleRoutes.productDetail(product.id)
                    )
                }
            },
            onRelatedProductsClick = {},
            onCompanyBestSellerProductsClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyProducts)
            },
            onRelatedCategoryClick = { category ->
                if (category.id > 0) {
                    navigator.navController.navigate(
                        WholesaleRoutes.categoryDetail(category.id)
                    )
                }
            }
        )
    }

    composable(route = RfqRoutes.List) {
        val rfqState by rfqController.State.collectAsState()

        LaunchedEffect(sessionState.MemberId) {
            rfqController.GetBuyerRequests(
                memberId = sessionState.MemberId
            )
        }

        RfqListScreen(
            requests = rfqState.BuyerRequests,
            isLoading = rfqState.IsLoading && (
                    rfqState.CurrentAction == "GetBuyerRequests" ||
                            rfqState.CurrentAction == "DeleteBuyerRequest"
                    ),
            errorMessage = rfqState.BuyerRequestListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?: rfqState.BuyerRequestDeleteResult
                    ?.takeIf { !it.Success }
                    ?.Message,
            deletingBuyerRequestKey = null,
            onBackClick = {
                navigator.back()
            },
            onDiscoverWholesaleClick = {
                navigator.navController.navigate(WholesaleRoutes.ProductList)
            },
            onOffersClick = { buyerRequestKey ->
                navigator.navController.navigate(
                    RfqRoutes.offers(buyerRequestKey)
                )
            },
            onDetailClick = { buyerRequestKey ->
                navigator.navController.navigate(
                    RfqRoutes.detail(buyerRequestKey)
                )
            },
            onDeleteClick = { buyerRequestKey ->
                rfqController.DeleteBuyerRequest(
                    buyerRequestKey = buyerRequestKey,
                    onSuccess = {
                        rfqController.GetBuyerRequests(
                            memberId = sessionState.MemberId
                        )
                    }
                )
            },
            onCreateRfqClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onRetryClick = {
                rfqController.GetBuyerRequests(
                    memberId = sessionState.MemberId
                )
            },
            onHomeClick = {
                navigator.navController.navigate(WholesaleRoutes.Home) {
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
                navigator.navController.navigate(RfqRoutes.List) {
                    launchSingleTop = true
                }
            },
            onAccountClick = {
                navigator.navigateToAccount()
            }
        )
    }

    composable(route = RfqRoutes.Create) {
        val rfqState by rfqController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        LaunchedEffect(languageId) {
            rfqController.ClearInsertResult()
            rfqController.LoadCreateOptions(languageId)
        }

        RfqCreateScreen(
            memberId = sessionState.MemberId,
            productCategories = rfqState.ProductCategories,
            units = rfqState.Units,
            currencies = rfqState.Currencies,
            colors = rfqState.Colors,
            materialTypes = rfqState.MaterialTypes,
            paymentTerms = rfqState.PaymentTerms,
            tradeTerms = rfqState.TradeTerms,
            isOptionsLoading = rfqState.IsCreateOptionsLoading,
            isSubmitting = rfqState.IsLoading &&
                    rfqState.CurrentAction == "InsertBuyerRequest",
            errorMessage = rfqState.CreateOptionsErrorMessage
                ?: rfqState.BuyerRequestInsertResult
                    ?.takeIf { !it.Success }
                    ?.Message,
            onBackClick = {
                navigator.back()
            },
            onRetryOptionsClick = {
                rfqController.LoadCreateOptions(languageId)
            },
            onSendClick = { model ->
                rfqController.InsertBuyerRequest(
                    model = model,
                    onSuccess = {
                        rfqController.GetBuyerRequests(
                            memberId = sessionState.MemberId
                        )

                        navigator.navController.navigate(RfqRoutes.List) {
                            popUpTo(RfqRoutes.Create) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            }
        )
    }

    composable(
        route = RfqRoutes.Detail,
        arguments = listOf(
            navArgument(RfqRoutes.ArgBuyerRequestKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val rfqState by rfqController.State.collectAsState()

        val buyerRequestKey = backStackEntry.arguments
            ?.getString(RfqRoutes.ArgBuyerRequestKey)
            .orEmpty()

        LaunchedEffect(buyerRequestKey) {
            rfqController.ClearBuyerRequestDetail()

            if (buyerRequestKey.isNotBlank()) {
                rfqController.GetBuyerRequest(
                    buyerRequestKey = buyerRequestKey
                )
            }
        }

        RfqDetailScreen(
            buyerRequest = rfqState.BuyerRequest,
            isLoading = rfqState.IsLoading &&
                    rfqState.CurrentAction == "GetBuyerRequest",
            errorMessage = rfqState.BuyerRequestDetailResult
                ?.takeIf { !it.Success }
                ?.Message,
            onBackClick = {
                navigator.back()
            },
            onOffersClick = {
                if (buyerRequestKey.isNotBlank()) {
                    navigator.navController.navigate(
                        RfqRoutes.offers(buyerRequestKey)
                    )
                }
            },
            onCreateRfqClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onRetryClick = {
                rfqController.GetBuyerRequest(
                    buyerRequestKey = buyerRequestKey
                )
            }
        )
    }

    composable(
        route = RfqRoutes.Offers,
        arguments = listOf(
            navArgument(RfqRoutes.ArgBuyerRequestKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val rfqState by rfqController.State.collectAsState()

        val buyerRequestKey = backStackEntry.arguments
            ?.getString(RfqRoutes.ArgBuyerRequestKey)
            .orEmpty()

        LaunchedEffect(buyerRequestKey) {
            if (buyerRequestKey.isNotBlank()) {
                rfqController.GetSendedOffers(
                    buyerRequestKey = buyerRequestKey
                )
            }
        }

        RfqOffersScreen(
            buyerRequestKey = buyerRequestKey,
            offers = rfqState.SendedOffers,
            isLoading = rfqState.IsLoading &&
                    rfqState.CurrentAction == "GetSendedOffers",
            errorMessage = rfqState.SendedOfferListResult
                ?.takeIf { !it.Success }
                ?.Message,
            onBackClick = {
                navigator.back()
            },
            onOfferClick = { sendedOfferId ->
                navigator.navController.navigate(
                    RfqRoutes.offerDetail(sendedOfferId)
                )
            },
            onRetryClick = {
                rfqController.GetSendedOffers(
                    buyerRequestKey = buyerRequestKey
                )
            }
        )
    }

    composable(
        route = RfqRoutes.OfferDetail,
        arguments = listOf(
            navArgument(RfqRoutes.ArgSendedOfferId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val rfqState by rfqController.State.collectAsState()

        val sendedOfferId = backStackEntry.arguments
            ?.getInt(RfqRoutes.ArgSendedOfferId)
            ?: return@composable

        LaunchedEffect(sendedOfferId) {
            rfqController.ClearSendedOfferDetail()
            rfqController.GetSendedOffer(sendedOfferId)
        }

        RfqOfferDetailScreen(
            offer = rfqState.SendedOffer,
            isLoading = rfqState.IsLoading &&
                    rfqState.CurrentAction == "GetSendedOffer",
            errorMessage = rfqState.SendedOfferDetailResult
                ?.takeIf { !it.Success }
                ?.Message,
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
            },
            onRetryClick = {
                rfqController.GetSendedOffer(sendedOfferId)
            }
        )
    }
}