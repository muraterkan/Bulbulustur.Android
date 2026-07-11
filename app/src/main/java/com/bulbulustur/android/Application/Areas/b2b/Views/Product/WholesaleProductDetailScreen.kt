package com.bulbulustur.android.Application.Areas.b2b.Views.Product

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import coil3.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductControllerState
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout

@Composable
fun WholesaleProductDetailScreen(
    State: ProductControllerState = ProductControllerState(),
    productId: Int = 1,
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {},
    onCompanyClick: (WholesaleProductDetailCompany) -> Unit = {},
    onCompanyProductsClick: () -> Unit = {},
    onRelatedProductClick: (WholesaleMiniProduct) -> Unit = {},
    onCompanyBestSellerProductClick: (WholesaleMiniProduct) -> Unit = {},
    onRelatedProductsClick: () -> Unit = {},
    onCompanyBestSellerProductsClick: () -> Unit = onCompanyProductsClick,
    onRelatedCategoryClick: (WholesaleRelatedCategoryChip) -> Unit = {}
) {
    val productDto = State.ProductDetailResult?.Data
    val relatedProductDtos = State.RelatedProductsResult?.Data.orEmpty()
    val relatedCategoryDtos = State.RelatedCategories

    val product = remember(productDto, relatedProductDtos, relatedCategoryDtos) {
        productDto?.ToWholesaleProductDetail(
            relatedProducts = relatedProductDtos,
            relatedCategories = relatedCategoryDtos
        )
    }


    
    val isDetailLoading =
        State.IsLoading &&
                State.CurrentAction == "Detail" &&
                product == null

    val detailErrorMessage =
        State.ErrorMessage
            ?.takeIf {
                it.isNotBlank()
            }

    val isProductNotFound =
        !State.IsLoading &&
                State.ProductDetailResult?.Success == true &&
                product == null

if (isDetailLoading) {
        WholesaleProductDetailStateScaffold(
            onBackClick = onBackClick,
            onFavoriteClick = onFavoriteClick,
            onMessageClick = onMessageClick,
            isLoading = true
        )

        return
    }

    if (detailErrorMessage != null) {
        WholesaleProductDetailStateScaffold(
            onBackClick = onBackClick,
            onFavoriteClick = onFavoriteClick,
            onMessageClick = onMessageClick,
            title = "Ürün bilgileri alınamadı",
            description = detailErrorMessage
        )

        return
    }

    if (isProductNotFound || product == null) {
        WholesaleProductDetailStateScaffold(
            onBackClick = onBackClick,
            onFavoriteClick = onFavoriteClick,
            onMessageClick = onMessageClick,
            title = "Ürün bulunamadı",
            description = "Bu ürün kaldırılmış veya artık yayında olmayabilir."
        )

        return
    }

    var searchText by remember {
        mutableStateOf("")
    }

    val pagerState = rememberPagerState(
        pageCount = {
            maxOf(
                1,
                product.images.size
            )
        }
    )

    Scaffold(
        containerColor = BBColors.SurfaceMuted,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = {},
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = product.searchPlaceholder,
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                leadingAction = WholesaleSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            WholesaleProductDetailBottomBar(
                onLastPriceRequestClick = onLastPriceRequestClick,
                onSampleRequestClick = onSampleRequestClick,
                onCustomizationRequestClick = onCustomizationRequestClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BBColors.SurfaceMuted)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            if (product.images.isNotEmpty()) {
                WholesaleProductDetailGallery(
                    product = product,
                    currentPage = pagerState.currentPage,
                    pagerContent = {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->
                            WholesaleProductDetailImageSlide(
                                image = product.images[page]
                            )
                        }
                    }
                )
            }

            WholesaleProductTitleCard(
                product = product
            )

            WholesaleTradeSummaryCard(
                product = product
            )

            if (product.priceBreaks.isNotEmpty()) {
                WholesalePriceBreakdownCard(
                    priceBreaks = product.priceBreaks,
                    onClick = onLastPriceRequestClick
                )
            }

            if (product.highlightFeatures.isNotEmpty()) {
                WholesaleFeatureGridCard(
                    features = product.highlightFeatures
                )
            }

            if (product.customizationOptions.isNotEmpty()) {
                WholesaleCustomizationOptionsCard(
                    options = product.customizationOptions,
                    onCustomizationRequestClick = onCustomizationRequestClick
                )
            }

            if (
                product.packagingDescription.isNotBlank() ||
                product.deliveryDescription.isNotBlank() ||
                product.deliverySteps.isNotEmpty()
            ) {
                WholesaleOrderAndDeliveryCard(
                    product = product
                )
            }

            WholesaleSecureTradeCard()

            if (product.company.name.isNotBlank()) {
                WholesaleCompanyDeepCard(
                    company = product.company,
                    onCompanyProfileClick = {
                        onCompanyClick(product.company)
                    },
                    onCompanyProductsClick = onCompanyProductsClick
                )
            }

            if (product.longDescription.isNotBlank()) {
                WholesaleProductDescriptionCard(
                    product = product
                )
            }

            if (product.properties.isNotEmpty()) {
                WholesaleProductInformationCard(
                    properties = product.properties
                )
            }

            if (product.relatedProducts.isNotEmpty()) {
                WholesaleHorizontalProductSection(
                    title = "Benzer Ürünler",
                    products = product.relatedProducts,
                    onHeaderClick = onRelatedProductsClick,
                    onProductClick = onRelatedProductClick
                )
            }

            if (product.companyBestSellerProducts.isNotEmpty()) {
                WholesaleHorizontalProductSection(
                    title = "Bu Firmanın Çok Satanları",
                    products = product.companyBestSellerProducts,
                    onHeaderClick = onCompanyBestSellerProductsClick,
                    onProductClick = onCompanyBestSellerProductClick
                )
            }

            if (product.relatedCategories.isNotEmpty()) {
                WholesaleRelatedCategoryChipsSection(
                    categories = product.relatedCategories,
                    onCategoryClick = onRelatedCategoryClick
                )
            }

            Spacer(
                modifier = Modifier.height(BBSpacing.Space6)
            )
        }
    }
}

