package com.bulbulustur.android.Application.Views.Message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Reply
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Verified
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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextarea
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun MessageDetailScreen(
    messageId: Int = 1,
    onBackClick: () -> Unit = {},
    onSendClick: (String) -> Unit = {}
) {
    val conversation = remember(messageId) {
        getMessageConversation(messageId)
    }

    var replyText by remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Mesaj Detayı",
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
                MessageSubjectCard(conversation = conversation)
            }

            item {
                MessageCompanyCard(conversation = conversation)
            }

            item {
                MessageConversationHeader()
            }

            items(
                items = conversation.messages,
                key = { message -> message.conversationItemId }
            ) { message ->
                MessageBubbleCard(message = message)
            }

            item {
                MessageReplyEditor(
                    value = replyText,
                    onValueChange = { value ->
                        replyText = value
                    },
                    onSendClick = {
                        onSendClick(replyText)
                        replyText = ""
                    }
                )
            }
        }
    }
}

@Composable
private fun MessageSubjectCard(
    conversation: MessageConversation
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                MessageIconBox(
                    icon = Icons.Outlined.RequestQuote,
                    backgroundColor = BBColors.Yellow.Yellow100,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "Konuşma",
                        style = MaterialTheme.typography.labelSmall,
                        color = BBColors.Yellow.Yellow800
                    )

                    Text(
                        text = conversation.subject,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Ticaret görüşmenizi görüntüleyin ve karşı tarafa yanıt gönderin.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            MessageTagRow(
                tags = listOf(
                    conversation.commerceModeName,
                    conversation.messageTypeName,
                    conversation.statusName
                )
            )
        }
    }
}

@Composable
private fun MessageCompanyCard(
    conversation: MessageConversation
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            MessageIconBox(
                icon = Icons.Outlined.Business,
                backgroundColor = BBColors.Blue.Blue50,
                iconColor = BBColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = conversation.companyName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )

                    if (conversation.isVerifiedCompany) {
                        Icon(
                            imageVector = Icons.Outlined.Verified,
                            contentDescription = null,
                            tint = BBColors.Green.Green600,
                            modifier = Modifier.size(BBIcon.Action)
                        )
                    }
                }

                Text(
                    text = conversation.companyDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Son mesaj: ${conversation.lastMessageDate}",
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
            text = "Konuşma",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Mesaj geçmişi ve ekler",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageBubbleCard(
    message: MessageConversationItem
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isMine) Alignment.End else Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message.senderName,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = message.sentAt,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (message.isMine) BBColors.Yellow.Yellow50 else MaterialTheme.colorScheme.surface,
                    shape = BBRadius.LgShape
                )
                .padding(BBSpacing.CardPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Text(
                    text = message.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (message.attachments.isNotEmpty()) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        message.attachments.forEach { attachment ->
                            MessageAttachmentTag(text = attachment)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MessageReplyEditor(
    value: String,
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
                    text = "Yanıtla",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            BbTextarea(
                value = value,
                onValueChange = onValueChange,
                label = "Mesaj",
                placeholder = "Yanıtınızı yazın...",
                minLines = 4,
                maxLines = 8
            )

            BbButton(
                text = "Gönder",
                onClick = onSendClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = value.isNotBlank(),
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageTagRow(
    tags: List<String>
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        tags.forEach { tag ->
            MessageSmallTag(text = tag)
        }
    }
}

@Composable
private fun MessageSmallTag(
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun MessageAttachmentTag(
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                color = BBColors.Blue.Blue50,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.Blue.Blue700
        )
    }
}

@Composable
private fun MessageIconBox(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: androidx.compose.ui.graphics.Color,
    iconColor: androidx.compose.ui.graphics.Color
) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Action)
        )
    }
}

data class MessageConversation(
    val messageId: Int,
    val subject: String,
    val companyName: String,
    val companyDescription: String,
    val commerceModeName: String,
    val messageTypeName: String,
    val statusName: String,
    val lastMessageDate: String,
    val isVerifiedCompany: Boolean,
    val messages: List<MessageConversationItem>
)

data class MessageConversationItem(
    val conversationItemId: Int,
    val senderName: String,
    val body: String,
    val sentAt: String,
    val isMine: Boolean,
    val attachments: List<String>
)

private fun getMessageConversation(
    messageId: Int
): MessageConversation {
    return MessageConversation(
        messageId = messageId,
        subject = "450W paneller için fiyat teklifi",
        companyName = "Anadolu Ambalaj Sanayi",
        companyDescription = "Toptan ürün, numune ve RFQ görüşmeleri için doĞrulanmış firma.",
        commerceModeName = "Toptan",
        messageTypeName = "Gelen Kutusu",
        statusName = "Okundu",
        lastMessageDate = "10.05.2026 13:37",
        isVerifiedCompany = true,
        messages = listOf(
            MessageConversationItem(
                conversationItemId = 1,
                senderName = "Anadolu Ambalaj Sanayi",
                body = "RFQ talebinize istinaden numune ve fiyat bilgilerini paylaşmak isteriz. Miktar ve teslimat hedefinizi netleştirirseniz daha doĞru fiyat sunabiliriz.",
                sentAt = "10.05.2026 12:47",
                isMine = false,
                attachments = emptyList()
            ),
            MessageConversationItem(
                conversationItemId = 2,
                senderName = "Bulbulustur",
                body = "Merhaba, 10.000 adet için İstanbul teslim fiyat ve termin süresini paylaşabilir misiniz?",
                sentAt = "10.05.2026 13:37",
                isMine = true,
                attachments = listOf("RFQ Özeti", "Teknik Not")
            )
        )
    )
}

