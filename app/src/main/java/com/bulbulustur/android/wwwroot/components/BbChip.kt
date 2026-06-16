package com.bulbulustur.android.wwwroot.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbAlpha

enum class BbChipVariant {
    Default,
    Primary,
    B2B,
    B2C,
    Success,
    Warning,
    Danger,
    Info,
    Soft
}

enum class BbChipSize {
    Small,
    Medium
}

@Composable
fun BbChip(
    text: String,
    modifier: Modifier = Modifier,
    variant: BbChipVariant = BbChipVariant.Default,
    size: BbChipSize = BbChipSize.Medium,
    selected: Boolean = false,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    if (onClick == null) {
        BbStaticChip(
            text = text,
            modifier = modifier,
            variant = variant,
            size = size,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )

        return
    }

    if (selected) {
        FilterChip(
            selected = true,
            onClick = onClick,
            modifier = modifier.defaultMinSize(minHeight = getBbChipMinHeight(size)),
            enabled = enabled,
            shape = BbRadius.Chip,
            label = {
                BbChipLabel(text = text, size = size)
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = getBbChipContainerColor(variant, selected = true),
                selectedLabelColor = getBbChipContentColor(variant, selected = true),
                selectedLeadingIconColor = getBbChipContentColor(variant, selected = true),
                selectedTrailingIconColor = getBbChipContentColor(variant, selected = true),
                containerColor = getBbChipContainerColor(variant, selected = false),
                labelColor = getBbChipContentColor(variant, selected = false),
                disabledContainerColor = getBbChipContainerColor(variant, selected = false).copy(alpha = BbAlpha.DisabledContainer),
                disabledLabelColor = getBbChipContentColor(variant, selected = false).copy(alpha = BbAlpha.DisabledLabel)
            ),
            border = getBbChipBorder(variant, selected = true)
        )

        return
    }

    AssistChip(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = getBbChipMinHeight(size)),
        enabled = enabled,
        shape = BbRadius.Chip,
        label = {
            BbChipLabel(text = text, size = size)
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = getBbChipContainerColor(variant, selected = false),
            labelColor = getBbChipContentColor(variant, selected = false),
            leadingIconContentColor = getBbChipContentColor(variant, selected = false),
            trailingIconContentColor = getBbChipContentColor(variant, selected = false),
            disabledContainerColor = getBbChipContainerColor(variant, selected = false).copy(alpha = BbAlpha.DisabledContainer),
            disabledLabelColor = getBbChipContentColor(variant, selected = false).copy(alpha = BbAlpha.DisabledLabel)
        ),
        border = getBbChipBorder(variant, selected = false)
    )
}

@Composable
fun BbSuggestionChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: BbChipVariant = BbChipVariant.Default,
    size: BbChipSize = BbChipSize.Medium,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null
) {
    SuggestionChip(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = getBbChipMinHeight(size)),
        enabled = enabled,
        shape = BbRadius.Chip,
        label = {
            BbChipLabel(text = text, size = size)
        },
        icon = icon,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = getBbChipContainerColor(variant, selected = false),
            labelColor = getBbChipContentColor(variant, selected = false),
            iconContentColor = getBbChipContentColor(variant, selected = false),
            disabledContainerColor = getBbChipContainerColor(variant, selected = false).copy(alpha = BbAlpha.DisabledContainer),
            disabledLabelColor = getBbChipContentColor(variant, selected = false).copy(alpha = BbAlpha.DisabledLabel)
        ),
        border = getBbChipBorder(variant, selected = false)
    )
}

@Composable
private fun BbStaticChip(
    text: String,
    modifier: Modifier,
    variant: BbChipVariant,
    size: BbChipSize,
    leadingIcon: (@Composable () -> Unit)?,
    trailingIcon: (@Composable () -> Unit)?
) {
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = getBbChipMinHeight(size))
            .padding(
                horizontal = getBbChipHorizontalPadding(size),
                vertical = getBbChipVerticalPadding(size)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            leadingIcon()
            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier.padding(start = BbSpacing.IconTextGapSmall)
            )
        }

        BbChipLabel(text = text, size = size)

        if (trailingIcon != null) {
            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier.padding(start = BbSpacing.IconTextGapSmall)
            )
            trailingIcon()
        }
    }
}

