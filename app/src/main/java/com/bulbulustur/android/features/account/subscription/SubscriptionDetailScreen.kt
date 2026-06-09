package com.bulbulustur.android.features.account.subscription

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun SubscriptionDetailScreen(
    subscriptionId: Int = 1,
    onBackClick: () -> Unit = {},
    onCancelSubscriptionClick: (Int) -> Unit = {}
) {
    val subscription = getDemoSubscriptionDetail(subscriptionId)

    AccountPageScaffold(
        title = "Abonelik Detayı",
        kicker = "Paket Bilgisi",
        description = "Seçili abonelik paketine ait dönem, ödeme ve durum bilgilerini buradan inceleyebilirsiniz.",
        backButtonText = "Aboneliklerime Dön",
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
                    Column(
                        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                    ) {
                        Text(
                            text = subscription.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = subscription.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    SubscriptionDetailStatusBox(
                        statusText = subscription.statusText,
                        active = subscription.active
                    )
                }
            }

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                ) {
                    SubscriptionDetailRow(
                        label = "Abonelik No",
                        value = subscription.subscriptionNumber
                    )

                    SubscriptionDetailRow(
                        label = "Başlangıç Tarihi",
                        value = subscription.startDate
                    )

                    SubscriptionDetailRow(
                        label = "Bitiş Tarihi",
                        value = subscription.endDate
                    )

                    SubscriptionDetailRow(
                        label = "Paket Ücreti",
                        value = subscription.priceText
                    )

                    SubscriptionDetailRow(
                        label = "Yenileme",
                        value = subscription.renewalText
                    )
                }
            }

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                ) {
                    Text(
                        text = "Paket Kapsamı",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    subscription.features.forEach { feature ->
                        SubscriptionFeatureText(
                            text = feature
                        )
                    }
                }
            }

            if (subscription.active) {
                BbButton(
                    text = "Aboneliği İptal Et",
                    onClick = {
                        onCancelSubscriptionClick(subscription.subscriptionId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Danger,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun SubscriptionDetailRow(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun SubscriptionDetailStatusBox(
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
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Text(
            text = statusText,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}

@Composable
private fun SubscriptionFeatureText(
    text: String
) {
    Text(
        text = "• $text",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

private fun getDemoSubscriptionDetail(
    subscriptionId: Int
): SubscriptionDetailUiModel {
    return SubscriptionDetailUiModel(
        subscriptionId = subscriptionId,
        subscriptionNumber = "SUB-2026-000$subscriptionId",
        title = "Bulbulustur Standart Paket",
        description = "Bulbulustur üzerinde daha görünür olmak ve temel avantajlardan yararlanmak için kullanılan örnek abonelik paketi.",
        priceText = "₺249,90 / ay",
        startDate = "01 Mayıs 2026",
        endDate = "01 Haziran 2026",
        renewalText = "Aylık yenileme",
        statusText = "Aktif abonelik",
        active = true,
        features = listOf(
            "Hesap görünürlüğü",
            "Temel destek önceliği",
            "Standart ilan ve vitrin kullanım hakları",
            "Mobil uygulama üzerinden abonelik takibi"
        )
    )
}

private data class SubscriptionDetailUiModel(
    val subscriptionId: Int,
    val subscriptionNumber: String,
    val title: String,
    val description: String,
    val priceText: String,
    val startDate: String,
    val endDate: String,
    val renewalText: String,
    val statusText: String,
    val active: Boolean,
    val features: List<String>
)