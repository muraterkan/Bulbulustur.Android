package com.bulbulustur.android.Application.Areas.b2c.Views.Basket

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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.Areas.b2c.Controllers.BasketControllerState
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(
    State: BasketControllerState = BasketControllerState(),
    favorites: List<ProductFavoriteDTO> = emptyList(),
    isFavoriteLoading: Boolean = false,
    favoriteErrorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onRetryFavoritesClick: () -> Unit = {},
    onAddFavoriteToBasketClick: (ProductFavoriteDTO) -> Unit = {},
    onCheckoutClick: (List<BasketDTO>) -> Unit = {},
    onProductClick: (BasketDTO) -> Unit = {},
    onStoreClick: (Int) -> Unit = {},
    onIncreaseQuantityClick: (BasketDTO) -> Unit = {},
    onDecreaseQuantityClick: (BasketDTO) -> Unit = {},
    onRemoveClick: (BasketDTO) -> Unit = {},
    onMoveToFavoriteClick: (BasketDTO) -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    val basketItems = State.BasketItems
    val basketLines = remember(basketItems) { basketItems.map { basket -> basket.ToBasketLineItem() } }

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
                storeLogoUrl = basketGroup.value.first().storeLogoUrl,
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
            favorites = favorites,
            isLoading = isFavoriteLoading,
            errorMessage = favoriteErrorMessage,
            onRetryClick = onRetryFavoritesClick,
            onAddFavoriteClick = onAddFavoriteToBasketClick,
            onDismiss = {
                showFavoriteSheet = false
            }
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "644233b1-e3f8-4255-a523-2de4a6b0369c", fallback = "Sepetim"),
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
                            onCheckoutClick(
                                basketItems
                            )
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
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                BasketHeaderCard(
                    lineCount = basketLines.size,
                    storeCount = storeGroups.size
                )
            }

            if (basketLines.isEmpty()) {
                item { BasketEmptyCard() }

                if (favorites.isNotEmpty() || isFavoriteLoading || !favoriteErrorMessage.isNullOrBlank()) {
                    item {
                        BasketFavoriteShortcutCard(
                            onClick = {
                                showFavoriteSheet = true
                            }
                        )
                    }
                }

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
                        storeGroup =
                            storeGroup,
                        onStoreClick = {
                            onStoreClick(
                                storeGroup.storeId
                            )
                        },
                        onProductClick = { line ->
                            onProductClick(
                                line.source
                            )
                        },
                        onIncreaseQuantityClick = { line ->
                            onIncreaseQuantityClick(
                                line.source
                            )
                        },
                        onDecreaseQuantityClick = { line ->
                            onDecreaseQuantityClick(
                                line.source
                            )
                        },
                        onRemoveClick = { line ->
                            onRemoveClick(
                                line.source
                            )
                        },
                        onMoveToFavoriteClick = { line ->
                            onMoveToFavoriteClick(
                                line.source
                            )
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasketIconBox(
                icon = Icons.Outlined.ShoppingBasket,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = if (lineCount > 0) "$lineCount ürün sepette" else BBLocalization.Current.Get(key = "2617a5c2-1dca-464d-b4d4-f44ad5a5b7ad", fallback = ""),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = if (lineCount > 0) {
                        "$storeCount mağazadan gönderim yapılacak"
                    } else {
                        BBLocalization.Current.Get(key = "507ef499-3ec4-4197-98b3-66c6a6402a33", fallback = "Ürün Keşfine dönüp sepetini doldurabilirsin.")
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasketIconBox(
                icon = Icons.Outlined.ConfirmationNumber,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "b2007b6f-06c1-4ddf-b73e-2f6da5361af3", fallback = "Kupon ve İndirimler"),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.surface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = if (couponApplied) {
                        "WELCOME75 kuponu uygulandı."
                    } else {
                        BBLocalization.Current.Get(key = "b9d14d51-71dc-48f4-88eb-d2a4e85f7496", fallback = "İndirim kodu ekle veya kullanılabilir kuponlarını görüntüle.")
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Action)
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasketIconBox(
                icon = Icons.Outlined.FavoriteBorder,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = MaterialTheme.colorScheme.onSurface
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "55923458-0616-4032-931c-1b5b1bcce9eb", fallback = "Favorilerimden Sepete Ekle"),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = BBLocalization.Current.Get(key = "7dfdda4a-d0ee-4f95-b744-ed44e127308f", fallback = "Daha önce beğendiğin ürünleri hızlıca sepete aktar."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Action)
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
    onRemoveClick: (BasketLineItem) -> Unit,
    onMoveToFavoriteClick: (BasketLineItem) -> Unit
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
                    },
                    onMoveToFavoriteClick = {
                        onMoveToFavoriteClick(
                            line
                        )
                    }
                )

                if (index != storeGroup.lines.lastIndex) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
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
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(BBIcon.BoxMd).background(color = MaterialTheme.colorScheme.surfaceVariant, shape = BBRadius.LgShape),
            contentAlignment = Alignment.Center
        ) {
            if (storeGroup.storeLogoUrl.isNotBlank()) {
                AsyncImage(
                    model = storeGroup.storeLogoUrl,
                    contentDescription = storeGroup.storeName,
                    modifier = Modifier.fillMaxSize().padding(BBSpacing.Space1),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text(text = storeGroup.storeLogoText, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            }
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.Inline)
                )

                Text(
                    text = storeGroup.storeName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalShipping,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBIcon.Inline)
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
            modifier = Modifier.size(BBIcon.Action)
        )
    }
}

