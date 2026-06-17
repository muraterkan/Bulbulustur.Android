/*
 * Bulbulustur Android Native Buyer App
 * Design System / Layout Dimension Tokens
 *
 * This file is the single source of truth for shared layout, media,
 * logo, carousel, bottom action and page block dimensions.
 *
 * Rules:
 * - BbSpacing is for spacing, padding and gaps.
 * - BbIcon is for icons, icon boxes, avatars and logo marks.
 * - BbLayout is for shared width, height, min/max size and media dimensions.
 * - Feature screens should not use raw layout dimensions such as 168.dp, 236.dp, 390.dp.
 * - Raw dp values are allowed here because this file defines shared layout tokens.
 * - Page-specific one-off values should first be reviewed before becoming tokens.
 */

package com.bulbulustur.android.Application.wwwroot.DesignTokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object BBLayout {

    /*
     * Generic component heights
     */

    val DividerHeight: Dp = BBSpacing.Divider

    val TopBarHeight: Dp = BBSpacing.Space16
    val BottomNavigationHeight: Dp = BBSpacing.Space18

    val SearchHeaderHeight: Dp = BBSpacing.Space12
    val SearchInputHeight: Dp = BBSpacing.Space12

    val BottomActionBarHeight: Dp = BBSpacing.Space16
    val BottomCtaHeight: Dp = BBSpacing.Space13

    val ToolbarActionSize: Dp = BBIcon.HeaderActionBox
    val CardActionSize: Dp = BBIcon.CardActionBox

    /*
     * Button / control dimensions
     */

    val ControlHeightSmall: Dp = BBSpacing.Space9
    val ControlHeightMedium: Dp = BBSpacing.Space11
    val ControlHeightLarge: Dp = BBSpacing.Space13

    val SegmentedControlHeight: Dp = BBSpacing.Space11
    val QuantityControlHeight: Dp = BBSpacing.Space10
    val QuantityControlWidth: Dp = BBSpacing.Space14

    /*
 * Chip / badge dimensions
 */

    val ChipHeightSmall: Dp = BBSpacing.Space7
    val ChipHeightMedium: Dp = BBSpacing.Space9

    val BadgeDotSize: Dp = BBSpacing.Space2
    val BadgeSmallSize: Dp = BBSpacing.Space5
    val BadgeMediumSize: Dp = BBSpacing.Space6

    /*
     * Logo dimensions
     */

    val LogoWidthSmall: Dp = 150.dp
    val LogoWidthMedium: Dp = 178.dp
    val LogoWidthLarge: Dp = 230.dp

    val LogoHeightSmall: Dp = 36.dp
    val LogoHeightMedium: Dp = 42.dp
    val LogoHeightLarge: Dp = 48.dp

    /*
     * Header / hero dimensions
     */

    val CompactHeaderHeight: Dp = BBSpacing.Space16
    val AccountHeaderHeight: Dp = 124.dp
    val AccountHeroHeight: Dp = 132.dp

    val StoreHeroHeight: Dp = 160.dp
    val CampaignHeroHeight: Dp = 120.dp
    val CategoryHeroHeight: Dp = 120.dp

    /*
     * Product detail media
     */

    val ProductDetailImageMaxHeight: Dp = 390.dp
    val ProductDetailThumbWidth: Dp = 58.dp
    val ProductDetailThumbHeight: Dp = 42.dp

    val ProductDetailInfoMinHeight: Dp = 78.dp
    val ProductDetailRfqMinHeight: Dp = 84.dp
    val ProductDetailSellerCardMinHeight: Dp = 72.dp

    /*
     * Product / commerce cards
     */

    val ProductCardWidthSmall: Dp = 132.dp
    val ProductCardWidthMedium: Dp = 168.dp
    val ProductCardWidthLarge: Dp = 236.dp

    val ProductCardMediaHeightSmall: Dp = 96.dp
    val ProductCardMediaHeightMedium: Dp = 118.dp
    val ProductCardMediaHeightLarge: Dp = 188.dp

    val MiniProductCardWidth: Dp = 132.dp
    val RelatedProductCardWidth: Dp = 236.dp

    /*
     * Home / carousel cards
     */

    val HomeCompactCardWidth: Dp = 168.dp
    val HomeMediumCardWidth: Dp = 176.dp
    val HomeWideCardWidth: Dp = 220.dp
    val HomeFeatureCardWidth: Dp = 236.dp

    val HorizontalCategoryCardWidth: Dp = 168.dp
    val HorizontalStoreCardWidth: Dp = 220.dp
    val HorizontalCampaignCardWidth: Dp = 236.dp

    /*
     * Campaign / category / store listing cards
     */

    val CampaignListImageSize: Dp = 54.dp
    val CampaignDetailImageSize: Dp = 58.dp
    val CategoryListImageSize: Dp = 54.dp
    val StoreProductImageSize: Dp = 76.dp

    val SearchSuggestionImageSize: Dp = 48.dp
    val SearchCategoryImageSize: Dp = 68.dp

    /*
     * Store / company dimensions
     */

    val StoreLogoSizeSmall: Dp = BBIcon.AvatarMd
    val StoreLogoSizeMedium: Dp = BBIcon.AvatarLg
    val StoreLogoSizeLarge: Dp = 82.dp

    val CompanyLogoSizeSmall: Dp = BBIcon.AvatarMd
    val CompanyLogoSizeMedium: Dp = BBIcon.AvatarLg
    val CompanyLogoSizeLarge: Dp = BBIcon.AvatarXl

    val CompanyContactBlockHeight: Dp = 72.dp
    val CompanyMapPreviewHeight: Dp = 160.dp

    /*
     * Account dimensions
     */

    val AccountAvatarSize: Dp = 106.dp
    val AboutAppLogoOuterSize: Dp = 122.dp
    val AboutAppLogoInnerSize: Dp = 102.dp

    val AccountQuickActionSize: Dp = BBIcon.CardActionBox
    val AccountStatBlockHeight: Dp = BBSpacing.Space9

    /*
     * Basket / checkout dimensions
     */

    val CheckoutStepIconSize: Dp = 38.dp
    val CheckoutAddressIconSize: Dp = BBSpacing.Space13
    val CheckoutPaymentCardIconSize: Dp = 46.dp
    val CheckoutCargoLogoSize: Dp = BBSpacing.Space12

    val OrderSuccessIconBoxSize: Dp = BBSpacing.Space18
    val OrderSuccessMiniIconSize: Dp = BBSpacing.Space11

    /*
     * Empty / state dimensions
     */

    val EmptyStateBoxSize: Dp = BBIcon.EmptyStateBox
    val EmptyStateIllustrationSize: Dp = BBSpacing.Space20

    /*
     * Special fixed widths found during token audit
     * Use these only while consolidating existing screens.
     * If a value becomes page-specific noise, remove it after refactor.
     */

    /*
     * Brand scroller dimensions
     */

    val BrandScrollerItemWidth: Dp = 76.dp
    val BrandScrollerItemHeight: Dp = 98.dp
    val BrandScrollerLogoSize: Dp = StoreProductImageSize

    /*
     * Screen container widths
     */

    val ScreenContainerWidthCompact: Dp = 520.dp
    val ScreenContainerWidthDefault: Dp = 720.dp
    val ScreenContainerWidthWide: Dp = 960.dp

    val FixedWidth92: Dp = 92.dp
    val FixedWidth142: Dp = 142.dp
    val FixedWidth150: Dp = 150.dp
    val FixedWidth168: Dp = 168.dp
    val FixedWidth176: Dp = 176.dp
    val FixedWidth178: Dp = 178.dp
    val FixedWidth180: Dp = 180.dp
    val FixedWidth212: Dp = 212.dp
    val FixedWidth220: Dp = 220.dp
    val FixedWidth230: Dp = 230.dp
    val FixedWidth236: Dp = 236.dp

    val FixedHeight40: Dp = 40.dp
    val FixedHeight42: Dp = 42.dp
    val FixedHeight44: Dp = 44.dp
    val FixedHeight48: Dp = 48.dp
    val FixedHeight52: Dp = 52.dp
    val FixedHeight58: Dp = 58.dp
    val FixedHeight72: Dp = 72.dp
    val FixedHeight96: Dp = 96.dp
    val FixedHeight118: Dp = 118.dp
    val FixedHeight120: Dp = 120.dp
    val FixedHeight124: Dp = 124.dp
    val FixedHeight132: Dp = 132.dp
    val FixedHeight160: Dp = 160.dp
    val FixedHeight188: Dp = 188.dp
    val FixedHeight390: Dp = 390.dp
}
