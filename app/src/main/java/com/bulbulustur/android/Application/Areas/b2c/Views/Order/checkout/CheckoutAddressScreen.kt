package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun CheckoutAddressScreen(
    onBackClick: () -> Unit = {},
    onAddAddressClick: () -> Unit = {},
    onEditAddressClick: (CheckoutAddressItem) -> Unit = {},
    onContinueClick: (CheckoutAddressItem) -> Unit = {}
) {
    val screenData = remember {
        getCheckoutAddressScreenData()
    }

    var selectedAddressId by remember {
        mutableIntStateOf(screenData.addresses.first().id)
    }

    val selectedAddress = remember(selectedAddressId, screenData.addresses) {
        screenData.addresses.first {
            it.id == selectedAddressId
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
                    CheckoutAddressTopBar(
                        onBackClick = onBackClick
                    )
                }

                item {
                    CheckoutProgressCard(
                        currentStep = "1",
                        title = "Teslimat adresi",
                        description = "Siparişin gönderileceĞi adresi seç."
                    )
                }

                item {
                    CheckoutAddressSummaryCard(
                        summary = screenData.summary
                    )
                }

                item {
                    CheckoutAddressSectionTitle(
                        title = "Adreslerim",
                        description = "Teslimat için kayıtlı adreslerinden birini seç."
                    )
                }

                items(screenData.addresses) { address ->
                    CheckoutAddressCard(
                        address = address,
                        isSelected = selectedAddressId == address.id,
                        onSelectClick = {
                            selectedAddressId = address.id
                        },
                        onEditAddressClick = {
                            onEditAddressClick(address)
                        }
                    )
                }

                item {
                    CheckoutAddAddressCard(
                        onAddAddressClick = onAddAddressClick
                    )
                }

                item {
                    CheckoutInvoiceInfoCard()
                }
            }

            CheckoutAddressBottomBar(
                selectedAddress = selectedAddress,
                onContinueClick = {
                    onContinueClick(selectedAddress)
                }
            )
        }
    }
}

@Composable
private fun CheckoutAddressTopBar(
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
                text = "â€¹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Teslimat adresi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Checkout adım 1 / 4",
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
private fun CheckoutAddressSummaryCard(
    summary: CheckoutAddressOrderSummary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CheckoutAddressSummaryMiniCard(
            modifier = Modifier.weight(1f),
            title = "${summary.productCount}",
            subtitle = "ürün"
        )

        CheckoutAddressSummaryMiniCard(
            modifier = Modifier.weight(1f),
            title = summary.totalPriceText,
            subtitle = "tutar"
        )

        CheckoutAddressSummaryMiniCard(
            modifier = Modifier.weight(1f),
            title = summary.storeCountText,
            subtitle = "maĞaza"
        )
    }
}

@Composable
private fun CheckoutAddressSummaryMiniCard(
    modifier: Modifier,
    title: String,
    subtitle: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutAddressCard(
    address: CheckoutAddressItem,
    isSelected: Boolean,
    onSelectClick: () -> Unit,
    onEditAddressClick: () -> Unit
) {
    val containerColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surface
    }

    val contentColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelectClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = onSelectClick
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = address.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = contentColor
                        )

                        if (address.isDefault) {
                            Spacer(modifier = Modifier.width(8.dp))

                            CheckoutAddressDefaultBadge()
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = address.fullName,
                        style = MaterialTheme.typography.bodySmall,
                        color = contentColor
                    )
                }

                Text(
                    text = "Düzenle",
                    modifier = Modifier.clickable {
                        onEditAddressClick()
                    },
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = address.fullAddress,
                style = MaterialTheme.typography.bodySmall,
                color = contentColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = address.phoneNumber,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor
            )
        }
    }
}

@Composable
private fun CheckoutAddressDefaultBadge() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.76f))
            .padding(
                horizontal = 8.dp,
                vertical = 3.dp
            )
    ) {
        Text(
            text = "Varsayılan",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun CheckoutAddAddressCard(
    onAddAddressClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onAddAddressClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BbIconBox(
                size = BbIconBoxSize.Large,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Yeni adres ekle",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Teslimat için yeni bir adres oluştur.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "â€º",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutInvoiceInfoCard() {
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
                text = "Fatura adresi",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "V1â€™de fatura adresi teslimat adresiyle aynı kabul edilir. Kurumsal fatura ve farklı fatura adresi akışı sonraki adımda genişletilebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutAddressBottomBar(
    selectedAddress: CheckoutAddressItem,
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
                    text = "Seçili adres",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = selectedAddress.title,
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
                    text = "Kargoya geç",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun CheckoutAddressSectionTitle(
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

data class CheckoutAddressScreenData(
    val summary: CheckoutAddressOrderSummary,
    val addresses: List<CheckoutAddressItem>
)

data class CheckoutAddressOrderSummary(
    val productCount: Int,
    val totalPriceText: String,
    val storeCountText: String
)

data class CheckoutAddressItem(
    val id: Int,
    val title: String,
    val fullName: String,
    val phoneNumber: String,
    val fullAddress: String,
    val isDefault: Boolean
)

private fun getCheckoutAddressScreenData(): CheckoutAddressScreenData {
    return CheckoutAddressScreenData(
        summary = CheckoutAddressOrderSummary(
            productCount = 4,
            totalPriceText = "â‚º2.849,60",
            storeCountText = "2"
        ),
        addresses = listOf(
            CheckoutAddressItem(
                id = 1,
                title = "Ev adresim",
                fullName = "Murat Erkan",
                phoneNumber = "+90 555 000 00 00",
                fullAddress = "Kızılay Mah. Atatürk Bulvarı No: 12 Daire: 8 Ã‡ankaya / Ankara",
                isDefault = true
            ),
            CheckoutAddressItem(
                id = 2,
                title = "Ofis",
                fullName = "Murat Erkan",
                phoneNumber = "+90 555 111 11 11",
                fullAddress = "Ä°ş Merkezi Cad. No: 24 Kat: 5 Ã‡ankaya / Ankara",
                isDefault = false
            ),
            CheckoutAddressItem(
                id = 3,
                title = "Kayseri adresi",
                fullName = "Murat Erkan",
                phoneNumber = "+90 555 222 22 22",
                fullAddress = "Melikgazi Mah. Cumhuriyet Cad. No: 7 Melikgazi / Kayseri",
                isDefault = false
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckoutAddressScreenPreview() {
    MaterialTheme {
        CheckoutAddressScreen()
    }
}


