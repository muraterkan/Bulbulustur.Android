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
fun RegionSettingsScreen(
    onBackClick: () -> Unit = {}
) {
    val selectedRegionState = remember {
        mutableStateOf("TR")
    }

    val regions = remember {
        listOf(
            RegionOption(
                code = "TR",
                countryName = "Türkiye",
                regionName = "Türkiye Pazarı",
                flagFileName = "turkey.svg"
            ),
            RegionOption(
                code = "US",
                countryName = "United States",
                regionName = "North America",
                flagFileName = "United States of America.svg"
            ),
            RegionOption(
                code = "DE",
                countryName = "Deutschland",
                regionName = "Europe",
                flagFileName = "germany.svg"
            ),
            RegionOption(
                code = "FR",
                countryName = "France",
                regionName = "Europe",
                flagFileName = "france.svg"
            ),
            RegionOption(
                code = "GB",
                countryName = "United Kingdom",
                regionName = "Europe",
                flagFileName = "United Kingdom.svg"
            ),
            RegionOption(
                code = "AE",
                countryName = "United Arab Emirates",
                regionName = "Middle East",
                flagFileName = "United Arab Emirates.svg"
            ),
            RegionOption(
                code = "SA",
                countryName = "Saudi Arabia",
                regionName = "Middle East",
                flagFileName = "saudi-arabia.svg"
            ),
            RegionOption(
                code = "CN",
                countryName = "China",
                regionName = "Asia",
                flagFileName = "china.svg"
            )
        )
    }

    val selectedRegion = regions.firstOrNull { region ->
        region.code == selectedRegionState.value
    } ?: regions.first()

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
                title = "Ülke ve Bölge",
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
                RegionIntroCard()
            }

            item {
                SelectedRegionCard(
                    selectedRegion = selectedRegion
                )
            }

            items(
                items = regions,
                key = { region ->
                    region.code
                }
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
            text = "Seçiminiz ürün görünürlüğü, teslimat seçenekleri, ödeme deneyimi ve yerel içeriklerde kullanılacaktır.",
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegionFlag(
                flagFileName = selectedRegion.flagFileName,
                contentDescription = "${selectedRegion.countryName} bayrağı",
                highlighted = true
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Seçili Bölge",
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = selectedRegion.countryName,
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegionFlag(
                flagFileName = item.flagFileName,
                contentDescription = "${item.countryName} bayrağı"
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
                    contentDescription = "Seçili bölge",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.SizeLg)
                )
            }
        }
    }
}

@Composable
private fun RegionFlag(
    flagFileName: String,
    contentDescription: String,
    highlighted: Boolean = false
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(BBIcon.BoxLg)
            .background(
                color = if (highlighted) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                },
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

private data class RegionOption(
    val code: String,
    val countryName: String,
    val regionName: String,
    val flagFileName: String
)