package com.bulbulustur.android.Application.Areas.b2c.Views.Campaign

import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes

@Composable
fun CampaignListScreen(
    campaigns: List<CampaignDTO>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onCampaignClick: (CampaignDTO) -> Unit = {},
    onSearchSubmit: (String) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val filteredCampaigns = remember(
        searchText,
        campaigns
    ) {
        if (searchText.isBlank()) {
            campaigns
        } else {
            campaigns.filter { campaign ->
                campaign.CampaignName.orEmpty().contains(
                    other = searchText,
                    ignoreCase = true
                ) ||
                        campaign.Description.orEmpty().contains(
                            other = searchText,
                            ignoreCase = true
                        ) ||
                        campaign.CategoryName.orEmpty().contains(
                            other = searchText,
                            ignoreCase = true
                        )
            }
        }
    }

    val campaignRows = remember(filteredCampaigns) {
        filteredCampaigns.chunked(2)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                placeholder = BBLocalization.Current.Get(key = "b66af534-b950-4603-8fb6-caf6ca5be73d", fallback = "Kampanya ara"),
                onSearchClick = {
                    onSearchSubmit(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                onMenuClick = {},
                onFavoriteClick = {},
                leadingAction =
                    RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.SectionGapCompact
            )
        ) {
            item {
                CampaignListHero(
                    campaignCount = campaigns.size
                )
            }

            item {
                CampaignSectionTitle(
                    title = BBLocalization.Current.Get(key = "e2812624-6bbc-4034-9a09-6570540d0785", fallback = "Tüm Kampanyalar"),
                    description = BBLocalization.Current.Get(key = "387b8cfc-5009-4880-92f1-4ba72f11e883", fallback = "Bulbulustur içinde öne çıkan kampanya alanlarını ve avantajlı alışveriş fırsatlarını görüntüle.")
                )
            }

            when {
                isLoading -> {
                    item {
                        CampaignListInfoCard(
                            title = BBLocalization.Current.Get(key = "b0232075-fe70-45c8-aabf-7f0e54f81ce5", fallback = "Kampanyalar yükleniyor"),
                            description = BBLocalization.Current.Get(key = "20b5f12d-1dbf-467c-af8e-3138533bedcc", fallback = ""),
                            showProgress = true
                        )
                    }
                }

                !errorMessage.isNullOrBlank() &&
                        campaigns.isEmpty() -> {
                    item {
                        CampaignListInfoCard(
                            title = BBLocalization.Current.Get(key = "bdf61c70-ebbc-45bc-bb86-a8c7e4d90fe0", fallback = "Kampanyalar alınamadı"),
                            description = errorMessage
                        )
                    }
                }

                filteredCampaigns.isEmpty() -> {
                    item {
                        CampaignListInfoCard(
                            title = BBLocalization.Current.Get(key = "e59db449-f1ba-4c09-b6ea-0c60689bfd19", fallback = "Kampanya bulunamadı"),
                            description = if (searchText.isBlank()) {
                                BBLocalization.Current.Get(key = "53cbf453-6e41-4101-b87b-a8fc1d657774", fallback = "Gösterilecek aktif kampanya bulunmuyor.")
                            } else {
                                BBLocalization.Current.Get(key = "9bde1108-7242-4562-8e63-2e4c42e8d8da", fallback = "Arama kriterine uygun kampanya bulunamadı.")
                            }
                        )
                    }
                }

                else -> {
                    itemsIndexed(
                        items = campaignRows,
                        key = { rowIndex, row ->
                            val rowKey = row.joinToString("-") {
                                it.CampaignId.toString()
                            }

                            "campaign-row-$rowIndex-$rowKey"
                        }
                    ) { rowIndex, rowCampaigns ->
                        CampaignGridRow(
                            rowIndex = rowIndex,
                            campaigns = rowCampaigns,
                            onCampaignClick = onCampaignClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CampaignListHero(
    campaignCount: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            CampaignLabel(
                text = BBLocalization.Current.Get(key = "020751bc-1a5b-4d88-b23b-37b77a471c2f", fallback = "Güncel Kampanyalar")
            )

            Text(
                text = BBLocalization.Current.Get(key = "fa54b6d9-199b-4867-99e8-ff8865857ce0", fallback = "Alışveriş Fırsatlarını Keşfet"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = BBLocalization.Current.Get(key = "bb280593-2026-4351-8976-11a701bde6a9", fallback = "Seçili kampanyaları, avantajlı ürün gruplarını ve fırsat alanlarını tek yerden incele."),
                style = MaterialTheme.typography.bodyMedium,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "$campaignCount aktif kampanya",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun CampaignSectionTitle(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Text(
            text = BBLocalization.Current.Get(key = "f37b300a-de24-47cd-8e2e-df828a9bde38", fallback = "GÜNCEL KAMPANYALAR"),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CampaignGridRow(
    rowIndex: Int,
    campaigns: List<CampaignDTO>,
    onCampaignClick: (CampaignDTO) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        ),
        verticalAlignment = Alignment.Top
    ) {
        campaigns.forEachIndexed { columnIndex, campaign ->
            CampaignListCard(
                campaign = campaign,
                modifier = Modifier.weight(1f),
                onClick = {
                    if (campaign.CampaignId > 0) {
                        onCampaignClick(campaign)
                    }
                }
            )
        }

        if (campaigns.size == 1) {
            Spacer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun CampaignListCard(
    campaign: CampaignDTO,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val campaignName =
        campaign.CampaignName
            ?.takeIf { it.isNotBlank() }
            ?: BBLocalization.Current.Get(key = "b66af534-b950-4603-8fb6-caf6ca5be73d", fallback = "Kampanya")

    val description =
        campaign.Description
            ?.takeIf { it.isNotBlank() }
            ?: BBLocalization.Current.Get(key = "1f5aca93-a0a5-4cbf-9294-4d01d58415d5", fallback = "Avantajlı kampanya fırsatlarını keşfet.")

    val campaignCondition =
        campaign.CampaignCondition
            ?.takeIf { it.isNotBlank() }
            ?: BBLocalization.Current.Get(key = "72a2d2e6-646c-417f-9353-98d3a3f2b490", fallback = "Süreli kampanya")

    val campaignPicture =
        campaign.Picture
            ?.takeIf { it.isNotBlank() }
            ?: campaign.DefaultPicture
                ?.takeIf { it.isNotBlank() }
            ?: ""

    Card(
        modifier = modifier
            .clickable(
                enabled = campaign.CampaignId > 0
            ) {
                onClick()
            },
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = BBSpacing.Space3,
                        top = BBSpacing.Space3,
                        end = BBSpacing.Space3
                    )
            ) {
                AsyncImage(
                    model = ImageUrlResolver.Resolve(campaignPicture),
                    contentDescription = campaignName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(BBRadius.LgShape)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant
                        ),
                    contentScale = ContentScale.Crop
                )

                CampaignBadge(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(BBSpacing.Space2)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.Space3),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "b66af534-b950-4603-8fb6-caf6ca5be73d", fallback = "Kampanya"),
                    style = MaterialTheme.typography.labelSmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = campaignName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    minLines = 3,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                CampaignConditionChip(
                    text = campaignCondition
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color =
                        MaterialTheme.colorScheme.outlineVariant
                ) {}

                BbButton(
                    text = BBLocalization.Current.Get(key = "b136ac4d-f11b-4e90-9231-3fd15c387daa", fallback = "Detayları Gör"),
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small,
                    enabled = campaign.CampaignId > 0,
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector =
                                Icons.AutoMirrored.Outlined.ArrowForward,
                            contentDescription = null
                        )
                    },
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
private fun CampaignBadge(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.error
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                imageVector = Icons.Outlined.LocalOffer,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onError
            )

            Text(
                text = BBLocalization.Current.Get(key = "b66af534-b950-4603-8fb6-caf6ca5be73d", fallback = "Kampanya"),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Composable
private fun CampaignConditionChip(
    text: String
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun CampaignLabel(
    text: String
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.72f
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                imageVector = Icons.Outlined.LocalOffer,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CampaignListInfoCard(
    title: String,
    description: String,
    showProgress: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            if (showProgress) {
                CircularProgressIndicator()
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

