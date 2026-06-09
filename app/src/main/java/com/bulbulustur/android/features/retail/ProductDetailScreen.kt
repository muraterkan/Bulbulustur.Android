package com.bulbulustur.android.features.retail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CircleNotifications
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.Report
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
import com.bulbulustur.android.features.retail.components.RetailSearchHeader
import com.bulbulustur.android.features.retail.components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun ProductDetailScreen(
    productId: Int = 1,
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onAddToBasketClick: (RetailProductDetailSelection) -> Unit = {},
    onBuyNowClick: (RetailProductDetailSelection) -> Unit = {},
    onStockAlarmClick: (RetailProductDetailSelection) -> Unit = {},
    onStoreClick: (RetailProductDetailStore) -> Unit = {},
    onOtherSellerClick: () -> Unit = {},
    onSizeGuideClick: () -> Unit = {},
    onReviewClick: () -> Unit = {},
    onQuestionClick: () -> Unit = {},
    onSellerProductClick: (RetailSellerProductItem) -> Unit = {},
    onRelatedCategoryClick: (RetailRelatedCategoryChip) -> Unit = {},
    onLowerPriceClick: () -> Unit = {},
    onReportAbuseClick: () -> Unit = {},
    onReturnPolicyClick: () -> Unit = {}
) {
    val product = remember(productId) {
        getRetailProductDetail(productId)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedColorId by remember {
        mutableStateOf(product.colorVariants.first().id)
    }

    var selectedSizeId by remember {
        mutableStateOf(product.sizeOptions.first { sizeOption ->
            sizeOption.state == RetailProductSizeState.Selected
        }.id)
    }

    var quantity by remember {
        mutableIntStateOf(1)
    }

    val selectedColorVariant = product.colorVariants.first { colorVariant ->
        colorVariant.id == selectedColorId
    }

    val selectedSizeOption = product.sizeOptions.first { sizeOption ->
        sizeOption.id == selectedSizeId
    }

    val visibleImages = selectedColorVariant.images

    val pagerState = rememberPagerState(
        pageCount = {
            visibleImages.size
        }
    )

    LaunchedEffect(selectedColorId) {
        pagerState.scrollToPage(0)
    }

    val selection = RetailProductDetailSelection(
        productId = product.id,
        selectedColor = selectedColorVariant.name,
        selectedSize = selectedSizeOption.label,
        quantity = quantity
    )

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = onBackClick,
                onFavoriteClick = onFavoriteClick,
                placeholder = product.searchPlaceholder,
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                leadingAction = RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            RetailProductDetailBottomBar(
                isInStock = product.isInStock,
                onAddToBasketClick = {
                    onAddToBasketClick(selection)
                },
                onBuyNowClick = {
                    onBuyNowClick(selection)
                },
                onStockAlarmClick = {
                    onStockAlarmClick(selection)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            RetailProductDetailGallery(
                product = product,
                images = visibleImages,
                currentPage = pagerState.currentPage,
                pagerContent = {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    ) { page ->
                        RetailProductDetailImageSlide(
                            image = visibleImages[page]
                        )
                    }
                }
            )

            RetailProductTitleCard(
                product = product
            )

            RetailProductRatingSummaryCard(
                ratingText = product.ratingText,
                reviewCount = product.reviewCount,
                onReviewClick = onReviewClick
            )

            RetailProductDetailVariantCard(
                product = product,
                selectedColorVariant = selectedColorVariant,
                selectedSizeOption = selectedSizeOption,
                quantity = quantity,
                onColorClick = { colorVariant ->
                    selectedColorId = colorVariant.id
                },
                onSizeClick = { sizeOption ->
                    if (sizeOption.state != RetailProductSizeState.Disabled &&
                        sizeOption.state != RetailProductSizeState.OutOfStock
                    ) {
                        selectedSizeId = sizeOption.id
                    }
                },
                onSizeGuideClick = onSizeGuideClick,
                onDecreaseQuantityClick = {
                    if (quantity > 1) {
                        quantity -= 1
                    }
                },
                onIncreaseQuantityClick = {
                    quantity += 1
                }
            )

            RetailProductPriceCard(
                product = product
            )

            if (!product.isInStock) {
                RetailProductStockAlertCard(
                    onStockAlarmClick = {
                        onStockAlarmClick(selection)
                    }
                )
            }

            RetailProductTrustLinksCard(
                onLowerPriceClick = onLowerPriceClick,
                onReportAbuseClick = onReportAbuseClick,
                onReturnPolicyClick = onReturnPolicyClick
            )

            RetailSellerProductsCarousel(
                products = product.sellerProducts,
                onProductClick = onSellerProductClick,
                onStoreClick = {
                    onStoreClick(product.store)
                }
            )

            RetailReviewCarousel(
                reviews = product.reviews,
                ratingText = product.ratingText,
                reviewCount = product.reviewCount,
                onReviewClick = onReviewClick
            )

            RetailProductDetailStoreCard(
                store = product.store,
                onStoreClick = {
                    onStoreClick(product.store)
                }
            )

            RetailProductDetailQuickActions(
                product = product,
                onOtherSellerClick = onOtherSellerClick,
                onQuestionClick = onQuestionClick
            )

            RetailProductDescriptionSection(
                description = product.description
            )

            RetailRelatedCategoryChipsSection(
                categories = product.relatedCategories,
                onCategoryClick = onRelatedCategoryClick
            )

            Spacer(
                modifier = Modifier.height(BbSpacing.Space16)
            )
        }
    }
}

