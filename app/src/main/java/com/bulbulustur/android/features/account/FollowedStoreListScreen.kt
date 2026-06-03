package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Storefront
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
fun FollowedStoreListScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {

        BbSectionHeader(
            title = "Takip Edilen Mağazalar",
            subtitle = "Takip ettiğiniz mağazaları görüntüleyin ve profillerine hızlı erişin"
        )

        BbCard(
            padding = BbCardPadding.Large
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
            ) {

                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                BbChip(
                    text = "Mağaza Yok"
                )

                Text(
                    text = "Kayıt bulunamadı!",
                    style = BbTypography.headlineSmall,
                    color = BbColors.TextStrong
                )

                Text(
                    text = "Henüz takip ettiğiniz mağaza bulunmuyor. Mağaza profillerini takip ederek burada hızlı erişim listesi oluşturabilirsiniz.",
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )

                BbButton(
                    text = "Mağazaları Keşfet",
                    onClick = {}
                )
            }
        }
    }
}