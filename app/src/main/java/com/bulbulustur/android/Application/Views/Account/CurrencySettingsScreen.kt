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

            when {
                isLoading -> {
                    item {
                        CurrencyLoadingCard()
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    item {
                        CurrencyErrorCard(errorMessage)
                    }
                }

                currencies.isEmpty() -> {
                    item {
                        CurrencyErrorCard("Kullanılabilir para birimi bulunamadı.")
                    }
                }

                else -> {
                    items(
                        items = currencies,
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
                        item.IsoCode.take(1)
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
                    text = item.IsoCode,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.Content,
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
                strokeWidth = BBSpacing.Space1 / 2
            )

            Text(
                text = "Para birimleri yükleniyor...",
                style = BbTypography.bodyMedium
            )
        }
    }
}

@Composable
private fun CurrencyErrorCard(message: String) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = message,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}