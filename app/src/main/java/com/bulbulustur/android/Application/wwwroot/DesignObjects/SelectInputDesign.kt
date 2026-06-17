package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

data class BbSelectOption(
    val value: String,
    val text: String
)

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

    val selectedText = options
        .firstOrNull { option -> option.value == selectedValue }
        ?.text
        .orEmpty()

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = BBSpacing.Space14)
                    .clickable(enabled = enabled) {
                        isExpanded = true
                    },
                enabled = enabled,
                readOnly = true,
                label = {
                    Text(text = label)
                },
                placeholder = {
                    Text(text = placeholder)
                },
                trailingIcon = {
                    Text(
                        text = if (isExpanded) {
                            "▴"
                        } else {
                            "▾"
                        },
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                isError = errorText != null,
                shape = BBRadius.Input
            )

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option.text,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onValueChange(option.value)
                            isExpanded = false
                        }
                    )
                }
            }
        }

        BbSelectSupportText(
            helperText = helperText,
            errorText = errorText
        )
    }
}

@Composable
private fun BbSelectSupportText(
    helperText: String?,
    errorText: String?
) {
    if (errorText != null) {
        Spacer(modifier = Modifier.height(BBSpacing.Space1))

        Text(
            text = errorText,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.error
        )

        return
    }

    if (helperText != null) {
        Spacer(modifier = Modifier.height(BBSpacing.Space1))

        Text(
            text = helperText,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
