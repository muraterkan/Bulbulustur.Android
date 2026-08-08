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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.OrderDTO
import java.text.NumberFormat
import java.util.Locale

@Composable
fun OrderListScreen(
    memberId: Int,
    onBackClick: () -> Unit = {},
    onOrderDetailClick: (Int, String) -> Unit = { _, _ -> },
    controller: OrderController = viewModel()
) {
    val state by controller.State.collectAsStateWithLifecycle()

    LaunchedEffect(memberId) {
        if (memberId > 0) {
            controller.GetOrdersByMemberIdAsync(memberId)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "2c20dfd9-18d2-44a2-8298-95d7f91ea8e8", fallback = ""),
                subtitle = BBLocalization.Current.Get(key = "fb16cb4b-ab22-49e9-b6aa-9687cc81a41a", fallback = "Sipariş ve teslimat durumlarınızı takip edin."),
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
                memberId <= 0 -> {
                    item {
                        OrderMessageCard(
                            title = BBLocalization.Current.Get(key = "7d3eff10-e01c-4564-a290-e3542478f979", fallback = "Oturum bilgisi bulunamadı"),
                            description = BBLocalization.Current.Get(key = "23036200-215d-4425-b832-1d99ea7aa703", fallback = "Siparişlerinizi görüntülemek için hesabınıza giriş yapmanız gerekiyor.")
                        )
                    }
                }

                state.IsLoading && state.Orders.isEmpty() -> {
                    item {
                        OrderLoadingCard()
                    }
                }

                state.ErrorMessage != null && state.Orders.isEmpty() -> {
                    item {
                        OrderMessageCard(
                            title = BBLocalization.Current.Get(key = "9f159f2d-5f29-4ead-87cb-85e6c6c7e2cb", fallback = "Siparişler alınamadı"),
                            description = state.ErrorMessage.orEmpty()
                        )
                    }
                }

                state.Orders.isEmpty() -> {
                    item {
                        OrderEmptyState()
                    }
                }

                else -> {
                    item {
                        OrderListOverviewCard(totalOrderCount = state.Orders.size)
                    }

                    items(
                        items = state.Orders,
                        key = { order -> order.OrderId }
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
}

@Composable
private fun OrderListOverviewCard(totalOrderCount: Int) {
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
                OrderIconBox(
                    icon = Icons.Outlined.ReceiptLong,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "0c433c4e-215e-41ed-9e27-e87d73028b66", fallback = ""),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = BBLocalization.Current.Get(key = "a565098f-3c40-4845-a0a6-208931f663ed", fallback = "Geçmiş ve devam eden siparişleriniz."),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            OrderStatBox(
                modifier = Modifier.fillMaxWidth(),
                title = BBLocalization.Current.Get(key = "aa6c81e4-9db4-4711-a7e5-07b4cbef137f", fallback = "Toplam Sipariş"),
                value = totalOrderCount.toString()
            )
        }
    }
}

@Composable
private fun OrderCard(
    order: OrderDTO,
    onOrderDetailClick: (Int, String) -> Unit
) {
    val statusText = order.OrderStatusId.takeIf { it > 0 }?.let {
        "Sipariş Durumu #$it"
    } ?: BBLocalization.Current.Get(key = "cb303be2-afdb-4770-9baf-f58c86d5f7fe", fallback = "Sipariş Alındı")

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            onOrderDetailClick(order.OrderId, order.OrderKey)
        }
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
                OrderIconBox(
                    icon = Icons.Outlined.CalendarMonth,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "Sipariş #${order.OrderId}",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = order.InsertedDate.ifBlank { order.OrderKey },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                OrderStatusBadge(
                    text = statusText,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                OrderInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "DURUM",
                    value = statusText,
                    icon = Icons.Outlined.LocalShipping,
                    iconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OrderInfoBox(
                    modifier = Modifier.weight(1f),
                    title = "TUTAR",
                    value = order.GrandTotal.toCurrencyText(),
                    icon = Icons.Outlined.Payments,
                    iconColor = BBColors.Yellow.Yellow800
                )
            }

            OrderProductSummaryBox(order = order)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.Inline)
                    )

                    Text(
                        text = BBLocalization.Current.Get(key = "a9ba4576-144b-476d-b96a-bb8c27a54563", fallback = "Satıcı bilgileri sipariş detayında"),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BbButton(
                    text = BBLocalization.Current.Get(key = "3feff293-3c00-44ae-a23f-0a2c613ee66f", fallback = "Detay"),
                    onClick = {
                        onOrderDetailClick(order.OrderId, order.OrderKey)
                    },
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Visibility,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
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
private fun OrderProductSummaryBox(order: OrderDTO) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ReceiptLong,
            contentDescription = null,
            tint = BBColors.Blue.Blue600,
            modifier = Modifier.size(BBIcon.Action)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = order.OrderKey.ifBlank { "Sipariş #${order.OrderId}" },
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "61961c85-56dd-41af-a0c7-dc18630ed8d1", fallback = "Ürün ve mağaza bilgileri için sipariş detayını görüntüleyin."),
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
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
private fun OrderIconBox(
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
private fun OrderLoadingCard() {
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
                text = BBLocalization.Current.Get(key = "932722ec-cd40-4c87-a068-f0912d06e8af", fallback = "Siparişler yükleniyor"),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun OrderMessageCard(
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
            OrderIconBox(
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
private fun OrderEmptyState() {
    OrderMessageCard(
        title = BBLocalization.Current.Get(key = "ce5819fb-ccf1-4289-afe8-805f804a9cfa", fallback = "Sipariş bulunamadı"),
        description = BBLocalization.Current.Get(key = "d3da98ef-dc95-41b3-9f93-4920f9051e73", fallback = "Geçmiş veya devam eden siparişleriniz oluştuğunda burada listelenir.")
    )
}

private fun Double.toCurrencyText(): String {
    return NumberFormat.getCurrencyInstance(Locale.forLanguageTag("tr-TR")).format(this)
}