package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO

private const val LANGUAGE_SETTINGS_TURKISH_FLAG = "file:///android_asset/flags/turkey.svg"
private const val LANGUAGE_SETTINGS_ENGLISH_FLAG = "file:///android_asset/flags/uk.svg"
private const val LANGUAGE_SETTINGS_FALLBACK_FLAG = "file:///android_asset/flags/flag.svg"

@Composable
fun LanguageSettingsScreen(
    languages: List<SystemDescLanguageDTO>,
    selectedLanguageId: Int,
    isLoading: Boolean,
    errorMessage: String?,
    onLanguageSelected: (Int) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val visibleLanguages = ResolveLanguageSettingsItems(languages)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Dil",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                LanguageIntroCard()
            }

            if (isLoading && languages.isEmpty()) {
                item {
                    SettingsLoadingCard("Diller yükleniyor...")
                }
            } else {
                items(
                    items = visibleLanguages,
                    key = { it.id }
                ) { language ->
                    LanguageRow(
                        item = language,
                        isSelected = language.id == selectedLanguageId,
                        onClick = {
                            if (language.id != selectedLanguageId) {
                                onLanguageSelected(language.id)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LanguageIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Uygulamada kullanmak istediğiniz dili seçin.",
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LanguageRow(
    item: LanguageSettingsItem,
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = item.flagAssetPath,
                    contentDescription = item.title,
                    modifier = Modifier.size(BBIcon.SizeLg),
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = item.title,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.code.uppercase(),
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Seçili dil",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.SizeLg)
                )
            }
        }
    }
}

@Composable
private fun SettingsLoadingCard(message: String) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(BBIcon.SizeLg),
                strokeWidth = BBSpacing.Space1 / 2,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = message,
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun ResolveLanguageSettingsItems(
    languages: List<SystemDescLanguageDTO>
): List<LanguageSettingsItem> {
    return languages
        .filter {
            it.SystemDescLanguageId == 1 ||
                    it.SystemDescLanguageId == 2
        }
        .map {
            LanguageSettingsItem(
                id = it.SystemDescLanguageId,
                title = it.Content
                    ?.takeIf { value -> value.isNotBlank() }
                    ?: when (it.SystemDescLanguageId) {
                        2 -> "English"
                        else -> "Türkçe"
                    },
                code = it.LanguageIsoCode
                    ?.takeIf { value -> value.isNotBlank() }
                    ?: when (it.SystemDescLanguageId) {
                        2 -> "en"
                        else -> "tr"
                    },
                flagAssetPath = ResolveLanguageSettingsFlagPath(
                    it.SystemDescLanguageId
                )
            )
        }
        .ifEmpty {
            listOf(
                LanguageSettingsItem(
                    id = 1,
                    title = "Türkçe",
                    code = "tr",
                    flagAssetPath = LANGUAGE_SETTINGS_TURKISH_FLAG
                ),
                LanguageSettingsItem(
                    id = 2,
                    title = "English",
                    code = "en",
                    flagAssetPath = LANGUAGE_SETTINGS_ENGLISH_FLAG
                )
            )
        }
}

private fun ResolveLanguageSettingsFlagPath(
    languageId: Int
): String {
    return when (languageId) {
        1 -> LANGUAGE_SETTINGS_TURKISH_FLAG
        2 -> LANGUAGE_SETTINGS_ENGLISH_FLAG
        else -> LANGUAGE_SETTINGS_FALLBACK_FLAG
    }
}

private data class LanguageSettingsItem(
    val id: Int,
    val title: String,
    val code: String,
    val flagAssetPath: String
)