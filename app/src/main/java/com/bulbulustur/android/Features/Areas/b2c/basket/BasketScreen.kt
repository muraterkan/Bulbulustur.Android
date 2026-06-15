package com.bulbulustur.android.Features.areas.b2c.basket

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Features.areas.b2c.components.RetailBottomNavigation
import com.bulbulustur.android.Features.areas.b2c.components.RetailBottomNavigationItem
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbAlpha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(
    onBackClick: () -> Unit = {},
    onCheckoutClick: (List<BasketLineItem>) -> Unit = {},
    onProductClick: (BasketLineItem) -> Unit = {},
    onStoreClick: (BasketStoreGroup) -> Unit = {},
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

    var showCouponSheet by remember { mutableStateOf(false) }
    var showFavoriteSheet by remember { mutableStateOf(false) }
    var couponApplied by remember { mutableStateOf(false) }

    val storeGroups = basketLines
        .groupBy { it.storeId }
        .map { basketGroup ->
            BasketStoreGroup(
                storeId = basketGroup.key,
                storeName = basketGroup.value.first().storeName,
                storeLogoText = basketGroup.value.first().storeLogoText,
                cargoText = basketGroup.value.first().cargoText,
                lines = basketGroup.value
            )
        }

    val productTotal = basketLines.sumOf { it.priceValue * it.quantity }
    val cargoTotal = storeGroups.sumOf { it.lines.first().cargoPriceValue }
    val lineDiscountTotal = basketLines.sumOf { it.discountValue * it.quantity }
    val couponDiscount = if (couponApplied) 75.0 else 0.0
    val discountTotal = lineDiscountTotal + couponDiscount
    val payableTotal = productTotal + cargoTotal - discountTotal

    if (showCouponSheet) {
        BasketCouponSheet(
            couponApplied = couponApplied,
            onApplyCouponClick = {
                couponApplied = true
                showCouponSheet = false
            },
            onDismiss = {
                showCouponSheet = false
            }
        )
    }

    if (showFavoriteSheet) {
        BasketFavoriteSheet(
            onAddFavoriteClick = {
                showFavoriteSheet = false
            },
            onDismiss = {
                showFavoriteSheet = false
            }
        )
    }

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Sepetim",
                onBackClick = onBackClick
            )
        },
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
                .background(BbColors.SurfaceMuted)
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
                BasketHeaderCard(
                    lineCount = basketLines.size,
                    storeCount = storeGroups.size
                )
            }

            if (basketLines.isEmpty()) {
                item { BasketEmptyCard() }
                item { BasketBuyerProtectionCard() }
            } else {
                item {
                    BasketCouponCard(
                        couponApplied = couponApplied,
                        onClick = {
                            showCouponSheet = true
                        }
                    )
                }

                item {
                    BasketFavoriteShortcutCard(
                        onClick = {
                            showFavoriteSheet = true
                        }
                    )
                }

                items(
                    items = storeGroups,
                    key = { storeGroup -> storeGroup.storeId }
                ) { storeGroup ->
                    BasketStoreGroupCard(
                        storeGroup = storeGroup,
                        onStoreClick = {
                            onStoreClick(storeGroup)
                        },
                        onProductClick = onProductClick,
                        onIncreaseQuantityClick = { line ->
                            val index = basketLines.indexOfFirst { it.id == line.id }

                            if (index >= 0) {
                                basketLines[index] = basketLines[index].copy(
                                    quantity = basketLines[index].quantity + 1
                                )
                            }
                        },
                        onDecreaseQuantityClick = { line ->
                            val index = basketLines.indexOfFirst { it.id == line.id }

                            if (index >= 0 && basketLines[index].quantity > 1) {
                                basketLines[index] = basketLines[index].copy(
                                    quantity = basketLines[index].quantity - 1
                                )
                            }
                        },
                        onRemoveClick = { line ->
                            basketLines.removeAll { it.id == line.id }
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

                item { BasketBuyerProtectionCard() }
            }
        }
    }
}

