package com.bulbulustur.android.features.wholesale.rfq

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
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.CurrencyLira
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Straighten
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
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigation
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigationItem
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun RfqCreateScreen(
    productId: Int? = null,
    companyId: Int? = null,
    initialProductName: String = "",
    onBackClick: () -> Unit = {},
    onSendClick: () -> Unit = {},

    // Bottom navigation
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    val productName = remember {
        mutableStateOf(initialProductName)
    }

    val categoryName = remember {
        mutableStateOf("")
    }

    val quantity = remember {
        mutableStateOf("")
    }

    val unitName = remember {
        mutableStateOf("")
    }

    val unitPrice = remember {
        mutableStateOf("")
    }

    val currencyName = remember {
        mutableStateOf("")
    }

    val materialName = remember {
        mutableStateOf("")
    }

    val paymentTerm = remember {
        mutableStateOf("")
    }

    val tradeTerm = remember {
        mutableStateOf("")
    }

    val shippingTarget = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        bottomBar = {
            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Basket,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        WholesaleBottomNavigationItem.Home -> onHomeClick()
                        WholesaleBottomNavigationItem.Menu -> onMenuClick()
                        WholesaleBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        WholesaleBottomNavigationItem.Basket -> onBasketClick()
                        WholesaleBottomNavigationItem.Account -> onAccountClick()
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                RfqCreateHeader(
                    productId = productId,
                    companyId = companyId,
                    onBackClick = onBackClick
                )
            }

            item {
                RfqCreateHintPanel()
            }

            item {
                BbSectionHeader(
                    title = "Talep bilgileri",
                    subtitle = "Tedarikçilere neye ihtiyacınız olduğunu net anlatın"
                )
            }

            item {
                RfqTextField(
                    value = productName.value,
                    onValueChange = {
                        productName.value = it
                    },
                    label = "Ürün adı",
                    placeholder = "Örn. Endüstriyel vana",
                    icon = Icons.Outlined.Inventory2
                )
            }

            item {
                RfqTextField(
                    value = categoryName.value,
                    onValueChange = {
                        categoryName.value = it
                    },
                    label = "Kategori",
                    placeholder = "Örn. Kök / Endüstriyel ürünler",
                    icon = Icons.Outlined.Category
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    RfqTextField(
                        value = quantity.value,
                        onValueChange = {
                            quantity.value = it
                        },
                        label = "Satın alma miktarı",
                        placeholder = "0",
                        icon = Icons.Outlined.Straighten,
                        modifier = Modifier.weight(1f)
                    )

                    RfqTextField(
                        value = unitName.value,
                        onValueChange = {
                            unitName.value = it
                        },
                        label = "Birim",
                        placeholder = "Varil",
                        icon = Icons.Outlined.Inventory2,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    RfqTextField(
                        value = unitPrice.value,
                        onValueChange = {
                            unitPrice.value = it
                        },
                        label = "Birim fiyat",
                        placeholder = "0",
                        icon = Icons.Outlined.Payments,
                        modifier = Modifier.weight(1f)
                    )

                    RfqTextField(
                        value = currencyName.value,
                        onValueChange = {
                            currencyName.value = it
                        },
                        label = "Para birimi",
                        placeholder = "TL / USD / EUR",
                        icon = Icons.Outlined.CurrencyLira,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    RfqTextField(
                        value = materialName.value,
                        onValueChange = {
                            materialName.value = it
                        },
                        label = "Malzeme",
                        placeholder = "Titanyum",
                        icon = Icons.Outlined.Verified,
                        modifier = Modifier.weight(1f)
                    )

                    RfqTextField(
                        value = paymentTerm.value,
                        onValueChange = {
                            paymentTerm.value = it
                        },
                        label = "Ödeme şartı",
                        placeholder = "Peşin / vadeli",
                        icon = Icons.Outlined.Payments,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                RfqTextField(
                    value = tradeTerm.value,
                    onValueChange = {
                        tradeTerm.value = it
                    },
                    label = "Ticaret şartları",
                    placeholder = "Örn. FOB, CIF, EXW veya özel şartlar",
                    icon = Icons.Outlined.RequestQuote
                )
            }

            item {
                RfqTextField(
                    value = shippingTarget.value,
                    onValueChange = {
                        shippingTarget.value = it
                    },
                    label = "Nakliye hedefi",
                    placeholder = "Örn. Türkiye / İstanbul / Ambarlı Port",
                    icon = Icons.Outlined.LocalShipping
                )
            }

            item {
                RfqLongTextField(
                    value = description.value,
                    onValueChange = {
                        description.value = it
                    },
                    label = "Ürün açıklaması",
                    placeholder = "Aradığınız ürünü açıklayın. Renk, malzeme, boyut, ambalaj, paketleme ve sertifika gerekliliklerini yazın."
                )
            }

            item {
                RfqSuggestionChips(
                    onSuggestionClick = {
                        description.value = it
                    }
                )
            }

            item {
                RfqSendCard(
                    onSendClick = onSendClick
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(BbSpacing.Space8)
                )
            }
        }
    }
}

@Composable
private fun RfqCreateHeader(
    productId: Int?,
    companyId: Int?,
    onBackClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.CardPadding),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.RequestQuote,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "B2B RFQ",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "RFQ talebi gönder",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Tedarikçilere neye ihtiyacınız olduğunu söyleyin. Ürün, miktar, ticaret şartı ve teslimat hedefini ne kadar net yazarsanız teklif süreci o kadar sağlıklı ilerler.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbChip(
                    text = "Net talep",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Doğru tedarikçi",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Ticari şartlar",
                    selected = false,
                    onClick = {}
                )

                if (productId != null) {
                    BbChip(
                        text = "Üründen geldi",
                        selected = false,
                        onClick = {}
                    )
                }

                if (companyId != null) {
                    BbChip(
                        text = "Firmadan geldi",
                        selected = false,
                        onClick = {}
                    )
                }
            }

            BbButton(
                text = "Toptana Dön",
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun RfqCreateHintPanel() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.CardPaddingCompact),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Verified,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Satın alma akışı",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = "Sevkiyat, ödeme, hedef teslimat ve ticari şartları baştan yazmak teklifleri karşılaştırmayı kolaylaştırır.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RfqTextField(
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
private fun RfqLongTextField(
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
private fun RfqSuggestionChips(
    onSuggestionClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Text(
            text = "Hazır talep notları",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            rfqSuggestionTexts().forEach { suggestion ->
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
}

@Composable
private fun RfqSendCard(
    onSendClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onSendClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.CardPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Send,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Gönder",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "RFQ talebi API bağlantısından sonra gerçek endpoint’e gönderilecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private data class RfqSuggestionText(
    val title: String,
    val description: String
)

private fun rfqSuggestionTexts(): List<RfqSuggestionText> {
    return listOf(
        RfqSuggestionText(
            title = "Renk / Malzeme",
            description = "Ürünün renk, malzeme ve yüzey özellikleri için teklif almak istiyorum."
        ),
        RfqSuggestionText(
            title = "Ölçü / Teknik Detay",
            description = "Ürün için teknik ölçüler, üretim toleransları ve kullanım alanı hakkında teklif almak istiyorum."
        ),
        RfqSuggestionText(
            title = "Ambalaj / Logo",
            description = "Ürünün ambalaj, etiket ve logo baskı seçenekleriyle birlikte fiyatlandırılmasını istiyorum."
        ),
        RfqSuggestionText(
            title = "Teslimat / Ödeme",
            description = "Teslimat süresi, ödeme şartı, nakliye hedefi ve ticari koşulların teklifte belirtilmesini istiyorum."
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun RfqCreateScreenPreview() {
    BbTheme {
        RfqCreateScreen()
    }
}