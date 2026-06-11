package com.bulbulustur.android.features.wholesale

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Tune
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
import com.bulbulustur.android.R
import com.bulbulustur.android.features.wholesale.components.WholesaleSearchHeader
import com.bulbulustur.android.features.wholesale.components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun WholesaleProductDetailScreen(
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
    onCompanySimilarProductClick: (WholesaleMiniProduct) -> Unit = {},
    onCompanyBestSellerProductClick: (WholesaleMiniProduct) -> Unit = {},
    onCompanySimilarProductsClick: () -> Unit = onCompanyProductsClick,
    onCompanyBestSellerProductsClick: () -> Unit = onCompanyProductsClick,
    onRelatedCategoryClick: (WholesaleRelatedCategoryChip) -> Unit = {}
) {
    val product = remember(productId) {
        getWholesaleProductDetail(productId)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    val pagerState = rememberPagerState(
        pageCount = {
            product.images.size
        }
    )

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
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
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
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

            WholesaleProductTitleCard(product = product)

            WholesaleTradeSummaryCard(product = product)

            WholesalePriceBreakdownCard(
                priceBreaks = product.priceBreaks,
                onClick = onLastPriceRequestClick
            )

            WholesaleFeatureGridCard(
                features = product.highlightFeatures
            )

            WholesaleCustomizationOptionsCard(
                options = product.customizationOptions,
                onCustomizationRequestClick = onCustomizationRequestClick
            )

            WholesaleOrderAndDeliveryCard(product = product)

            WholesaleSecureTradeCard()

            WholesaleCompanyDeepCard(
                company = product.company,
                onCompanyProfileClick = {
                    onCompanyClick(product.company)
                },
                onCompanyProductsClick = onCompanyProductsClick
            )

            WholesaleProductDescriptionCard(product = product)

            WholesaleProductInformationCard(
                properties = product.properties
            )

            WholesaleHorizontalProductSection(
                title = "Bu Firmadan Benzer Ürünler",
                products = product.companySimilarProducts,
                onHeaderClick = onCompanySimilarProductsClick,
                onProductClick = onCompanySimilarProductClick
            )

            WholesaleHorizontalProductSection(
                title = "Bu Firmanın Çok Satanları",
                products = product.companyBestSellerProducts,
                onHeaderClick = onCompanyBestSellerProductsClick,
                onProductClick = onCompanyBestSellerProductClick
            )

            WholesaleRelatedCategoryChipsSection(
                categories = product.relatedCategories,
                onCategoryClick = onRelatedCategoryClick
            )

            Spacer(
                modifier = Modifier.height(BbSpacing.Space6)
            )
        }
    }
}

@Composable
fun ProductDetailScreen(
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
    onCompanySimilarProductClick: (WholesaleMiniProduct) -> Unit = {},
    onCompanyBestSellerProductClick: (WholesaleMiniProduct) -> Unit = {},
    onCompanySimilarProductsClick: () -> Unit = onCompanyProductsClick,
    onCompanyBestSellerProductsClick: () -> Unit = onCompanyProductsClick,
    onRelatedCategoryClick: (WholesaleRelatedCategoryChip) -> Unit = {}
) {
    WholesaleProductDetailScreen(
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
        onCompanySimilarProductClick = onCompanySimilarProductClick,
        onCompanyBestSellerProductClick = onCompanyBestSellerProductClick,
        onCompanySimilarProductsClick = onCompanySimilarProductsClick,
        onCompanyBestSellerProductsClick = onCompanyBestSellerProductsClick,
        onRelatedCategoryClick = onRelatedCategoryClick
    )
}

