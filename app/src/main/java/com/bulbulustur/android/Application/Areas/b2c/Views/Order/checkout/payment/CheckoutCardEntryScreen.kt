package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.payment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.*
import com.bulbulustur.android.Application.wwwroot.DesignTokens.*

@Composable
fun CheckoutCardEntryScreen(
    onBackClick: () -> Unit,
    onContinueClick: (CheckoutPaymentCardUiModel) -> Unit
) {
    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var cvc by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var storeCard by remember { mutableStateOf(false) }

    val digits = cardNumber.filter(Char::isDigit)
    val complete = digits.length in 13..19 && expiry.filter(Char::isDigit).length == 4 && cvc.length in 3..4 && name.isNotBlank()

    Column(Modifier.fillMaxSize()) {

        BbInnerPageHeader(title = "Yeni Kart", onBackClick = onBackClick)

        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(BBSpacing.PageHorizontal),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it.filter(Char::isDigit).take(19).chunked(4).joinToString(" ") },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Kart Numarası") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = BBRadius.Input
            )

            Row(horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {

                OutlinedTextField(
                    value = expiry,
                    onValueChange = {
                        val value = it.filter(Char::isDigit).take(4)
                        expiry = if (value.length <= 2) value else "${value.take(2)}/${value.drop(2)}"
                    },
                    modifier = Modifier.weight(1f),
                    label = { Text("Son Kullanma Tarihi") },
                    placeholder = { Text("AA/YY") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = BBRadius.Input
                )

                OutlinedTextField(
                    value = cvc,
                    onValueChange = { cvc = it.filter(Char::isDigit).take(4) },
                    modifier = Modifier.weight(1f),
                    label = { Text("Güvenlik Kodu") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    shape = BBRadius.Input
                )
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Ad Soyad") },
                singleLine = true,
                shape = BBRadius.Input
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = storeCard, onCheckedChange = { storeCard = it })
                Text("Kartımı sonraki alışverişlerim için kaydet")
            }
        }

        BbButton(
            text = "Devam Et",
            enabled = complete,
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Medium,
            modifier = Modifier.fillMaxWidth().padding(BBSpacing.PageHorizontal),
            onClick = {
                onContinueClick(
                    CheckoutPaymentCardUiModel(
                        bankName = "Yeni Kart",
                        cardAlias = name,
                        maskedNumber = "**** **** **** ${digits.takeLast(4)}",
                        cardBrand = "CARD"
                    )
                )
            }
        )
    }
}