package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Controllers.CompanyController
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.CompanyRoutes
import com.bulbulustur.android.Application.Navigation.Routes.WholesaleRoutes
import com.bulbulustur.android.Application.Views.Company.CompanyContactScreen
import com.bulbulustur.android.Application.Views.Company.CompanyDetailScreen
import com.bulbulustur.android.Application.Views.Company.CompanyHomeScreen
import com.bulbulustur.android.Application.Views.Company.CompanyListScreen
import com.bulbulustur.android.Application.Views.Company.CompanyProductsScreen

fun NavGraphBuilder.companyGraph(
    navigator: BulbulusturNavigator,
    languageId: Int,
    companyController: CompanyController
) {
    composable(CompanyRoutes.CompanyHome) {
        val companyState by companyController.State.collectAsState()
        val companyId = 1

        LaunchedEffect(languageId, companyId) {
            companyController.GetCompany(languageId = languageId, companyId = companyId)
        }

        CompanyHomeScreen(
            company = companyState.CompanyResult?.Data,
            isLoading = companyState.IsLoading,
            errorMessage = companyState.ErrorMessage,
            onBackClick = { navigator.back() },
            onProfileClick = { navigator.navController.navigate(CompanyRoutes.CompanyDetail) },
            onProductsClick = { navigator.navController.navigate(CompanyRoutes.CompanyProducts) },
            onContactClick = { navigator.navController.navigate(CompanyRoutes.CompanyContact) }
        )
    }

    composable(CompanyRoutes.CompanyProducts) {
        CompanyProductsScreen(
            onBackClick = { navigator.back() },
            onCompanyProfileClick = { navigator.navController.navigate(CompanyRoutes.CompanyDetail) },
            onCompanyContactClick = { navigator.navController.navigate(CompanyRoutes.CompanyContact) },
            onProductClick = { navigator.navController.navigate(WholesaleRoutes.ProductDetail) }
        )
    }

    composable(CompanyRoutes.CompanyContact) {
        CompanyContactScreen(
            onBackClick = { navigator.back() },
            onCompanyProfileClick = { navigator.navController.navigate(CompanyRoutes.CompanyDetail) },
            onCompanyProductsClick = { navigator.navController.navigate(CompanyRoutes.CompanyProducts) }
        )
    }

    composable(CompanyRoutes.CompanyList) {
        val companyState by companyController.State.collectAsState()

        LaunchedEffect(languageId) {
            companyController.GetCompanies(languageId = languageId, page = 1, pageSize = 20)
        }

        CompanyListScreen(
            companies = companyState.CompanyListResult?.Data?.Items.orEmpty(),
            isLoading = companyState.IsLoading,
            errorMessage = companyState.ErrorMessage,
            onBackClick = { navigator.back() },
            onCompanyClick = { navigator.navController.navigate(CompanyRoutes.CompanyDetail) },
            onProductListClick = { navigator.navController.navigate(CompanyRoutes.CompanyProducts) },
            onMessageClick = { navigator.navController.navigate(CompanyRoutes.CompanyContact) }
        )
    }

    composable(CompanyRoutes.CompanyDetail) {
        CompanyDetailScreen(
            onBackClick = { navigator.back() },
            onHomeClick = { navigator.navController.navigate(CompanyRoutes.CompanyHome) },
            onProductListClick = { navigator.navController.navigate(CompanyRoutes.CompanyProducts) },
            onMessageClick = { navigator.navController.navigate(CompanyRoutes.CompanyContact) },
            onContactClick = { navigator.navController.navigate(CompanyRoutes.CompanyContact) }
        )
    }
}
