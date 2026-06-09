package com.bulbulustur.android.features.retail

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
import androidx.compose.material.icons.outlined.Search
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
import com.bulbulustur.android.features.retail.components.RetailBottomNavigation
import com.bulbulustur.android.features.retail.components.RetailBottomNavigationItem
import com.bulbulustur.android.features.retail.components.RetailSearchHeader
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun RetailHomeScreen(
    onSearchClick: (String) -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onProductDetailClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onStoreClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
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
                        RetailBottomNavigationItem.Messages -> onMessageClick()
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
                start = BbSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGapCompact)
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
                    title = "Kategori keşfi",
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
                    title = "Bugünün vitrinleri",
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
                    title = "Öne çıkan ürünler",
                    subtitle = "Demo akış, API sonrası gerçek vitrinlerden beslenecek."
                )
            }

            item {
                RetailHomeProductRow(
                    onProductDetailClick = onProductDetailClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Mağaza keşfi",
                    subtitle = "Seçilmiş mağaza ve marka alanları."
                )
            }

            item {
                RetailHomeStoreRow(
                    onStoreClick = onStoreClick
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(BbSpacing.Space4)
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
        shape = BbRadius.XlShape,
        color = BbColors.Yellow.Yellow100
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BbColors.Yellow.Yellow100)
                .padding(BbSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = BbColors.TextStrong
                )

                Text(
                    text = "Perakende alışveriş",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Ürünleri, mağazaları ve kategori vitrinlerini keşfet.",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Bulbulustur Perakende; hızlı keşif, güvenli alışveriş ve temiz kategori geçişleri için tasarlandı.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextSubtle
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getRetailHomeGateways(),
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .width(44.dp)
                    .height(44.dp)
                    .background(
                        color = item.backgroundColor,
                        shape = BbRadius.LgShape
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.Primary
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getRetailHomeShowcases(),
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
        shape = BbRadius.XlShape,
        color = item.backgroundColor,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = item.iconColor
            )

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextSubtle
            )
        }
    }
}

@Composable
private fun RetailHomeProductRow(
    onProductDetailClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(118.dp)
                    .background(
                        color = item.backgroundColor,
                        shape = BbRadius.LgShape
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
                color = BbColors.TextStrong,
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
                    .background(
                        color = BbColors.PrimarySoft,
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = BbColors.TextStrong
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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

private fun getRetailHomeGateways(): List<RetailHomeGatewayItem> {
    return listOf(
        RetailHomeGatewayItem(
            title = "Kategoriler",
            description = "Ana kategori kapısından başla",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = RetailHomeGatewayTarget.Menu
        ),
        RetailHomeGatewayItem(
            title = "Ürünler",
            description = "Perakende ürün akışına gir",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = RetailHomeGatewayTarget.Products
        ),
        RetailHomeGatewayItem(
            title = "Mağazalar",
            description = "Satıcı ve marka vitrinleri",
            icon = Icons.Outlined.Storefront,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = RetailHomeGatewayTarget.Stores
        ),
        RetailHomeGatewayItem(
            title = "Favoriler",
            description = "Beğendiğin alanlara dön",
            icon = Icons.Outlined.FavoriteBorder,
            backgroundColor = BbColors.Pink.Pink50,
            iconColor = BbColors.Pink.Pink700,
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

private fun getRetailHomeShowcases(): List<RetailHomeShowcaseItem> {
    return listOf(
        RetailHomeShowcaseItem(
            title = "Yeni gelenler",
            description = "Bugün eklenen perakende ürünleri keşfet.",
            icon = Icons.Outlined.NewReleases,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = RetailHomeShowcaseTarget.Products
        ),
        RetailHomeShowcaseItem(
            title = "Kampanya vitrinleri",
            description = "Seçili fırsatlar ve dönemsel ürün akışları.",
            icon = Icons.Outlined.LocalOffer,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = RetailHomeShowcaseTarget.Products
        ),
        RetailHomeShowcaseItem(
            title = "Mağaza keşfi",
            description = "Öne çıkan mağaza ve koleksiyonlara göz at.",
            icon = Icons.Outlined.Storefront,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = RetailHomeShowcaseTarget.Stores
        ),
        RetailHomeShowcaseItem(
            title = "Favori akışı",
            description = "Beğendiğin ürün ve mağazalara hızlı dönüş.",
            icon = Icons.Outlined.FavoriteBorder,
            backgroundColor = BbColors.Pink.Pink50,
            iconColor = BbColors.Pink.Pink700,
            target = RetailHomeShowcaseTarget.Favorites
        )
    )
}

private fun getRetailHomeProducts(): List<RetailHomeProductItem> {
    return listOf(
        RetailHomeProductItem(
            title = "Akıllı ev ürünü",
            price = "₺749,90",
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700
        ),
        RetailHomeProductItem(
            title = "Mutfak yardımcısı",
            price = "₺399,90",
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700
        ),
        RetailHomeProductItem(
            title = "Yeni sezon çanta",
            price = "₺1.249,90",
            backgroundColor = BbColors.Pink.Pink50,
            iconColor = BbColors.Pink.Pink700
        ),
        RetailHomeProductItem(
            title = "Spor aksesuarı",
            price = "₺219,90",
            backgroundColor = BbColors.Purple.Purple50,
            iconColor = BbColors.Purple.Purple700
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
