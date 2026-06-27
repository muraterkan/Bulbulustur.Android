package com.bulbulustur.android.Application.Areas.b2b.Views.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.R
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
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
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun WholesaleHomeScreen(
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onProductDetailClick: (Int) -> Unit = {},
    onRfqListClick: () -> Unit = {},
    onRfqCreateClick: () -> Unit = {},
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    val categories = wholesaleCategoryItems()

    val showcaseProducts = remember {
        wholesaleShowcaseProducts()
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val actionItems = wholesaleActionItems(
        onRfqListClick = onRfqListClick,
        onRfqCreateClick = onRfqCreateClick,
        onLastPriceRequestClick = onLastPriceRequestClick,
        onSampleRequestClick = onSampleRequestClick,
        onCustomizationRequestClick = onCustomizationRequestClick
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                placeholder = "Ürün, firma veya RFQ ara",
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                onMessageClick = onMessageClick
            )
        },
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Home,
                onItemClick = { item ->
                    when (item) {
                        WholesaleBottomNavigationItem.Home -> {
                            Unit
                        }

                        WholesaleBottomNavigationItem.Menu -> {
                            onCategoryClick()
                        }

                        WholesaleBottomNavigationItem.ModeSwitch -> {
                            onModeSwitchClick()
                        }

                        WholesaleBottomNavigationItem.Basket -> {
                            onRfqListClick()
                        }

                        WholesaleBottomNavigationItem.Account -> {
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
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.SectionGap
            )
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
                    onCategoryClick = onCategoryClick
                )
            }

            item {
                WholesaleSectionTitleWithAction(
                    title = "Toptan Vitrin Ürünleri",
                    description = "Tedarikçi Ürünlerini hızlıca incele.",
                    actionText = "Tümünü gör",
                    onActionClick = onProductListClick
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space3
                    ),
                    contentPadding = PaddingValues(
                        end = BBSpacing.PageHorizontal
                    )
                ) {
                    items(
                        items = showcaseProducts,
                        key = { product ->
                            product.Id
                        }
                    ) { product ->
                        val isFavorite =
                            favoriteProductIds.contains(product.Id)

                        WholesaleProductCard(
                            product = product.copy(
                                IsFavorite = isFavorite
                            ),
                            modifier = Modifier.width(
                                BBSpacing.Space24 +
                                        BBSpacing.Space24
                            ),
                            onClick = {
                                onProductDetailClick(product.Id)
                            },
                            onFavoriteClick = {
                                favoriteProductIds =
                                    if (isFavorite) {
                                        favoriteProductIds - product.Id
                                    } else {
                                        favoriteProductIds + product.Id
                                    }
                            },
                            onRfqClick = onRfqCreateClick
                        )
                    }
                }
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space4
                )
            ) {
                WholesaleIconBox(
                    icon = Icons.Outlined.Business,
                    tint = BBColors.Navy.Navy700,
                    backgroundColor = BBColors.Navy.Navy50
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = "Toptan Ticaret Merkezi",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "Ürün, firma ve teklif akışını tek yerden yönet.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BbButton(
                    text = "Teklif İste",
                    onClick = onRfqCreateClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.RequestQuote,
                            contentDescription = null,
                            modifier = Modifier.size(
                                BBIcon.ButtonIcon
                            )
                        )
                    }
                )

                BbButton(
                    text = "Ürünler",
                    onClick = onProductListClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(
                                BBIcon.ButtonIcon
                            )
                        )
                    }
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
                title = "Doğrulanmış"
            )

            WholesaleTrustItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Language,
                title = "41 dil"
            )

            WholesaleTrustItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Shield,
                title = "Güvenli"
            )
        }
    }
}

