package com.bulbulustur.android.features.message

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.MarkEmailRead
import androidx.compose.material.icons.outlined.MarkEmailUnread
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun MessageInboxScreen(
    onBackClick: () -> Unit = {},
    onMessageClick: (Int) -> Unit = {}
) {
    val messages = getDemoMessages()

    Scaffold(
        containerColor = BbColors.Surface
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BbColors.Surface)
                .windowInsetsPadding(WindowInsets.statusBars)
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
                MessageTopBackButton(
                    onBackClick = onBackClick
                )
            }

            item {
                MessageHeroCard()
            }

            item {
                MessageStatsRow()
            }

            item {
                MessageFilterChips()
            }

            item {
                MessageSectionTitle()
            }

            items(
                items = messages,
                key = { item ->
                    item.id
                }
            ) { message ->
                MessageCard(
                    message = message,
                    onClick = {
                        onMessageClick(message.id)
                    }
                )
            }
        }
    }
}

@Composable
private fun MessageTopBackButton(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = BbColors.SurfaceMuted,
                    shape = BbRadius.PillShape
                )
                .clickable {
                    onBackClick()
                }
                .padding(
                    horizontal = BbSpacing.Space3,
                    vertical = BbSpacing.Space2
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Geri dön",
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(BbIcon.SizeMd)
                )

                Text(
                    text = "Geri Dön",
                    style = BbTypography.labelMedium,
                    color = BbColors.TextStrong
                )
            }
        }
    }
}

@Composable
private fun MessageHeroCard() {
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
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Mail,
                    contentDescription = null,
                    tint = BbColors.Primary,
                    modifier = Modifier.size(BbIcon.SizeLg)
                )

                Text(
                    text = "Mesaj Merkezi",
                    style = BbTypography.titleSmall,
                    color = BbColors.Primary
                )
            }

            Text(
                text = "Gelen kutusu",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong
            )

            Text(
                text = "Toptan ve perakende ticaret görüşmelerinizden gelen mesajları buradan takip edin.",
                style = BbTypography.bodyMedium,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun MessageStatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        MessageStatCard(
            modifier = Modifier.weight(1f),
            iconType = MessageStatIconType.Inbox,
            value = "12",
            label = "Gelen"
        )

        MessageStatCard(
            modifier = Modifier.weight(1f),
            iconType = MessageStatIconType.Unread,
            value = "3",
            label = "Okunmamış"
        )

        MessageStatCard(
            modifier = Modifier.weight(1f),
            iconType = MessageStatIconType.Read,
            value = "9",
            label = "Okundu"
        )
    }
}

@Composable
private fun MessageStatCard(
    modifier: Modifier,
    iconType: MessageStatIconType,
    value: String,
    label: String
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Icon(
                imageVector = when (iconType) {
                    MessageStatIconType.Inbox -> Icons.Outlined.Mail
                    MessageStatIconType.Unread -> Icons.Outlined.MarkEmailUnread
                    MessageStatIconType.Read -> Icons.Outlined.MarkEmailRead
                },
                contentDescription = null,
                tint = BbColors.Primary,
                modifier = Modifier.size(BbIcon.SizeLg)
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                color = BbColors.TextStrong
            )

            Text(
                text = label,
                style = BbTypography.labelMedium,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun MessageFilterChips() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        MessageFilterChip("Tümü")
        MessageFilterChip("Okunmamış")
        MessageFilterChip("Okundu")
        MessageFilterChip("Toptan")
        MessageFilterChip("Perakende")
        MessageFilterChip("RFQ")
        MessageFilterChip("Firma")
    }
}

@Composable
private fun MessageFilterChip(
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                color = BbColors.SurfaceMuted,
                shape = BbRadius.PillShape
            )
            .padding(
                horizontal = BbSpacing.Space4,
                vertical = BbSpacing.Space2
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = BbTypography.labelMedium,
            color = BbColors.TextSubtle
        )
    }
}

