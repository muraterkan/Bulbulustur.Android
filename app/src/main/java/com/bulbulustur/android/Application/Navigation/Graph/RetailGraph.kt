package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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

fun NavGraphBuilder.retailGraph(
    navigator: BulbulusturNavigator,
    productController: ProductController
) {
    composable(
        route = RetailRoutes.Home
    ) {
        RetailHomeScreen(
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
                navigator.navigateToInbox()
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
                navigator.navigateToInbox()
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

        LaunchedEffect(
            productId,
            storeId,
            variantId
        ) {
            if (
                productId > 0 &&
                storeId > 0
            ) {
                productController.Detail(
                    languageId = 1,
                    storeId = storeId,
                    productId = productId,
                    variantId = variantId
                )
            }

            if (
                variantId > 0
            ) {
                productController.VariantPictures(
                    variantId = variantId,
                    count = 10
                )
            }
        }

        ProductDetailScreen(
            State = productState,
            productId = productId,
            onBackClick = {
                navigator.back()
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreDetail
                )
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
        route = StoreRoutes.StoreDetail
    ) {
        StoreDetailScreen(
            onBackClick = {
                navigator.back()
            },
            onProductClick = {
                /*
                 * StoreDetailScreen henüz detail kimliklerini taşımıyor.
                 */
                navigator.navController.navigate(
                    RetailRoutes.ProductList
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
        route = BasketRoutes.Basket
    ) {
        BasketScreen(
            onBackClick = {
                navigator.back()
            },
            onCheckoutClick = {
                /*
                 * BasketRoutes.Checkout hedefi graph içine bağlandığında
                 * checkout navigation burada açılacak.
                 */
            },
            onProductClick = {
                /*
                 * BasketScreen henüz detail kimliklerini taşımıyor.
                 */
                navigator.navController.navigate(
                    RetailRoutes.ProductList
                )
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreDetail
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
                navigator.navigateToInbox()
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