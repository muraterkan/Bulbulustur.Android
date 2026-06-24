package com.bulbulustur.android.Application.Areas.b2b.Views.Category

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
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
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
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
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
                    title = "Toptan Kategori akışı",
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
                    title = "Popüler ticaret Aramaları",
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
                    modifier = Modifier.height(BBSpacing.Space4)
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
        shape = BBRadius.XlShape,
        color = BBColors.Navy.Navy900
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BBColors.Navy.Navy900)
                .padding(BBSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Factory,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Toptan Kategori",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Elektronik Parçalar",
                style = MaterialTheme.typography.headlineSmall,
                color = BBColors.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Alt sektörleri incele, doĞrulanmış tedarikçilere ulaş ve seçili kategori için teklif akışını başlat.",
                style = MaterialTheme.typography.bodyMedium,
                color = BBColors.Gray.Gray200
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
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
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
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
private fun WholesalePopularSearchChipRow(
    onProductListClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
            backgroundColor = BBColors.Yellow.Yellow50,
            iconColor = BBColors.Navy.Navy900,
            target = WholesaleCategoryQuickActionTarget.Companies
        ),
        WholesaleCategoryQuickActionItem(
            title = "Teklif İste",
            description = "İhtiyacını firmalara ilet",
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = BBColors.Blue.Blue50,
            iconColor = BBColors.Blue.Blue700,
            target = WholesaleCategoryQuickActionTarget.Rfq
        ),
        WholesaleCategoryQuickActionItem(
            title = "Son Fiyat",
            description = "Güncel toptan fiyat al",
            icon = Icons.Outlined.Search,
            backgroundColor = BBColors.Green.Green50,
            iconColor = BBColors.Green.Green700,
            target = WholesaleCategoryQuickActionTarget.LastPrice
        ),
        WholesaleCategoryQuickActionItem(
            title = "Numune",
            description = "Sipariş öncesi örnek",
            icon = Icons.Outlined.LocalShipping,
            backgroundColor = BBColors.Purple.Purple50,
            iconColor = BBColors.Purple.Purple700,
            target = WholesaleCategoryQuickActionTarget.Sample
        ),
        WholesaleCategoryQuickActionItem(
            title = "Özelleştir",
            description = "Özel üretim talebi oluştur",
            icon = Icons.Outlined.Tune,
            backgroundColor = BBColors.Orange.Orange50,
            iconColor = BBColors.Orange.Orange700,
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
            backgroundColor = BBColors.Yellow.Yellow50,
            iconColor = BBColors.Navy.Navy900
        ),
        WholesaleCategoryHomeSubCategoryItem(
            title = "Piller ve Güç Kaynakları",
            description = "Pil, batarya ve güç çözümleri",
            icon = Icons.Outlined.Category,
            backgroundColor = BBColors.Blue.Blue50,
            iconColor = BBColors.Blue.Blue700
        ),
        WholesaleCategoryHomeSubCategoryItem(
            title = "Aktif Bileşenler",
            description = "Endüstriyel elektronik parçalar",
            icon = Icons.Outlined.Category,
            backgroundColor = BBColors.Green.Green50,
            iconColor = BBColors.Green.Green700
        ),
        WholesaleCategoryHomeSubCategoryItem(
            title = "Entegre Devreler",
            description = "Çip, modül ve devre ürünleri",
            icon = Icons.Outlined.Category,
            backgroundColor = BBColors.Purple.Purple50,
            iconColor = BBColors.Purple.Purple700
        )
    )
}

private fun getWholesaleCategoryShowcases(): List<WholesaleCategoryShowcaseItem> {
    return listOf(
        WholesaleCategoryShowcaseItem(
            title = "Toptan Ürünler",
            description = "Bu kategori içindeki ürün gruplarına geç.",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = BBColors.Yellow.Yellow50,
            iconColor = BBColors.Navy.Navy900,
            target = WholesaleCategoryShowcaseTarget.Products
        ),
        WholesaleCategoryShowcaseItem(
            title = "DoĞrulanmış Tedarikçiler",
            description = "Bu kategoride satış yapan firmalara ulaş.",
            icon = Icons.Outlined.Verified,
            backgroundColor = BBColors.Blue.Blue50,
            iconColor = BBColors.Blue.Blue700,
            target = WholesaleCategoryShowcaseTarget.Companies
        ),
        WholesaleCategoryShowcaseItem(
            title = "Teklif Topla",
            description = "Kategori bazlı RFQ talebi oluştur.",
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = BBColors.Green.Green50,
            iconColor = BBColors.Green.Green700,
            target = WholesaleCategoryShowcaseTarget.Rfq
        )
    )
}

private fun getWholesalePopularSearches(): List<String> {
    return listOf(
        "LED sürücü",
        "Güç kaynaĞı",
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

