package com.bulbulustur.android.Application.Views.Account

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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextInput
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
        "IBAN Numarasını DeĞiştir"
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
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
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
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
        "Kayıtlı banka hesabınıza ait IBAN bilgisini düzenleyebilirsiniz. GirdiĞiniz IBAN vadeli hesaba ait olmamalıdır."
    } else {
        "Para aktarımı ve ödeme sürecinde kullanılacak banka hesabını güvenli şekilde kaydedebilirsiniz. GirdiĞiniz IBAN vadeli hesaba ait olmamalıdır."
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
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = "Finansal Bilgi Kontrolü",
            style = MaterialTheme.typography.labelLarge,
            color = BBColors.Yellow.Yellow800
        )

        Text(
            text = "GüncellediĞiniz banka hesabı bilgileri ödeme ve aktarım süreçlerinde kullanılacaĞı için doĞru girilmelidir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


