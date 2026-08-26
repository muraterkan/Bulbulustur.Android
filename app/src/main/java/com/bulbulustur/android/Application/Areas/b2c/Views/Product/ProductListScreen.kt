package com.bulbulustur.android.Application.Areas.b2c.Views.Product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCard
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCardModel
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductGrid
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductData
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductFilterDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductListScreen(
    State: ProductControllerState = ProductControllerState(),
    languageId: Int = 1,
    productCategoryId: Int = 0,
    categoryIds: List<Int> = emptyList(),
    OnLoadProducts: (
        filters: B2CProductFilterDTO,
        page: Int,
        pageSize: Int
    ) -> Unit = { _, _, _ -> },
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductDetailClick: (
        productId: Int,
        storeId: Int,
        variantId: Int
    ) -> Unit = { _, _, _ -> },
    onProductFavoriteClick: (B2CProductData) -> Unit = {},
    onAddToBasketClick: (Int) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    var selectedSortOption by remember {
        mutableStateOf(BBLocalization.Current.Get(key = "d02a9b8b-001b-4e99-9073-9150016441f3", fallback = "Öne çıkan"))
    }

    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(
            emptySet()
        )
    }

    val initialFilters =
        remember(languageId, productCategoryId, categoryIds) {
            B2CProductFilterDTO(
                ProductCategoryId = productCategoryId,
                Categories = categoryIds.ifEmpty { listOf(productCategoryId) },
                LanguageId = languageId,
                SortOrder = "Name_Desc"
            )
        }

    LaunchedEffect(
        languageId,
        productCategoryId,
        categoryIds
    ) {
        OnLoadProducts(
            initialFilters,
            1,
            50
        )
    }

    val products =
        State.ProductListData
            ?.Products2
            ?.Items
            .orEmpty()

    val filteredProducts =
        remember(
            products,
            searchText,
            selectedSortOption
        ) {
            val searchFilteredProducts =
                if (
                    searchText.isBlank()
                ) {
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

            when (
                selectedSortOption
            ) {
                BBLocalization.Current.Get(key = "c4fa944e-eb81-40dd-80f6-4209c78db57c", fallback = "En düşük fiyat") -> {
                    searchFilteredProducts.sortedBy { product ->
                        product.Price
                    }
                }

                BBLocalization.Current.Get(key = "a6913ebd-df97-42c0-9fa5-2138d2cd73e1", fallback = "En yüksek fiyat") -> {
                    searchFilteredProducts.sortedByDescending { product ->
                        product.Price
                    }
                }

                BBLocalization.Current.Get(key = "6788b820-f4b2-470b-92f8-7a8470387d4e", fallback = "Yeni gelenler") -> {
                    searchFilteredProducts.sortedByDescending { product ->
                        product.InsertedDate
                    }
                }

                else -> {
                    searchFilteredProducts
                }
            }
        }

    val sortOptions =
        remember {
            listOf(
                BBLocalization.Current.Get(key = "d02a9b8b-001b-4e99-9073-9150016441f3", fallback = "Öne çıkan"),
                BBLocalization.Current.Get(key = "c4fa944e-eb81-40dd-80f6-4209c78db57c", fallback = "En düşük fiyat"),
                BBLocalization.Current.Get(key = "a6913ebd-df97-42c0-9fa5-2138d2cd73e1", fallback = "En yüksek fiyat"),
                BBLocalization.Current.Get(key = "6788b820-f4b2-470b-92f8-7a8470387d4e", fallback = "Yeni gelenler")
            )
        }

    Scaffold(
        modifier =
            Modifier.fillMaxSize(),
        containerColor =
            MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText =
                    searchText,
                onSearchTextChange = {
                    searchText =
                        it
                },
                onMenuClick =
                    onMenuClick,
                onFavoriteClick =
                    onFavoriteClick,
                onMessageClick =
                    onMessageClick,
                placeholder =
                    BBLocalization.Current.Get(key = "e4f653c3-8828-4934-aa3b-959cede38feb", fallback = "Ürün, kategori veya marka ara"),
                onSearchClick = {
                    onSearchClick(
                        searchText
                    )
                },
                onClearClick = {
                    searchText =
                        ""
                },
                leadingAction =
                    RetailSearchHeaderLeadingAction.Back,
                onBackClick =
                    onBackClick
            )
        },
        bottomBar = {
            RetailBottomNavigation(
                selectedItem =
                    RetailBottomNavigationItem.Menu,
                onItemClick = { selectedItem ->
                    when (
                        selectedItem
                    ) {
                        RetailBottomNavigationItem.Home -> {
                            onHomeClick()
                        }

                        RetailBottomNavigationItem.Menu -> {
                            onMenuClick()
                        }

                        RetailBottomNavigationItem.ModeSwitch -> {
                            onModeSwitchClick()
                        }

                        RetailBottomNavigationItem.Basket -> {
                            onBasketClick()
                        }

                        RetailBottomNavigationItem.Account -> {
                            onAccountClick()
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.background
                    )
                    .padding(
                        innerPadding
                    )
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            start =
                                BBSpacing.PageHorizontal,
                            top =
                                BBSpacing.PageTopCompact,
                            end =
                                BBSpacing.PageHorizontal
                        ),
                verticalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
            ) {
                BbSectionHeader(
                    title =
                        State.ProductListData
                            ?.CategoryName
                            ?.takeIf {
                                it.isNotBlank()
                            }
                            ?: BBLocalization.Current.Get(key = "824f1dda-7e98-4590-981e-6b7c34826978", fallback = "Perakende Ürünler"),
                    subtitle =
                        State.ProductListData
                            ?.CategoryDescription
                            ?.takeIf {
                                it.isNotBlank()
                            }
                            ?: BBLocalization.Current.Get(key = "54b5e4b6-e6e5-42bf-95f2-b0574db5f622", fallback = "Kategorilerden, mağazalardan ve kampanyalardan ürün keşfet.")
                )
            }

            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )

            RetailProductListHorizontalFilters(
                items =
                    sortOptions,
                selectedItem =
                    selectedSortOption,
                onItemClick = {
                    selectedSortOption =
                        it
                }
            )

            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )

            RetailProductListResultHeader(
                productCount =
                    State.ProductListData
                        ?.Products2
                        ?.TotalItemCount
                        ?: filteredProducts.size
            )

            when {
                State.IsLoading &&
                        products.isEmpty() -> {
                    RetailProductListLoading()
                }

                !State.ErrorMessage.isNullOrBlank() &&
                        products.isEmpty() -> {
                    RetailProductListError(
                        message =
                            State.ErrorMessage
                                ?: BBLocalization.Current.Get(key = "0a4c4d79-c42d-473a-8f38-23d57c89bbbe", fallback = "Ürünler yüklenemedi.")
                    )
                }

                filteredProducts.isEmpty() -> {
                    RetailProductListEmpty()
                }

                else -> {
                    BbProductGrid(
                        contentPadding =
                            PaddingValues(
                                start =
                                    BBSpacing.PageHorizontal,
                                top =
                                    BBSpacing.Space3,
                                end =
                                    BBSpacing.PageHorizontal,
                                bottom =
                                    BBSpacing.PageBottom
                            ),
                        horizontalSpacing =
                            BBSpacing.Space3,
                        verticalSpacing =
                            BBSpacing.Space4
                    ) {
                        items(
                            items =
                                filteredProducts,
                            key = { product ->
                                "${product.ProductId}-${product.VariantId}-${product.StoreId}"
                            }
                        ) { product ->
                            val isFavorite =
                                favoriteProductIds.contains(
                                    product.ProductId
                                )

                            BbProductCard(
                                product =
                                    product.ToProductCardModel(
                                        isFavorite =
                                            isFavorite
                                    ),
                                onClick = {
                                    onProductDetailClick(
                                        product.ProductId,
                                        product.StoreId,
                                        product.VariantId
                                    )
                                },
                                onFavoriteClick = {
                                    favoriteProductIds =
                                        if (
                                            isFavorite
                                        ) {
                                            favoriteProductIds -
                                                    product.ProductId
                                        } else {
                                            favoriteProductIds +
                                                    product.ProductId
                                        }

                                    onProductFavoriteClick(
                                        product
                                    )
                                },
                                onAddToBasketClick = {
                                    onAddToBasketClick(
                                        product.ProductVariantPriceId
                                    )
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
private fun RetailProductListHorizontalFilters(
    items: List<String>,
    selectedItem: String,
    onItemClick: (String) -> Unit
) {
    LazyRow(
        modifier =
            Modifier.fillMaxWidth(),
        contentPadding =
            PaddingValues(
                horizontal =
                    BBSpacing.PageHorizontal
            ),
        horizontalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        items(
            items =
                items,
            key = { item ->
                item
            }
        ) { item ->
            BbChip(
                text =
                    item,
                selected =
                    selectedItem == item,
                onClick = {
                    onItemClick(
                        item
                    )
                }
            )
        }
    }
}

@Composable
private fun RetailProductListResultHeader(
    productCount: Int
) {
    androidx.compose.foundation.layout.Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    start =
                        BBSpacing.PageHorizontal,
                    end =
                        BBSpacing.PageHorizontal,
                    top =
                        BBSpacing.Space1,
                    bottom =
                        BBSpacing.Space1
                ),
        verticalAlignment =
            Alignment.CenterVertically
    ) {
        Column(
            modifier =
                Modifier.weight(
                    1f
                ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space1
                )
        ) {
            Text(
                text =
                    BBLocalization.Current.Get(key = "6cf7b92f-05e7-4ac7-be8c-ce98d8bf20c5", fallback = "Tüm Ürünler"),
                style =
                    MaterialTheme.typography.titleSmall,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onSurface
            )

            Text(
                text =
                    "$productCount ürün listeleniyor",
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text =
                BBLocalization.Current.Get(key = "bb4d65cd-d8cf-485d-9689-4f44c7353dfa", fallback = "Filtrele"),
            style =
                MaterialTheme.typography.labelMedium,
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun RetailProductListLoading() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.PageHorizontal
                ),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            )
    ) {
        CircularProgressIndicator()

        Text(
            text =
                BBLocalization.Current.Get(key = "0953041e-609f-40de-90b7-ced652c5cb95", fallback = ""),
            style =
                MaterialTheme.typography.bodyMedium,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RetailProductListError(
    message: String
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.PageHorizontal
                ),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        Text(
            text =
                BBLocalization.Current.Get(key = "0a4c4d79-c42d-473a-8f38-23d57c89bbbe", fallback = "Ürünler yüklenemedi"),
            style =
                MaterialTheme.typography.titleMedium,
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.error
        )

        Text(
            text =
                message,
            style =
                MaterialTheme.typography.bodyMedium,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RetailProductListEmpty() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.PageHorizontal
                ),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        Text(
            text =
                BBLocalization.Current.Get(key = "9afc052e-e2bf-413d-81c6-461bfc3c9174", fallback = "Ürün bulunamadı"),
            style =
                MaterialTheme.typography.titleMedium,
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onSurface
        )

        Text(
            text =
                BBLocalization.Current.Get(key = "59f50847-365f-4959-b050-641d7c1e18cc", fallback = "Arama veya filtre seçimini değiştirerek tekrar deneyebilirsin."),
            style =
                MaterialTheme.typography.bodyMedium,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun B2CProductData.ToProductCardModel(
    isFavorite: Boolean
): BbProductCardModel {
    return BbProductCardModel(
        Id =
            ProductId,
        Name =
            ProductName,
        StoreName =
            "",
        ImageUrl =
            ImageUrlResolver.Resolve(DefaultPicture),
        PriceText =
            FormatProductPrice(
                price =
                    Price,
                currencySymbol =
                    CurrencySymbol
            ),
        OldPriceText =
            "",
        BadgeText =
            "",
        RatingText =
            "",
        CargoText =
            "",
        IsFavorite =
            isFavorite
    )
}

private fun FormatProductPrice(
    price: Double,
    currencySymbol: String
): String {
    val formatter =
        NumberFormat.getNumberInstance(
            Locale(
                "tr",
                "TR"
            )
        ).apply {
            minimumFractionDigits =
                2

            maximumFractionDigits =
                2
        }

    return buildString {
        append(
            currencySymbol
        )

        if (
            currencySymbol.isNotBlank()
        ) {
            append(
                " "
            )
        }

        append(
            formatter.format(
                price
            )
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ProductListScreenPreview() {
    BbTheme {
        ProductListScreen()
    }
}