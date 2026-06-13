/*
 * Bulbulustur Android Native Buyer App
 * Design System / Spacing Tokens
 *
 * This file is the single source of truth for spacing, padding, gaps,
 * border widths, stroke widths and elevation values.
 *
 * Rules:
 * - Feature screens must not use raw dp values such as 8.dp, 16.dp, 24.dp.
 * - Feature screens should use BbSpacing for spacing, padding and gaps.
 * - Width, height, min/max size and media dimensions belong to BbLayout.
 * - Icon, avatar, logo-mark and icon-box dimensions belong to BbIcon.
 * - Raw dp values are allowed here because this file defines the spacing token scale.
 * - Spacing tokens are theme-independent and can be shared with future iOS / SwiftUI design tokens.
 */

package com.bulbulustur.android.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object BbSpacing {

    /*
     * Primitive spacing scale
     */

    val None: Dp = 0.dp

    val Space1: Dp = 4.dp
    val Space2: Dp = 8.dp
    val Space3: Dp = 12.dp
    val Space4: Dp = 16.dp
    val Space5: Dp = 20.dp
    val Space6: Dp = 24.dp
    val Space7: Dp = 28.dp
    val Space8: Dp = 32.dp
    val Space9: Dp = 36.dp
    val Space10: Dp = 40.dp
    val Space11: Dp = 44.dp
    val Space12: Dp = 48.dp
    val Space13: Dp = 52.dp
    val Space14: Dp = 56.dp
    val Space15: Dp = 60.dp
    val Space16: Dp = 64.dp
    val Space18: Dp = 72.dp
    val Space20: Dp = 80.dp
    val Space24: Dp = 96.dp

    /*
     * Compact aliases
     */

    val xs: Dp = Space1
    val sm: Dp = Space2
    val md: Dp = Space4
    val lg: Dp = Space6
    val xl: Dp = Space8
    val xxl: Dp = Space12

    /*
     * Border / divider / stroke
     */

    val Hairline: Dp = 1.dp

    val BorderThin: Dp = Hairline
    val BorderMedium: Dp = 2.dp
    val BorderNormal: Dp = BorderMedium

    val Divider: Dp = BorderThin

    val StrokeThin: Dp = BorderThin
    val StrokeMedium: Dp = BorderMedium
    val ProgressStroke: Dp = StrokeMedium

    /*
     * Elevation
     */

    val ElevationNone: Dp = None
    val ElevationXs: Dp = Hairline
    val ElevationSm: Dp = Space1
    val ElevationMd: Dp = Space2
    val ElevationLg: Dp = Space3
    val ElevationXl: Dp = Space5
    val Elevation2Xl: Dp = Space6

    /*
     * Page spacing
     */

    val PageHorizontal: Dp = Space4
    val PageHorizontalCompact: Dp = Space3
    val PageHorizontalWide: Dp = Space5

    val PageTop: Dp = Space6
    val PageTopCompact: Dp = Space4
    val PageTopSpaced: Dp = Space8

    val PageBottom: Dp = Space12
    val PageBottomCompact: Dp = Space5
    val PageBottomLoose: Dp = Space16
    val PageBottomWithCta: Dp = Space16

    /*
     * Section spacing
     */

    val SectionGap: Dp = Space6
    val SectionGapCompact: Dp = Space4
    val SectionGapLoose: Dp = Space8

    val SectionHeaderGap: Dp = Space2
    val SectionContentGap: Dp = Space4

    /*
     * Card spacing
     */

    val CardPadding: Dp = Space4
    val CardPaddingCompact: Dp = Space3
    val CardPaddingLoose: Dp = Space5

    val CardGap: Dp = Space4
    val CardGapCompact: Dp = Space3
    val CardGapLoose: Dp = Space6

    /*
     * Button spacing
     */

    val ButtonPaddingHorizontal: Dp = Space6
    val ButtonPaddingHorizontalCompact: Dp = Space4
    val ButtonPaddingHorizontalLoose: Dp = Space8

    val ButtonPaddingVertical: Dp = Space3
    val ButtonPaddingVerticalCompact: Dp = Space2
    val ButtonPaddingVerticalLoose: Dp = Space4

    val ButtonGap: Dp = Space2

    /*
     * Chip spacing
     */

    val ChipPaddingHorizontal: Dp = Space3
    val ChipPaddingHorizontalCompact: Dp = Space2

    val ChipPaddingVertical: Dp = Space2
    val ChipPaddingVerticalCompact: Dp = Space1

    val ChipGap: Dp = Space2

    /*
     * Badge spacing
     */

    val BadgePaddingHorizontal: Dp = Space2
    val BadgePaddingVertical: Dp = Space1

    /*
     * Input / form spacing
     */

    val InputPaddingHorizontal: Dp = Space4
    val InputPaddingVertical: Dp = Space3

    val InputGap: Dp = Space3
    val FormSectionGap: Dp = Space5
    val FormFieldGap: Dp = Space4
    val FormActionGap: Dp = Space3

    /*
     * Icon / text spacing
     */

    val IconTextGap: Dp = Space2
    val IconTextGapSmall: Dp = Space1
    val IconTextGapLarge: Dp = Space3

    /*
     * List spacing
     */

    val ListItemGap: Dp = Space3
    val ListSectionGap: Dp = Space5
    val ListDividerInset: Dp = Space4

    /*
     * Product card spacing
     */

    val ProductCardPadding: Dp = Space4
    val ProductCardPaddingCompact: Dp = Space3

    val ProductCardGap: Dp = Space3
    val ProductCardMediaPadding: Dp = Space3

    /*
     * Store card spacing
     */

    val StoreCardPadding: Dp = Space4
    val StoreCardPaddingCompact: Dp = Space3

    val StoreCardGap: Dp = Space3

    /*
     * Checkout / basket spacing
     */

    val CheckoutStepGap: Dp = Space4
    val CheckoutSectionGap: Dp = Space5
    val CheckoutSummaryGap: Dp = Space3

    val BasketLineGap: Dp = Space4
    val BasketSummaryGap: Dp = Space3

    /*
     * Account spacing
     */

    val AccountSectionGap: Dp = Space5
    val AccountMenuGap: Dp = Space3
    val AccountStatGap: Dp = Space3

    /*
     * Support / settings spacing
     */

    val SettingsGroupGap: Dp = Space5
    val SettingsItemGap: Dp = Space3

    val SupportSectionGap: Dp = Space5
    val SupportItemGap: Dp = Space3
}