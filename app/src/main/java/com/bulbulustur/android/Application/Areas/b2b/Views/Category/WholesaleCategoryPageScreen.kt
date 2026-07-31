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
import androidx.compose.material3.CircularProgressIndicator
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
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO

@Composable
fun WholesaleCategoryHomeScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
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
    categories: List<ProductCategoryDTO> = emptyList(),
    onSubCategoryClick: (Int) -> Unit = {},
    onCompanyListClick: () -> Unit = {},
    onRfqClick: () -> Unit = {},
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val categoryItems = categories
        .filter { category ->
            category.ProductCategoryId > 0
        }
        .mapIndexed { index, category ->
            WholesaleCategoryHomeItem(
                id = category.ProductCategoryId,
                title = category.CategoryName.ifBlank {
                    BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = "")
                },
                description = category.Breadcrumb.ifBlank {
                    "Toptan kategori ürünlerini keşfet"
                },
                icon = Icons.Outlined.Category,
                backgroundColor = when (index % 4) {
                    0 -> MaterialTheme.colorScheme.primaryContainer
                    1 -> MaterialTheme.colorScheme.surfaceVariant
                    2 -> MaterialTheme.colorScheme.secondaryContainer
                    else -> MaterialTheme.colorScheme.tertiaryContainer
                },
                iconColor = when (index % 4) {
                    0 -> MaterialTheme.colorScheme.onPrimaryContainer
                    1 -> MaterialTheme.colorScheme.onSurfaceVariant
                    2 -> MaterialTheme.colorScheme.onSecondaryContainer
                    else -> MaterialTheme.colorScheme.onTertiaryContainer
                }
            )
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.SectionGapCompact
            )
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
                    title = "Toptan Kategoriler",
                    subtitle = "Ürün gruplarını ve alt kategori yapılarını incele."
                )
            }

            when {
                isLoading -> {
                    item {
                        WholesaleCategoryLoadingState()
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    item {
                        WholesaleCategoryErrorState(
                            message = errorMessage
                        )
                    }
                }

                categoryItems.isEmpty() -> {
                    item {
                        WholesaleCategoryEmptyState()
                    }
                }

                else -> {
                    items(
                        items = categoryItems,
                        key = { item ->
                            item.id
                        }
                    ) { item ->
                        WholesaleCategoryItemCard(
                            item = item,
                            onClick = {
                                onSubCategoryClick(item.id)
                            }
                        )
                    }
                }
            }

            item {
                BbSectionHeader(
                    title = "Toptan Ticaret Akışı",
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
                Spacer(
                    modifier = Modifier.height(BBSpacing.Space4)
                )
            }
        }
    }
}

