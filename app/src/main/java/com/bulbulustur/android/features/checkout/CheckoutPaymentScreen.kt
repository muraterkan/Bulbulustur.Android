package com.bulbulustur.app.features.checkout

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CheckoutPaymentScreen(
    onBackClick: () -> Unit = {},
    onContinueClick: (CheckoutPaymentSelection) -> Unit = {}
) {
    val screenData = remember {
        getCheckoutPaymentScreenData()
    }

    var selectedPaymentMethodId by remember {
        mutableIntStateOf(screenData.paymentMethods.first().id)
    }

    var cardNumber by remember {
        mutableStateOf("")
    }

    var cardHolderName by remember {
        mutableStateOf("")
    }

    var expireDate by remember {
        mutableStateOf("")
    }

    var cvc by remember {
        mutableStateOf("")
    }

    var saveCard by remember {
        mutableStateOf(false)
    }

    val selectedPaymentMethod = remember(selectedPaymentMethodId, screenData.paymentMethods) {
        screenData.paymentMethods.first {
            it.id == selectedPaymentMethodId
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    CheckoutPaymentTopBar(
                        onBackClick = onBackClick
                    )
                }

                item {
                    CheckoutProgressCard(
                        currentStep = "3",
                        title = "Ödeme",
                        description = "Ödeme yöntemini seç ve kart bilgilerini gir."
                    )
                }

                item {
                    CheckoutPaymentSummaryCard(
                        summary = screenData.summary
                    )
                }

                item {
                    CheckoutPaymentSectionTitle(
                        title = "Ödeme yöntemi",
                        description = "Sipariş için kullanmak istediğin ödeme tipini seç."
                    )
                }

                items(screenData.paymentMethods) { paymentMethod ->
                    CheckoutPaymentMethodCard(
                        paymentMethod = paymentMethod,
                        isSelected = selectedPaymentMethodId == paymentMethod.id,
                        onClick = {
                            selectedPaymentMethodId = paymentMethod.id
                        }
                    )
                }

                if (selectedPaymentMethod.paymentType == CheckoutPaymentType.NewCard) {
                    item {
                        CheckoutNewCardForm(
                            cardNumber = cardNumber,
                            onCardNumberChange = {
                                cardNumber = it
                            },
                            cardHolderName = cardHolderName,
                            onCardHolderNameChange = {
                                cardHolderName = it
                            },
                            expireDate = expireDate,
                            onExpireDateChange = {
                                expireDate = it
                            },
                            cvc = cvc,
                            onCvcChange = {
                                cvc = it
                            },
                            saveCard = saveCard,
                            onSaveCardChange = {
                                saveCard = it
                            }
                        )
                    }
                }

                item {
                    CheckoutSecurePaymentInfoCard()
                }
            }

            CheckoutPaymentBottomBar(
                totalPriceText = screenData.summary.totalPriceText,
                onContinueClick = {
                    onContinueClick(
                        CheckoutPaymentSelection(
                            paymentMethod = selectedPaymentMethod,
                            cardNumber = cardNumber,
                            cardHolderName = cardHolderName,
                            expireDate = expireDate,
                            cvc = cvc,
                            saveCard = saveCard
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun CheckoutPaymentTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
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
                text = "Ödeme",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Checkout adım 3 / 4",
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
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
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
private fun CheckoutPaymentSummaryCard(
    summary: CheckoutPaymentSummary
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
            modifier = Modifier.padding(16.dp)
        ) {
            CheckoutPaymentSummaryRow(
                title = "Ürün toplamı",
                value = summary.productTotalText
            )

            Spacer(modifier = Modifier.height(8.dp))

            CheckoutPaymentSummaryRow(
                title = "Kargo",
                value = summary.cargoTotalText
            )

            Spacer(modifier = Modifier.height(8.dp))

            CheckoutPaymentSummaryRow(
                title = "İndirim",
                value = summary.discountText
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(modifier = Modifier.height(12.dp))

            CheckoutPaymentSummaryRow(
                title = "Ödenecek tutar",
                value = summary.totalPriceText,
                isStrong = true
            )
        }
    }
}

@Composable
private fun CheckoutPaymentSummaryRow(
    title: String,
    value: String,
    isStrong: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = if (isStrong) {
                MaterialTheme.typography.titleSmall
            } else {
                MaterialTheme.typography.bodySmall
            },
            fontWeight = if (isStrong) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = value,
            style = if (isStrong) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodySmall
            },
            fontWeight = if (isStrong) {
                FontWeight.Bold
            } else {
                FontWeight.SemiBold
            },
            color = if (isStrong) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
    }
}

@Composable
private fun CheckoutPaymentMethodCard(
    paymentMethod: CheckoutPaymentMethodItem,
    isSelected: Boolean,
    onClick: () -> Unit
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
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = paymentMethod.iconText,
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
                    text = paymentMethod.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = paymentMethod.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = contentColor
                )
            }
        }
    }
}

