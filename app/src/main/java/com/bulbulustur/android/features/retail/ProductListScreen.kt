package com.bulbulustur.android.features.retail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.commercecomponents.BbProductGrid
import com.bulbulustur.android.ui.components.BbBottomNavigation
import com.bulbulustur.android.ui.components.BbBottomNavigationItem
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbSearchBar
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun ProductListScreen(
    onProductClick: (RetailProductListItem) -> Unit = {},
    onBottomNavigationClick: (BbBottomNavigationItem) -> Unit = {}
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
            products.filter {
                it.name.contains(searchText, ignoreCase = true) ||
                        it.storeName.contains(searchText, ignoreCase = true) ||
                        it.categoryName.contains(searchText, ignoreCase = true)
            }
        }

        val categoryFilteredProducts = if (selectedCategory == "Tümü") {
            searchFilteredProducts
        } else {
            searchFilteredProducts.filter {
                it.categoryName == selectedCategory
            }
        }

        when (selectedSortOption) {
            "En düşük fiyat" -> categoryFilteredProducts.sortedBy {
                it.priceValue
            }

            "En yüksek fiyat" -> categoryFilteredProducts.sortedByDescending {
                it.priceValue
            }

            "Yeni gelenler" -> categoryFilteredProducts.filter {
                it.isNew
            }

            else -> categoryFilteredProducts
        }
    }

    Scaffold(
        bottomBar = {
            BbBottomNavigation(
                selectedItem = BbBottomNavigationItem.Categories,
                onItemClick = {
                    onBottomNavigationClick(it)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
        ) {
            BbSearchBar(
                value = searchText,
                placeholder = "Perakende ürün ara",
                onValueChange = {
                    searchText = it
                },
                modifier = Modifier.padding(BbSpacing.md)
            )

            BbSectionHeader(
                title = "Perakende Ürünler",
                subtitle = "Kategorilerden, mağazalardan ve kampanyalardan ürün keşfet"
            )

            Spacer(modifier = Modifier.height(BbSpacing.sm))

            ProductListHorizontalFilters(
                items = categories,
                selectedItem = selectedCategory,
                onItemClick = {
                    selectedCategory = it
                }
            )

            Spacer(modifier = Modifier.height(BbSpacing.sm))

            ProductListHorizontalFilters(
                items = sortOptions,
                selectedItem = selectedSortOption,
                onItemClick = {
                    selectedSortOption = it
                }
            )

            Spacer(modifier = Modifier.height(BbSpacing.sm))

            ProductListResultHeader(
                productCount = filteredProducts.size,
                selectedCategory = selectedCategory
            )

            BbProductGrid(
                contentPadding = PaddingValues(BbSpacing.md),
                horizontalSpacing = BbSpacing.sm,
                verticalSpacing = BbSpacing.md
            ) {
                items(filteredProducts) { product ->
                    RetailProductListCard(
                        product = product,
                        onClick = {
                            onProductClick(product)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductListHorizontalFilters(
    items: List<String>,
    selectedItem: String,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = BbSpacing.md),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        items.forEach { item ->
            FilterChip(
                selected = selectedItem == item,
                onClick = {
                    onItemClick(item)
                },
                label = {
                    Text(text = item)
                }
            )
        }
    }
}

@Composable
private fun ProductListResultHeader(
    productCount: Int,
    selectedCategory: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.md,
                end = BbSpacing.md,
                top = BbSpacing.xs,
                bottom = BbSpacing.xs
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
                color = BbColors.TextStrong.copy(alpha = 0.62f)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            ProductImagePlaceholder(
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
                color = BbColors.TextStrong.copy(alpha = 0.62f),
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
                    Spacer(modifier = Modifier.width(BbSpacing.xs))

                    Text(
                        text = product.oldPriceText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.TextStrong.copy(alpha = 0.48f)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProductSmallPill(
                    text = product.ratingText
                )

                if (product.cargoText.isNotBlank()) {
                    ProductSmallPill(
                        text = product.cargoText
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductImagePlaceholder(
    imageText: String,
    badgeText: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BbSpacing.Space16 + BbSpacing.xl)
            .clip(RoundedCornerShape(BbRadius.lg))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = imageText,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong.copy(alpha = 0.46f)
        )

        if (badgeText.isNotBlank()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(BbSpacing.xs)
                    .clip(RoundedCornerShape(BbRadius.pill))
                    .background(BbColors.Success)
                    .padding(
                        horizontal = BbSpacing.sm,
                        vertical = BbSpacing.xs
                    )
            ) {
                Text(
                    text = badgeText,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProductSmallPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.sm,
                vertical = BbSpacing.xs
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextStrong.copy(alpha = 0.68f)
        )
    }
}

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
        ),
        RetailProductListItem(
            id = 7,
            name = "Minimal bileklik seti",
            storeName = "Urban Touch",
            categoryName = "Çanta",
            priceText = "₺219,90",
            oldPriceText = "",
            priceValue = 219.90,
            ratingText = "★ 4.3",
            cargoText = "Hızlı kargo",
            badgeText = "",
            imageText = "P7",
            isNew = true
        ),
        RetailProductListItem(
            id = 8,
            name = "Pamuklu erkek basic sweatshirt",
            storeName = "Moda Nova",
            categoryName = "Giyim",
            priceText = "₺529,90",
            oldPriceText = "₺649,90",
            priceValue = 529.90,
            ratingText = "★ 4.5",
            cargoText = "",
            badgeText = "%18",
            imageText = "P8",
            isNew = false
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    ProductListScreen()
}