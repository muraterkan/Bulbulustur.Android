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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Localization.LocalizationKeys
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage

@Composable
fun LanguageSettingsScreen(
    selectedLanguage: EApplicationLanguage,
    onLanguageSelected: (EApplicationLanguage) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val localization = BBLocalization.Current

    val languages = remember {
        listOf(
            LanguageOption(
                Language = EApplicationLanguage.Turkish,
                NameKey = LocalizationKeys.Language.Turkish,
                Region = "Türkiye",
                FlagFileName = "turkey.svg"
            ),
            LanguageOption(
                Language = EApplicationLanguage.English,
                NameKey = LocalizationKeys.Language.English,
                Region = "United States",
                FlagFileName = "United States of America.svg"
            )
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = localization.Get(
                    LocalizationKeys.Language.HeaderLabel
                ),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGap
            )
        ) {
            item {
                LanguageIntroCard()
            }

            items(
                items = languages,
                key = { language ->
                    language.Language.name
                }
            ) { language ->
                LanguageRow(
                    item = language,
                    isSelected = selectedLanguage == language.Language,
                    onClick = {
                        if (selectedLanguage != language.Language) {
                            onLanguageSelected(language.Language)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun LanguageIntroCard() {
    val localization = BBLocalization.Current

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = localization.Get(
                LocalizationKeys.Language.LocalPreferenceDescription
            ),
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LanguageRow(
    item: LanguageOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val localization = BBLocalization.Current

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageFlag(
                flagFileName = item.FlagFileName,
                contentDescription = item.Region
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = localization.Get(
                        item.NameKey
                    ),
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.Region,
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

@Composable
private fun LanguageFlag(
    flagFileName: String,
    contentDescription: String
) {
    val context = LocalContext.current

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
            model = ImageRequest.Builder(context)
                .data(
                    "file:///android_asset/flags/$flagFileName"
                )
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.size(BBIcon.Size2Xl),
            contentScale = ContentScale.Fit
        )
    }
}

private data class LanguageOption(
    val Language: EApplicationLanguage,
    val NameKey: String,
    val Region: String,
    val FlagFileName: String
)