@Composable
private fun BasketHeaderCard(
    lineCount: Int,
    storeCount: Int
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasketIconBox(
                icon = Icons.Outlined.ShoppingBasket,
                backgroundColor = BbColors.Yellow.Yellow100,
                iconColor = BbColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = if (lineCount > 0) "$lineCount ürün sepette" else "Sepetin boş",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = if (lineCount > 0) {
                        "$storeCount mağazadan gönderim yapılacak"
                    } else {
                        "Ürün keşfine dönüp sepetini doldurabilirsin."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun BasketCouponCard(
    couponApplied: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasketIconBox(
                icon = Icons.Outlined.ConfirmationNumber,
                backgroundColor = BbColors.Yellow.Yellow100,
                iconColor = BbColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Kupon ve İndirimler",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = if (couponApplied) {
                        "WELCOME75 kuponu uygulandı."
                    } else {
                        "İndirim kodu ekle veya kullanılabilir kuponlarını görüntüle."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BbIcon.Action)
            )
        }
    }
}

@Composable
private fun BasketFavoriteShortcutCard(
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasketIconBox(
                icon = Icons.Outlined.FavoriteBorder,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = MaterialTheme.colorScheme.onSurface
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Favorilerinden Sepete Ekle",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Daha önce beğendiğin ürünleri hızlıca sepete aktar.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BbIcon.Action)
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
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            BasketStoreHeader(
                storeGroup = storeGroup,
                onStoreClick = onStoreClick
            )

            storeGroup.lines.forEachIndexed { index, line ->
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

                if (index != storeGroup.lines.lastIndex) {
                    HorizontalDivider(color = BbColors.Border)
                }
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
            .clickable { onStoreClick() },
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BbRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = storeGroup.storeLogoText,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BbIcon.Inline)
                )

                Text(
                    text = storeGroup.storeName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalShipping,
                    contentDescription = null,
                    tint = BbColors.Yellow.Yellow800,
                    modifier = Modifier.size(BbIcon.Inline)
                )

                Text(
                    text = storeGroup.cargoText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BbIcon.Action)
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
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .clickable { onProductClick() }
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbSpacing.Space16)
                .background(
                    color = BbColors.Surface,
                    shape = BbRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = line.imageText,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = line.productName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2
            )

            Text(
                text = line.variantText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )

            Text(
                text = line.priceText,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.Yellow.Yellow800
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasketQuantityButton(
                    icon = Icons.Outlined.Remove,
                    onClick = onDecreaseQuantityClick
                )

                Text(
                    text = line.quantity.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                BasketQuantityButton(
                    icon = Icons.Outlined.Add,
                    onClick = onIncreaseQuantityClick
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.clickable { onRemoveClick() },
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BbIcon.Inline)
                    )

                    Text(
                        text = "Sil",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun BasketQuantityButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxSm)
            .background(
                color = BbColors.Surface,
                shape = BbRadius.IconBoxSoft
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(BbIcon.Inline)
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
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Text(
                text = "Sepet Özeti",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            BasketSummaryRow("Ürün Toplamı", productTotalText)
            BasketSummaryRow("Kargo", cargoTotalText)
            BasketSummaryRow("İndirim", discountTotalText)

            HorizontalDivider(color = BbColors.Border)

            BasketSummaryRow(
                title = "Ödenecek Tutar",
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
            color = if (isStrong) {
                BbColors.Yellow.Yellow800
            } else {
                MaterialTheme.colorScheme.onSurface
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
        color = BbColors.TextStrong,
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Toplam",
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.White.copy(alpha = BbAlpha.Muted)
                )

                Text(
                    text = payableTotalText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Primary
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = BbColors.Primary,
                        shape = BbRadius.PillShape
                    )
                    .clickable { onCheckoutClick() }
                    .padding(
                        horizontal = BbSpacing.Space5,
                        vertical = BbSpacing.Space3
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Siparişi Tamamla",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }
        }
    }
}

@Composable
private fun BasketEmptyCard() {
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
            BasketIconBox(
                icon = Icons.Outlined.ShoppingBasket,
                backgroundColor = BbColors.Yellow.Yellow100,
                iconColor = BbColors.Yellow.Yellow800
            )

            Text(
                text = "Sepetin boş",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Ürün keşfine dönüp sepetini doldurabilirsin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BasketBuyerProtectionCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Text(
                text = "Alıcı Koruması",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                BasketProtectionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Security,
                    title = "Güvenli ödeme"
                )

                BasketProtectionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.LocalShipping,
                    title = "Lojistik destek"
                )

                BasketProtectionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Wallet,
                    title = "Kolay iade"
                )
            }
        }
    }
}

