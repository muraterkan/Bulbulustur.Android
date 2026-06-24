package com.bulbulustur.android.Application.Areas.b2b.Views.Rfq

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha

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
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Fiyat Teklifi İstekleri",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Add,
                actionContentDescription = "Teklif İste",
                onActionClick = onCreateRfqClick
            )
        },
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
                RfqCreateActionCard(
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
                    key = { item -> item.buyerRequestId }
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
private fun RfqCreateActionCard(
    onCreateRfqClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onCreateRfqClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RfqIconBox(
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Yeni Teklif İste",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Ürün, miktar ve ticari koşulları belirterek tedarikçilerden teklif alın.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Action)
            )
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onDetailClick(item.buyerRequestId)
            },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                RfqIconBox(
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                        ) {
                            Text(
                                text = item.productName,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                text = item.createdDate,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        RfqStatusBadge(
                            text = item.statusText,
                            color = item.statusColor
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RfqSmallChip(
                            icon = Icons.Outlined.Tag,
                            text = "RFQ #${item.buyerRequestId}"
                        )

                        RfqSmallChip(
                            icon = Icons.Outlined.LocalOffer,
                            text = item.offerCountText
                        )
                    }
                }
            }

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
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
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )
            }

            BbButton(
                text = "RFQ Kaydını Sil",
                onClick = {
                    onDeleteClick(item.buyerRequestId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Ghost,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqStatusBadge(
    text: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .background(
                color = color.copy(alpha = BBAlpha.Overlay),
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@Composable
private fun RfqSmallChip(
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.SizeSm)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            RfqIconBox(
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Text(
                text = "Henüz Teklif İstemadınız",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Tedarikçilerden fiyat teklifi alarak ihtiyaçlarınıza en uygun çözümleri Keşfedin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
private fun RfqIconBox(
    backgroundColor: Color,
    iconColor: Color
) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxLg)
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.RequestQuote,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Section)
        )
    }
}

private fun getDemoRfqRequests(): List<RfqRequestUiModel> {
    return listOf(
        RfqRequestUiModel(
            buyerRequestId = 1001,
            productName = "Endüstriyel ambalaj ürünleri için teklif",
            createdDate = "10 Mayıs 2026",
            description = "Bu kayıt için gelen teklifleri inceleyebilir veya istek detayına geçebilirsiniz.",
            offerCount = 2,
            statusText = "Açık",
            statusColor = BBColors.Blue.Blue600
        ),
        RfqRequestUiModel(
            buyerRequestId = 1002,
            productName = "Toptan ayakkabı alımı",
            createdDate = "12 Mayıs 2026",
            description = "Tedarikçilerden gelen fiyat tekliflerini karşılaştırabilirsiniz.",
            offerCount = 0,
            statusText = "Beklemede",
            statusColor = BBColors.Orange.Orange600
        )
    )
}

private val RfqRequestUiModel.offerCountText: String
    get() = if (offerCount > 0) {
        "$offerCount Teklif"
    } else {
        "Teklif Yok"
    }

private data class RfqRequestUiModel(
    val buyerRequestId: Int,
    val productName: String,
    val createdDate: String,
    val description: String,
    val offerCount: Int,
    val statusText: String,
    val statusColor: Color
)

