package com.bulbulustur.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography
import androidx.compose.ui.unit.dp

@Composable
fun BbSearchBar(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onSearchClick: (() -> Unit)? = null,
    onClearClick: (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(BbRadius.lg),
        textStyle = BbTypography.bodyMedium,
        placeholder = {
            Text(
                text = placeholder,
                style = BbTypography.bodyMedium,
                color = BbColors.TextMuted
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.clickable(enabled = onSearchClick != null) {
                    onSearchClick?.invoke()
                }
            )
        },
        trailingIcon = {
            if (value.isNotBlank()) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Temizle",
                    tint = BbColors.TextMuted,
                    modifier = Modifier.clickable {
                        onClearClick?.invoke()
                    }
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = BbColors.SurfaceMuted,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = BbColors.Primary
        )
    )
}