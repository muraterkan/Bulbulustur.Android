package com.bulbulustur.android.features.message

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.components.form.BbTextarea
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

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
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGapCompact)
        ) {
            item {
                MessageDetailHeader(
                    conversation = conversation,
                    onBackClick = onBackClick
                )
            }

            item {
                MessageConversationInfoCard(
                    conversation = conversation
                )
            }

            item {
                BbSectionHeader(
                    title = "Konuşma",
                    subtitle = "Toptan ticaret görüşmesini görüntüleyin ve yanıt gönderin"
                )
            }

            items(
                items = conversation.messages,
                key = { message ->
                    message.conversationItemId
                }
            ) { message ->
                MessageBubbleCard(
                    message = message
                )
            }

            item {
                MessageReplyEditor(
                    value = replyText,
                    onValueChange = {
                        replyText = it
                    },
                    onSendClick = {
                        onSendClick(replyText)
                        replyText = ""
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(BbSpacing.Space4))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageDetailHeader(
    conversation: MessageConversation,
    onBackClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                BbButton(
                    text = "",
                    onClick = onBackClick,
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Mesaj",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = conversation.subject,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.MoreHoriz,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "Toptan ticaret görüşmenizi görüntüleyin ve karşı tarafa yanıt gönderin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
            ) {
                BbChip(
                    text = conversation.commerceModeName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = conversation.messageTypeName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = conversation.statusName,
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun MessageConversationInfoCard(
    conversation: MessageConversation
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGapSmall)
                ) {
                    Text(
                        text = conversation.companyName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (conversation.isVerifiedCompany) {
                        Icon(
                            imageVector = Icons.Outlined.Verified,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
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
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageBubbleCard(
    message: MessageConversationItem
) {
    val horizontalAlignment = if (message.isMine) {
        Alignment.End
    } else {
        Alignment.Start
    }

    val cardVariant = if (message.isMine) {
        BbCardVariant.Outlined
    } else {
        BbCardVariant.Outlined
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGapSmall)
        ) {
            if (!message.isMine) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = message.senderName,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = message.sentAt,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (message.isMine) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = cardVariant,
            padding = BbCardPadding.Medium
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
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
                        verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
                    ) {
                        message.attachments.forEach { attachment ->
                            BbChip(
                                text = attachment,
                                selected = false,
                                onClick = {}
                            )
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Reply,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Yanıt yaz",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
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
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
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

private fun getMessageConversation(messageId: Int): MessageConversation {
    return MessageConversation(
        messageId = messageId,
        subject = "450W paneller için fiyat teklifi",
        companyName = "Drauger Network Firması",
        companyDescription = "Toptan ürün, teknik doküman ve proje bazlı teklif görüşmesi.",
        commerceModeName = "Toptan",
        messageTypeName = "Gelen Kutusu",
        statusName = "Okundu",
        lastMessageDate = "10.05.2026 13:37",
        isVerifiedCompany = true,
        messages = listOf(
            MessageConversationItem(
                conversationItemId = 1,
                senderName = "Murat Erkan",
                body = "Selamlar, Drauger Network üzerindeki 450W panellerden 200 adetlik bir proje için fiyat teklifi rica ediyorum. Teslimat süresi nedir?",
                sentAt = "10.05.2026 12:47",
                isMine = false,
                attachments = emptyList()
            ),
            MessageConversationItem(
                conversationItemId = 2,
                senderName = "Bulbulustur",
                body = "Belgeleri Drauger panelindeki Teknik Dokümanlar kısmına ekledim. Siparişi onaylamanız durumunda kargo çıkışını yarın sabah sağlarız.",
                sentAt = "10.05.2026 13:37",
                isMine = true,
                attachments = listOf(
                    "Teknik Doküman",
                    "Proforma"
                )
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun MessageDetailScreenPreview() {
    BbTheme {
        MessageDetailScreen()
    }
}