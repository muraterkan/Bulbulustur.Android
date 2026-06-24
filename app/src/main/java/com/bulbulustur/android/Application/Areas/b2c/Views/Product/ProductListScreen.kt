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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.R
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCard
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCardModel
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductGrid
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun ProductListScreen(
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductDetailClick: (Int) -> Unit = {},
    onProductFavoriteClick: (Int) -> Unit = {},
    onAddToBasketClick: (Int) -> Unit = {}
) {
    val products = remember {
        getRetailProductListItems()
    }

    val categories = remember {
        listOf(
            "Tümü",
            "Ayakkabı",
            "Giyim",
            "Çanta",
            "Elektronik",
            "Ev ve yaşam"
        )
    }

    val sortOptions = remember {
        listOf(
            "Öne çıkan",
            "En düşük fiyat",
            "En yüksek fiyat",
            "Yeni gelenler"
        )
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("Tümü")
    }

    var selectedSortOption by remember {
        mutableStateOf("Öne çıkan")
    }

    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val filteredProducts = remember(
        searchText,
        selectedCategory,
        selectedSortOption,
        products
    ) {
        val searchFilteredProducts = if (
            searchText.isBlank()
        ) {
            products
        } else {
            products.filter { product ->
                product.Name.contains(
                    other = searchText,
                    ignoreCase = true
                ) ||
                        product.StoreName.contains(
                            other = searchText,
                            ignoreCase = true
                        ) ||
                        product.CategoryName.contains(
                            other = searchText,
                            ignoreCase = true
                        )
            }
        }

        val categoryFilteredProducts = if (
            selectedCategory == "Tümü"
        ) {
            searchFilteredProducts
        } else {
            searchFilteredProducts.filter { product ->
                product.CategoryName == selectedCategory
            }
        }

        when (selectedSortOption) {
            "En düşük fiyat" -> {
                categoryFilteredProducts.sortedBy { product ->
                    product.PriceValue
                }
            }

            "En yüksek fiyat" -> {
                categoryFilteredProducts.sortedByDescending { product ->
                    product.PriceValue
                }
            }

            "Yeni gelenler" -> {
                categoryFilteredProducts.filter { product ->
                    product.IsNew
                }
            }

            else -> {
                categoryFilteredProducts
            }
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
                },
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = "Ürün, kategori veya marka ara",
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                leadingAction = RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            RetailBottomNavigation(
                selectedItem = RetailBottomNavigationItem.Menu,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
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
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                BbSectionHeader(
                    title = "Perakende Ürünler",
                    subtitle = "Kategorilerden, mağazalardan ve kampanyalardan ürün keşfet."
                )
            }

            Spacer(
                modifier = Modifier.height(
                    BBSpacing.Space3
                )
            )

            RetailProductListHorizontalFilters(
                items = categories,
                selectedItem = selectedCategory,
                onItemClick = {
                    selectedCategory = it
                }
            )

            Spacer(
                modifier = Modifier.height(
                    BBSpacing.Space2
                )
            )

            RetailProductListHorizontalFilters(
                items = sortOptions,
                selectedItem = selectedSortOption,
                onItemClick = {
                    selectedSortOption = it
                }
            )

            Spacer(
                modifier = Modifier.height(
                    BBSpacing.Space3
                )
            )

            RetailProductListResultHeader(
                productCount = filteredProducts.size,
                selectedCategory = selectedCategory
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
                        product.Id
                    }
                ) { product ->
                    val isFavorite = favoriteProductIds.contains(
                        product.Id
                    )

                    BbProductCard(
                        product = BbProductCardModel(
                            Id = product.Id,
                            Name = product.Name,
                            StoreName = product.StoreName,
                            ImageResId = product.ImageResId,
                            PriceText = product.PriceText,
                            OldPriceText = product.OldPriceText,
                            BadgeText = product.BadgeText,
                            RatingText = product.RatingText,
                            CargoText = product.CargoText,
                            IsFavorite = isFavorite
                        ),
                        onClick = {
                            onProductDetailClick(
                                product.Id
                            )
                        },
                        onFavoriteClick = {
                            favoriteProductIds = if (
                                isFavorite
                            ) {
                                favoriteProductIds - product.Id
                            } else {
                                favoriteProductIds + product.Id
                            }

                            onProductFavoriteClick(
                                product.Id
                            )
                        },
                        onAddToBasketClick = {
                            onAddToBasketClick(
                                product.Id
                            )
                        }
                    )
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
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = BBSpacing.PageHorizontal
        ),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space2
        )
    ) {
        items(
            items = items,
            key = { item ->
                item
            }
        ) { item ->
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
private fun RetailProductListResultHeader(
    productCount: Int,
    selectedCategory: String
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal,
                top = BBSpacing.Space1,
                bottom = BBSpacing.Space1
            ),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Text(
                text = if (
                    selectedCategory == "Tümü"
                ) {
                    "Tüm Ürünler"
                } else {
                    selectedCategory
                },
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "$productCount ürün listeleniyor",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = "Filtrele",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Immutable
data class RetailProductListItem(
    val Id: Int,
    val Name: String,
    val StoreName: String,
    val CategoryName: String,
    val ImageResId: Int,
    val PriceText: String,
    val OldPriceText: String,
    val PriceValue: Double,
    val RatingText: String,
    val CargoText: String,
    val BadgeText: String,
    val IsNew: Boolean
)

private fun getRetailProductListItems(): List<RetailProductListItem> {
    return listOf(
        RetailProductListItem(
            Id = 1,
            Name = "Comfort bayan terlik Ortobella T05",
            StoreName = "Ortobella Store",
            CategoryName = "Ayakkabı",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach,
            PriceText = "â‚º3.750,00",
            OldPriceText = "",
            PriceValue = 3750.00,
            RatingText = "4.8",
            CargoText = "Hızlı kargo",
            BadgeText = "Yeni",
            IsNew = true
        ),
        RetailProductListItem(
            Id = 2,
            Name = "Comfort kadın günlük terlik",
            StoreName = "Ortobella Store",
            CategoryName = "Ayakkabı",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar1,
            PriceText = "â‚º3.450,00",
            OldPriceText = "â‚º3.750,00",
            PriceValue = 3450.00,
            RatingText = "4.7",
            CargoText = "Ücretsiz kargo",
            BadgeText = "%8",
            IsNew = true
        ),
        RetailProductListItem(
            Id = 3,
            Name = "Hakiki deri kadın comfort terlik",
            StoreName = "Ortobella Store",
            CategoryName = "Ayakkabı",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar2,
            PriceText = "â‚º3.590,00",
            OldPriceText = "",
            PriceValue = 3590.00,
            RatingText = "4.6",
            CargoText = "",
            BadgeText = "",
            IsNew = false
        ),
        RetailProductListItem(
            Id = 4,
            Name = "Kadın anatomik taban günlük terlik",
            StoreName = "Ortobella Store",
            CategoryName = "Ayakkabı",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar3,
            PriceText = "â‚º3.290,00",
            OldPriceText = "â‚º3.690,00",
            PriceValue = 3290.00,
            RatingText = "4.5",
            CargoText = "Hızlı kargo",
            BadgeText = "%11",
            IsNew = true
        ),
        RetailProductListItem(
            Id = 5,
            Name = "Kadın klasik sneaker ayakkabı",
            StoreName = "Ortobella Store",
            CategoryName = "Ayakkabı",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach,
            PriceText = "â‚º2.899,90",
            OldPriceText = "â‚º3.199,90",
            PriceValue = 2899.90,
            RatingText = "4.4",
            CargoText = "Ücretsiz kargo",
            BadgeText = "%10",
            IsNew = false
        ),
        RetailProductListItem(
            Id = 6,
            Name = "Rahat taban kadın yazlık terlik",
            StoreName = "Ortobella Store",
            CategoryName = "Ayakkabı",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar1,
            PriceText = "â‚º3.150,00",
            OldPriceText = "",
            PriceValue = 3150.00,
            RatingText = "4.7",
            CargoText = "",
            BadgeText = "Popüler",
            IsNew = false
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