@Composable
private fun BasketProtectionItem(
    modifier: Modifier,
    icon: ImageVector,
    title: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        BasketIconBox(
            icon = icon,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            iconColor = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun BasketIconBox(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BasketCouponSheet(
    couponApplied: Boolean,
    onApplyCouponClick: () -> Unit,
    onDismiss: () -> Unit
) {
    var couponCode by remember {
        mutableStateOf(if (couponApplied) "WELCOME75" else "")
    }

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
            Text(
                text = "Kupon ve İndirimler",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            OutlinedTextField(
                value = couponCode,
                onValueChange = { couponCode = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "İndirim kodu")
                },
                singleLine = true,
                shape = BbRadius.Input
            )

            BbButton(
                text = if (couponApplied) "Kupon Uygulandı" else "Kuponu Uygula",
                onClick = onApplyCouponClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )

            BasketCouponOption(
                title = "WELCOME75",
                description = "Sepette 75 TL indirim",
                onClick = onApplyCouponClick
            )

            BasketCouponOption(
                title = "KARGO50",
                description = "Seçili mağazalarda kargo indirimi",
                onClick = onApplyCouponClick
            )
        }
    }
}

@Composable
private fun BasketCouponOption(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .clickable { onClick() }
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasketIconBox(
            icon = Icons.Outlined.ConfirmationNumber,
            backgroundColor = BbColors.Yellow.Yellow100,
            iconColor = BbColors.Yellow.Yellow800
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BasketFavoriteSheet(
    onAddFavoriteClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val favorites = getFavoriteSuggestions()

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
            Text(
                text = "Favorilerinden Sepete Ekle",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                contentPadding = PaddingValues(end = BbSpacing.PageHorizontal)
            ) {
                items(
                    items = favorites,
                    key = { favorite -> favorite.name }
                ) { favorite ->
                    BasketFavoriteSuggestionCard(
                        modifier = Modifier.fillParentMaxWidth(0.42f),
                        favorite = favorite,
                        onAddFavoriteClick = onAddFavoriteClick
                    )
                }
            }
        }
    }
}

@Composable
private fun BasketFavoriteSuggestionCard(
    modifier: Modifier = Modifier,
    favorite: BasketFavoriteSuggestion,
    onAddFavoriteClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(BbSpacing.Space20)
                .background(
                    color = BbColors.Surface,
                    shape = BbRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = favorite.imageText,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = favorite.name,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )

        Text(
            text = favorite.priceText,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.Yellow.Yellow800,
            fontWeight = FontWeight.Bold
        )

        BbButton(
            text = "Sepete Ekle",
            onClick = onAddFavoriteClick,
            modifier = Modifier.fillMaxWidth(),
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Small
        )
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

private data class BasketFavoriteSuggestion(
    val name: String,
    val priceText: String,
    val imageText: String
)

private fun getFavoriteSuggestions(): List<BasketFavoriteSuggestion> {
    return listOf(
        BasketFavoriteSuggestion(
            name = "Ortobella deri terlik",
            priceText = "₺849,90",
            imageText = "F1"
        ),
        BasketFavoriteSuggestion(
            name = "Pamuklu basic tişört",
            priceText = "₺349,90",
            imageText = "F2"
        ),
        BasketFavoriteSuggestion(
            name = "Kışlık bot koleksiyonu",
            priceText = "₺1.249,00",
            imageText = "F3"
        ),
        BasketFavoriteSuggestion(
            name = "Rahat taban günlük ayakkabı",
            priceText = "₺749,90",
            imageText = "F4"
        )
    )
}

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

@Preview(showBackground = true)
@Composable
private fun BasketScreenPreview() {
    BasketScreen()
}