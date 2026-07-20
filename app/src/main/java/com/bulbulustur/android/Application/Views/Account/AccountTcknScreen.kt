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
import androidx.compose.runtime.LaunchedEffect
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
fun AccountTcknScreen(
    currentTckn: String?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (String) -> Unit = {}
) {
    val tcknState = remember {
        mutableStateOf("")
    }

    LaunchedEffect(currentTckn) {
        tcknState.value = currentTckn
            ?.filter { it.isDigit() }
            ?.take(11)
            .orEmpty()
    }

    val tckn = tcknState.value.trim()

    val initialTckn = currentTckn
        ?.filter { it.isDigit() }
        ?.take(11)
        .orEmpty()

    val isValid = isValidTckn(tckn)

    val canSubmit =
        isValid &&
                tckn != initialTckn &&
                !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "T.C. Kimlik Numarası",
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
            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Text(
                    text = "T.C. kimlik numaranızı doğrulama ve faturalandırma işlemlerinde kullanmak üzere güncelleyebilirsiniz.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    OutlinedTextField(
                        value = tcknState.value,
                        onValueChange = { value ->
                            tcknState.value = value
                                .filter { it.isDigit() }
                                .take(11)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "T.C. Kimlik Numarası")
                        },
                        placeholder = {
                            Text(text = "11 haneli kimlik numarası")
                        },
                        supportingText = {
                            when {
                                tckn.isBlank() -> {
                                    Text(text = "11 haneli T.C. kimlik numaranızı girin.")
                                }

                                !isValid -> {
                                    Text(
                                        text = "Geçerli bir T.C. kimlik numarası girin.",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }

                                else -> {
                                    Text(text = "T.C. kimlik numarası geçerli.")
                                }
                            }
                        },
                        isError = tckn.isNotBlank() && !isValid,
                        enabled = !isLoading,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        shape = BBRadius.Input,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                }
            }

            errorMessage
                ?.takeIf { it.isNotBlank() }
                ?.let { message ->
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

            BbButton(
                text = "Güncelle",
                onClick = {
                    onSaveClick(tckn)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = canSubmit,
                isLoading = isLoading
            )
        }
    }
}

private fun isValidTckn(value: String): Boolean {
    if (value.length != 11) return false
    if (!value.all { it.isDigit() }) return false
    if (value.first() == '0') return false

    val digits = value.map { it.digitToInt() }

    val oddSum =
        digits[0] +
                digits[2] +
                digits[4] +
                digits[6] +
                digits[8]

    val evenSum =
        digits[1] +
                digits[3] +
                digits[5] +
                digits[7]

    val tenthDigit =
        ((oddSum * 7) - evenSum)
            .mod(10)

    if (digits[9] != tenthDigit) return false

    val eleventhDigit =
        digits
            .take(10)
            .sum()
            .mod(10)

    return digits[10] == eleventhDigit
}
