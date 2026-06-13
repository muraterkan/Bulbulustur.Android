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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.bulbulustur.android.ui.theme.BbColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbLayout

@Composable
fun CategoryDetailScreen(
    categoryId: Int = 1,
    onBackClick: () -> Unit = {},
    onSubCategoryClick: (RetailSubCategoryItem) -> Unit = {},
    onProductClick: (RetailCategoryProductItem) -> Unit = {},
    onCampaignClick: (RetailCategoryCampaignItem) -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    val category = remember(categoryId) {
        getRetailCategoryDetail(categoryId)
    }

    var searchText by remember {
        mutableStateOf("")
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
                CategoryDetailTopBar(
                    title = category.name,
                    onBackClick = onBackClick
                )
            }

            item {
                CategoryDetailHero(
                    category = category
                )
            }

            item {
                CategoryDetailSearchBox(
                    searchText = searchText,
                    onSearchTextChange = {
                        searchText = it
                    },
                    onSearchClick = {
                        onSearchClick(searchText)
                    }
                )
            }

            item {
                CategorySubCategorySection(
                    subCategories = category.subCategories,
                    onSubCategoryClick = onSubCategoryClick
                )
            }

            item {
                CategoryCampaignSection(
                    campaigns = category.campaigns,
                    onCampaignClick = onCampaignClick
                )
            }

            item {
                CategoryQuickFilterSection(
                    filters = category.quickFilters
                )
            }

            item {
                CategoryProductSectionHeader()
            }

            items(category.products) { product ->
                CategoryProductRow(
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
private fun CategoryDetailTopBar(
    title: String,
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

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun CategoryDetailHero(
    category: RetailCategoryDetail
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
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.iconText,
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
                        text = category.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = category.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                CategoryStatPill(
                    title = "${category.productCount}",
                    subtitle = "ürün"
                )

                CategoryStatPill(
                    title = "${category.storeCount}",
                    subtitle = "mağaza"
                )

                CategoryStatPill(
                    title = "${category.campaignCount}",
                    subtitle = "kampanya"
                )
            }
        }
    }
}

@Composable
private fun CategoryStatPill(
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
private fun CategoryDetailSearchBox(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp)),
        placeholder = {
            Text(text = "Bu kategoride ara")
        },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = BbColors.Transparent,
            unfocusedIndicatorColor = BbColors.Transparent,
            disabledIndicatorColor = BbColors.Transparent
        ),
        trailingIcon = {
            Text(
                text = "Ara",
                modifier = Modifier
                    .padding(end = BbSpacing.Space3)
                    .clickable {
                        onSearchClick()
                    },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}

@Composable
private fun CategorySubCategorySection(
    subCategories: List<RetailSubCategoryItem>,
    onSubCategoryClick: (RetailSubCategoryItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RetailSectionTitle(
            title = "Alt kategoriler",
            description = "Doğrudan ürün akışına inmek için hızlı seçim."
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(subCategories) { subCategory ->
                SubCategoryCard(
                    subCategory = subCategory,
                    onClick = {
                        onSubCategoryClick(subCategory)
                    }
                )
            }
        }
    }
}

@Composable
private fun SubCategoryCard(
    subCategory: RetailSubCategoryItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(142.dp)
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
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subCategory.iconText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = subCategory.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${subCategory.productCount} ürün",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CategoryCampaignSection(
    campaigns: List<RetailCategoryCampaignItem>,
    onCampaignClick: (RetailCategoryCampaignItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RetailSectionTitle(
            title = "Kategori fırsatları",
            description = "Bu kategoriye bağlı aktif vitrinler ve kampanyalar."
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(campaigns) { campaign ->
                CategoryCampaignCard(
                    campaign = campaign,
                    onClick = {
                        onCampaignClick(campaign)
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryCampaignCard(
    campaign: RetailCategoryCampaignItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(BbLayout.LogoWidthLarge)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space4)
        ) {
            Text(
                text = campaign.badge,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = campaign.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = campaign.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CategoryQuickFilterSection(
    filters: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RetailSectionTitle(
            title = "Hızlı filtreler",
            description = "Listeye geçmeden önce akışı daralt."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            filters.forEachIndexed { index, filter ->
                FilterChip(
                    selected = index == 0,
                    onClick = {},
                    label = {
                        Text(text = filter)
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryProductSectionHeader() {
    RetailSectionTitle(
        title = "Öne çıkan ürünler",
        description = "Bu kategoride dikkat çeken ürünlerden kısa bir seçki."
    )
}

@Composable
private fun CategoryProductRow(
    product: RetailCategoryProductItem,
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
                    .size(68.dp)
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

                Text(
                    text = product.priceText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
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
private fun RetailSectionTitle(
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

data class RetailCategoryDetail(
    val id: Int,
    val name: String,
    val description: String,
    val iconText: String,
    val productCount: Int,
    val storeCount: Int,
    val campaignCount: Int,
    val subCategories: List<RetailSubCategoryItem>,
    val campaigns: List<RetailCategoryCampaignItem>,
    val quickFilters: List<String>,
    val products: List<RetailCategoryProductItem>
)

data class RetailSubCategoryItem(
    val id: Int,
    val name: String,
    val iconText: String,
    val productCount: Int
)

data class RetailCategoryCampaignItem(
    val id: Int,
    val title: String,
    val description: String,
    val badge: String
)

data class RetailCategoryProductItem(
    val id: Int,
    val name: String,
    val storeName: String,
    val priceText: String,
    val imageText: String
)

private fun getRetailCategoryDetail(categoryId: Int): RetailCategoryDetail {
    return RetailCategoryDetail(
        id = categoryId,
        name = "Moda",
        description = "Giyim, ayakkabı, çanta ve aksesuar ürünlerinde seçili mağazaları ve fırsatları keşfedin.",
        iconText = "MO",
        productCount = 18420,
        storeCount = 624,
        campaignCount = 12,
        subCategories = listOf(
            RetailSubCategoryItem(
                id = 1,
                name = "Kadın Giyim",
                iconText = "KG",
                productCount = 5320
            ),
            RetailSubCategoryItem(
                id = 2,
                name = "Erkek Giyim",
                iconText = "EG",
                productCount = 4210
            ),
            RetailSubCategoryItem(
                id = 3,
                name = "Ayakkabı",
                iconText = "AY",
                productCount = 3170
            ),
            RetailSubCategoryItem(
                id = 4,
                name = "Çanta",
                iconText = "ÇA",
                productCount = 1460
            )
        ),
        campaigns = listOf(
            RetailCategoryCampaignItem(
                id = 1,
                title = "Sezonun öne çıkanları",
                description = "Yeni gelen ürünlerde seçili fırsatlar.",
                badge = "Yeni sezon"
            ),
            RetailCategoryCampaignItem(
                id = 2,
                title = "Haftanın vitrinleri",
                description = "Popüler mağazalardan hızlı keşif.",
                badge = "Vitrin"
            ),
            RetailCategoryCampaignItem(
                id = 3,
                title = "Avantajlı ürünler",
                description = "Fiyat/performans ürünleri bir arada.",
                badge = "Fırsat"
            )
        ),
        quickFilters = listOf(
            "Popüler",
            "Yeni gelenler",
            "Çok satanlar",
            "İndirimli",
            "Ücretsiz kargo",
            "Yüksek puanlı"
        ),
        products = listOf(
            RetailCategoryProductItem(
                id = 1,
                name = "Kadın klasik sneaker ayakkabı",
                storeName = "Ortobella",
                priceText = "₺899,90",
                imageText = "P1"
            ),
            RetailCategoryProductItem(
                id = 2,
                name = "Oversize pamuklu basic tişört",
                storeName = "Moda Nova",
                priceText = "₺349,90",
                imageText = "P2"
            ),
            RetailCategoryProductItem(
                id = 3,
                name = "Günlük kullanım omuz çantası",
                storeName = "Urban Touch",
                priceText = "₺649,90",
                imageText = "P3"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryDetailScreenPreview() {
    MaterialTheme {
        CategoryDetailScreen()
    }
}