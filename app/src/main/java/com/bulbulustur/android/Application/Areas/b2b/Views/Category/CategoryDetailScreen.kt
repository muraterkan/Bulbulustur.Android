package com.bulbulustur.android.Application.Areas.b2b.Views.Category

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
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.Inventory2
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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun CategoryDetailScreen(
    categoryId: Int = 1,
    onSubCategoryClick: (Int) -> Unit = {},
    onProductListClick: (Int) -> Unit = {},
    onCompanyListClick: (Int) -> Unit = {},
    onRfqCreateClick: (Int) -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    val category = remember(categoryId) {
        getWholesaleCategoryDetail(
            categoryId
        )
    }

    var searchText by remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                WholesaleCategoryDetailHeader(
                    category = category
                )
            }

            item {

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
                    title = "Alt kategoriler",
                    subtitle = "Bu sektör altındaki ana ürün grupları"
                )
            }

            items(
                items = category.subCategories,
                key = { subCategory ->
                    subCategory.categoryId
                }
            ) { subCategory ->
                WholesaleSubCategoryCard(
                    subCategory = subCategory,
                    onClick = {
                        onSubCategoryClick(subCategory.categoryId)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Popüler ürün grupları",
                    subtitle = "API sonrası gerçek talep ve liste verileriyle beslenecek"
                )
            }

            item {
                WholesalePopularProductGroups(
                    productGroups = category.popularProductGroups,
                    onProductListClick = {
                        onProductListClick(category.categoryId)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Sektör özeti",
                    subtitle = "Dummy Toptan kategori istatistikleri"
                )
            }

            item {
                WholesaleCategoryStats(
                    category = category
                )
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            WholesaleCategoryIconTitleRow(
                icon = category.icon,
                title = "Toptan Kategori"
            )

            Text(
                text = category.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = category.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
            ) {
                BbChip(
                    text = "${category.productCount} ürün grubu",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "${category.companyCount} firma",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "${category.rfqCount} teklif talebi",
                    selected = false,
                    onClick = {}
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
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
        ) {
            WholesaleCategoryActionCard(
                title = "Ürünleri Gör",
                description = "Toptan liste",
                icon = Icons.Outlined.Inventory2,
                modifier = Modifier.weight(1f),
                onClick = {
                    onProductListClick(categoryId)
                }
            )

            WholesaleCategoryActionCard(
                title = "Firmalar",
                description = "Firma Vitrinleri",
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f),
                onClick = {
                    onCompanyListClick(categoryId)
                }
            )
        }

        BbButton(
            text = "Bu kategoride teklif talebi oluştur",
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = subCategory.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = subCategory.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = subCategory.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${subCategory.productCount} ürün grubu",
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WholesalePopularProductGroups(
    productGroups: List<String>,
    onProductListClick: () -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
    ) {
        productGroups.forEach { productGroup ->
            BbChip(
                text = productGroup,
                selected = false,
                onClick = onProductListClick
            )
        }
    }
}

@Composable
private fun WholesaleCategoryStats(
    category: WholesaleCategoryDetail
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        WholesaleCategoryStatCard(
            title = "Ürün",
            value = category.productCount.toString(),
            modifier = Modifier.weight(1f)
        )

        WholesaleCategoryStatCard(
            title = "Firma",
            value = category.companyCount.toString(),
            modifier = Modifier.weight(1f)
        )

        WholesaleCategoryStatCard(
            title = "RFQ",
            value = category.rfqCount.toString(),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun WholesaleCategoryStatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.IconTextGap)
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
    val productCount: Int,
    val companyCount: Int,
    val rfqCount: Int,
    val icon: ImageVector,
    val subCategories: List<WholesaleSubCategoryItem>,
    val popularProductGroups: List<String>
)

data class WholesaleSubCategoryItem(
    val categoryId: Int,
    val name: String,
    val description: String,
    val productCount: Int,
    val icon: ImageVector
)

private fun getWholesaleCategoryDetail(categoryId: Int): WholesaleCategoryDetail {
    return WholesaleCategoryDetail(
        categoryId = categoryId,
        name = "Ambalaj ve Paketleme",
        description = "Koli, kutu, poşet, etiket, streç film ve endüstriyel ambalaj ürünleri için toptan kategori merkezi.",
        productCount = 128,
        companyCount = 42,
        rfqCount = 17,
        icon = Icons.Outlined.Category,
        subCategories = getWholesaleSubCategories(),
        popularProductGroups = getWholesalePopularProductGroups()
    )
}

private fun getWholesaleSubCategories(): List<WholesaleSubCategoryItem> {
    return listOf(
        WholesaleSubCategoryItem(
            categoryId = 101,
            name = "Koli ve Kutu",
            description = "E-ticaret, sanayi ve depolama için koli çeşitleri.",
            productCount = 36,
            icon = Icons.Outlined.Inventory2
        ),
        WholesaleSubCategoryItem(
            categoryId = 102,
            name = "Poşet ve Çanta",
            description = "Mağaza, kargo ve üretim süreçleri için poşet çözümleri.",
            productCount = 28,
            icon = Icons.Outlined.Business
        ),
        WholesaleSubCategoryItem(
            categoryId = 103,
            name = "Etiket ve Barkod",
            description = "Ürün, depo, sevkiyat ve fiyat etiketi çözümleri.",
            productCount = 21,
            icon = Icons.Outlined.Verified
        ),
        WholesaleSubCategoryItem(
            categoryId = 104,
            name = "Streç ve Koruyucu Ambalaj",
            description = "Paletleme, sarma ve taşıma güvenliği için ürünler.",
            productCount = 43,
            icon = Icons.Outlined.Factory
        )
    )
}

private fun getWholesalePopularProductGroups(): List<String> {
    return listOf(
        "E-ticaret kolisi",
        "Baskılı poşet",
        "Kargo etiketi",
        "Streç film",
        "Köpük ambalaj",
        "Barkod etiketi",
        "Kilitli poşet"
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryDetailScreenPreview() {
    BbTheme {
        CategoryDetailScreen()
    }
}
