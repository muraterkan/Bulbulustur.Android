package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun DeactivateDashboardScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    successMessage: String? = null,
    onBackClick: () -> Unit = {},
    onDeactivateClick: (
        reason: String,
        accepted: Boolean
    ) -> Unit = { _, _ -> }
) {
    val reasonState = remember {
        mutableStateOf("")
    }

    val acceptedState = remember {
        mutableStateOf(false)
    }

    val validationState = remember(
        reasonState.value,
        acceptedState.value
    ) {
        derivedStateOf {
            validateDeactivateAccountForm(
                reason = reasonState.value,
                accepted = acceptedState.value
            )
        }
    }

    val canSubmit = validationState.value.canSubmit && !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "64052773-9d1d-48ee-b933-7c387c42147d", fallback = "Hesabı Devre Dışı Bırak"),
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
            DeactivateWarningCard()

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                ) {
                    DeactivateSectionHeader(
                        title = BBLocalization.Current.Get(key = "6403c207-18d4-4730-88f2-1ae1a4fd37d9", fallback = "Devre Dışı Bırakma Nedeni"),
                        description = BBLocalization.Current.Get(key = "77425e39-365d-473f-bd18-e52e5819bc31", fallback = "Hesabınızı neden devre dışı bırakmak istediğinizi kısaca yazın.")
                    )

                    OutlinedTextField(
                        value = reasonState.value,
                        onValueChange = { value ->
                            reasonState.value = value
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = BBLocalization.Current.Get(key = "db0a3356-2fa4-4c1f-9432-2c299ac52b92", fallback = "Açıklama"))
                        },
                        placeholder = {
                            Text(text = BBLocalization.Current.Get(key = "a2462a3b-d507-4e80-94a8-a497c534a9a4", fallback = "Kısa bir açıklama yazabilirsiniz"))
                        },
                        minLines = 4,
                        shape = BBRadius.Input,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        isError = reasonState.value.isNotBlank() && !validationState.value.hasReason,
                        supportingText = {
                            DeactivateReasonSupportingText(
                                validation = validationState.value,
                                reason = reasonState.value
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                            errorIndicatorColor = MaterialTheme.colorScheme.error,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            errorLabelColor = MaterialTheme.colorScheme.error
                        )
                    )

                    DeactivateConfirmationCard(
                        accepted = acceptedState.value,
                        onAcceptedChange = { value ->
                            acceptedState.value = value
                        }
                    )

                    errorMessage?.takeIf { message ->
                        message.isNotBlank()
                    }?.let { message ->
                        DeactivateMessageBox(
                            title = BBLocalization.Current.Get(key = "aee471fe-aa14-4587-897c-f5a3489aa980", fallback = "İşlem Tamamlanamadı"),
                            message = message,
                            type = DeactivateMessageType.Error
                        )
                    }

                    successMessage?.takeIf { message ->
                        message.isNotBlank()
                    }?.let { message ->
                        DeactivateMessageBox(
                            title = BBLocalization.Current.Get(key = "60bcbeb4-25cd-4a8b-9f12-030e4a75b2b3", fallback = "İşlem Alındı"),
                            message = message,
                            type = DeactivateMessageType.Success
                        )
                    }

                    BbButton(
                        text = BBLocalization.Current.Get(key = "64052773-9d1d-48ee-b933-7c387c42147d", fallback = "Hesabı Devre Dışı Bırak"),
                        onClick = {
                            onDeactivateClick(
                                reasonState.value,
                                acceptedState.value
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Danger,
                        size = BbButtonSize.Medium,
                        enabled = canSubmit,
                        isLoading = isLoading
                    )
                }
            }
        }
    }
}

@Composable
private fun DeactivateWarningCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "eb1f182e-9533-4577-b0a3-d39abcd42468", fallback = "Bu İşlem Dikkat Gerektirir"),
                style = MaterialTheme.typography.titleSmall,
                color = BBColors.Red.Red700
            )

            Text(
                text = BBLocalization.Current.Get(key = "bcc8f323-2cda-420f-aee0-b17fabcde9bb", fallback = "Hesabınızı devre dışı bırakmadan önce açık sipariş, ödeme, talep ve iletişim süreçlerinizi kontrol etmeniz önerilir."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = BBColors.Red.Red50,
                        shape = BBRadius.LgShape
                    )
                    .padding(BBSpacing.CardPaddingCompact)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "4e0a8573-70cb-4cf2-b843-e6b140fd7774", fallback = "Kritik Uyarı"),
                        style = MaterialTheme.typography.labelLarge,
                        color = BBColors.Red.Red700
                    )

                    Text(
                        text = BBLocalization.Current.Get(key = "79703e78-03ae-43b2-bca6-ff247c67406e", fallback = "Devre dışı bırakma işlemi geri dönüş ve hesap kurtarma süreçlerine tabi olabilir."),
                        style = MaterialTheme.typography.bodySmall,
                        color = BBColors.Red.Red700
                    )
                }
            }
        }
    }
}

@Composable
private fun DeactivateSectionHeader(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DeactivateReasonSupportingText(
    validation: DeactivateAccountValidationState,
    reason: String
) {
    if (reason.isBlank()) {
        Text(
            text = BBLocalization.Current.Get(key = "153322d5-f390-4de2-b9aa-86f0ac15edc2", fallback = "Devre dışı bırakma nedeni yazın."),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        return
    }

    if (!validation.hasReason) {
        Text(
            text = BBLocalization.Current.Get(key = "69137feb-df7d-4dbb-8fad-29b4a10a75a9", fallback = "Açıklama en az 10 karakter olmalı."),
            color = MaterialTheme.colorScheme.error
        )

        return
    }

    Text(
        text = BBLocalization.Current.Get(key = "b9083e02-b948-43f4-868f-1b1da9cb221d", fallback = "Açıklama uygun görünüyor."),
        color = BBColors.Green.Green700
    )
}

@Composable
private fun DeactivateConfirmationCard(
    accepted: Boolean,
    onAcceptedChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = accepted,
                onCheckedChange = { value ->
                    onAcceptedChange(value)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.outline,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "be8f7748-f764-4f04-9ddd-1a6de091ef34", fallback = "Onay"),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "d6ec5438-30ce-45a1-bbec-2699f8ffd9df", fallback = "Hesabımı devre dışı bırakma işleminin etkilerini anladım ve bu işlemi başlatmak istiyorum."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun DeactivateMessageBox(
    title: String,
    message: String,
    type: DeactivateMessageType
) {
    val backgroundColor = when (type) {
        DeactivateMessageType.Success -> BBColors.Green.Green50
        DeactivateMessageType.Error -> BBColors.Red.Red50
    }

    val titleColor = when (type) {
        DeactivateMessageType.Success -> BBColors.Green.Green700
        DeactivateMessageType.Error -> BBColors.Red.Red700
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = titleColor
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun validateDeactivateAccountForm(
    reason: String,
    accepted: Boolean
): DeactivateAccountValidationState {
    val trimmedReason = reason.trim()
    val hasReason = trimmedReason.length >= 10

    return DeactivateAccountValidationState(
        hasReason = hasReason,
        accepted = accepted
    )
}

private enum class DeactivateMessageType {
    Success,
    Error
}

private data class DeactivateAccountValidationState(
    val hasReason: Boolean,
    val accepted: Boolean
) {
    val canSubmit: Boolean
        get() {
            return hasReason && accepted
        }
}


