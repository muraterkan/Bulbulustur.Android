package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Areas.b2c.Controllers.CategoryController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.CampaignController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.HomeController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.SearchController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.StoreController
import com.bulbulustur.android.Application.Areas.b2c.Views.Basket.BasketScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Campaign.CampaignDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Campaign.CampaignListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Category.CategoryLevel1Screen
import com.bulbulustur.android.Application.Areas.b2c.Views.Category.CategoryLevel2Screen
import com.bulbulustur.android.Application.Areas.b2c.Views.Category.RetailCategoryHomeScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Home.RetailHomeScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.DealsOfTheDay.DealsOfTheDayListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.OtherSellerListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductListScreen as RetailProductListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Store.StoreDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Store.StoreListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Store.StoreOnboardingInfoScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Store.SearchScreen as RetailSearchScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Search.SearchScreen as RetailSearchScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Search.RetailSearchType

import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.BasketRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Navigation.Routes.StoreRoutes
import com.bulbulustur.android.Application.Areas.b2c.Controllers.BasketController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.DealsOfTheDayController
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductQuestionController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductReviewController
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductHomepageSpecialListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductCategoryContentListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductQuestionScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.Product.ProductReviewScreen
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Controllers.AccountController
import com.bulbulustur.android.Application.Areas.b2c.Views.Store.StoreProductListScreen
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductFilterDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAlarmListInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductComplaintInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductLowPriceReportInsertModel
import coil3.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

