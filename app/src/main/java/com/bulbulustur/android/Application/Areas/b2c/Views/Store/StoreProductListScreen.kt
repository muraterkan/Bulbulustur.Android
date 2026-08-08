package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
        mutableStateOf(BBLocalization.Current.Get(key = "d02a9b8b-001b-4e99-9073-9150016441f3", fallback = "Öne çıkan"))
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
            BBLocalization.Current.Get(key = "c4fa944e-eb81-40dd-80f6-4209c78db57c", fallback = "En düşük fiyat") -> searchFilteredProducts.sortedBy { product ->
                product.Price
            }

            BBLocalization.Current.Get(key = "a6913ebd-df97-42c0-9fa5-2138d2cd73e1", fallback = "En yüksek fiyat") -> searchFilteredProducts.sortedByDescending { product ->
                product.Price
            }

            BBLocalization.Current.Get(key = "6788b820-f4b2-470b-92f8-7a8470387d4e", fallback = "Yeni Gelenler") -> searchFilteredProducts.sortedByDescending { product ->
                product.InsertedDate
            }

            else -> searchFilteredProducts
        }
    }

    val sortOptions = remember {
        listOf(
            BBLocalization.Current.Get(key = "d02a9b8b-001b-4e99-9073-9150016441f3", fallback = "Öne çıkan"),
            BBLocalization.Current.Get(key = "c4fa944e-eb81-40dd-80f6-4209c78db57c", fallback = "En düşük fiyat"),
            BBLocalization.Current.Get(key = "a6913ebd-df97-42c0-9fa5-2138d2cd73e1", fallback = "En yüksek fiyat"),
            BBLocalization.Current.Get(key = "6788b820-f4b2-470b-92f8-7a8470387d4e", fallback = "Yeni Gelenler")
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
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.PageHorizontal
                    ),
                    title = BBLocalization.Current.Get(key = "c8bd0ce4-56e5-4b39-bca3-33a6fcfbdcdf", fallback = "Mağaza Ürünleri")
                )

                StoreProductListHorizontalFilters(
                    items = sortOptions,
                    selectedItem = selectedSortOption,
                    onItemClick = {
                        selectedSortOption = it
                    }
                )

                StoreProductListResultHeader(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.PageHorizontal
                    ),
                    productCount = State.StoreProductListData
                        ?.Products2
                        ?.TotalItemCount
                        ?: filteredProducts.size
                )

                HorizontalDivider(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.PageHorizontal
                    ),
                    color = MaterialTheme.colorScheme.outlineVariant
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
                        message = State.ErrorMessage ?: BBLocalization.Current.Get(key = "0a4c4d79-c42d-473a-8f38-23d57c89bbbe", fallback = "Ürünler yüklenemedi.")
                    )
                }

                filteredProducts.isEmpty() -> {
                    StoreProductListEmpty()
                }

                else -> {
                    BbProductGrid(
                        contentPadding = PaddingValues(
                            start = BBSpacing.PageHorizontal,
                            top = BBSpacing.Space4,
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
    modifier: Modifier = Modifier,
    productCount: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${FormatStoreProductCount(productCount)} ürün",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = BBLocalization.Current.Get(key = "845e87d3-d457-473f-8359-3efbf74746d4", fallback = "Mağaza ürünleri"),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun FormatStoreProductCount(
    count: Int
): String {
    return NumberFormat.getIntegerInstance(
        Locale("tr", "TR")
    ).format(count)
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
            text = BBLocalization.Current.Get(key = "f1849aad-bdb6-4e9e-8e93-ec17941da932", fallback = "Mağaza ürünleri yükleniyor..."),
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
            text = BBLocalization.Current.Get(key = "0a4c4d79-c42d-473a-8f38-23d57c89bbbe", fallback = "Ürünler alınamadı"),
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
            text = BBLocalization.Current.Get(key = "9afc052e-e2bf-413d-81c6-461bfc3c9174", fallback = "Ürün bulunamadı"),
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
            text = BBLocalization.Current.Get(key = "59f50847-365f-4959-b050-641d7c1e18cc", fallback = "Arama veya filtre seçimini değiştirerek tekrar deneyebilirsin."),
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
        BadgeText = CategoryName.orEmpty(),
        RatingText = "",
        CargoText = "",
        IsFavorite = isFavorite
    )
}
/*
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
*/

private fun ResolveStoreProductImageUrl(
    picture: String
): String {
    return ApiRoutes.B2C_TEST_PRODUCT_IMAGE_URL
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