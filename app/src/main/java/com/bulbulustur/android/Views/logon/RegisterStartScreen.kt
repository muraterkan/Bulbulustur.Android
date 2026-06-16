package com.bulbulustur.android.Views.logon

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
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

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

        Spacer(modifier = Modifier.height(BbSpacing.Space8))

        Text(
            text = "Hesap Türü",
            style = MaterialTheme.typography.labelLarge,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
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

        Spacer(modifier = Modifier.height(BbSpacing.Space6))

        LogonPublicFieldLabel(
            text = "Ad"
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

        LogonPublicTextField(
            value = firstName,
            onValueChange = {
                firstName = it
            },
            placeholder = "Adınız"
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space4))

        LogonPublicFieldLabel(
            text = "Soyad"
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

        LogonPublicTextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            placeholder = "Soyadınız"
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space4))

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

        Spacer(modifier = Modifier.height(BbSpacing.Space4))

        LogonPublicFieldLabel(
            text = "Telefon"
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

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

        Spacer(modifier = Modifier.height(BbSpacing.Space4))

        LogonPublicFieldLabel(
            text = "Şifre"
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

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

        Spacer(modifier = Modifier.height(BbSpacing.Space7))

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

        Spacer(modifier = Modifier.height(BbSpacing.Space5))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Zaten hesabınız var mı?",
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
        BbColors.Border
    }

    val backgroundColor = if (isSelected) {
        BbColors.PrimarySoft
    } else {
        BbColors.SurfaceMuted
    }

    val titleColor = if (isSelected) {
        BbColors.TextStrong
    } else {
        BbColors.TextStrong
    }

    val descriptionColor = if (isSelected) {
        BbColors.TextSubtle
    } else {
        BbColors.TextMuted
    }

    Surface(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = BbRadius.Card
            )
            .clickable {
                onClick()
            },
        color = backgroundColor,
        shape = BbRadius.Card
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.CardPaddingCompact)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(BbSpacing.Space1))

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