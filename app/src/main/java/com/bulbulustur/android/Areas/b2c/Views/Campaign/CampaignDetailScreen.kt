package com.bulbulustur.android.Areas.b2c.Views.Campaign

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import com.bulbulustur.android.wwwroot.components.BbIconBoxSize
import com.bulbulustur.android.wwwroot.components.BbIconBox
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
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbLayout

@Composable
fun CampaignDetailScreen(
    campaignId: Int = 1,
    onBackClick: () -> Unit = {},
    onProductClick: (RetailCampaignProductItem) -> Unit = {},
    onCategoryClick: (RetailCampaignCategoryItem) -> Unit = {},
    onStoreClick: (RetailCampaignStoreItem) -> Unit = {}
) {
    val campaign = remember(campaignId) {
        getRetailCampaignDetail(campaignId)
    }

    var selectedFilter by remember {
        mutableStateOf(campaign.productFilters.firstOrNull().orEmpty())
    }

    val filteredProducts = remember(selectedFilter, campaign.products) {
        if (selectedFilter.isBlank() || selectedFilter == "Tümü") {
            campaign.products
        } else {
            campaign.products.filter {
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            item {
                CampaignDetailTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                CampaignDetailHero(
                    campaign = campaign
                )
            }

            item {
                CampaignSummaryCards(
                    campaign = campaign
                )
            }

            item {
                CampaignCategorySection(
                    categories = campaign.categories,
                    onCategoryClick = onCategoryClick
                )
            }

            item {
                CampaignStoreSection(
                    stores = campaign.stores,
                    onStoreClick = onStoreClick
                )
            }

            item {
                CampaignConditionSection(
                    conditions = campaign.conditions
                )
            }

            item {
                CampaignProductFilterSection(
                    filters = campaign.productFilters,
                    selectedFilter = selectedFilter,
                    onFilterChange = {
                        selectedFilter = it
                    }
                )
            }

            item {
                CampaignDetailSectionTitle(
                    title = "Kampanya ürünleri",
                    description = "Bu kampanyaya dahil seçili ürünler."
                )
            }

            items(filteredProducts) { product ->
                CampaignProductRow(
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
private fun CampaignDetailTopBar(
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
                text = "Kampanya detayı",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Fırsat kapsamı, koşullar ve ürünler.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CampaignDetailHero(
    campaign: RetailCampaignDetail
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
                        .size(58.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = campaign.iconText,
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
                        text = campaign.badgeText,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = campaign.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = campaign.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                CampaignDetailHeroPill(
                    title = campaign.discountText,
                    subtitle = "avantaj"
                )

                CampaignDetailHeroPill(
                    title = campaign.endDateText,
                    subtitle = "süre"
                )

                CampaignDetailHeroPill(
                    title = "${campaign.productCount}",
                    subtitle = "ürün"
                )
            }
        }
    }
}

@Composable
private fun CampaignDetailHeroPill(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.72f))
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CampaignSummaryCards(
    campaign: RetailCampaignDetail
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CampaignSummaryCard(
            modifier = Modifier.weight(1f),
            title = "${campaign.storeCount}",
            subtitle = "mağaza"
        )

        CampaignSummaryCard(
            modifier = Modifier.weight(1f),
            title = "${campaign.categoryCount}",
            subtitle = "kategori"
        )

        CampaignSummaryCard(
            modifier = Modifier.weight(1f),
            title = campaign.cargoText,
            subtitle = "kargo"
        )
    }
}

@Composable
private fun CampaignSummaryCard(
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

@Composable
private fun CampaignCategorySection(
    categories: List<RetailCampaignCategoryItem>,
    onCategoryClick: (RetailCampaignCategoryItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CampaignDetailSectionTitle(
            title = "Kapsamdaki kategoriler",
            description = "Kampanya hangi alışveriş alanlarında geçerli?"
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(categories) { category ->
                CampaignCategoryCard(
                    category = category,
                    onClick = {
                        onCategoryClick(category)
                    }
                )
            }
        }
    }
}

@Composable
private fun CampaignCategoryCard(
    category: RetailCampaignCategoryItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(BbLayout.FixedWidth150)
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
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            BbIconBox(
                size = BbIconBoxSize.Medium,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Text(
                    text = category.iconText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = category.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${category.productCount} ürün",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CampaignStoreSection(
    stores: List<RetailCampaignStoreItem>,
    onStoreClick: (RetailCampaignStoreItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CampaignDetailSectionTitle(
            title = "Katılan mağazalar",
            description = "Bu kampanyada öne çıkan mağazalar."
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(stores) { store ->
                CampaignStoreCard(
                    store = store,
                    onClick = {
                        onStoreClick(store)
                    }
                )
            }
        }
    }
}

@Composable
private fun CampaignStoreCard(
    store: RetailCampaignStoreItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(180.dp)
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
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BbIconBox(
                size = BbIconBoxSize.Medium,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Text(
                    text = store.logoText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = store.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "${store.productCount} ürün",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CampaignConditionSection(
    conditions: List<String>
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
            modifier = Modifier.padding(BbSpacing.Space4)
        ) {
            CampaignDetailSectionTitle(
                title = "Kampanya koşulları",
                description = "Alışverişten önce bilinmesi gereken kısa notlar."
            )

            Spacer(modifier = Modifier.height(12.dp))

            conditions.forEach { condition ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = condition,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CampaignProductFilterSection(
    filters: List<String>,
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CampaignDetailSectionTitle(
            title = "Ürün filtresi",
            description = "Kampanya ürünlerini hızlıca daralt."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
private fun CampaignProductRow(
    product: RetailCampaignProductItem,
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
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = product.discountedPriceText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = product.discountText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
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
private fun CampaignDetailSectionTitle(
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

data class RetailCampaignDetail(
    val id: Int,
    val title: String,
    val description: String,
    val badgeText: String,
    val iconText: String,
    val discountText: String,
    val endDateText: String,
    val cargoText: String,
    val productCount: Int,
    val storeCount: Int,
    val categoryCount: Int,
    val categories: List<RetailCampaignCategoryItem>,
    val stores: List<RetailCampaignStoreItem>,
    val conditions: List<String>,
    val productFilters: List<String>,
    val products: List<RetailCampaignProductItem>
)

data class RetailCampaignCategoryItem(
    val id: Int,
    val name: String,
    val iconText: String,
    val productCount: Int
)

data class RetailCampaignStoreItem(
    val id: Int,
    val name: String,
    val logoText: String,
    val productCount: Int
)

data class RetailCampaignProductItem(
    val id: Int,
    val name: String,
    val storeName: String,
    val discountedPriceText: String,
    val discountText: String,
    val imageText: String,
    val filterTags: List<String>
)

private fun getRetailCampaignDetail(campaignId: Int): RetailCampaignDetail {
    return RetailCampaignDetail(
        id = campaignId,
        title = "Sezonun öne çıkanları",
        description = "Moda kategorisinde yeni sezon ürünleri, seçili mağaza vitrinleri ve avantajlı fiyatlarla hazırlanmış perakende kampanyası.",
        badgeText = "Yeni sezon kampanyası",
        iconText = "MO",
        discountText = "%35'e varan",
        endDateText = "7 gün",
        cargoText = "Avantajlı",
        productCount = 1240,
        storeCount = 38,
        categoryCount = 5,
        categories = listOf(
            RetailCampaignCategoryItem(
                id = 1,
                name = "Kadın Giyim",
                iconText = "KG",
                productCount = 420
            ),
            RetailCampaignCategoryItem(
                id = 2,
                name = "Ayakkabı",
                iconText = "AY",
                productCount = 310
            ),
            RetailCampaignCategoryItem(
                id = 3,
                name = "Çanta",
                iconText = "ÇA",
                productCount = 180
            )
        ),
        stores = listOf(
            RetailCampaignStoreItem(
                id = 1,
                name = "Ortobella",
                logoText = "OR",
                productCount = 248
            ),
            RetailCampaignStoreItem(
                id = 2,
                name = "Moda Nova",
                logoText = "MN",
                productCount = 392
            ),
            RetailCampaignStoreItem(
                id = 3,
                name = "Urban Touch",
                logoText = "UT",
                productCount = 184
            )
        ),
        conditions = listOf(
            "Kampanya seçili ürünlerde ve kampanyaya katılan mağazalarda geçerlidir.",
            "Stok durumuna göre ürün görünürlüğü ve fiyatlar değişebilir.",
            "Kargo avantajı mağaza ve ürün koşullarına göre farklılık gösterebilir.",
            "Sepet ve ödeme adımlarında nihai fiyat tekrar gösterilir."
        ),
        productFilters = listOf(
            "Tümü",
            "Yeni sezon",
            "İndirimli",
            "Ücretsiz kargo",
            "Çok satan"
        ),
        products = listOf(
            RetailCampaignProductItem(
                id = 1,
                name = "Kadın klasik sneaker ayakkabı",
                storeName = "Ortobella",
                discountedPriceText = "₺899,90",
                discountText = "%20",
                imageText = "P1",
                filterTags = listOf("Yeni sezon", "İndirimli", "Çok satan")
            ),
            RetailCampaignProductItem(
                id = 2,
                name = "Oversize pamuklu basic tişört",
                storeName = "Moda Nova",
                discountedPriceText = "₺349,90",
                discountText = "%15",
                imageText = "P2",
                filterTags = listOf("Yeni sezon", "İndirimli")
            ),
            RetailCampaignProductItem(
                id = 3,
                name = "Günlük kullanım omuz çantası",
                storeName = "Urban Touch",
                discountedPriceText = "₺649,90",
                discountText = "%25",
                imageText = "P3",
                filterTags = listOf("Ücretsiz kargo", "İndirimli")
            ),
            RetailCampaignProductItem(
                id = 4,
                name = "Rahat taban günlük ayakkabı",
                storeName = "Ortobella",
                discountedPriceText = "₺749,90",
                discountText = "%18",
                imageText = "P4",
                filterTags = listOf("Çok satan", "Ücretsiz kargo")
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CampaignDetailScreenPreview() {
    MaterialTheme {
        CampaignDetailScreen()
    }
}
