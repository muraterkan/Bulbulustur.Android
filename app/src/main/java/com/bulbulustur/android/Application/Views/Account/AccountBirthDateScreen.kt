package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

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
fun AccountBirthDateScreen(
    currentBirthDate: String?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (birthDate: String) -> Unit = {}
) {
    val birthDateState = remember {
        mutableStateOf("")
    }

    LaunchedEffect(currentBirthDate) {
        birthDateState.value = normalizeBirthDate(currentBirthDate)
    }

    val normalizedBirthDate = birthDateState.value.trim()
    val initialBirthDate = normalizeBirthDate(currentBirthDate)

    val isValid = isValidBirthDate(normalizedBirthDate)

    val canSubmit = isValid &&
            normalizedBirthDate != initialBirthDate &&
            !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "f622784c-d8fe-46e0-adee-dcc5e8c03e57", fallback = "Doğum Tarihi"),
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
                    text = "Doğum tarihinizi yıl-ay-gün biçiminde girin. Örnek: 1990-05-24",
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
                        value = birthDateState.value,
                        onValueChange = { value ->
                            birthDateState.value = formatBirthDateInput(value)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = BBLocalization.Current.Get(key = "f622784c-d8fe-46e0-adee-dcc5e8c03e57", fallback = "Doğum Tarihi"))
                        },
                        placeholder = {
                            Text(text = "YYYY-MM-DD")
                        },
                        supportingText = {
                            if (
                                birthDateState.value.isNotBlank() &&
                                !isValid
                            ) {
                                Text(
                                    text = "Geçerli bir tarih girin. Örnek: 1990-05-24",
                                    color = MaterialTheme.colorScheme.error
                                )
                            } else {
                                Text(text = "Tarih biçimi: YYYY-MM-DD")
                            }
                        },
                        isError = birthDateState.value.isNotBlank() && !isValid,
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
                text = BBLocalization.Current.Get(key = "58104fd9-46c6-4304-9abb-07f5273a33f9", fallback = "Güncelle"),
                onClick = {
                    onSaveClick(normalizedBirthDate)
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

private fun normalizeBirthDate(value: String?): String {
    return value
        ?.trim()
        ?.take(10)
        ?.takeIf { it.matches(Regex("""\d{4}-\d{2}-\d{2}""")) }
        .orEmpty()
}

private fun formatBirthDateInput(value: String): String {
    val digits = value
        .filter { it.isDigit() }
        .take(8)

    return buildString {
        digits.forEachIndexed { index, character ->
            if (index == 4 || index == 6) {
                append('-')
            }

            append(character)
        }
    }
}

private fun isValidBirthDate(value: String): Boolean {
    val match = Regex("""^(\d{4})-(\d{2})-(\d{2})$""")
        .matchEntire(value)
        ?: return false

    val year = match.groupValues[1].toIntOrNull() ?: return false
    val month = match.groupValues[2].toIntOrNull() ?: return false
    val day = match.groupValues[3].toIntOrNull() ?: return false

    if (year !in 1900..2100) return false
    if (month !in 1..12) return false

    val maxDay = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> {
            val leapYear = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)

            if (leapYear) 29 else 28
        }

        else -> return false
    }

    return day in 1..maxDay
}
