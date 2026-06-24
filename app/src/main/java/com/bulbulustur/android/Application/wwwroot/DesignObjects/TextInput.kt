package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun BbTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    helperText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = BBSpacing.Space14),
            enabled = enabled,
            label = {
                Text(text = label)
            },
            placeholder = {
                if (placeholder.isNotBlank()) {
                    Text(text = placeholder)
                }
            },
            singleLine = singleLine,
            isError = errorText != null,
            shape = BBRadius.Input,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            )
        )

        BbInputSupportText(
            helperText = helperText,
            errorText = errorText
        )
    }
}

@Composable
private fun BbInputSupportText(
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

