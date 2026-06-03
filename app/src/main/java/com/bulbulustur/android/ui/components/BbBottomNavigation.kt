package com.bulbulustur.android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbTypography

enum class BbBottomNavigationItem {
    Home,
    Categories,
    Basket,
    Account,
    More
}

@Composable
fun BbBottomNavigation(
    selectedItem: BbBottomNavigationItem,
    onItemClick: (BbBottomNavigationItem) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = BbColors.TextStrong
    ) {
        NavigationBarItem(
            selected = selectedItem == BbBottomNavigationItem.Home,
            onClick = { onItemClick(BbBottomNavigationItem.Home) },
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
            colors = bbBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == BbBottomNavigationItem.Categories,
            onClick = { onItemClick(BbBottomNavigationItem.Categories) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Category,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Kategoriler",
                    style = BbTypography.labelSmall
                )
            },
            colors = bbBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == BbBottomNavigationItem.Basket,
            onClick = { onItemClick(BbBottomNavigationItem.Basket) },
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
            colors = bbBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == BbBottomNavigationItem.Account,
            onClick = { onItemClick(BbBottomNavigationItem.Account) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Hesabım",
                    style = BbTypography.labelSmall
                )
            },
            colors = bbBottomNavigationColors()
        )

        NavigationBarItem(
            selected = selectedItem == BbBottomNavigationItem.More,
            onClick = { onItemClick(BbBottomNavigationItem.More) },
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
            colors = bbBottomNavigationColors()
        )
    }
}

@Composable
private fun bbBottomNavigationColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = BbColors.Primary,
    selectedTextColor = BbColors.Primary,
    unselectedIconColor = BbColors.TextMuted,
    unselectedTextColor = BbColors.TextMuted,
    indicatorColor = BbColors.PrimarySoft
)