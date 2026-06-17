package com.bulbulustur.android.Application.Navigation

import androidx.navigation.NavHostController
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.BasketRoutes
import com.bulbulustur.android.Application.Navigation.Routes.MessageRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Navigation.Routes.SplashRoutes
import com.bulbulustur.android.Application.Navigation.Routes.WholesaleRoutes

class BulbulusturNavigator(
    val navController: NavHostController,
    private val openBuyerModeSheet: () -> Unit,
    private val closeBuyerModeSheet: () -> Unit
) {
    fun openModeSheet() {
        openBuyerModeSheet()
    }

    fun closeModeSheet() {
        closeBuyerModeSheet()
    }

    fun back() {
        navController.popBackStack()
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
        closeBuyerModeSheet()

        navController.navigate(WholesaleRoutes.RfqCreate) {
            launchSingleTop = true
        }
    }

    fun navigateToAccount() {
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
                this.inclusive = true
            }

            launchSingleTop = true
        }
    }

    fun navigateFromModeSelectionToWholesale() {
        navController.navigate(WholesaleRoutes.Home) {
            popUpTo(SplashRoutes.ModeSelection) {
                this.inclusive = true
            }

            launchSingleTop = true
        }
    }
}