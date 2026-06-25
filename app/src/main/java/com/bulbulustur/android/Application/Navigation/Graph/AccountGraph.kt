package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Controllers.LogonController
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.BankAccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Navigation.Routes.OrderRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Navigation.Routes.SettingsRoutes
import com.bulbulustur.android.Application.Navigation.Routes.SplashRoutes
import com.bulbulustur.android.Application.Navigation.Routes.StoreRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Views.Account.AccountScreen
import com.bulbulustur.android.Application.Views.Account.AccountSecurityScreen
import com.bulbulustur.android.Application.Views.Account.AddressFormScreen
import com.bulbulustur.android.Application.Views.Account.AddressListScreen
import com.bulbulustur.android.Application.Views.Account.BankAccountCreateScreen
import com.bulbulustur.android.Application.Views.Account.BankAccountEditScreen
import com.bulbulustur.android.Application.Views.Account.BankAccountListScreen
import com.bulbulustur.android.Application.Views.Account.ChangeEmailScreen
import com.bulbulustur.android.Application.Views.Account.ChangePasswordScreen
import com.bulbulustur.android.Application.Views.Account.CompanyB2BIndexScreen
import com.bulbulustur.android.Application.Views.Account.CompanyB2BStatusScreen
import com.bulbulustur.android.Application.Views.Account.CompanyInfoEditScreen
import com.bulbulustur.android.Application.Views.Account.CompanyInfoScreen
import com.bulbulustur.android.Application.Views.Account.CouponListScreen
import com.bulbulustur.android.Application.Views.Account.FavoriteListScreen
import com.bulbulustur.android.Application.Views.Account.FollowedStoreListScreen
import com.bulbulustur.android.Application.Views.Account.LoginActivitiesScreen
import com.bulbulustur.android.Application.Views.Account.NotificationListScreen
import com.bulbulustur.android.Application.Views.Account.PhoneCreateScreen
import com.bulbulustur.android.Application.Views.Account.PhoneListScreen
import com.bulbulustur.android.Application.Views.Account.PhoneVerifyScreen
import com.bulbulustur.android.Application.Views.Account.ProfileScreen
import com.bulbulustur.android.Application.Views.Account.RequestDetailScreen
import com.bulbulustur.android.Application.Views.Account.RequestListScreen
import com.bulbulustur.android.Application.Views.Account.ReviewEditScreen
import com.bulbulustur.android.Application.Views.Account.ReviewListScreen
import com.bulbulustur.android.Application.Views.Account.SubscriptionDetailScreen
import com.bulbulustur.android.Application.Views.Account.SubscriptionListScreen
import com.bulbulustur.android.Application.Views.Account.WalletBalanceScreen
import com.bulbulustur.android.Application.Views.Preference.UsagePurposeScreen
import com.bulbulustur.android.Application.Views.Question.QuestionAnswerScreen
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage

