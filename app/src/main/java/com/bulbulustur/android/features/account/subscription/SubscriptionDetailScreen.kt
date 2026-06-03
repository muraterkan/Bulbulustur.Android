package com.bulbulustur.android.features.account.subscription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.theme.BbSpacing

data class SubscriptionDetailUiState(
    val subscriptionId: Int,
    val subscriptionName: String,
    val subscriptionType: String,
    val planType: String,
    val startDateText: String,
    val endDateText: String,
    val planPriceText: String,
    val isActive: Boolean
)

@Composable
fun SubscriptionDetailScreen(
    subscriptionDetail: SubscriptionDetailUiState = createSampleSubscriptionDetail(),
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.PageTop
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGap)
    ) {
        SubscriptionDetailHeroCard(
            subscriptionDetail = subscriptionDetail,
            onBackClick = onBackClick
        )

        SubscriptionMetricCards(
            subscriptionDetail = subscriptionDetail
        )

        SubscriptionPlanInfoCard(
            subscriptionDetail = subscriptionDetail
        )
    }
}

@Composable
private fun SubscriptionDetailHeroCard(
    subscriptionDetail: SubscriptionDetailUiState,
    onBackClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            BbChip(
                text = if (subscriptionDetail.isActive) {
                    "Aktif Abonelik"
                } else {
                    "Pasif Abonelik"
                }
            )

            Text(
                text = subscriptionDetail.subscriptionName,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Abonelik dönemin ve plan ücretin aşağıdaki bilgilerle kayıtlıdır.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Aboneliklerime Dön",
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Outline
            )
        }
    }
}

@Composable
private fun SubscriptionMetricCards(
    subscriptionDetail: SubscriptionDetailUiState
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        SubscriptionMetricCard(
            title = "Başlangıç Tarihi",
            value = subscriptionDetail.startDateText
        )

        SubscriptionMetricCard(
            title = "Bitiş Tarihi",
            value = subscriptionDetail.endDateText
        )

        SubscriptionMetricCard(
            title = "Plan Ücreti",
            value = subscriptionDetail.planPriceText
        )
    }
}

@Composable
private fun SubscriptionMetricCard(
    title: String,
    value: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun SubscriptionPlanInfoCard(
    subscriptionDetail: SubscriptionDetailUiState
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            Text(
                text = "Plan Bilgileri",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Aboneliğinin temel plan, dönem ve ücret bilgileri.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SubscriptionInfoRow(
                label = "Abonelik Tipi",
                value = subscriptionDetail.subscriptionType
            )

            SubscriptionInfoRow(
                label = "Plan Tipi",
                value = subscriptionDetail.planType
            )

            SubscriptionInfoRow(
                label = "Başlangıç Tarihi",
                value = subscriptionDetail.startDateText
            )

            SubscriptionInfoRow(
                label = "Bitiş Tarihi",
                value = subscriptionDetail.endDateText
            )

            SubscriptionInfoRow(
                label = "Plan Fiyatı",
                value = subscriptionDetail.planPriceText
            )
        }
    }
}

@Composable
private fun SubscriptionInfoRow(
    label: String,
    value: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

private fun createSampleSubscriptionDetail(): SubscriptionDetailUiState {
    return SubscriptionDetailUiState(
        subscriptionId = 3,
        subscriptionName = "B2B e-marketplace / Free",
        subscriptionType = "B2B e-marketplace",
        planType = "Free",
        startDateText = "25.09.2025",
        endDateText = "25.09.2026",
        planPriceText = "0,00 TL",
        isActive = true
    )
}