@Composable
private fun CheckoutNewCardForm(
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    cardHolderName: String,
    onCardHolderNameChange: (String) -> Unit,
    expireDate: String,
    onExpireDateChange: (String) -> Unit,
    cvc: String,
    onCvcChange: (String) -> Unit,
    saveCard: Boolean,
    onSaveCardChange: (Boolean) -> Unit
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
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CheckoutPaymentSectionTitle(
                title = "Kart bilgileri",
                description = "Kart bilgilerin güvenli ödeme altyapısıyla işlenir."
            )

            CheckoutPaymentTextField(
                value = cardNumber,
                onValueChange = onCardNumberChange,
                placeholder = "Kart numarası"
            )

            CheckoutPaymentTextField(
                value = cardHolderName,
                onValueChange = onCardHolderNameChange,
                placeholder = "Kart üzerindeki isim"
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CheckoutPaymentTextField(
                    modifier = Modifier.weight(1f),
                    value = expireDate,
                    onValueChange = onExpireDateChange,
                    placeholder = "AA/YY"
                )

                CheckoutPaymentTextField(
                    modifier = Modifier.weight(1f),
                    value = cvc,
                    onValueChange = onCvcChange,
                    placeholder = "CVC"
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Kartı sonraki alışverişler için kaydet",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Kart saklama altyapısı aktif olduğunda kullanılacak.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Switch(
                    checked = saveCard,
                    onCheckedChange = onSaveCardChange
                )
            }
        }
    }
}

@Composable
private fun CheckoutPaymentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.clip(RoundedCornerShape(18.dp)),
        placeholder = {
            Text(text = placeholder)
        },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun CheckoutSecurePaymentInfoCard() {
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
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✓",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Güvenli ödeme",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Ödeme adımında 3D Secure ve banka doğrulama süreçleri ödeme sağlayıcısına göre çalışır. Sipariş onayı bir sonraki adımda gösterilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CheckoutPaymentBottomBar(
    totalPriceText: String,
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
                    text = "Ödenecek tutar",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = totalPriceText,
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
                    text = "Özete geç",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun CheckoutPaymentSectionTitle(
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

data class CheckoutPaymentScreenData(
    val summary: CheckoutPaymentSummary,
    val paymentMethods: List<CheckoutPaymentMethodItem>
)

data class CheckoutPaymentSummary(
    val productTotalText: String,
    val cargoTotalText: String,
    val discountText: String,
    val totalPriceText: String
)

data class CheckoutPaymentMethodItem(
    val id: Int,
    val title: String,
    val description: String,
    val iconText: String,
    val paymentType: CheckoutPaymentType
)

data class CheckoutPaymentSelection(
    val paymentMethod: CheckoutPaymentMethodItem,
    val cardNumber: String,
    val cardHolderName: String,
    val expireDate: String,
    val cvc: String,
    val saveCard: Boolean
)

enum class CheckoutPaymentType {
    NewCard,
    SavedCard,
    BankTransfer
}

private fun getCheckoutPaymentScreenData(): CheckoutPaymentScreenData {
    return CheckoutPaymentScreenData(
        summary = CheckoutPaymentSummary(
            productTotalText = "₺2.759,80",
            cargoTotalText = "₺89,80",
            discountText = "-₺120,00",
            totalPriceText = "₺2.729,60"
        ),
        paymentMethods = listOf(
            CheckoutPaymentMethodItem(
                id = 1,
                title = "Yeni kart ile öde",
                description = "Kredi kartı veya banka kartı bilgilerini gir.",
                iconText = "YK",
                paymentType = CheckoutPaymentType.NewCard
            ),
            CheckoutPaymentMethodItem(
                id = 2,
                title = "Kayıtlı kart",
                description = "Daha önce kaydedilen kartla hızlı ödeme.",
                iconText = "KK",
                paymentType = CheckoutPaymentType.SavedCard
            ),
            CheckoutPaymentMethodItem(
                id = 3,
                title = "Havale / EFT",
                description = "V1 sonrası aktif edilebilecek alternatif ödeme akışı.",
                iconText = "HE",
                paymentType = CheckoutPaymentType.BankTransfer
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckoutPaymentScreenPreview() {
    MaterialTheme {
        CheckoutPaymentScreen()
    }
}