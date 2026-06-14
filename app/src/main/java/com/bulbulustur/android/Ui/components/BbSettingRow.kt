package com.bulbulustur.android.Ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbSpacing

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
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.CardPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingContent != null) {
            leadingContent()

            Spacer(
                modifier = Modifier.width(BbSpacing.IconTextGapLarge)
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (!subtitle.isNullOrBlank()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BbIcon.Inline)
        )
    }
}