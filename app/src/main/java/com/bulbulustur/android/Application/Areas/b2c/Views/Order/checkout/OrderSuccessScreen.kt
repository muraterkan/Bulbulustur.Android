package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun OrderSuccessScreen(
    orderId: Int = 1,
    onGoHomeClick: () -> Unit = {},
    onOrderDetailClick: (Int) -> Unit = {},
    onContinueShoppingClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    val screenData = remember(orderId) {
        getOrderSuccessScreenData(orderId)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            RetailBottomNavigation(
                selectedItem = RetailBottomNavigationItem.Basket,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        RetailBottomNavigationItem.Home -> onGoHomeClick()
                        RetailBottomNavigationItem.Menu -> onMenuClick()
                        RetailBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        RetailBottomNavigationItem.Basket -> onBasketClick()
                        RetailBottomNavigationItem.Account -> onAccountClick()
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                OrderSuccessHero(
                    summary = screenData.summary,
                    onOrderDetailClick = {
                        onOrderDetailClick(screenData.summary.orderId)
                    }
                )
            }

            item {
                OrderSuccessSummaryCard(
                    summary = screenData.summary
                )
            }

            item {
                OrderSuccessSectionTitle(
                    title = BBLocalization.Current.Get(
                        key = "e90ea04b-714e-4f1f-92ed-7b962c39fb13",
                        fallback = "Sipariş paketleri"
                    ),
                    description = BBLocalization.Current.Get(
                        key = "0ee1e938-f007-463d-9831-62fdc25ad6de",
                        fallback = "Mağaza bazlı gönderim bilgileri."
                    )
                )
            }

            items(
                items = screenData.shipmentGroups,
                key = { shipmentGroup -> shipmentGroup.storeId }
            ) { shipmentGroup ->
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

            item {
                BbButton(
                    text = BBLocalization.Current.Get(
                        key = "45ff1768-f800-4829-9ccf-e59a75e76f16",
                        fallback = "Sipariş Detayına Git"
                    ),
                    onClick = {
                        onOrderDetailClick(screenData.summary.orderId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )
            }

            item {
                BbButton(
                    text = BBLocalization.Current.Get(
                        key = "507ef499-3ec4-4197-98b3-66c6a6402a33",
                        fallback = "Alışverişe devam et"
                    ),
                    onClick = onContinueShoppingClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Secondary,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun OrderSuccessHero(
    summary: OrderSuccessSummary,
    onOrderDetailClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxXl)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = androidx.compose.foundation.shape.CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Text(
                text = BBLocalization.Current.Get(
                    key = "68da4900-3eff-41c4-863d-51cf011a164d",
                    fallback = "Siparişin alındı"
                ),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Sipariş numaran ${summary.orderNumber}. Hazırlık ve kargo süreçlerini sipariş detayından takip edebilirsin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = BBLocalization.Current.Get(
                    key = "45ff1768-f800-4829-9ccf-e59a75e76f16",
                    fallback = "Sipariş Detayına Git"
                ),
                onClick = onOrderDetailClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun OrderSuccessSummaryCard(
    summary: OrderSuccessSummary
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            OrderSuccessSummaryRow(
                title = "Sipariş No",
                value = summary.orderNumber
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            OrderSuccessSummaryRow(
                title = "Tarih",
                value = summary.orderDateText
            )

            OrderSuccessSummaryRow(
                title = "Ödeme",
                value = summary.paymentStatusText
            )

            OrderSuccessSummaryRow(
                title = "Paket",
                value = summary.packageCountText
            )
        }
    }
}

@Composable
private fun OrderSuccessSummaryRow(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun OrderSuccessShipmentCard(
    shipmentGroup: OrderSuccessShipmentGroup
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = androidx.compose.foundation.shape.CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = shipmentGroup.storeLogoText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = shipmentGroup.storeName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "${shipmentGroup.productCount} ürün · ${shipmentGroup.cargoCompanyName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = shipmentGroup.statusText,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = shipmentGroup.deliveryEstimateText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            shipmentGroup.products.forEachIndexed { index, product ->
                OrderSuccessProductLine(
                    product = product
                )

                if (index != shipmentGroup.products.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
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
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BBIcon.BoxMd)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = product.imageText,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "${product.variantText} · x${product.quantity}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = product.priceText,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun OrderSuccessTotalCard(
    total: OrderSuccessTotal
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BBIcon.Inline)
                )

                Text(
                    text = BBLocalization.Current.Get(
                        key = "c3894b16-f66b-47f2-8853-11c6d9084bdf",
                        fallback = "Sipariş Özeti"
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            OrderSuccessTotalRow(
                title = BBLocalization.Current.Get(
                    key = "9ca1b3ac-05ef-462c-a4ef-e4bcd4b4b11b",
                    fallback = "Ürün Toplamı"
                ),
                value = total.productTotalText
            )

            OrderSuccessTotalRow(
                title = BBLocalization.Current.Get(
                    key = "8fa1207a-2a06-4bdb-936b-f7da848e0f72",
                    fallback = "Kargo"
                ),
                value = total.cargoTotalText
            )

            OrderSuccessTotalRow(
                title = BBLocalization.Current.Get(
                    key = "9dd8d854-ca26-4660-bcb3-b7ec8e3f458b",
                    fallback = "İndirim"
                ),
                value = total.discountText
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            OrderSuccessTotalRow(
                title = BBLocalization.Current.Get(
                    key = "368c0ffb-df0f-4e02-ab6c-733fa4a53e49",
                    fallback = "Ödenen Tutar"
                ),
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
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
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
            fontWeight = if (isStrong) FontWeight.Bold else FontWeight.Normal,
            color = if (isStrong) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )

        Text(
            text = value,
            style = if (isStrong) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodySmall
            },
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun OrderSuccessInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = BBLocalization.Current.Get(
                    key = "17dc9296-ed13-44eb-8228-b54473055950",
                    fallback = "Sırada ne var?"
                ),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "05b110ed-bb73-42d1-9faa-38d6d3636868",
                    fallback = "Mağazalar siparişini hazırlamaya başladığında kargo bilgileri sipariş detayına düşer. Bildirimler ve e-posta ile süreç hakkında bilgi alırsın."
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderSuccessSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

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
                statusText = BBLocalization.Current.Get(
                    key = "8e8c20e8-d05d-4d0b-898f-89d856c92e99",
                    fallback = "Hazırlanıyor"
                ),
                productCount = 2,
                products = listOf(
                    OrderSuccessProductItem(
                        id = 1,
                        name = BBLocalization.Current.Get(
                            key = "cf2f4de0-711c-4308-a055-3ef7eb00d9c7",
                            fallback = "Kadın klasik sneaker ayakkabı"
                        ),
                        variantText = "Beyaz · 38",
                        quantity = 1,
                        priceText = "₺899,90",
                        imageText = "P1"
                    ),
                    OrderSuccessProductItem(
                        id = 2,
                        name = BBLocalization.Current.Get(
                            key = "4948aead-b9d0-49ad-993c-0104e68e7f1c",
                            fallback = "Rahat taban günlük ayakkabı"
                        ),
                        variantText = "Siyah · 39",
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
                        variantText = "Lacivert · M",
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
    BbTheme {
        OrderSuccessScreen()
    }
}
