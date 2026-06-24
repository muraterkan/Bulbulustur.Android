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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun ChangeEmailScreen(
    currentEmail: String = "murat@example.com",
    isLoading: Boolean = false,
    errorMessage: String? = null,
    successMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (String) -> Unit = {}
) {
    val newEmailState = remember {
        mutableStateOf("")
    }

    val emailValidationState = remember(
        currentEmail,
        newEmailState.value
    ) {
        derivedStateOf {
            validateEmailForm(
                currentEmail = currentEmail,
                newEmail = newEmailState.value
            )
        }
    }

    val canSubmit = emailValidationState.value.canSubmit && !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "E-Posta DeĞiştir",
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

                    OutlinedTextField(
                        value = newEmailState.value,
                        onValueChange = { value ->
                            newEmailState.value = value
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "Yeni E-Posta")
                        },
                        placeholder = {
                            Text(text = "ornek@bulbulustur.com")
                        },
                        singleLine = true,
                        shape = BBRadius.Input,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        isError = newEmailState.value.isNotBlank() &&
                                !emailValidationState.value.isValidEmailFormat,
                        supportingText = {
                            EmailSupportingText(
                                validation = emailValidationState.value,
                                newEmail = newEmailState.value
                            )
                        },
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
                            title = "E-Posta Güncellendi",
                            message = message,
                            type = EmailMessageType.Success
                        )
                    }

                    BbButton(
                        text = "E-Postayı Güncelle",
                        onClick = {
                            onSaveClick(newEmailState.value)
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
            text = "Hesabınıza baĞlı e-posta adresini güncelleyin. Yeni e-posta için doĞrulama süreci gerekebilir.",
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
                text = currentEmail,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun EmailSupportingText(
    validation: EmailValidationState,
    newEmail: String
) {
    if (newEmail.isBlank()) {
        Text(
            text = "Yeni e-posta adresinizi yazın.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        return
    }

    if (!validation.isValidEmailFormat) {
        Text(
            text = "Geçerli bir e-posta adresi girin.",
            color = MaterialTheme.colorScheme.error
        )

        return
    }

    if (validation.isSameEmail) {
        Text(
            text = "Yeni e-posta mevcut e-posta ile aynı olamaz.",
            color = MaterialTheme.colorScheme.error
        )

        return
    }

    Text(
        text = "E-posta formatı uygun görünüyor.",
        color = BBColors.Green.Green700
    )
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
                text = "DoĞrulama Gerekebilir",
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.Blue.Blue700
            )

            Text(
                text = "E-posta deĞişikliĞinden sonra hesabınızın güvenliĞi için yeni adresin doĞrulanması istenebilir.",
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

private fun validateEmailForm(
    currentEmail: String,
    newEmail: String
): EmailValidationState {
    val trimmedCurrentEmail = currentEmail.trim()
    val trimmedNewEmail = newEmail.trim()

    val hasEmail = trimmedNewEmail.isNotBlank()
    val isValidEmailFormat = isValidEmailAddress(trimmedNewEmail)
    val isSameEmail = trimmedCurrentEmail.equals(
        other = trimmedNewEmail,
        ignoreCase = true
    )

    return EmailValidationState(
        hasEmail = hasEmail,
        isValidEmailFormat = isValidEmailFormat,
        isSameEmail = isSameEmail
    )
}

private fun isValidEmailAddress(
    email: String
): Boolean {
    if (email.isBlank()) {
        return false
    }

    val emailRegex = Regex(
        pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    )

    return emailRegex.matches(email)
}

private enum class EmailMessageType {
    Success,
    Error
}

private data class EmailValidationState(
    val hasEmail: Boolean,
    val isValidEmailFormat: Boolean,
    val isSameEmail: Boolean
) {
    val canSubmit: Boolean
        get() {
            return hasEmail &&
                    isValidEmailFormat &&
                    !isSameEmail
        }
}


