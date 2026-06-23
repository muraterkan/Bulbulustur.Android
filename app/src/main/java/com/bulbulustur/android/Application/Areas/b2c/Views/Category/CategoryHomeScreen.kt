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
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

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
    onSubCategoryClick: () -> Unit = {},
    onCampaignClick: () -> Unit = {},
    onStoreClick: () -> Unit = {}
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
                    title = "Alt Kategoriler",
                    subtitle = "Bu kategori altındaki alışveriş kırılımlarını incele."
                )
            }

            items(
                items = getRetailCategoryHomeSubCategories(),
                key = { item ->
                    item.title
                }
            ) { item ->
                RetailCategoryHomeSubCategoryCard(
                    item = item,
                    onClick = onSubCategoryClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Kategori Vitrinleri",
                    subtitle = "Ürün, mağaza ve kampanya akışlarına hızlı geç."
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
                    title = "Popüler Aramalar",
                    subtitle = "Bu kategoride sık kullanılan başlıklar."
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
        color = BBColors.Yellow.Yellow100
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BBColors.Yellow.Yellow100)
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
                    tint = BBColors.TextStrong
                )

                Text(
                    text = "Perakende Kategori",
                    style = MaterialTheme.typography.labelLarge,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Elektronik",
                style = MaterialTheme.typography.headlineSmall,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Alt Kategorileri, ürün Vitrinlerini ve mağaza geçişlerini bu alandan keşfet.",
                style = MaterialTheme.typography.bodyMedium,
                color = BBColors.TextSubtle
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                BbButton(
                    text = "Ürünleri Gör",
                    onClick = onProductListClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Dark,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "Mağazalar",
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
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items(
            items = getRetailCategoryHomeGateways(),
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
                title = "Güvenli",
                modifier = Modifier.weight(1f)
            )

            RetailCategoryHomeTrustItem(
                icon = Icons.Outlined.WorkspacePremium,
                title = "Seçili",
                modifier = Modifier.weight(1f)
            )

            RetailCategoryHomeTrustItem(
                icon = Icons.Outlined.Storefront,
                title = "Mağazalar",
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
            tint = BBColors.Primary
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
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items(
            items = getRetailCategoryHomeShowcases(),
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
            width = 1.dp,
            color = BBColors.Border
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
                color = BBColors.TextStrong,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextSubtle
            )
        }
    }
}

@Composable
private fun RetailCategoryHomePopularSearchChipRow(
    onSearchClick: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space2
        )
    ) {
        items(
            items = getRetailCategoryHomePopularSearches(),
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

private fun getRetailCategoryHomeGateways(): List<RetailCategoryHomeGatewayItem> {
    return listOf(
        RetailCategoryHomeGatewayItem(
            title = "Ürünler",
            description = "Bu kategorideki ürünler",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = BBColors.Blue.Blue50,
            iconColor = BBColors.Blue.Blue700,
            target = RetailCategoryHomeGatewayTarget.Products
        ),
        RetailCategoryHomeGatewayItem(
            title = "Mağazalar",
            description = "Kategori mağazaları",
            icon = Icons.Outlined.Storefront,
            backgroundColor = BBColors.Green.Green50,
            iconColor = BBColors.Green.Green700,
            target = RetailCategoryHomeGatewayTarget.Stores
        ),
        RetailCategoryHomeGatewayItem(
            title = "Favoriler",
            description = "Beğenilen ürünler",
            icon = Icons.Outlined.FavoriteBorder,
            backgroundColor = BBColors.Pink.Pink50,
            iconColor = BBColors.Pink.Pink700,
            target = RetailCategoryHomeGatewayTarget.Favorites
        )
    )
}

private fun getRetailCategoryHomeSubCategories(): List<RetailCategoryHomeSubCategoryItem> {
    return listOf(
        RetailCategoryHomeSubCategoryItem(
            title = "Telefonlar",
            description = "Akıllı telefon ve aksesuarları",
            icon = Icons.Outlined.Category,
            backgroundColor = BBColors.Yellow.Yellow50,
            iconColor = BBColors.Navy.Navy900
        ),
        RetailCategoryHomeSubCategoryItem(
            title = "Bilgisayar",
            description = "Notebook, masaüstü ve çevre birimleri",
            icon = Icons.Outlined.Category,
            backgroundColor = BBColors.Blue.Blue50,
            iconColor = BBColors.Blue.Blue700
        ),
        RetailCategoryHomeSubCategoryItem(
            title = "Akıllı Ev",
            description = "Ev otomasyonu ve güvenlik ürünleri",
            icon = Icons.Outlined.Category,
            backgroundColor = BBColors.Green.Green50,
            iconColor = BBColors.Green.Green700
        ),
        RetailCategoryHomeSubCategoryItem(
            title = "Ses ve Görüntü",
            description = "Kulaklık, hoparlör ve medya Ürünleri",
            icon = Icons.Outlined.Category,
            backgroundColor = BBColors.Purple.Purple50,
            iconColor = BBColors.Purple.Purple700
        )
    )
}

private fun getRetailCategoryHomeShowcases(): List<RetailCategoryHomeShowcaseItem> {
    return listOf(
        RetailCategoryHomeShowcaseItem(
            title = "Yeni Gelenler",
            description = "Bu kategoriye yeni eklenen ürünleri keşfet.",
            icon = Icons.Outlined.NewReleases,
            backgroundColor = BBColors.Yellow.Yellow50,
            iconColor = BBColors.Navy.Navy900,
            target = RetailCategoryHomeShowcaseTarget.Products
        ),
        RetailCategoryHomeShowcaseItem(
            title = "Kampanyalar",
            description = "Seçili indirim ve fırsat Vitrinlerine bak.",
            icon = Icons.Outlined.LocalOffer,
            backgroundColor = BBColors.Green.Green50,
            iconColor = BBColors.Green.Green700,
            target = RetailCategoryHomeShowcaseTarget.Campaigns
        ),
        RetailCategoryHomeShowcaseItem(
            title = "Mağaza Keşfi",
            description = "Bu kategoride öne çıkan Mağazaları incele.",
            icon = Icons.Outlined.Storefront,
            backgroundColor = BBColors.Blue.Blue50,
            iconColor = BBColors.Blue.Blue700,
            target = RetailCategoryHomeShowcaseTarget.Stores
        )
    )
}

private fun getRetailCategoryHomePopularSearches(): List<String> {
    return listOf(
        "Telefon",
        "Bluetooth kulaklık",
        "Notebook",
        "Akıllı saat",
        "Tablet",
        "Şarj adaptörü",
        "Oyuncu ekipmanı",
        "Ev elektroniği"
    )
}

@Preview(showBackground = true)
@Composable
private fun RetailCategoryHomeScreenPreview() {
    BbTheme {
        RetailCategoryHomeScreen()
    }
}