package com.bulbulustur.android.Application.Areas.b2c.Views.Category

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.NewReleases
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.WorkspacePremium
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO

@Composable
fun RetailCategoryHomeScreen(
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    categories: List<ProductCategoryDTO> = emptyList(),
    onSubCategoryClick: (Int) -> Unit = {},
    onCampaignClick: () -> Unit = {},
    onStoreClick: () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val subCategories =
        if (categories.isNotEmpty()) {
            categories.mapIndexed { index, category ->
                RetailCategoryHomeSubCategoryItem(
                    id = category.ProductCategoryId,
                    title = category.CategoryName?.takeIf { it.isNotBlank() } ?: BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = ""),
                    description = category.Breadcrumb?.takeIf { it.isNotBlank() } ?: BBLocalization.Current.Get(key = "eb31aa30-3912-4a76-ac9b-457c319493f1", fallback = "Kategori ürünlerini keşfet"),
                    icon = Icons.Outlined.Category,
                    backgroundColor = when (index % 4) {
                        0 -> MaterialTheme.colorScheme.primaryContainer
                        1 -> MaterialTheme.colorScheme.surfaceVariant
                        2 -> MaterialTheme.colorScheme.secondaryContainer
                        else -> MaterialTheme.colorScheme.tertiaryContainer
                    },
                    iconColor = when (index % 4) {
                        0 -> MaterialTheme.colorScheme.onPrimaryContainer
                        1 -> MaterialTheme.colorScheme.onSurfaceVariant
                        2 -> MaterialTheme.colorScheme.onSecondaryContainer
                        else -> MaterialTheme.colorScheme.onTertiaryContainer
                    }
                )
            }
        } else {
            getRetailCategoryHomeSubCategories()
        }

    Scaffold(
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
                placeholder = BBLocalization.Current.Get(key = "e4f653c3-8828-4934-aa3b-959cede38feb", fallback = "Ürün, kategori veya marka ara"),
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
                            Unit
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
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.SectionGapCompact
            )
        ) {
            item {
                RetailCategoryHomeHeroCard(
                    onProductListClick = onProductListClick,
                    onStoreClick = onStoreClick
                )
            }

            item {
                RetailCategoryHomeGatewayRow(
                    onProductListClick = onProductListClick,
                    onStoreClick = onStoreClick,
                    onFavoriteClick = onFavoriteClick
                )
            }

            item {
                RetailCategoryHomeTrustStrip()
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(key = "19e928cc-d4e4-426f-a1e8-fb8d9adf872f", fallback = ""),
                    subtitle = BBLocalization.Current.Get(key = "5332539f-0ba4-4605-b655-ee387521b43f", fallback = "Bu kategori altındaki alışveriş kırılımlarını incele.")
                )
            }

            items(
                items = subCategories,
                key = { item ->
                    item.title
                }
            ) { item ->
                RetailCategoryHomeSubCategoryCard(
                    item = item,
                    onClick = {
                        onSubCategoryClick(item.id)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(key = "21f6b0ee-67eb-40fb-899d-640fb99a7397", fallback = "Kategori Vitrinleri"),
                    subtitle = BBLocalization.Current.Get(key = "9beff001-6dd9-4017-9f1f-72ef6606495a", fallback = "Ürün, mağaza ve kampanya akışlarına hızlı geç.")
                )
            }

            item {
                RetailCategoryHomeShowcaseRow(
                    onProductListClick = onProductListClick,
                    onCampaignClick = onCampaignClick,
                    onStoreClick = onStoreClick,
                    onFavoriteClick = onFavoriteClick
                )
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(key = "7b4d8fc2-76a4-4cdb-aacf-b52bb67c4661", fallback = "Popüler Aramalar"),
                    subtitle = BBLocalization.Current.Get(key = "7000c524-84e5-4469-9567-bee3f4137598", fallback = "Bu kategoride sık kullanılan başlıklar.")
                )
            }

            item {
                RetailCategoryHomePopularSearchChipRow(
                    onSearchClick = onSearchClick
                )
            }
        }
    }
}

