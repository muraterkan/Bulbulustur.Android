package com.bulbulustur.android.Views.Modeselection

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTypography

@Composable
fun ModeSelectionScreen(
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Storefront,
            contentDescription = null,
            tint = BbColors.Primary
        )

        Spacer(modifier = Modifier.height(BbSpacing.md))

        Text(
            text = "Bulbulustur",
            style = BbTypography.headlineMedium,
            color = BbColors.TextStrong,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(BbSpacing.sm))

        Text(
            text = "Alışveriş deneyimini seçerek başlayalım.",
            style = BbTypography.bodyMedium,
            color = BbColors.TextMuted,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(BbSpacing.xl))

        ModeSelectionCard(
            title = "Perakende Alışveriş",
            description = "Ürünleri keşfet, favorilerine ekle ve güvenle sepetine taşı.",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.LocalMall,
                    contentDescription = null,
                    tint = BbColors.Primary
                )
            },
            onClick = onRetailClick
        )

        Spacer(modifier = Modifier.height(BbSpacing.md))

        ModeSelectionCard(
            title = "Toptan Alışveriş",
            description = "Tedarikçileri, toplu ürünleri ve teklif süreçlerini keşfet.",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Groups,
                    contentDescription = null,
                    tint = BbColors.Info
                )
            },
            onClick = onWholesaleClick
        )
    }
}

@Composable
private fun ModeSelectionCard(
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(BbRadius.lg),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(BbRadius.md),
                color = BbColors.PrimarySoft
            ) {
                Column(
                    modifier = Modifier.padding(BbSpacing.md),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    icon()
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = BbSpacing.md)
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    style = BbTypography.titleMedium,
                    color = BbColors.TextStrong
                )

                Spacer(modifier = Modifier.height(BbSpacing.xs))

                Text(
                    text = description,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }
    }
}