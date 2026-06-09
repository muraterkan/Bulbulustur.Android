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
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onLastPriceRequestClick: () -> Unit = {},
    onSampleRequestClick: () -> Unit = {},
    onCustomizationRequestClick: () -> Unit = {},
    onCompanyClick: (WholesaleProductDetailCompany) -> Unit = {},
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
            WholesaleProductDetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onBackClick = onBackClick,
                onFavoriteClick = onFavoriteClick,
                placeholder = product.searchPlaceholder,
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                }
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

            WholesaleProductTitleCard(
                product = product
            )

            WholesaleQuoteSummaryCard(
                product = product
            )

            WholesaleProductDescriptionCard(
                product = product
            )

            WholesaleProductInformationCard(
                properties = product.properties
            )

            WholesaleOrderAndDeliveryCard(
                product = product
            )

            WholesaleCustomizationOptionsCard(
                options = product.customizationOptions,
                onCustomizationRequestClick = onCustomizationRequestClick
            )

            WholesaleCompanyCard(
                company = product.company,
                onCompanyClick = {
                    onCompanyClick(product.company)
                }
            )

            WholesaleSecureTradeCard()

            WholesaleRelatedCategoryChipsSection(
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
private fun WholesaleProductDetailSearchHeader(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    placeholder: String,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = BbColors.Surface,
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = BbSpacing.Space2,
                    top = BbSpacing.Space2,
                    end = BbSpacing.Space2,
                    bottom = BbSpacing.Space2
                )
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "Geri",
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(BbIcon.SizeMd)
                )
            }

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(BbRadius.xl),
                color = BbColors.SurfaceSoft,
                border = BorderStroke(
                    width = 1.dp,
                    color = BbColors.BorderStrong
                )
            ) {
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodySmall,
                            color = BbColors.TextMuted
                        )
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = onSearchClick
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Ara",
                                tint = BbColors.TextMuted
                            )
                        }
                    },
                    trailingIcon = {
                        if (searchText.isNotBlank()) {
                            IconButton(
                                onClick = onClearClick
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = "Temizle",
                                    tint = BbColors.TextMuted
                                )
                            }
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = BbColors.TextStrong,
                        unfocusedTextColor = BbColors.TextStrong,
                        disabledTextColor = BbColors.TextMuted,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = BbColors.Primary
                    )
                )
            }

            IconButton(
                onClick = onFavoriteClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorilerim",
                    tint = BbColors.TextStrong
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductDetailGallery(
    product: WholesaleProductDetail,
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Inventory2,
                    contentDescription = null,
                    tint = image.foregroundColor,
                    modifier = Modifier.size(64.dp)
                )

                Text(
                    text = image.label,
                    style = MaterialTheme.typography.displaySmall,
                    color = image.foregroundColor,
                    fontWeight = FontWeight.ExtraBold
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Surface(
                    shape = BbRadius.PillShape,
                    color = BbColors.PrimarySoft
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = BbSpacing.Space2,
                            vertical = BbSpacing.Space1
                        ),
                        text = "Toptan ürün",
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (product.company.isVerified) {
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
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = null,
                                tint = BbColors.Success,
                                modifier = Modifier.size(BbIcon.Size2Xs)
                            )

                            Text(
                                text = "Doğrulanmış",
                                style = MaterialTheme.typography.labelSmall,
                                color = BbColors.Success,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

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
private fun WholesaleQuoteSummaryCard(
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
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(42.dp),
                    shape = CircleShape,
                    color = BbColors.Surface
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.RequestQuote,
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
                        text = "Teklif ile fiyatlandırma",
                        style = MaterialTheme.typography.titleMedium,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Güncel fiyat ve ticari koşullar için tedarikçiye teklif sor.",
                        style = MaterialTheme.typography.bodySmall,
                        color = BbColors.TextSubtle
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleSummaryMetric(
                    title = "Fiyat",
                    value = product.priceLabel,
                    modifier = Modifier.weight(1f)
                )

                WholesaleSummaryMetric(
                    title = "Min. sipariş",
                    value = product.minimumOrderLabel,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleSummaryMetric(
                    title = "Tedarik süresi",
                    value = product.deliveryTimeLabel,
                    modifier = Modifier.weight(1f)
                )

                WholesaleSummaryMetric(
                    title = "Menşei",
                    value = product.originCountry,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleBenefitPill(
                    text = "Kurumsal alım",
                    icon = Icons.Outlined.Business
                )

                WholesaleBenefitPill(
                    text = "Teklif ile fiyat",
                    icon = Icons.Outlined.Paid
                )

                WholesaleBenefitPill(
                    text = "Güvenli ticaret",
                    icon = Icons.Outlined.Security
                )
            }
        }
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
                text = "Ürün açıklaması",
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
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.padding(
                    horizontal = BbSpacing.Space3,
                    vertical = BbSpacing.Space3
                )
            ) {
                WholesaleSectionLabel(
                    text = "Ürün bilgileri",
                    icon = Icons.Outlined.Inventory2
                )
            }

            properties.forEachIndexed { index, property ->
                WholesaleProductPropertyRow(
                    property = property
                )

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
                text = "Sipariş ve teslimat bilgileri",
                icon = Icons.Outlined.LocalShipping
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleInfoBlock(
                    title = "Ambalaj ve teslimat",
                    description = product.packagingDescription
                )

                WholesaleDashedDivider()

                WholesaleInfoBlock(
                    title = "Teslim süresi",
                    description = product.deliveryDescription
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                product.deliverySteps.forEach { step ->
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = BbRadius.LgShape,
                        color = BbColors.SurfaceMuted
                    ) {
                        Column(
                            modifier = Modifier.padding(BbSpacing.Space2),
                            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                        ) {
                            Text(
                                text = step.quantityLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = BbColors.TextMuted
                            )

                            Text(
                                text = step.timeLabel,
                                style = MaterialTheme.typography.labelMedium,
                                color = BbColors.TextStrong,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
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
            WholesaleSectionLabel(
                text = "Özelleştirme seçenekleri",
                icon = Icons.Outlined.Tune
            )

            options.forEachIndexed { index, option ->
                WholesaleInfoBlock(
                    title = option.title,
                    description = option.description
                )

                if (index != options.lastIndex) {
                    WholesaleDashedDivider()
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(BbRadius.XxlShape)
                    .clickable {
                        onCustomizationRequestClick()
                    },
                shape = BbRadius.XxlShape,
                color = BbColors.Primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Özelleştirme Talebi Oluştur",
                        style = MaterialTheme.typography.labelLarge,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleCompanyCard(
    company: WholesaleProductDetailCompany,
    onCompanyClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.padding(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.Space3,
            end = BbSpacing.PageHorizontal
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onCompanyClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(52.dp),
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
                            text = company.logoText,
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
                            text = company.name,
                            style = MaterialTheme.typography.titleSmall,
                            color = BbColors.TextStrong,
                            fontWeight = FontWeight.Bold
                        )

                        if (company.isVerified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = null,
                                tint = BbColors.Primary,
                                modifier = Modifier.size(BbIcon.SizeSm)
                            )
                        }
                    }

                    Text(
                        text = company.description,
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleCompanyMetric(
                    title = "Puan",
                    value = company.ratingText,
                    modifier = Modifier.weight(1f)
                )

                WholesaleCompanyMetric(
                    title = "Ürün",
                    value = company.productCountText,
                    modifier = Modifier.weight(1f)
                )

                WholesaleCompanyMetric(
                    title = "Konum",
                    value = company.country,
                    modifier = Modifier.weight(1f)
                )
            }
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
        modifier = modifier,
        shape = BbRadius.LgShape,
        color = BbColors.SurfaceMuted
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(42.dp),
                shape = CircleShape,
                color = BbColors.PrimarySoft
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Security,
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
                    text = "Güvenli ticaret",
                    style = MaterialTheme.typography.titleSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Teklif, numune ve özelleştirme taleplerini Bulbulustur güvencesiyle başlat.",
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
private fun WholesaleRelatedCategoryChipsSection(
    categories: List<WholesaleRelatedCategoryChip>,
    onCategoryClick: (WholesaleRelatedCategoryChip) -> Unit
) {
    Column(
        modifier = Modifier.padding(
            top = BbSpacing.Space3
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        WholesaleSectionTitle(
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
private fun WholesaleSectionTitle(
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
        shadowElevation = 12.dp
    ) {
        Column(
            modifier = Modifier.padding(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.Space2,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.Space2
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            WholesaleBottomActionButton(
                text = "Teklif İste",
                onClick = onLastPriceRequestClick,
                modifier = Modifier.fillMaxWidth(),
                containerColor = BbColors.Primary,
                contentColor = BbColors.TextStrong,
                borderColor = BbColors.Primary,
                leadingIcon = Icons.Outlined.RequestQuote
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleBottomActionButton(
                    text = "Numune İste",
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
                    modifier = Modifier.weight(1f),
                    containerColor = BbColors.SurfaceMuted,
                    contentColor = BbColors.TextStrong,
                    borderColor = BbColors.Border,
                    leadingIcon = Icons.Outlined.Tune
                )
            }
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
            .height(44.dp)
            .clip(BbRadius.XxlShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.XxlShape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
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
    val originCountry: String,
    val modelNo: String,
    val packagingDescription: String,
    val deliveryDescription: String,
    val images: List<WholesaleProductImage>,
    val properties: List<WholesaleProductProperty>,
    val deliverySteps: List<WholesaleDeliveryStep>,
    val customizationOptions: List<WholesaleCustomizationOption>,
    val relatedCategories: List<WholesaleRelatedCategoryChip>,
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
data class WholesaleProductDetailCompany(
    val id: Int,
    val name: String,
    val logoText: String,
    val description: String,
    val ratingText: String,
    val productCountText: String,
    val country: String,
    val isVerified: Boolean
)

private fun getWholesaleProductDetail(
    productId: Int
): WholesaleProductDetail {
    return WholesaleProductDetail(
        id = productId,
        name = "BSCI Customized Kindergarten Elementary School Printing Light Folding Splicing Children Backpack",
        searchPlaceholder = "Toptan ürün, kategori veya tedarikçi ara",
        shortDescription = "Okul, promosyon ve kurumsal alımlar için özelleştirilebilir çocuk sırt çantası.",
        longDescription = "Farklı renk, baskı ve paketleme seçenekleriyle toptan siparişe uygun çocuk sırt çantasıdır. Ürün; okul, anaokulu, promosyon ve kurumsal kampanya ihtiyaçları için planlanabilir. Minimum sipariş, üretim süresi, ambalaj, logo baskısı ve teslimat koşulları teklif sürecinde netleştirilir.",
        badgeText = "Toptan kategori",
        priceLabel = "Teklif ile",
        minimumOrderLabel = "499 adet",
        deliveryTimeLabel = "15-25 gün",
        originCountry = "Türkiye",
        modelNo = "BKJ-90",
        packagingDescription = "Paket ve sevkiyat ölçüleri sipariş adetlerine göre tedarikçi tarafından teklif sürecinde netleştirilir.",
        deliveryDescription = "Üretim miktarına, özelleştirme durumuna ve teslimat lokasyonuna göre tahmini termin süresi değişebilir.",
        images = listOf(
            WholesaleProductImage(
                label = "Ürün 1",
                backgroundColor = BbColors.Surface,
                foregroundColor = BbColors.TextMuted
            ),
            WholesaleProductImage(
                label = "Ürün 2",
                backgroundColor = BbColors.SurfaceSoft,
                foregroundColor = BbColors.TextMuted
            ),
            WholesaleProductImage(
                label = "Ürün 3",
                backgroundColor = BbColors.SurfaceMuted,
                foregroundColor = BbColors.TextMuted
            ),
            WholesaleProductImage(
                label = "Ürün 4",
                backgroundColor = BbColors.Surface,
                foregroundColor = BbColors.TextMuted
            )
        ),
        properties = listOf(
            WholesaleProductProperty(
                name = "Model No",
                value = "BKJ-90"
            ),
            WholesaleProductProperty(
                name = "Marka Adı",
                value = "Ac Çimento"
            ),
            WholesaleProductProperty(
                name = "Menşei",
                value = "Türkiye"
            ),
            WholesaleProductProperty(
                name = "GTIP Kodu",
                value = "8421.39.10.00"
            ),
            WholesaleProductProperty(
                name = "Birim başına ağırlık",
                value = "10,6 adet"
            ),
            WholesaleProductProperty(
                name = "Birim başına boyut",
                value = "47.3 × 46.0 × 80.0 cm"
            ),
            WholesaleProductProperty(
                name = "Tedarik süresi",
                value = "15-25 gün"
            ),
            WholesaleProductProperty(
                name = "Ödeme şartı",
                value = "Teklif sürecinde netleşir"
            )
        ),
        deliverySteps = listOf(
            WholesaleDeliveryStep(
                quantityLabel = "1-2",
                timeLabel = "7 gün"
            ),
            WholesaleDeliveryStep(
                quantityLabel = "3-10",
                timeLabel = "8 gün"
            ),
            WholesaleDeliveryStep(
                quantityLabel = "11-100",
                timeLabel = "9 gün"
            ),
            WholesaleDeliveryStep(
                quantityLabel = ">100",
                timeLabel = "Müzakere"
            )
        ),
        customizationOptions = listOf(
            WholesaleCustomizationOption(
                title = "Paketleme",
                description = "Minimum sipariş ve özel fiyat talebi için tedarikçiyle görüş."
            ),
            WholesaleCustomizationOption(
                title = "Renk / Ölçü / Logo",
                description = "Özel üretim uygunluğu tedarikçi onayına bağlıdır."
            )
        ),
        relatedCategories = listOf(
            WholesaleRelatedCategoryChip(1, "Çocuk çantası"),
            WholesaleRelatedCategoryChip(2, "Okul çantası"),
            WholesaleRelatedCategoryChip(3, "Promosyon ürünleri"),
            WholesaleRelatedCategoryChip(4, "Tekstil aksesuarları"),
            WholesaleRelatedCategoryChip(5, "Kurumsal alım")
        ),
        company = WholesaleProductDetailCompany(
            id = 1,
            name = "Ortobella Comfort",
            logoText = "OC",
            description = "Çanta, tekstil ve promosyon ürünleri alanında çalışan doğrulanmış tedarikçi.",
            ratingText = "4.2",
            productCountText = "5 mağaza puanı",
            country = "Türkiye",
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