package com.bulbulustur.android.Application.Views.Logon

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.foundation.layout.width
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.LogonPublicFieldLabel
import com.bulbulustur.android.Application.Views.Shared.LogonPublicPageTitle
import com.bulbulustur.android.Application.Views.Shared.LogonPublicRegisterLegalFooter
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.Views.Shared.LogonPublicTextField
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun FirstDoorScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onContinueClick: (email: String) -> Unit = {},
    onInputChanged: () -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var email by remember {
        mutableStateOf("")
    }

    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        },
        footer = {
            LogonPublicRegisterLegalFooter()
        }
    ) {
        LogonPublicPageTitle(
            eyebrow = BBLocalization.Current.Get(key = "67f4719d-9886-4fbc-9fad-0ef6a100db26", fallback = "Yeni Üyelik"),
            title = BBLocalization.Current.Get(key = "e6f44bb3-1a51-4db7-86d7-ab38d008440a", fallback = "E-posta Adresinizi Girin"),
            description = BBLocalization.Current.Get(key = "ff3bf20d-7ddc-4034-a9d5-000c7ea4a69c", fallback = "Kayıt işlemini başlatmak için e-posta adresinize bir doğrulama bağlantısı göndereceğiz.")
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space8
                )
        )

        LogonPublicFieldLabel(
            text = BBLocalization.Current.Get(key = "1246f9ff-205d-4d92-84ee-7c8c7a3f2d46", fallback = "")
        )

        Spacer(
            modifier =
                Modifier.width(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value = email,
            onValueChange = {
                email = it
                onInputChanged()
            },
            placeholder = BBLocalization.Current.Get(key = "457d3d09-532e-4d44-b7fc-b6f18f43d5f7", fallback = ""),
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

            Text(
                text = errorMessage,
                style =
                    MaterialTheme.typography.bodySmall,
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
                    BBLocalization.Current.Get(key = "747533e0-13c8-4b49-82ca-ebf9fea6b37f", fallback = "Gönderiliyor")
                } else {
                    BBLocalization.Current.Get(key = "3bd173f1-2a4e-41b6-a560-738fee4b4324", fallback = "Doğrulama Bağlantısı Gönder")
                },
            onClick = {
                if (!isLoading) {
                    onContinueClick(
                        email
                    )
                }
            },
            variant =
                BbButtonVariant.Primary,
            size =
                BbButtonSize.Large
        )

        if (isLoading) {
            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space4
                    )
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                CircularProgressIndicator()

                Spacer(
                    modifier =
                        Modifier.width(
                            BBSpacing.Space2
                        )
                )

                Text(
                    text = BBLocalization.Current.Get(key = "0b721994-9d50-4a43-8fcf-82c33dfde21e", fallback = "Doğrulama e-postası hazırlanıyor."),
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

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
                text = BBLocalization.Current.Get(key = "450a3be3-a073-4365-a1a5-5d8a0e7ae4e7", fallback = "Zaten hesabınız var mı?"),
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            TextButton(
                onClick =
                    onBackToLogonClick
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "72289e9d-49e1-4c2a-8b0b-3ab5a67610a6", fallback = "Giriş Yap"),
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

@Preview(
    showBackground = true
)
@Composable
private fun FirstDoorScreenPreview() {
    BbTheme {
        FirstDoorScreen()
    }
}