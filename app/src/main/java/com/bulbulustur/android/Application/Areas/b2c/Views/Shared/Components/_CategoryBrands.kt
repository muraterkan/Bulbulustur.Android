package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandCategoryMapDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun CategoryBrands(
    brands: List<ProductBrandCategoryMapDTO>,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    val validBrands = brands
        .filter {
            it.BrandId > 0 &&
                    it.Brand.trim().isNotBlank()
        }
        .distinctBy {
            it.BrandId
        }

    if (
        !isLoading &&
        validBrands.isEmpty()
    ) {
        return
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space4
        )
    ) {
        CategoryBrandsHeader(
            count = validBrands.size
        )

        if (
            isLoading &&
            validBrands.isEmpty()
        ) {
            CategoryBrandsLoading()
        } else {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    end = BBSpacing.Space2
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                items(
                    items = validBrands,
                    key = {
                        it.BrandId
                    }
                ) { brand ->
                    CategoryBrandCard(
                        brand = brand
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryBrandsHeader(
    count: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(
                1f
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Text(
                text = "Markalar",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Bu Kategorideki Markalar",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Kategoride listelenen öne çıkan markaları keşfedin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (
            count > 0
        ) {
            Surface(
                shape = BBRadius.PillShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                border = BorderStroke(
                    width = BBSpacing.Hairline,
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.20f
                    )
                )
            ) {
                Text(
                    text = count.toString(),
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.Space3,
                        vertical = BBSpacing.Space2
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun CategoryBrandCard(
    brand: ProductBrandCategoryMapDTO
) {
    val brandName = brand.Brand.trim()
    val pictureUrl = ImageUrlResolver.Resolve(brand.Picture.trim())

    Surface(
        modifier = Modifier.size(
            width = BBSpacing.Space24 + BBSpacing.Space12,
            height = BBSpacing.Space16 + BBSpacing.Space4
        ),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BBSpacing.Space4,
                    vertical = BBSpacing.Space3
                ),
            contentAlignment = Alignment.Center
        ) {
            if (pictureUrl.isNotBlank()) {
                AsyncImage(
                    model = pictureUrl,
                    contentDescription = brandName,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text(
                    text = brandName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
@Composable
private fun CategoryBrandsLoading() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.Space6
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                strokeWidth = BBSpacing.ProgressStroke
            )
        }
    }
}