@Composable
private fun RetailProductDetailGallery(
    product: RetailProductDetail,
    images: List<RetailProductImage>,
    currentPage: Int,
    pagerContent: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 390.dp)
                .background(BbColors.White),
            contentAlignment = Alignment.Center
        ) {
            pagerContent()

            if (product.badgeText.isNotBlank()) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(BbSpacing.Space3),
                    shape = BbRadius.PillShape,
                    color = BbColors.Green.Green500
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = BbSpacing.Space3,
                            vertical = BbSpacing.Space1
                        ),
                        text = product.badgeText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            RetailProductDetailImageCounter(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = BbSpacing.Space3,
                        bottom = BbSpacing.Space3
                    ),
                currentPage = currentPage + 1,
                totalPage = images.size
            )
        }
    }
}

@Composable
private fun RetailProductDetailImageSlide(
    image: RetailProductImage
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 390.dp)
            .aspectRatio(0.92f)
            .background(image.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (image.drawableResId != null) {
            Image(
                painter = painterResource(id = image.drawableResId),
                contentDescription = image.label,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(
                text = image.label,
                style = MaterialTheme.typography.displaySmall,
                color = image.foregroundColor,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
private fun RetailProductDetailImageCounter(
    modifier: Modifier,
    currentPage: Int,
    totalPage: Int
) {
    Surface(
        modifier = modifier,
        shape = BbRadius.PillShape,
        color = BbColors.Black.copy(alpha = 0.52f)
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            text = "$currentPage / $totalPage",
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun RetailProductTitleCard(
    product: RetailProductDetail
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = product.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextSubtle
            )
        }
    }
}

@Composable
private fun RetailProductRatingSummaryCard(
    ratingText: String,
    reviewCount: Int,
    onReviewClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onReviewClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = BbColors.Primary,
                        modifier = Modifier.size(BbIcon.SizeSm)
                    )
                }
            }

            Text(
                text = ratingText.replace("★ ", ""),
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.weight(1f),
                text = "$reviewCount değerlendirme",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.size(BbIcon.SizeMd)
            )
        }
    }
}

