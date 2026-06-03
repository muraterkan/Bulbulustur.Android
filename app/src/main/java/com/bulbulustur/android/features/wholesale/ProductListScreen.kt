package com.bulbulustur.android.features.wholesale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Verified
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
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSearchBar
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun ProductListScreen(
    categoryId: Int? = null,
    searchQuery: String? = null,
    onProductClick: (Int) -> Unit = {},
    onCompanyClick: (Int) -> Unit = {},
    onCategoryClick: (Int) -> Unit = {},
    onQuoteRequestClick: (Int) -> Unit = {},
    onFilterClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf(searchQuery.orEmpty())
    }

    val products = remember {
        getWholesaleProducts()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
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
                WholesaleProductListHeader(
                    categoryId = categoryId,
                    searchQuery = searchQuery
                )
            }

            item {
                BbSearchBar(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearchClick(it)
                    },
                    placeholder = "Toptan ürün ara"
                )
            }

            item {
                WholesaleProductListQuickFilters(
                    onCategoryClick = onCategoryClick,
                    onFilterClick = onFilterClick
                )
            }

            item {
                WholesaleProductListSummary(
                    totalProductCount = products.size,
                    onFilterClick = onFilterClick
                )
            }

            items(
                items = products,
                key = { product ->
                    product.productId
                }
            ) { product ->
                WholesaleProductCard(
                    product = product,
                    onProductClick = {
                        onProductClick(product.productId)
                    },
                    onCompanyClick = {
                        onCompanyClick(product.companyId)
                    },
                    onQuoteRequestClick = {
                        onQuoteRequestClick(product.productId)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(BbSpacing.Space4))
            }
        }
    }
}

@Composable
private fun WholesaleProductListHeader(
    categoryId: Int?,
    searchQuery: String?
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            WholesaleProductIconTitleRow(
                icon = Icons.Outlined.Inventory2,
                title = "Toptan Ürünler"
            )

            Text(
                text = getWholesaleProductHeaderTitle(
                    categoryId = categoryId,
                    searchQuery = searchQuery
                ),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Minimum sipariş, firma, kategori ve teklif akışıyla toptan ürün keşfi.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WholesaleProductListQuickFilters(
    onCategoryClick: (Int) -> Unit,
    onFilterClick: () -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
    ) {
        BbChip(
            text = "Tüm filtreler",
            selected = false,
            onClick = onFilterClick
        )

        getWholesaleProductFilterCategories().forEach { category ->
            BbChip(
                text = category.name,
                selected = false,
                onClick = {
                    onCategoryClick(category.categoryId)
                }
            )
        }
    }
}

@Composable
private fun WholesaleProductListSummary(
    totalProductCount: Int,
    onFilterClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onFilterClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.FilterList,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "$totalProductCount toptan ürün grubu gösteriliyor",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Kategori, minimum sipariş, şehir ve firma filtresi eklenecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WholesaleProductCard(
    product: WholesaleProductListItem,
    onProductClick: () -> Unit,
    onCompanyClick: () -> Unit,
    onQuoteRequestClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onProductClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Icon(
                    imageVector = product.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "Min. ${product.minimumOrderQuantity} adet • ${product.city}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
            ) {
                BbChip(
                    text = product.categoryName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = product.priceLabel,
                    selected = false,
                    onClick = {}
                )

                if (product.isVerifiedCompany) {
                    BbChip(
                        text = "Doğrulanmış firma",
                        selected = false,
                        onClick = {}
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
            ) {
                WholesaleProductSmallAction(
                    title = product.companyName,
                    icon = Icons.Outlined.Business,
                    modifier = Modifier.weight(1f),
                    onClick = onCompanyClick
                )

                WholesaleProductSmallAction(
                    title = "Teklif iste",
                    icon = Icons.Outlined.RequestQuote,
                    modifier = Modifier.weight(1f),
                    onClick = onQuoteRequestClick
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductSmallAction(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun WholesaleProductIconTitleRow(
    icon: ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
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

data class WholesaleProductListItem(
    val productId: Int,
    val companyId: Int,
    val name: String,
    val description: String,
    val companyName: String,
    val categoryName: String,
    val city: String,
    val minimumOrderQuantity: Int,
    val priceLabel: String,
    val isVerifiedCompany: Boolean,
    val icon: ImageVector
)

data class WholesaleProductFilterCategory(
    val categoryId: Int,
    val name: String
)

private fun getWholesaleProductHeaderTitle(
    categoryId: Int?,
    searchQuery: String?
): String {
    if (!searchQuery.isNullOrBlank()) {
        return "\"$searchQuery\" arama sonuçları"
    }

    if (categoryId != null) {
        return "Kategori ürünleri"
    }

    return "Toptan ürün listesi"
}

private fun getWholesaleProductFilterCategories(): List<WholesaleProductFilterCategory> {
    return listOf(
        WholesaleProductFilterCategory(
            categoryId = 1,
            name = "Ambalaj"
        ),
        WholesaleProductFilterCategory(
            categoryId = 2,
            name = "Makine"
        ),
        WholesaleProductFilterCategory(
            categoryId = 3,
            name = "Gıda"
        ),
        WholesaleProductFilterCategory(
            categoryId = 4,
            name = "Tekstil"
        ),
        WholesaleProductFilterCategory(
            categoryId = 5,
            name = "Medikal"
        )
    )
}

private fun getWholesaleProducts(): List<WholesaleProductListItem> {
    return listOf(
        WholesaleProductListItem(
            productId = 1,
            companyId = 101,
            name = "E-ticaret Kargo Kolisi",
            description = "Farklı ölçülerde, dayanıklı, toptan koli grubu.",
            companyName = "Anadolu Ambalaj",
            categoryName = "Ambalaj",
            city = "İstanbul",
            minimumOrderQuantity = 100,
            priceLabel = "Teklif ile fiyat",
            isVerifiedCompany = true,
            icon = Icons.Outlined.Inventory2
        ),
        WholesaleProductListItem(
            productId = 2,
            companyId = 102,
            name = "Baskılı Mağaza Poşeti",
            description = "Logo baskılı, farklı gramaj ve ebat seçenekli poşet.",
            companyName = "Marmara Tedarik",
            categoryName = "Ambalaj",
            city = "Kocaeli",
            minimumOrderQuantity = 500,
            priceLabel = "₺2,40+",
            isVerifiedCompany = true,
            icon = Icons.Outlined.LocalShipping
        ),
        WholesaleProductListItem(
            productId = 3,
            companyId = 103,
            name = "Endüstriyel Streç Film",
            description = "Paletleme ve depo operasyonları için güçlü sarım filmi.",
            companyName = "Ege Paketleme",
            categoryName = "Depolama",
            city = "İzmir",
            minimumOrderQuantity = 50,
            priceLabel = "₺145,00+",
            isVerifiedCompany = false,
            icon = Icons.Outlined.Category
        ),
        WholesaleProductListItem(
            productId = 4,
            companyId = 104,
            name = "Barkod ve Kargo Etiketi",
            description = "Termal baskıya uygun rulo etiket ürün grubu.",
            companyName = "Etiket Merkezi",
            categoryName = "Etiket",
            city = "Bursa",
            minimumOrderQuantity = 200,
            priceLabel = "₺38,00+",
            isVerifiedCompany = true,
            icon = Icons.Outlined.Verified
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