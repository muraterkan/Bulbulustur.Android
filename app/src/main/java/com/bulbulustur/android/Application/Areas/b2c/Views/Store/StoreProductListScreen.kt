package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun StoreProductListScreen(
    storeId: Int = 1,
    onBackClick: () -> Unit = {},
    onProductClick: (RetailStoreProductListItem) -> Unit = {},
    onSearchSubmit: (String) -> Unit = {}
) {
    val screenData = remember(storeId) {
        getRetailStoreProductListScreenData(storeId)
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

    val filteredProducts = remember(selectedCategory, selectedSort, screenData.products) {
        val categoryFilteredProducts = if (selectedCategory == "Tümü") {
            screenData.products
        } else {
            screenData.products.filter {
                it.categoryName == selectedCategory
            }
        }

        when (selectedSort) {
            "En düşük fiyat" -> categoryFilteredProducts.sortedBy { it.priceValue }
            "En yüksek fiyat" -> categoryFilteredProducts.sortedByDescending { it.priceValue }
            "Yeni Gelenler" -> categoryFilteredProducts.filter { it.isNew }
            else -> categoryFilteredProducts
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 14.dp,
                end = 16.dp,
                bottom = 28.dp
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            item {
                StoreProductListTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                StoreProductListStoreSummary(
                    store = screenData.store
                )
            }

            item {
                StoreProductSearchBox(
                    searchText = searchText,
                    onSearchTextChange = {
                        searchText = it
                    },
                    onSearchSubmit = {
                        onSearchSubmit(searchText)
                    }
                )
            }

            item {
                StoreProductCategoryFilterSection(
                    categories = screenData.categories,
                    selectedCategory = selectedCategory,
                    onCategoryChange = {
                        selectedCategory = it
                    }
                )
            }

            item {
                StoreProductSortFilterSection(
                    sortOptions = screenData.sortOptions,
                    selectedSort = selectedSort,
                    onSortChange = {
                        selectedSort = it
                    }
                )
            }

            item {
                StoreProductListSectionTitle(
                    title = "MaĞaza ürünleri",
                    description = "${filteredProducts.size} ürün listeleniyor."
                )
            }

            items(filteredProducts) { product ->
                StoreProductListCard(
                    product = product,
                    onClick = {
                        onProductClick(product)
                    }
                )
            }
        }
    }
}

@Composable
private fun StoreProductListTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BbIconBox(
            modifier = Modifier.clickable {
                onBackClick()
            },
            size = BbIconBoxSize.Medium,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            Text(
                text = "â€¹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "MaĞaza ürünleri",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "MaĞaza Vitrininin tüm ürün akışı.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StoreProductListStoreSummary(
    store: RetailStoreProductListStoreSummary
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = store.logoText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = store.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    if (store.isVerified) {
                        Spacer(modifier = Modifier.width(8.dp))

                        StoreProductVerifiedBadge()
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "${store.productCount} ürün Â· ${store.ratingText} puan Â· ${store.cargoText}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
private fun StoreProductVerifiedBadge() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.78f))
            .padding(
                horizontal = 8.dp,
                vertical = 3.dp
            )
    ) {
        Text(
            text = "DoĞrulanmış",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun StoreProductSearchBox(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchSubmit: () -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp)),
        placeholder = {
            Text(text = "Bu maĞazada ürün ara")
        },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = BBColors.Transparent,
            unfocusedIndicatorColor = BBColors.Transparent,
            disabledIndicatorColor = BBColors.Transparent
        ),
        trailingIcon = {
            Text(
                text = "Ara",
                modifier = Modifier
                    .padding(end = BBSpacing.Space3)
                    .clickable {
                        onSearchSubmit()
                    },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StoreProductCategoryFilterSection(
    categories: List<String>,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StoreProductListSectionTitle(
            title = "Kategori Filtresi",
            description = "MaĞaza ürünlerini kategoriye göre daralt."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = {
                        onCategoryChange(category)
                    },
                    label = {
                        Text(text = category)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StoreProductSortFilterSection(
    sortOptions: List<String>,
    selectedSort: String,
    onSortChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StoreProductListSectionTitle(
            title = "Sıralama",
            description = "Ürün akışını alışveriş önceliĞine göre düzenle."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            sortOptions.forEach { sortOption ->
                FilterChip(
                    selected = selectedSort == sortOption,
                    onClick = {
                        onSortChange(sortOption)
                    },
                    label = {
                        Text(text = sortOption)
                    }
                )
            }
        }
    }
}

@Composable
private fun StoreProductListCard(
    product: RetailStoreProductListItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.categoryName,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    if (product.isNew) {
                        Spacer(modifier = Modifier.width(8.dp))

                        StoreProductSmallBadge(
                            text = "Yeni"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = product.shortDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(9.dp))

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = product.priceText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    if (product.discountText.isNotBlank()) {
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = product.discountText,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            Text(
                text = "â€º",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StoreProductSmallBadge(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(
                horizontal = 8.dp,
                vertical = 3.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun StoreProductListSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class RetailStoreProductListScreenData(
    val store: RetailStoreProductListStoreSummary,
    val categories: List<String>,
    val sortOptions: List<String>,
    val products: List<RetailStoreProductListItem>
)

data class RetailStoreProductListStoreSummary(
    val id: Int,
    val name: String,
    val logoText: String,
    val productCount: Int,
    val ratingText: String,
    val cargoText: String,
    val isVerified: Boolean
)

data class RetailStoreProductListItem(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val categoryName: String,
    val priceText: String,
    val priceValue: Double,
    val discountText: String,
    val imageText: String,
    val isNew: Boolean
)

private fun getRetailStoreProductListScreenData(storeId: Int): RetailStoreProductListScreenData {
    return RetailStoreProductListScreenData(
        store = RetailStoreProductListStoreSummary(
            id = storeId,
            name = "Ortobella Store",
            logoText = "OS",
            productCount = 248,
            ratingText = "4.8",
            cargoText = "Hızlı kargo",
            isVerified = true
        ),
        categories = listOf(
            "Tümü",
            "Ayakkabı",
            "Kadın Giyim",
            "Çanta",
            "Aksesuar"
        ),
        sortOptions = listOf(
            "Öne çıkan",
            "En düşük fiyat",
            "En yüksek fiyat",
            "Yeni Gelenler"
        ),
        products = listOf(
            RetailStoreProductListItem(
                id = 1,
                name = "Kadın klasik sneaker ayakkabı",
                shortDescription = "Günlük kullanım için rahat tabanlı sneaker.",
                categoryName = "Ayakkabı",
                priceText = "â‚º899,90",
                priceValue = 899.90,
                discountText = "%20",
                imageText = "P1",
                isNew = true
            ),
            RetailStoreProductListItem(
                id = 2,
                name = "Rahat taban günlük ayakkabı",
                shortDescription = "Yumuşak iç taban ve hafif yapı.",
                categoryName = "Ayakkabı",
                priceText = "â‚º749,90",
                priceValue = 749.90,
                discountText = "",
                imageText = "P2",
                isNew = false
            ),
            RetailStoreProductListItem(
                id = 3,
                name = "Günlük kullanım omuz çantası",
                shortDescription = "Minimal tasarım, günlük kullanım bölmeleri.",
                categoryName = "Çanta",
                priceText = "â‚º649,90",
                priceValue = 649.90,
                discountText = "%15",
                imageText = "P3",
                isNew = true
            ),
            RetailStoreProductListItem(
                id = 4,
                name = "Basic pamuklu kadın tişört",
                shortDescription = "Pamuklu kumaş, regular fit günlük model.",
                categoryName = "Kadın Giyim",
                priceText = "â‚º329,90",
                priceValue = 329.90,
                discountText = "%10",
                imageText = "P4",
                isNew = false
            ),
            RetailStoreProductListItem(
                id = 5,
                name = "Minimal bileklik seti",
                shortDescription = "Günlük kombinler için aksesuar seti.",
                categoryName = "Aksesuar",
                priceText = "â‚º219,90",
                priceValue = 219.90,
                discountText = "",
                imageText = "P5",
                isNew = true
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun StoreProductListScreenPreview() {
    MaterialTheme {
        StoreProductListScreen()
    }
}


