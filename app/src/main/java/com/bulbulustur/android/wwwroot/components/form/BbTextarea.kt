package com.bulbulustur.android.wwwroot.components.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun BbTextarea(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    helperText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true,
    minLines: Int = 4,
    maxLines: Int = 8
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = BbSpacing.Space20),
            enabled = enabled,
            label = {
                Text(text = label)
            },
            placeholder = {
                if (placeholder.isNotBlank()) {
                    Text(text = placeholder)
                }
            },
            minLines = minLines,
            maxLines = maxLines,
            isError = errorText != null,
            shape = BbRadius.Input,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        BbTextareaSupportText(
            helperText = helperText,
            errorText = errorText
        )
    }
}

@Composable
private fun BbTextareaSupportText(
    helperText: String?,
    errorText: String?
) {
    if (errorText != null) {
        Spacer(modifier = Modifier.height(BbSpacing.Space1))

        Text(
            text = errorText,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.error
        )

        return
    }

    if (helperText != null) {
        Spacer(modifier = Modifier.height(BbSpacing.Space1))

        Text(
            text = helperText,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}