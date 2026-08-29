package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Controllers.MessageController
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Navigation.Routes.MessageRoutes
import com.bulbulustur.android.Application.Navigation.Routes.WholesaleRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Views.Message.MessageDetailScreen
import com.bulbulustur.android.Application.Views.Message.MessageInboxScreen

fun NavGraphBuilder.messageGraph(
    navigator: BulbulusturNavigator,
    messageController: MessageController,
    sessionState: UserSessionState
) {
    composable(MessageRoutes.Inbox) {
        if (!sessionState.IsAuthenticated || sessionState.MemberId <= 0) {
            LaunchedEffect(Unit) {
                navigator.navController.navigate(LogonRoutes.Logon) {
                    popUpTo(MessageRoutes.Inbox) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }

            return@composable
        }

        val state by messageController.State.collectAsState()
        val memberId = sessionState.MemberId
        val languageId = sessionState.Language.Id

        LaunchedEffect(languageId, memberId) {
            messageController.Inbox(
                languageId = languageId,
                memberId = memberId
            )

            messageController.UnreadCount(
                memberId = memberId
            )
        }

        MessageInboxScreen(
            messages = state.Messages,
            unreadCount = state.UnreadCount,
            isLoading = state.IsLoading && (state.CurrentAction == "Inbox" || state.CurrentAction == "UnreadCount"),
            errorMessage = state.ErrorMessage,
            currentMemberId = memberId,
            onBackClick = {
                val didPop = navigator.navController.popBackStack()

                if (!didPop) {
                    navigator.navController.navigate(WholesaleRoutes.Home) {
                        launchSingleTop = true
                    }
                }
            },
            onRetryClick = {
                messageController.Inbox(languageId = languageId, memberId = memberId)
                messageController.UnreadCount(memberId = memberId)
            },
            onMessageClick = { messageThreadId, messageId ->
                navigator.navController.navigate(MessageRoutes.detail(messageThreadId, messageId))
            }
        )
    }

    composable(
        route = MessageRoutes.Detail,
        arguments = listOf(
            navArgument(MessageRoutes.ArgMessageThreadId) {
                type = NavType.IntType
            },
            navArgument(MessageRoutes.ArgMessageId) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val state by messageController.State.collectAsState()
        val memberId = sessionState.MemberId
        val languageId = sessionState.Language.Id
        val messageThreadId = backStackEntry.arguments?.getInt(MessageRoutes.ArgMessageThreadId) ?: 0
        val messageId = backStackEntry.arguments?.getInt(MessageRoutes.ArgMessageId) ?: 0

        LaunchedEffect(languageId, memberId, messageThreadId, messageId) {
            messageController.Thread(
                languageId = languageId,
                memberId = memberId,
                messageThreadId = messageThreadId
            )
            messageController.OtherUser(
                languageId = languageId,
                memberId = memberId,
                messageThreadId = messageThreadId
            )
            messageController.MarkAsRead(memberId = memberId, messageId = messageId)
        }

        MessageDetailScreen(
            currentMemberId = memberId,
            messages = state.ThreadMessages,
            otherUser = state.OtherUser,
            isLoading = state.IsLoading && (state.CurrentAction == "Thread" || state.CurrentAction == "OtherUser"),
            isSending = state.IsLoading && state.CurrentAction == "Reply",
            errorMessage = state.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onRetryClick = {
                messageController.Thread(
                    languageId = languageId,
                    memberId = memberId,
                    messageThreadId = messageThreadId
                )
                messageController.OtherUser(
                    languageId = languageId,
                    memberId = memberId,
                    messageThreadId = messageThreadId
                )
            },
            onSendClick = { body ->
                messageController.Reply(
                    memberId = memberId,
                    messageThreadId = messageThreadId,
                    body = body,
                    onSuccess = {
                        messageController.Thread(
                            languageId = languageId,
                            memberId = memberId,
                            messageThreadId = messageThreadId
                        )
                        messageController.Inbox(languageId = languageId, memberId = memberId)
                    }
                )
            }
        )
    }
}
