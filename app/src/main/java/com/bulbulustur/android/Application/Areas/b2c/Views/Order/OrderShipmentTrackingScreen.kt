package com.bulbulustur.android.Application.Areas.b2c.Views.order

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BBColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Kargom Nerede?",
                subtitle = "Sipariş kargo durumunu takip edin.",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BBColors.SurfaceMuted)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGap
            )
        ) {
            item {
                OrderShipmentSummaryCard(
                    tracking = tracking
                )
            }

            item {
                OrderShipmentInfoGrid(
                    tracking = tracking
                )
            }

            item {
                OrderShipmentTimelineCard(
                    tracking = tracking
                )
            }

            item {
                OrderShipmentHelpCard()
            }

            item {
                BbButton(
                    text = "Sipariş Detaylarına Dön",
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
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.Top
        ) {
            OrderShipmentIconBox(
                icon = Icons.Outlined.LocalShipping,
                backgroundColor = BBColors.Yellow.Yellow100,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
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
                    color = BBColors.TextMuted
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
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            OrderShipmentSectionTitle(
                title = "Kargo Akışı",
                subtitle = "Gönderinizin son durum adımları"
            )

            tracking.steps.forEachIndexed { index, step ->
                OrderShipmentStepRow(
                    step = step
                )

                if (index != tracking.steps.lastIndex) {
                    HorizontalDivider(
                        color = BBColors.Border
                    )
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
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        ),
        verticalAlignment = Alignment.Top
    ) {
        OrderShipmentIconBox(
            icon = if (step.completed) {
                Icons.Outlined.CheckCircle
            } else {
                Icons.Outlined.LocationOn
            },
            backgroundColor = if (step.completed) {
                BBColors.Green.Green50
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            iconColor = if (step.completed) {
                BBColors.Green.Green600
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
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
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.Top
        ) {
            OrderShipmentIconBox(
                icon = Icons.Outlined.Info,
                backgroundColor = BBColors.Blue.Blue50,
                iconColor = BBColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
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
                shape = BBRadius.LgShape
            )
            .padding(
                BBSpacing.CardPaddingCompact
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BBColors.Blue.Blue600,
                modifier = Modifier.size(
                    BBIcon.Action
                )
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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
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
    backgroundColor: Color,
    iconColor: Color
) {
    Box(
        modifier = Modifier
            .size(
                BBIcon.BoxMd
            )
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
            modifier = Modifier.size(
                BBIcon.Action
            )
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