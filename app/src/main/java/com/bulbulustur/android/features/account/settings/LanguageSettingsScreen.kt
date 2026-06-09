package com.bulbulustur.android.features.account.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.bulbulustur.android.ui.components.*
import com.bulbulustur.android.ui.theme.*

@Composable
fun LanguageSettingsScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedLanguage by remember { mutableStateOf("tr") }

    val languages = listOf(
        LanguageOption("tr", "Türkçe", "Türkiye"),
        LanguageOption("en", "English", "United States"),
        LanguageOption("de", "Deutsch", "Deutschland"),
        LanguageOption("fr", "Français", "France"),
        LanguageOption("es", "Español", "España"),
        LanguageOption("ru", "Русский", "Россия"),
        LanguageOption("ar", "العربية", "Saudi Arabia"),
        LanguageOption("zh", "简体中文", "China")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            )
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
                kicker = "Uygulama Dili",
                title = "Dil",
                description = "Bulbulustur uygulamasında kullanmak istediğiniz dili seçin.",
                onBackClick = onBackClick
            )
        }

        items(languages.size) { index ->
            val language = languages[index]

            LanguageRow(
                item = language,
                isSelected = selectedLanguage == language.code,
                onClick = {
                    selectedLanguage = language.code
                }
            )
        }
    }
}

@Composable
private fun LanguageRow(
    item: LanguageOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = item.name,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.region,
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

private data class LanguageOption(
    val code: String,
    val name: String,
    val region: String
)