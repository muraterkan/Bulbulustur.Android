package com.bulbulustur.android.features.retail

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
fun OtherSellerListScreen(
    productId: Int = 1,
    onBackClick: () -> Unit = {},
    onSellerClick: (RetailOtherSellerItem) -> Unit = {},
    onAddToBasketClick: (RetailOtherSellerItem) -> Unit = {}
) {
    val screenData = remember(productId) {
        getRetailOtherSellerScreenData(productId)
    }

    var selectedFilter by remember {
        mutableStateOf("Tümü")
    }

    val filteredSellers = remember(selectedFilter, screenData.sellers) {
        if (selectedFilter == "Tümü") {
            screenData.sellers
        } else {
            screenData.sellers.filter {
                it.filterTags.contains(selectedFilter)
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
                OtherSellerTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                OtherSellerProductSummary(
                    product = screenData.product
                )
            }

            item {
                OtherSellerFilterSection(
                    filters = screenData.filters,
                    selectedFilter = selectedFilter,
                    onFilterChange = {
                        selectedFilter = it
                    }
                )
            }

            item {
                OtherSellerSectionTitle(
                    title = "Diğer satıcılar",
                    description = "Aynı ürünü satan mağazaları fiyat, kargo ve puana göre karşılaştır."
                )
            }

            items(filteredSellers) { seller ->
                OtherSellerCard(
                    seller = seller,
                    onSellerClick = {
                        onSellerClick(seller)
                    },
                    onAddToBasketClick = {
                        onAddToBasketClick(seller)
                    }
                )
            }
        }
    }
}

@Composable
private fun OtherSellerTopBar(
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
                text = "Diğer satıcılar",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Aynı ürün için mağaza seçenekleri.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OtherSellerProductSummary(
    product: RetailOtherSellerProductSummary
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
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = product.variantText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(9.dp))

                Text(
                    text = "${product.sellerCount} satıcı listeleniyor",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OtherSellerFilterSection(
    filters: List<String>,
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OtherSellerSectionTitle(
            title = "Hızlı filtre",
            description = "Satıcıları alışveriş önceliğine göre daralt."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEach { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = {
                        onFilterChange(filter)
                    },
                    label = {
                        Text(text = filter)
                    }
                )
            }
        }
    }
}

@Composable
private fun OtherSellerCard(
    seller: RetailOtherSellerItem,
    onSellerClick: () -> Unit,
    onAddToBasketClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSellerClick()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = seller.logoText,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = seller.storeName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        if (seller.isVerified) {
                            Spacer(modifier = Modifier.width(8.dp))

                            OtherSellerVerifiedBadge()
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${seller.ratingText} puan · ${seller.cargoText}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = "›",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = seller.priceText,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = seller.stockText,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable {
                            onAddToBasketClick()
                        }
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sepete ekle",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            if (seller.badgeText.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))

                OtherSellerInfoBadge(
                    text = seller.badgeText
                )
            }
        }
    }
}

@Composable
private fun OtherSellerVerifiedBadge() {
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
            text = "Doğrulanmış",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun OtherSellerInfoBadge(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OtherSellerSectionTitle(
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

data class RetailOtherSellerScreenData(
    val product: RetailOtherSellerProductSummary,
    val filters: List<String>,
    val sellers: List<RetailOtherSellerItem>
)

data class RetailOtherSellerProductSummary(
    val id: Int,
    val name: String,
    val variantText: String,
    val imageText: String,
    val sellerCount: Int
)

data class RetailOtherSellerItem(
    val id: Int,
    val storeName: String,
    val logoText: String,
    val ratingText: String,
    val cargoText: String,
    val priceText: String,
    val stockText: String,
    val badgeText: String,
    val isVerified: Boolean,
    val filterTags: List<String>
)

private fun getRetailOtherSellerScreenData(productId: Int): RetailOtherSellerScreenData {
    return RetailOtherSellerScreenData(
        product = RetailOtherSellerProductSummary(
            id = productId,
            name = "Kadın klasik sneaker ayakkabı",
            variantText = "Beyaz · 38 numara",
            imageText = "P1",
            sellerCount = 5
        ),
        filters = listOf(
            "Tümü",
            "En düşük fiyat",
            "Hızlı kargo",
            "Doğrulanmış",
            "Yüksek puan"
        ),
        sellers = listOf(
            RetailOtherSellerItem(
                id = 1,
                storeName = "Ortobella Store",
                logoText = "OS",
                ratingText = "4.8",
                cargoText = "Hızlı kargo",
                priceText = "₺899,90",
                stockText = "Stokta var",
                badgeText = "En uygun fiyat",
                isVerified = true,
                filterTags = listOf("En düşük fiyat", "Hızlı kargo", "Doğrulanmış", "Yüksek puan")
            ),
            RetailOtherSellerItem(
                id = 2,
                storeName = "Moda Nova",
                logoText = "MN",
                ratingText = "4.6",
                cargoText = "Standart kargo",
                priceText = "₺929,90",
                stockText = "Stokta var",
                badgeText = "",
                isVerified = true,
                filterTags = listOf("Doğrulanmış", "Yüksek puan")
            ),
            RetailOtherSellerItem(
                id = 3,
                storeName = "Urban Touch",
                logoText = "UT",
                ratingText = "4.7",
                cargoText = "Hızlı kargo",
                priceText = "₺949,90",
                stockText = "Son 3 ürün",
                badgeText = "Az stok",
                isVerified = false,
                filterTags = listOf("Hızlı kargo", "Yüksek puan")
            ),
            RetailOtherSellerItem(
                id = 4,
                storeName = "Sneaker House",
                logoText = "SH",
                ratingText = "4.4",
                cargoText = "Standart kargo",
                priceText = "₺979,90",
                stockText = "Stokta var",
                badgeText = "",
                isVerified = false,
                filterTags = listOf()
            ),
            RetailOtherSellerItem(
                id = 5,
                storeName = "Ayakkabı Merkezi",
                logoText = "AM",
                ratingText = "4.5",
                cargoText = "Hızlı kargo",
                priceText = "₺999,90",
                stockText = "Stokta var",
                badgeText = "Kargo avantajı",
                isVerified = true,
                filterTags = listOf("Hızlı kargo", "Doğrulanmış")
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun OtherSellerListScreenPreview() {
    MaterialTheme {
        OtherSellerListScreen()
    }
}
