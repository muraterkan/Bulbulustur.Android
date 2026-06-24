package com.bulbulustur.android.Application.Areas.b2b.Views.Rfq

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
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Schedule
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
fun RfqDetailScreen(
    buyerRequestId: Int,
    onBackClick: () -> Unit = {},
    onOfferClick: (Int) -> Unit = {},
    onCreateRfqClick: () -> Unit = {}
) {
    val rfq = getDemoRfqDetail(
        buyerRequestId = buyerRequestId
    )

    Scaffold(
        containerColor = BBColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "RFQ Detayı",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BBColors.SurfaceMuted)
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
                RfqDetailSummaryCard(
                    rfq = rfq,
                    onCreateRfqClick = onCreateRfqClick
                )
            }

            item {
                RfqDetailStatGrid(rfq = rfq)
            }

            item {
                RfqDetailDescriptionCard(rfq = rfq)
            }

            item {
                RfqDetailTradeCard(rfq = rfq)
            }

            item {
                RfqDetailDeliveryCard(rfq = rfq)
            }

            item {
                RfqOffersSectionHeader(
                    offerCount = rfq.offers.size
                )
            }

            if (rfq.offers.isEmpty()) {
                item {
                    RfqOffersEmptyCard()
                }
            } else {
                items(
                    items = rfq.offers,
                    key = { offer -> offer.sendedOfferId }
                ) { offer ->
                    RfqOfferCard(
                        offer = offer,
                        onOfferClick = onOfferClick
                    )
                }
            }
        }
    }
}

@Composable
private fun RfqDetailSummaryCard(
    rfq: RfqDetailUiModel,
    onCreateRfqClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
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
                RfqDetailIconBox(
                    icon = Icons.Outlined.RequestQuote,
                    backgroundColor = BBColors.Yellow.Yellow100,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "RFQ No: ${rfq.buyerRequestId}",
                        style = MaterialTheme.typography.labelSmall,
                        color = BBColors.Yellow.Yellow800
                    )

                    Text(
                        text = rfq.productName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Fiyat teklifi isteĞinizin ürün, miktar, ödeme, teslimat ve teklif detaylarını inceleyin.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                RfqStatusBadge(
                    text = rfq.statusText,
                    color = rfq.statusColor
                )
            }

            BbButton(
                text = "Yeni Teklif Ä°ste",
                onClick = onCreateRfqClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.RequestQuote,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqDetailStatGrid(
    rfq: RfqDetailUiModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            RfqStatBox(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Inventory2,
                title = "Talep Miktarı",
                value = rfq.purchaseQuantityText
            )

            RfqStatBox(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Payments,
                title = "Hedef Fiyat",
                value = rfq.unitPriceText
            )
        }

        RfqStatBox(
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Outlined.Schedule,
            title = "Son Talep Tarihi",
            value = rfq.lastRequestDateText
        )
    }
}

@Composable
private fun RfqDetailDescriptionCard(
    rfq: RfqDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            RfqSectionTitle(
                icon = Icons.Outlined.Article,
                title = "Ürün ve Talep Açıklaması",
                subtitle = "Talebin ürün adı, açıklaması ve kategori bilgileri."
            )

            RfqInfoRow(
                title = "Ürün",
                value = rfq.productName
            )

            HorizontalDivider(color = BBColors.Border)

            RfqInfoRow(
                title = "Kategori",
                value = rfq.categoryName
            )

            HorizontalDivider(color = BBColors.Border)

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Açıklama",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = rfq.productDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun RfqDetailTradeCard(
    rfq: RfqDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            RfqSectionTitle(
                icon = Icons.Outlined.Handshake,
                title = "Ticaret Koşulları",
                subtitle = "RFQ için belirlenen ticaret ve ödeme bilgileri."
            )

            RfqInfoRow(
                title = "Ticaret Åartı",
                value = rfq.tradeTermName
            )

            HorizontalDivider(color = BBColors.Border)

            RfqInfoRow(
                title = "Ã–deme Åartı",
                value = rfq.paymentTermName
            )

            HorizontalDivider(color = BBColors.Border)

            RfqInfoRow(
                title = "Malzeme",
                value = rfq.materialTypeName
            )
        }
    }
}

@Composable
private fun RfqDetailDeliveryCard(
    rfq: RfqDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            RfqSectionTitle(
                icon = Icons.Outlined.LocalShipping,
                title = "Miktar ve Teslimat",
                subtitle = "Satın alma miktarı, fiyat ve teslimat hedefi."
            )

            RfqInfoRow(
                title = "Miktar",
                value = rfq.purchaseQuantityText
            )

            HorizontalDivider(color = BBColors.Border)

            RfqInfoRow(
                title = "Hedef Birim Fiyat",
                value = rfq.unitPriceText
            )

            HorizontalDivider(color = BBColors.Border)

            RfqInfoRow(
                title = "Nakliye Hedefi",
                value = rfq.shippingTarget
            )
        }
    }
}

