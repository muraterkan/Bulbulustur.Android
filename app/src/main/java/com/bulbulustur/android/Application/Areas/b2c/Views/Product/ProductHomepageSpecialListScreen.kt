package com.bulbulustur.android.Application.Areas.b2c.Views.Product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCard
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCardModel
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductHomepageSpecialListScreen(
    groupName: String = "",
    products: List<ProductHomepageSpecialDTO> = emptyList(),
    currentPage: Int = 1,
    totalPages: Int = 1,
    totalItemCount: Int = 0,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onPageChange: (Int) -> Unit = {},
    onProductClick: (ProductHomepageSpecialDTO) -> Unit = {},
    onFavoriteClick: (ProductHomepageSpecialDTO) -> Unit = {},
    onAddToBasketClick: (Int) -> Unit = {}
) {
    var favoriteProductKeys by remember { mutableStateOf<Set<String>>(emptySet()) }

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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = groupName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (totalItemCount > 0) {
                        Text(
                            text = "$totalItemCount ürün",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            when {
                isLoading && products.isEmpty() -> {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(BBSpacing.Space6),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                !errorMessage.isNullOrBlank() && products.isEmpty() -> {
                    item {
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                products.isEmpty() -> {
                    item {
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

                else -> {
                    val rows = products.chunked(2)

                    items(
                        items = rows,
                        key = { row ->
                            row.joinToString("-") {
                                "${it.ProductId}-${it.VariantId}-${it.StoreId}"
                            }
                        }
                    ) { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                            verticalAlignment = Alignment.Top
                        ) {
                            rowProducts.forEach { product ->
                                val favoriteKey = product.SpecialFavoriteKey()
                                val isFavorite = favoriteProductKeys.contains(favoriteKey)

                                BbProductCard(
                                    modifier = Modifier.weight(1f),
                                    product = product.ToSpecialListProductCardModel(
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

            if (totalPages > 1) {
                item {
                    ProductHomepageSpecialPagination(
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
private fun ProductHomepageSpecialPagination(
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
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
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

private fun ProductHomepageSpecialDTO.ToSpecialListProductCardModel(
    isFavorite: Boolean
): BbProductCardModel {
    return BbProductCardModel(
        Id = ProductId,
        Name = ProductName,
        StoreName = "",
        ImageUrl = ImageUrlResolver.Resolve(DefaultPicture),
        PriceText = FormatSpecialListPrice(Price),
        OldPriceText = "",
        BadgeText = "",
        RatingText = "",
        CargoText = "",
        IsFavorite = isFavorite
    )
}

private fun ProductHomepageSpecialDTO.SpecialFavoriteKey(): String {
    return "$ProductId-$VariantId-$StoreId"
}

private fun FormatSpecialListPrice(
    price: Double
): String {
    val formatter = NumberFormat
        .getNumberInstance(Locale.forLanguageTag("tr-TR"))
        .apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }

    return "${formatter.format(price)} ₺"
}

@Preview(showBackground = true)
@Composable
private fun ProductHomepageSpecialListScreenPreview() {
    BbTheme {
        ProductHomepageSpecialListScreen(
            groupName = "Yeni Gelenler",
            currentPage = 1,
            totalPages = 5,
            totalItemCount = 48
        )
    }
}