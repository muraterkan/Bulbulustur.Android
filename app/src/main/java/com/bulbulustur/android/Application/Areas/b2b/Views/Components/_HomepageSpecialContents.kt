package com.bulbulustur.android.Application.Areas.b2b.Views.Home.Components

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes

@Composable
fun HomepageSpecialContents(
    specialContents: List<WholesaleHomepageSpecialContentDTO>,
    onProductClick: (Int) -> Unit,
    onRfqClick: (Int) -> Unit
) {
    if (specialContents.isEmpty()) {
        return
    }

    var selectedGroupIndex by remember {
        mutableIntStateOf(0)
    }

    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val selectedGroup = specialContents.getOrNull(selectedGroupIndex) ?: specialContents.first()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "b18fcc04-78f7-4914-afb6-8f283fd08a61", fallback = "Seçilmiş Toptan Ürün Grupları"),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = BBLocalization.Current.Get(key = "79b1703e-e0b9-43a0-80ef-9ceb2dd6933a", fallback = "Toptan alıma uygun özel ürün vitrinlerini keşfedin."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            contentPadding = PaddingValues(end = BBSpacing.PageHorizontal)
        ) {
            items(
                count = specialContents.size,
                key = { index -> specialContents[index].ProductSpecialGroupId }
            ) { index ->
                val group = specialContents[index]

                BbChip(
                    text = group.GroupName.ifBlank { BBLocalization.Current.Get(key = "4272734e-b75c-4eb5-a568-7d7f629116fe", fallback = "Toptan Vitrin") },
                    selected = selectedGroupIndex == index,
                    onClick = {
                        selectedGroupIndex = index
                    }
                )
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            contentPadding = PaddingValues(end = BBSpacing.PageHorizontal)
        ) {
            items(
                items = selectedGroup.Products,
                key = { product -> product.WholesaleProductId }
            ) { product ->
                val productId = product.WholesaleProductId
                val isFavorite = favoriteProductIds.contains(productId)

                WholesaleProductCard(
                    product = product.ToWholesaleProductCardModel(isFavorite),
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

private fun WholesaleHomepageSpecialDTO.ToWholesaleProductCardModel(isFavorite: Boolean): WholesaleProductCardModel {
    return WholesaleProductCardModel(
        Id = WholesaleProductId,
        Title = ProductName,
        PriceText = "Teklif ile",
        MoqText = if (MinimumOrderQuantity > 0) "MOQ $MinimumOrderQuantity" else "",
        BadgeText = BBLocalization.Current.Get(key = "03fd0ba4-09a9-4909-bdb0-e0951aab5800", fallback = "Özel Vitrin"),
        ImageUrl = ResolveWholesaleHomepageSpecialImageUrl(DefaultPicture),
        IsFavorite = isFavorite
    )
}

private fun ResolveWholesaleHomepageSpecialImageUrl(imagePath: String): String {
    return ApiRoutes.B2C_TEST_PRODUCT_IMAGE_URL
}
