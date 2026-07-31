package com.bulbulustur.android.Application.Areas.b2c.Views.order

import com.bulbulustur.android.Application.Localization.BBLocalization

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
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bulbulustur.android.Application.Areas.b2c.Controllers.OrderController
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
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreDTO
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreLineDTO
import java.text.NumberFormat
import java.util.Locale

@Composable
fun OrderDetailScreen(
    orderId: Int,
    orderKey: String,
    onBackClick: () -> Unit = {},
    onContractClick: (String) -> Unit = {},
    onSupportClick: () -> Unit = {},
    onStoreClick: (Int) -> Unit = {},
    onCancelRequestClick: (Long, String) -> Unit = { _, _ -> },
    onReturnRequestClick: (Long, String) -> Unit = { _, _ -> },
    onReviewCreateClick: (Long, Long, String) -> Unit = { _, _, _ -> },
    onShipmentTrackingClick: (Int) -> Unit = {},
    controller: OrderController = viewModel()
) {
    val state by controller.State.collectAsStateWithLifecycle()

    LaunchedEffect(orderKey) {
        if (orderKey.isNotBlank()) {
            controller.GetOrderStoresAsync(orderKey)
        }
    }

    val orderStores = state.OrderStores
    val orderLines = orderStores.flatMap { it.OrderStoreLines }
    val total = orderStores.sumOf { it.StoreGrandTotal }
    val netTotal = orderStores.sumOf { it.StoreTotalNetPrice }
    val shippingTotal = orderStores.sumOf { it.StoreTotalShipping }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "b820bc4a-7523-4901-a326-a07c9ec43637", fallback = "Sipariş Detayları"),
                subtitle = orderKey.ifBlank { "Sipariş #$orderId" },
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            when {
                orderKey.isBlank() -> {
                    item {
                        OrderDetailMessageCard(
                            title = "Sipariş anahtarı bulunamadı",
                            description = "Sipariş detayı açılamadı."
                        )
                    }
                }

                state.IsLoading && orderStores.isEmpty() -> {
                    item {
                        OrderDetailLoadingCard()
                    }
                }

                state.ErrorMessage != null && orderStores.isEmpty() -> {
                    item {
                        OrderDetailMessageCard(
                            title = "Sipariş detayı alınamadı",
                            description = state.ErrorMessage.orEmpty()
                        )
                    }
                }

                orderStores.isEmpty() -> {
                    item {
                        OrderDetailMessageCard(
                            title = BBLocalization.Current.Get(key = "838ffc4f-83f8-4fc2-87dc-8b668084ba59", fallback = "Sipariş detayı bulunamadı"),
                            description = "Bu siparişe ait mağaza veya ürün kaydı bulunamadı."
                        )
                    }
                }

                else -> {
                    item {
                        OrderDetailSummaryCard(
                            orderId = orderId,
                            orderKey = orderKey,
                            productCount = orderLines.sumOf { it.Quantity },
                            storeCount = orderStores.size,
                            total = total
                        )
                    }

                    items(
                        items = orderStores,
                        key = { store -> store.OrderStoreId }
                    ) { store ->
                        OrderDetailStoreCard(
                            store = store,
                            orderKey = orderKey,
                            onContractClick = onContractClick,
                            onSupportClick = onSupportClick,
                            onStoreClick = onStoreClick,
                            onCancelRequestClick = onCancelRequestClick,
                            onReturnRequestClick = onReturnRequestClick,
                            onReviewCreateClick = onReviewCreateClick,
                            onShipmentTrackingClick = onShipmentTrackingClick
                        )
                    }

                    item {
                        OrderDetailPaymentCard(
                            netTotal = netTotal,
                            shippingTotal = shippingTotal,
                            total = total
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderDetailSummaryCard(
    orderId: Int,
    orderKey: String,
    productCount: Int,
    storeCount: Int,
    total: Double
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderDetailIconBox(
                    icon = Icons.Outlined.ReceiptLong,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "Sipariş #$orderId",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = orderKey,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                OrderDetailMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "TOPLAM",
                    value = total.toCurrencyText(),
                    icon = Icons.Outlined.Payments,
                    iconColor = BBColors.Yellow.Yellow800
                )

                OrderDetailMiniBox(
                    modifier = Modifier.weight(1f),
                    title = BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = ""),
                    value = "$productCount adet",
                    icon = Icons.Outlined.ReceiptLong,
                    iconColor = BBColors.Blue.Blue600
                )
            }

            Text(
                text = "$storeCount mağazadan oluşan sipariş",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderDetailStoreCard(
    store: OrderStoreDTO,
    orderKey: String,
    onContractClick: (String) -> Unit,
    onSupportClick: () -> Unit,
    onStoreClick: (Int) -> Unit,
    onCancelRequestClick: (Long, String) -> Unit,
    onReturnRequestClick: (Long, String) -> Unit,
    onReviewCreateClick: (Long, Long, String) -> Unit,
    onShipmentTrackingClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Default,
                padding = BbCardPadding.None,
                onClick = {
                    onStoreClick(store.StoreId)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BBSpacing.CardPaddingCompact),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OrderDetailIconBox(
                        icon = Icons.Outlined.Storefront,
                        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                        iconColor = MaterialTheme.colorScheme.onSurface
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        Text(
                            text = BBLocalization.Current.Get(key = "2ac4c8be-0d5d-4c84-afe8-628839892727", fallback = ""),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = store.StoreName.ifBlank { "Mağaza #${store.StoreId}" },
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.Action)
                    )
                }
            }

            BbButton(
                text = BBLocalization.Current.Get(key = "8b46757f-5819-4b3d-ab88-c52ce2008e3f", fallback = "Satış Sözleşmesi"),
                onClick = {
                    onContractClick(store.StoreKey)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.RequestQuote,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )

            store.OrderStoreLines.forEachIndexed { index, line ->
                OrderDetailProductRow(
                    line = line,
                    orderKey = orderKey,
                    onSupportClick = onSupportClick,
                    onCancelRequestClick = onCancelRequestClick,
                    onReturnRequestClick = onReturnRequestClick,
                    onReviewCreateClick = onReviewCreateClick,
                    onShipmentTrackingClick = onShipmentTrackingClick
                )

                if (index != store.OrderStoreLines.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }

            OrderDetailStoreTotalRow(
                title = "Mağaza Toplamı",
                value = store.StoreGrandTotal.toCurrencyText()
            )
        }
    }
}

@Composable
private fun OrderDetailProductRow(
    line: OrderStoreLineDTO,
    orderKey: String,
    onSupportClick: () -> Unit,
    onCancelRequestClick: (Long, String) -> Unit,
    onReturnRequestClick: (Long, String) -> Unit,
    onReviewCreateClick: (Long, Long, String) -> Unit,
    onShipmentTrackingClick: (Int) -> Unit
) {
    val statusText = line.OrderStoreLineStatus.ifBlank {
        line.OrderStatus.ifBlank { "Sipariş Alındı" }
    }

    val statusColor = getOrderLineStatusColor(statusText)
    val deliveryNumber = line.DeliveryNumber.toIntOrNull()
    val isDelivered = statusText.contains("teslim", ignoreCase = true)
    val isShipped = statusText.contains("kargo", ignoreCase = true)
    val isCancelable = !isDelivered && !isShipped && line.CancellationDate.isBlank()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.Feature)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = line.ProductName.ifBlank { "Ürün #${line.ProductId}" },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "${line.Quantity} adet · ${line.UnitPrice.toCurrencyText(line.CurrencySymbol)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (line.Color.isNotBlank() || line.Size.isNotBlank()) {
                    Text(
                        text = listOf(line.Color, line.Size)
                            .filter { it.isNotBlank() }
                            .joinToString(" · "),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                OrderDetailStatusBadge(
                    text = statusText,
                    color = statusColor
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "e736c25f-c944-4f52-a206-819f93d64a29", fallback = "Toplam"),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = line.TotalPrice.toCurrencyText(line.CurrencySymbol),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        if (line.CargoCompany.isNotBlank() || line.DeliveryNumber.isNotBlank()) {
            OrderDetailCargoBox(
                cargoCompany = line.CargoCompany,
                deliveryNumber = line.DeliveryNumber,
                onClick = {
                    deliveryNumber?.let(onShipmentTrackingClick)
                },
                enabled = deliveryNumber != null
            )
        }

        if (isCancelable) {
            BbButton(
                text = BBLocalization.Current.Get(key = "92ebe8f3-c0b3-48a9-88a5-bb431ba27bf8", fallback = "İptal Et"),
                onClick = {
                    onCancelRequestClick(
                        line.OrderStoreLineId.toLong(),
                        orderKey
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )
        }

        if (isDelivered) {
            BbButton(
                text = BBLocalization.Current.Get(key = "c1b6be9d-c63a-494b-aa84-efbe15520640", fallback = "İade Talebi"),
                onClick = {
                    onReturnRequestClick(
                        line.OrderStoreLineId.toLong(),
                        orderKey
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "000bf440-0909-4b14-b7f1-cba8861e579f", fallback = "Değerlendir"),
                onClick = {
                    onReviewCreateClick(
                        line.OrderStoreLineId.toLong(),
                        line.ProductId.toLong(),
                        line.ProductSecureKey
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }

        BbButton(
            text = BBLocalization.Current.Get(key = "fcb264e8-a984-415f-b971-69ea0a531bd9", fallback = "Talep Oluştur"),
            onClick = onSupportClick,
            modifier = Modifier.fillMaxWidth(),
            variant = BbButtonVariant.Light,
            size = BbButtonSize.Small,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.HelpOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.ButtonIcon)
                )
            }
        )
    }
}

@Composable
private fun OrderDetailCargoBox(
    cargoCompany: String,
    deliveryNumber: String,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BBColors.Blue.Blue50,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
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
                OrderDetailIconBox(
                    icon = Icons.Outlined.LocalShipping,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    iconColor = BBColors.Blue.Blue600
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = cargoCompany.ifBlank { "Kargo Bilgisi" },
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = deliveryNumber.ifBlank { "Takip numarası bulunamadı" },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (enabled) {
                BbButton(
                    text = "Kargom Nerede?",
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.LocalShipping,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun OrderDetailPaymentCard(
    netTotal: Double,
    shippingTotal: Double,
    total: Double
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "af87a38e-5f78-42ee-ac0f-ee365d81e179", fallback = "Ödeme Özeti"),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            OrderDetailStoreTotalRow(
                title = BBLocalization.Current.Get(key = "9ca1b3ac-05ef-462c-a4ef-e4bcd4b4b11b", fallback = "Ürün Toplamı"),
                value = netTotal.toCurrencyText()
            )

            OrderDetailStoreTotalRow(
                title = BBLocalization.Current.Get(key = "8fa1207a-2a06-4bdb-936b-f7da848e0f72", fallback = "Kargo"),
                value = shippingTotal.toCurrencyText()
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            OrderDetailStoreTotalRow(
                title = BBLocalization.Current.Get(key = "5a003c3e-9b6c-42ee-a69e-8b3b40e5176a", fallback = "Genel Toplam"),
                value = total.toCurrencyText(),
                strong = true
            )
        }
    }
}

@Composable
private fun OrderDetailStoreTotalRow(
    title: String,
    value: String,
    strong: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = if (strong) {
                MaterialTheme.typography.titleSmall
            } else {
                MaterialTheme.typography.bodySmall
            },
            color = if (strong) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )

        Text(
            text = value,
            style = if (strong) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodySmall
            },
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun OrderDetailMiniBox(
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
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(BBIcon.Action)
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
private fun OrderDetailStatusBadge(
    text: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .background(
                color = color.copy(alpha = BBAlpha.Overlay),
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
private fun OrderDetailIconBox(
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color
) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
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
            modifier = Modifier.size(BBIcon.Action)
        )
    }
}

@Composable
private fun OrderDetailLoadingCard() {
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
            CircularProgressIndicator()

            Text(
                text = "Sipariş detayı yükleniyor",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun OrderDetailMessageCard(
    title: String,
    description: String
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
            OrderDetailIconBox(
                icon = Icons.Outlined.ReceiptLong,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun getOrderLineStatusColor(status: String): Color {
    return when {
        status.contains("teslim", ignoreCase = true) -> BBColors.Green.Green600
        status.contains("kargo", ignoreCase = true) -> BBColors.Blue.Blue600
        status.contains("hazır", ignoreCase = true) -> BBColors.Orange.Orange600
        status.contains("iptal", ignoreCase = true) -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
}

private fun Double.toCurrencyText(symbol: String = "₺"): String {
    val formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("tr-TR"))
    formatter.minimumFractionDigits = 2
    formatter.maximumFractionDigits = 2

    return "${formatter.format(this)} ${symbol.ifBlank { "₺" }}"
}