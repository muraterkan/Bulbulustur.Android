package com.bulbulustur.android.features.order

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
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
fun OrderDetailScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {

        BbSectionHeader(
            title = "Sipariş Detayı",
            subtitle = "Sipariş ve teslimat bilgilerini görüntüle"
        )

        BbCard(
            padding = BbCardPadding.Medium
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Text(
                    text = "BB-2026-0001",
                    style = BbTypography.titleMedium,
                    color = BbColors.TextStrong
                )

                BbChip(
                    text = "Hazırlanıyor"
                )

                Text(
                    text = "02.06.2026",
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }

        BbCard(
            padding = BbCardPadding.Medium
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Column(
                    modifier = Modifier.padding(start = BbSpacing.md)
                ) {
                    Text(
                        text = "Ortobella",
                        style = BbTypography.titleSmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = "Satıcı",
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }
            }
        }

        BbCard(
            padding = BbCardPadding.Medium
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalShipping,
                    contentDescription = null,
                    tint = BbColors.Success
                )

                Column(
                    modifier = Modifier.padding(start = BbSpacing.md)
                ) {
                    Text(
                        text = "Kargo Durumu",
                        style = BbTypography.titleSmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = "Hazırlanıyor",
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }
            }
        }

        BbCard(
            padding = BbCardPadding.Medium
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = BbColors.Warning
                )

                Text(
                    text = "Toplam",
                    modifier = Modifier
                        .padding(start = BbSpacing.md)
                        .weight(1f),
                    style = BbTypography.titleSmall,
                    color = BbColors.TextStrong
                )

                BbPriceBlock(
                    price = 1647.0
                )
            }
        }
    }
}