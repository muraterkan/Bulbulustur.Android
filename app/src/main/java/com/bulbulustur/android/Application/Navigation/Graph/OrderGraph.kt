package com.bulbulustur.android.Application.Navigation.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.OrderRoutes
import com.bulbulustur.android.Application.Navigation.Routes.StoreRoutes
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderCancelRequestScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderContractScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderReturnRequestScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderReviewCreateScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderShipmentTrackingScreen

fun NavGraphBuilder.orderGraph(
    navigator: BulbulusturNavigator
) {
    composable(OrderRoutes.List) {
        OrderListScreen(
            onBackClick = { navigator.back() },
            onOrderDetailClick = { orderId ->
                navigator.navController.navigate(OrderRoutes.detail(orderId))
            }
        )
    }

    composable(
        route = OrderRoutes.Detail,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments?.getInt(OrderRoutes.ArgOrderId) ?: 0

        OrderDetailScreen(
            orderId = orderId,
            onBackClick = { navigator.back() },
            onContractClick = {
                navigator.navController.navigate(
                    OrderRoutes.contract(
                        orderKey = "ORD-F4QO-AFPR-J5EX",
                        storeKey = "STORE-ORTOBELLA"
                    )
                )
            },
            onStoreClick = {
                navigator.navController.navigate(StoreRoutes.StoreDetail)
            },
            onSupportClick = {},
            onCancelRequestClick = { orderStoreLineId, orderKey ->
                navigator.navController.navigate(
                    OrderRoutes.cancelRequest(
                        orderStoreLineId = orderStoreLineId,
                        orderKey = orderKey
                    )
                )
            },
            onReturnRequestClick = { orderStoreLineId, orderKey ->
                navigator.navController.navigate(
                    OrderRoutes.returnRequest(
                        orderStoreLineId = orderStoreLineId,
                        orderKey = orderKey
                    )
                )
            },
            onReviewCreateClick = { orderStoreLineId, productId, memberKey ->
                navigator.navController.navigate(
                    OrderRoutes.reviewCreate(
                        orderStoreLineId = orderStoreLineId,
                        productId = productId,
                        memberKey = memberKey
                    )
                )
            },
            onShipmentTrackingClick = { orderStoreLineId ->
                navigator.navController.navigate(
                    OrderRoutes.shipmentTracking(orderStoreLineId)
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

        OrderContractScreen(
            orderCode = orderKey.ifBlank { "ORD-F4QO-AFPR-J5EX" },
            onBackClick = { navigator.back() }
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
    ) {
        OrderCancelRequestScreen(
            onBackClick = { navigator.back() },
            onSubmitClick = { navigator.back() }
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
    ) {
        OrderReturnRequestScreen(
            onBackClick = { navigator.back() },
            onSubmitClick = { navigator.back() }
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
            navArgument(OrderRoutes.ArgMemberKey) {
                type = NavType.StringType
            }
        )
    ) {
        OrderReviewCreateScreen(
            onBackClick = { navigator.back() },
            onSubmitClick = { navigator.back() }
        )
    }

    composable(
        route = OrderRoutes.ShipmentTracking,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderStoreLineId) {
                type = NavType.LongType
            }
        )
    ) {
        OrderShipmentTrackingScreen(
            onBackClick = { navigator.back() }
        )
    }
}


