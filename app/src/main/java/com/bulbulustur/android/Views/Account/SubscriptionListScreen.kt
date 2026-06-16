package com.bulbulustur.android.Views.Account

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun SubscriptionListScreen(
    onBackClick: () -> Unit = {},
    onSubscriptionDetailClick: (Int) -> Unit = {}
) {
    val subscriptions = getDemoSubscriptions()

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Aboneliklerim",
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
                SubscriptionIntroCard()
            }

            if (subscriptions.isEmpty()) {
                item {
                    SubscriptionEmptyState()
                }
            }

            items(
                items = subscriptions,
                key = { subscription -> subscription.subscriptionId }
            ) { subscription ->
                SubscriptionCard(
                    subscription = subscription,
                    onSubscriptionDetailClick = onSubscriptionDetailClick
                )
            }
        }
    }
}

@Composable
private fun SubscriptionIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            SubscriptionIconBox(
                iconColor = BbColors.Yellow.Yellow800,
                backgroundColor = BbColors.Yellow.Yellow100
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Paket ve Ã¼yelik sÃ¼reÃ§leri",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "HesabÄ±nÄ±za baÄŸlÄ± aktif veya geÃ§miÅŸ abonelikleri, dÃ¶nem aralÄ±klarÄ±nÄ± ve plan Ã¼cretlerini buradan takip edebilirsiniz.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SubscriptionCard(
    subscription: SubscriptionUiModel,
    onSubscriptionDetailClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            onSubscriptionDetailClick(subscription.subscriptionId)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubscriptionIconBox(
                    iconColor = BbColors.Yellow.Yellow800,
                    backgroundColor = BbColors.Yellow.Yellow100
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "ABONELÄ°K PLANI",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = subscription.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                SubscriptionStatusBadge(
                    statusText = subscription.statusText,
                    active = subscription.active
                )
            }

            SubscriptionPriceBox(
                priceText = subscription.priceText
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                SubscriptionInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "BAÅLANGIÃ‡",
                    value = subscription.startDate,
                    iconColor = BbColors.Blue.Blue600
                )

                SubscriptionInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "BÄ°TÄ°Å",
                    value = subscription.endDate,
                    iconColor = BbColors.Orange.Orange600
                )
            }

            BbButton(
                text = "DetaylarÄ± GÃ¶r",
                onClick = {
                    onSubscriptionDetailClick(subscription.subscriptionId)
                },
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

@Composable
private fun SubscriptionPriceBox(
    priceText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.Yellow.Yellow50,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.CreditCard,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.Action)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "PLAN ÃœCRETÄ°",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = priceText,
                style = MaterialTheme.typography.titleMedium,
                color = BbColors.Yellow.Yellow800
            )
        }
    }
}

@Composable
private fun SubscriptionInfoBox(
    modifier: Modifier,
    title: String,
    value: String,
    iconColor: Color
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
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
private fun SubscriptionStatusBadge(
    statusText: String,
    active: Boolean
) {
    val backgroundColor = if (active) BbColors.Green.Green50 else BbColors.Gray.Gray100
    val textColor = if (active) BbColors.Green.Green700 else BbColors.Gray.Gray700

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = statusText,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

@Composable
private fun SubscriptionIconBox(
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
            imageVector = Icons.Outlined.Subscriptions,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BbIcon.Action)
        )
    }
}

@Composable
private fun SubscriptionEmptyState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            SubscriptionIconBox(
                iconColor = BbColors.Yellow.Yellow800,
                backgroundColor = BbColors.Yellow.Yellow100
            )

            Text(
                text = "Aktif aboneliÄŸiniz yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Abonelik veya paket bilgileriniz oluÅŸtuÄŸunda burada gÃ¶rÃ¼ntÃ¼lenir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getDemoSubscriptions(): List<SubscriptionUiModel> {
    return listOf(
        SubscriptionUiModel(
            subscriptionId = 1,
            title = "B2B e-marketplace / Free",
            priceText = "0 â‚º",
            startDate = "25.09.2025",
            endDate = "25.09.2026",
            statusText = "Aktif Plan",
            active = true
        )
    )
}

private data class SubscriptionUiModel(
    val subscriptionId: Int,
    val title: String,
    val priceText: String,
    val startDate: String,
    val endDate: String,
    val statusText: String,
    val active: Boolean
)
