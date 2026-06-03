package com.bulbulustur.android.ui.commercecomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

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
    contentPadding: PaddingValues = PaddingValues(horizontal = BbSpacing.md),
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
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
            .size(width = 76.dp, height = 98.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Surface(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            ),
            shadowElevation = 2.dp
        ) {
            BbBrandLogoPlaceholder(
                brandName = brand.brandName
            )
        }

        Text(
            text = brand.brandName,
            style = BbTypography.labelSmall,
            color = BbColors.TextStrong,
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
            .background(BbColors.SurfaceMuted),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Storefront,
            contentDescription = brandName,
            tint = BbColors.TextMuted,
            modifier = Modifier.size(26.dp)
        )
    }
}