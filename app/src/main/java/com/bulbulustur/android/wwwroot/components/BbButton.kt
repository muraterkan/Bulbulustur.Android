package com.bulbulustur.android.wwwroot.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.wwwroot.theme.BbAlpha
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbLayout
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

enum class BbButtonVariant {
    Primary,
    Secondary,
    Dark,
    Light,
    Success,
    Warning,
    Danger,
    Info,
    Ghost,
    Outline
}

enum class BbButtonSize {
    Small,
    Medium,
    Large
}

@Composable
fun BbButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: BbButtonVariant = BbButtonVariant.Primary,
    size: BbButtonSize = BbButtonSize.Medium,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    val buttonColors = getBbButtonColors(variant)
    val contentPadding = getBbButtonPadding(size)
    val minHeight = getBbButtonMinHeight(size)

    val buttonEnabled = enabled && !isLoading

    if (variant == BbButtonVariant.Ghost) {
        TextButton(
            onClick = onClick,
            modifier = modifier.defaultMinSize(minHeight = minHeight),
            enabled = buttonEnabled,
            shape = BbRadius.Button,
            colors = buttonColors,
            contentPadding = contentPadding
        ) {
            BbButtonContent(
                text = text,
                size = size,
                isLoading = isLoading,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon
            )
        }

        return
    }

    if (variant == BbButtonVariant.Outline) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier.defaultMinSize(minHeight = minHeight),
            enabled = buttonEnabled,
            shape = BbRadius.Button,
            colors = buttonColors,
            border = BorderStroke(
                width = BbSpacing.BorderThin,
                color = if (enabled) {
                    MaterialTheme.colorScheme.outline
                } else {
                    MaterialTheme.colorScheme.outlineVariant
                }
            ),
            contentPadding = contentPadding
        ) {
            BbButtonContent(
                text = text,
                size = size,
                isLoading = isLoading,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon
            )
        }

        return
    }

    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = minHeight),
        enabled = buttonEnabled,
        shape = BbRadius.Button,
        colors = buttonColors,
        contentPadding = contentPadding,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = BbSpacing.ElevationNone,
            pressedElevation = BbSpacing.ElevationNone,
            focusedElevation = BbSpacing.ElevationNone,
            hoveredElevation = BbSpacing.ElevationNone,
            disabledElevation = BbSpacing.ElevationNone
        )
    ) {
        BbButtonContent(
            text = text,
            size = size,
            isLoading = isLoading,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

@Composable
private fun BbButtonContent(
    text: String,
    size: BbButtonSize,
    isLoading: Boolean,
    leadingIcon: (@Composable () -> Unit)?,
    trailingIcon: (@Composable () -> Unit)?
) {
    val iconSize = when (size) {
        BbButtonSize.Small -> BbIcon.ButtonIconSmall
        BbButtonSize.Medium -> BbIcon.ButtonIcon
        BbButtonSize.Large -> BbIcon.ButtonIconLarge
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(iconSize),
                strokeWidth = BbSpacing.ProgressStroke,
                color = LocalContentColor.current
            )
            return@Row
        }

        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.size(BbSpacing.ButtonGap))
        }

        Text(
            text = text,
            style = when (size) {
                BbButtonSize.Small -> MaterialTheme.typography.labelMedium
                BbButtonSize.Medium -> MaterialTheme.typography.labelLarge
                BbButtonSize.Large -> MaterialTheme.typography.titleSmall
            }
        )

        if (trailingIcon != null) {
            Spacer(modifier = Modifier.size(BbSpacing.ButtonGap))
            trailingIcon()
        }
    }
}

@Composable
private fun getBbButtonColors(
    variant: BbButtonVariant
) = when (variant) {
    BbButtonVariant.Primary -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = BbAlpha.DisabledContainer),
        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Secondary -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = BbAlpha.DisabledContainer),
        disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Dark -> ButtonDefaults.buttonColors(
        containerColor = BbColors.Navy.Navy900,
        contentColor = BbColors.White,
        disabledContainerColor = BbColors.Navy.Navy900.copy(alpha = BbAlpha.DisabledContainer),
        disabledContentColor = BbColors.White.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Light -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = BbAlpha.DisabledContainer),
        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Success -> ButtonDefaults.buttonColors(
        containerColor = BbColors.Green.Green500,
        contentColor = BbColors.White,
        disabledContainerColor = BbColors.Green.Green500.copy(alpha = BbAlpha.DisabledContainer),
        disabledContentColor = BbColors.White.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Warning -> ButtonDefaults.buttonColors(
        containerColor = BbColors.Orange.Orange500,
        contentColor = BbColors.Gray.Gray900,
        disabledContainerColor = BbColors.Orange.Orange500.copy(alpha = BbAlpha.DisabledContainer),
        disabledContentColor = BbColors.Gray.Gray900.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Danger -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onError,
        disabledContainerColor = MaterialTheme.colorScheme.error.copy(alpha = BbAlpha.DisabledContainer),
        disabledContentColor = MaterialTheme.colorScheme.onError.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Info -> ButtonDefaults.buttonColors(
        containerColor = BbColors.Blue.Blue500,
        contentColor = BbColors.White,
        disabledContainerColor = BbColors.Blue.Blue500.copy(alpha = BbAlpha.DisabledContainer),
        disabledContentColor = BbColors.White.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Ghost -> ButtonDefaults.textButtonColors(
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = BbAlpha.DisabledContent)
    )

    BbButtonVariant.Outline -> ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = BbAlpha.DisabledContent)
    )
}

private fun getBbButtonPadding(
    size: BbButtonSize
): PaddingValues {
    return when (size) {
        BbButtonSize.Small -> PaddingValues(
            horizontal = BbSpacing.ButtonPaddingHorizontalCompact,
            vertical = BbSpacing.ButtonPaddingVerticalCompact
        )

        BbButtonSize.Medium -> PaddingValues(
            horizontal = BbSpacing.ButtonPaddingHorizontal,
            vertical = BbSpacing.ButtonPaddingVertical
        )

        BbButtonSize.Large -> PaddingValues(
            horizontal = BbSpacing.ButtonPaddingHorizontalLoose,
            vertical = BbSpacing.ButtonPaddingVerticalLoose
        )
    }
}

private fun getBbButtonMinHeight(
    size: BbButtonSize
) = when (size) {
    BbButtonSize.Small -> BbLayout.ControlHeightSmall
    BbButtonSize.Medium -> BbLayout.ControlHeightMedium
    BbButtonSize.Large -> BbLayout.ControlHeightLarge
}