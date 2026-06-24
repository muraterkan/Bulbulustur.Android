package com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    val Category: String,
    val PriceText: String,
    val MoqText: String,
    val SupplierText: String,
    val BadgeText: String,
    @DrawableRes val ImageResId: Int,
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
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.ProductCardGap
                )
            ) {
                Text(
                    text = product.Category,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.Title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.PriceText,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space2
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WholesaleProductMetaPill(
                        text = product.MoqText,
                        modifier = Modifier.weight(1f)
                    )

                    WholesaleProductMetaPill(
                        text = product.SupplierText,
                        modifier = Modifier.weight(1f)
                    )
                }

                BbButton(
                    text = "Teklif İste",
                    onClick = onRfqClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.RequestQuote,
                            contentDescription = null,
                            modifier = Modifier.size(
                                BBIcon.ButtonIcon
                            )
                        )
                    }
                )

                BbButton(
                    text = "Ürünü İncele",
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Small,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(
                                BBIcon.ButtonIcon
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductCardMedia(
    @DrawableRes imageResId: Int,
    productTitle: String,
    badgeText: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    val imageShape = RoundedCornerShape(
        topStart = BBRadius.lg,
        topEnd = BBRadius.lg,
        bottomStart = BBRadius.none,
        bottomEnd = BBRadius.none
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(imageShape)
    ) {
        if (imageResId != 0) {
            Image(
                painter = painterResource(
                    id = imageResId
                ),
                contentDescription = productTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        } else {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ImageNotSupported,
                        contentDescription = null,
                        modifier = Modifier.size(
                            BBIcon.EmptyStateIcon
                        ),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        if (badgeText.isNotBlank()) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(BBSpacing.ProductCardMediaPadding),
                shape = BBRadius.Badge,
                color = BBColors.Navy.Navy900.copy(
                    alpha = 0.90f
                ),
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
            color = MaterialTheme.colorScheme.surface.copy(
                alpha = 0.94f
            ),
            contentColor = MaterialTheme.colorScheme.onSurface,
            shadowElevation = BBSpacing.Space1,
            onClick = onFavoriteClick
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isFavorite) {
                        Icons.Outlined.Favorite
                    } else {
                        Icons.Outlined.FavoriteBorder
                    },
                    contentDescription = if (isFavorite) {
                        "Favorilerden kaldır"
                    } else {
                        "Favorilere ekle"
                    },
                    modifier = Modifier.size(
                        BBIcon.Action
                    ),
                    tint = if (isFavorite) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
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
        color = BBColors.Gray.Gray50,
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
