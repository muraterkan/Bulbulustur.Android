package com.bulbulustur.android.features.account.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun FavoriteListScreen(
    onBackClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onRemoveFavoriteClick: (Int) -> Unit = {},
    onAddToBasketClick: (Int) -> Unit = {}
) {
    val favorites = getDemoFavoriteProducts()

    AccountPageScaffold(
        title = "Favorilerim",
        kicker = "Alışveriş Listem",
        description = "Beğendiğiniz ve daha sonra incelemek istediğiniz ürünleri buradan yönetebilirsiniz.",
        backButtonText = "Hesabıma Dön",
        onBackClick = onBackClick
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            if (favorites.isEmpty()) {
                item {
                    FavoriteEmptyState()
                }
            }

            items(
                items = favorites,
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
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FavoriteProductImagePlaceholder()

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = favorite.storeName,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = favorite.productName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = favorite.priceText,
                        style = MaterialTheme.typography.titleMedium,
                        color = BbColors.Yellow.Yellow800
                    )
                }
            }

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
                    text = "Sepete Ekle",
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
                variant = BbButtonVariant.Danger,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun FavoriteEmptyState() {
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
                text = "Henüz favori ürününüz yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Ürünleri favorilerinize ekleyerek daha sonra hızlıca ulaşabilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FavoriteProductImagePlaceholder() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space16)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Ürün",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
        Text(
            text = "♥",
            style = MaterialTheme.typography.headlineSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

private fun getDemoFavoriteProducts(): List<FavoriteProductUiModel> {
    return listOf(
        FavoriteProductUiModel(
            favoriteId = 1,
            productId = 101,
            productName = "Ortobella Comfort Hakiki Deri Topuk Dikeni Terlik M13",
            storeName = "Ortobella",
            priceText = "₺849,90"
        ),
        FavoriteProductUiModel(
            favoriteId = 2,
            productId = 102,
            productName = "Kadın Siyah Kışlık Bot",
            storeName = "PetPlace",
            priceText = "₺1.249,00"
        )
    )
}

private data class FavoriteProductUiModel(
    val favoriteId: Int,
    val productId: Int,
    val productName: String,
    val storeName: String,
    val priceText: String
)