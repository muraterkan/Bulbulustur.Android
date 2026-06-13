package com.bulbulustur.android.features.wholesale.components

import androidx.compose.foundation.BorderStroke
import com.bulbulustur.android.ui.theme.BbLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

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
        color = BbColors.Surface,
        shadowElevation = BbSpacing.Space1
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BbColors.Surface)
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(
                    start = BbSpacing.Space2,
                    top = BbSpacing.Space2,
                    end = BbSpacing.Space2,
                    bottom = BbSpacing.Space2
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BbLayout.TopBarHeight),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                WholesaleHeaderActionButton(
                    icon = if (leadingAction == WholesaleSearchHeaderLeadingAction.Back) {
                        Icons.AutoMirrored.Outlined.ArrowBack
                    } else {
                        Icons.Outlined.Menu
                    },
                    contentDescription = if (leadingAction == WholesaleSearchHeaderLeadingAction.Back) {
                        "Geri"
                    } else {
                        "Menü"
                    },
                    onClick = {
                        if (leadingAction == WholesaleSearchHeaderLeadingAction.Back) {
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
    Surface(
        modifier = Modifier.size(BbIcon.BoxMd),
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = BbSpacing.Divider,
            color = BbColors.Border
        )
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.TopBarIcon)
            )
        }
    }
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
        modifier = modifier.height(BbLayout.TopBarHeight - BbSpacing.Space4),
        shape = RoundedCornerShape(BbRadius.xl),
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = BbSpacing.Divider,
            color = BbColors.BorderStrong
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
                    color = BbColors.TextMuted
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
                        tint = BbColors.TextMuted
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
                            tint = BbColors.TextMuted
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = BbColors.TextStrong,
                unfocusedTextColor = BbColors.TextStrong,
                disabledTextColor = BbColors.TextMuted,
                focusedContainerColor = BbColors.Transparent,
                unfocusedContainerColor = BbColors.Transparent,
                disabledContainerColor = BbColors.Transparent,
                focusedIndicatorColor = BbColors.Transparent,
                unfocusedIndicatorColor = BbColors.Transparent,
                disabledIndicatorColor = BbColors.Transparent,
                cursorColor = BbColors.Primary
            )
        )
    }
}