@Composable
private fun RfqOffersSectionHeader(
    offerCount: Int
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Default,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RfqDetailIconBox(
                icon = Icons.Outlined.LocalOffer,
                backgroundColor = BBColors.Yellow.Yellow100,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Gelen Teklifler",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = if (offerCount > 0) {
                        "$offerCount satıcı teklif gönderdi."
                    } else {
                        "Bu RFQ için henüz teklif gelmedi."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun RfqOfferCard(
    offer: RfqOfferUiModel,
    onOfferClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
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
                RfqDetailIconBox(
                    icon = Icons.Outlined.Handshake,
                    backgroundColor = BBColors.Yellow.Yellow100,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "Satıcı Teklifi",
                        style = MaterialTheme.typography.labelSmall,
                        color = BBColors.Yellow.Yellow800
                    )

                    Text(
                        text = offer.sellerName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = offer.offerDetail,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            RfqOfferMetaRow(offer = offer)

            BbButton(
                text = "Teklif Detayını Gör",
                onClick = {
                    onOfferClick(offer.sendedOfferId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Visibility,
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
private fun RfqOfferMetaRow(
    offer: RfqOfferUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.CalendarMonth,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Ui)
        )

        Text(
            text = offer.insertedDate,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RfqOffersEmptyCard() {
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
            RfqDetailIconBox(
                icon = Icons.Outlined.LocalOffer,
                backgroundColor = BBColors.Yellow.Yellow100,
                iconColor = BBColors.Yellow.Yellow800
            )

            Text(
                text = "Teklif Yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bu fiyat teklifi isteĞi için henüz gönderilmiş teklif bulunmuyor.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RfqSectionTitle(
    icon: ImageVector,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        RfqDetailIconBox(
            icon = icon,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurface
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RfqInfoRow(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            modifier = Modifier.weight(1.25f),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun RfqStatBox(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    value: String
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            RfqDetailIconBox(
                icon = icon,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
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
private fun RfqDetailIconBox(
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color
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

private fun getDemoRfqDetail(
    buyerRequestId: Int
): RfqDetailUiModel {
    return RfqDetailUiModel(
        buyerRequestId = buyerRequestId,
        productName = "Endüstriyel ambalaj ürünleri için teklif",
        statusText = "Açık",
        statusColor = BBColors.Blue.Blue600,
        purchaseQuantityText = "10.000 Adet",
        unitPriceText = "8,75 TL",
        lastRequestDateText = "20 Mayıs 2026",
        productDescription = "Gıda ambalajı için kullanılacak, baskıya uygun, dayanıklı ve seri üretime uygun ambalaj ürünleri için fiyat teklifi beklenmektedir.",
        categoryName = "Ambalaj ve Paketleme",
        tradeTermName = "FOB",
        paymentTermName = "Vadeli / Görüşülebilir",
        materialTypeName = "Karton / Kraft",
        shippingTarget = "Türkiye / Ä°stanbul / Ambarlı Port",
        offers = listOf(
            RfqOfferUiModel(
                sendedOfferId = 501,
                sellerName = "Anadolu Endüstriyel Tedarik",
                offerDetail = "10.000 adet için 12 iş günü teslim süresiyle fiyat teklifi sunabiliriz.",
                insertedDate = "14 Mayıs 2026"
            ),
            RfqOfferUiModel(
                sendedOfferId = 502,
                sellerName = "Marmara Ambalaj Sanayi",
                offerDetail = "Talep edilen ambalaj ürünleri için alternatif gramaj seçenekleriyle teklif verebiliriz.",
                insertedDate = "15 Mayıs 2026"
            )
        )
    )
}

private data class RfqDetailUiModel(
    val buyerRequestId: Int,
    val productName: String,
    val statusText: String,
    val statusColor: Color,
    val purchaseQuantityText: String,
    val unitPriceText: String,
    val lastRequestDateText: String,
    val productDescription: String,
    val categoryName: String,
    val tradeTermName: String,
    val paymentTermName: String,
    val materialTypeName: String,
    val shippingTarget: String,
    val offers: List<RfqOfferUiModel>
)

private data class RfqOfferUiModel(
    val sendedOfferId: Int,
    val sellerName: String,
    val offerDetail: String,
    val insertedDate: String
)

