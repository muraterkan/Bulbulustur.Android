package com.bulbulustur.android.features.account.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.form.BbFormSection
import com.bulbulustur.android.ui.components.form.BbPasswordInput
import com.bulbulustur.android.ui.components.form.BbTextInput
import com.bulbulustur.android.ui.theme.BbSpacing

data class ChangeEmailFormState(
    val currentEmailAddress: String = "murat@example.com",
    val newEmailAddress: String = "",
    val password: String = "",
    val validationMessage: String? = null
) {
    val hasValidEmailFormat: Boolean
        get() {
            return newEmailAddress.contains("@") &&
                    newEmailAddress.contains(".") &&
                    newEmailAddress.length >= 6
        }

    val isDifferentEmailAddress: Boolean
        get() {
            return newEmailAddress.isNotBlank() &&
                    !newEmailAddress.equals(currentEmailAddress, ignoreCase = true)
        }

    val canSubmit: Boolean
        get() {
            return hasValidEmailFormat &&
                    isDifferentEmailAddress &&
                    password.isNotBlank()
        }
}

@Composable
fun ChangeEmailScreen(
    currentEmailAddress: String = "murat@example.com",
    onBackClick: () -> Unit = {},
    onEmailChangeClick: (ChangeEmailFormState) -> Unit = {},
    onLoginActivitiesClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onDeactivateAccountClick: () -> Unit = {},
    isSubmitting: Boolean = false
) {
    val formState = remember(currentEmailAddress) {
        mutableStateOf(
            ChangeEmailFormState(
                currentEmailAddress = currentEmailAddress
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.PageTop
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGap)
    ) {
        BbButton(
            text = "Hesabıma Dön",
            onClick = onBackClick,
            variant = BbButtonVariant.Outline
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            BbChip(
                text = "E-posta Güvenliği"
            )

            Text(
                text = "E-posta Değişimi",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabına bağlı e-posta adresini değiştirmek için yeni adresini ve mevcut şifreni gir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Large
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Mevcut E-posta",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = formState.value.currentEmailAddress,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Yeni e-posta adresin doğrulama sonrasında hesabına tanımlanır.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        BbFormSection(
            title = "Yeni e-posta bilgileri"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Güvenlik için bu işlemde mevcut şifren de istenir. Yeni e-posta adresine doğrulama bağlantısı gönderilebilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbTextInput(
                    value = formState.value.newEmailAddress,
                    onValueChange = { newEmailAddress ->
                        formState.value = formState.value.copy(
                            newEmailAddress = newEmailAddress,
                            validationMessage = null
                        )
                    },
                    label = "Yeni E-posta",
                    placeholder = "ornek@bulbulustur.com"
                )

                BbPasswordInput(
                    value = formState.value.password,
                    onValueChange = { password ->
                        formState.value = formState.value.copy(
                            password = password,
                            validationMessage = null
                        )
                    },
                    label = "Mevcut Şifre",
                    placeholder = "Mevcut şifren"
                )

                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "E-posta değişikliği sonrası",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "Yeni adres doğrulanana kadar bazı güvenlik işlemleri mevcut e-posta üzerinden devam edebilir.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (formState.value.validationMessage != null) {
                    Text(
                        text = formState.value.validationMessage.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                BbButton(
                    text = "Değiştir",
                    onClick = {
                        if (formState.value.canSubmit) {
                            onEmailChangeClick(formState.value)
                        } else {
                            formState.value = formState.value.copy(
                                validationMessage = getChangeEmailValidationMessage(formState.value)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    enabled = formState.value.canSubmit,
                    isLoading = isSubmitting
                )

                BbButton(
                    text = "Vazgeç",
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Outline,
                    enabled = !isSubmitting
                )
            }
        }

        AccountSecurityBottomMenu(
            selectedItem = AccountSecurityMenuItem.ChangeEmail,
            onLoginActivitiesClick = onLoginActivitiesClick,
            onChangePasswordClick = onChangePasswordClick,
            onChangeEmailClick = {},
            onDeactivateAccountClick = onDeactivateAccountClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private fun getChangeEmailValidationMessage(
    formState: ChangeEmailFormState
): String {
    if (!formState.hasValidEmailFormat) {
        return "Geçerli bir e-posta adresi girmelisin."
    }

    if (!formState.isDifferentEmailAddress) {
        return "Yeni e-posta adresi mevcut e-posta adresinden farklı olmalıdır."
    }

    if (formState.password.isBlank()) {
        return "Devam etmek için mevcut şifreni girmelisin."
    }

    return "E-posta değişikliği için gerekli alanları kontrol etmelisin."
}