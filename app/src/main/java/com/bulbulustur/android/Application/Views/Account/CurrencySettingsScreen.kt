package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun CurrencySettingsScreen(
    onBackClick: () -> Unit = {}
) {
    val selectedCurrencyState = remember {
        mutableStateOf("TRY")
    }

    val currencies = remember {
        listOf(
            CurrencyOption(
                code = "TRY",
                name = "Türk Lirası",
                flagFileName = "turkey.svg"
            ),
            CurrencyOption(
                code = "USD",
                name = "Amerikan Doları",
                flagFileName = "United States of America.svg"
            ),
            CurrencyOption(
                code = "EUR",
                name = "Euro",
                flagFileName = "european-union.svg"
            ),
            CurrencyOption(
                code = "GBP",
                name = "İngiliz Sterlini",
                flagFileName = "United Kingdom.svg"
            ),
            CurrencyOption(
                code = "AED",
                name = "Emirati Dirhem",
                flagFileName = "United Arab Emirates.svg"
            ),
            CurrencyOption(
                code = "SAR",
                name = "Suudi Riyali",
                flagFileName = "saudi-arabia.svg"
            ),
            CurrencyOption(
                code = "CNY",
                name = "Çin Yuanı",
                flagFileName = "china.svg"
            )
        )
    }

    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = BBAlpha.DisabledContainer
            ),
            MaterialTheme.colorScheme.surfaceVariant.copy(
                alpha = 0.85f
            ),
            MaterialTheme.colorScheme.surfaceVariant
        )
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Para Birimi",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(pageBackground)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGap
            )
        ) {
            item {
                CurrencyIntroCard()
            }

            items(
                items = currencies,
                key = { currency ->
                    currency.code
                }
            ) { currency ->
                CurrencyRow(
                    item = currency,
                    isSelected = selectedCurrencyState.value == currency.code,
                    onClick = {
                        selectedCurrencyState.value = currency.code
                    }
                )
            }
        }
    }
}

@Composable
private fun CurrencyIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Seçiminiz ürün listeleme, sepet ve ödeme ekranlarında kullanılacaktır.",
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CurrencyRow(
    item: CurrencyOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CurrencyFlag(
                flagFileName = item.flagFileName,
                contentDescription = "${item.name} bayraĞı"
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = item.code,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.name,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Seçili para birimi",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.SizeLg)
                )
            }
        }
    }
}

@Composable
private fun CurrencyFlag(
    flagFileName: String,
    contentDescription: String
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(BBIcon.BoxLg)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.PillShape
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(
                    "file:///android_asset/flags/$flagFileName"
                )
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.size(BBIcon.Size2Xl),
            contentScale = ContentScale.Fit
        )
    }
}

private data class CurrencyOption(
    val code: String,
    val name: String,
    val flagFileName: String
)
