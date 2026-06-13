package com.bulbulustur.android.features.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun OrderShipmentTrackingScreen(
    onBackClick: () -> Unit = {}
) {
    val tracking = OrderShipmentTrackingUiModel(
        orderNumber = "Sipariş #1000001",
        cargoCompany = "Yurtiçi Kargo",
        trackingNumber = "YK-2026-00012345",
        currentStatus = "Dağıtım şubesinde",
        estimatedDelivery = "Bugün teslim edilebilir",
        steps = listOf(
            OrderShipmentStepUiModel(
                title = "Sipariş hazırlandı",
                description = "Satıcı ürünü kargoya teslim etmek üzere hazırladı.",
                completed = true
            ),
            OrderShipmentStepUiModel(
                title = "Kargoya verildi",
                description = "Gönderi taşıyıcı firmaya teslim edildi.",
                completed = true
            ),
            OrderShipmentStepUiModel(
                title = "Transfer merkezinde",
                description = "Gönderi teslimat bölgesine yönlendirildi.",
                completed = true
            ),
            OrderShipmentStepUiModel(
                title = "Dağıtım şubesinde",
                description = "Gönderi teslimat şubesine ulaştı.",
                completed = true
            ),
            OrderShipmentStepUiModel(
                title = "Teslimat bekleniyor",
                description = "Kurye dağıtım süreci başladığında durum güncellenir.",
                completed = false
            )
        )
    )

    OrderShipmentPageScaffold(
        title = "Kargom Nerede?",
        subtitle = "Sipariş kargo durumunu takip edin.",
        onBackClick = onBackClick
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                OrderShipmentSummaryCard(tracking = tracking)
            }

            item {
                OrderShipmentInfoGrid(tracking = tracking)
            }

            item {
                OrderShipmentTimelineCard(tracking = tracking)
            }

            item {
                OrderShipmentHelpCard()
            }

            item {
                BbButton(
                    text = "Sipariş Detayına Dön",
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun OrderShipmentPageScaffold(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.SurfaceMuted)
            .navigationBarsPadding()
    ) {
        OrderShipmentTopHeader(
            title = title,
            subtitle = subtitle,
            onBackClick = onBackClick
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            content()
        }
    }
}

@Composable
private fun OrderShipmentTopHeader(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Surface)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BbSpacing.PageHorizontal,
                    vertical = BbSpacing.Space3
                ),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(BbIcon.BoxMd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Geri dön",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BbIcon.TopBarIcon)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        HorizontalDivider(
            color = BbColors.Border
        )
    }
}

@Composable
private fun OrderShipmentSummaryCard(
    tracking: OrderShipmentTrackingUiModel
) {
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
            OrderShipmentIconBox(
                icon = Icons.Outlined.LocalShipping,
                backgroundColor = BbColors.Yellow.Yellow100,
                iconColor = BbColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = tracking.currentStatus,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = tracking.estimatedDelivery,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = tracking.orderNumber,
                    style = MaterialTheme.typography.labelMedium,
                    color = BbColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun OrderShipmentInfoGrid(
    tracking: OrderShipmentTrackingUiModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        OrderShipmentInfoBox(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.Storefront,
            title = "Kargo Firması",
            value = tracking.cargoCompany
        )

        OrderShipmentInfoBox(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.ReceiptLong,
            title = "Takip No",
            value = tracking.trackingNumber
        )
    }
}

@Composable
private fun OrderShipmentTimelineCard(
    tracking: OrderShipmentTrackingUiModel
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
            OrderShipmentSectionTitle(
                title = "Kargo Akışı",
                subtitle = "Gönderinizin son durum adımları"
            )

            tracking.steps.forEachIndexed { index, step ->
                OrderShipmentStepRow(step = step)

                if (index != tracking.steps.lastIndex) {
                    HorizontalDivider(color = BbColors.Border)
                }
            }
        }
    }
}

@Composable
private fun OrderShipmentStepRow(
    step: OrderShipmentStepUiModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        OrderShipmentIconBox(
            icon = if (step.completed) Icons.Outlined.CheckCircle else Icons.Outlined.LocationOn,
            backgroundColor = if (step.completed) BbColors.Green.Green50 else MaterialTheme.colorScheme.surfaceVariant,
            iconColor = if (step.completed) BbColors.Green.Green600 else MaterialTheme.colorScheme.onSurfaceVariant
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = step.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = step.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderShipmentHelpCard() {
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
            OrderShipmentIconBox(
                icon = Icons.Outlined.Info,
                backgroundColor = BbColors.Blue.Blue50,
                iconColor = BbColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Kargo bilgisi hakkında",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Kargo hareketleri taşıyıcı firmadan gelen bilgiye göre güncellenir. Gecikme veya teslimat sorunu varsa sipariş detayından talep oluşturabilirsiniz.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun OrderShipmentInfoBox(
    modifier: Modifier,
    icon: ImageVector,
    title: String,
    value: String
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Blue.Blue600,
                modifier = Modifier.size(BbIcon.Action)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
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
private fun OrderShipmentSectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OrderShipmentIconBox(
    icon: ImageVector,
    backgroundColor: androidx.compose.ui.graphics.Color,
    iconColor: androidx.compose.ui.graphics.Color
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

private data class OrderShipmentTrackingUiModel(
    val orderNumber: String,
    val cargoCompany: String,
    val trackingNumber: String,
    val currentStatus: String,
    val estimatedDelivery: String,
    val steps: List<OrderShipmentStepUiModel>
)

private data class OrderShipmentStepUiModel(
    val title: String,
    val description: String,
    val completed: Boolean
)