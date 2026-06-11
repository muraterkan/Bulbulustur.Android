package com.bulbulustur.android.features.wholesale

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.GppGood
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Storefront
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
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigation
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigationItem
import com.bulbulustur.android.features.wholesale.components.WholesaleSearchHeader
import com.bulbulustur.android.features.wholesale.components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun WholesaleHomeScreen(
    onSearchClick: (String) -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onProductDetailClick: () -> Unit = {},
    onCompanyListClick: () -> Unit = {},
    onRfqListClick: () -> Unit = {},
    onRfqCreateClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {},
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
        containerColor = BbColors.SurfaceMuted,
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
                leadingAction = WholesaleSearchHeaderLeadingAction.Menu
            )
        },
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Home,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        WholesaleBottomNavigationItem.Home -> Unit
                        WholesaleBottomNavigationItem.Menu -> onCategoryClick()
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
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottomLoose
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                WholesaleHeroCard(
                    onCategoryClick = onCategoryClick,
                    onRfqCreateClick = onRfqCreateClick,
                    onCompanyListClick = onCompanyListClick
                )
            }

            item {
                WholesaleQuickAccessRow(
                    onCategoryClick = onCategoryClick,
                    onProductListClick = onProductListClick,
                    onCompanyListClick = onCompanyListClick,
                    onRfqCreateClick = onRfqCreateClick
                )
            }

            item {
                WholesaleTrustStrip()
            }

            item {
                BbSectionHeader(
                    title = "Toptan kategori keşfi",
                    subtitle = "Ana sektörlerden başlayarak ticaret akışına gir."
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                    contentPadding = PaddingValues(end = BbSpacing.PageHorizontal)
                ) {
                    items(
                        items = getWholesaleCategoryCards(),
                        key = { item ->
                            item.title
                        }
                    ) { item ->
                        WholesaleCategoryCard(
                            item = item,
                            onClick = onCategoryClick
                        )
                    }
                }
            }

            item {
                BbSectionHeader(
                    title = "Toptan ticaret vitrinleri",
                    subtitle = "Ürün, firma ve teklif kanallarına hızlı geçiş."
                )
            }

            item {
                WholesaleActionGrid(
                    onProductListClick = onProductListClick,
                    onCompanyListClick = onCompanyListClick,
                    onRfqListClick = onRfqListClick,
                    onRfqCreateClick = onRfqCreateClick,
                    onLastPriceRequestClick = onLastPriceRequestClick,
                    onSampleRequestClick = onSampleRequestClick,
                    onCustomizationRequestClick = onCustomizationRequestClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Öne çıkan toptan ürünler",
                    subtitle = "Demo ürün akışı. API sonrası gerçek ürünlerle beslenecek."
                )
            }

            items(
                items = getWholesaleProducts(),
                key = { item ->
                    item.id
                }
            ) { item ->
                WholesaleProductRow(
                    item = item,
                    onClick = onProductDetailClick
                )
            }
        }
    }
}

