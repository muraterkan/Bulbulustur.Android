package com.bulbulustur.android.Application.Areas.b2c.Views.Campaign

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun CampaignDetailScreen(
    campaignId: Int = 1,
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductClick: (RetailCampaignProductItem) -> Unit = {},
    onCategoryClick: (RetailCampaignCategoryItem) -> Unit = {},
    onStoreClick: (RetailCampaignStoreItem) -> Unit = {}
) {
    val campaign = remember(campaignId) {
        getRetailCampaignDetail(campaignId)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedFilter by remember(campaignId) {
        mutableStateOf(
            campaign.productFilters.firstOrNull().orEmpty()
        )
    }

    val filteredProducts = remember(
        selectedFilter,
        campaign.products
    ) {
        if (
            selectedFilter.isBlank() ||
            selectedFilter == "Tümü"
        ) {
            campaign.products
        } else {
            campaign.products.filter { product ->
                product.filterTags.contains(selectedFilter)
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottomCompact
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.SectionGapCompact
            )
        ) {
            item {
                CampaignDetailPageHeading()
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
                BbSectionHeader(
                    title = "Kampanya Ürünleri",
                    subtitle = "Bu kampanyaya dahil seçili ürünler."
                )
            }

            items(
                items = filteredProducts,
                key = { product ->
                    product.id
                }
            ) { product ->
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
private fun CampaignDetailPageHeading() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Text(
            text = "Kampanya Detayları",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Fırsat kapsamını, koşulları ve kampanya Ürünlerini incele.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CampaignDetailHero(
    campaign: RetailCampaignDetail
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(BBSpacing.Space14)
                        .clip(BBRadius.XlShape)
                        .background(
                            MaterialTheme.colorScheme.primary
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = campaign.iconText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = campaign.badgeText,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = campaign.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Text(
                text = campaign.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                item {
                    CampaignDetailHeroPill(
                        title = campaign.discountText,
                        subtitle = "avantaj"
                    )
                }

                item {
                    CampaignDetailHeroPill(
                        title = campaign.endDateText,
                        subtitle = "süre"
                    )
                }

                item {
                    CampaignDetailHeroPill(
                        title = campaign.productCount.toString(),
                        subtitle = "ürün"
                    )
                }
            }
        }
    }
}

@Composable
private fun CampaignDetailHeroPill(
    title: String,
    subtitle: String
) {
    Surface(
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.72f
        )
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
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
}

@Composable
private fun CampaignSummaryCards(
    campaign: RetailCampaignDetail
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space2
        )
    ) {
        CampaignSummaryCard(
            modifier = Modifier.weight(1f),
            title = campaign.storeCount.toString(),
            subtitle = "maĞaza"
        )

        CampaignSummaryCard(
            modifier = Modifier.weight(1f),
            title = campaign.categoryCount.toString(),
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
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
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
private fun CampaignCategorySection(
    categories: List<RetailCampaignCategoryItem>,
    onCategoryClick: (RetailCampaignCategoryItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        BbSectionHeader(
            title = "Kapsamdaki Kategoriler",
            subtitle = "Kampanyanın geçerli olduĞu alışveriş alanları."
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            items(
                items = categories,
                key = { category ->
                    category.id
                }
            ) { category ->
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
    BbCard(
        modifier = Modifier.width(
            BBLayout.FixedWidth150
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
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

            Text(
                text = category.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

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
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        BbSectionHeader(
            title = "Katılan Mağazalar",
            subtitle = "Bu kampanyada öne çıkan Mağazalar."
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            items(
                items = stores,
                key = { store ->
                    store.id
                }
            ) { store ->
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
    BbCard(
        modifier = Modifier.width(
            BBSpacing.Space20 +
                    BBSpacing.Space20 +
                    BBSpacing.Space5
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
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

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = store.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

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
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            BbSectionHeader(
                title = "Kampanya Koşulları",
                subtitle = "Alışverişten önce bilinmesi gereken kısa notlar."
            )

            conditions.forEach { condition ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
                ) {
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = condition,
                        modifier = Modifier.weight(1f),
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
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        BbSectionHeader(
            title = "Ürün Filtresi",
            subtitle = "Kampanya Ürünlerini hızlıca daralt."
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            filters.forEach { filter ->
                BbChip(
                    text = filter,
                    selected = selectedFilter == filter,
                    onClick = {
                        onFilterChange(filter)
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
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Box(
                modifier = Modifier
                    .size(BBSpacing.Space16)
                    .clip(BBRadius.XlShape)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
                ) {
                    Text(
                        text = product.discountedPriceText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = product.discountText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Immutable
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

@Immutable
data class RetailCampaignCategoryItem(
    val id: Int,
    val name: String,
    val iconText: String,
    val productCount: Int
)

@Immutable
data class RetailCampaignStoreItem(
    val id: Int,
    val name: String,
    val logoText: String,
    val productCount: Int
)

@Immutable
data class RetailCampaignProductItem(
    val id: Int,
    val name: String,
    val storeName: String,
    val discountedPriceText: String,
    val discountText: String,
    val imageText: String,
    val filterTags: List<String>
)

private fun getRetailCampaignDetail(
    campaignId: Int
): RetailCampaignDetail {
    return RetailCampaignDetail(
        id = campaignId,
        title = "Sezonun öne çıkanları",
        description = "Moda kategorisinde yeni sezon ürünleri, seçili maĞaza vitrinleri ve avantajlı fiyatlarla hazırlanmış perakende kampanyası.",
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
                name = "Kadın giyim",
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
            "Kampanya seçili ürünlerde ve kampanyaya katılan Mağazalarda geçerlidir.",
            "Stok durumuna göre ürün görünürlüĞü ve fiyatlar deĞişebilir.",
            "Kargo avantajı maĞaza ve ürün koşullarına göre farklılık gösterebilir.",
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
                filterTags = listOf(
                    "Yeni sezon",
                    "İndirimli",
                    "Çok satan"
                )
            ),
            RetailCampaignProductItem(
                id = 2,
                name = "Oversize pamuklu basic tişört",
                storeName = "Moda Nova",
                discountedPriceText = "₺349,90",
                discountText = "%15",
                imageText = "P2",
                filterTags = listOf(
                    "Yeni sezon",
                    "İndirimli"
                )
            ),
            RetailCampaignProductItem(
                id = 3,
                name = "Günlük kullanım omuz çantası",
                storeName = "Urban Touch",
                discountedPriceText = "₺649,90",
                discountText = "%25",
                imageText = "P3",
                filterTags = listOf(
                    "Ücretsiz kargo",
                    "İndirimli"
                )
            ),
            RetailCampaignProductItem(
                id = 4,
                name = "Rahat taban günlük ayakkabı",
                storeName = "Ortobella",
                discountedPriceText = "₺749,90",
                discountText = "%18",
                imageText = "P4",
                filterTags = listOf(
                    "Çok satan",
                    "Ücretsiz kargo"
                )
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CampaignDetailScreenPreview() {
    BbTheme {
        CampaignDetailScreen()
    }
}
