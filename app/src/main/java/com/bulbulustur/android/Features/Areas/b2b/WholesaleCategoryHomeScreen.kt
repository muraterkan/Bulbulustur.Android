package com.bulbulustur.android.Features.areas.b2b

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Tune
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
import com.bulbulustur.android.Features.areas.b2b.components.WholesaleBottomNavigation
import com.bulbulustur.android.Features.areas.b2b.components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Features.areas.b2b.components.WholesaleSearchHeader
import com.bulbulustur.android.Features.areas.b2b.components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbChip
import com.bulbulustur.android.Ui.components.BbSectionHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTheme

@Composable
fun WholesaleCategoryHomeScreen(
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
    onCompanyListClick: () -> Unit = {},
    onRfqClick: () -> Unit = {},
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = "Toptan ürün, kategori veya tedarikçi ara",
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                leadingAction = WholesaleSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Menu,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        WholesaleBottomNavigationItem.Home -> onHomeClick()
                        WholesaleBottomNavigationItem.Menu -> Unit
                        WholesaleBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        WholesaleBottomNavigationItem.Basket -> onBasketClick()
                        WholesaleBottomNavigationItem.Account -> onAccountClick()
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
                start = BbSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGapCompact)
        ) {
            item {
                WholesaleCategoryHeroCard(
                    onProductListClick = onProductListClick,
                    onCompanyListClick = onCompanyListClick,
                    onRfqClick = onRfqClick
                )
            }

            item {
                WholesaleCategoryQuickActionRow(
                    onCompanyListClick = onCompanyListClick,
                    onRfqClick = onRfqClick,
                    onLastPriceRequestClick = onLastPriceRequestClick,
                    onSampleRequestClick = onSampleRequestClick,
                    onCustomizationRequestClick = onCustomizationRequestClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Alt Kategoriler",
                    subtitle = "Bu sektör içindeki alt kırılımları incele."
                )
            }

            items(
                items = getWholesaleSubCategories(),
                key = { item ->
                    item.title
                }
            ) { item ->
                WholesaleSubCategoryCard(
                    item = item,
                    onClick = onSubCategoryClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Toptan kategori akışı",
                    subtitle = "Ürün, tedarikçi ve teklif kanallarına hızlı geç."
                )
            }

            item {
                WholesaleCategoryShowcaseRow(
                    onProductListClick = onProductListClick,
                    onCompanyListClick = onCompanyListClick,
                    onRfqClick = onRfqClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Popüler ticaret aramaları",
                    subtitle = "Bu sektörde sık kullanılan arama başlıkları"
                )
            }

            item {
                WholesalePopularSearchChipRow(
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
private fun WholesaleCategoryHeroCard(
    onProductListClick: () -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BbRadius.XlShape,
        color = BbColors.Navy.Navy900
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BbColors.Navy.Navy900)
                .padding(BbSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Factory,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Toptan kategori",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Elektronik Parçalar",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Alt sektörleri incele, doğrulanmış tedarikçilere ulaş ve seçili kategori için teklif akışını başlat.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.Gray.Gray200
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "Ürünleri Gör",
                    onClick = onProductListClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "Teklif İste",
                    onClick = onRfqClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium
                )
            }

            BbButton(
                text = "Tedarikçileri Gör",
                onClick = onCompanyListClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Outline,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun WholesaleCategoryQuickActionRow(
    onCompanyListClick: () -> Unit,
    onRfqClick: () -> Unit,
    onLastPriceRequestClick: () -> Unit,
    onSampleRequestClick: () -> Unit,
    onCustomizationRequestClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getWholesaleQuickActions(),
            key = { item ->
                item.title
            }
        ) { item ->
            WholesaleCategoryQuickActionCard(
                item = item,
                onClick = {
                    when (item.target) {
                        WholesaleCategoryQuickActionTarget.Companies -> onCompanyListClick()
                        WholesaleCategoryQuickActionTarget.Rfq -> onRfqClick()
                        WholesaleCategoryQuickActionTarget.LastPrice -> onLastPriceRequestClick()
                        WholesaleCategoryQuickActionTarget.Sample -> onSampleRequestClick()
                        WholesaleCategoryQuickActionTarget.Customization -> onCustomizationRequestClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun WholesaleCategoryQuickActionCard(
    item: WholesaleCategoryQuickActionItem,
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
                    .width(BbSpacing.Space11)
                    .height(BbSpacing.Space11)
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
private fun WholesaleSubCategoryCard(
    item: WholesaleCategoryHomeSubCategoryItem,
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
private fun WholesaleCategoryShowcaseRow(
    onProductListClick: () -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getWholesaleCategoryShowcases(),
            key = { item ->
                item.title
            }
        ) { item ->
            WholesaleCategoryShowcaseCard(
                item = item,
                onClick = {
                    when (item.target) {
                        WholesaleCategoryShowcaseTarget.Products -> onProductListClick()
                        WholesaleCategoryShowcaseTarget.Companies -> onCompanyListClick()
                        WholesaleCategoryShowcaseTarget.Rfq -> onRfqClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun WholesaleCategoryShowcaseCard(
    item: WholesaleCategoryShowcaseItem,
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
private fun WholesalePopularSearchChipRow(
    onProductListClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        items(
            items = getWholesalePopularSearches(),
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
private data class WholesaleCategoryQuickActionItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: WholesaleCategoryQuickActionTarget
)

private enum class WholesaleCategoryQuickActionTarget {
    Companies,
    Rfq,
    LastPrice,
    Sample,
    Customization
}

@Immutable
private data class WholesaleCategoryHomeSubCategoryItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color
)

@Immutable
private data class WholesaleCategoryShowcaseItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: WholesaleCategoryShowcaseTarget
)

private enum class WholesaleCategoryShowcaseTarget {
    Products,
    Companies,
    Rfq
}

private fun getWholesaleQuickActions(): List<WholesaleCategoryQuickActionItem> {
    return listOf(
        WholesaleCategoryQuickActionItem(
            title = "Tedarikçiler",
            description = "Bu sektördeki firmalar",
            icon = Icons.Outlined.Business,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = WholesaleCategoryQuickActionTarget.Companies
        ),
        WholesaleCategoryQuickActionItem(
            title = "Teklif İste",
            description = "İhtiyacını firmalara ilet",
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = WholesaleCategoryQuickActionTarget.Rfq
        ),
        WholesaleCategoryQuickActionItem(
            title = "Son Fiyat",
            description = "Güncel toptan fiyat al",
            icon = Icons.Outlined.Search,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = WholesaleCategoryQuickActionTarget.LastPrice
        ),
        WholesaleCategoryQuickActionItem(
            title = "Numune",
            description = "Sipariş öncesi örnek iste",
            icon = Icons.Outlined.LocalShipping,
            backgroundColor = BbColors.Purple.Purple50,
            iconColor = BbColors.Purple.Purple700,
            target = WholesaleCategoryQuickActionTarget.Sample
        ),
        WholesaleCategoryQuickActionItem(
            title = "Özelleştir",
            description = "Özel üretim talebi oluştur",
            icon = Icons.Outlined.Tune,
            backgroundColor = BbColors.Orange.Orange50,
            iconColor = BbColors.Orange.Orange700,
            target = WholesaleCategoryQuickActionTarget.Customization
        )
    )
}

private fun getWholesaleSubCategories(): List<WholesaleCategoryHomeSubCategoryItem> {
    return listOf(
        WholesaleCategoryHomeSubCategoryItem(
            title = "Transistörler, Diyotlar ve Tüpler",
            description = "Toptan elektronik bileşen tedariki",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900
        ),
        WholesaleCategoryHomeSubCategoryItem(
            title = "Piller ve Güç Kaynakları",
            description = "Pil, batarya ve güç çözümleri",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700
        ),
        WholesaleCategoryHomeSubCategoryItem(
            title = "Aktif Bileşenler",
            description = "Endüstriyel elektronik parçalar",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700
        ),
        WholesaleCategoryHomeSubCategoryItem(
            title = "Entegre Devreler",
            description = "Çip, modül ve devre ürünleri",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Purple.Purple50,
            iconColor = BbColors.Purple.Purple700
        )
    )
}

private fun getWholesaleCategoryShowcases(): List<WholesaleCategoryShowcaseItem> {
    return listOf(
        WholesaleCategoryShowcaseItem(
            title = "Toptan Ürünler",
            description = "Bu kategori içindeki ürün gruplarına geç.",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = WholesaleCategoryShowcaseTarget.Products
        ),
        WholesaleCategoryShowcaseItem(
            title = "Doğrulanmış Tedarikçiler",
            description = "Bu kategoride satış yapan firmalara ulaş.",
            icon = Icons.Outlined.Verified,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = WholesaleCategoryShowcaseTarget.Companies
        ),
        WholesaleCategoryShowcaseItem(
            title = "Teklif Topla",
            description = "Kategori bazlı RFQ talebi oluştur.",
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = WholesaleCategoryShowcaseTarget.Rfq
        )
    )
}

private fun getWholesalePopularSearches(): List<String> {
    return listOf(
        "LED sürücü",
        "Güç kaynağı",
        "Sensör",
        "Konnektör",
        "PCB",
        "Röle",
        "Transistör",
        "Endüstriyel modül"
    )
}

@Preview(showBackground = true)
@Composable
private fun WholesaleCategoryHomeScreenPreview() {
    BbTheme {
        WholesaleCategoryHomeScreen()
    }
}