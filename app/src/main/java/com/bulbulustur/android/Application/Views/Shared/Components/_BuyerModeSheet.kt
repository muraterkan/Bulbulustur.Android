package com.bulbulustur.android.Application.Views.Shared.Components

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bulbulustur.android.businesslayer.Core.Enums.EBuyerMode
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyerModeSheet(
    currentMode: EBuyerMode,
    onDismissRequest: () -> Unit,
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit,
    onRfqClick: (() -> Unit)? = null
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(
            topStart = BBRadius.xxl,
            topEnd = BBRadius.xxl
        )
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "e68bab96-0c58-43b4-bd21-63e5166669a3", fallback = "Alışveriş Alanları"),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "ecffedc0-db3e-4c40-9f24-dd8b4569a9a8", fallback = "Perakende Alışveriş ve toptan ticaret arasında geçiş yapabilirsin."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BuyerModeOptionCard(
                title = BBLocalization.Current.Get(key = "adfb92da-e66b-4549-b73e-ebc51a56acbb", fallback = "Perakende Alışveriş"),
                description = BBLocalization.Current.Get(key = "77844a6f-0213-4ed0-8c63-e6717805e0f6", fallback = "Kampanyalar, ürünler ve sepet"),
                selected = currentMode == EBuyerMode.Retail,
                iconContainerColor = MaterialTheme.colorScheme.primaryContainer,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.LocalMall,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeLg)
                    )
                },
                onClick = onRetailClick
            )

            BuyerModeOptionCard(
                title = BBLocalization.Current.Get(key = "f1aa464c-b1eb-49cd-a651-3802350f4af1", fallback = "Toptan Ticaret"),
                description = BBLocalization.Current.Get(key = "b03ffae8-e9a8-4202-8a32-a4c496b42918", fallback = "RFQ, MOQ, tedarikçiler ve teklifler"),
                selected = currentMode == EBuyerMode.Wholesale,
                iconContainerColor = BBColors.Orange.Orange100,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Business,
                        contentDescription = null,
                        tint = BBColors.Orange.Orange700,
                        modifier = Modifier.size(BBIcon.SizeLg)
                    )
                },
                onClick = onWholesaleClick
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Text(
                text = BBLocalization.Current.Get(key = "4631eb59-7c46-4055-8654-e747cd4b52bd", fallback = "Hızlı toptan aksiyon"),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            BuyerRfqShortcutCard(
                onClick = {
                    if (onRfqClick != null) {
                        onRfqClick()
                    } else {
                        onWholesaleClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun BuyerModeOptionCard(
    title: String,
    description: String,
    selected: Boolean,
    iconContainerColor: Color,
    icon: @Composable () -> Unit,
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGap),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = iconContainerColor,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }

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

            if (selected) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxSm)
                        .background(
                            color = BBColors.Green.Green50,
                            shape = BBRadius.PillShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = BBColors.Green.Green600,
                        modifier = Modifier.size(BBIcon.SizeSm)
                    )
                }
            }
        }
    }
}

@Composable
private fun BuyerRfqShortcutCard(
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGap),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = BBColors.Navy.Navy900,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.RequestQuote,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.SizeLg)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "d2843cb0-ebbe-4d77-9873-62ac1d7ea9ee", fallback = "RFQ Talebi Gönder"),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "2bd761fb-1995-4f2f-9a09-028a11ca693e", fallback = "Toptan alım için tedarikçilerden son fiyat iste"),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.SizeLg)
            )
        }
    }
}