@Composable
private fun WholesaleCategoryLoadingState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun WholesaleCategoryErrorState(
    message: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Text(
                text = "Kategoriler yüklenemedi",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleCategoryEmptyState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.Category,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Gösterilecek toptan kategori bulunamadı.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
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
                    imageVector = Icons.Outlined.Factory,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Toptan Ticaret",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Toptan ürün kategorilerini keşfet",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Ürün gruplarını incele, tedarikçilere ulaş ve teklif süreçlerini başlat.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(key = "5e09b1c8-93e6-4e9a-a055-2f556f57d6dc", fallback = "Ürünleri Gör"),
                    onClick = onProductListClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "9aa9e9a4-18b3-427b-943f-36170e46cb37", fallback = "Teklif Al"),
                    onClick = onRfqClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium
                )
            }

            BbButton(
                text = BBLocalization.Current.Get(key = "9346820b-56b1-4be1-9ac2-4d6f22fa18b5", fallback = "Tedarikçileri Gör"),
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
    val quickActions = getWholesaleQuickActions()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items(
            items = quickActions,
            key = { item ->
                item.target
            }
        ) { item ->
            WholesaleCategoryQuickActionCard(
                item = item,
                onClick = {
                    when (item.target) {
                        WholesaleCategoryQuickActionTarget.Companies ->
                            onCompanyListClick()

                        WholesaleCategoryQuickActionTarget.Rfq ->
                            onRfqClick()

                        WholesaleCategoryQuickActionTarget.LastPrice ->
                            onLastPriceRequestClick()

                        WholesaleCategoryQuickActionTarget.Sample ->
                            onSampleRequestClick()

                        WholesaleCategoryQuickActionTarget.Customization ->
                            onCustomizationRequestClick()
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
private fun WholesaleCategoryItemCard(
    item: WholesaleCategoryHomeItem,
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
private fun WholesaleCategoryShowcaseRow(
    onProductListClick: () -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqClick: () -> Unit
) {
    val showcases = getWholesaleCategoryShowcases()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        items(
            items = showcases,
            key = { item ->
                item.target
            }
        ) { item ->
            WholesaleCategoryShowcaseCard(
                item = item,
                onClick = {
                    when (item.target) {
                        WholesaleCategoryShowcaseTarget.Products ->
                            onProductListClick()

                        WholesaleCategoryShowcaseTarget.Companies ->
                            onCompanyListClick()

                        WholesaleCategoryShowcaseTarget.Rfq ->
                            onRfqClick()
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
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(
                BBSpacing.Space4
            ),
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
private data class WholesaleCategoryHomeItem(
    val id: Int,
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

@Composable
private fun getWholesaleQuickActions(): List<WholesaleCategoryQuickActionItem> {
    return listOf(
        WholesaleCategoryQuickActionItem(
            title = BBLocalization.Current.Get(key = "9346820b-56b1-4be1-9ac2-4d6f22fa18b5", fallback = "Tedarikçiler"),
            description = "Toptan satış yapan firmalar",
            icon = Icons.Outlined.Business,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            target = WholesaleCategoryQuickActionTarget.Companies
        ),
        WholesaleCategoryQuickActionItem(
            title = BBLocalization.Current.Get(key = "9aa9e9a4-18b3-427b-943f-36170e46cb37", fallback = "Teklif Al"),
            description = "İhtiyacını firmalara ilet",
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            target = WholesaleCategoryQuickActionTarget.Rfq
        ),
        WholesaleCategoryQuickActionItem(
            title = "Son Fiyat",
            description = "Güncel toptan fiyat al",
            icon = Icons.Outlined.Search,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            target = WholesaleCategoryQuickActionTarget.LastPrice
        ),
        WholesaleCategoryQuickActionItem(
            title = "Numune",
            description = "Sipariş öncesi numune iste",
            icon = Icons.Outlined.LocalShipping,
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            target = WholesaleCategoryQuickActionTarget.Sample
        ),
        WholesaleCategoryQuickActionItem(
            title = BBLocalization.Current.Get(key = "44b76fc9-f305-4368-80ce-fea7d160eb17", fallback = ""),
            description = "Özel üretim talebi oluştur",
            icon = Icons.Outlined.Tune,
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            target = WholesaleCategoryQuickActionTarget.Customization
        )
    )
}

@Composable
private fun getWholesaleCategoryShowcases(): List<WholesaleCategoryShowcaseItem> {
    return listOf(
        WholesaleCategoryShowcaseItem(
            title = BBLocalization.Current.Get(key = "1d7da276-0c79-47a8-b8f5-d8aa0967d923", fallback = ""),
            description = "Yayınlanan toptan ürünleri listele.",
            icon = Icons.Outlined.ShoppingBasket,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            target = WholesaleCategoryShowcaseTarget.Products
        ),
        WholesaleCategoryShowcaseItem(
            title = BBLocalization.Current.Get(key = "9346820b-56b1-4be1-9ac2-4d6f22fa18b5", fallback = "Tedarikçiler"),
            description = "Toptan satış yapan firmalara ulaş.",
            icon = Icons.Outlined.Verified,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            target = WholesaleCategoryShowcaseTarget.Companies
        ),
        WholesaleCategoryShowcaseItem(
            title = "Teklif Topla",
            description = BBLocalization.Current.Get(key = "203882aa-6872-41de-a0db-26b13a6389e3", fallback = ""),
            icon = Icons.Outlined.RequestQuote,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            target = WholesaleCategoryShowcaseTarget.Rfq
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun WholesaleCategoryHomeScreenPreview() {
    BbTheme {
        WholesaleCategoryHomeScreen()
    }
}