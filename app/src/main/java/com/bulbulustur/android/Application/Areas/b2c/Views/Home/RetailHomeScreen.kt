package com.bulbulustur.android.Application.Areas.b2c.Views.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components.B2CHomepageSpecialContents
import com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components.CampaignBanners
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
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
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialContentDTO
import com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components.B2CDealsOfTheDay
import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO

@Composable
fun RetailHomeScreen(
    campaigns: List<CampaignDTO> = emptyList(),
    dealsOfTheDays: List<DealsOfTheDayDTO> = emptyList(),
    specialContents: List<ProductHomepageSpecialContentDTO> = emptyList(),
    onSearchClick: (String) -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onProductDetailClick: (productId: Int, storeId: Int, variantId: Int) -> Unit = { _, _, _ -> },
    onDealClick: (productId: Int, storeId: Int, variantId: Int) -> Unit = { _, _, _ -> },
    onDealsOfTheDayListClick: () -> Unit = {},
    onCampaignClick: (Int) -> Unit = {},
    onCampaignListClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onStoreClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
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
                selectedItem = RetailBottomNavigationItem.Home,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        RetailBottomNavigationItem.Home -> Unit
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
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottomCompact
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                RetailHomeHeroCard(
                    onMenuClick = onMenuClick,
                    onProductListClick = onProductListClick
                )
            }

            item {
                RetailHomeQuickGatewayRow(
                    onMenuClick = onMenuClick,
                    onProductListClick = onProductListClick,
                    onStoreClick = onStoreClick,
                    onFavoriteClick = onFavoriteClick
                )
            }

            item {
                RetailHomeTrustStrip()
            }

            item {
                BbSectionHeader(
                    title = "Kategori Keşfi",
                    subtitle = "Ana kategorilerden başlayarak alışveriş dünyasına gir."
                )
            }

            item {
                RetailHomeCategoryChipRow(
                    onCategoryClick = onCategoryClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Bugünün Vitrinleri",
                    subtitle = "Perakende ana sayfa için seçilmiş hızlı geçişler."
                )
            }

            item {
                RetailHomeShowcaseRow(
                    onProductListClick = onProductListClick,
                    onStoreClick = onStoreClick,
                    onFavoriteClick = onFavoriteClick
                )
            }

            item {
                CampaignBanners(
                    campaigns = campaigns,
                    onCampaignClick = onCampaignClick,
                    onViewAllClick = onCampaignListClick
                )
            }

            item {
                B2CDealsOfTheDay(
                    dealsOfTheDays = dealsOfTheDays,
                    onProductClick = onDealClick,
                    onViewAllClick = onDealsOfTheDayListClick
                )
            }

            item {
                B2CHomepageSpecialContents(
                    specialContents = specialContents,
                    onProductClick = onProductDetailClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Mağaza Keşfi",
                    subtitle = "Seçilmiş mağaza ve marka alanları."
                )
            }

            item {
                RetailHomeStoreRow(
                    onStoreClick = onStoreClick
                )
            }
        }
    }
}

