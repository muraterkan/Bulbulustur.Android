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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.Enums.EBuyerMode

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
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = BBSpacing.Space5
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.BorderThin)
                    .background(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.Space24)
                    .padding(
                        start = BBSpacing.Space2,
                        top = BBSpacing.Space3,
                        end = BBSpacing.Space2,
                        bottom = BBSpacing.Space4
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Home,
                    label = if (mode == EBuyerMode.Retail) BBLocalization.Current.Get(key = "fe9c56ac-dbc2-4fc6-afe0-bb3f7cf1f8f7", fallback = "Ana Sayfa") else "Toptan",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                            modifier = Modifier.size(
                                BBIcon.BottomNavigationIcon
                            )
                        )
                    },
                    onClick = {
                        onItemClick(
                            BuyerBottomNavigationItem.Home
                        )
                    }
                )

                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Menu,
                    label = BBLocalization.Current.Get(key = "9eeb9367-4c7f-4740-b2c6-1badfe7798f2", fallback = "Kategoriler"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Category,
                            contentDescription = null,
                            modifier = Modifier.size(
                                BBIcon.BottomNavigationIcon
                            )
                        )
                    },
                    onClick = {
                        onItemClick(
                            BuyerBottomNavigationItem.Menu
                        )
                    }
                )

                BuyerBottomNavigationCenterAction(
                    onClick = {
                        onItemClick(
                            BuyerBottomNavigationItem.Switch
                        )
                    }
                )

                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Basket,
                    label = if (mode == EBuyerMode.Retail) "Sepet" else BBLocalization.Current.Get(key = "05ce926b-485a-4872-b758-ac3eea7a80a2", fallback = ""),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.LocalOffer,
                            contentDescription = null,
                            modifier = Modifier.size(
                                BBIcon.BottomNavigationIcon
                            )
                        )
                    },
                    onClick = {
                        onItemClick(
                            BuyerBottomNavigationItem.Basket
                        )
                    }
                )

                BuyerBottomNavigationItemView(
                    selected = selectedItem == BuyerBottomNavigationItem.Account,
                    label = BBLocalization.Current.Get(key = "12cf0c4a-1b66-4a2e-800c-dfe75644a6bc", fallback = ""),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(
                                BBIcon.BottomNavigationIcon
                            )
                        )
                    },
                    onClick = {
                        onItemClick(
                            BuyerBottomNavigationItem.Account
                        )
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
    val contentColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Box(
            modifier = if (selected) {
                Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
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
    val contentColor = MaterialTheme.colorScheme.onPrimary

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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Surface(
            modifier = Modifier.size(
                BBIcon.BoxXl
            ),
            shape = BBRadius.XlShape,
            color = MaterialTheme.colorScheme.primary,
            contentColor = contentColor,
            border = BorderStroke(
                width = BBSpacing.Space1,
                color = MaterialTheme.colorScheme.primary
            ),
            shadowElevation = BBSpacing.Space8
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Cached,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(
                        BBIcon.SizeXl
                    )
                )
            }
        }

        Text(
            text = BBLocalization.Current.Get(key = "59c8160b-b937-47cc-9ed5-02963b02e394", fallback = "Geçiş"),
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}