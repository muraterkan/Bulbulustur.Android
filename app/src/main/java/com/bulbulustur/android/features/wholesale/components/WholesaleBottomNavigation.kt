package com.bulbulustur.android.features.wholesale.components

import androidx.compose.runtime.Composable
import com.bulbulustur.android.ui.shell.BuyerBottomNavigation
import com.bulbulustur.android.ui.shell.BuyerBottomNavigationItem
import com.bulbulustur.android.ui.shell.BuyerMode

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
    BuyerBottomNavigation(
        mode = BuyerMode.Wholesale,
        selectedItem = selectedItem.toBuyerItem(),
        onItemClick = { item ->
            onItemClick(item.toWholesaleItem())
        }
    )
}

private fun WholesaleBottomNavigationItem.toBuyerItem(): BuyerBottomNavigationItem {
    return when (this) {
        WholesaleBottomNavigationItem.Home -> BuyerBottomNavigationItem.Home
        WholesaleBottomNavigationItem.Menu -> BuyerBottomNavigationItem.Menu
        WholesaleBottomNavigationItem.Messages -> BuyerBottomNavigationItem.Switch
        WholesaleBottomNavigationItem.Basket -> BuyerBottomNavigationItem.Basket
        WholesaleBottomNavigationItem.Account -> BuyerBottomNavigationItem.Account
    }
}

private fun BuyerBottomNavigationItem.toWholesaleItem(): WholesaleBottomNavigationItem {
    return when (this) {
        BuyerBottomNavigationItem.Home -> WholesaleBottomNavigationItem.Home
        BuyerBottomNavigationItem.Menu -> WholesaleBottomNavigationItem.Menu
        BuyerBottomNavigationItem.Switch -> WholesaleBottomNavigationItem.Messages
        BuyerBottomNavigationItem.Basket -> WholesaleBottomNavigationItem.Basket
        BuyerBottomNavigationItem.Account -> WholesaleBottomNavigationItem.Account
    }
}