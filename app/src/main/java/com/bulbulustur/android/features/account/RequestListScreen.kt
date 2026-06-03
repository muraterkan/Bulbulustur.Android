package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun RequestListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        BbSectionHeader(
            title = "Taleplerim",
            subtitle = "İade ve sipariş taleplerini görüntüle, detaylarına ulaş veya iade kargo kodu al"
        )

        RequestEmptyState()
    }
}

@Composable
private fun RequestEmptyState() {
    BbCard(
        padding = BbCardPadding.Large
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.Assignment,
                contentDescription = null,
                tint = BbColors.Primary
            )

            BbChip(
                text = "Talep Yok"
            )

            Text(
                text = "Kayıt bulunamadı!",
                style = BbTypography.headlineSmall,
                color = BbColors.TextStrong
            )

            Text(
                text = "Henüz oluşturulmuş iade veya sipariş talebiniz bulunmuyor. Sipariş detaylarından yeni talep oluşturabilirsiniz.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )

            BbButton(
                text = "Siparişlerime Git",
                onClick = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = null
                    )
                }
            )
        }
    }
}