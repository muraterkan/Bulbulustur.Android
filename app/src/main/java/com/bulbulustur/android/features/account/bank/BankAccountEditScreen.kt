package com.bulbulustur.android.features.account.bank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun BankAccountEditScreen(
    bankAccountId: Int? = null,
    initialIban: String = "",
    onBackClick: () -> Unit = {},
    onSaveClick: (String) -> Unit = {}
) {
    val ibanState = remember {
        mutableStateOf(initialIban)
    }

    val isEditMode = bankAccountId != null

    val title = if (isEditMode) {
        "IBAN Numarasını Değiştir"
    } else {
        "IBAN Numarası Ekle"
    }

    val saveButtonText = if (isEditMode) {
        "Güncelle"
    } else {
        "Kaydet"
    }

    AccountPageScaffold(
        title = title,
        kicker = "Banka Hesabı",
        description = "Kayıtlı banka hesabınıza ait IBAN bilgisini düzenleyebilirsiniz. Girdiğiniz IBAN vadeli hesaba ait olmamalıdır.",
        backButtonText = "Banka Hesaplarıma Dön",
        onBackClick = onBackClick
    ) {
        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Medium
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "IBAN Bilgisi",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "IBAN bilgisini boşluk bırakmadan ya da boşluklu olarak girebilirsiniz.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                OutlinedTextField(
                    value = ibanState.value,
                    onValueChange = { value ->
                        ibanState.value = value
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "IBAN")
                    },
                    placeholder = {
                        Text(text = "TR00 0000 0000 0000 0000 0000 00")
                    },
                    singleLine = true,
                    shape = BbRadius.Input,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        keyboardType = KeyboardType.Text
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                BankAccountWarningBox()

                BbButton(
                    text = saveButtonText,
                    onClick = {
                        onSaveClick(ibanState.value)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun BankAccountWarningBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.Yellow.Yellow50,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = "Finansal Bilgi Kontrolü",
            style = MaterialTheme.typography.labelLarge,
            color = BbColors.Yellow.Yellow800
        )

        Text(
            text = "Güncellediğiniz banka hesabı bilgileri ödeme ve aktarım süreçlerinde kullanılacağı için doğru girilmelidir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}