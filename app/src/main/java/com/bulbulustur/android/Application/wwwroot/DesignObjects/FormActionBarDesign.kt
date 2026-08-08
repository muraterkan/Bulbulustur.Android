package com.bulbulustur.android.Application.wwwroot.DesignObjects

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun BbFormActionBar(
    primaryText: String,
    onPrimaryClick: () -> Unit,
    modifier: Modifier = Modifier,
    secondaryText: String = BBLocalization.Current.Get(key = "18a6f5c0-ab35-483d-8691-fad99e9680f2", fallback = "Vazgeç"),
    onSecondaryClick: (() -> Unit)? = null,
    primaryEnabled: Boolean = true,
    isLoading: Boolean = false
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            if (onSecondaryClick != null) {
                BbButton(
                    text = secondaryText,
                    onClick = onSecondaryClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium,
                    enabled = !isLoading
                )
            }

            BbButton(
                text = primaryText,
                onClick = onPrimaryClick,
                modifier = Modifier.weight(1f),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = primaryEnabled,
                isLoading = isLoading
            )
        }
    }
}

