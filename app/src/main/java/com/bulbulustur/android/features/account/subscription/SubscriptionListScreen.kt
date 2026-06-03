package com.bulbulustur.android.features.account.subscription

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun SubscriptionListScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {

        BbSectionHeader(
            title = "Abonelikler",
            subtitle = "Aktif servis ve üyeliklerinizi yönetin"
        )

        SubscriptionCard(
            serviceName = "Professional Membership",
            status = "Aktif",
            expireDate = "01.01.2027"
        )

        SubscriptionCard(
            serviceName = "Secure Trade",
            status = "Aktif",
            expireDate = "01.01.2027"
        )

        SubscriptionCard(
            serviceName = "Brand Page",
            status = "Deneme",
            expireDate = "15.07.2026"
        )
    }
}

@Composable
private fun SubscriptionCard(
    serviceName: String,
    status: String,
    expireDate: String
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
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = serviceName,
                    modifier = Modifier
                        .padding(start = BbSpacing.md)
                        .weight(1f),
                    style = BbTypography.titleSmall,
                    color = BbColors.TextStrong
                )

                BbChip(
                    text = status
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = BbColors.TextMuted
                )

                Text(
                    text = "Bitiş Tarihi: $expireDate",
                    modifier = Modifier.padding(start = BbSpacing.sm),
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Inventory2,
                    contentDescription = null,
                    tint = BbColors.TextMuted
                )

                Text(
                    text = "Servis Detaylarını Gör",
                    modifier = Modifier.padding(start = BbSpacing.sm),
                    style = BbTypography.bodyMedium,
                    color = BbColors.Primary
                )
            }
        }
    }
}