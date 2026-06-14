package com.bulbulustur.android.Features.settings

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
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbChip
import com.bulbulustur.android.Ui.components.BbSectionHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTheme

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
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
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
            Spacer(modifier = Modifier.height(BbSpacing.xl))
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
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Dil",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
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
                text = "Bulbulustur uygulamasında kullanılacak dili buradan değiştirebilirsiniz.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
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
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = option.icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
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
                    color = BbColors.Primary
                )
            }

            if (selected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = BbColors.Primary
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
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.PhoneAndroid,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = "Yerel dil tercihi",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Dil seçimi daha sonra DataStore üzerinde saklanacak. Aktif diller API’den beslendiğinde bu liste dinamik hale getirilecek.",
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