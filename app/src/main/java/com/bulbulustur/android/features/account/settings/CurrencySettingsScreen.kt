package com.bulbulustur.android.features.account.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.bulbulustur.android.ui.components.*
import com.bulbulustur.android.ui.theme.*

@Composable
fun CurrencySettingsScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedCurrency by remember { mutableStateOf("TRY") }

    val currencies = listOf(
        CurrencyOption("TRY", "Türk Lirası", "🇹🇷"),
        CurrencyOption("USD", "Amerikan Doları", "🇺🇸"),
        CurrencyOption("EUR", "Euro", "🇪🇺"),
        CurrencyOption("GBP", "İngiliz Sterlini", "🇬🇧"),
        CurrencyOption("AED", "Emirati Dirhem", "🇦🇪"),
        CurrencyOption("SAR", "Suudi Riyali", "🇸🇦"),
        CurrencyOption("CNY", "Çin Yuanı", "🇨🇳")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            )
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentPadding = PaddingValues(
            horizontal = BbSpacing.PageHorizontal,
            vertical = BbSpacing.PageTopCompact
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        item {
            SettingsHeaderCard(
                backText = "Ayarlara Dön",
                kicker = "Alışveriş Tercihi",
                title = "Para Birimi",
                description = "Ürün fiyatlarını görmek istediğiniz para birimini seçin.",
                onBackClick = onBackClick
            )
        }

        item {
            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    Text(
                        text = "Desteklenen para birimleri",
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Seçiminiz ürün listeleme, sepet ve ödeme ekranlarında kullanılacaktır.",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        items(currencies.size) { index ->
            val currency = currencies[index]

            CurrencyRow(
                item = currency,
                isSelected = selectedCurrency == currency.code,
                onClick = {
                    selectedCurrency = currency.code
                }
            )
        }
    }
}

@Composable
private fun CurrencyRow(
    item: CurrencyOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.flag,
                style = BbTypography.titleLarge
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BbIcon.SizeLg)
                )
            }
        }
    }
}

private data class CurrencyOption(
    val code: String,
    val name: String,
    val flag: String
)