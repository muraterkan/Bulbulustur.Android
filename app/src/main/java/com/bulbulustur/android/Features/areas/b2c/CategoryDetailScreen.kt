package com.bulbulustur.android.Features.areas.b2c

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
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
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbIconBox
import com.bulbulustur.android.Ui.components.BbIconBoxSize
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTheme

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
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGapCompact)
        ) {
            item {
                CategoryDetailTopBar(
                    title = category.name,
                    onBackClick = onBackClick
                )
            }

            item {
                CategoryDetailHero(category = category)
            }

            item {
                CategoryDetailSearchBox(
                    searchText = searchText,
                    onSearchTextChange = { searchText = it },
                    onSearchClick = { onSearchClick(searchText) }
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
                CategoryQuickFilterSection(filters = category.quickFilters)
            }

            item {
                CategoryProductSectionHeader()
            }

            items(category.products) { product ->
                CategoryProductRow(
                    product = product,
                    onClick = { onProductClick(product) }
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
        BbIconBox(
            modifier = Modifier.clickable { onBackClick() },
            size = BbIconBoxSize.Medium,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Geri",
                modifier = Modifier.size(BbIcon.TopBarIcon)
            )
        }

        Spacer(modifier = Modifier.width(BbSpacing.Space3))

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
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Default,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BbIconBox(
                    size = BbIconBoxSize.Xl,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Text(
                        text = category.iconText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(modifier = Modifier.width(BbSpacing.Space4))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(BbSpacing.Space1))

                    Text(
                        text = category.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(BbSpacing.Space4))

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                CategoryStatPill("${category.productCount}", "ürün")
                CategoryStatPill("${category.storeCount}", "mağaza")
                CategoryStatPill("${category.campaignCount}", "kampanya")
            }
        }
    }
}

@Composable
private fun CategoryStatPill(
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .clip(BbRadius.PillShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            )
    ) {
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

@Composable
private fun CategoryDetailSearchBox(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(BbRadius.Input)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.InputPaddingHorizontal,
                vertical = BbSpacing.InputPaddingVertical
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            decorationBox = { innerTextField ->
                if (searchText.isEmpty()) {
                    Text(
                        text = "Bu kategoride ara",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                innerTextField()
            }
        )

        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Ara",
            modifier = Modifier
                .size(BbIcon.Action)
                .clickable { onSearchClick() },
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
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

        Spacer(modifier = Modifier.height(BbSpacing.Space3))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            items(subCategories) { subCategory ->
                SubCategoryCard(
                    subCategory = subCategory,
                    onClick = { onSubCategoryClick(subCategory) }
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
    BbCard(
        modifier = Modifier
            .width(BbSpacing.Space24 + BbSpacing.Space12)
            .clickable { onClick() },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column {
            BbIconBox(
                size = BbIconBoxSize.Medium,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Text(
                    text = subCategory.iconText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(BbSpacing.Space3))

            Text(
                text = subCategory.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(BbSpacing.Space1))

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

        Spacer(modifier = Modifier.height(BbSpacing.Space3))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            items(campaigns) { campaign ->
                CategoryCampaignCard(
                    campaign = campaign,
                    onClick = { onCampaignClick(campaign) }
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
    BbCard(
        modifier = Modifier
            .width(BbSpacing.Space20 + BbSpacing.Space16)
            .clickable { onClick() },
        variant = BbCardVariant.Default,
        padding = BbCardPadding.Medium
    ) {
        Column {
            Text(
                text = campaign.badge,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.Purple.Purple600
            )

            Spacer(modifier = Modifier.height(BbSpacing.Space2))

            Text(
                text = campaign.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(BbSpacing.Space2))

            Text(
                text = campaign.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            filters.forEach { filter ->
                AssistChip(
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
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space16 + BbSpacing.Space1)
                    .clip(BbRadius.XlShape)
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

            Spacer(modifier = Modifier.width(BbSpacing.Space4))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(BbSpacing.Space1))

                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(BbSpacing.Space2))

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

        Spacer(modifier = Modifier.height(BbSpacing.Space1))

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
            RetailSubCategoryItem(1, "Kadın Giyim", "KG", 5320),
            RetailSubCategoryItem(2, "Erkek Giyim", "EG", 4210),
            RetailSubCategoryItem(3, "Ayakkabı", "AY", 3170),
            RetailSubCategoryItem(4, "Çanta", "ÇA", 1460)
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
    BbTheme {
        CategoryDetailScreen()
    }
}