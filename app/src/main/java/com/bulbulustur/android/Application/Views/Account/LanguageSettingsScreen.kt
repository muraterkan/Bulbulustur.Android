package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun LanguageSettingsScreen(
    onBackClick: () -> Unit = {}
) {
    val selectedLanguageState = remember {
        mutableStateOf("tr")
    }

    val languages = remember {
        listOf(
            LanguageOption(
                code = "tr",
                name = "Türkçe",
                region = "Türkiye",
                flagFileName = "turkey.svg"
            ),
            LanguageOption(
                code = "en",
                name = "English",
                region = "United States",
                flagFileName = "United States of America.svg"
            ),
            LanguageOption(
                code = "de",
                name = "Deutsch",
                region = "Deutschland",
                flagFileName = "germany.svg"
            ),
            LanguageOption(
                code = "fr",
                name = "Français",
                region = "France",
                flagFileName = "france.svg"
            ),
            LanguageOption(
                code = "es",
                name = "EspaÃ±ol",
                region = "EspaÃ±a",
                flagFileName = "spain.svg"
            ),
            LanguageOption(
                code = "ru",
                name = "Ğ ÑƒÑÑĞºĞ¸Ğ¹",
                region = "Ğ Ğ¾ÑÑĞ¸Ñ",
                flagFileName = "russia.svg"
            ),
            LanguageOption(
                code = "ar",
                name = "Ø§Ù„Ø¹Ø±Ø¨ÙŠØ©",
                region = "Saudi Arabia",
                flagFileName = "saudi-arabia.svg"
            ),
            LanguageOption(
                code = "zh",
                name = "ç®€ä½“ä¸­æ–‡",
                region = "China",
                flagFileName = "china.svg"
            )
        )
    }

    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = BBAlpha.DisabledContainer
            ),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f),
            MaterialTheme.colorScheme.surfaceVariant
        )
    )

    Scaffold(
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
                .background(pageBackground)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                LanguageIntroCard()
            }

            items(
                items = languages,
                key = { language ->
                    language.code
                }
            ) { language ->
                LanguageRow(
                    item = language,
                    isSelected = selectedLanguageState.value == language.code,
                    onClick = {
                        selectedLanguageState.value = language.code
                    }
                )
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
            text = "Seçiminiz uygulama metinlerinde, bildirimlerde ve destek içeriklerinde kullanılacaktır.",
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
            LanguageFlag(
                flagFileName = item.flagFileName,
                contentDescription = "${item.region} bayraĞı"
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
                    contentDescription = "Seçili dil",
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
                .data("file:///android_asset/flags/$flagFileName")
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.size(BBIcon.Size2Xl),
            contentScale = ContentScale.Fit
        )
    }
}

private data class LanguageOption(
    val code: String,
    val name: String,
    val region: String,
    val flagFileName: String
)