@Composable
private fun RetailProductDetailVariantCard(
    product: RetailProductDetail,
    selectedColorVariant: RetailProductColorVariant,
    selectedSizeOption: RetailProductSizeOption,
    quantity: Int,
    onColorClick: (RetailProductColorVariant) -> Unit,
    onSizeClick: (RetailProductSizeOption) -> Unit,
    onSizeGuideClick: () -> Unit,
    onDecreaseQuantityClick: () -> Unit,
    onIncreaseQuantityClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space5)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Text(
                    text = "Renk: ${selectedColorVariant.name}",
                    style = MaterialTheme.typography.titleSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Seçenekler",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    product.colorVariants.forEach { colorVariant ->
                        RetailProductColorChoice(
                            colorVariant = colorVariant,
                            isSelected = selectedColorVariant.id == colorVariant.id,
                            onClick = {
                                onColorClick(colorVariant)
                            }
                        )
                    }
                }
            }

            RetailDashedDivider()

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Beden: ${selectedSizeOption.label}",
                            style = MaterialTheme.typography.titleSmall,
                            color = BbColors.TextStrong,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Seçenekler",
                            style = MaterialTheme.typography.bodySmall,
                            color = BbColors.TextMuted
                        )
                    }

                    Row(
                        modifier = Modifier.clickable {
                            onSizeGuideClick()
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Straighten,
                            contentDescription = null,
                            tint = BbColors.Success,
                            modifier = Modifier.size(BbIcon.SizeSm)
                        )

                        Text(
                            text = "Beden rehberi",
                            style = MaterialTheme.typography.labelMedium,
                            color = BbColors.Success,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    product.sizeOptions.forEach { sizeOption ->
                        RetailProductSizeChoice(
                            sizeOption = sizeOption,
                            isSelected = selectedSizeOption.id == sizeOption.id,
                            onClick = {
                                onSizeClick(sizeOption)
                            }
                        )
                    }
                }
            }

            RetailDashedDivider()

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Adet",
                        style = MaterialTheme.typography.titleSmall,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Sepete eklenecek ürün adedi",
                        style = MaterialTheme.typography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }

                RetailProductQuantityButton(
                    icon = Icons.Outlined.Remove,
                    onClick = onDecreaseQuantityClick
                )

                Text(
                    modifier = Modifier.padding(horizontal = BbSpacing.Space4),
                    text = quantity.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                RetailProductQuantityButton(
                    icon = Icons.Outlined.Add,
                    onClick = onIncreaseQuantityClick
                )
            }
        }
    }
}

@Composable
private fun RetailProductColorChoice(
    colorVariant: RetailProductColorVariant,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) {
        BbColors.TextStrong
    } else {
        BbColors.Border
    }

    Surface(
        modifier = Modifier
            .width(92.dp)
            .clip(BbRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.XlShape,
        color = if (isSelected) {
            BbColors.SurfaceMuted
        } else {
            BbColors.Surface
        },
        border = BorderStroke(
            width = if (isSelected) {
                2.dp
            } else {
                1.dp
            },
            color = borderColor
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .clip(BbRadius.LgShape)
                    .background(BbColors.SurfaceMuted),
                contentAlignment = Alignment.Center
            ) {
                val previewImage = colorVariant.images.firstOrNull()

                if (previewImage?.drawableResId != null) {
                    Image(
                        painter = painterResource(id = previewImage.drawableResId),
                        contentDescription = colorVariant.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Text(
                        text = colorVariant.name.take(2),
                        style = MaterialTheme.typography.labelMedium,
                        color = BbColors.TextMuted,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Surface(
                    modifier = Modifier.size(14.dp),
                    shape = CircleShape,
                    color = colorVariant.swatchColor,
                    border = BorderStroke(
                        width = 1.dp,
                        color = BbColors.BorderStrong
                    )
                ) {}

                Text(
                    text = colorVariant.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun RetailProductSizeChoice(
    sizeOption: RetailProductSizeOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val isEnabled = sizeOption.state != RetailProductSizeState.Disabled &&
            sizeOption.state != RetailProductSizeState.OutOfStock

    val shape = BbRadius.PillShape

    val backgroundColor = when {
        isSelected -> BbColors.TextStrong
        !isEnabled -> BbColors.SurfaceMuted
        else -> BbColors.Surface
    }

    val textColor = when {
        isSelected -> BbColors.White
        !isEnabled -> BbColors.TextMuted
        else -> BbColors.TextStrong
    }

    val borderColor = when {
        isSelected -> BbColors.TextStrong
        sizeOption.state == RetailProductSizeState.Limited -> BbColors.Primary
        else -> BbColors.Border
    }

    val modifier = Modifier
        .width(58.dp)
        .height(42.dp)
        .then(
            if (sizeOption.state == RetailProductSizeState.Limited && !isSelected) {
                Modifier.drawBehind {
                    drawRoundRect(
                        color = BbColors.Primary,
                        cornerRadius = CornerRadius(21.dp.toPx(), 21.dp.toPx()),
                        style = Stroke(
                            width = 1.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(8f, 6f),
                                phase = 0f
                            )
                        )
                    )
                }
            } else {
                Modifier
            }
        )
        .clip(shape)
        .clickable(
            enabled = isEnabled
        ) {
            onClick()
        }

    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        border = if (sizeOption.state == RetailProductSizeState.Limited && !isSelected) {
            null
        } else {
            BorderStroke(
                width = 1.dp,
                color = borderColor
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = sizeOption.label,
                style = MaterialTheme.typography.labelLarge,
                color = textColor,
                fontWeight = FontWeight.Bold,
                textDecoration = if (!isEnabled) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                }
            )
        }
    }
}

@Composable
private fun RetailProductQuantityButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        shape = CircleShape,
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.SizeSm)
            )
        }
    }
}

