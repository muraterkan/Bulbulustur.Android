package com.bulbulustur.android.Application.Areas.b2c.Views.Basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CheckCircleOutline
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2c.Controllers.BasketControllerState
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.BbCommerceBottomBar
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.BbCommerceCouponSheet
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.BbCommerceOrderSummaryOverlay
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutPriceSummary
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Composable
fun BasketScreen(State: BasketControllerState = BasketControllerState(), favorites: List<ProductFavoriteDTO> = emptyList(), isFavoriteLoading: Boolean = false, favoriteErrorMessage: String? = null, onBackClick: () -> Unit = {}, onRetryFavoritesClick: () -> Unit = {}, onAddFavoriteToBasketClick: (ProductFavoriteDTO) -> Unit = {}, onCheckoutClick: (List<BasketDTO>) -> Unit = {}, onProductClick: (BasketDTO) -> Unit = {}, onStoreClick: (Int) -> Unit = {}, onIncreaseQuantityClick: (BasketDTO) -> Unit = {}, onDecreaseQuantityClick: (BasketDTO) -> Unit = {}, onRemoveClick: (BasketDTO) -> Unit = {}, onMoveToFavoriteClick: (BasketDTO) -> Unit = {}, onCouponSelected: (MemberCouponDTO) -> Unit = {}, onCouponCodeApply: (String) -> Unit = {}, onCouponCleared: () -> Unit = {}, onHomeClick: () -> Unit = {}, onMenuClick: () -> Unit = {}, onModeSwitchClick: () -> Unit = {}, onAccountClick: () -> Unit = {}) {
    val basketLoaded = State.BasketListResult != null
    val basketItems = State.BasketItems
    val basketLines = remember(basketItems) { basketItems.map { basket -> basket.ToBasketLineItem() } }
    val storeGroups = remember(basketLines) { basketLines.ToBasketStoreGroups() }
    val productTotal = basketLines.sumOf { line -> line.priceValue * line.quantity }
    val cargoTotal = storeGroups.sumOf { storeGroup -> storeGroup.lines.firstOrNull()?.cargoPriceValue ?: 0.0 }
    val lineDiscountTotal = basketLines.sumOf { line -> line.discountValue * line.quantity }
    val selectedCoupon = State.SelectedCoupon
    val appliedCoupon = selectedCoupon?.takeIf { coupon -> coupon.IsUsableForBasket(productTotal) }
    val preCouponTotal = (productTotal + cargoTotal - lineDiscountTotal).coerceAtLeast(0.0)
    val couponDiscount = appliedCoupon?.Amount?.coerceAtLeast(0.0)?.coerceAtMost(preCouponTotal) ?: 0.0
    val payableTotal = (preCouponTotal - couponDiscount).coerceAtLeast(0.0)
    val usableCouponCount = remember(State.Coupons, productTotal) { State.Coupons.count { coupon -> coupon.IsUsableForBasket(productTotal) } }

    var showCouponSheet by rememberSaveable { mutableStateOf(false) }
    var showOrderSummary by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(selectedCoupon?.MemberCouponId, productTotal) {
        if (selectedCoupon != null && !selectedCoupon.IsUsableForBasket(productTotal)) {
            onCouponCleared()
        }
    }

    if (showCouponSheet) {
        BbCommerceCouponSheet(coupons = State.Coupons, selectedCoupon = appliedCoupon, basketTotal = productTotal, isLoading = State.IsCouponLoading, errorMessage = State.CouponErrorMessage, onCouponCodeApply = onCouponCodeApply, onDismiss = { showCouponSheet = false })
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            BbInnerPageHeader(title = BBLocalization.Current.Get(key = "644233b1-e3f8-4255-a523-2de4a6b0369c", fallback = "Sepetim"), onBackClick = onBackClick)
        },
        bottomBar = {}
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
                contentPadding = PaddingValues(start = BBSpacing.PageHorizontal, top = BBSpacing.PageTopCompact, end = BBSpacing.PageHorizontal, bottom = if (basketLines.isEmpty()) BBSpacing.PageBottom else 112.dp),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
            ) {
                when {
                    !basketLoaded -> {
                        item {
                            BasketLoadingCard()
                        }
                    }

                    basketLines.isEmpty() -> {
                        item {
                            BasketEmptyCard(onHomeClick = onHomeClick)
                        }

                        item {
                            BasketFavoritesSection(favorites = favorites, isLoading = isFavoriteLoading, errorMessage = favoriteErrorMessage, onRetryClick = onRetryFavoritesClick, onAddFavoriteClick = onAddFavoriteToBasketClick)
                        }

                        item {
                            BasketBuyerProtectionCard()
                        }
                    }

                    else -> {
                        item {
                            BasketCouponCard(coupons = State.Coupons, selectedCoupon = appliedCoupon, usableCouponCount = usableCouponCount, isLoading = State.IsCouponLoading, errorMessage = State.CouponErrorMessage, onClick = { showCouponSheet = true })
                        }

                        items(items = storeGroups, key = { storeGroup -> storeGroup.storeId }) { storeGroup ->
                            BasketStoreGroupCard(
                                storeGroup = storeGroup,
                                onStoreClick = { onStoreClick(storeGroup.storeId) },
                                onProductClick = { line -> onProductClick(line.source) },
                                onIncreaseQuantityClick = { line -> onIncreaseQuantityClick(line.source) },
                                onDecreaseQuantityClick = { line -> onDecreaseQuantityClick(line.source) },
                                onRemoveClick = { line -> onRemoveClick(line.source) },
                                onMoveToFavoriteClick = { line -> onMoveToFavoriteClick(line.source) }
                            )
                        }

                        if (favorites.isNotEmpty() || isFavoriteLoading || !favoriteErrorMessage.isNullOrBlank()) {
                            item {
                                BasketFavoritesSection(favorites = favorites, isLoading = isFavoriteLoading, errorMessage = favoriteErrorMessage, onRetryClick = onRetryFavoritesClick, onAddFavoriteClick = onAddFavoriteToBasketClick)
                            }
                        }

                        item {
                            BasketBuyerProtectionCard()
                        }
                    }
                }
            }

            if (basketLines.isNotEmpty() && showOrderSummary) {
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.32f)).clickable { showOrderSummary = false })
            }

            if (basketLines.isNotEmpty()) {
                Column(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()) {
                    if (showOrderSummary) {
                        BasketOrderSummaryOverlay(
                            productTotalText = formatPrice(productTotal),
                            cargoTotalText = formatPrice(cargoTotal),
                            discountTotalText = lineDiscountTotal.takeIf { it > 0.0 }?.let { "-${formatPrice(it)}" }.orEmpty(),
                            couponTotalText = couponDiscount.takeIf { it > 0.0 }?.let { "-${formatPrice(it)}" }.orEmpty(),
                            payableTotalText = formatPrice(payableTotal)
                        )
                    }

                    BasketCheckoutBar(payableTotalText = formatPrice(payableTotal), summaryExpanded = showOrderSummary, onSummaryClick = { showOrderSummary = !showOrderSummary }, onCheckoutClick = { onCheckoutClick(basketItems) })
                }
            }
        }
    }
}

