package com.bulbulustur.android.Ui.commercecomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbLayout
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing

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
            modifier = modifier.size(BbLayout.CardActionSize),
            shape = BbRadius.PillShape,
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            border = BorderStroke(
                width = BbSpacing.BorderThin,
                color = MaterialTheme.colorScheme.outlineVariant
            ),
            shadowElevation = BbSpacing.ElevationXs
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
                MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.size(BbIcon.Action)
        )
    }
}