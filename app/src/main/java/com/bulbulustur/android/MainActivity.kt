package com.bulbulustur.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bulbulustur.android.features.account.AccountScreen
import com.bulbulustur.android.features.account.AccountSecurityScreen
import com.bulbulustur.android.features.account.AddressFormScreen
import com.bulbulustur.android.features.account.AddressListScreen
import com.bulbulustur.android.features.account.BankAccountListScreen
import com.bulbulustur.android.features.account.CommunicationPreferenceScreen
import com.bulbulustur.android.features.account.CompanyInfoScreen
import com.bulbulustur.android.features.account.CouponListScreen
import com.bulbulustur.android.features.account.FavoriteListScreen
import com.bulbulustur.android.features.account.FollowedStoreListScreen
import com.bulbulustur.android.features.account.OrderDetailScreen
import com.bulbulustur.android.features.account.OrderListScreen
import com.bulbulustur.android.features.account.QuotationRequestListScreen
import com.bulbulustur.android.features.account.RequestListScreen
import com.bulbulustur.android.features.account.ReviewListScreen
import com.bulbulustur.android.ui.theme.BbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BbTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = AccountRoutes.AccountHome
                    ) {
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
                                    navController.navigate(AccountRoutes.QuotationRequests)
                                },
                                onOrdersClick = {
                                    navController.navigate(AccountRoutes.Orders)
                                },
                                onFavoritesClick = {
                                    navController.navigate(AccountRoutes.Favorites)
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
                                    navController.navigate(AccountRoutes.BankAccounts)
                                },
                                onCommunicationPreferencesClick = {
                                    navController.navigate(AccountRoutes.CommunicationPreferences)
                                },
                                onSupportClick = {
                                },
                                onLogoutClick = {
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
                                onLoginActivitiesClick = {
                                    navController.navigate(AccountRoutes.LoginActivities)
                                },
                                onCommunicationPreferencesClick = {
                                    navController.navigate(AccountRoutes.CommunicationPreferences)
                                }
                            )
                        }

                        composable(AccountRoutes.AddressList) {
                            AddressListScreen()
                        }

                        composable(AccountRoutes.AddressForm) {
                            AddressFormScreen()
                        }

                        composable(AccountRoutes.Notifications) {
                            CommunicationPreferenceScreen()
                        }

                        composable(AccountRoutes.CompanyInfo) {
                            CompanyInfoScreen()
                        }

                        composable(AccountRoutes.FollowedStores) {
                            FollowedStoreListScreen()
                        }

                        composable(AccountRoutes.QuotationRequests) {
                            QuotationRequestListScreen()
                        }

                        composable(AccountRoutes.Orders) {
                            OrderListScreen()
                        }

                        composable(AccountRoutes.OrderDetail) {
                            OrderDetailScreen()
                        }

                        composable(AccountRoutes.Favorites) {
                            FavoriteListScreen()
                        }

                        composable(AccountRoutes.Reviews) {
                            ReviewListScreen()
                        }

                        composable(AccountRoutes.Coupons) {
                            CouponListScreen()
                        }

                        composable(AccountRoutes.Requests) {
                            RequestListScreen()
                        }

                        composable(AccountRoutes.Subscriptions) {
                            CommunicationPreferenceScreen()
                        }

                        composable(AccountRoutes.BankAccounts) {
                            BankAccountListScreen()
                        }

                        composable(AccountRoutes.CommunicationPreferences) {
                            CommunicationPreferenceScreen()
                        }
                    }
                }
            }
        }
    }
}

object AccountRoutes {
    const val AccountHome = "account"

    const val Security = "account/security"
    const val ProfileInfo = "account/profile-info"
    const val EmailChange = "account/security/email"
    const val PasswordChange = "account/security/password"
    const val LoginActivities = "account/security/login-activities"

    const val AddressList = "account/address"
    const val AddressForm = "account/address/form"
    const val Notifications = "account/notifications"
    const val CompanyInfo = "account/company"
    const val FollowedStores = "account/followed-stores"
    const val QuotationRequests = "account/quotations"
    const val Orders = "account/orders"
    const val OrderDetail = "account/orders/detail"
    const val Favorites = "account/favorites"
    const val Reviews = "account/reviews"
    const val Coupons = "account/coupons"
    const val Requests = "account/requests"
    const val Subscriptions = "account/subscriptions"
    const val BankAccounts = "account/bank-accounts"
    const val BankAccountCreate = "account/bank-accounts/create"
    const val CommunicationPreferences = "account/preferences"
}