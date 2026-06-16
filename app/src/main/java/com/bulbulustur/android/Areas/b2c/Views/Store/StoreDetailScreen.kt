package com.bulbulustur.android.Areas.b2c.Views.Store

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
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

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
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Mağaza Detayı",
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
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.SectionGapCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
                    description = "Mağazanın ürünlerini inceleyin, favorilerinize ekleyin ve alışverişe devam edin."
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
            .height(BbSpacing.Space10)
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = if (isFollowing) {
            BbColors.SurfaceMuted
        } else {
            BbColors.Primary
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (isFollowing) {
                BbColors.Border
            } else {
                BbColors.Primary
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.SizeSm)
            )

            Text(
                text = if (isFollowing) {
                    "Takipte"
                } else {
                    "Takip Et"
                },
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.TextStrong,
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
        shape = BbRadius.XxlShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary.copy(alpha = 0.35f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.CardPadding),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space5)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = BbRadius.XlShape,
                    color = BbColors.Primary
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = store.logoText,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = BbColors.TextStrong
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                    ) {
                        Text(
                            text = store.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = BbColors.TextStrong,
                            maxLines = 1
                        )

                        if (store.isVerified) {
                            StoreVerifiedBadge()
                        }
                    }

                    Text(
                        text = store.shortDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BbColors.TextStrong
                    )
                }
            }

            Text(
                text = store.description,
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextStrong
            )
        }
    }
}

@Composable
private fun StoreVerifiedBadge() {
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.Surface.copy(alpha = 0.78f),
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = BbColors.Primary,
                modifier = Modifier.size(BbIcon.Size2Xs)
            )

            Text(
                text = "Doğrulanmış",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.Primary
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.TextMuted
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BbIcon.BoxMd),
                shape = BbRadius.LgShape,
                color = BbColors.Primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Verified,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Satıcı Şirket Bilgileri",
                    style = MaterialTheme.typography.titleSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${store.name} mağazası, Bulbulustur store kayıtları üzerinden doğrulanmış mağaza vitrini olarak listelenir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BbIcon.BoxMd),
                shape = BbRadius.LgShape,
                color = BbColors.PrimarySoft,
                border = BorderStroke(
                    width = 1.dp,
                    color = BbColors.Primary.copy(alpha = 0.35f)
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Diğer Mağazalar",
                    style = MaterialTheme.typography.titleSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Bulbulustur’daki diğer perakende mağazalarını keşfedin.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.size(BbIcon.SizeMd)
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        StoreDetailSectionTitle(
            title = "Ürün Kategorileri",
            description = "Mağaza kategorilerine göre ürünleri keşfedin."
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = if (selected) {
            BbColors.Primary
        } else {
            BbColors.Surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                BbColors.Primary
            } else {
                BbColors.Border
            }
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            ),
            style = MaterialTheme.typography.labelMedium,
            color = BbColors.TextStrong,
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(82.dp),
                    shape = BbRadius.XlShape,
                    color = BbColors.SurfaceMuted,
                    border = BorderStroke(
                        width = 1.dp,
                        color = BbColors.Border
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
                            color = BbColors.TextMuted
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = product.categoryName,
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.TextMuted,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.TextStrong
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                    ) {
                        Text(
                            text = product.priceText,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = BbColors.TextStrong
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
                    tint = BbColors.TextMuted,
                    modifier = Modifier.size(BbIcon.SizeMd)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
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
        shape = BbRadius.PillShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextStrong,
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
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.SizeSm)
            )

            Spacer(modifier = Modifier.width(BbSpacing.Space1))

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.TextStrong,
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextMuted
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
        description = "Ortobella Store, seçili ayakkabı ve günlük kullanım ürünlerini perakende alışveriş akışında sunan doğrulanmış mağaza vitrinidir.",
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
                priceText = "₺899,90",
                badgeText = "%20",
                imageText = "P1"
            ),
            RetailStoreProductItem(
                id = 2,
                name = "Rahat Taban Günlük Ayakkabı",
                categoryName = "Ayakkabı",
                priceText = "₺749,90",
                badgeText = "Yeni",
                imageText = "P2"
            ),
            RetailStoreProductItem(
                id = 3,
                name = "Günlük Kullanım Omuz Çantası",
                categoryName = "Çanta",
                priceText = "₺649,90",
                badgeText = "",
                imageText = "P3"
            ),
            RetailStoreProductItem(
                id = 4,
                name = "Basic Pamuklu Kadın Tişört",
                categoryName = "Kadın Giyim",
                priceText = "₺329,90",
                badgeText = "%15",
                imageText = "P4"
            ),
            RetailStoreProductItem(
                id = 5,
                name = "Yeni Sezon Kadın Spor Ayakkabı",
                categoryName = "Ayakkabı",
                priceText = "₺1.099,90",
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