@Composable
private fun BasketCouponCard(coupons: List<MemberCouponDTO>, selectedCoupon: MemberCouponDTO?, usableCouponCount: Int, isLoading: Boolean, errorMessage: String?, onClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth().clickable { onClick() }, shape = MaterialTheme.shapes.large, color = Color.White, tonalElevation = BBSpacing.Space1, border = BorderStroke(width = 1.dp, color = if (selectedCoupon != null) MaterialTheme.colorScheme.primary.copy(alpha = 0.36f) else MaterialTheme.colorScheme.outlineVariant)) {
        Row(modifier = Modifier.fillMaxWidth().padding(BBSpacing.Space4), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.CenterVertically) {
            BasketIconBox(icon = if (selectedCoupon != null) Icons.Outlined.CheckCircleOutline else Icons.Outlined.ConfirmationNumber, backgroundColor = MaterialTheme.colorScheme.primaryContainer, iconColor = MaterialTheme.colorScheme.primary)

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(text = BBLocalization.Current.Get(key = "b2007b6f-06c1-4ddf-b73e-2f6da5361af3", fallback = "Kupon ve İndirimler"), style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)

                val description = when {
                    selectedCoupon != null -> "${selectedCoupon.CouponCode.orEmpty().ifBlank { "Kupon" }} · ${formatPrice(selectedCoupon.Amount)} indirim"
                    isLoading -> "Kuponların yükleniyor..."
                    !errorMessage.isNullOrBlank() -> errorMessage
                    usableCouponCount > 0 -> "$usableCouponCount kullanılabilir kuponun var."
                    coupons.isNotEmpty() -> "Hesabındaki kuponları görüntüle."
                    else -> BBLocalization.Current.Get(key = "b9d14d51-71dc-48f4-88eb-d2a4e85f7496", fallback = "Kupon ekle veya kullanılabilir kuponlarını görüntüle.")
                }

                Text(text = description, style = MaterialTheme.typography.bodySmall, color = if (selectedCoupon != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }

            Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.Action))
        }
    }
}

