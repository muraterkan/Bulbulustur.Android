package com.bulbulustur.android.features.order

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
fun OrderListScreen(
    onBackClick: () -> Unit = {},
    onOrderDetailClick: (Int) -> Unit = {}
) {
    val orders = getDemoOrders()

    AccountPageScaffold(
        title = "Siparişlerim",
        kicker = "Sipariş Yönetimi",
        description = "Siparişlerinizi listeleyin, detaylarına ulaşın ve işlem durumlarını kontrol edin.",
        backButtonText = "Hesabıma Dön",
        onBackClick = onBackClick
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            if (orders.isEmpty()) {
                item {
                    OrderEmptyState()
                }
            }

            items(
                items = orders,
                key = { order -> order.orderId }
            ) { order ->
                OrderCard(
                    order = order,
                    onOrderDetailClick = onOrderDetailClick
                )
            }
        }
    }
}

@Composable
private fun OrderCard(
    order: OrderListUiModel,
    onOrderDetailClick: (Int) -> Unit
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
                OrderIconBox()

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "SİPARİŞ TARİHİ",
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.Yellow.Yellow800
                    )

                    Text(
                        text = order.orderNumber,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "${order.orderDate} tarihinde eklendi",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                OrderInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "SİPARİŞ DURUMU",
                    value = order.statusText,
                    icon = Icons.Outlined.LocalShipping,
                    iconColor = order.statusColor
                )

                OrderInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "TUTAR",
                    value = order.totalText,
                    icon = Icons.Outlined.Payments,
                    iconColor = BbColors.Yellow.Yellow800
                )
            }

            OrderInfoBox(
                modifier = Modifier.fillMaxWidth(),
                title = "ÜRÜN",
                value = order.productCountText,
                icon = Icons.Outlined.ReceiptLong,
                iconColor = BbColors.Blue.Blue600
            )

            BbButton(
                text = "Sipariş Detayı",
                onClick = {
                    onOrderDetailClick(order.orderId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Visibility,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderInfoBox(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color
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
                tint = iconColor,
                modifier = Modifier.size(BbSpacing.Space5)
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
private fun OrderIconBox() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space12)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.CalendarMonth,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbSpacing.Space6)
        )
    }
}

@Composable
private fun OrderEmptyState() {
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
            OrderIconBox()

            Text(
                text = "Sipariş bulunamadı",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Geçmiş veya devam eden siparişleriniz oluştuğunda burada listelenir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getDemoOrders(): List<OrderListUiModel> {
    return listOf(
        OrderListUiModel(
            orderId = 1,
            orderNumber = "Sipariş #1000000",
            orderDate = "9 Mayıs 2026",
            statusText = "Hazırlanıyor",
            productCountText = "2 ürün",
            totalText = "1.250,50 ₺",
            statusColor = BbColors.Orange.Orange600
        ),
        OrderListUiModel(
            orderId = 2,
            orderNumber = "Sipariş #1000001",
            orderDate = "9 Mayıs 2026",
            statusText = "Teslim Edildi",
            productCountText = "2 ürün",
            totalText = "3.450,00 ₺",
            statusColor = BbColors.Green.Green600
        ),
        OrderListUiModel(
            orderId = 3,
            orderNumber = "Sipariş #1000002",
            orderDate = "9 Mayıs 2026",
            statusText = "Hazırlanıyor",
            productCountText = "1 ürün",
            totalText = "850,25 ₺",
            statusColor = BbColors.Orange.Orange600
        ),
        OrderListUiModel(
            orderId = 4,
            orderNumber = "Sipariş #1000003",
            orderDate = "9 Mayıs 2026",
            statusText = "Ödeme Alındı",
            productCountText = "5 ürün",
            totalText = "15.600,00 ₺",
            statusColor = BbColors.Blue.Blue600
        ),
        OrderListUiModel(
            orderId = 5,
            orderNumber = "Sipariş #1000004",
            orderDate = "9 Mayıs 2026",
            statusText = "Teslim Edildi",
            productCountText = "1 ürün",
            totalText = "210,00 ₺",
            statusColor = BbColors.Green.Green600
        )
    )
}

private data class OrderListUiModel(
    val orderId: Int,
    val orderNumber: String,
    val orderDate: String,
    val statusText: String,
    val productCountText: String,
    val totalText: String,
    val statusColor: Color
)