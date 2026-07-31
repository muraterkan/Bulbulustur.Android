package com.bulbulustur.android.Application.Areas.b2c.Views.Campaign

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignProductDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import java.util.Locale

private const val DEFAULT_CAMPAIGN_PICTURE_PATH =
    "/UploadedFiles/B2C/Campaigns/campaign-banner.jpg"

@Composable
fun CampaignDetailScreen(
    campaign: CampaignDTO?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductClick: (CampaignProductDTO) -> Unit = {},
    onCategoryClick: (Int) -> Unit = {},
    onStoreClick: (Int) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = "Ürün, kategori veya marka ara",
                onSearchClick = {
                    onSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                leadingAction = RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottom
            ),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            when {
                isLoading -> {
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        CampaignDetailInfoCard(
                            title = "Kampanya yükleniyor",
                            description = "Kampanya detayları getiriliyor.",
                            showProgress = true
                        )
                    }
                }

                campaign == null -> {
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        CampaignDetailInfoCard(
                            title = "Kampanya bulunamadı",
                            description = errorMessage
                                ?.takeIf { it.isNotBlank() }
                                ?: "Kampanya detayı alınamadı."
                        )
                    }
                }

                else -> {
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        CampaignDetailHero(
                            campaign = campaign
                        )
                    }

                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        CampaignDetailSummary(
                            campaign = campaign,
                            onCategoryClick = {
                                if (campaign.ProductCategoryId > 0) {
                                    onCategoryClick(
                                        campaign.ProductCategoryId
                                    )
                                }
                            }
                        )
                    }

                    if (!campaign.CampaignCondition.isNullOrBlank()) {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            CampaignConditionCard(
                                condition =
                                    campaign.CampaignCondition.orEmpty()
                            )
                        }
                    }

                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        BbSectionHeader(
                            title = "Bu Kampanyadaki Ürünler",
                            subtitle = "Kampanya kapsamındaki seçili ürünleri, kampanyalı fiyatları ve alışveriş seçeneklerini incele."
                        )
                    }

                    if (campaign.CampaignProducts.isNullOrEmpty()) {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            CampaignDetailInfoCard(
                                title = "Ürün bulunamadı",
                                description = "Bu kampanyaya bağlı ürün bulunmuyor."
                            )
                        }
                    } else {
                        itemsIndexed(
                            items = campaign.CampaignProducts.orEmpty(),
                            key = { index, product ->
                                "campaign-product-${product.CampaignProductId}-${product.ProductId}-${product.VariantId}-$index"
                            }
                        ) { _, product ->
                            CampaignProductCard(
                                product = product,
                                onProductClick = {
                                    if (
                                        product.ProductId > 0 &&
                                        product.StoreId > 0 &&
                                        product.VariantId > 0
                                    ) {
                                        onProductClick(product)
                                    }
                                },
                                onStoreClick = {
                                    if (product.StoreId > 0) {
                                        onStoreClick(product.StoreId)
                                    }
                                }
                            )
                        }
                    }

                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        CampaignExploreCard(
                            onAllCampaignsClick = onBackClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CampaignDetailHero(
    campaign: CampaignDTO
) {
    val campaignName =
        campaign.CampaignName
            ?.takeIf { it.isNotBlank() }
            ?: BBLocalization.Current.Get(key = "b66af534-b950-4603-8fb6-caf6ca5be73d", fallback = "Kampanya")

    val description =
        campaign.Description
            ?.takeIf { it.isNotBlank() }

    val startDate =
        campaign.CampaignStartDate
            ?.take(10)
            ?.takeIf { it.isNotBlank() }
            ?: "-"

    val endDate =
        campaign.CampaignEndDate
            ?.take(10)
            ?.takeIf { it.isNotBlank() }
            ?: "-"

    val campaignPicture =
        campaign.Picture
            ?.takeIf { it.isNotBlank() }
            ?: campaign.DefaultPicture
                ?.takeIf { it.isNotBlank() }
            ?: DEFAULT_CAMPAIGN_PICTURE_PATH

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                    )
            ) {
                AsyncImage(
                    model = resolveCampaignImageUrl(
                        campaignPicture
                    ),
                    contentDescription = campaignName,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                CampaignBadge(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(BBSpacing.Space3),
                    text = BBLocalization.Current.Get(key = "b66af534-b950-4603-8fb6-caf6ca5be73d", fallback = "Kampanya"),
                    icon = Icons.Outlined.LocalOffer
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.Space5),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space4
                )
            ) {
                Text(
                    text = campaignName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onPrimaryContainer
                )

                if (description != null) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color =
                            MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space3
                    )
                ) {
                    CampaignHeroInfo(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Outlined.CalendarMonth,
                        label = "Kampanya Tarihi",
                        value = "$startDate - $endDate"
                    )

                    CampaignHeroInfo(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Outlined.Inventory2,
                        label = "Ürün Sayısı",
                        value = campaign.CampaignProducts
                            .orEmpty()
                            .size
                            .toString()
                    )
                }
            }
        }
    }
}

