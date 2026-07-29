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
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.SendedOfferDTO

@Composable
fun RfqOffersScreen(
    buyerRequestKey: String,
    offers: List<SendedOfferDTO>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onOfferClick: (Int) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Gelen Teklifler",
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
                RfqOffersSummaryCard(
                    buyerRequestKey = buyerRequestKey,
                    offerCount = offers.size
                )
            }

            when {
                isLoading && offers.isEmpty() -> {
                    item {
                        RfqOffersLoadingCard()
                    }
                }

                !errorMessage.isNullOrBlank() && offers.isEmpty() -> {
                    item {
                        RfqOffersErrorCard(
                            message = errorMessage,
                            onRetryClick = onRetryClick
                        )
                    }
                }

                offers.isEmpty() -> {
                    item {
                        RfqOffersEmptyCard()
                    }
                }

                else -> {
                    items(
                        items = offers,
                        key = { offer -> offer.SendedOfferId }
                    ) { offer ->
                        RfqOfferListCard(
                            offer = offer,
                            onOfferClick = onOfferClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RfqOffersSummaryCard(
    buyerRequestKey: String,
    offerCount: Int
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.MdShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalOffer,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Teklifler",
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "RFQ: $buyerRequestKey",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = offerCount.toString(),
                style = BbTypography.titleMedium,
                color = BBColors.Yellow.Yellow800
            )
        }
    }
}

@Composable
private fun RfqOfferListCard(
    offer: SendedOfferDTO,
    onOfferClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            onOfferClick(offer.SendedOfferId)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BBRadius.MdShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Handshake,
                        contentDescription = null,
                        tint = BBColors.Yellow.Yellow800,
                        modifier = Modifier.size(BBIcon.Section)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = offer.Seller.orEmpty().ifBlank { "Satıcı" },
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Teklif No: ${offer.SendedOfferId}",
                        style = BbTypography.bodySmall,
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

            RfqOfferListInfoRow(
                icon = Icons.Outlined.Business,
                title = "Satıcı",
                value = offer.Seller.orEmpty().ifBlank { "-" }
            )

            RfqOfferListInfoRow(
                icon = Icons.Outlined.CalendarMonth,
                title = "Gönderim Tarihi",
                value = offer.InsertedDate.ifBlank { "-" }
            )

            if (offer.OfferDetail.isNotBlank()) {
                Text(
                    text = offer.OfferDetail,
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun RfqOfferListInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BBIcon.Ui)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun RfqOffersLoadingCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Teklifler yükleniyor...",
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RfqOffersErrorCard(
    message: String,
    onRetryClick: () -> Unit
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
            Text(
                text = message,
                style = BbTypography.bodySmall,
                color = BBColors.Red.Red600
            )

            BbButton(
                text = "Tekrar Dene",
                onClick = onRetryClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Outline,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun RfqOffersEmptyCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalOffer,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Text(
                text = "Henüz Teklif Yok",
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bu fiyat teklifi isteğine henüz satıcı teklifi gönderilmedi.",
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}