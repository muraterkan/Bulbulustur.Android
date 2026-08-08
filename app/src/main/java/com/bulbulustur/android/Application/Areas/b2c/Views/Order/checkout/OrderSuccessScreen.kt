package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun OrderSuccessScreen(
    orderId: Int = 1,
    onGoHomeClick: () -> Unit = {},
    onOrderDetailClick: (Int) -> Unit = {},
    onContinueShoppingClick: () -> Unit = {}
) {
    val screenData = remember(orderId) {
        getOrderSuccessScreenData(orderId)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 18.dp,
                    end = 16.dp,
                    bottom = 18.dp
                ),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                item {
                    OrderSuccessHero(
                        summary = screenData.summary
                    )
                }

                item {
                    OrderSuccessStatusCard(
                        summary = screenData.summary
                    )
                }

                item {
                    OrderSuccessSectionTitle(
                        title = BBLocalization.Current.Get(key = "e90ea04b-714e-4f1f-92ed-7b962c39fb13", fallback = "Sipariş paketleri"),
                        description = BBLocalization.Current.Get(key = "0ee1e938-f007-463d-9831-62fdc25ad6de", fallback = "Mağaza bazlı gönderim bilgileri.")
                    )
                }

                items(screenData.shipmentGroups) { shipmentGroup ->
                    OrderSuccessShipmentCard(
                        shipmentGroup = shipmentGroup
                    )
                }

                item {
                    OrderSuccessTotalCard(
                        total = screenData.total
                    )
                }

                item {
                    OrderSuccessInfoCard()
                }
            }

            OrderSuccessBottomBar(
                orderId = screenData.summary.orderId,
                onGoHomeClick = onGoHomeClick,
                onOrderDetailClick = onOrderDetailClick,
                onContinueShoppingClick = onContinueShoppingClick
            )
        }
    }
}

