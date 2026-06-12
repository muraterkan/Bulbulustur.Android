package com.bulbulustur.android.features.account.favorite

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbInnerPageHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

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
        containerColor = BbColors.SurfaceMuted,
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
                .background(BbColors.SurfaceMuted)
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
                    start = BbSpacing.PageHorizontal,
                    top = BbSpacing.PageTopCompact,
                    end = BbSpacing.PageHorizontal,
                    bottom = BbSpacing.PageBottom
                ),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
            .background(BbColors.Surface)
            .padding(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.Space3
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = BbColors.Surface,
                    shape = BbRadius.LgShape
                )
                .padding(BbSpacing.CardPaddingCompact),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(
                        width = BbSpacing.Space1,
                        height = BbSpacing.Space14
                    )
                    .background(
                        color = BbColors.Primary,
                        shape = BbRadius.PillShape
                    )
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
            .background(BbColors.Surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BbSpacing.PageHorizontal),
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

        HorizontalDivider(color = BbColors.Border)
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
            .padding(top = BbSpacing.Space3),
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
                .padding(top = BbSpacing.Space3)
                .fillMaxWidth()
                .height(BbSpacing.Space1)
                .background(
                    color = if (isSelected) BbColors.TextStrong else Color.Transparent,
                    shape = BbRadius.PillShape
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space5)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FavoriteProductImagePlaceholder(mode = favorite.mode)

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
                        color = BbColors.Yellow.Yellow800
                    )
                }
            }

            FavoriteMetaBox(favorite = favorite)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                        modifier = Modifier.size(BbIcon.ButtonIcon)
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
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
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
                BbColors.Yellow.Yellow800
            } else {
                BbColors.Blue.Blue600
            },
            modifier = Modifier.size(BbIcon.Action)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
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
            .size(BbSpacing.Space16)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
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
            modifier = Modifier.size(BbIcon.Feature)
        )
    }
}

@Composable
private fun FavoriteIconBox() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space12)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.Space2),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.Feature)
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