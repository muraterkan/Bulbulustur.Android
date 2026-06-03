package com.bulbulustur.app.features.retail

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp

@Composable
fun StoreDetailScreen(
    storeId: Int = 1,
    onBackClick: () -> Unit = {},
    onProductClick: (RetailStoreProductItem) -> Unit = {},
    onCategoryClick: (String) -> Unit = {}
) {
    val store = remember(storeId) {
        getRetailStoreDetail(storeId)
    }

    var selectedCategory by remember {
        mutableStateOf("Tümü")
    }

    val filteredProducts = remember(selectedCategory, store.products) {
        if (selectedCategory == "Tümü") {
            store.products
        } else {
            store.products.filter {
                it.categoryName == selectedCategory
            }
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                StoreDetailTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                StoreDetailHero(
                    store = store
                )
            }

            item {
                StoreInfoStatSection(
                    store = store
                )
            }

            item {
                StoreCategoryFilterSection(
                    categories = store.categories,
                    selectedCategory = selectedCategory,
                    onCategoryChange = {
                        selectedCategory = it
                        onCategoryClick(it)
                    }
                )
            }

            item {
                StoreDetailSectionTitle(
                    title = "Mağaza ürünleri",
                    description = "Bu mağazanın perakende ürün akışı."
                )
            }

            items(filteredProducts) { product ->
                StoreProductRow(
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
private fun StoreDetailTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Mağaza detayı",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Mağaza vitrini, ürünleri ve alışveriş bilgileri.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StoreDetailHero(
    store: RetailStoreDetail
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(RoundedCornerShape(22.dp))
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
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        if (store.isVerified) {
                            Spacer(modifier = Modifier.width(8.dp))

                            StoreVerifiedBadge()
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = store.shortDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = store.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun StoreVerifiedBadge() {
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
            text = "Doğrulanmış",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun StoreInfoStatSection(
    store: RetailStoreDetail
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = "${store.productCount}",
            subtitle = "ürün"
        )

        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.ratingText,
            subtitle = "puan"
        )

        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.cargoText,
            subtitle = "kargo"
        )
    }
}

@Composable
private fun StoreInfoStatCard(
    modifier: Modifier,
    title: String,
    subtitle: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StoreCategoryFilterSection(
    categories: List<String>,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StoreDetailSectionTitle(
            title = "Mağaza kategorileri",
            description = "Ürün akışını kategoriye göre daralt."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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

@Composable
private fun StoreProductRow(
    product: RetailStoreProductItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(22.dp),
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
                    .size(70.dp)
                    .clip(RoundedCornerShape(18.dp))
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
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.categoryName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = product.priceText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    if (product.badgeText.isNotBlank()) {
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = product.badgeText,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StoreDetailSectionTitle(
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

data class RetailStoreDetail(
    val id: Int,
    val name: String,
    val logoText: String,
    val shortDescription: String,
    val description: String,
    val productCount: Int,
    val ratingText: String,
    val cargoText: String,
    val isVerified: Boolean,
    val categories: List<String>,
    val products: List<RetailStoreProductItem>
)

data class RetailStoreProductItem(
    val id: Int,
    val name: String,
    val categoryName: String,
    val priceText: String,
    val badgeText: String,
    val imageText: String
)

private fun getRetailStoreDetail(storeId: Int): RetailStoreDetail {
    return RetailStoreDetail(
        id = storeId,
        name = "Ortobella Store",
        logoText = "OS",
        shortDescription = "Ayakkabı ve günlük moda ürünleri",
        description = "Ortobella Store, seçili ayakkabı ve günlük kullanım ürünlerini perakende alışveriş akışında sunan doğrulanmış mağaza vitrinidir.",
        productCount = 248,
        ratingText = "4.8",
        cargoText = "Hızlı",
        isVerified = true,
        categories = listOf(
            "Tümü",
            "Ayakkabı",
            "Kadın Giyim",
            "Çanta",
            "Aksesuar"
        ),
        products = listOf(
            RetailStoreProductItem(
                id = 1,
                name = "Kadın klasik sneaker ayakkabı",
                categoryName = "Ayakkabı",
                priceText = "₺899,90",
                badgeText = "%20",
                imageText = "P1"
            ),
            RetailStoreProductItem(
                id = 2,
                name = "Rahat taban günlük ayakkabı",
                categoryName = "Ayakkabı",
                priceText = "₺749,90",
                badgeText = "Yeni",
                imageText = "P2"
            ),
            RetailStoreProductItem(
                id = 3,
                name = "Günlük kullanım omuz çantası",
                categoryName = "Çanta",
                priceText = "₺649,90",
                badgeText = "",
                imageText = "P3"
            ),
            RetailStoreProductItem(
                id = 4,
                name = "Basic pamuklu kadın tişört",
                categoryName = "Kadın Giyim",
                priceText = "₺329,90",
                badgeText = "%15",
                imageText = "P4"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun StoreDetailScreenPreview() {
    MaterialTheme {
        StoreDetailScreen()
    }
}