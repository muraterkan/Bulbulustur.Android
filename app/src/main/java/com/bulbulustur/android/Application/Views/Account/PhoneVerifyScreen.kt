package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun PhoneVerifyScreen(
    phone: String,
    isLoading: Boolean = false,
    currentAction: String? = null,
    errorMessage: String? = null,
    successMessage: String? = null,
    onBackClick: () -> Unit = {},
    onVerifyClick: (String) -> Unit = {},
    onResendCodeClick: () -> Unit = {}
) {
    val verificationCodeState = remember {
        mutableStateOf("")
    }

    val normalizedCode = verificationCodeState.value
        .filter { character ->
            character.isDigit()
        }
        .take(4)

    val isPhoneLoading = isLoading &&
            currentAction == "GetPhone"

    val isVerifying = isLoading &&
            currentAction == "VerifyPhone"

    val isSendingSms = isLoading &&
            currentAction == "SendPhoneVerificationSms"

    val canVerify = normalizedCode.length == 4 &&
            !isVerifying &&
            !isSendingSms &&
            !isPhoneLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Telefon Doğrulama",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    PaddingValues(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
        ) {
            if (isPhoneLoading) {
                CircularProgressIndicator()
            } else {
                PhoneVerifyIntroCard(
                    phone = phone
                )

                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                        ) {
                            Text(
                                text = "Doğrulama Kodu",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                text = "$phone numarasına gönderilen 4 haneli kodu girin.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        OutlinedTextField(
                            value = normalizedCode,
                            onValueChange = { value ->
                                verificationCodeState.value = value
                                    .filter { character ->
                                        character.isDigit()
                                    }
                                    .take(4)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(text = "Kod")
                            },
                            placeholder = {
                                Text(text = "4 haneli kod")
                            },
                            singleLine = true,
                            enabled = !isVerifying && !isSendingSms,
                            shape = BBRadius.Input,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                                focusedLabelColor = MaterialTheme.colorScheme.primary,
                                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )

                        errorMessage
                            ?.takeIf { it.isNotBlank() }
                            ?.let { message ->
                                Text(
                                    text = message,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }

                        successMessage
                            ?.takeIf { it.isNotBlank() }
                            ?.let { message ->
                                Text(
                                    text = message,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                        BbButton(
                            text = "Telefonu Doğrula",
                            onClick = {
                                onVerifyClick(normalizedCode)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            variant = BbButtonVariant.Primary,
                            size = BbButtonSize.Medium,
                            enabled = canVerify,
                            isLoading = isVerifying
                        )

                        BbButton(
                            text = "Kodu Tekrar Gönder",
                            onClick = onResendCodeClick,
                            modifier = Modifier.fillMaxWidth(),
                            variant = BbButtonVariant.Light,
                            size = BbButtonSize.Medium,
                            enabled = !isVerifying && !isSendingSms,
                            isLoading = isSendingSms
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PhoneVerifyIntroCard(phone: String) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "$phone numarasına gönderilen doğrulama kodunu girerek telefonunuzu hesabınıza doğrulanmış olarak bağlayabilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}