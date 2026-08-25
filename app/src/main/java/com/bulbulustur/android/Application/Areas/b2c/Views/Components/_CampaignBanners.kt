package com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components

import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes

@Composable
fun CampaignBanners(
    campaigns: List<CampaignDTO>,
    onCampaignClick: (Int) -> Unit,
    onViewAllClick: () -> Unit
) {
    if (campaigns.isEmpty()) {
        return
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "e2812624-6bbc-4034-9a09-6570540d0785", fallback = "Kampanyalar"),
                    style = MaterialTheme.typography.labelMedium,
                    color = BBColors.Yellow.Yellow700,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = BBLocalization.Current.Get(key = "fa54b6d9-199b-4867-99e8-ff8865857ce0", fallback = "Alışveriş Fırsatlarını Keşfet"),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = BBLocalization.Current.Get(key = "5f890756-3d46-4903-a7e8-db5f95d96f87", fallback = "Seçili kampanyalar, avantajlı alışveriş alanları ve öne çıkan fırsatlar burada."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            TextButton(onClick = onViewAllClick) {
                Text(
                    text = BBLocalization.Current.Get(key = "e2812624-6bbc-4034-9a09-6570540d0785", fallback = "Tüm Kampanyalar"),
                    style = MaterialTheme.typography.labelLarge
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            contentPadding = PaddingValues(end = BBSpacing.PageHorizontal)
        ) {
            items(
                items = campaigns,
                key = { campaign -> campaign.CampaignId }
            ) { campaign ->
                CampaignBannerCard(
                    campaign = campaign,
                    onClick = {
                        onCampaignClick(campaign.CampaignId)
                    }
                )
            }
        }
    }
}

@Composable
private fun CampaignBannerCard(
    campaign: CampaignDTO,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.width(BBSpacing.Space24 + BBSpacing.Space24 + BBSpacing.Space16),
        onClick = onClick,
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.CardPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocalOffer,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.SizeSm),
                        tint = BBColors.Orange.Orange600
                    )

                    Text(
                        text = BBLocalization.Current.Get(key = "b66af534-b950-4603-8fb6-caf6ca5be73d", fallback = "Kampanya"),
                        style = MaterialTheme.typography.labelMedium,
                        color = BBColors.Orange.Orange700,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = campaign.CampaignName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (campaign.Description.isNotBlank()) {
                    Text(
                        text = campaign.Description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "02d77c71-e8dc-4ff3-825d-9775f263f006", fallback = "Şimdi Gör"),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.SizeSm),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AsyncImage(
                model = ImageUrlResolver.Resolve(
                    campaign.Picture.ifBlank {
                        campaign.DefaultPicture
                    }
                ),
                contentDescription = campaign.CampaignName,
                modifier = Modifier
                    .width(BBSpacing.Space24)
                    .height(BBSpacing.Space24),
                contentScale = ContentScale.Fit
            )
        }
    }
}

