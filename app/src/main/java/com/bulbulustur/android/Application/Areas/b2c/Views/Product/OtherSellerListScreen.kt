package com.bulbulustur.android.Application.Areas.b2c.Views.Product

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun OtherSellerListScreen(
    productId: Int = 1,
    onBackClick: () -> Unit = {},
    onSellerClick: (RetailOtherSellerItem) -> Unit = {},
    onAddToBasketClick: (RetailOtherSellerItem) -> Unit = {}
) {
    val screenData = remember(productId) {
        getRetailOtherSellerScreenData(productId)
    }

    var selectedFilter by remember {
        mutableStateOf("Tümü")
    }

    val filteredSellers = remember(selectedFilter, screenData.sellers) {
        if (selectedFilter == "Tümü") {
            screenData.sellers
        } else {
            screenData.sellers.filter {
                it.filterTags.contains(selectedFilter)
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Diğer Satıcılar",
                onBackClick = onBackClick,
                /*actionIcon = Icons.Outlined.Tune,
                actionContentDescription = "Filtreler",
                onActionClick = {
                    selectedFilter = "Tümü"
                }*/
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.SectionGapCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                OtherSellerProductSummary(
                    product = screenData.product
                )
            }

            item {
                OtherSellerFilterSection(
                    filters = screenData.filters,
                    selectedFilter = selectedFilter,
                    onFilterChange = {
                        selectedFilter = it
                    }
                )
            }

            item {
                OtherSellerSectionTitle(
                    title = BBLocalization.Current.Get(key = "3e8682e0-2701-4782-8adc-03c2a13a052d", fallback = "Satıcı seçenekleri"),
                    description = BBLocalization.Current.Get(key = "e5d9ffdf-0d2d-4bd4-b49d-5d708c471002", fallback = "Aynı ürünü satan Mağazaları fiyat, kargo ve puana göre karşılaştır.")
                )
            }

            items(
                items = filteredSellers,
                key = { seller -> seller.id }
            ) { seller ->
                OtherSellerCard(
                    seller = seller,
                    onSellerClick = {
                        onSellerClick(seller)
                    },
                    onAddToBasketClick = {
                        onAddToBasketClick(seller)
                    }
                )
            }
        }
    }
}

@Composable
private fun OtherSellerProductSummary(
    product: RetailOtherSellerProductSummary
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.CardPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BBSpacing.Space18)
                    .clip(BBRadius.XlShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = product.variantText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${product.sellerCount} satıcı listeleniyor",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OtherSellerFilterSection(
    filters: List<String>,
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        OtherSellerSectionTitle(
            title = BBLocalization.Current.Get(key = "825d9d14-3075-4a3a-8e4f-7eef8b04ee31", fallback = "Hızlı filtre"),
            description = BBLocalization.Current.Get(key = "57f8d517-6746-463a-80e5-16fad0b6a5a7", fallback = "Satıcıları alışveriş önceliğine göre daralt.")
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            filters.forEach { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = {
                        onFilterChange(filter)
                    },
                    label = {
                        Text(text = filter)
                    }
                )
            }
        }
    }
}

@Composable
private fun OtherSellerCard(
    seller: RetailOtherSellerItem,
    onSellerClick: () -> Unit,
    onAddToBasketClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSellerClick()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Box(
                    modifier = Modifier
                        .size(BBSpacing.Space13)
                        .clip(BBRadius.LgShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = seller.logoText,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        Text(
                            text = seller.storeName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        if (seller.isVerified) {
                            OtherSellerVerifiedBadge()
                        }
                    }

                    Text(
                        text = "${seller.ratingText} puan . ${seller.cargoText}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = "›",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = seller.priceText,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = seller.stockText,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BbButton(
                    text = "Sepete Ekle",
                    onClick = onAddToBasketClick,
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small
                )
            }

            if (seller.badgeText.isNotBlank()) {
                OtherSellerInfoBadge(
                    text = seller.badgeText
                )
            }
        }
    }
}