@Composable
private fun CampaignHeroInfo(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.heightIn(min = 94.dp),
        shape = BBRadius.LgShape,
        color =
            MaterialTheme.colorScheme.surface.copy(alpha = 0.84f)
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CampaignDetailSummary(
    campaign: CampaignDTO,
    onCategoryClick: () -> Unit
) {
    val categoryName =
        campaign.CategoryName
            ?.takeIf { it.isNotBlank() }
            ?: "Genel"

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            CampaignSummaryLine(
                icon = Icons.Outlined.Category,
                title = BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = ""),
                value = categoryName
            )

            CampaignSummaryLine(
                icon = Icons.Outlined.ShoppingBag,
                title = BBLocalization.Current.Get(key = "5c8e4b6c-cf81-4990-a2e2-2735855d38bf", fallback = "Maksimum ürün"),
                value = campaign.MaximumProducts.toString()
            )

            CampaignSummaryLine(
                icon = Icons.Outlined.Inventory2,
                title = BBLocalization.Current.Get(key = "d778653c-63d8-4680-9240-dd275184250c", fallback = "Kampanya ürünü"),
                value = campaign.CampaignProducts
                    .orEmpty()
                    .size
                    .toString()
            )

            if (campaign.ProductCategoryId > 0) {
                BbButton(
                    text = "Kategoriye Git",
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium,
                    onClick = onCategoryClick
                )
            }
        }
    }
}

