package com.bulbulustur.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

enum class BbBadgeVariant {
    Primary,
    Secondary,
    B2B,
    B2C,
    Success,
    Warning,
    Danger,
    Info,
    Dark,
    Light,
    Soft
}

enum class BbBadgeSize {
    Dot,
    Small,
    Medium
}

@Composable
fun BbBadge(
    text: String? = null,
    modifier: Modifier = Modifier,
    variant: BbBadgeVariant = BbBadgeVariant.Primary,
    size: BbBadgeSize = BbBadgeSize.Medium,
    bordered: Boolean = false
) {
    val containerColor = getBbBadgeContainerColor(variant)
    val contentColor = getBbBadgeContentColor(variant)

    val badgeModifier = modifier
        .defaultMinSize(
            minWidth = getBbBadgeMinWidth(size),
            minHeight = getBbBadgeMinHeight(size)
        )
        .clip(
            if (size == BbBadgeSize.Dot) {
                CircleShape
            } else {
                BbRadius.Badge
            }
        )
        .background(containerColor)
        .then(
            if (bordered) {
                Modifier.border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = getBbBadgeBorderColor(variant)
                    ),
                    shape = if (size == BbBadgeSize.Dot) {
                        CircleShape
                    } else {
                        BbRadius.Badge
                    }
                )
            } else {
                Modifier
            }
        )
        .padding(getBbBadgePadding(size))

    androidx.compose.foundation.layout.Box(
        modifier = badgeModifier,
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        if (size != BbBadgeSize.Dot && !text.isNullOrBlank()) {
            Text(
                text = text,
                color = contentColor,
                style = when (size) {
                    BbBadgeSize.Dot -> MaterialTheme.typography.labelSmall
                    BbBadgeSize.Small -> MaterialTheme.typography.labelSmall
                    BbBadgeSize.Medium -> MaterialTheme.typography.labelMedium
                },
                maxLines = 1
            )
        }
    }
}

@Composable
fun BbBadgedBox(
    badgeText: String? = null,
    modifier: Modifier = Modifier,
    variant: BbBadgeVariant = BbBadgeVariant.Danger,
    showBadge: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    BadgedBox(
        modifier = modifier,
        badge = {
            if (showBadge) {
                Badge(
                    containerColor = getBbBadgeContainerColor(variant),
                    contentColor = getBbBadgeContentColor(variant)
                ) {
                    if (!badgeText.isNullOrBlank()) {
                        Text(
                            text = badgeText,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        },
        content = content
    )
}

@Composable
private fun getBbBadgeContainerColor(
    variant: BbBadgeVariant
): Color {
    return when (variant) {
        BbBadgeVariant.Primary -> MaterialTheme.colorScheme.primary
        BbBadgeVariant.Secondary -> MaterialTheme.colorScheme.secondary
        BbBadgeVariant.B2B -> BbColors.Blue.Blue500
        BbBadgeVariant.B2C -> BbColors.Orange.Orange500
        BbBadgeVariant.Success -> BbColors.Green.Green500
        BbBadgeVariant.Warning -> BbColors.Orange.Orange500
        BbBadgeVariant.Danger -> MaterialTheme.colorScheme.error
        BbBadgeVariant.Info -> BbColors.Turquoise.Turquoise500
        BbBadgeVariant.Dark -> BbColors.Navy.Navy900
        BbBadgeVariant.Light -> MaterialTheme.colorScheme.surfaceVariant
        BbBadgeVariant.Soft -> MaterialTheme.colorScheme.primaryContainer
    }
}

@Composable
private fun getBbBadgeContentColor(
    variant: BbBadgeVariant
): Color {
    return when (variant) {
        BbBadgeVariant.Primary -> MaterialTheme.colorScheme.onPrimary
        BbBadgeVariant.Secondary -> MaterialTheme.colorScheme.onSecondary
        BbBadgeVariant.B2B -> BbColors.White
        BbBadgeVariant.B2C -> BbColors.White
        BbBadgeVariant.Success -> BbColors.White
        BbBadgeVariant.Warning -> BbColors.Gray.Gray900
        BbBadgeVariant.Danger -> MaterialTheme.colorScheme.onError
        BbBadgeVariant.Info -> BbColors.White
        BbBadgeVariant.Dark -> BbColors.White
        BbBadgeVariant.Light -> MaterialTheme.colorScheme.onSurfaceVariant
        BbBadgeVariant.Soft -> MaterialTheme.colorScheme.onPrimaryContainer
    }
}

@Composable
private fun getBbBadgeBorderColor(
    variant: BbBadgeVariant
): Color {
    return when (variant) {
        BbBadgeVariant.Primary -> MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        BbBadgeVariant.Secondary -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.35f)
        BbBadgeVariant.B2B -> BbColors.Blue.Blue200
        BbBadgeVariant.B2C -> BbColors.Orange.Orange200
        BbBadgeVariant.Success -> BbColors.Green.Green200
        BbBadgeVariant.Warning -> BbColors.Orange.Orange200
        BbBadgeVariant.Danger -> MaterialTheme.colorScheme.error.copy(alpha = 0.35f)
        BbBadgeVariant.Info -> BbColors.Turquoise.Turquoise200
        BbBadgeVariant.Dark -> BbColors.Navy.Navy700
        BbBadgeVariant.Light -> MaterialTheme.colorScheme.outlineVariant
        BbBadgeVariant.Soft -> MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
    }
}

private fun getBbBadgeMinWidth(
    size: BbBadgeSize
) = when (size) {
    BbBadgeSize.Dot -> 8.dp
    BbBadgeSize.Small -> 20.dp
    BbBadgeSize.Medium -> 24.dp
}

private fun getBbBadgeMinHeight(
    size: BbBadgeSize
) = when (size) {
    BbBadgeSize.Dot -> 8.dp
    BbBadgeSize.Small -> 20.dp
    BbBadgeSize.Medium -> 24.dp
}

private fun getBbBadgePadding(
    size: BbBadgeSize
): PaddingValues {
    return when (size) {
        BbBadgeSize.Dot -> PaddingValues(0.dp)

        BbBadgeSize.Small -> PaddingValues(
            horizontal = BbSpacing.BadgePaddingHorizontal,
            vertical = 2.dp
        )

        BbBadgeSize.Medium -> PaddingValues(
            horizontal = BbSpacing.Space2,
            vertical = BbSpacing.Space1
        )
    }
}