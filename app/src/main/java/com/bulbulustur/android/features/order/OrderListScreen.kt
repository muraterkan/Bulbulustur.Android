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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.bulbulustur.android.ui.theme.BbAlpha

@Composable
fun OrderListScreen(
    onBackClick: () -> Unit = {},
    onOrderDetailClick: (Int) -> Unit = {}
) {
    val orders = getDemoOrders()

    OrderListPageScaffold(
        title = "Siparişlerim",
        subtitle = "Sipariş ve teslimat durumlarınızı takip edin.",
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
            if (orders.isEmpty()) {
                item {
                    OrderEmptyState()
                }
            } else {
                item {
                    OrderListOverviewCard(
                        totalOrderCount = orders.size,
                        activeOrderCount = orders.count { it.isActive },
                        deliveredOrderCount = orders.count { it.statusText == "Teslim Edildi" }
                    )
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
}

@Composable
private fun OrderListPageScaffold(
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
        OrderListTopHeader(
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
private fun OrderListTopHeader(
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
private fun OrderListOverviewCard(
    totalOrderCount: Int,
    activeOrderCount: Int,
    deliveredOrderCount: Int
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
                OrderIconBox(
                    icon = Icons.Outlined.ReceiptLong,
                    backgroundColor = BbColors.Yellow.Yellow100,
                    iconColor = BbColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Sipariş Özeti",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Devam eden ve tamamlanan siparişlerinizi buradan yönetin.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                OrderStatBox(
                    modifier = Modifier.weight(1f),
                    title = "Toplam",
                    value = totalOrderCount.toString()
                )

                OrderStatBox(
                    modifier = Modifier.weight(1f),
                    title = "Aktif",
                    value = activeOrderCount.toString()
                )

                OrderStatBox(
                    modifier = Modifier.weight(1f),
                    title = "Teslim",
                    value = deliveredOrderCount.toString()
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
        padding = BbCardPadding.Medium,
        onClick = {
            onOrderDetailClick(order.orderId)
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
                OrderIconBox(
                    icon = Icons.Outlined.CalendarMonth,
                    backgroundColor = BbColors.Yellow.Yellow100,
                    iconColor = BbColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = order.orderNumber,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "${order.orderDate} tarihinde oluşturuldu",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                OrderStatusBadge(
                    text = order.statusText,
                    color = order.statusColor
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                OrderInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "DURUM",
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

            OrderProductSummaryBox(order = order)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BbIcon.Inline)
                    )

                    Text(
                        text = order.storeName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BbButton(
                    text = "Detay",
                    onClick = {
                        onOrderDetailClick(order.orderId)
                    },
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Visibility,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(BbIcon.ButtonIcon)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(BbIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun OrderProductSummaryBox(
    order: OrderListUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ReceiptLong,
            contentDescription = null,
            tint = BbColors.Blue.Blue600,
            modifier = Modifier.size(BbIcon.Action)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = order.productTitle,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = order.productCountText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
private fun OrderStatBox(
    modifier: Modifier = Modifier,
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderStatusBadge(
    text: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .background(
                color = color.copy(alpha = BbAlpha.Overlay),
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@Composable
private fun OrderIconBox(
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color
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
            OrderIconBox(
                icon = Icons.Outlined.ReceiptLong,
                backgroundColor = BbColors.Yellow.Yellow100,
                iconColor = BbColors.Yellow.Yellow800
            )

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
            productTitle = "Kadın Sneaker Günlük Ayakkabı",
            productCountText = "2 ürün",
            totalText = "1.250,50 ₺",
            storeName = "Ortobella",
            statusColor = BbColors.Orange.Orange600,
            isActive = true
        ),
        OrderListUiModel(
            orderId = 2,
            orderNumber = "Sipariş #1000001",
            orderDate = "9 Mayıs 2026",
            statusText = "Teslim Edildi",
            productTitle = "Pamuklu Basic Tişört",
            productCountText = "2 ürün",
            totalText = "3.450,00 ₺",
            storeName = "ModaLine",
            statusColor = BbColors.Green.Green600,
            isActive = false
        ),
        OrderListUiModel(
            orderId = 3,
            orderNumber = "Sipariş #1000002",
            orderDate = "9 Mayıs 2026",
            statusText = "Hazırlanıyor",
            productTitle = "Minimal Sırt Çantası",
            productCountText = "1 ürün",
            totalText = "850,25 ₺",
            storeName = "UrbanBag",
            statusColor = BbColors.Orange.Orange600,
            isActive = true
        ),
        OrderListUiModel(
            orderId = 4,
            orderNumber = "Sipariş #1000003",
            orderDate = "9 Mayıs 2026",
            statusText = "Ödeme Alındı",
            productTitle = "Ofis Aksesuar Seti",
            productCountText = "5 ürün",
            totalText = "15.600,00 ₺",
            storeName = "OfficePlus",
            statusColor = BbColors.Blue.Blue600,
            isActive = true
        ),
        OrderListUiModel(
            orderId = 5,
            orderNumber = "Sipariş #1000004",
            orderDate = "9 Mayıs 2026",
            statusText = "Teslim Edildi",
            productTitle = "Seramik Kupa",
            productCountText = "1 ürün",
            totalText = "210,00 ₺",
            storeName = "HomeCraft",
            statusColor = BbColors.Green.Green600,
            isActive = false
        )
    )
}

private data class OrderListUiModel(
    val orderId: Int,
    val orderNumber: String,
    val orderDate: String,
    val statusText: String,
    val productTitle: String,
    val productCountText: String,
    val totalText: String,
    val storeName: String,
    val statusColor: Color,
    val isActive: Boolean
)