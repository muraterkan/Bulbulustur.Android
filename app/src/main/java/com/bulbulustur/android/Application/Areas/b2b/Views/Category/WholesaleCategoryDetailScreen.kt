package com.bulbulustur.android.Application.Areas.b2b.Views.Category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO

@Composable
fun WholesaleCategoryDetailScreen(
    categoryId: Int = 0,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    categoryInfo: ProductCategoryDTO? = null,
    childCategories: List<ProductCategoryDTO> = emptyList(),

    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},

    onSubCategoryClick: (Int) -> Unit = {},
    onProductListClick: (Int) -> Unit = {},
    onCompanyListClick: (Int) -> Unit = {},
    onRfqCreateClick: (Int) -> Unit = {},
    onPopularProductGroupClick: (Int, String) -> Unit = { _, _ -> },
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val category = remember(
        categoryId,
        categoryInfo,
        childCategories
    ) {
        createWholesaleCategoryDetail(
            categoryId = categoryId,
            categoryInfo = categoryInfo,
            childCategories = childCategories
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                placeholder = BBLocalization.Current.Get(
                    key = "8d009caa-1db4-42e9-b394-dc818277d259",
                    fallback = "Toptan Ürün, Kategori Veya Firma Ara"
                ),
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                leadingAction = WholesaleSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick,
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick
            )
        },
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Menu,
                onItemClick = { item ->
                    when (item) {
                        WholesaleBottomNavigationItem.Home -> onHomeClick()
                        WholesaleBottomNavigationItem.Menu -> onMenuClick()
                        WholesaleBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        WholesaleBottomNavigationItem.Basket -> onBasketClick()
                        WholesaleBottomNavigationItem.Account -> onAccountClick()
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
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
            when {
                isLoading -> {
                    item {
                        WholesaleCategoryDetailLoadingState()
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    item {
                        WholesaleCategoryDetailErrorState(
                            message = errorMessage
                        )
                    }
                }

                categoryInfo == null -> {
                    item {
                        WholesaleCategoryDetailEmptyState()
                    }
                }

                else -> {
                    item {
                        WholesaleCategoryDetailHeader(
                            category = category
                        )
                    }

                    item {
                        WholesaleCategoryDetailActions(
                            categoryId = category.categoryId,
                            onProductListClick = onProductListClick,
                            onCompanyListClick = onCompanyListClick,
                            onRfqCreateClick = onRfqCreateClick
                        )
                    }

                    item {
                        BbSectionHeader(
                            title = BBLocalization.Current.Get(
                                key = "19e928cc-d4e4-426f-a1e8-fb8d9adf872f",
                                fallback = ""
                            ),
                            subtitle = BBLocalization.Current.Get(
                                key = "8f3a995c-a9b5-4018-98d1-b99eaf8dcdbb",
                                fallback = "Bu kategoriye bağlı ürün gruplarını incele."
                            )
                        )
                    }

                    if (category.subCategories.isEmpty()) {
                        item {
                            WholesaleChildCategoryEmptyState()
                        }
                    } else {
                        items(
                            items = category.subCategories,
                            key = { subCategory ->
                                subCategory.categoryId
                            }
                        ) { subCategory ->
                            WholesaleSubCategoryCard(
                                subCategory = subCategory,
                                onClick = {
                                    onSubCategoryClick(
                                        subCategory.categoryId
                                    )
                                }
                            )
                        }
                    }

                    item {
                        Spacer(
                            modifier = Modifier.height(
                                BBSpacing.Space4
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleCategoryDetailLoadingState() {
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
private fun WholesaleCategoryDetailErrorState(
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
                text = BBLocalization.Current.Get(
                    key = "59050de1-3d23-41f3-8099-5c8b508e43b4",
                    fallback = "Kategori bilgisi yüklenemedi"
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
private fun WholesaleCategoryDetailEmptyState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Category,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "507c8e7a-40f0-424e-b7dc-e8f5d0a3df07",
                    fallback = "Kategori bilgisi bulunamadı."
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleChildCategoryEmptyState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Category,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "768246a2-980d-4c21-add0-efadd2a4f584",
                    fallback = "Bu kategoriye bağlı alt kategori bulunamadı."
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleCategoryDetailHeader(
    category: WholesaleCategoryDetail
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            WholesaleCategoryIconTitleRow(
                icon = category.icon,
                title = BBLocalization.Current.Get(
                    key = "f8b3417d-7a08-4b79-b94b-c048415a71b4",
                    fallback = "Toptan Kategori"
                )
            )

            Text(
                text = category.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (category.description.isNotBlank()) {
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun WholesaleCategoryDetailActions(
    categoryId: Int,
    onProductListClick: (Int) -> Unit,
    onCompanyListClick: (Int) -> Unit,
    onRfqCreateClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.CardGapCompact
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGapCompact
            )
        ) {
            WholesaleCategoryActionCard(
                title = BBLocalization.Current.Get(
                    key = "5e09b1c8-93e6-4e9a-a055-2f556f57d6dc",
                    fallback = "Ürünleri Gör"
                ),
                description = BBLocalization.Current.Get(
                    key = "a4a0f1e9-0aff-4073-bf49-f0c661e20e66",
                    fallback = "Kategori ürünleri"
                ),
                icon = Icons.Outlined.Inventory2,
                modifier = Modifier.weight(1f),
                onClick = {
                    onProductListClick(categoryId)
                }
            )

            WholesaleCategoryActionCard(
                title = BBLocalization.Current.Get(
                    key = "a4d349d0-5340-4075-b2bb-1e900584f3b7",
                    fallback = "Firmalar"
                ),
                description = BBLocalization.Current.Get(
                    key = "5df4c5b2-4f87-488b-acd1-50e38a211cb5",
                    fallback = "Tedarikçi firmalar"
                ),
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f),
                onClick = {
                    onCompanyListClick(categoryId)
                }
            )
        }

        BbButton(
            text = BBLocalization.Current.Get(
                key = "203882aa-6872-41de-a0db-26b13a6389e3",
                fallback = ""
            ),
            onClick = {
                onRfqCreateClick(categoryId)
            },
            modifier = Modifier.fillMaxWidth(),
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Medium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.RequestQuote,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@Composable
private fun WholesaleCategoryActionCard(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleSubCategoryCard(
    subCategory: WholesaleSubCategoryItem,
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
            Icon(
                imageVector = subCategory.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = subCategory.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (subCategory.description.isNotBlank()) {
                    Text(
                        text = subCategory.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleCategoryIconTitleRow(
    icon: ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.IconTextGap
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

data class WholesaleCategoryDetail(
    val categoryId: Int,
    val name: String,
    val description: String,
    val icon: ImageVector,
    val subCategories: List<WholesaleSubCategoryItem>
)

data class WholesaleSubCategoryItem(
    val categoryId: Int,
    val name: String,
    val description: String,
    val icon: ImageVector
)

private fun createWholesaleCategoryDetail(
    categoryId: Int,
    categoryInfo: ProductCategoryDTO?,
    childCategories: List<ProductCategoryDTO>
): WholesaleCategoryDetail {
    return WholesaleCategoryDetail(
        categoryId = categoryInfo
            ?.ProductCategoryId
            ?.takeIf { id ->
                id > 0
            }
            ?: categoryId,
        name = categoryInfo
            ?.CategoryName
            .orEmpty()
            .ifBlank {
                BBLocalization.Current.Get(
                    key = "1a132fdc-096f-42d7-835d-96b0a17b3675",
                    fallback = ""
                )
            },
        description = categoryInfo
            ?.Breadcrumb
            .orEmpty()
            .ifBlank {
                categoryInfo
                    ?.CategoryName
                    .orEmpty()
            },
        icon = Icons.Outlined.Category,
        subCategories = childCategories
            .filter { child ->
                child.ProductCategoryId > 0
            }
            .map { child ->
                WholesaleSubCategoryItem(
                    categoryId = child.ProductCategoryId,
                    name = child.CategoryName.ifBlank {
                        BBLocalization.Current.Get(
                            key = "1a132fdc-096f-42d7-835d-96b0a17b3675",
                            fallback = ""
                        )
                    },
                    description = child.Breadcrumb.ifBlank {
                        child.CategoryName
                    },
                    icon = Icons.Outlined.Category
                )
            }
    )
}

@Preview(showBackground = true)
@Composable
private fun WholesaleCategoryDetailScreenPreview() {
    BbTheme {
        WholesaleCategoryDetailScreen(
            categoryId = 1,
            categoryInfo = ProductCategoryDTO(
                ProductCategoryId = 1,
                CategoryName = BBLocalization.Current.Get(
                    key = "18101507-39f0-482d-80e7-491992e2915b",
                    fallback = "Elektronik"
                ),
                Breadcrumb = BBLocalization.Current.Get(
                    key = "24c60230-72a0-45c3-aaf1-ced7ede1957d",
                    fallback = "Elektronik ürünleri"
                )
            ),
            childCategories = listOf(
                ProductCategoryDTO(
                    ProductCategoryId = 2,
                    CategoryName = BBLocalization.Current.Get(
                        key = "4cd27909-9fb8-4840-b99a-8ad26dbf61fc",
                        fallback = "Elektronik Bileşenler"
                    ),
                    Breadcrumb = BBLocalization.Current.Get(
                        key = "4a5ea768-dc60-4e68-97d5-0c51c5635d12",
                        fallback = "Elektronik > Elektronik Bileşenler"
                    )
                )
            )
        )
    }
}