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
                                text = BBLocalization.Current.Get(key = "cd9e75c2-c960-4df6-9348-e01d058beef2", fallback = "IBAN Bilgisi"),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                text = BBLocalization.Current.Get(key = "964c09d9-8339-415f-ad2a-7891b3b99099", fallback = "IBAN bilgisini boşluk bırakmadan ya da boşluklu olarak girebilirsiniz."),
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
                                    validationMessage = BBLocalization.Current.Get(key = "49d652d9-e25c-4444-80b5-56938b629f14", fallback = "IBAN numarası TR ile başlamalıdır.")
                                    return@BbButton
                                }

                                if (!hasValidLength) {
                                    validationMessage = BBLocalization.Current.Get(key = "113b9c40-09c9-4af1-a746-3bc14dddcbec", fallback = "Türkiye IBAN numarası 26 karakter olmalıdır.")
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
            text = BBLocalization.Current.Get(key = "54f7b170-7cef-47bf-8f7d-6514cac65e22", fallback = "Kayıtlı banka hesabınıza ait IBAN bilgisini düzenleyebilirsiniz. Girdiğiniz IBAN vadeli hesaba ait olmamalıdır."),
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
                text = BBLocalization.Current.Get(key = "cd3f2e6e-5248-45e2-83a7-6cdd1a6330ba", fallback = "Banka hesabı yükleniyor."),
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
                text = BBLocalization.Current.Get(key = "409e5b15-7913-4306-a273-28af1c3fe334", fallback = "IBAN Kontrolü"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = when {
                    iban.isBlank() -> BBLocalization.Current.Get(key = "49d652d9-e25c-4444-80b5-56938b629f14", fallback = "IBAN numarası TR ile başlamalıdır.")
                    hasValidPrefix -> BBLocalization.Current.Get(key = "8d07611b-1b27-4fad-95ac-bdee9235fb1b", fallback = "IBAN ülke kodu uygun görünüyor.")
                    else -> BBLocalization.Current.Get(key = "0c5b40e9-09dc-46a8-aa8e-ddbcc34f8677", fallback = "Türkiye IBAN numarası TR ile başlamalıdır.")
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
                    iban.isBlank() -> BBLocalization.Current.Get(key = "113b9c40-09c9-4af1-a746-3bc14dddcbec", fallback = "Türkiye IBAN numarası 26 karakter olmalıdır.")
                    hasValidLength -> BBLocalization.Current.Get(key = "71b0db09-f1df-4d81-ba57-781ae5d652f5", fallback = "IBAN uzunluğu uygun görünüyor.")
                    else -> BBLocalization.Current.Get(key = "30dc9bf1-dc06-45fa-8aa6-7336ebef89dd", fallback = "IBAN uzunluğu kontrol edilmeli. Türkiye IBAN numarası 26 karakter olmalıdır.")
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
            text = BBLocalization.Current.Get(key = "4f771ca4-6742-4da2-b499-e3421a729511", fallback = "Finansal Bilgi Kontrolü"),
            style = MaterialTheme.typography.labelLarge,
            color = BBColors.Yellow.Yellow800
        )

        Text(
            text = BBLocalization.Current.Get(key = "ed822b2b-f973-4f37-a68f-3f7341bbc1c4", fallback = "Güncellediğiniz banka hesabı bilgileri ödeme ve aktarım süreçlerinde kullanılacağı için doğru girilmelidir."),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}