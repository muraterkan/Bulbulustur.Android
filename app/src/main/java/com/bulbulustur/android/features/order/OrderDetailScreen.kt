package com.bulbulustur.android.features.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun OrderDetailScreen(
    orderId: Int = 1,
    onBackClick: () -> Unit = {},
    onContractClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onStoreClick: () -> Unit = {},
    onCancelRequestClick: (Long, String) -> Unit = { _, _ -> },
    onReturnRequestClick: (Long, String) -> Unit = { _, _ -> },
    onReviewCreateClick: (Long, Long, String) -> Unit = { _, _, _ -> },
    onShipmentTrackingClick: (Long) -> Unit = {}
) {
    val order = getDemoOrderDetail(orderId)

    var showSupportSheet by remember {
        mutableStateOf(false)
    }

    if (showSupportSheet) {
        OrderSupportBottomSheet(
            orderNumber = order.orderNumber,
            onDismiss = {
                showSupportSheet = false
            }
        )
    }

    OrderDetailPageScaffold(
        title = "Sipariş Detayı",
        subtitle = order.orderNumber,
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
                OrderDetailSummaryCard(order = order)
            }

            item {
                OrderDetailStatusCard(order = order)
            }

            order.storeGroups.forEach { storeGroup ->
                item {
                    OrderDetailStoreGroupCard(
                        order = order,
                        storeGroup = storeGroup,
                        onContractClick = onContractClick,
                        onStoreClick = onStoreClick,
                        onSupportClick = {
                            onSupportClick()
                            showSupportSheet = true
                        },
                        onCancelRequestClick = onCancelRequestClick,
                        onReturnRequestClick = onReturnRequestClick,
                        onReviewCreateClick = onReviewCreateClick,
                        onShipmentTrackingClick = onShipmentTrackingClick
                    )
                }
            }

            item {
                OrderDetailDeliveryCard(order = order)
            }

            item {
                OrderDetailPaymentCard(order = order)
            }

            item {
                OrderDetailActionsCard(
                    onContractClick = onContractClick,
                    onSupportClick = {
                        onSupportClick()
                        showSupportSheet = true
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrderSupportBottomSheet(
    orderNumber: String,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = BbColors.Surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    start = BbSpacing.PageHorizontal,
                    end = BbSpacing.PageHorizontal,
                    bottom = BbSpacing.PageBottom
                ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Sipariş Desteği",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "$orderNumber için destek almak istediğiniz konuyu seçin.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            OrderSupportActionRow(
                icon = Icons.Outlined.LocalShipping,
                title = "Kargo ve Teslimat Sorunu",
                subtitle = "Teslimat gecikmesi, kargo durumu veya adres sorunu",
                onClick = onDismiss
            )

            OrderSupportActionRow(
                icon = Icons.Outlined.ReceiptLong,
                title = "Ürün veya Sipariş Sorunu",
                subtitle = "Eksik, hatalı veya farklı ürün bildirimi",
                onClick = onDismiss
            )

            OrderSupportActionRow(
                icon = Icons.Outlined.Description,
                title = "İptal ve İade Koşulları",
                subtitle = "Siparişe ait iptal/iade süreci hakkında bilgi",
                onClick = onDismiss
            )

            BbButton(
                text = "Kapat",
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun OrderSupportActionRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .clickable {
                onClick()
            }
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OrderDetailIconBox(
            icon = icon,
            backgroundColor = BbColors.Surface,
            iconColor = MaterialTheme.colorScheme.onSurface
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BbIcon.Action)
        )
    }
}

@Composable
private fun OrderDetailPageScaffold(
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
        OrderDetailTopHeader(
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
private fun OrderDetailTopHeader(
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
private fun OrderDetailSummaryCard(
    order: OrderDetailUiModel
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
                OrderDetailIconBox(
                    icon = Icons.Outlined.ReceiptLong,
                    backgroundColor = BbColors.Yellow.Yellow100,
                    iconColor = BbColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = order.orderNumber,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "${order.orderDate} tarihinde oluşturuldu",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                OrderDetailStatusBadge(
                    text = order.statusText,
                    color = order.statusColor
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                OrderDetailMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "TUTAR",
                    value = order.totalText,
                    icon = Icons.Outlined.Payments,
                    iconColor = BbColors.Yellow.Yellow800
                )

                OrderDetailMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "ÜRÜN",
                    value = order.productCountText,
                    icon = Icons.Outlined.ReceiptLong,
                    iconColor = BbColors.Blue.Blue600
                )
            }
        }
    }
}

@Composable
private fun OrderDetailStatusCard(
    order: OrderDetailUiModel
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
            OrderDetailSectionTitle(
                title = "Sipariş Akışı",
                subtitle = "Siparişinizin güncel işlem durumu"
            )

            order.steps.forEachIndexed { index, step ->
                OrderDetailStepRow(step = step)

                if (index != order.steps.lastIndex) {
                    HorizontalDivider(color = BbColors.Border)
                }
            }
        }
    }
}

@Composable
private fun OrderDetailStoreGroupCard(
    order: OrderDetailUiModel,
    storeGroup: OrderDetailStoreGroupUiModel,
    onContractClick: () -> Unit,
    onStoreClick: () -> Unit,
    onSupportClick: () -> Unit,
    onCancelRequestClick: (Long, String) -> Unit,
    onReturnRequestClick: (Long, String) -> Unit,
    onReviewCreateClick: (Long, Long, String) -> Unit,
    onShipmentTrackingClick: (Long) -> Unit
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
                OrderDetailIconBox(
                    icon = Icons.Outlined.Storefront,
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                    iconColor = MaterialTheme.colorScheme.onSurface
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onStoreClick()
                        },
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Satıcı",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = storeGroup.storeName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BbIcon.Action)
                )
            }

            BbButton(
                text = "Satış Sözleşmesi",
                onClick = onContractClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Description,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )

            if (storeGroup.hasShipmentTracking) {
                OrderDetailCargoCard(
                    storeGroup = storeGroup,
                    onShipmentTrackingClick = {
                        onShipmentTrackingClick(storeGroup.firstTrackableOrderStoreLineId)
                    }
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                storeGroup.products.forEachIndexed { index, product ->
                    OrderDetailProductRow(
                        orderKey = order.orderKey,
                        product = product,
                        onSupportClick = onSupportClick,
                        onCancelRequestClick = onCancelRequestClick,
                        onReturnRequestClick = onReturnRequestClick,
                        onReviewCreateClick = onReviewCreateClick,
                        onShipmentTrackingClick = onShipmentTrackingClick
                    )

                    if (index != storeGroup.products.lastIndex) {
                        HorizontalDivider(color = BbColors.Border)
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderDetailCargoCard(
    storeGroup: OrderDetailStoreGroupUiModel,
    onShipmentTrackingClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.Blue.Blue50,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderDetailIconBox(
                    icon = Icons.Outlined.LocalShipping,
                    backgroundColor = BbColors.Surface,
                    iconColor = BbColors.Blue.Blue600
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Kargo Bilgisi",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "${storeGroup.cargoCompany} · ${storeGroup.cargoTrackingNumber}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            BbButton(
                text = "Kargom Nerede?",
                onClick = onShipmentTrackingClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocalShipping,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderDetailProductRow(
    orderKey: String,
    product: OrderDetailProductUiModel,
    onSupportClick: () -> Unit,
    onCancelRequestClick: (Long, String) -> Unit,
    onReturnRequestClick: (Long, String) -> Unit,
    onReviewCreateClick: (Long, Long, String) -> Unit,
    onShipmentTrackingClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BbIcon.Feature)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "${product.quantity} adet · ${product.unitPriceText}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OrderDetailLineStatusBadge(
                    text = product.statusText,
                    status = product.status
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Toplam",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = product.totalText,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        OrderDetailProductActions(
            orderKey = orderKey,
            product = product,
            onSupportClick = onSupportClick,
            onCancelRequestClick = onCancelRequestClick,
            onReturnRequestClick = onReturnRequestClick,
            onReviewCreateClick = onReviewCreateClick,
            onShipmentTrackingClick = onShipmentTrackingClick
        )
    }
}

@Composable
private fun OrderDetailProductActions(
    orderKey: String,
    product: OrderDetailProductUiModel,
    onSupportClick: () -> Unit,
    onCancelRequestClick: (Long, String) -> Unit,
    onReturnRequestClick: (Long, String) -> Unit,
    onReviewCreateClick: (Long, Long, String) -> Unit,
    onShipmentTrackingClick: (Long) -> Unit
) {
    val actions = product.availableActions()

    if (actions.isEmpty()) {
        return
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        actions.forEach { action ->
            when (action) {
                OrderDetailLineAction.Cancel -> {
                    BbButton(
                        text = "İptal Et",
                        onClick = {
                            onCancelRequestClick(product.orderStoreLineId, orderKey)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small
                    )
                }

                OrderDetailLineAction.Return -> {
                    BbButton(
                        text = "İade Talebi",
                        onClick = {
                            onReturnRequestClick(product.orderStoreLineId, orderKey)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small
                    )
                }

                OrderDetailLineAction.Support -> {
                    BbButton(
                        text = "Talep Oluştur",
                        onClick = onSupportClick,
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.HelpOutline,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(BbIcon.ButtonIcon)
                            )
                        }
                    )
                }

                OrderDetailLineAction.Review -> {
                    BbButton(
                        text = "Değerlendir",
                        onClick = {
                            onReviewCreateClick(
                                product.orderStoreLineId,
                                product.productId,
                                product.memberKey
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
                                modifier = Modifier.size(BbIcon.ButtonIcon)
                            )
                        }
                    )
                }

                OrderDetailLineAction.ShipmentTracking -> {
                    BbButton(
                        text = "Kargom Nerede?",
                        onClick = {
                            onShipmentTrackingClick(product.orderStoreLineId)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.LocalShipping,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(BbIcon.ButtonIcon)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderDetailDeliveryCard(
    order: OrderDetailUiModel
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
            OrderDetailSectionTitle(
                title = "Teslimat Bilgileri",
                subtitle = "Siparişe ait teslimat adresi"
            )

            OrderDetailInfoRow(
                icon = Icons.Outlined.CalendarMonth,
                title = "Tahmini Teslimat",
                value = order.estimatedDelivery
            )

            HorizontalDivider(color = BbColors.Border)

            Text(
                text = order.addressText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderDetailPaymentCard(
    order: OrderDetailUiModel
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
            OrderDetailSectionTitle(
                title = "Ödeme Özeti",
                subtitle = "Siparişe ait tutar dağılımı"
            )

            OrderDetailAmountRow(
                title = "Ürün Toplamı",
                value = order.productTotalText
            )

            OrderDetailAmountRow(
                title = "Kargo",
                value = order.cargoText
            )

            HorizontalDivider(color = BbColors.Border)

            OrderDetailAmountRow(
                title = "Genel Toplam",
                value = order.totalText,
                isStrong = true
            )
        }
    }
}

@Composable
private fun OrderDetailActionsCard(
    onContractClick: () -> Unit,
    onSupportClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            OrderDetailSectionTitle(
                title = "Sipariş İşlemleri",
                subtitle = "Sözleşme ve destek aksiyonları"
            )

            BbButton(
                text = "Mesafeli Satış Sözleşmesi",
                onClick = onContractClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Description,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )

            BbButton(
                text = "Sipariş İçin Destek Al",
                onClick = onSupportClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocalShipping,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderDetailStepRow(
    step: OrderDetailStepUiModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OrderDetailIconBox(
            icon = step.icon,
            backgroundColor = step.color.copy(alpha = if (step.isCompleted) 0.16f else 0.08f),
            iconColor = step.color
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
private fun OrderDetailInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OrderDetailIconBox(
            icon = icon,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurface
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
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
private fun OrderDetailAmountRow(
    title: String,
    value: String,
    isStrong: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
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
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun OrderDetailSectionTitle(
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
private fun OrderDetailStatusBadge(
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
private fun OrderDetailLineStatusBadge(
    text: String,
    status: OrderDetailLineStatus
) {
    val color = when (status) {
        OrderDetailLineStatus.Received -> BbColors.Blue.Blue600
        OrderDetailLineStatus.Preparing -> BbColors.Orange.Orange600
        OrderDetailLineStatus.Shipped -> BbColors.Blue.Blue600
        OrderDetailLineStatus.Delivered -> BbColors.Green.Green600
        OrderDetailLineStatus.Other -> BbColors.TextMuted
    }

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
private fun OrderDetailIconBox(
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

private fun OrderDetailProductUiModel.availableActions(): List<OrderDetailLineAction> {
    return when (status) {
        OrderDetailLineStatus.Received -> {
            listOf(OrderDetailLineAction.Cancel)
        }

        OrderDetailLineStatus.Preparing -> {
            listOf(OrderDetailLineAction.Support)
        }

        OrderDetailLineStatus.Shipped -> {
            listOf(
                OrderDetailLineAction.ShipmentTracking,
                OrderDetailLineAction.Support
            )
        }

        OrderDetailLineStatus.Delivered -> {
            listOf(
                OrderDetailLineAction.ShipmentTracking,
                OrderDetailLineAction.Return,
                OrderDetailLineAction.Support,
                OrderDetailLineAction.Review
            )
        }

        OrderDetailLineStatus.Other -> {
            emptyList()
        }
    }
}

private val OrderDetailStoreGroupUiModel.hasShipmentTracking: Boolean
    get() = products.any {
        it.status == OrderDetailLineStatus.Shipped || it.status == OrderDetailLineStatus.Delivered
    }

private val OrderDetailStoreGroupUiModel.firstTrackableOrderStoreLineId: Long
    get() = products.firstOrNull {
        it.status == OrderDetailLineStatus.Shipped || it.status == OrderDetailLineStatus.Delivered
    }?.orderStoreLineId ?: 0L

private fun getDemoOrderDetail(
    orderId: Int
): OrderDetailUiModel {
    return OrderDetailUiModel(
        orderId = orderId,
        orderKey = "ORD-F4QO-AFPR-J5EX",
        orderNumber = "Sipariş #1000000",
        orderDate = "9 Mayıs 2026",
        statusText = "İşlemde",
        statusColor = BbColors.Orange.Orange600,
        productCountText = "3 ürün",
        productTotalText = "2.400,75 ₺",
        cargoText = "50,00 ₺",
        totalText = "2.450,75 ₺",
        estimatedDelivery = "12 Mayıs 2026",
        addressText = "Murat Erkan · İstanbul / Türkiye · Teslimat adresi API bağlandığında gerçek kullanıcı adresinden beslenecek.",
        storeGroups = listOf(
            OrderDetailStoreGroupUiModel(
                storeKey = "STORE-ORTOBELLA",
                storeName = "Ortobella",
                cargoCompany = "Yurtiçi Kargo",
                cargoTrackingNumber = "YK-2026-00012345",
                products = listOf(
                    OrderDetailProductUiModel(
                        orderStoreLineId = 10001L,
                        productId = 501L,
                        memberKey = "MEMBER-SECURE-501",
                        name = "Minimal Sırt Çantası",
                        quantity = 1,
                        unitPriceText = "850,25 ₺",
                        totalText = "850,25 ₺",
                        statusText = "Sipariş Alındı",
                        status = OrderDetailLineStatus.Received
                    ),
                    OrderDetailProductUiModel(
                        orderStoreLineId = 10002L,
                        productId = 502L,
                        memberKey = "MEMBER-SECURE-502",
                        name = "Kadın Sneaker Günlük Ayakkabı",
                        quantity = 1,
                        unitPriceText = "1.200,25 ₺",
                        totalText = "1.200,25 ₺",
                        statusText = "Kargoya Verildi",
                        status = OrderDetailLineStatus.Shipped
                    ),
                    OrderDetailProductUiModel(
                        orderStoreLineId = 10003L,
                        productId = 503L,
                        memberKey = "MEMBER-SECURE-503",
                        name = "Pamuklu Basic Tişört",
                        quantity = 1,
                        unitPriceText = "350,25 ₺",
                        totalText = "350,25 ₺",
                        statusText = "Teslim Edildi",
                        status = OrderDetailLineStatus.Delivered
                    )
                )
            )
        ),
        steps = listOf(
            OrderDetailStepUiModel(
                title = "Sipariş Alındı",
                description = "Siparişiniz başarıyla oluşturuldu.",
                icon = Icons.Outlined.ReceiptLong,
                color = BbColors.Green.Green600,
                isCompleted = true
            ),
            OrderDetailStepUiModel(
                title = "Ödeme Onaylandı",
                description = "Ödeme işlemi tamamlandı.",
                icon = Icons.Outlined.Payments,
                color = BbColors.Green.Green600,
                isCompleted = true
            ),
            OrderDetailStepUiModel(
                title = "Sipariş İşlemde",
                description = "Satıcı ve kargo süreçleri ürün satırlarına göre güncelleniyor.",
                icon = Icons.Outlined.LocalShipping,
                color = BbColors.Orange.Orange600,
                isCompleted = false
            )
        )
    )
}

private data class OrderDetailUiModel(
    val orderId: Int,
    val orderKey: String,
    val orderNumber: String,
    val orderDate: String,
    val statusText: String,
    val statusColor: Color,
    val productCountText: String,
    val productTotalText: String,
    val cargoText: String,
    val totalText: String,
    val estimatedDelivery: String,
    val addressText: String,
    val storeGroups: List<OrderDetailStoreGroupUiModel>,
    val steps: List<OrderDetailStepUiModel>
)

private data class OrderDetailStoreGroupUiModel(
    val storeKey: String,
    val storeName: String,
    val cargoCompany: String,
    val cargoTrackingNumber: String,
    val products: List<OrderDetailProductUiModel>
)

private data class OrderDetailProductUiModel(
    val orderStoreLineId: Long,
    val productId: Long,
    val memberKey: String,
    val name: String,
    val quantity: Int,
    val unitPriceText: String,
    val totalText: String,
    val statusText: String,
    val status: OrderDetailLineStatus
)

private data class OrderDetailStepUiModel(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val isCompleted: Boolean
)

private enum class OrderDetailLineStatus {
    Received,
    Preparing,
    Shipped,
    Delivered,
    Other
}

private enum class OrderDetailLineAction {
    Cancel,
    Return,
    Support,
    Review,
    ShipmentTracking
}