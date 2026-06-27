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
import com.bulbulustur.android.R
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

@Composable
fun ProductListScreen(
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
    val products = remember {
        getWholesaleProductListItems()
    }

    val categoryFilters = remember {
        listOf(
            "Tümü",
            "Ambalaj",
            "Makine",
            "Elektronik",
            "Tekstil",
            "Gıda"
        )
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
                product.Card.Title.contains(
                    other = searchText,
                    ignoreCase = true
                ) ||
                        product.Card.Category.contains(
                            other = searchText,
                            ignoreCase = true
                        ) ||
                        product.Card.SupplierText.contains(
                            other = searchText,
                            ignoreCase = true
                        )
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
                    "Tüm toptan ürünler"
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

private fun getWholesaleProductListItems(): List<WholesaleProductListEntry> {
    return listOf(
        WholesaleProductListEntry(
            Card = WholesaleProductCardModel(
                Id = 1,
                Title = "Endüstriyel karton koli seti",
                Category = "Ambalaj",
                PriceText = "₺12,40 / adet",
                MoqText = "Min. 1.000 adet",
                SupplierText = "Anadolu Ambalaj",
                BadgeText = "Toptan",
                ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach,
                IsFavorite = false
            ),
            MinimumOrderValue = 1000,
            IsNew = false,
            HasFastQuote = true
        ),
        WholesaleProductListEntry(
            Card = WholesaleProductCardModel(
                Id = 2,
                Title = "Paslanmaz makine yedek parçası",
                Category = "Makine",
                PriceText = "Teklif iste",
                MoqText = "Min. 50 adet",
                SupplierText = "Delta Makine",
                BadgeText = "RFQ",
                ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar1,
                IsFavorite = false
            ),
            MinimumOrderValue = 50,
            IsNew = false,
            HasFastQuote = true
        ),
        WholesaleProductListEntry(
            Card = WholesaleProductCardModel(
                Id = 3,
                Title = "Elektronik güç modülü",
                Category = "Elektronik",
                PriceText = "₺84,90 / adet",
                MoqText = "Min. 250 adet",
                SupplierText = "Tekno Bileşen",
                BadgeText = "Yeni",
                ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar2,
                IsFavorite = false
            ),
            MinimumOrderValue = 250,
            IsNew = true,
            HasFastQuote = false
        ),
        WholesaleProductListEntry(
            Card = WholesaleProductCardModel(
                Id = 4,
                Title = "Toptan pamuklu kumaş rulosu",
                Category = "Tekstil",
                PriceText = "₺68,00 / metre",
                MoqText = "Min. 500 metre",
                SupplierText = "Mira Tekstil",
                BadgeText = "Toptan",
                ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar3,
                IsFavorite = false
            ),
            MinimumOrderValue = 500,
            IsNew = false,
            HasFastQuote = true
        ),
        WholesaleProductListEntry(
            Card = WholesaleProductCardModel(
                Id = 5,
                Title = "Gıda ambalaj poşeti",
                Category = "Gıda",
                PriceText = "₺0,92 / adet",
                MoqText = "Min. 5.000 adet",
                SupplierText = "Ege Paket",
                BadgeText = "Popüler",
                ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach,
                IsFavorite = false
            ),
            MinimumOrderValue = 5000,
            IsNew = false,
            HasFastQuote = true
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    BbTheme {
        ProductListScreen()
    }
}
