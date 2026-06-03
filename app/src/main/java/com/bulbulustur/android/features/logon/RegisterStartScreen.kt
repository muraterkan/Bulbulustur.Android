package com.bulbulustur.android.features.logon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun RegisterStartScreen(
    onContinueClick: (registerStartForm: RegisterStartForm) -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedAccountType by remember { mutableStateOf(RegisterAccountType.Individual) }
    var isAgreementAccepted by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = BbSpacing.PageHorizontalWide),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(BbSpacing.PageTop))

            RegisterStartTopBar(
                onLanguageClick = onLanguageClick
            )

            Spacer(modifier = Modifier.size(BbSpacing.Space8))

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Elevated,
                padding = BbCardPadding.Large
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RegisterStartBadge(
                        text = "Yeni Üyelik"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Text(
                        text = "Kayıt Ol",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    Text(
                        text = "Bulbulustur hesabınızı oluşturmak için temel bilgilerinizi girin.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    Text(
                        text = "Hesap Türü",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

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

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    RegisterStartFieldLabel(
                        text = "Ad"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    RegisterStartTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                        },
                        leadingText = "👤"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space4))

                    RegisterStartFieldLabel(
                        text = "Soyad"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    RegisterStartTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                        },
                        leadingText = "👤"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space4))

                    RegisterStartFieldLabel(
                        text = "E-posta"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    RegisterStartTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        leadingText = "✉",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space4))

                    RegisterStartFieldLabel(
                        text = "Telefon"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    RegisterStartTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                        },
                        leadingText = "☎",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        )
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space4))

                    RegisterStartFieldLabel(
                        text = "Şifre"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    RegisterStartTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        leadingText = "🔒",
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Checkbox(
                            checked = isAgreementAccepted,
                            onCheckedChange = {
                                isAgreementAccepted = it
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.primary,
                                uncheckedColor = MaterialTheme.colorScheme.outline,
                                checkmarkColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )

                        Column(
                            modifier = Modifier.padding(top = BbSpacing.Space2)
                        ) {
                            Text(
                                text = "Üyelik koşullarını, gizlilik politikasını ve KVKK aydınlatma metnini okudum, kabul ediyorum.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    BbButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Devam Et",
                        onClick = {
                            onContinueClick(
                                RegisterStartForm(
                                    firstName = firstName,
                                    lastName = lastName,
                                    email = email,
                                    phone = phone,
                                    password = password,
                                    accountType = selectedAccountType,
                                    isAgreementAccepted = isAgreementAccepted
                                )
                            )
                        },
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Large,
                        enabled = isAgreementAccepted
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space4))

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

            Spacer(modifier = Modifier.size(BbSpacing.Space12))

            Text(
                text = "© 2026 Bulbulustur - Tüm hakları saklıdır",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.size(BbSpacing.PageBottomCompact))
        }
    }
}

@Composable
private fun RegisterStartTopBar(
    onLanguageClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                append("bulbulustur")

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append(".")
                }
            },
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onLanguageClick,
            shape = BbRadius.Button
        ) {
            Text(
                text = "🌐",
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.size(BbSpacing.IconTextGap))

            Text(
                text = "Türkçe",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
private fun RegisterStartBadge(
    text: String
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = BbRadius.Badge
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            ),
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun RegisterStartFieldLabel(
    text: String
) {
    Text(
        text = buildAnnotatedString {
            append(text)
            append(" ")

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.error
                )
            ) {
                append("*")
            }
        },
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun RegisterStartTextField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingText: String,
    modifier: Modifier = Modifier,
    visualTransformation: androidx.compose.ui.text.input.VisualTransformation = androidx.compose.ui.text.input.VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = BbSpacing.Space14),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = {
            Text(
                text = leadingText,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            disabledIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = BbRadius.Input
    )
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
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.size(BbSpacing.Space1))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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