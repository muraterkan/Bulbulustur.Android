package com.bulbulustur.android.Application.Views.Shared.Components

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxIcon
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun BbInnerPageHeader(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    actionIcon: ImageVector? = null,
    actionContentDescription: String? = null,
    onActionClick: (() -> Unit)? = null,
    actionContent: (@Composable () -> Unit)? = null
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shadowElevation = BBSpacing.ElevationSm
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface
                )
                .windowInsetsPadding(
                    WindowInsets.statusBars
                )
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
                    .heightIn(
                        min = BBLayout.TopBarHeight
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.IconTextGap
                )
            ) {
                BbInnerHeaderActionButton(
                    icon = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = BBLocalization.Current.Get(key = "d94f43b6-5081-4e7d-b66e-8d19b37e9751", fallback = "Geri"),
                    onClick = onBackClick
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (!subtitle.isNullOrBlank()) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                when {
                    actionContent != null -> {
                        actionContent()
                    }

                    actionIcon != null && onActionClick != null -> {
                        BbInnerHeaderActionButton(
                            icon = actionIcon,
                            contentDescription = actionContentDescription
                                ?: title,
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
        icon = icon,
        contentDescription = contentDescription,
        size = BbIconBoxSize.Medium,
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        iconColor = MaterialTheme.colorScheme.onSurface,
        borderColor = MaterialTheme.colorScheme.outlineVariant,
        borderWidth = BBSpacing.BorderThin,
        bordered = true,
        radius = BBRadius.xl,
        onClick = onClick
    )
}
