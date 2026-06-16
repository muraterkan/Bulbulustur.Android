package com.bulbulustur.android.Views.message

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
import androidx.compose.material.icons.outlined.Description
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
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.components.form.BbTextarea
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

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
        containerColor = BbColors.SurfaceMuted,
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
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                MessageIconBox(
                    icon = Icons.Outlined.Description,
                    backgroundColor = BbColors.Yellow.Yellow100,
                    iconColor = BbColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Konuşma",
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.Yellow.Yellow800
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            MessageIconBox(
                icon = Icons.Outlined.Business,
                backgroundColor = BbColors.Blue.Blue50,
                iconColor = BbColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
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
                            tint = BbColors.Green.Green600,
                            modifier = Modifier.size(BbIcon.Action)
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
                    color = BbColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun MessageConversationHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
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
                color = BbColors.TextMuted
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (message.isMine) BbColors.Yellow.Yellow50 else BbColors.Surface,
                    shape = BbRadius.LgShape
                )
                .padding(BbSpacing.CardPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Text(
                    text = message.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (message.attachments.isNotEmpty()) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Reply,
                    contentDescription = null,
                    tint = BbColors.Yellow.Yellow800,
                    modifier = Modifier.size(BbIcon.Ui)
                )

                Text(
                    text = "Yanıt yaz",
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
                        modifier = Modifier.size(BbIcon.ButtonIcon)
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
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
                color = BbColors.Blue.Blue50,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.Blue.Blue700
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
            .size(BbIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BbIcon.Action)
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
        companyDescription = "Toptan ürün, numune ve RFQ görüşmeleri için doğrulanmış firma.",
        commerceModeName = "Toptan",
        messageTypeName = "Gelen Kutusu",
        statusName = "Okundu",
        lastMessageDate = "10.05.2026 13:37",
        isVerifiedCompany = true,
        messages = listOf(
            MessageConversationItem(
                conversationItemId = 1,
                senderName = "Anadolu Ambalaj Sanayi",
                body = "RFQ talebinize istinaden numune ve fiyat bilgilerini paylaşmak isteriz. Miktar ve teslimat hedefinizi netleştirirseniz daha doğru fiyat sunabiliriz.",
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