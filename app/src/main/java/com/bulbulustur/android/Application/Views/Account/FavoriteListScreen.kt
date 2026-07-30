package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
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
import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleFavoriteDTO
import java.text.NumberFormat
import java.util.Locale

@Composable
fun FavoriteListScreen(
    retailFavorites: List<ProductFavoriteDTO> = emptyList(),
    wholesaleFavorites: List<WholesaleFavoriteDTO> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onRetailProductClick: (productId: Int, storeId: Int, variantId: Int) -> Unit = { _, _, _ -> },
    onWholesaleProductClick: (wholesaleProductId: Int) -> Unit = {},
    onRemoveRetailFavoriteClick: (favoriteId: Int) -> Unit = {},
    onRemoveWholesaleFavoriteClick: (favoriteId: Int) -> Unit = {},
    onAddRetailFavoriteToBasketClick: (favoriteId: Int) -> Unit = {},
    onRequestQuoteClick: (wholesaleProductId: Int) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(FavoriteTab.Retail) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Favorilerim",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
        ) {
            FavoriteTabs(
                selectedTab = selectedTab,
                onTabClick = { selectedTab = it }
            )

            when {
                isLoading -> {
                    FavoriteLoadingState()
                }

                !errorMessage.isNullOrBlank() -> {
                    FavoriteErrorState(
                        message = errorMessage,
                        onRetryClick = onRetryClick
                    )
                }

                selectedTab == FavoriteTab.Retail -> {
                    RetailFavoriteList(
                        favorites = retailFavorites,
                        onProductClick = onRetailProductClick,
                        onRemoveFavoriteClick = onRemoveRetailFavoriteClick,
                        onAddToBasketClick = onAddRetailFavoriteToBasketClick
                    )
                }

                else -> {
                    WholesaleFavoriteList(
                        favorites = wholesaleFavorites,
                        onProductClick = onWholesaleProductClick,
                        onRemoveFavoriteClick = onRemoveWholesaleFavoriteClick,
                        onRequestQuoteClick = onRequestQuoteClick
                    )
                }
            }
        }
    }
}

@Composable
private fun FavoriteTabs(
    selectedTab: FavoriteTab,
    onTabClick: (FavoriteTab) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BBSpacing.PageHorizontal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FavoriteTabItem(
                modifier = Modifier.weight(1f),
                title = "Perakende",
                isSelected = selectedTab == FavoriteTab.Retail,
                onClick = { onTabClick(FavoriteTab.Retail) }
            )

            FavoriteTabItem(
                modifier = Modifier.weight(1f),
                title = "Toptan",
                isSelected = selectedTab == FavoriteTab.Wholesale,
                onClick = { onTabClick(FavoriteTab.Wholesale) }
            )
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Composable
private fun FavoriteTabItem(
    modifier: Modifier,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(top = BBSpacing.Space3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = if (isSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
        )

        Box(
            modifier = Modifier
                .padding(top = BBSpacing.Space3)
                .fillMaxWidth()
                .height(BBSpacing.Space1)
                .background(
                    color = if (isSelected) MaterialTheme.colorScheme.onSurface else BBColors.Transparent,
                    shape = BBRadius.PillShape
                )
        )
    }
}

@Composable
private fun RetailFavoriteList(
    favorites: List<ProductFavoriteDTO>,
    onProductClick: (productId: Int, storeId: Int, variantId: Int) -> Unit,
    onRemoveFavoriteClick: (favoriteId: Int) -> Unit,
    onAddToBasketClick: (favoriteId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = favoriteListPadding(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
    ) {
        if (favorites.isEmpty()) {
            item {
                FavoriteEmptyState(tab = FavoriteTab.Retail)
            }
        }

        items(
            items = favorites,
            key = { favorite -> favorite.FavoriteId }
        ) { favorite ->
            RetailFavoriteCard(
                favorite = favorite,
                onProductClick = onProductClick,
                onRemoveFavoriteClick = onRemoveFavoriteClick,
                onAddToBasketClick = onAddToBasketClick
            )
        }
    }
}

@Composable
private fun WholesaleFavoriteList(
    favorites: List<WholesaleFavoriteDTO>,
    onProductClick: (wholesaleProductId: Int) -> Unit,
    onRemoveFavoriteClick: (favoriteId: Int) -> Unit,
    onRequestQuoteClick: (wholesaleProductId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = favoriteListPadding(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
    ) {
        if (favorites.isEmpty()) {
            item {
                FavoriteEmptyState(tab = FavoriteTab.Wholesale)
            }
        }

        items(
            items = favorites,
            key = { favorite -> favorite.WholesaleFavoriteId }
        ) { favorite ->
            WholesaleFavoriteCard(
                favorite = favorite,
                onProductClick = onProductClick,
                onRemoveFavoriteClick = onRemoveFavoriteClick,
                onRequestQuoteClick = onRequestQuoteClick
            )
        }
    }
}

@Composable
private fun RetailFavoriteCard(
    favorite: ProductFavoriteDTO,
    onProductClick: (productId: Int, storeId: Int, variantId: Int) -> Unit,
    onRemoveFavoriteClick: (favoriteId: Int) -> Unit,
    onAddToBasketClick: (favoriteId: Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            onProductClick(
                favorite.ProductId,
                favorite.StoreId,
                favorite.VariantId
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                FavoriteProductImagePlaceholder(mode = FavoriteMode.Retail)

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
                            text = "Mağaza #${favorite.StoreId}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        text = "Ürün #${favorite.ProductId}",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "Varyant #${favorite.VariantId}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                FavoriteRemoveButton(
                    onClick = {
                        onRemoveFavoriteClick(favorite.FavoriteId)
                    }
                )
            }

            FavoriteMetaBox(
                mode = FavoriteMode.Retail,
                title = "PERAKENDE",
                value = favorite.Note.ifBlank { "Favori perakende ürün" }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(key = "24627553-85ff-442a-b353-f12f5e4a1612", fallback = ""),
                    onClick = {
                        onProductClick(
                            favorite.ProductId,
                            favorite.StoreId,
                            favorite.VariantId
                        )
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = "Sepete Ekle",
                    onClick = {
                        onAddToBasketClick(favorite.FavoriteId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small
                )
            }
        }
    }
}

@Composable
private fun WholesaleFavoriteCard(
    favorite: WholesaleFavoriteDTO,
    onProductClick: (wholesaleProductId: Int) -> Unit,
    onRemoveFavoriteClick: (favoriteId: Int) -> Unit,
    onRequestQuoteClick: (wholesaleProductId: Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            onProductClick(favorite.WholesaleProductId)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                FavoriteProductImagePlaceholder(mode = FavoriteMode.Wholesale)

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
                            text = favorite.MemberName.orEmpty().ifBlank { "Toptan satıcı" },
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        text = favorite.ProductName.orEmpty().ifBlank { "Toptan Ürün #${favorite.WholesaleProductId}" },
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = favorite.wholesalePriceText(),
                        style = MaterialTheme.typography.titleMedium,
                        color = BBColors.Yellow.Yellow800
                    )
                }

                FavoriteRemoveButton(
                    onClick = {
                        onRemoveFavoriteClick(favorite.WholesaleFavoriteId)
                    }
                )
            }

            FavoriteMetaBox(
                mode = FavoriteMode.Wholesale,
                title = "TOPTAN",
                value = favorite.Note.ifBlank { "Teklif istenebilir" }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(key = "24627553-85ff-442a-b353-f12f5e4a1612", fallback = ""),
                    onClick = {
                        onProductClick(favorite.WholesaleProductId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = "Teklif Al",
                    onClick = {
                        onRequestQuoteClick(favorite.WholesaleProductId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small
                )
            }
        }
    }
}

@Composable
private fun FavoriteRemoveButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxSm)
            .background(
                color = MaterialTheme.colorScheme.errorContainer,
                shape = BBRadius.MdShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorilerden kaldır",
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(BBIcon.Action)
        )
    }
}

