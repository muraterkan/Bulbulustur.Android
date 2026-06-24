package com.bulbulustur.android.Application.Areas.b2c.Views.Campaign


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun CampaignListScreen(
    onBackClick: () -> Unit = {},
    onCampaignClick: (RetailCampaignListItem) -> Unit = {},
    onSearchSubmit: (String) -> Unit = {}
) {
    val campaigns = remember {
        getRetailCampaignListItems()
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedCampaignType by remember {
        mutableStateOf(RetailCampaignType.All)
    }

    val filteredCampaigns = remember(selectedCampaignType, campaigns) {
        if (selectedCampaignType == RetailCampaignType.All) {
            campaigns
        } else {
            campaigns.filter {
                it.campaignType == selectedCampaignType
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 14.dp,
                end = 16.dp,
                bottom = 28.dp
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            item {
                CampaignListTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                CampaignListHero()
            }

            item {
                CampaignSearchBox(
                    searchText = searchText,
                    onSearchTextChange = {
                        searchText = it
                    },
                    onSearchSubmit = {
                        onSearchSubmit(searchText)
                    }
                )
            }

            item {
                CampaignTypeFilterChips(
                    selectedCampaignType = selectedCampaignType,
                    onCampaignTypeChange = {
                        selectedCampaignType = it
                    }
                )
            }

            item {
                CampaignSectionTitle(
                    title = "Aktif kampanyalar",
                    description = "Perakende Alışverişte öne çıkan fırsatları keşfet."
                )
            }

            items(filteredCampaigns) { campaign ->
                CampaignListCard(
                    campaign = campaign,
                    onClick = {
                        onCampaignClick(campaign)
                    }
                )
            }
        }
    }
}

@Composable
private fun CampaignListTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BbIconBox(
            modifier = Modifier.clickable {
                onBackClick()
            },
            size = BbIconBoxSize.Medium,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            Text(
                text = "â€¹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Kampanyalar",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Fırsat, Vitrin ve sezon kampanyaları.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CampaignListHero() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Text(
                text = "Bugünün perakende fırsatları",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Seçili maĞazalar, kategori Vitrinleri ve indirimli ürün akışları burada toplanır.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                CampaignHeroPill(
                    title = "24",
                    subtitle = "aktif"
                )

                CampaignHeroPill(
                    title = "8",
                    subtitle = "Vitrin"
                )

                CampaignHeroPill(
                    title = "12K",
                    subtitle = "ürün"
                )
            }
        }
    }
}

@Composable
private fun CampaignHeroPill(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.72f))
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CampaignSearchBox(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchSubmit: () -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp)),
        placeholder = {
            Text(text = "Kampanya veya kategori ara")
        },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = BBColors.Transparent,
            unfocusedIndicatorColor = BBColors.Transparent,
            disabledIndicatorColor = BBColors.Transparent
        ),
        trailingIcon = {
            Text(
                text = "Ara",
                modifier = Modifier
                    .padding(end = BBSpacing.Space3)
                    .clickable {
                        onSearchSubmit()
                    },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CampaignTypeFilterChips(
    selectedCampaignType: RetailCampaignType,
    onCampaignTypeChange: (RetailCampaignType) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        RetailCampaignType.entries.forEach { campaignType ->
            FilterChip(
                selected = selectedCampaignType == campaignType,
                onClick = {
                    onCampaignTypeChange(campaignType)
                },
                label = {
                    Text(text = campaignType.title)
                }
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CampaignListCard(
    campaign: RetailCampaignListItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = campaign.iconText,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = campaign.badgeText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = campaign.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Text(
                    text = "â€º",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = campaign.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                CampaignMetaPill(
                    text = campaign.categoryName
                )

                CampaignMetaPill(
                    text = "${campaign.productCount} ürün"
                )

                CampaignMetaPill(
                    text = campaign.endDateText
                )
            }
        }
    }
}

@Composable
private fun CampaignMetaPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = 9.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

enum class RetailCampaignType(
    val title: String
) {
    All("Tümü"),
    Discount("Ä°ndirim"),
    Showcase("Vitrin"),
    NewSeason("Yeni sezon"),
    Cargo("Kargo")
}

data class RetailCampaignListItem(
    val id: Int,
    val title: String,
    val description: String,
    val badgeText: String,
    val iconText: String,
    val categoryName: String,
    val productCount: Int,
    val endDateText: String,
    val campaignType: RetailCampaignType
)

private fun getRetailCampaignListItems(): List<RetailCampaignListItem> {
    return listOf(
        RetailCampaignListItem(
            id = 1,
            title = "Sezonun öne çıkanları",
            description = "Moda kategorisinde yeni sezon ürünleri ve seçili maĞaza Vitrinleri.",
            badgeText = "Yeni sezon",
            iconText = "MO",
            categoryName = "Moda",
            productCount = 1240,
            endDateText = "7 gün kaldı",
            campaignType = RetailCampaignType.NewSeason
        ),
        RetailCampaignListItem(
            id = 2,
            title = "Elektronikte hızlı fırsatlar",
            description = "Telefon aksesuarı, kulaklık ve günlük teknoloji ürünlerinde avantajlı seçimler.",
            badgeText = "Ä°ndirim",
            iconText = "EL",
            categoryName = "Elektronik",
            productCount = 680,
            endDateText = "3 gün kaldı",
            campaignType = RetailCampaignType.Discount
        ),
        RetailCampaignListItem(
            id = 3,
            title = "Ev & Yaşam Vitrinleri",
            description = "Mutfak, dekorasyon ve ev tekstili ürünlerinde öne çıkan MaĞazalar.",
            badgeText = "Vitrin",
            iconText = "EV",
            categoryName = "Ev & Yaşam",
            productCount = 910,
            endDateText = "12 gün kaldı",
            campaignType = RetailCampaignType.Showcase
        ),
        RetailCampaignListItem(
            id = 4,
            title = "Ücretsiz kargo seçkisi",
            description = "Seçili maĞazalarda kargo avantajı sunan ürünleri tek akışta keşfet.",
            badgeText = "Kargo",
            iconText = "KG",
            categoryName = "Karışık",
            productCount = 1560,
            endDateText = "Bugün aktif",
            campaignType = RetailCampaignType.Cargo
        ),
        RetailCampaignListItem(
            id = 5,
            title = "Anne & Bebek fırsatları",
            description = "Bebek bakım, oyuncak ve tekstil ürünlerinde günlük fırsat ürünleri.",
            badgeText = "Ä°ndirim",
            iconText = "AB",
            categoryName = "Anne & Bebek",
            productCount = 420,
            endDateText = "5 gün kaldı",
            campaignType = RetailCampaignType.Discount
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CampaignListScreenPreview() {
    MaterialTheme {
        CampaignListScreen()
    }
}