@Composable
fun ProductDetailScreen(
    State: ProductControllerState = ProductControllerState(),
    productId: Int = 1,
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {},
    onCompanyClick: (WholesaleProductDetailCompany) -> Unit = {},
    onCompanyProductsClick: () -> Unit = {},
    onRelatedProductClick: (WholesaleMiniProduct) -> Unit = {},
    onCompanyBestSellerProductClick: (WholesaleMiniProduct) -> Unit = {},
    onRelatedProductsClick: () -> Unit = {},
    onCompanyBestSellerProductsClick: () -> Unit = onCompanyProductsClick,
    onRelatedCategoryClick: (WholesaleRelatedCategoryChip) -> Unit = {}
) {
    WholesaleProductDetailScreen(
        State = State,
        productId = productId,
        onBackClick = onBackClick,
        onSearchClick = onSearchClick,
        onFavoriteClick = onFavoriteClick,
        onMessageClick = onMessageClick,
        onMoreClick = onMoreClick,
        onLastPriceRequestClick = onLastPriceRequestClick,
        onSampleRequestClick = onSampleRequestClick,
        onCustomizationRequestClick = onCustomizationRequestClick,
        onCompanyClick = onCompanyClick,
        onCompanyProductsClick = onCompanyProductsClick,
        onRelatedProductClick = onRelatedProductClick,
        onCompanyBestSellerProductClick = onCompanyBestSellerProductClick,
        onRelatedProductsClick = onRelatedProductsClick,
        onCompanyBestSellerProductsClick = onCompanyBestSellerProductsClick,
        onRelatedCategoryClick = onRelatedCategoryClick
    )
}

@Composable
private fun WholesaleProductDetailStateScaffold(
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onMessageClick: () -> Unit,
    isLoading: Boolean = false,
    title: String = "",
    description: String = ""
) {
    var searchText by remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = BBColors.SurfaceMuted,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = {},
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = "Toptan Ürün, Kategori Veya Firma Ara",
                onSearchClick = {},
                onClearClick = {
                    searchText = ""
                },
                leadingAction = WholesaleSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BBColors.SurfaceMuted)
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = BBColors.Primary
                )
            } else {
                BbCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BBSpacing.PageHorizontal),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Inventory2,
                            contentDescription = null,
                            tint = BBColors.TextMuted,
                            modifier = Modifier.size(BBIcon.BoxLg)
                        )

                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            color = BBColors.TextStrong,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall,
                            color = BBColors.TextMuted
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleProductDetailGallery(product: WholesaleProductDetail, currentPage: Int, pagerContent: @Composable () -> Unit)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BBColors.Surface)
            .heightIn(max = 390.dp),
        contentAlignment = Alignment.Center
    ) {
        pagerContent()

        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(BBSpacing.Space3),
            shape = BBRadius.PillShape,
            color = BBColors.Primary
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
                    imageVector = Icons.Outlined.Business,
                    contentDescription = null,
                    tint = BBColors.TextStrong,
                    modifier = Modifier.size(BBIcon.Size2Xs)
                )

                Text(
                    text = product.badgeText,
                    style = MaterialTheme.typography.labelSmall,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        WholesaleProductDetailImageCounter(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = BBSpacing.Space3,
                    bottom = BBSpacing.Space3
                ),
            currentPage = currentPage + 1,
            totalPage = product.images.size
        )
    }
}

