package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxIcon
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

enum class RetailSearchHeaderLeadingAction {
    Menu,
    Back
}

@Composable
fun RetailSearchHeader(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onMenuClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ürün, kategori veya marka ara",
    onSearchClick: (() -> Unit)? = null,
    onClearClick: (() -> Unit)? = null,
    leadingAction: RetailSearchHeaderLeadingAction = RetailSearchHeaderLeadingAction.Menu,
    onBackClick: (() -> Unit)? = null,
    onMessageClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = BBSpacing.Space1
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(
                    start = BBSpacing.Space2,
                    top = BBSpacing.Space2,
                    end = BBSpacing.Space2,
                    bottom = BBSpacing.Space2
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBLayout.TopBarHeight),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                RetailHeaderActionButton(
                    icon = if (
                        leadingAction == RetailSearchHeaderLeadingAction.Back
                    ) {
                        Icons.AutoMirrored.Outlined.ArrowBack
                    } else {
                        Icons.Outlined.Menu
                    },
                    contentDescription = if (
                        leadingAction == RetailSearchHeaderLeadingAction.Back
                    ) {
                        "Geri"
                    } else {
                        "Menü"
                    },
                    onClick = {
                        if (
                            leadingAction == RetailSearchHeaderLeadingAction.Back
                        ) {
                            onBackClick?.invoke() ?: onMenuClick()
                        } else {
                            onMenuClick()
                        }
                    }
                )

                RetailSearchInput(
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange,
                    placeholder = placeholder,
                    onSearchClick = onSearchClick,
                    onClearClick = onClearClick,
                    modifier = Modifier.weight(1f)
                )
                RetailHeaderActionButton(
                    icon = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorilerim",
                    onClick = onFavoriteClick
                )
            }
        }
    }
}

@Composable
private fun RetailHeaderActionButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    BbIconBoxIcon(
        icon = icon,
        contentDescription = contentDescription,
        size = BbIconBoxSize.Medium,
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        iconColor = MaterialTheme.colorScheme.onSurface,
        borderColor = MaterialTheme.colorScheme.outlineVariant,
        borderWidth = BBSpacing.Divider,
        bordered = true,
        radius = BBRadius.xl,
        onClick = onClick
    )
}

@Composable
private fun RetailSearchInput(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    placeholder: String,
    onSearchClick: (() -> Unit)?,
    onClearClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(
            BBLayout.TopBarHeight - BBSpacing.Space4
        ),
        shape = RoundedCornerShape(BBRadius.xl),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            singleLine = true,
            textStyle = BbTypography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        onSearchClick?.invoke()
                    },
                    enabled = onSearchClick != null
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Ara",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.height(
                            BBIcon.TopBarIcon
                        )
                    )
                }
            },
            trailingIcon = {
                if (searchText.isNotBlank()) {
                    IconButton(
                        onClick = {
                            onClearClick?.invoke()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Temizle",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = BBColors.Transparent,
                unfocusedContainerColor = BBColors.Transparent,
                disabledContainerColor = BBColors.Transparent,
                focusedIndicatorColor = BBColors.Transparent,
                unfocusedIndicatorColor = BBColors.Transparent,
                disabledIndicatorColor = BBColors.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}
