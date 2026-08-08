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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
    onQuickFilterClick: (String) -> Unit = {},
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
                placeholder = BBLocalization.Current.Get(key = "e4f653c3-8828-4934-aa3b-959cede38feb", fallback = "Ürün, kategori veya marka ara"),
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
                CategoryQuickFilterSection(
                    filters = category.quickFilters,
                    onQuickFilterClick = onQuickFilterClick
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
                CategoryStatPill("${category.productCount}", BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = ""))
                CategoryStatPill("${category.storeCount}", BBLocalization.Current.Get(key = "a4bd79dd-e7ee-4407-9e7d-00582840c43a", fallback = "mağaza"))
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
            title = BBLocalization.Current.Get(key = "19e928cc-d4e4-426f-a1e8-fb8d9adf872f", fallback = ""),
            description = BBLocalization.Current.Get(key = "74a46e5a-2695-4867-8660-b0fe2b4f8528", fallback = "Doğrudan ürün akışına inmek için hızlı seçim.")
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
            title = BBLocalization.Current.Get(key = "21f6b0ee-67eb-40fb-899d-640fb99a7397", fallback = "Kategori Vitrinleri"),
            description = BBLocalization.Current.Get(key = "9beff001-6dd9-4017-9f1f-72ef6606495a", fallback = "Ürün, mağaza ve kampanya akışlarına hızlı geç.")
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
    filters: List<String>,
    onQuickFilterClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RetailSectionTitle(
            title = BBLocalization.Current.Get(key = "06861c0e-a393-4c0d-8851-978793cae548", fallback = ""),
            description = BBLocalization.Current.Get(key = "095ec6b8-65fd-4f79-9512-e675636a5455", fallback = "Listeye geçmeden önce akışı daralt.")
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            filters.forEach { filter ->
                AssistChip(
                    onClick = {
                        onQuickFilterClick(filter)
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
private fun CategoryProductSectionHeader() {
    RetailSectionTitle(
        title = BBLocalization.Current.Get(key = "1c7c6ac9-2b6d-46ec-90f0-3f88b65beb11", fallback = ""),
        description = BBLocalization.Current.Get(key = "4340b4b1-5348-4601-ad94-18ea0ca8f5cc", fallback = "Bu kategoride dikkat çeken ürünlerden kısa bir seçki.")
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
        name = categoryInfo?.CategoryName?.ifBlank { BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = "") } ?: BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = ""),
        description = categoryInfo?.Breadcrumb?.ifBlank { BBLocalization.Current.Get(key = "eaccf589-4817-4e98-a847-53257da7e56c", fallback = "Kategori ürünlerini keşfedin.") } ?: BBLocalization.Current.Get(key = "eaccf589-4817-4e98-a847-53257da7e56c", fallback = "Kategori ürünlerini keşfedin."),
        iconText = (categoryInfo?.CategoryName?.ifBlank { "KA" } ?: "KA").take(2).uppercase(),
        productCount = 18420,
        storeCount = 624,
        campaignCount = 12,
        subCategories =
            if (childCategories.isNotEmpty()) {
                childCategories.map { child ->
                    RetailSubCategoryItem(
                        id = child.ProductCategoryId,
                        name = child.CategoryName.ifBlank { BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = "") },
                        iconText = child.CategoryName.ifBlank { "KA" }.take(2).uppercase(),
                        productCount = 0
                    )
                }
            } else {
                listOf(
                    RetailSubCategoryItem(1, BBLocalization.Current.Get(key = "f481d8fc-9de9-4a6b-870d-1918537ae795", fallback = "Kadın Giyim"), "KG", 5320),
                    RetailSubCategoryItem(2, BBLocalization.Current.Get(key = "a32e412f-224f-494e-995e-04e6fd8550aa", fallback = "Erkek Giyim"), "EG", 4210),
                    RetailSubCategoryItem(3, "Ayakkabı", "AY", 3170),
                    RetailSubCategoryItem(4, "Çanta", "ÇA", 1460)
                )
            },
        campaigns = listOf(
            RetailCategoryCampaignItem(
                id = 1,
                title = BBLocalization.Current.Get(key = "1ed21217-a8d6-4f77-92fa-74f896f7095e", fallback = "Sezonun öne çıkanları"),
                description = BBLocalization.Current.Get(key = "978c0658-d963-40e6-ab55-197f5a2987b9", fallback = "Yeni gelen ürünlerde seçili fırsatlar."),
                badge = BBLocalization.Current.Get(key = "d0d0d256-d10b-4d42-9264-995b9315c54d", fallback = "Yeni sezon"),
                icon = Icons.Outlined.LocalOffer,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            RetailCategoryCampaignItem(
                id = 2,
                title = BBLocalization.Current.Get(key = "02d45695-958e-4458-9deb-d27d85a58d73", fallback = "Haftanın Vitrinleri"),
                description = BBLocalization.Current.Get(key = "cb15dd3d-ec2b-4ed9-ad0a-30c02fbff24c", fallback = "Popüler mağazalardan hızlı keşif."),
                badge = "Vitrin",
                icon = Icons.Outlined.Storefront,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                iconColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            RetailCategoryCampaignItem(
                id = 3,
                title = BBLocalization.Current.Get(key = "ac891c5a-522e-48a8-b750-744f9d6b364e", fallback = "Avantajlı ürünler"),
                description = BBLocalization.Current.Get(key = "46803f6f-2986-4bd3-8315-67d453e2bd72", fallback = "Fiyat/performans ürünleri bir arada."),
                badge = BBLocalization.Current.Get(key = "2499e0cc-b6ba-4d7d-92e7-d93d72414d14", fallback = "Fırsat"),
                icon = Icons.Outlined.Search,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ),
        quickFilters = listOf(
            BBLocalization.Current.Get(key = "c45d05c5-c097-40c4-9379-a7ec77726c36", fallback = "Popüler"),
            BBLocalization.Current.Get(key = "6788b820-f4b2-470b-92f8-7a8470387d4e", fallback = "Yeni Gelenler"),
            BBLocalization.Current.Get(key = "ba358f76-477e-4da3-b3e2-bc88c7ddc6df", fallback = "Çok satanlar"),
            BBLocalization.Current.Get(key = "5d1d3591-7f28-4ff3-95a2-e196e8faf1cb", fallback = "İndirimli"),
            BBLocalization.Current.Get(key = "fc8b89db-1fef-443a-9740-e1c37f44ca2f", fallback = "Ücretsiz kargo"),
            BBLocalization.Current.Get(key = "d1f63ea5-7c48-4767-a74f-2e7b6efdf474", fallback = "Yüksek puanlı")
        ),
        products = listOf(
            RetailCategoryProductItem(
                id = 1,
                name = BBLocalization.Current.Get(key = "cf2f4de0-711c-4308-a055-3ef7eb00d9c7", fallback = "Kadın klasik sneaker ayakkabı"),
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
                name = BBLocalization.Current.Get(key = "71e49e6e-e73e-4edd-88c3-3f835352d635", fallback = "Günlük kullanım omuz çantası"),
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
