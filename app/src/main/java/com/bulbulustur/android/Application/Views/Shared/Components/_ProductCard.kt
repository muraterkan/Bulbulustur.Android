package com.bulbulustur.android.Application.Views.Shared.Components

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ImageNotSupported
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Star
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Immutable
data class BbProductCardModel(
    val Id: Int,
    val Name: String,
    val StoreName: String,
    @DrawableRes val ImageResId: Int = 0,
    val ImageUrl: String = "",
    val PriceText: String,
    val OldPriceText: String = "",
    val BadgeText: String = "",
    val RatingText: String = "",
    val CargoText: String = "",
    val IsFavorite: Boolean = false
)

@Composable
fun BbProductCard(
    product: BbProductCardModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onAddToBasketClick: () -> Unit = {}
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
            BbProductCardMedia(
                imageResId = product.ImageResId,
                imageUrl = product.ImageUrl,
                productName = product.Name,
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
                    text = product.StoreName,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.Name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                BbProductCardPrice(
                    priceText = product.PriceText,
                    oldPriceText = product.OldPriceText
                )

                BbProductCardMetaRow(
                    ratingText = product.RatingText,
                    cargoText = product.CargoText
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "9a748489-8d57-4bc5-becc-0937717d80df", fallback = "Sepete Ekle"),
                    onClick = onAddToBasketClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBasket,
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
private fun BbProductCardMedia(
    @DrawableRes imageResId: Int,
    imageUrl: String,
    productName: String,
    badgeText: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    val context = LocalContext.current

    val mediaShape = RoundedCornerShape(
        topStart = BBRadius.lg,
        topEnd = BBRadius.lg,
        bottomEnd = BBRadius.none,
        bottomStart = BBRadius.none
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(mediaShape)
    ) {
        when {
            imageUrl.isNotBlank() -> {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .build(),
                    contentDescription = productName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(mediaShape),
                    contentScale = ContentScale.Crop
                )
            }

            imageResId != 0 -> {
                Image(
                    painter = painterResource(
                        id = imageResId
                    ),
                    contentDescription = productName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(mediaShape),
                    contentScale = ContentScale.Crop
                )
            }

            else -> {
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
        }

        if (badgeText.isNotBlank()) {
            BbProductCardBadge(
                text = badgeText,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        BBSpacing.ProductCardMediaPadding
                    )
            )
        }

        BbProductCardFavoriteButton(
            isFavorite = isFavorite,
            onClick = onFavoriteClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    BBSpacing.ProductCardMediaPadding
                )
        )
    }
}

@Composable
private fun BbProductCardFavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.size(
            BBIcon.BoxMd
        ),
        shape = BBRadius.MdShape,
        color = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.94f
        ),
        contentColor = MaterialTheme.colorScheme.onSurface,
        shadowElevation = BBSpacing.Space1,
        onClick = onClick
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
                    BBLocalization.Current.Get(key = "5b2cc9ba-c14b-4c3d-8b1b-31159085896f", fallback = "Favorilerden kaldır")
                } else {
                    BBLocalization.Current.Get(key = "78ef79d0-8390-42b9-a896-d370aa0d3928", fallback = "Favorilere ekle")
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

@Composable
private fun BbProductCardBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = BBRadius.Badge,
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Text(
            text = text,
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

@Composable
private fun BbProductCardPrice(
    priceText: String,
    oldPriceText: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Text(
            text = priceText,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (oldPriceText.isNotBlank()) {
            Text(
                text = oldPriceText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textDecoration = TextDecoration.LineThrough,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun BbProductCardMetaRow(
    ratingText: String,
    cargoText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        if (ratingText.isNotBlank()) {
            BbProductCardMetaPill(
                text = ratingText,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        modifier = Modifier.size(
                            BBIcon.Compact
                        ),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
        }

        if (cargoText.isNotBlank()) {
            BbProductCardMetaPill(
                text = cargoText,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.LocalShipping,
                        contentDescription = null,
                        modifier = Modifier.size(
                            BBIcon.Compact
                        ),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
        }
    }
}

@Composable
private fun BbProductCardMetaPill(
    text: String,
    icon: @Composable () -> Unit
) {
    Surface(
        shape = BBRadius.Chip,
        color = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.IconTextGapSmall
            )
        ) {
            icon()

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
