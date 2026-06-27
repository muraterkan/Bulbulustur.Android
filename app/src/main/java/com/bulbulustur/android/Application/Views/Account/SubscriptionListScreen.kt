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

@Composable
fun SubscriptionListScreen(
    onBackClick: () -> Unit = {},
    onSubscriptionDetailClick: (Int) -> Unit = {}
) {
    val subscriptions = getDemoSubscriptions()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            SubscriptionIconBox(
                iconColor = BBColors.Yellow.Yellow800,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Paket ve Üyelik Süreçleri",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Hesabınıza baĞlı aktif veya geçmiş abonelikleri, dönem aralıklarını ve plan ücretlerini buradan takip edebilirsiniz.",
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubscriptionIconBox(
                    iconColor = BBColors.Yellow.Yellow800,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "ABONELİK PLANI",
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
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                SubscriptionInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "BAŞLANGIÇ",
                    value = subscription.startDate,
                    iconColor = BBColors.Blue.Blue600
                )

                SubscriptionInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "BİTİŞ",
                    value = subscription.endDate,
                    iconColor = BBColors.Orange.Orange600
                )
            }

            BbButton(
                text = "Detayları Gör",
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
                        modifier = Modifier.size(BBIcon.ButtonIcon)
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
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.CreditCard,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Action)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = "PLAN ÜCRETİ",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = priceText,
                style = MaterialTheme.typography.titleMedium,
                color = BBColors.Yellow.Yellow800
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
private fun SubscriptionStatusBadge(
    statusText: String,
    active: Boolean
) {
    val backgroundColor = if (active) BBColors.Green.Green50 else BBColors.Gray.Gray100
    val textColor = if (active) BBColors.Green.Green700 else BBColors.Gray.Gray700

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
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
            .size(BBIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Subscriptions,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Action)
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            SubscriptionIconBox(
                iconColor = BBColors.Yellow.Yellow800,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )

            Text(
                text = "Aktif aboneliĞiniz yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Abonelik veya paket bilgileriniz oluştuĞunda burada görüntülenir.",
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
            priceText = "0 ₺",
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


