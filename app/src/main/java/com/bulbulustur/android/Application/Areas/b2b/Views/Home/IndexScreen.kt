package com.bulbulustur.android.Application.Areas.b2b.Views.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun WholesaleHomeScreen(
    onSearchClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},

    onCategoryClick: () -> Unit = {},
    onCategoriesClick: () -> Unit = onCategoryClick,
    onCategoryHomeClick: () -> Unit = onCategoryClick,

    onProductListClick: () -> Unit = {},
    onProductDetailClick: () -> Unit = {},
    onProductClick: () -> Unit = onProductDetailClick,
    onProductsClick: () -> Unit = onProductListClick,

    onSupplierClick: () -> Unit = {},
    onSupplierListClick: () -> Unit = onSupplierClick,
    onCompanyClick: () -> Unit = onSupplierClick,
    onCompaniesClick: () -> Unit = onSupplierClick,
    onCompanyListClick: () -> Unit = onSupplierClick,

    onQuotationRequestsClick: () -> Unit = {},
    onRfqListClick: () -> Unit = onQuotationRequestsClick,
    onRfqClick: () -> Unit = onRfqListClick,
    onRfqCreateClick: () -> Unit = {},
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {},

    onModeSwitchClick: () -> Unit = {},
    onModeClick: () -> Unit = onModeSwitchClick,

    onBasketClick: () -> Unit = onRfqListClick,
    onFavoriteClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = onFavoriteClick,
    onMessageClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    val categories = wholesaleCategoryItems()
    val showcaseProducts = wholesaleShowcaseProducts()
    val actionItems = wholesaleActionItems(
        onQuotationRequestsClick = onRfqClick,
        onRfqCreateClick = onRfqCreateClick,
        onLastPriceRequestClick = onLastPriceRequestClick,
        onSampleRequestClick = onSampleRequestClick,
        onCustomizationRequestClick = onCustomizationRequestClick
    )

    Scaffold(
        containerColor = BBColors.SurfaceSoft,
        topBar = {
            WholesaleSearchHeader(
                searchText = "",
                onSearchTextChange = {},
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoritesClick,
                placeholder = "Ürün, Firma Veya RFQ Ara",
                onSearchClick = onSearchClick,
                onClearClick = {},
                onMessageClick = onMessageClick
            )
        },
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Home,
                onItemClick = { item ->
                    when (item) {
                        WholesaleBottomNavigationItem.Home -> Unit
                        WholesaleBottomNavigationItem.Menu -> onCategoryHomeClick()
                        WholesaleBottomNavigationItem.ModeSwitch -> onModeClick()
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
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
        ) {
            item {
                WholesaleHeroCard(
                    onRfqCreateClick = onRfqCreateClick,
                    onProductListClick = onProductListClick
                )
            }

            item {
                WholesaleTrustRail()
            }

            item {
                WholesaleSectionTitle(
                    title = "Toptan Kategori Keşfi",
                    description = "Ana sektörlerden başlayarak ticaret akışına gir."
                )
            }

            item {
                WholesaleCategoryGrid(
                    items = categories,
                    onCategoryClick = onCategoryHomeClick
                )
            }

            item {
                WholesaleSectionTitleWithAction(
                    title = "Toptan Vitrin Ürünleri",
                    description = "Tedarikçi ürünlerini hızlıca incele.",
                    actionText = "Tümünü Gör",
                    onActionClick = onProductListClick
                )
            }

            item {
                WholesaleProductShowcaseRow(
                    products = showcaseProducts,
                    onProductClick = onProductClick
                )
            }

            item {
                WholesaleSectionTitle(
                    title = "Tedarik Aksiyonları",
                    description = "Teklif, son fiyat, numune ve özel üretim kanalları."
                )
            }

            item {
                WholesaleActionRow(
                    items = actionItems
                )
            }
        }
    }
}

