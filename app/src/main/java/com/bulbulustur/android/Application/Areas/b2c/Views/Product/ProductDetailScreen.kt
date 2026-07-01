package com.bulbulustur.android.Application.Areas.b2c.Views.Product

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import coil3.compose.AsyncImage
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CircleNotifications
import androidx.compose.material.icons.outlined.Close
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductControllerState
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components._B2CRelatedCategoryLinks
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components._B2CProductBrowsingHistory
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components._B2CSponsoredAdverts
import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components._FromBrands
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandSectionPageDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    State: ProductControllerState = ProductControllerState(),
    productId: Int = 0,
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onColorVariantChange: (Int) -> Unit = {},
    onSizeVariantChange: (Int) -> Unit = {},
    onAddToBasketClick: (RetailProductDetailSelection) -> Unit = {},
    onBuyNowClick: (RetailProductDetailSelection) -> Unit = {},
    onStockAlarmClick: (RetailProductDetailSelection) -> Unit = {},
    onStoreClick: (RetailProductDetailStore) -> Unit = {},
    onOtherSellerClick: () -> Unit = {},
    onSizeGuideClick: () -> Unit = {},
    onReviewClick: () -> Unit = {},
    onQuestionClick: () -> Unit = {},
    onSellerProductClick: (RetailSellerProductItem) -> Unit = {},
    onSponsoredAdvertClick: (AdvertSponsoredDTO) -> Unit = {},
    onBrandSectionPageClick: (ProductBrandSectionPageDTO) -> Unit = {},
    onBrowsingHistoryProductClick: (ProductBrowsingHistoryDTO) -> Unit = {},
    onRelatedCategoryClick: (RetailRelatedCategoryChip) -> Unit = {},
    onLowerPriceClick: () -> Unit = {},
    onReportAbuseClick: () -> Unit = {},
    onReturnPolicyClick: () -> Unit = {}
) {
    val productDto =
        State.ProductDetailResult
            ?.Data

    val variantPictures =
        State.ProductVariantPicturesResult
            ?.Data
            .orEmpty()

    val colorVariants =
        State.ColorVariants

    val sizeVariants =
        State.SizeVariants

    val otherStorePrices =
        State.OtherStorePrices

    val smallestPrice =
        State.SmallestPriceResult
            ?.Data

    val selectedVariant =
        State.SelectedVariantResult
            ?.Data

    val product =
        remember(
            productDto,
            selectedVariant,
            variantPictures,
            colorVariants,
            sizeVariants,
            otherStorePrices,
            smallestPrice,
            productId
        ) {
            productDto
                ?.ToRetailProductDetail(
                    variantPictures =
                        variantPictures,
                    colorVariants =
                        colorVariants,
                    sizeVariants =
                        sizeVariants,
                    otherStorePrices =
                        otherStorePrices,
                    smallestPrice =
                        smallestPrice,
                    selectedVariant =
                        selectedVariant
                )
                ?: getRetailProductDetail(
                    productId =
                        productId
                )
        }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedColorId by remember(
        product.id,
        product.colorVariants
    ) {
        mutableStateOf(
            product.colorVariants
                .firstOrNull()
                ?.id
                ?: ""
        )
    }

    var selectedSizeId by remember(
        product.id,
        product.sizeOptions
    ) {
        mutableStateOf(
            product.sizeOptions
                .firstOrNull { sizeOption ->
                    sizeOption.state ==
                            RetailProductSizeState.Selected
                }
                ?.id
                ?: product.sizeOptions
                    .firstOrNull()
                    ?.id
                ?: ""
        )
    }

    var quantity by remember(
        product.id
    ) {
        mutableIntStateOf(1)
    }

    var activeSheet by remember {
        mutableStateOf<RetailProductDetailSheet?>(null)
    }

    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded =
                true
        )

    LaunchedEffect(
        product.id,
        product.colorVariants,
        product.sizeOptions
    ) {
        selectedColorId =
            product.colorVariants
                .firstOrNull()
                ?.id
                ?: ""

        selectedSizeId =
            product.sizeOptions
                .firstOrNull { sizeOption ->
                    sizeOption.state ==
                            RetailProductSizeState.Selected
                }
                ?.id
                ?: product.sizeOptions.firstOrNull()?.id ?: ""

        quantity = 1
    }

    val selectedColorVariant =
        product.colorVariants
            .firstOrNull { colorVariant ->
                colorVariant.id ==
                        selectedColorId
            }
            ?: product.colorVariants.firstOrNull()
            ?: RetailProductColorVariant(
                id = "default",
                name = "Standart",
                swatchColor = BBColors.Gray.Gray400,
                images = emptyList()
            )

    val selectedSizeOption =
        product.sizeOptions
            .firstOrNull { sizeOption ->
                sizeOption.id ==
                        selectedSizeId
            }
            ?: product.sizeOptions
                .firstOrNull()
            ?: RetailProductSizeOption(
                id =
                    "default",
                label =
                    "Standart",
                state =
                    if (
                        product.isInStock
                    ) {
                        RetailProductSizeState.Selected
                    } else {
                        RetailProductSizeState.OutOfStock
                    }
            )

    val visibleImages =
        selectedColorVariant.images
            .ifEmpty {
                listOf(
                    RetailProductImage(
                        label =
                            product.name,
                        backgroundColor =
                            BBColors.White,
                        foregroundColor =
                            BBColors.Gray.Gray500
                    )
                )
            }

    val pagerState =
        rememberPagerState(
            initialPage =
                0,
            pageCount = {
                visibleImages.size
            }
        )

    LaunchedEffect(
        product.id,
        selectedColorId
    ) {
        if (
            visibleImages.isNotEmpty()
        ) {
            pagerState.scrollToPage(
                0
            )
        }
    }

    val selectedVariantId =
        selectedSizeOption.variantId
            .takeIf {
                it > 0
            }
            ?: selectedColorVariant.variantId
                .takeIf {
                    it > 0
                }
            ?: selectedVariant
                ?.VariantId
            ?: 0

    val selectedPriceId =
        selectedSizeOption.priceId
            .takeIf {
                it > 0
            }
            ?: selectedColorVariant.priceId
                .takeIf {
                    it > 0
                }
            ?: selectedVariant
                ?.ProductVariantPriceId
                ?.takeIf {
                    it > 0
                }
            ?: productDto
                ?.ProductVariantPriceId
                ?.takeIf {
                    it > 0
                }
            ?: 0

    val selectedStoreId =
        selectedSizeOption.storeId
            .takeIf {
                it > 0
            }
            ?: selectedColorVariant.storeId
                .takeIf {
                    it > 0
                }
            ?: selectedVariant
                ?.StoreId
                ?.takeIf {
                    it > 0
                }
            ?: product.store.id

    val selectedColorIdentity =
        selectedSizeOption.colorId
            .takeIf {
                it > 0
            }
            ?: selectedColorVariant.colorId
                .takeIf {
                    it > 0
                }
            ?: selectedVariant
                ?.ColorId
            ?: 0

    val selectedSizeIdentity =
        selectedSizeOption.sizeId
            .takeIf {
                it > 0
            }
            ?: selectedVariant
                ?.SizeId
            ?: 0

    val selection =
        RetailProductDetailSelection(
            productId =
                product.id,
            variantId =
                selectedVariantId,
            priceId =
                selectedPriceId,
            storeId =
                selectedStoreId,
            colorId =
                selectedColorIdentity,
            sizeId =
                selectedSizeIdentity,
            selectedColor =
                selectedColorVariant.name,
            selectedSize =
                selectedSizeOption.label,
            quantity =
                quantity
        )

    Scaffold(
        containerColor =
            MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            RetailSearchHeader(
                searchText =
                    searchText,
                onSearchTextChange = {
                    searchText =
                        it
                },
                onMenuClick =
                    onBackClick,
                onFavoriteClick =
                    onFavoriteClick,
                onMessageClick =
                    onMessageClick,
                placeholder =
                    product.searchPlaceholder,
                onSearchClick = {
                    onSearchClick(
                        searchText
                    )
                },
                onClearClick = {
                    searchText =
                        ""
                },
                leadingAction =
                    RetailSearchHeaderLeadingAction.Back,
                onBackClick =
                    onBackClick
            )
        },
        bottomBar = {
            RetailProductDetailBottomBar(
                isInStock =
                    product.isInStock,
                onAddToBasketClick = {
                    onAddToBasketClick(
                        selection
                    )
                },
                onBuyNowClick = {
                    onBuyNowClick(
                        selection
                    )
                },
                onStockAlarmClick = {
                    onStockAlarmClick(
                        selection
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                    .padding(
                        innerPadding
                    )
                    .verticalScroll(
                        rememberScrollState()
                    )
        ) {
            RetailProductDetailGallery(
                product =
                    product,
                images =
                    visibleImages,
                currentPage =
                    pagerState.currentPage,
                pagerContent = {
                    HorizontalPager(
                        state =
                            pagerState,
                        modifier =
                            Modifier.fillMaxWidth()
                    ) { page ->
                        val image =
                            visibleImages
                                .getOrNull(
                                    page
                                )
                                ?: visibleImages.first()

                        RetailProductDetailImageSlide(
                            image =
                                image
                        )
                    }
                }
            )

            RetailProductTitleCard(
                product =
                    product
            )

            RetailProductRatingSummaryCard(
                ratingText =
                    product.ratingText,
                reviewCount =
                    product.reviewCount,
                onReviewClick =
                    onReviewClick
            )

            RetailProductDetailVariantCard(
                product =
                    product,
                selectedColorVariant =
                    selectedColorVariant,
                selectedSizeOption =
                    selectedSizeOption,
                quantity =
                    quantity,
                onColorClick = { colorVariant ->
                    if (
                        colorVariant.variantId > 0 &&
                        selectedColorId != colorVariant.id
                    ) {
                        selectedColorId =
                            colorVariant.id

                        selectedSizeId =
                            ""

                        quantity =
                            1

                        onColorVariantChange(
                            colorVariant.variantId
                        )
                    }
                },
                onSizeClick = { sizeOption ->
                    if (
                        sizeOption.state !=
                        RetailProductSizeState.Disabled &&
                        sizeOption.state !=
                        RetailProductSizeState.OutOfStock &&
                        sizeOption.variantId > 0 &&
                        selectedSizeId != sizeOption.id
                    ) {
                        selectedSizeId =
                            sizeOption.id

                        quantity =
                            1

                        onSizeVariantChange(
                            sizeOption.variantId
                        )
                    }
                },
                onSizeGuideClick = {
                    activeSheet =
                        RetailProductDetailSheet.SizeGuide

                    onSizeGuideClick()
                },
                onDecreaseQuantityClick = {
                    if (
                        quantity > 1
                    ) {
                        quantity -=
                            1
                    }
                },
                onIncreaseQuantityClick = {
                    quantity +=
                        1
                }
            )

            RetailProductPriceCard(
                product =
                    product
            )

            if (
                !product.isInStock
            ) {
                RetailProductStockAlertCard(
                    onStockAlarmClick = {
                        onStockAlarmClick(
                            selection
                        )
                    }
                )
            }

            RetailProductTrustLinksCard(
                onLowerPriceClick = {
                    activeSheet =
                        RetailProductDetailSheet.LowerPrice

                    onLowerPriceClick()
                },
                onReportAbuseClick = {
                    activeSheet =
                        RetailProductDetailSheet.ReportAbuse

                    onReportAbuseClick()
                },
                onReturnPolicyClick = {
                    activeSheet =
                        RetailProductDetailSheet.ReturnPolicy

                    onReturnPolicyClick()
                }
            )

            if (
                product.sellerProducts.isNotEmpty()
            ) {
                RetailSellerProductsCarousel(
                    products =
                        product.sellerProducts,
                    onProductClick =
                        onSellerProductClick,
                    onStoreClick = {
                        onStoreClick(
                            product.store
                        )
                    }
                )
            }

            if (
                product.reviews.isNotEmpty() ||
                product.reviewCount > 0
            ) {
                RetailReviewCarousel(
                    reviews =
                        product.reviews,
                    ratingText =
                        product.ratingText,
                    reviewCount =
                        product.reviewCount,
                    onReviewClick =
                        onReviewClick
                )
            }

            RetailProductDetailStoreCard(
                store =
                    product.store,
                onStoreClick = {
                    onStoreClick(
                        product.store
                    )
                }
            )

            RetailProductDetailQuickActions(
                product =
                    product,
                onOtherSellerClick =
                    onOtherSellerClick,
                onQuestionClick =
                    onQuestionClick
            )

            RetailProductDescriptionSection(
                description =
                    product.description
            )

            _B2CSponsoredAdverts(
                Adverts =
                    State.SponsoredAdverts,
                onAdvertClick =
                    onSponsoredAdvertClick
            )

            _FromBrands(
                Sections =
                    State.ProductBrandSections,
                onPageClick =
                    onBrandSectionPageClick
            )

            _B2CProductBrowsingHistory(
                Histories =
                    State.ProductBrowsingHistories,
                onProductClick =
                    onBrowsingHistoryProductClick
            )

            _B2CRelatedCategoryLinks(
                Categories =
                    State.RelatedCategories,
                onCategoryClick = { category ->
                    onRelatedCategoryClick(
                        RetailRelatedCategoryChip(
                            id =
                                category.ProductCategoryId,
                            name =
                                category.CategoryName
                        )
                    )
                }
            )

            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space16
                    )
            )
        }
    }

    val currentSheet =
        activeSheet

    if (
        currentSheet != null
    ) {
        ModalBottomSheet(
            onDismissRequest = {
                activeSheet =
                    null
            },
            sheetState =
                sheetState,
            containerColor =
                MaterialTheme.colorScheme.surface
        ) {
            RetailProductDetailSheetContent(
                sheet =
                    currentSheet,
                product =
                    product,
                onCloseClick = {
                    activeSheet =
                        null
                },
                onStoreClick = {
                    activeSheet =
                        null

                    onStoreClick(
                        product.store
                    )
                }
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
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = BBLayout.ProductDetailImageMaxHeight)
                .background(BBColors.White),
            contentAlignment = Alignment.Center
        ) {
            pagerContent()

            if (product.badgeText.isNotBlank()) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(BBSpacing.Space3),
                    shape = BBRadius.PillShape,
                    color = BBColors.Green.Green500
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = BBSpacing.Space3,
                            vertical = BBSpacing.Space1
                        ),
                        text = product.badgeText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BBColors.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            RetailProductDetailImageCounter(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = BBSpacing.Space3,
                        bottom = BBSpacing.Space3
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
            .heightIn(
                max =
                    BBLayout.ProductDetailImageMaxHeight
            )
            .aspectRatio(
                0.92f
            )
            .background(
                image.backgroundColor
            ),
        contentAlignment =
            Alignment.Center
    ) {
        when {
            image.imageUrl.isNotBlank() -> {
                AsyncImage(
                    model =
                        image.imageUrl,
                    contentDescription =
                        image.label,
                    modifier =
                        Modifier.fillMaxSize(),
                    contentScale =
                        ContentScale.Fit
                )
            }

            image.drawableResId != null -> {
                Image(
                    painter =
                        painterResource(
                            id =
                                image.drawableResId
                        ),
                    contentDescription =
                        image.label,
                    modifier =
                        Modifier.fillMaxSize(),
                    contentScale =
                        ContentScale.Fit
                )
            }

            else -> {
                Text(
                    text =
                        image.label,
                    style =
                        MaterialTheme.typography.displaySmall,
                    color =
                        image.foregroundColor,
                    fontWeight =
                        FontWeight.ExtraBold
                )
            }
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
        shape = BBRadius.PillShape,
        color = BBColors.Black.copy(alpha = 0.52f)
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            text = "$currentPage / $totalPage",
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.White,
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
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = product.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onReviewClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(BBIcon.SizeSm)
                    )
                }
            }

            Text(
                text = ratingText.replace("★ ", ""),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.weight(1f),
                text = "$reviewCount değerlendirme",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

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
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space5)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Text(
                    text = "Renk: ${selectedColorVariant.name}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Seçenekler",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Seçenekler",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Row(
                        modifier = Modifier.clickable {
                            onSizeGuideClick()
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Straighten,
                            contentDescription = null,
                            tint = BBColors.Success,
                            modifier = Modifier.size(BBIcon.SizeSm)
                        )

                        Text(
                            text = "Beden Rehberi",
                            style = MaterialTheme.typography.labelMedium,
                            color = BBColors.Success,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Sepete Eklenecek ürün adedi",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                RetailProductQuantityButton(
                    icon = Icons.Outlined.Remove,
                    onClick = onDecreaseQuantityClick
                )

                Text(
                    modifier = Modifier.padding(horizontal = BBSpacing.Space4),
                    text = quantity.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
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
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }

    Surface(
        modifier = Modifier
            .width(BBLayout.FixedWidth92)
            .clip(BBRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.XlShape,
        color = if (isSelected) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            MaterialTheme.colorScheme.surface
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
            modifier = Modifier.padding(BBSpacing.Space2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .clip(BBRadius.LgShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                val previewImage = colorVariant.images.firstOrNull()

                when {
                    previewImage?.imageUrl?.isNotBlank() == true -> {
                        AsyncImage(
                            model =
                                previewImage.imageUrl,
                            contentDescription =
                                colorVariant.name,
                            modifier =
                                Modifier.fillMaxSize(),
                            contentScale =
                                ContentScale.Fit
                        )
                    }

                    previewImage?.drawableResId != null -> {
                        Image(
                            painter =
                                painterResource(
                                    id =
                                        previewImage.drawableResId
                                ),
                            contentDescription =
                                colorVariant.name,
                            modifier =
                                Modifier.fillMaxSize(),
                            contentScale =
                                ContentScale.Fit
                        )
                    }

                    else -> {
                        Text(
                            text =
                                colorVariant.name.take(
                                    2
                                ),
                            style =
                                MaterialTheme.typography.labelMedium,
                            color =
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight =
                                FontWeight.Bold
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Surface(
                    modifier = Modifier.size(14.dp),
                    shape = BBRadius.IconBoxSoft,
                    color = colorVariant.swatchColor,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                ) {}

                Text(
                    text = colorVariant.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
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

    val shape = BBRadius.PillShape

    val backgroundColor = when {
        isSelected -> MaterialTheme.colorScheme.onSurface
        !isEnabled -> MaterialTheme.colorScheme.surfaceVariant
        else -> MaterialTheme.colorScheme.surface
    }

    val textColor = when {
        isSelected -> BBColors.White
        !isEnabled -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.onSurface
    }

    val borderColor = when {
        isSelected -> MaterialTheme.colorScheme.onSurface
        sizeOption.state == RetailProductSizeState.Limited -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.outlineVariant
    }

    val modifier = Modifier
        .width(58.dp)
        .height(42.dp)
        .then(
            if (sizeOption.state == RetailProductSizeState.Limited && !isSelected) {
                Modifier.drawBehind {
                    drawRoundRect(
                        color = borderColor,
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
            .clip(BBRadius.IconBoxSoft)
            .clickable {
                onClick()
            },
        shape = BBRadius.IconBoxSoft,
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.SizeSm)
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
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.Space3,
                end = BBSpacing.PageHorizontal
            ),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Text(
                    text = product.priceText,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.ExtraBold
                )

                if (product.oldPriceText.isNotBlank()) {
                    Text(
                        text = product.oldPriceText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                if (product.discountText.isNotBlank()) {
                    Surface(
                        shape = BBRadius.PillShape,
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                horizontal = BBSpacing.Space2,
                                vertical = BBSpacing.Space1
                            ),
                            text = product.discountText,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                RetailProductBenefitPill(
                    text = product.cargoText,
                    icon = Icons.Outlined.LocalShipping
                )

                RetailProductBenefitPill(
                    text = if (product.isInStock) {
                        product.stockText
                    } else {
                        "Stokta Yok"
                    },
                    icon = Icons.Outlined.Inventory2
                )

                RetailProductBenefitPill(
                    text = "Güvenli Alışveriş",
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
        shape = BBRadius.PillShape,
        color = BBColors.Success.copy(alpha = 0.10f),
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Success.copy(alpha = BBAlpha.OverlayHeavy)
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
                imageVector = icon,
                contentDescription = null,
                tint = BBColors.Success,
                modifier = Modifier.size(BBIcon.Size2Xs)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.Success,
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
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = BBRadius.XlShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.Space3,
                        vertical = BBSpacing.Space3
                    ),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CircleNotifications,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        Text(
                            text = "Stokta Yok",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Bu ürün tekrar stoğa girdiğinde haberdar olabilirsin.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Text(
                        text = "Stok Alarmı",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.Space12)
                    .clip(BBRadius.XxlShape)
                    .clickable {
                        onStockAlarmClick()
                    },
                shape = BBRadius.XxlShape,
                color = BBColors.Success
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.NotificationsActive,
                        contentDescription = null,
                        tint = BBColors.White,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )

                    Spacer(
                        modifier = Modifier.width(BBSpacing.Space2)
                    )

                    Text(
                        text = "Stoğa Gelince Haber Ver",
                        style = MaterialTheme.typography.labelLarge,
                        color = BBColors.White,
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
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
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
            .height(BBSpacing.Space13)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = BBSpacing.Space3
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Surface(
            modifier = Modifier.size(26.dp),
            shape = BBRadius.MdShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )
            }
        }

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BBIcon.SizeMd)
        )
    }
}

@Composable
private fun RetailDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BBSpacing.BorderThin)
            .padding(horizontal = BBSpacing.Space3)
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
            top = BBSpacing.Space5
        ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        RetailSectionTitle(
            title = "Satıcının Diğer Ürünleri",
            actionText = "Mağazaya Git",
            onActionClick = onStoreClick
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = BBSpacing.PageHorizontal),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
            .width(BBLayout.ProductCardWidthSmall)
            .clip(BBRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.Space24)
                    .clip(BBRadius.LgShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
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
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2
            )

            Text(
                text = product.priceText,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
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
            .width(BBLayout.ProductCardWidthSmall)
            .height(BBLayout.ProductCardMediaHeightLarge)
            .clip(BBRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.Storefront,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.SizeLg)
            )

            Spacer(
                modifier = Modifier.height(BBSpacing.Space2)
            )

            Text(
                text = "Diğer Ürünler",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Mağazaya Git",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
            top = BBSpacing.Space3
        ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        RetailSectionTitle(
            title = "Ürün Değerlendirmeleri",
            actionText = "Tüm Yorumlar",
            onActionClick = onReviewClick
        )

        BbCard(
            modifier = Modifier.padding(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal
            ),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Medium,
            onClick = onReviewClick
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    Text(
                        text = ratingText.replace("★ ", ""),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
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
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(BBIcon.SizeSm)
                            )
                        }
                    }

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "$reviewCount Yorum",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Surface(
                    modifier = Modifier.size(34.dp),
                    shape = BBRadius.IconBoxSoft,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = review.avatarText,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface,
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
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = review.dateText,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(BBIcon.Size2Xs)
                    )
                }
            }

            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onStoreClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = BBRadius.LgShape,
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
                        text = store.logoText,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
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
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
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
                    text = "${store.ratingText} Puan . ${store.productCount} Ürün",
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
private fun RetailProductDetailQuickActions(
    product: RetailProductDetail,
    onOtherSellerClick: () -> Unit,
    onQuestionClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
        ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        RetailProductDetailActionRow(
            title = "Diğer Satıcılar",
            subtitle = "${product.otherSellerCount} Satıcı daha bu ürünü sunuyor",
            onClick = onOtherSellerClick
        )

        RetailProductDetailActionRow(
            title = "Soru & Cevap",
            subtitle = "${product.questionCount} Ürün sorusu",
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = subtitle,
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
private fun RetailProductDescriptionSection(
    description: String
) {
    BbCard(
        modifier = Modifier.padding(
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = "Ürün Açıklaması",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
            .padding(horizontal = BBSpacing.PageHorizontal),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        if (actionText.isNotBlank()) {
            Row(
                modifier = Modifier.clickable {
                    onActionClick()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BBIcon.SizeSm)
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
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
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
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.Size2Xs)
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 12.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.Space2,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.Space2
                )
        ) {
            if (isInStock) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    RetailProductBottomActionButton(
                        text = "Sepete Ekle",
                        onClick = onAddToBasketClick,
                        modifier = Modifier.weight(1f),
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        borderColor = MaterialTheme.colorScheme.primary,
                        leadingIcon = Icons.Outlined.ShoppingCart
                    )

                    RetailProductBottomActionButton(
                        text = "Hemen Al",
                        onClick = onBuyNowClick,
                        modifier = Modifier.weight(1f),
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        borderColor = MaterialTheme.colorScheme.primary,
                        leadingIcon = null
                    )
                }
            } else {
                RetailProductBottomActionButton(
                    text = "Stoğa Gelince Haber Ver",
                    onClick = onStockAlarmClick,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = BBColors.Success,
                    contentColor = BBColors.White,
                    borderColor = BBColors.Success,
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
            .height(BBSpacing.Space11)
            .clip(BBRadius.XxlShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.XxlShape,
        color = containerColor,
        border = BorderStroke(
            width = 2.dp,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )

                Spacer(
                    modifier = Modifier.width(BBSpacing.Space2)
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

private enum class RetailProductDetailSheet {
    SizeGuide,
    LowerPrice,
    ReportAbuse,
    ReturnPolicy
}

@Composable
private fun RetailProductDetailSheetContent(
    sheet: RetailProductDetailSheet,
    product: RetailProductDetail,
    onCloseClick: () -> Unit,
    onStoreClick: () -> Unit
) {
    when (sheet) {
        RetailProductDetailSheet.SizeGuide -> {
            RetailSizeGuideSheet(
                onCloseClick = onCloseClick
            )
        }

        RetailProductDetailSheet.LowerPrice -> {
            RetailLowerPriceSheet(
                product = product,
                onCloseClick = onCloseClick
            )
        }

        RetailProductDetailSheet.ReportAbuse -> {
            RetailReportAbuseSheet(
                product = product,
                onCloseClick = onCloseClick
            )
        }

        RetailProductDetailSheet.ReturnPolicy -> {
            RetailReturnPolicySheet(
                onCloseClick = onCloseClick,
                onStoreClick = onStoreClick
            )
        }
    }
}

@Composable
private fun RetailSheetContainer(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onCloseClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.Space8
            ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
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
                        imageVector = icon,
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
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Surface(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .clip(BBRadius.IconBoxSoft)
                    .clickable {
                        onCloseClick()
                    },
                shape = BBRadius.IconBoxSoft,
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
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Kapat",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }
        }

        content()
    }
}

@Composable
private fun RetailSizeGuideSheet(
    onCloseClick: () -> Unit
) {
    RetailSheetContainer(
        title = "Ölçü Rehberi",
        subtitle = "Doğru beden seçimi için kısa rehber",
        icon = Icons.Outlined.Straighten,
        onCloseClick = onCloseClick
    ) {
        RetailSheetInfoBox(
            title = "Pratik Öneri",
            description = "Ölçüm yaparken ürünü kullanacağınız koşulları dikkate alın. Ayakkabı için çorap kalınlığı, kıyafet için kullanım rahatlığı önemlidir."
        )

        RetailSizeGuideTable()

        RetailSheetMutedBox(
            text = "Ölçü tabloları genel bilgilendirme amaçlıdır. Marka, model ve üretim kalıbına göre küçük farklılıklar olabilir."
        )

        RetailSheetPrimaryButton(
            text = "Anladım",
            onClick = onCloseClick
        )
    }
}

@Composable
private fun RetailReturnPolicySheet(
    onCloseClick: () -> Unit,
    onStoreClick: () -> Unit
) {
    RetailSheetContainer(
        title = "İptal ve İade Koşulları",
        subtitle = "Cayma hakkı, iade ve sipariş iptali",
        icon = Icons.Outlined.VerifiedUser,
        onCloseClick = onCloseClick
    ) {
        Text(
            text = "İptal ve İade Süreci",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Sipariş iptali ve iade süreçleri ürün, satıcı, teslimat durumu ve ilgili mevzuat kapsamında değerlendirilir. İade talebinizi sipariş detayınız üzerinden başlatabilirsiniz.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        RetailSheetCheckRow(
            text = "Satıcı tarafından henüz onaylanmamış siparişler iptal edilebilir."
        )

        RetailSheetCheckRow(
            text = "İade koşulları ürün türüne, kullanım durumuna ve satıcı politikalarına göre değişebilir."
        )

        RetailSheetCheckRow(
            text = "Cayma hakkı ve iade süreci yürürlükteki tüketici mevzuatı kapsamında değerlendirilir."
        )

        RetailSheetMutedBox(
            text = "Ürünü iade etmeden önce kullanılmamış ve mümkünse orijinal ambalajında olmasına dikkat edin."
        )

        RetailSheetPrimaryButton(
            text = "Mağazaya Git",
            onClick = onStoreClick
        )
    }
}

@Composable
private fun RetailLowerPriceSheet(
    product: RetailProductDetail,
    onCloseClick: () -> Unit
) {
    var competitorName by remember {
        mutableStateOf("")
    }

    var competitorLink by remember {
        mutableStateOf("")
    }

    var competitorPrice by remember {
        mutableStateOf("")
    }

    RetailSheetContainer(
        title = "Daha Düşük Fiyat mı Gördünüz?",
        subtitle = "Fiyat geri bildirimi",
        icon = Icons.Outlined.WarningAmber,
        onCloseClick = onCloseClick
    ) {
        RetailSheetProductPill(
            text = product.name
        )

        Text(
            text = "Fiyat Bilgilerini Gönder",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Aynı ürünü başka bir platformda daha uygun fiyata gördüyseniz bize bildirin. Bilgiler kontrol edilerek değerlendirilecektir.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        RetailSheetTextField(
            value = competitorName,
            onValueChange = {
                competitorName = it
            },
            label = "Rakip Firma Adı",
            placeholder = "Örn: Amazon, Trendyol, Hepsiburada"
        )

        RetailSheetTextField(
            value = competitorLink,
            onValueChange = {
                competitorLink = it
            },
            label = "Rakip Linki",
            placeholder = "https://..."
        )

        RetailSheetTextField(
            value = competitorPrice,
            onValueChange = {
                competitorPrice = it
            },
            label = "Rakip Fiyatı",
            placeholder = "0,00",
            keyboardType = KeyboardType.Decimal
        )

        RetailSheetMutedBox(
            text = "Lütfen yalnızca herkesin erişebileceği ürün linklerini paylaşın. Sepet, kişisel hesap veya ödeme ekranı bağlantıları göndermeyin."
        )

        RetailSheetPrimaryButton(
            text = "Fiyatı Bildir",
            onClick = onCloseClick
        )
    }
}

@Composable
private fun RetailReportAbuseSheet(
    product: RetailProductDetail,
    onCloseClick: () -> Unit
) {
    val reasons = listOf(
        "Yanıltıcı Bilgi",
        "Uygunsuz Görsel",
        "Sahte Ürün",
        "Yasaklı İçerik"
    )

    var selectedReason by remember {
        mutableStateOf(reasons.first())
    }

    var detailText by remember {
        mutableStateOf("")
    }

    RetailSheetContainer(
        title = "Kötüye Kullanımı Bildir",
        subtitle = "Güvenlik ve kalite bildirimi",
        icon = Icons.Outlined.Report,
        onCloseClick = onCloseClick
    ) {
        RetailSheetProductPill(
            text = product.name
        )

        Text(
            text = "Şikayet Nedeni",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            reasons.forEach { reason ->
                RetailReasonChip(
                    text = reason,
                    selected = selectedReason == reason,
                    onClick = {
                        selectedReason = reason
                    }
                )
            }
        }

        RetailSheetTextField(
            value = detailText,
            onValueChange = {
                detailText = it
            },
            label = "Detay",
            placeholder = "Bildirmek istediğiniz durumu kısaca yazın.",
            minLines = 4
        )

        RetailSheetMutedBox(
            text = "Kişisel bilgi, ödeme bilgisi veya üçüncü kişilere ait özel bilgi paylaşmayın."
        )

        RetailSheetPrimaryButton(
            text = "Gönder",
            onClick = onCloseClick
        )
    }
}


@Composable
private fun RetailReasonChip(
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
            MaterialTheme.colorScheme.surfaceVariant
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
private fun RetailSheetTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    minLines: Int = 1
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        minLines = minLines,
        singleLine = minLines == 1,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun RetailReasonOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(BBRadius.LgShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.LgShape,
        color = if (selected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
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
        Row(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Surface(
                modifier = Modifier.size(18.dp),
                shape = BBRadius.IconBoxSoft,
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
            ) {}

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun RetailSizeGuideTable() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            RetailSizeGuideTableRow(
                first = "Beden",
                second = "Önerilen Ölçü",
                third = "Not",
                strong = true,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )

            RetailSizeGuideTableRow(
                first = "Standart",
                second = "Ürün Açıklamasını Kontrol Edin",
                third = "Marka ve Modele Göre Değişebilir"
            )

            RetailSizeGuideTableRow(
                first = "Dar Kalıp",
                second = "Bir Beden Büyük Tercih Edilebilir",
                third = "Satıcı Notlarını Kontrol Edin"
            )

            RetailSizeGuideTableRow(
                first = "Geniş Kalıp",
                second = "Normal Beden Tercih Edilebilir",
                third = "Müşteri Sorularını İnceleyin"
            )
        }
    }
}

@Composable
private fun RetailSizeGuideTableRow(
    first: String,
    second: String,
    third: String,
    strong: Boolean = false,
    backgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space3
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        RetailTableCell(
            text = first,
            modifier = Modifier.weight(0.75f),
            strong = strong
        )

        RetailTableCell(
            text = second,
            modifier = Modifier.weight(1.35f),
            strong = strong
        )

        RetailTableCell(
            text = third,
            modifier = Modifier.weight(1.4f),
            strong = strong
        )
    }
}

@Composable
private fun RetailTableCell(
    text: String,
    modifier: Modifier,
    strong: Boolean
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = if (strong) {
            FontWeight.Bold
        } else {
            FontWeight.SemiBold
        }
    )
}

@Composable
private fun RetailSheetInfoBox(
    title: String,
    description: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = BBAlpha.DisabledContainer)
        )
    ) {
        Row(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Straighten,
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
}

@Composable
private fun RetailSheetCheckRow(
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Icon(
            imageVector = Icons.Outlined.Verified,
            contentDescription = null,
            tint = BBColors.Green.Green500,
            modifier = Modifier.size(BBIcon.SizeSm)
        )

        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun RetailSheetMutedBox(
    text: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            modifier = Modifier.padding(BBSpacing.Space3),
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RetailSheetProductPill(
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
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Icon(
                imageVector = Icons.Outlined.Inventory2,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(BBIcon.SizeSm)
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
private fun RetailSheetPrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(BBSpacing.Space12)
            .clip(BBRadius.XxlShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Immutable
data class RetailProductDetailSelection(
    val productId: Int,
    val variantId: Int,
    val priceId: Int,
    val storeId: Int,
    val colorId: Int,
    val sizeId: Int,
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
    val store: RetailProductDetailStore
)

@Immutable
data class RetailProductColorVariant(
    val id: String,
    val variantId: Int = 0,
    val priceId: Int = 0,
    val storeId: Int = 0,
    val colorId: Int = 0,
    val sizeId: Int = 0,
    val name: String,
    val swatchColor: Color,
    val images: List<RetailProductImage>
)

@Immutable
data class RetailProductImage(
    val label: String,
    val backgroundColor: Color,
    val foregroundColor: Color,
    val imageUrl: String = "",
    @DrawableRes val drawableResId: Int? = null
)

@Immutable
data class RetailProductSizeOption(
    val id: String,
    val variantId: Int = 0,
    val priceId: Int = 0,
    val storeId: Int = 0,
    val colorId: Int = 0,
    val sizeId: Int = 0,
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

private fun ResolveRetailProductImageUrl(
    imagePath: String
): String {
    val normalizedPath =
        imagePath.trim()

    if (
        normalizedPath.isBlank()
    ) {
        return ""
    }

    if (
        normalizedPath.startsWith(
            "http://",
            ignoreCase =
                true
        ) ||
        normalizedPath.startsWith(
            "https://",
            ignoreCase =
                true
        )
    ) {
        return normalizedPath
    }

    val baseUrl =
        ApiRoutes.B2C_BASE_URL
            .substringBefore(
                "/api/"
            )
            .trimEnd(
                '/'
            )

    return "$baseUrl/${normalizedPath.trimStart('/')}"
}

private fun ProductDTO.ToRetailProductDetail(
    variantPictures: List<ProductVariantPictureDTO>,
    colorVariants: List<ProductVariantDTO>,
    sizeVariants: List<ProductVariantDTO>,
    otherStorePrices: List<ProductVariantDTO>,
    smallestPrice: ProductVariantDTO?,
    selectedVariant: ProductVariantDTO?
): RetailProductDetail {
    val fallbackProduct =
        getRetailProductDetail(
            productId =
                ProductId
        )

    val activeVariant =
        selectedVariant
            ?: ProductVariantDTO(
                VariantId =
                    VariantId,
                ProductId =
                    ProductId,
                ColorId =
                    ColorId,
                Color =
                    Color,
                SizeId =
                    SizeId,
                Size =
                    Size,
                StoreId =
                    StoreId,
                Store =
                    Store,
                ProductVariantPriceId =
                    ProductVariantPriceId,
                Price =
                    Price,
                CurrencySymbol =
                    CurrencySymbol,
                Stock =
                    Stock,
                DefaultPicture =
                    DefaultPicture,
                Picture =
                    Picture,
                Rating =
                    StoreRating
            )

    val activeVariantId =
        activeVariant.VariantId
            .takeIf {
                it > 0
            }
            ?: VariantId

    val activePrice =
        activeVariant.Price
            .takeIf {
                it > 0.0
            }
            ?: smallestPrice
                ?.Price
                ?.takeIf {
                    it > 0.0
                }
            ?: Price

    val activeCurrencySymbol =
        activeVariant.CurrencySymbol
            .takeIf {
                it.isNotBlank()
            }
            ?: smallestPrice
                ?.CurrencySymbol
                ?.takeIf {
                    it.isNotBlank()
                }
            ?: CurrencySymbol

    val activeStock =
        if (
            selectedVariant != null
        ) {
            selectedVariant.Stock
        } else {
            Stock
        }

    val resolvedVariantImages =
        variantPictures
            .sortedWith(
                compareByDescending<ProductVariantPictureDTO> {
                    it.IsDefault
                }.thenBy {
                    it.Sorting
                }
            )
            .mapNotNull { picture ->
                val rawPicturePath =
                    if (picture.Picture.isNotBlank()) {
                        picture.Picture
                    } else {
                        picture.DirectoryName +
                                picture.PictureName
                    }

                val imageUrl =
                    ResolveRetailProductImageUrl(
                        imagePath =
                            rawPicturePath
                    )

                if (imageUrl.isBlank()) {
                    null
                } else {
                    RetailProductImage(
                        label =
                            picture.PictureName
                                .ifBlank {
                                    ProductName
                                },
                        backgroundColor =
                            BBColors.White,
                        foregroundColor =
                            BBColors.Gray.Gray500,
                        imageUrl =
                            imageUrl
                    )
                }
            }

    val resolvedColorVariants =
        colorVariants
            .distinctBy {
                it.ColorId
            }
            .map { colorVariant ->
                val colorImageUrl =
                    ResolveRetailProductImageUrl(
                        imagePath =
                            colorVariant.DefaultPicture
                                .takeIf {
                                    it.isNotBlank()
                                }
                                ?: colorVariant.Picture
                    )

                val isActiveColor =
                    colorVariant.ColorId > 0 &&
                            colorVariant.ColorId ==
                            activeVariant.ColorId

                RetailProductColorVariant(
                    id =
                        colorVariant.ColorId
                            .toString(),
                    variantId =
                        colorVariant.VariantId,
                    priceId =
                        colorVariant.ProductVariantPriceId,
                    storeId =
                        colorVariant.StoreId,
                    colorId =
                        colorVariant.ColorId,
                    sizeId =
                        colorVariant.SizeId,
                    name =
                        colorVariant.Color
                            .takeIf {
                                it.isNotBlank()
                            }
                            ?: "Standart",
                    swatchColor =
                        BBColors.Gray.Gray400,
                    images =
                        if (
                            isActiveColor &&
                            resolvedVariantImages.isNotEmpty()
                        ) {
                            resolvedVariantImages
                        } else {
                            listOfNotNull(
                                colorImageUrl
                                    .takeIf {
                                        it.isNotBlank()
                                    }
                                    ?.let { imageUrl ->
                                        RetailProductImage(
                                            label =
                                                colorVariant.Color
                                                    .takeIf {
                                                        it.isNotBlank()
                                                    }
                                                    ?: ProductName,
                                            backgroundColor =
                                                BBColors.White,
                                            foregroundColor =
                                                BBColors.Gray.Gray500,
                                            imageUrl =
                                                imageUrl
                                        )
                                    }
                            )
                        }
                )
            }

    val resolvedSizeVariants =
        sizeVariants
            .distinctBy {
                it.SizeId
            }
            .map { sizeVariant ->
                RetailProductSizeOption(
                    id =
                        sizeVariant.SizeId
                            .toString(),
                    variantId =
                        sizeVariant.VariantId,
                    priceId =
                        sizeVariant.ProductVariantPriceId,
                    storeId =
                        sizeVariant.StoreId,
                    colorId =
                        sizeVariant.ColorId,
                    sizeId =
                        sizeVariant.SizeId,
                    label =
                        sizeVariant.Size
                            .takeIf {
                                it.isNotBlank()
                            }
                            ?: "Standart",
                    state =
                        when {
                            sizeVariant.VariantId ==
                                    activeVariantId -> {
                                RetailProductSizeState.Selected
                            }

                            sizeVariant.Stock <= 0 -> {
                                RetailProductSizeState.OutOfStock
                            }

                            sizeVariant.Stock <= 3 -> {
                                RetailProductSizeState.Limited
                            }

                            else -> {
                                RetailProductSizeState.Available
                            }
                        }
                )
            }

    val resolvedOtherStoreProducts =
        otherStorePrices
            .filter {
                it.StoreId > 0
            }
            .map { storeVariant ->
                RetailSellerProductItem(
                    id =
                        storeVariant.StoreId,
                    name =
                        storeVariant.Store
                            .takeIf {
                                it.isNotBlank()
                            }
                            ?: "Satıcı",
                    priceText =
                        FormatRetailProductPrice(
                            price =
                                storeVariant.Price,
                            currencySymbol =
                                storeVariant.CurrencySymbol
                        ),
                    imageLabel =
                        storeVariant.Store
                            .takeIf {
                                it.isNotBlank()
                            }
                            ?: "Satıcı"
                )
            }

    val resolvedColorName =
        activeVariant.Color
            .takeIf {
                it.isNotBlank()
            }
            ?: Color.takeIf {
                it.isNotBlank()
            }
            ?: "Standart"

    val resolvedColorId =
        activeVariantId
            .takeIf {
                it > 0
            }
            ?.toString()
            ?: activeVariant.ColorId
                .takeIf {
                    it > 0
                }
                ?.toString()
            ?: "default"

    val resolvedSizeName =
        activeVariant.Size
            .takeIf {
                it.isNotBlank()
            }
            ?: Size.takeIf {
                it.isNotBlank()
            }
            ?: "Standart"

    val resolvedSizeId =
        activeVariantId
            .takeIf {
                it > 0
            }
            ?.toString()
            ?: activeVariant.SizeId
                .takeIf {
                    it > 0
                }
                ?.toString()
            ?: "default"

    val resolvedBrandName =
        BrandData
            ?.Brand
            ?.takeIf {
                it.isNotBlank()
            }
            ?: fallbackProduct.brandName

    val resolvedStoreName =
        Store.takeIf {
            it.isNotBlank()
        } ?: "Satıcı"

    val resolvedStoreRating =
        StoreRating
            .takeIf {
                it > 0.0
            }
            ?.let { storeRating ->
                java.lang.String.format(
                    java.util.Locale(
                        "tr",
                        "TR"
                    ),
                    "%.1f",
                    storeRating
                )
            }
            ?: ""

    val resolvedRating =
        Rating
            .takeIf {
                it > 0.0
            }
            ?.let { rating ->
                "★ ${
                    java.lang.String.format(
                        java.util.Locale(
                            "tr",
                            "TR"
                        ),
                        "%.1f",
                        rating
                    )
                }"
            }
            ?: ""

    val resolvedReviewCount =
        ReviewNumber
            ?: 0

    val resolvedDescription =
        Description.takeIf {
            it.isNotBlank()
        } ?: "Bu ürün için açıklama bilgisi bulunmamaktadır."

    val resolvedCategoryName =
        CategoryName.takeIf {
            it.isNotBlank()
        } ?: fallbackProduct.categoryName

    val resolvedPicture =
        DefaultPicture
            .takeIf {
                it.isNotBlank()
            }
            ?: Picture

    val resolvedPictureUrl =
        ResolveRetailProductImageUrl(
            imagePath =
                resolvedPicture
        )

    val resolvedProductName =
        ProductName.takeIf {
            it.isNotBlank()
        } ?: "Ürün"

    val resolvedStoreLogoText =
        resolvedStoreName
            .split(
                " "
            )
            .filter {
                it.isNotBlank()
            }
            .take(
                2
            )
            .mapNotNull {
                it.firstOrNull()
                    ?.uppercase()
            }
            .joinToString(
                separator =
                    ""
            )
            .takeIf {
                it.isNotBlank()
            }
            ?: "ST"

    return RetailProductDetail(
        id =
            ProductId,
        name =
            resolvedProductName,
        brandName =
            resolvedBrandName,
        searchPlaceholder =
            "Ürün, kategori veya marka ara",
        shortDescription =
            resolvedDescription,
        description =
            resolvedDescription,
        categoryName =
            resolvedCategoryName,
        priceText =
            FormatRetailProductPrice(
                price =
                    activePrice,
                currencySymbol =
                    activeCurrencySymbol
            ),
        oldPriceText =
            "",
        discountText =
            "",
        badgeText =
            "",
        ratingText =
            resolvedRating,
        cargoText =
            ShippingDuration
                .takeIf {
                    it > 0
                }
                ?.let {
                    "$it günde kargo"
                }
                ?: "",
        stockText =
            if (
                activeStock > 0
            ) {
                "Stokta $activeStock adet var"
            } else {
                "Stokta yok"
            },
        isInStock =
            activeStock > 0,
        reviewCount =
            resolvedReviewCount,
        questionCount =
            0,
        otherSellerCount =
            resolvedOtherStoreProducts.size,
        colorVariants =
            resolvedColorVariants
                .ifEmpty {
                    listOf(
                        RetailProductColorVariant(
                            id =
                                resolvedColorId,
                            name =
                                resolvedColorName,
                            swatchColor =
                                BBColors.Gray.Gray400,
                            images =
                                resolvedVariantImages
                                    .ifEmpty {
                                        listOf(
                                            RetailProductImage(
                                                label =
                                                    resolvedProductName,
                                                backgroundColor =
                                                    BBColors.White,
                                                foregroundColor =
                                                    BBColors.Gray.Gray500,
                                                imageUrl =
                                                    resolvedPictureUrl,
                                                drawableResId =
                                                    if (
                                                        resolvedPictureUrl.isBlank()
                                                    ) {
                                                        fallbackProduct
                                                            .colorVariants
                                                            .firstOrNull()
                                                            ?.images
                                                            ?.firstOrNull()
                                                            ?.drawableResId
                                                    } else {
                                                        null
                                                    }
                                            )
                                        )
                                    }
                        )
                    )
                },
        sizeOptions =
            resolvedSizeVariants
                .ifEmpty {
                    listOf(
                        RetailProductSizeOption(
                            id =
                                resolvedSizeId,
                            label =
                                resolvedSizeName,
                            state =
                                if (
                                    activeStock > 0
                                ) {
                                    RetailProductSizeState.Selected
                                } else {
                                    RetailProductSizeState.OutOfStock
                                }
                        )
                    )
                },
        sellerProducts =
            resolvedOtherStoreProducts,
        reviews =
            emptyList(),
        store =
            RetailProductDetailStore(
                id =
                    StoreId,
                name =
                    resolvedStoreName,
                logoText =
                    resolvedStoreLogoText,
                ratingText =
                    resolvedStoreRating,
                productCount =
                    0,
                isVerified =
                    false
            )
    )
}

private fun FormatRetailProductPrice(
    price: Double,
    currencySymbol: String
): String {
    val formatter =
        java.text.NumberFormat.getNumberInstance(
            java.util.Locale(
                "tr",
                "TR"
            )
        ).apply {
            minimumFractionDigits =
                2

            maximumFractionDigits =
                2
        }

    return buildString {
        if (
            currencySymbol.isNotBlank()
        ) {
            append(
                currencySymbol
            )

            append(
                " "
            )
        }

        append(
            formatter.format(
                price
            )
        )
    }
}

private fun getRetailProductDetail(
    productId: Int
): RetailProductDetail {
    val sellerProducts = (1..12).map { index ->
        RetailSellerProductItem(
            id = index,
            name = "Ortobella Günlük Sneaker Modeli $index",
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
        name = "Kadın Klasik Sneaker Ayakkabı",
        brandName = "Ortobella",
        searchPlaceholder = "Ürün, kategori veya marka ara",
        shortDescription = "Günlük kullanım için rahat tabanlı, sade ve modern sneaker modeli.",
        description = "Hafif tabanı, yumuşak iç yüzeyi ve günlük kombinlere uyum sağlayan sade tasarımıyla şehir içi kullanım için hazırlanmıştır. Ürün kalıbı standarttır. Taraklı ayaklarda yarım numara büyük tercih edilebilir.",
        categoryName = "Ayakkabı",
        priceText = "₺899,90",
        oldPriceText = "₺1.099,90",
        discountText = "%20 İndirim",
        badgeText = "%20",
        ratingText = "★ 4.8",
        cargoText = "Hızlı Kargo",
        stockText = "Stokta Var",
        isInStock = true,
        reviewCount = 126,
        questionCount = 18,
        otherSellerCount = 5,
        colorVariants = listOf(
            RetailProductColorVariant(
                id = "white",
                name = "Beyaz",
                swatchColor = BBColors.White,
                images = listOf(
                    RetailProductImage(
                        label = "Beyaz 1",
                        backgroundColor = BBColors.White,
                        foregroundColor = BBColors.Gray.Gray500,
                        drawableResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach
                    ),
                    RetailProductImage(
                        label = "Beyaz 2",
                        backgroundColor = BBColors.White,
                        foregroundColor = BBColors.Gray.Gray500,
                        drawableResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach
                    ),
                    RetailProductImage(
                        label = "Beyaz 3",
                        backgroundColor = BBColors.White,
                        foregroundColor = BBColors.Gray.Gray500,
                        drawableResId = R.drawable.h3ff3b33d6a1447c898cee6e336867bach
                    )
                )
            ),
            RetailProductColorVariant(
                id = "black",
                name = "Siyah",
                swatchColor = BBColors.Black,
                images = listOf(
                    RetailProductImage(
                        label = "Siyah 1",
                        backgroundColor = BBColors.Coal.Coal100,
                        foregroundColor = BBColors.Gray.Gray300
                    ),
                    RetailProductImage(
                        label = "Siyah 2",
                        backgroundColor = BBColors.Coal.Coal200,
                        foregroundColor = BBColors.Gray.Gray200
                    )
                )
            ),
            RetailProductColorVariant(
                id = "beige",
                name = "Bej",
                swatchColor = BBColors.Beige.Beige400,
                images = listOf(
                    RetailProductImage(
                        label = "Bej 1",
                        backgroundColor = BBColors.Beige.Beige100,
                        foregroundColor = BBColors.Beige.Beige700
                    ),
                    RetailProductImage(
                        label = "Bej 2",
                        backgroundColor = BBColors.Beige.Beige200,
                        foregroundColor = BBColors.Beige.Beige800
                    )
                )
            ),
            RetailProductColorVariant(
                id = "gray",
                name = "Gri",
                swatchColor = BBColors.Gray.Gray400,
                images = listOf(
                    RetailProductImage(
                        label = "Gri 1",
                        backgroundColor = BBColors.Gray.Gray100,
                        foregroundColor = BBColors.Gray.Gray600
                    ),
                    RetailProductImage(
                        label = "Gri 2",
                        backgroundColor = BBColors.Gray.Gray200,
                        foregroundColor = BBColors.Gray.Gray700
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

