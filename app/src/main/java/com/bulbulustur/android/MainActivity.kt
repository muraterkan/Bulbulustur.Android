package com.bulbulustur.android

import com.bulbulustur.android.Features.account.AccountRoutes
import com.bulbulustur.android.Features.company.CompanyRoutes
import com.bulbulustur.android.Features.account.bank.BankAccountRoutes
import com.bulbulustur.android.Features.account.settings.SettingsRoutes
import com.bulbulustur.android.Features.message.MessageRoutes
import com.bulbulustur.android.Features.splash.SplashRoutes

import com.bulbulustur.android.Features.logon.logonGraph

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

import com.bulbulustur.android.Features.areas.b2c.order.OrderListScreen
import com.bulbulustur.android.Features.areas.b2c.order.OrderDetailScreen
import com.bulbulustur.android.Features.areas.b2c.order.OrderContractScreen
import com.bulbulustur.android.Features.areas.b2c.order.OrderCancelRequestScreen
import com.bulbulustur.android.Features.areas.b2c.order.OrderReturnRequestScreen
import com.bulbulustur.android.Features.areas.b2c.order.OrderReviewCreateScreen
import com.bulbulustur.android.Features.areas.b2c.order.OrderShipmentTrackingScreen
import com.bulbulustur.android.Features.account.wallet.WalletBalanceScreen
import com.bulbulustur.android.Features.account.subscription.SubscriptionListScreen
import com.bulbulustur.android.Features.account.subscription.SubscriptionDetailScreen
import com.bulbulustur.android.Features.account.AccountScreen
import com.bulbulustur.android.Features.account.AccountSecurityScreen
import com.bulbulustur.android.Features.account.AddressFormScreen
import com.bulbulustur.android.Features.account.AddressListScreen
import com.bulbulustur.android.Features.account.CommunicationPreferenceScreen
import com.bulbulustur.android.Features.company.CompanyDetailScreen
import com.bulbulustur.android.Features.company.CompanyListScreen
import com.bulbulustur.android.Features.account.CouponListScreen
import com.bulbulustur.android.Features.account.RequestListScreen
import com.bulbulustur.android.Features.account.RequestDetailScreen
import com.bulbulustur.android.Features.account.ReviewListScreen
import com.bulbulustur.android.Features.account.review.ReviewEditScreen
import com.bulbulustur.android.Features.account.bank.BankAccountCreateScreen
import com.bulbulustur.android.Features.account.bank.BankAccountEditScreen
import com.bulbulustur.android.Features.account.bank.BankAccountListScreen
import com.bulbulustur.android.Features.account.company.CompanyB2BIndexScreen
import com.bulbulustur.android.Features.account.company.CompanyB2BStatusScreen
import com.bulbulustur.android.Features.account.company.CompanyInfoEditScreen
import com.bulbulustur.android.Features.account.company.CompanyInfoScreen
import com.bulbulustur.android.Features.account.favorite.FavoriteListScreen
import com.bulbulustur.android.Features.account.favorite.FollowedStoreListScreen
import com.bulbulustur.android.Features.account.notification.NotificationListScreen
import com.bulbulustur.android.Features.account.phone.PhoneCreateScreen
import com.bulbulustur.android.Features.account.phone.PhoneListScreen
import com.bulbulustur.android.Features.account.phone.PhoneVerifyScreen
import com.bulbulustur.android.Features.account.preference.UsagePurposeScreen
import com.bulbulustur.android.Features.account.profile.ProfileScreen
import com.bulbulustur.android.Features.account.question.QuestionAnswerScreen
import com.bulbulustur.android.Features.account.security.ChangeEmailScreen
import com.bulbulustur.android.Features.account.security.ChangePasswordScreen
import com.bulbulustur.android.Features.account.security.LoginActivitiesScreen
import com.bulbulustur.android.Features.account.settings.AboutThisAppScreen
import com.bulbulustur.android.Features.account.settings.AccountSettingsScreen
import com.bulbulustur.android.Features.account.settings.AppearanceSettingsScreen
import com.bulbulustur.android.Features.account.settings.CurrencySettingsScreen
import com.bulbulustur.android.Features.account.settings.LanguageSettingsScreen
import com.bulbulustur.android.Features.account.settings.LegalPoliciesScreen
import com.bulbulustur.android.Features.account.settings.LegalPolicyDetailScreen
import com.bulbulustur.android.Features.account.settings.RegionSettingsScreen
import com.bulbulustur.android.Features.areas.b2c.basket.BasketScreen
import com.bulbulustur.android.Features.message.MessageInboxScreen
import com.bulbulustur.android.Features.message.MessageDetailScreen
import com.bulbulustur.android.Features.areas.b2c.CampaignDetailScreen
import com.bulbulustur.android.Features.areas.b2c.CampaignListScreen
import com.bulbulustur.android.Features.areas.b2c.OtherSellerListScreen
import com.bulbulustur.android.Features.areas.b2c.ProductDetailScreen as RetailProductDetailScreen
import com.bulbulustur.android.Features.areas.b2c.ProductListScreen as RetailProductListScreen
import com.bulbulustur.android.Features.areas.b2c.ProductQuestionScreen
import com.bulbulustur.android.Features.areas.b2c.ProductReviewScreen
import com.bulbulustur.android.Features.areas.b2c.RetailCategoryHomeScreen
import com.bulbulustur.android.Features.areas.b2c.RetailHomeScreen
import com.bulbulustur.android.Features.areas.b2c.menu.RetailMenuScreen
import com.bulbulustur.android.Features.splash.ModeSelectionScreen
import com.bulbulustur.android.Features.areas.b2c.store.StoreDetailScreen
import com.bulbulustur.android.Features.areas.b2c.store.StoreListScreen
import com.bulbulustur.android.Features.areas.b2c.store.StoreProductListScreen
import com.bulbulustur.android.Features.company.CompanyHomeScreen
import com.bulbulustur.android.Features.company.CompanyProductsScreen
import com.bulbulustur.android.Features.company.CompanyContactScreen
import com.bulbulustur.android.Features.areas.b2b.CategoryDetailScreen as WholesaleCategoryDetailScreen
import com.bulbulustur.android.Features.areas.b2b.CustomizationRequestScreen
import com.bulbulustur.android.Features.areas.b2b.LastPriceRequestScreen
import com.bulbulustur.android.Features.areas.b2b.ProductDetailScreen as WholesaleProductDetailScreen
import com.bulbulustur.android.Features.areas.b2b.ProductListScreen as WholesaleProductListScreen
import com.bulbulustur.android.Features.areas.b2b.SampleRequestScreen
import com.bulbulustur.android.Features.areas.b2b.SearchScreen as WholesaleSearchScreen
import com.bulbulustur.android.Features.areas.b2b.WholesaleCategoryHomeScreen
import com.bulbulustur.android.Features.areas.b2b.WholesaleHomeScreen
import com.bulbulustur.android.Features.areas.b2b.menu.WholesaleMenuScreen
import com.bulbulustur.android.Features.areas.b2b.rfq.RfqDetailScreen
import com.bulbulustur.android.Features.areas.b2b.rfq.RfqOfferDetailScreen
import com.bulbulustur.android.Features.areas.b2b.rfq.RfqCreateScreen
import com.bulbulustur.android.Features.areas.b2b.rfq.RfqListScreen
import com.bulbulustur.android.Ui.shell.BuyerMode
import com.bulbulustur.android.Ui.shell.BuyerModeSheet
import com.bulbulustur.android.Ui.theme.BbTheme

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
                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.basket.BasketRoutes.Basket) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToFavorites() {
                        navController.navigate(AccountRoutes.Favorites) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToRetailCategories() {
                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.CategoryHome) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToWholesaleCategories() {
                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.CategoryHome) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToWholesaleOffers() {
                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.QuotationRequests) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateToWholesaleRfqCreate() {
                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.RfqCreate) {
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

                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
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

                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                            launchSingleTop = true
                        }
                    }

                    fun navigateFromModeSelectionToRetail() {
                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
                            popUpTo(SplashRoutes.ModeSelection) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }

                    fun navigateFromModeSelectionToWholesale() {
                        navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                            popUpTo(SplashRoutes.ModeSelection) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = SplashRoutes.ModeSelection
                    ) {

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
                        logonGraph(
                            navController = navController
                        )
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
                                            navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                                                launchSingleTop = true
                                            }
                                        } else {
                                            navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
                                                launchSingleTop = true
                                            }
                                        }
                                    }
                                },
                                onMessageClick = { messageId ->
                                    navController.navigate(MessageRoutes.detail(messageId))
                                }
                            )
                        }

                        composable(
                            route = MessageRoutes.Detail,
                            arguments = listOf(
                                navArgument(MessageRoutes.ArgMessageId) {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val messageId = backStackEntry.arguments
                                ?.getInt(MessageRoutes.ArgMessageId)
                                ?: 1

                            MessageDetailScreen(
                                messageId = messageId,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSendClick = {
                                    // V1 dummy
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.basket.BasketRoutes.Basket) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.basket.BasketScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCheckoutClick = {
                                },
                                onProductClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail)
                                },
                                onStoreClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreDetail)
                                },
                                onHomeClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
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

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailHomeScreen(
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Search)
                                },
                                onCategoryClick = {
                                    navigateToRetailCategories()
                                },
                                onProductListClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductList)
                                },
                                onProductDetailClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail)
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onStoreClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreList)
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

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Menu) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.menu.RetailMenuScreen(
                                onHomeClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Search)
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.CampaignList)
                                },
                                onStoresClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreList)
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.CategoryHome) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailCategoryHomeScreen(
                                onBackClick = {
                                    navigateToRetailHome()
                                },
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Search)
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductList)
                                },
                                onSubCategoryClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.CategoryHome) {
                                        launchSingleTop = true
                                    }
                                },
                                onStoreClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreList)
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductList) {
                            RetailProductListScreen(
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Search)
                                },
                                onMenuClick = {
                                    navigateToRetailCategories()
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onProductDetailClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail)
                                },
                                onHomeClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
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

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Search) {
                            RetailProductListScreen(
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Search)
                                },
                                onMenuClick = {
                                    navigateToRetailCategories()
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onProductDetailClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail)
                                },
                                onHomeClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
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

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail) {
                            RetailProductDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Search)
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreDetail)
                                },
                                onOtherSellerClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.OtherSellerList)
                                },
                                onReviewClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductReview)
                                },
                                onQuestionClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductQuestion)
                                },
                                onSellerProductClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail) {
                                        launchSingleTop = true
                                    }
                                },
                                onRelatedCategoryClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductList)
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductReview) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.ProductReviewScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductQuestion) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.ProductQuestionScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.OtherSellerList) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.OtherSellerListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.CampaignList) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.CampaignListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCampaignClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.CampaignDetail)
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.CampaignDetail) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.CampaignDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onProductClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail)
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreList) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onStoreClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreDetail) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreDetail) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onProductClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail) {
                                        launchSingleTop = true
                                    }
                                },
                                onStoreListClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreList) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreProductList) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreProductListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onProductClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.QuotationRequests) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqListScreen(
                                onBackClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onDiscoverWholesaleClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onOffersClick = { buyerRequestId ->
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.detail(
                                            buyerRequestId
                                        )
                                    )
                                },
                                onDetailClick = { buyerRequestId ->
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.detail(
                                            buyerRequestId
                                        )
                                    )
                                },
                                onDeleteClick = {
                                    // V1: silme dialogu sonra eklenecek
                                },
                                onCreateRfqClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.Create)
                                },
                                onHomeClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.QuotationRequests) {
                                        launchSingleTop = true
                                    }
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.RfqCreate) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqCreateScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.Create) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqCreateScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSendClick = {
                                    navController.popBackStack()
                                },
                                onHomeClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.QuotationRequests) {
                                        launchSingleTop = true
                                    }
                                },
                                onAccountClick = {
                                    navigateToAccount()
                                }
                            )
                        }

                        composable(
                            route = _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.Detail,
                            arguments = listOf(
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.ArgBuyerRequestId) {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val buyerRequestId = backStackEntry.arguments
                                ?.getInt(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.ArgBuyerRequestId)
                                ?: 0

                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqDetailScreen(
                                buyerRequestId = buyerRequestId,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onOfferClick = { sendedOfferId ->
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.offerDetail(
                                            buyerRequestId = buyerRequestId,
                                            sendedOfferId = sendedOfferId
                                        )
                                    )
                                },
                                onCreateRfqClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.Create)
                                }
                            )
                        }

                        composable(
                            route = _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.OfferDetail,
                            arguments = listOf(
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.ArgBuyerRequestId) {
                                    type = NavType.IntType
                                },
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.ArgSendedOfferId) {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val buyerRequestId = backStackEntry.arguments
                                ?.getInt(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.ArgBuyerRequestId)
                                ?: 0

                            val sendedOfferId = backStackEntry.arguments
                                ?.getInt(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqRoutes.ArgSendedOfferId)
                                ?: 0

                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.rfq.RfqOfferDetailScreen(
                                buyerRequestId = buyerRequestId,
                                sendedOfferId = sendedOfferId,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSellerClick = {
                                    navController.navigate(CompanyRoutes.CompanyDetail)
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleHomeScreen(
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Search)
                                },
                                onCategoryClick = {
                                    navigateToWholesaleCategories()
                                },
                                onProductListClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductList)
                                },
                                onProductDetailClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductDetail)
                                },
                                onCompanyListClick = {
                                    navController.navigate(CompanyRoutes.CompanyList)
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.LastPriceRequest)
                                },
                                onSampleRequestClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.SampleRequest)
                                },
                                onCustomizationRequestClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.CustomizationRequest)
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

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Menu) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.menu.WholesaleMenuScreen(
                                onHomeClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                    }
                                },
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Search)
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
                                    navController.navigate(CompanyRoutes.CompanyList)
                                },
                                onRfqClick = {
                                    navigateToWholesaleRfqCreate()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.CategoryHome) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleCategoryHomeScreen(
                                onBackClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                                            inclusive = false
                                        }
                                    }
                                },
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Search)
                                },
                                onMenuClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Home) {
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductList)
                                },
                                onSubCategoryClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.CategoryHome) {
                                        launchSingleTop = true
                                    }
                                },
                                onCompanyListClick = {
                                    navController.navigate(CompanyRoutes.CompanyList)
                                },
                                onRfqClick = {
                                    navigateToWholesaleRfqCreate()
                                },
                                onLastPriceRequestClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.LastPriceRequest)
                                },
                                onSampleRequestClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.SampleRequest)
                                },
                                onCustomizationRequestClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.CustomizationRequest)
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.CategoryDetail) {
                            WholesaleCategoryDetailScreen()
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductList) {
                            WholesaleProductListScreen()
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductDetail) {
                            WholesaleProductDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSearchClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Search)
                                },
                                onFavoriteClick = {
                                    navigateToFavorites()
                                },
                                onMessageClick = {
                                    navigateToInbox()
                                },
                                onCompanyClick = {
                                    navController.navigate(CompanyRoutes.CompanyDetail)
                                },
                                onCompanyProductsClick = {
                                    navController.navigate(CompanyRoutes.CompanyProducts)
                                },
                                onCompanySimilarProductClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductDetail)
                                },
                                onCompanyBestSellerProductClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductDetail)
                                },
                                onCompanySimilarProductsClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductList)
                                },
                                onCompanyBestSellerProductsClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductList)
                                },
                                onLastPriceRequestClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.LastPriceRequest)
                                },
                                onSampleRequestClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.SampleRequest)
                                },
                                onCustomizationRequestClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.CustomizationRequest)
                                },
                                onRelatedCategoryClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductList)
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.Search) {
                            WholesaleSearchScreen()
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.LastPriceRequest) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.LastPriceRequestScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.SampleRequest) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.SampleRequestScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.CustomizationRequest) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.CustomizationRequestScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }






                        composable(CompanyRoutes.CompanyHome) {
                            CompanyHomeScreen(
                                onBackClick = { navController.popBackStack() },
                                onProfileClick = { navController.navigate(CompanyRoutes.CompanyDetail) },
                                onProductsClick = { navController.navigate(CompanyRoutes.CompanyProducts) },
                                onContactClick = { navController.navigate(CompanyRoutes.CompanyContact) }
                            )
                        }

                        composable(CompanyRoutes.CompanyProducts) {
                            CompanyProductsScreen(
                                onBackClick = { navController.popBackStack() },
                                onCompanyProfileClick = { navController.navigate(CompanyRoutes.CompanyDetail) },
                                onCompanyContactClick = { navController.navigate(CompanyRoutes.CompanyContact) },
                                onProductClick = { navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleRoutes.ProductDetail) }
                            )
                        }

                        composable(CompanyRoutes.CompanyContact) {
                            CompanyContactScreen(
                                onBackClick = { navController.popBackStack() },
                                onCompanyProfileClick = { navController.navigate(CompanyRoutes.CompanyDetail) },
                                onCompanyProductsClick = { navController.navigate(CompanyRoutes.CompanyProducts) }
                            )
                        }

                        composable(CompanyRoutes.CompanyList) {
                            CompanyListScreen(
                                onCompanyClick = {
                                    navController.navigate(CompanyRoutes.CompanyDetail)
                                }
                            )
                        }

                        composable(CompanyRoutes.CompanyDetail) {
                            CompanyDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onHomeClick = {
                                    navController.navigate(CompanyRoutes.CompanyHome)
                                },
                                onProductListClick = {
                                    navController.navigate(CompanyRoutes.CompanyProducts)
                                },
                                onMessageClick = {
                                    navController.navigate(CompanyRoutes.CompanyContact)
                                },
                                onContactClick = {
                                    navController.navigate(CompanyRoutes.CompanyContact)
                                }
                            )
                        }


                        composable(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.List) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onOrderDetailClick = { orderId ->
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.detail(
                                            orderId
                                        )
                                    )
                                }
                            )
                        }

                        composable(
                            route = _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.Detail,
                            arguments = listOf(
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderId) {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val orderId = backStackEntry.arguments?.getInt(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderId) ?: 0

                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderDetailScreen(
                                orderId = orderId,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onContractClick = {
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.contract(
                                            orderKey = "ORD-F4QO-AFPR-J5EX",
                                            storeKey = "STORE-ORTOBELLA"
                                        )
                                    )
                                },
                                onStoreClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreDetail)
                                },
                                onSupportClick = {
                                    // V1: support bottom sheet OrderDetail içinde açılıyor.
                                },
                                onCancelRequestClick = { orderStoreLineId, orderKey ->
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.cancelRequest(
                                            orderStoreLineId = orderStoreLineId,
                                            orderKey = orderKey
                                        )
                                    )
                                },
                                onReturnRequestClick = { orderStoreLineId, orderKey ->
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.returnRequest(
                                            orderStoreLineId = orderStoreLineId,
                                            orderKey = orderKey
                                        )
                                    )
                                },
                                onReviewCreateClick = { orderStoreLineId, productId, memberKey ->
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.reviewCreate(
                                            orderStoreLineId = orderStoreLineId,
                                            productId = productId,
                                            memberKey = memberKey
                                        )
                                    )
                                },
                                onShipmentTrackingClick = { orderStoreLineId ->
                                    navController.navigate(
                                        _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.shipmentTracking(
                                            orderStoreLineId = orderStoreLineId
                                        )
                                    )
                                }
                            )


                        }

                        composable(
                            route = _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.Contract,
                            arguments = listOf(
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderKey) {
                                    type = NavType.StringType
                                },
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgStoreKey) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val orderKey = backStackEntry.arguments?.getString(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderKey).orEmpty()

                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderContractScreen(
                                orderCode = orderKey.ifBlank { "ORD-F4QO-AFPR-J5EX" },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.CancelRequest,
                            arguments = listOf(
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderStoreLineId) {
                                    type = NavType.LongType
                                },
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderKey) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderCancelRequestScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSubmitClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ReturnRequest,
                            arguments = listOf(
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderStoreLineId) {
                                    type = NavType.LongType
                                },
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderKey) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderReturnRequestScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSubmitClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ReviewCreate,
                            arguments = listOf(
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderStoreLineId) {
                                    type = NavType.LongType
                                },
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgProductId) {
                                    type = NavType.LongType
                                },
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgMemberKey) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderReviewCreateScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSubmitClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ShipmentTracking,
                            arguments = listOf(
                                navArgument(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.ArgOrderStoreLineId) {
                                    type = NavType.LongType
                                }
                            )
                        ) {
                            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderShipmentTrackingScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.AccountHome) {
                            AccountScreen(
                                onSecurityClick = {
                                    navController.navigate(AccountRoutes.Security)
                                },
                                onProfileClick = {
                                    navController.navigate(AccountRoutes.ProfileInfo)
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.List)
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
                                onWalletBalanceClick = {
                                    navController.navigate(AccountRoutes.WalletBalance)
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
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.Home) {
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

                        composable(AccountRoutes.Favorites) {
                            FavoriteListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.Reviews) {
                            ReviewListScreen(
                                onBackClick = { navController.popBackStack() },
                                onProductClick = { navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailRoutes.ProductDetail) },
                                onEditReviewClick = { navController.navigate(AccountRoutes.ReviewEdit) },
                                onDeleteReviewClick = {
                                    // V1: silme dialogu sonra eklenir

                                }
                            )
                        }

                        composable(AccountRoutes.ReviewEdit) {
                            ReviewEditScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSaveClick = {
                                    navController.popBackStack()
                                },
                                onDeleteClick = {
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
                                },
                                onRequestDetailClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.detail(1))
                                },
                                onOrderListClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.order.OrderRoutes.List)
                                }
                            )
                        }

                        composable(AccountRoutes.RequestDetail) {
                            RequestDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onOrderClick = {
                                    navController.navigate(AccountRoutes.OrderDetail)
                                },
                                onStoreClick = {
                                    navController.navigate(_root_ide_package_.com.bulbulustur.android.Features.areas.b2c.store.StoreRoutes.StoreDetail)
                                }
                            )
                        }

                        composable(AccountRoutes.Subscriptions) {
                            SubscriptionListScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSubscriptionDetailClick = {
                                    navController.navigate(AccountRoutes.SubscriptionDetail)
                                }
                            )
                        }

                        composable(AccountRoutes.SubscriptionDetail) {
                            SubscriptionDetailScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(AccountRoutes.WalletBalance) {
                            WalletBalanceScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onBankAccountsClick = {
                                    navController.navigate(BankAccountRoutes.List)
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
                                },
                                onPolicyClick = { item ->
                                    navController.navigate(SettingsRoutes.legalPolicyDetail(item.key))
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

                        composable(
                            route = SettingsRoutes.LegalPolicyDetail,
                            arguments = listOf(
                                navArgument("policyKey") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val policyKey = backStackEntry.arguments?.getString("policyKey").orEmpty()

                            LegalPolicyDetailScreen(
                                policyKey = policyKey,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onOpenWebClick = {
                                    // V1: burada browser intent / webview sonra bağlanacak
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
                            },
                            onRfqClick = {
                                closeBuyerModeSheet()
                                navigateToWholesaleRfqCreate()
                            }
                        )
                    }
                }
            }
        }
    }
}