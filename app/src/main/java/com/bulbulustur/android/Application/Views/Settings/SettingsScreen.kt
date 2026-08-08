package com.bulbulustur.android.Application.Views.Settings

import com.bulbulustur.android.Application.Localization.BBLocalization

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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun SettingsScreen(
    currentThemeName: String = BBLocalization.Current.Get(key = "0aa4645e-5d01-4a47-88b1-89e737922d4d", fallback = "Sistem Varsayılanı"),
    currentLanguageName: String = BBLocalization.Current.Get(key = "0917b779-9fd5-4c09-a77a-7561824c9d2c", fallback = "Türkçe"),
    onThemeClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.md)
    ) {
        item {
            SettingsHeader()
        }

        item {
            BbSectionHeader(
                title = BBLocalization.Current.Get(key = "0922dd57-03f9-47a3-a4de-8aaddad92aba", fallback = "Uygulama Ayarları"),
                subtitle = BBLocalization.Current.Get(key = "b386c2f3-d2b6-47e5-bca8-c9857d128c72", fallback = "Bu alan yalnızca uygulamanın görünüm ve dil tercihlerini değiştirir")
            )
        }

        item {
            SettingsOptionCard(
                title = BBLocalization.Current.Get(key = "ad70cf0b-cc2b-48f5-b015-292828278234", fallback = "Görünüm"),
                description = BBLocalization.Current.Get(key = "36c3bcf7-76f8-4245-bb13-2f57c651b868", fallback = "Tema Tercihini Değiştir"),
                currentValue = currentThemeName,
                icon = Icons.Outlined.Palette,
                onClick = onThemeClick
            )
        }

        item {
            SettingsOptionCard(
                title = BBLocalization.Current.Get(key = "5259eecf-5b93-46fb-bf7c-34acd890bf9a", fallback = "Dil"),
                description = BBLocalization.Current.Get(key = "231c58c8-64af-4405-be8e-9442cd43bfc6", fallback = "Uygulama Dilini Değiştir"),
                currentValue = currentLanguageName,
                icon = Icons.Outlined.Language,
                onClick = onLanguageClick
            )
        }

        item {
            SettingsInfoCard()
        }

        item {
            Spacer(modifier = Modifier.height(BBSpacing.xl))
        }
    }
}

@Composable
private fun SettingsHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = BBLocalization.Current.Get(key = "1280c93e-c908-4ee3-9e4c-d09fec8f88ec", fallback = "Ayarlar"),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "0922dd57-03f9-47a3-a4de-8aaddad92aba", fallback = "Uygulama Ayarları"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "edb980be-4f0e-4d96-ade8-8a7d427bbe31", fallback = "Tema ve dil tercihlerinizi buradan değiştirin."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                BbChip(
                    text = "Tema",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = BBLocalization.Current.Get(key = "5259eecf-5b93-46fb-bf7c-34acd890bf9a", fallback = "Dil"),
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = BBLocalization.Current.Get(key = "cfe8dd38-02c6-43f0-89a2-9ce28d06e169", fallback = "Uygulama tercihi"),
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
                .padding(BBSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
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
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
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
                .padding(BBSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.DarkMode,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "f2d807c4-c812-414e-a857-90a9f8764d45", fallback = "Cihazınıza uyumlu deneyim"),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "84a0f6f0-b2a9-47bb-8e9f-539747da548a", fallback = "Tema ve dil tercihleri daha sonra yerel kayıt alanında saklanacak. API bağlantısından sonra aktif diller servis üzerinden beslenecek."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.Translate,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
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