@Composable
private fun BasketStoreGroupCard(storeGroup: BasketStoreGroup, onStoreClick: () -> Unit, onProductClick: (BasketLineItem) -> Unit, onIncreaseQuantityClick: (BasketLineItem) -> Unit, onDecreaseQuantityClick: (BasketLineItem) -> Unit, onRemoveClick: (BasketLineItem) -> Unit, onMoveToFavoriteClick: (BasketLineItem) -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.large, color = Color.White, tonalElevation = BBSpacing.Space1, border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            BasketStoreHeader(storeGroup = storeGroup, onStoreClick = onStoreClick)
            BasketShippingStrip(text = storeGroup.cargoText)

            storeGroup.lines.forEachIndexed { index, line ->
                BasketLineCard(line = line, onProductClick = { onProductClick(line) }, onIncreaseQuantityClick = { onIncreaseQuantityClick(line) }, onDecreaseQuantityClick = { onDecreaseQuantityClick(line) }, onRemoveClick = { onRemoveClick(line) }, onMoveToFavoriteClick = { onMoveToFavoriteClick(line) })

                if (index != storeGroup.lines.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(horizontal = BBSpacing.Space4), color = MaterialTheme.colorScheme.outlineVariant)
                }
            }
        }
    }
}

@Composable
private fun BasketStoreHeader(storeGroup: BasketStoreGroup, onStoreClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable { onStoreClick() }.padding(horizontal = BBSpacing.Space4, vertical = BBSpacing.Space3), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.CenterVertically) {
        Surface(modifier = Modifier.size(40.dp), shape = MaterialTheme.shapes.medium, color = Color.White, border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)) {
            if (storeGroup.storeLogoUrl.isNotBlank()) {
                AsyncImage(model = storeGroup.storeLogoUrl, contentDescription = storeGroup.storeName, modifier = Modifier.fillMaxSize().padding(BBSpacing.Space1), contentScale = ContentScale.Fit)
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = storeGroup.storeLogoText, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
            }
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Outlined.Storefront, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.Inline))
                Text(text = storeGroup.storeName, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }

            Text(text = "${storeGroup.lines.size} ürün", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.Action))
    }
}

