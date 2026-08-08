package com.bulbulustur.android.Application.Areas.b2c.Views.Store

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.StoreDTO

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
    val store = remember(storeId, storeDetail) {
        storeDetail?.ToRetailStoreDetail() ?: getRetailStoreDetail(storeId)
    }

    var selectedCategory by remember {
        mutableStateOf(BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü"))
    }

    var isFollowing by remember {
        mutableStateOf(false)
    }

    val filteredProducts = remember(selectedCategory, store.products) {
        if (selectedCategory == BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü")) {
            store.products
        } else {
            store.products.filter {
                it.categoryName == selectedCategory
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "a97998ac-8e30-4f27-ba9c-f6be45243d04", fallback = "Mağaza Detayı"),
                onBackClick = onBackClick,
                actionContent = {
                    StoreFollowButton(
                        isFollowing = isFollowing,
                        onClick = {
                            isFollowing = !isFollowing
                            onFollowClick(store)
                        }
                    )
                }
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
            if (isLoading && storeDetail == null) {
                item {
                    StoreDetailStatusCard(
                        title = BBLocalization.Current.Get(key = "f58ed75d-1b29-43cc-8eec-5ca92118d7dd", fallback = "Mağaza bilgileri yükleniyor"),
                        description = BBLocalization.Current.Get(key = "e1f51cc1-c58d-4d80-80d2-bfeb1c7635c9", fallback = "Mağaza bilgileri sunucudan alınıyor.")
                    )
                }
            }

            if (!errorMessage.isNullOrBlank() && storeDetail == null) {
                item {
                    StoreDetailStatusCard(
                        title = BBLocalization.Current.Get(key = "c466e1d0-f104-4b5b-b0e3-b0d51ca833fe", fallback = "Mağaza bilgileri alınamadı"),
                        description = errorMessage
                    )
                }
            }

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
                StoreTrustInfoCard(
                    store = store
                )
            }

            item {
                StoreProductsCallout(
                    onClick = onStoreProductListClick
                )
            }

            item {
                StoreOtherStoresCard(
                    onStoreListClick = onStoreListClick
                )
            }

            if (store.categories.isNotEmpty()) {
                item {
                    StoreCategoryFilterSection(
                        categories = store.categories,
                        selectedCategory = selectedCategory,
                        onCategoryChange = {
                            selectedCategory = it
                            onCategoryClick(it)
                        }
                    )
                }
            }

            item {
                StoreDetailSectionTitle(
                    title = "${store.name} Ürünleri",
                    description = BBLocalization.Current.Get(key = "d16b2842-3a2f-445a-bbd0-07bddcb6cbd0", fallback = "Mağazanın ürünlerini inceleyin, Favorilerinize ekleyin ve alışverişe devam edin.")
                )
            }

            if (filteredProducts.isEmpty()) {
                item {
                    StoreDetailEmptyProductCard(
                        onStoreProductListClick = onStoreProductListClick
                    )
                }
            } else {
                items(
                    items = filteredProducts,
                    key = { product -> product.id }
                ) { product ->
                    StoreProductCard(
                        product = product,
                        onProductClick = {
                            onProductClick(product)
                        },
                        onAddToBasketClick = {
                            onAddToBasketClick(product)
                        },
                        onFavoriteClick = {
                            onFavoriteClick(product)
                        }
                    )
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
private fun StoreProductsCallout(
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "1d6f30bb-859a-436a-a205-92219421ccaa", fallback = "Mağazanın Tüm Ürünleri"),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = BBLocalization.Current.Get(key = "ea348216-7750-4545-8a78-1d70302e2158", fallback = "Bu mağazaya ait gerçek ürün listesini görüntüle."),
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
private fun StoreDetailEmptyProductCard(
    onStoreProductListClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onStoreProductListClick
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
                    text = BBLocalization.Current.Get(key = "d6dbe940-3d82-44c0-8244-759c555aac1e", fallback = "Ürün listesi ayrı ekranda"),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = BBLocalization.Current.Get(key = "e037c89a-d78b-4a68-8d81-6833a8718b6b", fallback = "Gerçek mağaza ürünlerini listelemek için dokunun."),
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
private fun StoreFollowButton(
    isFollowing: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(BBSpacing.Space10)
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = if (isFollowing) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            MaterialTheme.colorScheme.primary
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (isFollowing) {
                MaterialTheme.colorScheme.outlineVariant
            } else {
                MaterialTheme.colorScheme.primary
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Text(
                text = if (isFollowing) {
                    "Takipte"
                } else {
                    BBLocalization.Current.Get(key = "b7552375-6be4-4428-9cd0-6a70e3e8cc1d", fallback = "Takip Et")
                },
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun StoreDetailHero(
    store: RetailStoreDetail
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.CardPadding),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space5)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = BBRadius.XlShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = store.logoText,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
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
                            text = store.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1
                        )

                        if (store.isVerified) {
                            StoreVerifiedBadge()
                        }
                    }

                    Text(
                        text = store.shortDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = store.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun StoreVerifiedBadge() {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.78f),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(BBIcon.Size2Xs)
            )

            Text(
                text = BBLocalization.Current.Get(key = "c6a0ff62-8828-475f-b553-37effb42efe6", fallback = "Doğrulanmış"),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
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
            title = "${store.productCount}",
            subtitle = BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = "Ürün")
        )

        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.ratingText,
            subtitle = BBLocalization.Current.Get(key = "3b38b896-9858-47a3-a4c5-e6792e7eca82", fallback = "Puan")
        )

        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.cargoText,
            subtitle = BBLocalization.Current.Get(key = "8fa1207a-2a06-4bdb-936b-f7da848e0f72", fallback = "Kargo")
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
private fun StoreTrustInfoCard(
    store: RetailStoreDetail
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Verified,
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
                    text = BBLocalization.Current.Get(key = "5d3c17c2-d063-4757-9940-62331a540e23", fallback = "Satıcı Şirket Bilgileri"),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${store.name} mağazası, Bulbulustur store kayıtları üzerinden doğrulanmış mağaza Vitrini olarak listelenir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
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
                    text = BBLocalization.Current.Get(key = "d911c86a-c16b-4b36-b7d8-32b8b80b2ac4", fallback = "Diğer Mağazalar"),
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StoreCategoryFilterSection(
    categories: List<String>,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        StoreDetailSectionTitle(
            title = BBLocalization.Current.Get(key = "bc3a7952-0d83-44ca-a8fa-c5a4dac434b4", fallback = "Ürün Kategorileri"),
            description = BBLocalization.Current.Get(key = "cf6fa404-afd3-4ec6-b671-3067a0f1f49b", fallback = "Mağaza kategorilerine göre ürünleri keşfedin.")
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            categories.forEach { category ->
                StoreCategoryChip(
                    text = category,
                    selected = selectedCategory == category,
                    onClick = {
                        onCategoryChange(category)
                    }
                )
            }
        }
    }
}

@Composable
private fun StoreCategoryChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun StoreProductCard(
    product: RetailStoreProductItem,
    onProductClick: () -> Unit,
    onAddToBasketClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onProductClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(82.dp),
                    shape = BBRadius.XlShape,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = product.imageText,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = product.categoryName,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        Text(
                            text = product.priceText,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        if (product.badgeText.isNotBlank()) {
                            StoreProductBadge(
                                text = product.badgeText
                            )
                        }
                    }
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.SizeMd)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StoreSmallActionButton(
                    text = BBLocalization.Current.Get(key = "9a748489-8d57-4bc5-becc-0937717d80df", fallback = "Sepete Ekle"),
                    icon = Icons.Outlined.ShoppingCart,
                    modifier = Modifier.weight(1f),
                    onClick = onAddToBasketClick
                )

                StoreSmallActionButton(
                    text = BBLocalization.Current.Get(key = "f5008ba2-ccb2-4dfd-85bb-d3b7a84ffd14", fallback = "Favori"),
                    icon = Icons.Outlined.FavoriteBorder,
                    modifier = Modifier.weight(1f),
                    onClick = onFavoriteClick
                )
            }
        }
    }
}