@Composable
private fun WholesaleProductDetailImageSlide(
    image: WholesaleProductImage
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = BBLayout.ProductDetailImageMaxHeight)
            .aspectRatio(0.92f)
            .background(image.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        when {
            image.imageUrl.isNotBlank() -> {
                AsyncImage(
                    model = image.imageUrl,
                    contentDescription = image.label,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            image.drawableResId != null -> {
                Image(
                    painter = painterResource(id = image.drawableResId),
                    contentDescription = image.label,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            else -> {
                Icon(
                    imageVector = Icons.Outlined.Inventory2,
                    contentDescription = image.label,
                    tint = image.foregroundColor,
                    modifier = Modifier.size(BBIcon.BoxXl)
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductDetailImageCounter(
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
            text = "Fotoğraflar $currentPage/$totalPage",
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun WholesaleProductTitleCard(
    product: WholesaleProductDetail
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
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                WholesaleMiniPill(
                    text = product.badgeText,
                    icon = Icons.Outlined.Business,
                    containerColor = BBColors.PrimarySoft,
                    contentColor = BBColors.TextStrong
                )

                if (product.company.isVerified) {
                    WholesaleMiniPill(
                        text = "Doğrulanmış Firma",
                        icon = Icons.Outlined.Verified,
                        containerColor = BBColors.Green.Green50,
                        contentColor = BBColors.Green.Green700
                    )
                }

                if (product.customizationOptions.isNotEmpty()) {
                    WholesaleMiniPill(
                        text = "Özelleştirilebilir",
                        icon = Icons.Outlined.Tune,
                        containerColor = BBColors.Blue.Blue50,
                        contentColor = BBColors.Blue.Blue700
                    )
                }
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            if (
                product.soldCountText.isNotBlank() ||
                product.company.ratingText.isNotBlank()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    if (product.soldCountText.isNotBlank()) {
                        Text(
                            text = "${product.soldCountText} Görüntülenme",
                            style = MaterialTheme.typography.bodySmall,
                            color = BBColors.TextMuted
                        )
                    }

                    if (
                        product.soldCountText.isNotBlank() &&
                        product.company.ratingText.isNotBlank()
                    ) {
                        Text(
                            text = "•",
                            style = MaterialTheme.typography.bodySmall,
                            color = BBColors.TextMuted
                        )
                    }

                    if (product.company.ratingText.isNotBlank()) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = null,
                            tint = BBColors.Orange.Orange500,
                            modifier = Modifier.size(BBIcon.SizeSm)
                        )

                        Text(
                            text = product.company.ratingText,
                            style = MaterialTheme.typography.bodySmall,
                            color = BBColors.TextStrong,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Firma Puanı",
                            style = MaterialTheme.typography.bodySmall,
                            color = BBColors.TextMuted
                        )
                    }
                }
            }

            if (product.shortDescription.isNotBlank()) {
                Text(
                    text = product.shortDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BBColors.TextSubtle
                )
            }
        }
    }
}

@Composable
private fun WholesaleTradeSummaryCard(
    product: WholesaleProductDetail
) {
    val firstRowMetrics = buildList {
        if (product.priceLabel.isNotBlank()) {
            add("Fiyat" to product.priceLabel)
        }

        if (product.minimumOrderLabel.isNotBlank()) {
            add("Min. Sipariş" to product.minimumOrderLabel)
        }
    }

    val secondRowMetrics = buildList {
        if (product.deliveryTimeLabel.isNotBlank()) {
            add("Üretim Süresi" to product.deliveryTimeLabel)
        }

        if (product.originCountry.isNotBlank()) {
            add("Menşei" to product.originCountry)
        }
    }

    if (
        firstRowMetrics.isEmpty() &&
        secondRowMetrics.isEmpty() &&
        product.tradeBenefits.isEmpty()
    ) {
        return
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.Space3,
                end = BBSpacing.PageHorizontal
            ),
        shape = BBRadius.XlShape,
        color = BBColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Primary
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            WholesaleSectionLabel(
                text = "Ticari Teklif Özeti",
                icon = Icons.Outlined.RequestQuote
            )

            if (firstRowMetrics.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    firstRowMetrics.forEach { metric ->
                        WholesaleSummaryMetric(
                            metric.first,
                            metric.second,
                            Modifier.weight(1f)
                        )
                    }

                    repeat(2 - firstRowMetrics.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            if (secondRowMetrics.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    secondRowMetrics.forEach { metric ->
                        WholesaleSummaryMetric(
                            metric.first,
                            metric.second,
                            Modifier.weight(1f)
                        )
                    }

                    repeat(2 - secondRowMetrics.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            if (product.tradeBenefits.isNotEmpty()) {
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    product.tradeBenefits.forEach { benefit ->
                        WholesaleBenefitPill(
                            text = benefit.text,
                            icon = benefit.icon
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesalePriceBreakdownCard(
    priceBreaks: List<WholesalePriceBreak>,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space3,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None,
        onClick = onClick
    ) {
        Column {
            WholesaleCardHeader(
                title = "Adede Göre Teklif",
                icon = Icons.Outlined.Paid,
                onClick = onClick
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = BBSpacing.Space3,
                        end = BBSpacing.Space3,
                        bottom = BBSpacing.Space3
                    ),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                priceBreaks.forEach { item ->
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = BBRadius.LgShape,
                        color = BBColors.SurfaceSoft,
                        border = BorderStroke(
                            width = 1.dp,
                            color = BBColors.Border
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(BBSpacing.Space3),
                            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                        ) {
                            Text(
                                text = item.priceLabel,
                                style = MaterialTheme.typography.titleSmall,
                                color = BBColors.TextStrong,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = item.quantityLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = BBColors.TextMuted
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleFeatureGridCard(
    features: List<WholesaleHighlightFeature>
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            WholesaleSectionLabel(
                text = "Öne Çıkan Özellikler",
                icon = Icons.Outlined.Inventory2
            )

            features.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    rowItems.forEach { feature ->
                        WholesaleFeatureCell(
                            feature = feature,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleFeatureCell(
    feature: WholesaleHighlightFeature,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.heightIn(min = 78.dp),
        shape = BBRadius.LgShape,
        color = BBColors.SurfaceSoft,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = feature.value,
                style = MaterialTheme.typography.titleSmall,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = feature.label,
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextMuted
            )
        }
    }
}

@Composable
private fun WholesaleCustomizationOptionsCard(options: List<WholesaleCustomizationOption>, onCustomizationRequestClick: () -> Unit)
{
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
            WholesaleCardHeaderInline(
                title = "Özelleştirme Seçenekleri",
                icon = Icons.Outlined.Tune,
                onClick = onCustomizationRequestClick
            )

            options.forEachIndexed { index, option ->
                WholesaleCheckInfoBlock(
                    title = option.title,
                    description = option.description
                )

                if (index != options.lastIndex) {
                    WholesaleDashedDivider()
                }
            }

            WholesaleInlineAction(
                text = "Özelleştirme Talebi Oluştur",
                icon = Icons.Outlined.Tune,
                onClick = onCustomizationRequestClick
            )
        }
    }
}

@Composable
private fun WholesaleOrderAndDeliveryCard(
    product: WholesaleProductDetail
) {
    if (
        product.packagingDescription.isBlank() &&
        product.deliveryDescription.isBlank() &&
        product.deliverySteps.isEmpty()
    ) {
        return
    }

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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            WholesaleSectionLabel(
                text = "Üretim ve Teslimat",
                icon = Icons.Outlined.LocalShipping
            )

            if (product.deliverySteps.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    product.deliverySteps.forEach { step ->
                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = BBRadius.LgShape,
                            color = BBColors.SurfaceSoft,
                            border = BorderStroke(
                                width = 1.dp,
                                color = BBColors.Border
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(BBSpacing.Space3),
                                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                            ) {
                                Text(
                                    text = step.quantityLabel,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = BBColors.TextMuted
                                )

                                Text(
                                    text = step.timeLabel,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = BBColors.TextStrong,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            if (product.packagingDescription.isNotBlank()) {
                WholesaleInfoBlock(
                    title = "Ambalaj ve Sevkiyat",
                    description = product.packagingDescription
                )
            }

            if (
                product.packagingDescription.isNotBlank() &&
                product.deliveryDescription.isNotBlank()
            ) {
                WholesaleDashedDivider()
            }

            if (product.deliveryDescription.isNotBlank()) {
                WholesaleInfoBlock(
                    title = "Teslimat Notu",
                    description = product.deliveryDescription
                )
            }
        }
    }
}

@Composable
private fun WholesaleSecureTradeCard() {
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            WholesaleSectionLabel(
                text = "Bulbulustur Güvenli Ticaret",
                icon = Icons.Outlined.Security
            )

            Text(
                text = "Teklif, numune ve özelleştirme taleplerini kayıt altına alarak firma ile güvenli ticari iletişim başlat.",
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextSubtle
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                WholesaleTrustMetric("Güvenli Teklif", Icons.Outlined.RequestQuote, Modifier.weight(1f))
                WholesaleTrustMetric("Numune Desteği", Icons.Outlined.Inventory2, Modifier.weight(1f))
                WholesaleTrustMetric("Firma Doğrulama", Icons.Outlined.Verified, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun WholesaleCompanyDeepCard(
    company: WholesaleProductDetailCompany,
    onCompanyProfileClick: () -> Unit,
    onCompanyProductsClick: () -> Unit
) {
    if (company.name.isBlank()) {
        return
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BBSpacing.Space3),
        color = BBColors.Blue.Blue50
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = BBSpacing.PageHorizontal,
                vertical = BBSpacing.Space4
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(BBRadius.XlShape)
                    .clickable {
                        onCompanyProfileClick()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                WholesaleCompanyLogoBox(company = company)

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = company.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (company.description.isNotBlank()) {
                        Text(
                            text = company.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = BBColors.TextSubtle,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    if (company.isVerified) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = null,
                                tint = BBColors.Green.Green700,
                                modifier = Modifier.size(BBIcon.SizeSm)
                            )

                            Text(
                                text = "Doğrulanmış Firma",
                                style = MaterialTheme.typography.labelSmall,
                                color = BBColors.Green.Green700,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = "Firma Profilini Görüntüle",
                    tint = BBColors.TextMuted,
                    modifier = Modifier.size(BBIcon.SizeMd)
                )
            }

            if (company.metrics.isNotEmpty()) {
                BbCard(
                    variant = BbCardVariant.Default,
                    padding = BbCardPadding.Medium
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                    ) {
                        Text(
                            text = "Firma Genel Bilgileri",
                            style = MaterialTheme.typography.titleSmall,
                            color = BBColors.TextStrong,
                            fontWeight = FontWeight.Bold
                        )

                        company.metrics.chunked(3).forEach { rowItems ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                            ) {
                                rowItems.forEach { metric ->
                                    WholesaleCompanyMetric(
                                        title = metric.title,
                                        value = metric.value,
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                repeat(3 - rowItems.size) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                WholesaleOutlineActionButton(
                    text = "Daha Fazla Ürün Göster",
                    onClick = onCompanyProductsClick,
                    modifier = Modifier.weight(1f)
                )

                WholesaleOutlineActionButton(
                    text = "Firma Profilini Görüntüle",
                    onClick = onCompanyProfileClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductDescriptionCard(
    product: WholesaleProductDetail
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
            WholesaleSectionLabel(
                text = "Ürün Açıklaması",
                icon = Icons.Outlined.RequestQuote
            )

            Text(
                text = product.longDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = BBColors.TextSubtle
            )
        }
    }
}

@Composable
private fun WholesaleProductInformationCard(
    properties: List<WholesaleProductProperty>
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
        Column {
            Box(
                modifier = Modifier.padding(
                    horizontal = BBSpacing.Space3,
                    vertical = BBSpacing.Space3
                )
            ) {
                WholesaleSectionLabel(
                    text = "Teknik Bilgiler",
                    icon = Icons.Outlined.Inventory2
                )
            }

            properties.forEachIndexed { index, property ->
                WholesaleProductPropertyRow(property = property)

                if (index != properties.lastIndex) {
                    WholesaleDashedDivider()
                }
            }
        }
    }
}

@Composable
private fun WholesaleProductPropertyRow(
    property: WholesaleProductProperty
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = BBSpacing.Space12)
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = property.name,
            style = MaterialTheme.typography.bodySmall,
            color = BBColors.TextMuted
        )

        Text(
            modifier = Modifier.weight(1f),
            text = property.value,
            style = MaterialTheme.typography.bodySmall,
            color = BBColors.TextStrong,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun WholesaleHorizontalProductSection(
    title: String,
    products: List<WholesaleMiniProduct>,
    onHeaderClick: () -> Unit,
    onProductClick: (WholesaleMiniProduct) -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space4,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(BBRadius.LgShape)
                    .clickable {
                        onHeaderClick()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = "Tümünü Gör",
                    tint = BBColors.TextMuted,
                    modifier = Modifier.size(BBIcon.SizeMd)
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                contentPadding = PaddingValues(
                    start = BBSpacing.None,
                    end = BBSpacing.Space4
                )
            ) {
                items(
                    items = products,
                    key = { product ->
                        product.id
                    }
                ) { item ->
                    WholesaleMiniProductCard(
                        product = item,
                        onClick = {
                            onProductClick(item)
                        },
                        modifier = Modifier.width(212.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleMiniProductCard(
    product: WholesaleMiniProduct,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(BBRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.XlShape,
        color = BBColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(132.dp),
                shape = BBRadius.LgShape,
                color = product.backgroundColor,
                border = BorderStroke(
                    width = 1.dp,
                    color = BBColors.Border
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        product.imageUrl.isNotBlank() -> {
                            AsyncImage(
                                model = product.imageUrl,
                                contentDescription = product.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        product.drawableResId != null -> {
                            Image(
                                painter = painterResource(id = product.drawableResId),
                                contentDescription = product.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        else -> {
                            Icon(
                                imageVector = Icons.Outlined.Inventory2,
                                contentDescription = null,
                                tint = product.foregroundColor,
                                modifier = Modifier.size(BBIcon.BoxLg)
                            )
                        }
                    }
                }
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.priceLabel,
                style = MaterialTheme.typography.titleSmall,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.metaLabel,
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextMuted,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.badgeLabel,
                style = MaterialTheme.typography.labelMedium,
                color = BBColors.Red.Red600,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun WholesaleRelatedCategoryChipsSection(
    categories: List<WholesaleRelatedCategoryChip>,
    onCategoryClick: (WholesaleRelatedCategoryChip) -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.Space4,
            end = BBSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = "İlgili Kategoriler",
                style = MaterialTheme.typography.titleMedium,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                contentPadding = PaddingValues(end = BBSpacing.Space4)
            ) {
                items(
                    items = categories,
                    key = { category ->
                        category.id
                    }
                ) { category ->
                    Surface(
                        modifier = Modifier
                            .clip(BBRadius.PillShape)
                            .clickable {
                                onCategoryClick(category)
                            },
                        shape = BBRadius.PillShape,
                        color = BBColors.Surface,
                        border = BorderStroke(
                            width = 1.dp,
                            color = BBColors.Border
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = BBSpacing.Space3,
                                vertical = BBSpacing.Space2
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Category,
                                contentDescription = null,
                                tint = BBColors.TextMuted,
                                modifier = Modifier.size(BBIcon.Size2Xs)
                            )

                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.labelMedium,
                                color = BBColors.TextStrong,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleCardHeader(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WholesaleSectionLabel(
            text = title,
            icon = icon
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = BBColors.TextMuted,
            modifier = Modifier.size(BBIcon.SizeMd)
        )
    }
}

@Composable
private fun WholesaleCardHeaderInline(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        WholesaleSectionLabel(
            text = title,
            icon = icon
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = BBColors.TextMuted,
            modifier = Modifier.size(BBIcon.SizeMd)
        )
    }
}

@Composable
private fun WholesaleSectionLabel(
    text: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Surface(
            modifier = Modifier.size(28.dp),
            shape = BBRadius.MdShape,
            color = BBColors.PrimarySoft
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BBColors.TextStrong,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )
            }
        }

        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = BBColors.TextStrong,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun WholesaleSummaryMetric(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = BBRadius.LgShape,
        color = BBColors.Surface
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.TextMuted
            )

            Text(
                text = value,
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun WholesaleBenefitPill(
    text: String,
    icon: ImageVector
) {
    Surface(
        shape = BBRadius.PillShape,
        color = BBColors.Green.Green50,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Green.Green100
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
                tint = BBColors.Green.Green700,
                modifier = Modifier.size(BBIcon.Size2Xs)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.Green.Green800,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun WholesaleMiniPill(
    text: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color
) {
    Surface(
        shape = BBRadius.PillShape,
        color = containerColor
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
                tint = contentColor,
                modifier = Modifier.size(BBIcon.Size2Xs)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun WholesaleTrustMetric(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.heightIn(min = 84.dp),
        shape = BBRadius.LgShape,
        color = BBColors.Green.Green50,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Green.Green100
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BBColors.Green.Green700,
                modifier = Modifier.size(BBIcon.SizeMd)
            )

            Spacer( modifier = Modifier.height(BBSpacing.Space1))

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.Green.Green800,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun WholesaleCompanyLogoBox(
    company: WholesaleProductDetailCompany
) {
    Surface(
        modifier = Modifier.size(56.dp),
        shape = BBRadius.LgShape,
        color = BBColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = company.logoText,
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
private fun WholesaleCompanyMetric(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.heightIn(min = 72.dp),
        shape = BBRadius.LgShape,
        color = BBColors.SurfaceSoft,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.TextMuted
            )
        }
    }
}

@Composable
private fun WholesaleInfoBlock(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = BBColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = BBColors.TextSubtle
        )
    }
}

@Composable
private fun WholesaleCheckInfoBlock(
    title: String,
    description: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier.size(22.dp),
            shape = BBRadius.IconBoxSoft,
            color = BBColors.Green.Green50,
            border = BorderStroke(
                width = 1.dp,
                color = BBColors.Green.Green200
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Verified,
                    contentDescription = null,
                    tint = BBColors.Green.Green700,
                    modifier = Modifier.size(BBIcon.Size2Xs)
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
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextSubtle
            )
        }
    }
}

@Composable
private fun WholesaleInlineAction(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(BBSpacing.Space11)
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = BBColors.Primary
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BBColors.TextStrong,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Spacer(modifier = Modifier.width(BBSpacing.Space2))

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun WholesaleOutlineActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(BBSpacing.Space11)
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = BBColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.TextStrong
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = BBSpacing.Space2),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun WholesaleDashedDivider() {
    val dividerColor = BBColors.BorderStrong

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
private fun WholesaleProductDetailBottomBar(onLastPriceRequestClick: () -> Unit, onSampleRequestClick: () -> Unit, onCustomizationRequestClick: () -> Unit)
{
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = BBColors.Surface,
        shadowElevation = 22.dp
    ) {
        Row(
            modifier = Modifier.padding(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.Space2,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.Space2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            WholesaleBottomActionButton(
                text = "Numune",
                onClick = onSampleRequestClick,
                modifier = Modifier.weight(1f),
                containerColor = BBColors.SurfaceMuted,
                contentColor = BBColors.TextStrong,
                borderColor = BBColors.Border,
                leadingIcon = Icons.Outlined.Inventory2
            )

            WholesaleBottomActionButton(
                text = "Özelleştir",
                onClick = onCustomizationRequestClick,
                modifier = Modifier.weight(1.05f),
                containerColor = BBColors.SurfaceMuted,
                contentColor = BBColors.TextStrong,
                borderColor = BBColors.Border,
                leadingIcon = Icons.Outlined.Tune
            )

            WholesaleBottomActionButton(
                text = "Teklif Al",
                onClick = onLastPriceRequestClick,
                modifier = Modifier.weight(1.05f),
                containerColor = BBColors.Primary,
                contentColor = BBColors.TextStrong,
                borderColor = BBColors.Primary,
                leadingIcon = Icons.Outlined.RequestQuote
            )
        }
    }
}

@Composable
private fun WholesaleBottomActionButton(
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
            .height(BBSpacing.Space12)
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space1,
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

                Spacer(modifier = Modifier.width(BBSpacing.Space1))
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Immutable
data class WholesaleProductDetail(
    val id: Int,
    val name: String,
    val searchPlaceholder: String,
    val shortDescription: String,
    val longDescription: String,
    val badgeText: String,
    val priceLabel: String,
    val minimumOrderLabel: String,
    val deliveryTimeLabel: String,
    val deliveryCountryLabel: String,
    val originCountry: String,
    val modelNo: String,
    val soldCountText: String,
    val packagingDescription: String,
    val deliveryDescription: String,
    val images: List<WholesaleProductImage>,
    val priceBreaks: List<WholesalePriceBreak>,
    val tradeBenefits: List<WholesaleTradeBenefit>,
    val highlightFeatures: List<WholesaleHighlightFeature>,
    val properties: List<WholesaleProductProperty>,
    val deliverySteps: List<WholesaleDeliveryStep>,
    val customizationOptions: List<WholesaleCustomizationOption>,
    val relatedCategories: List<WholesaleRelatedCategoryChip>,
    val relatedProducts: List<WholesaleMiniProduct>,
    val companyBestSellerProducts: List<WholesaleMiniProduct>,
    val company: WholesaleProductDetailCompany
)

@Immutable
data class WholesaleProductImage(
    val label: String,
    val backgroundColor: Color,
    val foregroundColor: Color,
    val imageUrl: String = "",
    @DrawableRes val drawableResId: Int? = null
)

@Immutable
data class WholesalePriceBreak(
    val quantityLabel: String,
    val priceLabel: String
)

@Immutable
data class WholesaleTradeBenefit(
    val text: String,
    val icon: ImageVector
)

@Immutable
data class WholesaleHighlightFeature(
    val label: String,
    val value: String
)

@Immutable
data class WholesaleProductProperty(
    val name: String,
    val value: String
)

@Immutable
data class WholesaleDeliveryStep(
    val quantityLabel: String,
    val timeLabel: String
)

@Immutable
data class WholesaleCustomizationOption(
    val title: String,
    val description: String
)

@Immutable
data class WholesaleRelatedCategoryChip(
    val id: Int,
    val name: String
)

@Immutable
data class WholesaleMiniProduct(
    val id: Int,
    val name: String,
    val priceLabel: String,
    val metaLabel: String,
    val badgeLabel: String,
    val backgroundColor: Color,
    val foregroundColor: Color,
    val imageUrl: String = "",
    @DrawableRes val drawableResId: Int? = null
)

@Immutable
data class WholesaleCompanyMetricItem(
    val title: String,
    val value: String
)

@Immutable
data class WholesaleProductDetailCompany(
    val id: Int,
    val name: String,
    val logoText: String,
    val description: String,
    val ratingText: String,
    val productCountText: String,
    val country: String,
    val isVerified: Boolean,
    val metrics: List<WholesaleCompanyMetricItem>
)

private fun ResolveWholesaleProductImageUrl(imagePath: String): String {
    val normalizedPath = imagePath.trim()

    if (normalizedPath.isBlank()) {
        return ""
    }

    if (
        normalizedPath.startsWith("http://", ignoreCase = true) ||
        normalizedPath.startsWith("https://", ignoreCase = true)
    ) {
        return normalizedPath
    }

    val baseUrl = ApiRoutes.B2B_PRODUCT_BASE_URL
        .substringBefore("/api/")
        .trimEnd('/')

    return "$baseUrl/${normalizedPath.trimStart('/')}"
}

private fun WholesaleProductDTO.ToWholesaleProductDetail(relatedProducts: List<WholesaleProductRelatedDTO> = emptyList(), relatedCategories: List<ProductCategoryDTO> = emptyList()): WholesaleProductDetail {
    val mainPrice = MainPrice
    val brandData = BrandData
    val verificationSummary = VerificationSummary
    val unitText = MinimumOrderUnit.ifBlank { "Adet" }

    val resolvedPriceLabel = when {
        mainPrice?.Prices?.isNotBlank() == true -> mainPrice.Prices.orEmpty()
        mainPrice != null && mainPrice.Price > 0 -> mainPrice.Price.ToWholesalePriceText(mainPrice.CurrencySymbol)
        Price > 0 -> Price.ToWholesalePriceText("")
        else -> "Teklif İle"
    }

    val resolvedPriceBreaks = Prices.map { price ->
        val priceUnit = price.Unit.ifBlank { unitText }

        val quantityLabel = when {
            price.MinQuantity > 0 && price.MaxQuantity > 0 -> "${price.MinQuantity}-${price.MaxQuantity} $priceUnit"
            price.MinQuantity > 0 -> "${price.MinQuantity}+ $priceUnit"
            else -> priceUnit
        }

        val priceLabel = when {
            price.Prices.orEmpty().isNotBlank() -> price.Prices.orEmpty()
            price.Price > 0 -> price.Price.ToWholesalePriceText(price.CurrencySymbol)
            else -> "Teklif İle"
        }

        WholesalePriceBreak(
            quantityLabel = quantityLabel,
            priceLabel = priceLabel
        )
    }

    val resolvedImages = listOf(
        DefaultPicture,
        Picture
    )
        .map { it.orEmpty().trim() }
        .filter { it.isNotBlank() }
        .distinct()
        .mapIndexed { index, imagePath ->
            WholesaleProductImage(
                label = if (index == 0) ProductName.orEmpty().ifBlank { "Ürün" } else "${ProductName.orEmpty().ifBlank { "Ürün" }} ${index + 1}",
                backgroundColor = BBColors.Surface,
                foregroundColor = BBColors.TextMuted,
                imageUrl = ResolveWholesaleProductImageUrl(imagePath)
            )
        }
        .filter { it.imageUrl.isNotBlank() }

    val resolvedRelatedProducts = relatedProducts
        .filter { it.RelatedWholesaleProductId > 0 }
        .distinctBy { it.RelatedWholesaleProductId }
        .map { relatedProduct ->
            WholesaleMiniProduct(
                id = relatedProduct.RelatedWholesaleProductId,
                name = relatedProduct.ProductName,
                priceLabel = if (relatedProduct.Price > 0) {
                    relatedProduct.Price.ToWholesalePriceText(relatedProduct.CurrencySymbol)
                } else {
                    "Teklif İle"
                },
                metaLabel = if (relatedProduct.MinimumOrderQuantity > 0) {
                    "Minimum ${relatedProduct.MinimumOrderQuantity} Adet"
                } else {
                    relatedProduct.CategoryName
                },
                badgeLabel = relatedProduct.CategoryName,
                backgroundColor = BBColors.Surface,
                foregroundColor = BBColors.TextMuted,
                imageUrl = ""
            )
        }

    val resolvedTradeBenefits = buildList {
        if (CompanyBusinessTypes.isNotBlank()) {
            add(
                WholesaleTradeBenefit(
                    text = "Kurumsal Alım",
                    icon = Icons.Outlined.Business
                )
            )
        }

        if (SamplePrice > 0) {
            add(
                WholesaleTradeBenefit(
                    text = "Numune Mevcut",
                    icon = Icons.Outlined.Inventory2
                )
            )
        }

        if (verificationSummary?.Verifications?.isNotEmpty() == true) {
            add(
                WholesaleTradeBenefit(
                    text = "Doğrulanmış Tedarikçi",
                    icon = Icons.Outlined.Verified
                )
            )
        }

        if (Customization.isNotBlank()) {
            add(
                WholesaleTradeBenefit(
                    text = "Özelleştirilebilir",
                    icon = Icons.Outlined.Tune
                )
            )
        }
    }

    val resolvedHighlightFeatures = buildList {
        if (Category.orEmpty().isNotBlank()) {
            add(
                WholesaleHighlightFeature(
                    label = "Kategori",
                    value = Category
                )
            )
        }

        if (brandData?.Brand?.isNotBlank() == true) {
            add(
                WholesaleHighlightFeature(
                    label = "Marka",
                    value = brandData.Brand
                )
            )
        }

        if (MonthlyProduction > 0) {
            add(
                WholesaleHighlightFeature(
                    label = "Aylık Üretim",
                    value = MonthlyProduction.toString()
                )
            )
        }

        if (Customization.isNotBlank()) {
            add(
                WholesaleHighlightFeature(
                    label = "Özelleştirme",
                    value = Customization
                )
            )
        }

        if (Origin.isNotBlank()) {
            add(
                WholesaleHighlightFeature(
                    label = "Menşei",
                    value = Origin
                )
            )
        }

        if (MinimumOrderQuantity > 0) {
            add(
                WholesaleHighlightFeature(
                    label = "Minimum Sipariş",
                    value = "$MinimumOrderQuantity $unitText"
                )
            )
        }
    }

    val resolvedProperties = buildList {
        if (ModelNumber.isNotBlank()) {
            add(WholesaleProductProperty("Model No", ModelNumber))
        }

        if (brandData?.Brand?.isNotBlank() == true) {
            add(WholesaleProductProperty("Marka Adı", brandData.Brand))
        }

        if (Origin.isNotBlank()) {
            add(WholesaleProductProperty("Menşei", Origin))
        }

        if (HtsCode.isNotBlank()) {
            add(WholesaleProductProperty("GTIP Kodu", HtsCode))
        }

        if (WeightPerUnit > 0) {
            add(
                WholesaleProductProperty(
                    name = "Birim Ağırlık",
                    value = "$WeightPerUnit ${WeightPerUnitType.ifBlank { "Kg" }}"
                )
            )
        }

        if (DimensionsPerUnit.isNotBlank()) {
            add(
                WholesaleProductProperty(
                    name = "Birim Ölçü",
                    value = "$DimensionsPerUnit $DimensionsPerUnitType".trim()
                )
            )
        }

        if (LeadTime.isNotBlank()) {
            add(WholesaleProductProperty("Tedarik Süresi", LeadTime))
        }

        if (PaymentTermsFormatted.isNotBlank()) {
            add(WholesaleProductProperty("Ödeme Şartı", PaymentTermsFormatted))
        }
    }

    val resolvedCustomizationOptions = buildList {
        if (Customization.isNotBlank()) {
            add(
                WholesaleCustomizationOption(
                    title = "Özelleştirme",
                    description = Customization
                )
            )
        }
    }

    val resolvedRelatedCategories = relatedCategories
        .filter { it.ProductCategoryId > 0 && it.CategoryName.isNotBlank() }
        .distinctBy { it.ProductCategoryId }
        .map { category ->
            WholesaleRelatedCategoryChip(
                id = category.ProductCategoryId,
                name = category.CategoryName
            )
        }

    val companyMetrics = buildList {
        if (verificationSummary != null) {
            if (verificationSummary.VerificationStatus.isNotBlank()) {
                add(
                    WholesaleCompanyMetricItem(
                        title = "Doğrulama Durumu",
                        value = verificationSummary.VerificationStatus
                    )
                )
            }

            if (verificationSummary.TotalYearsValid > 0) {
                add(
                    WholesaleCompanyMetricItem(
                        title = "Geçerlilik Süresi",
                        value = "${verificationSummary.TotalYearsValid} Yıl"
                    )
                )
            }

            if (verificationSummary.Verifications.isNotEmpty()) {
                add(
                    WholesaleCompanyMetricItem(
                        title = "Doğrulama Sayısı",
                        value = verificationSummary.Verifications.size.toString()
                    )
                )
            }
        }

        if (ViewCount > 0) {
            add(
                WholesaleCompanyMetricItem(
                    title = "Görüntülenme",
                    value = ViewCount.toString()
                )
            )
        }

        if (MonthlyProduction > 0) {
            add(
                WholesaleCompanyMetricItem(
                    title = "Aylık Üretim",
                    value = MonthlyProduction.toString()
                )
            )
        }

        if (PaymentTerms.isNotEmpty()) {
            add(
                WholesaleCompanyMetricItem(
                    title = "Ödeme Seçeneği",
                    value = PaymentTerms.size.toString()
                )
            )
        }
    }

    val packagingDescription = buildList {
        if (DimensionsPerUnit.isNotBlank()) {
            add("Birim ölçü: $DimensionsPerUnit $DimensionsPerUnitType".trim())
        }

        if (WeightPerUnit > 0) {
            add("Birim ağırlık: $WeightPerUnit ${WeightPerUnitType.ifBlank { "Kg" }}")
        }
    }.joinToString(separator = " • ")

    return WholesaleProductDetail(
        id = WholesaleProductId,
        name = ProductName.orEmpty().ifBlank { "Ürün" },
        searchPlaceholder = "Toptan Ürün, Kategori Veya Firma Ara",
        shortDescription = Description.take(220),
        longDescription = Description,
        badgeText = Category.orEmpty().ifBlank { "Toptan Ürün" },
        priceLabel = resolvedPriceLabel,
        minimumOrderLabel = if (MinimumOrderQuantity > 0) "$MinimumOrderQuantity $unitText" else "",
        deliveryTimeLabel = LeadTime,
        deliveryCountryLabel = "",
        originCountry = Origin,
        modelNo = ModelNumber,
        soldCountText = if (ViewCount > 0) ViewCount.toString() else "",
        packagingDescription = packagingDescription,
        deliveryDescription = if (LeadTime.isNotBlank()) "Tahmini üretim ve teslim süresi: $LeadTime" else "",
        images = resolvedImages,
        priceBreaks = resolvedPriceBreaks,
        tradeBenefits = resolvedTradeBenefits,
        highlightFeatures = resolvedHighlightFeatures,
        properties = resolvedProperties,
        deliverySteps = emptyList(),
        customizationOptions = resolvedCustomizationOptions,
        relatedCategories = resolvedRelatedCategories,
        relatedProducts = resolvedRelatedProducts,
        companyBestSellerProducts = emptyList(),
        company = WholesaleProductDetailCompany(
            id = CompanyId,
            name = CompanyName,
            logoText = CompanyName.ToWholesaleCompanyLogoText(),
            description = CompanyBusinessTypes,
            ratingText = if (Rating > 0) Rating.toString() else "",
            productCountText = "",
            country = "",
            isVerified = verificationSummary?.Verifications?.isNotEmpty() == true,
            metrics = companyMetrics
        )
    )
}

private fun Double.ToWholesalePriceText(currencySymbol: String): String {
    val formattedPrice = if (this % 1.0 == 0.0) {
        toLong().toString()
    } else {
        String.format("%.2f", this)
    }

    return if (currencySymbol.isNotBlank()) {
        "$formattedPrice $currencySymbol"
    } else {
        formattedPrice
    }
}

private fun String.ToWholesaleCompanyLogoText(): String {
    val words = trim()
        .split(" ")
        .filter {
            it.isNotBlank()
        }

    if (words.isEmpty()) {
        return "BB"
    }

    return words
        .take(2)
        .joinToString("") {
            it.take(1).uppercase()
        }
}

@Preview(showBackground = true)
@Composable
private fun WholesaleProductDetailScreenPreview() {
    BbTheme {
        WholesaleProductDetailScreen()
    }
}

