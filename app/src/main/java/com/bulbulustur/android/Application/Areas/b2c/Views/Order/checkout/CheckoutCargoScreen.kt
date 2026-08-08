package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun CheckoutCargoScreen(
    onBackClick: () -> Unit = {},
    onContinueClick: (Map<Int, CheckoutCargoOptionItem>) -> Unit = {}
) {
    val screenData = remember {
        getCheckoutCargoScreenData()
    }

    val selectedCargoOptions = remember {
        mutableStateMapOf<Int, Int>().apply {
            screenData.storeCargoGroups.forEach { cargoGroup ->
                this[cargoGroup.storeId] = cargoGroup.cargoOptions.first().id
            }
        }
    }

    val selectedCargoOptionMap = remember(selectedCargoOptions.toMap()) {
        screenData.storeCargoGroups.associate { cargoGroup ->
            val selectedCargoOptionId = selectedCargoOptions[cargoGroup.storeId]
            val selectedCargoOption = cargoGroup.cargoOptions.first {
                it.id == selectedCargoOptionId
            }

            cargoGroup.storeId to selectedCargoOption
        }
    }

    val totalCargoPriceText = remember(selectedCargoOptionMap) {
        val totalCargoPrice = selectedCargoOptionMap.values.sumOf {
            it.priceValue
        }

        if (totalCargoPrice == 0.0) {
            BBLocalization.Current.Get(key = "bebf2e88-e365-492b-9376-25547df90b7c", fallback = "Ücretsiz")
        } else {
            "₺${String.format("%.2f", totalCargoPrice).replace(".", ",")}"
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 14.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                item {
                    CheckoutCargoTopBar(
                        onBackClick = onBackClick
                    )
                }

                item {
                    CheckoutProgressCard(
                        currentStep = "2",
                        title = BBLocalization.Current.Get(key = "4a269f07-f7cb-47b1-ad1d-70a97dbc2156", fallback = "Kargo seçimi"),
                        description = BBLocalization.Current.Get(key = "59a23ed4-dda0-42e8-840b-acd1078f0ea2", fallback = "Mağaza bazlı kargo seçeneklerini belirle.")
                    )
                }

                item {
                    CheckoutCargoAddressCard(
                        address = screenData.deliveryAddress
                    )
                }

                item {
                    CheckoutCargoSectionTitle(
                        title = BBLocalization.Current.Get(key = "f9544e16-222d-4ce8-a182-f603e604d29c", fallback = "Kargo paketleri"),
                        description = BBLocalization.Current.Get(key = "0059ee39-71df-4650-aca4-6057c0c5c0ca", fallback = "Her mağaza kendi kargo seçeneğiyle ayrı paketlenebilir.")
                    )
                }

                items(screenData.storeCargoGroups) { cargoGroup ->
                    CheckoutStoreCargoGroupCard(
                        cargoGroup = cargoGroup,
                        selectedCargoOptionId = selectedCargoOptions[cargoGroup.storeId] ?: 0,
                        onCargoOptionSelect = { cargoOption ->
                            selectedCargoOptions[cargoGroup.storeId] = cargoOption.id
                        }
                    )
                }

                item {
                    CheckoutCargoInfoCard()
                }
            }

            CheckoutCargoBottomBar(
                totalCargoPriceText = totalCargoPriceText,
                onContinueClick = {
                    onContinueClick(selectedCargoOptionMap)
                }
            )
        }
    }
}

