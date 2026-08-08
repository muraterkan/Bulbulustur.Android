package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO

@Composable
fun CurrencySettingsScreen(
    currencies: List<SystemDescCurrencyDTO>,
    selectedCurrencyId: Int,
    selectedCurrencyCode: String,
    isLoading: Boolean,
    errorMessage: String?,
    onCurrencySelected: (SystemDescCurrencyDTO) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val visibleCurrencies = ResolveCurrencySettingsItems(currencies)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "47942374-ab80-47b3-af0f-c8a6aaf728e3", fallback = ""),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                CurrencyIntroCard()
            }

            if (isLoading && currencies.isEmpty()) {
                item {
                    CurrencyLoadingCard()
                }
            } else {
                items(
                    items = visibleCurrencies,
                    key = { it.SystemDescCurrencyId }
                ) { currency ->
                    val isSelected =
                        currency.SystemDescCurrencyId == selectedCurrencyId ||
                                (
                                        selectedCurrencyId == 0 &&
                                                currency.IsoCode.equals(
                                                    selectedCurrencyCode,
                                                    ignoreCase = true
                                                )
                                        )

                    CurrencyRow(
                        item = currency,
                        isSelected = isSelected,
                        onClick = {
                            onCurrencySelected(currency)
                        }
                    )
                }
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
            text = BBLocalization.Current.Get(key = "93d16054-82e1-408d-8736-daf94f15de5d", fallback = "Seçiminiz ürün listeleme, sepet ve ödeme ekranlarında kullanılacaktır."),
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CurrencyRow(
    item: SystemDescCurrencyDTO,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.CurrencySymbol.ifBlank {
                        item.IsoCode.take(1).uppercase()
                    },
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = item.IsoCode.ifBlank { "CUR" },
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.Content.ifBlank {
                        CurrencyFallbackName(item.IsoCode)
                    },
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = BBLocalization.Current.Get(key = "47942374-ab80-47b3-af0f-c8a6aaf728e3", fallback = ""),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.SizeLg)
                )
            }
        }
    }
}

@Composable
private fun CurrencyLoadingCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(BBIcon.SizeLg),
                strokeWidth = BBSpacing.Space1 / 2,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = BBLocalization.Current.Get(key = "8230ed4c-e124-474d-87d6-054b5a0d8008", fallback = "Para birimleri yükleniyor..."),
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun ResolveCurrencySettingsItems(
    currencies: List<SystemDescCurrencyDTO>
): List<SystemDescCurrencyDTO> {
    return currencies.ifEmpty {
        listOf(
            SystemDescCurrencyDTO(
                SystemDescCurrencyId = 1,
                IsoCode = "TRY",
                CurrencySymbol = "₺",
                Content = BBLocalization.Current.Get(key = "03f77d05-c54c-44f2-99f9-3dc82473eb51", fallback = "Türk Lirası")
            ),
            SystemDescCurrencyDTO(
                SystemDescCurrencyId = 2,
                IsoCode = "USD",
                CurrencySymbol = "$",
                Content = "US Dollar"
            ),
            SystemDescCurrencyDTO(
                SystemDescCurrencyId = 3,
                IsoCode = "EUR",
                CurrencySymbol = "€",
                Content = "Euro"
            ),
            SystemDescCurrencyDTO(
                SystemDescCurrencyId = 4,
                IsoCode = "GBP",
                CurrencySymbol = "£",
                Content = "Pound Sterling"
            )
        )
    }
}

private fun CurrencyFallbackName(
    isoCode: String
): String {
    return when (isoCode.uppercase()) {
        "TRY" -> BBLocalization.Current.Get(key = "03f77d05-c54c-44f2-99f9-3dc82473eb51", fallback = "Türk Lirası")
        "USD" -> "US Dollar"
        "EUR" -> "Euro"
        "GBP" -> "Pound Sterling"
        else -> "Para birimi"
    }
}