package com.bulbulustur.android.Features.Areas.b2b.components

import androidx.compose.runtime.Composable
import com.bulbulustur.android.Ui.shell.BuyerBottomNavigation
import com.bulbulustur.android.Ui.shell.BuyerBottomNavigationItem
import com.bulbulustur.android.Ui.shell.BuyerMode

enum class WholesaleBottomNavigationItem {
    Home,
    Menu,
    ModeSwitch,
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
        WholesaleBottomNavigationItem.ModeSwitch -> BuyerBottomNavigationItem.Switch
        WholesaleBottomNavigationItem.Basket -> BuyerBottomNavigationItem.Basket
        WholesaleBottomNavigationItem.Account -> BuyerBottomNavigationItem.Account
    }
}

private fun BuyerBottomNavigationItem.toWholesaleItem(): WholesaleBottomNavigationItem {
    return when (this) {
        BuyerBottomNavigationItem.Home -> WholesaleBottomNavigationItem.Home
        BuyerBottomNavigationItem.Menu -> WholesaleBottomNavigationItem.Menu
        BuyerBottomNavigationItem.Switch -> WholesaleBottomNavigationItem.ModeSwitch
        BuyerBottomNavigationItem.Basket -> WholesaleBottomNavigationItem.Basket
        BuyerBottomNavigationItem.Account -> WholesaleBottomNavigationItem.Account
    }
}