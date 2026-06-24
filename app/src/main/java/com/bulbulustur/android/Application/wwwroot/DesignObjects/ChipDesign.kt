package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha

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
            shape = BBRadius.Chip,
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
                disabledContainerColor = getBbChipContainerColor(variant, selected = false).copy(alpha = BBAlpha.DisabledContainer),
                disabledLabelColor = getBbChipContentColor(variant, selected = false).copy(alpha = BBAlpha.DisabledLabel)
            ),
            border = getBbChipBorder(variant, selected = true)
        )

        return
    }

    AssistChip(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = getBbChipMinHeight(size)),
        enabled = enabled,
        shape = BBRadius.Chip,
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
            disabledContainerColor = getBbChipContainerColor(variant, selected = false).copy(alpha = BBAlpha.DisabledContainer),
            disabledLabelColor = getBbChipContentColor(variant, selected = false).copy(alpha = BBAlpha.DisabledLabel)
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
        shape = BBRadius.Chip,
        label = {
            BbChipLabel(text = text, size = size)
        },
        icon = icon,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = getBbChipContainerColor(variant, selected = false),
            labelColor = getBbChipContentColor(variant, selected = false),
            iconContentColor = getBbChipContentColor(variant, selected = false),
            disabledContainerColor = getBbChipContainerColor(variant, selected = false).copy(alpha = BBAlpha.DisabledContainer),
            disabledLabelColor = getBbChipContentColor(variant, selected = false).copy(alpha = BBAlpha.DisabledLabel)
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
            Spacer(
                modifier = Modifier.padding(start = BBSpacing.IconTextGapSmall)
            )
        }

        BbChipLabel(text = text, size = size)

        if (trailingIcon != null) {
            Spacer(
                modifier = Modifier.padding(start = BBSpacing.IconTextGapSmall)
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
        BBColors.Blue.Blue500
    } else {
        BBColors.Blue.Blue50
    }

    BbChipVariant.B2C -> if (selected) {
        BBColors.Orange.Orange500
    } else {
        BBColors.Orange.Orange50
    }

    BbChipVariant.Success -> if (selected) {
        BBColors.Green.Green500
    } else {
        BBColors.Green.Green50
    }

    BbChipVariant.Warning -> if (selected) {
        BBColors.Orange.Orange500
    } else {
        BBColors.Orange.Orange50
    }

    BbChipVariant.Danger -> if (selected) {
        BBColors.Red.Red500
    } else {
        BBColors.Red.Red50
    }

    BbChipVariant.Info -> if (selected) {
        BBColors.Turquoise.Turquoise500
    } else {
        BBColors.Turquoise.Turquoise50
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
        BBColors.White
    } else {
        BBColors.Blue.Blue700
    }

    BbChipVariant.B2C -> if (selected) {
        BBColors.White
    } else {
        BBColors.Orange.Orange700
    }

    BbChipVariant.Success -> if (selected) {
        BBColors.White
    } else {
        BBColors.Green.Green700
    }

    BbChipVariant.Warning -> if (selected) {
        BBColors.Gray.Gray900
    } else {
        BBColors.Orange.Orange700
    }

    BbChipVariant.Danger -> if (selected) {
        BBColors.White
    } else {
        BBColors.Red.Red700
    }

    BbChipVariant.Info -> if (selected) {
        BBColors.White
    } else {
        BBColors.Turquoise.Turquoise700
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
        BbChipVariant.B2B -> BBColors.Blue.Blue200
        BbChipVariant.B2C -> BBColors.Orange.Orange200
        BbChipVariant.Success -> BBColors.Green.Green200
        BbChipVariant.Warning -> BBColors.Orange.Orange200
        BbChipVariant.Danger -> BBColors.Red.Red200
        BbChipVariant.Info -> BBColors.Turquoise.Turquoise200
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
    BbChipSize.Small -> BBSpacing.Space2
    BbChipSize.Medium -> BBSpacing.ChipPaddingHorizontal
}

private fun getBbChipVerticalPadding(
    size: BbChipSize
) = when (size) {
    BbChipSize.Small -> BBSpacing.Space1
    BbChipSize.Medium -> BBSpacing.ChipPaddingVertical
}

