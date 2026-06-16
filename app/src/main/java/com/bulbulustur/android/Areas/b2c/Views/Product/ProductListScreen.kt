package com.bulbulustur.android.Areas.b2c.Views.Product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Icon
import com.bulbulustur.android.Areas.b2c.ViewComponents.RetailBottomNavigation
import com.bulbulustur.android.Areas.b2c.ViewComponents.RetailBottomNavigationItem
import com.bulbulustur.android.Areas.b2c.ViewComponents.RetailSearchHeader
import com.bulbulustur.android.wwwroot.components.commerce.BbProductGrid
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbChip
import com.bulbulustur.android.wwwroot.components.BbSectionHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

@Composable
fun ProductListScreen(
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductDetailClick: () -> Unit = {}
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
            "Ev & Yaşam"
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

    val filteredProducts = remember(
        searchText,
        selectedCategory,
        selectedSortOption,
        products
    ) {
        val searchFilteredProducts = if (searchText.isBlank()) {
            products
        } else {
            products.filter { product ->
                product.name.contains(searchText, ignoreCase = true) ||
                        product.storeName.contains(searchText, ignoreCase = true) ||
                        product.categoryName.contains(searchText, ignoreCase = true)
            }
        }

        val categoryFilteredProducts = if (selectedCategory == "Tümü") {
            searchFilteredProducts
        } else {
            searchFilteredProducts.filter { product ->
                product.categoryName == selectedCategory
            }
        }

        when (selectedSortOption) {
            "En düşük fiyat" -> categoryFilteredProducts.sortedBy { product ->
                product.priceValue
            }

            "En yüksek fiyat" -> categoryFilteredProducts.sortedByDescending { product ->
                product.priceValue
            }

            "Yeni gelenler" -> categoryFilteredProducts.filter { product ->
                product.isNew
            }

            else -> categoryFilteredProducts
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                }
            )
        },
        bottomBar = {
            RetailBottomNavigation(
                selectedItem = RetailBottomNavigationItem.Menu,
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            BbSectionHeader(
                title = "Perakende Ürünler",
                subtitle = "Kategorilerden, mağazalardan ve kampanyalardan ürün keşfet"
            )

            Spacer(
                modifier = Modifier.height(BbSpacing.Space2)
            )

            RetailProductListHorizontalFilters(
                items = categories,
                selectedItem = selectedCategory,
                onItemClick = {
                    selectedCategory = it
                }
            )

            Spacer(
                modifier = Modifier.height(BbSpacing.Space2)
            )

            RetailProductListHorizontalFilters(
                items = sortOptions,
                selectedItem = selectedSortOption,
                onItemClick = {
                    selectedSortOption = it
                }
            )

            Spacer(
                modifier = Modifier.height(BbSpacing.Space2)
            )

            RetailProductListResultHeader(
                productCount = filteredProducts.size,
                selectedCategory = selectedCategory
            )

            BbProductGrid(
                contentPadding = PaddingValues(
                    start = BbSpacing.PageHorizontal,
                    top = BbSpacing.Space3,
                    end = BbSpacing.PageHorizontal,
                    bottom = BbSpacing.PageBottom
                ),
                horizontalSpacing = BbSpacing.Space3,
                verticalSpacing = BbSpacing.Space4
            ) {
                items(
                    items = filteredProducts,
                    key = { product ->
                        product.id
                    }
                ) { product ->
                    RetailProductListCard(
                        product = product,
                        onClick = onProductDetailClick
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BbSpacing.PageHorizontal),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        items.take(4).forEach { item ->
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.PageHorizontal,
                end = BbSpacing.PageHorizontal,
                top = BbSpacing.Space1,
                bottom = BbSpacing.Space1
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = if (selectedCategory == "Tümü") {
                    "Tüm ürünler"
                } else {
                    selectedCategory
                },
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "$productCount ürün listeleniyor",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )
        }

        Text(
            text = "Filtrele",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = BbColors.Success
        )
    }
}

@Composable
private fun RetailProductListCard(
    product: RetailProductListItem,
    onClick: () -> Unit
) {
    BbCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            RetailProductImagePlaceholder(
                imageText = product.imageText,
                badgeText = product.badgeText
            )

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = BbColors.TextStrong,
                maxLines = 2
            )

            Text(
                text = product.storeName,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted,
                maxLines = 1
            )

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = product.priceText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Success
                )

                if (product.oldPriceText.isNotBlank()) {
                    Spacer(
                        modifier = Modifier.width(BbSpacing.Space1)
                    )

                    Text(
                        text = product.oldPriceText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.TextMuted
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RetailProductSmallPill(
                    text = product.ratingText
                )

                if (product.cargoText.isNotBlank()) {
                    RetailProductSmallPill(
                        text = product.cargoText
                    )
                }
            }
        }
    }
}

