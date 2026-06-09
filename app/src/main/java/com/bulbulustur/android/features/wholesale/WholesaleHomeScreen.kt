package com.bulbulustur.android.features.wholesale

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
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Tune
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
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigation
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigationItem
import com.bulbulustur.android.features.wholesale.components.WholesaleSearchHeader
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
fun WholesaleHomeScreen(
    onSearchClick: (String) -> Unit = {},
    onProductDetailClick: () -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onCompanyListClick: () -> Unit = {},
    onRfqListClick: () -> Unit = {},
    onRfqCreateClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {},
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
            WholesaleSearchHeader(
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
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Home,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        WholesaleBottomNavigationItem.Home -> Unit
                        WholesaleBottomNavigationItem.Menu -> onMenuClick()
                        WholesaleBottomNavigationItem.Messages -> onMessageClick()
                        WholesaleBottomNavigationItem.Basket -> onBasketClick()
                        WholesaleBottomNavigationItem.Account -> onAccountClick()
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
                WholesaleHomeHeroCard(
                    onMenuClick = onMenuClick,
                    onRfqCreateClick = onRfqCreateClick,
                    onCompanyListClick = onCompanyListClick
                )
            }

            item {
                WholesaleHomeGatewayRow(
                    onMenuClick = onMenuClick,
                    onProductListClick = onProductListClick,
                    onCompanyListClick = onCompanyListClick,
                    onRfqCreateClick = onRfqCreateClick,
                    onLastPriceRequestClick = onLastPriceRequestClick
                )
            }

            item {
                WholesaleHomeTrustStrip()
            }

            item {
                BbSectionHeader(
                    title = "Toptan kategori keşfi",
                    subtitle = "Ana sektörlerden başlayarak ticaret akışına gir."
                )
            }

            item {
                WholesaleHomeSectorChipRow(
                    onCategoryClick = onCategoryClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Toptan ticaret vitrinleri",
                    subtitle = "Ürün, firma ve teklif kanallarına hızlı geçiş."
                )
            }

            item {
                WholesaleHomeShowcaseRow(
                    onProductListClick = onProductListClick,
                    onCompanyListClick = onCompanyListClick,
                    onRfqListClick = onRfqListClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Öne çıkan toptan ürünler",
                    subtitle = "Toptan ürün detayını incelemek için kartlara dokun."
                )
            }

            item {
                WholesaleHomeFeaturedProductRow(
                    onProductClick = onProductDetailClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Ticaret işlemleri",
                    subtitle = "Teklif, son fiyat, numune ve özel üretim talepleri."
                )
            }

            item {
                WholesaleHomeTradeFlowRow(
                    onRfqCreateClick = onRfqCreateClick,
                    onLastPriceRequestClick = onLastPriceRequestClick,
                    onSampleRequestClick = onSampleRequestClick,
                    onCustomizationRequestClick = onCustomizationRequestClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Öne çıkan tedarikçiler",
                    subtitle = "Doğrulanmış firma vitrinleri."
                )
            }

            item {
                WholesaleHomeCompanyRow(
                    onCompanyListClick = onCompanyListClick
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
private fun WholesaleHomeHeroCard(
    onMenuClick: () -> Unit,
    onRfqCreateClick: () -> Unit,
    onCompanyListClick: () -> Unit
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
                    text = "Toptan ticaret kokpiti",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Sektörleri, tedarikçileri ve teklif akışını tek yerden yönet.",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Bulbulustur Toptan; ürün listesinden önce doğru sektöre, doğru firmaya ve doğru talebe ulaşma alanıdır.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.Gray.Gray200
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "Kategoriler",
                    onClick = onMenuClick,
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
private fun WholesaleHomeGatewayRow(
    onMenuClick: () -> Unit,
    onProductListClick: () -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqCreateClick: () -> Unit,
    onLastPriceRequestClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getWholesaleHomeGateways(),
            key = { item ->
                item.title
            }
        ) { item ->
            WholesaleHomeGatewayCard(
                item = item,
                onClick = {
                    when (item.target) {
                        WholesaleHomeGatewayTarget.Menu -> onMenuClick()
                        WholesaleHomeGatewayTarget.Products -> onProductListClick()
                        WholesaleHomeGatewayTarget.Companies -> onCompanyListClick()
                        WholesaleHomeGatewayTarget.Rfq -> onRfqCreateClick()
                        WholesaleHomeGatewayTarget.LastPrice -> onLastPriceRequestClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun WholesaleHomeGatewayCard(
    item: WholesaleHomeGatewayItem,
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
private fun WholesaleHomeTrustStrip() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            WholesaleHomeTrustItem(
                icon = Icons.Outlined.Verified,
                title = "Doğrulanmış",
                modifier = Modifier.weight(1f)
            )

            WholesaleHomeTrustItem(
                icon = Icons.Outlined.Language,
                title = "41 Dil",
                modifier = Modifier.weight(1f)
            )

            WholesaleHomeTrustItem(
                icon = Icons.Outlined.Security,
                title = "Güvenli",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun WholesaleHomeTrustItem(
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
private fun WholesaleHomeSectorChipRow(
    onCategoryClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        items(
            items = getWholesaleHomeSectorNames(),
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
private fun WholesaleHomeShowcaseRow(
    onProductListClick: () -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqListClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getWholesaleHomeShowcases(),
            key = { item ->
                item.title
            }
        ) { item ->
            WholesaleHomeShowcaseCard(
                item = item,
                onClick = {
                    when (item.target) {
                        WholesaleHomeShowcaseTarget.Products -> onProductListClick()
                        WholesaleHomeShowcaseTarget.Companies -> onCompanyListClick()
                        WholesaleHomeShowcaseTarget.Rfq -> onRfqListClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun WholesaleHomeShowcaseCard(
    item: WholesaleHomeShowcaseItem,
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
private fun WholesaleHomeFeaturedProductRow(
    onProductClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getWholesaleHomeFeaturedProducts(),
            key = { item ->
                item.name
            }
        ) { item ->
            WholesaleHomeFeaturedProductCard(
                item = item,
                onClick = onProductClick
            )
        }
    }
}

@Composable
private fun WholesaleHomeFeaturedProductCard(
    item: WholesaleHomeFeaturedProductItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(220.dp),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(116.dp)
                    .background(
                        color = item.imageBackgroundColor,
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = item.iconColor,
                    modifier = Modifier.width(42.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = item.categoryName,
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.TextMuted,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleHomeProductPill(
                    text = item.priceLabel
                )

                WholesaleHomeProductPill(
                    text = item.minimumOrderLabel
                )
            }

            Text(
                text = item.companyName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun WholesaleHomeProductPill(
    text: String
) {
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary
        )
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun WholesaleHomeTradeFlowRow(
    onRfqCreateClick: () -> Unit,
    onLastPriceRequestClick: () -> Unit,
    onSampleRequestClick: () -> Unit,
    onCustomizationRequestClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getWholesaleHomeTradeFlows(),
            key = { item ->
                item.title
            }
        ) { item ->
            WholesaleHomeTradeFlowCard(
                item = item,
                onClick = {
                    when (item.target) {
                        WholesaleHomeTradeFlowTarget.Rfq -> onRfqCreateClick()
                        WholesaleHomeTradeFlowTarget.LastPrice -> onLastPriceRequestClick()
                        WholesaleHomeTradeFlowTarget.Sample -> onSampleRequestClick()
                        WholesaleHomeTradeFlowTarget.Customization -> onCustomizationRequestClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun WholesaleHomeTradeFlowCard(
    item: WholesaleHomeTradeFlowItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(180.dp),
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
private fun WholesaleHomeCompanyRow(
    onCompanyListClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        items(
            items = getWholesaleHomeCompanies(),
            key = { item ->
                item.name
            }
        ) { item ->
            WholesaleHomeCompanyCard(
                item = item,
                onClick = onCompanyListClick
            )
        }
    }
}

@Composable
private fun WholesaleHomeCompanyCard(
    item: WholesaleHomeCompanyItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(220.dp),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                        imageVector = Icons.Outlined.Business,
                        contentDescription = null,
                        tint = BbColors.TextStrong
                    )
                }

                Column {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = item.city,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Immutable
private data class WholesaleHomeGatewayItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: WholesaleHomeGatewayTarget
)

@Immutable
private data class WholesaleHomeFeaturedProductItem(
    val name: String,
    val categoryName: String,
    val companyName: String,
    val priceLabel: String,
    val minimumOrderLabel: String,
    val icon: ImageVector,
    val imageBackgroundColor: Color,
    val iconColor: Color
)

private enum class WholesaleHomeGatewayTarget {
    Menu,
    Products,
    Companies,
    Rfq,
    LastPrice
}

@Immutable
private data class WholesaleHomeShowcaseItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: WholesaleHomeShowcaseTarget
)

private enum class WholesaleHomeShowcaseTarget {
    Products,
    Companies,
    Rfq
}

@Immutable
private data class WholesaleHomeTradeFlowItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color,
    val target: WholesaleHomeTradeFlowTarget
)

private enum class WholesaleHomeTradeFlowTarget {
    Rfq,
    LastPrice,
    Sample,
    Customization
}

@Immutable
private data class WholesaleHomeCompanyItem(
    val name: String,
    val city: String,
    val description: String
)

private fun getWholesaleHomeGateways(): List<WholesaleHomeGatewayItem> {
    return listOf(
        WholesaleHomeGatewayItem(
            title = "Kategoriler",
            description = "Ana sektör kapısından başla",
            icon = Icons.Outlined.Category,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = WholesaleHomeGatewayTarget.Menu
        ),
        WholesaleHomeGatewayItem(
            title = "Ürünler",
            description = "Toptan ürün akışına gir",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = WholesaleHomeGatewayTarget.Products
        ),
        WholesaleHomeGatewayItem(
            title = "Tedarikçiler",
            description = "Firma vitrinlerini keşfet",
            icon = Icons.Outlined.Business,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = WholesaleHomeGatewayTarget.Companies
        ),
        WholesaleHomeGatewayItem(
            title = "Teklif İste",
            description = "RFQ akışını başlat",
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = BbColors.Purple.Purple50,
            iconColor = BbColors.Purple.Purple700,
            target = WholesaleHomeGatewayTarget.Rfq
        ),
        WholesaleHomeGatewayItem(
            title = "Son Fiyat",
            description = "Tedarikçiden fiyat sor",
            icon = Icons.AutoMirrored.Outlined.Message,
            backgroundColor = BbColors.Orange.Orange50,
            iconColor = BbColors.Orange.Orange700,
            target = WholesaleHomeGatewayTarget.LastPrice
        )
    )
}

private fun getWholesaleHomeSectorNames(): List<String> {
    return listOf(
        "Ambalaj",
        "Makine",
        "Gıda",
        "Tekstil",
        "Medikal",
        "Kimya",
        "Elektrik",
        "Yapı",
        "Otomotiv",
        "Mobilya"
    )
}

private fun getWholesaleHomeShowcases(): List<WholesaleHomeShowcaseItem> {
    return listOf(
        WholesaleHomeShowcaseItem(
            title = "Yeni tedarikçiler",
            description = "Bu hafta eklenen firma vitrinleri.",
            icon = Icons.Outlined.Business,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = WholesaleHomeShowcaseTarget.Companies
        ),
        WholesaleHomeShowcaseItem(
            title = "Toptan ürünler",
            description = "Ana kategorilerden seçilmiş ürün akışı.",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = WholesaleHomeShowcaseTarget.Products
        ),
        WholesaleHomeShowcaseItem(
            title = "Açık talepler",
            description = "Teklif toplanabilecek RFQ akışları.",
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = WholesaleHomeShowcaseTarget.Rfq
        )
    )
}

private fun getWholesaleHomeTradeFlows(): List<WholesaleHomeTradeFlowItem> {
    return listOf(
        WholesaleHomeTradeFlowItem(
            title = "Teklif talebi",
            description = "İhtiyacını yaz, teklif topla",
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900,
            target = WholesaleHomeTradeFlowTarget.Rfq
        ),
        WholesaleHomeTradeFlowItem(
            title = "Son fiyat",
            description = "Güncel toptan fiyat sor",
            icon = Icons.AutoMirrored.Outlined.Message,
            backgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700,
            target = WholesaleHomeTradeFlowTarget.LastPrice
        ),
        WholesaleHomeTradeFlowItem(
            title = "Numune",
            description = "Sipariş öncesi örnek iste",
            icon = Icons.Outlined.LocalShipping,
            backgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700,
            target = WholesaleHomeTradeFlowTarget.Sample
        ),
        WholesaleHomeTradeFlowItem(
            title = "Özel üretim",
            description = "Logo, ölçü veya renk talebi",
            icon = Icons.Outlined.Tune,
            backgroundColor = BbColors.Purple.Purple50,
            iconColor = BbColors.Purple.Purple700,
            target = WholesaleHomeTradeFlowTarget.Customization
        )
    )
}

private fun getWholesaleHomeCompanies(): List<WholesaleHomeCompanyItem> {
    return listOf(
        WholesaleHomeCompanyItem(
            name = "Anadolu Ambalaj",
            city = "İstanbul",
            description = "Karton, koli ve özel baskılı ambalaj çözümleri."
        ),
        WholesaleHomeCompanyItem(
            name = "Delta Makine",
            city = "Konya",
            description = "Endüstriyel üretim hatları ve yedek parça."
        ),
        WholesaleHomeCompanyItem(
            name = "Mira Tekstil",
            city = "Denizli",
            description = "Toptan ev tekstili ve üretici koleksiyonları."
        )
    )
}

private fun getWholesaleHomeFeaturedProducts(): List<WholesaleHomeFeaturedProductItem> {
    return listOf(
        WholesaleHomeFeaturedProductItem(
            name = "BSCI özelleştirilebilir çocuk okul çantası",
            categoryName = "Çanta ve tekstil",
            companyName = "Ortobella Comfort",
            priceLabel = "Teklif ile",
            minimumOrderLabel = "499 adet",
            icon = Icons.Outlined.LocalShipping,
            imageBackgroundColor = BbColors.Yellow.Yellow50,
            iconColor = BbColors.Navy.Navy900
        ),
        WholesaleHomeFeaturedProductItem(
            name = "Baskılı e-ticaret kargo kolisi",
            categoryName = "Ambalaj",
            companyName = "Anadolu Ambalaj",
            priceLabel = "Teklif ile",
            minimumOrderLabel = "1.000 adet",
            icon = Icons.Outlined.LocalShipping,
            imageBackgroundColor = BbColors.Blue.Blue50,
            iconColor = BbColors.Blue.Blue700
        ),
        WholesaleHomeFeaturedProductItem(
            name = "Logo baskılı pamuklu promosyon çanta",
            categoryName = "Promosyon",
            companyName = "Mira Tekstil",
            priceLabel = "Teklif ile",
            minimumOrderLabel = "300 adet",
            icon = Icons.Outlined.WorkspacePremium,
            imageBackgroundColor = BbColors.Green.Green50,
            iconColor = BbColors.Green.Green700
        ),
        WholesaleHomeFeaturedProductItem(
            name = "Endüstriyel yedek parça taşıma kutusu",
            categoryName = "Sanayi ekipmanı",
            companyName = "Delta Makine",
            priceLabel = "Teklif ile",
            minimumOrderLabel = "100 adet",
            icon = Icons.Outlined.Factory,
            imageBackgroundColor = BbColors.Purple.Purple50,
            iconColor = BbColors.Purple.Purple700
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun WholesaleHomeScreenPreview() {
    BbTheme {
        WholesaleHomeScreen()
    }
}