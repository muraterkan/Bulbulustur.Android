package com.bulbulustur.android.wwwroot.shell

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
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Storefront
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
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyerModeSheet(
    currentMode: BuyerMode,
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
        containerColor = BbColors.Surface,
        shape = RoundedCornerShape(
            topStart = BbRadius.xxl,
            topEnd = BbRadius.xxl
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    start = BbSpacing.PageHorizontal,
                    end = BbSpacing.PageHorizontal,
                    bottom = BbSpacing.PageBottom
                ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            Text(
                text = "Alışveriş Alanları",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong
            )

            Text(
                text = "Perakende alışveriş ve toptan ticaret arasında geçiş yapabilirsin.",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )

            BuyerModeOptionCard(
                title = "Perakende Alışveriş",
                description = "Kampanyalar, ürünler ve sepet",
                selected = currentMode == BuyerMode.Retail,
                iconContainerColor = BbColors.PrimarySoft,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBasket,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbIcon.SizeLg)
                    )
                },
                onClick = onRetailClick
            )

            BuyerModeOptionCard(
                title = "Toptan Ticaret",
                description = "RFQ, MOQ, tedarikçiler ve teklifler",
                selected = currentMode == BuyerMode.Wholesale,
                iconContainerColor = BbColors.Orange.Orange100,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = BbColors.Orange.Orange700,
                        modifier = Modifier.size(BbIcon.SizeLg)
                    )
                },
                onClick = onWholesaleClick
            )

            HorizontalDivider(
                color = BbColors.Border
            )

            Text(
                text = "Hızlı toptan aksiyon",
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.TextStrong
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = iconContainerColor,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = BbColors.TextStrong
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            if (selected) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxSm)
                        .background(
                            color = BbColors.Green.Green50,
                            shape = BbRadius.PillShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = BbColors.Green.Green600,
                        modifier = Modifier.size(BbIcon.SizeSm)
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = BbColors.Navy.Navy900,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Description,
                    contentDescription = null,
                    tint = BbColors.Primary,
                    modifier = Modifier.size(BbIcon.SizeLg)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "RFQ Talebi Gönder",
                    style = MaterialTheme.typography.titleSmall,
                    color = BbColors.TextStrong
                )

                Text(
                    text = "Toptan alım için tedarikçilerden son fiyat iste",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.size(BbIcon.SizeLg)
            )
        }
    }
}