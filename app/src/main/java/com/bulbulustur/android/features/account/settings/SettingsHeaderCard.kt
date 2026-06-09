package com.bulbulustur.android.features.account.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.*
import com.bulbulustur.android.ui.theme.*

@Composable
fun SettingsHeaderCard(
    backText: String,
    kicker: String,
    title: String,
    description: String,
    onBackClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            BbButton(
                text = backText,
                onClick = onBackClick,
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Text(
                    text = kicker,
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = title,
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = description,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}