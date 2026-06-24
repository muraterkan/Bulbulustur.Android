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
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            RfqOfferIconBox(
                icon = Icons.Outlined.Handshake
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
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "RFQ No: ${offer.buyerRequestId} Â· Teklif No: ${offer.sendedOfferId}",
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
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
                title = "BaĞlı RFQ",
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.RequestQuote,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBIcon.Ui)
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
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
                        modifier = Modifier.size(BBIcon.ButtonIcon)
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
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqOfferInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RfqOfferIconBox(icon = icon)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
            .background(
                color = BBColors.Yellow.Yellow100,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Action)
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
        offerDetail = "BelirttiĞiniz ambalaj ürünleri için 10.000 adet üretim kapasitemiz bulunmaktadır. Teslim süresi yaklaşık 12 iş günüdür. Ödeme ve sevkiyat koşulları görüşmeye açıktır."
    )
}

private data class RfqOfferDetailUiModel(
    val buyerRequestId: Int,
    val sendedOfferId: Int,
    val sellerName: String,
    val insertedDate: String,
    val offerDetail: String
)

