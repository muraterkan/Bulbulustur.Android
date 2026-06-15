package com.bulbulustur.android.Features.Areas.b2b.rfq

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing

@Composable
fun RfqOfferDetailScreen(
    buyerRequestId: Int,
    sendedOfferId: Int,
    onBackClick: () -> Unit = {},
    onSellerClick: () -> Unit = {},
    onMessageClick: () -> Unit = {}
) {
    val offer = getDemoRfqOfferDetail(
        buyerRequestId = buyerRequestId,
        sendedOfferId = sendedOfferId
    )

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Teklif Detayı",
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
                RfqOfferSummaryCard(offer = offer)
            }

            item {
                RfqOfferInfoCard(offer = offer)
            }

            item {
                RfqOfferMessageCard(offer = offer)
            }

            item {
                RfqOfferActionCard(
                    onSellerClick = onSellerClick,
                    onMessageClick = onMessageClick
                )
            }
        }
    }
}

@Composable
private fun RfqOfferSummaryCard(
    offer: RfqOfferDetailUiModel
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
            RfqOfferIconBox(
                icon = Icons.Outlined.Handshake
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Satıcı Teklifi",
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.Yellow.Yellow800
                )

                Text(
                    text = offer.sellerName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "RFQ No: ${offer.buyerRequestId} · Teklif No: ${offer.sendedOfferId}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun RfqOfferInfoCard(
    offer: RfqOfferDetailUiModel
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
            RfqOfferInfoRow(
                icon = Icons.Outlined.Business,
                title = "Satıcı",
                value = offer.sellerName
            )

            RfqOfferInfoRow(
                icon = Icons.Outlined.CalendarMonth,
                title = "Gönderim Tarihi",
                value = offer.insertedDate
            )

            RfqOfferInfoRow(
                icon = Icons.Outlined.RequestQuote,
                title = "Bağlı RFQ",
                value = "RFQ-${offer.buyerRequestId}"
            )
        }
    }
}

@Composable
private fun RfqOfferMessageCard(
    offer: RfqOfferDetailUiModel
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
                    imageVector = Icons.Outlined.Description,
                    contentDescription = null,
                    tint = BbColors.Yellow.Yellow800,
                    modifier = Modifier.size(BbIcon.Ui)
                )

                Text(
                    text = "Teklif Açıklaması",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = offer.offerDetail,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RfqOfferActionCard(
    onSellerClick: () -> Unit,
    onMessageClick: () -> Unit
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
            BbButton(
                text = "Satıcıyı Gör",
                onClick = onSellerClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Business,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )

            BbButton(
                text = "Mesaj Gönder",
                onClick = onMessageClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Message,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqOfferInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RfqOfferIconBox(icon = icon)

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
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun RfqOfferIconBox(
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxMd)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.Action)
        )
    }
}

private fun getDemoRfqOfferDetail(
    buyerRequestId: Int,
    sendedOfferId: Int
): RfqOfferDetailUiModel {
    return RfqOfferDetailUiModel(
        buyerRequestId = buyerRequestId,
        sendedOfferId = sendedOfferId,
        sellerName = "Anadolu Endüstriyel Tedarik",
        insertedDate = "14 Mayıs 2026",
        offerDetail = "Belirttiğiniz ambalaj ürünleri için 10.000 adet üretim kapasitemiz bulunmaktadır. Teslim süresi yaklaşık 12 iş günüdür. Ödeme ve sevkiyat koşulları görüşmeye açıktır."
    )
}

private data class RfqOfferDetailUiModel(
    val buyerRequestId: Int,
    val sendedOfferId: Int,
    val sellerName: String,
    val insertedDate: String,
    val offerDetail: String
)