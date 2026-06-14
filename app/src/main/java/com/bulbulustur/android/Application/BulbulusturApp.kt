package com.bulbulustur.android.Application

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bulbulustur.android.Features.modeselection.ModeSelectionScreen

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
            _root_ide_package_.com.bulbulustur.android.Features.areas.b2c.RetailHomeScreen()
        }

        BulbulusturCommerceMode.Wholesale -> {
            _root_ide_package_.com.bulbulustur.android.Features.areas.b2b.WholesaleHomeScreen()
        }
    }
}