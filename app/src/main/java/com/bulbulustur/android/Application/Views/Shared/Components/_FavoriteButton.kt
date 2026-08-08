package com.bulbulustur.android.Application.Views.Shared.Components

import com.bulbulustur.android.Application.Localization.BBLocalization

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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
            modifier = modifier.size(BBLayout.CardActionSize),
            shape = BBRadius.PillShape,
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            border = BorderStroke(
                width = BBSpacing.BorderThin,
                color = MaterialTheme.colorScheme.outlineVariant
            ),
            shadowElevation = BBSpacing.ElevationXs
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
                BBLocalization.Current.Get(key = "ed502881-8152-44af-8238-accd89828c46", fallback = "Favorilerden çıkar")
            } else {
                BBLocalization.Current.Get(key = "78ef79d0-8390-42b9-a896-d370aa0d3928", fallback = "Favorilere ekle")
            },
            tint = if (isFavorite) {
                BBColors.Danger
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.size(BBIcon.Action)
        )
    }
}

