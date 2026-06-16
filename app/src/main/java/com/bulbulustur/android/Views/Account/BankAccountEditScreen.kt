package com.bulbulustur.android.Views.Account

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
        "IBAN NumarasÄ±nÄ± DeÄŸiÅŸtir"
    } else {
        "IBAN NumarasÄ± Ekle"
    }

    val saveButtonText = if (isEditMode) {
        "GÃ¼ncelle"
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
                            text = "IBAN bilgisini boÅŸluk bÄ±rakmadan ya da boÅŸluklu olarak girebilirsiniz.",
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
        "KayÄ±tlÄ± banka hesabÄ±nÄ±za ait IBAN bilgisini dÃ¼zenleyebilirsiniz. GirdiÄŸiniz IBAN vadeli hesaba ait olmamalÄ±dÄ±r."
    } else {
        "Para aktarÄ±mÄ± ve Ã¶deme sÃ¼recinde kullanÄ±lacak banka hesabÄ±nÄ± gÃ¼venli ÅŸekilde kaydedebilirsiniz. GirdiÄŸiniz IBAN vadeli hesaba ait olmamalÄ±dÄ±r."
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
            text = "Finansal Bilgi KontrolÃ¼",
            style = MaterialTheme.typography.labelLarge,
            color = BbColors.Yellow.Yellow800
        )

        Text(
            text = "GÃ¼ncellediÄŸiniz banka hesabÄ± bilgileri Ã¶deme ve aktarÄ±m sÃ¼reÃ§lerinde kullanÄ±lacaÄŸÄ± iÃ§in doÄŸru girilmelidir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
