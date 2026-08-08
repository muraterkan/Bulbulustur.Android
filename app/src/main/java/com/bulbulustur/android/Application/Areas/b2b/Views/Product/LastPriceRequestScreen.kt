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
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectOption
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescUnitDTO

@Composable
fun LastPriceRequestScreen(
    productId: Int,
    productName: String,
    companyName: String,
    currentPriceLabel: String,
    paymentTerms: List<SystemDescPaymentTermDTO>,
    units: List<SystemDescUnitDTO>,
    onBackClick: () -> Unit,
    onSendClick: (
        quantity: String,
        unitId: String,
        targetPrice: String,
        paymentTermId: String,
        deliveryTarget: String,
        detail: String
    ) -> Unit
) {
    val quantity = remember { mutableStateOf("") }
    val unitId = remember { mutableStateOf("") }
    val targetPrice = remember { mutableStateOf("") }
    val paymentTermId = remember { mutableStateOf("") }
    val deliveryTarget = remember { mutableStateOf("") }
    val detail = remember { mutableStateOf("") }

    val unitOptions = units
        .filter { it.SystemDescUnitId > 0 && it.Content.isNotBlank() }
        .sortedBy { it.Sequence }
        .map {
            BbSelectOption(
                value = it.SystemDescUnitId.toString(),
                text = if (it.Symbol.isNotBlank()) "${it.Content} (${it.Symbol})" else it.Content
            )
        }

    val paymentTermOptions = paymentTerms
        .filter { it.SystemDescPaymentTermId > 0 && it.Content.isNotBlank() }
        .sortedBy { it.Content }
        .map {
            BbSelectOption(
                value = it.SystemDescPaymentTermId.toString(),
                text = it.Content
            )
        }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(
                    key = "7b2a3a2b-db92-4545-8079-19e0cc8d870d",
                    fallback = "Son Fiyat Talebi"
                ),
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
                LastPriceRequestHeader(productName = productName)
            }

            item {
                LastPriceProductSummaryCard(
                    productId = productId,
                    productName = productName,
                    companyName = companyName,
                    currentPriceLabel = currentPriceLabel
                )
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "c179b226-774a-4b79-bcc5-5b4fbb580ae6",
                        fallback = "Talep Detayları"
                    ),
                    subtitle = BBLocalization.Current.Get(
                        key = "b83165cc-5107-45c7-8fc8-b3cc324e184c",
                        fallback = "Son fiyat alabilmek için miktar ve beklentilerinizi yazın"
                    )
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    LastPriceTextField(
                        value = quantity.value,
                        onValueChange = { quantity.value = it },
                        label = BBLocalization.Current.Get(
                            key = "64f1e179-caee-4a60-9500-d35fbc4ed554",
                            fallback = "Miktar"
                        ),
                        placeholder = BBLocalization.Current.Get(
                            key = "3244645a-2875-455d-adcb-bb6d35900a11",
                            fallback = "Örn. 1000"
                        ),
                        icon = Icons.Outlined.Inventory2,
                        modifier = Modifier.weight(1f)
                    )

                    BbSelectInput(
                        selectedValue = unitId.value,
                        onValueChange = { unitId.value = it },
                        options = unitOptions,
                        label = BBLocalization.Current.Get(
                            key = "8c9bc441-0d68-4f53-9549-179f61d7ece0",
                            fallback = "Birim"
                        ),
                        placeholder = BBLocalization.Current.Get(
                            key = "723120da-c41c-4722-8827-f0bce1d29c34",
                            fallback = "Birim seçiniz"
                        ),
                        enabled = unitOptions.isNotEmpty(),
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
                        value = targetPrice.value,
                        onValueChange = { targetPrice.value = it },
                        label = BBLocalization.Current.Get(
                            key = "da75d718-6804-45ca-b211-efc540fba53d",
                            fallback = "Hedef Fiyat"
                        ),
                        placeholder = BBLocalization.Current.Get(
                            key = "04dbf174-0666-41b1-957d-149566da5b81",
                            fallback = "Örn. 18"
                        ),
                        icon = Icons.Outlined.PriceCheck,
                        modifier = Modifier.weight(1f)
                    )

                    BbSelectInput(
                        selectedValue = paymentTermId.value,
                        onValueChange = { paymentTermId.value = it },
                        options = paymentTermOptions,
                        label = BBLocalization.Current.Get(
                            key = "0ce51541-2adb-4cf7-91be-d1fcb7ffe88a",
                            fallback = "Ödeme Şartı"
                        ),
                        placeholder = BBLocalization.Current.Get(
                            key = "c89a68fc-73df-440a-b534-d51ce207c623",
                            fallback = "Ödeme şartı seçiniz"
                        ),
                        enabled = paymentTermOptions.isNotEmpty(),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                LastPriceTextField(
                    value = deliveryTarget.value,
                    onValueChange = { deliveryTarget.value = it },
                    label = BBLocalization.Current.Get(
                        key = "79063e0f-af2c-4425-9c4a-90140dd6493f",
                        fallback = "Teslimat Hedefi"
                    ),
                    placeholder = BBLocalization.Current.Get(
                        key = "def11977-0637-45bf-bc3f-2000d823a075",
                        fallback = "İstanbul / depo"
                    ),
                    icon = Icons.Outlined.LocalShipping,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                LastPriceLongTextField(
                    value = detail.value,
                    onValueChange = { detail.value = it },
                    label = BBLocalization.Current.Get(
                        key = "a997a8c2-5f4b-45e1-9aae-40c652406fcb",
                        fallback = "Diğer Detaylar"
                    ),
                    placeholder = BBLocalization.Current.Get(
                        key = "11e55e57-2aa4-4d3e-9917-5e50eff3fae4",
                        fallback = "Bilmemiz gereken başka bir şey var mı? Miktar, hedef fiyat, ödeme ve teslimat beklentinizi yazın."
                    )
                )
            }

            item {
                LastPriceSuggestionChips(
                    onSuggestionClick = { detail.value = it }
                )
            }

            item {
                LastPriceHintCard()
            }

            item {
                LastPriceSendCard(
                    onSendClick = {
                        onSendClick(
                            quantity.value,
                            unitId.value,
                            targetPrice.value,
                            paymentTermId.value,
                            deliveryTarget.value,
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
private fun LastPriceRequestHeader(productName: String) {
    BbCard(modifier = Modifier.fillMaxWidth()) {
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
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = BBLocalization.Current.Get(
                        key = "1cfc3769-add7-41d6-b18b-117466c6e19f",
                        fallback = "Son Fiyat"
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = BBLocalization.Current.Get(
                    key = "86986db4-eff0-40c1-9ca5-319486ed2651",
                    fallback = "Son Fiyat İsteği Oluştur"
                ),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "bd755d12-fd31-4418-928c-383c6160beed",
                    fallback = "Toptan alım miktarınıza göre tedarikçiden son fiyat, ödeme ve teslimat şartları için teklif isteyin."
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (productName.isNotBlank()) {
                BbChip(
                    text = productName,
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun LastPriceProductSummaryCard(
    productId: Int,
    productName: String,
    companyName: String,
    currentPriceLabel: String
) {
    BbCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = BBLocalization.Current.Get(
                    key = "37f5db70-845d-4498-96d4-fb3a2d29326c",
                    fallback = "Ürün"
                ),
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
                if (productId > 0) {
                    BbChip(
                        text = "Ürün No: $productId",
                        selected = false,
                        onClick = {}
                    )
                }

                if (companyName.isNotBlank()) {
                    BbChip(
                        text = companyName,
                        selected = false,
                        onClick = {}
                    )
                }

                if (currentPriceLabel.isNotBlank()) {
                    BbChip(
                        text = "Mevcut Fiyat: $currentPriceLabel",
                        selected = false,
                        onClick = {}
                    )
                }

                BbChip(
                    text = BBLocalization.Current.Get(
                        key = "4f09d5f4-e175-4d89-b2d4-7767a4b3298c",
                        fallback = "Toptan Teklif"
                    ),
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
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
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
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
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
private fun LastPriceSuggestionChips(onSuggestionClick: (String) -> Unit) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        lastPriceSuggestionTexts().forEach { suggestion ->
            BbChip(
                text = suggestion.title,
                selected = false,
                onClick = { onSuggestionClick(suggestion.description) }
            )
        }
    }
}

@Composable
private fun LastPriceHintCard() {
    BbCard(modifier = Modifier.fillMaxWidth()) {
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
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "988e84b5-c895-406e-8ce1-7b572535b02b",
                    fallback = "Net miktar, hedef fiyat, ödeme yöntemi ve teslimat beklentisi yazarsanız tedarikçi daha doğru son fiyat verebilir."
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun LastPriceSendCard(onSendClick: () -> Unit) {
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

            Text(
                text = BBLocalization.Current.Get(
                    key = "1bba90af-aa63-41f8-bd0d-b51c4477afd7",
                    fallback = "Gönder"
                ),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
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
            title = BBLocalization.Current.Get(
                key = "b03a59f3-d2c7-466f-a0c8-a5954ebe4fff",
                fallback = "Toplu Alım"
            ),
            description = BBLocalization.Current.Get(
                key = "cb501efa-bdcc-443a-b193-6a459368739f",
                fallback = "Belirttiğim miktar için en iyi son fiyatı rica ederim."
            )
        ),
        LastPriceSuggestionText(
            title = BBLocalization.Current.Get(
                key = "0ce51541-2adb-4cf7-91be-d1fcb7ffe88a",
                fallback = "Ödeme Şartı"
            ),
            description = "Peşin ve vadeli ödeme seçeneklerine göre son fiyat bilgisini paylaşabilir misiniz?"
        ),
        LastPriceSuggestionText(
            title = BBLocalization.Current.Get(
                key = "fa83755d-d80d-4f50-bb5a-631ef8331078",
                fallback = "Teslimat"
            ),
            description = BBLocalization.Current.Get(
                key = "5618cfcf-13c3-4b66-9fb9-b23ab4fdbf11",
                fallback = "Teslimat süresi, kargo/lojistik maliyeti ve hedef teslimat bilgisiyle birlikte son fiyat rica ederim."
            )
        ),
        LastPriceSuggestionText(
            title = BBLocalization.Current.Get(
                key = "ab778422-abe4-4635-b214-9d43ec55750c",
                fallback = "Karşılaştırma"
            ),
            description = BBLocalization.Current.Get(
                key = "359aaac9-5dbe-4f23-93a9-4a2f2470d824",
                fallback = "Aynı ürün için alternatif kalite veya ambalaj seçenekleri varsa fiyat karşılaştırması almak isterim."
            )
        )
    )
}