package com.bulbulustur.android.Application.Areas.b2c.Views.Home.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun B2CHomepageSpecialContents(
    specialContents: List<ProductHomepageSpecialContentDTO>,
    onProductClick: (productId: Int, storeId: Int, variantId: Int) -> Unit
) {
    if (specialContents.isEmpty()) {
        return
    }

    var selectedGroupIndex by remember {
        mutableIntStateOf(0)
    }

    val selectedGroup =
        specialContents.getOrNull(selectedGroupIndex)
            ?: specialContents.first()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "ca2297c4-f57f-4a7a-9380-65693990a625", fallback = "Ürün Vitrini"),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = BBLocalization.Current.Get(key = "1ec41061-3327-4607-bf1a-78856e233f24", fallback = "Seçilmiş ürün gruplarını keşfedin."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            contentPadding = PaddingValues(
                end = BBSpacing.PageHorizontal
            )
        ) {
            items(
                count = specialContents.size,
                key = { index ->
                    specialContents[index].ProductSpecialGroupId
                }
            ) { index ->
                val group = specialContents[index]

                BbChip(
                    text = group.GroupName.ifBlank {
                        BBLocalization.Current.Get(key = "ca2297c4-f57f-4a7a-9380-65693990a625", fallback = "Ürün Vitrini")
                    },
                    selected = selectedGroupIndex == index,
                    onClick = {
                        selectedGroupIndex = index
                    }
                )
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            contentPadding = PaddingValues(
                end = BBSpacing.PageHorizontal
            )
        ) {
            items(
                items = selectedGroup.Products,
                key = { product ->
                    product.ProductHomepageSpecialId
                }
            ) { product ->
                B2CHomepageSpecialProductCard(
                    product = product,
                    onClick = {
                        onProductClick(
                            product.ProductId,
                            product.StoreId,
                            product.VariantId
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun B2CHomepageSpecialProductCard(
    product: ProductHomepageSpecialDTO,
    onClick: () -> Unit
) {
    val imageUrl = ImageUrlResolver.Resolve(product.DefaultPicture)

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
                if (imageUrl.isNotBlank()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = product.ProductName,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(BBRadius.LgShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = product.ProductName
                            .ifBlank { BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = "") }
                            .take(2)
                            .uppercase(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier.padding(BBSpacing.Space3),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = product.ProductName.ifBlank {
                        BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = "")
                    },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )

                Text(
                    text = BBLocalization.Current.Get(key = "0aa8fda4-a781-4746-8082-f0be1c5d8e50", fallback = ""),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

