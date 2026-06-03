package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Verified
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
fun CouponListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        BbSectionHeader(
            title = "Kuponlarım",
            subtitle = "Kazanılmış ve kullanılabilir kuponlarını takip et"
        )

        CouponCard(
            title = "150 TL",
            description = "Alt limit: 300 TL",
            expireText = "07.06.2026, 23:59",
            statusText = "Kazandın"
        )

        CouponCard(
            title = "%50 indirim",
            description = "Alt limit: 1 TL",
            expireText = "03.06.2026, 23:59",
            statusText = "Kazandın"
        )
    }
}

@Composable
private fun CouponCard(
    title: String,
    description: String,
    expireText: String,
    statusText: String
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
                    imageVector = Icons.Outlined.LocalOffer,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Column(
                    modifier = Modifier
                        .padding(start = BbSpacing.md)
                        .weight(1f)
                ) {
                    Text(
                        text = title,
                        style = BbTypography.headlineSmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = description,
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }

                BbChip(
                    text = statusText,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Verified,
                            contentDescription = null
                        )
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Schedule,
                    contentDescription = null,
                    tint = BbColors.TextMuted
                )

                Text(
                    text = expireText,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted,
                    modifier = Modifier.padding(start = BbSpacing.sm)
                )
            }
        }
    }
}