@Composable
private fun StoreProductBadge(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun StoreSmallActionButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(42.dp)
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Spacer(
                modifier = Modifier.width(BBSpacing.Space1)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun StoreDetailSectionTitle(
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

@Immutable
data class RetailStoreDetail(
    val id: Int,
    val name: String,
    val logoText: String,
    val logoUrl: String,
    val shortDescription: String,
    val description: String,
    val productCount: Int,
    val ratingText: String,
    val cargoText: String,
    val isVerified: Boolean,
    val categories: List<String>,
    val products: List<RetailStoreProductItem>
)

@Immutable
data class RetailStoreProductItem(
    val id: Int,
    val name: String,
    val categoryName: String,
    val priceText: String,
    val badgeText: String,
    val imageText: String
)

private fun StoreDTO.ToRetailStoreDetail(): RetailStoreDetail {
    val resolvedName = StoreName.ifBlank {
        BBLocalization.Current.Get(key = "a4bd79dd-e7ee-4407-9e7d-00582840c43a", fallback = "Mağaza")
    }

    return RetailStoreDetail(
        id = StoreId,
        name = resolvedName,
        logoText = resolvedName.take(2).uppercase(),
        logoUrl = ResolveStoreLogoUrl(Picture),
        shortDescription = StoreDescription.ifBlank {
            BBLocalization.Current.Get(key = "7ef0bc22-35db-4470-a426-670c1408e856", fallback = "Mağaza vitrini")
        },
        description = StoreDescription.ifBlank {
            BBLocalization.Current.Get(key = "8e6ffde2-f883-41f3-aad0-b954b910a581", fallback = "Bu mağazaya ait ürünleri ve mağaza bilgilerini inceleyebilirsiniz.")
        },
        productCount = ReviewNumber,
        ratingText = if (Rating > 0.0) Rating.toString() else "-",
        cargoText = if (DefaultEstimatedShippingTime > 0) {
            "${DefaultEstimatedShippingTime} gün"
        } else {
            BBLocalization.Current.Get(key = "081fb0ca-4f68-4277-9ca0-028d1e9d147f", fallback = "Standart")
        },
        isVerified = CompanyId > 0 || StoreKey.isNotBlank(),
        categories = listOf(BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü")),
        products = emptyList()
    )
}

private fun getRetailStoreDetail(
    storeId: Int
): RetailStoreDetail {
    return RetailStoreDetail(
        id = storeId,
        name = "Ortobella Store",
        logoText = "OS",
        logoUrl = "",
        shortDescription = "Ayakkabı ve günlük moda ürünleri",
        description = "Ortobella Store, seçili ayakkabı ve günlük kullanım ürünlerini perakende alışveriş akışında sunan doğrulanmış mağaza vitrinidir.",
        productCount = 248,
        ratingText = "4.8",
        cargoText = BBLocalization.Current.Get(key = "e21d608b-5771-4e33-b57e-4aa1597fe6ed", fallback = "Hızlı"),
        isVerified = true,
        categories = listOf(
            BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü"),
            "Ayakkabı",
            BBLocalization.Current.Get(key = "f481d8fc-9de9-4a6b-870d-1918537ae795", fallback = "Kadın Giyim"),
            "Çanta",
            BBLocalization.Current.Get(key = "053ae8f0-d8f8-46b1-b062-bc65615ce7a1", fallback = "Aksesuar")
        ),
        products = listOf(
            RetailStoreProductItem(
                id = 1,
                name = BBLocalization.Current.Get(key = "eb8d15f2-e540-461d-bd3d-015033d93512", fallback = "Kadın Klasik Sneaker Ayakkabı"),
                categoryName = "Ayakkabı",
                priceText = "₺899,90",
                badgeText = "%20",
                imageText = "P1"
            ),
            RetailStoreProductItem(
                id = 2,
                name = BBLocalization.Current.Get(key = "362a7dca-0496-42b5-a62c-2f605d7befc8", fallback = "Rahat Taban Günlük Ayakkabı"),
                categoryName = "Ayakkabı",
                priceText = "₺749,90",
                badgeText = BBLocalization.Current.Get(key = "557ea0c9-948d-4e62-8ddc-948294a55b11", fallback = "Yeni"),
                imageText = "P2"
            ),
            RetailStoreProductItem(
                id = 3,
                name = BBLocalization.Current.Get(key = "a36cb9c0-b208-452a-ba5b-65604d0a9f19", fallback = "Günlük Kullanım Omuz Çantası"),
                categoryName = "Çanta",
                priceText = "₺649,90",
                badgeText = "",
                imageText = "P3"
            ),
            RetailStoreProductItem(
                id = 4,
                name = "Basic Pamuklu Kadın Tişört",
                categoryName = BBLocalization.Current.Get(key = "f481d8fc-9de9-4a6b-870d-1918537ae795", fallback = "Kadın Giyim"),
                priceText = "₺329,90",
                badgeText = "%15",
                imageText = "P4"
            ),
            RetailStoreProductItem(
                id = 5,
                name = "Yeni Sezon Kadın Spor Ayakkabı",
                categoryName = "Ayakkabı",
                priceText = "₺1.099,90",
                badgeText = BBLocalization.Current.Get(key = "557ea0c9-948d-4e62-8ddc-948294a55b11", fallback = "Yeni"),
                imageText = "P5"
            )
        )
    )
}

private fun ResolveStoreLogoUrl(picture: String): String {
    val normalizedPicture =
        picture.trim()

    if (normalizedPicture.isBlank()) {
        return ""
    }

    if (
        normalizedPicture.startsWith(
            "http://",
            ignoreCase = true
        ) ||
        normalizedPicture.startsWith(
            "https://",
            ignoreCase = true
        )
    ) {
        return normalizedPicture
    }

    return "https://www.bulbulustur.com/UploadedFiles/B2C/Stores/" +
            normalizedPicture.trimStart('/')
}

@Preview(showBackground = true)
@Composable
private fun StoreDetailScreenPreview() {
    BbTheme {
        StoreDetailScreen()
    }
}