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
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbFormSection
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextInput
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

data class BankAccountCreateFormState(
    val iban: String = "",
    val validationMessage: String? = null
) {
    val normalizedIban: String
        get() {
            return iban
                .replace(" ", "")
                .uppercase()
        }

    val hasValidTurkeyIbanPrefix: Boolean
        get() {
            return normalizedIban.startsWith("TR")
        }

    val hasExpectedLength: Boolean
        get() {
            return normalizedIban.length == 26
        }

    val canSubmit: Boolean
        get() {
            return hasValidTurkeyIbanPrefix && hasExpectedLength
        }
}

@Composable
fun BankAccountCreateScreen(
    onBackClick: () -> Unit = {},
    onBankAccountCreateClick: (BankAccountCreateFormState) -> Unit = {},
    isSubmitting: Boolean = false
) {
    val formState = remember {
        mutableStateOf(BankAccountCreateFormState())
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "42468515-8191-418a-a245-72ecf9a06558", fallback = ""),
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
            BankAccountCreateIntroCard()

            BbFormSection(
                title = "IBAN Bilgisi"
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "IBAN bilgisini boşluk bırakmadan ya da boşluklu olarak girebilirsin. Sistem doğrulama kurallarını çalıştırmaya devam eder.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    BbTextInput(
                        value = formState.value.iban,
                        onValueChange = { iban ->
                            formState.value = formState.value.copy(
                                iban = iban,
                                validationMessage = null
                            )
                        },
                        label = "IBAN",
                        placeholder = "TR00 0000 0000 0000 0000 0000 00"
                    )

                    Text(
                        text = "IBAN, sana ait geçerli bir vadesiz hesaba ait olmalıdır. Vadeli hesap IBANy'ları ödeme süreçlerinden çıkarılabilir.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    BankAccountValidationInfoCard(
                        formState = formState.value
                    )

                    BankAccountFinancialInfoCard()

                    if (formState.value.validationMessage != null) {
                        Text(
                            text = formState.value.validationMessage.orEmpty(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    BbButton(
                        text = "Kaydet",
                        onClick = {
                            if (formState.value.canSubmit) {
                                onBankAccountCreateClick(formState.value)
                            } else {
                                formState.value = formState.value.copy(
                                    validationMessage = getBankAccountCreateValidationMessage(formState.value)
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Primary,
                        enabled = formState.value.canSubmit,
                        isLoading = isSubmitting
                    )
                }
            }
        }
    }
}

@Composable
private fun BankAccountCreateIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Para aktarımı ve ödeme sürecinde kullanılacak banka hesabını güvenli şekilde kaydet. Girdiğin IBAN numarası vadeli bir hesaba ait olmamalıdır.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun BankAccountValidationInfoCard(
    formState: BankAccountCreateFormState
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "IBAN Kontrolü",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = getIbanPrefixMessage(formState),
                style = MaterialTheme.typography.bodySmall,
                color = if (formState.hasValidTurkeyIbanPrefix || formState.iban.isBlank()) {
                    MaterialTheme.colorScheme.onSurfaceVariant
                } else {
                    MaterialTheme.colorScheme.error
                }
            )

            Text(
                text = getIbanLengthMessage(formState),
                style = MaterialTheme.typography.bodySmall,
                color = if (formState.hasExpectedLength || formState.iban.isBlank()) {
                    MaterialTheme.colorScheme.onSurfaceVariant
                } else {
                    MaterialTheme.colorScheme.error
                }
            )
        }
    }
}

@Composable
private fun BankAccountFinancialInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Finansal Bilgi Kontrolü",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Banka hesabı bilgileri ödeme ve aktarım süreçlerinde kullanılacağı için doğru girilmelidir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getIbanPrefixMessage(
    formState: BankAccountCreateFormState
): String {
    if (formState.iban.isBlank()) {
        return "IBAN numarası TR ile başlamalıdır."
    }

    if (formState.hasValidTurkeyIbanPrefix) {
        return "IBAN ülke kodu uygun görünüyor."
    }

    return "Türkiye IBAN numarası TR ile başlamalıdır."
}

private fun getIbanLengthMessage(
    formState: BankAccountCreateFormState
): String {
    if (formState.iban.isBlank()) {
        return "Türkiye IBAN numarası 26 karakter olmalıdır."
    }

    if (formState.hasExpectedLength) {
        return "IBAN uzunluğu uygun görünüyor."
    }

    return "IBAN uzunluğu kontrol edilmeli. Türkiye IBAN numarası 26 karakter olmalıdır."
}

private fun getBankAccountCreateValidationMessage(
    formState: BankAccountCreateFormState
): String {
    if (!formState.hasValidTurkeyIbanPrefix) {
        return "IBAN numarası TR ile başlamalıdır."
    }

    if (!formState.hasExpectedLength) {
        return "Türkiye IBAN numarası 26 karakter olmalıdır."
    }

    return "IBAN bilgisini kontrol etmelisin."
}


