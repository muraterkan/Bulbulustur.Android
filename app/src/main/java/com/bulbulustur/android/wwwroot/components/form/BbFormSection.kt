package com.bulbulustur.android.wwwroot.components.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun BbFormSection(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        BbCard(
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Medium
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
            ) {
                content()
            }
        }
    }
}