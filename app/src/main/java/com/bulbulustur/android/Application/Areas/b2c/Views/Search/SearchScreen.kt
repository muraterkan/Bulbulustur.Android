package com.bulbulustur.android.Application.Areas.b2c.Views.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCard
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCardModel
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    initialSearchText: String = "",
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onSearchSubmit: (String, RetailSearchType) -> Unit = { _, _ -> },
    onProductClick: (RetailSearchProductItem) -> Unit = {},
    onCategoryClick: (RetailSearchCategoryItem) -> Unit = {},
    onBrandClick: (RetailSearchBrandItem) -> Unit = {},
    onStoreClick: (RetailSearchStoreItem) -> Unit = {},
    productResults: List<ProductDTO> = emptyList(),
    hasProductSearch: Boolean = false
) {
    var searchText by remember(initialSearchText) {
        mutableStateOf(initialSearchText)
    }

    var lastSubmittedSearchText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(searchText) {
        val currentSearchText = searchText.trim()

        if (currentSearchText.length < 3) {
            return@LaunchedEffect
        }

        if (currentSearchText == lastSubmittedSearchText) {
            return@LaunchedEffect
        }

        delay(500)

        val latestSearchText = searchText.trim()

        if (
            latestSearchText.length >= 3 &&
            latestSearchText != lastSubmittedSearchText
        ) {
            lastSubmittedSearchText = latestSearchText
            android.util.Log.d("BB_SEARCH", "Retail auto search submit key=$latestSearchText")

            onSearchSubmit(
                latestSearchText,
                RetailSearchType.Product
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                    android.util.Log.d("BB_SEARCH", "Retail text change=$it")
                },
                placeholder = BBLocalization.Current.Get(
                    key = "e4f653c3-8828-4934-aa3b-959cede38feb",
                    fallback = "Ürün, kategori veya marka ara"
                ),
                onSearchClick = {
                    val currentSearchText = searchText.trim()
                    android.util.Log.d("BB_SEARCH", "Retail header search click key=$currentSearchText")

                    if (currentSearchText.length >= 3) {
                        lastSubmittedSearchText = currentSearchText

                        onSearchSubmit(
                            currentSearchText,
                            RetailSearchType.Product
                        )
                    }
                },
                onClearClick = {
                    searchText = ""
                    lastSubmittedSearchText = ""
                },
                onMenuClick = onBackClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                leadingAction = RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            RetailBottomNavigation(
                selectedItem = RetailBottomNavigationItem.Home,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        RetailBottomNavigationItem.Home -> onHomeClick()
                        RetailBottomNavigationItem.Menu -> onMenuClick()
                        RetailBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        RetailBottomNavigationItem.Basket -> onBasketClick()
                        RetailBottomNavigationItem.Account -> onAccountClick()
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "71aeca46-c5db-45dc-be29-b8041d0a30aa",
                        fallback = "Ürün sonuçları"
                    ),
                    subtitle = if (hasProductSearch) {
                        BBLocalization.Current.Get(
                            key = "bd212e25-400e-41b6-bc99-51a1a0b8fc75",
                            fallback = "Aramana yakın ürün sonuçları."
                        )
                    } else {
                        BBLocalization.Current.Get(
                            key = "0f1a5891-6f1e-49bc-8e19-80150640d9b1",
                            fallback = "Ürün aramak için üstteki arama alanını kullanın."
                        )
                    }
                )
            }

            when {
                !hasProductSearch -> {
                    item {
                        SearchInfoCard(
                            title = BBLocalization.Current.Get(
                                key = "0e2dc829-9eb6-4c30-a0d8-321e3a6d4b89",
                                fallback = "Arama yapın"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "4c5a8e2b-6de8-4f6c-b88a-43641c33b9ac",
                                fallback = "Ürünleri bulmak için en az 3 karakterlik bir arama terimi girin."
                            )
                        )
                    }
                }

                productResults.isEmpty() -> {
                    item {
                        SearchInfoCard(
                            title = BBLocalization.Current.Get(
                                key = "f8c4d4d4-6a99-47ea-b1f8-2f0cd78ad553",
                                fallback = "Ürün bulunamadı"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "9bde1108-7242-4562-8e63-2e4c42e8d8da",
                                fallback = "Arama kriterine uygun ürün bulunamadı."
                            )
                        )
                    }
                }

                else -> {
                    itemsIndexed(
                        items = productResults,
                        key = { index, product ->
                            "retail-search-product-${product.ProductId}-${product.VariantId}-${product.StoreId}-$index"
                        }
                    ) { _, product ->
                        BbProductCard(
                            product = product.toSearchProductCardModel(),
                            onClick = {
                                onProductClick(product.toRetailSearchProductItem())
                            }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@Composable
private fun SearchInfoCard(
    title: String,
    description: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Inventory2,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

enum class RetailSearchType {
    Product,
    Category,
    Brand,
    Store
}

data class RetailSearchProductItem(
    val id: Int,
    val name: String,
    val storeName: String,
    val priceText: String,
    val imageText: String
)

data class RetailSearchCategoryItem(
    val id: Int = 0
)

data class RetailSearchBrandItem(
    val id: Int = 0
)

data class RetailSearchStoreItem(
    val id: Int = 0
)

private fun ProductDTO.toRetailSearchProductItem(): RetailSearchProductItem {
    val safeProductName = ProductName.orEmpty().trim()
    val safeSeoTitle = SeoTitle.orEmpty().trim()
    val safeStore = Store.orEmpty().trim()
    val safeCategoryName = CategoryName.orEmpty().trim()
    val safeCurrencySymbol = CurrencySymbol.orEmpty().trim()

    val name = safeProductName
        .takeIf { it.isNotBlank() }
        ?: safeSeoTitle.takeIf { it.isNotBlank() }
        ?: BBLocalization.Current.Get(
            key = "c1b573ef-6e74-4646-8512-37ab80572c47",
            fallback = "Ürün"
        )

    val storeName = safeStore
        .takeIf { it.isNotBlank() }
        ?: safeCategoryName.takeIf { it.isNotBlank() }
        ?: BBLocalization.Current.Get(
            key = "d7ef8746-7182-43ef-8c73-64a50ae37c1b",
            fallback = "Mağaza bilgisi yok"
        )

    val currencySymbol = safeCurrencySymbol
        .takeIf { it.isNotBlank() }
        ?: when (CurrencyId) {
            1 -> "₺"
            else -> "₺"
        }

    val priceText = if (Price > 0.0) {
        "$currencySymbol$Price"
    } else {
        ""
    }

    val imageText = name
        .trim()
        .take(2)
        .uppercase()
        .ifBlank { "P" }

    return RetailSearchProductItem(
        id = ProductId,
        name = name,
        storeName = storeName,
        priceText = priceText,
        imageText = imageText
    )
}

private fun ProductDTO.toSearchProductCardModel(): BbProductCardModel {
    val name = ProductName.orEmpty().trim()
        .ifBlank { SeoTitle.orEmpty().trim() }
        .ifBlank {
            BBLocalization.Current.Get(
                key = "c1b573ef-6e74-4646-8512-37ab80572c47",
                fallback = "Ürün"
            )
        }

    val storeName = Store.orEmpty().trim()
        .ifBlank { CategoryName.orEmpty().trim() }
        .ifBlank {
            BBLocalization.Current.Get(
                key = "d7ef8746-7182-43ef-8c73-64a50ae37c1b",
                fallback = "Mağaza bilgisi yok"
            )
        }

    val currencySymbol = CurrencySymbol.orEmpty().trim().ifBlank { "₺" }

    return BbProductCardModel(
        Id = ProductId,
        Name = name,
        StoreName = storeName,
        PriceText = if (Price > 0.0) "$currencySymbol$Price" else "",
        ImageUrl = ImageUrlResolver.Resolve(DefaultPicture.orEmpty().trim()),
        IsFavorite = false
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    MaterialTheme {
        SearchScreen()
    }
}