package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun BbPasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    helperText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

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
            singleLine = true,
            isError = errorText != null,
            shape = BBRadius.Input,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                TextButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Text(
                        text = if (isPasswordVisible) {
                            "Gizle"
                        } else {
                            "Göster"
                        },
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        )

        BbPasswordSupportText(
            helperText = helperText,
            errorText = errorText
        )
    }
}

@Composable
private fun BbPasswordSupportText(
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
