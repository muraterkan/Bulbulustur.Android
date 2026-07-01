package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Star
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
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import java.text.NumberFormat
import java.util.Locale

@Composable
fun _B2CProductBrowsingHistory(
    Histories: List<ProductBrowsingHistoryDTO>,
    onProductClick: (ProductBrowsingHistoryDTO) -> Unit
) {
    if (
        Histories.isEmpty()
    ) {
        return
    }

    Column(
        modifier =
            Modifier.padding(
                top =
                    BBSpacing.Space5
            ),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        ProductBrowsingHistorySectionTitle()

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        horizontal =
                            BBSpacing.PageHorizontal
                    ),
            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {
            Histories.forEach { history ->
                ProductBrowsingHistoryCard(
                    History =
                        history,
                    onClick = {
                        onProductClick(
                            history
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductBrowsingHistorySectionTitle() {
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
                Modifier.width(
                    BBIcon.BoxMd
                ),
            shape =
                BBRadius.IconBoxSoft,
            color =
                MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(
                            BBIcon.BoxMd
                        ),
                contentAlignment =
                    Alignment.Center
            ) {
                Icon(
                    imageVector =
                        Icons.Outlined.History,
                    contentDescription =
                        null,
                    tint =
                        MaterialTheme.colorScheme.onSurface,
                    modifier =
                        Modifier.width(
                            BBIcon.SizeMd
                        )
                )
            }
        }

        Column {
            Text(
                text =
                    "Görüntüleme Geçmişin",
                style =
                    MaterialTheme.typography.titleMedium,
                color =
                    MaterialTheme.colorScheme.onSurface,
                fontWeight =
                    FontWeight.Bold
            )

            Text(
                text =
                    "Daha önce baktığın ürünler",
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductBrowsingHistoryCard(
    History: ProductBrowsingHistoryDTO,
    onClick: () -> Unit
) {
    val imageUrl =
        ResolveBrowsingHistoryImageUrl(
            imagePath =
                History.DefaultPicture
                    .takeIf {
                        it.isNotBlank()
                    }
                    ?: History.Picture
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
                    BBSpacing.BorderThin,
                color =
                    MaterialTheme.colorScheme.outlineVariant
            )
    ) {
        Column {
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
                            History.ProductName,
                        modifier =
                            Modifier.fillMaxSize(),
                        contentScale =
                            ContentScale.Crop
                    )
                } else {
                    Text(
                        text =
                            History.ProductName
                                .take(
                                    2
                                )
                                .uppercase(),
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
                        History.ProductName,
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
                    History.CategoryName.isNotBlank()
                ) {
                    Text(
                        text =
                            History.CategoryName,
                        style =
                            MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines =
                            1
                    )
                }

                if (
                    History.Rating > 0
                ) {
                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically,
                        horizontalArrangement =
                            Arrangement.spacedBy(
                                BBSpacing.Space1
                            )
                    ) {
                        Icon(
                            imageVector =
                                Icons.Outlined.Star,
                            contentDescription =
                                null,
                            tint =
                                MaterialTheme.colorScheme.primary,
                            modifier =
                                Modifier.width(
                                    BBIcon.Size2Xs
                                )
                        )

                        Text(
                            text =
                                FormatBrowsingHistoryRating(
                                    rating =
                                        History.Rating
                                ),
                            style =
                                MaterialTheme.typography.labelSmall,
                            color =
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        if (
                            History.ReviewNumber > 0
                        ) {
                            Text(
                                text =
                                    "(${History.ReviewNumber})",
                                style =
                                    MaterialTheme.typography.labelSmall,
                                color =
                                    MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Text(
                    text =
                        FormatBrowsingHistoryPrice(
                            price =
                                History.Price,
                            currencySymbol =
                                History.CurrencySymbol
                        ),
                    style =
                        MaterialTheme.typography.titleSmall,
                    color =
                        MaterialTheme.colorScheme.onSurface,
                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}

private fun ResolveBrowsingHistoryImageUrl(
    imagePath: String
): String {
    val normalizedPath =
        imagePath.trim()

    if (
        normalizedPath.isBlank()
    ) {
        return ""
    }

    if (
        normalizedPath.startsWith(
            "http://",
            ignoreCase =
                true
        ) ||
        normalizedPath.startsWith(
            "https://",
            ignoreCase =
                true
        )
    ) {
        return normalizedPath
    }

    val baseUrl =
        ApiRoutes.B2C_BASE_URL
            .substringBefore(
                "/api/"
            )
            .trimEnd(
                '/'
            )

    return "$baseUrl/${normalizedPath.trimStart('/')}"
}

private fun FormatBrowsingHistoryPrice(
    price: Double,
    currencySymbol: String
): String {
    val formatter =
        NumberFormat.getNumberInstance(
            Locale(
                "tr",
                "TR"
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

private fun FormatBrowsingHistoryRating(
    rating: Double
): String {
    return String.format(
        Locale(
            "tr",
            "TR"
        ),
        "%.1f",
        rating
    )
}