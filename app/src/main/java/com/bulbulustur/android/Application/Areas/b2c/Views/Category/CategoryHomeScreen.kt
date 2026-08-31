package com.bulbulustur.android.Application.Areas.b2c.Views.Category

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
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Kitchen
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.MinorCrash
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SettingsSuggest
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.Storefront
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
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
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
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO

@Composable
fun RetailCategoryHomeScreen(
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    categories: List<ProductCategoryDTO> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onSubCategoryClick: (Int) -> Unit = {},
    onCampaignClick: () -> Unit = {},
    onStoreClick: () -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    val categoryItems = remember(categories) {
        categories
            .asSequence()
            .filter { it.ProductCategoryId > 0 && it.CategoryLevel == 1 && it.CategoryName.isNotBlank() }
            .distinctBy { it.ProductCategoryId }
            .sortedBy { it.CategoryName }
            .map {
                RetailCategoryHomeItem(
                    id = it.ProductCategoryId,
                    title = it.CategoryName,
                    description = it.Breadcrumb,
                    iconClass = it.IconClass
                )
            }
            .toList()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = BBLocalization.Current.Get(
                    key = "e4f653c3-8828-4934-aa3b-959cede38feb",
                    fallback = "Ürün, kategori veya marka ara"
                ),
                onSearchClick = { onSearchClick(searchText) },
                onClearClick = { searchText = "" },
                leadingAction = RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            RetailBottomNavigation(
                selectedItem = RetailBottomNavigationItem.Menu,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        RetailBottomNavigationItem.Home -> onHomeClick()
                        RetailBottomNavigationItem.Menu -> Unit
                        RetailBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        RetailBottomNavigationItem.Basket -> onBasketClick()
                        RetailBottomNavigationItem.Account -> onAccountClick()
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
                RetailCategoryHomeIntroCard()
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "19e928cc-d4e4-426f-a1e8-fb8d9adf872f",
                        fallback = "Kategoriler"
                    ),
                    subtitle = BBLocalization.Current.Get(
                        key = "5332539f-0ba4-4605-b655-ee387521b43f",
                        fallback = "Alışveriş yapmak istediğiniz kategoriyi seçin."
                    )
                )
            }

            when {
                isLoading && categoryItems.isEmpty() -> item {
                    RetailCategoryStatusCard(
                        title = "Kategoriler yükleniyor",
                        description = "Kategori bilgileri hazırlanıyor.",
                        loading = true
                    )
                }

                !errorMessage.isNullOrBlank() && categoryItems.isEmpty() -> item {
                    RetailCategoryStatusCard(
                        title = "Kategoriler alınamadı",
                        description = errorMessage
                    )
                }

                categoryItems.isEmpty() -> item {
                    RetailCategoryStatusCard(
                        title = "Kategori bulunamadı",
                        description = "Gösterilebilecek bir kategori bulunmuyor."
                    )
                }

                else -> items(
                    items = categoryItems,
                    key = { it.id }
                ) { item ->
                    RetailCategoryHomeCategoryCard(
                        item = item,
                        onClick = { onSubCategoryClick(item.id) }
                    )
                }
            }

            item {
                BbSectionHeader(
                    title = "Hızlı Geçişler",
                    subtitle = "Alışverişin diğer alanlarına geçin."
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                    contentPadding = PaddingValues(end = BBSpacing.Space1)
                ) {
                    item {
                        RetailCategoryQuickCard(
                            title = "Kampanyalar",
                            description = "Güncel kampanya ve fırsatları incele",
                            icon = Icons.Outlined.LocalOffer,
                            onClick = onCampaignClick
                        )
                    }

                    item {
                        RetailCategoryQuickCard(
                            title = "Mağazalar",
                            description = "Satıcı mağazalarını keşfet",
                            icon = Icons.Outlined.Storefront,
                            onClick = onStoreClick
                        )
                    }

                    item {
                        RetailCategoryQuickCard(
                            title = "Favoriler",
                            description = "Kaydettiğiniz ürünlere git",
                            icon = Icons.Outlined.FavoriteBorder,
                            onClick = onFavoriteClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RetailCategoryHomeIntroCard() {
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
                    imageVector = Icons.Outlined.Category,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Perakende Kategoriler",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Ne arıyorsunuz?",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Ürünleri kategoriye göre keşfedin ve aradığınız bölüme hızlıca ulaşın.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RetailCategoryHomeCategoryCard(
    item: RetailCategoryHomeItem,
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
private fun RetailCategoryQuickCard(
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

@Composable
private fun RetailCategoryStatusCard(
    title: String,
    description: String,
    loading: Boolean = false
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            if (loading) {
                CircularProgressIndicator()
            } else {
                Icon(
                    imageVector = Icons.Outlined.Category,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
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

fun ResolveRetailCategoryIcon(iconClass: String): ImageVector {
    return when (iconClass.trim().lowercase()) {
        "build" -> Icons.Outlined.Build
        "security" -> Icons.Outlined.Security
        "minor_crash" -> Icons.Outlined.MinorCrash
        "settings_suggest" -> Icons.Outlined.SettingsSuggest
        "directions_car" -> Icons.Outlined.DirectionsCar
        "checkroom" -> Icons.Outlined.Checkroom
        "weekend" -> Icons.Outlined.Weekend
        "sports_esports" -> Icons.Outlined.SportsEsports
        "computer" -> Icons.Outlined.Computer
        "local_florist" -> Icons.Outlined.LocalFlorist
        "pets" -> Icons.Outlined.Pets
        "restaurant" -> Icons.Outlined.Restaurant
        "kitchen" -> Icons.Outlined.Kitchen
        "child_care" -> Icons.Outlined.ChildCare
        "home" -> Icons.Outlined.Home
        "face" -> Icons.Outlined.Face
        else -> Icons.Outlined.Category
    }
}

@Immutable
private data class RetailCategoryHomeItem(
    val id: Int,
    val title: String,
    val description: String,
    val iconClass: String
)