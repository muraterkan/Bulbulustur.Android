package com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import java.text.NumberFormat
import java.util.Locale

@Composable
fun B2CDealsOfTheDay(
    dealsOfTheDays: List<DealsOfTheDayDTO>,
    onProductClick: (productId: Int, storeId: Int, variantId: Int) -> Unit,
    onViewAllClick: () -> Unit = {}
) {
    if (dealsOfTheDays.isEmpty()) {
        return
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Öne Çıkan Fırsatlar",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Sınırlı süreli fiyatları ve seçilmiş ürünleri keşfedin.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = BBSpacing.Space3)
                    .clickable {
                        onViewAllClick()
                    },
                text = "Tümünü Gör",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            contentPadding = PaddingValues(end = BBSpacing.PageHorizontal)
        ) {
            items(
                items = dealsOfTheDays,
                key = { deal -> deal.DealsOfTheDayId }
            ) { deal ->
                B2CDealOfTheDayCard(
                    deal = deal,
                    onClick = {
                        onProductClick(
                            deal.ProductId,
                            deal.StoreId,
                            deal.VariantId
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun B2CDealOfTheDayCard(
    deal: DealsOfTheDayDTO,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .width(BBSpacing.Space24 + BBSpacing.Space20)
            .clickable(onClick = onClick),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ResolveB2CDealImageUrl(
                        deal.DefaultPicture.orEmpty().ifBlank {
                            deal.Picture
                        }
                    ),
                    contentDescription = deal.ProductName.orEmpty().ifBlank { "Ürün" },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(BBRadius.LgShape),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier.padding(BBSpacing.Space3),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Text(
                    text = deal.ProductName.orEmpty().ifBlank { "Ürün" },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )

                if (deal.Store.orEmpty().isNotBlank()) {
                    Text(
                        text = deal.Store.orEmpty(),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = FormatDealPrice(
                            price = deal.CampaignPrice,
                            currencySymbol = deal.CurrencySymbol
                        ),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    if (deal.Price > deal.CampaignPrice && deal.CampaignPrice > 0.0) {
                        Text(
                            text = FormatDealPrice(
                                price = deal.Price,
                                currencySymbol = deal.CurrencySymbol
                            ),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Text(
                    text = "Fırsatı İncele",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

private fun FormatDealPrice(
    price: Double,
    currencySymbol: String
): String {
    val formatter = NumberFormat.getNumberInstance(
        Locale("tr", "TR")
    )

    formatter.minimumFractionDigits = 2
    formatter.maximumFractionDigits = 2

    return "${currencySymbol.orEmpty().ifBlank { "₺" }}${formatter.format(price)}"
}

private fun ResolveB2CDealImageUrl(
    picture: String
): String {
    return ApiRoutes.B2C_TEST_PRODUCT_IMAGE_URL
}
