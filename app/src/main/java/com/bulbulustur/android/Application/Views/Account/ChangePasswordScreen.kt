package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun ChangePasswordScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    successMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (
        oldPassword: String,
        newPassword: String,
        newPasswordAgain: String
    ) -> Unit = { _, _, _ -> }
) {
    val oldPasswordState = remember {
        mutableStateOf("")
    }

    val newPasswordState = remember {
        mutableStateOf("")
    }

    val newPasswordAgainState = remember {
        mutableStateOf("")
    }

    val showOldPasswordState = remember {
        mutableStateOf(false)
    }

    val showNewPasswordState = remember {
        mutableStateOf(false)
    }

    val showNewPasswordAgainState = remember {
        mutableStateOf(false)
    }

    val passwordValidationState = remember(
        oldPasswordState.value,
        newPasswordState.value,
        newPasswordAgainState.value
    ) {
        derivedStateOf {
            validatePasswordForm(
                oldPassword = oldPasswordState.value,
                newPassword = newPasswordState.value,
                newPasswordAgain = newPasswordAgainState.value
            )
        }
    }

    val canSubmit = passwordValidationState.value.canSubmit && !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Şifre Değiştir",
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
            ChangePasswordIntroCard()

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                ) {
                    PasswordTextField(
                        value = oldPasswordState.value,
                        onValueChange = { value ->
                            oldPasswordState.value = value
                        },
                        label = "Mevcut Şifre",
                        visible = showOldPasswordState.value,
                        onVisibilityChange = {
                            showOldPasswordState.value = !showOldPasswordState.value
                        }
                    )

                    PasswordTextField(
                        value = newPasswordState.value,
                        onValueChange = { value ->
                            newPasswordState.value = value
                        },
                        label = "Yeni Şifre",
                        visible = showNewPasswordState.value,
                        onVisibilityChange = {
                            showNewPasswordState.value = !showNewPasswordState.value
                        }
                    )

                    PasswordTextField(
                        value = newPasswordAgainState.value,
                        onValueChange = { value ->
                            newPasswordAgainState.value = value
                        },
                        label = "Yeni Şifre Tekrar",
                        visible = showNewPasswordAgainState.value,
                        onVisibilityChange = {
                            showNewPasswordAgainState.value = !showNewPasswordAgainState.value
                        }
                    )

                    PasswordRuleBox(
                        validation = passwordValidationState.value
                    )

                    errorMessage?.takeIf { message ->
                        message.isNotBlank()
                    }?.let { message ->
                        PasswordMessageBox(
                            title = "İşlem Tamamlanamadı",
                            message = message,
                            type = PasswordMessageType.Error
                        )
                    }

                    successMessage?.takeIf { message ->
                        message.isNotBlank()
                    }?.let { message ->
                        PasswordMessageBox(
                            title = "Şifre Güncellendi",
                            message = message,
                            type = PasswordMessageType.Success
                        )
                    }

                    BbButton(
                        text = "Şifreyi Güncelle",
                        onClick = {
                            onSaveClick(
                                oldPasswordState.value,
                                newPasswordState.value,
                                newPasswordAgainState.value
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
private fun ChangePasswordIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Hesabınızın şifresini güçlü ve size özel bir şifreyle güncelleyin.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visible: Boolean,
    onVisibilityChange: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },
        singleLine = true,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (visible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(
                onClick = onVisibilityChange
            ) {
                Text(
                    text = if (visible) {
                        "Gizle"
                    } else {
                        "Göster"
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun PasswordRuleBox(
    validation: PasswordValidationState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = "Güçlü Şifre Kontrolü",
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.Yellow.Yellow800
            )

            PasswordRuleRow(
                text = "Mevcut şifre girilmeli",
                valid = validation.hasOldPassword
            )

            PasswordRuleRow(
                text = "Yeni şifre en az 8 karakter olmalı",
                valid = validation.hasMinimumLength
            )

            PasswordRuleRow(
                text = "Yeni şifre büyük harf, küçük harf ve rakam içermeli",
                valid = validation.hasStrongPattern
            )

            PasswordRuleRow(
                text = "Yeni şifre ve tekrar şifre aynı olmalı",
                valid = validation.passwordsMatch
            )
        }
    }
}

@Composable
private fun PasswordRuleRow(
    text: String,
    valid: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Text(
            text = if (valid) {
                "✓"
            } else {
                "•"
            },
            style = MaterialTheme.typography.labelLarge,
            color = if (valid) {
                BBColors.Green.Green700
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = if (valid) {
                BBColors.Green.Green700
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    }
}

@Composable
private fun PasswordMessageBox(
    title: String,
    message: String,
    type: PasswordMessageType
) {
    val backgroundColor = when (type) {
        PasswordMessageType.Success -> BBColors.Green.Green50
        PasswordMessageType.Error -> BBColors.Red.Red50
    }

    val titleColor = when (type) {
        PasswordMessageType.Success -> BBColors.Green.Green700
        PasswordMessageType.Error -> BBColors.Red.Red700
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

private fun validatePasswordForm(
    oldPassword: String,
    newPassword: String,
    newPasswordAgain: String
): PasswordValidationState {
    val hasOldPassword = oldPassword.isNotBlank()
    val hasMinimumLength = newPassword.length >= 8
    val hasUppercase = newPassword.any { character ->
        character.isUpperCase()
    }
    val hasLowercase = newPassword.any { character ->
        character.isLowerCase()
    }
    val hasDigit = newPassword.any { character ->
        character.isDigit()
    }
    val hasStrongPattern = hasUppercase && hasLowercase && hasDigit
    val passwordsMatch = newPassword.isNotBlank() && newPassword == newPasswordAgain

    return PasswordValidationState(
        hasOldPassword = hasOldPassword,
        hasMinimumLength = hasMinimumLength,
        hasStrongPattern = hasStrongPattern,
        passwordsMatch = passwordsMatch
    )
}

private enum class PasswordMessageType {
    Success,
    Error
}

private data class PasswordValidationState(
    val hasOldPassword: Boolean,
    val hasMinimumLength: Boolean,
    val hasStrongPattern: Boolean,
    val passwordsMatch: Boolean
) {
    val canSubmit: Boolean
        get() {
            return hasOldPassword &&
                    hasMinimumLength &&
                    hasStrongPattern &&
                    passwordsMatch
        }
}


