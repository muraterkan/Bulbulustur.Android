package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
    borderWidth: Dp = BBSpacing.BorderThin,
    bordered: Boolean = false,
    radius: Dp? = null,
    customShape: Shape? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val boxSize = getBbIconBoxSize(size)

    val boxShape = customShape ?: getBbIconBoxShape(
        size = size,
        shape = shape,
        radius = radius
    )

    val border = if (
        bordered &&
        borderWidth > BBSpacing.None
    ) {
        BorderStroke(
            width = borderWidth,
            color = borderColor
        )
    } else {
        null
    }

    if (onClick != null) {
        Surface(
            onClick = onClick,
            modifier = modifier.size(boxSize),
            enabled = enabled,
            shape = boxShape,
            color = backgroundColor,
            contentColor = contentColor,
            border = border
        ) {
            BbIconBoxContent(
                content = content
            )
        }

        return
    }

    Surface(
        modifier = modifier.size(boxSize),
        shape = boxShape,
        color = backgroundColor,
        contentColor = contentColor,
        border = border
    ) {
        BbIconBoxContent(
            content = content
        )
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
    borderWidth: Dp = BBSpacing.BorderThin,
    bordered: Boolean = false,
    radius: Dp? = null,
    customShape: Shape? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
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
        customShape = customShape,
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.size(
                getBbIconSize(size)
            )
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
    borderWidth: Dp = BBSpacing.BorderThin,
    bordered: Boolean = false,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
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
        enabled = enabled,
        onClick = onClick,
        content = content
    )
}

@Composable
private fun BbIconBoxContent(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = content
    )
}

fun getBbIconSize(
    size: BbIconBoxSize
): Dp {
    return when (size) {
        BbIconBoxSize.TwoXs -> BBIcon.Size2Xs
        BbIconBoxSize.Xs -> BBIcon.SizeXs
        BbIconBoxSize.Small -> BBIcon.SizeSm
        BbIconBoxSize.Medium -> BBIcon.SizeLg
        BbIconBoxSize.Large -> BBIcon.SizeLg
        BbIconBoxSize.Xl -> BBIcon.SizeXl
        BbIconBoxSize.TwoXl -> BBIcon.Size2Xl
        BbIconBoxSize.ThreeXl -> BBIcon.Size3Xl
    }
}

private fun getBbIconBoxSize(
    size: BbIconBoxSize
): Dp {
    return when (size) {
        BbIconBoxSize.TwoXs -> BBIcon.Box2Xs
        BbIconBoxSize.Xs -> BBIcon.BoxXs
        BbIconBoxSize.Small -> BBIcon.BoxSm
        BbIconBoxSize.Medium -> BBIcon.BoxMd
        BbIconBoxSize.Large -> BBIcon.BoxLg
        BbIconBoxSize.Xl -> BBIcon.BoxXl
        BbIconBoxSize.TwoXl -> BBIcon.Box2Xl
        BbIconBoxSize.ThreeXl -> BBIcon.Box3Xl
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
        BbIconBoxSize.TwoXs -> BBRadius.MdShape
        BbIconBoxSize.Xs -> BBRadius.MdShape
        BbIconBoxSize.Small -> BBRadius.MdShape
        BbIconBoxSize.Medium -> BBRadius.LgShape
        BbIconBoxSize.Large -> BBRadius.XlShape
        BbIconBoxSize.Xl -> BBRadius.XlShape
        BbIconBoxSize.TwoXl -> BBRadius.XlShape
        BbIconBoxSize.ThreeXl -> BBRadius.XxlShape
    }
}