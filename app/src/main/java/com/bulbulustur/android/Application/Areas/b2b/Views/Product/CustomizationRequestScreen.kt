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
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material.icons.outlined.Style
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
fun CustomizationRequestScreen(
    productId: Int = 1,
    productName: String = "Square Silver Starlight Chain Shirt Collar Anti-Blood Brooch",
    companyName: String = "Anadolu Tedarik",
    onBackClick: () -> Unit = {},
    onSendClick: () -> Unit = {}
) {
    val detail = remember {
        mutableStateOf("")
    }

    val colorMaterial = remember {
        mutableStateOf("")
    }

    val sizeTechnical = remember {
        mutableStateOf("")
    }

    val packageLogo = remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Özelleştirme Talebi",
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
                CustomizationRequestHeader(
                    productName = productName
                )
            }

            item {
                CustomizationProductSummaryCard(
                    productName = productName,
                    companyName = companyName
                )
            }

            item {
                BbSectionHeader(
                    title = "Özelleştirme Detayları",
                    subtitle = "Tedarikçinin doğru dönüş yapabilmesi için değişiklikleri açık yazın"
                )
            }

            item {
                CustomizationLongTextField(
                    value = detail.value,
                    onValueChange = {
                        detail.value = it
                    },
                    label = "Diğer Detaylar",
                    placeholder = "Talep ettiğiniz ürün özelleştirmesi hakkında daha fazla detay verin."
                )
            }

            item {
                CustomizationSuggestionChips(
                    onSuggestionClick = {
                        detail.value = it
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Hızlı Detay Alanları",
                    subtitle = "İsterseniz özelleştirme bilgisini ayrı ayrı da yazabilirsiniz"
                )
            }

            item {
                CustomizationTextField(
                    value = colorMaterial.value,
                    onValueChange = {
                        colorMaterial.value = it
                    },
                    label = "Renk / Malzeme",
                    placeholder = "Örn. siyah, metal, mat yüzey, titanyum",
                    icon = Icons.Outlined.ColorLens
                )
            }

            item {
                CustomizationTextField(
                    value = sizeTechnical.value,
                    onValueChange = {
                        sizeTechnical.value = it
                    },
                    label = "Ölçü / Teknik Detay",
                    placeholder = "Örn. 30x20 cm, kalınlık, bağlantı detayı",
                    icon = Icons.Outlined.Straighten
                )
            }

            item {
                CustomizationTextField(
                    value = packageLogo.value,
                    onValueChange = {
                        packageLogo.value = it
                    },
                    label = "Ambalaj / Logo",
                    placeholder = "Örn. logolu ambalaj, özel kutu, etiket baskısı",
                    icon = Icons.Outlined.Style
                )
            }

            item {
                CustomizationHintCard()
            }

            item {
                CustomizationSendCard(
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
private fun CustomizationRequestHeader(
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
                    imageVector = Icons.Outlined.Style,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Özelleştirme Talebi",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Ürün Özelleştirme İsteği Oluştur",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Ürün üzerinde ölçü, renk, malzeme, ambalaj, logo veya üretim detayları için tedarikçiye özel talep gönderin.",
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
private fun CustomizationProductSummaryCard(
    productName: String,
    companyName: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = "Tedarikçi",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
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
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbChip(
                    text = companyName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Model No: ABS-778",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Özel Üretim Desteklenir",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun CustomizationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
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
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun CustomizationLongTextField(
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
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun CustomizationSuggestionChips(
    onSuggestionClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        customizationSuggestionTexts()
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
private fun CustomizationHintCard() {
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
                imageVector = Icons.Outlined.LocalOffer,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Özelleştirme talebinde ölçü, renk, malzeme, kullanım amacı ve minimum sipariş miktarını belirtmek tedarikçi dönüşünü hızlandırır.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun CustomizationSendCard(
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
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Gönder",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Özelleştirme isteği API bağlantısından sonra gerçek endpointy'e gönderilecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

private data class CustomizationSuggestionText(
    val title: String,
    val description: String
)

private fun customizationSuggestionTexts(): List<CustomizationSuggestionText> {
    return listOf(
        CustomizationSuggestionText(
            title = "Renk / Malzeme",
            description = "Ürün için özel renk, malzeme ve yüzey seçenekleri hakkında teklif almak istiyorum."
        ),
        CustomizationSuggestionText(
            title = "Ölçü / Teknik Detay",
            description = "Ürün ölçülerinin ve teknik detayların ihtiyacıma göre özelleştirilmesini istiyorum."
        ),
        CustomizationSuggestionText(
            title = "Ambalaj / Logo",
            description = "Ürün ambalajı, logo baskısı ve etiketleme seçenekleriyle birlikte fiyat almak istiyorum."
        ),
        CustomizationSuggestionText(
            title = "Özel Üretim",
            description = "Bu ürünün özel üretim koşulları, minimum sipariş miktarı ve teslim süresi hakkında bilgi almak istiyorum."
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomizationRequestScreenPreview() {
    BbTheme {
        CustomizationRequestScreen()
    }
}