@Composable
private fun WholesaleTrustItem(
    modifier: Modifier,
    icon: ImageVector,
    title: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space2
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(
                BBIcon.SizeXl
            ),
            tint = BBColors.Yellow.Yellow600
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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

            Spacer(
                modifier = Modifier.width(
                    BBSpacing.Space1
                )
            )

            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = actionText,
                modifier = Modifier.size(
                    BBIcon.SizeSm
                ),
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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                rowItems.forEach { item ->
                    WholesaleCategoryCard(
                        item = item,
                        onClick = onCategoryClick,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (rowItems.size == 1) {
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
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
        modifier = modifier.height(
            BBSpacing.Space24 +
                    BBSpacing.Space16
        ),
        onClick = onClick,
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BBSpacing.CardPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            WholesaleIconBox(
                icon = item.Icon,
                tint = item.Tint,
                backgroundColor = item.BackgroundColor
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = item.Title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = item.Description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun WholesaleActionRow(
    items: List<WholesaleHomeActionItem>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        ),
        contentPadding = PaddingValues(
            end = BBSpacing.PageHorizontal
        )
    ) {
        items(
            items = items,
            key = { item ->
                item.Title
            }
        ) { item ->
            Surface(
                modifier = Modifier
                    .widthIn(
                        min = BBSpacing.Space24 +
                                BBSpacing.Space20
                    )
                    .heightIn(
                        min = BBSpacing.Space20
                    ),
                onClick = item.OnClick,
                shape = BBRadius.XlShape,
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(
                    width = BBSpacing.BorderThin,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        BBSpacing.CardPadding
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space3
                    )
                ) {
                    WholesaleIconBox(
                        icon = item.Icon,
                        tint = item.Tint,
                        backgroundColor = item.BackgroundColor,
                        compact = true
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            BBSpacing.Space1
                        )
                    ) {
                        Text(
                            text = item.Title,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1
                        )

                        Text(
                            text = item.Description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
    val boxSize = if (compact) {
        BBIcon.BoxMd
    } else {
        BBIcon.BoxXl
    }

    val iconSize = if (compact) {
        BBIcon.SizeMd
    } else {
        BBIcon.SizeXl
    }

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

@Immutable
private data class WholesaleCategoryHomeItem(
    val Title: String,
    val Description: String,
    val Icon: ImageVector,
    val Tint: Color,
    val BackgroundColor: Color
)

@Immutable
private data class WholesaleHomeActionItem(
    val Title: String,
    val Description: String,
    val Icon: ImageVector,
    val Tint: Color,
    val BackgroundColor: Color,
    val OnClick: () -> Unit
)

@Composable
private fun wholesaleCategoryItems(): List<WholesaleCategoryHomeItem> {
    return listOf(
        WholesaleCategoryHomeItem(
            Title = "Elektronik",
            Description = "Cihaz ve bileşenler",
            Icon = Icons.Outlined.Inventory2,
            Tint = MaterialTheme.colorScheme.onSurface,
            BackgroundColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        WholesaleCategoryHomeItem(
            Title = "Ambalaj",
            Description = "Kutu, etiket ve paketleme",
            Icon = Icons.Outlined.Category,
            Tint = MaterialTheme.colorScheme.onPrimaryContainer,
            BackgroundColor = MaterialTheme.colorScheme.primaryContainer
        ),
        WholesaleCategoryHomeItem(
            Title = "Tekstil",
            Description = "Kumaş, giyim ve aksesuar",
            Icon = Icons.Outlined.Storefront,
            Tint = MaterialTheme.colorScheme.onSecondaryContainer,
            BackgroundColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        WholesaleCategoryHomeItem(
            Title = "Makine",
            Description = "Üretim ve sanayi ekipmanı",
            Icon = Icons.Outlined.Business,
            Tint = MaterialTheme.colorScheme.onSurfaceVariant,
            BackgroundColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        WholesaleCategoryHomeItem(
            Title = "Gıda",
            Description = "Toptan gıda ürünleri",
            Icon = Icons.Outlined.LocalShipping,
            Tint = MaterialTheme.colorScheme.onTertiaryContainer,
            BackgroundColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        WholesaleCategoryHomeItem(
            Title = "Kimya",
            Description = "Endüstriyel hammaddeler",
            Icon = Icons.Outlined.LocalOffer,
            Tint = MaterialTheme.colorScheme.onTertiaryContainer,
            BackgroundColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    )
}

private fun wholesaleShowcaseProducts(): List<WholesaleProductCardModel> {
    return listOf(
        WholesaleProductCardModel(
            Id = 1,
            Title = "Endüstriyel ambalaj kutusu",
            Category = "Ambalaj ve paketleme",
            PriceText = "₺18,90 / adet",
            MoqText = "MOQ 500",
            SupplierText = "3 firma",
            BadgeText = "Vitrin",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach
        ),
        WholesaleProductCardModel(
            Id = 2,
            Title = "Toptan üretim kumaşı",
            Category = "Tekstil ve giyim",
            PriceText = "₺74,50 / metre",
            MoqText = "MOQ 250",
            SupplierText = "5 firma",
            BadgeText = "Yeni",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar1
        ),
        WholesaleProductCardModel(
            Id = 3,
            Title = "Paslanmaz üretim parçası",
            Category = "Makine ve sanayi",
            PriceText = "₺42,00 / adet",
            MoqText = "MOQ 100",
            SupplierText = "4 firma",
            BadgeText = "RFQ",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar2
        ),
        WholesaleProductCardModel(
            Id = 4,
            Title = "Toptan elektronik modül",
            Category = "Elektronik bileşen",
            PriceText = "₺96,00 / adet",
            MoqText = "MOQ 200",
            SupplierText = "6 firma",
            BadgeText = "Popüler",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar3
        ),
        WholesaleProductCardModel(
            Id = 5,
            Title = "Özel baskılı etiket rulosu",
            Category = "Etiket ve baskı",
            PriceText = "₺31,75 / rulo",
            MoqText = "MOQ 300",
            SupplierText = "2 firma",
            BadgeText = "Özel",
            ImageResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach
        )
    )
}

private fun wholesaleActionItems(
    onRfqListClick: () -> Unit,
    onRfqCreateClick: () -> Unit,
    onLastPriceRequestClick: () -> Unit,
    onSampleRequestClick: () -> Unit,
    onCustomizationRequestClick: () -> Unit
): List<WholesaleHomeActionItem> {
    return listOf(
        WholesaleHomeActionItem(
            Title = "Teklifler",
            Description = "RFQ kayıtlarını gör",
            Icon = Icons.Outlined.RequestQuote,
            Tint = BBColors.Orange.Orange700,
            BackgroundColor = BBColors.Orange.Orange50,
            OnClick = onRfqListClick
        ),
        WholesaleHomeActionItem(
            Title = "Teklif İste",
            Description = "Yeni teklif iste",
            Icon = Icons.Outlined.LocalOffer,
            Tint = BBColors.Orange.Orange700,
            BackgroundColor = BBColors.Orange.Orange50,
            OnClick = onRfqCreateClick
        ),
        WholesaleHomeActionItem(
            Title = "Son fiyat",
            Description = "Fiyat pazarlıĞı başlat",
            Icon = Icons.Outlined.LocalOffer,
            Tint = BBColors.Red.Red700,
            BackgroundColor = BBColors.Red.Red50,
            OnClick = onLastPriceRequestClick
        ),
        WholesaleHomeActionItem(
            Title = "Numune",
            Description = "Numune talebi gönder",
            Icon = Icons.Outlined.Inventory2,
            Tint = BBColors.Blue.Blue700,
            BackgroundColor = BBColors.Blue.Blue50,
            OnClick = onSampleRequestClick
        ),
        WholesaleHomeActionItem(
            Title = "Özelleştirme",
            Description = "Logo, renk ve üretim detayı",
            Icon = Icons.Outlined.Category,
            Tint = BBColors.Purple.Purple700,
            BackgroundColor = BBColors.Purple.Purple50,
            OnClick = onCustomizationRequestClick
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
