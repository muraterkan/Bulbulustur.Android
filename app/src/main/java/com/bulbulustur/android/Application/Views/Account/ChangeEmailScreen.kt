package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun ChangeEmailScreen(
    currentEmail: String,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    successMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (newEmail: String, reNewEmail: String) -> Unit = { _, _ -> }
) {
    val newEmailState = remember {
        mutableStateOf("")
    }

    val reNewEmailState = remember {
        mutableStateOf("")
    }

    val emailValidationState = remember(
        currentEmail,
        newEmailState.value,
        reNewEmailState.value
    ) {
        derivedStateOf {
            ValidateEmailForm(
                currentEmail = currentEmail,
                newEmail = newEmailState.value,
                reNewEmail = reNewEmailState.value
            )
        }
    }

    val canSubmit = emailValidationState.value.CanSubmit && !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "E-Posta Değiştir",
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
            ChangeEmailIntroCard()

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                ) {
                    CurrentEmailBox(
                        currentEmail = currentEmail
                    )

                    EmailTextField(
                        value = newEmailState.value,
                        onValueChange = { value ->
                            newEmailState.value = value
                        },
                        label = "Yeni E-Posta",
                        placeholder = "ornek@bulbulustur.com",
                        isError = newEmailState.value.isNotBlank() &&
                                (!emailValidationState.value.IsNewEmailValid ||
                                        emailValidationState.value.IsSameEmail),
                        supportingText = {
                            NewEmailSupportingText(
                                validation = emailValidationState.value,
                                newEmail = newEmailState.value
                            )
                        }
                    )

                    EmailTextField(
                        value = reNewEmailState.value,
                        onValueChange = { value ->
                            reNewEmailState.value = value
                        },
                        label = "Yeni E-Posta Tekrar",
                        placeholder = "ornek@bulbulustur.com",
                        isError = reNewEmailState.value.isNotBlank() &&
                                (!emailValidationState.value.IsReNewEmailValid ||
                                        !emailValidationState.value.EmailsMatch),
                        supportingText = {
                            ReNewEmailSupportingText(
                                validation = emailValidationState.value,
                                reNewEmail = reNewEmailState.value
                            )
                        }
                    )

                    ChangeEmailInfoBox()

                    errorMessage?.takeIf { message ->
                        message.isNotBlank()
                    }?.let { message ->
                        EmailMessageBox(
                            title = "İşlem Tamamlanamadı",
                            message = message,
                            type = EmailMessageType.Error
                        )
                    }

                    successMessage?.takeIf { message ->
                        message.isNotBlank()
                    }?.let { message ->
                        EmailMessageBox(
                            title = "Doğrulama Bağlantısı Gönderildi",
                            message = message,
                            type = EmailMessageType.Success
                        )
                    }

                    BbButton(
                        text = "Doğrulama Bağlantısı Gönder",
                        onClick = {
                            onSaveClick(
                                newEmailState.value.trim(),
                                reNewEmailState.value.trim()
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Medium,
                        enabled = canSubmit,
                        isLoading = isLoading
                    )
                }
            }
        }
    }
}

@Composable
private fun ChangeEmailIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Yeni e-posta adresinize bir doğrulama bağlantısı gönderilir. E-posta adresiniz, bağlantıyı açtıktan sonra değiştirilir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CurrentEmailBox(
    currentEmail: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = "Mevcut E-Posta",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = currentEmail.ifBlank {
                    "E-posta bilgisi bulunamadı"
                },
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isError: Boolean,
    supportingText: @Composable () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        singleLine = true,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        isError = isError,
        supportingText = supportingText,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            errorIndicatorColor = MaterialTheme.colorScheme.error,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            errorLabelColor = MaterialTheme.colorScheme.error
        )
    )
}

@Composable
private fun NewEmailSupportingText(
    validation: EmailValidationState,
    newEmail: String
) {
    when {
        newEmail.isBlank() -> {
            Text(
                text = "Yeni e-posta adresinizi yazın.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        !validation.IsNewEmailValid -> {
            Text(
                text = "Geçerli bir e-posta adresi girin.",
                color = MaterialTheme.colorScheme.error
            )
        }

        validation.IsSameEmail -> {
            Text(
                text = "Yeni e-posta mevcut e-posta ile aynı olamaz.",
                color = MaterialTheme.colorScheme.error
            )
        }

        else -> {
            Text(
                text = "E-posta formatı uygun görünüyor.",
                color = BBColors.Green.Green700
            )
        }
    }
}

@Composable
private fun ReNewEmailSupportingText(
    validation: EmailValidationState,
    reNewEmail: String
) {
    when {
        reNewEmail.isBlank() -> {
            Text(
                text = "Yeni e-posta adresinizi tekrar yazın.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        !validation.IsReNewEmailValid -> {
            Text(
                text = "Geçerli bir e-posta adresini tekrar girin.",
                color = MaterialTheme.colorScheme.error
            )
        }

        !validation.EmailsMatch -> {
            Text(
                text = "Yeni e-posta adresleri eşleşmiyor.",
                color = MaterialTheme.colorScheme.error
            )
        }

        else -> {
            Text(
                text = "E-posta adresleri eşleşiyor.",
                color = BBColors.Green.Green700
            )
        }
    }
}

@Composable
private fun ChangeEmailInfoBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BBColors.Blue.Blue50,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = "Doğrulama Bağlantısı",
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.Blue.Blue700
            )

            Text(
                text = "Bağlantı yeni e-posta adresinize gönderilir. Değişiklik tamamlandığında güvenlik nedeniyle yeniden giriş yapmanız gerekebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun EmailMessageBox(
    title: String,
    message: String,
    type: EmailMessageType
) {
    val backgroundColor = when (type) {
        EmailMessageType.Success -> BBColors.Green.Green50
        EmailMessageType.Error -> BBColors.Red.Red50
    }

    val titleColor = when (type) {
        EmailMessageType.Success -> BBColors.Green.Green700
        EmailMessageType.Error -> BBColors.Red.Red700
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = titleColor
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun ValidateEmailForm(
    currentEmail: String,
    newEmail: String,
    reNewEmail: String
): EmailValidationState {
    val normalizedCurrentEmail = currentEmail.trim()
    val normalizedNewEmail = newEmail.trim()
    val normalizedReNewEmail = reNewEmail.trim()

    val isNewEmailValid = IsValidEmailAddress(normalizedNewEmail)
    val isReNewEmailValid = IsValidEmailAddress(normalizedReNewEmail)
    val isSameEmail = normalizedCurrentEmail.equals(
        other = normalizedNewEmail,
        ignoreCase = true
    )
    val emailsMatch = normalizedNewEmail.isNotBlank() &&
            normalizedNewEmail.equals(
                other = normalizedReNewEmail,
                ignoreCase = true
            )

    return EmailValidationState(
        IsNewEmailValid = isNewEmailValid,
        IsReNewEmailValid = isReNewEmailValid,
        IsSameEmail = isSameEmail,
        EmailsMatch = emailsMatch
    )
}

private fun IsValidEmailAddress(email: String): Boolean {
    if (email.isBlank()) return false

    val emailRegex = Regex(
        pattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"
    )

    return emailRegex.matches(email)
}

private enum class EmailMessageType {
    Success,
    Error
}

private data class EmailValidationState(
    val IsNewEmailValid: Boolean,
    val IsReNewEmailValid: Boolean,
    val IsSameEmail: Boolean,
    val EmailsMatch: Boolean
) {
    val CanSubmit: Boolean
        get() = IsNewEmailValid &&
                IsReNewEmailValid &&
                !IsSameEmail &&
                EmailsMatch
}