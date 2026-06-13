package com.bulbulustur.android.features.wholesale.rfq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.CurrencyLira
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Lightbulb
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
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigation
import com.bulbulustur.android.features.wholesale.components.WholesaleBottomNavigationItem
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbInnerPageHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun RfqCreateScreen(
    productId: Int? = null,
    companyId: Int? = null,
    initialProductName: String = "",
    onBackClick: () -> Unit = {},
    onSendClick: () -> Unit = {},
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
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "RFQ Oluştur",
                onBackClick = onBackClick
            )
        },
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
                .background(BbColors.SurfaceMuted)
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
                RfqCreateIntroCard(
                    productId = productId,
                    companyId = companyId
                )
            }

            item {
                RfqFormSection(
                    title = "Temel Bilgiler",
                    subtitle = "Tedarikçilerin talebi hızlı anlayabilmesi için ürün ve kategori bilgisini net yazın."
                ) {
                    RfqTextField(
                        value = productName.value,
                        onValueChange = {
                            productName.value = it
                        },
                        label = "Ürün adı",
                        placeholder = "Örn. Endüstriyel vana",
                        icon = Icons.Outlined.Inventory2
                    )

                    RfqTextField(
                        value = categoryName.value,
                        onValueChange = {
                            categoryName.value = it
                        },
                        label = "Kategori",
                        placeholder = "Örn. Ambalaj ve Paketleme",
                        icon = Icons.Outlined.Category
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                    ) {
                        RfqTextField(
                            value = quantity.value,
                            onValueChange = {
                                quantity.value = it
                            },
                            label = "Miktar",
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
                            placeholder = "Adet",
                            icon = Icons.Outlined.Inventory2,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            item {
                RfqFormSection(
                    title = "Ticari Bilgiler",
                    subtitle = "Hedef fiyat, ödeme ve teslimat şartları teklif kalitesini artırır."
                ) {
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
                            placeholder = "TL",
                            icon = Icons.Outlined.CurrencyLira,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    RfqTextField(
                        value = materialName.value,
                        onValueChange = {
                            materialName.value = it
                        },
                        label = "Malzeme",
                        placeholder = "Örn. Kraft / Metal / Plastik",
                        icon = Icons.Outlined.Verified
                    )

                    RfqTextField(
                        value = paymentTerm.value,
                        onValueChange = {
                            paymentTerm.value = it
                        },
                        label = "Ödeme şartı",
                        placeholder = "Peşin / Vadeli / Görüşülebilir",
                        icon = Icons.Outlined.Payments
                    )

                    RfqTextField(
                        value = tradeTerm.value,
                        onValueChange = {
                            tradeTerm.value = it
                        },
                        label = "Ticaret şartı",
                        placeholder = "FOB / CIF / EXW",
                        icon = Icons.Outlined.RequestQuote
                    )

                    RfqTextField(
                        value = shippingTarget.value,
                        onValueChange = {
                            shippingTarget.value = it
                        },
                        label = "Nakliye hedefi",
                        placeholder = "Türkiye / İstanbul / Ambarlı Port",
                        icon = Icons.Outlined.LocalShipping
                    )
                }
            }

            item {
                RfqFormSection(
                    title = "Talep Açıklaması",
                    subtitle = "Renk, ölçü, malzeme, ambalaj, sertifika veya özel üretim beklentilerini yazın."
                ) {
                    RfqLongTextField(
                        value = description.value,
                        onValueChange = {
                            description.value = it
                        },
                        label = "Ürün açıklaması",
                        placeholder = "Aradığınız ürünü ve beklentilerinizi detaylandırın."
                    )
                }
            }

            item {
                RfqSuggestionCard(
                    onSuggestionClick = { suggestion ->
                        description.value = suggestion
                    }
                )
            }

            item {
                RfqSubmitCard(
                    onSendClick = onSendClick
                )
            }
        }
    }
}

@Composable
private fun RfqCreateIntroCard(
    productId: Int?,
    companyId: Int?
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            RfqCreateIconBox(
                icon = Icons.Outlined.RequestQuote
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Text(
                    text = "B2B RFQ",
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.Yellow.Yellow800
                )

                Text(
                    text = "Doğru talebi yaz, daha kaliteli teklif al.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = buildString {
                        append("Ürün, miktar, ticari şart ve teslimat hedefini netleştirerek tedarikçilerden daha sağlıklı dönüş alın.")
                        if (productId != null) {
                            append(" Bu talep ürün üzerinden başlatıldı.")
                        }
                        if (companyId != null) {
                            append(" Bu talep firma üzerinden başlatıldı.")
                        }
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun RfqFormSection(
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            content()
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
        shape = BbRadius.Input,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = BbColors.Primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = BbColors.Yellow.Yellow800,
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
            .height(BbSpacing.Space20),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        shape = BbRadius.Input,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = BbColors.Primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = BbColors.Yellow.Yellow800,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = BbColors.Primary
        )
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RfqSuggestionCard(
    onSuggestionClick: (String) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Lightbulb,
                    contentDescription = null,
                    tint = BbColors.Yellow.Yellow800,
                    modifier = Modifier.size(BbIcon.Ui)
                )

                Text(
                    text = "Hazır talep notları",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

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
}

@Composable
private fun RfqSubmitCard(
    onSendClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Default,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Text(
                text = "Talebi göndermeye hazır mısınız?",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "API bağlantısından sonra bu form gerçek RFQ endpoint’ine gönderilecek.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "RFQ Gönder",
                onClick = onSendClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Large,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqCreateIconBox(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxMd)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.Action)
        )
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