@Composable
private fun FavoriteMetaBox(
    mode: FavoriteMode,
    title: String,
    value: String
) {
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
            imageVector = if (mode == FavoriteMode.Retail) Icons.Outlined.ShoppingBag else Icons.Outlined.Inventory2,
            contentDescription = null,
            tint = if (mode == FavoriteMode.Retail) BBColors.Yellow.Yellow800 else BBColors.Blue.Blue600,
            modifier = Modifier.size(BBIcon.Action)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun FavoriteLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun FavoriteErrorState(
    message: String,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = BBSpacing.PageHorizontal,
                vertical = BBSpacing.PageTop
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Large
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )

                BbButton(
                    text = "Tekrar Dene",
                    onClick = onRetryClick,
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun FavoriteEmptyState(
    tab: FavoriteTab
) {
    val title = when (tab) {
        FavoriteTab.Retail -> "Henüz perakende favoriniz yok"
        FavoriteTab.Wholesale -> "Henüz toptan favoriniz yok"
    }

    val description = when (tab) {
        FavoriteTab.Retail -> "Perakende ürünlerini favorilerinize ekleyerek daha sonra hızlıca ulaşabilirsiniz."
        FavoriteTab.Wholesale -> "Toptan ürünleri favorilerinize ekleyerek teklif süreçlerine hızlıca dönebilirsiniz."
    }

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
            FavoriteIconBox()

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
private fun FavoriteProductImagePlaceholder(
    mode: FavoriteMode
) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space16)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (mode == FavoriteMode.Retail) Icons.Outlined.ShoppingBag else Icons.Outlined.LocalShipping,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BBIcon.Feature)
        )
    }
}

@Composable
private fun FavoriteIconBox() {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space12)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.Space2),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Feature)
        )
    }
}

private fun favoriteListPadding(): PaddingValues {
    return PaddingValues(
        start = BBSpacing.PageHorizontal,
        top = BBSpacing.PageTopCompact,
        end = BBSpacing.PageHorizontal,
        bottom = BBSpacing.PageBottom
    )
}

private fun WholesaleFavoriteDTO.wholesalePriceText(): String {
    if (Price <= 0.0) return "Teklif ile"

    val formattedPrice = NumberFormat
        .getNumberInstance(Locale("tr", "TR"))
        .format(Price)

    return "$formattedPrice ${CurrencySymbol.ifBlank { "" }}".trim()
}

private enum class FavoriteTab {
    Retail,
    Wholesale
}

private enum class FavoriteMode {
    Retail,
    Wholesale
}