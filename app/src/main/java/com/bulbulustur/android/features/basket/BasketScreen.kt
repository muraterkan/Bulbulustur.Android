package com.bulbulustur.android.features.basket

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.features.retail.components.RetailBottomNavigation
import com.bulbulustur.android.features.retail.components.RetailBottomNavigationItem
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun BasketScreen(
    onBackClick: () -> Unit = {},
    onCheckoutClick: (List<BasketLineItem>) -> Unit = {},
    onProductClick: (BasketLineItem) -> Unit = {},
    onStoreClick: (BasketStoreGroup) -> Unit = {},

    // Bottom navigation
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    val basketLines = remember {
        mutableStateListOf<BasketLineItem>().apply {
            addAll(getBasketLineItems())
        }
    }

    val storeGroups = basketLines.groupBy {
        it.storeId
    }.map { basketGroup ->
        BasketStoreGroup(
            storeId = basketGroup.key,
            storeName = basketGroup.value.first().storeName,
            storeLogoText = basketGroup.value.first().storeLogoText,
            cargoText = basketGroup.value.first().cargoText,
            lines = basketGroup.value
        )
    }

    val productTotal = basketLines.sumOf {
        it.priceValue * it.quantity
    }

    val cargoTotal = storeGroups.sumOf {
        it.lines.first().cargoPriceValue
    }

    val discountTotal = basketLines.sumOf {
        it.discountValue * it.quantity
    }

    val payableTotal = productTotal + cargoTotal - discountTotal

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (basketLines.isNotEmpty()) {
                    BasketCheckoutBar(
                        payableTotalText = formatPrice(payableTotal),
                        onCheckoutClick = {
                            onCheckoutClick(basketLines.toList())
                        }
                    )
                }

                RetailBottomNavigation(
                    selectedItem = RetailBottomNavigationItem.Basket,
                    onItemClick = { selectedItem ->
                        when (selectedItem) {
                            RetailBottomNavigationItem.Home -> onHomeClick()
                            RetailBottomNavigationItem.Menu -> onMenuClick()
                            RetailBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                            RetailBottomNavigationItem.Basket -> Unit
                            RetailBottomNavigationItem.Account -> onAccountClick()
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                BasketTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                BasketHeaderCard(
                    lineCount = basketLines.size,
                    storeCount = storeGroups.size
                )
            }

            if (basketLines.isEmpty()) {
                item {
                    BasketEmptyCard()
                }
            } else {
                items(
                    items = storeGroups,
                    key = { storeGroup ->
                        storeGroup.storeId
                    }
                ) { storeGroup ->
                    BasketStoreGroupCard(
                        storeGroup = storeGroup,
                        onStoreClick = {
                            onStoreClick(storeGroup)
                        },
                        onProductClick = onProductClick,
                        onIncreaseQuantityClick = { line ->
                            val index = basketLines.indexOfFirst {
                                it.id == line.id
                            }

                            if (index >= 0) {
                                basketLines[index] = basketLines[index].copy(
                                    quantity = basketLines[index].quantity + 1
                                )
                            }
                        },
                        onDecreaseQuantityClick = { line ->
                            val index = basketLines.indexOfFirst {
                                it.id == line.id
                            }

                            if (index >= 0 && basketLines[index].quantity > 1) {
                                basketLines[index] = basketLines[index].copy(
                                    quantity = basketLines[index].quantity - 1
                                )
                            }
                        },
                        onRemoveClick = { line ->
                            basketLines.removeAll {
                                it.id == line.id
                            }
                        }
                    )
                }

                item {
                    BasketSummaryCard(
                        productTotalText = formatPrice(productTotal),
                        cargoTotalText = formatPrice(cargoTotal),
                        discountTotalText = "-${formatPrice(discountTotal)}",
                        payableTotalText = formatPrice(payableTotal)
                    )
                }
            }
        }
    }
}

