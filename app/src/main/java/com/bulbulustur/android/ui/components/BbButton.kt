package com.bulbulustur.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

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
                width = 1.dp,
                color = if (enabled) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.outlineVariant
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
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp,
            disabledElevation = 0.dp
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
        BbButtonSize.Small -> BbIcon.SizeSm
        BbButtonSize.Medium -> BbIcon.ButtonIcon
        BbButtonSize.Large -> BbIcon.SizeLg
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(iconSize),
                strokeWidth = 2.dp,
                color = Color.Unspecified
            )
            return@Row
        }

        if (leadingIcon != null) {
            leadingIcon()
        }

        if (leadingIcon != null) {
            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier.size(BbSpacing.ButtonGap)
            )
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
            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier.size(BbSpacing.ButtonGap)
            )
        }

        if (trailingIcon != null) {
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
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.45f),
        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.65f)
    )

    BbButtonVariant.Secondary -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.45f),
        disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.65f)
    )

    BbButtonVariant.Dark -> ButtonDefaults.buttonColors(
        containerColor = BbColors.Navy.Navy900,
        contentColor = BbColors.White,
        disabledContainerColor = BbColors.Navy.Navy900.copy(alpha = 0.45f),
        disabledContentColor = BbColors.White.copy(alpha = 0.65f)
    )

    BbButtonVariant.Light -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.65f)
    )

    BbButtonVariant.Success -> ButtonDefaults.buttonColors(
        containerColor = BbColors.Green.Green500,
        contentColor = BbColors.White,
        disabledContainerColor = BbColors.Green.Green500.copy(alpha = 0.45f),
        disabledContentColor = BbColors.White.copy(alpha = 0.65f)
    )

    BbButtonVariant.Warning -> ButtonDefaults.buttonColors(
        containerColor = BbColors.Orange.Orange500,
        contentColor = BbColors.Gray.Gray900,
        disabledContainerColor = BbColors.Orange.Orange500.copy(alpha = 0.45f),
        disabledContentColor = BbColors.Gray.Gray900.copy(alpha = 0.65f)
    )

    BbButtonVariant.Danger -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onError,
        disabledContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.45f),
        disabledContentColor = MaterialTheme.colorScheme.onError.copy(alpha = 0.65f)
    )

    BbButtonVariant.Info -> ButtonDefaults.buttonColors(
        containerColor = BbColors.Blue.Blue500,
        contentColor = BbColors.White,
        disabledContainerColor = BbColors.Blue.Blue500.copy(alpha = 0.45f),
        disabledContentColor = BbColors.White.copy(alpha = 0.65f)
    )

    BbButtonVariant.Ghost -> ButtonDefaults.textButtonColors(
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)
    )

    BbButtonVariant.Outline -> ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
    )
}

private fun getBbButtonPadding(
    size: BbButtonSize
): PaddingValues {
    return when (size) {
        BbButtonSize.Small -> PaddingValues(
            horizontal = BbSpacing.Space4,
            vertical = BbSpacing.Space2
        )

        BbButtonSize.Medium -> PaddingValues(
            horizontal = BbSpacing.ButtonPaddingHorizontal,
            vertical = BbSpacing.ButtonPaddingVertical
        )

        BbButtonSize.Large -> PaddingValues(
            horizontal = BbSpacing.Space8,
            vertical = BbSpacing.Space4
        )
    }
}

private fun getBbButtonMinHeight(
    size: BbButtonSize
) = when (size) {
    BbButtonSize.Small -> 36.dp
    BbButtonSize.Medium -> 44.dp
    BbButtonSize.Large -> 52.dp
}