package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Navigation.AccountRoutes
import com.bulbulustur.android.Application.Navigation.BankAccountRoutes
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.OrderRoutes
import com.bulbulustur.android.Application.Navigation.RetailRoutes
import com.bulbulustur.android.Application.Navigation.SettingsRoutes
import com.bulbulustur.android.Application.Navigation.StoreRoutes
import com.bulbulustur.android.Features.account.AccountScreen
import com.bulbulustur.android.Features.account.AccountSecurityScreen
import com.bulbulustur.android.Features.account.AddressFormScreen
import com.bulbulustur.android.Features.account.AddressListScreen
import com.bulbulustur.android.Features.account.CouponListScreen
import com.bulbulustur.android.Features.account.RequestDetailScreen
import com.bulbulustur.android.Features.account.RequestListScreen
import com.bulbulustur.android.Features.account.ReviewListScreen
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
import com.bulbulustur.android.Features.account.review.ReviewEditScreen
import com.bulbulustur.android.Features.account.security.ChangeEmailScreen
import com.bulbulustur.android.Features.account.security.ChangePasswordScreen
import com.bulbulustur.android.Features.account.security.LoginActivitiesScreen
import com.bulbulustur.android.Features.account.subscription.SubscriptionDetailScreen
import com.bulbulustur.android.Features.account.subscription.SubscriptionListScreen
import com.bulbulustur.android.Features.account.wallet.WalletBalanceScreen

