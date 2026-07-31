package com.bulbulustur.android.Application.Views.Logon

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Views.Shared.LogonPublicFieldLabel
import com.bulbulustur.android.Application.Views.Shared.LogonPublicPageTitle
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.Views.Shared.LogonPublicTextField
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun SetNewPasswordScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onUpdatePasswordClick: (
        newPassword: String,
        reNewPassword: String
    ) -> Unit = { _, _ -> },
    onInputChanged: () -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    val focusManager =
        LocalFocusManager.current

    var newPassword by remember {
        mutableStateOf("")
    }

    var reNewPassword by remember {
        mutableStateOf("")
    }

    var isNewPasswordVisible by remember {
        mutableStateOf(false)
    }

    var isReNewPasswordVisible by remember {
        mutableStateOf(false)
    }

    fun SubmitPassword() {
        if (isLoading) {
            return
        }

        focusManager.clearFocus()

        onUpdatePasswordClick(
            newPassword,
            reNewPassword
        )
    }

    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        }
    ) {
        LogonPublicPageTitle(
            eyebrow =
                "Hesap Kurtarma",
            title =
                BBLocalization.Current.Get(key = "b950c83e-4e34-4b6d-a1fa-053f26502e63", fallback = "Yeni Şifre Belirle"),
            description =
                "Hesabınız için yeni bir şifre oluşturun. Şifreniz 8 ile 16 karakter arasında olmalıdır."
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space8
                )
        )

        LogonPublicFieldLabel(
            text =
                BBLocalization.Current.Get(key = "868ebdd6-edca-430d-b987-78f5a7c89abd", fallback = "Yeni Şifre")
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value =
                newPassword,
            onValueChange = { value ->
                newPassword =
                    value

                onInputChanged()
            },
            placeholder =
                BBLocalization.Current.Get(key = "868ebdd6-edca-430d-b987-78f5a7c89abd", fallback = "Yeni şifreniz"),
            trailingContent = {
                TextButton(
                    onClick = {
                        isNewPasswordVisible =
                            !isNewPasswordVisible
                    },
                    enabled =
                        !isLoading
                ) {
                    Text(
                        text =
                            if (isNewPasswordVisible) {
                                "Gizle"
                            } else {
                                "Göster"
                            },
                        style =
                            MaterialTheme.typography.labelSmall,
                        fontWeight =
                            FontWeight.SemiBold,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            visualTransformation =
                if (isNewPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password,
                    imeAction =
                        ImeAction.Next
                )
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space5
                )
        )

        LogonPublicFieldLabel(
            text =
                "Yeni Şifre Tekrar"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value =
                reNewPassword,
            onValueChange = { value ->
                reNewPassword =
                    value

                onInputChanged()
            },
            placeholder =
                BBLocalization.Current.Get(key = "f5a11c37-9ee5-47c0-a178-6343ee5bc63d", fallback = "Yeni şifrenizi tekrar girin"),
            trailingContent = {
                TextButton(
                    onClick = {
                        isReNewPasswordVisible =
                            !isReNewPasswordVisible
                    },
                    enabled =
                        !isLoading
                ) {
                    Text(
                        text =
                            if (isReNewPasswordVisible) {
                                "Gizle"
                            } else {
                                "Göster"
                            },
                        style =
                            MaterialTheme.typography.labelSmall,
                        fontWeight =
                            FontWeight.SemiBold,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            visualTransformation =
                if (isReNewPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password,
                    imeAction =
                        ImeAction.Done
                )
        )

        if (!errorMessage.isNullOrBlank()) {
            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )

            Text(
                text =
                    errorMessage,
                style =
                    MaterialTheme.typography.bodySmall,
                fontWeight =
                    FontWeight.Medium,
                color =
                    MaterialTheme.colorScheme.error
            )
        }

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space7
                )
        )

        BbButton(
            modifier =
                Modifier.fillMaxWidth(),
            text =
                if (isLoading) {
                    "Şifre Güncelleniyor..."
                } else {
                    "Şifremi Güncelle"
                },
            onClick = {
                SubmitPassword()
            },
            variant =
                BbButtonVariant.Primary,
            size =
                BbButtonSize.Large,
            enabled =
                !isLoading
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        BbButton(
            modifier =
                Modifier.fillMaxWidth(),
            text =
                "Giriş Ekranına Dön",
            onClick =
                onBackToLogonClick,
            variant =
                BbButtonVariant.Outline,
            size =
                BbButtonSize.Large,
            enabled =
                !isLoading
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space5
                )
        )

        Text(
            text =
                "Şifrenizi başka hesaplarda kullandığınız şifrelerden farklı belirlemeniz önerilir.",
            style =
                MaterialTheme.typography.bodySmall,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(
    showBackground =
        true
)
@Composable
private fun SetNewPasswordScreenPreview() {
    BbTheme {
        SetNewPasswordScreen()
    }
}