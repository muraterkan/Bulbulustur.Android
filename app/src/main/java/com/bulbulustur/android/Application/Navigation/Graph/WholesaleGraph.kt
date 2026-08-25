package com.bulbulustur.android.Application.Navigation.Graph

import android.util.Log

import com.bulbulustur.android.Application.Areas.b2b.Controllers.CategoryController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.HomeController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.SearchController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Areas.b2b.Controllers.RfqController
import com.bulbulustur.android.Application.Controllers.MessageController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.WholesaleBuyerRequestController
import com.bulbulustur.android.Application.Areas.b2b.Views.Category.WholesaleCategoryLevel1Screen
import com.bulbulustur.android.Application.Areas.b2b.Views.Category.WholesaleCategoryLevel2Screen
import com.bulbulustur.android.Application.Areas.b2b.Views.Category.WholesaleCategoryHomeScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Home.WholesaleHomeScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Search.SearchScreen as WholesaleSearchScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.WholesaleProductListScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.WholesaleFeaturedProductsScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.WholesaleProductDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.LastPriceRequestScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.SampleRequestScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Product.CustomizationRequestScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqCreateScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqEditScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqListScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqOfferDetailScreen
import com.bulbulustur.android.Application.Areas.b2b.Views.Rfq.RfqOffersScreen
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.CompanyRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RfqRoutes
import com.bulbulustur.android.Application.Navigation.Routes.WholesaleRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerCustomizeRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerLastPriceRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerSampleRequestInsertModel
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductControllerState
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes

