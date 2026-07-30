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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.CircularProgressIndicator
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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreLineDTO

@Composable
fun OrderShipmentTrackingScreen(
    cargoTrackingNumber: Int,
    memberId: Int,
    onBackClick: () -> Unit = {},
    controller: OrderController = viewModel()
) {
    val state by controller.State.collectAsStateWithLifecycle()

    LaunchedEffect(cargoTrackingNumber, memberId) {
        if (cargoTrackingNumber > 0 && memberId > 0) {
            controller.GetOrderTrackingAsync(
                cargoTrackingNumber = cargoTrackingNumber,
                memberId = memberId
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
                cargoTrackingNumber <= 0 -> {
                    item {
                        OrderShipmentMessageCard(
                            title = "Kargo takip numarası bulunamadı",
                            description = "Gönderi bilgisi görüntülenemedi."
                        )
                    }
                }

                memberId <= 0 -> {
                    item {
                        OrderShipmentMessageCard(
                            title = "Oturum bilgisi bulunamadı",
                            description = "Kargo bilgisini görüntülemek için hesabınıza giriş yapmanız gerekiyor."
                        )
                    }
                }

                state.IsLoading && state.OrderTracking == null -> {
                    item {
                        OrderShipmentLoadingCard()
                    }
                }

                state.ErrorMessage != null && state.OrderTracking == null -> {
                    item {
                        OrderShipmentMessageCard(
                            title = "Kargo bilgisi alınamadı",
                            description = state.ErrorMessage.orEmpty()
                        )
                    }
                }

                state.OrderTracking == null -> {
                    item {
                        OrderShipmentMessageCard(
                            title = "Kargo kaydı bulunamadı",
                            description = "Bu takip numarasına ait gönderi bilgisi bulunamadı."
                        )
                    }
                }

                else -> {
                    val tracking = state.OrderTracking!!

                    item {
                        OrderShipmentSummaryCard(
                            tracking = tracking,
                            cargoTrackingNumber = cargoTrackingNumber
                        )
                    }

                    item {
                        OrderShipmentInfoGrid(
                            tracking = tracking,
                            cargoTrackingNumber = cargoTrackingNumber
                        )
                    }

                    item {
                        OrderShipmentDetailCard(
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
    }
}

@Composable
private fun OrderShipmentSummaryCard(
    tracking: OrderStoreLineDTO,
    cargoTrackingNumber: Int
) {
    val statusText = tracking.OrderStoreLineStatus.ifBlank {
        tracking.OrderStatus.ifBlank {
            "Kargo süreci devam ediyor"
        }
    }

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            OrderShipmentIconBox(
                icon = Icons.Outlined.LocalShipping,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = statusText,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = tracking.CargoCompany.ifBlank {
                        "Kargo firması bilgisi bulunamadı"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = tracking.OrderKey.ifBlank {
                        "Takip No: $cargoTrackingNumber"
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun OrderShipmentInfoGrid(
    tracking: OrderStoreLineDTO,
    cargoTrackingNumber: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        OrderShipmentInfoBox(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.Storefront,
            title = "Kargo Firması",
            value = tracking.CargoCompany.ifBlank { "-" }
        )

        OrderShipmentInfoBox(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.ReceiptLong,
            title = "Takip No",
            value = tracking.DeliveryNumber.ifBlank {
                cargoTrackingNumber.toString()
            }
        )
    }
}

@Composable
private fun OrderShipmentDetailCard(tracking: OrderStoreLineDTO) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            OrderShipmentSectionTitle(
                title = "Gönderi Bilgileri",
                subtitle = "Sipariş satırına ait güncel kargo bilgileri"
            )

            OrderShipmentDetailRow(
                title = BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = ""),
                value = tracking.ProductName.ifBlank {
                    "Ürün #${tracking.ProductId}"
                }
            )

            OrderShipmentDetailRow(
                title = "Sipariş",
                value = tracking.OrderKey.ifBlank {
                    tracking.OrderId.toString()
                }
            )

            OrderShipmentDetailRow(
                title = "Mağaza",
                value = tracking.CompanyName?.toString()
                    ?.takeIf { it.isNotBlank() }
                    ?: "Mağaza #${tracking.StoreId}"
            )

            OrderShipmentDetailRow(
                title = "Kargo Durumu",
                value = tracking.OrderStoreLineStatus.ifBlank {
                    tracking.OrderStatus.ifBlank { "-" }
                }
            )

            OrderShipmentDetailRow(
                title = "Teslimat Tarihi",
                value = tracking.DeliveryDate
                    ?.takeIf { it.isNotBlank() }
                    ?: "-"
            )

            OrderShipmentDetailRow(
                title = "Adet",
                value = tracking.Quantity.toString()
            )
        }
    }
}

@Composable
private fun OrderShipmentDetailRow(
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
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            OrderShipmentIconBox(
                icon = Icons.Outlined.Info,
                backgroundColor = BBColors.Blue.Blue50,
                iconColor = BBColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Kargo bilgisi hakkında",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Kargo durumu sipariş ve taşıyıcı firma kayıtlarına göre güncellenir.",
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
                color = MaterialTheme.colorScheme.surface,
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
                tint = BBColors.Blue.Blue600,
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
private fun OrderShipmentSectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
private fun OrderShipmentLoadingCard() {
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
                text = "Kargo bilgisi yükleniyor",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun OrderShipmentMessageCard(
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
            OrderShipmentIconBox(
                icon = Icons.Outlined.LocalShipping,
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