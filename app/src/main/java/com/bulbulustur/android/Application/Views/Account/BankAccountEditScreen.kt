package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextInput
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun BankAccountEditScreen(
    bankAccountId: Int,
    initialIban: String = "",
    isLoading: Boolean = false,
    isSubmitting: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (String) -> Unit = {}
) {
    var iban by remember(initialIban) {
        mutableStateOf(initialIban)
    }

    var validationMessage by remember {
        mutableStateOf<String?>(null)
    }

    val normalizedIban = iban
        .replace(" ", "")
        .uppercase()

    val hasValidPrefix = normalizedIban.startsWith("TR")
    val hasValidLength = normalizedIban.length == 26
    val canSubmit = hasValidPrefix && hasValidLength && !isLoading && !isSubmitting

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "c0270f19-a8c0-4511-8178-6520fa601a90", fallback = "IBAN Numarasını Değiştir"),
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
            BankAccountEditIntroCard()

            if (isLoading) {
                BankAccountEditLoadingState()
            } else {
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
                            value = iban,
                            onValueChange = { value ->
                                iban = value.uppercase()
                                validationMessage = null
                            },
                            label = "IBAN",
                            placeholder = "TR00 0000 0000 0000 0000 0000 00",
                            enabled = !isSubmitting
                        )

                        BankAccountEditValidationCard(
                            iban = iban,
                            hasValidPrefix = hasValidPrefix,
                            hasValidLength = hasValidLength
                        )

                        BankAccountWarningBox()

                        if (!validationMessage.isNullOrBlank()) {
                            Text(
                                text = validationMessage.orEmpty(),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        if (!errorMessage.isNullOrBlank()) {
                            Text(
                                text = errorMessage,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        BbButton(
                            text = BBLocalization.Current.Get(key = "58104fd9-46c6-4304-9abb-07f5273a33f9", fallback = "Güncelle"),
                            onClick = {
                                if (!hasValidPrefix) {
                                    validationMessage = "IBAN numarası TR ile başlamalıdır."
                                    return@BbButton
                                }

                                if (!hasValidLength) {
                                    validationMessage = "Türkiye IBAN numarası 26 karakter olmalıdır."
                                    return@BbButton
                                }

                                onSaveClick(normalizedIban)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            variant = BbButtonVariant.Primary,
                            size = BbButtonSize.Medium,
                            enabled = canSubmit,
                            isLoading = isSubmitting
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BankAccountEditIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Kayıtlı banka hesabınıza ait IBAN bilgisini düzenleyebilirsiniz. Girdiğiniz IBAN vadeli hesaba ait olmamalıdır.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun BankAccountEditLoadingState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(BBSpacing.Space2),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Banka hesabı yükleniyor.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BankAccountEditValidationCard(
    iban: String,
    hasValidPrefix: Boolean,
    hasValidLength: Boolean
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = "IBAN Kontrolü",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = when {
                    iban.isBlank() -> "IBAN numarası TR ile başlamalıdır."
                    hasValidPrefix -> "IBAN ülke kodu uygun görünüyor."
                    else -> "Türkiye IBAN numarası TR ile başlamalıdır."
                },
                style = MaterialTheme.typography.bodySmall,
                color = if (iban.isBlank() || hasValidPrefix) {
                    MaterialTheme.colorScheme.onSurfaceVariant
                } else {
                    MaterialTheme.colorScheme.error
                }
            )

            Text(
                text = when {
                    iban.isBlank() -> "Türkiye IBAN numarası 26 karakter olmalıdır."
                    hasValidLength -> "IBAN uzunluğu uygun görünüyor."
                    else -> "IBAN uzunluğu kontrol edilmeli. Türkiye IBAN numarası 26 karakter olmalıdır."
                },
                style = MaterialTheme.typography.bodySmall,
                color = if (iban.isBlank() || hasValidLength) {
                    MaterialTheme.colorScheme.onSurfaceVariant
                } else {
                    MaterialTheme.colorScheme.error
                }
            )
        }
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
            text = "Güncellediğiniz banka hesabı bilgileri ödeme ve aktarım süreçlerinde kullanılacağı için doğru girilmelidir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}