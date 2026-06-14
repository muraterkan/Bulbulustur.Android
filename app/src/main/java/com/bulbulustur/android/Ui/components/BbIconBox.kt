package com.bulbulustur.android.Ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing

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
    size: BbIconBoxSize = BbIconBoxSize.Medium,
    shape: BbIconBoxShape = BbIconBoxShape.Box,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    borderWidth: Dp = BbSpacing.BorderThin,
    bordered: Boolean = false,
    radius: Dp? = null,
    customShape: Shape? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val boxSize = getBbIconBoxSize(size)
    val boxShape = customShape ?: getBbIconBoxShape(
        size = size,
        shape = shape,
        radius = radius
    )

    Box(
        modifier = modifier
            .size(boxSize)
            .clip(boxShape)
            .background(backgroundColor)
            .then(
                if (bordered && borderWidth > BbSpacing.None) {
                    Modifier.border(
                        border = BorderStroke(
                            width = borderWidth,
                            color = borderColor
                        ),
                        shape = boxShape
                    )
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            content()
        }
    }
}

@Composable
fun BbIconBoxIcon(
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: BbIconBoxSize = BbIconBoxSize.Medium,
    shape: BbIconBoxShape = BbIconBoxShape.Box,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    iconColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    borderWidth: Dp = BbSpacing.BorderThin,
    bordered: Boolean = false,
    radius: Dp? = null,
    customShape: Shape? = null
) {
    BbIconBox(
        modifier = modifier,
        size = size,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = iconColor,
        borderColor = borderColor,
        borderWidth = borderWidth,
        bordered = bordered,
        radius = radius,
        customShape = customShape
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.size(getBbIconSize(size))
        )
    }
}

@Composable
fun BbIconCircle(
    modifier: Modifier = Modifier,
    size: BbIconBoxSize = BbIconBoxSize.Medium,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    borderWidth: Dp = BbSpacing.BorderThin,
    bordered: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    BbIconBox(
        modifier = modifier,
        size = size,
        shape = BbIconBoxShape.Circle,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        borderColor = borderColor,
        borderWidth = borderWidth,
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
    shape: BbIconBoxShape,
    radius: Dp?
): Shape {
    if (radius != null) {
        return RoundedCornerShape(radius)
    }

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