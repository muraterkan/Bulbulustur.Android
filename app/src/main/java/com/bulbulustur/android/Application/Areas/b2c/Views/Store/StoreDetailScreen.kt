package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.StoreDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun StoreDetailScreen(
    storeId: Int = 1,
    storeDetail: StoreDTO? = null,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onProductClick: (RetailStoreProductItem) -> Unit = {},
    onCategoryClick: (String) -> Unit = {},
    onFollowClick: (RetailStoreDetail) -> Unit = {},
    onStoreListClick: () -> Unit = {},
    onStoreProductListClick: () -> Unit = {},
    onAddToBasketClick: (RetailStoreProductItem) -> Unit = {},
    onFavoriteClick: (RetailStoreProductItem) -> Unit = {}
) {
    val store = remember(storeDetail) {
        storeDetail?.ToRetailStoreDetail()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(
                    key = "a97998ac-8e30-4f27-ba9c-f6be45243d04",
                    fallback = "Mağaza Detayı"
                ),
                onBackClick = onBackClick
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
            when {
                isLoading && store == null -> {
                    item {
                        StoreDetailStatusCard(
                            title = BBLocalization.Current.Get(
                                key = "f58ed75d-1b29-43cc-8eec-5ca92118d7dd",
                                fallback = "Mağaza bilgileri yükleniyor"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "e1f51cc1-c58d-4d80-80d2-bfeb1c7635c9",
                                fallback = "Mağaza bilgileri sunucudan alınıyor."
                            )
                        )
                    }
                }

                !errorMessage.isNullOrBlank() && store == null -> {
                    item {
                        StoreDetailStatusCard(
                            title = BBLocalization.Current.Get(
                                key = "c466e1d0-f104-4b5b-b0e3-b0d51ca833fe",
                                fallback = "Mağaza bilgileri alınamadı"
                            ),
                            description = errorMessage
                        )
                    }
                }

                store == null -> {
                    item {
                        StoreDetailStatusCard(
                            title = BBLocalization.Current.Get(
                                key = "9141e794-372f-4548-81a2-ef81b797a60c",
                                fallback = "Mağaza Bulunamadı"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "16f66cd8-6413-4f4e-b981-0bf04a4a9b69",
                                fallback = "Listelenecek Mağaza Bulunmuyor"
                            )
                        )
                    }
                }

                else -> {
                    item {
                        StoreDetailHero(
                            store = store
                        )
                    }

                    item {
                        StoreInfoStatSection(
                            store = store
                        )
                    }

                    item {
                        StoreProductsCallout(
                            storeName = store.name,
                            onClick = onStoreProductListClick
                        )
                    }

                    item {
                        StoreOtherStoresCard(
                            onStoreListClick = onStoreListClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StoreDetailStatusCard(
    title: String,
    description: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
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
private fun StoreDetailHero(
    store: RetailStoreDetail
) {
    var logoLoadFailed by remember(store.logoUrl) {
        mutableStateOf(false)
    }

    val showLogo =
        store.logoUrl.isNotBlank() &&
            !logoLoadFailed

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.CardPadding),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = BBRadius.XlShape,
                    color =
                        if (showLogo) {
                            MaterialTheme.colorScheme.surface
                        } else {
                            MaterialTheme.colorScheme.primary
                        },
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (showLogo) {
                            AsyncImage(
                                model = store.logoUrl,
                                contentDescription = store.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(BBSpacing.Space2),
                                onError = {
                                    logoLoadFailed = true
                                }
                            )
                        } else {
                            Text(
                                text = store.logoText,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = store.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2
                    )

                    if (store.shortDescription.isNotBlank()) {
                        Text(
                            text = store.shortDescription,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            if (
                store.description.isNotBlank() &&
                store.description != store.shortDescription
            ) {
                Text(
                    text = store.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun StoreInfoStatSection(
    store: RetailStoreDetail
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.reviewCount.toString(),
            subtitle = "Değerlendirme"
        )

        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.ratingText ?: "-",
            subtitle = BBLocalization.Current.Get(
                key = "3b38b896-9858-47a3-a4c5-e6792e7eca82",
                fallback = "Puan"
            )
        )

        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.shippingText ?: "-",
            subtitle = BBLocalization.Current.Get(
                key = "8fa1207a-2a06-4bdb-936b-f7da848e0f72",
                fallback = "Kargo"
            )
        )
    }
}

@Composable
private fun StoreInfoStatCard(
    modifier: Modifier,
    title: String,
    subtitle: String
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StoreProductsCallout(
    storeName: String,
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
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "$storeName Ürünleri",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = BBLocalization.Current.Get(
                        key = "ea348216-7750-4545-8a78-1d70302e2158",
                        fallback = "Bu mağazaya ait ürünleri görüntüle."
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.SizeMd)
            )
        }
    }
}

@Composable
private fun StoreOtherStoresCard(
    onStoreListClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onStoreListClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(
                        key = "d911c86a-c16b-4b36-b7d8-32b8b80b2ac4",
                        fallback = "Diğer Mağazalar"
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Bulbulustur'daki diğer perakende mağazalarını keşfedin.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.SizeMd)
            )
        }
    }
}

@Immutable
data class RetailStoreDetail(
    val id: Int,
    val companyId: Int,
    val name: String,
    val logoText: String,
    val logoUrl: String,
    val shortDescription: String,
    val description: String,
    val reviewCount: Int,
    val ratingText: String?,
    val shippingText: String?
)

@Immutable
data class RetailStoreProductItem(
    val id: Int,
    val name: String = "",
    val categoryName: String = "",
    val priceText: String = "",
    val badgeText: String = "",
    val imageText: String = ""
)

private fun StoreDTO.ToRetailStoreDetail(): RetailStoreDetail {
    val resolvedName =
        StoreName.ifBlank {
            BBLocalization.Current.Get(
                key = "a4bd79dd-e7ee-4407-9e7d-00582840c43a",
                fallback = "Mağaza"
            )
        }

    return RetailStoreDetail(
        id = StoreId,
        companyId = CompanyId,
        name = resolvedName,
        logoText = resolvedName
            .trim()
            .take(2)
            .uppercase(),
        logoUrl = ImageUrlResolver.Resolve(Picture),
        shortDescription = StoreDescription.trim(),
        description = StoreDescription.trim(),
        reviewCount = ReviewNumber,
        ratingText =
            Rating
                .takeIf { rating ->
                    rating > 0.0
                }
                ?.toString(),
        shippingText =
            DefaultEstimatedShippingTime
                .takeIf { shippingTime ->
                    shippingTime > 0
                }
                ?.let { shippingTime ->
                    "$shippingTime gün"
                }
    )
}

@Preview(showBackground = true)
@Composable
private fun StoreDetailScreenPreview() {
    BbTheme {
        StoreDetailScreen()
    }
}
