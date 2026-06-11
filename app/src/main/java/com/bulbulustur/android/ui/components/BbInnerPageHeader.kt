package com.bulbulustur.android.ui.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun BbInnerPageHeader(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    actionIcon: ImageVector? = null,
    actionContentDescription: String? = null,
    onActionClick: (() -> Unit)? = null
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
                    .height(BbSpacing.TopBarHeight),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (actionIcon != null && onActionClick != null) {
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

@Composable
private fun BbInnerHeaderActionButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.size(BbIcon.BoxMd),
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.TopBarIcon)
            )
        }
    }
}