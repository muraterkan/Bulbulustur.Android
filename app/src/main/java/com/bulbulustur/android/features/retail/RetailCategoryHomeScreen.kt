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
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.NewReleases
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
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
fun RetailCategoryHomeScreen(
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onSubCategoryClick: () -> Unit = {},
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
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        RetailBottomNavigationItem.Home -> onHomeClick()
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
                RetailCategoryHeroCard(
                    onProductListClick = onProductListClick,
                    onSubCategoryClick = onSubCategoryClick
                )
            }

            item {
                RetailCategoryQuickStartRow(
                    onSubCategoryClick = onSubCategoryClick,
                    onProductListClick = onProductListClick,
                    onStoreClick = onStoreClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Alt Kategoriler",
                    subtitle = "Bu ana kategori içindeki alt kırılımları keşfet."
                )
            }

            items(
                items = getRetailSubCategories(),
                key = { item ->
                    item.title
                }
            ) { item ->
                RetailSubCategoryCard(
                    item = item,
                    onClick = onSubCategoryClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Kategori vitrinleri",
                    subtitle = "Perakende kategori ana sayfasından ürün akışına geç."
                )
            }

            item {
                RetailCategoryShowcaseRow(
                    onProductListClick = onProductListClick,
                    onStoreClick = onStoreClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Popüler aramalar",
                    subtitle = "Bu kategori içinde sık keşfedilen alanlar"
                )
            }

            item {
                RetailPopularSearchChipRow(
                    onProductListClick = onProductListClick
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
private fun RetailCategoryHeroCard(
    onProductListClick: () -> Unit,
    onSubCategoryClick: () -> Unit
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
                    imageVector = Icons.Outlined.Category,
                    contentDescription = null,
                    tint = BbColors.TextStrong
                )

                Text(
                    text = "Perakende kategori",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Elektronik Parçalar",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Alt kategorileri incele, ürün vitrinlerine göz at ve seçili kategori içinde alışverişe başla.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextSubtle
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "Ürünleri Gör",
                    onClick = onProductListClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Dark,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "Alt Kategoriler",
                    onClick = onSubCategoryClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun RetailCategoryQuickStartRow(
    onSubCategoryClick: () -> Unit,
    onProductListClick: () -> Unit,
    onStoreClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getRetailQuickStartItems(),
            key = { item ->
                item.title
            }
        ) { item ->
            RetailCategoryQuickStartCard(
                item = item,
                onClick = {
                    when (item.target) {
                        RetailCategoryQuickStartTarget.SubCategories -> onSubCategoryClick()
                        RetailCategoryQuickStartTarget.Products -> onProductListClick()
                        RetailCategoryQuickStartTarget.Stores -> onStoreClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun RetailCategoryQuickStartCard(
    item: RetailCategoryQuickStartItem,
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
private fun RetailSubCategoryCard(
    item: RetailSubCategoryItem,
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
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

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
private fun RetailCategoryShowcaseRow(
    onProductListClick: () -> Unit,
    onStoreClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getRetailCategoryShowcases(),
            key = { item ->
                item.title
            }
        ) { item ->
            RetailCategoryShowcaseCard(
                item = item,
                onClick = {
                    when (item.target) {
                        RetailCategoryShowcaseTarget.Products -> onProductListClick()
                        RetailCategoryShowcaseTarget.Stores -> onStoreClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun RetailCategoryShowcaseCard(
    item: RetailCategoryShowcaseItem,
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
private fun RetailPopularSearchChipRow(
    onProductListClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        items(
            items = getRetailPopularSearches(),
            key = { item ->
                item
            }
        ) { item ->
            BbChip(
                text = item,
                selected = false,
                onClick = onProductListClick
            )
        }
    }
}

@Immutable
private data class RetailCategoryQuickStartItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: RetailCategoryQuickStartTarget
)

private enum class RetailCategoryQuickStartTarget {
    SubCategories,
    Products,
    Stores
}

@Immutable
private data class RetailSubCategoryItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color
)

@Immutable
private data class RetailCategoryShowcaseItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: RetailCategoryShowcaseTarget
)

private enum class RetailCategoryShowcaseTarget {
    Products,
    Stores
}

private fun getRetailQuickStartItems(): List<RetailCategoryQuickStartItem> {
    return listOf(
        RetailCategoryQuickStartItem(
            title = "Alt Kategoriler",
            description = "Kategori ağacında ilerle",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = RetailCategoryQuickStartTarget.SubCategories
        ),
        RetailCategoryQuickStartItem(
            title = "Ürün Vitrini",
            description = "Seçili ürünlere bak",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = RetailCategoryQuickStartTarget.Products
        ),
        RetailCategoryQuickStartItem(
            title = "Mağazalar",
            description = "Bu kategorideki mağazalar",
            icon = Icons.Outlined.Storefront,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = RetailCategoryQuickStartTarget.Stores
        )
    )
}

private fun getRetailSubCategories(): List<RetailSubCategoryItem> {
    return listOf(
        RetailSubCategoryItem(
            title = "Transistörler, Diyotlar ve Tüpler",
            description = "Elektronik devre parçaları",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900
        ),
        RetailSubCategoryItem(
            title = "Piller ve Aksesuarlar",
            description = "Pil, batarya ve güç aksesuarları",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700
        ),
        RetailSubCategoryItem(
            title = "Elektromekanik Bileşenler",
            description = "Bağlantı, anahtar ve mekanik parçalar",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700
        ),
        RetailSubCategoryItem(
            title = "Entegre Devreler",
            description = "Çip, modül ve devre çözümleri",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Purple.Purple50,
            iconColor = BbColors.Purple.Purple700
        )
    )
}

private fun getRetailCategoryShowcases(): List<RetailCategoryShowcaseItem> {
    return listOf(
        RetailCategoryShowcaseItem(
            title = "Yeni Ürünler",
            description = "Bu kategoride yeni eklenen ürünleri keşfet.",
            icon = Icons.Outlined.NewReleases,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = RetailCategoryShowcaseTarget.Products
        ),
        RetailCategoryShowcaseItem(
            title = "Kategori Mağazaları",
            description = "Bu kategoride satış yapan mağazalara göz at.",
            icon = Icons.Outlined.Storefront,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = RetailCategoryShowcaseTarget.Stores
        ),
        RetailCategoryShowcaseItem(
            title = "Fırsatlar",
            description = "Kategori içindeki kampanya ve vitrinleri incele.",
            icon = Icons.Outlined.LocalOffer,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = RetailCategoryShowcaseTarget.Products
        )
    )
}

private fun getRetailPopularSearches(): List<String> {
    return listOf(
        "Arduino",
        "Sensör",
        "Batarya",
        "Adaptör",
        "Kablo",
        "Devre kartı",
        "Modül",
        "Güç kaynağı"
    )
}

@Preview(showBackground = true)
@Composable
private fun RetailCategoryHomeScreenPreview() {
    BbTheme {
        RetailCategoryHomeScreen()
    }
}