@Composable
private fun BasketTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIconSizeTopBar())
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong
            )
        }

        Spacer(
            modifier = Modifier.width(BbSpacing.Space3)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Sepetim",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Ürünleri kontrol et ve checkout adımına geç",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun BasketHeaderCard(
    lineCount: Int,
    storeCount: Int
) {
    BbCard {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space12)
                    .clip(RoundedCornerShape(BbRadius.md))
                    .background(BbColors.Success),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "S",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
            }

            Spacer(
                modifier = Modifier.width(BbSpacing.Space3)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "$lineCount ürün sepette",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = "$storeCount mağazadan gönderim yapılacak",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun BasketEmptyCard() {
    BbCard {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space16)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🧺",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Text(
                text = "Sepetin boş",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Ürün keşfine dönüp sepetini doldurabilirsin.",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun BasketStoreGroupCard(
    storeGroup: BasketStoreGroup,
    onStoreClick: () -> Unit,
    onProductClick: (BasketLineItem) -> Unit,
    onIncreaseQuantityClick: (BasketLineItem) -> Unit,
    onDecreaseQuantityClick: (BasketLineItem) -> Unit,
    onRemoveClick: (BasketLineItem) -> Unit
) {
    BbCard {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            BasketStoreHeader(
                storeGroup = storeGroup,
                onStoreClick = onStoreClick
            )

            storeGroup.lines.forEach { line ->
                BasketLineCard(
                    line = line,
                    onProductClick = {
                        onProductClick(line)
                    },
                    onIncreaseQuantityClick = {
                        onIncreaseQuantityClick(line)
                    },
                    onDecreaseQuantityClick = {
                        onDecreaseQuantityClick(line)
                    },
                    onRemoveClick = {
                        onRemoveClick(line)
                    }
                )
            }
        }
    }
}

@Composable
private fun BasketStoreHeader(
    storeGroup: BasketStoreGroup,
    onStoreClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onStoreClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbSpacing.Space8)
                .clip(RoundedCornerShape(BbRadius.md))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = storeGroup.storeLogoText,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )
        }

        Spacer(
            modifier = Modifier.width(BbSpacing.Space2)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = storeGroup.storeName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = storeGroup.cargoText,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )
        }

        Text(
            text = "›",
            style = MaterialTheme.typography.headlineSmall,
            color = BbColors.TextMuted
        )
    }
}

@Composable
private fun BasketLineCard(
    line: BasketLineItem,
    onProductClick: () -> Unit,
    onIncreaseQuantityClick: () -> Unit,
    onDecreaseQuantityClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(BbRadius.lg))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onProductClick()
            }
            .padding(BbSpacing.Space2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbSpacing.Space16)
                .clip(RoundedCornerShape(BbRadius.md))
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = line.imageText,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextMuted
            )
        }

        Spacer(
            modifier = Modifier.width(BbSpacing.Space2)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = line.productName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = BbColors.TextStrong,
                maxLines = 2
            )

            Text(
                text = line.variantText,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted,
                maxLines = 1
            )

            Text(
                text = line.priceText,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.Success
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasketQuantityButton(
                    text = "-",
                    onClick = onDecreaseQuantityClick
                )

                Text(
                    text = line.quantity.toString(),
                    modifier = Modifier.padding(horizontal = BbSpacing.Space2),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                BasketQuantityButton(
                    text = "+",
                    onClick = onIncreaseQuantityClick
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Sil",
                    modifier = Modifier.clickable {
                        onRemoveClick()
                    },
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun BasketQuantityButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space6)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )
    }
}

