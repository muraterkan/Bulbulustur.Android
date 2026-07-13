package com.bulbulustur.android.Application.Areas.b2b.Views.Product

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductControllerState
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductGrid
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.R
import com.bulbulustur.android.businesslayer.Core.DTO.B2BProductData

@Composable
fun WholesaleProductListScreen(
    State: ProductControllerState = ProductControllerState(),
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductDetailClick: (Int) -> Unit = {},
    onProductFavoriteClick: (Int) -> Unit = {},
    onRfqClick: (Int) -> Unit = {}
) {
    val products = remember(State.ProductListData?.Products2?.Items) {
        State.ProductListData?.Products2?.Items.orEmpty().map { product ->
            product.ToWholesaleProductListEntry()
        }
    }

    val categoryFilters = remember(products) {
        listOf("Tümü") + products
            .map { product -> product.Card.Category }
            .filter { category -> category.isNotBlank() }
            .distinct()
    }

    val sortFilters = remember {
        listOf(
            "Öne çıkan",
            "Min. sipariş",
            "Yeni tedarik",
            "Hızlı teklif"
        )
    }

    val favoriteStates = remember {
        mutableStateMapOf<Int, Boolean>()
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("Tümü")
    }

    var selectedSort by remember {
        mutableStateOf("Öne çıkan")
    }

    val filteredProducts = remember(
        products,
        searchText,
        selectedCategory,
        selectedSort
    ) {
        val searchResult = if (searchText.isBlank()) {
            products
        } else {
            products.filter { product ->
                product.Card.Title.contains(other = searchText, ignoreCase = true) ||
                        product.Card.Category.contains(other = searchText, ignoreCase = true) ||
                        product.Card.SupplierText.contains(other = searchText, ignoreCase = true)
            }
        }

        val categoryResult = if (selectedCategory == "Tümü") {
            searchResult
        } else {
            searchResult.filter { product ->
                product.Card.Category == selectedCategory
            }
        }

        when (selectedSort) {
            "Min. sipariş" -> {
                categoryResult.sortedBy { product ->
                    product.MinimumOrderValue
                }
            }

            "Yeni tedarik" -> {
                categoryResult.filter { product ->
                    product.IsNew
                }
            }

            "Hızlı teklif" -> {
                categoryResult.filter { product ->
                    product.HasFastQuote
                }
            }

            else -> categoryResult
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                placeholder = "Ürün, firma veya RFQ ara",
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                leadingAction = WholesaleSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick,
                onMessageClick = onMessageClick
            )
        },
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Menu,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        WholesaleBottomNavigationItem.Home -> {
                            onHomeClick()
                        }

                        WholesaleBottomNavigationItem.Menu -> {
                            onMenuClick()
                        }

                        WholesaleBottomNavigationItem.ModeSwitch -> {
                            onModeSwitchClick()
                        }

                        WholesaleBottomNavigationItem.Basket -> {
                            onBasketClick()
                        }

                        WholesaleBottomNavigationItem.Account -> {
                            onAccountClick()
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            WholesaleProductListIntroCard()

            WholesaleProductFilterRow(
                items = categoryFilters,
                selectedItem = selectedCategory,
                modifier = Modifier.padding(
                    top = BBSpacing.SectionGapCompact
                ),
                onItemClick = { category ->
                    selectedCategory = category
                }
            )

            WholesaleProductFilterRow(
                items = sortFilters,
                selectedItem = selectedSort,
                modifier = Modifier.padding(
                    top = BBSpacing.Space3
                ),
                onItemClick = { sort ->
                    selectedSort = sort
                }
            )

            BbSectionHeader(
                title = if (selectedCategory == "Tümü") {
                    "Tüm Toptan ürünler"
                } else {
                    selectedCategory
                },
                subtitle = "${filteredProducts.size} ürün grubu listeleniyor",
                modifier = Modifier.padding(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.SectionGapCompact,
                    end = BBSpacing.PageHorizontal
                )
            )

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
                        product.Card.Id
                    }
                ) { product ->
                    val productId = product.Card.Id

                    val isFavorite = favoriteStates[productId]
                        ?: product.Card.IsFavorite

                    WholesaleProductCard(
                        product = product.Card.copy(
                            IsFavorite = isFavorite
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onProductDetailClick(productId)
                        },
                        onFavoriteClick = {
                            favoriteStates[productId] = !isFavorite
                            onProductFavoriteClick(productId)
                        },
                        onRfqClick = {
                            onRfqClick(productId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleProductListIntroCard() {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal
            ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Text(
                text = "Toptan Ürünler",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Sektörlerden, tedarikçilerden ve teklif akışlarından ürün keşfet.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WholesaleProductFilterRow(
    items: List<String>,
    selectedItem: String,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(
                rememberScrollState()
            )
            .padding(
                horizontal = BBSpacing.PageHorizontal
            ),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.ChipGap
        ),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.ChipGap
        )
    ) {
        items.forEach { item ->
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

@Immutable
private data class WholesaleProductListEntry(
    val Card: WholesaleProductCardModel,
    val MinimumOrderValue: Int,
    val IsNew: Boolean,
    val HasFastQuote: Boolean
)

private fun B2BProductData.ToWholesaleProductListEntry(): WholesaleProductListEntry {
    val priceText = if (Price > 0.0) {
        "₺$Price"
    } else {
        "Teklif iste"
    }

    val moqText = if (MinimumOrderQuantity > 0) {
        "Min. $MinimumOrderQuantity $MinimumOrderUnit"
    } else {
        "Min. sipariş bilgisi yok"
    }

    return WholesaleProductListEntry(
        Card = WholesaleProductCardModel(
            Id = WholesaleProductId,
            Title = ProductName,
            Category = CategoryName,
            PriceText = priceText,
            MoqText = moqText,
            SupplierText = CompanyName,
            BadgeText = "Toptan",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach,
            IsFavorite = false
        ),
        MinimumOrderValue = MinimumOrderQuantity,
        IsNew = false,
        HasFastQuote = true
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    BbTheme {
        WholesaleProductListScreen()
    }
}