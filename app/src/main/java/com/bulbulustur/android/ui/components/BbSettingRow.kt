package com.bulbulustur.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.theme.*

@Composable
fun BbSettingRow(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingContent: (@Composable (() -> Unit))? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
            .padding(
                horizontal = BbSpacing.md,
                vertical = BbSpacing.md
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (leadingContent != null) {
            leadingContent()

            Spacer(
                modifier = Modifier.width(BbSpacing.md)
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = BbTypography.bodyLarge,
                color = BbColors.TextStrong
            )

            if (!subtitle.isNullOrBlank()) {
                Text(
                    text = subtitle,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = BbColors.TextMuted
        )
    }
}