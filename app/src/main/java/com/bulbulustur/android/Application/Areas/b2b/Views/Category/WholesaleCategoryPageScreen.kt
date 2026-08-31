package com.bulbulustur.android.Application.Areas.b2b.Views.Category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Kitchen
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.MinorCrash
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SettingsSuggest
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.Weekend
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbMaterialSymbol
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
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
    var searchText by remember { mutableStateOf("") }

    val categoryItems = remember(categories) {
        categories
            .asSequence()
            .filter { it.ProductCategoryId > 0 && it.CategoryLevel == 1 && it.CategoryName.isNotBlank() }
            .distinctBy { it.ProductCategoryId }
            .sortedBy { it.CategoryName }
            .map {
                WholesaleCategoryHomeItem(
                    id = it.ProductCategoryId,
                    title = it.CategoryName,
                    description = it.Breadcrumb.ifBlank {
                        BBLocalization.Current.Get(
                            key = "9eb4ea20-1955-4ce0-9126-7c9a396c6bcc",
                            fallback = "Toptan kategori ürünlerini keşfet"
                        )
                    },
                    iconClass = it.IconClass
                )
            }
            .toList()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = BBLocalization.Current.Get(
                    key = "8d009caa-1db4-42e9-b394-dc818277d259",
                    fallback = "Toptan ürün, kategori veya tedarikçi ara"
                ),
                onSearchClick = { onSearchClick(searchText) },
                onClearClick = { searchText = "" },
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
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                WholesaleCategoryHomeIntroCard()
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "c5fd418b-c93c-4495-87c3-643f24fbde2d",
                        fallback = "Toptan Kategoriler"
                    ),
                    subtitle = BBLocalization.Current.Get(
                        key = "d820fdf3-d021-4654-ba31-e2a30181780d",
                        fallback = "Ürün gruplarını ve alt kategori yapılarını incele."
                    )
                )
            }

            when {
                isLoading && categoryItems.isEmpty() -> item {
                    WholesaleCategoryLoadingState()
                }

                !errorMessage.isNullOrBlank() && categoryItems.isEmpty() -> item {
                    WholesaleCategoryErrorState(
                        message = errorMessage
                    )
                }

                categoryItems.isEmpty() -> item {
                    WholesaleCategoryEmptyState()
                }

                else -> items(
                    items = categoryItems,
                    key = { it.id }
                ) { item ->
                    WholesaleCategoryHomeCategoryCard(
                        item = item,
                        onClick = { onSubCategoryClick(item.id) }
                    )
                }
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "06e02e92-8cf3-4ace-bbc9-31c5255f24bf",
                        fallback = "Toptan Ticaret Akışı"
                    ),
                    subtitle = BBLocalization.Current.Get(
                        key = "d65eae8b-22c0-41b3-a61a-b5bb210a8dd9",
                        fallback = "Ürün, tedarikçi ve teklif kanallarına hızlı geç."
                    )
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                    contentPadding = PaddingValues(end = BBSpacing.Space1)
                ) {
                    item {
                        WholesaleCategoryQuickCard(
                            title = BBLocalization.Current.Get(
                                key = "9346820b-56b1-4be1-9ac2-4d6f22fa18b5",
                                fallback = "Tedarikçiler"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "3372543b-f704-405e-af2e-bb2fec721b48",
                                fallback = "Toptan satış yapan firmalar"
                            ),
                            icon = Icons.Outlined.Business,
                            onClick = onCompanyListClick
                        )
                    }

                    item {
                        WholesaleCategoryQuickCard(
                            title = BBLocalization.Current.Get(
                                key = "9aa9e9a4-18b3-427b-943f-36170e46cb37",
                                fallback = "Teklif Al"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "b372f794-1665-42e3-b543-1d7b1f9ab0be",
                                fallback = "İhtiyacını firmalara ilet"
                            ),
                            icon = Icons.Outlined.RequestQuote,
                            onClick = onRfqClick
                        )
                    }

                    item {
                        WholesaleCategoryQuickCard(
                            title = BBLocalization.Current.Get(
                                key = "1cfc3769-add7-41d6-b18b-117466c6e19f",
                                fallback = "Son Fiyat"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "e5b83084-8ef7-4ff8-8dff-df7117bbd2c0",
                                fallback = "Güncel toptan fiyat al"
                            ),
                            icon = Icons.Outlined.Search,
                            onClick = onLastPriceRequestClick
                        )
                    }

                    item {
                        WholesaleCategoryQuickCard(
                            title = BBLocalization.Current.Get(
                                key = "76f040d9-6f98-4e10-aeba-fcbf5a6691ba",
                                fallback = "Numune"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "0b9b7506-3593-4f44-b8b9-c68c8e19e1ab",
                                fallback = "Sipariş öncesi numune iste"
                            ),
                            icon = Icons.Outlined.LocalShipping,
                            onClick = onSampleRequestClick
                        )
                    }

                    item {
                        WholesaleCategoryQuickCard(
                            title = BBLocalization.Current.Get(
                                key = "44b76fc9-f305-4368-80ce-fea7d160eb17",
                                fallback = ""
                            ),
                            description = BBLocalization.Current.Get(
                                key = "5534f745-5831-49cb-abd0-6ef43c11f684",
                                fallback = "Özel üretim talebi oluştur"
                            ),
                            icon = Icons.Outlined.Tune,
                            onClick = onCustomizationRequestClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleCategoryHomeIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
                    text = BBLocalization.Current.Get(
                        key = "f1aa464c-b1eb-49cd-a651-3802350f4af1",
                        fallback = "Toptan Ticaret"
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = BBLocalization.Current.Get(
                    key = "243b21cb-d0b0-4a37-852b-6fe2e8a63f85",
                    fallback = "Toptan ürün kategorilerini keşfet"
                ),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "d96a80bd-840a-4a7b-9ee8-a0897c32328a",
                    fallback = "Ürün gruplarını incele, tedarikçilere ulaş ve teklif süreçlerini başlat."
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = BBLocalization.Current.Get(
                    key = "0fbe628e-e5fb-4f84-8bdd-b7172065d2c6",
                    fallback = "Kategoriler yüklenemedi"
                ),
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.Category,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "9bff57b4-bc7a-4b4e-b655-7a1d63b4df56",
                    fallback = "Gösterilecek toptan kategori bulunamadı."
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleCategoryHomeCategoryCard(
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            BbIconBox(
                size = BbIconBoxSize.Medium,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.primary,
                radius = BBRadius.lg
            ) {
                BbMaterialSymbol(
                    iconClass = item.iconClass,
                    tint = MaterialTheme.colorScheme.primary,
                    size = BBIcon.Section
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
private fun WholesaleCategoryQuickCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(BBSpacing.Space24 + BBSpacing.Space20),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .width(BBSpacing.Space11)
                    .height(BBSpacing.Space11)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Immutable
private data class WholesaleCategoryHomeItem(
    val id: Int,
    val title: String,
    val description: String,
    val iconClass: String
)

@Preview(showBackground = true)
@Composable
private fun WholesaleCategoryHomeScreenPreview() {
    BbTheme {
        WholesaleCategoryHomeScreen()
    }
}