package com.bulbulustur.android.Application.Navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Features.message.MessageDetailScreen
import com.bulbulustur.android.Features.message.MessageInboxScreen

fun NavGraphBuilder.messageGraph(
    navigator: BulbulusturNavigator
) {
    composable(MessageRoutes.Inbox) {
        MessageInboxScreen(
            onBackClick = {
                val previousRoute = navigator.navController.previousBackStackEntry
                    ?.destination
                    ?.route
                    .orEmpty()

                val didPop = navigator.navController.popBackStack()

                if (!didPop) {
                    if (previousRoute.startsWith("wholesale/")) {
                        navigator.navController.navigate(WholesaleRoutes.Home) {
                            launchSingleTop = true
                        }
                    } else {
                        navigator.navController.navigate(RetailRoutes.Home) {
                            launchSingleTop = true
                        }
                    }
                }
            },
            onMessageClick = { messageId ->
                navigator.navController.navigate(MessageRoutes.detail(messageId))
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
                navigator.back()
            },
            onSendClick = {
                // V1 dummy
            }
        )
    }
}