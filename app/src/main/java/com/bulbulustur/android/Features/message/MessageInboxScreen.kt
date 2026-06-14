package com.bulbulustur.android.Features.message

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbAlpha

@Composable
fun MessageInboxScreen(
    onBackClick: () -> Unit = {},
    onMessageClick: (Int) -> Unit = {}
) {
    val selectedFilter = remember {
        mutableStateOf(MessageFilter.All)
    }

    val messages = getDemoMessages().filterBy(selectedFilter.value)

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Mesajlar",
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
                MessageStatsRow()
            }

            item {
                MessageFilterChips(
                    selectedFilter = selectedFilter.value,
                    onFilterClick = { filter ->
                        selectedFilter.value = filter
                    }
                )
            }

            item {
                MessageSectionTitle()
            }

            items(
                items = messages,
                key = { item -> item.id }
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
private fun MessageStatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        MessageStatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.Mail,
            value = "12",
            label = "Gelen",
            color = BbColors.Navy.Navy500,
            backgroundColor = BbColors.Navy.Navy50
        )

        MessageStatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.MarkEmailUnread,
            value = "3",
            label = "Yeni",
            color = BbColors.Blue.Blue600,
            backgroundColor = BbColors.Blue.Blue50
        )

        MessageStatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.MarkEmailRead,
            value = "9",
            label = "Okundu",
            color = BbColors.Green.Green600,
            backgroundColor = BbColors.Green.Green50
        )
    }
}

@Composable
private fun MessageStatCard(
    modifier: Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String,
    color: Color,
    backgroundColor: Color
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                    tint = color,
                    modifier = Modifier.size(BbIcon.Action)
                )
            }

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageFilterChips(
    selectedFilter: MessageFilter,
    onFilterClick: (MessageFilter) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        MessageFilter.entries.forEach { filter ->
            MessageFilterChip(
                text = filter.label,
                selected = selectedFilter == filter,
                onClick = {
                    onFilterClick(filter)
                }
            )
        }
    }
}

@Composable
private fun MessageFilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        variant = if (selected) BbCardVariant.Default else BbCardVariant.Outlined,
        padding = BbCardPadding.Small,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = if (selected) {
                BbColors.Yellow.Yellow800
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
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
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Okunmamış ve okunmuş mesajlarınızı konuşma bazlı görüntüleyin.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageCard(
    message: MessageInboxUiModel,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
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
                MessageAvatarBox(message = message)

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
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )

                        MessageStatusBadge(
                            isUnread = message.isUnread
                        )
                    }

                    Text(
                        text = message.subject,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = message.preview,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = message.dateText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.TextMuted
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BbIcon.Action)
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
private fun MessageAvatarBox(
    message: MessageInboxUiModel
) {
    val backgroundColor = when {
        message.isUnread -> BbColors.Blue.Blue50
        message.isCompany -> BbColors.Yellow.Yellow100
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val iconColor = when {
        message.isUnread -> BbColors.Blue.Blue600
        message.isCompany -> BbColors.Yellow.Yellow800
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

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
            imageVector = if (message.isCompany) Icons.Outlined.Business else Icons.Outlined.Person,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BbIcon.Action)
        )
    }
}

@Composable
private fun MessageStatusBadge(
    isUnread: Boolean
) {
    val color = if (isUnread) BbColors.Blue.Blue600 else BbColors.Green.Green600
    val text = if (isUnread) "Yeni" else "Okundu"

    Box(
        modifier = Modifier
            .background(
                color = color.copy(alpha = BbAlpha.Overlay),
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
            color = color
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

private fun List<MessageInboxUiModel>.filterBy(
    filter: MessageFilter
): List<MessageInboxUiModel> {
    return when (filter) {
        MessageFilter.All -> this
        MessageFilter.Unread -> filter { it.isUnread }
        MessageFilter.Read -> filter { !it.isUnread }
        MessageFilter.Wholesale -> filter { it.commerceMode == "Toptan" || it.commerceMode == "RFQ" }
        MessageFilter.Retail -> filter { it.commerceMode == "Perakende" }
    }
}

private enum class MessageFilter(
    val label: String
) {
    All("Tümü"),
    Unread("Okunmamış"),
    Read("Okundu"),
    Wholesale("Toptan"),
    Retail("Perakende")
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
            preview = "Selamlar, 450W panellerden 200 adetlik proje için fiyat teklifi rica ediyorum.",
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
            preview = "RFQ talebinize istinaden numune ve fiyat bilgilerini paylaşmak isteriz.",
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