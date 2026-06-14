package com.bulbulustur.android.Features.account.alarm

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing

@Composable
fun AlarmListScreen(
    onBackClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onDeleteAlarmClick: (Int) -> Unit = {}
) {
    val alarms = getDemoAlarms()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Alarmlarım",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            if (alarms.isEmpty()) {
                item {
                    AlarmEmptyState()
                }
            }

            items(
                items = alarms,
                key = { alarm -> alarm.alarmId }
            ) { alarm ->
                AlarmCard(
                    alarm = alarm,
                    onProductClick = onProductClick,
                    onDeleteAlarmClick = onDeleteAlarmClick
                )
            }
        }
    }
}

@Composable
private fun AlarmCard(
    alarm: AccountAlarmUiModel,
    onProductClick: (Int) -> Unit,
    onDeleteAlarmClick: (Int) -> Unit
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
                AlarmProductImagePlaceholder()

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Takipteki Ürün",
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.Yellow.Yellow700
                    )

                    Text(
                        text = alarm.productName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "${alarm.insertedDate} tarihinde eklendi",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "Ürünü Gör",
                    onClick = {
                        onProductClick(alarm.productId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = "Sil",
                    onClick = {
                        onDeleteAlarmClick(alarm.alarmId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Danger,
                    size = BbButtonSize.Small
                )
            }
        }
    }
}

@Composable
private fun AlarmEmptyState() {
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
            AlarmIconBox()

            Text(
                text = "Henüz ürün alarmınız yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Ürün detay sayfalarından alarm oluşturarak takip etmek istediğiniz ürünleri buradan yönetebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun AlarmProductImagePlaceholder() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space16)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Ürün",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AlarmIconBox() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space12)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "⏰",
            style = MaterialTheme.typography.headlineSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

private fun getDemoAlarms(): List<AccountAlarmUiModel> {
    return listOf(
        AccountAlarmUiModel(
            alarmId = 1,
            productId = 101,
            productName = "Ortobella Comfort Hakiki Deri Topuk Dikeni Terlik M13",
            insertedDate = "22 Mayıs 2026"
        )
    )
}

private data class AccountAlarmUiModel(
    val alarmId: Int,
    val productId: Int,
    val productName: String,
    val insertedDate: String
)