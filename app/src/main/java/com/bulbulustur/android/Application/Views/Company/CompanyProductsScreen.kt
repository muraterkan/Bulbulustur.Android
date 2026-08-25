package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.B2BProductData
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun CompanyProductsScreen(
    company: CompanyDTO? = null,
    products: List<B2BProductData> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onCompanyProfileClick: () -> Unit = {},
    onCompanyContactClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onProductFavoriteClick: (Int) -> Unit = {},
    onRfqCreateClick: (Int) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(
                    key = "5441c892-ca73-4202-8a98-e5dcb7893bee",
                    fallback = "Firma ürünleri"
                ),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && company == null && products.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            company == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(BBSpacing.PageHorizontal),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = errorMessage?.takeIf { it.isNotBlank() }
                            ?: BBLocalization.Current.Get(
                                key = "8d62c316-0982-473f-b9ce-01b1aebccdf9",
                                fallback = "Şirket bilgisi bulunamadı."
                            ),
                        color = if (!errorMessage.isNullOrBlank()) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }

            else -> {
                CompanyProductsContent(
                    company = company,
                    products = products.filter { it.CompanyId == company.CompanyId },
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    innerPadding = innerPadding,
                    onCompanyProfileClick = onCompanyProfileClick,
                    onCompanyContactClick = onCompanyContactClick,
                    onProductClick = onProductClick,
                    onProductFavoriteClick = onProductFavoriteClick,
                    onRfqCreateClick = onRfqCreateClick
                )
            }
        }
    }
}

