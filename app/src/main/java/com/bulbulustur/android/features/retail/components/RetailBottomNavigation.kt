package com.bulbulustur.android.features.retail.components

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

enum class RetailBottomNavigationItem {
    Home,
    Menu,
    Messages,
    Basket,
    Account
}

@Composable
fun RetailBottomNavigation(
    selectedItem: RetailBottomNavigationItem,
    onItemClick: (RetailBottomNavigationItem) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = BbColors.TextStrong
    ) {
        NavigationBarItem(
            selected = selectedItem == RetailBottomNavigationItem.Home,
            onClick = { onItemClick(RetailBottomNavigationItem.Home) },
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
            colors = retailBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == RetailBottomNavigationItem.Menu,
            onClick = { onItemClick(RetailBottomNavigationItem.Menu) },
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
            colors = retailBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == RetailBottomNavigationItem.Messages,
            onClick = { onItemClick(RetailBottomNavigationItem.Messages) },
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
            colors = retailBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == RetailBottomNavigationItem.Basket,
            onClick = { onItemClick(RetailBottomNavigationItem.Basket) },
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
            colors = retailBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == RetailBottomNavigationItem.Account,
            onClick = { onItemClick(RetailBottomNavigationItem.Account) },
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
            colors = retailBottomNavigationColors()
        )
    }
}

@Composable
private fun retailBottomNavigationColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = BbColors.Primary,
    selectedTextColor = BbColors.TextStrong,
    unselectedIconColor = BbColors.TextMuted,
    unselectedTextColor = BbColors.TextMuted,
    indicatorColor = BbColors.PrimarySoft
)