@Composable
private fun BasketShippingStrip(text: String) {
    if (text.isBlank()) {
        return
    }

    Column(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = BBSpacing.Space4, vertical = BBSpacing.Space2), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Outlined.LocalShipping, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(BBIcon.Inline))
            Text(text = text, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Composable
private fun BasketLineCard(line: BasketLineItem, onProductClick: () -> Unit, onIncreaseQuantityClick: () -> Unit, onDecreaseQuantityClick: () -> Unit, onRemoveClick: () -> Unit, onMoveToFavoriteClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(BBSpacing.Space4), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.Top) {
        Surface(modifier = Modifier.size(92.dp).clickable { onProductClick() }, shape = MaterialTheme.shapes.medium, color = Color.White, border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)) {
            if (line.imageUrl.isNotBlank()) {
                AsyncImage(model = line.imageUrl, contentDescription = line.productName, modifier = Modifier.fillMaxSize().padding(BBSpacing.Space1), contentScale = ContentScale.Fit)
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = line.imageText, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2), verticalAlignment = Alignment.Top) {
                Text(text = line.productName, modifier = Modifier.weight(1f).clickable { onProductClick() }, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface, maxLines = 2, overflow = TextOverflow.Ellipsis)

                Row(horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                    BasketCompactActionButton(icon = Icons.Outlined.FavoriteBorder, contentDescription = "Favoriye Taşı", onClick = onMoveToFavoriteClick)
                    BasketCompactActionButton(icon = Icons.Outlined.DeleteOutline, contentDescription = BBLocalization.Current.Get(key = "e38050df-62e1-4b83-97ee-2643ad73390c", fallback = "Sil"), onClick = onRemoveClick)
                }
            }

            if (line.variantText.isNotBlank()) {
                Text(text = line.variantText, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }

            Text(text = line.priceText, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                BasketQuantityControl(quantity = line.quantity, onDecreaseClick = onDecreaseQuantityClick, onIncreaseClick = onIncreaseQuantityClick)

                if (line.discountValue > 0.0) {
                    Surface(shape = BBRadius.PillShape, color = MaterialTheme.colorScheme.primaryContainer) {
                        Text(text = "Kazanç ${formatPrice(line.discountValue * line.quantity)}", modifier = Modifier.padding(horizontal = BBSpacing.Space2, vertical = BBSpacing.Space1), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

@Composable
private fun BasketCompactActionButton(icon: ImageVector, contentDescription: String, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(34.dp)) {
        Icon(imageVector = icon, contentDescription = contentDescription, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.Inline))
    }
}

@Composable
private fun BasketQuantityControl(quantity: Int, onDecreaseClick: () -> Unit, onIncreaseClick: () -> Unit) {
    Surface(shape = BBRadius.PillShape, color = Color.White, border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BasketQuantityButton(icon = Icons.Outlined.Remove, onClick = onDecreaseClick)
            Text(text = quantity.toString(), modifier = Modifier.width(30.dp), style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            BasketQuantityButton(icon = Icons.Outlined.Add, onClick = onIncreaseClick)
        }
    }
}

@Composable
private fun BasketQuantityButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(36.dp)) {
        Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(BBIcon.Inline))
    }
}

@Composable
private fun BasketCheckoutBar(payableTotalText: String, summaryExpanded: Boolean, onSummaryClick: () -> Unit, onCheckoutClick: () -> Unit) {
    BbCommerceBottomBar(totalPriceText = payableTotalText, actionText = "Sepeti Onayla", canContinue = true, summaryExpanded = summaryExpanded, onSummaryClick = onSummaryClick, onContinueClick = onCheckoutClick)
}

@Composable
private fun BasketOrderSummaryOverlay(productTotalText: String, cargoTotalText: String, discountTotalText: String, couponTotalText: String, payableTotalText: String) {
    BbCommerceOrderSummaryOverlay(summary = CheckoutPriceSummary(productTotalText = productTotalText, cargoTotalText = cargoTotalText, discountTotalText = discountTotalText, couponTotalText = couponTotalText, payableTotalText = payableTotalText))
}

@Composable
private fun BasketLoadingCard() {
    Surface(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.large, color = MaterialTheme.colorScheme.surface) {
        Box(modifier = Modifier.fillMaxWidth().height(180.dp), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun BasketEmptyCard(onHomeClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.large, color = Color.White, border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = BBSpacing.Space4, vertical = BBSpacing.Space6), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            BasketIconBox(icon = Icons.Outlined.ShoppingBasket, backgroundColor = MaterialTheme.colorScheme.primaryContainer, iconColor = MaterialTheme.colorScheme.primary)

            Text(text = "Sepetin henüz boş", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)

            Text(text = "Beğendiğin ürünleri sepete ekleyerek alışverişe başlayabilirsin.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(BBSpacing.Space1))

            BbButton(text = "Alışverişe Başla", onClick = onHomeClick, modifier = Modifier.fillMaxWidth(), variant = BbButtonVariant.Primary, size = BbButtonSize.Medium)
        }
    }
}

@Composable
private fun BasketBuyerProtectionCard() {
    Surface(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.large, color = Color.White, border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)) {
        Column(modifier = Modifier.fillMaxWidth().padding(BBSpacing.Space4), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
            Text(text = BBLocalization.Current.Get(key = "fc82ed32-9912-4b06-a0a8-6b18c5b59bc1", fallback = "Alıcı Koruması"), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
                BasketProtectionItem(modifier = Modifier.weight(1f), icon = Icons.Outlined.Security, title = BBLocalization.Current.Get(key = "f3579e87-ed23-48b3-bcf7-b1eae15a5c50", fallback = "Güvenli ödeme"))
                BasketProtectionItem(modifier = Modifier.weight(1f), icon = Icons.Outlined.LocalShipping, title = BBLocalization.Current.Get(key = "74ca1228-4df5-45be-82a3-43a8ea25d7a8", fallback = "Lojistik destek"))
                BasketProtectionItem(modifier = Modifier.weight(1f), icon = Icons.Outlined.Wallet, title = BBLocalization.Current.Get(key = "e23b24ca-7b5a-4151-8a18-4ce8df96d94a", fallback = "Kolay iade"))
            }
        }
    }
}

@Composable
private fun BasketProtectionItem(modifier: Modifier, icon: ImageVector, title: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
        BasketIconBox(icon = icon, backgroundColor = MaterialTheme.colorScheme.surface, iconColor = MaterialTheme.colorScheme.onSurface)
        Text(text = title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun BasketIconBox(icon: ImageVector, backgroundColor: Color, iconColor: Color) {
    Box(modifier = Modifier.size(BBIcon.BoxMd).background(color = backgroundColor, shape = BBRadius.LgShape), contentAlignment = Alignment.Center) {
        Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(BBIcon.Action))
    }
}

@Composable
private fun BasketFavoritesSection(favorites: List<ProductFavoriteDTO>, isLoading: Boolean, errorMessage: String?, onRetryClick: () -> Unit, onAddFavoriteClick: (ProductFavoriteDTO) -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.large, color = Color.White, border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)) {
        Column(modifier = Modifier.fillMaxWidth().padding(BBSpacing.Space4), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Text(text = BBLocalization.Current.Get(key = "55923458-0616-4032-931c-1b5b1bcce9eb", fallback = "Favorilerimden Sepete Ekle"), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxWidth().height(96.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
                        Text(text = errorMessage, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error)
                        BbButton(text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"), onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
                    }
                }

                favorites.isEmpty() -> {
                    Text(text = BBLocalization.Current.Get(key = "72e32e3b-2153-4f5f-a506-d70cfad97fae", fallback = "Henüz sepete ekleyebileceğin bir favorin bulunmuyor."), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                else -> {
                    LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2), contentPadding = PaddingValues(end = BBSpacing.Space1)) {
                        items(items = favorites, key = { favorite -> favorite.FavoriteId }) { favorite ->
                            BasketFavoriteSuggestionCard(favorite = favorite, onAddFavoriteClick = { onAddFavoriteClick(favorite) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BasketFavoriteSuggestionCard(favorite: ProductFavoriteDTO, onAddFavoriteClick: () -> Unit) {
    val currencySymbol = favorite.CurrencySymbol.ifBlank { "₺" }
    val imageUrl = ImageUrlResolver.Resolve(imagePath = favorite.DefaultPicture.ifBlank { favorite.Picture })
    val imageText = favorite.ProductName.trim().split(" ").filter { it.isNotBlank() }.take(2).mapNotNull { word -> word.firstOrNull() }.joinToString("").uppercase().ifBlank { "Ü" }

    Column(modifier = Modifier.width(146.dp).border(border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant), shape = MaterialTheme.shapes.medium).background(color = Color.White, shape = MaterialTheme.shapes.medium).padding(BBSpacing.Space2), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
        Box(modifier = Modifier.fillMaxWidth().height(92.dp).background(color = Color.White, shape = MaterialTheme.shapes.medium), contentAlignment = Alignment.Center) {
            if (imageUrl.isNotBlank()) {
                AsyncImage(model = imageUrl, contentDescription = favorite.ProductName, modifier = Modifier.fillMaxSize().padding(BBSpacing.Space1), contentScale = ContentScale.Fit)
            } else {
                Text(text = imageText, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Text(text = favorite.ProductName.ifBlank { "Ürün" }, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Text(text = FormatBasketPrice(value = favorite.Price, currencySymbol = currencySymbol), style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
        BbButton(text = BBLocalization.Current.Get(key = "9a748489-8d57-4bc5-becc-0937717d80df", fallback = "Sepete Ekle"), onClick = onAddFavoriteClick, modifier = Modifier.fillMaxWidth(), variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
    }
}

data class BasketStoreGroup(val storeId: Int, val storeName: String, val storeLogoText: String, val storeLogoUrl: String, val cargoText: String, val lines: List<BasketLineItem>)

data class BasketLineItem(val id: Int, val productId: Int, val variantId: Int, val priceId: Int, val storeId: Int, val storeName: String, val storeLogoText: String, val storeLogoUrl: String, val productName: String, val variantText: String, val priceText: String, val priceValue: Double, val discountValue: Double, val quantity: Int, val cargoText: String, val cargoPriceValue: Double, val imageText: String, val imageUrl: String, val source: BasketDTO)

private fun List<BasketLineItem>.ToBasketStoreGroups(): List<BasketStoreGroup> {
    return groupBy { line -> line.storeId }.map { basketGroup ->
        val firstLine = basketGroup.value.first()
        BasketStoreGroup(storeId = basketGroup.key, storeName = firstLine.storeName, storeLogoText = firstLine.storeLogoText, storeLogoUrl = firstLine.storeLogoUrl, cargoText = firstLine.cargoText, lines = basketGroup.value)
    }
}

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
        productName = ProductName.ifBlank { "Ürün" },
        variantText = resolvedVariantText,
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

private fun MemberCouponDTO.IsUsableForBasket(basketTotal: Double): Boolean {
    if (Used == 1 || OrderId.orEmpty().isNotBlank()) {
        return false
    }

    val lastUsingDate = LastUsingDate.orEmpty().ToCouponLocalDate()

    if (lastUsingDate != null && lastUsingDate.isBefore(LocalDate.now())) {
        return false
    }

    if (UpAmount > 0.0 && basketTotal < UpAmount) {
        return false
    }

    return true
}

private fun String.ToCouponLocalDate(): LocalDate? {
    val value = trim()

    if (value.isBlank() || value.startsWith("0001-01-01") || value.startsWith("1.01.0001")) {
        return null
    }

    return runCatching { OffsetDateTime.parse(value).toLocalDate() }.getOrElse {
        runCatching { LocalDateTime.parse(value).toLocalDate() }.getOrElse {
            runCatching { LocalDate.parse(value.substringBefore("T")) }.getOrNull()
        }
    }
}

private fun FormatBasketPrice(value: Double, currencySymbol: String): String {
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
