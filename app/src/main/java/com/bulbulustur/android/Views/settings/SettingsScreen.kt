package com.bulbulustur.android.Views.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbChip
import com.bulbulustur.android.wwwroot.components.BbSectionHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

@Composable
fun SettingsScreen(
    currentThemeName: String = "Sistem varsayılanı",
    currentLanguageName: String = "Türkçe",
    onThemeClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        item {
            SettingsHeader()
        }

        item {
            BbSectionHeader(
                title = "Uygulama ayarları",
                subtitle = "Bu alan yalnızca uygulamanın görünüm ve dil tercihlerini değiştirir"
            )
        }

        item {
            SettingsOptionCard(
                title = "Görünüm",
                description = "Tema tercihini değiştir",
                currentValue = currentThemeName,
                icon = Icons.Outlined.Palette,
                onClick = onThemeClick
            )
        }

        item {
            SettingsOptionCard(
                title = "Dil",
                description = "Uygulama dilini değiştir",
                currentValue = currentLanguageName,
                icon = Icons.Outlined.Language,
                onClick = onLanguageClick
            )
        }

        item {
            SettingsInfoCard()
        }

        item {
            Spacer(modifier = Modifier.height(BbSpacing.xl))
        }
    }
}

@Composable
private fun SettingsHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Ayarlar",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Uygulama ayarları",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Tema ve dil tercihlerinizi buradan değiştirin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                BbChip(
                    text = "Tema",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Dil",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Uygulama tercihi",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun SettingsOptionCard(
    title: String,
    description: String,
    currentValue: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = currentValue,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BbColors.Primary
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun SettingsInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.DarkMode,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = "Cihazınıza uyumlu deneyim",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Tema ve dil tercihleri daha sonra yerel kayıt alanında saklanacak. API bağlantısından sonra aktif diller servis üzerinden beslenecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.Translate,
                contentDescription = null,
                tint = BbColors.TextMuted
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    BbTheme {
        SettingsScreen()
    }
}