@Composable
private fun CheckoutCargoTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BbIconBox(
            modifier = Modifier.clickable {
                onBackClick()
            },
            size = BbIconBoxSize.Medium,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "4a269f07-f7cb-47b1-ad1d-70a97dbc2156", fallback = "Kargo seçimi"),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = BBLocalization.Current.Get(key = "ec7e3d00-810a-4ecf-85cd-be5550f74e91", fallback = "Checkout adım 2 / 4"),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutProgressCard(
    currentStep: String,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BbIconBox(
                size = BbIconBoxSize.Xl,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text(
                    text = currentStep,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
private fun CheckoutCargoAddressCard(
    address: CheckoutCargoDeliveryAddress
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "fa3df4de-7069-4a3d-9dac-5a4ea9b88b65", fallback = "Teslimat adresi"),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = address.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = address.fullAddress,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutStoreCargoGroupCard(
    cargoGroup: CheckoutStoreCargoGroup,
    selectedCargoOptionId: Int,
    onCargoOptionSelect: (CheckoutCargoOptionItem) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space4)
        ) {
            CheckoutStoreCargoHeader(
                cargoGroup = cargoGroup
            )

            Spacer(modifier = Modifier.height(14.dp))

            cargoGroup.cargoOptions.forEach { cargoOption ->
                CheckoutCargoOptionRow(
                    cargoOption = cargoOption,
                    isSelected = selectedCargoOptionId == cargoOption.id,
                    onClick = {
                        onCargoOptionSelect(cargoOption)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun CheckoutStoreCargoHeader(
    cargoGroup: CheckoutStoreCargoGroup
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BBSpacing.Space12)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = cargoGroup.storeLogoText,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = cargoGroup.storeName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${cargoGroup.productCount} ürün . ${cargoGroup.packageCount} paket",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutCargoOptionRow(
    cargoOption: CheckoutCargoOptionItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val contentColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(containerColor)
            .clickable {
                onClick()
            }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = cargoOption.companyName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = cargoOption.deliveryEstimateText,
                style = MaterialTheme.typography.bodySmall,
                color = contentColor
            )

            if (cargoOption.note.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = cargoOption.note,
                    style = MaterialTheme.typography.labelSmall,
                    color = contentColor
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = cargoOption.priceText,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )
    }
}

@Composable
private fun CheckoutCargoInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space4)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "9d03853a-1657-49d2-87e4-a30a6fc1f26f", fallback = "Kargo bilgilendirmesi"),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = BBLocalization.Current.Get(key = "a3a38516-5c3f-4c88-816c-8b516e54e990", fallback = "Siparişin birden fazla mağazadan oluşuyorsa ürünler ayrı paketler halinde gönderilebilir. Teslimat süreleri mağaza ve kargo firmasına göre değişebilir."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutCargoBottomBar(
    totalCargoPriceText: String,
    onContinueClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 12.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "2456f709-8316-4109-99f7-d6d35341d61b", fallback = "Kargo toplamı"),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = totalCargoPriceText,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        onContinueClick()
                    }
                    .padding(
                        horizontal = 18.dp,
                        vertical = 12.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "9fe1660d-5aeb-4808-bdbb-0d3be6fca8b9", fallback = "Ödemeye geç"),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun CheckoutCargoSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class CheckoutCargoScreenData(
    val deliveryAddress: CheckoutCargoDeliveryAddress,
    val storeCargoGroups: List<CheckoutStoreCargoGroup>
)

data class CheckoutCargoDeliveryAddress(
    val title: String,
    val fullAddress: String
)

data class CheckoutStoreCargoGroup(
    val storeId: Int,
    val storeName: String,
    val storeLogoText: String,
    val productCount: Int,
    val packageCount: Int,
    val cargoOptions: List<CheckoutCargoOptionItem>
)

data class CheckoutCargoOptionItem(
    val id: Int,
    val companyName: String,
    val deliveryEstimateText: String,
    val priceText: String,
    val priceValue: Double,
    val note: String
)

private fun getCheckoutCargoScreenData(): CheckoutCargoScreenData {
    return CheckoutCargoScreenData(
        deliveryAddress = CheckoutCargoDeliveryAddress(
            title = BBLocalization.Current.Get(key = "8f6d0dd3-f45d-4fce-a684-47ee00038be8", fallback = "Ev adresim"),
            fullAddress = "Kızılay Mah. Atatürk Bulvarı No: 12 Daire: 8 Çankaya / Ankara"
        ),
        storeCargoGroups = listOf(
            CheckoutStoreCargoGroup(
                storeId = 1,
                storeName = "Ortobella Store",
                storeLogoText = "OS",
                productCount = 2,
                packageCount = 1,
                cargoOptions = listOf(
                    CheckoutCargoOptionItem(
                        id = 1,
                        companyName = BBLocalization.Current.Get(key = "e592d42e-ccb1-4c88-b47e-89a6757970f5", fallback = "Yurtiçi Kargo"),
                        deliveryEstimateText = "Tahmini teslimat: 1-3 iş günü",
                        priceText = "₺49,90",
                        priceValue = 49.90,
                        note = BBLocalization.Current.Get(key = "8f015fcc-7ded-464e-82c0-475faccfcd1f", fallback = "Hızlı gönderim")
                    ),
                    CheckoutCargoOptionItem(
                        id = 2,
                        companyName = BBLocalization.Current.Get(key = "e905eb3f-e2ff-4923-9b1d-1c3e792e1e08", fallback = "Standart Kargo"),
                        deliveryEstimateText = "Tahmini teslimat: 3-5 iş günü",
                        priceText = BBLocalization.Current.Get(key = "bebf2e88-e365-492b-9376-25547df90b7c", fallback = "Ücretsiz"),
                        priceValue = 0.0,
                        note = BBLocalization.Current.Get(key = "f77449bf-f01e-4599-8870-dcdb71ea1ab3", fallback = "Mağaza kampanyası")
                    )
                )
            ),
            CheckoutStoreCargoGroup(
                storeId = 2,
                storeName = "Moda Nova",
                storeLogoText = "MN",
                productCount = 2,
                packageCount = 1,
                cargoOptions = listOf(
                    CheckoutCargoOptionItem(
                        id = 3,
                        companyName = BBLocalization.Current.Get(key = "e592d42e-ccb1-4c88-b47e-89a6757970f5", fallback = "Yurtiçi Kargo"),
                        deliveryEstimateText = "Tahmini teslimat: 2-4 iş günü",
                        priceText = "₺39,90",
                        priceValue = 39.90,
                        note = ""
                    ),
                    CheckoutCargoOptionItem(
                        id = 4,
                        companyName = BBLocalization.Current.Get(key = "1dd5843e-4611-4525-99ad-2f823696ae24", fallback = "Ekonomik Kargo"),
                        deliveryEstimateText = "Tahmini teslimat: 4-6 iş günü",
                        priceText = "₺19,90",
                        priceValue = 19.90,
                        note = BBLocalization.Current.Get(key = "c84a464a-e8ee-418b-b5b9-6f35626862d5", fallback = "Ekonomik seçenek")
                    )
                )
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckoutCargoScreenPreview() {
    MaterialTheme {
        CheckoutCargoScreen()
    }
}


