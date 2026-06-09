package com.bulbulustur.android.features.wholesale.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbTypography

enum class WholesaleBottomNavigationItem {
    Home,
    Menu,
    Messages,
    Basket,
    Account
}

@Composable
fun WholesaleBottomNavigation(
    selectedItem: WholesaleBottomNavigationItem,
    onItemClick: (WholesaleBottomNavigationItem) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = BbColors.TextStrong
    ) {
        NavigationBarItem(
            selected = selectedItem == WholesaleBottomNavigationItem.Home,
            onClick = { onItemClick(WholesaleBottomNavigationItem.Home) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Ana Sayfa",
                    style = BbTypography.labelSmall
                )
            },
            colors = wholesaleBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == WholesaleBottomNavigationItem.Menu,
            onClick = { onItemClick(WholesaleBottomNavigationItem.Menu) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ViewList,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Menü",
                    style = BbTypography.labelSmall
                )
            },
            colors = wholesaleBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == WholesaleBottomNavigationItem.Messages,
            onClick = { onItemClick(WholesaleBottomNavigationItem.Messages) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.MailOutline,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Mesaj",
                    style = BbTypography.labelSmall
                )
            },
            colors = wholesaleBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == WholesaleBottomNavigationItem.Basket,
            onClick = { onItemClick(WholesaleBottomNavigationItem.Basket) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ShoppingBasket,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Sepet",
                    style = BbTypography.labelSmall
                )
            },
            colors = wholesaleBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == WholesaleBottomNavigationItem.Account,
            onClick = { onItemClick(WholesaleBottomNavigationItem.Account) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Hesap",
                    style = BbTypography.labelSmall
                )
            },
            colors = wholesaleBottomNavigationColors()
        )
    }
}

@Composable
private fun wholesaleBottomNavigationColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = BbColors.Primary,
    selectedTextColor = BbColors.TextStrong,
    unselectedIconColor = BbColors.TextMuted,
    unselectedTextColor = BbColors.TextMuted,
    indicatorColor = BbColors.PrimarySoft
)