package com.bulbulustur.android.Views.Account

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
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTypography
import com.bulbulustur.android.wwwroot.theme.BbAlpha

@Composable
fun LanguageSettingsScreen(
    onBackClick: () -> Unit = {}
) {
    val selectedLanguageState = remember {
        mutableStateOf("tr")
    }

    val languages = listOf(
        LanguageOption("tr", "TÃ¼rkÃ§e", "TÃ¼rkiye", "ğŸ‡¹ğŸ‡·"),
        LanguageOption("en", "English", "United States", "ğŸ‡ºğŸ‡¸"),
        LanguageOption("de", "Deutsch", "Deutschland", "ğŸ‡©ğŸ‡ª"),
        LanguageOption("fr", "FranÃ§ais", "France", "ğŸ‡«ğŸ‡·"),
        LanguageOption("es", "EspaÃ±ol", "EspaÃ±a", "ğŸ‡ªğŸ‡¸"),
        LanguageOption("ru", "Ğ ÑƒÑÑĞºĞ¸Ğ¹", "Ğ Ğ¾ÑÑĞ¸Ñ", "ğŸ‡·ğŸ‡º"),
        LanguageOption("ar", "Ø§Ù„Ø¹Ø±Ø¨ÙŠØ©", "Saudi Arabia", "ğŸ‡¸ğŸ‡¦"),
        LanguageOption("zh", "ç®€ä½“ä¸­æ–‡", "China", "ğŸ‡¨ğŸ‡³")
    )

    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = BbAlpha.DisabledContainer),
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
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                LanguageIntroCard()
            }

            items(
                items = languages,
                key = { language -> language.code }
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
            text = "SeÃ§iminiz uygulama metinlerinde, bildirimlerde ve destek iÃ§eriklerinde kullanÄ±lacaktÄ±r.",
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.flag,
                    style = BbTypography.titleLarge
                )
            }

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
    val region: String,
    val flag: String
)
