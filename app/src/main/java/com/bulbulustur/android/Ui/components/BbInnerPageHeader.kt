package com.bulbulustur.android.Ui.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Ui.theme.BbLayout
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing

@Composable
fun BbInnerPageHeader(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    actionIcon: ImageVector? = null,
    actionContentDescription: String? = null,
    onActionClick: (() -> Unit)? = null,
    actionContent: (@Composable () -> Unit)? = null
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shadowElevation = BbSpacing.ElevationSm
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
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
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
            ) {
                BbInnerHeaderActionButton(
                    icon = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Geri",
                    onClick = onBackClick
                )

                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                when {
                    actionContent != null -> {
                        actionContent()
                    }

                    actionIcon != null && onActionClick != null -> {
                        BbInnerHeaderActionButton(
                            icon = actionIcon,
                            contentDescription = actionContentDescription ?: title,
                            onClick = onActionClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BbInnerHeaderActionButton(
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
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        iconColor = MaterialTheme.colorScheme.onSurface,
        borderColor = MaterialTheme.colorScheme.outlineVariant,
        borderWidth = BbSpacing.BorderThin,
        bordered = true,
        radius = BbRadius.xl
    )
}