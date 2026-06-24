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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Public
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
fun LanguageSelectionScreen(
    selectedLanguageCode: String = "tr",
    onLanguageSelected: (AppLanguageOption) -> Unit = {}
) {
    val selectedLanguage = languageOptions()
        .firstOrNull { it.languageOption.code == selectedLanguageCode }
        ?.languageOption
        ?: AppLanguageOption.Turkish

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.md)
    ) {
        item {
            LanguageSelectionHeader(
                selectedLanguage = selectedLanguage
            )
        }

        item {
            BbSectionHeader(
                title = "Dil seçimi",
                subtitle = "Uygulama içinde kullanılacak dili seçin"
            )
        }

        items(languageOptions()) { option ->
            LanguageOptionCard(
                option = option,
                selected = selectedLanguageCode == option.languageOption.code,
                onClick = {
                    onLanguageSelected(option.languageOption)
                }
            )
        }

        item {
            LanguageSelectionInfoCard()
        }

        item {
            Spacer(modifier = Modifier.height(BBSpacing.xl))
        }
    }
}

@Composable
private fun LanguageSelectionHeader(
    selectedLanguage: AppLanguageOption
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
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Dil",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Uygulama dili",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bulbulustur uygulamasında kullanılacak dili buradan deĞiştirebilirsiniz.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                BbChip(
                    text = selectedLanguage.displayName,
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
private fun LanguageOptionCard(
    option: LanguageOptionItem,
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
                tint = MaterialTheme.colorScheme.primary
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

                Text(
                    text = option.languageOption.code.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (selected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun LanguageSelectionInfoCard() {
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
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = "Yerel dil tercihi",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Dil seçimi daha sonra DataStore üzerinde saklanacak. Aktif diller APIy'den beslendiĞinde bu liste dinamik hale getirilecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

enum class AppLanguageOption(
    val code: String,
    val displayName: String
) {
    Turkish(
        code = "tr",
        displayName = "Türkçe"
    ),
    English(
        code = "en",
        displayName = "English"
    )
}

private data class LanguageOptionItem(
    val languageOption: AppLanguageOption,
    val title: String,
    val description: String,
    val icon: ImageVector
)

private fun languageOptions(): List<LanguageOptionItem> {
    return listOf(
        LanguageOptionItem(
            languageOption = AppLanguageOption.Turkish,
            title = "Türkçe",
            description = "Uygulamayı Türkçe kullan.",
            icon = Icons.Outlined.Translate
        ),
        LanguageOptionItem(
            languageOption = AppLanguageOption.English,
            title = "English",
            description = "Use the application in English.",
            icon = Icons.Outlined.Public
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun LanguageSelectionScreenPreview() {
    BbTheme {
        LanguageSelectionScreen()
    }
}

