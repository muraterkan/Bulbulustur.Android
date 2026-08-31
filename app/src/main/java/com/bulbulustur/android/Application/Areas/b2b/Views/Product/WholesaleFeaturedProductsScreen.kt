package com.bulbulustur.android.Application.Areas.b2b.Views.Product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageFeaturedProductDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun WholesaleFeaturedProductsScreen(
    products: List<WholesaleHomepageFeaturedProductDTO> = emptyList(),
    isLoading: Boolean = false,
    onBackClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onRfqClick: (Int) -> Unit = {}
) {
    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(
                    key = "1c7c6ac9-2b6d-46ec-90f0-3f88b65beb11",
                    fallback = "Öne Çıkan Ürünler"
                ),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && products.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            products.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(BBSpacing.PageHorizontal),
                    contentAlignment = Alignment.Center
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

            else -> {
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
                    items(
                        items = products.chunked(2),
                        key = { row ->
                            row.joinToString("-") {
                                it.WholesaleProductId.toString()
                            }
                        }
                    ) { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                BBSpacing.Space3
                            ),
                            verticalAlignment = Alignment.Top
                        ) {
                            rowProducts.forEach { product ->
                                val productId =
                                    product.WholesaleProductId

                                val isFavorite =
                                    favoriteProductIds.contains(productId)

                                WholesaleProductCard(
                                    modifier = Modifier.weight(1f),
                                    product = product.ToFeaturedProductCardModel(
                                        isFavorite = isFavorite
                                    ),
                                    onClick = {
                                        onProductClick(productId)
                                    },
                                    onFavoriteClick = {
                                        favoriteProductIds =
                                            if (isFavorite) {
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

                            if (rowProducts.size == 1) {
                                Box(
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

private fun WholesaleHomepageFeaturedProductDTO.ToFeaturedProductCardModel(
    isFavorite: Boolean
): WholesaleProductCardModel {
    return WholesaleProductCardModel(
        Id = WholesaleProductId,
        Title = ProductName,
        PriceText = BBLocalization.Current.Get(
            key = "ba6fe1b6-4d68-487c-b98a-eed9fe59bb2c",
            fallback = "Teklif ile"
        ),
        MoqText = if (MinimumOrderQuantity > 0) {
            "MOQ $MinimumOrderQuantity"
        } else {
            ""
        },
        BadgeText = BBLocalization.Current.Get(
            key = "d02a9b8b-001b-4e99-9073-9150016441f3",
            fallback = "Öne Çıkan"
        ),
        ImageUrl = ImageUrlResolver.Resolve(DefaultPicture),
        IsFavorite = isFavorite
    )
}