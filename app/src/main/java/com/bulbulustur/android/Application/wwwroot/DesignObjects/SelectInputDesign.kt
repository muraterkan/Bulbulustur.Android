package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

data class BbSelectOption(
    val value: String,
    val text: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbSelectInput(
    selectedValue: String,
    options: List<BbSelectOption>,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "Seçiniz",
    helperText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val selectedText =
        options
            .firstOrNull { option ->
                option.value == selectedValue
            }
            ?.text
            .orEmpty()

    Column(
        modifier =
            modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded =
                isExpanded,
            onExpandedChange = {
                if (enabled) {
                    isExpanded =
                        !isExpanded
                }
            },
            modifier =
                Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value =
                    selectedText,
                onValueChange = {
                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .defaultMinSize(
                            minHeight =
                                BBSpacing.Space14
                        ),
                enabled =
                    enabled,
                readOnly =
                    true,
                label = {
                    Text(
                        text =
                            label
                    )
                },
                placeholder = {
                    Text(
                        text =
                            placeholder
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector =
                            if (isExpanded) {
                                Icons.Outlined.ArrowDropUp
                            } else {
                                Icons.Outlined.ArrowDropDown
                            },
                        contentDescription =
                            if (isExpanded) {
                                "Listeyi kapat"
                            } else {
                                "Listeyi aç"
                            },
                        tint =
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier =
                            Modifier.defaultMinSize(
                                minWidth =
                                    BBIcon.SizeMd,
                                minHeight =
                                    BBIcon.SizeMd
                            )
                    )
                },
                isError =
                    errorText != null,
                shape =
                    BBRadius.Input,
                colors =
                    ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )

            ExposedDropdownMenu(
                expanded =
                    isExpanded,
                onDismissRequest = {
                    isExpanded =
                        false
                },
                modifier =
                    Modifier.fillMaxWidth()
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text =
                                    option.text,
                                style =
                                    MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onValueChange(
                                option.value
                            )

                            isExpanded =
                                false
                        }
                    )
                }
            }
        }

        BbSelectSupportText(
            helperText =
                helperText,
            errorText =
                errorText
        )
    }
}

@Composable
private fun BbSelectSupportText(
    helperText: String?,
    errorText: String?
) {
    if (errorText != null) {
        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space1
                )
        )

        Text(
            text =
                errorText,
            style =
                MaterialTheme.typography.labelSmall,
            color =
                MaterialTheme.colorScheme.error
        )

        return
    }

    if (helperText != null) {
        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space1
                )
        )

        Text(
            text =
                helperText,
            style =
                MaterialTheme.typography.labelSmall,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}