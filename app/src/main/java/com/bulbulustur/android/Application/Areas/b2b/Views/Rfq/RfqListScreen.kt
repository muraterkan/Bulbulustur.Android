package com.bulbulustur.android.Application.Areas.b2b.Views.Rfq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextButton
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
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestDTO

@Composable
fun RfqListScreen(
    requests: List<BuyerRequestDTO>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    deletingBuyerRequestKey: String? = null,
    onBackClick: () -> Unit = {},
    onDiscoverWholesaleClick: () -> Unit = {},
    onOffersClick: (String) -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    onDeleteClick: (String) -> Unit = {},
    onCreateRfqClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    var pendingDeleteKey by remember { mutableStateOf<String?>(null) }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "d497388f-3bab-40fd-af5e-e6294231b04f", fallback = "Fiyat Teklifi İstekleri"),
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Add,
                actionContentDescription = BBLocalization.Current.Get(key = "9aa9e9a4-18b3-427b-943f-36170e46cb37", fallback = "Teklif Al"),
                onActionClick = onCreateRfqClick
            )
        },
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Basket,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        WholesaleBottomNavigationItem.Home -> onHomeClick()
                        WholesaleBottomNavigationItem.Menu -> onMenuClick()
                        WholesaleBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        WholesaleBottomNavigationItem.Basket -> onBasketClick()
                        WholesaleBottomNavigationItem.Account -> onAccountClick()
                    }
                }
            )
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
                RfqCreateActionCard(onCreateRfqClick = onCreateRfqClick)
            }

            when {
                isLoading && requests.isEmpty() -> {
                    item {
                        RfqListLoadingCard()
                    }
                }

                !errorMessage.isNullOrBlank() && requests.isEmpty() -> {
                    item {
                        RfqListErrorCard(
                            message = errorMessage,
                            onRetryClick = onRetryClick
                        )
                    }
                }

                requests.isEmpty() -> {
                    item {
                        RfqEmptyState(
                            onDiscoverWholesaleClick = onDiscoverWholesaleClick
                        )
                    }
                }

                else -> {
                    items(
                        items = requests,
                        key = { item -> item.BuyerRequestKey }
                    ) { item ->
                        RfqRequestCard(
                            item = item,
                            isDeleting = deletingBuyerRequestKey == item.BuyerRequestKey,
                            onOffersClick = onOffersClick,
                            onDetailClick = onDetailClick,
                            onDeleteClick = { pendingDeleteKey = it }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RfqCreateActionCard(onCreateRfqClick: () -> Unit) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onCreateRfqClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RfqIconBox()

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "9aa9e9a4-18b3-427b-943f-36170e46cb37", fallback = "Yeni Teklif Al"),
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "68197456-74c2-4d7d-ab5f-ffe8c3a93929", fallback = "Ürün, miktar ve ticari koşulları belirterek tedarikçilerden teklif alın."),
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Action)
            )
        }
    }
}

@Composable
private fun RfqRequestCard(
    item: BuyerRequestDTO,
    isDeleting: Boolean,
    onOffersClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            onDetailClick(item.BuyerRequestKey)
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
                RfqIconBox()

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    Text(
                        text = item.ProductName.ifBlank { BBLocalization.Current.Get(key = "d6a3a561-934c-46b0-af29-c48498e0171c", fallback = "Teklif Talebi") },
                        style = BbTypography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = item.InsertedDate.ifBlank { "-" },
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RfqSmallChip(
                            icon = Icons.Outlined.Tag,
                            text = "RFQ #${item.BuyerRequestId}"
                        )

                        RfqSmallChip(
                            icon = Icons.Outlined.LocalOffer,
                            text = item.CategoryName.ifBlank { BBLocalization.Current.Get(key = "a4848550-7fae-4a4a-b9c5-2a63b29d3c9e", fallback = "Kategori Yok") }
                        )
                    }
                }
            }

            if (item.ProductDescription.isNotBlank()) {
                Text(
                    text = item.ProductDescription,
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(key = "05ce926b-485a-4872-b758-ac3eea7a80a2", fallback = ""),
                    onClick = {
                        onOffersClick(item.BuyerRequestKey)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium,
                    enabled = !isDeleting,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.LocalOffer,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "3feff293-3c00-44ae-a23f-0a2c613ee66f", fallback = "Detay"),
                    onClick = {
                        onDetailClick(item.BuyerRequestKey)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium,
                    enabled = !isDeleting,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Visibility,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )
            }

            BbButton(
                text = BBLocalization.Current.Get(key = "49c98334-3fc8-4e86-b378-507450b813b4", fallback = "RFQ Kaydını Sil"),
                onClick = {
                    onDeleteClick(item.BuyerRequestKey)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Ghost,
                size = BbButtonSize.Medium,
                enabled = !isDeleting,
                isLoading = isDeleting,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null,
                        tint = BBColors.Red.Red500,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqIconBox() {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.MdShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.RequestQuote,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Section)
        )
    }
}

@Composable
private fun RfqSmallChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.PillShape
            )
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.SizeSm)
        )

        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RfqListLoadingCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = BBLocalization.Current.Get(key = "d497388f-3bab-40fd-af5e-e6294231b04f", fallback = "Fiyat teklifi istekleri yükleniyor..."),
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RfqListErrorCard(
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
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"),
                onClick = onRetryClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Outline,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun RfqEmptyState(onDiscoverWholesaleClick: () -> Unit) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            RfqIconBox()

            Text(
                text = BBLocalization.Current.Get(key = "0ca4cc60-47c3-4281-8f1a-684a45c2c8e5", fallback = "Teklif Talebi Bulunamadı"),
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "2fff02da-88d2-46d7-b646-b9a757139a49", fallback = "Henüz kayıtlı fiyat teklifi isteğiniz bulunmuyor."),
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "d941e312-4eb0-44eb-b9cf-d7d943697808", fallback = "Toptan Ürünleri Keşfet"),
                onClick = onDiscoverWholesaleClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}