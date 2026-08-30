package com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySliderDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySliderPageDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun WholesaleProductCategorySlider(
    slider: WholesaleProductCategorySliderDTO?,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    onPageClick: (WholesaleProductCategorySliderPageDTO) -> Unit = {}
) {
    val pages = slider
        ?.Pages
        .orEmpty()
        .filter {
            it.WholesaleProductCategorySliderPageId > 0
        }

    when {
        isLoading && pages.isEmpty() -> {
            WholesaleProductCategorySliderLoading(
                modifier = modifier
            )
        }

        pages.isNotEmpty() -> {
            val pagerState = rememberPagerState(
                pageCount = {
                    pages.size
                }
            )

            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = BBRadius.XxlShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = BorderStroke(
                        width = BBSpacing.Hairline,
                        color = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.24f
                        )
                    )
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    ) { pageIndex ->
                        WholesaleProductCategorySliderPage(
                            page = pages[pageIndex],
                            onClick = {
                                onPageClick(
                                    pages[pageIndex]
                                )
                            }
                        )
                    }
                }

                if (pages.size > 1) {
                    WholesaleProductCategorySliderIndicator(
                        pageCount = pages.size,
                        currentPage = pagerState.currentPage
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleProductCategorySliderPage(
    page: WholesaleProductCategorySliderPageDTO,
    onClick: () -> Unit
) {
    val label = page.Content1.trim()
    val title = page.Content2.trim()
    val buttonCaption = page.ButtonCaption.trim()
    val pictureUrl = ImageUrlResolver.Resolve(
        page.Picture.trim()
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(BBSpacing.Space5),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space4
        )
    ) {
        if (label.isNotBlank()) {
            Surface(
                shape = BBRadius.PillShape,
                color = MaterialTheme.colorScheme.surface.copy(
                    alpha = 0.86f
                ),
                border = BorderStroke(
                    width = BBSpacing.Hairline,
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.18f
                    )
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.Space3,
                        vertical = BBSpacing.Space2
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Campaign,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.Ui),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (title.isNotBlank()) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = BBRadius.XlShape,
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(
                width = BBSpacing.Hairline,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        ) {
            if (pictureUrl.isNotBlank()) {
                AsyncImage(
                    model = pictureUrl,
                    contentDescription = title.ifBlank {
                        label
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(BBRadius.XlShape),
                    contentScale = ContentScale.Fit
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BBSpacing.Space6),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Image,
                        contentDescription = null,
                        modifier = Modifier.size(
                            BBIcon.CampaignMediaIcon
                        ),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        if (buttonCaption.isNotBlank()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    },
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.ButtonPaddingHorizontal,
                        vertical = BBSpacing.ButtonPaddingVertical
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = buttonCaption,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = BBSpacing.ButtonGap
                            )
                            .size(BBIcon.ButtonIcon),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleProductCategorySliderIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            Surface(
                modifier = Modifier
                    .padding(
                        horizontal = BBSpacing.Space1
                    )
                    .size(
                        if (index == currentPage) {
                            BBSpacing.Space3
                        } else {
                            BBSpacing.Space2
                        }
                    ),
                shape = BBRadius.PillShape,
                color = if (index == currentPage) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.outlineVariant
                }
            ) {}
        }
    }
}

@Composable
private fun WholesaleProductCategorySliderLoading(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.24f
            )
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space6),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}