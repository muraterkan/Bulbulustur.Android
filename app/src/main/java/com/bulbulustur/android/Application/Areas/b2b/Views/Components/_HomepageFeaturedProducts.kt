package com.bulbulustur.android.Application.Areas.b2b.Views.Home.Components

import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageFeaturedProductDTO

@Composable
fun HomepageFeaturedProducts(
    products: List<WholesaleHomepageFeaturedProductDTO>,
    onProductClick: (Int) -> Unit,
    onRfqClick: (Int) -> Unit,
    onViewAllClick: () -> Unit
) {
    if (products.isEmpty()) {
        return
    }

    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        HomepageFeaturedProductsHeader(
            onViewAllClick = onViewAllClick
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            contentPadding = PaddingValues(end = BBSpacing.PageHorizontal)
        ) {
            items(
                items = products,
                key = { product -> product.WholesaleProductId }
            ) { product ->
                val productId = product.WholesaleProductId
                val isFavorite = favoriteProductIds.contains(productId)

                WholesaleProductCard(
                    product = product.ToWholesaleProductCardModel(
                        isFavorite = isFavorite
                    ),
                    modifier = Modifier.width(BBSpacing.Space24 + BBSpacing.Space24),
                    onClick = {
                        onProductClick(productId)
                    },
                    onFavoriteClick = {
                        favoriteProductIds = if (isFavorite) {
                            favoriteProductIds - productId
                        } else {
                            favoriteProductIds + productId
                        }
                    },
                    onRfqClick = {
                        onRfqClick(productId)
                    }
                )
            }
        }
    }
}

@Composable
private fun HomepageFeaturedProductsHeader(
    onViewAllClick: () -> Unit
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
                text = BBLocalization.Current.Get(key = "1c7c6ac9-2b6d-46ec-90f0-3f88b65beb11", fallback = ""),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = BBLocalization.Current.Get(key = "f5f5e279-1f6e-412b-bf54-bff171fe9b72", fallback = "Minimum sipariş bilgilerini incele."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        TextButton(
            onClick = onViewAllClick
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "7fa2dfd8-809f-4a8d-8fde-f33e7f652b45", fallback = "Tümünü Gör"),
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.Navy.Navy700
            )

            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = BBLocalization.Current.Get(key = "7fa2dfd8-809f-4a8d-8fde-f33e7f652b45", fallback = "Tümünü Gör"),
                modifier = Modifier.size(BBIcon.SizeSm),
                tint = BBColors.Navy.Navy700
            )
        }
    }
}

private fun WholesaleHomepageFeaturedProductDTO.ToWholesaleProductCardModel(
    isFavorite: Boolean
): WholesaleProductCardModel {
    return WholesaleProductCardModel(
        Id = WholesaleProductId,
        Title = ProductName,
        PriceText = "Teklif ile",
        MoqText = if (MinimumOrderQuantity > 0) {
            "MOQ $MinimumOrderQuantity"
        } else {
            ""
        },
        BadgeText = BBLocalization.Current.Get(key = "d02a9b8b-001b-4e99-9073-9150016441f3", fallback = "Öne Çıkan"),
        ImageUrl = ImageUrlResolver.Resolve(DefaultPicture),
        IsFavorite = isFavorite
    )
}

