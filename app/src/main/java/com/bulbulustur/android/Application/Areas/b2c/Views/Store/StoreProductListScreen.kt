package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductControllerState
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCard
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCardModel
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductGrid
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductData
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductFilterDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import java.text.NumberFormat
import java.util.Locale

@Composable
fun StoreProductListScreen(
    State: ProductControllerState = ProductControllerState(),
    languageId: Int = 1,
    storeId: Int = 0,
    OnLoadProducts: (
        filters: B2CProductFilterDTO,
        page: Int,
        pageSize: Int
    ) -> Unit = { _, _, _ -> },
    onBackClick: () -> Unit = {},
    onProductClick: (
        productId: Int,
        storeId: Int,
        variantId: Int
    ) -> Unit = { _, _, _ -> },
    onSearchSubmit: (String) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    var selectedSortOption by remember {
        mutableStateOf("Öne çıkan")
    }

    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val initialFilters = remember(languageId, storeId) {
        B2CProductFilterDTO(
            LanguageId = languageId,
            SortOrder = "Name_Desc"
        )
    }

    LaunchedEffect(
        languageId,
        storeId
    ) {
        if (storeId > 0) {
            OnLoadProducts(
                initialFilters,
                1,
                50
            )
        }
    }

    val products = State.StoreProductListData
        ?.Products2
        ?.Items
        .orEmpty()

    val filteredProducts = remember(
        products,
        searchText,
        selectedSortOption
    ) {
        val searchFilteredProducts =
            if (searchText.isBlank()) {
                products
            } else {
                products.filter { product ->
                    product.ProductName.contains(
                        other = searchText,
                        ignoreCase = true
                    ) ||
                            product.CategoryName.contains(
                                other = searchText,
                                ignoreCase = true
                            ) ||
                            product.BrandName.contains(
                                other = searchText,
                                ignoreCase = true
                            )
                }
            }

        when (selectedSortOption) {
            "En düşük fiyat" -> searchFilteredProducts.sortedBy { product ->
                product.Price
            }

            "En yüksek fiyat" -> searchFilteredProducts.sortedByDescending { product ->
                product.Price
            }

            "Yeni Gelenler" -> searchFilteredProducts.sortedByDescending { product ->
                product.InsertedDate
            }

            else -> searchFilteredProducts
        }
    }

    val sortOptions = remember {
        listOf(
            "Öne çıkan",
            "En düşük fiyat",
            "En yüksek fiyat",
            "Yeni Gelenler"
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onSearchClick = {
                    onSearchSubmit(searchText)
                },
                onMenuClick = onBackClick,
                onFavoriteClick = {},
                leadingAction = RetailSearchHeaderLeadingAction.Back
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Spacer(
                    modifier = Modifier.height(
                        innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact
                    )
                )

                BbSectionHeader(
                    title = "Mağaza Ürünleri",
                    subtitle = "Bu mağazaya ait ürünleri inceleyin."
                )

                StoreProductListHorizontalFilters(
                    items = sortOptions,
                    selectedItem = selectedSortOption,
                    onItemClick = {
                        selectedSortOption = it
                    }
                )

                StoreProductListResultHeader(
                    productCount = State.StoreProductListData
                        ?.Products2
                        ?.TotalItemCount
                        ?: filteredProducts.size
                )
            }

            when {
                State.IsLoading &&
                        products.isEmpty() -> {
                    StoreProductListLoading()
                }

                !State.ErrorMessage.isNullOrBlank() &&
                        products.isEmpty() -> {
                    StoreProductListError(
                        message = State.ErrorMessage ?: "Ürünler yüklenemedi."
                    )
                }

                filteredProducts.isEmpty() -> {
                    StoreProductListEmpty()
                }

                else -> {
                    BbProductGrid(
                        contentPadding = PaddingValues(
                            start = BBSpacing.PageHorizontal,
                            top = BBSpacing.Space3,
                            end = BBSpacing.PageHorizontal,
                            bottom = BBSpacing.PageBottom
                        ),
                        horizontalSpacing = BBSpacing.Space3,
                        verticalSpacing = BBSpacing.Space4
                    ) {
                        items(
                            items = filteredProducts,
                            key = { product ->
                                "${product.ProductId}-${product.VariantId}-${product.StoreId}"
                            }
                        ) { product ->
                            val isFavorite = favoriteProductIds.contains(
                                product.ProductId
                            )

                            BbProductCard(
                                product = product.ToStoreProductCardModel(
                                    isFavorite = isFavorite
                                ),
                                onClick = {
                                    onProductClick(
                                        product.ProductId,
                                        product.StoreId,
                                        product.VariantId
                                    )
                                },
                                onFavoriteClick = {
                                    favoriteProductIds =
                                        if (isFavorite) {
                                            favoriteProductIds - product.ProductId
                                        } else {
                                            favoriteProductIds + product.ProductId
                                        }
                                },
                                onAddToBasketClick = {
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StoreProductListHorizontalFilters(
    items: List<String>,
    selectedItem: String,
    onItemClick: (String) -> Unit
) {
    androidx.compose.foundation.lazy.LazyRow(
        contentPadding = PaddingValues(
            horizontal = BBSpacing.PageHorizontal
        ),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space2
        )
    ) {
        items(
            count = items.size,
            key = { index ->
                items[index]
            }
        ) { index ->
            val item = items[index]

            BbChip(
                text = item,
                selected = selectedItem == item,
                onClick = {
                    onItemClick(item)
                }
            )
        }
    }
}

@Composable
private fun StoreProductListResultHeader(
    productCount: Int
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Text(
            modifier = Modifier,
            text = "$productCount ürün listeleniyor",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Ürün kartına dokunarak detay sayfasına geçebilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StoreProductListLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space3
            )
        )

        Text(
            text = "Mağaza ürünleri yükleniyor...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StoreProductListError(
    message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ürünler alınamadı",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space2
            )
        )

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StoreProductListEmpty() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ürün bulunamadı",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space2
            )
        )

        Text(
            text = "Arama veya filtre seçimini değiştirerek tekrar deneyebilirsin.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun B2CProductData.ToStoreProductCardModel(
    isFavorite: Boolean
): BbProductCardModel {
    return BbProductCardModel(
        Id = ProductId,
        Name = ProductName,
        StoreName = "",
        ImageUrl = ResolveStoreProductImageUrl(DefaultPicture),
        PriceText = FormatStoreProductPrice(
            price = Price,
            currencySymbol = CurrencySymbol
        ),
        OldPriceText = "",
        BadgeText = CategoryName,
        RatingText = "",
        CargoText = "",
        IsFavorite = isFavorite
    )
}

private fun ResolveStoreProductImageUrl(
    picture: String
): String {
    val normalizedPicture = picture.trim()

    if (normalizedPicture.isBlank()) {
        return ""
    }

    if (
        normalizedPicture.startsWith("http://", ignoreCase = true) ||
        normalizedPicture.startsWith("https://", ignoreCase = true)
    ) {
        return normalizedPicture
    }

    val serverBaseUrl = ApiRoutes.B2C_PRODUCT_BASE_URL
        .substringBefore("/api/")
        .trimEnd('/')

    return "$serverBaseUrl/${normalizedPicture.trimStart('/')}"
}

private fun FormatStoreProductPrice(
    price: Double,
    currencySymbol: String
): String {
    val formatter = NumberFormat.getNumberInstance(
        Locale("tr", "TR")
    )

    formatter.minimumFractionDigits = 2
    formatter.maximumFractionDigits = 2

    return "${currencySymbol.ifBlank { "₺" }}${formatter.format(price)}"
}

@Preview(showBackground = true)
@Composable
private fun StoreProductListScreenPreview() {
    BbTheme {
        StoreProductListScreen()
    }
}