package com.bulbulustur.android.Application.Views.Message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Reply
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextarea
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO

@Composable
fun MessageDetailScreen(
    currentMemberId: Int,
    messages: List<WholesaleMessageDTO>,
    otherUser: MemberDTO?,
    isLoading: Boolean,
    isSending: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onSendClick: (String) -> Unit = {}
) {
    var replyText by remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "6516e119-a8f7-44ed-aa46-f412c142a3ba", fallback = "Mesaj Detayı"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                MessageParticipantCard(otherUser = otherUser)
            }

            item {
                MessageConversationHeader()
            }

            when {
                isLoading && messages.isEmpty() -> {
                    item {
                        MessageDetailLoadingState()
                    }
                }

                !errorMessage.isNullOrBlank() && messages.isEmpty() -> {
                    item {
                        MessageDetailErrorState(
                            message = errorMessage,
                            onRetryClick = onRetryClick
                        )
                    }
                }

                messages.isEmpty() -> {
                    item {
                        MessageDetailEmptyState()
                    }
                }

                else -> {
                    items(
                        items = messages.asReversed(),
                        key = { message -> message.WholesaleMessageId }
                    ) { message ->
                        MessageBubbleCard(
                            message = message,
                            currentMemberId = currentMemberId
                        )
                    }
                }
            }

            item {
                MessageReplyEditor(
                    value = replyText,
                    isSending = isSending,
                    onValueChange = { value ->
                        replyText = value
                    },
                    onSendClick = {
                        val body = replyText.trim()

                        if (body.isNotEmpty()) {
                            onSendClick(body)
                            replyText = ""
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MessageParticipantCard(otherUser: MemberDTO?) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .background(MaterialTheme.colorScheme.primaryContainer, BBRadius.LgShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBIcon.Action)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = otherUser.fullName().ifBlank { BBLocalization.Current.Get(key = "2578a723-02d6-4f7b-aafb-ec618f46e735", fallback = "Toptan kullanıcı") },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (!otherUser?.Email.isNullOrBlank()) {
                    Text(
                        text = otherUser?.Email.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = BBLocalization.Current.Get(key = "c59382e8-3ebd-42c6-817e-758ce000a79e", fallback = "Toptan ticaret görüşmesi"),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun MessageConversationHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = BBLocalization.Current.Get(key = "6c37943c-1198-466b-8ffa-58787e7c327b", fallback = "Konuşma"),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = BBLocalization.Current.Get(key = "b402a12e-4194-494e-8fe8-250a55adafd4", fallback = "Mesaj geçmişinizi görüntüleyin ve yanıt gönderin."),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun MessageBubbleCard(
    message: WholesaleMessageDTO,
    currentMemberId: Int
) {
    val isMine = message.InsertedBy == currentMemberId || message.SenderId == currentMemberId
    val senderName = if (isMine) {
        "Siz"
    } else {
        message.SenderFullName
            ?.takeIf { it.isNotBlank() }
            ?: message.SenderName
                ?.takeIf { it.isNotBlank() }
            ?: BBLocalization.Current.Get(key = "418c6767-c6df-4e18-bee6-44d03099d84c", fallback = "Karşı taraf")
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isMine) Alignment.End else Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = senderName,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = message.InsertedDate,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isMine) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                    shape = BBRadius.LgShape
                )
                .padding(BBSpacing.CardPadding)
        ) {
            Text(
                text = message.Body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun MessageReplyEditor(
    value: String,
    isSending: Boolean,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Reply,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBIcon.Ui)
                )

                Text(
                    text = BBLocalization.Current.Get(key = "4a49d383-b72b-4b48-8985-f35822aaf75e", fallback = "Yanıtla"),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            BbTextarea(
                value = value,
                onValueChange = onValueChange,
                label = BBLocalization.Current.Get(key = "74d3bcb0-a3c9-477f-82f1-c091809c5a00", fallback = "Mesaj"),
                placeholder = BBLocalization.Current.Get(key = "97d0db58-c9c4-46d9-8384-23ed67446215", fallback = "Yanıtınızı yazın..."),
                minLines = 4,
                maxLines = 8
            )

            BbButton(
                text = if (isSending) "Gönderiliyor..." else BBLocalization.Current.Get(key = "1bba90af-aa63-41f8-bd0d-b51c4477afd7", fallback = ""),
                onClick = onSendClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = value.isNotBlank() && !isSending,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun MessageDetailLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.Space6),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MessageDetailErrorState(
    message: String,
    onRetryClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onRetryClick
    ) {
        Text(
            text = "$message\nTekrar denemek için dokunun.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun MessageDetailEmptyState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = BBLocalization.Current.Get(key = "d5404ff4-10e7-4335-9732-8f53641e2226", fallback = "Bu konuşmada gösterilecek mesaj bulunmuyor."),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun MemberDTO?.fullName(): String {
    if (this == null) return ""
    return listOf(Name, Surname).filter { it.isNotBlank() }.joinToString(" ")
}
