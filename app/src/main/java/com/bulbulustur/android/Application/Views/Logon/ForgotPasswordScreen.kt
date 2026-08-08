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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
                BBLocalization.Current.Get(key = "2463a1ba-32ae-4708-83d2-2ee47a3fea4e", fallback = "Hesap Kurtarma"),
            title =
                BBLocalization.Current.Get(key = "7719d233-45c9-4a08-9de7-e5fc19c7c4f6", fallback = "Şifremi Unuttum"),
            description =
                if (isSuccessful) {
                    BBLocalization.Current.Get(key = "f97201ef-bdb9-4eba-85d1-a5f4273c9c58", fallback = "Şifre yenileme talebiniz alındı.")
                } else {
                    BBLocalization.Current.Get(key = "4df5ca64-65e6-4185-acf6-422de95065b4", fallback = "E-posta adresinizi yazın. Şifre yenileme bağlantısını size gönderelim.")
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
                    BBLocalization.Current.Get(key = "6777bc00-123b-4116-9093-08c80fb9d405", fallback = "Giriş Ekranına Dön"),
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
                BBLocalization.Current.Get(key = "1246f9ff-205d-4d92-84ee-7c8c7a3f2d46", fallback = "")
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
                BBLocalization.Current.Get(key = "457d3d09-532e-4d44-b7fc-b6f18f43d5f7", fallback = ""),
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
                BBLocalization.Current.Get(key = "46ed67d0-2b86-45cb-86f6-4d84bd233de7", fallback = "Şifre Yenileme Bağlantısı Gönder"),
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
                BBLocalization.Current.Get(key = "6777bc00-123b-4116-9093-08c80fb9d405", fallback = "Giriş Ekranına Dön"),
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
                    BBLocalization.Current.Get(key = "6ac31ec1-45b7-4055-a728-f7f977d47d26", fallback = "Hesabınızı hatırladınız mı?"),
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
                        BBLocalization.Current.Get(key = "72289e9d-49e1-4c2a-8b0b-3ab5a67610a6", fallback = "Giriş Yap"),
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
                            BBLocalization.Current.Get(key = "f5f2b290-84ab-4bed-9172-96690928ecde", fallback = "İşlem Başarılı")
                        } else {
                            BBLocalization.Current.Get(key = "aee471fe-aa14-4587-897c-f5a3489aa980", fallback = "İşlem Tamamlanamadı")
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
                    BBLocalization.Current.Get(key = "545e09b0-df75-4578-8b69-d0b818cb0eda", fallback = "Güvenlik notu"),
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
                    BBLocalization.Current.Get(key = "a69e0547-8db8-432e-82f2-39a39e92e4be", fallback = "Bağlantı yalnızca kısa süre geçerli olur. Hesabınızı korumak için yeni şifrenizi kimseyle paylaşmayın."),
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