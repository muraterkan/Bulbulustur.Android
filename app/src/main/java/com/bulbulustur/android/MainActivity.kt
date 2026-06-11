package com.bulbulustur.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bulbulustur.android.features.account.AccountRoutes
import com.bulbulustur.android.features.account.AccountScreen
import com.bulbulustur.android.features.account.AccountSecurityScreen
import com.bulbulustur.android.features.account.AddressFormScreen
import com.bulbulustur.android.features.account.AddressListScreen
import com.bulbulustur.android.features.account.CommunicationPreferenceScreen
import com.bulbulustur.android.features.account.CouponListScreen
import com.bulbulustur.android.features.account.RequestListScreen
import com.bulbulustur.android.features.account.ReviewListScreen
import com.bulbulustur.android.features.account.bank.BankAccountCreateScreen
import com.bulbulustur.android.features.account.bank.BankAccountEditScreen
import com.bulbulustur.android.features.account.bank.BankAccountListScreen
import com.bulbulustur.android.features.account.bank.BankAccountRoutes
import com.bulbulustur.android.features.account.company.CompanyB2BIndexScreen
import com.bulbulustur.android.features.account.company.CompanyB2BStatusScreen
import com.bulbulustur.android.features.account.company.CompanyInfoEditScreen
import com.bulbulustur.android.features.account.company.CompanyInfoScreen
import com.bulbulustur.android.features.account.favorite.FavoriteListScreen
import com.bulbulustur.android.features.account.favorite.FollowedStoreListScreen
import com.bulbulustur.android.features.account.notification.NotificationListScreen
import com.bulbulustur.android.features.account.phone.PhoneCreateScreen
import com.bulbulustur.android.features.account.phone.PhoneListScreen
import com.bulbulustur.android.features.account.phone.PhoneVerifyScreen
import com.bulbulustur.android.features.account.preference.UsagePurposeScreen
import com.bulbulustur.android.features.account.profile.ProfileScreen
import com.bulbulustur.android.features.account.question.QuestionAnswerScreen
import com.bulbulustur.android.features.account.security.ChangeEmailScreen
import com.bulbulustur.android.features.account.security.ChangePasswordScreen
import com.bulbulustur.android.features.account.security.LoginActivitiesScreen
import com.bulbulustur.android.features.account.settings.AboutThisAppScreen
import com.bulbulustur.android.features.account.settings.AccountSettingsScreen
import com.bulbulustur.android.features.account.settings.AppearanceSettingsScreen
import com.bulbulustur.android.features.account.settings.CurrencySettingsScreen
import com.bulbulustur.android.features.account.settings.LanguageSettingsScreen
import com.bulbulustur.android.features.account.settings.LegalPoliciesScreen
import com.bulbulustur.android.features.account.settings.RegionSettingsScreen
import com.bulbulustur.android.features.account.settings.SettingsRoutes
import com.bulbulustur.android.features.basket.BasketRoutes
import com.bulbulustur.android.features.basket.BasketScreen
import com.bulbulustur.android.features.message.MessageInboxScreen
import com.bulbulustur.android.features.message.MessageRoutes
import com.bulbulustur.android.features.order.OrderDetailScreen
import com.bulbulustur.android.features.order.OrderListScreen
import com.bulbulustur.android.features.retail.CampaignDetailScreen
import com.bulbulustur.android.features.retail.CampaignListScreen
import com.bulbulustur.android.features.retail.OtherSellerListScreen
import com.bulbulustur.android.features.retail.ProductDetailScreen as RetailProductDetailScreen
import com.bulbulustur.android.features.retail.ProductListScreen as RetailProductListScreen
import com.bulbulustur.android.features.retail.ProductQuestionScreen
import com.bulbulustur.android.features.retail.ProductReviewScreen
import com.bulbulustur.android.features.retail.RetailCategoryHomeScreen
import com.bulbulustur.android.features.retail.RetailHomeScreen
import com.bulbulustur.android.features.retail.RetailRoutes
import com.bulbulustur.android.features.retail.menu.RetailMenuScreen
import com.bulbulustur.android.features.splash.LandingSplashScreen
import com.bulbulustur.android.features.splash.ModeSelectionScreen
import com.bulbulustur.android.features.splash.SplashRoutes
import com.bulbulustur.android.features.store.StoreDetailScreen
import com.bulbulustur.android.features.store.StoreListScreen
import com.bulbulustur.android.features.store.StoreProductListScreen
import com.bulbulustur.android.features.store.StoreRoutes
import com.bulbulustur.android.features.wholesale.CategoryDetailScreen as WholesaleCategoryDetailScreen
import com.bulbulustur.android.features.wholesale.CustomizationRequestScreen
import com.bulbulustur.android.features.wholesale.LastPriceRequestScreen
import com.bulbulustur.android.features.wholesale.ProductDetailScreen as WholesaleProductDetailScreen
import com.bulbulustur.android.features.wholesale.ProductListScreen as WholesaleProductListScreen
import com.bulbulustur.android.features.wholesale.SampleRequestScreen
import com.bulbulustur.android.features.wholesale.SearchScreen as WholesaleSearchScreen
import com.bulbulustur.android.features.wholesale.WholesaleCategoryHomeScreen
import com.bulbulustur.android.features.wholesale.WholesaleHomeScreen
import com.bulbulustur.android.features.wholesale.WholesaleRoutes
import com.bulbulustur.android.features.wholesale.menu.WholesaleMenuScreen
import com.bulbulustur.android.features.wholesale.rfq.RfqCreateScreen
import com.bulbulustur.android.features.wholesale.rfq.RfqListScreen
import com.bulbulustur.android.ui.shell.BuyerMode
import com.bulbulustur.android.ui.shell.BuyerModeSheet
import com.bulbulustur.android.ui.theme.BbTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContent {
            BbTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    var showBuyerModeSheet by remember {
                        mutableStateOf(false)
                    }

                    val currentBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = currentBackStackEntry?.destination?.route

                    val currentBuyerMode = when {
                        currentRoute?.startsWith("wholesale/") == true -> BuyerMode.Wholesale
                        else -> BuyerMode.Retail
                    }

                    fun openBuyerModeSheet() {
                        showBuyerModeSheet = true
                    }

                    fun closeBuyerModeSheet() {
                        showBuyerModeSheet = false
                    }

                    fun navigateToInbox() {
                        navController.navigate(MessageRoutes.Inbox) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToRetailBasket() {
                        navController.navigate(BasketRoutes.Basket) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToFavorites() {
                        navController.navigate(AccountRoutes.Favorites) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToRetailCategories() {
                        navController.navigate(RetailRoutes.CategoryHome) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToWholesaleCategories() {
                        navController.navigate(WholesaleRoutes.CategoryHome) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToWholesaleOffers() {
                        navController.navigate(WholesaleRoutes.QuotationRequests) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToWholesaleRfqCreate() {
                        navController.navigate(WholesaleRoutes.RfqCreate) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToAccount() {
                        navController.navigate(AccountRoutes.AccountHome) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateBackToAccount() {
                        navController.navigate(AccountRoutes.AccountHome) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToRetailHome() {
                        closeBuyerModeSheet()

                        navController.navigate(RetailRoutes.Home) {
                            launchSingleTop = true
                            restoreState = true

                            popUpTo(SplashRoutes.ModeSelection) {
                                inclusive = false
                                saveState = true
                            }
                        }
                    }

                    fun navigateToWholesaleHome() {
                        closeBuyerModeSheet()

                        navController.navigate(WholesaleRoutes.Home) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateFromModeSelectionToRetail() {
                        navController.navigate(RetailRoutes.Home) {
                            popUpTo(SplashRoutes.ModeSelection) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }

                    fun navigateFromModeSelectionToWholesale() {
                        navController.navigate(WholesaleRoutes.Home) {
                            popUpTo(SplashRoutes.ModeSelection) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = SplashRoutes.Landing
                    ) {
                        composable(SplashRoutes.Landing) {
                            LandingSplashScreen(
                                onSplashFinished = {
                                    navController.navigate(SplashRoutes.ModeSelection) {
                                        popUpTo(SplashRoutes.Landing) {
                                            inclusive = true
                                        }

                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(SplashRoutes.ModeSelection) {
                            ModeSelectionScreen(
                                onRetailClick = {
                                    navigateFromModeSelectionToRetail()
                                },
                                onWholesaleClick = {
                                    navigateFromModeSelectionToWholesale()
                                }
                            )
                        }

                        composable(MessageRoutes.Inbox) {
                            MessageInboxScreen(
                                onBackClick = {
                                    val previousRoute = navController.previousBackStackEntry
                                        ?.destination
                                        ?.route
                                        .orEmpty()

                                    val didPop = navController.popBackStack()

                                    if (!didPop) {
                                        if (previousRoute.startsWith("wholesale/")) {
                                            navController.navigate(WholesaleRoutes.Home) {
                                                launchSingleTop = true
                                            }
                                        } else {
                                            navController.navigate(RetailRoutes.Home) {
                                                launchSingleTop = true
                                            }
                                        }
                                    }
                                },
                                onMessageClick = {
                                }
                            )
                        }

                        composable(BasketRoutes.Basket) {
                            BasketScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCheckoutClick = {
                                },
                                onProductClick = {
                                    navController.navigate(RetailRoutes.ProductDetail)
                                },
                                onStoreClick = {
                                    navController.navigate(StoreRoutes.StoreDetail)
                                },
                                onHomeClick = {
                                    navController.navigate(RetailRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onMenuClick = {
                                    navigateToRetailCategories()
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                }
                            )
                        }

                        composable(RetailRoutes.Home) {
                            RetailHomeScreen(
                                onSearchClick = {
                                    navController.navigate(RetailRoutes.Search)
                                },
                                onCategoryClick = {
                                    navigateToRetailCategories()
                                },
                                onProductListClick = {
                                    navController.navigate(RetailRoutes.ProductList)
                                },
                                onProductDetailClick = {
                                    navController.navigate(RetailRoutes.ProductDetail)
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onStoreClick = {
                                    navController.navigate(StoreRoutes.StoreList)
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onBasketClick = {
                                    navigateToRetailBasket()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                },
                                onMenuClick = {
                                    navigateToRetailCategories()
                                }
                            )
                        }

                        composable(RetailRoutes.Menu) {
                            RetailMenuScreen(
                                onHomeClick = {
                                    navController.navigate(RetailRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onSearchClick = {
                                    navController.navigate(RetailRoutes.Search)
                                },
                                onBasketClick = {
                                    navigateToRetailBasket()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                },
                                onCategoryClick = {
                                    navigateToRetailCategories()
                                },
                                onCampaignsClick = {
                                    navController.navigate(RetailRoutes.CampaignList)
                                },
                                onStoresClick = {
                                    navController.navigate(StoreRoutes.StoreList)
                                }
                            )
                        }

                        composable(RetailRoutes.CategoryHome) {
                            RetailCategoryHomeScreen(
                                onBackClick = {
                                    navigateToRetailHome()
                                },
                                onSearchClick = {
                                    navController.navigate(RetailRoutes.Search)
                                },
                                onMenuClick = {
                                    navigateToRetailHome()
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                },
                                onHomeClick = {
                                    navigateToRetailHome()
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onBasketClick = {
                                    navigateToRetailBasket()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                },
                                onProductListClick = {
                                    navController.navigate(RetailRoutes.ProductList)
                                },
                                onSubCategoryClick = {
                                    navController.navigate(RetailRoutes.CategoryHome) {
                                        launchSingleTop = true
                                    }
                                },
                                onStoreClick = {
                                    navController.navigate(StoreRoutes.StoreList)
                                }
                            )
                        }

                        composable(RetailRoutes.ProductList) {
                            RetailProductListScreen(
                                onSearchClick = {
                                    navController.navigate(RetailRoutes.Search)
                                },
                                onMenuClick = {
                                    navigateToRetailCategories()
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onProductDetailClick = {
                                    navController.navigate(RetailRoutes.ProductDetail)
                                },
                                onHomeClick = {
                                    navController.navigate(RetailRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onBasketClick = {
                                    navigateToRetailBasket()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                }
                            )
                        }

                        composable(RetailRoutes.Search) {
                            RetailProductListScreen(
                                onSearchClick = {
                                    navController.navigate(RetailRoutes.Search)
                                },
                                onMenuClick = {
                                    navigateToRetailCategories()
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onProductDetailClick = {
                                    navController.navigate(RetailRoutes.ProductDetail)
                                },
                                onHomeClick = {
                                    navController.navigate(RetailRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onBasketClick = {
                                    navigateToRetailBasket()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                }
                            )
                        }

                        composable(RetailRoutes.ProductDetail) {
                            RetailProductDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSearchClick = {
                                    navController.navigate(RetailRoutes.Search)
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                },
                                onAddToBasketClick = {
                                    navigateToRetailBasket()
                                },
                                onBuyNowClick = {
                                    navigateToRetailBasket()
                                },
                                onStockAlarmClick = {
                                },
                                onStoreClick = {
                                    navController.navigate(StoreRoutes.StoreDetail)
                                },
                                onOtherSellerClick = {
                                    navController.navigate(RetailRoutes.OtherSellerList)
                                },
                                onReviewClick = {
                                    navController.navigate(RetailRoutes.ProductReview)
                                },
                                onQuestionClick = {
                                    navController.navigate(RetailRoutes.ProductQuestion)
                                },
                                onSellerProductClick = {
                                    navController.navigate(RetailRoutes.ProductDetail) {
                                        launchSingleTop = true
                                    }
                                },
                                onRelatedCategoryClick = {
                                    navController.navigate(RetailRoutes.ProductList)
                                }
                            )
                        }

                        composable(RetailRoutes.ProductReview) {
                            ProductReviewScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(RetailRoutes.ProductQuestion) {
                            ProductQuestionScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(RetailRoutes.OtherSellerList) {
                            OtherSellerListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(RetailRoutes.CampaignList) {
                            CampaignListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCampaignClick = {
                                    navController.navigate(RetailRoutes.CampaignDetail)
                                }
                            )
                        }

                        composable(RetailRoutes.CampaignDetail) {
                            CampaignDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onProductClick = {
                                    navController.navigate(RetailRoutes.ProductDetail)
                                }
                            )
                        }

                        composable(StoreRoutes.StoreList) {
                            StoreListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onStoreClick = {
                                    navController.navigate(StoreRoutes.StoreDetail) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(StoreRoutes.StoreDetail) {
                            StoreDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onProductClick = {
                                    navController.navigate(RetailRoutes.ProductDetail) {
                                        launchSingleTop = true
                                    }
                                },
                                onStoreListClick = {
                                    navController.navigate(StoreRoutes.StoreList) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(StoreRoutes.StoreProductList) {
                            StoreProductListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onProductClick = {
                                    navController.navigate(RetailRoutes.ProductDetail) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(WholesaleRoutes.QuotationRequests) {
                            RfqListScreen(
                                onBackClick = {
                                    navController.navigate(WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onHomeClick = {
                                    navController.navigate(WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onMenuClick = {
                                    navigateToWholesaleCategories()
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onBasketClick = {
                                    navigateToWholesaleOffers()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                },
                                onCreateRfqClick = {
                                    navigateToWholesaleRfqCreate()
                                }
                            )
                        }

                        composable(WholesaleRoutes.RfqCreate) {
                            RfqCreateScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(WholesaleRoutes.Home) {
                            WholesaleHomeScreen(
                                onSearchClick = {
                                    navController.navigate(WholesaleRoutes.Search)
                                },
                                onCategoryClick = {
                                    navigateToWholesaleCategories()
                                },
                                onProductListClick = {
                                    navController.navigate(WholesaleRoutes.ProductList)
                                },
                                onProductDetailClick = {
                                    navController.navigate(WholesaleRoutes.ProductDetail)
                                },
                                onCompanyListClick = {
                                },
                                onRfqListClick = {
                                    navigateToWholesaleOffers()
                                },
                                onRfqCreateClick = {
                                    navigateToWholesaleRfqCreate()
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onLastPriceRequestClick = {
                                    navController.navigate(WholesaleRoutes.LastPriceRequest)
                                },
                                onSampleRequestClick = {
                                    navController.navigate(WholesaleRoutes.SampleRequest)
                                },
                                onCustomizationRequestClick = {
                                    navController.navigate(WholesaleRoutes.CustomizationRequest)
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onBasketClick = {
                                    navigateToWholesaleOffers()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                },
                                onMenuClick = {
                                    navigateToWholesaleCategories()
                                }
                            )
                        }

                        composable(WholesaleRoutes.Menu) {
                            WholesaleMenuScreen(
                                onHomeClick = {
                                    navController.navigate(WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onSearchClick = {
                                    navController.navigate(WholesaleRoutes.Search)
                                },
                                onBasketClick = {
                                    navigateToWholesaleOffers()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                },
                                onCategoryClick = {
                                    navigateToWholesaleCategories()
                                },
                                onCompanyListClick = {
                                    navigateToWholesaleCategories()
                                },
                                onRfqClick = {
                                    navigateToWholesaleRfqCreate()
                                }
                            )
                        }

                        composable(WholesaleRoutes.CategoryHome) {
                            WholesaleCategoryHomeScreen(
                                onBackClick = {
                                    navController.navigate(WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo(WholesaleRoutes.Home) {
                                            inclusive = false
                                        }
                                    }
                                },
                                onSearchClick = {
                                    navController.navigate(WholesaleRoutes.Search)
                                },
                                onMenuClick = {
                                    navController.navigate(WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo(WholesaleRoutes.Home) {
                                            inclusive = false
                                        }
                                    }
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                },
                                onHomeClick = {
                                    navController.navigate(WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo(WholesaleRoutes.Home) {
                                            inclusive = false
                                        }
                                    }
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onBasketClick = {
                                    navigateToWholesaleOffers()
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                },
                                onProductListClick = {
                                    navController.navigate(WholesaleRoutes.ProductList)
                                },
                                onSubCategoryClick = {
                                    navController.navigate(WholesaleRoutes.CategoryHome) {
                                        launchSingleTop = true
                                    }
                                },
                                onCompanyListClick = {
                                    navigateToWholesaleCategories()
                                },
                                onRfqClick = {
                                    navigateToWholesaleRfqCreate()
                                },
                                onLastPriceRequestClick = {
                                    navController.navigate(WholesaleRoutes.LastPriceRequest)
                                },
                                onSampleRequestClick = {
                                    navController.navigate(WholesaleRoutes.SampleRequest)
                                },
                                onCustomizationRequestClick = {
                                    navController.navigate(WholesaleRoutes.CustomizationRequest)
                                }
                            )
                        }

                        composable(WholesaleRoutes.CategoryDetail) {
                            WholesaleCategoryDetailScreen()
                        }

                        composable(WholesaleRoutes.ProductList) {
                            WholesaleProductListScreen()
                        }

                        composable(WholesaleRoutes.ProductDetail) {
                            WholesaleProductDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSearchClick = {
                                    navController.navigate(WholesaleRoutes.Search)
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onLastPriceRequestClick = {
                                    navController.navigate(WholesaleRoutes.LastPriceRequest)
                                },
                                onSampleRequestClick = {
                                    navController.navigate(WholesaleRoutes.SampleRequest)
                                },
                                onCustomizationRequestClick = {
                                    navController.navigate(WholesaleRoutes.CustomizationRequest)
                                },
                                onRelatedCategoryClick = {
                                    navController.navigate(WholesaleRoutes.ProductList)
                                }
                            )
                        }

                        composable(WholesaleRoutes.Search) {
                            WholesaleSearchScreen()
                        }

                        composable(WholesaleRoutes.LastPriceRequest) {
                            LastPriceRequestScreen()
                        }

                        composable(WholesaleRoutes.SampleRequest) {
                            SampleRequestScreen()
                        }

                        composable(WholesaleRoutes.CustomizationRequest) {
                            CustomizationRequestScreen()
                        }

                        composable(AccountRoutes.AccountHome) {
                            AccountScreen(
                                onSecurityClick = {
                                    navController.navigate(AccountRoutes.Security)
                                },
                                onAddressClick = {
                                    navController.navigate(AccountRoutes.AddressList)
                                },
                                onNotificationClick = {
                                    navController.navigate(AccountRoutes.Notifications)
                                },
                                onCompanyInfoClick = {
                                    navController.navigate(AccountRoutes.CompanyInfo)
                                },
                                onFollowedStoresClick = {
                                    navController.navigate(AccountRoutes.FollowedStores)
                                },
                                onQuotationRequestsClick = {
                                    navigateToWholesaleOffers()
                                },
                                onOrdersClick = {
                                    navController.navigate(AccountRoutes.Orders)
                                },
                                onFavoritesClick = {
                                    navigateToFavorites()
                                },
                                onReviewsClick = {
                                    navController.navigate(AccountRoutes.Reviews)
                                },
                                onCouponsClick = {
                                    navController.navigate(AccountRoutes.Coupons)
                                },
                                onRequestsClick = {
                                    navController.navigate(AccountRoutes.Requests)
                                },
                                onSubscriptionsClick = {
                                    navController.navigate(AccountRoutes.Subscriptions)
                                },
                                onBankAccountsClick = {
                                    navController.navigate(BankAccountRoutes.List)
                                },
                                onSettingsClick = {
                                    navController.navigate(SettingsRoutes.Home)
                                },
                                onMessagesClick = {
                                    navigateToInbox()
                                },
                                onSupportClick = {
                                },
                                onLogoutClick = {
                                },
                                onQuestionsClick = {
                                    navController.navigate(AccountRoutes.QuestionAnswers)
                                },
                                onUsagePurposeClick = {
                                    navController.navigate(AccountRoutes.UsagePurpose)
                                },
                                onHomeClick = {
                                    navController.navigate(RetailRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onMenuClick = {
                                    navigateToRetailCategories()
                                },
                                onModeSwitchClick = {
                                    openBuyerModeSheet()
                                },
                                onBasketClick = {
                                    navigateToRetailBasket()
                                }
                            )
                        }

                        composable(AccountRoutes.Security) {
                            AccountSecurityScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onProfileInfoClick = {
                                    navController.navigate(AccountRoutes.ProfileInfo)
                                },
                                onEmailChangeClick = {
                                    navController.navigate(AccountRoutes.EmailChange)
                                },
                                onPasswordChangeClick = {
                                    navController.navigate(AccountRoutes.PasswordChange)
                                },
                                onPhonesClick = {
                                    navController.navigate(AccountRoutes.PhoneList)
                                },
                                onLoginActivitiesClick = {
                                    navController.navigate(AccountRoutes.LoginActivities)
                                }
                            )
                        }

                        composable(AccountRoutes.EmailChange) {
                            ChangeEmailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.PasswordChange) {
                            ChangePasswordScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.LoginActivities) {
                            LoginActivitiesScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.ProfileInfo) {
                            ProfileScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onEditClick = {
                                    navController.navigate(AccountRoutes.ProfileEdit)
                                },
                                onPhonesClick = {
                                    navController.navigate(AccountRoutes.PhoneList)
                                },
                                onEmailClick = {
                                    navController.navigate(AccountRoutes.EmailChange)
                                },
                                onUsagePurposeClick = {
                                    navController.navigate(AccountRoutes.UsagePurpose)
                                },
                                onCompanyInfoClick = {
                                    navController.navigate(AccountRoutes.CompanyInfo)
                                },
                                onB2BStatusClick = {
                                    navController.navigate(AccountRoutes.CompanyB2BStatus)
                                }
                            )
                        }

                        composable(AccountRoutes.ProfileEdit) {
                            ProfileScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.PhoneList) {
                            PhoneListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCreatePhoneClick = {
                                    navController.navigate(AccountRoutes.PhoneCreate)
                                },
                                onVerifyPhoneClick = {
                                    navController.navigate(AccountRoutes.PhoneVerify)
                                }
                            )
                        }

                        composable(AccountRoutes.PhoneCreate) {
                            PhoneCreateScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.PhoneVerify) {
                            PhoneVerifyScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.AddressList) {
                            AddressListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCreateAddressClick = {
                                    navController.navigate(AccountRoutes.AddressCreate)
                                },
                                onEditAddressClick = { addressId ->
                                    navController.navigate(AccountRoutes.editAddress(addressId))
                                }
                            )
                        }

                        composable(AccountRoutes.AddressCreate) {
                            AddressFormScreen(
                                addressId = null,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = AccountRoutes.AddressEdit,
                            arguments = listOf(
                                navArgument("addressId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val addressId = backStackEntry.arguments?.getInt("addressId") ?: 0

                            AddressFormScreen(
                                addressId = addressId,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.Notifications) {
                            NotificationListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.CompanyInfo) {
                            CompanyInfoScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onEditClick = {
                                    navController.navigate(AccountRoutes.CompanyInfoEdit)
                                },
                                onB2BIndexClick = {
                                    navController.navigate(AccountRoutes.CompanyB2BIndex)
                                },
                                onB2CStoreClick = {
                                }
                            )
                        }

                        composable(AccountRoutes.CompanyInfoEdit) {
                            CompanyInfoEditScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.CompanyB2BIndex) {
                            CompanyB2BIndexScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onActivateClick = {
                                    navController.navigate(AccountRoutes.CompanyB2BStatus)
                                }
                            )
                        }

                        composable(AccountRoutes.CompanyB2BStatus) {
                            CompanyB2BStatusScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onB2BManagementPanelClick = {
                                }
                            )
                        }

                        composable(AccountRoutes.UsagePurpose) {
                            UsagePurposeScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onRetailClick = {
                                    navController.popBackStack()
                                },
                                onWholesaleClick = {
                                    navController.popBackStack()
                                },
                                onBothClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.QuestionAnswers) {
                            QuestionAnswerScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.FollowedStores) {
                            FollowedStoreListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.Orders) {
                            OrderListScreen(
                                onBackClick = {
                                    navigateBackToAccount()
                                }
                            )
                        }

                        composable(AccountRoutes.OrderDetail) {
                            OrderDetailScreen()
                        }

                        composable(AccountRoutes.Favorites) {
                            FavoriteListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.Reviews) {
                            ReviewListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.Coupons) {
                            CouponListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.Requests) {
                            RequestListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.Subscriptions) {
                            CommunicationPreferenceScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(BankAccountRoutes.List) {
                            BankAccountListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCreateBankAccountClick = {
                                    navController.navigate(BankAccountRoutes.Create)
                                },
                                onEditBankAccountClick = { bankAccountId ->
                                    navController.navigate(BankAccountRoutes.edit(bankAccountId))
                                },
                                onDeleteBankAccountClick = {
                                },
                                onCopyIbanClick = {
                                }
                            )
                        }

                        composable(BankAccountRoutes.Create) {
                            BankAccountCreateScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = BankAccountRoutes.Edit,
                            arguments = listOf(
                                navArgument("bankAccountId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val bankAccountId = backStackEntry.arguments?.getInt("bankAccountId") ?: 0

                            BankAccountEditScreen(
                                bankAccountId = bankAccountId,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(SettingsRoutes.Home) {
                            AccountSettingsScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onAccountSecurityClick = {
                                    navController.navigate(AccountRoutes.Security)
                                },
                                onPrivacyClick = {
                                    navController.navigate(SettingsRoutes.LegalPolicies)
                                },
                                onPermissionsClick = {
                                    navController.navigate(SettingsRoutes.Communication)
                                },
                                onHelpCenterClick = {
                                },
                                onLanguageClick = {
                                    navController.navigate(SettingsRoutes.Language)
                                },
                                onAppearanceClick = {
                                    navController.navigate(SettingsRoutes.Appearance)
                                },
                                onRegionClick = {
                                    navController.navigate(SettingsRoutes.Region)
                                },
                                onCurrencyClick = {
                                    navController.navigate(SettingsRoutes.Currency)
                                },
                                onAboutThisAppClick = {
                                    navController.navigate(SettingsRoutes.AboutThisApp)
                                },
                                onLegalPoliciesClick = {
                                    navController.navigate(SettingsRoutes.LegalPolicies)
                                },
                                onShareThisAppClick = {
                                },
                                onSwitchAccountClick = {
                                },
                                onSignOutClick = {
                                }
                            )
                        }

                        composable(SettingsRoutes.Language) {
                            LanguageSettingsScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(SettingsRoutes.Appearance) {
                            AppearanceSettingsScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(SettingsRoutes.Communication) {
                            CommunicationPreferenceScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(SettingsRoutes.Currency) {
                            CurrencySettingsScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(SettingsRoutes.Region) {
                            RegionSettingsScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(SettingsRoutes.LegalPolicies) {
                            LegalPoliciesScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(SettingsRoutes.AboutThisApp) {
                            AboutThisAppScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onLegalPoliciesClick = {
                                    navController.navigate(SettingsRoutes.LegalPolicies)
                                }
                            )
                        }
                    }

                    if (showBuyerModeSheet) {
                        BuyerModeSheet(
                            currentMode = currentBuyerMode,
                            onDismissRequest = {
                                closeBuyerModeSheet()
                            },
                            onRetailClick = {
                                navigateToRetailHome()
                            },
                            onWholesaleClick = {
                                navigateToWholesaleHome()
                            }
                        )
                    }
                }
            }
        }
    }
}