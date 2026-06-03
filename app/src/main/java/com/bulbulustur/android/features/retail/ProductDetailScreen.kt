package com.bulbulustur.android.features.retail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbBottomNavigation
import com.bulbulustur.android.ui.components.BbBottomNavigationItem
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun ProductDetailScreen(
    productId: Int = 1,
    onBackClick: () -> Unit = {},
    onAddToBasketClick: (RetailProductDetailSelection) -> Unit = {},
    onBuyNowClick: (RetailProductDetailSelection) -> Unit = {},
    onStoreClick: (RetailProductDetailStore) -> Unit = {},
    onOtherSellerClick: () -> Unit = {},
    onSizeGuideClick: () -> Unit = {},
    onReviewClick: () -> Unit = {},
    onQuestionClick: () -> Unit = {},
    onBottomNavigationClick: (BbBottomNavigationItem) -> Unit = {}
) {
    val product = remember(productId) {
        getRetailProductDetail(productId)
    }

    var selectedImageIndex by remember {
        mutableIntStateOf(0)
    }

    var selectedColor by remember {
        mutableStateOf(product.colors.first())
    }

    var selectedSize by remember {
        mutableStateOf(product.sizes.first())
    }

    var quantity by remember {
        mutableIntStateOf(1)
    }

    val selection = RetailProductDetailSelection(
        productId = product.id,
        selectedColor = selectedColor,
        selectedSize = selectedSize,
        quantity = quantity
    )

    Scaffold(
        bottomBar = {
            ProductDetailBottomBar(
                priceText = product.priceText,
                onAddToBasketClick = {
                    onAddToBasketClick(selection)
                },
                onBuyNowClick = {
                    onBuyNowClick(selection)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ProductDetailTopBar(
                onBackClick = onBackClick
            )

            ProductDetailImageArea(
                product = product,
                selectedImageIndex = selectedImageIndex,
                onImageSelect = {
                    selectedImageIndex = it
                }
            )

            ProductDetailMainInfo(
                product = product
            )

            ProductDetailStoreCard(
                store = product.store,
                onStoreClick = {
                    onStoreClick(product.store)
                }
            )

            ProductDetailVariantSection(
                title = "Renk seçimi",
                subtitle = "Ürün rengini seç",
                items = product.colors,
                selectedItem = selectedColor,
                onItemClick = {
                    selectedColor = it
                }
            )

            ProductDetailVariantSection(
                title = "Beden seçimi",
                subtitle = "Bedenini seç veya ölçü rehberine bak",
                items = product.sizes,
                selectedItem = selectedSize,
                onItemClick = {
                    selectedSize = it
                },
                actionText = "Beden rehberi",
                onActionClick = onSizeGuideClick
            )

            ProductQuantitySection(
                quantity = quantity,
                onDecreaseClick = {
                    if (quantity > 1) {
                        quantity -= 1
                    }
                },
                onIncreaseClick = {
                    quantity += 1
                }
            )

            ProductDetailQuickActions(
                product = product,
                onOtherSellerClick = onOtherSellerClick,
                onReviewClick = onReviewClick,
                onQuestionClick = onQuestionClick
            )

            ProductDescriptionSection(
                description = product.description
            )

            ProductDetailBottomNavigationSpacer()
        }
    }
}

@Composable
private fun ProductDetailTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.md,
                top = BbSpacing.md,
                end = BbSpacing.md,
                bottom = BbSpacing.sm
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbSpacing.xl)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong
            )
        }

        Spacer(modifier = Modifier.width(BbSpacing.md))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Ürün detayı",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Perakende ürün bilgisi",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextStrong.copy(alpha = 0.62f)
            )
        }
    }
}

