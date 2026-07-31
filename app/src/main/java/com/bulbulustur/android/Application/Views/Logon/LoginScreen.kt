package com.bulbulustur.android.Application.Views.Logon

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.LogonDividerWithText
import com.bulbulustur.android.Application.Views.Shared.LogonPublicFieldLabel
import com.bulbulustur.android.Application.Views.Shared.LogonPublicPageTitle
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.Views.Shared.LogonPublicTextField
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.R

@Composable
fun LoginScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onLogonClick: (
        email: String,
        password: String
    ) -> Unit = { _, _ -> },
    onInputChanged: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    val focusManager =
        LocalFocusManager.current

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    fun SubmitLogin() {
        if (isLoading) {
            return
        }

        focusManager.clearFocus()

        onLogonClick(
            email,
            password
        )
    }

    LogonPublicScaffold(
        onLanguageSelected = {
            if (!isLoading) {
                onLanguageClick()
            }
        }
    ) {
        LogonPublicPageTitle(
            eyebrow = "Bulbulustur Hesabı",
            title = BBLocalization.Current.Get(key = "72289e9d-49e1-4c2a-8b0b-3ab5a67610a6", fallback = "Giriş Yap"),
            description =
                "Hesabınıza giriş yapın, alışveriş ve toptan talep akışlarınıza devam edin."
        )

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space8
            )
        )

        LogonPublicFieldLabel(
            text = BBLocalization.Current.Get(key = "1246f9ff-205d-4d92-84ee-7c8c7a3f2d46", fallback = "")
        )

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space2
            )
        )

        LogonPublicTextField(
            value = email,
            onValueChange = { value ->
                email = value
                onInputChanged()
            },
            placeholder = BBLocalization.Current.Get(key = "457d3d09-532e-4d44-b7fc-b6f18f43d5f7", fallback = ""),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space5
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            LogonPublicFieldLabel(
                text = BBLocalization.Current.Get(key = "f3bfe2a5-b25a-45b4-99f9-fbe3fb89bef5", fallback = "Şifre")
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            TextButton(
                onClick = {
                    if (!isLoading) {
                        onForgotPasswordClick()
                    }
                }
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "7719d233-45c9-4a08-9de7-e5fc19c7c4f6", fallback = "Şifremi Unuttum"),
                    style =
                        MaterialTheme.typography.labelLarge,
                    fontWeight =
                        FontWeight.SemiBold,
                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )
            }
        }

        LogonPublicTextField(
            value = password,
            onValueChange = { value ->
                password = value
                onInputChanged()
            },
            placeholder = "Şifreniz",
            trailingContent = {
                IconButton(
                    onClick = {
                        isPasswordVisible =
                            !isPasswordVisible
                    }
                ) {
                    Text(
                        text = if (
                            isPasswordVisible
                        ) {
                            "Gizle"
                        } else {
                            "Göster"
                        },
                        style =
                            MaterialTheme.typography
                                .labelSmall,
                        fontWeight =
                            FontWeight.SemiBold,
                        color =
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                    )
                }
            },
            visualTransformation =
                if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            keyboardOptions = KeyboardOptions(
                keyboardType =
                    KeyboardType.Password,
                imeAction =
                    ImeAction.Done
            )
        )

        if (!errorMessage.isNullOrBlank()) {
            Spacer(
                modifier = Modifier.height(
                    BBSpacing.Space3
                )
            )

            Text(
                text = errorMessage,
                style =
                    MaterialTheme.typography.bodySmall,
                fontWeight =
                    FontWeight.Medium,
                color =
                    MaterialTheme.colorScheme.error
            )
        }

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space7
            )
        )

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = if (isLoading) {
                "Giriş Yapılıyor..."
            } else {
                BBLocalization.Current.Get(key = "72289e9d-49e1-4c2a-8b0b-3ab5a67610a6", fallback = "Giriş Yap")
            },
            onClick = {
                SubmitLogin()
            },
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space5
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.Center,
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "84fcb238-af63-4127-81a8-8b258d53a822", fallback = "Hesabınız yok mu?"),
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )

            TextButton(
                onClick = {
                    if (!isLoading) {
                        onRegisterClick()
                    }
                }
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "2f3f4ea5-b7c1-4ebc-b42d-f81b4f84abec", fallback = ""),
                    style =
                        MaterialTheme.typography
                            .labelLarge,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme
                            .onSurface
                )
            }
        }

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space2
            )
        )

        LogonDividerWithText(
            text = "veya"
        )

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space5
            )
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            SocialLoginButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Google ile devam et",
                iconResId = R.drawable.ic_google,
                isLoading = isLoading,
                onClick = onGoogleClick
            )

            SocialLoginButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Facebook ile devam et",
                iconResId = R.drawable.ic_facebook,
                isLoading = isLoading,
                onClick = onFacebookClick
            )
        }
    }
}

@Composable
private fun SocialLoginButton(
    label: String,
    iconResId: Int,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BbButton(
        modifier = modifier.defaultMinSize(
            minHeight = BBSpacing.Space14
        ),
        text = label,
        onClick = {
            if (!isLoading) {
                onClick()
            }
        },
        variant = BbButtonVariant.Outline,
        size = BbButtonSize.Large,
        leadingIcon = {
            Image(
                painter = painterResource(
                    id = iconResId
                ),
                contentDescription = null,
                modifier = Modifier.size(
                    BBIcon.SizeLg
                )
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    BbTheme {
        LoginScreen(
            errorMessage =
                "E-posta adresi veya şifre hatalı."
        )
    }
}