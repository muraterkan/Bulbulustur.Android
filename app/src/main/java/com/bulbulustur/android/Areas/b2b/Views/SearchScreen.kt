package com.bulbulustur.android.Areas.b2b.Views

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
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Search
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
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbChip
import com.bulbulustur.android.wwwroot.components.BbSectionHeader
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

@Composable
fun SearchScreen(
    onProductSearchClick: (String) -> Unit = {},
    onCompanySearchClick: (String) -> Unit = {},
    onCategoryClick: (Int) -> Unit = {},
    onCompanyClick: (Int) -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onRfqCreateClick: (String) -> Unit = {}
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
                WholesaleSearchHeader()
            }

            item {

            }

            item {
                WholesaleSearchModeCards(
                    searchText = searchText,
                    onProductSearchClick = onProductSearchClick,
                    onCompanySearchClick = onCompanySearchClick,
                    onRfqCreateClick = onRfqCreateClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Popüler aramalar",
                    subtitle = "Toptan pazaryerinde sık kullanılan arama girişleri"
                )
            }

            item {
                WholesalePopularSearchChips(
                    onSearchClick = onProductSearchClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Önerilen kategoriler",
                    subtitle = "Aramaya başlamadan hızlı kategori girişi"
                )
            }

            items(
                items = getWholesaleSearchCategoryResults(),
                key = { category ->
                    category.categoryId
                }
            ) { category ->
                WholesaleSearchResultCard(
                    title = category.name,
                    description = category.description,
                    meta = "${category.productCount} ürün grubu",
                    icon = category.icon,
                    onClick = {
                        onCategoryClick(category.categoryId)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Öne çıkan firmalar",
                    subtitle = "Dummy firma sonuçları"
                )
            }

            items(
                items = getWholesaleSearchCompanyResults(),
                key = { company ->
                    company.companyId
                }
            ) { company ->
                WholesaleSearchResultCard(
                    title = company.name,
                    description = company.description,
                    meta = "${company.productCount} ürün • ${company.city}",
                    icon = company.icon,
                    onClick = {
                        onCompanyClick(company.companyId)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Öne çıkan ürünler",
                    subtitle = "API sonrası gerçek arama sonuçları burada listelenecek"
                )
            }

            items(
                items = getWholesaleSearchProductResults(),
                key = { product ->
                    product.productId
                }
            ) { product ->
                WholesaleSearchResultCard(
                    title = product.name,
                    description = product.description,
                    meta = "Min. ${product.minimumOrderQuantity} adet • ${product.companyName}",
                    icon = product.icon,
                    onClick = {
                        onProductClick(product.productId)
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
private fun WholesaleSearchHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            WholesaleSearchIconTitleRow(
                icon = Icons.Outlined.Search,
                title = "Toptan Arama"
            )

            Text(
                text = "Ürün, firma ve sektör bul",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Toptan akışında arama sadece ürün değil; firma, kategori ve teklif ihtiyacına da kapı açar.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleSearchModeCards(
    searchText: String,
    onProductSearchClick: (String) -> Unit,
    onCompanySearchClick: (String) -> Unit,
    onRfqCreateClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
        ) {
            WholesaleSearchModeCard(
                title = "Ürünlerde ara",
                description = "Toptan ürün listesi",
                icon = Icons.Outlined.Inventory2,
                modifier = Modifier.weight(1f),
                onClick = {
                    onProductSearchClick(searchText)
                }
            )

            WholesaleSearchModeCard(
                title = "Firmalarda ara",
                description = "Firma vitrini",
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f),
                onClick = {
                    onCompanySearchClick(searchText)
                }
            )
        }

        BbButton(
            text = "Aradığını bulamadın mı? Teklif talebi oluştur",
            onClick = {
                onRfqCreateClick(searchText)
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
private fun WholesaleSearchModeCard(
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WholesalePopularSearchChips(
    onSearchClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
    ) {
        getWholesalePopularSearchTerms().forEach { searchTerm ->
            BbChip(
                text = searchTerm,
                selected = false,
                onClick = {
                    onSearchClick(searchTerm)
                }
            )
        }
    }
}

@Composable
private fun WholesaleSearchResultCard(
    title: String,
    description: String,
    meta: String,
    icon: ImageVector,
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
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = meta,
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
private fun WholesaleSearchIconTitleRow(
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

data class WholesaleSearchCategoryResult(
    val categoryId: Int,
    val name: String,
    val description: String,
    val productCount: Int,
    val icon: ImageVector
)

data class WholesaleSearchCompanyResult(
    val companyId: Int,
    val name: String,
    val description: String,
    val city: String,
    val productCount: Int,
    val icon: ImageVector
)

data class WholesaleSearchProductResult(
    val productId: Int,
    val name: String,
    val description: String,
    val companyName: String,
    val minimumOrderQuantity: Int,
    val icon: ImageVector
)

private fun getWholesalePopularSearchTerms(): List<String> {
    return listOf(
        "Koli",
        "Streç film",
        "Baskılı poşet",
        "Kargo etiketi",
        "Endüstriyel makine",
        "Kuru gıda",
        "Medikal sarf",
        "Tekstil aksesuar"
    )
}

private fun getWholesaleSearchCategoryResults(): List<WholesaleSearchCategoryResult> {
    return listOf(
        WholesaleSearchCategoryResult(
            categoryId = 1,
            name = "Ambalaj ve Paketleme",
            description = "Koli, kutu, poşet, etiket ve koruyucu ambalaj ürünleri.",
            productCount = 128,
            icon = Icons.Outlined.Category
        ),
        WholesaleSearchCategoryResult(
            categoryId = 2,
            name = "Makine ve Endüstriyel Ekipman",
            description = "Üretim, bakım, yedek parça ve sanayi ekipmanları.",
            productCount = 96,
            icon = Icons.Outlined.Verified
        )
    )
}

private fun getWholesaleSearchCompanyResults(): List<WholesaleSearchCompanyResult> {
    return listOf(
        WholesaleSearchCompanyResult(
            companyId = 1,
            name = "Anadolu Ambalaj Sanayi",
            description = "Koli, kutu ve e-ticaret ambalaj ürünleri firması.",
            city = "İstanbul",
            productCount = 42,
            icon = Icons.Outlined.Business
        ),
        WholesaleSearchCompanyResult(
            companyId = 2,
            name = "Marmara Tedarik Merkezi",
            description = "Endüstriyel sarf ve depolama ürünleri firması.",
            city = "Kocaeli",
            productCount = 31,
            icon = Icons.Outlined.Storefront
        )
    )
}

private fun getWholesaleSearchProductResults(): List<WholesaleSearchProductResult> {
    return listOf(
        WholesaleSearchProductResult(
            productId = 1,
            name = "E-ticaret Kargo Kolisi",
            description = "Çoklu ölçü seçeneğiyle toptan koli ürünü.",
            companyName = "Anadolu Ambalaj",
            minimumOrderQuantity = 100,
            icon = Icons.Outlined.Inventory2
        ),
        WholesaleSearchProductResult(
            productId = 2,
            name = "Baskılı Mağaza Poşeti",
            description = "Logo baskılı, farklı ebat seçenekli poşet grubu.",
            companyName = "Marmara Tedarik",
            minimumOrderQuantity = 500,
            icon = Icons.Outlined.History
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    BbTheme {
        SearchScreen()
    }
}