@Composable
private fun RetailProductPriceCard(
    product: RetailProductDetail
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.Space3,
                end = BbSpacing.PageHorizontal
            ),
        shape = BbRadius.XlShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Text(
                    text = product.priceText,
                    style = MaterialTheme.typography.headlineMedium,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.ExtraBold
                )

                if (product.oldPriceText.isNotBlank()) {
                    Text(
                        text = product.oldPriceText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BbColors.TextMuted,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                if (product.discountText.isNotBlank()) {
                    Surface(
                        shape = BbRadius.PillShape,
                        color = BbColors.Surface,
                        border = BorderStroke(
                            width = 1.dp,
                            color = BbColors.Primary
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                horizontal = BbSpacing.Space2,
                                vertical = BbSpacing.Space1
                            ),
                            text = product.discountText,
                            style = MaterialTheme.typography.labelSmall,
                            color = BbColors.TextStrong,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                RetailProductBenefitPill(
                    text = product.cargoText,
                    icon = Icons.Outlined.LocalShipping
                )

                RetailProductBenefitPill(
                    text = if (product.isInStock) {
                        product.stockText
                    } else {
                        "Stokta yok"
                    },
                    icon = Icons.Outlined.Inventory2
                )

                RetailProductBenefitPill(
                    text = "Güvenli alışveriş",
                    icon = Icons.Outlined.Verified
                )
            }
        }
    }
}

@Composable
private fun RetailProductBenefitPill(
    text: String,
    icon: ImageVector
) {
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.Success.copy(alpha = 0.10f),
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Success.copy(alpha = 0.24f)
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
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Success,
                modifier = Modifier.size(BbIcon.Size2Xs)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.Success,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun RetailProductStockAlertCard(
    onStockAlarmClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = BbRadius.XlShape,
                color = BbColors.PrimarySoft,
                border = BorderStroke(
                    width = 1.dp,
                    color = BbColors.Primary
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = BbSpacing.Space3,
                        vertical = BbSpacing.Space3
                    ),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CircleNotifications,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                    ) {
                        Text(
                            text = "Stokta yok",
                            style = MaterialTheme.typography.titleSmall,
                            color = BbColors.TextStrong,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Bu ürün tekrar stoğa girdiğinde haberdar olabilirsin.",
                            style = MaterialTheme.typography.bodySmall,
                            color = BbColors.TextSubtle
                        )
                    }

                    Text(
                        text = "Stok alarmı",
                        style = MaterialTheme.typography.labelMedium,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(BbRadius.XxlShape)
                    .clickable {
                        onStockAlarmClick()
                    },
                shape = BbRadius.XxlShape,
                color = BbColors.Success
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.NotificationsActive,
                        contentDescription = null,
                        tint = BbColors.White,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )

                    Spacer(
                        modifier = Modifier.width(BbSpacing.Space2)
                    )

                    Text(
                        text = "Stoğa Gelince Haber Ver",
                        style = MaterialTheme.typography.labelLarge,
                        color = BbColors.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun RetailProductTrustLinksCard(
    onLowerPriceClick: () -> Unit,
    onReportAbuseClick: () -> Unit,
    onReturnPolicyClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            RetailProductTrustLinkRow(
                title = "Daha Düşük Fiyat mı Gördünüz?",
                icon = Icons.Outlined.WarningAmber,
                onClick = onLowerPriceClick
            )

            RetailDashedDivider()

            RetailProductTrustLinkRow(
                title = "Kötüye Kullanımı Bildir",
                icon = Icons.Outlined.Report,
                onClick = onReportAbuseClick
            )

            RetailDashedDivider()

            RetailProductTrustLinkRow(
                title = "İptal ve İade Koşulları",
                icon = Icons.Outlined.VerifiedUser,
                onClick = onReturnPolicyClick
            )
        }
    }
}

@Composable
private fun RetailProductTrustLinkRow(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = BbSpacing.Space3
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Surface(
            modifier = Modifier.size(26.dp),
            shape = BbRadius.MdShape,
            color = BbColors.PrimarySoft
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(BbIcon.SizeSm)
                )
            }
        }

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = BbColors.TextMuted,
            modifier = Modifier.size(BbIcon.SizeMd)
        )
    }
}

