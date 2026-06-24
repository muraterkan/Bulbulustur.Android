/*
 * Bulbulustur Android Native Buyer App
 * Design System / Icon Size Tokens
 *
 * This file is the single source of truth for icon, icon-box, avatar,
 * logo-mark and action control dimensions.
 *
 * Rules:
 * - Feature screens must not use raw icon sizes such as 18.dp, 24.dp, 48.dp.
 * - Feature screens should use BbIcon semantic tokens whenever possible.
 * - Raw dp values are allowed here because this file defines the token scale.
 * - Layout/media widths and large page-specific dimensions should live in BbLayout, not here.
 */

package com.bulbulustur.android.Application.wwwroot.DesignTokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object BBIcon {

    /*
     * Primitive icon size scale
     */

    val Size3Xs: Dp = 12.dp
    val Size2Xs: Dp = 14.dp
    val SizeXs: Dp = 16.dp
    val SizeSm: Dp = 18.dp
    val SizeMd: Dp = 20.dp
    val SizeLg: Dp = 24.dp
    val SizeXl: Dp = 28.dp
    val Size2Xl: Dp = 32.dp
    val Size3Xl: Dp = 40.dp
    val Size4Xl: Dp = 48.dp
    val Size5Xl: Dp = 56.dp
    val Size6Xl: Dp = 64.dp

    /*
     * Semantic icon sizes
     */

    val Default: Dp = SizeLg

    val Inline: Dp = SizeSm
    val Compact: Dp = SizeXs
    val Ui: Dp = SizeMd
    val Action: Dp = SizeMd
    val Section: Dp = SizeLg
    val Feature: Dp = SizeXl
    val Hero: Dp = Size3Xl
    val Empty: Dp = Size4Xl

    /*
     * Navigation / shell icon sizes
     */

    val TopBarIcon: Dp = SizeLg
    val HeaderIcon: Dp = SizeLg
    val BackIcon: Dp = SizeLg
    val CloseIcon: Dp = SizeLg
    val BottomNavigationIcon: Dp = SizeLg
    val TabIcon: Dp = SizeMd

    /*
     * Button / chip / badge icon sizes
     */

    val ButtonIcon: Dp = SizeMd
    val ButtonIconSmall: Dp = SizeSm
    val ButtonIconLarge: Dp = SizeLg

    val ChipIcon: Dp = SizeSm
    val BadgeIcon: Dp = SizeSm

    /*
     * Commerce icon sizes
     */

    val ProductCardActionIcon: Dp = SizeMd
    val ProductBadgeIcon: Dp = SizeSm
    val PriceIcon: Dp = SizeSm
    val RatingIcon: Dp = SizeSm
    val StoreIcon: Dp = SizeLg
    val BasketIcon: Dp = SizeLg
    val OrderIcon: Dp = SizeLg
    val RfqIcon: Dp = SizeLg

    /*
     * Empty / state icon sizes
     */

    val EmptyStateIcon: Dp = Empty
    val LoadingStateIcon: Dp = Size3Xl
    val ErrorStateIcon: Dp = Size4Xl
    val SuccessStateIcon: Dp = Size4Xl
    val WarningStateIcon: Dp = Size4Xl

    /*
     * Primitive icon box scale
     */

    val Box2Xs: Dp = 24.dp
    val BoxXs: Dp = 28.dp
    val BoxSm: Dp = 32.dp
    val BoxMd: Dp = 40.dp
    val BoxLg: Dp = 48.dp
    val BoxXl: Dp = 56.dp
    val Box2Xl: Dp = 64.dp
    val Box3Xl: Dp = 80.dp
    val Box4Xl: Dp = 96.dp
    val Box5Xl: Dp = 120.dp

    /*
     * Semantic icon box sizes
     */

    val InlineBox: Dp = BoxSm
    val ActionBox: Dp = BoxMd
    val HeaderActionBox: Dp = BoxLg
    val CardActionBox: Dp = BoxLg
    val FeatureBox: Dp = BoxXl
    val HeroBox: Dp = Box3Xl
    val EmptyStateBox: Dp = Box3Xl

    /*
     * Avatar / logo mark sizes
     */

    val AvatarXs: Dp = BoxSm
    val AvatarSm: Dp = BoxMd
    val AvatarMd: Dp = BoxLg
    val AvatarLg: Dp = Box2Xl
    val AvatarXl: Dp = Box3Xl

    val LogoMarkSm: Dp = BoxMd
    val LogoMarkMd: Dp = BoxLg
    val LogoMarkLg: Dp = Box2Xl
    val LogoMarkXl: Dp = Box3Xl

    /*
     * Media icon sizes
     */

    val ProductMediaIcon: Dp = Size4Xl
    val StoreMediaIcon: Dp = Size4Xl
    val CompanyMediaIcon: Dp = Size4Xl
    val CampaignMediaIcon: Dp = Size4Xl
}

