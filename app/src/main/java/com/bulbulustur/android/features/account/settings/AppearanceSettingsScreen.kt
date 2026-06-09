package com.bulbulustur.android.features.account.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun AppearanceSettingsScreen(
    onBackClick: () -> Unit = {}
) {
    val selectedThemeState = remember {
        mutableStateOf("light")
    }

    val themes = listOf(
        AppearanceThemeOption(
            code = "light",
            title = "Açık Tema",
            description = "Aydınlık ve temiz görünüm.",
            icon = Icons.Outlined.LightMode
        ),
        AppearanceThemeOption(
            code = "navy",
            title = "Lacivert Tema",
            description = "Yumuşak koyu görünüm.",
            icon = Icons.Outlined.NightsStay
        ),
        AppearanceThemeOption(
            code = "dark",
            title = "Koyu Tema",
            description = "Daha kontrastlı gece görünümü.",
            icon = Icons.Outlined.DarkMode
        )
    )

    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f),
            MaterialTheme.colorScheme.surfaceVariant
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(pageBackground)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentPadding = PaddingValues(
            horizontal = BbSpacing.PageHorizontal,
            vertical = BbSpacing.PageTopCompact
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        item {
            SettingsHeaderCard(
                backText = "Ayarlara Dön",
                kicker = "Görünüm",
                title = "Tema Seçimi",
                description = "Bulbulustur uygulamasında kullanmak istediğiniz görünüm modunu seçin.",
                onBackClick = onBackClick
            )
        }

        items(
            count = themes.size
        ) { index ->
            val theme = themes[index]

            AppearanceThemeRow(
                option = theme,
                isSelected = selectedThemeState.value == theme.code,
                onClick = {
                    selectedThemeState.value = theme.code
                }
            )
        }
    }
}

@Composable
private fun AppearanceThemeRow(
    option: AppearanceThemeOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = option.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BbIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = option.title,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = option.description,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BbIcon.SizeLg)
                )
            }
        }
    }
}

private data class AppearanceThemeOption(
    val code: String,
    val title: String,
    val description: String,
    val icon: ImageVector
)