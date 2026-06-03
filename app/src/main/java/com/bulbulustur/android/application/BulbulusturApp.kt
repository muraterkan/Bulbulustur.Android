package com.bulbulustur.android.application

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bulbulustur.android.features.modeselection.ModeSelectionScreen
import com.bulbulustur.android.features.retail.RetailHomeScreen
import com.bulbulustur.android.features.wholesale.WholesaleHomeScreen

enum class BulbulusturCommerceMode {
    ModeSelection,
    Retail,
    Wholesale
}

@Composable
fun BulbulusturApp() {
    val selectedMode = remember {
        mutableStateOf(BulbulusturCommerceMode.ModeSelection)
    }

    when (selectedMode.value) {
        BulbulusturCommerceMode.ModeSelection -> {
            ModeSelectionScreen(
                onRetailClick = {
                    selectedMode.value = BulbulusturCommerceMode.Retail
                },
                onWholesaleClick = {
                    selectedMode.value = BulbulusturCommerceMode.Wholesale
                }
            )
        }

        BulbulusturCommerceMode.Retail -> {
            RetailHomeScreen()
        }

        BulbulusturCommerceMode.Wholesale -> {
            WholesaleHomeScreen()
        }
    }
}