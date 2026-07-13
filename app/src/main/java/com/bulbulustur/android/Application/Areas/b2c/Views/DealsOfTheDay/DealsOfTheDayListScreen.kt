package com.bulbulustur.android.Application.Areas.b2c.Views.DealsOfTheDay

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalOffer
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import java.util.Locale

@Composable
fun DealsOfTheDayListScreen(
    dealsOfTheDays: List<DealsOfTheDayDTO>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onProductClick: (DealsOfTheDayDTO) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val filteredDeals = remember(
        searchText,
        dealsOfTheDays
    ) {
        if (searchText.isBlank()) {
            dealsOfTheDays
        } else {
            dealsOfTheDays.filter { deal ->
                deal.ProductName.orEmpty().contains(
                    other = searchText,
                    ignoreCase = true
                ) ||
                        deal.CategoryName.orEmpty().contains(
                            other = searchText,
                            ignoreCase = true
                        ) ||
                        deal.Brand.orEmpty().contains(
                            other = searchText,
                            ignoreCase = true
                        ) ||
                        deal.Store.orEmpty().contains(
                            other = searchText,
                            ignoreCase = true
                        )
            }
        }
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
                placeholder = "Fırsat ürünü ara",
                onSearchClick = {
                    Unit
                },
                onClearClick = {
                    searchText = ""
                },
                onMenuClick = {},
                onFavoriteClick = {},
                leadingAction =
                    RetailSearchHeaderLeadingAction.Back,
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
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                DealsHero(
                    count = dealsOfTheDays.size
                )
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                DealsSectionHeader()
            }

            when {
                isLoading -> {
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        DealsInfoCard(
                            title = "Fırsatlar yükleniyor",
                            description = "Bugünün fırsatları getiriliyor.",
                            showProgress = true
                        )
                    }
                }

                !errorMessage.isNullOrBlank() &&
                        dealsOfTheDays.isEmpty() -> {
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        DealsInfoCard(
                            title = "Fırsatlar alınamadı",
                            description = errorMessage
                        )
                    }
                }

                filteredDeals.isEmpty() -> {
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        DealsInfoCard(
                            title = "Fırsat bulunamadı",
                            description = if (searchText.isBlank()) {
                                "Şu anda listelenecek fırsat bulunmuyor."
                            } else {
                                "Arama kriterine uygun fırsat bulunamadı."
                            }
                        )
                    }
                }

                else -> {
                    itemsIndexed(
                        items = filteredDeals,
                        key = { index, deal ->
                            "deal-${deal.DealsOfTheDayId}-${deal.ProductId}-${deal.VariantId}-$index"
                        }
                    ) { _, deal ->
                        DealProductCard(
                            deal = deal,
                            onClick = {
                                if (
                                    deal.ProductId > 0 &&
                                    deal.StoreId > 0 &&
                                    deal.VariantId > 0
                                ) {
                                    onProductClick(deal)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DealsHero(
    count: Int
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
            DealHeroLabel()

            Text(
                text = "Günün Öne Çıkan Fırsatları",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "Kampanyalı ürünleri, avantajlı fiyatları ve alışveriş fırsatlarını tek sayfada keşfet.",
                style = MaterialTheme.typography.bodyMedium,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "$count fırsat listeleniyor",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun DealHeroLabel() {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.76f
        )
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
                modifier = Modifier.size(14.dp),
                imageVector = Icons.Outlined.LocalOffer,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Günün Fırsatları",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun DealsSectionHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Text(
            text = "LİSTE İÇERİĞİ",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = "Kampanyalı Ürünler",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Bugün öne çıkan indirimli ürünleri incele ve alışverişe devam et.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DealProductCard(
    deal: DealsOfTheDayDTO,
    onClick: () -> Unit
) {
    val productName =
        deal.ProductName
            ?.takeIf { it.isNotBlank() }
            ?: "Ürün"

    val categoryName =
        deal.CategoryName
            ?.takeIf { it.isNotBlank() }
            ?: "Günün Fırsatı"

    val brandName =
        deal.Brand
            ?.takeIf { it.isNotBlank() }

    val storeName =
        deal.Store
            ?.takeIf { it.isNotBlank() }

    val campaignPrice =
        getDealPrice(deal)

    val normalPrice =
        deal.Price

    val currencySymbol =
        deal.CurrencySymbol
            ?.takeIf { it.isNotBlank() }
            ?: "₺"

    val productImageUrl =
        resolveDealImageUrl(
            defaultPicture = deal.DefaultPicture.orEmpty(),
            picture = deal.Picture.orEmpty()
        )

    val isProductAvailable =
        deal.ProductId > 0 &&
                deal.StoreId > 0 &&
                deal.VariantId > 0

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
                    .aspectRatio(0.92f)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                    .clickable(
                        enabled = isProductAvailable
                    ) {
                        onClick()
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

                DealBadge(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(BBSpacing.Space2)
                )

                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(BBSpacing.Space2),
                    shape = BBRadius.LgShape,
                    color = MaterialTheme.colorScheme.surface.copy(
                        alpha = 0.92f
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color =
                            MaterialTheme.colorScheme.outlineVariant
                    )
                ) {
                    Box(
                        modifier = Modifier.size(34.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(19.dp),
                            imageVector =
                                Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint =
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
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

                if (brandName != null) {
                    Text(
                        text = brandName,
                        style = MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (storeName != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            BBSpacing.Space1
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Outlined.Storefront,
                            contentDescription = null,
                            tint =
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = storeName,
                            style =
                                MaterialTheme.typography.labelSmall,
                            color =
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = formatDealPrice(
                            price = campaignPrice,
                            currencySymbol = currencySymbol
                        ),
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
                            text = formatDealPrice(
                                price = normalPrice,
                                currencySymbol = currencySymbol
                            ),
                            style =
                                MaterialTheme.typography.labelSmall,
                            color =
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            textDecoration =
                                TextDecoration.LineThrough
                        )
                    }

                    Text(
                        text = "KDV dahil fiyat",
                        style = MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    DealTag(
                        text = "Yoğun İlgi",
                        highlighted = true
                    )

                    DealTag(
                        text = "Günün Fırsatı",
                        highlighted = false
                    )
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                ) {}

                BbButton(
                    text = "Ürünü İncele",
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    enabled = isProductAvailable,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
private fun DealBadge(
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
                modifier = Modifier.size(13.dp),
                imageVector = Icons.Outlined.LocalOffer,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onError
            )

            Text(
                text = "Günün Fırsatı",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onError,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun DealTag(
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
                    alpha = 0.28f
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
            maxLines = 1
        )
    }
}

@Composable
private fun DealsInfoCard(
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

private fun getDealPrice(
    deal: DealsOfTheDayDTO
): Double {
    return if (deal.CampaignPrice > 0.0) {
        deal.CampaignPrice
    } else {
        deal.Price
    }
}

private fun formatDealPrice(
    price: Double,
    currencySymbol: String
): String {
    return currencySymbol + String.format(
        Locale.getDefault(),
        "%.2f",
        price
    )
}

private fun resolveDealImageUrl(
    defaultPicture: String,
    picture: String
): String {
    val normalizedPicture =
        defaultPicture
            .trim()
            .ifBlank {
                picture.trim()
            }

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