package com.bulbulustur.android.Application.Areas.b2c.Views.Product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCard
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCardModel
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductCategoryContentListScreen(
    groupName: String = "",
    products: List<ProductCategoryContentDTO> = emptyList(),
    currentPage: Int = 1,
    totalPages: Int = 1,
    totalItemCount: Int = 0,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onPageChange: (Int) -> Unit = {},
    onProductClick: (ProductCategoryContentDTO) -> Unit = {},
    onFavoriteClick: (ProductCategoryContentDTO) -> Unit = {},
    onAddToBasketClick: (Int) -> Unit = {}
) {
    var favoriteKeys by remember { mutableStateOf<Set<String>>(emptySet()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = groupName.ifBlank {
                    BBLocalization.Current.Get(
                        key = "21f6b0ee-67eb-40fb-899d-640fb99a7397",
                        fallback = "Kategori Vitrinleri"
                    )
                },
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            when {
                isLoading && products.isEmpty() -> item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(BBSpacing.Space6),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator()
                    }
                }

                !errorMessage.isNullOrBlank() && products.isEmpty() -> item {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                products.isEmpty() -> item {
                    Text(
                        text = BBLocalization.Current.Get(
                            key = "9afc052e-e2bf-413d-81c6-461bfc3c9174",
                            fallback = "Ürün bulunamadı"
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                else -> {
                    items(
                        items = products.chunked(2),
                        key = { row -> row.joinToString("-") { "${it.ProductId}-${it.VariantId}-${it.StoreId}" } }
                    ) { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                            verticalAlignment = Alignment.Top
                        ) {
                            rowProducts.forEach { product ->
                                val favoriteKey = "${product.ProductId}-${product.VariantId}-${product.StoreId}"
                                val isFavorite = favoriteKeys.contains(favoriteKey)

                                BbProductCard(
                                    modifier = Modifier.weight(1f),
                                    product = product.ToCardModel(isFavorite),
                                    onClick = { onProductClick(product) },
                                    onFavoriteClick = {
                                        favoriteKeys = if (isFavorite) favoriteKeys - favoriteKey else favoriteKeys + favoriteKey
                                        onFavoriteClick(product)
                                    },
                                    onAddToBasketClick = {
                                        if (product.ProductVariantPriceId > 0) {
                                            onAddToBasketClick(product.ProductVariantPriceId)
                                        }
                                    }
                                )
                            }

                            if (rowProducts.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            if (totalPages > 1) {
                item {
                    CategoryContentPagination(
                        currentPage = currentPage,
                        totalPages = totalPages,
                        isLoading = isLoading,
                        onPageChange = onPageChange
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryContentPagination(
    currentPage: Int,
    totalPages: Int,
    isLoading: Boolean,
    onPageChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            enabled = currentPage > 1 && !isLoading,
            onClick = {
                if (currentPage > 1) {
                    onPageChange(currentPage - 1)
                }
            }
        ) {
            Text("Önceki")
        }

        Text(
            text = "$currentPage / $totalPages",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )

        Button(
            enabled = currentPage < totalPages && !isLoading,
            onClick = {
                if (currentPage < totalPages) {
                    onPageChange(currentPage + 1)
                }
            }
        ) {
            Text("Sonraki")
        }
    }
}

private fun ProductCategoryContentDTO.ToCardModel(
    isFavorite: Boolean
): BbProductCardModel {
    val formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("tr-TR")).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return BbProductCardModel(
        Id = ProductId,
        Name = ProductName,
        StoreName = "",
        ImageUrl = ImageUrlResolver.Resolve(DefaultPicture),
        PriceText = "${formatter.format(Price)} ₺",
        OldPriceText = "",
        BadgeText = "",
        RatingText = "",
        CargoText = "",
        IsFavorite = isFavorite
    )
}