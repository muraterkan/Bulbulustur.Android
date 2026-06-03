package com.bulbulustur.android.features.wholesale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.PriceCheck
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun LastPriceRequestScreen(
    productId: Int = 1,
    productName: String = "Square Silver Starlight Chain Shirt Collar Anti-Blood Brooch",
    companyName: String = "Anadolu Tedarik",
    currentPriceLabel: String = "20 $",
    onSendClick: () -> Unit = {}
) {
    val quantity = remember {
        mutableStateOf("")
    }

    val targetPrice = remember {
        mutableStateOf("")
    }

    val paymentTerm = remember {
        mutableStateOf("")
    }

    val deliveryTarget = remember {
        mutableStateOf("")
    }

    val detail = remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        item {
            LastPriceRequestHeader(
                productName = productName
            )
        }

        item {
            LastPriceProductSummaryCard(
                productName = productName,
                companyName = companyName,
                currentPriceLabel = currentPriceLabel
            )
        }

        item {
            BbSectionHeader(
                title = "Talep detayları",
                subtitle = "Son fiyat alabilmek için miktar ve beklentilerinizi yazın"
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                LastPriceTextField(
                    value = quantity.value,
                    onValueChange = {
                        quantity.value = it
                    },
                    label = "Miktar",
                    placeholder = "Örn. 1000",
                    icon = Icons.Outlined.Inventory2,
                    modifier = Modifier.weight(1f)
                )

                LastPriceTextField(
                    value = targetPrice.value,
                    onValueChange = {
                        targetPrice.value = it
                    },
                    label = "Hedef fiyat",
                    placeholder = "Örn. 18 $",
                    icon = Icons.Outlined.PriceCheck,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                LastPriceTextField(
                    value = paymentTerm.value,
                    onValueChange = {
                        paymentTerm.value = it
                    },
                    label = "Ödeme şartı",
                    placeholder = "Peşin / vadeli",
                    icon = Icons.Outlined.Payments,
                    modifier = Modifier.weight(1f)
                )

                LastPriceTextField(
                    value = deliveryTarget.value,
                    onValueChange = {
                        deliveryTarget.value = it
                    },
                    label = "Teslimat hedefi",
                    placeholder = "İstanbul / depo",
                    icon = Icons.Outlined.LocalShipping,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            LastPriceLongTextField(
                value = detail.value,
                onValueChange = {
                    detail.value = it
                },
                label = "Diğer detaylar",
                placeholder = "Bilmemiz gereken başka bir şey var mı? Miktar, hedef fiyat, ödeme ve teslimat beklentinizi yazın."
            )
        }

        item {
            LastPriceSuggestionChips(
                onSuggestionClick = {
                    detail.value = it
                }
            )
        }

        item {
            LastPriceHintCard()
        }

        item {
            LastPriceSendCard(
                onSendClick = onSendClick
            )
        }

        item {
            Spacer(modifier = Modifier.height(BbSpacing.xl))
        }
    }
}

@Composable
private fun LastPriceRequestHeader(
    productName: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.PriceCheck,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Son Fiyat Talebi",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Son fiyat isteği oluştur",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Toptan alım miktarınıza göre tedarikçiden son fiyat, ödeme ve teslimat şartları için teklif isteyin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbChip(
                text = productName,
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
private fun LastPriceProductSummaryCard(
    productName: String,
    companyName: String,
    currentPriceLabel: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.md),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Text(
                text = "Ürün",
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.Primary,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = productName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                BbChip(
                    text = companyName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Mevcut fiyat: $currentPriceLabel",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Toptan teklif",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun LastPriceTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = BbColors.Primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = BbColors.Primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = BbColors.Primary
        )
    )
}

@Composable
private fun LastPriceLongTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(BbSpacing.Space16 * 3),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = BbColors.Primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = BbColors.Primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = BbColors.Primary
        )
    )
}

@Composable
private fun LastPriceSuggestionChips(
    onSuggestionClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        lastPriceSuggestionTexts().forEach { suggestion ->
            BbChip(
                text = suggestion.title,
                selected = false,
                onClick = {
                    onSuggestionClick(suggestion.description)
                }
            )
        }
    }
}

@Composable
private fun LastPriceHintCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Text(
                text = "Net miktar, hedef fiyat, ödeme yöntemi ve teslimat beklentisi yazarsanız tedarikçi daha doğru son fiyat verebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun LastPriceSendCard(
    onSendClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onSendClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.lg),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.Send,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = "Gönder",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Son fiyat isteği API bağlantısından sonra gerçek endpoint’e gönderilecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = BbColors.Primary
            )
        }
    }
}

private data class LastPriceSuggestionText(
    val title: String,
    val description: String
)

private fun lastPriceSuggestionTexts(): List<LastPriceSuggestionText> {
    return listOf(
        LastPriceSuggestionText(
            title = "Toplu alım",
            description = "Belirttiğim miktar için en iyi son fiyatı rica ederim."
        ),
        LastPriceSuggestionText(
            title = "Ödeme şartı",
            description = "Peşin ve vadeli ödeme seçeneklerine göre son fiyat bilgisini paylaşabilir misiniz?"
        ),
        LastPriceSuggestionText(
            title = "Teslimat",
            description = "Teslimat süresi, kargo/lojistik maliyeti ve hedef teslimat bilgisiyle birlikte son fiyat rica ederim."
        ),
        LastPriceSuggestionText(
            title = "Karşılaştırma",
            description = "Aynı ürün için alternatif kalite veya ambalaj seçenekleri varsa fiyat karşılaştırması almak isterim."
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun LastPriceRequestScreenPreview() {
    BbTheme {
        LastPriceRequestScreen()
    }
}