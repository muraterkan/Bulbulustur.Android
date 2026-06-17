package com.bulbulustur.android.Application.Areas.b2b.Views.Product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductGrid
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun ProductListScreen(
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductDetailClick: () -> Unit = {}
) {
    val products = remember {
        getWholesaleProductListItems()
    }

    val categories = remember {
        listOf(
            "Tümü",
            "Ambalaj",
            "Makine",
            "Elektronik",
            "Tekstil",
            "Gıda"
        )
    }

    val sortOptions = remember {
        listOf(
            "Öne çıkan",
            "Min. sipariş",
            "Yeni tedarik",
            "Hızlı teklif"
        )
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("Tümü")
    }

    var selectedSortOption by remember {
        mutableStateOf("Öne çıkan")
    }

    val filteredProducts = remember(
        searchText,
        selectedCategory,
        selectedSortOption,
        products
    ) {
        val searchFilteredProducts = if (searchText.isBlank()) {
            products
        } else {
            products.filter { product ->
                product.name.contains(searchText, ignoreCase = true) ||
                        product.companyName.contains(searchText, ignoreCase = true) ||
                        product.categoryName.contains(searchText, ignoreCase = true)
            }
        }

        val categoryFilteredProducts = if (selectedCategory == "Tümü") {
            searchFilteredProducts
        } else {
            searchFilteredProducts.filter { product ->
                product.categoryName == selectedCategory
            }
        }

        when (selectedSortOption) {
            "Min. sipariş" -> categoryFilteredProducts.sortedBy { product ->
                product.minimumOrderValue
            }

            "Yeni tedarik" -> categoryFilteredProducts.filter { product ->
                product.isNew
            }

            "Hızlı teklif" -> categoryFilteredProducts.filter { product ->
                product.hasFastQuote
            }

            else -> categoryFilteredProducts
        }
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
                selectedItem = WholesaleBottomNavigationItem.Menu,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            BbSectionHeader(
                title = "Toptan Ürünler",
                subtitle = "Sektörlerden, tedarikçilerden ve teklif akışlarından ürün keşfet"
            )

            Spacer(
                modifier = Modifier.height(BBSpacing.Space2)
            )

            WholesaleProductListHorizontalFilters(
                items = categories,
                selectedItem = selectedCategory,
                onItemClick = {
                    selectedCategory = it
                }
            )

            Spacer(
                modifier = Modifier.height(BBSpacing.Space2)
            )

            WholesaleProductListHorizontalFilters(
                items = sortOptions,
                selectedItem = selectedSortOption,
                onItemClick = {
                    selectedSortOption = it
                }
            )

            Spacer(
                modifier = Modifier.height(BBSpacing.Space2)
            )

            WholesaleProductListResultHeader(
                productCount = filteredProducts.size,
                selectedCategory = selectedCategory
            )

            BbProductGrid(
                contentPadding = PaddingValues(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.Space3,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.PageBottom
                ),
                horizontalSpacing = BBSpacing.Space3,
                verticalSpacing = BBSpacing.Space4
            ) {
                items(
                    items = filteredProducts,
                    key = { product ->
                        product.id
                    }
                ) { product ->
                    WholesaleProductListCard(
                        product = product,
                        onClick = onProductDetailClick
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleProductListHorizontalFilters(
    items: List<String>,
    selectedItem: String,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BBSpacing.PageHorizontal),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        items.take(4).forEach { item ->
            BbChip(
                text = item,
                selected = selectedItem == item,
                onClick = {
                    onItemClick(item)
                }
            )
        }
    }
}

@Composable
private fun WholesaleProductListResultHeader(
    productCount: Int,
    selectedCategory: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal,
                top = BBSpacing.Space1,
                bottom = BBSpacing.Space1
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = if (selectedCategory == "Tümü") {
                    "Tüm toptan ürünler"
                } else {
                    selectedCategory
                },
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = BBColors.TextStrong
            )

            Text(
                text = "$productCount ürün grubu listeleniyor",
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextMuted
            )
        }

        Text(
            text = "Filtrele",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = BBColors.Primary
        )
    }
}

