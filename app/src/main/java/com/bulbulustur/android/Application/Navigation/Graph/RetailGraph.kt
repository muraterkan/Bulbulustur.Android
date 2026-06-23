package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.BasketRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Navigation.Routes.StoreRoutes

fun NavGraphBuilder.retailGraph(
    navigator: BulbulusturNavigator
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
                navigator.navController.navigate(
                    RetailRoutes.ProductDetail
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
                navigator.navController.navigate(
                    RetailRoutes.ProductDetail
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
        RetailProductListScreen(
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
                navigator.navController.navigate(
                    RetailRoutes.ProductDetail
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
        route = RetailRoutes.ProductDetail
    ) {
        ProductDetailScreen(
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
                navigator.navController.navigate(
                    RetailRoutes.ProductDetail
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
                 * BasketRoutes.Checkout graph hedefi bağlandığında
                 * checkout navigation burada açılacak.
                 *
                 * Hedef route henüz graph içinde kayıtlı olmadığı için
                 * şimdilik boş bırakılmıştır. Aksi durumda aynı navigation
                 * destination hatasıyla uygulama kapanır.
                 */
            },
            onProductClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductDetail
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
                navigator.navController.navigate(
                    RetailRoutes.ProductDetail
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