@Composable
private fun RetailHomeHeroCard(
    onMenuClick: () -> Unit,
    onProductListClick: () -> Unit
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Perakende Alışveriş",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Ürünleri, mağazaları ve kategori vitrinlerini keşfet.",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Bulbulustur Perakende; hızlı keşif, güvenli alışveriş ve temiz kategori geçişleri için tasarlandı.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = "Kategoriler",
                    onClick = onMenuClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Dark,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "Ürünleri Gez",
                    onClick = onProductListClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun RetailHomeQuickGatewayRow(
    onMenuClick: () -> Unit,
    onProductListClick: () -> Unit,
    onStoreClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val gateways = getRetailHomeGateways()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        items(
            items = gateways,
            key = { item -> item.Title }
        ) { item ->
            RetailHomeGatewayCard(
                item = item,
                onClick = {
                    when (item.Target) {
                        RetailHomeGatewayTarget.Menu -> onMenuClick()
                        RetailHomeGatewayTarget.Products -> onProductListClick()
                        RetailHomeGatewayTarget.Stores -> onStoreClick()
                        RetailHomeGatewayTarget.Favorites -> onFavoriteClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun RetailHomeGatewayCard(
    item: RetailHomeGatewayItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(168.dp),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .width(BBSpacing.Space11)
                    .height(BBSpacing.Space11)
                    .background(
                        color = item.BackgroundColor,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.Icon,
                    contentDescription = null,
                    tint = item.IconColor
                )
            }

            Text(
                text = item.Title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = item.Description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RetailHomeTrustStrip() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            RetailHomeTrustItem(
                icon = Icons.Outlined.Verified,
                title = "Güvenli",
                modifier = Modifier.weight(1f)
            )

            RetailHomeTrustItem(
                icon = Icons.Outlined.WorkspacePremium,
                title = "Seçili",
                modifier = Modifier.weight(1f)
            )

            RetailHomeTrustItem(
                icon = Icons.Outlined.Storefront,
                title = "Mağazalar",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun RetailHomeTrustItem(
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
private fun RetailHomeCategoryChipRow(
    onCategoryClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        items(
            items = getRetailHomeCategoryNames(),
            key = { item -> item }
        ) { item ->
            BbChip(
                text = item,
                selected = false,
                onClick = onCategoryClick
            )
        }
    }
}

@Composable
private fun RetailHomeShowcaseRow(
    onProductListClick: () -> Unit,
    onStoreClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val showcases = getRetailHomeShowcases()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        items(
            items = showcases,
            key = { item -> item.Title }
        ) { item ->
            RetailHomeShowcaseCard(
                item = item,
                onClick = {
                    when (item.Target) {
                        RetailHomeShowcaseTarget.Products -> onProductListClick()
                        RetailHomeShowcaseTarget.Stores -> onStoreClick()
                        RetailHomeShowcaseTarget.Favorites -> onFavoriteClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun RetailHomeShowcaseCard(
    item: RetailHomeShowcaseItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.width(236.dp),
        shape = BBRadius.XlShape,
        color = item.BackgroundColor,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = item.Icon,
                contentDescription = null,
                tint = item.IconColor
            )

            Text(
                text = item.Title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = item.Description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RetailHomeStoreRow(
    onStoreClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        items(
            items = getRetailHomeStores(),
            key = { item -> item.Name }
        ) { item ->
            RetailHomeStoreCard(
                item = item,
                onClick = onStoreClick
            )
        }
    }
}

@Composable
private fun RetailHomeStoreCard(
    item: RetailHomeStoreItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(220.dp),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = item.Name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = item.Description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Immutable
private data class RetailHomeGatewayItem(
    val Title: String,
    val Description: String,
    val Icon: ImageVector,
    val BackgroundColor: Color,
    val IconColor: Color,
    val Target: RetailHomeGatewayTarget
)

private enum class RetailHomeGatewayTarget {
    Menu,
    Products,
    Stores,
    Favorites
}

@Immutable
private data class RetailHomeShowcaseItem(
    val Title: String,
    val Description: String,
    val Icon: ImageVector,
    val BackgroundColor: Color,
    val IconColor: Color,
    val Target: RetailHomeShowcaseTarget
)

private enum class RetailHomeShowcaseTarget {
    Products,
    Stores,
    Favorites
}

@Immutable
private data class RetailHomeStoreItem(
    val Name: String,
    val Description: String
)

@Composable
private fun getRetailHomeGateways(): List<RetailHomeGatewayItem> {
    return listOf(
        RetailHomeGatewayItem(
            Title = "Kategoriler",
            Description = "Ana kategori kapısından başla",
            Icon = Icons.Outlined.Category,
            BackgroundColor = MaterialTheme.colorScheme.primaryContainer,
            IconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            Target = RetailHomeGatewayTarget.Menu
        ),
        RetailHomeGatewayItem(
            Title = "Ürünler",
            Description = "Perakende ürün akışına gir",
            Icon = Icons.Outlined.ShoppingBasket,
            BackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            IconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            Target = RetailHomeGatewayTarget.Products
        ),
        RetailHomeGatewayItem(
            Title = "Mağazalar",
            Description = "Satıcı ve marka vitrinleri",
            Icon = Icons.Outlined.Storefront,
            BackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            IconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            Target = RetailHomeGatewayTarget.Stores
        ),
        RetailHomeGatewayItem(
            Title = "Favoriler",
            Description = "Beğendiğin alanlara dön",
            Icon = Icons.Outlined.FavoriteBorder,
            BackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            IconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            Target = RetailHomeGatewayTarget.Favorites
        )
    )
}

private fun getRetailHomeCategoryNames(): List<String> {
    return listOf(
        "Elektronik",
        "Ev Yaşam",
        "Moda",
        "Kozmetik",
        "Spor",
        "Süpermarket",
        "Mobilya",
        "Oyuncak"
    )
}

@Composable
private fun getRetailHomeShowcases(): List<RetailHomeShowcaseItem> {
    return listOf(
        RetailHomeShowcaseItem(
            Title = "Yeni Gelenler",
            Description = "Bugün eklenen perakende ürünleri keşfet.",
            Icon = Icons.Outlined.NewReleases,
            BackgroundColor = MaterialTheme.colorScheme.primaryContainer,
            IconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            Target = RetailHomeShowcaseTarget.Products
        ),
        RetailHomeShowcaseItem(
            Title = "Kampanya Vitrinleri",
            Description = "Seçili fırsatlar ve dönemsel ürün akışları.",
            Icon = Icons.Outlined.LocalOffer,
            BackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            IconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            Target = RetailHomeShowcaseTarget.Products
        ),
        RetailHomeShowcaseItem(
            Title = "Mağaza Keşfi",
            Description = "Öne çıkan mağaza ve koleksiyonlara göz at.",
            Icon = Icons.Outlined.Storefront,
            BackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            IconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            Target = RetailHomeShowcaseTarget.Stores
        ),
        RetailHomeShowcaseItem(
            Title = "Favori Akışı",
            Description = "Beğendiğin ürün ve mağazalara hızlı dönüş.",
            Icon = Icons.Outlined.FavoriteBorder,
            BackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            IconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            Target = RetailHomeShowcaseTarget.Favorites
        )
    )
}

private fun getRetailHomeStores(): List<RetailHomeStoreItem> {
    return listOf(
        RetailHomeStoreItem(
            Name = "Bulbulustur Store",
            Description = "Seçilmiş ürünler"
        ),
        RetailHomeStoreItem(
            Name = "Ev & Yaşam Pazarı",
            Description = "Ev ihtiyaçları"
        ),
        RetailHomeStoreItem(
            Name = "Tekno Vitrin",
            Description = "Teknoloji ürünleri"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun RetailHomeScreenPreview() {
    BbTheme {
        RetailHomeScreen()
    }
}