fun NavGraphBuilder.retailGraph(
    navigator: BulbulusturNavigator,
    categoryController: CategoryController,
    homeController: HomeController,
    campaignController: CampaignController,
    dealsOfTheDayController: DealsOfTheDayController,
    productController: ProductController,
    productReviewController: ProductReviewController,
    productQuestionController: ProductQuestionController,
    searchController: SearchController,
    storeController: StoreController,
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
            onProductDetailClick = { productId, storeId, variantId ->
                if (productId > 0 && storeId > 0 && variantId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId = productId,
                            storeId = storeId,
                            variantId = variantId
                        )
                    )
                } else {
                    navigator.navController.navigate(
                        RetailRoutes.ProductList
                    )
                }
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
            onDealsOfTheDayListClick = {
                navigator.navController.navigate(
                    RetailRoutes.DealsOfTheDayList
                )
            },
            onCampaignClick = { campaignId ->
                navigator.navController.navigate(
                    RetailRoutes.campaignDetail(campaignId)
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
                    StoreRoutes.StoreList
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

    composable(route = RetailRoutes.Search) {
        val searchState by searchController.State.collectAsState()

        android.util.Log.d(
            "BB_SEARCH",
            "RetailGraph state hasResult=${searchState.ProductSearchResult != null} count=${searchState.ProductSearchResult?.Data?.Items?.size ?: -1}"
        )

        RetailSearchScreen(
            onBackClick = {
                navigator.back()
            },
            onFavoriteClick = {
                navigator.navController.navigate(AccountRoutes.Favorites)
            },
            onMessageClick = {
                navigator.navigateToInbox()
            },
            onHomeClick = {
                navigator.navController.navigate(RetailRoutes.Home) {
                    launchSingleTop = true
                }
            },
            onMenuClick = {
                navigator.navController.navigate(RetailRoutes.CategoryHome)
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
            onSearchSubmit = { key: String, searchType: RetailSearchType ->
                android.util.Log.d("BB_SEARCH", "RetailGraph onSearchSubmit key=$key type=$searchType")

                if (searchType == RetailSearchType.Product) {
                    searchController.SearchProducts(
                        storeId = 0,
                        key = key,
                        page = 1,
                        pageSize = 20
                    )
                }
            },


            onProductClick = { product ->

                navigator.navController.navigate(RetailRoutes.ProductList)
            },
            onCategoryClick = {
                navigator.navController.navigate(RetailRoutes.CategoryHome)
            },
            onBrandClick = {
                navigator.navController.navigate(RetailRoutes.ProductList)
            },
            onStoreClick = {
                navigator.navController.navigate(StoreRoutes.StoreList)
            },
            productResults = searchState.ProductSearchResult?.Data?.Items.orEmpty(),
            hasProductSearch = searchState.ProductSearchResult != null
        )
    }

    composable(
        route = RetailRoutes.CategoryHome
    ) {
        val categoryState by categoryController.State.collectAsState()

        LaunchedEffect(sessionState.Language.Id) {
            categoryController.LoadHome(
                languageId = sessionState.Language.Id
            )
        }
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
            categories = categoryState.Categories,
            isLoading = categoryState.IsLoading,
            errorMessage = categoryState.ErrorMessage,
            onSubCategoryClick = { categoryId ->
                navigator.navController.navigate(
                    RetailRoutes.categoryLevel1(categoryId)
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
        route = RetailRoutes.CategoryLevel1,
        arguments = listOf(
            navArgument(RetailRoutes.ArgCategoryId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->

        val categoryState by categoryController.State.collectAsState()
        val productState by productController.State.collectAsState()
        val campaignState by campaignController.State.collectAsState()
        val dealsOfTheDayState by dealsOfTheDayController.State.collectAsState()

        val categoryId =
            backStackEntry.arguments
                ?.getInt(RetailRoutes.ArgCategoryId)
                ?: return@composable

        LaunchedEffect(
            sessionState.Language.Id,
            categoryId
        ) {

            categoryController.LoadDetail(
                languageId = sessionState.Language.Id,
                productCategoryId = categoryId
            )


            categoryController.LoadProductCategoryContents(
                languageId = sessionState.Language.Id,
                productCategoryId = categoryId,
                groupCount = 3,
                productCount = 4
            )

            categoryController.LoadCategoryBrands(
                productCategoryId = categoryId,
                count = 30
            )

            productController.List(
                filters = B2CProductFilterDTO(
                    ProductCategoryId = categoryId,
                    LanguageId = sessionState.Language.Id,
                    SortOrder = "Name_Desc"
                ),
                page = 1,
                pageSize = 8
            )

            campaignController.LoadCampaignsByCategory(
                languageId = sessionState.Language.Id,
                categoryId = categoryId,
                count = 8
            )

            val categoryScopeIds = categoryController.GetCategoryScopeIds(categoryId)

            dealsOfTheDayController.LoadByProductCategoryList(
                languageId = sessionState.Language.Id,
                productCategoryIds = categoryScopeIds,
                count = 6
            )

            productController.SponsoredAdvertsByProductCategoryList(
                languageId = sessionState.Language.Id,
                productCategoryIds = categoryScopeIds,
                count = 6
            )
        }


        CategoryLevel1Screen(
            categoryId = categoryId,
            categoryInfo = categoryState.Category,

            childCategories = categoryState.ChildCategories,
            categoryContents = categoryState.CategoryContents,
            isCategoryContentsLoading = categoryState.IsCategoryContentsLoading,
            products = productState.ProductListData
                ?.Products2
                ?.Items
                .orEmpty(),
            campaigns = campaignState.Campaigns,
            dealsOfTheDays = dealsOfTheDayState.CategoryDealsOfTheDays,
            sponsoredAdverts = productState.CategorySponsoredAdverts,
            categoryBrands = categoryState.CategoryBrands,
            isCategoryBrandsLoading = categoryState.IsCategoryBrandsLoading,
            isProductLoading =
                productState.IsLoading &&
                        productState.ProductListData == null,
            onBackClick = {
                navigator.back()
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
            onSubCategoryClick = { subCategoryId ->
                if (subCategoryId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productList(
                            subCategoryId
                        )
                    )
                }
            },
            onCategoryViewAllClick = { content ->
                if (content.ProductCategoryContentGroupId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productCategoryContentList(
                            categoryContentGroupId = content.ProductCategoryContentGroupId,
                            groupName = content.GroupName
                        )
                    )
                }
            },
            onCategoryProductClick = { special ->
                if (special.ProductId > 0 && special.StoreId > 0 && special.VariantId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId = special.ProductId,
                            storeId = special.StoreId,
                            variantId = special.VariantId
                        )
                    )
                }
            },
            onCategoryProductFavoriteClick = { special ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    accountController.InsertProductFavorite(
                        memberId = sessionState.MemberId,
                        model = ProductFavoriteInsertModel(
                            InsertedBy = sessionState.MemberId,
                            StoreId = special.StoreId,
                            ProductId = special.ProductId,
                            VariantId = special.VariantId
                        ),
                        onSuccess = {
                            accountController.GetProductFavorites(
                                memberId = sessionState.MemberId
                            )
                        }
                    )
                }
            },
            onCategoryAddToBasketClick = { priceId ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else if (priceId > 0) {
                    basketController.AddToBasket(
                        memberId = sessionState.MemberId,
                        priceId = priceId,
                        quantity = 1,
                        onSuccess = {
                            basketController.Refresh(
                                memberId = sessionState.MemberId
                            )
                        }
                    )
                }
            },
            onProductClick = { product ->

                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        productId = product.ProductId,
                        storeId = product.StoreId,
                        variantId = product.VariantId
                    )
                )
            },
            onProductFavoriteClick = { product ->
                if (
                    !sessionState.IsAuthenticated ||
                    sessionState.MemberId <= 0
                ) {
                    navigator.navController.navigate(
                        LogonRoutes.Logon
                    )
                } else {
                    accountController.InsertProductFavorite(
                        memberId = sessionState.MemberId,
                        model = ProductFavoriteInsertModel(
                            InsertedBy = sessionState.MemberId,
                            StoreId = product.StoreId,
                            ProductId = product.ProductId,
                            VariantId = product.VariantId
                        ),
                        onSuccess = {
                            accountController.GetProductFavorites(
                                memberId = sessionState.MemberId
                            )
                        }
                    )
                }
            },
            onAddToBasketClick = { priceId ->
                if (
                    !sessionState.IsAuthenticated ||
                    sessionState.MemberId <= 0
                ) {
                    navigator.navController.navigate(
                        LogonRoutes.Logon
                    )
                } else {
                    basketController.AddToBasket(
                        memberId = sessionState.MemberId,
                        priceId = priceId,
                        quantity = 1,
                        onSuccess = {
                            basketController.Refresh(
                                memberId = sessionState.MemberId
                            )
                        }
                    )
                }
            },
            onSponsoredProductClick = { advert ->
                if (advert.ProductId > 0 && advert.StoreId > 0 && advert.VariantId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId = advert.ProductId,
                            storeId = advert.StoreId,
                            variantId = advert.VariantId
                        )
                    )
                }
            },
            onSponsoredFavoriteClick = { advert ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    accountController.InsertProductFavorite(
                        memberId = sessionState.MemberId,
                        model = ProductFavoriteInsertModel(
                            InsertedBy = sessionState.MemberId,
                            StoreId = advert.StoreId,
                            ProductId = advert.ProductId,
                            VariantId = advert.VariantId
                        ),
                        onSuccess = {
                            accountController.GetProductFavorites(memberId = sessionState.MemberId)
                        }
                    )
                }
            },
            onSponsoredAddToBasketClick = { priceId ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    basketController.AddToBasket(
                        memberId = sessionState.MemberId,
                        priceId = priceId,
                        quantity = 1,
                        onSuccess = {
                            basketController.Refresh(memberId = sessionState.MemberId)
                        }
                    )
                }
            },
            onCampaignClick = { campaign ->
                if (campaign.CampaignId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.campaignDetail(
                            campaign.CampaignId
                        )
                    )
                }
            },
            onSearchClick = {
                navigator.navController.navigate(
                    RetailRoutes.productList(categoryId)
                )
            }
        )
    }

    composable(
        route = RetailRoutes.ProductList,
        arguments = listOf(
            navArgument(RetailRoutes.ArgCategoryId) {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    ) { backStackEntry ->
        val productCategoryId = backStackEntry.arguments?.getInt(RetailRoutes.ArgCategoryId) ?: 0
        val productState by
        productController.State.collectAsState()

        val productReviewState by
        productReviewController.State.collectAsState()

        val productQuestionState by
        productQuestionController.State.collectAsState()

        val categoryIds = remember(productCategoryId) {
            categoryController.GetCategoryScopeIds(productCategoryId)
        }

        RetailProductListScreen(
            State = productState,
            languageId = sessionState.Language.Id,
            productCategoryId = productCategoryId,
            categoryIds = categoryIds,
            OnLoadProducts = {
                    filters,
                    page,
                    pageSize ->

                productController.List(
                    filters = filters,
                    page = page,
                    pageSize = pageSize
                )
            },
            onBackClick = { navigator.back()},
            onSearchClick = { navigator.navController.navigate(RetailRoutes.ProductList )},
            onMenuClick = { navigator.navigateToRetailCategories()},
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
            onProductFavoriteClick = { product ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    accountController.InsertProductFavorite(
                        memberId = sessionState.MemberId,
                        model = ProductFavoriteInsertModel(
                            InsertedBy = sessionState.MemberId,
                            StoreId = product.StoreId,
                            ProductId = product.ProductId,
                            VariantId = product.VariantId
                        ),
                        onSuccess = {
                            accountController.GetProductFavorites(memberId = sessionState.MemberId)
                        }
                    )
                }
            },
            onAddToBasketClick = { priceId ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    basketController.AddToBasket(
                        memberId = sessionState.MemberId,
                        priceId = priceId,
                        quantity = 1,
                        onSuccess = {
                            basketController.Refresh(memberId = sessionState.MemberId)
                        }
                    )
                }
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
                        productId = productId,
                        storeId = storeId,
                        variantId = variantId
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
            onOtherSellerClick = {
                navigator.navController.navigate(
                    RetailRoutes.otherSellerList(
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
                    navigator.navController.navigate(
                        RetailRoutes.categoryLevel2(page.ProductCategoryId)
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
                if (productCategoryId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.categoryLevel2(productCategoryId)
                    )
                }
            },
            onStoreClick = {
                if (storeId > 0) {
                    navigator.navController.navigate(
                        StoreRoutes.storeDetail(storeId)
                    )
                }
            }
        )
    }

    composable(
        route = RetailRoutes.CategoryLevel2,
        arguments = listOf(
            navArgument(RetailRoutes.ArgCategoryId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->

        val categoryState by categoryController.State.collectAsState()
        val productState by productController.State.collectAsState()
        val campaignState by campaignController.State.collectAsState()
        val dealsOfTheDayState by dealsOfTheDayController.State.collectAsState()

        val categoryId =
            backStackEntry.arguments
                ?.getInt(RetailRoutes.ArgCategoryId)
                ?: return@composable

        LaunchedEffect(
            sessionState.Language.Id,
            categoryId
        ) {

            categoryController.LoadDetail(
                languageId = sessionState.Language.Id,
                productCategoryId = categoryId
            )


            categoryController.LoadSpecialContents(
                languageId = sessionState.Language.Id,
                count = 5
            )

            productController.List(
                filters = B2CProductFilterDTO(
                    ProductCategoryId = categoryId,
                    LanguageId = sessionState.Language.Id,
                    SortOrder = "Name_Desc"
                ),
                page = 1,
                pageSize = 8
            )

            campaignController.LoadCampaignsByCategory(
                languageId = sessionState.Language.Id,
                categoryId = categoryId,
                count = 8
            )

            productController.SponsoredAdverts(
                languageId = sessionState.Language.Id,
                productCategoryId = categoryId,
                count = 6
            )
        }


        CategoryLevel2Screen(
            categoryId = categoryId,
            categoryInfo = categoryState.Category,

            childCategories = categoryState.ChildCategories,
            categoryContents = categoryState.CategoryContents,
            isCategoryContentsLoading = categoryState.IsCategoryContentsLoading,
            products = productState.ProductListData
                ?.Products2
                ?.Items
                .orEmpty(),
            campaigns = campaignState.Campaigns,
            dealsOfTheDays = dealsOfTheDayState.CategoryDealsOfTheDays,
            sponsoredAdverts = productState.CategorySponsoredAdverts,
            categoryBrands = categoryState.CategoryBrands,
            isCategoryBrandsLoading = categoryState.IsCategoryBrandsLoading,
            isProductLoading =
                productState.IsLoading &&
                        productState.ProductListData == null,
            onBackClick = {
                navigator.back()
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
            onSubCategoryClick = { subCategoryId ->
                if (subCategoryId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.categoryLevel2(
                            subCategoryId
                        )
                    )
                }
            },
            onCategoryViewAllClick = { content ->
                if (content.ProductCategoryContentGroupId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productCategoryContentList(
                            categoryContentGroupId = content.ProductCategoryContentGroupId,
                            groupName = content.GroupName
                        )
                    )
                }
            },
            onCategoryProductClick = { special ->
                if (special.ProductId > 0 && special.StoreId > 0 && special.VariantId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId = special.ProductId,
                            storeId = special.StoreId,
                            variantId = special.VariantId
                        )
                    )
                }
            },
            onCategoryProductFavoriteClick = { special ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    accountController.InsertProductFavorite(
                        memberId = sessionState.MemberId,
                        model = ProductFavoriteInsertModel(
                            InsertedBy = sessionState.MemberId,
                            StoreId = special.StoreId,
                            ProductId = special.ProductId,
                            VariantId = special.VariantId
                        ),
                        onSuccess = {
                            accountController.GetProductFavorites(
                                memberId = sessionState.MemberId
                            )
                        }
                    )
                }
            },
            onCategoryAddToBasketClick = { priceId ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else if (priceId > 0) {
                    basketController.AddToBasket(
                        memberId = sessionState.MemberId,
                        priceId = priceId,
                        quantity = 1,
                        onSuccess = {
                            basketController.Refresh(
                                memberId = sessionState.MemberId
                            )
                        }
                    )
                }
            },
            onProductClick = { product ->

                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        productId = product.ProductId,
                        storeId = product.StoreId,
                        variantId = product.VariantId
                    )
                )
            },
            onProductFavoriteClick = { product ->
                if (
                    !sessionState.IsAuthenticated ||
                    sessionState.MemberId <= 0
                ) {
                    navigator.navController.navigate(
                        LogonRoutes.Logon
                    )
                } else {
                    accountController.InsertProductFavorite(
                        memberId = sessionState.MemberId,
                        model = ProductFavoriteInsertModel(
                            InsertedBy = sessionState.MemberId,
                            StoreId = product.StoreId,
                            ProductId = product.ProductId,
                            VariantId = product.VariantId
                        ),
                        onSuccess = {
                            accountController.GetProductFavorites(
                                memberId = sessionState.MemberId
                            )
                        }
                    )
                }
            },
            onAddToBasketClick = { priceId ->
                if (
                    !sessionState.IsAuthenticated ||
                    sessionState.MemberId <= 0
                ) {
                    navigator.navController.navigate(
                        LogonRoutes.Logon
                    )
                } else {
                    basketController.AddToBasket(
                        memberId = sessionState.MemberId,
                        priceId = priceId,
                        quantity = 1,
                        onSuccess = {
                            basketController.Refresh(
                                memberId = sessionState.MemberId
                            )
                        }
                    )
                }
            },
            onSponsoredProductClick = { advert ->
                if (advert.ProductId > 0 && advert.StoreId > 0 && advert.VariantId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId = advert.ProductId,
                            storeId = advert.StoreId,
                            variantId = advert.VariantId
                        )
                    )
                }
            },
            onSponsoredFavoriteClick = { advert ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    accountController.InsertProductFavorite(
                        memberId = sessionState.MemberId,
                        model = ProductFavoriteInsertModel(
                            InsertedBy = sessionState.MemberId,
                            StoreId = advert.StoreId,
                            ProductId = advert.ProductId,
                            VariantId = advert.VariantId
                        ),
                        onSuccess = {
                            accountController.GetProductFavorites(memberId = sessionState.MemberId)
                        }
                    )
                }
            },
            onSponsoredAddToBasketClick = { priceId ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    basketController.AddToBasket(
                        memberId = sessionState.MemberId,
                        priceId = priceId,
                        quantity = 1,
                        onSuccess = {
                            basketController.Refresh(memberId = sessionState.MemberId)
                        }
                    )
                }
            },
            onCampaignClick = { campaign ->
                if (campaign.CampaignId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.campaignDetail(
                            campaign.CampaignId
                        )
                    )
                }
            },
            onSearchClick = {
                navigator.navController.navigate(
                    RetailRoutes.productList(categoryId)
                )
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
            onOtherSellerClick = {
                navigator.navController.navigate(
                    RetailRoutes.otherSellerList(
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
                    navigator.navController.navigate(
                        RetailRoutes.categoryLevel2(page.ProductCategoryId)
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
                if (productCategoryId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.categoryLevel2(productCategoryId)
                    )
                }
            },
            onStoreClick = {
                if (storeId > 0) {
                    navigator.navController.navigate(
                        StoreRoutes.storeDetail(storeId)
                    )
                }
            }
        )
    }
    composable(
        route = RetailRoutes.ProductCategoryContentList,
        arguments = listOf(
            navArgument(RetailRoutes.ArgCategoryContentGroupId) {
                type = NavType.IntType
            },
            navArgument(RetailRoutes.ArgCategoryContentGroupName) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val categoryState by categoryController.State.collectAsState()

        val groupId = backStackEntry.arguments
            ?.getInt(RetailRoutes.ArgCategoryContentGroupId)
            ?: 0

        val groupName = backStackEntry.arguments
            ?.getString(RetailRoutes.ArgCategoryContentGroupName)
            .orEmpty()

        val pagedResult = categoryState.ProductCategoryContentListResult?.Data
        val currentPage = pagedResult?.PageNumber ?: 1

        LaunchedEffect(groupId) {
            categoryController.LoadProductCategoryContentPage(
                productCategoryContentGroupId = groupId,
                page = 1,
                pageSize = 20
            )
        }

        ProductCategoryContentListScreen(
            groupName = groupName,
            products = pagedResult?.Items.orEmpty(),
            currentPage = currentPage,
            totalPages = pagedResult?.TotalPageCount ?: 1,
            totalItemCount = pagedResult?.TotalItemCount ?: 0,
            isLoading = categoryState.IsCategoryContentListLoading,
            errorMessage = categoryState.CategoryContentListErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onPageChange = { page ->
                categoryController.LoadProductCategoryContentPage(
                    productCategoryContentGroupId = groupId,
                    page = page,
                    pageSize = 20
                )
            },
            onProductClick = { product ->
                if (product.ProductId > 0 && product.StoreId > 0 && product.VariantId > 0) {
                    navigator.navController.navigate(
                        RetailRoutes.productDetail(
                            productId = product.ProductId,
                            storeId = product.StoreId,
                            variantId = product.VariantId
                        )
                    )
                }
            }
        )
    }

    composable(
        route = RetailRoutes.ProductHomepageSpecialList,
        arguments = listOf(
            navArgument(RetailRoutes.ArgSpecialGroupId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->

        val specialGroupId =
            backStackEntry.arguments
                ?.getInt(RetailRoutes.ArgSpecialGroupId)
                ?: return@composable

        ProductHomepageSpecialListScreen(
            groupName = "Ürünler",
            products = emptyList(),
            currentPage = 1,
            totalPages = 1,
            totalItemCount = 0,
            isLoading = false,
            onBackClick = {
                navigator.back()
            }
        )
    }
    composable(
        route =
            RetailRoutes.OtherSellerList,
        arguments =
            listOf(
                navArgument(RetailRoutes.ArgProductId) {
                    type = NavType.IntType
                },
                navArgument(RetailRoutes.ArgStoreId) {
                    type = NavType.IntType
                },
                navArgument(RetailRoutes.ArgVariantId) {
                    type = NavType.IntType
                }
            )
    ) { backStackEntry ->
        val productId =
            backStackEntry.arguments
                ?.getInt(RetailRoutes.ArgProductId)
                ?: 0

        val storeId =
            backStackEntry.arguments
                ?.getInt(RetailRoutes.ArgStoreId)
                ?: 0

        val variantId =
            backStackEntry.arguments
                ?.getInt(RetailRoutes.ArgVariantId)
                ?: 0

        LaunchedEffect(
            productId,
            storeId,
            variantId
        ) {
            productController.OtherSellerList(
                languageId =
                    sessionState.Language.Id,
                productId =
                    productId,
                variantId =
                    variantId,
                storeId =
                    storeId
            )
        }

        val productState by productController.State.collectAsState()

        OtherSellerListScreen(
            productId =
                productId,
            sellers =
                productState.OtherStorePrices,
            isLoading =
                productState.IsLoading &&
                        productState.CurrentAction == "OtherSellerList",
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = RetailRoutes.ProductReview,
        arguments = listOf(
            navArgument(RetailRoutes.ArgProductId) { type = NavType.IntType },
            navArgument(RetailRoutes.ArgStoreId) { type = NavType.IntType },
            navArgument(RetailRoutes.ArgVariantId) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val productId = backStackEntry.arguments?.getInt(RetailRoutes.ArgProductId) ?: 0
        val storeId = backStackEntry.arguments?.getInt(RetailRoutes.ArgStoreId) ?: 0
        val variantId = backStackEntry.arguments?.getInt(RetailRoutes.ArgVariantId) ?: 0

        val productState by productController.State.collectAsState()
        val reviewState by productReviewController.State.collectAsState()

        LaunchedEffect(productId, storeId, variantId) {
            if (productId <= 0 || storeId <= 0) return@LaunchedEffect

            productReviewController.Clear()

            productReviewController.List(
                sourceType = "PRODUCT",
                sourceId = productId,
                variantId = variantId,
                page = 1,
                pageSize = 10
            )

            val currentProduct = productState.ProductDetailResult?.Data

            if (currentProduct == null || currentProduct.ProductId != productId) {
                productController.Detail(
                    languageId = 1,
                    storeId = storeId,
                    productId = productId,
                    variantId = variantId
                )
            }

            if (variantId > 0) {
                productController.VariantPictures(
                    variantId = variantId,
                    count = 10
                )
            }
        }

        val productPicture =
            productState.ProductVariantPicturesResult
                ?.Data
                .orEmpty()
                .sortedWith(
                    compareByDescending<com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO> { it.IsDefault }
                        .thenBy { it.Sorting }
                )
                .firstOrNull()
                ?.let { picture ->
                    if (picture.Picture.isNotBlank()) picture.Picture else picture.DirectoryName + picture.PictureName
                }
                .orEmpty()
                .ifBlank {
                    productState.ProductDetailResult?.Data?.DefaultPicture.orEmpty()
                        .ifBlank { productState.ProductDetailResult?.Data?.Picture.orEmpty() }
                }

        ProductReviewScreen(
            State = reviewState,
            product = productState.ProductDetailResult?.Data,
            productPicture = productPicture,
            onBackClick = { navigator.back() },
            onLoadMoreClick = { productReviewController.LoadMore() }
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

            if (variantId > 0) {
                productController.VariantPictures(
                    variantId = variantId,
                    count = 10
                )
            }

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

        val productPicture =
            productState.ProductVariantPicturesResult
                ?.Data
                .orEmpty()
                .sortedWith(
                    compareByDescending<com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO> {
                        it.IsDefault
                    }.thenBy {
                        it.Sorting
                    }
                )
                .firstOrNull()
                ?.let { picture ->
                    if (picture.Picture.isNotBlank()) {
                        picture.Picture
                    } else {
                        picture.DirectoryName + picture.PictureName
                    }
                }
                .orEmpty().ifBlank {
                    product
                        ?.DefaultPicture
                        .orEmpty()
                        .ifBlank {
                            product
                                ?.Picture
                                .orEmpty()
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
            productPicture =
                productPicture,
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
            onInsertQuestion = { question ->
                if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
                    navigator.navController.navigate(LogonRoutes.Logon)
                } else {
                    productQuestionController.Insert(
                        languageId = sessionState.Language.Id,
                        memberId = sessionState.MemberId,
                        storeId = storeId,
                        productId = productId,
                        //variantId = variantId,
                        productSecureKey = product?.ProductSecureKey.orEmpty(),
                        question = question,
                        onSuccess = {
                            productQuestionController.List(
                                productId = productId,
                                count = 100
                            )
                        }
                    )
                }
            }
        )
    }

    composable(
        route = StoreRoutes.StoreList
    ) {
        val storeState by storeController.State.collectAsState()

        LaunchedEffect(Unit) {
            storeController.LoadList()
        }

        StoreListScreen(
            stores = storeState.Stores,
            isLoading = storeState.IsLoading,
            errorMessage = storeState.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onStoreClick = { storeId ->
                navigator.navController.navigate(
                    StoreRoutes.storeDetail(storeId)
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

        val storeState by storeController.State.collectAsState()

        LaunchedEffect(storeId) {
            storeController.LoadDetail(storeId = storeId)
        }

        StoreDetailScreen(
            storeId = storeId,
            storeDetail = storeState.StoreDetail,
            isLoading = storeState.IsLoading,
            errorMessage = storeState.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onProductClick = { product ->
                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        productId = product.id,
                        storeId = storeId,
                        variantId = 0
                    )
                )
            },
            onStoreProductListClick = {
                navigator.navController.navigate(
                    StoreRoutes.storeProductList(storeId)
                )
            },
            onStoreListClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreList
                )
            }
        )
    }

    composable(
        route = StoreRoutes.StoreProductList,
        arguments = listOf(
            navArgument(StoreRoutes.ArgStoreId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val storeId = backStackEntry.arguments
            ?.getInt(StoreRoutes.ArgStoreId)
            ?: 0

        val productState by productController.State.collectAsState()

        StoreProductListScreen(
            State = productState,
            languageId = sessionState.Language.Id,
            storeId = storeId,
            OnLoadProducts = { filters, page, pageSize ->
                productController.StoreProductList(
                    storeId = storeId,
                    filters = filters,
                    page = page,
                    pageSize = pageSize
                )
            },
            onBackClick = {
                navigator.back()
            },
            onProductClick = { productId, productStoreId, variantId ->
                val resolvedStoreId = productStoreId.takeIf { it > 0 } ?: storeId

                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        productId = productId,
                        storeId = resolvedStoreId,
                        variantId = variantId
                    )
                )
            }
        )
    }

    composable(
        route = BasketRoutes.Basket
    ) {
        if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
            LaunchedEffect(Unit) {
                navigator.navController.navigate(LogonRoutes.Logon) {
                    popUpTo(BasketRoutes.Basket) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
            return@composable
        }

        val basketState by basketController.State.collectAsState()
        val accountState by accountController.State.collectAsState()

        LaunchedEffect(sessionState.MemberId) {
            basketController.Refresh(
                memberId = sessionState.MemberId
            )

            accountController.GetProductFavorites(
                memberId = sessionState.MemberId
            )
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
        route = RetailRoutes.DealsOfTheDayList
    ) {
        val dealsState by dealsOfTheDayController.State.collectAsState()

        LaunchedEffect(sessionState.Language.Id) {
            dealsOfTheDayController.Load(languageId = sessionState.Language.Id)
        }

        DealsOfTheDayListScreen(
            dealsOfTheDays = dealsState.DealsOfTheDays,
            isLoading = dealsState.IsLoading,
            errorMessage = dealsState.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onProductClick = { deal ->
                navigator.navController.navigate(
                    RetailRoutes.productDetail(deal.ProductId, deal.StoreId, deal.VariantId)
                )
            }
        )
    }

    composable(
        route = RetailRoutes.CampaignList
    ) {
        val campaignState by campaignController.State.collectAsState()

        LaunchedEffect(sessionState.Language.Id) {
            campaignController.LoadCampaigns(languageId = sessionState.Language.Id)
        }

        CampaignListScreen(
            campaigns = campaignState.Campaigns,
            isLoading = campaignState.IsLoading,
            errorMessage = campaignState.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onCampaignClick = { campaign ->
                navigator.navController.navigate(
                    RetailRoutes.campaignDetail(campaign.CampaignId)
                )
            }
        )
    }

    composable(
        route = RetailRoutes.CampaignDetail,
        arguments = listOf(
            navArgument("campaignId") {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val campaignId = backStackEntry.arguments?.getInt("campaignId") ?: 0
        val campaignState by campaignController.State.collectAsState()

        LaunchedEffect(sessionState.Language.Id, campaignId) {
            if (campaignId > 0) {
                campaignController.LoadCampaignDetail(languageId = sessionState.Language.Id, campaignId = campaignId)
            }
        }

        CampaignDetailScreen(
            campaign = campaignState.Campaign,
            isLoading = campaignState.IsLoading,
            errorMessage = campaignState.ErrorMessage,
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
            onProductClick = { product ->
                android.util.Log.d(
                    "CAMPAIGN_PRODUCT_CLICK",
                    "campaignProductId=${product.CampaignProductId} productId=${product.ProductId} storeId=${product.StoreId} variantId=${product.VariantId} priceId=${product.ProductVariantPriceId}"
                )

                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        product.ProductId,
                        product.StoreId,
                        product.VariantId
                    )
                )
            },
            onCategoryClick = { categoryId ->
                navigator.navController.navigate(
                    RetailRoutes.categoryLevel1(categoryId)
                )
            },
            onStoreClick = { storeId ->
                navigator.navController.navigate(
                    StoreRoutes.storeDetail(storeId)
                )
            }
        )
    }
}