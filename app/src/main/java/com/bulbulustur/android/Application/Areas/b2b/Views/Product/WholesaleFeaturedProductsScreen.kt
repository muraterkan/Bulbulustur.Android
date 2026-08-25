package com.bulbulustur.android.Application.Areas.b2b.Views.Product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Localization.BBLocalization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WholesaleFeaturedProductsScreen(onBackClick: () -> Unit = {}) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = BBLocalization.Current.Get(key = "1c7c6ac9-2b6d-46ec-90f0-3f88b65beb11", fallback = "Öne Çıkan Ürünler"),
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = BBLocalization.Current.Get(key = "common_back", fallback = "Geri")
                        )
                    }
                }
            )
        }
    ) { _ ->
        Box(modifier = Modifier.fillMaxSize())
    }
}