@Composable
private fun BasketLineCard(
    line: BasketLineItem,
    onProductClick: () -> Unit,
    onIncreaseQuantityClick: () -> Unit,
    onDecreaseQuantityClick: () -> Unit,
    onRemoveClick: () -> Unit,
    onMoveToFavoriteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .clickable { onProductClick() }
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(BBSpacing.Space16).background(color = MaterialTheme.colorScheme.surface, shape = BBRadius.LgShape),
            contentAlignment = Alignment.Center
        ) {
            if (line.imageUrl.isNotBlank()) {
                AsyncImage(
                    model = line.imageUrl,
                    contentDescription = line.productName,
                    modifier = Modifier.fillMaxSize().padding(BBSpacing.Space1),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text(text = line.imageText, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
                color = BBColors.Yellow.Yellow800
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
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
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.Inline)
                    )

                    Text(
                        text = BBLocalization.Current.Get(key = "e38050df-62e1-4b83-97ee-2643ad73390c", fallback = "Sil"),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text =
                            BBLocalization.Current.Get(key = "da76c00e-61ee-46da-9690-5a8d15c7ce6e", fallback = "Favoriye Taşı"),
                        style =
                            MaterialTheme.typography.labelSmall,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
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
            .size(BBIcon.BoxSm)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = BBRadius.IconBoxSoft
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(BBIcon.Inline)
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "c3894b16-f66b-47f2-8853-11c6d9084bdf", fallback = "Sepet Özeti"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            BasketSummaryRow(BBLocalization.Current.Get(key = "9ca1b3ac-05ef-462c-a4ef-e4bcd4b4b11b", fallback = "Ürün Toplamı"), productTotalText)
            BasketSummaryRow(BBLocalization.Current.Get(key = "8fa1207a-2a06-4bdb-936b-f7da848e0f72", fallback = "Kargo"), cargoTotalText)
            BasketSummaryRow(BBLocalization.Current.Get(key = "9dd8d854-ca26-4660-bcb3-b7ec8e3f458b", fallback = "İndirim"), discountTotalText)

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            BasketSummaryRow(

                title = BBLocalization.Current.Get(key = "0234baa2-519d-42ae-a2e8-760ebc0a1d06", fallback = "Ödenecek Tutar"),
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
            color = if (isStrong) {
                BBColors.Yellow.Yellow800
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
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = BBSpacing.Space1,
        shadowElevation = BBSpacing.Space2
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BBSpacing.PageHorizontal,
                    vertical = BBSpacing.Space3
                ),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "e736c25f-c944-4f52-a206-819f93d64a29", fallback = "Toplam"),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = payableTotalText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = BBRadius.PillShape
                    )
                    .clickable { onCheckoutClick() }
                    .padding(
                        horizontal = BBSpacing.Space5,
                        vertical = BBSpacing.Space3
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "e07ad224-7f9f-4399-8920-8f0072f48d66", fallback = "Siparişi Tamamla"),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            BasketIconBox(
                icon = Icons.Outlined.ShoppingBasket,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Text(
                text = BBLocalization.Current.Get(key = "2617a5c2-1dca-464d-b4d4-f44ad5a5b7ad", fallback = ""),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "507ef499-3ec4-4197-98b3-66c6a6402a33", fallback = "Ürün Keşfine dönüp sepetini doldurabilirsin."),
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "fc82ed32-9912-4b06-a0a8-6b18c5b59bc1", fallback = "Alıcı Koruması"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                BasketProtectionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Security,
                    title = BBLocalization.Current.Get(key = "f3579e87-ed23-48b3-bcf7-b1eae15a5c50", fallback = "Güvenli ödeme")
                )

                BasketProtectionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.LocalShipping,
                    title = BBLocalization.Current.Get(key = "74ca1228-4df5-45be-82a3-43a8ea25d7a8", fallback = "Lojistik destek")
                )

                BasketProtectionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Wallet,
                    title = BBLocalization.Current.Get(key = "e23b24ca-7b5a-4151-8a18-4ce8df96d94a", fallback = "Kolay iade")
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
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "b2007b6f-06c1-4ddf-b73e-2f6da5361af3", fallback = "Kupon ve İndirimler"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            OutlinedTextField(
                value = couponCode,
                onValueChange = { couponCode = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = BBLocalization.Current.Get(key = "dc05e804-da03-4004-9d31-a2fb22475bb4", fallback = "İndirim kodu"))
                },
                singleLine = true,
                shape = BBRadius.Input
            )

            BbButton(
                text = if (couponApplied) "Kupon Uygulandı" else BBLocalization.Current.Get(key = "d7620322-834c-4750-97aa-95fb812b1bdc", fallback = "Kuponu Uygula"),
                onClick = onApplyCouponClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )

            BasketCouponOption(
                title = "WELCOME75",
                description = BBLocalization.Current.Get(key = "4db7a44f-e02d-4241-930f-a49fa767ec44", fallback = "Sepette 75 TL indirim"),
                onClick = onApplyCouponClick
            )

            BasketCouponOption(
                title = "KARGO50",
                description = BBLocalization.Current.Get(key = "5a535861-862b-4088-96af-dc7a41dea2e1", fallback = "Seçili mağazalarda kargo indirimi"),
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
                shape = BBRadius.LgShape
            )
            .clickable { onClick() }
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasketIconBox(
            icon = Icons.Outlined.ConfirmationNumber,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconColor = BBColors.Yellow.Yellow800
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
    favorites: List<ProductFavoriteDTO>,
    isLoading: Boolean,
    errorMessage: String?,
    onRetryClick: () -> Unit,
    onAddFavoriteClick: (ProductFavoriteDTO) -> Unit,
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "55923458-0616-4032-931c-1b5b1bcce9eb", fallback = "Favorilerimden Sepete Ekle"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(BBSpacing.Space20),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                    ) {
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )

                        BbButton(
                            text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"),
                            onClick = onRetryClick,
                            variant = BbButtonVariant.Primary,
                            size = BbButtonSize.Small
                        )
                    }
                }

                favorites.isEmpty() -> {
                    Text(
                        text = BBLocalization.Current.Get(key = "72e32e3b-2153-4f5f-a506-d70cfad97fae", fallback = "Henüz sepete ekleyebileceğin bir favorin bulunmuyor."),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                else -> {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                        contentPadding = PaddingValues(end = BBSpacing.PageHorizontal)
                    ) {
                        items(
                            items = favorites,
                            key = { favorite -> favorite.FavoriteId }
                        ) { favorite ->
                            BasketFavoriteSuggestionCard(
                                modifier = Modifier.fillParentMaxWidth(0.42f),
                                favorite = favorite,
                                onAddFavoriteClick = {
                                    onAddFavoriteClick(favorite)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BasketFavoriteSuggestionCard(
    modifier: Modifier = Modifier,
    favorite: ProductFavoriteDTO,
    onAddFavoriteClick: () -> Unit
) {
    val currencySymbol = favorite.CurrencySymbol.ifBlank { "₺" }
    val imageUrl = ImageUrlResolver.Resolve(imagePath = favorite.DefaultPicture.ifBlank { favorite.Picture })
    val imageText = favorite.ProductName
        .trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .mapNotNull { word -> word.firstOrNull() }
        .joinToString("")
        .uppercase()
        .ifBlank { "Ü" }

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(BBSpacing.Space20).background(color = MaterialTheme.colorScheme.surface, shape = BBRadius.LgShape),
            contentAlignment = Alignment.Center
        ) {
            if (imageUrl.isNotBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = favorite.ProductName,
                    modifier = Modifier.fillMaxSize().padding(BBSpacing.Space1),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text(text = imageText, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Text(
            text = favorite.ProductName.ifBlank { BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = "") },
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )

        Text(
            text = FormatBasketPrice(
                value = favorite.Price,
                currencySymbol = currencySymbol
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.Yellow.Yellow800,
            fontWeight = FontWeight.Bold
        )

        BbButton(
            text = BBLocalization.Current.Get(key = "9a748489-8d57-4bc5-becc-0937717d80df", fallback = "Sepete Ekle"),
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
    val storeLogoUrl: String,
    val cargoText: String,
    val lines: List<BasketLineItem>
)

data class BasketLineItem(
    val id: Int,
    val productId: Int,
    val variantId: Int,
    val priceId: Int,
    val storeId: Int,
    val storeName: String,
    val storeLogoText: String,
    val storeLogoUrl: String,
    val productName: String,
    val variantText: String,
    val priceText: String,
    val priceValue: Double,
    val discountValue: Double,
    val quantity: Int,
    val cargoText: String,
    val cargoPriceValue: Double,
    val imageText: String,
    val imageUrl: String,
    val source: BasketDTO
)

private fun BasketDTO.ToBasketLineItem(): BasketLineItem {
    val resolvedUnitPrice = UnitPrice.takeIf { it > 0.0 } ?: if (Quantity > 0) TotalPrice / Quantity else TotalPrice
    val resolvedCurrencySymbol = CurrencySymbol.takeIf { it.isNotBlank() } ?: "₺"
    val resolvedVariantText = listOfNotNull(Color.takeIf { it.isNotBlank() }, Size.takeIf { it.isNotBlank() }).joinToString(separator = " · ")
    val resolvedImageText = ProductName.trim().split(" ").filter { it.isNotBlank() }.take(2).mapNotNull { word -> word.firstOrNull() }.joinToString("").uppercase().ifBlank { "Ü" }
    val resolvedStoreLogoText = Store.trim().split(" ").filter { it.isNotBlank() }.take(2).mapNotNull { word -> word.firstOrNull() }.joinToString("").uppercase().ifBlank { "M" }
    val resolvedImageUrl = ImageUrlResolver.Resolve(imagePath = DefaultPicture.ifBlank { Picture })
    val resolvedStoreLogoUrl = ImageUrlResolver.Resolve(imagePath = StoreLogo)

    return BasketLineItem(
        id = BasketId,
        productId = ProductId,
        variantId = VariantId,
        priceId = PriceId,
        storeId = StoreId,
        storeName = Store.ifBlank { BBLocalization.Current.Get(key = "a4bd79dd-e7ee-4407-9e7d-00582840c43a", fallback = "Mağaza") },
        storeLogoText = resolvedStoreLogoText,
        storeLogoUrl = resolvedStoreLogoUrl,
        productName = ProductName.ifBlank { BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = "") },
        variantText = resolvedVariantText.ifBlank { BBLocalization.Current.Get(key = "081fb0ca-4f68-4277-9ca0-028d1e9d147f", fallback = "Standart") },
        priceText = FormatBasketPrice(value = resolvedUnitPrice, currencySymbol = resolvedCurrencySymbol),
        priceValue = resolvedUnitPrice,
        discountValue = DiscountAmount,
        quantity = Quantity,
        cargoText = BBLocalization.Current.Get(key = "0c7b108c-136d-4c5a-add7-bfbbba981634", fallback = "Kargo bilgisi ödeme adımında netleşir."),
        cargoPriceValue = SummaryShippingCost,
        imageText = resolvedImageText,
        imageUrl = resolvedImageUrl,
        source = this
    )
}

private fun FormatBasketPrice(
    value: Double,
    currencySymbol: String
): String {
    return "$currencySymbol${String.format("%.2f", value).replace(".", ",")}"
}

private fun formatPrice(value: Double): String {
    return "₺${String.format("%.2f", value).replace(".", ",")}"
}

@Preview(showBackground = true)
@Composable
private fun BasketScreenPreview() {
    BasketScreen()
}