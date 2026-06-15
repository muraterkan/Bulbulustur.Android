package com.bulbulustur.android.Features.areas.b2c.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.bulbulustur.android.Ui.components.BbIconBoxIcon
import com.bulbulustur.android.Ui.components.BbIconBoxSize
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbLayout
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTypography

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
                RetailHeaderActionButton(
                    icon = if (leadingAction == RetailSearchHeaderLeadingAction.Back) {
                        Icons.AutoMirrored.Outlined.ArrowBack
                    } else {
                        Icons.Outlined.Menu
                    },
                    contentDescription = if (leadingAction == RetailSearchHeaderLeadingAction.Back) "Geri" else "Menü",
                    onClick = {
                        if (leadingAction == RetailSearchHeaderLeadingAction.Back) {
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
                    icon = Icons.Outlined.MailOutline,
                    contentDescription = "Mesajlar",
                    onClick = onMessageClick
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
        modifier = Modifier.clickable {
            onClick()
        },
        icon = icon,
        contentDescription = contentDescription,
        size = BbIconBoxSize.Medium,
        backgroundColor = BbColors.SurfaceMuted,
        iconColor = BbColors.TextStrong,
        borderColor = BbColors.Border,
        borderWidth = BbSpacing.Divider,
        bordered = true,
        radius = BbRadius.xl
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
                    onClick = { onSearchClick?.invoke() },
                    enabled = onSearchClick != null
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Ara",
                        tint = BbColors.TextMuted,
                        modifier = Modifier.height(BbIcon.TopBarIcon)
                    )
                }
            },
            trailingIcon = {
                if (searchText.isNotBlank()) {
                    IconButton(
                        onClick = { onClearClick?.invoke() }
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