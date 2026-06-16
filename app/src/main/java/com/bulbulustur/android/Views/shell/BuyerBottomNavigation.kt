package com.bulbulustur.android.wwwroot.shell

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
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTypography

enum class BuyerBottomNavigationItem {
    Home,
    Menu,
    Switch,
    Basket,
    Account
}

@Composable
fun BuyerBottomNavigation(
    mode: BuyerMode,
    selectedItem: BuyerBottomNavigationItem,
    onItemClick: (BuyerBottomNavigationItem) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = BbColors.Surface,
        shadowElevation = BbSpacing.Space5
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BbSpacing.BorderThin)
                    .background(BbColors.Border)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BbSpacing.Space24)
                    .padding(
                        start = BbSpacing.Space2,
                        end = BbSpacing.Space2,
                        top = BbSpacing.Space3,
                        bottom = BbSpacing.Space4
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Home,
                    label = if (mode == BuyerMode.Retail) "Ana Sayfa" else "Toptan",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                            modifier = Modifier.size(BbIcon.BottomNavigationIcon)
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
                            modifier = Modifier.size(BbIcon.BottomNavigationIcon)
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
                    label = if (mode == BuyerMode.Retail) "Sepet" else "Teklifler",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.LocalOffer,
                            contentDescription = null,
                            modifier = Modifier.size(BbIcon.BottomNavigationIcon)
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
                            modifier = Modifier.size(BbIcon.BottomNavigationIcon)
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
    val contentColor = BbColors.Black

    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Box(
            modifier = if (selected) {
                Modifier
                    .background(
                        color = BbColors.PrimarySoft,
                        shape = BbRadius.PillShape
                    )
                    .padding(
                        horizontal = BbSpacing.Space3,
                        vertical = BbSpacing.Space1
                    )
            } else {
                Modifier.padding(
                    horizontal = BbSpacing.Space3,
                    vertical = BbSpacing.Space1
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
                horizontal = BbSpacing.Space1,
                vertical = BbSpacing.Space1
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Surface(
            modifier = Modifier.size(BbIcon.BoxXl),
            shape = BbRadius.XlShape,
            color = BbColors.Primary,
            contentColor = BbColors.Black,
            border = BorderStroke(
                width = BbSpacing.Space1,
                color = BbColors.Yellow.Yellow600
            ),
            shadowElevation = BbSpacing.Space8
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Cached,
                    contentDescription = null,
                    tint = BbColors.Black,
                    modifier = Modifier.size(BbIcon.SizeXl)
                )
            }
        }

        Text(
            text = "Geçiş",
            style = BbTypography.labelSmall,
            color = BbColors.Black
        )
    }
}