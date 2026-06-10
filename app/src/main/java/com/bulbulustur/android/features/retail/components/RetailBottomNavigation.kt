package com.bulbulustur.android.features.retail.components

import androidx.compose.runtime.Composable
import com.bulbulustur.android.ui.shell.BuyerBottomNavigation
import com.bulbulustur.android.ui.shell.BuyerBottomNavigationItem
import com.bulbulustur.android.ui.shell.BuyerMode

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
    BuyerBottomNavigation(
        mode = BuyerMode.Retail,
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
        RetailBottomNavigationItem.Messages -> BuyerBottomNavigationItem.Switch
        RetailBottomNavigationItem.Basket -> BuyerBottomNavigationItem.Basket
        RetailBottomNavigationItem.Account -> BuyerBottomNavigationItem.Account
    }
}

private fun BuyerBottomNavigationItem.toRetailItem(): RetailBottomNavigationItem {
    return when (this) {
        BuyerBottomNavigationItem.Home -> RetailBottomNavigationItem.Home
        BuyerBottomNavigationItem.Menu -> RetailBottomNavigationItem.Menu
        BuyerBottomNavigationItem.Switch -> RetailBottomNavigationItem.Messages
        BuyerBottomNavigationItem.Basket -> RetailBottomNavigationItem.Basket
        BuyerBottomNavigationItem.Account -> RetailBottomNavigationItem.Account
    }
}