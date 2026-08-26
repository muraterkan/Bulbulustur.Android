package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import java.text.NumberFormat
import java.util.Locale

@Composable
fun _B2CSponsoredAdverts(
    Adverts: List<AdvertSponsoredDTO>,
    onAdvertClick: (AdvertSponsoredDTO) -> Unit
) {
    val visibleAdverts =
        Adverts.filter { advert ->
            advert.ProductId > 0
        }

    if (
        visibleAdverts.isEmpty()
    ) {
        return
    }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    top =
                        BBSpacing.Space5
                ),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        SponsoredAdvertsSectionHeader()

        LazyRow(
            modifier =
                Modifier.fillMaxWidth(),
            contentPadding =
                PaddingValues(
                    horizontal =
                        BBSpacing.PageHorizontal
                ),
            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {
            items(
                items =
                    visibleAdverts,
                key = { advert ->
                    advert.AdvertSponsoredId
                        .takeIf {
                            it > 0
                        }
                        ?: advert.ProductId
                }
            ) { advert ->
                SponsoredAdvertCard(
                    Advert =
                        advert,
                    onClick = {
                        onAdvertClick(
                            advert
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun SponsoredAdvertsSectionHeader() {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal =
                        BBSpacing.PageHorizontal
                ),
        verticalAlignment =
            Alignment.CenterVertically,
        horizontalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        Surface(
            modifier =
                Modifier
                    .width(
                        BBIcon.BoxMd
                    )
                    .height(
                        BBIcon.BoxMd
                    ),
            shape =
                BBRadius.IconBoxSoft,
            color =
                MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(
                modifier =
                    Modifier.fillMaxSize(),
                contentAlignment =
                    Alignment.Center
            ) {
                Icon(
                    imageVector =
                        Icons.Outlined.Campaign,
                    contentDescription =
                        null,
                    tint =
                        MaterialTheme.colorScheme.onSurface,
                    modifier =
                        Modifier
                            .width(
                                BBIcon.SizeMd
                            )
                            .height(
                                BBIcon.SizeMd
                            )
                )
            }
        }

        Column(
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space1
                )
        ) {
            Text(
                text =
                    BBLocalization.Current.Get(key = "c0fa950b-d7b0-42a5-a83e-280020117364", fallback = "Sponsorlu Ürünler"),
                style =
                    MaterialTheme.typography.titleMedium,
                color =
                    MaterialTheme.colorScheme.onSurface,
                fontWeight =
                    FontWeight.Bold
            )

            Text(
                text =
                    BBLocalization.Current.Get(key = "1c7c6ac9-2b6d-46ec-90f0-3f88b65beb11", fallback = ""),
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SponsoredAdvertCard(
    Advert: AdvertSponsoredDTO,
    onClick: () -> Unit
) {
    val imageUrl =
        ImageUrlResolver.Resolve(
            imagePath =
                Advert.DefaultPicture
        )

    Surface(
        modifier =
            Modifier
                .width(
                    BBLayout.ProductCardWidthSmall
                )
                .clip(
                    BBRadius.XlShape
                )
                .clickable {
                    onClick()
                },
        shape =
            BBRadius.XlShape,
        color =
            MaterialTheme.colorScheme.surface,
        border =
            BorderStroke(
                width =
                    1.dp,
                color =
                    MaterialTheme.colorScheme.outlineVariant
            )
    ) {
        Column(
            modifier =
                Modifier.fillMaxWidth()
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(
                            BBLayout.ProductCardMediaHeightLarge
                        ),
                contentAlignment =
                    Alignment.Center
            ) {
                if (
                    imageUrl.isNotBlank()
                ) {
                    AsyncImage(
                        model =
                            imageUrl,
                        contentDescription =
                            Advert.ProductName,
                        modifier =
                            Modifier.fillMaxSize(),
                        contentScale =
                            ContentScale.Crop
                    )
                } else {
                    Text(
                        text =
                            Advert.ProductName
                                .takeIf {
                                    it.isNotBlank()
                                }
                                ?.take(
                                    2
                                )
                                ?.uppercase(
                                    Locale.forLanguageTag(
                                        "tr-TR"
                                    )
                                )
                                ?: "BB",
                        style =
                            MaterialTheme.typography.titleLarge,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }

            Column(
                modifier =
                    Modifier.padding(
                        BBSpacing.Space3
                    ),
                verticalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
            ) {
                Text(
                    text =
                        Advert.ProductName
                            .takeIf {
                                it.isNotBlank()
                            }
                            ?: BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = ""),
                    style =
                        MaterialTheme.typography.bodyMedium,
                    color =
                        MaterialTheme.colorScheme.onSurface,
                    fontWeight =
                        FontWeight.SemiBold,
                    maxLines =
                        2
                )

                if (
                    Advert.Unit.isNotBlank()
                ) {
                    Text(
                        text =
                            Advert.Unit,
                        style =
                            MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines =
                            1
                    )
                }

                Text(
                    text =
                        FormatSponsoredAdvertPrice(
                            price =
                                Advert.Price,
                            currencySymbol =
                                Advert.CurrencySymbol
                        ),
                    style =
                        MaterialTheme.typography.titleSmall,
                    color =
                        MaterialTheme.colorScheme.onSurface,
                    fontWeight =
                        FontWeight.Bold
                )

                Surface(
                    shape =
                        BBRadius.PillShape,
                    color =
                        MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        modifier =
                            Modifier.padding(
                                horizontal =
                                    BBSpacing.Space2,
                                vertical =
                                    BBSpacing.Space1
                            ),
                        text =
                            "Sponsorlu",
                        style =
                            MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurface,
                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }
        }
    }
}

private fun FormatSponsoredAdvertPrice(
    price: Double,
    currencySymbol: String
): String {
    val formatter =
        NumberFormat.getNumberInstance(
            Locale.forLanguageTag(
                "tr-TR"
            )
        ).apply {
            minimumFractionDigits =
                2

            maximumFractionDigits =
                2
        }

    return buildString {
        if (
            currencySymbol.isNotBlank()
        ) {
            append(
                currencySymbol
            )

            append(
                " "
            )
        }

        append(
            formatter.format(
                price
            )
        )
    }
}