@Composable
private fun WholesaleHeroCard(
    onCategoryClick: () -> Unit,
    onRfqCreateClick: () -> Unit,
    onCompanyListClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Default,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BbColors.Navy.Navy900, shape = BbRadius.XlShape)
                .padding(BbSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Factory,
                    contentDescription = null,
                    tint = BbColors.Primary,
                    modifier = Modifier.size(BbIcon.SizeLg)
                )

                Text(
                    text = "Toptan ticaret kokpiti",
                    style = BbTypography.titleSmall,
                    color = BbColors.Primary
                )
            }

            Text(
                text = "Sektörleri, tedarikçileri ve teklif akışını tek yerden yönet.",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.White
            )

            Text(
                text = "Bulbulustur Toptan; ürün listesinden önce doğru sektöre, doğru firmaya ve doğru talebe ulaşma alanıdır.",
                style = BbTypography.bodyMedium,
                color = BbColors.Gray.Gray200
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                BbButton(
                    text = "Kategoriler",
                    onClick = onCategoryClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "Teklif İste",
                    onClick = onRfqCreateClick,
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
private fun WholesaleQuickAccessRow(
    onCategoryClick: () -> Unit,
    onProductListClick: () -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqCreateClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        contentPadding = PaddingValues(end = BbSpacing.PageHorizontal)
    ) {
        item {
            WholesaleQuickAccessCard(
                title = "Kategoriler",
                subtitle = "Ana sektör kapısından başla",
                iconType = WholesaleQuickIcon.Category,
                onClick = onCategoryClick
            )
        }

        item {
            WholesaleQuickAccessCard(
                title = "Ürünler",
                subtitle = "Toptan ürün akışına gir",
                iconType = WholesaleQuickIcon.Product,
                onClick = onProductListClick
            )
        }

        item {
            WholesaleQuickAccessCard(
                title = "Tedarikçiler",
                subtitle = "Firmaları keşfet",
                iconType = WholesaleQuickIcon.Company,
                onClick = onCompanyListClick
            )
        }

        item {
            WholesaleQuickAccessCard(
                title = "Teklif İste",
                subtitle = "İhtiyacını firmalara ilet",
                iconType = WholesaleQuickIcon.Rfq,
                onClick = onRfqCreateClick
            )
        }
    }
}

@Composable
private fun WholesaleQuickAccessCard(
    title: String,
    subtitle: String,
    iconType: WholesaleQuickIcon,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .size(
                width = BbSpacing.Space20 + BbSpacing.Space16,
                height = BbSpacing.Space20 + BbSpacing.Space8
            ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = when (iconType) {
                            WholesaleQuickIcon.Category -> BbColors.Yellow.Yellow100
                            WholesaleQuickIcon.Product -> BbColors.Blue.Blue50
                            WholesaleQuickIcon.Company -> BbColors.Green.Green50
                            WholesaleQuickIcon.Rfq -> BbColors.Orange.Orange100
                        },
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (iconType) {
                        WholesaleQuickIcon.Category -> Icons.Outlined.Category
                        WholesaleQuickIcon.Product -> Icons.Outlined.Inventory2
                        WholesaleQuickIcon.Company -> Icons.Outlined.Apartment
                        WholesaleQuickIcon.Rfq -> Icons.Outlined.RequestQuote
                    },
                    contentDescription = null,
                    tint = when (iconType) {
                        WholesaleQuickIcon.Category -> BbColors.TextStrong
                        WholesaleQuickIcon.Product -> BbColors.Blue.Blue700
                        WholesaleQuickIcon.Company -> BbColors.Green.Green700
                        WholesaleQuickIcon.Rfq -> BbColors.Orange.Orange700
                    },
                    modifier = Modifier.size(BbIcon.SizeLg)
                )
            }

            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong
            )

            Text(
                text = subtitle,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun WholesaleTrustStrip() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WholesaleTrustItem(
                title = "Doğrulanmış",
                iconType = WholesaleTrustIcon.Verified
            )

            WholesaleTrustItem(
                title = "41 Dil",
                iconType = WholesaleTrustIcon.Language
            )

            WholesaleTrustItem(
                title = "Güvenli",
                iconType = WholesaleTrustIcon.Safe
            )
        }
    }
}

@Composable
private fun WholesaleTrustItem(
    title: String,
    iconType: WholesaleTrustIcon
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Icon(
            imageVector = when (iconType) {
                WholesaleTrustIcon.Verified -> Icons.Outlined.GppGood
                WholesaleTrustIcon.Language -> Icons.Outlined.Language
                WholesaleTrustIcon.Safe -> Icons.Outlined.Shield
            },
            contentDescription = null,
            tint = BbColors.Primary,
            modifier = Modifier.size(BbIcon.SizeLg)
        )

        Text(
            text = title,
            style = BbTypography.labelMedium,
            color = BbColors.TextStrong
        )
    }
}

