package com.bulbulustur.android.Application.Views.Shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.businesslayer.Core.Enums.EBuyerMode
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

enum class BuyerBottomNavigationItem {
    Home,
    Menu,
    Switch,
    Basket,
    Account
}

@Composable
fun BuyerBottomNavigation(
    mode: EBuyerMode,
    selectedItem: BuyerBottomNavigationItem,
    onItemClick: (BuyerBottomNavigationItem) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = BBColors.Surface,
        shadowElevation = BBSpacing.Space5
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.BorderThin)
                    .background(BBColors.Border)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.Space24)
                    .padding(
                        start = BBSpacing.Space2,
                        end = BBSpacing.Space2,
                        top = BBSpacing.Space3,
                        bottom = BBSpacing.Space4
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Home,
                    label = if (mode == EBuyerMode.Retail) "Ana Sayfa" else "Toptan",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.BottomNavigationIcon)
                        )
                    },
                    onClick = {
                        onItemClick(BuyerBottomNavigationItem.Home)
                    }
                )

                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Menu,
                    label = "Kategoriler",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Category,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.BottomNavigationIcon)
                        )
                    },
                    onClick = {
                        onItemClick(BuyerBottomNavigationItem.Menu)
                    }
                )

                BuyerBottomNavigationCenterAction(
                    onClick = {
                        onItemClick(BuyerBottomNavigationItem.Switch)
                    }
                )

                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Basket,
                    label = if (mode == EBuyerMode.Retail) "Sepet" else "Teklifler",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.LocalOffer,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.BottomNavigationIcon)
                        )
                    },
                    onClick = {
                        onItemClick(BuyerBottomNavigationItem.Basket)
                    }
                )

                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Account,
                    label = "Hesap",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.BottomNavigationIcon)
                        )
                    },
                    onClick = {
                        onItemClick(BuyerBottomNavigationItem.Account)
                    }
                )
            }
        }
    }
}

@Composable
private fun BuyerBottomNavigationItemView(
    selected: Boolean,
    label: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val contentColor = BBColors.Black

    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Box(
            modifier = if (selected) {
                Modifier
                    .background(
                        color = BBColors.PrimarySoft,
                        shape = BBRadius.PillShape
                    )
                    .padding(
                        horizontal = BBSpacing.Space3,
                        vertical = BBSpacing.Space1
                    )
            } else {
                Modifier.padding(
                    horizontal = BBSpacing.Space3,
                    vertical = BBSpacing.Space1
                )
            },
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(
                LocalContentColor provides contentColor
            ) {
                icon()
            }
        }

        Text(
            text = label,
            style = BbTypography.labelSmall,
            color = contentColor
        )
    }
}

@Composable
private fun BuyerBottomNavigationCenterAction(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = BBSpacing.Space1,
                vertical = BBSpacing.Space1
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Surface(
            modifier = Modifier.size(BBIcon.BoxXl),
            shape = BBRadius.XlShape,
            color = BBColors.Primary,
            contentColor = BBColors.Black,
            border = BorderStroke(
                width = BBSpacing.Space1,
                color = BBColors.Yellow.Yellow600
            ),
            shadowElevation = BBSpacing.Space8
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Cached,
                    contentDescription = null,
                    tint = BBColors.Black,
                    modifier = Modifier.size(BBIcon.SizeXl)
                )
            }
        }

        Text(
            text = "Geçiş",
            style = BbTypography.labelSmall,
            color = BBColors.Black
        )
    }
}
