package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
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
    composable(
        route = CompanyRoutes.CompanyHome,
        arguments = listOf(navArgument(CompanyRoutes.ArgCompanyId) { type = NavType.IntType })
    ) { backStackEntry ->
        val companyState by companyController.State.collectAsState()
        val companyId = backStackEntry.arguments?.getInt(CompanyRoutes.ArgCompanyId) ?: 0

        LaunchedEffect(languageId, companyId) {
            companyController.GetCompany(languageId = languageId, companyId = companyId)
        }

        CompanyHomeScreen(
            company = companyState.CompanyResult?.Data,
            isLoading = companyState.IsLoading,
            errorMessage = companyState.ErrorMessage,
            onBackClick = { navigator.back() },
            onProfileClick = { navigator.navController.navigate(CompanyRoutes.companyDetail(companyId)) },
            onProductsClick = { navigator.navController.navigate(CompanyRoutes.companyProducts(companyId)) },
            onContactClick = { navigator.navController.navigate(CompanyRoutes.companyContact(companyId)) }
        )
    }

    composable(
        route = CompanyRoutes.CompanyProducts,
        arguments = listOf(navArgument(CompanyRoutes.ArgCompanyId) { type = NavType.IntType })
    ) { backStackEntry ->
        val companyId = backStackEntry.arguments?.getInt(CompanyRoutes.ArgCompanyId) ?: 0
        val companyState by companyController.State.collectAsState()

        LaunchedEffect(languageId, companyId) {
            if (companyId > 0) {
                companyController.GetCompany(languageId = languageId, companyId = companyId)
                companyController.GetCompanyProducts(languageId = languageId)
            }
        }

        CompanyProductsScreen(
            company = companyState.CompanyResult?.Data,
            products = companyState.CompanyProductsResult?.Data?.let { data ->
                if (data.Products2.Items.isNotEmpty()) data.Products2.Items else data.Products
            }.orEmpty(),
            isLoading = companyState.IsLoading,
            errorMessage = companyState.ErrorMessage,
            onBackClick = { navigator.back() },
            onCompanyProfileClick = { navigator.navController.navigate(CompanyRoutes.companyDetail(companyId)) },
            onCompanyContactClick = { navigator.navController.navigate(CompanyRoutes.companyContact(companyId)) },
            onProductClick = { navigator.navController.navigate(WholesaleRoutes.ProductDetail) }
        )
    }

    composable(
        route = CompanyRoutes.CompanyContact,
        arguments = listOf(navArgument(CompanyRoutes.ArgCompanyId) { type = NavType.IntType })
    ) { backStackEntry ->
        val companyId = backStackEntry.arguments?.getInt(CompanyRoutes.ArgCompanyId) ?: 0

        CompanyContactScreen(
            companyId = companyId,
            onBackClick = { navigator.back() },
            onCompanyProfileClick = { navigator.navController.navigate(CompanyRoutes.companyDetail(companyId)) },
            onCompanyProductsClick = { navigator.navController.navigate(CompanyRoutes.companyProducts(companyId)) }
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
            onCompanyClick = { companyId -> navigator.navController.navigate(CompanyRoutes.companyDetail(companyId)) },
            onProductListClick = { companyId -> navigator.navController.navigate(CompanyRoutes.companyProducts(companyId)) },
            onMessageClick = { companyId -> navigator.navController.navigate(CompanyRoutes.companyContact(companyId)) }
        )
    }

    composable(
        route = CompanyRoutes.CompanyDetail,
        arguments = listOf(navArgument(CompanyRoutes.ArgCompanyId) { type = NavType.IntType })
    ) { backStackEntry ->
        val companyId = backStackEntry.arguments?.getInt(CompanyRoutes.ArgCompanyId) ?: 0
        val companyState by companyController.State.collectAsState()

        LaunchedEffect(languageId, companyId) {
            if (companyId > 0) {
                companyController.GetCompany(languageId = languageId, companyId = companyId)
            }
        }

        CompanyDetailScreen(
            companyDto = companyState.CompanyResult?.Data,
            isLoading = companyState.IsLoading,
            errorMessage = companyState.ErrorMessage,
            onBackClick = { navigator.back() },
            onHomeClick = { id -> navigator.navController.navigate(CompanyRoutes.companyHome(id)) },
            onProductListClick = { id -> navigator.navController.navigate(CompanyRoutes.companyProducts(id)) },
            onMessageClick = { id -> navigator.navController.navigate(CompanyRoutes.companyContact(id)) },
            onContactClick = { id -> navigator.navController.navigate(CompanyRoutes.companyContact(id)) }
        )
    }
}
