package com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ImageNotSupported
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Immutable
data class WholesaleProductCardModel(
    val Id: Int,
    val Title: String,
    val Category: String = "",
    val PriceText: String = "",
    val MoqText: String = "",
    val SupplierText: String = "",
    val BadgeText: String = "",
    val ImageUrl: String = "",
    @DrawableRes val ImageResId: Int = 0,
    val IsFavorite: Boolean = false
)

@Composable
fun WholesaleProductCard(
    product: WholesaleProductCardModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onRfqClick: () -> Unit = {}
) {
    BbCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(BBRadius.Card),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            WholesaleProductCardMedia(
                imageUrl = product.ImageUrl,
                imageResId = product.ImageResId,
                productTitle = product.Title,
                badgeText = product.BadgeText,
                isFavorite = product.IsFavorite,
                onFavoriteClick = onFavoriteClick
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.ProductCardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.ProductCardGap)
            ) {
                if (product.Category.isNotBlank()) {
                    Text(
                        text = product.Category,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Text(
                    text = product.Title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (product.PriceText.isNotBlank()) {
                    Text(
                        text = product.PriceText,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (product.MoqText.isNotBlank() || product.SupplierText.isNotBlank()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (product.MoqText.isNotBlank()) {
                            WholesaleProductMetaPill(
                                text = product.MoqText,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        if (product.SupplierText.isNotBlank()) {
                            WholesaleProductMetaPill(
                                text = product.SupplierText,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                BbButton(
                    text = BBLocalization.Current.Get(key = "9aa9e9a4-18b3-427b-943f-36170e46cb37", fallback = "Teklif Al"),
                    onClick = onRfqClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.RequestQuote,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "0aa8fda4-a781-4746-8082-f0be1c5d8e50", fallback = ""),
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Small,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductCardMedia(
    imageUrl: String,
    @DrawableRes imageResId: Int,
    productTitle: String,
    badgeText: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    val context = LocalContext.current

    val imageShape = RoundedCornerShape(
        topStart = BBRadius.lg,
        topEnd = BBRadius.lg,
        bottomStart = BBRadius.none,
        bottomEnd = BBRadius.none
    )

    val imageBitmap = remember(imageResId) {
        if (imageResId == 0) {
            null
        } else {
            BitmapFactory.decodeResource(
                context.resources,
                imageResId,
                BitmapFactory.Options().apply {
                    inSampleSize = 4
                    inPreferredConfig = Bitmap.Config.RGB_565
                }
            )?.asImageBitmap()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(imageShape)
    ) {
        when {
            imageUrl.isNotBlank() -> {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .build(),
                    contentDescription = productTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(imageShape),
                    contentScale = ContentScale.Crop
                )
            }

            imageBitmap != null -> {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = productTitle,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            else -> {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ImageNotSupported,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.EmptyStateIcon),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        if (badgeText.isNotBlank()) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(BBSpacing.ProductCardMediaPadding),
                shape = BBRadius.Badge,
                color = BBColors.Navy.Navy900.copy(alpha = 0.90f),
                contentColor = BBColors.White
            ) {
                Text(
                    text = badgeText,
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.BadgePaddingHorizontal,
                        vertical = BBSpacing.BadgePaddingVertical
                    ),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(BBSpacing.ProductCardMediaPadding)
                .size(BBIcon.BoxMd),
            shape = BBRadius.MdShape,
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.94f),
            contentColor = MaterialTheme.colorScheme.onSurface,
            shadowElevation = BBSpacing.Space1,
            onClick = onFavoriteClick
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite) BBLocalization.Current.Get(key = "5b2cc9ba-c14b-4c3d-8b1b-31159085896f", fallback = "Favorilerden kaldır") else BBLocalization.Current.Get(key = "78ef79d0-8390-42b9-a896-d370aa0d3928", fallback = "Favorilere ekle"),
                    modifier = Modifier.size(BBIcon.Action),
                    tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductMetaPill(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}