@Composable
private fun WholesaleHeroCard(
    onRfqCreateClick: () -> Unit,
    onProductListClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                WholesaleIconBox(
                    icon = Icons.Outlined.Business,
                    tint = BBColors.Navy.Navy700,
                    backgroundColor = BBColors.Navy.Navy50
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "Toptan Ticaret Merkezi",
                        style = MaterialTheme.typography.titleMedium,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "Ürün, firma ve teklif akışını tek yerden yönet.",
                        style = MaterialTheme.typography.bodySmall,
                        color = BBColors.TextMuted
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BbButton(
                    text = "RFQ Oluştur",
                    onClick = onRfqCreateClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "Ürünleri Gör",
                    onClick = onProductListClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun WholesaleTrustRail() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WholesaleTrustItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Verified,
                title = "Doğrulanmış",
                tint = BBColors.Yellow.Yellow600
            )

            WholesaleTrustItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Language,
                title = "41 Dil",
                tint = BBColors.Yellow.Yellow600
            )

            WholesaleTrustItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Shield,
                title = "Güvenli",
                tint = BBColors.Yellow.Yellow600
            )
        }
    }
}

@Composable
private fun WholesaleTrustItem(
    modifier: Modifier,
    icon: ImageVector,
    title: String,
    tint: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(BBIcon.SizeXl),
            tint = tint
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = BBColors.TextStrong,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun WholesaleSectionTitle(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = BBColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = BBColors.TextMuted
        )
    }
}

@Composable
private fun WholesaleSectionTitleWithAction(
    title: String,
    description: String,
    actionText: String,
    onActionClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = BBColors.TextMuted
            )
        }

        TextButton(
            onClick = onActionClick
        ) {
            Text(
                text = actionText,
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.Navy.Navy700
            )

            Spacer(modifier = Modifier.width(BBSpacing.Space1))

            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = actionText,
                modifier = Modifier.size(BBIcon.SizeSm),
                tint = BBColors.Navy.Navy700
            )
        }
    }
}