@Composable
private fun OtherSellerVerifiedBadge() {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        )
    ) {
        Text(
            text = "Doğrulanmış",
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun OtherSellerInfoBadge(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OtherSellerSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class RetailOtherSellerScreenData(
    val product: RetailOtherSellerProductSummary,
    val filters: List<String>,
    val sellers: List<RetailOtherSellerItem>
)

data class RetailOtherSellerProductSummary(
    val id: Int,
    val name: String,
    val variantText: String,
    val imageText: String,
    val sellerCount: Int
)

data class RetailOtherSellerItem(
    val id: Int,
    val storeName: String,
    val logoText: String,
    val ratingText: String,
    val cargoText: String,
    val priceText: String,
    val stockText: String,
    val badgeText: String,
    val isVerified: Boolean,
    val filterTags: List<String>
)

private fun getRetailOtherSellerScreenData(
    productId: Int
): RetailOtherSellerScreenData {
    return RetailOtherSellerScreenData(
        product = RetailOtherSellerProductSummary(
            id = productId,
            name = BBLocalization.Current.Get(key = "cf2f4de0-711c-4308-a055-3ef7eb00d9c7", fallback = "Kadın klasik sneaker ayakkabı"),
            variantText = "Beyaz . 38 numara",
            imageText = "P1",
            sellerCount = 5
        ),
        filters = listOf(
            "Tümü",
            "En düşük fiyat",
            BBLocalization.Current.Get(key = "6d125a4c-1b22-4449-a82e-b4e51ececea9", fallback = "Hızlı kargo"),
            "Doğrulanmış",
            "Yüksek puan"
        ),
        sellers = listOf(
            RetailOtherSellerItem(
                id = 1,
                storeName = "Ortobella Store",
                logoText = "OS",
                ratingText = "4.8",
                cargoText = BBLocalization.Current.Get(key = "6d125a4c-1b22-4449-a82e-b4e51ececea9", fallback = "Hızlı kargo"),
                priceText = "₺899,90",
                stockText = "Stokta var",
                badgeText = BBLocalization.Current.Get(key = "9bb6694a-3e36-490d-9767-c6fbe2baec1c", fallback = "En uygun fiyat"),
                isVerified = true,
                filterTags = listOf("En düşük fiyat", BBLocalization.Current.Get(key = "6d125a4c-1b22-4449-a82e-b4e51ececea9", fallback = "Hızlı kargo"), "Doğrulanmış", "Yüksek puan")
            ),
            RetailOtherSellerItem(
                id = 2,
                storeName = "Moda Nova",
                logoText = "MN",
                ratingText = "4.6",
                cargoText = BBLocalization.Current.Get(key = "e905eb3f-e2ff-4923-9b1d-1c3e792e1e08", fallback = "Standart kargo"),
                priceText = "₺929,90",
                stockText = "Stokta var",
                badgeText = "",
                isVerified = true,
                filterTags = listOf("Doğrulanmış", "Yüksek puan")
            ),
            RetailOtherSellerItem(
                id = 3,
                storeName = "Urban Touch",
                logoText = "UT",
                ratingText = "4.7",
                cargoText = BBLocalization.Current.Get(key = "6d125a4c-1b22-4449-a82e-b4e51ececea9", fallback = "Hızlı kargo"),
                priceText = "₺949,90",
                stockText = BBLocalization.Current.Get(key = "fcabe8cc-c42a-4f13-96f2-56f26683a544", fallback = "Son 3 ürün"),
                badgeText = BBLocalization.Current.Get(key = "4df69315-e3c0-44ad-b7cf-e2bb62fe74ff", fallback = "Az stok"),
                isVerified = false,
                filterTags = listOf(BBLocalization.Current.Get(key = "6d125a4c-1b22-4449-a82e-b4e51ececea9", fallback = "Hızlı kargo"), "Yüksek puan")
            ),
            RetailOtherSellerItem(
                id = 4,
                storeName = "Sneaker House",
                logoText = "SH",
                ratingText = "4.4",
                cargoText = BBLocalization.Current.Get(key = "e905eb3f-e2ff-4923-9b1d-1c3e792e1e08", fallback = "Standart kargo"),
                priceText = "₺979,90",
                stockText = "Stokta var",
                badgeText = "",
                isVerified = false,
                filterTags = listOf()
            ),
            RetailOtherSellerItem(
                id = 5,
                storeName = "Ayakkabı Merkezi",
                logoText = "AM",
                ratingText = "4.5",
                cargoText = BBLocalization.Current.Get(key = "6d125a4c-1b22-4449-a82e-b4e51ececea9", fallback = "Hızlı kargo"),
                priceText = "₺999,90",
                stockText = "Stokta var",
                badgeText = BBLocalization.Current.Get(key = "6c32e211-70dd-4c50-8be3-d870c8a610d0", fallback = "Kargo avantajı"),
                isVerified = true,
                filterTags = listOf(BBLocalization.Current.Get(key = "6d125a4c-1b22-4449-a82e-b4e51ececea9", fallback = "Hızlı kargo"), "Doğrulanmış")
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun OtherSellerListScreenPreview() {
    BbTheme {
        OtherSellerListScreen()
    }
}

