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
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Storefront
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
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun CategoryListScreen(
    onCategoryClick: (Int) -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
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
                WholesaleCategoryListHeader()
            }

            item {
                BbSearchBar(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearchClick(it)
                    },
                    placeholder = "Toptan kategori veya sektör ara"
                )
            }

            item {
                BbSectionHeader(
                    title = "Hızlı sektörler",
                    subtitle = "En çok kullanılan toptan kategori girişleri"
                )
            }

            item {
                WholesaleCategoryQuickChips(
                    onCategoryClick = onCategoryClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Toptan kategoriler",
                    subtitle = "Dummy kategori ağacı, API sonrası gerçek veriyle beslenecek"
                )
            }

            items(
                items = getWholesaleCategories(),
                key = { category ->
                    category.categoryId
                }
            ) { category ->
                WholesaleCategoryCard(
                    category = category,
                    onClick = {
                        onCategoryClick(category.categoryId)
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
private fun WholesaleCategoryListHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            WholesaleCategoryIconTitleRow(
                icon = Icons.Outlined.Category,
                title = "Kategori Ağacı"
            )

            Text(
                text = "Toptan sektörleri keşfet",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Ürün grupları, firmalar ve teklif talepleri için doğru sektörden başla.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WholesaleCategoryQuickChips(
    onCategoryClick: (Int) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
    ) {
        getWholesaleQuickCategories().forEach { quickCategory ->
            BbChip(
                text = quickCategory.name,
                selected = false,
                onClick = {
                    onCategoryClick(quickCategory.categoryId)
                }
            )
        }
    }
}

@Composable
private fun WholesaleCategoryCard(
    category: WholesaleCategoryListItem,
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${category.productCount} ürün grubu • ${category.companyCount} firma",
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
    }
}

@Composable
private fun WholesaleCategoryIconTitleRow(
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

data class WholesaleCategoryListItem(
    val categoryId: Int,
    val name: String,
    val description: String,
    val productCount: Int,
    val companyCount: Int,
    val icon: ImageVector
)

data class WholesaleQuickCategory(
    val categoryId: Int,
    val name: String
)

private fun getWholesaleQuickCategories(): List<WholesaleQuickCategory> {
    return listOf(
        WholesaleQuickCategory(
            categoryId = 1,
            name = "Ambalaj"
        ),
        WholesaleQuickCategory(
            categoryId = 2,
            name = "Makine"
        ),
        WholesaleQuickCategory(
            categoryId = 3,
            name = "Gıda"
        ),
        WholesaleQuickCategory(
            categoryId = 4,
            name = "Tekstil"
        ),
        WholesaleQuickCategory(
            categoryId = 5,
            name = "Medikal"
        )
    )
}

private fun getWholesaleCategories(): List<WholesaleCategoryListItem> {
    return listOf(
        WholesaleCategoryListItem(
            categoryId = 1,
            name = "Ambalaj ve Paketleme",
            description = "Koli, kutu, poşet, etiket, streç ve endüstriyel ambalaj ürünleri.",
            productCount = 128,
            companyCount = 42,
            icon = Icons.Outlined.Inventory2
        ),
        WholesaleCategoryListItem(
            categoryId = 2,
            name = "Makine ve Endüstriyel Ekipman",
            description = "Üretim makineleri, yedek parçalar, teknik ekipmanlar ve sanayi çözümleri.",
            productCount = 96,
            companyCount = 31,
            icon = Icons.Outlined.Factory
        ),
        WholesaleCategoryListItem(
            categoryId = 3,
            name = "Gıda ve İçecek",
            description = "Toptan gıda, içecek, hammadde, kuru gıda ve üretici ürünleri.",
            productCount = 214,
            companyCount = 58,
            icon = Icons.Outlined.Storefront
        ),
        WholesaleCategoryListItem(
            categoryId = 4,
            name = "Lojistik ve Depolama",
            description = "Taşıma, depolama, sevkiyat destek ürünleri ve operasyon çözümleri.",
            productCount = 64,
            companyCount = 19,
            icon = Icons.Outlined.LocalShipping
        ),
        WholesaleCategoryListItem(
            categoryId = 5,
            name = "Doğrulanmış Firmalar",
            description = "Kurumsal profili güçlü, güven odaklı firma kategorileri.",
            productCount = 87,
            companyCount = 26,
            icon = Icons.Outlined.Verified
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryListScreenPreview() {
    BbTheme {
        CategoryListScreen()
    }
}