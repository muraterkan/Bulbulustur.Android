package com.bulbulustur.android.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun ModeSelectionScreen(
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.Primary)
            .padding(horizontal = BbSpacing.Space5),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_black),
            contentDescription = "Bulbulustur",
            modifier = Modifier.width(218.dp)
        )

        Spacer(
            modifier = Modifier.height(BbSpacing.Space8)
        )

        Text(
            text = "Alışveriş deneyimini seç",
            color = BbColors.Gray.Gray900,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(BbSpacing.Space2)
        )

        Text(
            text = "Toptan veya perakende dünyasına hızlıca giriş yap.",
            color = BbColors.Gray.Gray800,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(BbSpacing.Space10)
        )

        ModeSelectionCard(
            title = "Perakende Alışveriş",
            description = "Ürünleri keşfet, favorilerine ekle ve güvenle sepetine taşı.",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.LocalMall,
                    contentDescription = null,
                    tint = BbColors.Gray.Gray900,
                    modifier = Modifier.size(28.dp)
                )
            },
            onClick = onRetailClick
        )

        Spacer(
            modifier = Modifier.height(BbSpacing.Space4)
        )

        ModeSelectionCard(
            title = "Toptan Alışveriş",
            description = "Tedarikçileri, toplu ürünleri ve teklif süreçlerini keşfet.",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Groups,
                    contentDescription = null,
                    tint = BbColors.Gray.Gray900,
                    modifier = Modifier.size(28.dp)
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
        shape = RoundedCornerShape(BbRadius.xl),
        color = BbColors.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(BbRadius.lg),
                color = BbColors.PrimarySoft
            ) {
                Column(
                    modifier = Modifier
                        .size(56.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    icon()
                }
            }

            Spacer(
                modifier = Modifier.width(BbSpacing.Space4)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BbColors.Gray.Gray900
                )

                Spacer(
                    modifier = Modifier.height(BbSpacing.Space1)
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.Gray.Gray700
                )
            }
        }
    }
}