@Composable
private fun BasketSummaryCard(
    productTotalText: String,
    cargoTotalText: String,
    discountTotalText: String,
    payableTotalText: String
) {
    BbCard {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            BbSectionHeader(
                title = "Sepet özeti",
                subtitle = "Ödeme öncesi toplamlar"
            )

            BasketSummaryRow(
                title = "Ürün toplamı",
                value = productTotalText
            )

            BasketSummaryRow(
                title = "Kargo",
                value = cargoTotalText
            )

            BasketSummaryRow(
                title = "İndirim",
                value = discountTotalText
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BbSpacing.Divider)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            BasketSummaryRow(
                title = "Ödenecek tutar",
                value = payableTotalText,
                isStrong = true
            )
        }
    }
}

@Composable
private fun BasketSummaryRow(
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
            color = BbColors.TextStrong
        )

        Text(
            text = value,
            style = if (isStrong) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodySmall
            },
            fontWeight = FontWeight.Bold,
            color = if (isStrong) {
                BbColors.Success
            } else {
                BbColors.TextStrong
            }
        )
    }
}

@Composable
private fun BasketCheckoutBar(
    payableTotalText: String,
    onCheckoutClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = BbSpacing.Space1,
        shadowElevation = BbSpacing.Space2
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BbSpacing.PageHorizontal,
                    vertical = BbSpacing.Space3
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Toplam",
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.TextMuted
                )

                Text(
                    text = payableTotalText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Success
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(BbRadius.pill))
                    .background(BbColors.Success)
                    .clickable {
                        onCheckoutClick()
                    }
                    .padding(
                        horizontal = BbSpacing.Space6,
                        vertical = BbSpacing.Space3
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Checkout’a geç",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

data class BasketStoreGroup(
    val storeId: Int,
    val storeName: String,
    val storeLogoText: String,
    val cargoText: String,
    val lines: List<BasketLineItem>
)

data class BasketLineItem(
    val id: Int,
    val productId: Int,
    val storeId: Int,
    val storeName: String,
    val storeLogoText: String,
    val productName: String,
    val variantText: String,
    val priceText: String,
    val priceValue: Double,
    val discountValue: Double,
    val quantity: Int,
    val cargoText: String,
    val cargoPriceValue: Double,
    val imageText: String
)

private fun getBasketLineItems(): List<BasketLineItem> {
    return listOf(
        BasketLineItem(
            id = 1,
            productId = 1,
            storeId = 1,
            storeName = "Ortobella Store",
            storeLogoText = "OS",
            productName = "Kadın klasik sneaker ayakkabı",
            variantText = "Beyaz · 38 numara",
            priceText = "₺899,90",
            priceValue = 899.90,
            discountValue = 80.0,
            quantity = 1,
            cargoText = "Yurtiçi Kargo · 1-3 iş günü",
            cargoPriceValue = 49.90,
            imageText = "P1"
        ),
        BasketLineItem(
            id = 2,
            productId = 2,
            storeId = 1,
            storeName = "Ortobella Store",
            storeLogoText = "OS",
            productName = "Rahat taban günlük ayakkabı",
            variantText = "Siyah · 39 numara",
            priceText = "₺749,90",
            priceValue = 749.90,
            discountValue = 40.0,
            quantity = 1,
            cargoText = "Yurtiçi Kargo · 1-3 iş günü",
            cargoPriceValue = 49.90,
            imageText = "P2"
        ),
        BasketLineItem(
            id = 3,
            productId = 3,
            storeId = 2,
            storeName = "Moda Nova",
            storeLogoText = "MN",
            productName = "Oversize pamuklu basic tişört",
            variantText = "Lacivert · M beden",
            priceText = "₺349,90",
            priceValue = 349.90,
            discountValue = 0.0,
            quantity = 2,
            cargoText = "Yurtiçi Kargo · 2-4 iş günü",
            cargoPriceValue = 39.90,
            imageText = "P3"
        )
    )
}

private fun formatPrice(value: Double): String {
    return "₺${String.format("%.2f", value).replace(".", ",")}"
}

@Composable
private fun BbIconSizeTopBar() = BbSpacing.Space8

@Preview(showBackground = true)
@Composable
private fun BasketScreenPreview() {
    BasketScreen()
}