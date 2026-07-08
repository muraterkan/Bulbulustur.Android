package com.bulbulustur.android.Application.Areas.b2c.Views.Category

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO

@Composable
fun CategoryDetailScreen(
    categoryId: Int = 1,
    categoryInfo: ProductCategoryDTO? = null,
    childCategories: List<ProductCategoryDTO> = emptyList(),
    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onSubCategoryClick: (RetailSubCategoryItem) -> Unit = {},
    onProductClick: (RetailCategoryProductItem) -> Unit = {},
    onCampaignClick: (RetailCategoryCampaignItem) -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    val category = getRetailCategoryDetail(
        categoryId = categoryId,
        categoryInfo = categoryInfo,
        childCategories = childCategories
    )

    var searchText by remember {
        mutableStateOf("")
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
                placeholder = "Ürün, kategori veya marka ara",
                leadingAction = RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick,
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
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
                onItemClick = { item ->
                    when (item) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                CategoryDetailHero(category = category)
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
                    onClick = {
                        onProductClick(product)
                    }
                )
            }
        }
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
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    radius = BBRadius.xl
                ) {
                    Text(
                        text = category.iconText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(modifier = Modifier.width(BBSpacing.Space4))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(BBSpacing.Space1))

                    Text(
                        text = category.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(BBSpacing.Space4))

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.PillShape
            )
            .padding(
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

@Composable
private fun CategorySubCategorySection(
    subCategories: List<RetailSubCategoryItem>,
    onSubCategoryClick: (RetailSubCategoryItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RetailSectionTitle(
            title = "Alt Kategoriler",
            description = "Doğrudan ürün akışına inmek için hızlı seçim."
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space3))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
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
    BbCard(
        modifier = Modifier.width(BBSpacing.Space24 + BBSpacing.Space12),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column {
            BbIconBox(
                size = BbIconBoxSize.Medium,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                radius = BBRadius.lg
            ) {
                Text(
                    text = subCategory.iconText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(BBSpacing.Space3))

            Text(
                text = subCategory.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(BBSpacing.Space1))

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
            title = "Kategori Vitrinleri",
            description = "Ürün, mağaza ve kampanya akışlarına hızlı geç."
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space3))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
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
    BbCard(
        modifier = Modifier.width(BBSpacing.Space24 + BBSpacing.Space16),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .background(
                        color = campaign.backgroundColor,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = campaign.icon,
                    contentDescription = null,
                    tint = campaign.iconColor,
                    modifier = Modifier.size(BBIcon.Ui)
                )
            }

            Spacer(modifier = Modifier.height(BBSpacing.Space4))

            Text(
                text = campaign.badge,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = campaign.iconColor
            )

            Spacer(modifier = Modifier.height(BBSpacing.Space2))

            Text(
                text = campaign.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(BBSpacing.Space2))

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
            title = "Hızlı Filtreler",
            description = "Listeye geçmeden önce akışı daralt."
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
        title = "Öne Çıkan Ürünler",
        description = "Bu kategoride dikkat çeken ürünlerden kısa bir seçki."
    )
}

@Composable
private fun CategoryProductRow(
    product: RetailCategoryProductItem,
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBSpacing.Space16 + BBSpacing.Space1)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.XlShape
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

            Spacer(modifier = Modifier.width(BBSpacing.Space4))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))

                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space2))

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

        Spacer(modifier = Modifier.height(BBSpacing.Space1))

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
    val badge: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val backgroundColor: androidx.compose.ui.graphics.Color,
    val iconColor: androidx.compose.ui.graphics.Color
)

data class RetailCategoryProductItem(
    val id: Int,
    val name: String,
    val storeName: String,
    val priceText: String,
    val imageText: String
)

@Composable
private fun getRetailCategoryDetail(
    categoryId: Int,
    categoryInfo: ProductCategoryDTO?,
    childCategories: List<ProductCategoryDTO>
): RetailCategoryDetail {
    return RetailCategoryDetail(
        id = categoryInfo?.ProductCategoryId ?: categoryId,
        name = categoryInfo?.CategoryName?.ifBlank { "Kategori" } ?: "Kategori",
        description = categoryInfo?.Breadcrumb?.ifBlank { "Kategori ürünlerini keşfedin." } ?: "Kategori ürünlerini keşfedin.",
        iconText = (categoryInfo?.CategoryName?.ifBlank { "KA" } ?: "KA").take(2).uppercase(),
        productCount = 18420,
        storeCount = 624,
        campaignCount = 12,
        subCategories =
            if (childCategories.isNotEmpty()) {
                childCategories.map { child ->
                    RetailSubCategoryItem(
                        id = child.ProductCategoryId,
                        name = child.CategoryName.ifBlank { "Kategori" },
                        iconText = child.CategoryName.ifBlank { "KA" }.take(2).uppercase(),
                        productCount = 0
                    )
                }
            } else {
                listOf(
                    RetailSubCategoryItem(1, "Kadın Giyim", "KG", 5320),
                    RetailSubCategoryItem(2, "Erkek Giyim", "EG", 4210),
                    RetailSubCategoryItem(3, "Ayakkabı", "AY", 3170),
                    RetailSubCategoryItem(4, "Çanta", "ÇA", 1460)
                )
            },
        campaigns = listOf(
            RetailCategoryCampaignItem(
                id = 1,
                title = "Sezonun öne çıkanları",
                description = "Yeni gelen ürünlerde seçili fırsatlar.",
                badge = "Yeni sezon",
                icon = Icons.Outlined.LocalOffer,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            RetailCategoryCampaignItem(
                id = 2,
                title = "Haftanın Vitrinleri",
                description = "Popüler mağazalardan hızlı keşif.",
                badge = "Vitrin",
                icon = Icons.Outlined.Storefront,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                iconColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            RetailCategoryCampaignItem(
                id = 3,
                title = "Avantajlı ürünler",
                description = "Fiyat/performans ürünleri bir arada.",
                badge = "Fırsat",
                icon = Icons.Outlined.Search,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ),
        quickFilters = listOf(
            "Popüler",
            "Yeni Gelenler",
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
