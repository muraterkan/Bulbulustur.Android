package com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components

import androidx.compose.runtime.Composable
import com.bulbulustur.android.Application.Views.Shared.BuyerBottomNavigation
import com.bulbulustur.android.Application.Views.Shared.BuyerBottomNavigationItem
import com.bulbulustur.android.businesslayer.Core.Enums.EBuyerMode

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
        mode = EBuyerMode.Wholesale,
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

