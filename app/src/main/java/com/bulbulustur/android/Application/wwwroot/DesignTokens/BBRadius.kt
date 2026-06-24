/*
 * Bulbulustur Android Native Buyer App
 * Design System / Radius Tokens
 *
 * This file is the single source of truth for corner radius and shared shapes.
 *
 * Rules:
 * - Feature screens must not use raw RoundedCornerShape(...) values.
 * - Feature screens should use BbRadius semantic shapes such as Card, Button, Input, Sheet.
 * - Raw dp values and RoundedCornerShape(...) are allowed here because this file defines the token scale.
 * - Radius tokens are theme-independent and can be shared with future iOS / SwiftUI design tokens.
 */

package com.bulbulustur.android.Application.wwwroot.DesignTokens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object BBRadius {

    /*
     * Primitive radius scale
     */

    val none: Dp = 0.dp
    val xs: Dp = 3.dp
    val sm: Dp = 6.dp
    val md: Dp = 8.dp
    val lg: Dp = 12.dp
    val xl: Dp = 16.dp
    val xxl: Dp = 24.dp
    val xxxl: Dp = 28.dp
    val hero: Dp = 30.dp
    val pill: Dp = 999.dp

    /*
     * Primitive shapes
     */

    val NoneShape: Shape = RoundedCornerShape(none)
    val XsShape: Shape = RoundedCornerShape(xs)
    val SmShape: Shape = RoundedCornerShape(sm)
    val MdShape: Shape = RoundedCornerShape(md)
    val LgShape: Shape = RoundedCornerShape(lg)
    val XlShape: Shape = RoundedCornerShape(xl)
    val XxlShape: Shape = RoundedCornerShape(xxl)
    val XxxlShape: Shape = RoundedCornerShape(xxxl)
    val HeroShape: Shape = RoundedCornerShape(hero)
    val PillShape: Shape = RoundedCornerShape(pill)

    /*
     * Core component shapes
     */

    val Card: Shape = LgShape
    val CardCompact: Shape = MdShape
    val CardLarge: Shape = XlShape
    val CardLoose: Shape = XxlShape

    val Button: Shape = MdShape
    val ButtonSoft: Shape = LgShape
    val ButtonPill: Shape = PillShape

    val Chip: Shape = PillShape
    val Badge: Shape = PillShape
    val Input: Shape = MdShape
    val InputLarge: Shape = LgShape

    /*
     * Layout / shell shapes
     */

    val Sheet: Shape = XxlShape
    val BottomSheet: Shape = XxlShape
    val Dialog: Shape = XxlShape
    val Modal: Shape = XxlShape

    val Header: Shape = XlShape
    val Search: Shape = XlShape
    val SearchPill: Shape = PillShape

    /*
     * Commerce component shapes
     */

    val ProductCard: Shape = Card
    val ProductImage: Shape = LgShape
    val ProductBadge: Shape = PillShape

    val StoreCard: Shape = Card
    val StoreLogo: Shape = LgShape

    val BasketLine: Shape = Card
    val OrderCard: Shape = Card

    /*
     * Visual / hero shapes
     */

    val HeroCard: Shape = HeroShape
    val FeatureCard: Shape = XxlShape
    val IconBox: Shape = LgShape
    val IconBoxSoft: Shape = XlShape
    val Avatar: Shape = PillShape
}

