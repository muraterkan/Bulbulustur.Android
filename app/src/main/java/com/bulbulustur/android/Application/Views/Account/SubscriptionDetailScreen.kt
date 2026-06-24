package com.bulbulustur.android.Application.Views.Account

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
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha

@Composable
fun SubscriptionDetailScreen(
    subscriptionId: Int = 1,
    onBackClick: () -> Unit = {}
) {
    val subscription = getDemoSubscriptionDetail(subscriptionId)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                SubscriptionHeroCard(subscription = subscription)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    SubscriptionDetailInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "BAŞLANGIÇ",
                        value = subscription.startDate,
                        iconColor = BBColors.Blue.Blue600
                    )

                    SubscriptionDetailInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "BİTİŞ",
                        value = subscription.endDate,
                        iconColor = BBColors.Orange.Orange600
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
                            modifier = Modifier.size(BBIcon.ButtonIcon)
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
                        BBColors.Coal.Coal400,
                        BBColors.Yellow.Yellow900
                    )
                ),
                shape = BBRadius.XlShape
            )
            .padding(BBSpacing.CardPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .background(
                        color = BBColors.White.copy(alpha = BBAlpha.Overlay),
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Verified,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.Action)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = subscription.statusText.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = subscription.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BBColors.White
                )

                Text(
                    text = "Abonelik dönemi ve plan ücretiniz aşaĞıdaki bilgilerle kayıtlıdır.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.White.copy(alpha = 0.78f)
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
                color = MaterialTheme.colorScheme.surface,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.CalendarMonth,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Inline)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubscriptionDetailIconBox(
                icon = Icons.Outlined.CreditCard,
                iconColor = BBColors.Yellow.Yellow800,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Plan Ücreti",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = subscription.priceText,
                    style = MaterialTheme.typography.titleMedium,
                    color = BBColors.Yellow.Yellow800
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
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Plan Bilgileri",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "AboneliĞinize ait temel plan, dönem ve ücret bilgileri.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            SubscriptionPlanRow("Abonelik Tipi", subscription.subscriptionType)
            SubscriptionPlanRow("Plan Tipi", subscription.planType)
            SubscriptionPlanRow("Başlangıç Tarihi", subscription.startDate)
            SubscriptionPlanRow("Bitiş Tarihi", subscription.endDate)
            SubscriptionPlanRow("Plan Fiyatı", subscription.priceText, valueColor = BBColors.Yellow.Yellow800)
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
                horizontal = BBSpacing.CardPadding,
                vertical = BBSpacing.Space3
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
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

    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
}

@Composable
private fun SubscriptionDetailIconBox(
    icon: ImageVector,
    iconColor: Color,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Action)
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


