package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoMode
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.Enums.EThemeMode

@Composable
fun AppearanceSettingsScreen(
    selectedTheme: EThemeMode,
    onThemeSelected: (EThemeMode) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val themes = appearanceThemeOptions()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "ad70cf0b-cc2b-48f5-b015-292828278234", fallback = "Görünüm"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGap
            )
        ) {
            item {
                AppearanceIntroCard()
            }

            items(
                items = themes,
                key = { theme ->
                    theme.ThemeMode.name
                }
            ) { theme ->
                AppearanceThemeRow(
                    option = theme,
                    isSelected = selectedTheme == theme.ThemeMode,
                    onClick = {
                        onThemeSelected(theme.ThemeMode)
                    }
                )
            }
        }
    }
}

@Composable
private fun AppearanceIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = BBLocalization.Current.Get(key = "92d1f72f-a2b0-49d4-a006-53acd554db25", fallback = "Bulbulustur uygulamasında kullanmak istediğiniz görünüm modunu seçin."),
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AppearanceThemeRow(
    option: AppearanceThemeOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = option.Icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = option.Title,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = option.Description,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.SizeLg)
                )
            }
        }
    }
}

private data class AppearanceThemeOption(
    val ThemeMode: EThemeMode,
    val Title: String,
    val Description: String,
    val Icon: ImageVector
)

private fun appearanceThemeOptions(): List<AppearanceThemeOption> {
    return listOf(
        AppearanceThemeOption(
            ThemeMode = EThemeMode.System,
            Title = BBLocalization.Current.Get(key = "0aa4645e-5d01-4a47-88b1-89e737922d4d", fallback = "Sistem Varsayılanı"),
            Description = BBLocalization.Current.Get(key = "a4f713cd-1086-4f43-81a7-b38509f8904e", fallback = "Uygulama cihazınızın açık veya koyu tema ayarını takip eder."),
            Icon = Icons.Outlined.AutoMode
        ),
        AppearanceThemeOption(
            ThemeMode = EThemeMode.Light,
            Title = BBLocalization.Current.Get(key = "0da169d7-c163-4a92-99c5-5e7aa5a6abce", fallback = "Açık Tema"),
            Description = BBLocalization.Current.Get(key = "b4314bbe-bd36-4d42-8e15-3fc93905be26", fallback = "Uygulama her zaman açık tema ile görüntülenir."),
            Icon = Icons.Outlined.LightMode
        ),
        AppearanceThemeOption(
            ThemeMode = EThemeMode.Dark,
            Title = BBLocalization.Current.Get(key = "5d279884-96b2-4549-b7f1-5d47493c3a59", fallback = "Koyu Tema"),
            Description = BBLocalization.Current.Get(key = "1b033efe-2086-4a59-892d-4506e3afb93d", fallback = "Uygulama her zaman koyu tema ile görüntülenir."),
            Icon = Icons.Outlined.DarkMode
        )
    )
}