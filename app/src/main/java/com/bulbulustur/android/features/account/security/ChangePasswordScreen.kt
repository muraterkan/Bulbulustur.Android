package com.bulbulustur.android.features.account.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
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
import com.bulbulustur.android.ui.theme.BbSpacing

data class ChangePasswordFormState(
    val currentPassword: String = "",
    val newPassword: String = "",
    val repeatedNewPassword: String = "",
    val validationMessage: String? = null
) {
    val hasMinimumLength: Boolean
        get() {
            return newPassword.length >= 8
        }

    val hasLetter: Boolean
        get() {
            return newPassword.any { character ->
                character.isLetter()
            }
        }

    val hasDigit: Boolean
        get() {
            return newPassword.any { character ->
                character.isDigit()
            }
        }

    val hasSpecialCharacter: Boolean
        get() {
            return newPassword.any { character ->
                !character.isLetterOrDigit()
            }
        }

    val passwordsMatch: Boolean
        get() {
            return newPassword.isNotBlank() && newPassword == repeatedNewPassword
        }

    val strengthScore: Int
        get() {
            var score = 0

            if (hasMinimumLength) {
                score += 1
            }

            if (hasLetter) {
                score += 1
            }

            if (hasDigit) {
                score += 1
            }

            if (hasSpecialCharacter) {
                score += 1
            }

            return score
        }

    val strengthProgress: Float
        get() {
            return strengthScore / 4f
        }

    val strengthLabel: String
        get() {
            return when (strengthScore) {
                0, 1 -> "Zayıf şifre"
                2 -> "Orta seviye şifre"
                3 -> "Güçlü şifre"
                else -> "Çok güçlü şifre"
            }
        }

    val canSubmit: Boolean
        get() {
            return currentPassword.isNotBlank() &&
                    hasMinimumLength &&
                    hasLetter &&
                    hasDigit &&
                    passwordsMatch
        }
}

@Composable
fun ChangePasswordScreen(
    onBackClick: () -> Unit = {},
    onPasswordChangeClick: (ChangePasswordFormState) -> Unit = {},
    onLoginActivitiesClick: () -> Unit = {},
    onChangeEmailClick: () -> Unit = {},
    onDeactivateAccountClick: () -> Unit = {},
    isSubmitting: Boolean = false
) {
    val formState = remember {
        mutableStateOf(ChangePasswordFormState())
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
                text = "Şifre Güvenliği"
            )

            Text(
                text = "Şifre Değiştir",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabın için güçlü, tahmin edilmesi zor ve daha önce kullanmadığın yeni bir şifre belirle.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        BbFormSection(
            title = "Yeni şifreni belirle"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Hesabını korumak için en az 8 karakterli, harf ve rakam içeren bir şifre kullan.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbPasswordInput(
                    value = formState.value.currentPassword,
                    onValueChange = { currentPassword ->
                        formState.value = formState.value.copy(
                            currentPassword = currentPassword,
                            validationMessage = null
                        )
                    },
                    label = "Mevcut Şifre",
                    placeholder = "Mevcut şifren"
                )

                BbPasswordInput(
                    value = formState.value.newPassword,
                    onValueChange = { newPassword ->
                        formState.value = formState.value.copy(
                            newPassword = newPassword,
                            validationMessage = null
                        )
                    },
                    label = "Yeni Şifre",
                    placeholder = "Yeni şifren"
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LinearProgressIndicator(
                        progress = {
                            formState.value.strengthProgress
                        },
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.error,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )

                    Text(
                        text = formState.value.strengthLabel,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BbPasswordInput(
                    value = formState.value.repeatedNewPassword,
                    onValueChange = { repeatedNewPassword ->
                        formState.value = formState.value.copy(
                            repeatedNewPassword = repeatedNewPassword,
                            validationMessage = null
                        )
                    },
                    label = "Yeniden Yeni Şifre",
                    placeholder = "Yeni şifreni tekrar gir"
                )

                ChangePasswordRuleGrid(
                    formState = formState.value
                )

                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Text(
                        text = "Şifreni değiştirdikten sonra hesabında güvenlik kontrolü yapılabilir. Şifreni kimseyle paylaşma.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
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
                            onPasswordChangeClick(formState.value)
                        } else {
                            formState.value = formState.value.copy(
                                validationMessage = "Yeni şifre en az 8 karakter olmalı, harf ve rakam içermeli ve tekrar alanı ile eşleşmelidir."
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

        AccountSecurityBottomMenu(
            selectedItem = AccountSecurityMenuItem.ChangePassword,
            onLoginActivitiesClick = onLoginActivitiesClick,
            onChangePasswordClick = {},
            onChangeEmailClick = onChangeEmailClick,
            onDeactivateAccountClick = onDeactivateAccountClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ChangePasswordRuleGrid(
    formState: ChangePasswordFormState
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            modifier = Modifier.fillMaxWidth()
        ) {
            ChangePasswordRuleCard(
                title = "En az 8 karakter",
                completed = formState.hasMinimumLength,
                modifier = Modifier.weight(1f)
            )

            ChangePasswordRuleCard(
                title = "Harf içermeli",
                completed = formState.hasLetter,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            modifier = Modifier.fillMaxWidth()
        ) {
            ChangePasswordRuleCard(
                title = "Rakam içermeli",
                completed = formState.hasDigit,
                modifier = Modifier.weight(1f)
            )

            ChangePasswordRuleCard(
                title = "Özel karakter önerilir",
                completed = formState.hasSpecialCharacter,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ChangePasswordRuleCard(
    title: String,
    completed: Boolean,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small
    ) {
        Text(
            text = if (completed) {
                "✓ $title"
            } else {
                "○ $title"
            },
            style = MaterialTheme.typography.labelMedium,
            color = if (completed) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    }
}