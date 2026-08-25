package com.bulbulustur.android.Application.Areas.b2b.Views.Category

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Areas.b2b.Views.Components.WholesaleCategoryProductShowcaseContent
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
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
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialContentDTO

@Composable
fun WholesaleCategoryDetailScreen(
    categoryId: Int = 0,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    categoryInfo: ProductCategoryDTO? = null,
    childCategories: List<ProductCategoryDTO> = emptyList(),
    specialContents: List<WholesaleHomepageSpecialContentDTO> = emptyList(),
    isSpecialContentsLoading: Boolean = false,
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
    onSpecialProductClick: (Int) -> Unit = {},
    onSpecialFavoriteClick: (Int) -> Unit = {},
    onSpecialViewAllClick: (WholesaleHomepageSpecialContentDTO) -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    val categoryName = safeCategoryText(categoryInfo?.CategoryName).ifBlank {
        BBLocalization.Current.Get(
            key = "1a132fdc-096f-42d7-835d-96b0a17b3675",
            fallback = ""
        )
    }

    val categoryDescription = safeCategoryText(categoryInfo?.Breadcrumb)
        .takeIf {
            it.isNotBlank() &&
                    it != categoryId.toString() &&
                    it.any { character -> !character.isDigit() }
        }
        .orEmpty()

    val validChildCategories = remember(childCategories) {
        childCategories
            .filter {
                it.ProductCategoryId > 0 &&
                        safeCategoryText(it.CategoryName).isNotBlank()
            }
            .distinctBy { it.ProductCategoryId }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                placeholder = BBLocalization.Current.Get(
                    key = "8d009caa-1db4-42e9-b394-dc818277d259",
                    fallback = "Toptan Ürün, Kategori Veya Firma Ara"
                ),
                leadingAction = WholesaleSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick,
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
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
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.SectionGapCompact
            )
        ) {
            when {
                isLoading -> {
                    item {
                        WholesaleCategoryLoading()
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    item {
                        WholesaleCategoryError(
                            message = errorMessage
                        )
                    }
                }

                categoryInfo == null -> {
                    item {
                        WholesaleCategoryEmpty()
                    }
                }

                else -> {
                    item {
                        WholesaleCategoryHero(
                            categoryName = categoryName,
                            description = categoryDescription,
                            childCategoryCount = validChildCategories.size,
                            hasShowcases = specialContents.isNotEmpty()
                        )
                    }

                    item {
                        WholesaleCategoryActions(
                            categoryId = categoryId,
                            onProductListClick = onProductListClick,
                            onCompanyListClick = onCompanyListClick
                        )
                    }

                    item {
                        WholesaleCategorySectionTitle(
                            title = BBLocalization.Current.Get(
                                key = "19e928cc-d4e4-426f-a1e8-fb8d9adf872f",
                                fallback = "Alt Kategoriler"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "74a46e5a-2695-4867-8660-b0fe2b4f8528",
                                fallback = "Doğrudan ürün akışına inmek için hızlı seçim."
                            )
                        )
                    }

                    if (validChildCategories.isEmpty()) {
                        item {
                            WholesaleChildCategoryEmpty()
                        }
                    } else {
                        items(
                            items = validChildCategories,
                            key = { category ->
                                category.ProductCategoryId
                            }
                        ) { category ->
                            WholesaleSubCategoryRow(
                                category = category,
                                onClick = {
                                    onSubCategoryClick(
                                        category.ProductCategoryId
                                    )
                                }
                            )
                        }
                    }

                    item {
                        WholesaleCategorySectionTitle(
                            title = BBLocalization.Current.Get(
                                key = "b18fcc04-78f7-4914-afb6-8f283fd08a61",
                                fallback = "Seçilmiş Toptan Ürün Grupları"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "79b1703e-e0b9-43a0-80ef-9ceb2dd6933a",
                                fallback = "Toptan alıma uygun özel ürün vitrinlerini keşfedin."
                            )
                        )
                    }

                    item {
                        WholesaleCategoryProductShowcaseContent(
                            specialContents = specialContents,
                            isLoading = isSpecialContentsLoading,
                            onProductClick = onSpecialProductClick,
                            onFavoriteClick = onSpecialFavoriteClick,
                            onRfqClick = onRfqCreateClick,
                            onViewAllClick = onSpecialViewAllClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleCategoryHero(
    categoryName: String,
    description: String,
    childCategoryCount: Int,
    hasShowcases: Boolean
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.35f
            )
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space4
                )
            ) {
                BbIconBox(
                    size = BbIconBoxSize.Xl,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    radius = BBRadius.xl
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Category,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.Ui),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = BBLocalization.Current.Get(
                            key = "f8b3417d-7a08-4b79-b94b-c048415a71b4",
                            fallback = "Toptan Kategori"
                        ),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = categoryName,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    if (description.isNotBlank()) {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            if (childCategoryCount > 0 || hasShowcases) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
                ) {
                    if (childCategoryCount > 0) {
                        item {
                            WholesaleCategoryInfoChip(
                                text = "$childCategoryCount ${
                                    BBLocalization.Current.Get(
                                        key = "19e928cc-d4e4-426f-a1e8-fb8d9adf872f",
                                        fallback = "Alt Kategori"
                                    )
                                }"
                            )
                        }
                    }

                    if (hasShowcases) {
                        item {
                            WholesaleCategoryInfoChip(
                                text = BBLocalization.Current.Get(
                                    key = "4272734e-b75c-4eb5-a568-7d7f629116fe",
                                    fallback = "Toptan Vitrin"
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleCategoryInfoChip(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.20f
            )
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun WholesaleCategoryActions(
    categoryId: Int,
    onProductListClick: (Int) -> Unit,
    onCompanyListClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.CardGapCompact
        )
    ) {
        WholesaleCategoryActionCard(
            modifier = Modifier.weight(1f),
            title = BBLocalization.Current.Get(
                key = "5e09b1c8-93e6-4e9a-a055-2f556f57d6dc",
                fallback = "Ürün Vitrinleri"
            ),
            description = BBLocalization.Current.Get(
                key = "a4a0f1e9-0aff-4073-bf49-f0c661e20e66",
                fallback = "Seçilmiş ürün grupları"
            ),
            icon = Icons.Outlined.Inventory2,
            onClick = {
                onProductListClick(categoryId)
            }
        )

        WholesaleCategoryActionCard(
            modifier = Modifier.weight(1f),
            title = BBLocalization.Current.Get(
                key = "a4d349d0-5340-4075-b2bb-1e900584f3b7",
                fallback = "Firmalar"
            ),
            description = BBLocalization.Current.Get(
                key = "5df4c5b2-4f87-488b-acd1-50e38a211cb5",
                fallback = "Tedarikçi firmalar"
            ),
            icon = Icons.Outlined.Business,
            onClick = {
                onCompanyListClick(categoryId)
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
                modifier = Modifier.size(BBIcon.Ui),
                tint = MaterialTheme.colorScheme.primary
            )

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

@Composable
private fun WholesaleSubCategoryRow(
    category: ProductCategoryDTO,
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
            BbIconBox(
                size = BbIconBoxSize.Medium,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                radius = BBRadius.lg
            ) {
                Icon(
                    imageVector = Icons.Outlined.Category,
                    contentDescription = null,
                    modifier = Modifier.size(BBIcon.Ui),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = safeCategoryText(
                    category.CategoryName
                ),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleCategorySectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        if (title.isNotBlank()) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }

        if (description.isNotBlank()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleCategoryLoading() {
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
private fun WholesaleCategoryError(
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
private fun WholesaleCategoryEmpty() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
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

@Composable
private fun WholesaleChildCategoryEmpty() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
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

private fun safeCategoryText(
    value: String?
): String {
    return value
        ?.trim()
        .orEmpty()
}

@Preview(showBackground = true)
@Composable
private fun WholesaleCategoryDetailScreenPreview() {
    BbTheme {
        WholesaleCategoryDetailScreen(
            categoryId = 1624,
            categoryInfo = ProductCategoryDTO(
                ProductCategoryId = 1624,
                CategoryName = "Elektronik Parçalar",
                Breadcrumb = ""
            ),
            childCategories = listOf(
                ProductCategoryDTO(
                    ProductCategoryId = 1625,
                    CategoryName = "Bobinler",
                    Breadcrumb = ""
                ),
                ProductCategoryDTO(
                    ProductCategoryId = 1626,
                    CategoryName = "İndüktörler",
                    Breadcrumb = ""
                ),
                ProductCategoryDTO(
                    ProductCategoryId = 1627,
                    CategoryName = "Dirençler",
                    Breadcrumb = ""
                )
            )
        )
    }
}