package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.commercecomponents.BbProductGrid
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import androidx.compose.material3.Text
import com.bulbulustur.android.ui.commercecomponents.BbPriceBlock
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun FavoriteListScreen() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        BbSectionHeader(
            title = "Favorilerim",
            subtitle = "Beğendiğin ürünleri tek yerde takip et"
        )

        BbProductGrid(
            contentPadding = PaddingValues(BbSpacing.md),
            horizontalSpacing = BbSpacing.sm,
            verticalSpacing = BbSpacing.md
        ) {
            items(favoriteProducts()) { product ->
                FavoriteProductCard(product)
            }
        }
    }
}

@Composable
private fun FavoriteProductCard(
    product: FavoriteProductItem
) {
    BbCard(
        padding = BbCardPadding.Small
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Text(
                text = product.name,
                style = BbTypography.bodyMedium,
                color = BbColors.TextStrong,
                maxLines = 2
            )

            BbPriceBlock(
                price = product.price,
                oldPrice = product.oldPrice,
                discountPercent = product.discountPercent
            )
        }
    }
}

private data class FavoriteProductItem(
    val id: Int,
    val name: String,
    val price: Double,
    val oldPrice: Double?,
    val discountPercent: Int?
)

private fun favoriteProducts(): List<FavoriteProductItem> {
    return listOf(
        FavoriteProductItem(1, "Kraf Büyüteç 90 mm Siyah Saplı", 148.0, 149.0, 1),
        FavoriteProductItem(2, "Dailytech Katlanabilir Masa Lambası", 1499.0, 1699.0, 12),
        FavoriteProductItem(3, "Apple iPhone 15 128 GB Mavi", 48749.0, null, null),
        FavoriteProductItem(4, "Mango Açık Sırtlı Lurex Elbise", 1144.17, null, 60)
    )
}