package com.bulbulustur.android.Application.Views.Logon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.width
import com.bulbulustur.android.Application.Views.Shared.LogonPublicFieldLabel
import com.bulbulustur.android.Application.Views.Shared.LogonPublicPageTitle
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.Views.Shared.LogonPublicTextField
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun ForgotPasswordScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    successMessage: String? = null,
    onSendResetLinkClick: (email: String) -> Unit = {},
    onInputChanged: () -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var email by remember {
        mutableStateOf("")
    }

    val isSuccessful =
        !successMessage.isNullOrBlank()

    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        }
    ) {
        LogonPublicPageTitle(
            eyebrow =
                "Hesap Kurtarma",
            title =
                "Şifremi Unuttum",
            description =
                if (isSuccessful) {
                    "Şifre yenileme talebiniz alındı."
                } else {
                    "E-posta adresinizi yazın. Şifre yenileme bağlantısını size gönderelim."
                }
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space8
                )
        )

        if (isSuccessful) {
            ForgotPasswordMessageBox(
                message =
                    successMessage.orEmpty(),
                isSuccess =
                    true
            )

            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space5
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
                    BbButtonVariant.Primary,
                size =
                    BbButtonSize.Large
            )

            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space6
                    )
            )

            ResetPasswordInfoBox()

            return@LogonPublicScaffold
        }

        LogonPublicFieldLabel(
            text =
                "E-posta"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value =
                email,
            onValueChange = { value ->
                email =
                    value

                onInputChanged()
            },
            placeholder =
                "E-posta adresiniz",
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Email
                )
        )

        if (!errorMessage.isNullOrBlank()) {
            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )

            ForgotPasswordMessageBox(
                message =
                    errorMessage,
                isSuccess =
                    false
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
                "Şifre Yenileme Bağlantısı Gönder",
            onClick = {
                onSendResetLinkClick(
                    email
                )
            },
            variant =
                BbButtonVariant.Primary,
            size =
                BbButtonSize.Large,
            enabled =
                !isLoading,
            isLoading =
                isLoading
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
                    BBSpacing.Space6
                )
        )

        ResetPasswordInfoBox()

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space5
                )
        )

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Text(
                text =
                    "Hesabınızı hatırladınız mı?",
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            TextButton(
                onClick =
                    onBackToLogonClick,
                enabled =
                    !isLoading
            ) {
                Text(
                    text =
                        "Giriş Yap",
                    style =
                        MaterialTheme.typography.labelLarge,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun ForgotPasswordMessageBox(
    message: String,
    isSuccess: Boolean
) {
    val containerColor =
        if (isSuccess) {
            BBColors.Green.Green50
        } else {
            BBColors.Red.Red50
        }

    val contentColor =
        if (isSuccess) {
            BBColors.Green.Green700
        } else {
            BBColors.Red.Red700
        }

    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        color =
            containerColor,
        contentColor =
            contentColor,
        shape =
            BBRadius.Card
    ) {
        Row(
            modifier =
                Modifier.padding(
                    BBSpacing.CardPaddingCompact
                ),
            verticalAlignment =
                Alignment.Top
        ) {
            Icon(
                imageVector =
                    if (isSuccess) {
                        Icons.Outlined.CheckCircle
                    } else {
                        Icons.Outlined.ErrorOutline
                    },
                contentDescription =
                    null,
                modifier =
                    Modifier.padding(
                        top =
                            BBSpacing.Space1
                    ),
                tint =
                    contentColor
            )

            Spacer(
                modifier =
                    Modifier.width(
                        BBSpacing.Space3
                    )
            )

            Column(
                modifier =
                    Modifier
                        .weight(
                            1f
                        )
            ) {
                Text(
                    text =
                        if (isSuccess) {
                            "İşlem Başarılı"
                        } else {
                            "İşlem Tamamlanamadı"
                        },
                    style =
                        MaterialTheme.typography.labelLarge,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        contentColor
                )

                Spacer(
                    modifier =
                        Modifier.height(
                            BBSpacing.Space1
                        )
                )

                Text(
                    text =
                        message,
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        contentColor
                )
            }
        }
    }
}

@Composable
private fun ResetPasswordInfoBox() {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        color =
            MaterialTheme.colorScheme.surfaceVariant,
        shape =
            BBRadius.Card
    ) {
        Column(
            modifier =
                Modifier.padding(
                    BBSpacing.CardPaddingCompact
                )
        ) {
            Text(
                text =
                    "Güvenlik notu",
                style =
                    MaterialTheme.typography.labelLarge,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onSurface
            )

            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space1
                    )
            )

            Text(
                text =
                    "Bağlantı yalnızca kısa süre geçerli olur. Hesabınızı korumak için yeni şifrenizi kimseyle paylaşmayın.",
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(
    showBackground =
        true
)
@Composable
private fun ForgotPasswordScreenPreview() {
    BbTheme {
        ForgotPasswordScreen()
    }
}