@Composable
private fun MessageSectionTitle() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = "Gelen kutusu",
            style = MaterialTheme.typography.titleLarge,
            color = BbColors.TextStrong
        )

        Text(
            text = "Okunmamış ve okunmuş mesajlarınızı konuşma bazlı görüntüleyin",
            style = BbTypography.bodySmall,
            color = BbColors.TextMuted
        )
    }
}

@Composable
private fun MessageCard(
    message: MessageInboxUiModel,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large,
        onClick = onClick
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
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxMd)
                        .background(
                            color = if (message.isCompany) {
                                BbColors.Yellow.Yellow100
                            } else {
                                BbColors.SurfaceMuted
                            },
                            shape = BbRadius.PillShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (message.isCompany) {
                            Icons.Outlined.Business
                        } else {
                            Icons.Outlined.Person
                        },
                        contentDescription = null,
                        tint = BbColors.Primary,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = message.senderName,
                            style = MaterialTheme.typography.titleMedium,
                            color = BbColors.TextStrong,
                            modifier = Modifier.weight(1f)
                        )

                        MessageStatusBadge(
                            text = if (message.isUnread) "Yeni" else "Okundu",
                            isUnread = message.isUnread
                        )
                    }

                    Text(
                        text = message.subject,
                        style = BbTypography.titleSmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = message.preview,
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )

                    Text(
                        text = message.dateText,
                        style = BbTypography.labelMedium,
                        color = BbColors.TextSubtle
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = BbColors.TextMuted,
                    modifier = Modifier.size(BbIcon.SizeLg)
                )
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                MessageSmallTag(message.boxLabel)
                MessageSmallTag(message.commerceMode)
                MessageSmallTag(message.replyCountText)
            }
        }
    }
}

@Composable
private fun MessageStatusBadge(
    text: String,
    isUnread: Boolean
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isUnread) BbColors.Primary else BbColors.SurfaceMuted,
                shape = BbRadius.PillShape
            )
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = BbTypography.labelMedium,
            color = if (isUnread) BbColors.TextStrong else BbColors.TextSubtle
        )
    }
}

@Composable
private fun MessageSmallTag(
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                color = BbColors.SurfaceMuted,
                shape = BbRadius.PillShape
            )
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = BbColors.TextMuted
        )
    }
}

private enum class MessageStatIconType {
    Inbox,
    Unread,
    Read
}

private data class MessageInboxUiModel(
    val id: Int,
    val senderName: String,
    val subject: String,
    val preview: String,
    val dateText: String,
    val boxLabel: String,
    val commerceMode: String,
    val replyCountText: String,
    val isUnread: Boolean,
    val isCompany: Boolean
)

private fun getDemoMessages(): List<MessageInboxUiModel> {
    return listOf(
        MessageInboxUiModel(
            id = 1,
            senderName = "Murat Erkan",
            subject = "450W paneller için fiyat teklifi",
            preview = "Selamlar, Draugr Network üzerindeki 450W panellerden 200 adetlik bir proje için fiyat teklifi rica ediyorum.",
            dateText = "10.05.2026 13:37",
            boxLabel = "Gelen Kutusu",
            commerceMode = "Toptan",
            replyCountText = "2 yanıt",
            isUnread = false,
            isCompany = false
        ),
        MessageInboxUiModel(
            id = 2,
            senderName = "Anadolu Ambalaj Sanayi",
            subject = "Numune talebi hakkında",
            preview = "Ambalaj ürünleri için gönderdiğiniz RFQ talebine istinaden numune ve fiyat bilgilerini paylaşmak isteriz.",
            dateText = "11.05.2026 09:20",
            boxLabel = "Gelen Kutusu",
            commerceMode = "RFQ",
            replyCountText = "1 yanıt",
            isUnread = true,
            isCompany = true
        ),
        MessageInboxUiModel(
            id = 3,
            senderName = "Moda Nova",
            subject = "Sipariş mesajı",
            preview = "Perakende siparişinizle ilgili kargo ve teslimat bilgileri güncellendi.",
            dateText = "12.05.2026 16:48",
            boxLabel = "Gelen Kutusu",
            commerceMode = "Perakende",
            replyCountText = "3 yanıt",
            isUnread = true,
            isCompany = true
        )
    )
}