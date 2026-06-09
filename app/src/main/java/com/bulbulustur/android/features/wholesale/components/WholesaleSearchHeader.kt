package com.bulbulustur.android.features.wholesale.components

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
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
    onBackClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = BbColors.Surface,
        shadowElevation = 3.dp
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
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                IconButton(
                    onClick = {
                        if (leadingAction == WholesaleSearchHeaderLeadingAction.Back) {
                            if (onBackClick != null) {
                                onBackClick()
                            } else {
                                onMenuClick()
                            }
                        } else {
                            onMenuClick()
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (leadingAction == WholesaleSearchHeaderLeadingAction.Back) {
                            Icons.AutoMirrored.Outlined.ArrowBack
                        } else {
                            Icons.Outlined.Menu
                        },
                        contentDescription = if (leadingAction == WholesaleSearchHeaderLeadingAction.Back) {
                            "Geri"
                        } else {
                            "Kategoriler"
                        },
                        tint = BbColors.TextStrong
                    )
                }

                WholesaleSearchInput(
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange,
                    placeholder = placeholder,
                    onSearchClick = onSearchClick,
                    onClearClick = onClearClick,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = onFavoriteClick
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorilerim",
                        tint = BbColors.TextStrong
                    )
                }
            }
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
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(BbRadius.xl),
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
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
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = BbColors.Primary
            )
        )
    }
}