@Composable
private fun WholesaleProductListCard(
    product: WholesaleProductListItem,
    onClick: () -> Unit
) {
    BbCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            WholesaleProductImagePlaceholder(
                imageText = product.imageText,
                badgeText = product.badgeText
            )

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = BBColors.TextStrong,
                maxLines = 2
            )

            Text(
                text = product.companyName,
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextMuted,
                maxLines = 1
            )

            Text(
                text = product.priceText,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BBColors.Primary
            )

            Text(
                text = product.minimumOrderText,
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextMuted,
                maxLines = 1
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WholesaleProductSmallPill(
                    text = product.locationText
                )

                if (product.hasFastQuote) {
                    WholesaleProductSmallPill(
                        text = "Hızlı teklif"
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleProductImagePlaceholder(
    imageText: String,
    badgeText: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BBSpacing.Space16 + BBSpacing.Space8)
            .clip(RoundedCornerShape(BBRadius.lg))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Inventory2,
            contentDescription = null,
            tint = BBColors.TextMuted
        )

        Text(
            text = imageText,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = BBColors.TextMuted,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        if (badgeText.isNotBlank()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(BBSpacing.Space1)
                    .clip(RoundedCornerShape(BBRadius.pill))
                    .background(BBColors.Primary)
                    .padding(
                        horizontal = BBSpacing.Space2,
                        vertical = BBSpacing.Space1
                    )
            ) {
                Text(
                    text = badgeText,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = BBColors.TextStrong
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductSmallPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BBRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.TextMuted
        )
    }
}

@Immutable
data class WholesaleProductListItem(
    val id: Int,
    val name: String,
    val companyName: String,
    val categoryName: String,
    val priceText: String,
    val minimumOrderText: String,
    val minimumOrderValue: Int,
    val locationText: String,
    val badgeText: String,
    val imageText: String,
    val isNew: Boolean,
    val hasFastQuote: Boolean
)

private fun getWholesaleProductListItems(): List<WholesaleProductListItem> {
    return listOf(
        WholesaleProductListItem(
            id = 1,
            name = "Endüstriyel karton koli seti",
            companyName = "Anadolu Ambalaj",
            categoryName = "Ambalaj",
            priceText = "₺12,40 / adet",
            minimumOrderText = "Min. sipariş: 1.000 adet",
            minimumOrderValue = 1000,
            locationText = "İstanbul",
            badgeText = "Toptan",
            imageText = "W1",
            isNew = true,
            hasFastQuote = true
        ),
        WholesaleProductListItem(
            id = 2,
            name = "Paslanmaz makine yedek parçası",
            companyName = "Delta Makine",
            categoryName = "Makine",
            priceText = "Teklif iste",
            minimumOrderText = "Min. sipariş: 50 adet",
            minimumOrderValue = 50,
            locationText = "Konya",
            badgeText = "RFQ",
            imageText = "W2",
            isNew = true,
            hasFastQuote = true
        ),
        WholesaleProductListItem(
            id = 3,
            name = "Elektronik güç modülü",
            companyName = "Tekno Bileşen",
            categoryName = "Elektronik",
            priceText = "₺84,90 / adet",
            minimumOrderText = "Min. sipariş: 250 adet",
            minimumOrderValue = 250,
            locationText = "İzmir",
            badgeText = "Yeni",
            imageText = "W3",
            isNew = true,
            hasFastQuote = false
        ),
        WholesaleProductListItem(
            id = 4,
            name = "Toptan pamuklu kumaş rulosu",
            companyName = "Mira Tekstil",
            categoryName = "Tekstil",
            priceText = "₺68,00 / metre",
            minimumOrderText = "Min. sipariş: 500 metre",
            minimumOrderValue = 500,
            locationText = "Denizli",
            badgeText = "",
            imageText = "W4",
            isNew = false,
            hasFastQuote = true
        ),
        WholesaleProductListItem(
            id = 5,
            name = "Gıda ambalaj poşeti",
            companyName = "Ege Paket",
            categoryName = "Gıda",
            priceText = "₺0,92 / adet",
            minimumOrderText = "Min. sipariş: 5.000 adet",
            minimumOrderValue = 5000,
            locationText = "Manisa",
            badgeText = "Popüler",
            imageText = "W5",
            isNew = false,
            hasFastQuote = true
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    BbTheme {
        ProductListScreen()
    }
}