fun NavGraphBuilder.accountGraph(
    navigator: BulbulusturNavigator,
    sessionState: UserSessionState,
    logonController: LogonController
) {
    composable(
        route = AccountRoutes.AccountHome
    ) {
        val logonState by
        logonController.State.collectAsState()

        val languageId =
            when (sessionState.Language) {
                EApplicationLanguage.Turkish -> 1
                EApplicationLanguage.English -> 2
            }

        if (!sessionState.IsAuthenticated) {
            LaunchedEffect(Unit) {
                navigator.navController.navigate(
                    LogonRoutes.Logon
                ) {
                    popUpTo(
                        AccountRoutes.AccountHome
                    ) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }

            return@composable
        }

        AccountScreen(
            isLogoutLoading =
                logonState.IsLoggingOut,
            onSecurityClick = {
                navigator.navController.navigate(
                    AccountRoutes.Security
                )
            },
            onProfileClick = {
                navigator.navController.navigate(
                    AccountRoutes.ProfileInfo
                )
            },
            onAddressClick = {
                navigator.navController.navigate(
                    AccountRoutes.AddressList
                )
            },
            onNotificationClick = {
                navigator.navController.navigate(
                    AccountRoutes.Notifications
                )
            },
            onCompanyInfoClick = {
                navigator.navController.navigate(
                    AccountRoutes.CompanyInfo
                )
            },
            onFollowedStoresClick = {
                navigator.navController.navigate(
                    AccountRoutes.FollowedStores
                )
            },
            onQuotationRequestsClick = {
                navigator.navigateToWholesaleOffers()
            },
            onOrdersClick = {
                navigator.navController.navigate(
                    OrderRoutes.List
                )
            },
            onFavoritesClick = {
                navigator.navigateToFavorites()
            },
            onReviewsClick = {
                navigator.navController.navigate(
                    AccountRoutes.Reviews
                )
            },
            onCouponsClick = {
                navigator.navController.navigate(
                    AccountRoutes.Coupons
                )
            },
            onRequestsClick = {
                navigator.navController.navigate(
                    AccountRoutes.Requests
                )
            },
            onSubscriptionsClick = {
                navigator.navController.navigate(
                    AccountRoutes.Subscriptions
                )
            },
            onWalletBalanceClick = {
                navigator.navController.navigate(
                    AccountRoutes.WalletBalance
                )
            },
            onBankAccountsClick = {
                navigator.navController.navigate(
                    BankAccountRoutes.List
                )
            },
            onSettingsClick = {
                navigator.navController.navigate(
                    SettingsRoutes.Home
                )
            },
            onMessagesClick = {
                navigator.navigateToInbox()
            },
            onSupportClick = {
            },
            onLogoutClick = {
                logonController.LogoutPost(
                    languageId = languageId,
                    onCompleted = {
                        navigator.navController.navigate(
                            SplashRoutes.ModeSelection
                        ) {
                            popUpTo(
                                navigator.navController.graph.startDestinationId
                            ) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            },
            onQuestionsClick = {
                navigator.navController.navigate(
                    AccountRoutes.QuestionAnswers
                )
            },
            onUsagePurposeClick = {
                navigator.navController.navigate(
                    AccountRoutes.UsagePurpose
                )
            },
            onHomeClick = {
                navigator.navigateToRetailHome()
            },
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onBasketClick = {
                navigator.navigateToRetailBasket()
            }
        )
    }

    composable(
        route = AccountRoutes.Security
    ) {
        AccountSecurityScreen(
            onBackClick = {
                navigator.back()
            },
            onProfileInfoClick = {
                navigator.navController.navigate(
                    AccountRoutes.ProfileInfo
                )
            },
            onEmailChangeClick = {
                navigator.navController.navigate(
                    AccountRoutes.EmailChange
                )
            },
            onPasswordChangeClick = {
                navigator.navController.navigate(
                    AccountRoutes.PasswordChange
                )
            },
            onPhonesClick = {
                navigator.navController.navigate(
                    AccountRoutes.PhoneList
                )
            },
            onLoginActivitiesClick = {
                navigator.navController.navigate(
                    AccountRoutes.LoginActivities
                )
            }
        )
    }

    composable(
        route = AccountRoutes.EmailChange
    ) {
        ChangeEmailScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.PasswordChange
    ) {
        ChangePasswordScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.LoginActivities
    ) {
        LoginActivitiesScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.ProfileInfo
    ) {
        ProfileScreen(
            onBackClick = {
                navigator.back()
            },
            onEditClick = {
                navigator.navController.navigate(
                    AccountRoutes.ProfileEdit
                )
            },
            onPhonesClick = {
                navigator.navController.navigate(
                    AccountRoutes.PhoneList
                )
            },
            onEmailClick = {
                navigator.navController.navigate(
                    AccountRoutes.EmailChange
                )
            },
            onUsagePurposeClick = {
                navigator.navController.navigate(
                    AccountRoutes.UsagePurpose
                )
            },
            onCompanyInfoClick = {
                navigator.navController.navigate(
                    AccountRoutes.CompanyInfo
                )
            },
            onB2BStatusClick = {
                navigator.navController.navigate(
                    AccountRoutes.CompanyB2BStatus
                )
            }
        )
    }

    composable(
        route = AccountRoutes.ProfileEdit
    ) {
        ProfileScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.PhoneList
    ) {
        PhoneListScreen(
            onBackClick = {
                navigator.back()
            },
            onCreatePhoneClick = {
                navigator.navController.navigate(
                    AccountRoutes.PhoneCreate
                )
            },
            onVerifyPhoneClick = {
                navigator.navController.navigate(
                    AccountRoutes.PhoneVerify
                )
            }
        )
    }

    composable(
        route = AccountRoutes.PhoneCreate
    ) {
        PhoneCreateScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.PhoneVerify
    ) {
        PhoneVerifyScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.AddressList
    ) {
        AddressListScreen(
            onBackClick = {
                navigator.back()
            },
            onCreateAddressClick = {
                navigator.navController.navigate(
                    AccountRoutes.AddressCreate
                )
            },
            onEditAddressClick = { addressId ->
                navigator.navController.navigate(
                    AccountRoutes.editAddress(
                        addressId
                    )
                )
            }
        )
    }

    composable(
        route = AccountRoutes.AddressCreate
    ) {
        AddressFormScreen(
            addressId = null,
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.AddressEdit,
        arguments = listOf(
            navArgument(
                "addressId"
            ) {
                type =
                    NavType.IntType
            }
        )
    ) { backStackEntry ->
        val addressId =
            backStackEntry.arguments
                ?.getInt(
                    "addressId"
                )
                ?: 0

        AddressFormScreen(
            addressId =
                addressId,
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.Notifications
    ) {
        NotificationListScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.CompanyInfo
    ) {
        CompanyInfoScreen(
            onBackClick = {
                navigator.back()
            },
            onEditClick = {
                navigator.navController.navigate(
                    AccountRoutes.CompanyInfoEdit
                )
            },
            onB2BIndexClick = {
                navigator.navController.navigate(
                    AccountRoutes.CompanyB2BIndex
                )
            },
            onB2CStoreClick = {
            }
        )
    }

    composable(
        route = AccountRoutes.CompanyInfoEdit
    ) {
        CompanyInfoEditScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.CompanyB2BIndex
    ) {
        CompanyB2BIndexScreen(
            onBackClick = {
                navigator.back()
            },
            onActivateClick = {
                navigator.navController.navigate(
                    AccountRoutes.CompanyB2BStatus
                )
            }
        )
    }

    composable(
        route = AccountRoutes.CompanyB2BStatus
    ) {
        CompanyB2BStatusScreen(
            onBackClick = {
                navigator.back()
            },
            onB2BManagementPanelClick = {
            }
        )
    }

    composable(
        route = AccountRoutes.UsagePurpose
    ) {
        UsagePurposeScreen(
            onBackClick = {
                navigator.back()
            },
            onRetailClick = {
                navigator.back()
            },
            onWholesaleClick = {
                navigator.back()
            },
            onBothClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.QuestionAnswers
    ) {
        QuestionAnswerScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.FollowedStores
    ) {
        FollowedStoreListScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.Favorites
    ) {
        FavoriteListScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.Reviews
    ) {
        ReviewListScreen(
            onBackClick = {
                navigator.back()
            },
            onProductClick = {
                navigator.navController.navigate(
                    RetailRoutes.ProductDetail
                )
            },
            onEditReviewClick = {
                navigator.navController.navigate(
                    AccountRoutes.ReviewEdit
                )
            },
            onDeleteReviewClick = {
            }
        )
    }

    composable(
        route = AccountRoutes.ReviewEdit
    ) {
        ReviewEditScreen(
            onBackClick = {
                navigator.back()
            },
            onSaveClick = {
                navigator.back()
            },
            onDeleteClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.Coupons
    ) {
        CouponListScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.Requests
    ) {
        RequestListScreen(
            onBackClick = {
                navigator.back()
            },
            onRequestDetailClick = {
                navigator.navController.navigate(
                    OrderRoutes.detail(
                        1
                    )
                )
            },
            onOrderListClick = {
                navigator.navController.navigate(
                    OrderRoutes.List
                )
            }
        )
    }

    composable(
        route = AccountRoutes.RequestDetail
    ) {
        RequestDetailScreen(
            onBackClick = {
                navigator.back()
            },
            onOrderClick = {
                navigator.navController.navigate(
                    AccountRoutes.OrderDetail
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
        route = AccountRoutes.Subscriptions
    ) {
        SubscriptionListScreen(
            onBackClick = {
                navigator.back()
            },
            onSubscriptionDetailClick = {
                navigator.navController.navigate(
                    AccountRoutes.SubscriptionDetail
                )
            }
        )
    }

    composable(
        route = AccountRoutes.SubscriptionDetail
    ) {
        SubscriptionDetailScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = AccountRoutes.WalletBalance
    ) {
        WalletBalanceScreen(
            onBackClick = {
                navigator.back()
            },
            onBankAccountsClick = {
                navigator.navController.navigate(
                    BankAccountRoutes.List
                )
            }
        )
    }

    composable(
        route = BankAccountRoutes.List
    ) {
        BankAccountListScreen(
            onBackClick = {
                navigator.back()
            },
            onCreateBankAccountClick = {
                navigator.navController.navigate(
                    BankAccountRoutes.Create
                )
            },
            onEditBankAccountClick = { bankAccountId ->
                navigator.navController.navigate(
                    BankAccountRoutes.edit(
                        bankAccountId
                    )
                )
            },
            onDeleteBankAccountClick = {
            },
            onCopyIbanClick = {
            }
        )
    }

    composable(
        route = BankAccountRoutes.Create
    ) {
        BankAccountCreateScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(
        route = BankAccountRoutes.Edit,
        arguments = listOf(
            navArgument(
                "bankAccountId"
            ) {
                type =
                    NavType.IntType
            }
        )
    ) { backStackEntry ->
        val bankAccountId =
            backStackEntry.arguments
                ?.getInt(
                    "bankAccountId"
                )
                ?: 0

        BankAccountEditScreen(
            bankAccountId =
                bankAccountId,
            onBackClick = {
                navigator.back()
            }
        )
    }
}