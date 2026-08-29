package com.bulbulustur.android.Application.Areas.b2b.Views.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun WholesaleCategoryProductContentShowcaseContent(
    categoryContents: List<WholesaleProductCategoryContentGroupDTO>,
    isLoading: Boolean = false,
    onProductClick: (Int) -> Unit = {},
    onFavoriteClick: (Int) -> Unit = {},
    onRfqClick: (Int) -> Unit = {},
    onViewAllClick: (WholesaleProductCategoryContentGroupDTO) -> Unit = {}
) {
    var selectedContentIndex by remember(categoryContents) { mutableIntStateOf(0) }
    var favoriteProductIds by remember { mutableStateOf<Set<Int>>(emptySet()) }

    when {
        isLoading && categoryContents.isEmpty() -> Row(modifier = Modifier.fillMaxWidth().padding(BBSpacing.Space5), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            CircularProgressIndicator()
        }

        categoryContents.isEmpty() -> WholesaleCategoryProductContentShowcaseEmpty()

        else -> {
            val selectedContent = categoryContents.getOrNull(selectedContentIndex) ?: categoryContents.first()
            val selectedProducts = selectedContent.Products.filter { it.WholesaleProductId > 0 }.distinctBy { it.WholesaleProductId }.take(12)

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2), contentPadding = PaddingValues(end = BBSpacing.Space3)) {
                    items(items = categoryContents, key = { "${it.WholesaleProductCategoryContentGroupId}-${it.ProductCategoryId}" }) { content ->
                        BbChip(text = content.GroupName, selected = categoryContents.indexOf(content) == selectedContentIndex, onClick = { selectedContentIndex = categoryContents.indexOf(content) })
                    }
                }

                WholesaleCategoryProductContentShowcaseHeader(title = selectedContent.GroupName, onViewAllClick = { onViewAllClick(selectedContent) })

                if (selectedProducts.isEmpty()) {
                    WholesaleCategoryProductContentShowcaseEmpty()
                } else {
                    selectedProducts.chunked(2).forEach { rowProducts ->
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.Top) {
                            rowProducts.forEach { product ->
                                val productId = product.WholesaleProductId
                                val isFavorite = favoriteProductIds.contains(productId)

                                WholesaleProductCard(
                                    modifier = Modifier.weight(1f),
                                    product = product.ToWholesaleCategoryProductContentCardModel(isFavorite),
                                    onClick = { if (productId > 0) onProductClick(productId) },
                                    onFavoriteClick = {
                                        favoriteProductIds = if (isFavorite) favoriteProductIds - productId else favoriteProductIds + productId
                                        if (productId > 0) onFavoriteClick(productId)
                                    },
                                    onRfqClick = { if (productId > 0) onRfqClick(productId) }
                                )
                            }

                            if (rowProducts.size == 1) Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleCategoryProductContentShowcaseHeader(title: String, onViewAllClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)

        Row(modifier = Modifier.clickable { onViewAllClick() }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
            Text(text = BBLocalization.Current.Get(key = "7fa2dfd8-809f-4a8d-8fde-f33e7f652b45", fallback = "Tümünü Gör"), style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
            Icon(imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight, contentDescription = null, modifier = Modifier.padding(start = BBSpacing.Space1), tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun WholesaleCategoryProductContentShowcaseEmpty() {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Text(text = BBLocalization.Current.Get(key = "9afc052e-e2bf-413d-81c6-461bfc3c9174", fallback = "Ürün bulunamadı"), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

private fun WholesaleProductCategoryContentDTO.ToWholesaleCategoryProductContentCardModel(isFavorite: Boolean): WholesaleProductCardModel {
    return WholesaleProductCardModel(
        Id = WholesaleProductId,
        Title = ProductName.trim(),
        PriceText = BBLocalization.Current.Get(key = "ba6fe1b6-4d68-487c-b98a-eed9fe59bb2c", fallback = "Teklif İle"),
        MoqText = if (MinimumOrderQuantity > 0) "MOQ $MinimumOrderQuantity" else "",
        BadgeText = "",
        ImageUrl = ImageUrlResolver.Resolve(DefaultPicture.trim()),
        IsFavorite = isFavorite
    )
}