@Composable
private fun WholesaleProductDetailGallery(
    product: WholesaleProductDetail,
    currentPage: Int,
    pagerContent: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Surface)
            .heightIn(max = 390.dp),
        contentAlignment = Alignment.Center
    ) {
        pagerContent()

        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(BbSpacing.Space3),
            shape = BbRadius.PillShape,
            color = BbColors.Primary
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
                    imageVector = Icons.Outlined.Business,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(BbIcon.Size2Xs)
                )

                Text(
                    text = product.badgeText,
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        WholesaleProductDetailImageCounter(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = BbSpacing.Space3,
                    bottom = BbSpacing.Space3
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
            Icon(
                imageVector = Icons.Outlined.Inventory2,
                contentDescription = image.label,
                tint = image.foregroundColor,
                modifier = Modifier.size(BbIcon.BoxXl)
            )
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
        shape = BbRadius.PillShape,
        color = BbColors.Black.copy(alpha = 0.52f)
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            text = "Fotoğraflar $currentPage/$totalPage",
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.White,
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
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleMiniPill(
                    text = "Toptan Ürün",
                    icon = Icons.Outlined.Business,
                    containerColor = BbColors.PrimarySoft,
                    contentColor = BbColors.TextStrong
                )

                WholesaleMiniPill(
                    text = "Doğrulanmış Firma",
                    icon = Icons.Outlined.Verified,
                    containerColor = BbColors.Green.Green50,
                    contentColor = BbColors.Green.Green700
                )

                WholesaleMiniPill(
                    text = "Özelleştirilebilir",
                    icon = Icons.Outlined.Tune,
                    containerColor = BbColors.Blue.Blue50,
                    contentColor = BbColors.Blue.Blue700
                )
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Text(
                    text = "${product.soldCountText} Satıldı",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )

                Text(
                    text = "•",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )

                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = BbColors.Orange.Orange500,
                    modifier = Modifier.size(BbIcon.SizeSm)
                )

                Text(
                    text = product.company.ratingText,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Mağaza Puanı",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Text(
                text = product.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextSubtle
            )
        }
    }
}

