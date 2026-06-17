package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import androidx.compose.runtime.Composable
import com.bulbulustur.android.Application.Views.Shared.BuyerBottomNavigation
import com.bulbulustur.android.Application.Views.Shared.BuyerBottomNavigationItem
import com.bulbulustur.android.businesslayer.Core.Enums.EBuyerMode

enum class RetailBottomNavigationItem {
    Home,
    Menu,
    ModeSwitch,
    Basket,
    Account
}

@Composable
fun RetailBottomNavigation(
    selectedItem: RetailBottomNavigationItem,
    onItemClick: (RetailBottomNavigationItem) -> Unit
) {
    BuyerBottomNavigation(
        mode = EBuyerMode.Retail,
        selectedItem = selectedItem.toBuyerItem(),
        onItemClick = { item ->
            onItemClick(item.toRetailItem())
        }
    )
}

private fun RetailBottomNavigationItem.toBuyerItem(): BuyerBottomNavigationItem {
    return when (this) {
        RetailBottomNavigationItem.Home -> BuyerBottomNavigationItem.Home
        RetailBottomNavigationItem.Menu -> BuyerBottomNavigationItem.Menu
        RetailBottomNavigationItem.ModeSwitch -> BuyerBottomNavigationItem.Switch
        RetailBottomNavigationItem.Basket -> BuyerBottomNavigationItem.Basket
        RetailBottomNavigationItem.Account -> BuyerBottomNavigationItem.Account
    }
}

private fun BuyerBottomNavigationItem.toRetailItem(): RetailBottomNavigationItem {
    return when (this) {
        BuyerBottomNavigationItem.Home -> RetailBottomNavigationItem.Home
        BuyerBottomNavigationItem.Menu -> RetailBottomNavigationItem.Menu
        BuyerBottomNavigationItem.Switch -> RetailBottomNavigationItem.ModeSwitch
        BuyerBottomNavigationItem.Basket -> RetailBottomNavigationItem.Basket
        BuyerBottomNavigationItem.Account -> RetailBottomNavigationItem.Account
    }
}
