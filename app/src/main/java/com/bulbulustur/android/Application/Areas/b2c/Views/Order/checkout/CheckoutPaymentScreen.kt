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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                item {
                    CheckoutPaymentTopBar(
                        onBackClick = onBackClick
                    )
                }

                item {
                    CheckoutProgressCard(
                        currentStep = "3",
                        title = BBLocalization.Current.Get(key = "0d5a9dc6-b4eb-4f81-b5f1-d9d09a40cf40", fallback = ""),
                        description = BBLocalization.Current.Get(key = "17bf05fa-23fa-465b-9ce6-471127a5e133", fallback = "Ödeme yöntemini seç ve kart bilgilerini gir.")
                    )
                }

                item {
                    CheckoutPaymentSummaryCard(
                        summary = screenData.summary
                    )
                }

                item {
                    CheckoutPaymentSectionTitle(
                        title = BBLocalization.Current.Get(key = "8c828c6c-67e7-4989-835d-1fead0c9518c", fallback = "Ödeme yöntemi"),
                        description = BBLocalization.Current.Get(key = "885a68c2-74c7-4574-82a7-0a74e2e06803", fallback = "Sipariş için kullanmak istediğin ödeme tipini seç.")
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
                text = BBLocalization.Current.Get(key = "0d5a9dc6-b4eb-4f81-b5f1-d9d09a40cf40", fallback = ""),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = BBLocalization.Current.Get(key = "aeebca81-a389-4d0b-8afd-9f2649ef2316", fallback = "Checkout adım 3 / 4"),
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
            modifier = Modifier.padding(BBSpacing.Space4)
        ) {
            CheckoutPaymentSummaryRow(
                title = BBLocalization.Current.Get(key = "9ca1b3ac-05ef-462c-a4ef-e4bcd4b4b11b", fallback = "Ürün toplamı"),
                value = summary.productTotalText
            )

            Spacer(modifier = Modifier.height(8.dp))

            CheckoutPaymentSummaryRow(
                title = BBLocalization.Current.Get(key = "8fa1207a-2a06-4bdb-936b-f7da848e0f72", fallback = "Kargo"),
                value = summary.cargoTotalText
            )

            Spacer(modifier = Modifier.height(8.dp))

            CheckoutPaymentSummaryRow(
                title = BBLocalization.Current.Get(key = "9dd8d854-ca26-4660-bcb3-b7ec8e3f458b", fallback = "İndirim"),
                value = summary.discountText
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.BorderThin)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(modifier = Modifier.height(12.dp))

            CheckoutPaymentSummaryRow(
                title = BBLocalization.Current.Get(key = "0234baa2-519d-42ae-a2e8-760ebc0a1d06", fallback = "Ödenecek Tutar"),
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            CheckoutPaymentSectionTitle(
                title = BBLocalization.Current.Get(key = "227d50c4-7fe0-4381-860e-960b3df7c10e", fallback = ""),
                description = BBLocalization.Current.Get(key = "f49620eb-c9eb-4f27-9bf2-3d2240bb6889", fallback = "Kart bilgilerin güvenli ödeme altyapısıyla işlenir.")
            )

            CheckoutPaymentTextField(
                value = cardNumber,
                onValueChange = onCardNumberChange,
                placeholder = BBLocalization.Current.Get(key = "b666b2ea-809a-4154-9c1a-bb80f7dbb042", fallback = "Kart numarası")
            )

            CheckoutPaymentTextField(
                value = cardHolderName,
                onValueChange = onCardHolderNameChange,
                placeholder = BBLocalization.Current.Get(key = "5bd3e50e-cfaa-4c0f-a743-db35321e51a4", fallback = "Kart üzerindeki isim")
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
                        text = BBLocalization.Current.Get(key = "691c3311-8b41-4e98-b83a-47c569511753", fallback = "Kartı sonraki alışverişler için kaydet"),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = BBLocalization.Current.Get(key = "710724c1-f69a-46a5-a621-62e23354510e", fallback = "Kart saklama altyapısı aktif olduğunda kullanılacak."),
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
            focusedIndicatorColor = BBColors.Transparent,
            unfocusedIndicatorColor = BBColors.Transparent,
            disabledIndicatorColor = BBColors.Transparent
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
            BbIconBox(
                size = BbIconBoxSize.Large,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
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
                    text = BBLocalization.Current.Get(key = "f3579e87-ed23-48b3-bcf7-b1eae15a5c50", fallback = "Güvenli ödeme"),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = BBLocalization.Current.Get(key = "768c487d-15fd-4400-bea6-f0470a4042f6", fallback = "Ödeme adımında 3D Secure ve banka doğrulama süreçleri ödeme sağlayıcısına göre çalışır. Sipariş onayı bir sonraki adımda gösterilir."),
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
                    text = BBLocalization.Current.Get(key = "c0cac04f-f3c8-4afc-8d3c-08c30a15e544", fallback = "Özete geç"),
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
                title = BBLocalization.Current.Get(key = "14a55dd6-8372-464b-9ad6-cc07baffd7c6", fallback = "Yeni kart ile öde"),
                description = BBLocalization.Current.Get(key = "27ddad65-f6d5-49f1-b457-567af3bc0cf7", fallback = "Kredi kartı veya banka kartı bilgilerini gir."),
                iconText = "YK",
                paymentType = CheckoutPaymentType.NewCard
            ),
            CheckoutPaymentMethodItem(
                id = 2,
                title = BBLocalization.Current.Get(key = "c29a02a2-aa01-4602-b804-77f45c5f07a0", fallback = "Kayıtlı kart"),
                description = BBLocalization.Current.Get(key = "e9cea64e-da69-4816-b727-617b561c5822", fallback = "Daha önce kaydedilen kartla hızlı ödeme."),
                iconText = "KK",
                paymentType = CheckoutPaymentType.SavedCard
            ),
            CheckoutPaymentMethodItem(
                id = 3,
                title = BBLocalization.Current.Get(key = "40344faa-49b8-44d6-9e3f-04feae013f42", fallback = ""),
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