@Composable
private fun WholesaleCategoryGrid(
    items: List<WholesaleCategoryHomeItem>,
    onCategoryClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                rowItems.forEach { item ->
                    WholesaleCategoryCard(
                        item = item,
                        onClick = onCategoryClick,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun WholesaleCategoryCard(
    item: WholesaleCategoryHomeItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(BBSpacing.Space24 + BBSpacing.Space16),
        onClick = onClick,
        shape = BBRadius.XlShape,
        color = BBColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BBSpacing.CardPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            WholesaleIconBox(
                icon = item.icon,
                tint = item.tint,
                backgroundColor = item.backgroundColor
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductShowcaseRow(
    products: List<WholesaleShowcaseProduct>,
    onProductClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        contentPadding = PaddingValues(end = BBSpacing.PageHorizontal)
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            WholesaleProductShowcaseCard(
                product = product,
                onClick = onProductClick
            )
        }
    }
}

@Composable
private fun WholesaleProductShowcaseCard(
    product: WholesaleShowcaseProduct,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(BBSpacing.Space24 + BBSpacing.Space24),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None,
        onClick = onClick
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.Space24 + BBSpacing.Space20)
                    .background(BBColors.Gray.Gray50),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(BBRadius.XlShape),
                    contentScale = ContentScale.Crop
                )

                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(BBSpacing.Space3),
                    shape = BBRadius.PillShape,
                    color = BBColors.Navy.Navy900.copy(alpha = 0.88f)
                ) {
                    Text(
                        text = product.badge,
                        modifier = Modifier.padding(
                            horizontal = BBSpacing.Space3,
                            vertical = BBSpacing.Space1
                        ),
                        style = MaterialTheme.typography.labelSmall,
                        color = BBColors.White,
                        maxLines = 1
                    )
                }
            }

            Column(
                modifier = Modifier.padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleSmall,
                    color = BBColors.Navy.Navy800,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WholesaleMiniPill(
                        text = product.moq
                    )

                    WholesaleMiniPill(
                        text = product.supplier
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleActionRow(
    items: List<WholesaleHomeActionItem>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        contentPadding = PaddingValues(end = BBSpacing.PageHorizontal)
    ) {
        items(
            items = items,
            key = { it.title }
        ) { item ->
            Surface(
                modifier = Modifier
                    .widthIn(min = BBSpacing.Space24 + BBSpacing.Space20)
                    .heightIn(min = BBSpacing.Space20),
                onClick = item.onClick,
                shape = BBRadius.XlShape,
                color = BBColors.Surface,
                border = BorderStroke(
                    width = 1.dp,
                    color = BBColors.Border
                )
            ) {
                Row(
                    modifier = Modifier.padding(BBSpacing.CardPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    WholesaleIconBox(
                        icon = item.icon,
                        tint = item.tint,
                        backgroundColor = item.backgroundColor,
                        compact = true
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleSmall,
                            color = BBColors.TextStrong,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1
                        )

                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = BBColors.TextMuted,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleIconBox(
    icon: ImageVector,
    tint: Color,
    backgroundColor: Color,
    compact: Boolean = false
) {
    val boxSize = if (compact) BBIcon.BoxMd else BBIcon.BoxXl
    val iconSize = if (compact) BBIcon.SizeMd else BBIcon.SizeXl

    Surface(
        modifier = Modifier.size(boxSize),
        shape = BBRadius.XlShape,
        color = backgroundColor
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = tint
            )
        }
    }
}

@Composable
private fun WholesaleMiniPill(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = BBColors.Gray.Gray50,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.TextMuted,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private data class WholesaleCategoryHomeItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val tint: Color,
    val backgroundColor: Color
)

private data class WholesaleShowcaseProduct(
    val id: Int,
    val title: String,
    val category: String,
    val price: String,
    val moq: String,
    val supplier: String,
    val badge: String,
    val imageRes: Int
)

private data class WholesaleHomeActionItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val tint: Color,
    val backgroundColor: Color,
    val onClick: () -> Unit
)

private fun wholesaleCategoryItems(): List<WholesaleCategoryHomeItem> {
    return listOf(
        WholesaleCategoryHomeItem(
            title = "Elektronik",
            description = "Cihaz ve Bileşenler",
            icon = Icons.Outlined.Inventory2,
            tint = BBColors.Blue.Blue700,
            backgroundColor = BBColors.Blue.Blue50
        ),
        WholesaleCategoryHomeItem(
            title = "Ambalaj",
            description = "Kutu, Etiket ve Paketleme",
            icon = Icons.Outlined.Category,
            tint = BBColors.Yellow.Yellow800,
            backgroundColor = BBColors.Yellow.Yellow100
        ),
        WholesaleCategoryHomeItem(
            title = "Tekstil",
            description = "Kumaş, Giyim ve Aksesuar",
            icon = Icons.Outlined.Storefront,
            tint = BBColors.Green.Green700,
            backgroundColor = BBColors.Green.Green50
        ),
        WholesaleCategoryHomeItem(
            title = "Makine",
            description = "Üretim ve Sanayi Ekipmanı",
            icon = Icons.Outlined.Business,
            tint = BBColors.Navy.Navy700,
            backgroundColor = BBColors.Navy.Navy50
        ),
        WholesaleCategoryHomeItem(
            title = "Gıda",
            description = "Toptan Gıda Ürünleri",
            icon = Icons.Outlined.LocalShipping,
            tint = BBColors.Orange.Orange700,
            backgroundColor = BBColors.Orange.Orange50
        ),
        WholesaleCategoryHomeItem(
            title = "Kimya",
            description = "Endüstriyel Hammaddeler",
            icon = Icons.Outlined.LocalOffer,
            tint = BBColors.Purple.Purple700,
            backgroundColor = BBColors.Purple.Purple50
        )
    )
}

private fun wholesaleShowcaseProducts(): List<WholesaleShowcaseProduct> {
    val defaultImage = R.drawable.h3ff3b33d6a1447c898cee6e336867bach

    return listOf(
        WholesaleShowcaseProduct(
            id = 1,
            title = "Endüstriyel Ambalaj Kutusu",
            category = "Ambalaj ve Paketleme",
            price = "₺18,90 / Adet",
            moq = "MOQ 500",
            supplier = "3 Firma",
            badge = "Vitrin",
            imageRes = defaultImage
        ),
        WholesaleShowcaseProduct(
            id = 2,
            title = "Toptan Üretim Kumaşı",
            category = "Tekstil ve Giyim",
            price = "₺74,50 / Metre",
            moq = "MOQ 250",
            supplier = "5 Firma",
            badge = "Yeni",
            imageRes = defaultImage
        ),
        WholesaleShowcaseProduct(
            id = 3,
            title = "Paslanmaz Üretim Parçası",
            category = "Makine ve Sanayi",
            price = "₺42,00 / Adet",
            moq = "MOQ 100",
            supplier = "4 Firma",
            badge = "RFQ",
            imageRes = defaultImage
        ),
        WholesaleShowcaseProduct(
            id = 4,
            title = "Toptan Elektronik Modül",
            category = "Elektronik Bileşen",
            price = "₺96,00 / Adet",
            moq = "MOQ 200",
            supplier = "6 Firma",
            badge = "Popüler",
            imageRes = defaultImage
        ),
        WholesaleShowcaseProduct(
            id = 5,
            title = "Özel Baskılı Etiket Rulosu",
            category = "Etiket ve Baskı",
            price = "₺31,75 / Rulo",
            moq = "MOQ 300",
            supplier = "2 Firma",
            badge = "Özel",
            imageRes = defaultImage
        )
    )
}

private fun wholesaleActionItems(
    onQuotationRequestsClick: () -> Unit,
    onRfqCreateClick: () -> Unit,
    onLastPriceRequestClick: () -> Unit,
    onSampleRequestClick: () -> Unit,
    onCustomizationRequestClick: () -> Unit
): List<WholesaleHomeActionItem> {
    return listOf(
        WholesaleHomeActionItem(
            title = "Teklifler",
            description = "RFQ kayıtlarını gör",
            icon = Icons.Outlined.RequestQuote,
            tint = BBColors.Orange.Orange700,
            backgroundColor = BBColors.Orange.Orange50,
            onClick = onQuotationRequestsClick
        ),
        WholesaleHomeActionItem(
            title = "RFQ Oluştur",
            description = "Yeni teklif iste",
            icon = Icons.Outlined.LocalOffer,
            tint = BBColors.Orange.Orange700,
            backgroundColor = BBColors.Orange.Orange50,
            onClick = onRfqCreateClick
        ),
        WholesaleHomeActionItem(
            title = "Son Fiyat",
            description = "Fiyat pazarlığı başlat",
            icon = Icons.Outlined.LocalOffer,
            tint = BBColors.Red.Red700,
            backgroundColor = BBColors.Red.Red50,
            onClick = onLastPriceRequestClick
        ),
        WholesaleHomeActionItem(
            title = "Numune",
            description = "Numune talebi gönder",
            icon = Icons.Outlined.Inventory2,
            tint = BBColors.Blue.Blue700,
            backgroundColor = BBColors.Blue.Blue50,
            onClick = onSampleRequestClick
        ),
        WholesaleHomeActionItem(
            title = "Özelleştirme",
            description = "Logo, renk ve üretim detayı",
            icon = Icons.Outlined.Category,
            tint = BBColors.Purple.Purple700,
            backgroundColor = BBColors.Purple.Purple50,
            onClick = onCustomizationRequestClick
        )
    )
}
