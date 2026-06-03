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
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material.icons.outlined.Style
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
fun CustomizationRequestScreen(
    productId: Int = 1,
    productName: String = "Square Silver Starlight Chain Shirt Collar Anti-Blood Brooch",
    companyName: String = "Anadolu Tedarik",
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

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
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
                title = "Özelleştirme detayları",
                subtitle = "Tedarikçinin doğru dönüş yapabilmesi için değişiklikleri açık yazın"
            )
        }

        item {
            CustomizationLongTextField(
                value = detail.value,
                onValueChange = {
                    detail.value = it
                },
                label = "Diğer detaylar",
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
                title = "Hızlı detay alanları",
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
            Spacer(modifier = Modifier.height(BbSpacing.xl))
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
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Style,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Özelleştirme Talebi",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Ürün özelleştirme isteği oluştur",
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
            modifier = Modifier.padding(BbSpacing.md),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Text(
                text = "Tedarikçi",
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
                    text = "Model No: ABS-778",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Özel üretim desteklenir",
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
            focusedIndicatorColor = BbColors.Primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = BbColors.Primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = BbColors.Primary
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
private fun CustomizationSuggestionChips(
    onSuggestionClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        customizationSuggestionTexts().forEach { suggestion ->
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
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.LocalOffer,
                contentDescription = null,
                tint = BbColors.Primary
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
                    text = "Özelleştirme isteği API bağlantısından sonra gerçek endpoint’e gönderilecek.",
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