@Composable
private fun CampaignSummaryLine(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color =
                        MaterialTheme.colorScheme.primaryContainer,
                    shape = BBRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CampaignConditionCard(
    condition: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space4),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        color =
                            MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalOffer,
                    contentDescription = null,
                    tint =
                        MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "387bcc7b-e309-4099-8f1d-0ee062d4b7f4", fallback = ""),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = condition,
                    style = MaterialTheme.typography.bodyMedium,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CampaignProductCard(
    product: CampaignProductDTO,
    onProductClick: () -> Unit,
    onStoreClick: () -> Unit
) {
    val productName =
        product.ProductName
            ?.takeIf { it.isNotBlank() }
            ?: BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = "")

    val categoryName =
        product.CategoryName
            ?.takeIf { it.isNotBlank() }
            ?: "Kampanya Ürünü"

    val storeName =
        product.StoreName
            ?.takeIf { it.isNotBlank() }

    val color =
        product.Color
            ?.takeIf { it.isNotBlank() }

    val size =
        product.Size
            ?.takeIf { it.isNotBlank() }

    val variantText =
        listOfNotNull(
            color,
            size
        ).joinToString(" / ")

    val campaignPrice =
        if (product.CampaignPrice > 0.0) {
            product.CampaignPrice
        } else {
            product.Price
        }

    val normalPrice =
        product.Price

    val productImageUrl =
        resolveProductImageUrl(
            product.DefaultPicture.orEmpty()
        )

    val isProductAvailable =
        product.ProductId > 0 &&
                product.StoreId > 0 &&
                product.VariantId > 0

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.error.copy(
                alpha = 0.22f
            )
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                    .clickable(
                        enabled = isProductAvailable
                    ) {
                        onProductClick()
                    }
            ) {
                AsyncImage(
                    model = productImageUrl,
                    contentDescription = productName,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(BBSpacing.Space2),
                    contentScale = ContentScale.Fit
                )

                CampaignBadge(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(BBSpacing.Space2),
                    text = "Fırsat",
                    icon = Icons.Outlined.LocalOffer
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.Space3),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                Text(
                    text = categoryName,
                    style = MaterialTheme.typography.labelSmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = productName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    minLines = 3,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                if (storeName != null) {
                    Row(
                        modifier = Modifier.clickable(
                            enabled = product.StoreId > 0
                        ) {
                            onStoreClick()
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            BBSpacing.Space1
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Outlined.Storefront,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = storeName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                if (variantText.isNotBlank()) {
                    Text(
                        text = variantText,
                        style = MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = formatPrice(campaignPrice),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error,
                        maxLines = 1
                    )

                    if (
                        normalPrice > 0.0 &&
                        normalPrice > campaignPrice
                    ) {
                        Text(
                            text = formatPrice(normalPrice),
                            style = MaterialTheme.typography.labelSmall,
                            color =
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            textDecoration =
                                TextDecoration.LineThrough,
                            maxLines = 1
                        )
                    }

                    Text(
                        text = BBLocalization.Current.Get(key = "5b922b77-6524-4fe5-816f-662afbee60ba", fallback = "Kampanyalı fiyat"),
                        style = MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    CampaignProductTag(
                        text = "Fırsat Ürünü",
                        highlighted = true
                    )

                    CampaignProductTag(
                        text = "Sınırlı süre",
                        highlighted = false
                    )
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color =
                        MaterialTheme.colorScheme.outlineVariant
                ) {}

                BbButton(
                    text = BBLocalization.Current.Get(key = "24627553-85ff-442a-b353-f12f5e4a1612", fallback = ""),
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    enabled = isProductAvailable,
                    onClick = onProductClick
                )
            }
        }
    }
}

@Composable
private fun CampaignProductTag(
    text: String,
    highlighted: Boolean
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = if (highlighted) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (highlighted) {
                MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.26f
                )
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
        )
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = if (highlighted) {
                MaterialTheme.colorScheme.onPrimaryContainer
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun CampaignBadge(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.error
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onError
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onError,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun CampaignExploreCard(
    onAllCampaignsClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Text(
                text = "Daha Fazla Fırsatı Keşfet",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "Seçili ürünlerdeki kampanyaları, indirimleri ve dönemsel fırsatları tek yerden incele.",
                style = MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "e2812624-6bbc-4034-9a09-6570540d0785", fallback = "Tüm Kampanyalar"),
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Dark,
                size = BbButtonSize.Medium,
                onClick = onAllCampaignsClick
            )
        }
    }
}

@Composable
private fun CampaignDetailInfoCard(
    title: String,
    description: String,
    showProgress: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            if (showProgress) {
                CircularProgressIndicator()
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun resolveCampaignImageUrl(
    picture: String
): String {
    val normalizedPicture =
        picture.trim()

    if (
        normalizedPicture.startsWith("http://") ||
        normalizedPicture.startsWith("https://")
    ) {
        return normalizedPicture
    }

    val applicationOrigin =
        ApiRoutes.B2C_TEST_PRODUCT_IMAGE_URL
            .substringBefore("/UploadedFiles/")

    val relativePath =
        normalizedPicture
            .ifBlank {
                DEFAULT_CAMPAIGN_PICTURE_PATH
            }
            .trimStart('/')

    return "$applicationOrigin/$relativePath"
}

private fun resolveProductImageUrl(
    picture: String
): String {
    val normalizedPicture = picture.trim()

    if (normalizedPicture.isBlank()) {
        return ApiRoutes.B2C_TEST_PRODUCT_IMAGE_URL
    }

    if (
        normalizedPicture.startsWith("http://") ||
        normalizedPicture.startsWith("https://")
    ) {
        return normalizedPicture
    }

    val applicationOrigin =
        ApiRoutes.B2C_TEST_PRODUCT_IMAGE_URL
            .substringBefore("/UploadedFiles/")

    return "$applicationOrigin/${normalizedPicture.trimStart('/')}"
}

private fun formatPrice(
    price: Double
): String {
    return "₺" + String.format(
        Locale.getDefault(),
        "%.2f",
        price
    )
}