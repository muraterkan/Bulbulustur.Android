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
import androidx.compose.material.icons.outlined.Science
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
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun SampleRequestScreen(
    productId: Int = 1,
    productName: String = "Square Silver Starlight Chain Shirt Collar Anti-Blood Brooch",
    companyName: String = "Anadolu Tedarik",
    onBackClick: () -> Unit = {},
    onSendClick: (
        quantity: String,
        detail: String
    ) -> Unit = { _, _ -> }
) {
    val quantity = remember {
        mutableStateOf("1")
    }

    val detail = remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "a2568540-dd6c-4296-a2a2-0cea6adf103f", fallback = "Numune Talebi"),
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
                SampleRequestHeader(
                    productName = productName
                )
            }

            item {
                SampleProductSummaryCard(
                    productName = productName,
                    companyName = companyName
                )
            }

            item {
                BbSectionHeader(
                    title = "Numune Detayları",
                    subtitle = "Kaç adet numune istediğinizi ve özel notlarınızı yazın"
                )
            }

            item {
                SampleRequestTextField(
                    value = quantity.value,
                    onValueChange = {
                        quantity.value = it
                    },
                    label = BBLocalization.Current.Get(key = "64f1e179-caee-4a60-9500-d35fbc4ed554", fallback = "Miktar"),
                    placeholder = "1",
                    icon = Icons.Outlined.Inventory2
                )
            }

            item {
                SampleRequestLongTextField(
                    value = detail.value,
                    onValueChange = {
                        detail.value = it
                    },
                    label = BBLocalization.Current.Get(key = "a997a8c2-5f4b-45e1-9aae-40c652406fcb", fallback = "Diğer Detaylar"),
                    placeholder = BBLocalization.Current.Get(key = "ba4941f2-9a1a-4bba-a9ba-cf1987d77308", fallback = "Bilmemiz gereken başka bir şey var mı? Tüm detayları buraya yazın.")
                )
            }

            item {
                SampleRequestHintCard()
            }

            item {
                SampleRequestSendCard(
                    onSendClick = {
                        onSendClick(
                            quantity.value,
                            detail.value
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
private fun SampleRequestHeader(
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
                    imageVector = Icons.Outlined.Science,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = BBLocalization.Current.Get(key = "a2568540-dd6c-4296-a2a2-0cea6adf103f", fallback = "Numune Talebi"),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Numune İsteği Oluştur",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Toptan alım öncesinde ürünü incelemek için tedarikçiden numune talep edin.",
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
private fun SampleProductSummaryCard(
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
                    text = "Numune Fiyatı: 20 $",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun SampleRequestHintCard() {
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
                imageVector = Icons.Outlined.LocalShipping,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Numune talebinizde hedef kullanım amacını, istediğiniz adet bilgisini ve teslimat notlarını belirtmeniz tedarikçi dönüşünü hızlandırır.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SampleRequestTextField(
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
private fun SampleRequestLongTextField(
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
private fun SampleRequestSendCard(
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
                    text = "Numune isteği API bağlantısından sonra gerçek endpointy'e gönderilecek.",
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

@Preview(showBackground = true)
@Composable
private fun SampleRequestScreenPreview() {
    BbTheme {
        SampleRequestScreen()
    }
}