fun NavGraphBuilder.wholesaleGraph(
    navigator: BulbulusturNavigator,
    sessionState: UserSessionState,
    categoryController: CategoryController,
    homeController: HomeController,
    productController: ProductController,
    searchController: SearchController,
    rfqController: RfqController,
    messageController: MessageController,
    wholesaleBuyerRequestController: WholesaleBuyerRequestController
){
    composable(route = WholesaleRoutes.Home) {
        val homeState by homeController.State.collectAsState()

        LaunchedEffect(sessionState.Language.Id) {
            homeController.Load(
                languageId = sessionState.Language.Id
            )
        }

        WholesaleHomeScreen(
            featuredProducts = homeState.FeaturedProducts,
            specialContents = homeState.SpecialContents,
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
                navigator.navigateToWholesaleCategories()
            },
            onFeaturedProductsClick = {
                navigator.navController.navigate(WholesaleRoutes.FeaturedProducts)
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

    composable(route = WholesaleRoutes.FeaturedProducts) {
        WholesaleFeaturedProductsScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(route = WholesaleRoutes.Search) {
        val searchState by searchController.State.collectAsState()

        WholesaleSearchScreen(
            onBackClick = {
                navigator.back()
            },
            onFavoriteClick = {
                navigator.navController.navigate(AccountRoutes.Favorites)
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onProductSearchClick = { key ->
                searchController.SearchProducts(
                    companyId = 0,
                    key = key,
                    page = 1,
                    pageSize = 20
                )
            },
            onCompanySearchClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyList)
            },
            onProductClick = { productId ->
                navigator.navController.navigate(WholesaleRoutes.productDetail(productId))
            },
            onRfqCreateClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            productResults = searchState.ProductSearchResult?.Data?.Items.orEmpty(),
            hasProductSearch = searchState.ProductSearchResult != null
        )
    }

    composable(route = WholesaleRoutes.CategoryHome) {
        val categoryState by categoryController.State.collectAsState()

        LaunchedEffect(sessionState.Language.Id) {
            categoryController.LoadHome(
                languageId = sessionState.Language.Id
            )
        }
        WholesaleCategoryHomeScreen(
            isLoading = categoryState.IsLoading,
            errorMessage = categoryState.ErrorMessage,
            categories = categoryState.Categories,
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
                navigator.navController.navigate(WholesaleRoutes.productList())
            },
            onSubCategoryClick = { subCategoryId ->
                if (subCategoryId > 0) {
                    navigator.navController.navigate(
                        WholesaleRoutes.categoryLevel1(subCategoryId)
                    )
                }
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
        route = WholesaleRoutes.CategoryLevel1,
        arguments = listOf(
            navArgument(WholesaleRoutes.ArgCategoryId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val categoryState by categoryController.State.collectAsState()

        val categoryId = backStackEntry.arguments
            ?.getInt(WholesaleRoutes.ArgCategoryId)
            ?: return@composable

        LaunchedEffect(sessionState.Language.Id, categoryId) {
            categoryController.LoadDetail(
                languageId = sessionState.Language.Id,
                productCategoryId = categoryId
            )

            categoryController.LoadSpecialContents(
                languageId = sessionState.Language.Id,
                count = 6
            )
        }

        WholesaleCategoryLevel1Screen(
            categoryId = categoryId,
            isLoading = categoryState.IsLoading,
            errorMessage = categoryState.ErrorMessage,

            categoryInfo = categoryState.Category,
            childCategories = categoryState.ChildCategories,
            specialContents = categoryState.SpecialContents,
            isSpecialContentsLoading = categoryState.IsSpecialContentsLoading,
            onBackClick = {
                navigator.back()
            },
            onSubCategoryClick = { subCategoryId ->
                navigator.navController.navigate(
                    WholesaleRoutes.productList(subCategoryId)
                )
            },
            onProductListClick = {
                navigator.navController.navigate(WholesaleRoutes.productList(categoryId))
            },
            onCompanyListClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyList)
            },
            onRfqCreateClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onPopularProductGroupClick = { _, _ ->
                navigator.navController.navigate(WholesaleRoutes.productList(categoryId))
            }
        )
    }

    composable(
        route = WholesaleRoutes.CategoryLevel2,
        arguments = listOf(
            navArgument(WholesaleRoutes.ArgCategoryId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val categoryState by categoryController.State.collectAsState()

        val categoryId = backStackEntry.arguments
            ?.getInt(WholesaleRoutes.ArgCategoryId)
            ?: return@composable

        LaunchedEffect(sessionState.Language.Id, categoryId) {
            categoryController.LoadDetail(
                languageId = sessionState.Language.Id,
                productCategoryId = categoryId
            )

            categoryController.LoadSpecialContents(
                languageId = sessionState.Language.Id,
                count = 6
            )
        }

        WholesaleCategoryLevel2Screen(
            categoryId = categoryId,
            isLoading = categoryState.IsLoading,
            errorMessage = categoryState.ErrorMessage,
            categoryInfo = categoryState.Category,
            childCategories = categoryState.ChildCategories,
            specialContents = categoryState.SpecialContents,
            isSpecialContentsLoading = categoryState.IsSpecialContentsLoading,
            onBackClick = {
                navigator.back()
            },
            onSubCategoryClick = { subCategoryId ->
                navigator.navController.navigate(
                    WholesaleRoutes.productList(subCategoryId)
                )
            },
            onProductListClick = {
                navigator.navController.navigate(WholesaleRoutes.productList(categoryId))
            },
            onCompanyListClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyList)
            },
            onRfqCreateClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            },
            onPopularProductGroupClick = { _, _ ->
                navigator.navController.navigate(WholesaleRoutes.productList(categoryId))
            }
        )
    }

    composable(
        route = WholesaleRoutes.ProductList,
        arguments = listOf(
            navArgument(WholesaleRoutes.ArgCategoryId) {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    ) { backStackEntry ->
        val productCategoryId = backStackEntry.arguments?.getInt(WholesaleRoutes.ArgCategoryId) ?: 0
        val productState by productController.State.collectAsState()

        LaunchedEffect(sessionState.Language.Id, productCategoryId) {
            productController.List(languageId = sessionState.Language.Id, productCategoryId = productCategoryId, page = 1, pageSize = 50)
        }

        WholesaleProductListScreen(
            State = productState,
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
                if (productId > 0) {
                    navigator.navController.navigate(
                        WholesaleRoutes.lastPriceRequest(productId)
                    )
                }
            },
            onSampleRequestClick = {
                if (productId > 0) {
                    navigator.navController.navigate(
                        WholesaleRoutes.sampleRequest(productId)
                    )
                }
            },
            onCustomizationRequestClick = {
                if (productId > 0) {
                    navigator.navController.navigate(
                        WholesaleRoutes.customizationRequest(productId)
                    )
                }
            },

            onCompanyClick = {
                val companyId = productState.ProductDetailResult?.Data?.CompanyId ?: 0
                if (companyId > 0) {
                    navigator.navController.navigate(CompanyRoutes.companyDetail(companyId))
                }
            },
            onCompanyProductsClick = {
                val companyId = productState.ProductDetailResult?.Data?.CompanyId ?: 0
                if (companyId > 0) {
                    navigator.navController.navigate(CompanyRoutes.companyProducts(companyId))
                }
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
                val companyId = productState.ProductDetailResult?.Data?.CompanyId ?: 0
                if (companyId > 0) {
                    navigator.navController.navigate(CompanyRoutes.companyProducts(companyId))
                }
            },
            onRelatedCategoryClick = { category ->
                if (category.id > 0) {
                    navigator.navController.navigate(
                        WholesaleRoutes.categoryLevel1(category.id)
                    )
                }
            }
        )
    }

    composable(
        route = WholesaleRoutes.LastPriceRequest,
        arguments = listOf(
            navArgument(WholesaleRoutes.ArgProductId) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val productState by productController.State.collectAsState()
        val requestState by wholesaleBuyerRequestController.State.collectAsState()
        val rfqState by rfqController.State.collectAsState()
        val requestProductId = backStackEntry.arguments?.getInt(WholesaleRoutes.ArgProductId) ?: 0
        LaunchedEffect(requestProductId, sessionState.Language.Id) {
            if (requestProductId > 0) {
                rfqController.LoadCreateOptions(sessionState.Language.Id)
                productController.Detail(
                    languageId = sessionState.Language.Id,
                    wholesaleProductId = requestProductId
                )
            }
        }
        val product = productState.ProductDetailResult?.Data

        LastPriceRequestScreen(
            productId = requestProductId,
            productName = product?.ProductName?.takeIf { it.isNotBlank() } ?: BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = ""),
            companyName = product?.CompanyName?.takeIf { it.isNotBlank() } ?: "Tedarikçi",
            currentPriceLabel = product?.Price?.takeIf { it > 0.0 }?.toString() ?: "",
            paymentTerms = rfqState.PaymentTerms,
            units = rfqState.Units,
            onBackClick = { navigator.back() },
            onSendClick = { quantity, unitId, targetPrice, paymentTerm, deliveryTarget, detail ->
                Log.d("BB_LAST_PRICE", "CLICK memberId=${sessionState.MemberId} languageId=${sessionState.Language.Id} productId=$requestProductId productNull=${product == null} companyId=${product?.CompanyId} unitId=$unitId paymentTerm=$paymentTerm quantity=$quantity targetPrice=$targetPrice")
                val selectedProduct = product
                if (selectedProduct != null) {
                    wholesaleBuyerRequestController.InsertLastPriceRequest(
                        languageId = sessionState.Language.Id,
                        model = WholesaleBuyerLastPriceRequestInsertModel(
                            InsertedBy = sessionState.MemberId,
                            CompanyId = selectedProduct.CompanyId,
                            WholesaleProductId = requestProductId,
                            ProductName = selectedProduct.ProductName,
                            Description = detail,
                            CategoryId = selectedProduct.ProductCategoryId,
                            UnitPriceRequested = targetPrice.replace(",", ".").toDoubleOrNull(),
                            CurrencyId = selectedProduct.Prices.firstOrNull()?.CurrencyId ?: 0,
                            EstimatedOrderQuantity = quantity.replace(",", ".").toDoubleOrNull() ?: 0.0,
                            UnitId = unitId.toIntOrNull() ?: 0,
                            CargoTarget = deliveryTarget
                        ),
                        onSuccess = {
                            navigator.back()
                        }
                    )
                }
            }
        )
    }

    composable(
        route = WholesaleRoutes.SampleRequest,
        arguments = listOf(
            navArgument(WholesaleRoutes.ArgProductId) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val productState by productController.State.collectAsState()
        val requestState by wholesaleBuyerRequestController.State.collectAsState()
        val requestProductId = backStackEntry.arguments?.getInt(WholesaleRoutes.ArgProductId) ?: 0
        LaunchedEffect(requestProductId, sessionState.Language.Id) {
            if (requestProductId > 0) {
                productController.Detail(
                    languageId = sessionState.Language.Id,
                    wholesaleProductId = requestProductId
                )
            }
        }
        val product = productState.ProductDetailResult?.Data

        SampleRequestScreen(
            productId = requestProductId,
            productName = product?.ProductName?.takeIf { it.isNotBlank() } ?: BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = ""),
            companyName = product?.CompanyName?.takeIf { it.isNotBlank() } ?: "Tedarikçi",
            onBackClick = { navigator.back() },
            onSendClick = { quantity, detail ->
                val selectedProduct = product
                if (selectedProduct != null) {
                    wholesaleBuyerRequestController.InsertSampleRequest(
                        languageId = sessionState.Language.Id,
                        model = WholesaleBuyerSampleRequestInsertModel(
                            InsertedBy = sessionState.MemberId,
                            CompanyId = selectedProduct.CompanyId,
                            WholesaleProductId = requestProductId,
                            ProductName = selectedProduct.ProductName,
                            Description = detail,
                            UnitId = selectedProduct.Prices.firstOrNull()?.UnitId ?: 0,
                            SamplePrice = selectedProduct.SamplePrice,
                            CurrencyId = selectedProduct.Prices.firstOrNull()?.CurrencyId ?: 0,
                            Quantity = quantity.toIntOrNull() ?: 1
                        ),
                        onSuccess = {
                            navigator.back()
                        }
                    )
                }
            }
        )
    }

    composable(
        route = WholesaleRoutes.CustomizationRequest,
        arguments = listOf(
            navArgument(WholesaleRoutes.ArgProductId) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val productState by productController.State.collectAsState()
        val requestState by wholesaleBuyerRequestController.State.collectAsState()
        val requestProductId = backStackEntry.arguments?.getInt(WholesaleRoutes.ArgProductId) ?: 0
        LaunchedEffect(requestProductId, sessionState.Language.Id) {
            if (requestProductId > 0) {
                productController.Detail(
                    languageId = sessionState.Language.Id,
                    wholesaleProductId = requestProductId
                )
            }
        }
        val product = productState.ProductDetailResult?.Data

        CustomizationRequestScreen(
            productId = requestProductId,
            productName = product?.ProductName?.takeIf { it.isNotBlank() } ?: BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = ""),
            companyName = product?.CompanyName?.takeIf { it.isNotBlank() } ?: "Tedarikçi",
            onBackClick = { navigator.back() },
            onSendClick = { detail, colorMaterial, sizeTechnical, packageLogo ->
                val selectedProduct = product
                if (selectedProduct != null) {
                    val combinedDescription = listOf(
                        detail.takeIf { it.isNotBlank() },
                        colorMaterial.takeIf { it.isNotBlank() }?.let { "Renk / Malzeme: $it" },
                        sizeTechnical.takeIf { it.isNotBlank() }?.let { "Ölçü / Teknik Detay: $it" },
                        packageLogo.takeIf { it.isNotBlank() }?.let { "Ambalaj / Logo: $it" }
                    ).filterNotNull().joinToString("\n\n")

                    wholesaleBuyerRequestController.InsertCustomizeRequest(
                        languageId = sessionState.Language.Id,
                        model = WholesaleBuyerCustomizeRequestInsertModel(
                            InsertedBy = sessionState.MemberId,
                            CompanyId = selectedProduct.CompanyId,
                            WholesaleProductId = requestProductId,
                            ProductName = selectedProduct.ProductName,
                            Description = combinedDescription,
                            SendOtherSeller = 0
                        ),
                        onSuccess = {
                            navigator.back()
                        }
                    )
                }
            }
        )
    }

    composable(route = RfqRoutes.List) {
        val rfqState by rfqController.State.collectAsState()
        val messageState by messageController.State.collectAsState()

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
            deletingBuyerRequestKey = rfqState.DeletingBuyerRequestKey,
            onBackClick = {
                navigator.back()
            },
            onDiscoverWholesaleClick = {
                navigator.navController.navigate(WholesaleRoutes.productList())
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

        val languageId = sessionState.Language.Id

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
            onCategorySearch = rfqController::SearchProductCategories,
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