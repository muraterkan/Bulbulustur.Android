package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderCancelRequestScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderContractScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderReturnRequestScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderReviewCreateScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderShipmentTrackingScreen
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.OrderRoutes
import com.bulbulustur.android.Application.Navigation.Routes.StoreRoutes

fun NavGraphBuilder.orderGraph(
    navigator: BulbulusturNavigator,
    memberId: Int,
    languageId: Int
){
    composable(OrderRoutes.List) {
        OrderListScreen(
            memberId = memberId,
            onBackClick = { navigator.back() },
            onOrderDetailClick = { orderId, orderKey ->
                navigator.navController.navigate(
                    OrderRoutes.detail(orderId, orderKey)
                )
            }
        )
    }

    composable(
        route = OrderRoutes.Detail,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderId) {
                type = NavType.IntType
            },
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments
            ?.getInt(OrderRoutes.ArgOrderId)
            ?: 0

        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()

        OrderDetailScreen(
            orderId = orderId,
            orderKey = orderKey,
            onBackClick = {
                navigator.back()
            },
            onContractClick = { storeKey ->
                navigator.navController.navigate(
                    OrderRoutes.contract(
                        orderKey = orderKey,
                        storeKey = storeKey
                    )
                )
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreDetail
                )
            },
            onSupportClick = {},
            onCancelRequestClick = { orderStoreLineId, selectedOrderKey ->
                navigator.navController.navigate(
                    OrderRoutes.cancelRequest(
                        orderStoreLineId = orderStoreLineId,
                        orderKey = selectedOrderKey
                    )
                )
            },
            onReturnRequestClick = { orderStoreLineId, selectedOrderKey ->
                navigator.navController.navigate(
                    OrderRoutes.returnRequest(
                        orderStoreLineId = orderStoreLineId,
                        orderKey = selectedOrderKey
                    )
                )
            },
            onReviewCreateClick = { orderStoreLineId, productId, productSecureKey ->
                navigator.navController.navigate(
                    OrderRoutes.reviewCreate(
                        orderStoreLineId = orderStoreLineId,
                        productId = productId,
                        productSecureKey = productSecureKey
                    )
                )
            },
            onShipmentTrackingClick = { cargoTrackingNumber ->
                navigator.navController.navigate(
                    OrderRoutes.shipmentTracking(
                        cargoTrackingNumber
                    )
                )
            }
        )
    }

    composable(
        route = OrderRoutes.Contract,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            },
            navArgument(OrderRoutes.ArgStoreKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()

        val storeKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgStoreKey)
            .orEmpty()

        OrderContractScreen(
            orderKey = orderKey,
            storeKey = storeKey,
            onBackClick = {
                navigator.back()
            },
            onPrintClick = { contractText ->
                // Android paylaşım/yazdırma entegrasyonu sonraki teknik fazda bağlanacak.
            }
        )
    }

    composable(
        route = OrderRoutes.CancelRequest,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderStoreLineId) {
                type = NavType.LongType
            },
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderStoreLineId = backStackEntry.arguments
            ?.getLong(OrderRoutes.ArgOrderStoreLineId)
            ?: 0L

        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()

        OrderCancelRequestScreen(
            orderStoreLineId = orderStoreLineId,
            orderKey = orderKey,
            memberId = memberId,
            languageId = languageId,
            onBackClick = {
                navigator.back()
            },
            onSubmitSuccess = {
                navigator.back()
            }
        )
    }

    composable(
        route = OrderRoutes.ReturnRequest,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderStoreLineId) {
                type = NavType.LongType
            },
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderStoreLineId = backStackEntry.arguments
            ?.getLong(OrderRoutes.ArgOrderStoreLineId)
            ?: 0L

        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()

        OrderReturnRequestScreen(
            orderStoreLineId = orderStoreLineId,
            orderKey = orderKey,
            memberId = memberId,
            languageId = languageId,
            onBackClick = {
                navigator.back()
            },
            onSubmitSuccess = {
                navigator.back()
            }
        )
    }

    composable(
        route = OrderRoutes.ReviewCreate,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderStoreLineId) {
                type = NavType.LongType
            },
            navArgument(OrderRoutes.ArgProductId) {
                type = NavType.LongType
            },
            navArgument(OrderRoutes.ArgProductSecureKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderStoreLineId = backStackEntry.arguments
            ?.getLong(OrderRoutes.ArgOrderStoreLineId)
            ?: 0L

        val productId = backStackEntry.arguments
            ?.getLong(OrderRoutes.ArgProductId)
            ?: 0L

        val productSecureKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgProductSecureKey)
            .orEmpty()

        OrderReviewCreateScreen(
            orderStoreLineId = orderStoreLineId,
            productId = productId,
            productSecureKey = productSecureKey,
            memberId = memberId,
            onBackClick = {
                navigator.back()
            },
            onSubmitSuccess = {
                navigator.back()
            }
        )
    }

    composable(
        route = OrderRoutes.ShipmentTracking,
        arguments = listOf(
            navArgument(OrderRoutes.ArgCargoTrackingNumber) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val cargoTrackingNumber = backStackEntry.arguments
            ?.getInt(OrderRoutes.ArgCargoTrackingNumber)
            ?: 0

        OrderShipmentTrackingScreen(
            cargoTrackingNumber = cargoTrackingNumber,
            memberId = memberId,
            onBackClick = {
                navigator.back()
            }
        )
    }
}