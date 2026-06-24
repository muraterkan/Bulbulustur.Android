package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
    val badgeShape = if (size == BbBadgeSize.Dot) {
        BBRadius.IconBoxSoft
    } else {
        BBRadius.Badge
    }

    val badgeModifier = modifier
        .defaultMinSize(
            minWidth = getBbBadgeMinWidth(size),
            minHeight = getBbBadgeMinHeight(size)
        )
        .clip(badgeShape)
        .background(containerColor)
        .then(
            if (bordered) {
                Modifier.border(
                    border = BorderStroke(
                        width = BBSpacing.BorderThin,
                        color = getBbBadgeBorderColor(variant)
                    ),
                    shape = badgeShape
                )
            } else {
                Modifier
            }
        )
        .padding(getBbBadgePadding(size))

    Box(
        modifier = badgeModifier,
        contentAlignment = Alignment.Center
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
        BbBadgeVariant.B2B -> BBColors.Blue.Blue500
        BbBadgeVariant.B2C -> BBColors.Orange.Orange500
        BbBadgeVariant.Success -> BBColors.Green.Green500
        BbBadgeVariant.Warning -> BBColors.Orange.Orange500
        BbBadgeVariant.Danger -> MaterialTheme.colorScheme.error
        BbBadgeVariant.Info -> BBColors.Turquoise.Turquoise500
        BbBadgeVariant.Dark -> BBColors.Navy.Navy900
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
        BbBadgeVariant.B2B -> BBColors.White
        BbBadgeVariant.B2C -> BBColors.White
        BbBadgeVariant.Success -> BBColors.White
        BbBadgeVariant.Warning -> BBColors.Gray.Gray900
        BbBadgeVariant.Danger -> MaterialTheme.colorScheme.onError
        BbBadgeVariant.Info -> BBColors.White
        BbBadgeVariant.Dark -> BBColors.White
        BbBadgeVariant.Light -> MaterialTheme.colorScheme.onSurfaceVariant
        BbBadgeVariant.Soft -> MaterialTheme.colorScheme.onPrimaryContainer
    }
}

@Composable
private fun getBbBadgeBorderColor(
    variant: BbBadgeVariant
): Color {
    return when (variant) {
        BbBadgeVariant.Primary -> MaterialTheme.colorScheme.primary.copy(alpha = BBAlpha.Faint)
        BbBadgeVariant.Secondary -> MaterialTheme.colorScheme.secondary.copy(alpha = BBAlpha.Faint)
        BbBadgeVariant.B2B -> BBColors.Blue.Blue200
        BbBadgeVariant.B2C -> BBColors.Orange.Orange200
        BbBadgeVariant.Success -> BBColors.Green.Green200
        BbBadgeVariant.Warning -> BBColors.Orange.Orange200
        BbBadgeVariant.Danger -> MaterialTheme.colorScheme.error.copy(alpha = BBAlpha.Faint)
        BbBadgeVariant.Info -> BBColors.Turquoise.Turquoise200
        BbBadgeVariant.Dark -> BBColors.Navy.Navy700
        BbBadgeVariant.Light -> MaterialTheme.colorScheme.outlineVariant
        BbBadgeVariant.Soft -> MaterialTheme.colorScheme.primary.copy(alpha = BBAlpha.OverlayHeavy)
    }
}

private fun getBbBadgeMinWidth(
    size: BbBadgeSize
) = when (size) {
    BbBadgeSize.Dot -> BBLayout.BadgeDotSize
    BbBadgeSize.Small -> BBLayout.BadgeSmallSize
    BbBadgeSize.Medium -> BBLayout.BadgeMediumSize
}

private fun getBbBadgeMinHeight(
    size: BbBadgeSize
) = when (size) {
    BbBadgeSize.Dot -> BBLayout.BadgeDotSize
    BbBadgeSize.Small -> BBLayout.BadgeSmallSize
    BbBadgeSize.Medium -> BBLayout.BadgeMediumSize
}

private fun getBbBadgePadding(
    size: BbBadgeSize
): PaddingValues {
    return when (size) {
        BbBadgeSize.Dot -> PaddingValues(BBSpacing.None)

        BbBadgeSize.Small -> PaddingValues(
            horizontal = BBSpacing.BadgePaddingHorizontal,
            vertical = BBSpacing.BadgePaddingVertical
        )

        BbBadgeSize.Medium -> PaddingValues(
            horizontal = BBSpacing.BadgePaddingHorizontal,
            vertical = BBSpacing.BadgePaddingVertical
        )
    }
}

