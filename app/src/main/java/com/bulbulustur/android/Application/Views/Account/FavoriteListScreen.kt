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
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Storefront
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

@Composable
fun FavoriteListScreen(
    onBackClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onRemoveFavoriteClick: (Int) -> Unit = {},
    onAddToBasketClick: (Int) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(FavoriteTab.Retail) }

    val retailFavorites = getDemoRetailFavoriteProducts()
    val wholesaleFavorites = getDemoWholesaleFavoriteProducts()

    val visibleFavorites = when (selectedTab) {
        FavoriteTab.Retail -> retailFavorites
        FavoriteTab.Wholesale -> wholesaleFavorites
    }

    Scaffold(
        containerColor = BBColors.SurfaceMuted,
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
                .background(BBColors.SurfaceMuted)
                .padding(innerPadding)
        ) {
            FavoriteIntroCard(selectedTab = selectedTab)

            FavoriteTabs(
                selectedTab = selectedTab,
                onTabClick = { selectedTab = it }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.PageTopCompact,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.PageBottom
                ),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
            ) {
                if (visibleFavorites.isEmpty()) {
                    item {
                        FavoriteEmptyState(tab = selectedTab)
                    }
                }

                items(
                    items = visibleFavorites,
                    key = { favorite -> favorite.favoriteId }
                ) { favorite ->
                    FavoriteProductCard(
                        favorite = favorite,
                        onProductClick = onProductClick,
                        onRemoveFavoriteClick = onRemoveFavoriteClick,
                        onAddToBasketClick = onAddToBasketClick
                    )
                }
            }
        }
    }
}

@Composable
private fun FavoriteIntroCard(
    selectedTab: FavoriteTab
) {
    val title = when (selectedTab) {
        FavoriteTab.Retail -> "Perakende favorileriniz"
        FavoriteTab.Wholesale -> "Toptan favorileriniz"
    }

    val description = when (selectedTab) {
        FavoriteTab.Retail -> "Perakende alışveriş için favoriye eklediğiniz ürünleri burada görebilir, daha sonra incelemek veya sepete eklemek için hızlıca geri dönebilirsiniz."
        FavoriteTab.Wholesale -> "Toptan alışveriş için favoriye eklediğiniz ürünleri burada görebilir, teklif ve tedarik süreçlerine hızlıca dönebilirsiniz."
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BBColors.Surface)
            .padding(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.Space3
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = BBColors.Surface,
                    shape = BBRadius.LgShape
                )
                .padding(BBSpacing.CardPaddingCompact),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(
                        width = BBSpacing.Space1,
                        height = BBSpacing.Space14
                    )
                    .background(
                        color = BBColors.Primary,
                        shape = BBRadius.PillShape
                    )
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
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
}

@Composable
private fun FavoriteTabs(
    selectedTab: FavoriteTab,
    onTabClick: (FavoriteTab) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BBColors.Surface)
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

        HorizontalDivider(color = BBColors.Border)
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
            color = if (isSelected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )

        Box(
            modifier = Modifier
                .padding(top = BBSpacing.Space3)
                .fillMaxWidth()
                .height(BBSpacing.Space1)
                .background(
                    color = if (isSelected) BBColors.TextStrong else BBColors.Transparent,
                    shape = BBRadius.PillShape
                )
        )
    }
}

@Composable
private fun FavoriteProductCard(
    favorite: FavoriteProductUiModel,
    onProductClick: (Int) -> Unit,
    onRemoveFavoriteClick: (Int) -> Unit,
    onAddToBasketClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            onProductClick(favorite.productId)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space5)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FavoriteProductImagePlaceholder(mode = favorite.mode)

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
                            text = favorite.storeName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        text = favorite.productName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = favorite.priceText,
                        style = MaterialTheme.typography.titleMedium,
                        color = BBColors.Yellow.Yellow800
                    )
                }
            }

            FavoriteMetaBox(favorite = favorite)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = "İncele",
                    onClick = {
                        onProductClick(favorite.productId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = if (favorite.mode == FavoriteMode.Retail) "Sepete Ekle" else "Teklif İste",
                    onClick = {
                        onAddToBasketClick(favorite.productId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small
                )
            }

            BbButton(
                text = "Favorilerden Kaldır",
                onClick = {
                    onRemoveFavoriteClick(favorite.favoriteId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Ghost,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun FavoriteMetaBox(
    favorite: FavoriteProductUiModel
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
            imageVector = if (favorite.mode == FavoriteMode.Retail) {
                Icons.Outlined.ShoppingBag
            } else {
                Icons.Outlined.Inventory2
            },
            contentDescription = null,
            tint = if (favorite.mode == FavoriteMode.Retail) {
                BBColors.Yellow.Yellow800
            } else {
                BBColors.Blue.Blue600
            },
            modifier = Modifier.size(BBIcon.Action)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = favorite.metaTitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = favorite.metaValue,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
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
        FavoriteTab.Retail -> "Perakende ürünleri favorilerinize ekleyerek daha sonra hızlıca ulaşabilirsiniz."
        FavoriteTab.Wholesale -> "Toptan ürünleri favorilerinize ekleyerek teklif ve tedarik süreçlerine hızlıca dönebilirsiniz."
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
            imageVector = if (mode == FavoriteMode.Retail) {
                Icons.Outlined.ShoppingBag
            } else {
                Icons.Outlined.LocalShipping
            },
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
                color = BBColors.Yellow.Yellow100,
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

private fun getDemoRetailFavoriteProducts(): List<FavoriteProductUiModel> {
    return listOf(
        FavoriteProductUiModel(
            favoriteId = 1,
            productId = 101,
            productName = "Ortobella Comfort Hakiki Deri Topuk Dikeni Terlik M13",
            storeName = "Ortobella",
            priceText = "₺849,90",
            metaTitle = "PERAKENDE",
            metaValue = "Sepete eklenebilir ürün",
            mode = FavoriteMode.Retail
        ),
        FavoriteProductUiModel(
            favoriteId = 2,
            productId = 102,
            productName = "Kadın Siyah Kışlık Bot",
            storeName = "PetPlace",
            priceText = "₺1.249,00",
            metaTitle = "PERAKENDE",
            metaValue = "Stokta mevcut",
            mode = FavoriteMode.Retail
        )
    )
}

private fun getDemoWholesaleFavoriteProducts(): List<FavoriteProductUiModel> {
    return listOf(
        FavoriteProductUiModel(
            favoriteId = 101,
            productId = 501,
            productName = "Toptan Medikal Terlik Serisi - 100 Adet MOQ",
            storeName = "Ortobella",
            priceText = "Teklif ile",
            metaTitle = "TOPTAN",
            metaValue = "MOQ 100 adet · Teklif istenebilir",
            mode = FavoriteMode.Wholesale
        ),
        FavoriteProductUiModel(
            favoriteId = 102,
            productId = 502,
            productName = "Toptan Kışlık Bot Koleksiyonu",
            storeName = "Anadolu Ayakkabı",
            priceText = "Teklif ile",
            metaTitle = "TOPTAN",
            metaValue = "Tedarikçi yanıt süresi: 24 saat",
            mode = FavoriteMode.Wholesale
        )
    )
}

private enum class FavoriteTab {
    Retail,
    Wholesale
}

private enum class FavoriteMode {
    Retail,
    Wholesale
}

private data class FavoriteProductUiModel(
    val favoriteId: Int,
    val productId: Int,
    val productName: String,
    val storeName: String,
    val priceText: String,
    val metaTitle: String,
    val metaValue: String,
    val mode: FavoriteMode
)

