package com.bulbulustur.android.Application.Areas.b2c.Views.Home

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalOffer
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
import com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components.B2CDealsOfTheDay
import com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components.B2CHomepageSpecialContents
import com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components.CampaignBanners
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialContentDTO

@Composable
fun RetailHomeScreen(
    campaigns: List<CampaignDTO> = emptyList(),
    dealsOfTheDays: List<DealsOfTheDayDTO> = emptyList(),
    specialContents: List<ProductHomepageSpecialContentDTO> = emptyList(),
    onSearchClick: (String) -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onProductDetailClick: (
        productId: Int,
        storeId: Int,
        variantId: Int
    ) -> Unit = { _, _, _ -> },
    onDealClick: (
        productId: Int,
        storeId: Int,
        variantId: Int
    ) -> Unit = { _, _, _ -> },
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
                RetailHomeHeroCard()
            }

            item {
                RetailHomeQuickGatewayRow(
                    onCampaignListClick = onCampaignListClick,
                    onDealsOfTheDayListClick =
                        onDealsOfTheDayListClick,
                    onStoreClick = onStoreClick,
                    onFavoriteClick = onFavoriteClick
                )
            }

            item {
                RetailHomeTrustStrip()
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
                    title = BBLocalization.Current.Get(key = "a4ebc64d-5787-4b21-bd80-f1daeb6068a2", fallback = "Mağaza Keşfi"),
                    subtitle = BBLocalization.Current.Get(key = "95717485-49c7-4c16-b6a7-585616ecac69", fallback = "Seçilmiş mağaza ve marka alanları.")
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
private fun RetailHomeHeroCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primaryContainer
                )
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
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "adfb92da-e66b-4549-b73e-ebc51a56acbb", fallback = "Perakende Alışveriş"),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "1e31ff0f-3b44-4965-9fcf-9dc8010c51b9", fallback = "Ürünleri, mağazaları ve kampanya vitrinlerini keşfet."),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = BBLocalization.Current.Get(key = "d2aa284d-6db6-4793-baed-92051b5c8ccb", fallback = "Bulbulustur Perakende; hızlı keşif, güvenli alışveriş ve seçilmiş fırsatlar için tasarlandı."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RetailHomeQuickGatewayRow(
    onCampaignListClick: () -> Unit,
    onDealsOfTheDayListClick: () -> Unit,
    onStoreClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val gateways = getRetailHomeGateways()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items(
            items = gateways,
            key = { item ->
                item.Title
            }
        ) { item ->
            RetailHomeGatewayCard(
                item = item,
                onClick = {
                    when (item.Target) {
                        RetailHomeGatewayTarget.Campaigns ->
                            onCampaignListClick()

                        RetailHomeGatewayTarget.DealsOfTheDay ->
                            onDealsOfTheDayListClick()

                        RetailHomeGatewayTarget.Stores ->
                            onStoreClick()

                        RetailHomeGatewayTarget.Favorites ->
                            onFavoriteClick()
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
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
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
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
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            RetailHomeTrustItem(
                icon = Icons.Outlined.Verified,
                title = BBLocalization.Current.Get(key = "aba99f7e-0b0a-45aa-96b2-6ac03f36582a", fallback = "Güvenli"),
                modifier = Modifier.weight(1f)
            )

            RetailHomeTrustItem(
                icon = Icons.Outlined.WorkspacePremium,
                title = BBLocalization.Current.Get(key = "c65ecb62-610f-4a60-9664-85404fe27a5c", fallback = "Seçili"),
                modifier = Modifier.weight(1f)
            )

            RetailHomeTrustItem(
                icon = Icons.Outlined.Storefront,
                title = BBLocalization.Current.Get(key = "91be3e9a-09f2-496b-806f-952a02209bb2", fallback = "Mağazalar"),
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
private fun RetailHomeStoreRow(
    onStoreClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items(
            items = getRetailHomeStores(),
            key = { item ->
                item.Name
            }
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
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Box(
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
                    .background(
                        color =
                            MaterialTheme.colorScheme.primaryContainer,
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
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
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
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
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
    Campaigns,
    DealsOfTheDay,
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
            Title = BBLocalization.Current.Get(key = "e2812624-6bbc-4034-9a09-6570540d0785", fallback = "Kampanyalar"),
            Description = BBLocalization.Current.Get(key = "9dd02f4a-0504-4948-b0e0-d6bc9695a6a0", fallback = "Aktif kampanya vitrinlerini keşfet"),
            Icon = Icons.Outlined.LocalOffer,
            BackgroundColor =
                MaterialTheme.colorScheme.primaryContainer,
            IconColor =
                MaterialTheme.colorScheme.onPrimaryContainer,
            Target = RetailHomeGatewayTarget.Campaigns
        ),
        RetailHomeGatewayItem(
            Title = BBLocalization.Current.Get(key = "66913b06-a571-41d5-8a2d-dae82701da35", fallback = "Günün Fırsatları"),
            Description = BBLocalization.Current.Get(key = "7e8d30d8-ae65-4694-9c72-eae8465b91eb", fallback = "Bugüne özel seçili fırsatları incele"),
            Icon = Icons.Outlined.LocalOffer,
            BackgroundColor =
                MaterialTheme.colorScheme.surfaceVariant,
            IconColor =
                MaterialTheme.colorScheme.onSurfaceVariant,
            Target = RetailHomeGatewayTarget.DealsOfTheDay
        ),
        RetailHomeGatewayItem(
            Title = BBLocalization.Current.Get(key = "91be3e9a-09f2-496b-806f-952a02209bb2", fallback = "Mağazalar"),
            Description = BBLocalization.Current.Get(key = "dfd83b43-54ab-4a40-a64b-6d1f747cb0ef", fallback = "Satıcı ve marka vitrinlerini keşfet"),
            Icon = Icons.Outlined.Storefront,
            BackgroundColor =
                MaterialTheme.colorScheme.secondaryContainer,
            IconColor =
                MaterialTheme.colorScheme.onSecondaryContainer,
            Target = RetailHomeGatewayTarget.Stores
        ),
        RetailHomeGatewayItem(
            Title = BBLocalization.Current.Get(key = "b4370fd4-885d-41e7-928c-36b693ce3966", fallback = "Favoriler"),
            Description = BBLocalization.Current.Get(key = "525a5767-cdbe-45cb-a279-40324a1780bf", fallback = "Beğendiğin ürün ve mağazalara dön"),
            Icon = Icons.Outlined.FavoriteBorder,
            BackgroundColor =
                MaterialTheme.colorScheme.tertiaryContainer,
            IconColor =
                MaterialTheme.colorScheme.onTertiaryContainer,
            Target = RetailHomeGatewayTarget.Favorites
        )
    )
}

private fun getRetailHomeStores(): List<RetailHomeStoreItem> {
    return listOf(
        RetailHomeStoreItem(
            Name = "Bulbulustur Store",
            Description = BBLocalization.Current.Get(key = "70484117-ffbc-4e50-b11a-81eaec5afdf5", fallback = "Seçilmiş ürünler")
        ),
        RetailHomeStoreItem(
            Name = BBLocalization.Current.Get(key = "0dff2601-0707-4fb9-a69e-0de051e6ca1b", fallback = "Ev & Yaşam Pazarı"),
            Description = BBLocalization.Current.Get(key = "37c33f8d-5a50-4fe2-9cf0-3e48c47de217", fallback = "Ev ihtiyaçları")
        ),
        RetailHomeStoreItem(
            Name = BBLocalization.Current.Get(key = "5a21ee07-c8eb-468d-9757-313254be909a", fallback = "Tekno Vitrin"),
            Description = BBLocalization.Current.Get(key = "9493a182-cb22-4679-91ce-ba73b34417b8", fallback = "Teknoloji ürünleri")
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