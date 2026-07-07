package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Areas.b2c.Controllers.HomeController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductController
import com.bulbulustur.android.Application.Areas.b2c.Views.Basket.BasketScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Campaign.CampaignDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Campaign.CampaignListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Category.CategoryDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Category.RetailCategoryHomeScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Home.RetailHomeScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductListScreen as RetailProductListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Store.StoreDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Store.StoreListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Store.StoreOnboardingInfoScreen
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.BasketRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Navigation.Routes.StoreRoutes
import com.bulbulustur.android.Application.Areas.b2c.Controllers.BasketController
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductQuestionController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductReviewController
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductQuestionScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductReviewScreen
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Controllers.AccountController

fun NavGraphBuilder.retailGraph(
    navigator: BulbulusturNavigator,
    homeController: HomeController,
    productController: ProductController,
    productReviewController: ProductReviewController,
    productQuestionController: ProductQuestionController,
    accountController: AccountController,
    basketController: BasketController,
    sessionState: UserSessionState
) {
    composable(
        route = RetailRoutes.Home
    ) {
        val homeState by homeController.State.collectAsState()

        LaunchedEffect(sessionState.Language.Id) {
            homeController.Load(
                languageId = sessionState.Language.Id
            )
        }

        RetailHomeScreen(
            campaigns = homeState.Campaigns,
            dealsOfTheDays = homeState.DealsOfTheDays,
            specialContents = homeState.SpecialContents,
            onSearchClick = {
                navigator.navController.navigate(
                    RetailRoutes.Search
                )
            },
            onCategoryClick = {
                navigator.navController.navigate(
                    RetailRoutes.CategoryHome
                )
            },
            onProductListClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onProductDetailClick = {
                /*
                 * RetailHomeScreen henüz ProductId, StoreId ve VariantId
                 * taşımadığı için detail route doğrudan açılamaz.
                 */
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onDealClick = { productId, storeId, variantId ->
                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        productId = productId,
                        storeId = storeId,
                        variantId = variantId
                    )
                )
            },
            onCampaignClick = { campaignId ->
                navigator.navController.navigate(
                    RetailRoutes.CampaignDetail
                )
            },
            onCampaignListClick = {
                navigator.navController.navigate(
                    RetailRoutes.CampaignList
                )
            },
            onFavoriteClick = {
                navigator.navController.navigate(
                    AccountRoutes.Favorites
                )
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreDetail
                )
            },
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onMessageClick = {
                Unit
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

    composable(
        route = RetailRoutes.CategoryHome
    ) {
        RetailCategoryHomeScreen(
            onBackClick = {
                navigator.back()
            },
            onSearchClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onFavoriteClick = {
                navigator.navController.navigate(
                    AccountRoutes.Favorites
                )
            },
            onMessageClick = {
                Unit
            },
            onHomeClick = {
                navigator.navController.navigate(
                    RetailRoutes.Home
                )
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onBasketClick = {
                navigator.navigateToRetailBasket()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onProductListClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onSubCategoryClick = {
                navigator.navController.navigate(
                    RetailRoutes.CategoryDetail
                )
            },
            onCampaignClick = {
                navigator.navController.navigate(
                    RetailRoutes.CampaignList
                )
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreList
                )
            }
        )
    }

    composable(
        route = RetailRoutes.CategoryDetail
    ) {
        CategoryDetailScreen(
            onBackClick = {
                navigator.back()
            },
            onSubCategoryClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onProductClick = {
                /*
                 * CategoryDetailScreen henüz detail kimliklerini taşımıyor.
                 */
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onCampaignClick = {
                navigator.navController.navigate(
                    RetailRoutes.CampaignDetail
                )
            },
            onSearchClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            }
        )
    }

    composable(
        route = RetailRoutes.ProductList
    ) {
        val productState by
        productController.State.collectAsState()

        val productReviewState by
        productReviewController.State.collectAsState()

        val productQuestionState by
        productQuestionController.State.collectAsState()

        RetailProductListScreen(
            State =
                productState,
            OnLoadProducts = {
                    filters,
                    page,
                    pageSize ->

                productController.List(
                    filters =
                        filters,
                    page =
                        page,
                    pageSize =
                        pageSize
                )
            },
            onBackClick = {
                navigator.back()
            },
            onSearchClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onFavoriteClick = {
                navigator.navController.navigate(
                    AccountRoutes.Favorites
                )
            },
            onHomeClick = {
                navigator.navController.navigate(
                    RetailRoutes.Home
                )
            },
            onMessageClick = {
                Unit
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onBasketClick = {
                navigator.navigateToRetailBasket()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onProductDetailClick = {
                    productId,
                    storeId,
                    variantId ->

                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        productId =
                            productId,
                        storeId =
                            storeId,
                        variantId =
                            variantId
                    )
                )
            },
            onProductFavoriteClick = {
                Unit
            },
            onAddToBasketClick = {
                navigator.navigateToRetailBasket()
            }
        )
    }

    composable(
        route =
            RetailRoutes.ProductDetail,
        arguments =
            listOf(
                navArgument(
                    RetailRoutes.ArgProductId
                ) {
                    type =
                        NavType.IntType
                },
                navArgument(
                    RetailRoutes.ArgStoreId
                ) {
                    type =
                        NavType.IntType
                },
                navArgument(
                    RetailRoutes.ArgVariantId
                ) {
                    type =
                        NavType.IntType
                }
            )
    ) { backStackEntry ->

        val productId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgProductId
                )
                ?: 0

        val storeId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgStoreId
                )
                ?: 0

        val variantId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgVariantId
                )
                ?: 0

        val productState by
        productController.State.collectAsState()

        val productDetail =
            productState.ProductDetailResult
                ?.Data

        val productCategoryId =
            productDetail
                ?.ProductCategoryId
                ?: 0

        val brandId =
            productDetail
                ?.BrandId
                ?: 0

        LaunchedEffect(
            productId,
            storeId,
            variantId
        ) {
            if (
                productId <= 0 ||
                storeId <= 0
            ) {
                return@LaunchedEffect
            }

            productController.ClearProductDetail()

            productController.Detail(
                languageId = 1,
                storeId = storeId,
                productId = productId,
                variantId = variantId
            )

            productController.Variants(
                languageId = 1,
                productId = productId,
                storeId = storeId,
                count = 100
            )

            productReviewController.List(
                sourceType =
                    "PRODUCT",
                sourceId =
                    productId,
                variantId =
                    variantId,
                page =
                    1,
                pageSize =
                    10
            )

            productQuestionController.List(
                productId =
                    productId,
                count =
                    100
            )

            productController.SmallestPrice(
                languageId = 1,
                productId = productId
            )

            if (variantId > 0) {
                productController.SelectedVariant(
                    languageId = 1,
                    variantId = variantId
                )

                productController.VariantPictures(
                    variantId = variantId,
                    count = 10
                )

                productController.ColorVariants(
                    languageId = 1,
                    productId = productId,
                    variantId = variantId
                )

                productController.SizeVariants(
                    languageId = 1,
                    productId = productId,
                    variantId = variantId
                )

                productController.OtherSellerList(
                    languageId = 1,
                    productId = productId,
                    variantId = variantId,
                    storeId = storeId
                )
            }
        }

        LaunchedEffect(
            productCategoryId,
            brandId,
            sessionState.MemberId,
            productId,
            storeId,
            variantId
        ) {
            if (
                productId <= 0 ||
                storeId <= 0
            ) {
                return@LaunchedEffect
            }

            if (
                productCategoryId > 0
            ) {
                productController.SponsoredAdverts(
                    languageId =
                        1,
                    productCategoryId =
                        productCategoryId,
                    count =
                        8
                )

                productController.RelatedCategories(
                    languageId =
                        1,
                    productCategoryId =
                        productCategoryId
                )
            }

            if (
                brandId > 0
            ) {
                productController.ProductBrandSections(
                    languageId =
                        1,
                    brandId =
                        brandId,
                    count =
                        5
                )
            }

            if (
                sessionState.IsAuthenticated &&
                sessionState.MemberId > 0
            ) {
                productController.ProductBrowsingHistories(
                    memberId =
                        sessionState.MemberId,
                    page =
                        1,
                    pageSize =
                        20
                )

                if (
                    variantId > 0
                ) {
                    productController.InsertBrowsingHistory(
                        memberId =
                            sessionState.MemberId,
                        storeId =
                            storeId,
                        productId =
                            productId,
                        variantId =
                            variantId
                    )
                }
            }
        }

        ProductDetailScreen(
            State =
                productState,
            productId =
                productId,
            onBackClick = {
                navigator.back()
            },
            onColorVariantChange = { selectedVariantId ->
                productController.SelectedVariant(
                    languageId =
                        1,
                    variantId =
                        selectedVariantId
                )

                productController.VariantPictures(
                    variantId =
                        selectedVariantId,
                    count =
                        10
                )

                productController.SizeVariants(
                    languageId =
                        1,
                    productId =
                        productId,
                    variantId =
                        selectedVariantId
                )

                productController.OtherSellerList(
                    languageId =
                        1,
                    productId =
                        productId,
                    variantId =
                        selectedVariantId,
                    storeId =
                        storeId
                )
            },
            onReviewClick = {
                navigator.navController.navigate(
                    RetailRoutes.productReview(
                        productId =
                            productId,
                        storeId =
                            storeId,
                        variantId =
                            variantId
                    )
                )
            },
            onQuestionClick = {
                navigator.navController.navigate(
                    RetailRoutes.productQuestion(
                        productId =
                            productId,
                        storeId =
                            storeId,
                        variantId =
                            variantId
                    )
                )
            },
            onSizeVariantChange = { selectedVariantId ->
                productController.SelectedVariant(
                    languageId =
                        1,
                    variantId =
                        selectedVariantId
                )

                productController.VariantPictures(
                    variantId =
                        selectedVariantId,
                    count =
                        10
                )

                productController.OtherSellerList(
                    languageId =
                        1,
                    productId =
                        productId,
                    variantId =
                        selectedVariantId,
                    storeId =
                        storeId
                )
            },
            onAddToBasketClick = { selection ->
                if (
                    !sessionState.IsAuthenticated ||
                    sessionState.MemberId <= 0
                ) {
                    navigator.navigateToAccount()
                } else {
                    basketController.AddToBasket(
                        memberId =
                            sessionState.MemberId,
                        priceId =
                            selection.priceId,
                        quantity =
                            selection.quantity
                    )
                }
            },
            onBuyNowClick = { selection ->
                if (
                    !sessionState.IsAuthenticated ||
                    sessionState.MemberId <= 0
                ) {
                    navigator.navigateToAccount()
                } else {
                    basketController.AddToBasket(
                        memberId =
                            sessionState.MemberId,
                        priceId =
                            selection.priceId,
                        quantity =
                            selection.quantity,
                        onSuccess = {
                            navigator.navigateToRetailBasket()
                        }
                    )
                }
            },
            onSponsoredAdvertClick = { advert ->
                if (
                    advert.ProductId > 0 &&
                    advert.StoreId > 0 &&
                    advert.VariantId > 0
                ) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId =
                                advert.ProductId,
                            storeId =
                                advert.StoreId,
                            variantId =
                                advert.VariantId
                        )
                    )
                }
            },
            onBrandSectionPageClick = { page ->
                if (
                    page.ProductId > 0 &&
                    page.StoreId > 0 &&
                    page.VariantId > 0
                ) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId =
                                page.ProductId,
                            storeId =
                                page.StoreId,
                            variantId =
                                page.VariantId
                        )
                    )
                } else if (
                    page.ProductCategoryId > 0
                ) {
                    /*
                     * Retail kategori route'u henüz categoryId taşımıyor.
                     * Şimdilik mevcut kategori detay ekranına gidiyoruz.
                     */
                    navigator.navController.navigate(
                        RetailRoutes.CategoryDetail
                    )
                }
            },
            onBrowsingHistoryProductClick = { history ->
                if (
                    history.ProductId > 0 &&
                    history.StoreId > 0 &&
                    history.VariantId > 0
                ) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId =
                                history.ProductId,
                            storeId =
                                history.StoreId,
                            variantId =
                                history.VariantId
                        )
                    )
                }
            },
            onRelatedCategoryClick = {
                /*
                 * RetailRoutes.CategoryDetail henüz categoryId taşımıyor.
                 */
                navigator.navController.navigate(
                    RetailRoutes.CategoryDetail
                )
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreDetail
                )
            }
        )
    }

    composable(
        route =
            RetailRoutes.ProductReview,
        arguments =
            listOf(
                navArgument(
                    RetailRoutes.ArgProductId
                ) {
                    type =
                        NavType.IntType
                },
                navArgument(
                    RetailRoutes.ArgStoreId
                ) {
                    type =
                        NavType.IntType
                },
                navArgument(
                    RetailRoutes.ArgVariantId
                ) {
                    type =
                        NavType.IntType
                }
            )
    ) { backStackEntry ->
        val productId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgProductId
                )
                ?: 0

        val storeId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgStoreId
                )
                ?: 0

        val variantId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgVariantId
                )
                ?: 0

        val productState by
        productController.State.collectAsState()

        val reviewState by
        productReviewController.State.collectAsState()

        LaunchedEffect(
            productId,
            storeId,
            variantId
        ) {
            if (
                productId <= 0 ||
                storeId <= 0
            ) {
                return@LaunchedEffect
            }

            productReviewController.Clear()

            productReviewController.List(
                sourceType =
                    "PRODUCT",
                sourceId =
                    productId,
                variantId =
                    variantId,
                page =
                    1,
                pageSize =
                    10
            )

            val currentProduct =
                productState.ProductDetailResult
                    ?.Data

            if (
                currentProduct == null ||
                currentProduct.ProductId != productId
            ) {
                productController.Detail(
                    languageId =
                        1,
                    storeId =
                        storeId,
                    productId =
                        productId,
                    variantId =
                        variantId
                )
            }
        }

        ProductReviewScreen(
            State =
                reviewState,
            product =
                productState.ProductDetailResult
                    ?.Data,
            onBackClick = {
                navigator.back()
            },
            onLoadMoreClick = {
                productReviewController.LoadMore()
            }
        )
    }

    composable(
        route =
            RetailRoutes.ProductQuestion,
        arguments =
            listOf(
                navArgument(
                    RetailRoutes.ArgProductId
                ) {
                    type =
                        NavType.IntType
                },
                navArgument(
                    RetailRoutes.ArgStoreId
                ) {
                    type =
                        NavType.IntType
                },
                navArgument(
                    RetailRoutes.ArgVariantId
                ) {
                    type =
                        NavType.IntType
                }
            )
    ) { backStackEntry ->
        val productId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgProductId
                )
                ?: 0

        val storeId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgStoreId
                )
                ?: 0

        val variantId =
            backStackEntry.arguments
                ?.getInt(
                    RetailRoutes.ArgVariantId
                )
                ?: 0

        val productState by
        productController.State.collectAsState()

        val questionState by
        productQuestionController.State.collectAsState()

        val product =
            productState.ProductDetailResult
                ?.Data

        LaunchedEffect(
            productId,
            storeId,
            variantId
        ) {
            if (
                productId <= 0 ||
                storeId <= 0
            ) {
                return@LaunchedEffect
            }

            productQuestionController.Clear()

            productQuestionController.List(
                productId =
                    productId,
                count =
                    100
            )

            if (
                product == null ||
                product.ProductId != productId
            ) {
                productController.Detail(
                    languageId =
                        1,
                    storeId =
                        storeId,
                    productId =
                        productId,
                    variantId =
                        variantId
                )
            }
        }

        ProductQuestionScreen(
            State =
                questionState,
            productId =
                productId,
            productName =
                product
                    ?.ProductName
                    .orEmpty(),
            storeName =
                product
                    ?.Store
                    .orEmpty(),
            variantId =
                variantId,
            isAuthenticated =
                sessionState.IsAuthenticated,
            onBackClick = {
                navigator.back()
            },
            onLoginRequired = {
                navigator.navController.navigate(
                    LogonRoutes.Logon
                )
            },
            onInsertQuestion = {
                /*
                 * MemberId henüz session contract'ında bulunmuyor.
                 * Backend JWT claim düzeltmesinden sonra burada
                 * productQuestionController.Insert(...) çağrılacak.
                 */
            }
        )
    }

    composable(
        route = StoreRoutes.StoreList
    ) {
        StoreListScreen(
            onBackClick = {
                navigator.back()
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreDetail
                )
            },
            onSellerInfoClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreOnboardingInfo
                )
            },
            onHowItWorksClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreOnboardingInfo
                )
            }
        )
    }

    composable(
        route = StoreRoutes.StoreOnboardingInfo
    ) {
        StoreOnboardingInfoScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = StoreRoutes.StoreDetail,
        arguments = listOf(
            navArgument(StoreRoutes.ArgStoreId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val storeId = backStackEntry.arguments
            ?.getInt(StoreRoutes.ArgStoreId)
            ?: 0

        StoreDetailScreen(
            storeId = storeId,
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route =
            BasketRoutes.Basket
    ) {
        val basketState by
        basketController.State.collectAsState()

        val accountState by
        accountController.State.collectAsState()

        LaunchedEffect(
            sessionState.IsAuthenticated,
            sessionState.MemberId
        ) {
            if (
                sessionState.IsAuthenticated &&
                sessionState.MemberId > 0
            ) {
                basketController.Refresh(
                    memberId =
                        sessionState.MemberId
                )

                accountController.GetProductFavorites(
                    memberId =
                        sessionState.MemberId
                )
            } else {
                basketController.Clear()
            }
        }

        BasketScreen(
            State =
                basketState,
            favorites =
                accountState.ProductFavorites,
            isFavoriteLoading =
                accountState.IsLoading &&
                        (
                                accountState.CurrentAction == "GetProductFavorites" ||
                                        accountState.CurrentAction == "MoveProductFavoriteToBasket"
                                ),
            favoriteErrorMessage =
                accountState.ProductFavoriteListResult
                    ?.takeIf {
                        !it.Success
                    }
                    ?.Message,
            onBackClick = {
                navigator.back()
            },
            onRetryFavoritesClick = {
                accountController.GetProductFavorites(
                    memberId =
                        sessionState.MemberId
                )
            },
            onAddFavoriteToBasketClick = { favorite ->
                accountController.MoveProductFavoriteToBasket(
                    memberId =
                        sessionState.MemberId,
                    favoriteId =
                        favorite.FavoriteId,
                    onSuccess = {
                        basketController.Refresh(
                            memberId =
                                sessionState.MemberId
                        )

                        accountController.GetProductFavorites(
                            memberId =
                                sessionState.MemberId
                        )
                    }
                )
            },
            onCheckoutClick = {
                /*
                 * Checkout feature açıldığında:
                 * navigator.navController.navigate(
                 *     BasketRoutes.Checkout
                 * )
                 */
            },
            onProductClick = { basket ->
                if (
                    basket.ProductId > 0 &&
                    basket.StoreId > 0 &&
                    basket.VariantId > 0
                ) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId =
                                basket.ProductId,
                            storeId =
                                basket.StoreId,
                            variantId =
                                basket.VariantId
                        )
                    )
                } else {
                    navigator.navController.navigate(
                        RetailRoutes.ProductList
                    )
                }
            },
            onStoreClick = { storeId ->
                if (storeId > 0) {
                    navigator.navController.navigate(
                        StoreRoutes.storeDetail(storeId)
                    )
                }
            },
            onIncreaseQuantityClick = { basket ->
                basketController.UpdateQuantity(
                    memberId =
                        sessionState.MemberId,
                    basketId =
                        basket.BasketId,
                    quantity =
                        basket.Quantity + 1
                )
            },
            onDecreaseQuantityClick = { basket ->
                if (basket.Quantity <= 1) {
                    basketController.Delete(
                        memberId =
                            sessionState.MemberId,
                        basketId =
                            basket.BasketId
                    )
                } else {
                    basketController.UpdateQuantity(
                        memberId =
                            sessionState.MemberId,
                        basketId =
                            basket.BasketId,
                        quantity =
                            basket.Quantity - 1
                    )
                }
            },
            onRemoveClick = { basket ->
                basketController.Delete(
                    memberId =
                        sessionState.MemberId,
                    basketId =
                        basket.BasketId
                )
            },
            onMoveToFavoriteClick = { basket ->
                basketController.MoveToFavorite(
                    memberId =
                        sessionState.MemberId,
                    basketId =
                        basket.BasketId
                )
            },
            onHomeClick = {
                navigator.navController.navigate(
                    RetailRoutes.Home
                )
            },
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            }
        )
    }

    composable(
        route = RetailRoutes.CampaignList
    ) {
        CampaignListScreen(
            onBackClick = {
                navigator.back()
            },
            onCampaignClick = {
                navigator.navController.navigate(
                    RetailRoutes.CampaignDetail
                )
            }
        )
    }

    composable(
        route = RetailRoutes.CampaignDetail
    ) {
        CampaignDetailScreen(
            onBackClick = {
                navigator.back()
            },
            onSearchClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onFavoriteClick = {
                navigator.navController.navigate(
                    AccountRoutes.Favorites
                )
            },
            onMessageClick = {
                Unit
            },
            onHomeClick = {
                navigator.navController.navigate(
                    RetailRoutes.Home
                )
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onBasketClick = {
                navigator.navigateToRetailBasket()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            },
            onProductClick = {
                /*
                 * CampaignDetailScreen henüz detail kimliklerini taşımıyor.
                 */
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onCategoryClick = {
                navigator.navController.navigate(
                    RetailRoutes.CategoryDetail
                )
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreDetail
                )
            }
        )
    }
}