@Composable
private fun RetailDashedDivider() {
    val dividerColor = BbColors.BorderStrong

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = BbSpacing.Space3)
            .drawBehind {
                drawLine(
                    color = dividerColor,
                    start = Offset.Zero,
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(8f, 8f),
                        phase = 0f
                    )
                )
            }
    )
}

@Composable
private fun RetailSellerProductsCarousel(
    products: List<RetailSellerProductItem>,
    onProductClick: (RetailSellerProductItem) -> Unit,
    onStoreClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(
            top = BbSpacing.Space5
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        RetailSectionTitle(
            title = "Satıcının diğer ürünleri",
            actionText = "Mağazaya git",
            onActionClick = onStoreClick
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = BbSpacing.PageHorizontal),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            products.forEach { product ->
                RetailSellerProductMiniCard(
                    product = product,
                    onClick = {
                        onProductClick(product)
                    }
                )
            }

            RetailSellerMoreProductsCard(
                onClick = onStoreClick
            )
        }
    }
}

@Composable
private fun RetailSellerProductMiniCard(
    product: RetailSellerProductItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(132.dp)
            .clip(BbRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.XlShape,
        color = BbColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .clip(BbRadius.LgShape)
                    .background(BbColors.SurfaceMuted),
                contentAlignment = Alignment.Center
            ) {
                if (product.drawableResId != null) {
                    Image(
                        painter = painterResource(id = product.drawableResId),
                        contentDescription = product.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = product.imageLabel,
                        style = MaterialTheme.typography.titleMedium,
                        color = BbColors.TextMuted,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2
            )

            Text(
                text = product.priceText,
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun RetailSellerMoreProductsCard(
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(132.dp)
            .height(188.dp)
            .clip(BbRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.XlShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space3),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.Storefront,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.SizeLg)
            )

            Spacer(
                modifier = Modifier.height(BbSpacing.Space2)
            )

            Text(
                text = "Diğer ürünler",
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Mağazaya git",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextSubtle
            )
        }
    }
}

@Composable
private fun RetailReviewCarousel(
    reviews: List<RetailReviewItem>,
    ratingText: String,
    reviewCount: Int,
    onReviewClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(
            top = BbSpacing.Space3
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        RetailSectionTitle(
            title = "Ürün değerlendirmeleri",
            actionText = "Tüm yorumlar",
            onActionClick = onReviewClick
        )

        BbCard(
            modifier = Modifier.padding(
                start = BbSpacing.PageHorizontal,
                end = BbSpacing.PageHorizontal
            ),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Medium,
            onClick = onReviewClick
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                ) {
                    Text(
                        text = ratingText.replace("★ ", ""),
                        style = MaterialTheme.typography.headlineSmall,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(1.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Outlined.Star,
                                contentDescription = null,
                                tint = BbColors.Primary,
                                modifier = Modifier.size(BbIcon.SizeSm)
                            )
                        }
                    }

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "$reviewCount yorum",
                        style = MaterialTheme.typography.bodySmall,
                        color = BbColors.TextMuted
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = BbColors.TextMuted,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    reviews.forEach { review ->
                        RetailReviewCard(
                            review = review
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RetailReviewCard(
    review: RetailReviewItem
) {
    Surface(
        modifier = Modifier.width(236.dp),
        shape = BbRadius.XlShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Surface(
                    modifier = Modifier.size(34.dp),
                    shape = CircleShape,
                    color = BbColors.Surface
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = review.avatarText,
                            style = MaterialTheme.typography.labelSmall,
                            color = BbColors.TextStrong,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = review.customerName,
                        style = MaterialTheme.typography.labelLarge,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = review.dateText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.TextMuted
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(review.rating) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = BbColors.Primary,
                        modifier = Modifier.size(BbIcon.Size2Xs)
                    )
                }
            }

            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextSubtle,
                maxLines = 4
            )
        }
    }
}