@Composable
private fun BbChipLabel(
    text: String,
    size: BbChipSize
) {
    Text(
        text = text,
        style = when (size) {
            BbChipSize.Small -> MaterialTheme.typography.labelSmall
            BbChipSize.Medium -> MaterialTheme.typography.labelMedium
        }
    )
}

@Composable
private fun getBbChipContainerColor(
    variant: BbChipVariant,
    selected: Boolean
) = when (variant) {
    BbChipVariant.Default -> if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    BbChipVariant.Primary -> if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    BbChipVariant.B2B -> if (selected) {
        BbColors.Blue.Blue500
    } else {
        BbColors.Blue.Blue50
    }

    BbChipVariant.B2C -> if (selected) {
        BbColors.Orange.Orange500
    } else {
        BbColors.Orange.Orange50
    }

    BbChipVariant.Success -> if (selected) {
        BbColors.Green.Green500
    } else {
        BbColors.Green.Green50
    }

    BbChipVariant.Warning -> if (selected) {
        BbColors.Orange.Orange500
    } else {
        BbColors.Orange.Orange50
    }

    BbChipVariant.Danger -> if (selected) {
        BbColors.Red.Red500
    } else {
        BbColors.Red.Red50
    }

    BbChipVariant.Info -> if (selected) {
        BbColors.Turquoise.Turquoise500
    } else {
        BbColors.Turquoise.Turquoise50
    }

    BbChipVariant.Soft -> if (selected) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }
}

@Composable
private fun getBbChipContentColor(
    variant: BbChipVariant,
    selected: Boolean
) = when (variant) {
    BbChipVariant.Default -> if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    BbChipVariant.Primary -> if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }

    BbChipVariant.B2B -> if (selected) {
        BbColors.White
    } else {
        BbColors.Blue.Blue700
    }

    BbChipVariant.B2C -> if (selected) {
        BbColors.White
    } else {
        BbColors.Orange.Orange700
    }

    BbChipVariant.Success -> if (selected) {
        BbColors.White
    } else {
        BbColors.Green.Green700
    }

    BbChipVariant.Warning -> if (selected) {
        BbColors.Gray.Gray900
    } else {
        BbColors.Orange.Orange700
    }

    BbChipVariant.Danger -> if (selected) {
        BbColors.White
    } else {
        BbColors.Red.Red700
    }

    BbChipVariant.Info -> if (selected) {
        BbColors.White
    } else {
        BbColors.Turquoise.Turquoise700
    }

    BbChipVariant.Soft -> if (selected) {
        MaterialTheme.colorScheme.onSecondary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
}

@Composable
private fun getBbChipBorder(
    variant: BbChipVariant,
    selected: Boolean
): BorderStroke? {
    if (selected) {
        return null
    }

    val borderColor = when (variant) {
        BbChipVariant.Default -> MaterialTheme.colorScheme.outlineVariant
        BbChipVariant.Primary -> MaterialTheme.colorScheme.primary.copy(alpha = 0.28f)
        BbChipVariant.B2B -> BbColors.Blue.Blue200
        BbChipVariant.B2C -> BbColors.Orange.Orange200
        BbChipVariant.Success -> BbColors.Green.Green200
        BbChipVariant.Warning -> BbColors.Orange.Orange200
        BbChipVariant.Danger -> BbColors.Red.Red200
        BbChipVariant.Info -> BbColors.Turquoise.Turquoise200
        BbChipVariant.Soft -> MaterialTheme.colorScheme.outlineVariant
    }

    return BorderStroke(
        width = 1.dp,
        color = borderColor
    )
}

private fun getBbChipMinHeight(
    size: BbChipSize
) = when (size) {
    BbChipSize.Small -> 28.dp
    BbChipSize.Medium -> 34.dp
}

private fun getBbChipHorizontalPadding(
    size: BbChipSize
) = when (size) {
    BbChipSize.Small -> BbSpacing.Space2
    BbChipSize.Medium -> BbSpacing.ChipPaddingHorizontal
}

private fun getBbChipVerticalPadding(
    size: BbChipSize
) = when (size) {
    BbChipSize.Small -> BbSpacing.Space1
    BbChipSize.Medium -> BbSpacing.ChipPaddingVertical
}