fun NavGraphBuilder.accountGraph(
    navigator: BulbulusturNavigator
) {
    composable(AccountRoutes.AccountHome) {
        AccountScreen(
            onSecurityClick = { navigator.navController.navigate(AccountRoutes.Security) },
            onProfileClick = { navigator.navController.navigate(AccountRoutes.ProfileInfo) },
            onAddressClick = { navigator.navController.navigate(AccountRoutes.AddressList) },
            onNotificationClick = { navigator.navController.navigate(AccountRoutes.Notifications) },
            onCompanyInfoClick = { navigator.navController.navigate(AccountRoutes.CompanyInfo) },
            onFollowedStoresClick = { navigator.navController.navigate(AccountRoutes.FollowedStores) },
            onQuotationRequestsClick = { navigator.navigateToWholesaleOffers() },
            onOrdersClick = { navigator.navController.navigate(OrderRoutes.List) },
            onFavoritesClick = { navigator.navigateToFavorites() },
            onReviewsClick = { navigator.navController.navigate(AccountRoutes.Reviews) },
            onCouponsClick = { navigator.navController.navigate(AccountRoutes.Coupons) },
            onRequestsClick = { navigator.navController.navigate(AccountRoutes.Requests) },
            onSubscriptionsClick = { navigator.navController.navigate(AccountRoutes.Subscriptions) },
            onWalletBalanceClick = { navigator.navController.navigate(AccountRoutes.WalletBalance) },
            onBankAccountsClick = { navigator.navController.navigate(BankAccountRoutes.List) },
            onSettingsClick = { navigator.navController.navigate(SettingsRoutes.Home) },
            onMessagesClick = { navigator.navigateToInbox() },
            onSupportClick = {},
            onLogoutClick = {},
            onQuestionsClick = { navigator.navController.navigate(AccountRoutes.QuestionAnswers) },
            onUsagePurposeClick = { navigator.navController.navigate(AccountRoutes.UsagePurpose) },
            onHomeClick = { navigator.navigateToRetailHome() },
            onMenuClick = { navigator.navigateToRetailCategories() },
            onModeSwitchClick = { navigator.openModeSheet() },
            onBasketClick = { navigator.navigateToRetailBasket() }
        )
    }

    composable(AccountRoutes.Security) {
        AccountSecurityScreen(
            onBackClick = { navigator.back() },
            onProfileInfoClick = { navigator.navController.navigate(AccountRoutes.ProfileInfo) },
            onEmailChangeClick = { navigator.navController.navigate(AccountRoutes.EmailChange) },
            onPasswordChangeClick = { navigator.navController.navigate(AccountRoutes.PasswordChange) },
            onPhonesClick = { navigator.navController.navigate(AccountRoutes.PhoneList) },
            onLoginActivitiesClick = { navigator.navController.navigate(AccountRoutes.LoginActivities) }
        )
    }

    composable(AccountRoutes.EmailChange) {
        ChangeEmailScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.PasswordChange) {
        ChangePasswordScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.LoginActivities) {
        LoginActivitiesScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.ProfileInfo) {
        ProfileScreen(
            onBackClick = { navigator.back() },
            onEditClick = { navigator.navController.navigate(AccountRoutes.ProfileEdit) },
            onPhonesClick = { navigator.navController.navigate(AccountRoutes.PhoneList) },
            onEmailClick = { navigator.navController.navigate(AccountRoutes.EmailChange) },
            onUsagePurposeClick = { navigator.navController.navigate(AccountRoutes.UsagePurpose) },
            onCompanyInfoClick = { navigator.navController.navigate(AccountRoutes.CompanyInfo) },
            onB2BStatusClick = { navigator.navController.navigate(AccountRoutes.CompanyB2BStatus) }
        )
    }

    composable(AccountRoutes.ProfileEdit) {
        ProfileScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.PhoneList) {
        PhoneListScreen(
            onBackClick = { navigator.back() },
            onCreatePhoneClick = { navigator.navController.navigate(AccountRoutes.PhoneCreate) },
            onVerifyPhoneClick = { navigator.navController.navigate(AccountRoutes.PhoneVerify) }
        )
    }

    composable(AccountRoutes.PhoneCreate) {
        PhoneCreateScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.PhoneVerify) {
        PhoneVerifyScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.AddressList) {
        AddressListScreen(
            onBackClick = { navigator.back() },
            onCreateAddressClick = { navigator.navController.navigate(AccountRoutes.AddressCreate) },
            onEditAddressClick = { addressId ->
                navigator.navController.navigate(AccountRoutes.editAddress(addressId))
            }
        )
    }

    composable(AccountRoutes.AddressCreate) {
        AddressFormScreen(
            addressId = null,
            onBackClick = { navigator.back() }
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
            onBackClick = { navigator.back() }
        )
    }

    composable(AccountRoutes.Notifications) {
        NotificationListScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.CompanyInfo) {
        CompanyInfoScreen(
            onBackClick = { navigator.back() },
            onEditClick = { navigator.navController.navigate(AccountRoutes.CompanyInfoEdit) },
            onB2BIndexClick = { navigator.navController.navigate(AccountRoutes.CompanyB2BIndex) },
            onB2CStoreClick = {}
        )
    }

    composable(AccountRoutes.CompanyInfoEdit) {
        CompanyInfoEditScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.CompanyB2BIndex) {
        CompanyB2BIndexScreen(
            onBackClick = { navigator.back() },
            onActivateClick = { navigator.navController.navigate(AccountRoutes.CompanyB2BStatus) }
        )
    }

    composable(AccountRoutes.CompanyB2BStatus) {
        CompanyB2BStatusScreen(
            onBackClick = { navigator.back() },
            onB2BManagementPanelClick = {}
        )
    }

    composable(AccountRoutes.UsagePurpose) {
        UsagePurposeScreen(
            onBackClick = { navigator.back() },
            onRetailClick = { navigator.back() },
            onWholesaleClick = { navigator.back() },
            onBothClick = { navigator.back() }
        )
    }

    composable(AccountRoutes.QuestionAnswers) {
        QuestionAnswerScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.FollowedStores) {
        FollowedStoreListScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.Favorites) {
        FavoriteListScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.Reviews) {
        ReviewListScreen(
            onBackClick = { navigator.back() },
            onProductClick = { navigator.navController.navigate(RetailRoutes.ProductDetail) },
            onEditReviewClick = { navigator.navController.navigate(AccountRoutes.ReviewEdit) },
            onDeleteReviewClick = {}
        )
    }

    composable(AccountRoutes.ReviewEdit) {
        ReviewEditScreen(
            onBackClick = { navigator.back() },
            onSaveClick = { navigator.back() },
            onDeleteClick = { navigator.back() }
        )
    }

    composable(AccountRoutes.Coupons) {
        CouponListScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.Requests) {
        RequestListScreen(
            onBackClick = { navigator.back() },
            onRequestDetailClick = { navigator.navController.navigate(OrderRoutes.detail(1)) },
            onOrderListClick = { navigator.navController.navigate(OrderRoutes.List) }
        )
    }

    composable(AccountRoutes.RequestDetail) {
        RequestDetailScreen(
            onBackClick = { navigator.back() },
            onOrderClick = { navigator.navController.navigate(AccountRoutes.OrderDetail) },
            onStoreClick = { navigator.navController.navigate(StoreRoutes.StoreDetail) }
        )
    }

    composable(AccountRoutes.Subscriptions) {
        SubscriptionListScreen(
            onBackClick = { navigator.back() },
            onSubscriptionDetailClick = {
                navigator.navController.navigate(AccountRoutes.SubscriptionDetail)
            }
        )
    }

    composable(AccountRoutes.SubscriptionDetail) {
        SubscriptionDetailScreen(onBackClick = { navigator.back() })
    }

    composable(AccountRoutes.WalletBalance) {
        WalletBalanceScreen(
            onBackClick = { navigator.back() },
            onBankAccountsClick = { navigator.navController.navigate(BankAccountRoutes.List) }
        )
    }

    composable(BankAccountRoutes.List) {
        BankAccountListScreen(
            onBackClick = { navigator.back() },
            onCreateBankAccountClick = { navigator.navController.navigate(BankAccountRoutes.Create) },
            onEditBankAccountClick = { bankAccountId ->
                navigator.navController.navigate(BankAccountRoutes.edit(bankAccountId))
            },
            onDeleteBankAccountClick = {},
            onCopyIbanClick = {}
        )
    }

    composable(BankAccountRoutes.Create) {
        BankAccountCreateScreen(onBackClick = { navigator.back() })
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
            onBackClick = { navigator.back() }
        )
    }
}