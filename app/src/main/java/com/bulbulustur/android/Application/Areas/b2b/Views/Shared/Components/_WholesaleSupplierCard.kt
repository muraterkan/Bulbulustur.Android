package com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySupplierDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySupplierSomeProductDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun WholesaleSupplierCard(
    supplier: WholesaleProductCategorySupplierDTO,
    modifier: Modifier = Modifier,
    onSupplierClick: (WholesaleProductCategorySupplierDTO) -> Unit = {},
    onProductClick: (WholesaleProductCategorySupplierSomeProductDTO) -> Unit = {}
) {
    val companyName = supplier.CompanyName
        .trim()
        .ifBlank {
            supplier.Name
                ?.trim()
                .orEmpty()
        }
        .ifBlank {
            "Tedarikçi"
        }

    val businessType = supplier.BusinessType
        .trim()

    val content = supplier.Content
        .trim()

    val logoUrl = ImageUrlResolver.Resolve(
        supplier.Logo.trim()
    )

    val products = supplier.SupplierSomeProduct
        .filter {
            it.WholesaleProductId > 0
        }
        .distinctBy {
            it.WholesaleProductId
        }
        .take(3)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onSupplierClick(supplier)
            },
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.StoreCardPadding),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.StoreCardGap
                )
            ) {
                WholesaleSupplierLogo(
                    logoUrl = logoUrl,
                    companyName = companyName
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = companyName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (businessType.isNotBlank()) {
                        Text(
                            text = businessType,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(
                        BBIcon.Action
                    ),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (content.isNotBlank()) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (products.isNotEmpty()) {
                WholesaleSupplierProducts(
                    products = products,
                    onProductClick = onProductClick
                )
            }
        }
    }
}

@Composable
fun WholesaleSupplierCompactCard(
    supplier: WholesaleProductCategorySupplierDTO,
    modifier: Modifier = Modifier,
    onSupplierClick: (WholesaleProductCategorySupplierDTO) -> Unit = {}
) {
    val companyName = supplier.CompanyName
        .trim()
        .ifBlank {
            supplier.Name
                ?.trim()
                .orEmpty()
        }
        .ifBlank {
            "Tedarikçi"
        }

    val businessType = supplier.BusinessType
        .trim()

    val logoUrl = ImageUrlResolver.Resolve(
        supplier.Logo.trim()
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onSupplierClick(supplier)
            },
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.StoreCardPaddingCompact
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.StoreCardGap
            )
        ) {
            WholesaleSupplierCompactLogo(
                logoUrl = logoUrl,
                companyName = companyName
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = companyName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (businessType.isNotBlank()) {
                    Text(
                        text = businessType,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(
                    BBIcon.Action
                ),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleSupplierLogo(
    logoUrl: String,
    companyName: String
) {
    Surface(
        modifier = Modifier.size(
            BBIcon.LogoMarkLg
        ),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        if (logoUrl.isNotBlank()) {
            AsyncImage(
                model = logoUrl,
                contentDescription = companyName,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(BBSpacing.Space2)
                    .clip(BBRadius.LgShape),
                contentScale = ContentScale.Fit
            )
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Business,
                    contentDescription = null,
                    modifier = Modifier.size(
                        BBIcon.CompanyMediaIcon
                    ),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun WholesaleSupplierCompactLogo(
    logoUrl: String,
    companyName: String
) {
    Surface(
        modifier = Modifier.size(
            BBIcon.LogoMarkMd
        ),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        if (logoUrl.isNotBlank()) {
            AsyncImage(
                model = logoUrl,
                contentDescription = companyName,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(BBSpacing.Space1)
                    .clip(BBRadius.LgShape),
                contentScale = ContentScale.Fit
            )
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Business,
                    contentDescription = null,
                    modifier = Modifier.size(
                        BBIcon.StoreIcon
                    ),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun WholesaleSupplierProducts(
    products: List<WholesaleProductCategorySupplierSomeProductDTO>,
    onProductClick: (WholesaleProductCategorySupplierSomeProductDTO) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space2
        )
    ) {
        Text(
            text = "Öne Çıkan Ürünler",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            products.forEach { product ->
                WholesaleSupplierProduct(
                    product = product,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onProductClick(product)
                    }
                )
            }

            repeat(
                3 - products.size
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
            }
        }
    }
}

@Composable
private fun WholesaleSupplierProduct(
    product: WholesaleProductCategorySupplierSomeProductDTO,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val pictureUrl = ImageUrlResolver.Resolve(
        product.DefaultPicture.trim()
    )

    Surface(
        modifier = modifier
            .aspectRatio(1f)
            .clickable {
                onClick()
            },
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        if (pictureUrl.isNotBlank()) {
            AsyncImage(
                model = pictureUrl,
                contentDescription = product.ProductName,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(BBSpacing.Space1)
                    .clip(BBRadius.LgShape),
                contentScale = ContentScale.Fit
            )
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Image,
                    contentDescription = null,
                    modifier = Modifier.size(
                        BBIcon.ProductMediaIcon
                    ),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}