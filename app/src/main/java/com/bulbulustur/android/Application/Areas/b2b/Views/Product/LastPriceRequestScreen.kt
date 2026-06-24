package com.bulbulustur.android.Application.Areas.b2b.Views.Product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.PriceCheck
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun LastPriceRequestScreen(
    productId: Int = 1,
    productName: String = "Square Silver Starlight Chain Shirt Collar Anti-Blood Brooch",
    companyName: String = "Anadolu Tedarik",
    currentPriceLabel: String = "20 $",
    onBackClick: () -> Unit = {},
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

    Scaffold(
        containerColor = BBColors.SurfaceSoft,
        topBar = {
            BbInnerPageHeader(
                title = "Son Fiyat Talebi",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
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
                    title = "Talep Detayları",
                    subtitle = "Son fiyat alabilmek için miktar ve beklentilerinizi yazın"
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    LastPriceTextField(
                        value = quantity.value,
                        onValueChange = {
                            quantity.value = it
                        },
                        label = "Miktar",
                        placeholder = "Ã–rn. 1000",
                        icon = Icons.Outlined.Inventory2,
                        modifier = Modifier.weight(1f)
                    )

                    LastPriceTextField(
                        value = targetPrice.value,
                        onValueChange = {
                            targetPrice.value = it
                        },
                        label = "Hedef Fiyat",
                        placeholder = "Ã–rn. 18 $",
                        icon = Icons.Outlined.PriceCheck,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    LastPriceTextField(
                        value = paymentTerm.value,
                        onValueChange = {
                            paymentTerm.value = it
                        },
                        label = "Ã–deme Åartı",
                        placeholder = "Peşin / vadeli",
                        icon = Icons.Outlined.Payments,
                        modifier = Modifier.weight(1f)
                    )

                    LastPriceTextField(
                        value = deliveryTarget.value,
                        onValueChange = {
                            deliveryTarget.value = it
                        },
                        label = "Teslimat Hedefi",
                        placeholder = "Ä°stanbul / depo",
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
                    label = "DiĞer Detaylar",
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
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
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
            modifier = Modifier.padding(BBSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.PriceCheck,
                    contentDescription = null,
                    tint = BBColors.Primary
                )

                Text(
                    text = "Son Fiyat Talebi",
                    style = MaterialTheme.typography.labelLarge,
                    color = BBColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Son Fiyat Ä°steĞi Oluştur",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = BBColors.TextStrong
            )

            Text(
                text = "Toptan alım miktarınıza göre tedarikçiden son fiyat, ödeme ve teslimat şartları için teklif isteyin.",
                style = MaterialTheme.typography.bodyMedium,
                color = BBColors.TextMuted
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
            modifier = Modifier.padding(BBSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = "Ürün",
                style = MaterialTheme.typography.labelMedium,
                color = BBColors.Primary,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = productName,
                style = MaterialTheme.typography.titleMedium,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbChip(
                    text = companyName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Mevcut Fiyat: $currentPriceLabel",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Toptan Teklif",
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
            focusedTextColor = BBColors.TextStrong,
            unfocusedTextColor = BBColors.TextStrong,
            focusedContainerColor = BBColors.Surface,
            unfocusedContainerColor = BBColors.Surface,
            focusedIndicatorColor = BBColors.Primary,
            unfocusedIndicatorColor = BBColors.Border,
            focusedLabelColor = BBColors.Primary,
            unfocusedLabelColor = BBColors.TextMuted,
            cursorColor = BBColors.Primary
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
            .height(BBSpacing.Space16 * 3),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = BBColors.TextStrong,
            unfocusedTextColor = BBColors.TextStrong,
            focusedContainerColor = BBColors.Surface,
            unfocusedContainerColor = BBColors.Surface,
            focusedIndicatorColor = BBColors.Primary,
            unfocusedIndicatorColor = BBColors.Border,
            focusedLabelColor = BBColors.Primary,
            unfocusedLabelColor = BBColors.TextMuted,
            cursorColor = BBColors.Primary
        )
    )
}

@Composable
private fun LastPriceSuggestionChips(
    onSuggestionClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        lastPriceSuggestionTexts()
            .forEach { suggestion ->
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
                .padding(BBSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = null,
                tint = BBColors.Primary
            )

            Text(
                text = "Net miktar, hedef fiyat, ödeme yöntemi ve teslimat beklentisi yazarsanız tedarikçi daha doĞru son fiyat verebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextMuted,
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
                .padding(BBSpacing.Space5),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Icon(
                imageVector = Icons.Outlined.Send,
                contentDescription = null,
                tint = BBColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Gönder",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BBColors.TextStrong
                )

                Text(
                    text = "Son fiyat isteĞi API baĞlantısından sonra gerçek endpointâ€™e gönderilecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted
                )
            }

            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = BBColors.Primary
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
            title = "Toplu Alım",
            description = "BelirttiĞim miktar için en iyi son fiyatı rica ederim."
        ),
        LastPriceSuggestionText(
            title = "Ã–deme Åartı",
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

