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
import androidx.compose.material.icons.outlined.Public
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
fun RegionSettingsScreen(
    onBackClick: () -> Unit = {}
) {
    val selectedRegionState = remember {
        mutableStateOf("TR")
    }

    val regions = listOf(
        RegionOption(
            code = "TR",
            countryName = "TÃ¼rkiye",
            regionName = "TÃ¼rkiye PazarÄ±",
            flag = "ğŸ‡¹ğŸ‡·"
        ),
        RegionOption(
            code = "US",
            countryName = "United States",
            regionName = "North America",
            flag = "ğŸ‡ºğŸ‡¸"
        ),
        RegionOption(
            code = "DE",
            countryName = "Deutschland",
            regionName = "Europe",
            flag = "ğŸ‡©ğŸ‡ª"
        ),
        RegionOption(
            code = "FR",
            countryName = "France",
            regionName = "Europe",
            flag = "ğŸ‡«ğŸ‡·"
        ),
        RegionOption(
            code = "GB",
            countryName = "United Kingdom",
            regionName = "Europe",
            flag = "ğŸ‡¬ğŸ‡§"
        ),
        RegionOption(
            code = "AE",
            countryName = "United Arab Emirates",
            regionName = "Middle East",
            flag = "ğŸ‡¦ğŸ‡ª"
        ),
        RegionOption(
            code = "SA",
            countryName = "Saudi Arabia",
            regionName = "Middle East",
            flag = "ğŸ‡¸ğŸ‡¦"
        ),
        RegionOption(
            code = "CN",
            countryName = "China",
            regionName = "Asia",
            flag = "ğŸ‡¨ğŸ‡³"
        )
    )

    val selectedRegion = regions.firstOrNull { region ->
        region.code == selectedRegionState.value
    } ?: regions.first()

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
                title = "Ãœlke Ve BÃ¶lge",
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
                RegionIntroCard()
            }

            item {
                SelectedRegionCard(
                    selectedRegion = selectedRegion
                )
            }

            items(
                items = regions,
                key = { region -> region.code }
            ) { region ->
                RegionRow(
                    item = region,
                    isSelected = selectedRegionState.value == region.code,
                    onClick = {
                        selectedRegionState.value = region.code
                    }
                )
            }
        }
    }
}

@Composable
private fun RegionIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "SeÃ§iminiz Ã¼rÃ¼n gÃ¶rÃ¼nÃ¼rlÃ¼ÄŸÃ¼, teslimat seÃ§enekleri, Ã¶deme deneyimi ve yerel iÃ§eriklerde kullanÄ±lacaktÄ±r.",
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SelectedRegionCard(
    selectedRegion: RegionOption
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
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
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Public,
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
                    text = "SeÃ§ili BÃ¶lge",
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${selectedRegion.flag} ${selectedRegion.countryName}",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = selectedRegion.regionName,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun RegionRow(
    item: RegionOption,
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
                    text = item.countryName,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.regionName,
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

private data class RegionOption(
    val code: String,
    val countryName: String,
    val regionName: String,
    val flag: String
)
