package com.bulbulustur.android.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object BbRadius {

    val none: Dp = 0.dp
    val xs: Dp = 3.dp
    val sm: Dp = 6.dp
    val md: Dp = 8.dp
    val lg: Dp = 12.dp
    val xl: Dp = 16.dp
    val xxl: Dp = 24.dp
    val pill: Dp = 999.dp

    val XsShape: Shape = RoundedCornerShape(xs)
    val SmShape: Shape = RoundedCornerShape(sm)
    val MdShape: Shape = RoundedCornerShape(md)
    val LgShape: Shape = RoundedCornerShape(lg)
    val XlShape: Shape = RoundedCornerShape(xl)
    val XxlShape: Shape = RoundedCornerShape(xxl)
    val PillShape: Shape = RoundedCornerShape(pill)

    val Card: Shape = LgShape
    val Button: Shape = MdShape
    val Chip: Shape = PillShape
    val Badge: Shape = PillShape
    val Input: Shape = MdShape
}