package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutPriceSummary
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing


/*
 * ============================================================================
 * SHARED COMMERCE BOTTOM BAR
 *
 * Basket + Checkout aynı component.
 * ============================================================================
 */

@Composable
fun BbCommerceBottomBar(
    totalPriceText: String,
    canContinue: Boolean,
    summaryExpanded: Boolean,
    applyNavigationBarsPadding: Boolean = true,
    actionText: String = "Devam Et",
    onSummaryClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val navigationModifier =
        if (applyNavigationBarsPadding) {
            Modifier.navigationBarsPadding()
        } else {
            Modifier
        }

    Surface(
        color =
            MaterialTheme.colorScheme.surface,
        tonalElevation =
            BBSpacing.Space1,
        shadowElevation =
            BBSpacing.Space3
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .then(
                        navigationModifier
                    )
        ) {
            HorizontalDivider(
                color =
                    MaterialTheme.colorScheme
                        .outlineVariant
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal =
                                BBSpacing.PageHorizontal,
                            vertical =
                                BBSpacing.Space3
                        ),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space3
                    ),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                Row(
                    modifier =
                        Modifier
                            .weight(
                                1f
                            )
                            .clip(
                                BBRadius.Button
                            )
                            .clickable {
                                onSummaryClick()
                            }
                            .padding(
                                horizontal =
                                    BBSpacing.Space2,
                                vertical =
                                    BBSpacing.Space2
                            ),
                    verticalAlignment =
                        Alignment.CenterVertically,
                    horizontalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.Space2
                        )
                ) {
                    Column(
                        modifier =
                            Modifier.weight(
                                1f
                            )
                    ) {
                        Text(
                            text =
                                BBLocalization.Current.Get(
                                    key =
                                        "e736c25f-c944-4f52-a206-819f93d64a29",
                                    fallback =
                                        "Toplam"
                                ),
                            style =
                                MaterialTheme.typography
                                    .labelSmall,
                            color =
                                MaterialTheme.colorScheme
                                    .onSurfaceVariant
                        )

                        Text(
                            text =
                                totalPriceText.ifBlank {
                                    "—"
                                },
                            style =
                                MaterialTheme.typography
                                    .titleMedium,
                            fontWeight =
                                FontWeight.Bold,
                            color =
                                MaterialTheme.colorScheme
                                    .onSurface
                        )
                    }

                    Icon(
                        imageVector =
                            if (summaryExpanded) {
                                Icons.Outlined
                                    .KeyboardArrowDown
                            } else {
                                Icons.Outlined
                                    .KeyboardArrowUp
                            },
                        contentDescription =
                            null,
                        tint =
                            MaterialTheme.colorScheme
                                .primary,
                        modifier =
                            Modifier.size(
                                BBIcon.Action
                            )
                    )
                }

                BbButton(
                    text =
                        actionText,
                    onClick =
                        onContinueClick,
                    modifier =
                        Modifier.weight(
                            1.35f
                        ),
                    variant =
                        BbButtonVariant.Primary,
                    size =
                        BbButtonSize.Medium,
                    enabled =
                        canContinue
                )
            }
        }
    }
}


/*
 * ============================================================================
 * SHARED ORDER SUMMARY
 * ============================================================================
 */

@Composable
fun BbCommerceOrderSummaryOverlay(
    summary: CheckoutPriceSummary
) {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        color =
            MaterialTheme.colorScheme.surface,
        shape =
            MaterialTheme.shapes.extraLarge,
        shadowElevation =
            BBSpacing.Space4
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start =
                            BBSpacing.PageHorizontal,
                        top =
                            BBSpacing.Space4,
                        end =
                            BBSpacing.PageHorizontal,
                        bottom =
                            BBSpacing.Space4
                    ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                )
        ) {
            Text(
                text =
                    "Sepet Özeti",
                style =
                    MaterialTheme.typography
                        .titleLarge,
                fontWeight =
                    FontWeight.Bold
            )

            BbCommerceSummaryContent(
                summary =
                    summary
            )
        }
    }
}


@Composable
private fun BbCommerceSummaryContent(
    summary: CheckoutPriceSummary
) {
    Column(
        modifier =
            Modifier.fillMaxWidth(),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            )
    ) {
        BbCommerceSummaryRow(
            title =
                "Ürün Toplamı",
            value =
                summary.productTotalText
        )

        BbCommerceSummaryRow(
            title =
                "Kargo",
            value =
                summary.cargoTotalText
        )

        if (
            summary.discountTotalText
                .isNotBlank()
        ) {
            BbCommerceSummaryRow(
                title =
                    "İndirim",
                value =
                    summary.discountTotalText
            )
        }

        if (
            summary.couponTotalText
                .isNotBlank()
        ) {
            BbCommerceSummaryRow(
                title =
                    "Kupon",
                value =
                    summary.couponTotalText
            )
        }

        if (
            summary.installmentFeeText
                .isNotBlank()
        ) {
            BbCommerceSummaryRow(
                title =
                    "Taksit Farkı",
                value =
                    summary.installmentFeeText
            )
        }

        if (
            summary.commissionTotalText
                .isNotBlank()
        ) {
            BbCommerceSummaryRow(
                title =
                    "Komisyon",
                value =
                    summary.commissionTotalText
            )
        }

        HorizontalDivider(
            color =
                MaterialTheme.colorScheme
                    .outlineVariant
        )

        BbCommerceSummaryRow(
            title =
                "Ödenecek Tutar",
            value =
                summary.payableTotalText,
            strong =
                true
        )
    }
}


@Composable
private fun BbCommerceSummaryRow(
    title: String,
    value: String,
    strong: Boolean = false
) {
    Row(
        modifier =
            Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            ),
        verticalAlignment =
            Alignment.CenterVertically
    ) {
        Text(
            text =
                title,
            modifier =
                Modifier.weight(
                    1f
                ),
            style =
                if (strong) {
                    MaterialTheme.typography
                        .titleSmall
                } else {
                    MaterialTheme.typography
                        .bodyMedium
                },
            fontWeight =
                if (strong) {
                    FontWeight.Bold
                } else {
                    FontWeight.Medium
                },
            color =
                MaterialTheme.colorScheme
                    .onSurface
        )

        Text(
            text =
                value,
            style =
                if (strong) {
                    MaterialTheme.typography
                        .titleMedium
                } else {
                    MaterialTheme.typography
                        .bodyMedium
                },
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme
                    .onSurface
        )
    }
}
