package com.bulbulustur.android.features.logon

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun LogonScreen(
    onLogonClick: (email: String, password: String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("muraterkan500@gmail.com") }
    var password by remember { mutableStateOf("12345678") }
    var isPasswordVisible by remember { mutableStateOf(false) }

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

            LogonTopBar(
                onLanguageClick = onLanguageClick
            )

            Spacer(modifier = Modifier.size(BbSpacing.Space12))

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Elevated,
                padding = BbCardPadding.Large
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LogonBadge(
                        text = "Bulbulustur Hesabı"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Text(
                        text = "Giriş Yap",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    Text(
                        text = "Hoş geldiniz! Hesabınıza giriş yapın.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space7))

                    LogonFieldLabel(
                        text = "E-posta"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    LogonTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        leadingText = "✉",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LogonFieldLabel(
                            text = "Şifre"
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(
                            onClick = onForgotPasswordClick
                        ) {
                            Text(
                                text = "Şifremi Unuttum!",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    LogonTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        leadingText = "🔒",
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
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
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

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    BbButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Giriş Yap",
                        onClick = {
                            onLogonClick(email, password)
                        },
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Large
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Hala bir hesabınız yok mu?",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        TextButton(
                            onClick = onRegisterClick
                        ) {
                            Text(
                                text = "Kayıt Ol",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    LogonDividerWithText(
                        text = "yada"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                    ) {
                        ExternalLogonButton(
                            modifier = Modifier.weight(1f),
                            label = "Google",
                            markerText = "G",
                            markerColor = BbColors.Blue.Blue500,
                            onClick = onGoogleClick
                        )

                        ExternalLogonButton(
                            modifier = Modifier.weight(1f),
                            label = "Facebook",
                            markerText = "f",
                            markerColor = BbColors.Blue.Blue600,
                            onClick = onFacebookClick
                        )
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
private fun LogonTopBar(
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

            Spacer(modifier = Modifier.width(BbSpacing.IconTextGap))

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
private fun LogonBadge(
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
private fun LogonFieldLabel(
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
private fun LogonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingText: String,
    modifier: Modifier = Modifier,
    trailingContent: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
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
        trailingIcon = trailingContent,
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
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = BbRadius.Input
    )
}

@Composable
private fun LogonDividerWithText(
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.outlineVariant
        )

        Text(
            modifier = Modifier.padding(horizontal = BbSpacing.Space4),
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Divider(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}

@Composable
private fun ExternalLogonButton(
    label: String,
    markerText: String,
    markerColor: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BbButton(
        modifier = modifier,
        text = label,
        onClick = onClick,
        variant = BbButtonVariant.Outline,
        size = BbButtonSize.Large,
        leadingIcon = {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxXs)
                    .background(
                        color = markerColor,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = markerText,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Black,
                    color = BbColors.White
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LogonScreenPreview() {
    BbTheme {
        LogonScreen()
    }
}