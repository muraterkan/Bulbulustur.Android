package com.bulbulustur.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius

enum class BbIconBoxVariant {
    Default,
    Primary,
    Accent,
    B2B,
    B2C,
    Success,
    Warning,
    Danger,
    Info,
    Dark,
    Light
}

enum class BbIconBoxSize {
    TwoXs,
    Xs,
    Small,
    Medium,
    Large,
    Xl,
    TwoXl,
    ThreeXl
}

enum class BbIconBoxShape {
    Box,
    Circle
}

@Composable
fun BbIconBox(
    modifier: Modifier = Modifier,
    variant: BbIconBoxVariant = BbIconBoxVariant.Default,
    size: BbIconBoxSize = BbIconBoxSize.Medium,
    shape: BbIconBoxShape = BbIconBoxShape.Box,
    bordered: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    val boxSize = getBbIconBoxSize(size)
    val boxShape = getBbIconBoxShape(size = size, shape = shape)
    val backgroundColor = getBbIconBoxBackgroundColor(variant)
    val borderColor = getBbIconBoxBorderColor(variant)

    Box(
        modifier = modifier
            .size(boxSize)
            .clip(boxShape)
            .background(backgroundColor)
            .then(
                if (bordered) {
                    Modifier.border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = borderColor
                        ),
                        shape = boxShape
                    )
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun BbIconCircle(
    modifier: Modifier = Modifier,
    variant: BbIconBoxVariant = BbIconBoxVariant.Default,
    size: BbIconBoxSize = BbIconBoxSize.Medium,
    bordered: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    BbIconBox(
        modifier = modifier,
        variant = variant,
        size = size,
        shape = BbIconBoxShape.Circle,
        bordered = bordered,
        content = content
    )
}

fun getBbIconSize(
    size: BbIconBoxSize
): Dp {
    return when (size) {
        BbIconBoxSize.TwoXs -> BbIcon.Size2Xs
        BbIconBoxSize.Xs -> BbIcon.SizeXs
        BbIconBoxSize.Small -> BbIcon.SizeSm
        BbIconBoxSize.Medium -> BbIcon.SizeLg
        BbIconBoxSize.Large -> BbIcon.SizeLg
        BbIconBoxSize.Xl -> BbIcon.SizeXl
        BbIconBoxSize.TwoXl -> BbIcon.Size2Xl
        BbIconBoxSize.ThreeXl -> BbIcon.Size3Xl
    }
}

private fun getBbIconBoxSize(
    size: BbIconBoxSize
): Dp {
    return when (size) {
        BbIconBoxSize.TwoXs -> BbIcon.Box2Xs
        BbIconBoxSize.Xs -> BbIcon.BoxXs
        BbIconBoxSize.Small -> BbIcon.BoxSm
        BbIconBoxSize.Medium -> BbIcon.BoxMd
        BbIconBoxSize.Large -> BbIcon.BoxLg
        BbIconBoxSize.Xl -> BbIcon.BoxXl
        BbIconBoxSize.TwoXl -> BbIcon.Box2Xl
        BbIconBoxSize.ThreeXl -> BbIcon.Box3Xl
    }
}

private fun getBbIconBoxShape(
    size: BbIconBoxSize,
    shape: BbIconBoxShape
): Shape {
    if (shape == BbIconBoxShape.Circle) {
        return CircleShape
    }

    return when (size) {
        BbIconBoxSize.TwoXs -> BbRadius.MdShape
        BbIconBoxSize.Xs -> BbRadius.MdShape
        BbIconBoxSize.Small -> BbRadius.MdShape
        BbIconBoxSize.Medium -> BbRadius.LgShape
        BbIconBoxSize.Large -> BbRadius.XlShape
        BbIconBoxSize.Xl -> BbRadius.XlShape
        BbIconBoxSize.TwoXl -> BbRadius.XlShape
        BbIconBoxSize.ThreeXl -> BbRadius.XxlShape
    }
}

@Composable
private fun getBbIconBoxBackgroundColor(
    variant: BbIconBoxVariant
): Color {
    return when (variant) {
        BbIconBoxVariant.Default -> MaterialTheme.colorScheme.surfaceVariant
        BbIconBoxVariant.Primary -> MaterialTheme.colorScheme.primaryContainer
        BbIconBoxVariant.Accent -> BbColors.Purple.Purple50
        BbIconBoxVariant.B2B -> BbColors.Blue.Blue50
        BbIconBoxVariant.B2C -> BbColors.Orange.Orange50
        BbIconBoxVariant.Success -> BbColors.Green.Green50
        BbIconBoxVariant.Warning -> BbColors.Orange.Orange50
        BbIconBoxVariant.Danger -> BbColors.Red.Red50
        BbIconBoxVariant.Info -> BbColors.Turquoise.Turquoise50
        BbIconBoxVariant.Dark -> BbColors.Navy.Navy900
        BbIconBoxVariant.Light -> MaterialTheme.colorScheme.surface
    }
}

@Composable
private fun getBbIconBoxBorderColor(
    variant: BbIconBoxVariant
): Color {
    return when (variant) {
        BbIconBoxVariant.Default -> MaterialTheme.colorScheme.outlineVariant
        BbIconBoxVariant.Primary -> MaterialTheme.colorScheme.primary.copy(alpha = 0.24f)
        BbIconBoxVariant.Accent -> BbColors.Purple.Purple200
        BbIconBoxVariant.B2B -> BbColors.Blue.Blue200
        BbIconBoxVariant.B2C -> BbColors.Orange.Orange200
        BbIconBoxVariant.Success -> BbColors.Green.Green200
        BbIconBoxVariant.Warning -> BbColors.Orange.Orange200
        BbIconBoxVariant.Danger -> BbColors.Red.Red200
        BbIconBoxVariant.Info -> BbColors.Turquoise.Turquoise200
        BbIconBoxVariant.Dark -> BbColors.Navy.Navy700
        BbIconBoxVariant.Light -> MaterialTheme.colorScheme.outlineVariant
    }
}

@Composable
fun getBbIconBoxContentColor(
    variant: BbIconBoxVariant
): Color {
    return when (variant) {
        BbIconBoxVariant.Default -> MaterialTheme.colorScheme.onSurfaceVariant
        BbIconBoxVariant.Primary -> MaterialTheme.colorScheme.primary
        BbIconBoxVariant.Accent -> BbColors.Purple.Purple700
        BbIconBoxVariant.B2B -> BbColors.Blue.Blue700
        BbIconBoxVariant.B2C -> BbColors.Orange.Orange700
        BbIconBoxVariant.Success -> BbColors.Green.Green700
        BbIconBoxVariant.Warning -> BbColors.Orange.Orange700
        BbIconBoxVariant.Danger -> BbColors.Red.Red700
        BbIconBoxVariant.Info -> BbColors.Turquoise.Turquoise700
        BbIconBoxVariant.Dark -> BbColors.White
        BbIconBoxVariant.Light -> MaterialTheme.colorScheme.onSurface
    }
}