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
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestDTO

@Composable
fun RfqDetailScreen(
    buyerRequest: BuyerRequestDTO?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onOffersClick: () -> Unit = {},
    onCreateRfqClick: () -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
            when {
                isLoading && buyerRequest == null -> {
                    item {
                        RfqDetailLoadingCard()
                    }
                }

                !errorMessage.isNullOrBlank() && buyerRequest == null -> {
                    item {
                        RfqDetailErrorCard(
                            message = errorMessage,
                            onRetryClick = onRetryClick
                        )
                    }
                }

                buyerRequest == null -> {
                    item {
                        RfqDetailEmptyCard()
                    }
                }

                else -> {
                    item {
                        RfqDetailSummaryCard(
                            buyerRequest = buyerRequest,
                            onCreateRfqClick = onCreateRfqClick
                        )
                    }

                    item {
                        RfqDetailQuantityCard(
                            buyerRequest = buyerRequest
                        )
                    }

                    item {
                        RfqDetailProductCard(
                            buyerRequest = buyerRequest
                        )
                    }

                    item {
                        RfqDetailTradeCard(
                            buyerRequest = buyerRequest
                        )
                    }

                    item {
                        RfqDetailDeliveryCard(
                            buyerRequest = buyerRequest
                        )
                    }

                    item {
                        RfqDetailOffersCard(
                            onOffersClick = onOffersClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RfqDetailSummaryCard(
    buyerRequest: BuyerRequestDTO,
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
                    icon = Icons.Outlined.RequestQuote
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "RFQ No: ${buyerRequest.BuyerRequestId}",
                        style = BbTypography.labelSmall,
                        color = BBColors.Yellow.Yellow800
                    )

                    Text(
                        text = buyerRequest.ProductName.ifBlank { "Teklif Alği" },
                        style = BbTypography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = buyerRequest.InsertedDate.ifBlank { "-" },
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            BbButton(
                text = "Yeni Teklif Al",
                onClick = onCreateRfqClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.RequestQuote,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqDetailQuantityCard(
    buyerRequest: BuyerRequestDTO
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
            RfqDetailSectionTitle(
                icon = Icons.Outlined.Inventory2,
                title = "Talep Bilgileri"
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.Straighten,
                title = "Talep Miktarı",
                value = buildString {
                    append(buyerRequest.PurchaseQuantity)
                    if (buyerRequest.UnitName.isNotBlank()) {
                        append(" ")
                        append(buyerRequest.UnitName)
                    }
                }
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.Payments,
                title = "Hedef Birim Fiyat",
                value = buildString {
                    append(buyerRequest.UnitPrice)
                    if (buyerRequest.CurrencySymbol.isNotBlank()) {
                        append(" ")
                        append(buyerRequest.CurrencySymbol)
                    } else if (buyerRequest.CurrencyName.isNotBlank()) {
                        append(" ")
                        append(buyerRequest.CurrencyName)
                    }
                }
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.Schedule,
                title = "Son Talep Tarihi",
                value = buyerRequest.LastRequestDate.ifBlank { "-" }
            )
        }
    }
}

@Composable
private fun RfqDetailProductCard(
    buyerRequest: BuyerRequestDTO
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
            RfqDetailSectionTitle(
                icon = Icons.Outlined.Article,
                title = "Ürün Bilgileri"
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.Inventory2,
                title = "Ürün",
                value = buyerRequest.ProductName.ifBlank { "-" }
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.Category,
                title = "Kategori",
                value = buyerRequest.CategoryName.ifBlank { "-" }
            )

            if (buyerRequest.ProductDescription.isNotBlank()) {
                Text(
                    text = buyerRequest.ProductDescription,
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (buyerRequest.MaterialTypeName.isNotBlank()) {
                RfqDetailInfoRow(
                    icon = Icons.Outlined.Article,
                    title = "Malzeme",
                    value = buyerRequest.MaterialTypeName
                )
            }

            if (buyerRequest.ColorName.isNotBlank()) {
                RfqDetailInfoRow(
                    icon = Icons.Outlined.Article,
                    title = "Renk",
                    value = buyerRequest.ColorName
                )
            }
        }
    }
}

@Composable
private fun RfqDetailTradeCard(
    buyerRequest: BuyerRequestDTO
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
            RfqDetailSectionTitle(
                icon = Icons.Outlined.Payments,
                title = "Ticari Koşullar"
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.Payments,
                title = "Ödeme Şartı",
                value = buyerRequest.PaymentTermName.ifBlank { "-" }
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.RequestQuote,
                title = "Ticaret Şartı",
                value = buyerRequest.TradeTermName.ifBlank { "-" }
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.Payments,
                title = "Maksimum Bütçe",
                value = buyerRequest.MaxbudgetName.ifBlank { "-" }
            )
        }
    }
}

@Composable
private fun RfqDetailDeliveryCard(
    buyerRequest: BuyerRequestDTO
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
            RfqDetailSectionTitle(
                icon = Icons.Outlined.LocalShipping,
                title = "Teslimat Bilgileri"
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.LocalShipping,
                title = "Teslimat Türü",
                value = buyerRequest.ShippingTypeName.ifBlank { "-" }
            )

            RfqDetailInfoRow(
                icon = Icons.Outlined.LocalShipping,
                title = "Teslimat Hedefi",
                value = buyerRequest.ShippingTarget.ifBlank { "-" }
            )
        }
    }
}

@Composable
private fun RfqDetailOffersCard(
    onOffersClick: () -> Unit
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
            RfqDetailSectionTitle(
                icon = Icons.Outlined.LocalOffer,
                title = "Gelen Teklifler"
            )

            Text(
                text = "Bu fiyat teklifi isteğine gönderilen satıcı tekliflerini ayrı sayfada inceleyin.",
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Teklifleri Gör",
                onClick = onOffersClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocalOffer,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqDetailSectionTitle(
    icon: ImageVector,
    title: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Ui)
        )

        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun RfqDetailInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(BBIcon.BoxSm)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = BBRadius.SmShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BBColors.Yellow.Yellow800,
                modifier = Modifier.size(BBIcon.SizeSm)
            )
        }

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
private fun RfqDetailIconBox(
    icon: ImageVector
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
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Section)
        )
    }
}

@Composable
private fun RfqDetailLoadingCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "RFQ detayı yükleniyor...",
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RfqDetailErrorCard(
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
                color = MaterialTheme.colorScheme.error
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
private fun RfqDetailEmptyCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "RFQ kaydı bulunamadı.",
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}