@Composable
private fun WholesaleTradeSummaryCard(
    product: WholesaleProductDetail
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            WholesaleSectionLabel(
                text = "Ticari Teklif Özeti",
                icon = Icons.Outlined.RequestQuote
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleSummaryMetric("Fiyat", product.priceLabel, Modifier.weight(1f))
                WholesaleSummaryMetric("Min. Sipariş", product.minimumOrderLabel, Modifier.weight(1f))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleSummaryMetric("Üretim Süresi", product.deliveryTimeLabel, Modifier.weight(1f))
                WholesaleSummaryMetric("Teslimat", product.deliveryCountryLabel, Modifier.weight(1f))
            }

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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

@Composable
private fun WholesalePriceBreakdownCard(
    priceBreaks: List<WholesalePriceBreak>,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
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
                        start = BbSpacing.Space3,
                        end = BbSpacing.Space3,
                        bottom = BbSpacing.Space3
                    ),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                priceBreaks.forEach { item ->
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = BbRadius.LgShape,
                        color = BbColors.SurfaceSoft,
                        border = BorderStroke(
                            width = 1.dp,
                            color = BbColors.Border
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(BbSpacing.Space3),
                            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                        ) {
                            Text(
                                text = item.priceLabel,
                                style = MaterialTheme.typography.titleSmall,
                                color = BbColors.TextStrong,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = item.quantityLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = BbColors.TextMuted
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
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            WholesaleSectionLabel(
                text = "Öne Çıkan Özellikler",
                icon = Icons.Outlined.Inventory2
            )

            features.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
        shape = BbRadius.LgShape,
        color = BbColors.SurfaceSoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = feature.value,
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = feature.label,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun WholesaleCustomizationOptionsCard(
    options: List<WholesaleCustomizationOption>,
    onCustomizationRequestClick: () -> Unit
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            WholesaleSectionLabel(
                text = "Üretim Ve Teslimat",
                icon = Icons.Outlined.LocalShipping
            )

            Text(
                text = "Teslimat Adresi: ${product.deliveryCountryLabel}",
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                product.deliverySteps.forEach { step ->
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = BbRadius.LgShape,
                        color = BbColors.SurfaceSoft,
                        border = BorderStroke(
                            width = 1.dp,
                            color = BbColors.Border
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(BbSpacing.Space3),
                            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                        ) {
                            Text(
                                text = step.quantityLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = BbColors.TextMuted
                            )

                            Text(
                                text = step.timeLabel,
                                style = MaterialTheme.typography.titleSmall,
                                color = BbColors.TextStrong,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            WholesaleInfoBlock(
                title = "Ambalaj Ve Sevkiyat",
                description = product.packagingDescription
            )

            WholesaleDashedDivider()

            WholesaleInfoBlock(
                title = "Teslimat Notu",
                description = product.deliveryDescription
            )
        }
    }
}

@Composable
private fun WholesaleSecureTradeCard() {
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            WholesaleSectionLabel(
                text = "Bulbulustur Güvenli Ticaret",
                icon = Icons.Outlined.Security
            )

            Text(
                text = "Teklif, numune ve özelleştirme taleplerini kayıt altına alarak firma ile güvenli ticari iletişim başlat.",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextSubtle
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BbSpacing.Space3),
        color = BbColors.Blue.Blue50
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.Space4
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(BbRadius.XlShape)
                    .clickable {
                        onCompanyProfileClick()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                WholesaleCompanyLogoBox(company = company)

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = company.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = company.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = BbColors.TextSubtle,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = "Firma Profilini Görüntüle",
                    tint = BbColors.TextMuted,
                    modifier = Modifier.size(BbIcon.SizeMd)
                )
            }

            BbCard(
                variant = BbCardVariant.Default,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                ) {
                    Text(
                        text = "Firma Genel Bilgileri",
                        style = MaterialTheme.typography.titleSmall,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    company.metrics.chunked(3).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
    }
}

@Composable
private fun WholesaleProductDescriptionCard(
    product: WholesaleProductDetail
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
            WholesaleSectionLabel(
                text = "Ürün Açıklaması",
                icon = Icons.Outlined.Description
            )

            Text(
                text = product.longDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextSubtle
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
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column {
            Box(
                modifier = Modifier.padding(
                    horizontal = BbSpacing.Space3,
                    vertical = BbSpacing.Space3
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
            .heightIn(min = 48.dp)
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = property.name,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextMuted
        )

        Text(
            modifier = Modifier.weight(1f),
            text = property.value,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextStrong,
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
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space4,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(BbRadius.LgShape)
                    .clickable {
                        onHeaderClick()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = "Tümünü Gör",
                    tint = BbColors.TextMuted,
                    modifier = Modifier.size(BbIcon.SizeMd)
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                contentPadding = PaddingValues(
                    start = BbSpacing.None,
                    end = BbSpacing.Space4
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(132.dp),
                shape = BbRadius.LgShape,
                color = product.backgroundColor,
                border = BorderStroke(
                    width = 1.dp,
                    color = BbColors.Border
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
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
                        Icon(
                            imageVector = Icons.Outlined.Inventory2,
                            contentDescription = null,
                            tint = product.foregroundColor,
                            modifier = Modifier.size(BbIcon.BoxLg)
                        )
                    }
                }
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.priceLabel,
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.metaLabel,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.badgeLabel,
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.Red.Red600,
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
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space4,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Text(
                text = "İlgili Kategoriler",
                style = MaterialTheme.typography.titleMedium,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                contentPadding = PaddingValues(end = BbSpacing.Space4)
            ) {
                items(
                    items = categories,
                    key = { category ->
                        category.id
                    }
                ) { category ->
                    Surface(
                        modifier = Modifier
                            .clip(BbRadius.PillShape)
                            .clickable {
                                onCategoryClick(category)
                            },
                        shape = BbRadius.PillShape,
                        color = BbColors.Surface,
                        border = BorderStroke(
                            width = 1.dp,
                            color = BbColors.Border
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = BbSpacing.Space3,
                                vertical = BbSpacing.Space2
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Category,
                                contentDescription = null,
                                tint = BbColors.TextMuted,
                                modifier = Modifier.size(BbIcon.Size2Xs)
                            )

                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.labelMedium,
                                color = BbColors.TextStrong,
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
            .padding(BbSpacing.Space3),
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
            tint = BbColors.TextMuted,
            modifier = Modifier.size(BbIcon.SizeMd)
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
            tint = BbColors.TextMuted,
            modifier = Modifier.size(BbIcon.SizeMd)
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Surface(
            modifier = Modifier.size(28.dp),
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
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = BbColors.TextStrong,
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
        shape = BbRadius.LgShape,
        color = BbColors.Surface
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.TextMuted
            )

            Text(
                text = value,
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.TextStrong,
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
        shape = BbRadius.PillShape,
        color = BbColors.Green.Green50,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Green.Green100
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
                tint = BbColors.Green.Green700,
                modifier = Modifier.size(BbIcon.Size2Xs)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.Green.Green800,
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
        shape = BbRadius.PillShape,
        color = containerColor
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
                tint = contentColor,
                modifier = Modifier.size(BbIcon.Size2Xs)
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
        shape = BbRadius.LgShape,
        color = BbColors.Green.Green50,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Green.Green100
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Green.Green700,
                modifier = Modifier.size(BbIcon.SizeMd)
            )

            Spacer(modifier = Modifier.height(BbSpacing.Space1))

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.Green.Green800,
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
        shape = BbRadius.LgShape,
        color = BbColors.Surface,
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
                text = company.logoText,
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.TextStrong,
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
        shape = BbRadius.LgShape,
        color = BbColors.SurfaceSoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.TextMuted
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextSubtle
        )
    }
}

@Composable
private fun WholesaleCheckInfoBlock(
    title: String,
    description: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier.size(22.dp),
            shape = CircleShape,
            color = BbColors.Green.Green50,
            border = BorderStroke(
                width = 1.dp,
                color = BbColors.Green.Green200
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Verified,
                    contentDescription = null,
                    tint = BbColors.Green.Green700,
                    modifier = Modifier.size(BbIcon.Size2Xs)
                )
            }
        }

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
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextSubtle
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
            .height(44.dp)
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = BbColors.Primary
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.SizeSm)
            )

            Spacer(modifier = Modifier.width(BbSpacing.Space2))

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.TextStrong,
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
            .height(44.dp)
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = BbColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.TextStrong
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = BbSpacing.Space2),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun WholesaleDashedDivider() {
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
private fun WholesaleProductDetailBottomBar(
    onLastPriceRequestClick: () -> Unit,
    onSampleRequestClick: () -> Unit,
    onCustomizationRequestClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = BbColors.Surface,
        shadowElevation = 22.dp
    ) {
        Row(
            modifier = Modifier.padding(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.Space2,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.Space2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            WholesaleBottomActionButton(
                text = "Numune",
                onClick = onSampleRequestClick,
                modifier = Modifier.weight(1f),
                containerColor = BbColors.SurfaceMuted,
                contentColor = BbColors.TextStrong,
                borderColor = BbColors.Border,
                leadingIcon = Icons.Outlined.Inventory2
            )

            WholesaleBottomActionButton(
                text = "Özelleştir",
                onClick = onCustomizationRequestClick,
                modifier = Modifier.weight(1.05f),
                containerColor = BbColors.SurfaceMuted,
                contentColor = BbColors.TextStrong,
                borderColor = BbColors.Border,
                leadingIcon = Icons.Outlined.Tune
            )

            WholesaleBottomActionButton(
                text = "Teklif Al",
                onClick = onLastPriceRequestClick,
                modifier = Modifier.weight(1.05f),
                containerColor = BbColors.Primary,
                contentColor = BbColors.TextStrong,
                borderColor = BbColors.Primary,
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
            .height(48.dp)
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space1,
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

                Spacer(modifier = Modifier.width(BbSpacing.Space1))
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
    val companySimilarProducts: List<WholesaleMiniProduct>,
    val companyBestSellerProducts: List<WholesaleMiniProduct>,
    val company: WholesaleProductDetailCompany
)

@Immutable
data class WholesaleProductImage(
    val label: String,
    val backgroundColor: Color,
    val foregroundColor: Color,
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

private fun getWholesaleProductDetail(
    productId: Int
): WholesaleProductDetail {
    return WholesaleProductDetail(
        id = productId,
        name = "BSCI Sertifikalı Özelleştirilebilir Çocuk Sırt Çantası",
        searchPlaceholder = "Toptan Ürün, Kategori Veya Firma Ara",
        shortDescription = "Okul, promosyon ve kurumsal alımlar için logo, renk ve ambalaj özelleştirme destekli toptan ürün.",
        longDescription = "Farklı renk, baskı ve paketleme seçenekleriyle toptan siparişe uygun çocuk sırt çantasıdır. Ürün; okul, anaokulu, promosyon ve kurumsal kampanya ihtiyaçları için planlanabilir. Minimum sipariş, üretim süresi, ambalaj, logo baskısı ve teslimat koşulları teklif sürecinde netleştirilir.",
        badgeText = "Toptan Kategori",
        priceLabel = "Teklif İle",
        minimumOrderLabel = "499 Adet",
        deliveryTimeLabel = "15-25 Gün",
        deliveryCountryLabel = "TR",
        originCountry = "Türkiye",
        modelNo = "BKJ-90",
        soldCountText = "120+",
        packagingDescription = "Paket ve sevkiyat ölçüleri sipariş adetlerine göre firma tarafından teklif sürecinde netleştirilir.",
        deliveryDescription = "Üretim miktarına, özelleştirme durumuna ve teslimat lokasyonuna göre tahmini termin süresi değişebilir. Kargo ücreti ve teslimat tarihi firma ile kararlaştırılır.",
        images = listOf(
            WholesaleProductImage("Ürün 1", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bach),
            WholesaleProductImage("Ürün 2", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar1),
            WholesaleProductImage("Ürün 3", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar2),
            WholesaleProductImage("Ürün 4", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar3)
        ),
        priceBreaks = listOf(
            WholesalePriceBreak("100-499 Adet", "Teklif İle"),
            WholesalePriceBreak("500-999 Adet", "Son Fiyat"),
            WholesalePriceBreak("1000+ Adet", "Özel Teklif")
        ),
        tradeBenefits = listOf(
            WholesaleTradeBenefit("Kurumsal Alım", Icons.Outlined.Business),
            WholesaleTradeBenefit("Numune Mevcut", Icons.Outlined.Inventory2),
            WholesaleTradeBenefit("Güvenli Ticaret", Icons.Outlined.Security),
            WholesaleTradeBenefit("Özelleştirilebilir", Icons.Outlined.Tune)
        ),
        highlightFeatures = listOf(
            WholesaleHighlightFeature("Kullanım Alanı", "Okul, Promosyon"),
            WholesaleHighlightFeature("Malzeme", "Polyester"),
            WholesaleHighlightFeature("Baskı", "Logo / Grafik"),
            WholesaleHighlightFeature("Paketleme", "Özel Ambalaj"),
            WholesaleHighlightFeature("Üretim Tipi", "OEM / ODM"),
            WholesaleHighlightFeature("Menşei", "Türkiye")
        ),
        properties = listOf(
            WholesaleProductProperty("Model No", "BKJ-90"),
            WholesaleProductProperty("Marka Adı", "Private Label"),
            WholesaleProductProperty("Menşei", "Türkiye"),
            WholesaleProductProperty("GTIP Kodu", "4202.92.91.00"),
            WholesaleProductProperty("Birim Ağırlık", "0,42 Kg"),
            WholesaleProductProperty("Birim Ölçü", "31 × 14 × 42 Cm"),
            WholesaleProductProperty("Tedarik Süresi", "15-25 Gün"),
            WholesaleProductProperty("Ödeme Şartı", "Teklif Sürecinde Netleşir")
        ),
        deliverySteps = listOf(
            WholesaleDeliveryStep("100+", "15 Gün"),
            WholesaleDeliveryStep("500+", "20 Gün"),
            WholesaleDeliveryStep("1000+", "Müzakere")
        ),
        customizationOptions = listOf(
            WholesaleCustomizationOption(
                title = "Logo / Grafik Tasarım",
                description = "Ürün üzerine marka logosu, okul amblemi veya kampanya görseli uygulanabilir."
            ),
            WholesaleCustomizationOption(
                title = "Özel Ambalaj",
                description = "Kutu, poşet, etiket ve sevkiyat paketleme seçenekleri siparişe göre planlanabilir."
            ),
            WholesaleCustomizationOption(
                title = "OEM / ODM Üretim",
                description = "Renk, ölçü, malzeme ve üretim standardı firma onayına bağlı olarak özelleştirilebilir."
            )
        ),
        relatedCategories = listOf(
            WholesaleRelatedCategoryChip(1, "Çocuk Çantası"),
            WholesaleRelatedCategoryChip(2, "Okul Çantası"),
            WholesaleRelatedCategoryChip(3, "Promosyon Ürünleri"),
            WholesaleRelatedCategoryChip(4, "Tekstil Aksesuarları"),
            WholesaleRelatedCategoryChip(5, "Kurumsal Alım")
        ),
        companySimilarProducts = listOf(
            WholesaleMiniProduct(1, "Benzer Ürün 1", "1.129-1.753 TL", "200 Adet MOQ", "Düşük Fiyat", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bach),
            WholesaleMiniProduct(2, "Benzer Ürün 2", "2.258-3.315 TL", "100 Adet MOQ", "Özel Üretim", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar1),
            WholesaleMiniProduct(3, "Benzer Ürün 3", "2.690-3.483 TL", "50 Adet MOQ", "Hızlı Teklif", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar2)
        ),
        companyBestSellerProducts = listOf(
            WholesaleMiniProduct(4, "Çok Satan Ürün 1", "1.561-2.066 TL", "10+ Görüntülenme", "Popüler", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar3),
            WholesaleMiniProduct(5, "Çok Satan Ürün 2", "2.618-3.685 TL", "1 Satıldı", "Yeni", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bach),
            WholesaleMiniProduct(6, "Çok Satan Ürün 3", "2.883-3.747 TL", "20+ Görüntülenme", "Düşük Fiyat", BbColors.Surface, BbColors.TextMuted, R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar1)
        ),
        company = WholesaleProductDetailCompany(
            id = 1,
            name = "Ortobella Comfort",
            logoText = "OC",
            description = "Çanta, tekstil ve promosyon ürünleri alanında çalışan doğrulanmış firma.",
            ratingText = "4.8",
            productCountText = "120+ Ürün",
            country = "Türkiye",
            isVerified = true,
            metrics = listOf(
                WholesaleCompanyMetricItem("Zamanında Teslim", "96%"),
                WholesaleCompanyMetricItem("Çevrim İçi Gelir", "₺10M+"),
                WholesaleCompanyMetricItem("Yanıt Süresi", "≤8s"),
                WholesaleCompanyMetricItem("Kuruluş Yılı", "2015"),
                WholesaleCompanyMetricItem("Tesis Alanı", "22.100m²"),
                WholesaleCompanyMetricItem("Ürün Sayısı", "120+")
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun WholesaleProductDetailScreenPreview() {
    BbTheme {
        WholesaleProductDetailScreen()
    }
}