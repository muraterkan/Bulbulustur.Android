package com.bulbulustur.android.Application.Views.Shared.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

data class BbBrandScrollerItem(
    val brandId: Int,
    val brandName: String,
    val logoUrl: String? = null
)

@Composable
fun BbBrandScroller(
    brands: List<BbBrandScrollerItem>,
    onBrandClick: (BbBrandScrollerItem) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = BBSpacing.PageHorizontal
    )
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
    ) {
        items(
            items = brands,
            key = { brand -> brand.brandId }
        ) { brand ->
            BbBrandCircleCard(
                brand = brand,
                onClick = {
                    onBrandClick(brand)
                }
            )
        }
    }
}

@Composable
private fun BbBrandCircleCard(
    brand: BbBrandScrollerItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(
                width = BBLayout.BrandScrollerItemWidth,
                height = BBLayout.BrandScrollerItemHeight
            )
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Surface(
            modifier = Modifier.size(BBLayout.BrandScrollerLogoSize),
            shape = BBRadius.PillShape,
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            border = BorderStroke(
                width = BBSpacing.BorderThin,
                color = MaterialTheme.colorScheme.outlineVariant
            ),
            shadowElevation = BBSpacing.ElevationXs
        ) {
            BbBrandLogoPlaceholder(
                brandName = brand.brandName
            )
        }

        Text(
            text = brand.brandName,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun BbBrandLogoPlaceholder(
    brandName: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Storefront,
            contentDescription = brandName,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BBIcon.SizeXl)
        )
    }
}

