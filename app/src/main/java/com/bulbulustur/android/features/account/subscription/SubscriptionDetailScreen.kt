package com.bulbulustur.android.features.account.subscription

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbInnerPageHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbAlpha

@Composable
fun SubscriptionDetailScreen(
    subscriptionId: Int = 1,
    onBackClick: () -> Unit = {}
) {
    val subscription = getDemoSubscriptionDetail(subscriptionId)

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Abonelik Detayı",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                SubscriptionHeroCard(subscription = subscription)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                ) {
                    SubscriptionDetailInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "BAŞLANGIÇ",
                        value = subscription.startDate,
                        iconColor = BbColors.Blue.Blue600
                    )

                    SubscriptionDetailInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "BİTİŞ",
                        value = subscription.endDate,
                        iconColor = BbColors.Orange.Orange600
                    )
                }
            }

            item {
                SubscriptionDetailPriceCard(subscription = subscription)
            }

            item {
                SubscriptionPlanInfoCard(subscription = subscription)
            }

            item {
                BbButton(
                    text = "Aboneliklerime Dön",
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(BbIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun SubscriptionHeroCard(
    subscription: SubscriptionDetailUiModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        BbColors.Coal.Coal400,
                        BbColors.Yellow.Yellow900
                    )
                ),
                shape = BbRadius.XlShape
            )
            .padding(BbSpacing.CardPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxMd)
                    .background(
                        color = BbColors.White.copy(alpha = BbAlpha.Overlay),
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Verified,
                    contentDescription = null,
                    tint = BbColors.Primary,
                    modifier = Modifier.size(BbIcon.Action)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = subscription.statusText.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.Primary
                )

                Text(
                    text = subscription.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BbColors.White
                )

                Text(
                    text = "Abonelik dönemi ve plan ücretiniz aşağıdaki bilgilerle kayıtlıdır.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.White.copy(alpha = 0.78f)
                )
            }
        }
    }
}

@Composable
private fun SubscriptionDetailInfoBox(
    modifier: Modifier,
    title: String,
    value: String,
    iconColor: Color
) {
    Row(
        modifier = modifier
            .background(
                color = BbColors.Surface,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.CalendarMonth,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BbIcon.Inline)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun SubscriptionDetailPriceCard(
    subscription: SubscriptionDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubscriptionDetailIconBox(
                icon = Icons.Outlined.CreditCard,
                iconColor = BbColors.Yellow.Yellow800,
                backgroundColor = BbColors.Yellow.Yellow100
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Plan Ücreti",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = subscription.priceText,
                    style = MaterialTheme.typography.titleMedium,
                    color = BbColors.Yellow.Yellow800
                )
            }
        }
    }
}

@Composable
private fun SubscriptionPlanInfoCard(
    subscription: SubscriptionDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Plan Bilgileri",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Aboneliğinize ait temel plan, dönem ve ücret bilgileri.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            HorizontalDivider(color = BbColors.Border)

            SubscriptionPlanRow("Abonelik Tipi", subscription.subscriptionType)
            SubscriptionPlanRow("Plan Tipi", subscription.planType)
            SubscriptionPlanRow("Başlangıç Tarihi", subscription.startDate)
            SubscriptionPlanRow("Bitiş Tarihi", subscription.endDate)
            SubscriptionPlanRow("Plan Fiyatı", subscription.priceText, valueColor = BbColors.Yellow.Yellow800)
        }
    }
}

@Composable
private fun SubscriptionPlanRow(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = BbSpacing.CardPadding,
                vertical = BbSpacing.Space3
            ),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelSmall,
            color = valueColor
        )
    }

    HorizontalDivider(color = BbColors.Border)
}

@Composable
private fun SubscriptionDetailIconBox(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BbIcon.Action)
        )
    }
}

private fun getDemoSubscriptionDetail(
    subscriptionId: Int
): SubscriptionDetailUiModel {
    return SubscriptionDetailUiModel(
        subscriptionId = subscriptionId,
        title = "B2B e-marketplace / Free",
        subscriptionType = "B2B e-marketplace",
        planType = "Free",
        priceText = "0,00 TL",
        startDate = "25.09.2025",
        endDate = "25.09.2026",
        statusText = "Aktif Abonelik"
    )
}

private data class SubscriptionDetailUiModel(
    val subscriptionId: Int,
    val title: String,
    val subscriptionType: String,
    val planType: String,
    val priceText: String,
    val startDate: String,
    val endDate: String,
    val statusText: String
)