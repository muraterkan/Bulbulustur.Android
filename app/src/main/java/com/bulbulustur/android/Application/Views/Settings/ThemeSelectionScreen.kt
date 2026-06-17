package com.bulbulustur.android.Application.Views.Settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoMode
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.PhoneAndroid
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
fun ThemeSelectionScreen(
    selectedTheme: AppThemeOption = AppThemeOption.System,
    onThemeSelected: (AppThemeOption) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.md)
    ) {
        item {
            ThemeSelectionHeader(
                selectedTheme = selectedTheme
            )
        }

        item {
            BbSectionHeader(
                title = "Tema seçimi",
                subtitle = "Uygulamanın görünüm tercihlerini değiştirin"
            )
        }

        items(themeOptions()) { option ->
            ThemeOptionCard(
                option = option,
                selected = selectedTheme == option.themeOption,
                onClick = {
                    onThemeSelected(option.themeOption)
                }
            )
        }

        item {
            ThemeSelectionInfoCard()
        }

        item {
            Spacer(modifier = Modifier.height(BBSpacing.xl))
        }
    }
}

@Composable
private fun ThemeSelectionHeader(
    selectedTheme: AppThemeOption
) {
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
                    imageVector = Icons.Outlined.Palette,
                    contentDescription = null,
                    tint = BBColors.Primary
                )

                Text(
                    text = "Görünüm",
                    style = MaterialTheme.typography.labelLarge,
                    color = BBColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Tema tercihi",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Uygulamanın açık, koyu veya cihaz ayarına bağlı görünmesini seçin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                BbChip(
                    text = selectedTheme.displayName,
                    selected = true,
                    onClick = {}
                )

                BbChip(
                    text = "Uygulama ayarı",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun ThemeOptionCard(
    option: ThemeOptionItem,
    selected: Boolean,
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
                imageVector = option.icon,
                contentDescription = null,
                tint = BBColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = option.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = option.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (selected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = BBColors.Primary
                )
            }
        }
    }
}

@Composable
private fun ThemeSelectionInfoCard() {
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
                imageVector = Icons.Outlined.PhoneAndroid,
                contentDescription = null,
                tint = BBColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = "Yerel tercih",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Tema seçimi daha sonra DataStore veya benzeri yerel kayıt alanında saklanacak.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

enum class AppThemeOption(
    val displayName: String
) {
    System("Sistem varsayılanı"),
    Light("Açık tema"),
    Dark("Koyu tema")
}

private data class ThemeOptionItem(
    val themeOption: AppThemeOption,
    val title: String,
    val description: String,
    val icon: ImageVector
)

private fun themeOptions(): List<ThemeOptionItem> {
    return listOf(
        ThemeOptionItem(
            themeOption = AppThemeOption.System,
            title = "Sistem varsayılanı",
            description = "Uygulama, cihazınızın açık/koyu tema ayarını takip eder.",
            icon = Icons.Outlined.AutoMode
        ),
        ThemeOptionItem(
            themeOption = AppThemeOption.Light,
            title = "Açık tema",
            description = "Uygulama her zaman açık tema ile görüntülenir.",
            icon = Icons.Outlined.LightMode
        ),
        ThemeOptionItem(
            themeOption = AppThemeOption.Dark,
            title = "Koyu tema",
            description = "Uygulama her zaman koyu tema ile görüntülenir.",
            icon = Icons.Outlined.DarkMode
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ThemeSelectionScreenPreview() {
    BbTheme {
        ThemeSelectionScreen()
    }
}
