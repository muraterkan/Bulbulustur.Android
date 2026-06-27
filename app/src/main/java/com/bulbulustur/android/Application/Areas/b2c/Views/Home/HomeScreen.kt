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
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun RetailHomeScreen(
    onSearchClick: (String) -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onProductDetailClick: () -> Unit = {},
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
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
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
                BbSectionHeader(
                    title = "Öne Çıkan Ürünler",
                    subtitle = "Demo akış, API sonrası gerçek Vitrinlerden beslenecek."
                )
            }

            item {
                RetailHomeProductRow(
                    onProductDetailClick = onProductDetailClick
                )
            }

            item {
                BbSectionHeader(
                    title = "MaĞaza Keşfi",
                    subtitle = "Seçilmiş maĞaza ve marka alanları."
                )
            }

            item {
                RetailHomeStoreRow(
                    onStoreClick = onStoreClick
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(BBSpacing.Space4)
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
                text = "Ürünleri, mağazaları ve Kategori Vitrinlerini Keşfet.",
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
            key = { item ->
                item.title
            }
        ) { item ->
            RetailHomeGatewayCard(
                item = item,
                onClick = {
                    when (item.target) {
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
            key = { item ->
                item
            }
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
            key = { item ->
                item.title
            }
        ) { item ->
            RetailHomeShowcaseCard(
                item = item,
                onClick = {
                    when (item.target) {
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
        color = item.backgroundColor,
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
private fun RetailHomeProductRow(
    onProductDetailClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        items(
            items = getRetailHomeProducts(),
            key = { item ->
                item.title
            }
        ) { item ->
            RetailHomeProductMiniCard(
                item = item,
                onClick = onProductDetailClick
            )
        }
    }
}

@Composable
private fun RetailHomeProductMiniCard(
    item: RetailHomeProductItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(176.dp),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(118.dp)
                    .background(
                        color = item.backgroundColor,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingBasket,
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
                text = item.price,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
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
            key = { item ->
                item.name
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
                    text = item.name,
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
}

@Immutable
private data class RetailHomeGatewayItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: RetailHomeGatewayTarget
)

private enum class RetailHomeGatewayTarget {
    Menu,
    Products,
    Stores,
    Favorites
}

@Immutable
private data class RetailHomeShowcaseItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: RetailHomeShowcaseTarget
)

private enum class RetailHomeShowcaseTarget {
    Products,
    Stores,
    Favorites
}

@Immutable
private data class RetailHomeProductItem(
    val title: String,
    val price: String,
    val backgroundColor: Color,
    val iconColor: Color
)

@Immutable
private data class RetailHomeStoreItem(
    val name: String,
    val description: String
)

@Composable
private fun getRetailHomeGateways(): List<RetailHomeGatewayItem> {
    return listOf(
        RetailHomeGatewayItem(
            title = "Kategoriler",
            description = "Ana kategori kapısından başla",
            icon = Icons.Outlined.Category,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            target = RetailHomeGatewayTarget.Menu
        ),
        RetailHomeGatewayItem(
            title = "Ürünler",
            description = "Perakende ürün akışına gir",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            target = RetailHomeGatewayTarget.Products
        ),
        RetailHomeGatewayItem(
            title = "Mağazalar",
            description = "Satıcı ve marka vitrinleri",
            icon = Icons.Outlined.Storefront,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            target = RetailHomeGatewayTarget.Stores
        ),
        RetailHomeGatewayItem(
            title = "Favoriler",
            description = "Beğendiğin alanlara dön",
            icon = Icons.Outlined.FavoriteBorder,
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            target = RetailHomeGatewayTarget.Favorites
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
            title = "Yeni Gelenler",
            description = "Bugün eklenen perakende ürünleri keşfet.",
            icon = Icons.Outlined.NewReleases,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            target = RetailHomeShowcaseTarget.Products
        ),
        RetailHomeShowcaseItem(
            title = "Kampanya Vitrinleri",
            description = "Seçili fırsatlar ve dönemsel ürün akışları.",
            icon = Icons.Outlined.LocalOffer,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            target = RetailHomeShowcaseTarget.Products
        ),
        RetailHomeShowcaseItem(
            title = "Mağaza Keşfi",
            description = "Öne çıkan mağaza ve koleksiyonlara göz at.",
            icon = Icons.Outlined.Storefront,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            target = RetailHomeShowcaseTarget.Stores
        ),
        RetailHomeShowcaseItem(
            title = "Favori Akışı",
            description = "Beğendiğin ürün ve mağazalara hızlı dönüş.",
            icon = Icons.Outlined.FavoriteBorder,
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            target = RetailHomeShowcaseTarget.Favorites
        )
    )
}

private fun getRetailHomeProducts(): List<RetailHomeProductItem> {
    return listOf(
        RetailHomeProductItem(
            title = "Akıllı ev ürünü",
            price = "₺749,90",
            backgroundColor = BBColors.Blue.Blue50,
            iconColor = BBColors.Blue.Blue700
        ),
        RetailHomeProductItem(
            title = "Mutfak yardımcısı",
            price = "₺399,90",
            backgroundColor = BBColors.Green.Green50,
            iconColor = BBColors.Green.Green700
        ),
        RetailHomeProductItem(
            title = "Yeni sezon çanta",
            price = "₺1.249,90",
            backgroundColor = BBColors.Pink.Pink50,
            iconColor = BBColors.Pink.Pink700
        ),
        RetailHomeProductItem(
            title = "Spor aksesuarı",
            price = "₺219,90",
            backgroundColor = BBColors.Purple.Purple50,
            iconColor = BBColors.Purple.Purple700
        )
    )
}

private fun getRetailHomeStores(): List<RetailHomeStoreItem> {
    return listOf(
        RetailHomeStoreItem(
            name = "Bulbulustur Store",
            description = "Seçilmiş ürünler"
        ),
        RetailHomeStoreItem(
            name = "Ev & Yaşam Pazarı",
            description = "Ev ihtiyaçları"
        ),
        RetailHomeStoreItem(
            name = "Tekno Vitrin",
            description = "Teknoloji ürünleri"
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


