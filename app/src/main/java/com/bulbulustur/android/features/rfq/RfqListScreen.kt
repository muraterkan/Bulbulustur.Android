package com.bulbulustur.android.features.rfq

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
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
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
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun RfqListScreen(
    onRfqClick: (Int) -> Unit = {},
    onRfqCreateClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val rfqItems = remember {
        getRfqListItems()
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
                RfqListHeader(
                    onRfqCreateClick = onRfqCreateClick
                )
            }

            item {

            }

            item {
                RfqStatusChips()
            }

            item {
                BbSectionHeader(
                    title = "Teklif talepleri",
                    subtitle = "Gönderilen, yanıt bekleyen ve tamamlanan RFQ kayıtları"
                )
            }

            items(
                items = rfqItems,
                key = { rfq ->
                    rfq.rfqId
                }
            ) { rfq ->
                RfqListCard(
                    rfq = rfq,
                    onClick = {
                        onRfqClick(rfq.rfqId)
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
private fun RfqListHeader(
    onRfqCreateClick: () -> Unit
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
            RfqIconTitleRow(
                icon = Icons.Outlined.RequestQuote,
                title = "RFQ"
            )

            Text(
                text = "Teklif taleplerim",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Toptan ürün, numune, özel üretim veya son fiyat taleplerini buradan takip et.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Yeni teklif talebi oluştur",
                onClick = onRfqCreateClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RfqStatusChips() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
    ) {
        getRfqStatusNames().forEach { statusName ->
            BbChip(
                text = statusName,
                selected = false,
                onClick = {}
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RfqListCard(
    rfq: RfqListItem,
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
                    imageVector = rfq.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = rfq.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = rfq.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "${rfq.categoryName} • ${rfq.insertedDate}",
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
                    text = rfq.statusName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "${rfq.offerCount} teklif",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = rfq.requestTypeName,
                    selected = false,
                    onClick = {}
                )
            }

            RfqMetaRow(
                rfq = rfq
            )
        }
    }
}

@Composable
private fun RfqMetaRow(
    rfq: RfqListItem
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
    ) {
        RfqMetaBox(
            title = "Miktar",
            value = rfq.quantityLabel,
            icon = Icons.Outlined.Inventory2,
            modifier = Modifier.weight(1f)
        )

        RfqMetaBox(
            title = "Son tarih",
            value = rfq.deadlineLabel,
            icon = Icons.Outlined.Schedule,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun RfqMetaBox(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = value,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun RfqIconTitleRow(
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

data class RfqListItem(
    val rfqId: Int,
    val title: String,
    val description: String,
    val categoryName: String,
    val insertedDate: String,
    val statusName: String,
    val requestTypeName: String,
    val quantityLabel: String,
    val deadlineLabel: String,
    val offerCount: Int,
    val icon: ImageVector
)

private fun getRfqStatusNames(): List<String> {
    return listOf(
        "Tümü",
        "Yanıt bekleyen",
        "Teklif geldi",
        "Tamamlandı",
        "Taslak"
    )
}

private fun getRfqListItems(): List<RfqListItem> {
    return listOf(
        RfqListItem(
            rfqId = 1,
            title = "E-ticaret kolisi için teklif talebi",
            description = "1000 adet için son fiyat ve teslimat şartları isteniyor.",
            categoryName = "Ambalaj",
            insertedDate = "Bugün",
            statusName = "Teklif geldi",
            requestTypeName = "Son fiyat",
            quantityLabel = "1000 adet",
            deadlineLabel = "3 gün",
            offerCount = 4,
            icon = Icons.Outlined.RequestQuote
        ),
        RfqListItem(
            rfqId = 2,
            title = "Logo baskılı poşet üretimi",
            description = "Özel ölçü, renk ve logo baskılı üretim talebi.",
            categoryName = "Ambalaj",
            insertedDate = "Dün",
            statusName = "Yanıt bekleyen",
            requestTypeName = "Özelleştirme",
            quantityLabel = "5000 adet",
            deadlineLabel = "7 gün",
            offerCount = 0,
            icon = Icons.Outlined.Category
        ),
        RfqListItem(
            rfqId = 3,
            title = "Numune koli talebi",
            description = "Ürün kalitesini görmek için numune gönderimi isteniyor.",
            categoryName = "Ambalaj",
            insertedDate = "2 gün önce",
            statusName = "Tamamlandı",
            requestTypeName = "Numune",
            quantityLabel = "1 adet",
            deadlineLabel = "Kapandı",
            offerCount = 1,
            icon = Icons.Outlined.Verified
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun RfqListScreenPreview() {
    BbTheme {
        RfqListScreen()
    }
}