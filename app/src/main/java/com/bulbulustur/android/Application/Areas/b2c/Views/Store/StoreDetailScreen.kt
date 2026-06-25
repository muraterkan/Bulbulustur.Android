package com.bulbulustur.android.Application.Areas.b2c.Views.Store

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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun StoreDetailScreen(
    storeId: Int = 1,
    onBackClick: () -> Unit = {},
    onProductClick: (RetailStoreProductItem) -> Unit = {},
    onCategoryClick: (String) -> Unit = {},
    onFollowClick: (RetailStoreDetail) -> Unit = {},
    onStoreListClick: () -> Unit = {},
    onAddToBasketClick: (RetailStoreProductItem) -> Unit = {},
    onFavoriteClick: (RetailStoreProductItem) -> Unit = {}
) {
    val store = remember(storeId) {
        getRetailStoreDetail(storeId)
    }

    var selectedCategory by remember {
        mutableStateOf("Tümü")
    }

    var isFollowing by remember {
        mutableStateOf(false)
    }

    val filteredProducts = remember(selectedCategory, store.products) {
        if (selectedCategory == "Tümü") {
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
                title = "MaĞaza Detayı",
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
                StoreOtherStoresCard(
                    onStoreListClick = onStoreListClick
                )
            }

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

            item {
                StoreDetailSectionTitle(
                    title = "${store.name} Ürünleri",
                    description = "MaĞazanın ürünlerini inceleyin, Favorilerinize ekleyin ve alışverişe devam edin."
                )
            }

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
                    "Takip Et"
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
                text = "Doğrulanmış",
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
            subtitle = "Ürün"
        )

        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.ratingText,
            subtitle = "Puan"
        )

        StoreInfoStatCard(
            modifier = Modifier.weight(1f),
            title = store.cargoText,
            subtitle = "Kargo"
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
                    text = "Satıcı Şirket Bilgileri",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${store.name} maĞazası, Bulbulustur store kayıtları üzerinden doğrulanmış maĞaza Vitrini olarak listelenir.",
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
                    text = "DiĞer Mağazalar",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Bulbulustury'daki diĞer perakende mağazalarını Keşfedin.",
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
            title = "Ürün Kategorileri",
            description = "MaĞaza kategorilerine göre ürünleri Keşfedin."
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
                    text = "Sepete Ekle",
                    icon = Icons.Outlined.ShoppingCart,
                    modifier = Modifier.weight(1f),
                    onClick = onAddToBasketClick
                )

                StoreSmallActionButton(
                    text = "Favori",
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

            Spacer(modifier = Modifier.width(BBSpacing.Space1))

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

private fun getRetailStoreDetail(
    storeId: Int
): RetailStoreDetail {
    return RetailStoreDetail(
        id = storeId,
        name = "Ortobella Store",
        logoText = "OS",
        shortDescription = "Ayakkabı ve günlük moda ürünleri",
        description = "Ortobella Store, seçili ayakkabı ve günlük kullanım Ürünlerini perakende alışveriş akışında sunan doğrulanmış maĞaza Vitrinidir.",
        productCount = 248,
        ratingText = "4.8",
        cargoText = "Hızlı",
        isVerified = true,
        categories = listOf(
            "Tümü",
            "Ayakkabı",
            "Kadın Giyim",
            "Çanta",
            "Aksesuar"
        ),
        products = listOf(
            RetailStoreProductItem(
                id = 1,
                name = "Kadın Klasik Sneaker Ayakkabı",
                categoryName = "Ayakkabı",
                priceText = "â‚º899,90",
                badgeText = "%20",
                imageText = "P1"
            ),
            RetailStoreProductItem(
                id = 2,
                name = "Rahat Taban Günlük Ayakkabı",
                categoryName = "Ayakkabı",
                priceText = "â‚º749,90",
                badgeText = "Yeni",
                imageText = "P2"
            ),
            RetailStoreProductItem(
                id = 3,
                name = "Günlük Kullanım Omuz Çantası",
                categoryName = "Çanta",
                priceText = "â‚º649,90",
                badgeText = "",
                imageText = "P3"
            ),
            RetailStoreProductItem(
                id = 4,
                name = "Basic Pamuklu Kadın Tişört",
                categoryName = "Kadın Giyim",
                priceText = "â‚º329,90",
                badgeText = "%15",
                imageText = "P4"
            ),
            RetailStoreProductItem(
                id = 5,
                name = "Yeni Sezon Kadın Spor Ayakkabı",
                categoryName = "Ayakkabı",
                priceText = "â‚º1.099,90",
                badgeText = "Yeni",
                imageText = "P5"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun StoreDetailScreenPreview() {
    BbTheme {
        StoreDetailScreen()
    }
}

