package com.bulbulustur.android.ui.commercecomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors

@Composable
fun BbFavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    showSurface: Boolean = true
) {
    if (showSurface) {
        Surface(
            modifier = modifier.size(42.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            ),
            shadowElevation = 2.dp
        ) {
            BbFavoriteIconButtonContent(
                isFavorite = isFavorite,
                onClick = onClick,
                enabled = enabled
            )
        }
    } else {
        BbFavoriteIconButtonContent(
            isFavorite = isFavorite,
            onClick = onClick,
            enabled = enabled,
            modifier = modifier
        )
    }
}

@Composable
private fun BbFavoriteIconButtonContent(
    isFavorite: Boolean,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            },
            contentDescription = if (isFavorite) {
                "Favorilerden çıkar"
            } else {
                "Favorilere ekle"
            },
            tint = if (isFavorite) {
                BbColors.Danger
            } else {
                BbColors.TextStrong
            }
        )
    }
}