@Composable
private fun RetailProductImagePlaceholder(
    imageText: String,
    badgeText: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BbSpacing.Space16 + BbSpacing.Space8)
            .clip(RoundedCornerShape(BbRadius.lg))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.ShoppingBasket,
            contentDescription = null,
            tint = BbColors.TextMuted
        )

        Text(
            text = imageText,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextMuted,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        if (badgeText.isNotBlank()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(BbSpacing.Space1)
                    .clip(RoundedCornerShape(BbRadius.pill))
                    .background(BbColors.Success)
                    .padding(
                        horizontal = BbSpacing.Space2,
                        vertical = BbSpacing.Space1
                    )
            ) {
                Text(
                    text = badgeText,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.White
                )
            }
        }
    }
}

@Composable
private fun RetailProductSmallPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextMuted
        )
    }
}

@Immutable
data class RetailProductListItem(
    val id: Int,
    val name: String,
    val storeName: String,
    val categoryName: String,
    val priceText: String,
    val oldPriceText: String,
    val priceValue: Double,
    val ratingText: String,
    val cargoText: String,
    val badgeText: String,
    val imageText: String,
    val isNew: Boolean
)

private fun getRetailProductListItems(): List<RetailProductListItem> {
    return listOf(
        RetailProductListItem(
            id = 1,
            name = "Kadın klasik sneaker ayakkabı",
            storeName = "Ortobella Store",
            categoryName = "Ayakkabı",
            priceText = "₺899,90",
            oldPriceText = "₺1.099,90",
            priceValue = 899.90,
            ratingText = "★ 4.8",
            cargoText = "Hızlı kargo",
            badgeText = "%20",
            imageText = "P1",
            isNew = true
        ),
        RetailProductListItem(
            id = 2,
            name = "Rahat taban günlük ayakkabı",
            storeName = "Ortobella Store",
            categoryName = "Ayakkabı",
            priceText = "₺749,90",
            oldPriceText = "",
            priceValue = 749.90,
            ratingText = "★ 4.7",
            cargoText = "Ücretsiz kargo",
            badgeText = "Yeni",
            imageText = "P2",
            isNew = true
        ),
        RetailProductListItem(
            id = 3,
            name = "Oversize pamuklu basic tişört",
            storeName = "Moda Nova",
            categoryName = "Giyim",
            priceText = "₺349,90",
            oldPriceText = "₺429,90",
            priceValue = 349.90,
            ratingText = "★ 4.6",
            cargoText = "",
            badgeText = "%15",
            imageText = "P3",
            isNew = false
        ),
        RetailProductListItem(
            id = 4,
            name = "Günlük kullanım omuz çantası",
            storeName = "Urban Touch",
            categoryName = "Çanta",
            priceText = "₺649,90",
            oldPriceText = "",
            priceValue = 649.90,
            ratingText = "★ 4.5",
            cargoText = "Hızlı kargo",
            badgeText = "",
            imageText = "P4",
            isNew = true
        ),
        RetailProductListItem(
            id = 5,
            name = "Kablosuz bluetooth kulaklık",
            storeName = "Tekno Sepet",
            categoryName = "Elektronik",
            priceText = "₺599,90",
            oldPriceText = "₺749,90",
            priceValue = 599.90,
            ratingText = "★ 4.4",
            cargoText = "Ücretsiz kargo",
            badgeText = "%20",
            imageText = "P5",
            isNew = false
        ),
        RetailProductListItem(
            id = 6,
            name = "Mutfak düzenleyici raf seti",
            storeName = "Casa Liva",
            categoryName = "Ev & Yaşam",
            priceText = "₺449,90",
            oldPriceText = "",
            priceValue = 449.90,
            ratingText = "★ 4.7",
            cargoText = "",
            badgeText = "Popüler",
            imageText = "P6",
            isNew = false
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
