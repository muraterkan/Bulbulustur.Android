package com.bulbulustur.android.Application.Navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Features.company.CompanyContactScreen
import com.bulbulustur.android.Features.company.CompanyDetailScreen
import com.bulbulustur.android.Features.company.CompanyHomeScreen
import com.bulbulustur.android.Features.company.CompanyListScreen
import com.bulbulustur.android.Features.company.CompanyProductsScreen

fun NavGraphBuilder.companyGraph(
    navigator: BulbulusturNavigator
) {
    composable(CompanyRoutes.CompanyHome) {
        CompanyHomeScreen(
            onBackClick = { navigator.back() },
            onProfileClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyDetail)
            },
            onProductsClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyProducts)
            },
            onContactClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyContact)
            }
        )
    }

    composable(CompanyRoutes.CompanyProducts) {
        CompanyProductsScreen(
            onBackClick = { navigator.back() },
            onCompanyProfileClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyDetail)
            },
            onCompanyContactClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyContact)
            },
            onProductClick = {
                navigator.navController.navigate(WholesaleRoutes.ProductDetail)
            }
        )
    }

    composable(CompanyRoutes.CompanyContact) {
        CompanyContactScreen(
            onBackClick = { navigator.back() },
            onCompanyProfileClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyDetail)
            },
            onCompanyProductsClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyProducts)
            }
        )
    }

    composable(CompanyRoutes.CompanyList) {
        CompanyListScreen(
            onCompanyClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyDetail)
            }
        )
    }

    composable(CompanyRoutes.CompanyDetail) {
        CompanyDetailScreen(
            onBackClick = { navigator.back() },
            onHomeClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyHome)
            },
            onProductListClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyProducts)
            },
            onMessageClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyContact)
            },
            onContactClick = {
                navigator.navController.navigate(CompanyRoutes.CompanyContact)
            }
        )
    }
}