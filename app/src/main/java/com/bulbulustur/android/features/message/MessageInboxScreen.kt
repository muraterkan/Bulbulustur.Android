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
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.MarkEmailRead
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Storefront
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
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSearchBar
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun MessageInboxScreen(
    onMessageClick: (Int) -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val messages = remember {
        getMessageInboxItems()
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
                MessageInboxHeader()
            }

            item {
                MessageInboxSummary()
            }

            item {
                BbSearchBar(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearchClick(it)
                    },
                    placeholder = "Mesaj, firma veya konu ara"
                )
            }

            item {
                MessageInboxFilterChips()
            }

            item {
                BbSectionHeader(
                    title = "Gelen kutusu",
                    subtitle = "Okunmamış ve okunmuş mesajlarınızı konuşma bazlı görüntüleyin"
                )
            }

            items(
                items = messages,
                key = { message ->
                    message.messageId
                }
            ) { message ->
                MessageInboxCard(
                    message = message,
                    onClick = {
                        onMessageClick(message.messageId)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(BbSpacing.Space4))
            }
        }
    }
}

@Composable
private fun MessageInboxHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            MessageIconTitleRow(
                icon = Icons.Outlined.Mail,
                title = "Mesaj Merkezi"
            )

            Text(
                text = "Gelen kutusu",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Toptan ve perakende ticaret görüşmelerinizden gelen mesajları buradan takip edin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun MessageInboxSummary() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
    ) {
        MessageSummaryCard(
            title = "Gelen",
            value = "12",
            icon = Icons.Outlined.Inbox,
            modifier = Modifier.weight(1f)
        )

        MessageSummaryCard(
            title = "Okunmamış",
            value = "3",
            icon = Icons.Outlined.Mail,
            modifier = Modifier.weight(1f)
        )

        MessageSummaryCard(
            title = "Okundu",
            value = "9",
            icon = Icons.Outlined.MarkEmailRead,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun MessageSummaryCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageInboxFilterChips() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
    ) {
        getMessageFilterNames().forEach { filterName ->
            BbChip(
                text = filterName,
                selected = false,
                onClick = {}
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageInboxCard(
    message: MessageInboxItem,
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
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Icon(
                    imageVector = message.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
                    ) {
                        Text(
                            text = message.senderName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        BbChip(
                            text = if (message.isRead) {
                                "Okundu"
                            } else {
                                "Yeni"
                            },
                            selected = !message.isRead,
                            onClick = {}
                        )
                    }

                    Text(
                        text = message.subject,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = message.preview,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = message.sentAt,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
            ) {
                BbChip(
                    text = message.messageTypeName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = message.commerceModeName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "${message.replyCount} yanıt",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun MessageIconTitleRow(
    icon: ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

data class MessageInboxItem(
    val messageId: Int,
    val senderName: String,
    val subject: String,
    val preview: String,
    val sentAt: String,
    val messageTypeName: String,
    val commerceModeName: String,
    val replyCount: Int,
    val isRead: Boolean,
    val icon: ImageVector
)

private fun getMessageFilterNames(): List<String> {
    return listOf(
        "Tümü",
        "Okunmamış",
        "Okundu",
        "Toptan",
        "Perakende",
        "RFQ",
        "Firma"
    )
}

private fun getMessageInboxItems(): List<MessageInboxItem> {
    return listOf(
        MessageInboxItem(
            messageId = 1,
            senderName = "Murat Erkan",
            subject = "450W paneller için fiyat teklifi",
            preview = "Selamlar, Draugr Network üzerindeki 450W panellerden 200 adetlik bir proje için fiyat teklifi rica ediyorum.",
            sentAt = "10.05.2026 13:37",
            messageTypeName = "Gelen Kutusu",
            commerceModeName = "Toptan",
            replyCount = 2,
            isRead = true,
            icon = Icons.Outlined.Person
        ),
        MessageInboxItem(
            messageId = 2,
            senderName = "Anadolu Ambalaj Sanayi",
            subject = "Numune talebiniz hakkında",
            preview = "Talep ettiğiniz numune için teslimat ve kargo bilgilerini paylaşabilir misiniz?",
            sentAt = "Bugün 11:20",
            messageTypeName = "Numune",
            commerceModeName = "Toptan",
            replyCount = 1,
            isRead = false,
            icon = Icons.Outlined.Business
        ),
        MessageInboxItem(
            messageId = 3,
            senderName = "Marmara Tedarik Merkezi",
            subject = "Son fiyat talebi yanıtı",
            preview = "Belirttiğiniz miktara göre fiyat teklifimizi güncelledik.",
            sentAt = "Dün 18:45",
            messageTypeName = "Son Fiyat",
            commerceModeName = "Toptan",
            replyCount = 4,
            isRead = false,
            icon = Icons.Outlined.RequestQuote
        ),
        MessageInboxItem(
            messageId = 4,
            senderName = "Nexa Store",
            subject = "Sipariş bilgilendirmesi",
            preview = "Perakende siparişinizle ilgili satıcı mesajı bulunmaktadır.",
            sentAt = "2 gün önce",
            messageTypeName = "Sipariş",
            commerceModeName = "Perakende",
            replyCount = 1,
            isRead = true,
            icon = Icons.Outlined.Storefront
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun MessageInboxScreenPreview() {
    BbTheme {
        MessageInboxScreen()
    }
}