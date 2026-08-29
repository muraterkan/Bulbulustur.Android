package com.bulbulustur.android.Application.Areas.b2b.Views.Product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun WholesaleProductCategoryContentListScreen(
    groupName: String = "",
    products: List<WholesaleProductCategoryContentDTO> = emptyList(),
    currentPage: Int = 1,
    totalPages: Int = 1,
    totalItemCount: Int = 0,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onPageChange: (Int) -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onFavoriteClick: (Int) -> Unit = {},
    onRfqClick: (Int) -> Unit = {}
) {
    var favoriteIds by remember { mutableStateOf<Set<Int>>(emptySet()) }

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
            item {
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                    Text(text = groupName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    if (totalItemCount > 0) Text(text = "$totalItemCount ürün", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            when {
                isLoading && products.isEmpty() -> item {
                    Row(modifier = Modifier.fillMaxWidth().padding(BBSpacing.Space6), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator()
                    }
                }

                !errorMessage.isNullOrBlank() && products.isEmpty() -> item {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                }

                products.isEmpty() -> item {
                    Text(
                        text = BBLocalization.Current.Get(key = "9afc052e-e2bf-413d-81c6-461bfc3c9174", fallback = "Ürün bulunamadı"),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                else -> items(
                    items = products,
                    key = { "${it.WholesaleProductCategoryContentId}-${it.WholesaleProductId}" }
                ) { product ->
                    val productId = product.WholesaleProductId
                    val isFavorite = favoriteIds.contains(productId)

                    WholesaleProductCard(
                        modifier = Modifier.fillMaxWidth(),
                        product = product.ToCardModel(isFavorite),
                        onClick = { if (productId > 0) onProductClick(productId) },
                        onFavoriteClick = {
                            favoriteIds = if (isFavorite) favoriteIds - productId else favoriteIds + productId
                            if (productId > 0) onFavoriteClick(productId)
                        },
                        onRfqClick = {
                            if (productId > 0) onRfqClick(productId)
                        }
                    )
                }
            }

            if (totalPages > 1) item {
                WholesaleCategoryContentPagination(
                    currentPage = currentPage,
                    totalPages = totalPages,
                    isLoading = isLoading,
                    onPageChange = onPageChange
                )
            }
        }
    }
}

@Composable
private fun WholesaleCategoryContentPagination(currentPage: Int, totalPages: Int, isLoading: Boolean, onPageChange: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Button(enabled = currentPage > 1 && !isLoading, onClick = { if (currentPage > 1) onPageChange(currentPage - 1) }) { Text("Önceki") }
        Text(text = "$currentPage / $totalPages", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
        Button(enabled = currentPage < totalPages && !isLoading, onClick = { if (currentPage < totalPages) onPageChange(currentPage + 1) }) { Text("Sonraki") }
    }
}

private fun WholesaleProductCategoryContentDTO.ToCardModel(isFavorite: Boolean): WholesaleProductCardModel {
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
