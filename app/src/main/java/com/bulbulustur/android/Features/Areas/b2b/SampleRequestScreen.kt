package com.bulbulustur.android.Features.Areas.b2b

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
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbChip
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.components.BbSectionHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTheme

@Composable
fun SampleRequestScreen(
    productId: Int = 1,
    productName: String = "Square Silver Starlight Chain Shirt Collar Anti-Blood Brooch",
    companyName: String = "Anadolu Tedarik",
    onBackClick: () -> Unit = {},
    onSendClick: () -> Unit = {}
) {
    val quantity = remember {
        mutableStateOf("1")
    }

    val detail = remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = BbColors.SurfaceSoft,
        topBar = {
            BbInnerPageHeader(
                title = "Numune Talebi",
                onBackClick = onBackClick
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
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
                    label = "Miktar",
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
                    label = "Diğer Detaylar",
                    placeholder = "Bilmemiz gereken başka bir şey var mı? Tüm detayları buraya yazın."
                )
            }

            item {
                SampleRequestHintCard()
            }

            item {
                SampleRequestSendCard(
                    onSendClick = onSendClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(BbSpacing.Space4))
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
            modifier = Modifier.padding(BbSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Science,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Numune Talebi",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Numune İsteği Oluştur",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Toptan alım öncesinde ürünü incelemek için tedarikçiden numune talep edin.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextMuted
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
            modifier = Modifier.padding(BbSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                .padding(BbSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.LocalShipping,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Text(
                text = "Numune talebinizde hedef kullanım amacını, istediğiniz adet bilgisini ve teslimat notlarını belirtmeniz tedarikçi dönüşünü hızlandırır.",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted,
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
            focusedTextColor = BbColors.TextStrong,
            unfocusedTextColor = BbColors.TextStrong,
            focusedContainerColor = BbColors.Surface,
            unfocusedContainerColor = BbColors.Surface,
            focusedIndicatorColor = BbColors.Primary,
            unfocusedIndicatorColor = BbColors.Border,
            focusedLabelColor = BbColors.Primary,
            unfocusedLabelColor = BbColors.TextMuted,
            cursorColor = BbColors.Primary
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
            focusedTextColor = BbColors.TextStrong,
            unfocusedTextColor = BbColors.TextStrong,
            focusedContainerColor = BbColors.Surface,
            unfocusedContainerColor = BbColors.Surface,
            focusedIndicatorColor = BbColors.Primary,
            unfocusedIndicatorColor = BbColors.Border,
            focusedLabelColor = BbColors.Primary,
            unfocusedLabelColor = BbColors.TextMuted,
            cursorColor = BbColors.Primary
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
                .padding(BbSpacing.Space5),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
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
                    color = BbColors.TextStrong
                )

                Text(
                    text = "Numune isteği API bağlantısından sonra gerçek endpoint’e gönderilecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
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

@Preview(showBackground = true)
@Composable
private fun SampleRequestScreenPreview() {
    BbTheme {
        SampleRequestScreen()
    }
}