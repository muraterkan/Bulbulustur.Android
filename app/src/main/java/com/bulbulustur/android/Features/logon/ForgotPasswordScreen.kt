package com.bulbulustur.android.Features.logon

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTheme

@Composable
fun ForgotPasswordScreen(
    onSendResetLinkClick: (email: String) -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var email by remember {
        mutableStateOf("")
    }

    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        }
    ) {
        LogonPublicPageTitle(
            eyebrow = "Hesap Kurtarma",
            title = "Şifremi Unuttum",
            description = "E-posta adresinizi yazın. Şifre yenileme bağlantısını size gönderelim."
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space8))

        LogonPublicFieldLabel(
            text = "E-posta"
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

        LogonPublicTextField(
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = "E-posta adresiniz",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space7))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Şifre Yenileme Bağlantısı Gönder",
            onClick = {
                onSendResetLinkClick(email)
            },
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space4))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Giriş Ekranına Dön",
            onClick = onBackToLogonClick,
            variant = BbButtonVariant.Outline,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space6))

        ResetPasswordInfoBox()

        Spacer(modifier = Modifier.height(BbSpacing.Space5))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hesabınızı hatırladınız mı?",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )

            TextButton(
                onClick = onBackToLogonClick
            ) {
                Text(
                    text = "Giriş Yap",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }
        }
    }
}

@Composable
private fun ResetPasswordInfoBox() {
    androidx.compose.material3.Surface(
        modifier = Modifier.fillMaxWidth(),
        color = BbColors.SurfaceMuted,
        shape = com.bulbulustur.android.Ui.theme.BbRadius.Card
    ) {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(BbSpacing.CardPaddingCompact)
        ) {
            Text(
                text = "Güvenlik notu",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Spacer(modifier = Modifier.height(BbSpacing.Space1))

            Text(
                text = "Bağlantı yalnızca kısa süre geçerli olur. Hesabınızı korumak için yeni şifrenizi kimseyle paylaşmayın.",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextSubtle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ForgotPasswordScreenPreview() {
    BbTheme {
        ForgotPasswordScreen()
    }
}