@Composable
private fun CompanyProductsContent(
    company: CompanyDTO,
    products: List<B2BProductData>,
    isLoading: Boolean,
    errorMessage: String?,
    innerPadding: PaddingValues,
    onCompanyProfileClick: () -> Unit,
    onCompanyContactClick: () -> Unit,
    onProductClick: (Int) -> Unit,
    onProductFavoriteClick: (Int) -> Unit,
    onRfqCreateClick: (Int) -> Unit
) {
    val favoriteProductIds = remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = BBSpacing.PageHorizontal,
            top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
            end = BBSpacing.PageHorizontal,
            bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
        ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            CompanyProductsHero(
                company = company,
                productCount = products.size,
                onCompanyProfileClick = onCompanyProfileClick,
                onCompanyContactClick = onCompanyContactClick
            )
        }

        if (!errorMessage.isNullOrBlank()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(
                        key = "79b4a21a-b9a4-47e9-8931-f5d66750cea0",
                        fallback = "Ürün Listesi"
                    ),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${products.size} ürün listeleniyor",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (isLoading && products.isEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BBSpacing.Space5),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        if (!isLoading && products.isEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Large
                ) {
                    Text(
                        text = BBLocalization.Current.Get(
                            key = "bc1bc62a-7609-4fde-8790-d2a4fd6d9229",
                            fallback = "Bu firmaya ait ürün bulunamadı."
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        items(
            items = products,
            key = { it.WholesaleProductId }
        ) { product ->
            val isFavorite = favoriteProductIds.value.contains(
                product.WholesaleProductId
            )

            CompanyWholesaleProductCard(
                product = product,
                isFavorite = isFavorite,
                onClick = {
                    if (product.WholesaleProductId > 0) {
                        onProductClick(product.WholesaleProductId)
                    }
                },
                onFavoriteClick = {
                    favoriteProductIds.value = if (isFavorite) {
                        favoriteProductIds.value - product.WholesaleProductId
                    } else {
                        favoriteProductIds.value + product.WholesaleProductId
                    }

                    if (product.WholesaleProductId > 0) {
                        onProductFavoriteClick(product.WholesaleProductId)
                    }
                },
                onRfqClick = {
                    if (product.WholesaleProductId > 0) {
                        onRfqCreateClick(product.WholesaleProductId)
                    }
                }
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(BBSpacing.Space4))
        }
    }
}

@Composable
private fun CompanyProductsHero(
    company: CompanyDTO,
    productCount: Int,
    onCompanyProfileClick: () -> Unit,
    onCompanyContactClick: () -> Unit
) {
    val chips = buildList {
        company.CountryName.orEmpty().trim()
            .takeIf { it.isNotBlank() }
            ?.let(::add)

        company.CityName.orEmpty().trim()
            .takeIf { it.isNotBlank() }
            ?.let(::add)

        company.CompanyType.orEmpty().trim()
            .takeIf { it.isNotBlank() }
            ?.let(::add)

        if (company.Verified) {
            add(
                BBLocalization.Current.Get(
                    key = "c6a0ff62-8828-475f-b553-37effb42efe6",
                    fallback = "Doğrulanmış"
                )
            )
        }

        if (productCount > 0) {
            add("$productCount ürün")
        }
    }

    val description = company.Slogan.orEmpty().trim().ifBlank {
        company.SeoDescription.orEmpty().trim()
    }

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
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
                CompanyProductsLogo(
                    companyName = company.CompanyName,
                    logoPath = company.Logo
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        BbChip(
                            text = BBLocalization.Current.Get(
                                key = "98067ca3-741f-4b7a-ba55-e22bbc1478b7",
                                fallback = "Tedarikçi Ürünleri"
                            ),
                            selected = false,
                            onClick = onCompanyProfileClick
                        )

                        if (company.Verified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = BBLocalization.Current.Get(
                                    key = "c00be3e3-90d4-4f66-ac51-db9a38bac686",
                                    fallback = "Doğrulanmış firma"
                                ),
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(BBIcon.SizeSm)
                            )
                        }
                    }

                    Text(
                        text = company.CompanyName,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    if (description.isNotBlank()) {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            if (chips.isNotEmpty()) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    chips.distinct().forEach { chip ->
                        BbChip(
                            text = chip,
                            selected = false,
                            onClick = {}
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(
                        key = "ab200e4f-1f9e-45f4-90a6-7d5d21d33953",
                        fallback = "Profil"
                    ),
                    onClick = onCompanyProfileClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = BBLocalization.Current.Get(
                        key = "0cf2cda1-7cf6-4d8b-ab56-8918e3a260fd",
                        fallback = "İletişim"
                    ),
                    onClick = onCompanyContactClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Secondary,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun CompanyProductsLogo(
    companyName: String,
    logoPath: String
) {
    val logoUrl = ImageUrlResolver.Resolve(logoPath)
    val logoLoadFailed = remember(logoUrl) {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.size(BBIcon.BoxXl),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (logoUrl.isNotBlank() && !logoLoadFailed.value) {
                AsyncImage(
                    model = logoUrl,
                    contentDescription = companyName,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(BBSpacing.Space2),
                    contentScale = ContentScale.Fit,
                    onError = {
                        logoLoadFailed.value = true
                    }
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Business,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(BBIcon.SizeLg)
                    )

                    val initials = companyName.toInitials()

                    if (initials.isNotBlank()) {
                        Text(
                            text = initials,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CompanyWholesaleProductCard(
    product: B2BProductData,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onRfqClick: () -> Unit
) {
    val imagePath = product.DefaultPicture.orEmpty()
        .trim()
        .ifBlank {
            product.Picture.orEmpty().trim()
        }

    val imageUrl = ImageUrlResolver.Resolve(imagePath)

    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUrl.isNotBlank()) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = product.ProductName,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Inventory2,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(BBIcon.SizeLg)
                        )
                    }
                }
            }

            Text(
                text = product.ProductName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (product.CategoryName.isNotBlank()) {
                Text(
                    text = product.CategoryName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (product.MinimumOrderQuantity > 0) {
                Text(
                    text = buildString {
                        append("MOQ ")
                        append(product.MinimumOrderQuantity)

                        if (product.MinimumOrderUnit.isNotBlank()) {
                            append(" ")
                            append(product.MinimumOrderUnit)
                        }
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (product.Price > 0.0) {
                Text(
                    text = product.Price.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (product.CompanyName.isNotBlank()) {
                Text(
                    text = product.CompanyName,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                BbButton(
                    text = if (isFavorite) "★" else "☆",
                    onClick = onFavoriteClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = "RFQ",
                    onClick = onRfqClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Secondary,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.RequestQuote,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

private fun String.toInitials(): String {
    return trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .mapNotNull {
            it.firstOrNull()?.uppercaseChar()?.toString()
        }
        .joinToString("")
}

@Preview(showBackground = true)
@Composable
private fun CompanyProductsScreenPreview() {
    BbTheme {
        CompanyProductsScreen()
    }
}
