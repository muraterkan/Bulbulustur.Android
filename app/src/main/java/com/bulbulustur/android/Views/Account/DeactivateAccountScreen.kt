package com.bulbulustur.android.Views.Account

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
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun DeactivateAccountScreen(
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
                title = "HesabÄ± Devre DÄ±ÅŸÄ± BÄ±rak",
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
                        start = BbSpacing.PageHorizontal,
                        top = BbSpacing.PageTopCompact,
                        end = BbSpacing.PageHorizontal,
                        bottom = BbSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGap)
        ) {
            DeactivateWarningCard()

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
                ) {
                    DeactivateSectionHeader(
                        title = "Devre DÄ±ÅŸÄ± BÄ±rakma Nedeni",
                        description = "HesabÄ±nÄ±zÄ± neden devre dÄ±ÅŸÄ± bÄ±rakmak istediÄŸinizi kÄ±saca yazÄ±n."
                    )

                    OutlinedTextField(
                        value = reasonState.value,
                        onValueChange = { value ->
                            reasonState.value = value
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "AÃ§Ä±klama")
                        },
                        placeholder = {
                            Text(text = "KÄ±sa bir aÃ§Ä±klama yazabilirsiniz")
                        },
                        minLines = 4,
                        shape = BbRadius.Input,
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
                            title = "Ä°ÅŸlem TamamlanamadÄ±",
                            message = message,
                            type = DeactivateMessageType.Error
                        )
                    }

                    successMessage?.takeIf { message ->
                        message.isNotBlank()
                    }?.let { message ->
                        DeactivateMessageBox(
                            title = "Ä°ÅŸlem AlÄ±ndÄ±",
                            message = message,
                            type = DeactivateMessageType.Success
                        )
                    }

                    BbButton(
                        text = "HesabÄ± Devre DÄ±ÅŸÄ± BÄ±rak",
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Text(
                text = "Bu Ä°ÅŸlem Dikkat Gerektirir",
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.Red.Red700
            )

            Text(
                text = "HesabÄ±nÄ±zÄ± devre dÄ±ÅŸÄ± bÄ±rakmadan Ã¶nce aÃ§Ä±k sipariÅŸ, Ã¶deme, talep ve iletiÅŸim sÃ¼reÃ§lerinizi kontrol etmeniz Ã¶nerilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = BbColors.Red.Red50,
                        shape = BbRadius.LgShape
                    )
                    .padding(BbSpacing.CardPaddingCompact)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Kritik UyarÄ±",
                        style = MaterialTheme.typography.labelLarge,
                        color = BbColors.Red.Red700
                    )

                    Text(
                        text = "Devre dÄ±ÅŸÄ± bÄ±rakma iÅŸlemi geri dÃ¶nÃ¼ÅŸ ve hesap kurtarma sÃ¼reÃ§lerine tabi olabilir.",
                        style = MaterialTheme.typography.bodySmall,
                        color = BbColors.Red.Red700
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
            text = "Devre dÄ±ÅŸÄ± bÄ±rakma nedeni yazÄ±n.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        return
    }

    if (!validation.hasReason) {
        Text(
            text = "AÃ§Ä±klama en az 10 karakter olmalÄ±.",
            color = MaterialTheme.colorScheme.error
        )

        return
    }

    Text(
        text = "AÃ§Ä±klama uygun gÃ¶rÃ¼nÃ¼yor.",
        color = BbColors.Green.Green700
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
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
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
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Onay",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "HesabÄ±mÄ± devre dÄ±ÅŸÄ± bÄ±rakma iÅŸleminin etkilerini anladÄ±m ve bu iÅŸlemi baÅŸlatmak istiyorum.",
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
        DeactivateMessageType.Success -> BbColors.Green.Green50
        DeactivateMessageType.Error -> BbColors.Red.Red50
    }

    val titleColor = when (type) {
        DeactivateMessageType.Success -> BbColors.Green.Green700
        DeactivateMessageType.Error -> BbColors.Red.Red700
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