@Composable
private fun WholesaleCategoryCard(
    item: WholesaleCategoryCardUiModel,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .size(
                width = BbSpacing.Space24 + BbSpacing.Space16,
                height = BbSpacing.Space16 + BbSpacing.Space8
            ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = item.iconBackground,
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = item.iconColor,
                    modifier = Modifier.size(BbIcon.SizeLg)
                )
            }

            Text(
                text = item.title,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong
            )

            Text(
                text = item.subtitle,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun WholesaleActionGrid(
    onProductListClick: () -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqListClick: () -> Unit,
    onRfqCreateClick: () -> Unit,
    onLastPriceRequestClick: () -> Unit,
    onSampleRequestClick: () -> Unit,
    onCustomizationRequestClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            WholesaleActionCard(
                modifier = Modifier.weight(1f),
                title = "Ürün Akışı",
                subtitle = "Toptan ürünleri listele",
                iconType = WholesaleQuickIcon.Product,
                onClick = onProductListClick
            )

            WholesaleActionCard(
                modifier = Modifier.weight(1f),
                title = "Firmalar",
                subtitle = "Tedarikçileri incele",
                iconType = WholesaleQuickIcon.Company,
                onClick = onCompanyListClick
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            WholesaleActionCard(
                modifier = Modifier.weight(1f),
                title = "Teklifler",
                subtitle = "RFQ kayıtlarını gör",
                iconType = WholesaleQuickIcon.Rfq,
                onClick = onRfqListClick
            )

            WholesaleActionCard(
                modifier = Modifier.weight(1f),
                title = "RFQ Oluştur",
                subtitle = "Yeni teklif iste",
                iconType = WholesaleQuickIcon.Rfq,
                onClick = onRfqCreateClick
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            WholesaleActionCard(
                modifier = Modifier.weight(1f),
                title = "Son Fiyat",
                subtitle = "Fiyat pazarlığı başlat",
                iconType = WholesaleQuickIcon.Rfq,
                onClick = onLastPriceRequestClick
            )

            WholesaleActionCard(
                modifier = Modifier.weight(1f),
                title = "Numune",
                subtitle = "Numune talebi gönder",
                iconType = WholesaleQuickIcon.Product,
                onClick = onSampleRequestClick
            )
        }

        WholesaleActionCard(
            modifier = Modifier.fillMaxWidth(),
            title = "Özelleştirme Talebi",
            subtitle = "Logo, ambalaj, renk ve üretim detayı için talep oluştur",
            iconType = WholesaleQuickIcon.Rfq,
            onClick = onCustomizationRequestClick
        )
    }
}

@Composable
private fun WholesaleActionCard(
    modifier: Modifier,
    title: String,
    subtitle: String,
    iconType: WholesaleQuickIcon,
    onClick: () -> Unit
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = BbSpacing.Space16),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxMd)
                    .background(
                        color = when (iconType) {
                            WholesaleQuickIcon.Category -> BbColors.Yellow.Yellow100
                            WholesaleQuickIcon.Product -> BbColors.Blue.Blue50
                            WholesaleQuickIcon.Company -> BbColors.Green.Green50
                            WholesaleQuickIcon.Rfq -> BbColors.Orange.Orange100
                        },
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (iconType) {
                        WholesaleQuickIcon.Category -> Icons.Outlined.Category
                        WholesaleQuickIcon.Product -> Icons.Outlined.Inventory2
                        WholesaleQuickIcon.Company -> Icons.Outlined.Storefront
                        WholesaleQuickIcon.Rfq -> Icons.Outlined.LocalOffer
                    },
                    contentDescription = null,
                    tint = when (iconType) {
                        WholesaleQuickIcon.Category -> BbColors.TextStrong
                        WholesaleQuickIcon.Product -> BbColors.Blue.Blue700
                        WholesaleQuickIcon.Company -> BbColors.Green.Green700
                        WholesaleQuickIcon.Rfq -> BbColors.Orange.Orange700
                    },
                    modifier = Modifier.size(BbIcon.SizeMd)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = BbTypography.titleSmall,
                    color = BbColors.TextStrong
                )

                Text(
                    text = subtitle,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductRow(
    item: WholesaleProductUiModel,
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.Box2Xl)
                    .background(
                        color = BbColors.SurfaceMuted,
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Inventory2,
                    contentDescription = null,
                    tint = BbColors.Blue.Blue700,
                    modifier = Modifier.size(BbIcon.Size2Xl)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = item.title,
                    style = BbTypography.titleSmall,
                    color = BbColors.TextStrong
                )

                Text(
                    text = item.company,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )

                Text(
                    text = item.priceText,
                    style = BbTypography.titleSmall,
                    color = BbColors.Success
                )
            }

            BbButton(
                text = "Detay",
                onClick = onClick,
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )
        }
    }
}

private enum class WholesaleQuickIcon {
    Category,
    Product,
    Company,
    Rfq
}

private enum class WholesaleTrustIcon {
    Verified,
    Language,
    Safe
}

private data class WholesaleCategoryCardUiModel(
    val title: String,
    val subtitle: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val iconBackground: androidx.compose.ui.graphics.Color,
    val iconColor: androidx.compose.ui.graphics.Color
)

private data class WholesaleProductUiModel(
    val id: Int,
    val title: String,
    val company: String,
    val priceText: String
)

private fun getWholesaleCategoryCards(): List<WholesaleCategoryCardUiModel> {
    return listOf(
        WholesaleCategoryCardUiModel(
            title = "Elektronik Parçalar",
            subtitle = "Bileşen ve ekipman",
            icon = Icons.Outlined.Inventory2,
            iconBackground = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700
        ),
        WholesaleCategoryCardUiModel(
            title = "Ambalaj",
            subtitle = "Koli, kutu, poşet",
            icon = Icons.Outlined.Category,
            iconBackground = BbColors.Yellow.Yellow100,
            iconColor = BbColors.Yellow.Yellow800
        ),
        WholesaleCategoryCardUiModel(
            title = "Tekstil",
            subtitle = "Kumaş ve üretim",
            icon = Icons.Outlined.Storefront,
            iconBackground = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700
        ),
        WholesaleCategoryCardUiModel(
            title = "Endüstriyel",
            subtitle = "Makine ve sarf",
            icon = Icons.Outlined.Factory,
            iconBackground = BbColors.Orange.Orange100,
            iconColor = BbColors.Orange.Orange700
        )
    )
}

private fun getWholesaleProducts(): List<WholesaleProductUiModel> {
    return listOf(
        WholesaleProductUiModel(
            id = 1,
            title = "Endüstriyel ambalaj koli seti",
            company = "Anadolu Ambalaj Sanayi",
            priceText = "₺84,90 / adet"
        ),
        WholesaleProductUiModel(
            id = 2,
            title = "Toptan sneaker üretim paketi",
            company = "Ortobella Üretim",
            priceText = "₺680,00 / çift"
        ),
        WholesaleProductUiModel(
            id = 3,
            title = "Elektronik komponent tedarik paketi",
            company = "Marmara Elektronik",
            priceText = "₺12,40 / adet"
        )
    )
}