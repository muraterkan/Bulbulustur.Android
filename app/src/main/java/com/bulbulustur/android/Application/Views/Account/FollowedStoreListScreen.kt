package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun FollowedStoreListScreen(
    onBackClick: () -> Unit = {},
    onStoreClick: (Int) -> Unit = {},
    onUnfollowStoreClick: (Int) -> Unit = {}
) {
    val followedStores = getDemoFollowedStores()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Takip Edilen Mağazalar",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            if (followedStores.isEmpty()) {
                item {
                    FollowedStoreEmptyState()
                }
            }

            items(
                items = followedStores,
                key = { store -> store.followedStoreId }
            ) { store ->
                FollowedStoreCard(
                    store = store,
                    onStoreClick = onStoreClick,
                    onUnfollowStoreClick = onUnfollowStoreClick
                )
            }
        }
    }
}

@Composable
private fun FollowedStoreCard(
    store: FollowedStoreUiModel,
    onStoreClick: (Int) -> Unit,
    onUnfollowStoreClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            StoreLogoArea(
                store = store
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                FollowStatusBadge()

                Text(
                    text = store.storeName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = store.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))

                BbButton(
                    text = "MaĞaza Profilini Görüntüle",
                    onClick = {
                        onStoreClick(store.storeId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = "Takipten Çıkar",
                    onClick = {
                        onUnfollowStoreClick(store.followedStoreId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Danger,
                    size = BbButtonSize.Small
                )
            }
        }
    }
}

@Composable
private fun StoreLogoArea(
    store: FollowedStoreUiModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.35f)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(BBSpacing.Space20)
                .background(
                    color = store.logoBackgroundColor,
                    shape = BBRadius.MdShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = store.logoText,
                style = MaterialTheme.typography.headlineSmall,
                color = store.logoTextColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun FollowStatusBadge() {
    Box(
        modifier = Modifier
            .background(
                color = BBColors.Yellow.Yellow100,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = "Takip Ediyor",
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun FollowedStoreEmptyState() {
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
            StoreEmptyIconBox()

            Text(
                text = "Kayıt Bulunamadı",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Henüz takip ettiĞiniz maĞaza bulunmuyor. MaĞaza profillerini takip ederek burada hızlı erişim listesi oluşturabilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StoreEmptyIconBox() {
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
        Text(
            text = "â–£",
            style = MaterialTheme.typography.headlineSmall,
            color = BBColors.Yellow.Yellow800
        )
    }
}

private fun getDemoFollowedStores(): List<FollowedStoreUiModel> {
    return listOf(
        FollowedStoreUiModel(
            followedStoreId = 1,
            storeId = 501,
            storeName = "Base & Quality Store",
            description = "Takip ettiĞiniz maĞaza",
            logoText = "B&Q",
            logoBackgroundColor = BBColors.Orange.Orange300,
            logoTextColor = BBColors.White
        ),
        FollowedStoreUiModel(
            followedStoreId = 2,
            storeId = 502,
            storeName = "Ortobella Comfort",
            description = "Takip ettiĞiniz maĞaza",
            logoText = "CİTRİX",
            logoBackgroundColor = BBColors.Green.Green700,
            logoTextColor = BBColors.White
        ),
        FollowedStoreUiModel(
            followedStoreId = 3,
            storeId = 503,
            storeName = "Ortobella Comfort",
            description = "Takip ettiĞiniz maĞaza",
            logoText = "CİTRİX",
            logoBackgroundColor = BBColors.Green.Green700,
            logoTextColor = BBColors.White
        )
    )
}

private data class FollowedStoreUiModel(
    val followedStoreId: Int,
    val storeId: Int,
    val storeName: String,
    val description: String,
    val logoText: String,
    val logoBackgroundColor: Color,
    val logoTextColor: Color
)


