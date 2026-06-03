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
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSearchBar
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun WholesaleHomeScreen(
    onSearchClick: (String) -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductListClick: () -> Unit = {},
    onCompanyListClick: () -> Unit = {},
    onRfqListClick: () -> Unit = {},
    onRfqCreateClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
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
                WholesaleHomeHero()
            }

            item {
                BbSearchBar(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearchClick(it)
                    },
                    placeholder = "Toptan ürün, firma veya kategori ara"
                )
            }

            item {
                WholesaleQuickActions(
                    onCategoryClick = onCategoryClick,
                    onProductListClick = onProductListClick,
                    onCompanyListClick = onCompanyListClick,
                    onRfqListClick = onRfqListClick,
                    onRfqCreateClick = onRfqCreateClick,
                    onFavoriteClick = onFavoriteClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Toptan keşif",
                    subtitle = "Ürün, firma ve teklif akışına hızlı giriş"
                )
            }

            items(
                items = getWholesaleDiscoveryItems(),
                key = { discoveryItem ->
                    discoveryItem.title
                }
            ) { discoveryItem ->
                WholesaleDiscoveryCard(
                    item = discoveryItem,
                    onClick = discoveryItem.resolveClick(
                        onCategoryClick = onCategoryClick,
                        onProductListClick = onProductListClick,
                        onCompanyListClick = onCompanyListClick,
                        onRfqCreateClick = onRfqCreateClick
                    )
                )
            }

            item {
                BbSectionHeader(
                    title = "Öne çıkan sektörler",
                    subtitle = "Dummy veri, API bağlantısından sonra gerçek kategori ağacı beslenecek"
                )
            }

            item {
                WholesaleSectorChips()
            }

            item {
                Spacer(modifier = Modifier.height(BbSpacing.Space4))
            }
        }
    }
}

@Composable
private fun WholesaleHomeHero() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            WholesaleIconTitleRow(
                icon = Icons.Outlined.Verified,
                title = "Toptan"
            )

            Text(
                text = "Bulbulustur Toptan Pazaryeri",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Üreticiler, firmalar, toptan ürünler ve teklif talepleri tek akışta.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleQuickActions(
    onCategoryClick: () -> Unit,
    onProductListClick: () -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqListClick: () -> Unit,
    onRfqCreateClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
        ) {
            WholesaleActionCard(
                title = "Kategoriler",
                description = "Sektör ağacı",
                icon = Icons.Outlined.Category,
                modifier = Modifier.weight(1f),
                onClick = onCategoryClick
            )

            WholesaleActionCard(
                title = "Ürünler",
                description = "Toptan liste",
                icon = Icons.Outlined.Inventory2,
                modifier = Modifier.weight(1f),
                onClick = onProductListClick
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
        ) {
            WholesaleActionCard(
                title = "Firmalar",
                description = "Firma vitrinleri",
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f),
                onClick = onCompanyListClick
            )

            WholesaleActionCard(
                title = "Teklifler",
                description = "RFQ listesi",
                icon = Icons.Outlined.RequestQuote,
                modifier = Modifier.weight(1f),
                onClick = onRfqListClick
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
        ) {
            WholesaleActionCard(
                title = "Teklif İste",
                description = "RFQ oluştur",
                icon = Icons.Outlined.RequestQuote,
                modifier = Modifier.weight(1f),
                onClick = onRfqCreateClick
            )

            WholesaleActionCard(
                title = "Favoriler",
                description = "Toptan kayıtlar",
                icon = Icons.Outlined.FavoriteBorder,
                modifier = Modifier.weight(1f),
                onClick = onFavoriteClick
            )
        }
    }
}

@Composable
private fun WholesaleActionCard(
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

@Composable
private fun WholesaleDiscoveryCard(
    item: WholesaleDiscoveryItem,
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
                imageVector = item.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WholesaleSectorChips() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
    ) {
        getWholesaleSectorNames().forEach { sectorName ->
            BbChip(
                text = sectorName,
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
private fun WholesaleIconTitleRow(
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

private data class WholesaleDiscoveryItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val target: WholesaleDiscoveryTarget
) {
    fun resolveClick(
        onCategoryClick: () -> Unit,
        onProductListClick: () -> Unit,
        onCompanyListClick: () -> Unit,
        onRfqCreateClick: () -> Unit
    ): () -> Unit {
        return when (target) {
            WholesaleDiscoveryTarget.Categories -> onCategoryClick
            WholesaleDiscoveryTarget.Products -> onProductListClick
            WholesaleDiscoveryTarget.Companies -> onCompanyListClick
            WholesaleDiscoveryTarget.RfqCreate -> onRfqCreateClick
        }
    }
}

private enum class WholesaleDiscoveryTarget {
    Categories,
    Products,
    Companies,
    RfqCreate
}

private fun getWholesaleDiscoveryItems(): List<WholesaleDiscoveryItem> {
    return listOf(
        WholesaleDiscoveryItem(
            title = "Toptan ürünleri keşfet",
            description = "Minimum sipariş, firma ve ürün gruplarını listele.",
            icon = Icons.Outlined.Inventory2,
            target = WholesaleDiscoveryTarget.Products
        ),
        WholesaleDiscoveryItem(
            title = "Firma ağına gir",
            description = "Doğrulanmış firmaları ve firma vitrinlerini incele.",
            icon = Icons.Outlined.Business,
            target = WholesaleDiscoveryTarget.Companies
        ),
        WholesaleDiscoveryItem(
            title = "Teklif talebi oluştur",
            description = "İhtiyacını yaz, uygun firmalardan teklif topla.",
            icon = Icons.Outlined.RequestQuote,
            target = WholesaleDiscoveryTarget.RfqCreate
        ),
        WholesaleDiscoveryItem(
            title = "Sektör kategorilerini gez",
            description = "Toptan kategori ağacı üzerinden hızlı yönlen.",
            icon = Icons.Outlined.Category,
            target = WholesaleDiscoveryTarget.Categories
        )
    )
}

private fun getWholesaleSectorNames(): List<String> {
    return listOf(
        "Ambalaj",
        "Makine",
        "Gıda",
        "Tekstil",
        "Medikal",
        "Kimya",
        "Elektrik",
        "Yapı",
        "Otomotiv",
        "Mobilya"
    )
}

@Preview(showBackground = true)
@Composable
private fun WholesaleHomeScreenPreview() {
    BbTheme {
        WholesaleHomeScreen()
    }
}