@Composable
private fun RetailProductDetailStoreCard(
    store: RetailProductDetailStore,
    onStoreClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onStoreClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = BbRadius.LgShape,
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
                        text = store.logoText,
                        style = MaterialTheme.typography.labelLarge,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
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
                        style = MaterialTheme.typography.titleSmall,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    if (store.isVerified) {
                        RetailProductDetailPill(
                            text = "Doğrulanmış",
                            icon = Icons.Outlined.Verified
                        )
                    }
                }

                Text(
                    text = "${store.ratingText} puan · ${store.productCount} ürün",
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

@Composable
private fun RetailProductDetailQuickActions(
    product: RetailProductDetail,
    onOtherSellerClick: () -> Unit,
    onQuestionClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        RetailProductDetailActionRow(
            title = "Diğer satıcılar",
            subtitle = "${product.otherSellerCount} satıcı daha bu ürünü sunuyor",
            onClick = onOtherSellerClick
        )

        RetailProductDetailActionRow(
            title = "Soru & Cevap",
            subtitle = "${product.questionCount} ürün sorusu",
            onClick = onQuestionClick
        )
    }
}

@Composable
private fun RetailProductDetailActionRow(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    BbCard(
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = subtitle,
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

@Composable
private fun RetailProductDescriptionSection(
    description: String
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Text(
                text = "Ürün açıklaması",
                style = MaterialTheme.typography.titleMedium,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextSubtle
            )
        }
    }
}

@Composable
private fun RetailRelatedCategoryChipsSection(
    categories: List<RetailRelatedCategoryChip>,
    onCategoryClick: (RetailRelatedCategoryChip) -> Unit
) {
    Column(
        modifier = Modifier.padding(
            top = BbSpacing.Space3
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        RetailSectionTitle(
            title = "İlgili kategoriler",
            actionText = "",
            onActionClick = {}
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = BbSpacing.PageHorizontal),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            categories.forEach { category ->
                Surface(
                    modifier = Modifier
                        .clip(BbRadius.PillShape)
                        .clickable {
                            onCategoryClick(category)
                        },
                    shape = BbRadius.PillShape,
                    color = BbColors.Surface
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = BbSpacing.Space3,
                            vertical = BbSpacing.Space2
                        ),
                        text = category.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun RetailSectionTitle(
    title: String,
    actionText: String,
    onActionClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BbSpacing.PageHorizontal),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        if (actionText.isNotBlank()) {
            Row(
                modifier = Modifier.clickable {
                    onActionClick()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelMedium,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(BbIcon.SizeSm)
                )
            }
        }
    }
}

@Composable
private fun RetailProductDetailPill(
    text: String,
    icon: ImageVector? = null
) {
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
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
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BbColors.TextMuted,
                    modifier = Modifier.size(BbIcon.Size2Xs)
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.TextSubtle,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun RetailProductDetailBottomBar(
    isInStock: Boolean,
    onAddToBasketClick: () -> Unit,
    onBuyNowClick: () -> Unit,
    onStockAlarmClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = BbColors.Surface,
        shadowElevation = 12.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = BbSpacing.PageHorizontal,
                    top = BbSpacing.Space2,
                    end = BbSpacing.PageHorizontal,
                    bottom = BbSpacing.Space2
                )
        ) {
            if (isInStock) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    RetailProductBottomActionButton(
                        text = "Sepete Ekle",
                        onClick = onAddToBasketClick,
                        modifier = Modifier.weight(1f),
                        containerColor = BbColors.PrimarySoft,
                        contentColor = BbColors.TextStrong,
                        borderColor = BbColors.Primary,
                        leadingIcon = Icons.Outlined.ShoppingCart
                    )

                    RetailProductBottomActionButton(
                        text = "Hemen Al",
                        onClick = onBuyNowClick,
                        modifier = Modifier.weight(1f),
                        containerColor = BbColors.Primary,
                        contentColor = BbColors.TextStrong,
                        borderColor = BbColors.Primary,
                        leadingIcon = null
                    )
                }
            } else {
                RetailProductBottomActionButton(
                    text = "Stoğa Gelince Haber Ver",
                    onClick = onStockAlarmClick,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = BbColors.Success,
                    contentColor = BbColors.White,
                    borderColor = BbColors.Success,
                    leadingIcon = Icons.Outlined.NotificationsActive
                )
            }
        }
    }
}

