package com.bulbulustur.android.Application.Views.Logon

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.LogonPublicFieldLabel
import com.bulbulustur.android.Application.Views.Shared.LogonPublicPageTitle
import com.bulbulustur.android.Application.Views.Shared.LogonPublicRegisterLegalFooter
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.Views.Shared.LogonPublicTextField
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun RegisterStartScreen(
    onContinueClick: (registerStartForm: RegisterStartForm) -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var selectedAccountType by remember {
        mutableStateOf(RegisterAccountType.Individual)
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
            eyebrow = "Yeni Üyelik",
            title = "Kayıt Ol",
            description = "Bulbulustur hesabınızı oluşturmak için temel bilgilerinizi girin."
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space8))

        Text(
            text = "Hesap Türü",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            RegisterAccountTypeCard(
                modifier = Modifier.weight(1f),
                title = "Bireysel",
                description = "Alıcı hesabı",
                isSelected = selectedAccountType == RegisterAccountType.Individual,
                onClick = {
                    selectedAccountType = RegisterAccountType.Individual
                }
            )

            RegisterAccountTypeCard(
                modifier = Modifier.weight(1f),
                title = "Kurumsal",
                description = "Firma alıcısı",
                isSelected = selectedAccountType == RegisterAccountType.Company,
                onClick = {
                    selectedAccountType = RegisterAccountType.Company
                }
            )
        }

        Spacer(modifier = Modifier.height(BBSpacing.Space6))

        LogonPublicFieldLabel(
            text = "Ad"
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

        LogonPublicTextField(
            value = firstName,
            onValueChange = {
                firstName = it
            },
            placeholder = "Adınız"
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space4))

        LogonPublicFieldLabel(
            text = "Soyad"
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

        LogonPublicTextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            placeholder = "Soyadınız"
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space4))

        LogonPublicFieldLabel(
            text = "E-posta"
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

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

        Spacer(modifier = Modifier.height(BBSpacing.Space4))

        LogonPublicFieldLabel(
            text = "Telefon"
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

        LogonPublicTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            placeholder = "Telefon numaranız",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space4))

        LogonPublicFieldLabel(
            text = "Şifre"
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

        LogonPublicTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = "Şifreniz",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space7))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Hesap Oluştur",
            onClick = {
                onContinueClick(
                    RegisterStartForm(
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        phone = phone,
                        password = password,
                        accountType = selectedAccountType,
                        isAgreementAccepted = true
                    )
                )
            },
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space5))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Zaten hesabınız var mı?",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            TextButton(
                onClick = onBackToLogonClick
            ) {
                Text(
                    text = "Giriş Yap",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun RegisterAccountTypeCard(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }

    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val titleColor = if (isSelected) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val descriptionColor = if (isSelected) {
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Surface(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = BBRadius.Card
            )
            .clickable {
                onClick()
            },
        color = backgroundColor,
        shape = BBRadius.Card
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.CardPaddingCompact)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(BBSpacing.Space1))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = descriptionColor
            )
        }
    }
}

data class RegisterStartForm(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val password: String,
    val accountType: RegisterAccountType,
    val isAgreementAccepted: Boolean
)

enum class RegisterAccountType {
    Individual,
    Company
}

@Preview(showBackground = true)
@Composable
private fun RegisterStartScreenPreview() {
    BbTheme {
        RegisterStartScreen()
    }
}

