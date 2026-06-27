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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Visibility
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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun OrderListScreen(
    onBackClick: () -> Unit = {},
    onOrderDetailClick: (Int) -> Unit = {}
) {
    val orders = getDemoOrders()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Siparişlerim",
                subtitle = "Sipariş ve teslimat durumlarınızı takip edin.",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
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
            if (orders.isEmpty()) {
                item {
                    OrderEmptyState()
                }
            } else {
                item {
                    OrderListOverviewCard(
                        totalOrderCount = orders.size,
                        activeOrderCount = orders.count {
                            it.isActive
                        },
                        deliveredOrderCount = orders.count {
                            it.statusText == "Teslim Edildi"
                        }
                    )
                }

                items(
                    items = orders,
                    key = { order ->
                        order.orderId
                    }
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderIconBox(
                    icon = Icons.Outlined.ReceiptLong,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
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
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderIconBox(
                    icon = Icons.Outlined.CalendarMonth,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
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
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
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
                    iconColor = BBColors.Yellow.Yellow800
                )
            }

            OrderProductSummaryBox(
                order = order
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space2
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(
                            BBIcon.Inline
                        )
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
                            modifier = Modifier.size(
                                BBIcon.ButtonIcon
                            )
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(
                                BBIcon.ButtonIcon
                            )
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
                shape = BBRadius.LgShape
            )
            .padding(
                BBSpacing.CardPaddingCompact
            ),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ReceiptLong,
            contentDescription = null,
            tint = BBColors.Blue.Blue600,
            modifier = Modifier.size(
                BBIcon.Action
            )
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
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
                tint = iconColor,
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
private fun OrderStatBox(
    modifier: Modifier = Modifier,
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
                BBSpacing.Space1
            )
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
                color = color.copy(
                    alpha = BBAlpha.Overlay
                ),
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            OrderIconBox(
                icon = Icons.Outlined.ReceiptLong,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Text(
                text = "Sipariş bulunamadı",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Geçmiş veya devam eden siparişleriniz oluştuĞunda burada listelenir.",
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
            statusColor = BBColors.Orange.Orange600,
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
            statusColor = BBColors.Green.Green600,
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
            statusColor = BBColors.Orange.Orange600,
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
            statusColor = BBColors.Blue.Blue600,
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
            statusColor = BBColors.Green.Green600,
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