@Composable
private fun RetailProductBottomActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    containerColor: Color,
    contentColor: Color,
    borderColor: Color,
    leadingIcon: ImageVector?
) {
    Surface(
        modifier = modifier
            .height(44.dp)
            .clip(BbRadius.XxlShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.XxlShape,
        color = containerColor,
        border = BorderStroke(
            width = 2.dp,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(BbIcon.SizeSm)
                )

                Spacer(
                    modifier = Modifier.width(BbSpacing.Space2)
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Immutable
data class RetailProductDetailSelection(
    val productId: Int,
    val selectedColor: String,
    val selectedSize: String,
    val quantity: Int
)

@Immutable
data class RetailProductDetail(
    val id: Int,
    val name: String,
    val brandName: String,
    val searchPlaceholder: String,
    val shortDescription: String,
    val description: String,
    val categoryName: String,
    val priceText: String,
    val oldPriceText: String,
    val discountText: String,
    val badgeText: String,
    val ratingText: String,
    val cargoText: String,
    val stockText: String,
    val isInStock: Boolean,
    val reviewCount: Int,
    val questionCount: Int,
    val otherSellerCount: Int,
    val colorVariants: List<RetailProductColorVariant>,
    val sizeOptions: List<RetailProductSizeOption>,
    val sellerProducts: List<RetailSellerProductItem>,
    val reviews: List<RetailReviewItem>,
    val relatedCategories: List<RetailRelatedCategoryChip>,
    val store: RetailProductDetailStore
)

@Immutable
data class RetailProductColorVariant(
    val id: String,
    val name: String,
    val swatchColor: Color,
    val images: List<RetailProductImage>
)

@Immutable
data class RetailProductImage(
    val label: String,
    val backgroundColor: Color,
    val foregroundColor: Color,
    @DrawableRes val drawableResId: Int? = null
)

@Immutable
data class RetailProductSizeOption(
    val id: String,
    val label: String,
    val state: RetailProductSizeState
)

enum class RetailProductSizeState {
    Selected,
    Available,
    Disabled,
    Limited,
    OutOfStock
}

@Immutable
data class RetailProductDetailStore(
    val id: Int,
    val name: String,
    val logoText: String,
    val ratingText: String,
    val productCount: Int,
    val isVerified: Boolean
)

@Immutable
data class RetailSellerProductItem(
    val id: Int,
    val name: String,
    val priceText: String,
    val imageLabel: String,
    @DrawableRes val drawableResId: Int? = null
)

@Immutable
data class RetailReviewItem(
    val id: Int,
    val customerName: String,
    val avatarText: String,
    val rating: Int,
    val dateText: String,
    val comment: String
)

@Immutable
data class RetailRelatedCategoryChip(
    val id: Int,
    val name: String
)

private fun getRetailProductDetail(
    productId: Int
): RetailProductDetail {
    val sellerProducts = (1..12).map { index ->
        RetailSellerProductItem(
            id = index,
            name = "Ortobella günlük sneaker modeli $index",
            priceText = "₺${799 + index * 20},90",
            imageLabel = "P$index",
            drawableResId = if (index <= 4) {
                R.drawable.h3ff3b33d6a1447c898cee6e336867bach
            } else {
                null
            }
        )
    }

    return RetailProductDetail(
        id = productId,
        name = "Kadın klasik sneaker ayakkabı",
        brandName = "Ortobella",
        searchPlaceholder = "Ürün, kategori veya marka ara",
        shortDescription = "Günlük kullanım için rahat tabanlı, sade ve modern sneaker modeli.",
        description = "Hafif tabanı, yumuşak iç yüzeyi ve günlük kombinlere uyum sağlayan sade tasarımıyla şehir içi kullanım için hazırlanmıştır. Ürün kalıbı standarttır. Taraklı ayaklarda yarım numara büyük tercih edilebilir.",
        categoryName = "Ayakkabı",
        priceText = "₺899,90",
        oldPriceText = "₺1.099,90",
        discountText = "%20 indirim",
        badgeText = "%20",
        ratingText = "★ 4.8",
        cargoText = "Hızlı kargo",
        stockText = "Stokta var",
        isInStock = true,
        reviewCount = 126,
        questionCount = 18,
        otherSellerCount = 5,
        colorVariants = listOf(
            RetailProductColorVariant(
                id = "white",
                name = "Beyaz",
                swatchColor = BbColors.White,
                images = listOf(
                    RetailProductImage(
                        label = "Beyaz 1",
                        backgroundColor = BbColors.White,
                        foregroundColor = BbColors.Gray.Gray500,
                        drawableResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach
                    ),
                    RetailProductImage(
                        label = "Beyaz 2",
                        backgroundColor = BbColors.White,
                        foregroundColor = BbColors.Gray.Gray500,
                        drawableResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach
                    ),
                    RetailProductImage(
                        label = "Beyaz 3",
                        backgroundColor = BbColors.White,
                        foregroundColor = BbColors.Gray.Gray500,
                        drawableResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach
                    )
                )
            ),
            RetailProductColorVariant(
                id = "black",
                name = "Siyah",
                swatchColor = BbColors.Black,
                images = listOf(
                    RetailProductImage(
                        label = "Siyah 1",
                        backgroundColor = BbColors.Coal.Coal100,
                        foregroundColor = BbColors.Gray.Gray300
                    ),
                    RetailProductImage(
                        label = "Siyah 2",
                        backgroundColor = BbColors.Coal.Coal200,
                        foregroundColor = BbColors.Gray.Gray200
                    )
                )
            ),
            RetailProductColorVariant(
                id = "beige",
                name = "Bej",
                swatchColor = BbColors.Beige.Beige400,
                images = listOf(
                    RetailProductImage(
                        label = "Bej 1",
                        backgroundColor = BbColors.Beige.Beige100,
                        foregroundColor = BbColors.Beige.Beige700
                    ),
                    RetailProductImage(
                        label = "Bej 2",
                        backgroundColor = BbColors.Beige.Beige200,
                        foregroundColor = BbColors.Beige.Beige800
                    )
                )
            ),
            RetailProductColorVariant(
                id = "gray",
                name = "Gri",
                swatchColor = BbColors.Gray.Gray400,
                images = listOf(
                    RetailProductImage(
                        label = "Gri 1",
                        backgroundColor = BbColors.Gray.Gray100,
                        foregroundColor = BbColors.Gray.Gray600
                    ),
                    RetailProductImage(
                        label = "Gri 2",
                        backgroundColor = BbColors.Gray.Gray200,
                        foregroundColor = BbColors.Gray.Gray700
                    )
                )
            )
        ),
        sizeOptions = listOf(
            RetailProductSizeOption(
                id = "36",
                label = "36",
                state = RetailProductSizeState.Selected
            ),
            RetailProductSizeOption(
                id = "37",
                label = "37",
                state = RetailProductSizeState.Disabled
            ),
            RetailProductSizeOption(
                id = "38",
                label = "38",
                state = RetailProductSizeState.OutOfStock
            ),
            RetailProductSizeOption(
                id = "39",
                label = "39",
                state = RetailProductSizeState.Limited
            ),
            RetailProductSizeOption(
                id = "40",
                label = "40",
                state = RetailProductSizeState.Available
            )
        ),
        sellerProducts = sellerProducts,
        reviews = listOf(
            RetailReviewItem(
                id = 1,
                customerName = "Ayşe K.",
                avatarText = "AK",
                rating = 5,
                dateText = "2 gün önce",
                comment = "Kalıbı rahat, günlük kullanım için gayet başarılı. Rengi fotoğraftaki gibi geldi."
            ),
            RetailReviewItem(
                id = 2,
                customerName = "Merve D.",
                avatarText = "MD",
                rating = 5,
                dateText = "1 hafta önce",
                comment = "Tabanı yumuşak ve hafif. Paketleme de iyiydi."
            ),
            RetailReviewItem(
                id = 3,
                customerName = "Selin A.",
                avatarText = "SA",
                rating = 4,
                dateText = "12 gün önce",
                comment = "Model güzel. Taraklı ayaklar için yarım numara büyük alınabilir."
            ),
            RetailReviewItem(
                id = 4,
                customerName = "Buse T.",
                avatarText = "BT",
                rating = 5,
                dateText = "3 hafta önce",
                comment = "Fiyatına göre kaliteli duruyor, hızlı kargolandı."
            ),
            RetailReviewItem(
                id = 5,
                customerName = "Elif Y.",
                avatarText = "EY",
                rating = 4,
                dateText = "1 ay önce",
                comment = "Rahat ve şık. Günlük kombinlerde iyi duruyor."
            )
        ),
        relatedCategories = listOf(
            RetailRelatedCategoryChip(1, "Sneaker"),
            RetailRelatedCategoryChip(2, "Kadın ayakkabı"),
            RetailRelatedCategoryChip(3, "Günlük ayakkabı"),
            RetailRelatedCategoryChip(4, "Spor ayakkabı"),
            RetailRelatedCategoryChip(5, "Rahat taban"),
            RetailRelatedCategoryChip(6, "Yeni sezon")
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
    BbTheme {
        ProductDetailScreen()
    }
}
