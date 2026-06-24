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
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Sipariş Detayları",
                subtitle = order.orderNumber,
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
            item {
                OrderDetailSummaryCard(
                    order = order
                )
            }

            item {
                OrderDetailStatusCard(
                    order = order
                )
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
                OrderDetailDeliveryCard(
                    order = order
                )
            }

            item {
                OrderDetailPaymentCard(
                    order = order
                )
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
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    start = BBSpacing.PageHorizontal,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.PageBottom
                ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = "Sipariş DesteĞi",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "$orderNumber için destek almak istediĞiniz konuyu seçin.",
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
                icon = Icons.Outlined.RequestQuote,
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
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.CardPaddingCompact
                ),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OrderDetailIconBox(
                icon = icon,
                backgroundColor = MaterialTheme.colorScheme.surface,
                iconColor = MaterialTheme.colorScheme.onSurface
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
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
                modifier = Modifier.size(
                    BBIcon.Action
                )
            )
        }
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
                OrderDetailIconBox(
                    icon = Icons.Outlined.ReceiptLong,
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
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                OrderDetailMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "TUTAR",
                    value = order.totalText,
                    icon = Icons.Outlined.Payments,
                    iconColor = BBColors.Yellow.Yellow800
                )

                OrderDetailMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "ÜRÜN",
                    value = order.productCountText,
                    icon = Icons.Outlined.ReceiptLong,
                    iconColor = BBColors.Blue.Blue600
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            OrderDetailSectionTitle(
                title = "Sipariş Akışı",
                subtitle = "Siparişinizin güncel işlem durumu"
            )

            order.steps.forEachIndexed { index, step ->
                OrderDetailStepRow(
                    step = step
                )

                if (index != order.steps.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Default,
                padding = BbCardPadding.None,
                onClick = onStoreClick
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            BBSpacing.CardPaddingCompact
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space3
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OrderDetailIconBox(
                        icon = Icons.Outlined.Storefront,
                        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                        iconColor = MaterialTheme.colorScheme.onSurface
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(
                            BBSpacing.Space1
                        )
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
                        modifier = Modifier.size(
                            BBIcon.Action
                        )
                    )
                }
            }

            BbButton(
                text = "Satış Sözleşmesi",
                onClick = onContractClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.RequestQuote,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(
                            BBIcon.ButtonIcon
                        )
                    )
                }
            )

            if (storeGroup.hasShipmentTracking) {
                OrderDetailCargoCard(
                    storeGroup = storeGroup,
                    onShipmentTrackingClick = {
                        onShipmentTrackingClick(
                            storeGroup.firstTrackableOrderStoreLineId
                        )
                    }
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
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
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
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
                color = BBColors.Blue.Blue50,
                shape = BBRadius.LgShape
            )
            .padding(
                BBSpacing.CardPaddingCompact
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderDetailIconBox(
                    icon = Icons.Outlined.LocalShipping,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    iconColor = BBColors.Blue.Blue600
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = "Kargo Bilgisi",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "${storeGroup.cargoCompany} Â· ${storeGroup.cargoTrackingNumber}",
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
                        modifier = Modifier.size(
                            BBIcon.ButtonIcon
                        )
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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(
                        BBIcon.BoxLg
                    )
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
                    modifier = Modifier.size(
                        BBIcon.Feature
                    )
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "${product.quantity} adet Â· ${product.unitPriceText}",
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
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space2
        )
    ) {
        actions.forEach { action ->
            when (action) {
                OrderDetailLineAction.Cancel -> {
                    BbButton(
                        text = "İptal Et",
                        onClick = {
                            onCancelRequestClick(
                                product.orderStoreLineId,
                                orderKey
                            )
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
                            onReturnRequestClick(
                                product.orderStoreLineId,
                                orderKey
                            )
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
                                modifier = Modifier.size(
                                    BBIcon.ButtonIcon
                                )
                            )
                        }
                    )
                }

                OrderDetailLineAction.Review -> {
                    BbButton(
                        text = "DeĞerlendir",
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
                                modifier = Modifier.size(
                                    BBIcon.ButtonIcon
                                )
                            )
                        }
                    )
                }

                OrderDetailLineAction.ShipmentTracking -> {
                    BbButton(
                        text = "Kargom Nerede?",
                        onClick = {
                            onShipmentTrackingClick(
                                product.orderStoreLineId
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.LocalShipping,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
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

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            OrderDetailSectionTitle(
                title = "Ödeme Özeti",
                subtitle = "Siparişe ait tutar daĞılımı"
            )

            OrderDetailAmountRow(
                title = "Ürün Toplamı",
                value = order.productTotalText
            )

            OrderDetailAmountRow(
                title = "Kargo",
                value = order.cargoText
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            OrderDetailSectionTitle(
                title = "Sipariş İşlemleri",
                subtitle = "Sözleşme ve destek Aksiyonları"
            )

            BbButton(
                text = "Mesafeli Satış Sözleşmesi",
                onClick = onContractClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.RequestQuote,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(
                            BBIcon.ButtonIcon
                        )
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
                        modifier = Modifier.size(
                            BBIcon.ButtonIcon
                        )
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
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OrderDetailIconBox(
            icon = step.icon,
            backgroundColor = step.color.copy(
                alpha = if (step.isCompleted) {
                    BBAlpha.High
                } else {
                    BBAlpha.Subtle
                }
            ),
            iconColor = step.color
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
private fun OrderDetailInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OrderDetailIconBox(
            icon = icon,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurface
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
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
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        ),
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
private fun OrderDetailStatusBadge(
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
private fun OrderDetailLineStatusBadge(
    text: String,
    status: OrderDetailLineStatus
) {
    val color = when (status) {
        OrderDetailLineStatus.Received -> {
            BBColors.Blue.Blue600
        }

        OrderDetailLineStatus.Preparing -> {
            BBColors.Orange.Orange600
        }

        OrderDetailLineStatus.Shipped -> {
            BBColors.Blue.Blue600
        }

        OrderDetailLineStatus.Delivered -> {
            BBColors.Green.Green600
        }

        OrderDetailLineStatus.Other -> {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
    }

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
private fun OrderDetailIconBox(
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

private fun OrderDetailProductUiModel.availableActions(): List<OrderDetailLineAction> {
    return when (status) {
        OrderDetailLineStatus.Received -> {
            listOf(
                OrderDetailLineAction.Cancel
            )
        }

        OrderDetailLineStatus.Preparing -> {
            listOf(
                OrderDetailLineAction.Support
            )
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
        it.status == OrderDetailLineStatus.Shipped ||
                it.status == OrderDetailLineStatus.Delivered
    }

private val OrderDetailStoreGroupUiModel.firstTrackableOrderStoreLineId: Long
    get() = products.firstOrNull {
        it.status == OrderDetailLineStatus.Shipped ||
                it.status == OrderDetailLineStatus.Delivered
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
        statusColor = BBColors.Orange.Orange600,
        productCountText = "3 ürün",
        productTotalText = "2.400,75 â‚º",
        cargoText = "50,00 â‚º",
        totalText = "2.450,75 â‚º",
        estimatedDelivery = "12 Mayıs 2026",
        addressText = "Murat Erkan Â· İstanbul / Türkiye Â· Teslimat adresi API baĞlandıĞında gerçek kullanıcı adresinden beslenecek.",
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
                        unitPriceText = "850,25 â‚º",
                        totalText = "850,25 â‚º",
                        statusText = "Sipariş Alındı",
                        status = OrderDetailLineStatus.Received
                    ),
                    OrderDetailProductUiModel(
                        orderStoreLineId = 10002L,
                        productId = 502L,
                        memberKey = "MEMBER-SECURE-502",
                        name = "Kadın Sneaker Günlük Ayakkabı",
                        quantity = 1,
                        unitPriceText = "1.200,25 â‚º",
                        totalText = "1.200,25 â‚º",
                        statusText = "Kargoya Verildi",
                        status = OrderDetailLineStatus.Shipped
                    ),
                    OrderDetailProductUiModel(
                        orderStoreLineId = 10003L,
                        productId = 503L,
                        memberKey = "MEMBER-SECURE-503",
                        name = "Pamuklu Basic Tişört",
                        quantity = 1,
                        unitPriceText = "350,25 â‚º",
                        totalText = "350,25 â‚º",
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
                color = BBColors.Green.Green600,
                isCompleted = true
            ),
            OrderDetailStepUiModel(
                title = "Ödeme Onaylandı",
                description = "Ödeme işlemi tamamlandı.",
                icon = Icons.Outlined.Payments,
                color = BBColors.Green.Green600,
                isCompleted = true
            ),
            OrderDetailStepUiModel(
                title = "Sipariş İşlemde",
                description = "Satıcı ve kargo süreçleri ürün satırlarına göre güncelleniyor.",
                icon = Icons.Outlined.LocalShipping,
                color = BBColors.Orange.Orange600,
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
