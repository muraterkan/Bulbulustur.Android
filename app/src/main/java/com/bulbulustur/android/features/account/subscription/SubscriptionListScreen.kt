package com.bulbulustur.android.features.account.subscription

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun SubscriptionListScreen(
    onBackClick: () -> Unit = {},
    onSubscriptionDetailClick: (Int) -> Unit = {},
    onCancelSubscriptionClick: (Int) -> Unit = {}
) {
    val subscriptions = getDemoSubscriptions()

    AccountPageScaffold(
        title = "Aboneliklerim",
        kicker = "Üyelik ve Paketler",
        description = "Hesabınıza bağlı aktif veya geçmiş abonelikleri buradan takip edebilirsiniz.",
        backButtonText = "Hesabıma Dön",
        onBackClick = onBackClick
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
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
                    onSubscriptionDetailClick = onSubscriptionDetailClick,
                    onCancelSubscriptionClick = onCancelSubscriptionClick
                )
            }
        }
    }
}

@Composable
private fun SubscriptionCard(
    subscription: SubscriptionUiModel,
    onSubscriptionDetailClick: (Int) -> Unit,
    onCancelSubscriptionClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
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
                    text = subscription.shortCode
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = subscription.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = subscription.priceText,
                        style = MaterialTheme.typography.titleMedium,
                        color = BbColors.Yellow.Yellow800
                    )

                    SubscriptionStatusBadge(
                        statusText = subscription.statusText,
                        active = subscription.active
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Başlangıç: ${subscription.startDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Bitiş: ${subscription.endDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "Detay",
                    onClick = {
                        onSubscriptionDetailClick(subscription.subscriptionId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                if (subscription.active) {
                    BbButton(
                        text = "İptal Et",
                        onClick = {
                            onCancelSubscriptionClick(subscription.subscriptionId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Danger,
                        size = BbButtonSize.Small
                    )
                }
            }
        }
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
            SubscriptionEmptyIconBox()

            Text(
                text = "Aktif aboneliğiniz yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Abonelik veya paket bilgileriniz oluştuğunda burada görüntülenir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SubscriptionStatusBadge(
    statusText: String,
    active: Boolean
) {
    val backgroundColor = if (active) {
        BbColors.Green.Green50
    } else {
        BbColors.Gray.Gray100
    }

    val textColor = if (active) {
        BbColors.Green.Green700
    } else {
        BbColors.Gray.Gray700
    }

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
    text: String
) {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space14)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.XlShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun SubscriptionEmptyIconBox() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space12)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.Space2),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "P",
            style = MaterialTheme.typography.headlineSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

private fun getDemoSubscriptions(): List<SubscriptionUiModel> {
    return listOf(
        SubscriptionUiModel(
            subscriptionId = 1,
            title = "Bulbulustur Standart Paket",
            shortCode = "S",
            priceText = "₺249,90 / ay",
            startDate = "01 Mayıs 2026",
            endDate = "01 Haziran 2026",
            statusText = "Aktif",
            active = true
        ),
        SubscriptionUiModel(
            subscriptionId = 2,
            title = "Bulbulustur Deneme Paketi",
            shortCode = "D",
            priceText = "₺0,00",
            startDate = "01 Nisan 2026",
            endDate = "15 Nisan 2026",
            statusText = "Sona Erdi",
            active = false
        )
    )
}

private data class SubscriptionUiModel(
    val subscriptionId: Int,
    val title: String,
    val shortCode: String,
    val priceText: String,
    val startDate: String,
    val endDate: String,
    val statusText: String,
    val active: Boolean
)