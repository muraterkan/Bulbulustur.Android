package com.bulbulustur.android.Application.Areas.b2c.Views.Product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import java.text.NumberFormat
import java.util.Locale

@Composable
fun OtherSellerListScreen(
    productId: Int,
    sellers: List<ProductVariantDTO>,
    isLoading: Boolean,
    onBackClick: () -> Unit = {},
    onSellerClick: (ProductVariantDTO) -> Unit = {},
    onAddToBasketClick: (ProductVariantDTO) -> Unit = {}
) {
    var selectedSort by remember {
        mutableStateOf(OtherSellerSort.Price)
    }

    val sortedSellers =
        remember(
            sellers,
            selectedSort
        ) {
            when (selectedSort) {
                OtherSellerSort.Price ->
                    sellers.sortedBy {
                        it.Price
                    }

                OtherSellerSort.Rating ->
                    sellers.sortedByDescending {
                        it.Rating
                    }

                OtherSellerSort.Default ->
                    sellers
            }
        }

    Scaffold(
        containerColor =
            MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title =
                    BBLocalization.Current.Get(
                        key = "b1b211c9-7273-40bf-af98-02456ff7666c",
                        fallback = "Diğer Satıcılar"
                    ),
                onBackClick =
                    onBackClick
            )
        }
    ) { innerPadding ->

        when {
            isLoading -> {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant
                            )
                            .padding(innerPadding)
                            .navigationBarsPadding(),
                    verticalArrangement =
                        Arrangement.Center,
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            sellers.isEmpty() -> {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant
                            )
                            .padding(innerPadding)
                            .padding(
                                horizontal =
                                    BBSpacing.PageHorizontal
                            )
                            .navigationBarsPadding(),
                    verticalArrangement =
                        Arrangement.Center,
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {
                    BbCard(
                        modifier =
                            Modifier.fillMaxWidth(),
                        variant =
                            BbCardVariant.Outlined,
                        padding =
                            BbCardPadding.Medium
                    ) {
                        Column(
                            modifier =
                                Modifier.fillMaxWidth(),
                            verticalArrangement =
                                Arrangement.spacedBy(
                                    BBSpacing.Space2
                                )
                        ) {
                            Text(
                                text =
                                    BBLocalization.Current.Get(
                                        key = "5cc5728c-3160-453f-9a5a-0376e2bf9021",
                                        fallback = "Başka satıcı bulunamadı"
                                    ),
                                style =
                                    MaterialTheme.typography.titleMedium,
                                fontWeight =
                                    FontWeight.Bold,
                                color =
                                    MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                text =
                                    BBLocalization.Current.Get(
                                        key = "177784c9-c4b8-4e67-bc41-3393a214e4ba",
                                        fallback = "Bu ürün varyantı için şu anda başka bir satıcı bulunmuyor."
                                    ),
                                style =
                                    MaterialTheme.typography.bodyMedium,
                                color =
                                    MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant
                            )
                            .padding(innerPadding)
                            .navigationBarsPadding(),
                    contentPadding =
                        PaddingValues(
                            start =
                                BBSpacing.PageHorizontal,
                            top =
                                BBSpacing.SectionGapCompact,
                            end =
                                BBSpacing.PageHorizontal,
                            bottom =
                                BBSpacing.PageBottom
                        ),
                    verticalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.CardGap
                        )
                ) {
                    item {
                        OtherSellerSummary(
                            sellerCount =
                                sellers.size
                        )
                    }

                    item {
                        OtherSellerSortSection(
                            selectedSort =
                                selectedSort,
                            onSortChange = {
                                selectedSort =
                                    it
                            }
                        )
                    }

                    item {
                        OtherSellerSectionTitle(
                            title =
                                BBLocalization.Current.Get(
                                    key = "3e8682e0-2701-4782-8adc-03c2a13a052d",
                                    fallback = "Satıcı seçenekleri"
                                ),
                            description =
                                BBLocalization.Current.Get(
                                    key = "e5d9ffdf-0d2d-4bd4-b49d-5d708c471002",
                                    fallback = "Aynı ürünü satan mağazaları fiyat ve puana göre karşılaştır."
                                )
                        )
                    }

                    items(
                        items =
                            sortedSellers,
                        key = { seller ->
                            seller.StoreId
                        }
                    ) { seller ->
                        OtherSellerCard(
                            seller =
                                seller,
                            onSellerClick = {
                                onSellerClick(
                                    seller
                                )
                            },
                            onAddToBasketClick = {
                                onAddToBasketClick(
                                    seller
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
private fun OtherSellerSummary(
    sellerCount: Int
) {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            BBRadius.XxlShape,
        color =
            MaterialTheme.colorScheme.primaryContainer,
        border =
            BorderStroke(
                width = 1.dp,
                color =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.35f
                    )
            )
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        BBSpacing.CardPadding
                    ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space1
                )
        ) {
            Text(
                text =
                    BBLocalization.Current.Get(
                        key = "b1b211c9-7273-40bf-af98-02456ff7666c",
                        fallback = "Diğer Satıcılar"
                    ),
                style =
                    MaterialTheme.typography.titleMedium,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onSurface
            )

            Text(
                text =
                    "$sellerCount satıcı listeleniyor",
                style =
                    MaterialTheme.typography.labelMedium,
                fontWeight =
                    FontWeight.SemiBold,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OtherSellerSortSection(
    selectedSort: OtherSellerSort,
    onSortChange: (OtherSellerSort) -> Unit
) {
    Column(
        modifier =
            Modifier.fillMaxWidth(),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        OtherSellerSectionTitle(
            title =
                BBLocalization.Current.Get(
                    key = "825d9d14-3075-4a3a-8e4f-7eef8b04ee31",
                    fallback = "Sıralama"
                ),
            description =
                BBLocalization.Current.Get(
                    key = "57f8d517-6746-463a-80e5-16fad0b6a5a7",
                    fallback = "Satıcıları fiyat veya mağaza puanına göre sırala."
                )
        )

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {
            FilterChip(
                selected =
                    selectedSort ==
                            OtherSellerSort.Price,
                onClick = {
                    onSortChange(
                        OtherSellerSort.Price
                    )
                },
                label = {
                    Text(
                        text =
                            BBLocalization.Current.Get(
                                key = "b6fdbcb1-d3d9-4b0e-b4da-9f7c427d89aa",
                                fallback = "En düşük fiyat"
                            )
                    )
                }
            )

            FilterChip(
                selected =
                    selectedSort ==
                            OtherSellerSort.Rating,
                onClick = {
                    onSortChange(
                        OtherSellerSort.Rating
                    )
                },
                label = {
                    Text(
                        text =
                            BBLocalization.Current.Get(
                                key = "d1f63ea5-7c48-4767-a74f-2e7b6efdf474",
                                fallback = "Mağaza puanı"
                            )
                    )
                }
            )

            FilterChip(
                selected =
                    selectedSort ==
                            OtherSellerSort.Default,
                onClick = {
                    onSortChange(
                        OtherSellerSort.Default
                    )
                },
                label = {
                    Text(
                        text =
                            BBLocalization.Current.Get(
                                key = "7fac1179-ab8e-4bb8-9ca0-92369db1597e",
                                fallback = "Varsayılan"
                            )
                    )
                }
            )
        }
    }
}

@Composable
private fun OtherSellerCard(
    seller: ProductVariantDTO,
    onSellerClick: () -> Unit,
    onAddToBasketClick: () -> Unit
) {
    BbCard(
        modifier =
            Modifier.fillMaxWidth(),
        variant =
            BbCardVariant.Outlined,
        padding =
            BbCardPadding.Medium
    ) {
        Column(
            modifier =
                Modifier.fillMaxWidth(),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space4
                )
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSellerClick()
                        },
                verticalAlignment =
                    Alignment.CenterVertically,
                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space3
                    )
            ) {
                OtherSellerLogo(
                    storeName =
                        seller.Store,
                    storeLogo =
                        seller.StoreLogo
                )

                Column(
                    modifier =
                        Modifier.weight(1f),
                    verticalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.Space1
                        )
                ) {
                    Text(
                        text =
                            seller.Store.ifBlank {
                                BBLocalization.Current.Get(
                                    key = "37890c03-0274-4439-9a6b-78cdba0cea05",
                                    fallback = "Mağaza"
                                )
                            },
                        style =
                            MaterialTheme.typography.titleMedium,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text =
                            if (seller.Rating > 0.0) {
                                "${formatRating(seller.Rating)} puan"
                            } else {
                                "Henüz puan yok"
                            },
                        style =
                            MaterialTheme.typography.bodySmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = "›",
                    style =
                        MaterialTheme.typography.headlineSmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.Bottom,
                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space3
                    )
            ) {
                Column(
                    modifier =
                        Modifier.weight(1f),
                    verticalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.Space1
                        )
                ) {
                    Text(
                        text =
                            formatPrice(
                                price =
                                    seller.Price,
                                currencySymbol =
                                    seller.CurrencySymbol
                            ),
                        style =
                            MaterialTheme.typography.titleLarge,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text =
                            if (seller.Stock > 0) {
                                "${seller.Stock} adet stokta"
                            } else {
                                "Stokta yok"
                            },
                        style =
                            MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BbButton(
                    text =
                        BBLocalization.Current.Get(
                            key = "65c77baa-8ec7-4650-b0b2-3cad0939b6d9",
                            fallback = "Sepete Ekle"
                        ),
                    onClick =
                        onAddToBasketClick,
                    variant =
                        BbButtonVariant.Primary,
                    size =
                        BbButtonSize.Small
                )
            }
        }
    }
}

@Composable
private fun OtherSellerSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier =
            Modifier.fillMaxWidth(),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space1
            )
    ) {
        Text(
            text =
                title,
            style =
                MaterialTheme.typography.titleMedium,
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onSurface
        )

        Text(
            text =
                description,
            style =
                MaterialTheme.typography.bodySmall,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun getStoreInitials(
    storeName: String
): String {
    if (storeName.isBlank()) {
        return "?"
    }

    val words =
        storeName
            .trim()
            .split(" ")
            .filter {
                it.isNotBlank()
            }

    return when {
        words.size >= 2 ->
            (
                    words[0]
                        .take(1) +
                            words[1]
                                .take(1)
                    ).uppercase()

        else ->
            storeName
                .trim()
                .take(2)
                .uppercase()
    }
}

private fun formatPrice(
    price: Double,
    currencySymbol: String
): String {
    val formatter =
        NumberFormat.getNumberInstance(
            Locale("tr", "TR")
        )

    formatter.minimumFractionDigits = 2
    formatter.maximumFractionDigits = 2

    val symbol =
        currencySymbol.ifBlank {
            "₺"
        }

    return "$symbol${formatter.format(price)}"
}

private fun formatRating(
    rating: Double
): String {
    return String.format(
        Locale("tr", "TR"),
        "%.1f",
        rating
    )
}

@Composable
private fun OtherSellerLogo(
    storeName: String,
    storeLogo: String
) {
    val logoUrl =
        ImageUrlResolver.Resolve(
            imagePath =
                storeLogo
        )

    var logoLoadFailed by
    remember(
        logoUrl
    ) {
        mutableStateOf(
            false
        )
    }

    Surface(
        modifier =
            Modifier.size(
                BBSpacing.Space13
            ),
        shape =
            BBRadius.LgShape,
        color =
            MaterialTheme.colorScheme.surfaceVariant
    ) {
        if (
            logoUrl.isNotBlank() &&
            !logoLoadFailed
        ) {
            AsyncImage(
                model =
                    logoUrl,
                contentDescription =
                    storeName,
                modifier =
                    Modifier.fillMaxSize(),
                contentScale =
                    ContentScale.Fit,
                onError = {
                    logoLoadFailed =
                        true
                }
            )
        } else {
            Column(
                modifier =
                    Modifier.fillMaxSize(),
                verticalArrangement =
                    Arrangement.Center,
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {
                Text(
                    text =
                        getStoreInitials(
                            storeName
                        ),
                    style =
                        MaterialTheme.typography.labelLarge,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

private enum class OtherSellerSort {
    Price,
    Rating,
    Default
}