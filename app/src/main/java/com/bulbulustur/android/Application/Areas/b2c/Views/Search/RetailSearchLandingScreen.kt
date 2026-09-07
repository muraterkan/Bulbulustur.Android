package com.bulbulustur.android.Application.Areas.b2c.Views.Search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import kotlinx.coroutines.delay

@Composable
fun RetailSearchLandingScreen(
    categories: List<ProductCategoryDTO> = emptyList(),
    histories: List<ProductBrowsingHistoryDTO> = emptyList(),
    brands: List<ProductBrandDTO> = emptyList(),
    onBackClick: () -> Unit = {},
    onSearchSubmit: (String) -> Unit = {},
    onCategoryClick: (ProductCategoryDTO) -> Unit = {},
    onHistoryProductClick: (ProductBrowsingHistoryDTO) -> Unit = {},
    onBrandClick: (String) -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    var lastSubmittedSearchText by remember {
        mutableStateOf("")
    }

    val popularCategories = remember(categories) {
        categories
            .filter { category ->
                category.ProductCategoryId > 0 &&
                        category.CategoryName.isNotBlank() &&
                        category.CategoryLevel == 1
            }
            .distinctBy { category ->
                category.ProductCategoryId
            }
            .take(12)
    }

    val personalBrands = remember {
        listOf(
            "Nike",
            "Adidas",
            "Samsung",
            "Apple",
            "Zara",
            "Penti"
        )
    }

    LaunchedEffect(searchText) {
        val currentSearchText = searchText.trim()

        if (currentSearchText.length < 3) {
            return@LaunchedEffect
        }

        if (currentSearchText == lastSubmittedSearchText) {
            return@LaunchedEffect
        }

        delay(400)

        val latestSearchText = searchText.trim()

        if (
            latestSearchText.length >= 3 &&
            latestSearchText != lastSubmittedSearchText
        ) {
            lastSubmittedSearchText = latestSearchText

            onSearchSubmit(
                latestSearchText
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = onBackClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = BBLocalization.Current.Get(
                    key = "e4f653c3-8828-4934-aa3b-959cede38feb",
                    fallback = "Ürün, kategori veya marka ara"
                ),
                onSearchClick = {
                    val currentSearchText =
                        searchText.trim()

                    if (currentSearchText.length >= 3) {
                        lastSubmittedSearchText =
                            currentSearchText

                        onSearchSubmit(
                            currentSearchText
                        )
                    }
                },
                onClearClick = {
                    searchText = ""
                    lastSubmittedSearchText = ""
                },
                leadingAction =
                    RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick,
                requestFocusOnStart = true
            )
        },
        bottomBar = {
            RetailBottomNavigation(
                selectedItem =
                    RetailBottomNavigationItem.Home,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        RetailBottomNavigationItem.Home ->
                            onHomeClick()

                        RetailBottomNavigationItem.Menu ->
                            onMenuClick()

                        RetailBottomNavigationItem.ModeSwitch ->
                            onModeSwitchClick()

                        RetailBottomNavigationItem.Basket ->
                            onBasketClick()

                        RetailBottomNavigationItem.Account ->
                            onAccountClick()
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top =
                    innerPadding.calculateTopPadding() +
                            BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom =
                    innerPadding.calculateBottomPadding() +
                            BBSpacing.PageBottom
            ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.SectionGapCompact
                )
        ) {
            item {
                RetailSearchLandingSectionTitle(
                    title = "Popüler Aramalar"
                )
            }

            item {
                LazyRow(
                    modifier =
                        Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.Space2
                        ),
                    contentPadding =
                        PaddingValues(
                            end = BBSpacing.Space2
                        )
                ) {
                    items(
                        items = popularCategories,
                        key = { category ->
                            category.ProductCategoryId
                        }
                    ) { category ->
                        BbChip(
                            text =
                                category.CategoryName,
                            selected = false,
                            onClick = {
                                onCategoryClick(
                                    category
                                )
                            }
                        )
                    }
                }
            }

            item {
                RetailSearchDottedDivider()
            }

            item {
                RetailSearchLandingSectionTitle(
                    title = "Önceden Gezdiklerim"
                )
            }

            if (histories.isNotEmpty()) {
                item {
                    LazyRow(
                        modifier =
                            Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.spacedBy(
                                BBSpacing.Space2
                            ),
                        contentPadding =
                            PaddingValues(
                                end = BBSpacing.Space2
                            )
                    ) {
                        items(
                            items =
                                histories.take(12),
                            key = { history ->
                                history.BrowsingHistoryId
                            }
                        ) { history ->
                            RetailSearchHistoryCard(
                                history = history,
                                onClick = {
                                    onHistoryProductClick(
                                        history
                                    )
                                }
                            )
                        }
                    }
                }
            }

            item {
                RetailSearchDottedDivider()
            }

            item {
                RetailSearchLandingSectionTitle(
                    title = "Sana Özel Markalar"
                )
            }

            item {
                LazyRow(
                    modifier =
                        Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.Space3
                        ),
                    contentPadding =
                        PaddingValues(
                            end = BBSpacing.Space2
                        )
                ) {
                    items(
                        items = brands
                            .filter { brand ->
                                brand.BrandId > 0 &&
                                        brand.Brand.isNotBlank()
                            }
                            .sortedWith(
                                compareByDescending<ProductBrandDTO> {
                                    it.IsFeatured
                                }.thenBy {
                                    it.SortOrder
                                }.thenBy {
                                    it.Brand
                                }
                            )
                            .take(12),
                        key = { brand ->
                            brand.BrandId
                        }
                    ) { brand ->
                        RetailSearchBrandCircle(
                            brand = brand,
                            onClick = {
                                onBrandClick(
                                    brand.Brand
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RetailSearchLandingSectionTitle(
    title: String
) {
    Text(
        text = title,
        style =
            MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color =
            MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun RetailSearchDottedDivider() {
    val dividerColor =
        MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = dividerColor,
            start =
                androidx.compose.ui.geometry.Offset(
                    0f,
                    size.height / 2f
                ),
            end =
                androidx.compose.ui.geometry.Offset(
                    size.width,
                    size.height / 2f
                ),
            strokeWidth = 1.dp.toPx(),
            pathEffect =
                PathEffect.dashPathEffect(
                    floatArrayOf(
                        5.dp.toPx(),
                        5.dp.toPx()
                    )
                )
        )
    }
}

@Composable
private fun RetailSearchHistoryCard(
    history: ProductBrowsingHistoryDTO,
    onClick: () -> Unit
) {
    val imageUrl =
        ImageUrlResolver.Resolve(
            history.DefaultPicture
                ?: history.Picture
        )

    Surface(
        modifier = Modifier
            .width(142.dp)
            .clickable {
                onClick()
            },
        shape =
            MaterialTheme.shapes.medium,
        color =
            MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color =
                MaterialTheme
                    .colorScheme
                    .outlineVariant
        )
    ) {
        Column(
            modifier =
                Modifier.padding(
                    BBSpacing.Space2
                ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .background(
                        MaterialTheme
                            .colorScheme
                            .surfaceVariant,
                        MaterialTheme
                            .shapes
                            .medium
                    ),
                contentAlignment =
                    Alignment.Center
            ) {
                if (imageUrl.isNotBlank()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription =
                            history.ProductName,
                        modifier =
                            Modifier.fillMaxSize(),
                        contentScale =
                            ContentScale.Fit
                    )
                }
            }

            Text(
                text =
                    history.ProductName,
                style =
                    MaterialTheme
                        .typography
                        .labelMedium,
                fontWeight =
                    FontWeight.Medium,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurface,
                maxLines = 2,
                overflow =
                    TextOverflow.Ellipsis
            )

            if (history.Price > 0) {
                Text(
                    text =
                        "${history.CurrencySymbol}${history.Price}",
                    style =
                        MaterialTheme
                            .typography
                            .labelMedium,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurface
                )
            }
        }
    }
}

@Composable
private fun RetailSearchBrandCircle(
    brand: ProductBrandDTO,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(78.dp)
            .clickable {
                onClick()
            },
        shape =
            androidx.compose.foundation
                .shape.CircleShape,
        color =
            MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color =
                MaterialTheme
                    .colorScheme
                    .outlineVariant
        )
    ) {
        Box(
            modifier =
                Modifier.fillMaxSize(),
            contentAlignment =
                Alignment.Center
        ) {
            Text(
                text = brand.Brand,
                modifier =
                    Modifier.padding(
                        BBSpacing.Space2
                    ),
                style =
                    MaterialTheme
                        .typography
                        .labelMedium,
                fontWeight =
                    FontWeight.SemiBold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RetailSearchLandingScreenPreview() {
    BbTheme {
        RetailSearchLandingScreen()
    }
}
