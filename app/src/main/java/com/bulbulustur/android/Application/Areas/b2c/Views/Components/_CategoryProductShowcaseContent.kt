package com.bulbulustur.android.Application.Areas.b2c.Views.Category.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCard
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCardModel
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CategoryProductShowcaseContent(
    specialContents: List<ProductHomepageSpecialContentDTO>,
    isLoading: Boolean = false,
    onProductClick: (ProductHomepageSpecialDTO) -> Unit = {},
    onFavoriteClick: (ProductHomepageSpecialDTO) -> Unit = {},
    onAddToBasketClick: (Int) -> Unit = {},
    onViewAllClick: (ProductHomepageSpecialContentDTO) -> Unit = {}
) {
    var selectedContentIndex by remember(specialContents) { mutableIntStateOf(0) }
    var favoriteProductKeys by remember { mutableStateOf<Set<String>>(emptySet()) }

    when {
        isLoading && specialContents.isEmpty() -> {
            Row(
                modifier = Modifier.fillMaxWidth().padding(BBSpacing.Space5),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator()
            }
        }

        specialContents.isEmpty() -> {
            CategoryProductShowcaseEmpty()
        }

        else -> {
            val selectedContent = specialContents.getOrNull(selectedContentIndex) ?: specialContents.first()

            val selectedProducts = selectedContent.Products
                .filter {
                    it.ProductId > 0 &&
                            it.StoreId > 0 &&
                            it.VariantId > 0
                }
                .take(12)

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    contentPadding = PaddingValues(end = BBSpacing.Space3)
                ) {
                    items(
                        count = specialContents.size,
                        key = { index ->
                            val item = specialContents[index]
                            "${item.ProductHomepageSpecialContentId}-${item.ProductSpecialGroupId}-$index"
                        }
                    ) { index ->
                        val content = specialContents[index]

                        BbChip(
                            text = content.GroupName.ifBlank { content.ContentName },
                            selected = selectedContentIndex == index,
                            onClick = {
                                selectedContentIndex = index
                            }
                        )
                    }
                }

                CategoryProductShowcaseHeader(
                    title = selectedContent.GroupName.ifBlank {
                        selectedContent.ContentName
                    },
                    onViewAllClick = {
                        onViewAllClick(selectedContent)
                    }
                )

                if (selectedProducts.isEmpty()) {
                    CategoryProductShowcaseEmpty()
                } else {
                    selectedProducts.chunked(2).forEach { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                            verticalAlignment = Alignment.Top
                        ) {
                            rowProducts.forEach { product ->
                                val favoriteKey = product.ShowcaseFavoriteKey()
                                val isFavorite = favoriteProductKeys.contains(favoriteKey)

                                BbProductCard(
                                    modifier = Modifier.weight(1f),
                                    product = product.ToShowcaseProductCardModel(
                                        isFavorite = isFavorite
                                    ),
                                    onClick = {
                                        onProductClick(product)
                                    },
                                    onFavoriteClick = {
                                        favoriteProductKeys =
                                            if (isFavorite) {
                                                favoriteProductKeys - favoriteKey
                                            } else {
                                                favoriteProductKeys + favoriteKey
                                            }

                                        onFavoriteClick(product)
                                    },
                                    onAddToBasketClick = {
                                        if (product.ProductVariantPriceId > 0) {
                                            onAddToBasketClick(
                                                product.ProductVariantPriceId
                                            )
                                        }
                                    }
                                )
                            }

                            if (rowProducts.size == 1) {
                                Spacer(
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryProductShowcaseHeader(
    title: String,
    onViewAllClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            modifier = Modifier.clickable {
                onViewAllClick()
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Text(
                text = BBLocalization.Current.Get(
                    key = "845e87d3-d457-473f-8359-3efbf74746d4",
                    fallback = "Tümünü Gör"
                ),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.padding(
                    start = BBSpacing.Space1
                ),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun CategoryProductShowcaseEmpty() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Text(
            text = BBLocalization.Current.Get(
                key = "9afc052e-e2bf-413d-81c6-461bfc3c9174",
                fallback = "Ürün bulunamadı"
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun ProductHomepageSpecialDTO.ToShowcaseProductCardModel(
    isFavorite: Boolean
): BbProductCardModel {
    return BbProductCardModel(
        Id = ProductId,
        Name = ProductName,
        StoreName = "",
        ImageUrl = ImageUrlResolver.Resolve(
            DefaultPicture
        ),
        PriceText = FormatShowcasePrice(
            Price
        ),
        OldPriceText = "",
        BadgeText = "",
        RatingText = "",
        CargoText = "",
        IsFavorite = isFavorite
    )
}

private fun ProductHomepageSpecialDTO.ShowcaseFavoriteKey(): String {
    return "$ProductId-$VariantId-$StoreId"
}

private fun FormatShowcasePrice(
    price: Double
): String {
    val formatter = NumberFormat
        .getNumberInstance(
            Locale.forLanguageTag("tr-TR")
        )
        .apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }

    return "${formatter.format(price)} ₺"
}