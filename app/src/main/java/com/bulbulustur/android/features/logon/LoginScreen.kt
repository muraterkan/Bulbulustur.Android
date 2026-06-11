package com.bulbulustur.android.features.logon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.R
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun LoginScreen(
    onLogonClick: (email: String, password: String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        }
    ) {
        LogonPublicPageTitle(
            eyebrow = "Bulbulustur Hesabı",
            title = "Giriş Yap",
            description = "Hesabınıza giriş yapın, alışveriş ve toptan talep akışlarınıza devam edin."
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

        Spacer(modifier = Modifier.height(BbSpacing.Space5))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogonPublicFieldLabel(
                text = "Şifre"
            )

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = onForgotPasswordClick
            ) {
                Text(
                    text = "Şifremi Unuttum",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = BbColors.TextSubtle
                )
            }
        }

        LogonPublicTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = "Şifreniz",
            trailingContent = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Text(
                        text = if (isPasswordVisible) {
                            "Gizle"
                        } else {
                            "Göster"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = BbColors.TextSubtle
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space7))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Giriş Yap",
            onClick = {
                onLogonClick(email, password)
            },
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space5))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hesabınız yok mu?",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )

            TextButton(
                onClick = onRegisterClick
            ) {
                Text(
                    text = "Kayıt Ol",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }
        }

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

        LogonDividerWithText(
            text = "veya"
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space5))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            SocialLoginButton(
                modifier = Modifier.weight(1f),
                label = "Google",
                iconResId = R.drawable.ic_google,
                onClick = onGoogleClick
            )

            SocialLoginButton(
                modifier = Modifier.weight(1f),
                label = "Facebook",
                iconResId = R.drawable.ic_facebook,
                onClick = onFacebookClick
            )
        }
    }
}

@Composable
private fun SocialLoginButton(
    label: String,
    iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BbButton(
        modifier = modifier.defaultMinSize(minHeight = BbSpacing.Space14),
        text = label,
        onClick = onClick,
        variant = BbButtonVariant.Outline,
        size = BbButtonSize.Large,
        leadingIcon = {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(BbIcon.SizeLg)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    BbTheme {
        LoginScreen()
    }
}