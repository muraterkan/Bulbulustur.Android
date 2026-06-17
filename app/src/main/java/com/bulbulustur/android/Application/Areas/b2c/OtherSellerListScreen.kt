package com.bulbulustur.android.Application.Areas.b2c

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
        containerColor = BBColors.SurfaceMuted,
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
                .background(BBColors.SurfaceMuted)
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
                    title = "Satıcı seçenekleri",
                    description = "Aynı ürünü satan mağazaları fiyat, kargo ve puana göre karşılaştır."
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
        color = BBColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Primary.copy(alpha = 0.35f)
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
                    .background(BBColors.Primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BBColors.TextStrong
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
                    color = BBColors.TextStrong
                )

                Text(
                    text = product.variantText,
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextSubtle
                )

                Text(
                    text = "${product.sellerCount} satıcı listeleniyor",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BBColors.TextStrong
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
            title = "Hızlı filtre",
            description = "Satıcıları alışveriş önceliğine göre daralt."
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
                        .background(BBColors.SurfaceMuted),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = seller.logoText,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = BBColors.TextStrong
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
                            color = BBColors.TextStrong
                        )

                        if (seller.isVerified) {
                            OtherSellerVerifiedBadge()
                        }
                    }

                    Text(
                        text = "${seller.ratingText} puan · ${seller.cargoText}",
                        style = MaterialTheme.typography.bodySmall,
                        color = BBColors.TextMuted
                    )
                }

                Text(
                    text = "›",
                    style = MaterialTheme.typography.headlineSmall,
                    color = BBColors.TextMuted
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
                        color = BBColors.Primary
                    )

                    Text(
                        text = seller.stockText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BBColors.TextMuted
                    )
                }

                BbButton(
                    text = "Sepete ekle",
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
        color = BBColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Primary.copy(alpha = 0.35f)
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
            color = BBColors.TextStrong
        )
    }
}

@Composable
private fun OtherSellerInfoBadge(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = BBColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.TextMuted
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
            color = BBColors.TextStrong
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = BBColors.TextMuted
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
            name = "Kadın klasik sneaker ayakkabı",
            variantText = "Beyaz · 38 numara",
            imageText = "P1",
            sellerCount = 5
        ),
        filters = listOf(
            "Tümü",
            "En düşük fiyat",
            "Hızlı kargo",
            "Doğrulanmış",
            "Yüksek puan"
        ),
        sellers = listOf(
            RetailOtherSellerItem(
                id = 1,
                storeName = "Ortobella Store",
                logoText = "OS",
                ratingText = "4.8",
                cargoText = "Hızlı kargo",
                priceText = "₺899,90",
                stockText = "Stokta var",
                badgeText = "En uygun fiyat",
                isVerified = true,
                filterTags = listOf("En düşük fiyat", "Hızlı kargo", "Doğrulanmış", "Yüksek puan")
            ),
            RetailOtherSellerItem(
                id = 2,
                storeName = "Moda Nova",
                logoText = "MN",
                ratingText = "4.6",
                cargoText = "Standart kargo",
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
                cargoText = "Hızlı kargo",
                priceText = "₺949,90",
                stockText = "Son 3 ürün",
                badgeText = "Az stok",
                isVerified = false,
                filterTags = listOf("Hızlı kargo", "Yüksek puan")
            ),
            RetailOtherSellerItem(
                id = 4,
                storeName = "Sneaker House",
                logoText = "SH",
                ratingText = "4.4",
                cargoText = "Standart kargo",
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
                cargoText = "Hızlı kargo",
                priceText = "₺999,90",
                stockText = "Stokta var",
                badgeText = "Kargo avantajı",
                isVerified = true,
                filterTags = listOf("Hızlı kargo", "Doğrulanmış")
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