@Composable
private fun OrderSuccessHero(
    summary: OrderSuccessSummary
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BbIconBox(
                size = BbIconBoxSize.ThreeXl,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text(
                    text = "✓",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = BBLocalization.Current.Get(key = "68da4900-3eff-41c4-863d-51cf011a164d", fallback = "Siparişin alındı"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sipariş numaran ${summary.orderNumber}. Hazırlık ve kargo süreçlerini sipariş detayından takip edebilirsin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun OrderSuccessStatusCard(
    summary: OrderSuccessSummary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OrderSuccessMiniStatCard(
            modifier = Modifier.weight(1f),
            title = summary.orderDateText,
            subtitle = "tarih"
        )

        OrderSuccessMiniStatCard(
            modifier = Modifier.weight(1f),
            title = summary.paymentStatusText,
            subtitle = "ödeme"
        )

        OrderSuccessMiniStatCard(
            modifier = Modifier.weight(1f),
            title = summary.packageCountText,
            subtitle = "paket"
        )
    }
}

@Composable
private fun OrderSuccessMiniStatCard(
    modifier: Modifier,
    title: String,
    subtitle: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderSuccessShipmentCard(
    shipmentGroup: OrderSuccessShipmentGroup
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBSpacing.Space12)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = shipmentGroup.storeLogoText,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = shipmentGroup.storeName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${shipmentGroup.productCount} ürün . ${shipmentGroup.cargoCompanyName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                OrderSuccessStatusBadge(
                    text = shipmentGroup.statusText
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = shipmentGroup.deliveryEstimateText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            shipmentGroup.products.forEach { product ->
                OrderSuccessProductLine(
                    product = product
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun OrderSuccessProductLine(
    product: OrderSuccessProductItem
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BBSpacing.Space11)
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = product.imageText,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "${product.variantText} . x${product.quantity}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = product.priceText,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun OrderSuccessStatusBadge(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(
                horizontal = 9.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun OrderSuccessTotalCard(
    total: OrderSuccessTotal
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4)
        ) {
            OrderSuccessTotalRow(
                title = "Ürün toplamı",
                value = total.productTotalText
            )

            Spacer(modifier = Modifier.height(8.dp))

            OrderSuccessTotalRow(
                title = "Kargo",
                value = total.cargoTotalText
            )

            Spacer(modifier = Modifier.height(8.dp))

            OrderSuccessTotalRow(
                title = BBLocalization.Current.Get(key = "9dd8d854-ca26-4660-bcb3-b7ec8e3f458b", fallback = "İndirim"),
                value = total.discountText
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.BorderThin)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OrderSuccessTotalRow(
                title = BBLocalization.Current.Get(key = "368c0ffb-df0f-4e02-ab6c-733fa4a53e49", fallback = "Ödenen tutar"),
                value = total.paidPriceText,
                isStrong = true
            )
        }
    }
}

@Composable
private fun OrderSuccessTotalRow(
    title: String,
    value: String,
    isStrong: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = if (isStrong) {
                MaterialTheme.typography.titleSmall
            } else {
                MaterialTheme.typography.bodySmall
            },
            fontWeight = if (isStrong) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = value,
            style = if (isStrong) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodySmall
            },
            fontWeight = if (isStrong) {
                FontWeight.Bold
            } else {
                FontWeight.SemiBold
            },
            color = if (isStrong) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
    }
}

@Composable
private fun OrderSuccessInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "17dc9296-ed13-44eb-8228-b54473055950", fallback = "Sırada ne var?"),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = BBLocalization.Current.Get(key = "05b110ed-bb73-42d1-9faa-38d6d3636868", fallback = "Mağazalar siparişini hazırlamaya başladığında kargo bilgileri sipariş detayına düşer. Bildirimler ve e-posta ile süreç hakkında bilgi alırsın."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderSuccessBottomBar(
    orderId: Int,
    onGoHomeClick: () -> Unit,
    onOrderDetailClick: (Int) -> Unit,
    onContinueShoppingClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 12.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(999.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        onOrderDetailClick(orderId)
                    }
                    .padding(
                        horizontal = 18.dp,
                        vertical = 12.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "45ff1768-f800-4829-9ccf-e59a75e76f16", fallback = ""),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(999.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable {
                            onGoHomeClick()
                        }
                        .padding(
                            horizontal = 14.dp,
                            vertical = 11.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ana sayfa",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(999.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable {
                            onContinueShoppingClick()
                        }
                        .padding(
                            horizontal = 14.dp,
                            vertical = 11.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Alışverişe devam",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderSuccessSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class OrderSuccessScreenData(
    val summary: OrderSuccessSummary,
    val shipmentGroups: List<OrderSuccessShipmentGroup>,
    val total: OrderSuccessTotal
)

data class OrderSuccessSummary(
    val orderId: Int,
    val orderNumber: String,
    val orderDateText: String,
    val paymentStatusText: String,
    val packageCountText: String
)

data class OrderSuccessShipmentGroup(
    val storeId: Int,
    val storeName: String,
    val storeLogoText: String,
    val cargoCompanyName: String,
    val deliveryEstimateText: String,
    val statusText: String,
    val productCount: Int,
    val products: List<OrderSuccessProductItem>
)

data class OrderSuccessProductItem(
    val id: Int,
    val name: String,
    val variantText: String,
    val quantity: Int,
    val priceText: String,
    val imageText: String
)

data class OrderSuccessTotal(
    val productTotalText: String,
    val cargoTotalText: String,
    val discountText: String,
    val paidPriceText: String
)

private fun getOrderSuccessScreenData(orderId: Int): OrderSuccessScreenData {
    return OrderSuccessScreenData(
        summary = OrderSuccessSummary(
            orderId = orderId,
            orderNumber = "BB-2026-000184",
            orderDateText = "Bugün",
            paymentStatusText = "Onaylandı",
            packageCountText = "2"
        ),
        shipmentGroups = listOf(
            OrderSuccessShipmentGroup(
                storeId = 1,
                storeName = "Ortobella Store",
                storeLogoText = "OS",
                cargoCompanyName = "Yurtiçi Kargo",
                deliveryEstimateText = "Tahmini teslimat: 1-3 iş günü",
                statusText = BBLocalization.Current.Get(key = "8e8c20e8-d05d-4d0b-898f-89d856c92e99", fallback = "Hazırlanıyor"),
                productCount = 2,
                products = listOf(
                    OrderSuccessProductItem(
                        id = 1,
                        name = BBLocalization.Current.Get(key = "cf2f4de0-711c-4308-a055-3ef7eb00d9c7", fallback = "Kadın klasik sneaker ayakkabı"),
                        variantText = "Beyaz . 38",
                        quantity = 1,
                        priceText = "₺899,90",
                        imageText = "P1"
                    ),
                    OrderSuccessProductItem(
                        id = 2,
                        name = BBLocalization.Current.Get(key = "4948aead-b9d0-49ad-993c-0104e68e7f1c", fallback = "Rahat taban günlük ayakkabı"),
                        variantText = "Siyah . 39",
                        quantity = 1,
                        priceText = "₺749,90",
                        imageText = "P2"
                    )
                )
            ),
            OrderSuccessShipmentGroup(
                storeId = 2,
                storeName = "Moda Nova",
                storeLogoText = "MN",
                cargoCompanyName = "Yurtiçi Kargo",
                deliveryEstimateText = "Tahmini teslimat: 2-4 iş günü",
                statusText = "Onaylandı",
                productCount = 1,
                products = listOf(
                    OrderSuccessProductItem(
                        id = 3,
                        name = "Oversize pamuklu basic tişört",
                        variantText = "Lacivert . M",
                        quantity = 2,
                        priceText = "₺699,80",
                        imageText = "P3"
                    )
                )
            )
        ),
        total = OrderSuccessTotal(
            productTotalText = "₺2.759,80",
            cargoTotalText = "₺89,80",
            discountText = "-₺120,00",
            paidPriceText = "₺2.729,60"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun OrderSuccessScreenPreview() {
    MaterialTheme {
        OrderSuccessScreen()
    }
}