@Composable
private fun RetailCategoryHomeHeroCard(
    onProductListClick: () -> Unit,
    onStoreClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(BBSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Category,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    text = BBLocalization.Current.Get(key = "62d92db8-bd1e-4cb7-8d00-5848a9478a6e", fallback = "Perakende Kategori"),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "18101507-39f0-482d-80e7-491992e2915b", fallback = "Elektronik"),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = BBLocalization.Current.Get(key = "fa6d7801-4ad5-43db-a588-cc1f1f8a3c01", fallback = "Alt kategorileri, ürün vitrinlerini ve mağaza geçişlerini bu alandan keşfet."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(key = "5e09b1c8-93e6-4e9a-a055-2f556f57d6dc", fallback = "Ürünleri Gör"),
                    onClick = onProductListClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Dark,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "91be3e9a-09f2-496b-806f-952a02209bb2", fallback = "Mağazalar"),
                    onClick = onStoreClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun RetailCategoryHomeGatewayRow(
    onProductListClick: () -> Unit,
    onStoreClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val gateways = getRetailCategoryHomeGateways()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items(
            items = gateways,
            key = { item ->
                item.title
            }
        ) { item ->
            RetailCategoryHomeGatewayCard(
                item = item,
                onClick = {
                    when (item.target) {
                        RetailCategoryHomeGatewayTarget.Products -> {
                            onProductListClick()
                        }

                        RetailCategoryHomeGatewayTarget.Stores -> {
                            onStoreClick()
                        }

                        RetailCategoryHomeGatewayTarget.Favorites -> {
                            onFavoriteClick()
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun RetailCategoryHomeGatewayCard(
    item: RetailCategoryHomeGatewayItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(168.dp),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Box(
                modifier = Modifier
                    .width(BBSpacing.Space11)
                    .height(BBSpacing.Space11)
                    .background(
                        color = item.backgroundColor,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = item.iconColor
                )
            }

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RetailCategoryHomeTrustStrip() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            RetailCategoryHomeTrustItem(
                icon = Icons.Outlined.Verified,
                title = BBLocalization.Current.Get(key = "aba99f7e-0b0a-45aa-96b2-6ac03f36582a", fallback = "Güvenli"),
                modifier = Modifier.weight(1f)
            )

            RetailCategoryHomeTrustItem(
                icon = Icons.Outlined.WorkspacePremium,
                title = BBLocalization.Current.Get(key = "c65ecb62-610f-4a60-9664-85404fe27a5c", fallback = "Seçili"),
                modifier = Modifier.weight(1f)
            )

            RetailCategoryHomeTrustItem(
                icon = Icons.Outlined.Storefront,
                title = BBLocalization.Current.Get(key = "91be3e9a-09f2-496b-806f-952a02209bb2", fallback = "Mağazalar"),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun RetailCategoryHomeTrustItem(
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun RetailCategoryHomeSubCategoryCard(
    item: RetailCategoryHomeSubCategoryItem,
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
                    .width(42.dp)
                    .height(42.dp)
                    .background(
                        color = item.backgroundColor,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = item.iconColor
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RetailCategoryHomeShowcaseRow(
    onProductListClick: () -> Unit,
    onCampaignClick: () -> Unit,
    onStoreClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val showcases = getRetailCategoryHomeShowcases()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items(
            items = showcases,
            key = { item ->
                item.title
            }
        ) { item ->
            RetailCategoryHomeShowcaseCard(
                item = item,
                onClick = {
                    when (item.target) {
                        RetailCategoryHomeShowcaseTarget.Products -> {
                            onProductListClick()
                        }

                        RetailCategoryHomeShowcaseTarget.Campaigns -> {
                            onCampaignClick()
                        }

                        RetailCategoryHomeShowcaseTarget.Stores -> {
                            onStoreClick()
                        }

                        RetailCategoryHomeShowcaseTarget.Favorites -> {
                            onFavoriteClick()
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun RetailCategoryHomeShowcaseCard(
    item: RetailCategoryHomeShowcaseItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.width(236.dp),
        shape = BBRadius.XlShape,
        color = item.backgroundColor,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = item.iconColor
            )

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RetailCategoryHomePopularSearchChipRow(
    onSearchClick: (String) -> Unit
) {
    val popularSearches = getRetailCategoryHomePopularSearches()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space2
        )
    ) {
        items(
            items = popularSearches,
            key = { item ->
                item
            }
        ) { item ->
            BbChip(
                text = item,
                selected = false,
                onClick = {
                    onSearchClick(item)
                }
            )
        }
    }
}

@Immutable
private data class RetailCategoryHomeGatewayItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: RetailCategoryHomeGatewayTarget
)

private enum class RetailCategoryHomeGatewayTarget {
    Products,
    Stores,
    Favorites
}

@Immutable
private data class RetailCategoryHomeSubCategoryItem(
    val id: Int,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color
)

@Immutable
private data class RetailCategoryHomeShowcaseItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: RetailCategoryHomeShowcaseTarget
)

private enum class RetailCategoryHomeShowcaseTarget {
    Products,
    Campaigns,
    Stores,
    Favorites
}

@Composable
private fun getRetailCategoryHomeGateways(): List<RetailCategoryHomeGatewayItem> {
    return listOf(
        RetailCategoryHomeGatewayItem(
            title = BBLocalization.Current.Get(key = "6cf7b92f-05e7-4ac7-be8c-ce98d8bf20c5", fallback = "Ürünler"),
            description = BBLocalization.Current.Get(key = "a4a0f1e9-0aff-4073-bf49-f0c661e20e66", fallback = "Kategori Ürünleri"),
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            target = RetailCategoryHomeGatewayTarget.Products
        ),
        RetailCategoryHomeGatewayItem(
            title = BBLocalization.Current.Get(key = "91be3e9a-09f2-496b-806f-952a02209bb2", fallback = "Mağazalar"),
            description = BBLocalization.Current.Get(key = "83c18147-dde5-4363-9cc6-fd02c3d77895", fallback = "Kategori mağazaları"),
            icon = Icons.Outlined.Storefront,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            target = RetailCategoryHomeGatewayTarget.Stores
        ),
        RetailCategoryHomeGatewayItem(
            title = BBLocalization.Current.Get(key = "b4370fd4-885d-41e7-928c-36b693ce3966", fallback = "Favoriler"),
            description = BBLocalization.Current.Get(key = "e43bd83a-f9c5-426b-9166-733a91bdcc28", fallback = "Beğenilen ürünler"),
            icon = Icons.Outlined.FavoriteBorder,
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            target = RetailCategoryHomeGatewayTarget.Favorites
        )
    )
}

@Composable
private fun getRetailCategoryHomeSubCategories(): List<RetailCategoryHomeSubCategoryItem> {
    return listOf(
        RetailCategoryHomeSubCategoryItem(
            id = 1,
            title = BBLocalization.Current.Get(key = "744aebc6-99c4-4de9-bef6-7b11c3e27c83", fallback = "Telefonlar"),
            description = "Akıllı telefon ve aksesuarları",
            icon = Icons.Outlined.Category,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        RetailCategoryHomeSubCategoryItem(
            id = 2,
            title = "Bilgisayar",
            description = "Notebook, masaüstü ve çevre birimleri",
            icon = Icons.Outlined.Category,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        RetailCategoryHomeSubCategoryItem(
            id = 3,
            title = "Akıllı Ev",
            description = BBLocalization.Current.Get(key = "a79a0ca1-07e2-495e-8b75-d826a12c632c", fallback = "Ev otomasyonu ve güvenlik ürünleri"),
            icon = Icons.Outlined.Category,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        RetailCategoryHomeSubCategoryItem(
            id = 4,
            title = BBLocalization.Current.Get(key = "ea909cd2-00f3-4b43-9038-efb1ddacaa71", fallback = "Ses ve Görüntü"),
            description = BBLocalization.Current.Get(key = "da20ff58-9a06-4bda-a0ac-5925064544da", fallback = "Kulaklık, hoparlör ve medya ürünleri"),
            icon = Icons.Outlined.Category,
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            iconColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    )
}

@Composable
private fun getRetailCategoryHomeShowcases(): List<RetailCategoryHomeShowcaseItem> {
    return listOf(
        RetailCategoryHomeShowcaseItem(
            title = BBLocalization.Current.Get(key = "6788b820-f4b2-470b-92f8-7a8470387d4e", fallback = "Yeni Gelenler"),
            description = BBLocalization.Current.Get(key = "2f319b47-267c-43c3-9ac4-c5d098496180", fallback = "Bu kategoriye yeni eklenen ürünleri keşfet."),
            icon = Icons.Outlined.NewReleases,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            target = RetailCategoryHomeShowcaseTarget.Products
        ),
        RetailCategoryHomeShowcaseItem(
            title = BBLocalization.Current.Get(key = "e2812624-6bbc-4034-9a09-6570540d0785", fallback = "Kampanyalar"),
            description = BBLocalization.Current.Get(key = "94958aa3-744e-471c-a8ce-19799fa91339", fallback = "Seçili indirim ve fırsat vitrinlerine bak."),
            icon = Icons.Outlined.LocalOffer,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            target = RetailCategoryHomeShowcaseTarget.Campaigns
        ),
        RetailCategoryHomeShowcaseItem(
            title = BBLocalization.Current.Get(key = "a4ebc64d-5787-4b21-bd80-f1daeb6068a2", fallback = "Mağaza Keşfi"),
            description = BBLocalization.Current.Get(key = "fa5ecca1-b907-42e7-9cdd-6b629848ac53", fallback = "Bu kategoride öne çıkan mağazaları incele."),
            icon = Icons.Outlined.Storefront,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            target = RetailCategoryHomeShowcaseTarget.Stores
        )
    )
}

private fun getRetailCategoryHomePopularSearches(): List<String> {
    return listOf(
        BBLocalization.Current.Get(key = "cf948c6a-2e6a-4f1e-b77b-13f8d15a1a67", fallback = "Telefon"),
        "Bluetooth kulaklık",
        "Notebook",
        "Akıllı saat",
        "Tablet",
        "Şarj adaptörü",
        "Oyuncu ekipmanı",
        BBLocalization.Current.Get(key = "b7f1f435-4725-479e-8235-90c93158645d", fallback = "Ev elektroniği")
    )
}

@Preview(showBackground = true)
@Composable
private fun RetailCategoryHomeScreenPreview() {
    BbTheme {
        RetailCategoryHomeScreen()
    }
}