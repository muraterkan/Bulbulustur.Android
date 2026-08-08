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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
    onSendClick: (
        detail: String,
        colorMaterial: String,
        sizeTechnical: String,
        packageLogo: String
    ) -> Unit = { _, _, _, _ -> }
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
                title = BBLocalization.Current.Get(key = "68960cea-67f2-4a28-a612-050ebc6f43aa", fallback = "Özelleştirme Talebi"),
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
                    title = BBLocalization.Current.Get(key = "0cc7e89c-1933-4ec9-8cd4-f3cf9a6d55b4", fallback = "Özelleştirme Detayları"),
                    subtitle = BBLocalization.Current.Get(key = "623167d9-dcfe-4ff4-ba73-72051793fe97", fallback = "Tedarikçinin doğru dönüş yapabilmesi için değişiklikleri açık yazın")
                )
            }

            item {
                CustomizationLongTextField(
                    value = detail.value,
                    onValueChange = {
                        detail.value = it
                    },
                    label = BBLocalization.Current.Get(key = "a997a8c2-5f4b-45e1-9aae-40c652406fcb", fallback = "Diğer Detaylar"),
                    placeholder = BBLocalization.Current.Get(key = "71fd9411-9b01-4e07-b0b9-67d79e1b3082", fallback = "Talep ettiğiniz ürün özelleştirmesi hakkında daha fazla detay verin.")
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
                    title = BBLocalization.Current.Get(key = "b9c5b4b4-1ba6-4a6a-95be-9e08acd2f3c8", fallback = "Hızlı Detay Alanları"),
                    subtitle = BBLocalization.Current.Get(key = "e6ed8a08-71de-4aae-880b-fa6dc43c0981", fallback = "İsterseniz özelleştirme bilgisini ayrı ayrı da yazabilirsiniz")
                )
            }

            item {
                CustomizationTextField(
                    value = colorMaterial.value,
                    onValueChange = {
                        colorMaterial.value = it
                    },
                    label = BBLocalization.Current.Get(key = "085e068b-d094-4f24-bbbd-dba4922bdb44", fallback = ""),
                    placeholder = BBLocalization.Current.Get(key = "a1cb90fd-1d36-4636-bf1e-dd5c326233f0", fallback = "Örn. siyah, metal, mat yüzey, titanyum"),
                    icon = Icons.Outlined.ColorLens
                )
            }

            item {
                CustomizationTextField(
                    value = sizeTechnical.value,
                    onValueChange = {
                        sizeTechnical.value = it
                    },
                    label = BBLocalization.Current.Get(key = "c876b0ed-5ded-4383-b970-851148e6fcfe", fallback = "Ölçü / Teknik Detay"),
                    placeholder = BBLocalization.Current.Get(key = "a497ea17-1750-4806-b5b1-2ab57da49fc2", fallback = "Örn. 30x20 cm, kalınlık, bağlantı detayı"),
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
                    placeholder = BBLocalization.Current.Get(key = "92d96cd7-7c2d-4270-9775-f98d41b012ae", fallback = "Örn. logolu ambalaj, özel kutu, etiket baskısı"),
                    icon = Icons.Outlined.Style
                )
            }

            item {
                CustomizationHintCard()
            }

            item {
                CustomizationSendCard(
                    onSendClick = {
                        onSendClick(
                            detail.value,
                            colorMaterial.value,
                            sizeTechnical.value,
                            packageLogo.value
                        )
                    }
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
                    text = BBLocalization.Current.Get(key = "68960cea-67f2-4a28-a612-050ebc6f43aa", fallback = "Özelleştirme Talebi"),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "be3c60fc-fae3-448b-a846-2e4fdb57b58a", fallback = "Ürün Özelleştirme İsteği Oluştur"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "a482ae05-d941-48b0-9086-dd22ccb60878", fallback = "Ürün üzerinde ölçü, renk, malzeme, ambalaj, logo veya üretim detayları için tedarikçiye özel talep gönderin."),
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
                text = BBLocalization.Current.Get(key = "95ab742c-6bb3-47da-bb8b-c37b3a979c24", fallback = "Tedarikçi"),
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
                    text = BBLocalization.Current.Get(key = "6d147934-75a3-4dba-b966-b1f6bd1bc8a0", fallback = "Özel Üretim Desteklenir"),
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
                text = BBLocalization.Current.Get(key = "c51fc92c-6fb0-407c-83c9-e259143d8c78", fallback = "Özelleştirme talebinde ölçü, renk, malzeme, kullanım amacı ve minimum sipariş miktarını belirtmek tedarikçi dönüşünü hızlandırır."),
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
                    text = BBLocalization.Current.Get(key = "1bba90af-aa63-41f8-bd0d-b51c4477afd7", fallback = ""),
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
            title = BBLocalization.Current.Get(key = "085e068b-d094-4f24-bbbd-dba4922bdb44", fallback = ""),
            description = BBLocalization.Current.Get(key = "1ec38a2a-bc67-4b05-970b-bfcc9d70b931", fallback = "Ürün için özel renk, malzeme ve yüzey seçenekleri hakkında teklif almak istiyorum.")
        ),
        CustomizationSuggestionText(
            title = BBLocalization.Current.Get(key = "c876b0ed-5ded-4383-b970-851148e6fcfe", fallback = "Ölçü / Teknik Detay"),
            description = BBLocalization.Current.Get(key = "c6e6da8c-40f8-4587-a933-5c526cbbf5d3", fallback = "Ürün ölçülerinin ve teknik detayların ihtiyacıma göre özelleştirilmesini istiyorum.")
        ),
        CustomizationSuggestionText(
            title = "Ambalaj / Logo",
            description = BBLocalization.Current.Get(key = "6245a8fb-1fa4-419c-9586-5940cb4c61cb", fallback = "Ürün ambalajı, logo baskısı ve etiketleme seçenekleriyle birlikte fiyat almak istiyorum.")
        ),
        CustomizationSuggestionText(
            title = BBLocalization.Current.Get(key = "fdc80137-c3bb-4017-a138-58ab80835b9e", fallback = "Özel Üretim"),
            description = BBLocalization.Current.Get(key = "c2fe6514-7b7f-400e-bbba-fc9be83a1134", fallback = "Bu ürünün özel üretim koşulları, minimum sipariş miktarı ve teslim süresi hakkında bilgi almak istiyorum.")
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