@Composable
private fun ProductDetailImageArea(
    product: RetailProductDetail,
    selectedImageIndex: Int,
    onImageSelect: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.padding(horizontal = BbSpacing.md)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BbSpacing.Space16 + BbSpacing.Space16 + BbSpacing.xl)
                    .clip(RoundedCornerShape(BbRadius.xl))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.images[selectedImageIndex],
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong.copy(alpha = 0.42f)
                )

                if (product.badgeText.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(BbSpacing.sm)
                            .clip(RoundedCornerShape(BbRadius.pill))
                            .background(BbColors.Success)
                            .padding(
                                horizontal = BbSpacing.md,
                                vertical = BbSpacing.xs
                            )
                    ) {
                        Text(
                            text = product.badgeText,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                product.images.forEachIndexed { index, imageText ->
                    ProductDetailThumbnail(
                        imageText = imageText,
                        isSelected = selectedImageIndex == index,
                        onClick = {
                            onImageSelect(index)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductDetailThumbnail(
    imageText: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) {
        BbColors.Success
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val contentColor = if (isSelected) {
        MaterialTheme.colorScheme.surface
    } else {
        BbColors.TextStrong.copy(alpha = 0.62f)
    }

    Box(
        modifier = Modifier
            .size(BbSpacing.xxl)
            .clip(RoundedCornerShape(BbRadius.md))
            .background(containerColor)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = imageText,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )
    }
}

@Composable
private fun ProductDetailMainInfo(
    product: RetailProductDetail
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.md,
            top = BbSpacing.md,
            end = BbSpacing.md
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Text(
                text = product.categoryName,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.Success
            )

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = product.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextStrong.copy(alpha = 0.70f)
            )

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = product.priceText,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Success
                )

                if (product.oldPriceText.isNotBlank()) {
                    Spacer(modifier = Modifier.width(BbSpacing.sm))

                    Text(
                        text = product.oldPriceText,
                        style = MaterialTheme.typography.bodySmall,
                        color = BbColors.TextStrong.copy(alpha = 0.46f)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                ProductDetailPill(
                    text = product.ratingText
                )

                ProductDetailPill(
                    text = product.cargoText
                )

                ProductDetailPill(
                    text = product.stockText
                )
            }
        }
    }
}

@Composable
private fun ProductDetailStoreCard(
    store: RetailProductDetailStore,
    onStoreClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .padding(
                start = BbSpacing.md,
                top = BbSpacing.md,
                end = BbSpacing.md
            )
            .clickable {
                onStoreClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.xxl)
                    .clip(RoundedCornerShape(BbRadius.md))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = store.logoText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }

            Spacer(modifier = Modifier.width(BbSpacing.md))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = store.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.TextStrong
                    )

                    if (store.isVerified) {
                        Spacer(modifier = Modifier.width(BbSpacing.sm))

                        ProductDetailPill(
                            text = "Doğrulanmış"
                        )
                    }
                }

                Text(
                    text = "${store.ratingText} puan · ${store.productCount} ürün",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextStrong.copy(alpha = 0.62f)
                )
            }

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong.copy(alpha = 0.52f)
            )
        }
    }
}

@Composable
private fun ProductDetailVariantSection(
    title: String,
    subtitle: String,
    items: List<String>,
    selectedItem: String,
    onItemClick: (String) -> Unit,
    actionText: String = "",
    onActionClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(
            start = BbSpacing.md,
            top = BbSpacing.md,
            end = BbSpacing.md
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BbSectionHeader(
                title = title,
                subtitle = subtitle,
                modifier = Modifier.weight(1f)
            )

            if (actionText.isNotBlank()) {
                Text(
                    text = actionText,
                    modifier = Modifier.clickable {
                        onActionClick()
                    },
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Success
                )
            }
        }

        Spacer(modifier = Modifier.height(BbSpacing.sm))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            items.forEach { item ->
                FilterChip(
                    selected = selectedItem == item,
                    onClick = {
                        onItemClick(item)
                    },
                    label = {
                        Text(text = item)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductQuantitySection(
    quantity: Int,
    onDecreaseClick: () -> Unit,
    onIncreaseClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.md,
            top = BbSpacing.md,
            end = BbSpacing.md
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Adet",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = "Sepete eklenecek ürün adedi",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextStrong.copy(alpha = 0.62f)
                )
            }

            ProductQuantityButton(
                text = "-",
                onClick = onDecreaseClick
            )

            Text(
                text = quantity.toString(),
                modifier = Modifier.padding(horizontal = BbSpacing.md),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            ProductQuantityButton(
                text = "+",
                onClick = onIncreaseClick
            )
        }
    }
}

@Composable
private fun ProductQuantityButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BbSpacing.xl)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )
    }
}

