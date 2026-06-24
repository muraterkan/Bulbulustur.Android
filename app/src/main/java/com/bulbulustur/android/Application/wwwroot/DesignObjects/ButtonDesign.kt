package com.bulbulustur.android.Application.wwwroot.DesignObjects

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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
            shape = BBRadius.Button,
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
            shape = BBRadius.Button,
            colors = buttonColors,
            border = BorderStroke(
                width = BBSpacing.BorderThin,
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
        shape = BBRadius.Button,
        colors = buttonColors,
        contentPadding = contentPadding,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = BBSpacing.ElevationNone,
            pressedElevation = BBSpacing.ElevationNone,
            focusedElevation = BBSpacing.ElevationNone,
            hoveredElevation = BBSpacing.ElevationNone,
            disabledElevation = BBSpacing.ElevationNone
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
        BbButtonSize.Small -> BBIcon.ButtonIconSmall
        BbButtonSize.Medium -> BBIcon.ButtonIcon
        BbButtonSize.Large -> BBIcon.ButtonIconLarge
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(iconSize),
                strokeWidth = BBSpacing.ProgressStroke,
                color = LocalContentColor.current
            )
            return@Row
        }

        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.size(BBSpacing.ButtonGap))
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
            Spacer(modifier = Modifier.size(BBSpacing.ButtonGap))
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
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = BBAlpha.DisabledContainer),
        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Secondary -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = BBAlpha.DisabledContainer),
        disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Dark -> ButtonDefaults.buttonColors(
        containerColor = BBColors.Navy.Navy900,
        contentColor = BBColors.White,
        disabledContainerColor = BBColors.Navy.Navy900.copy(alpha = BBAlpha.DisabledContainer),
        disabledContentColor = BBColors.White.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Light -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = BBAlpha.DisabledContainer),
        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Success -> ButtonDefaults.buttonColors(
        containerColor = BBColors.Green.Green500,
        contentColor = BBColors.White,
        disabledContainerColor = BBColors.Green.Green500.copy(alpha = BBAlpha.DisabledContainer),
        disabledContentColor = BBColors.White.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Warning -> ButtonDefaults.buttonColors(
        containerColor = BBColors.Orange.Orange500,
        contentColor = BBColors.Gray.Gray900,
        disabledContainerColor = BBColors.Orange.Orange500.copy(alpha = BBAlpha.DisabledContainer),
        disabledContentColor = BBColors.Gray.Gray900.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Danger -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onError,
        disabledContainerColor = MaterialTheme.colorScheme.error.copy(alpha = BBAlpha.DisabledContainer),
        disabledContentColor = MaterialTheme.colorScheme.onError.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Info -> ButtonDefaults.buttonColors(
        containerColor = BBColors.Blue.Blue500,
        contentColor = BBColors.White,
        disabledContainerColor = BBColors.Blue.Blue500.copy(alpha = BBAlpha.DisabledContainer),
        disabledContentColor = BBColors.White.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Ghost -> ButtonDefaults.textButtonColors(
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = BBAlpha.DisabledContent)
    )

    BbButtonVariant.Outline -> ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = BBAlpha.DisabledContent)
    )
}

private fun getBbButtonPadding(
    size: BbButtonSize
): PaddingValues {
    return when (size) {
        BbButtonSize.Small -> PaddingValues(
            horizontal = BBSpacing.ButtonPaddingHorizontalCompact,
            vertical = BBSpacing.ButtonPaddingVerticalCompact
        )

        BbButtonSize.Medium -> PaddingValues(
            horizontal = BBSpacing.ButtonPaddingHorizontal,
            vertical = BBSpacing.ButtonPaddingVertical
        )

        BbButtonSize.Large -> PaddingValues(
            horizontal = BBSpacing.ButtonPaddingHorizontalLoose,
            vertical = BBSpacing.ButtonPaddingVerticalLoose
        )
    }
}

private fun getBbButtonMinHeight(
    size: BbButtonSize
) = when (size) {
    BbButtonSize.Small -> BBLayout.ControlHeightSmall
    BbButtonSize.Medium -> BBLayout.ControlHeightMedium
    BbButtonSize.Large -> BBLayout.ControlHeightLarge
}

