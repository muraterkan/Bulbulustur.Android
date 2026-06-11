package com.bulbulustur.android.features.wholesale.rfq

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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigation
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigationItem
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun RfqListScreen(
    onBackClick: () -> Unit = {},
    onDiscoverWholesaleClick: () -> Unit = {},
    onOffersClick: (Int) -> Unit = {},
    onDetailClick: (Int) -> Unit = {},
    onDeleteClick: (Int) -> Unit = {},
    onCreateRfqClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    val rfqRequests = getDemoRfqRequests()

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Basket,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        WholesaleBottomNavigationItem.Home -> onHomeClick()
                        WholesaleBottomNavigationItem.Menu -> onMenuClick()
                        WholesaleBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        WholesaleBottomNavigationItem.Basket -> onBasketClick()
                        WholesaleBottomNavigationItem.Account -> onAccountClick()
                    }
                }
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
                RfqPageHeader(
                    onBackClick = onBackClick,
                    onCreateRfqClick = onCreateRfqClick
                )
            }

            if (rfqRequests.isEmpty()) {
                item {
                    RfqEmptyState(
                        onDiscoverWholesaleClick = onDiscoverWholesaleClick
                    )
                }
            } else {
                items(
                    items = rfqRequests,
                    key = { item ->
                        item.buyerRequestId
                    }
                ) { item ->
                    RfqRequestCard(
                        item = item,
                        onOffersClick = onOffersClick,
                        onDetailClick = onDetailClick,
                        onDeleteClick = onDeleteClick
                    )
                }
            }
        }
    }
}

@Composable
private fun RfqPageHeader(
    onBackClick: () -> Unit,
    onCreateRfqClick: () -> Unit
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                RfqIconBox()

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "RFQ Yönetimi",
                        style = BbTypography.labelSmall,
                        color = BbColors.Yellow.Yellow800
                    )

                    Text(
                        text = "Fiyat Teklifi İstekleri",
                        style = MaterialTheme.typography.titleLarge,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = "Oluşturduğunuz fiyat teklifi isteklerini görüntüleyin, detaylarını inceleyin veya gelen tekliflere ulaşın.",
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "Toptana Dön",
                    onClick = onBackClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = "RFQ Oluştur",
                    onClick = onCreateRfqClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small
                )
            }
        }
    }
}

@Composable
private fun RfqRequestCard(
    item: RfqRequestUiModel,
    onOffersClick: (Int) -> Unit,
    onDetailClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BbColors.Surface)
        ) {
            RfqRequestCardHeader(
                item = item
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                RfqMetaBox(
                    buyerRequestId = item.buyerRequestId
                )

                Text(
                    text = item.description,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    BbButton(
                        text = "Teklifler",
                        onClick = {
                            onOffersClick(item.buyerRequestId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.LocalOffer,
                                contentDescription = null,
                                tint = BbColors.TextStrong,
                                modifier = Modifier.size(BbIcon.ButtonIcon)
                            )
                        }
                    )

                    BbButton(
                        text = "Detay",
                        onClick = {
                            onDetailClick(item.buyerRequestId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Visibility,
                                contentDescription = null,
                                tint = BbColors.TextStrong,
                                modifier = Modifier.size(BbIcon.ButtonIcon)
                            )
                        }
                    )
                }

                BbButton(
                    text = "Sil",
                    onClick = {
                        onDeleteClick(item.buyerRequestId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Danger,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = BbColors.White,
                            modifier = Modifier.size(BbIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun RfqRequestCardHeader(
    item: RfqRequestUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Yellow.Yellow50)
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RfqIconBox()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "FİYAT TEKLİFİ İSTEĞİ",
                style = BbTypography.labelSmall,
                color = BbColors.Yellow.Yellow800
            )

            Text(
                text = item.productName,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong
            )

            Text(
                text = item.createdDate,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun RfqMetaBox(
    buyerRequestId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.SurfaceMuted,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Tag,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.Ui)
        )

        Text(
            text = "RFQ No: $buyerRequestId",
            style = BbTypography.bodyMedium,
            color = BbColors.TextStrong
        )
    }
}

@Composable
private fun RfqEmptyState(
    onDiscoverWholesaleClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            RfqIconBox()

            Box(
                modifier = Modifier
                    .background(
                        color = BbColors.Yellow.Yellow100,
                        shape = BbRadius.Badge
                    )
                    .padding(
                        horizontal = BbSpacing.BadgePaddingHorizontal,
                        vertical = BbSpacing.BadgePaddingVertical
                    )
            ) {
                Text(
                    text = "RFQ Yok",
                    style = BbTypography.labelSmall,
                    color = BbColors.Yellow.Yellow800
                )
            }

            Text(
                text = "Henüz bir fiyat teklifi isteği oluşturmadınız!",
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            Text(
                text = "Tedarikçilerden fiyat teklifi alarak ihtiyaçlarınıza en uygun çözümleri keşfedin.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )

            BbButton(
                text = "Toptan Ürünleri Keşfet",
                onClick = onDiscoverWholesaleClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun RfqIconBox() {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxLg)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.RequestQuote,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.Section)
        )
    }
}

private fun getDemoRfqRequests(): List<RfqRequestUiModel> {
    return listOf(
        RfqRequestUiModel(
            buyerRequestId = 1001,
            productName = "Endüstriyel ambalaj ürünleri için teklif",
            createdDate = "10 Mayıs 2026",
            description = "Bu kayıt için gelen teklifleri inceleyebilir veya istek detayına geçebilirsiniz."
        ),
        RfqRequestUiModel(
            buyerRequestId = 1002,
            productName = "Toptan ayakkabı alımı",
            createdDate = "12 Mayıs 2026",
            description = "Tedarikçilerden gelen fiyat tekliflerini karşılaştırabilirsiniz."
        )
    )
}

private data class RfqRequestUiModel(
    val buyerRequestId: Int,
    val productName: String,
    val createdDate: String,
    val description: String
)