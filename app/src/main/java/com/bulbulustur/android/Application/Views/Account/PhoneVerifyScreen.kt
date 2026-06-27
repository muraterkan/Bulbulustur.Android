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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun PhoneVerifyScreen(
    phone: String = "+90 555 710 64 17",
    onBackClick: () -> Unit = {},
    onVerifyClick: (String) -> Unit = {},
    onResendCodeClick: () -> Unit = {}
) {
    val verificationCodeState = remember {
        mutableStateOf("")
    }

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
                            text = "$phone numarasına gönderilen kodu girin.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    OutlinedTextField(
                        value = verificationCodeState.value,
                        onValueChange = { value ->
                            verificationCodeState.value = value
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "Kod")
                        },
                        placeholder = {
                            Text(text = "6 haneli kod")
                        },
                        singleLine = true,
                        shape = BBRadius.Input,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
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

                    BbButton(
                        text = "Telefonu Doğrula",
                        onClick = {
                            onVerifyClick(verificationCodeState.value)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Medium
                    )

                    BbButton(
                        text = "Kodu Tekrar Gönder",
                        onClick = onResendCodeClick,
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun PhoneVerifyIntroCard(
    phone: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Telefon numaranıza gönderilen doğrulama kodunu girerek $phone numarasını hesabınıza doğrulanmış olarak bağlayabilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


