package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.commercecomponents.BbPriceBlock
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun OrderListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        BbSectionHeader(
            title = "Siparişlerim",
            subtitle = "Geçmiş ve devam eden siparişlerini görüntüle"
        )

        OrderCard(
            orderNumber = "BB-2026-0001",
            dateText = "02.06.2026",
            statusText = "Hazırlanıyor",
            totalPrice = 1647.0
        )

        OrderCard(
            orderNumber = "BB-2026-0002",
            dateText = "29.05.2026",
            statusText = "Teslim Edildi",
            totalPrice = 148.0
        )
    }
}

@Composable
private fun OrderCard(
    orderNumber: String,
    dateText: String,
    statusText: String,
    totalPrice: Double
) {
    BbCard(
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Column(
                    modifier = Modifier
                        .padding(start = BbSpacing.md)
                        .weight(1f)
                ) {
                    Text(
                        text = orderNumber,
                        style = BbTypography.titleSmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = dateText,
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = BbColors.TextMuted
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                OrderInfoMiniCard(
                    title = "Durum",
                    value = statusText,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.LocalShipping,
                            contentDescription = null,
                            tint = BbColors.Success
                        )
                    },
                    modifier = Modifier.weight(1f)
                )

                OrderInfoMiniCard(
                    title = "Ürün",
                    value = "2 ürün",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Inventory2,
                            contentDescription = null,
                            tint = BbColors.Info
                        )
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BbChip(
                    text = statusText
                )

                Spacer(modifier = Modifier.weight(1f))

                BbPriceBlock(
                    price = totalPrice
                )
            }
        }
    }
}

@Composable
private fun OrderInfoMiniCard(
    title: String,
    value: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier,
        padding = BbCardPadding.Small
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
        ) {
            icon()

            Text(
                text = title,
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )

            Text(
                text = value,
                style = BbTypography.bodySmall,
                color = BbColors.TextStrong
            )
        }
    }
}