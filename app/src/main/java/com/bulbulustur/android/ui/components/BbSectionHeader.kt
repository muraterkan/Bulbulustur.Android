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
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun BbSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = BbSpacing.md,
                vertical = BbSpacing.sm
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            if (!subtitle.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(BbSpacing.xs))

                Text(
                    text = subtitle,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }

        if (!actionText.isNullOrBlank()) {
            Row(
                modifier = Modifier.clickable(enabled = onActionClick != null) {
                    onActionClick?.invoke()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = actionText,
                    style = BbTypography.labelMedium,
                    color = BbColors.Primary
                )

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = BbColors.Primary
                )
            }
        }
    }
}