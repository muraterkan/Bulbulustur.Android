package com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components

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
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

enum class WholesaleSearchHeaderLeadingAction {
    Menu,
    Back
}

@Composable
fun WholesaleSearchHeader(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onMenuClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Toptan ürün, kategori veya tedarikçi ara",
    onSearchClick: (() -> Unit)? = null,
    onClearClick: (() -> Unit)? = null,
    leadingAction: WholesaleSearchHeaderLeadingAction = WholesaleSearchHeaderLeadingAction.Menu,
    onBackClick: (() -> Unit)? = null,
    onMessageClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = BBColors.Surface,
        shadowElevation = BBSpacing.Space1
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BBColors.Surface)
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
                WholesaleHeaderActionButton(
                    icon = if (
                        leadingAction == WholesaleSearchHeaderLeadingAction.Back
                    ) {
                        Icons.AutoMirrored.Outlined.ArrowBack
                    } else {
                        Icons.Outlined.Menu
                    },
                    contentDescription = if (
                        leadingAction == WholesaleSearchHeaderLeadingAction.Back
                    ) {
                        "Geri"
                    } else {
                        "Menü"
                    },
                    onClick = {
                        if (
                            leadingAction == WholesaleSearchHeaderLeadingAction.Back
                        ) {
                            onBackClick?.invoke() ?: onMenuClick()
                        } else {
                            onMenuClick()
                        }
                    }
                )

                WholesaleSearchInput(
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange,
                    placeholder = placeholder,
                    onSearchClick = onSearchClick,
                    onClearClick = onClearClick,
                    modifier = Modifier.weight(1f)
                )

                WholesaleHeaderActionButton(
                    icon = Icons.Outlined.MailOutline,
                    contentDescription = "Mesajlar",
                    onClick = onMessageClick
                )

                WholesaleHeaderActionButton(
                    icon = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorilerim",
                    onClick = onFavoriteClick
                )
            }
        }
    }
}

@Composable
private fun WholesaleHeaderActionButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    BbIconBoxIcon(
        icon = icon,
        contentDescription = contentDescription,
        size = BbIconBoxSize.Medium,
        backgroundColor = BBColors.SurfaceMuted,
        iconColor = BBColors.TextStrong,
        borderColor = BBColors.Border,
        borderWidth = BBSpacing.Divider,
        bordered = true,
        radius = BBRadius.xl,
        onClick = onClick
    )
}

@Composable
private fun WholesaleSearchInput(
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
        color = BBColors.SurfaceMuted,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = BBColors.BorderStrong
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
                    color = BBColors.TextMuted
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
                        tint = BBColors.TextMuted,
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
                            tint = BBColors.TextMuted
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = BBColors.TextStrong,
                unfocusedTextColor = BBColors.TextStrong,
                disabledTextColor = BBColors.TextMuted,
                focusedContainerColor = BBColors.Transparent,
                unfocusedContainerColor = BBColors.Transparent,
                disabledContainerColor = BBColors.Transparent,
                focusedIndicatorColor = BBColors.Transparent,
                unfocusedIndicatorColor = BBColors.Transparent,
                disabledIndicatorColor = BBColors.Transparent,
                cursorColor = BBColors.Primary
            )
        )
    }
}
