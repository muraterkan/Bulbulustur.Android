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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedStoreDTO

@Composable
fun FollowedStoreListScreen(
    stores: List<MemberFollowedStoreDTO> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onStoreClick: (storeId: Int) -> Unit = {},
    onUnfollowStoreClick: (followedStoreId: Int) -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Takip Edilen Mağazalar",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && stores.isEmpty() -> {
                FollowedStoreLoadingState(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(innerPadding)
                )
            }

            !errorMessage.isNullOrBlank() && stores.isEmpty() -> {
                FollowedStoreErrorState(
                    message = errorMessage,
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(innerPadding)
                )
            }

            else -> {
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
                    if (!errorMessage.isNullOrBlank()) {
                        item(key = "followed-store-inline-error") {
                            FollowedStoreInlineError(
                                message = errorMessage,
                                onRetryClick = onRetryClick
                            )
                        }
                    }

                    if (stores.isEmpty()) {
                        item(key = "followed-store-empty") {
                            FollowedStoreEmptyState()
                        }
                    }

                    items(
                        items = stores,
                        key = { store -> store.MemberFollowedStoreId }
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
    }
}

@Composable
private fun FollowedStoreCard(
    store: MemberFollowedStoreDTO,
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
                    text = store.Store.orEmpty().ifBlank {
                        "Mağaza #${store.StoreId}"
                    },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = store.followedStoreDescription(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(BBSpacing.Space1)
                )

                BbButton(
                    text = "Mağaza Profilini Görüntüle",
                    onClick = {
                        onStoreClick(store.StoreId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = "Takipten Çıkar",
                    onClick = {
                        onUnfollowStoreClick(
                            store.MemberFollowedStoreId
                        )
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
    store: MemberFollowedStoreDTO
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.8f)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(BBSpacing.Space20)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = BBRadius.MdShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = store.storeLogoText(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
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
                color = MaterialTheme.colorScheme.primaryContainer,
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
            color = BBColors.Yellow.Yellow800,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun FollowedStoreLoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun FollowedStoreInlineError(
    message: String,
    onRetryClick: () -> Unit
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
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )

            BbButton(
                text = "Tekrar Dene",
                onClick = onRetryClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun FollowedStoreErrorState(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BbCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.PageHorizontal),
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
                    text = "Mağazalar Alınamadı",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                BbButton(
                    text = "Tekrar Dene",
                    onClick = onRetryClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )
            }
        }
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
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Henüz takip ettiğiniz mağaza bulunmuyor. Mağaza profillerini takip ederek burada hızlı erişim listesi oluşturabilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun StoreEmptyIconBox() {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxLg)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Storefront,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.SizeLg)
        )
    }
}

private fun MemberFollowedStoreDTO.storeLogoText(): String {
    val words = Store
        .trim()
        .split(Regex("\\s+"))
        .filter { word -> word.isNotBlank() }

    if (words.isEmpty()) {
        return "M"
    }

    if (words.size == 1) {
        return words.first()
            .take(2)
            .uppercase()
    }

    return words
        .take(2)
        .mapNotNull { word ->
            word.firstOrNull()
        }
        .joinToString("")
        .uppercase()
}

private fun MemberFollowedStoreDTO.followedStoreDescription(): String {
    return when {
        Company.orEmpty().isNotBlank() ->
            Company

        FollowedStoresType.orEmpty().isNotBlank() ->
            FollowedStoresType

        else ->
            "Takip ettiğiniz mağaza"
    }
}