@Composable
private fun ProductDetailQuickActions(
    product: RetailProductDetail,
    onOtherSellerClick: () -> Unit,
    onReviewClick: () -> Unit,
    onQuestionClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(
            start = BbSpacing.md,
            top = BbSpacing.md,
            end = BbSpacing.md
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        ProductDetailActionRow(
            title = "Diğer satıcılar",
            subtitle = "${product.otherSellerCount} satıcı daha bu ürünü sunuyor",
            onClick = onOtherSellerClick
        )

        ProductDetailActionRow(
            title = "Yorumlar",
            subtitle = "${product.reviewCount} yorum · ${product.ratingText}",
            onClick = onReviewClick
        )

        ProductDetailActionRow(
            title = "Soru & Cevap",
            subtitle = "${product.questionCount} ürün sorusu",
            onClick = onQuestionClick
        )
    }
}

@Composable
private fun ProductDetailActionRow(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextStrong.copy(alpha = 0.62f)
                )
            }

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong.copy(alpha = 0.52f)
            )
        }
    }
}

@Composable
private fun ProductDescriptionSection(
    description: String
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.md,
            top = BbSpacing.md,
            end = BbSpacing.md
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Text(
                text = "Ürün açıklaması",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextStrong.copy(alpha = 0.70f)
            )
        }
    }
}

@Composable
private fun ProductDetailPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.sm,
                vertical = BbSpacing.xs
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextStrong.copy(alpha = 0.68f)
        )
    }
}

@Composable
private fun ProductDetailBottomBar(
    priceText: String,
    onAddToBasketClick: () -> Unit,
    onBuyNowClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = BbSpacing.xs,
        shadowElevation = BbSpacing.sm
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Toplam",
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.TextStrong.copy(alpha = 0.62f)
                )

                Text(
                    text = priceText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Success
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(BbRadius.pill))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable {
                        onAddToBasketClick()
                    }
                    .padding(
                        horizontal = BbSpacing.md,
                        vertical = BbSpacing.sm
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sepete ekle",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }

            Spacer(modifier = Modifier.width(BbSpacing.sm))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(BbRadius.pill))
                    .background(BbColors.Success)
                    .clickable {
                        onBuyNowClick()
                    }
                    .padding(
                        horizontal = BbSpacing.md,
                        vertical = BbSpacing.sm
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hemen al",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
private fun ProductDetailBottomNavigationSpacer() {
    Spacer(
        modifier = Modifier.height(BbSpacing.xl)
    )
}

data class RetailProductDetailSelection(
    val productId: Int,
    val selectedColor: String,
    val selectedSize: String,
    val quantity: Int
)

data class RetailProductDetail(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val description: String,
    val categoryName: String,
    val priceText: String,
    val oldPriceText: String,
    val badgeText: String,
    val ratingText: String,
    val cargoText: String,
    val stockText: String,
    val reviewCount: Int,
    val questionCount: Int,
    val otherSellerCount: Int,
    val images: List<String>,
    val colors: List<String>,
    val sizes: List<String>,
    val store: RetailProductDetailStore
)

data class RetailProductDetailStore(
    val id: Int,
    val name: String,
    val logoText: String,
    val ratingText: String,
    val productCount: Int,
    val isVerified: Boolean
)

private fun getRetailProductDetail(productId: Int): RetailProductDetail {
    return RetailProductDetail(
        id = productId,
        name = "Kadın klasik sneaker ayakkabı",
        shortDescription = "Günlük kullanım için rahat tabanlı, sade ve modern sneaker modeli.",
        description = "Hafif tabanı, yumuşak iç yüzeyi ve günlük kombinlere uyum sağlayan sade tasarımıyla şehir içi kullanım için hazırlanmıştır. Ürün kalıbı standarttır. Taraklı ayaklarda yarım numara büyük tercih edilebilir.",
        categoryName = "Ayakkabı",
        priceText = "₺899,90",
        oldPriceText = "₺1.099,90",
        badgeText = "%20",
        ratingText = "★ 4.8",
        cargoText = "Hızlı kargo",
        stockText = "Stokta var",
        reviewCount = 126,
        questionCount = 18,
        otherSellerCount = 5,
        images = listOf(
            "P1",
            "P2",
            "P3",
            "P4"
        ),
        colors = listOf(
            "Beyaz",
            "Siyah",
            "Bej",
            "Gri"
        ),
        sizes = listOf(
            "36",
            "37",
            "38",
            "39",
            "40"
        ),
        store = RetailProductDetailStore(
            id = 1,
            name = "Ortobella Store",
            logoText = "OS",
            ratingText = "4.8",
            productCount = 248,
            isVerified = true
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailScreenPreview() {
    ProductDetailScreen()
}