package com.bulbulustur.android.Views.account.bank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.components.form.BbTextInput
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun BankAccountEditScreen(
    bankAccountId: Int? = null,
    initialIban: String = "",
    onBackClick: () -> Unit = {},
    onSaveClick: (String) -> Unit = {}
) {
    val ibanState = remember(initialIban) {
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

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = title,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    PaddingValues(
                        start = BbSpacing.PageHorizontal,
                        top = BbSpacing.PageTopCompact,
                        end = BbSpacing.PageHorizontal,
                        bottom = BbSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGap)
        ) {
            BankAccountEditIntroCard(
                isEditMode = isEditMode
            )

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

                    BbTextInput(
                        value = ibanState.value,
                        onValueChange = { value ->
                            ibanState.value = value.uppercase()
                        },
                        label = "IBAN",
                        placeholder = "TR00 0000 0000 0000 0000 0000 00"
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
}

@Composable
private fun BankAccountEditIntroCard(
    isEditMode: Boolean
) {
    val description = if (isEditMode) {
        "Kayıtlı banka hesabınıza ait IBAN bilgisini düzenleyebilirsiniz. Girdiğiniz IBAN vadeli hesaba ait olmamalıdır."
    } else {
        "Para aktarımı ve ödeme sürecinde kullanılacak banka hesabını güvenli şekilde kaydedebilirsiniz. Girdiğiniz IBAN vadeli hesaba ait olmamalıdır."
    }

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
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