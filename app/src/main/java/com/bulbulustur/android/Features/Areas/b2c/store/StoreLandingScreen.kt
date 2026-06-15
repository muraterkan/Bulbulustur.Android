package com.bulbulustur.android.Features.areas.b2c.store

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.bulbulustur.android.Ui.components.BbIconBoxSize
import com.bulbulustur.android.Ui.components.BbIconBox
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Ui.theme.BbSpacing

@Composable
fun SellerLandingScreen(
    onBackClick: () -> Unit = {},
    onStartSellerApplicationClick: () -> Unit = {},
    onSellerInfoClick: (RetailSellerInfoItem) -> Unit = {}
) {
    val screenData = remember {
        getRetailSellerLandingScreenData()
    }

    var selectedTab by remember {
        mutableStateOf("Avantajlar")
    }

    val visibleItems = remember(selectedTab, screenData.infoItems) {
        screenData.infoItems.filter {
            it.groupName == selectedTab
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            item {
                SellerLandingTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                SellerLandingHero(
                    summary = screenData.summary,
                    onStartSellerApplicationClick = onStartSellerApplicationClick
                )
            }

            item {
                SellerLandingStatSection(
                    summary = screenData.summary
                )
            }

            item {
                SellerLandingTabSection(
                    tabs = screenData.tabs,
                    selectedTab = selectedTab,
                    onTabChange = {
                        selectedTab = it
                    }
                )
            }

            item {
                SellerLandingSectionTitle(
                    title = selectedTab,
                    description = "Bulbulustur’da satışa başlamak isteyen mağazalar için kısa bilgiler."
                )
            }

            items(visibleItems) { infoItem ->
                SellerLandingInfoCard(
                    infoItem = infoItem,
                    onClick = {
                        onSellerInfoClick(infoItem)
                    }
                )
            }

            item {
                SellerLandingBottomCta(
                    onStartSellerApplicationClick = onStartSellerApplicationClick
                )
            }
        }
    }
}

@Composable
private fun SellerLandingTopBar(
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
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Bulbulustur’da satış yap",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Mağaza açma ve satış başlangıç rehberi.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SellerLandingHero(
    summary: RetailSellerLandingSummary,
    onStartSellerApplicationClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
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
                text = "Ürünlerini Bulbulustur’da vitrine çıkar",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Perakende ve toptan satış kanallarına tek ekosistemden ulaşmak isteyen mağazalar için başlangıç alanı.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                SellerLandingHeroPill(
                    title = summary.storeCountText,
                    subtitle = "mağaza"
                )

                SellerLandingHeroPill(
                    title = summary.categoryCountText,
                    subtitle = "kategori"
                )

                SellerLandingHeroPill(
                    title = summary.modeText,
                    subtitle = "kanal"
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        onStartSellerApplicationClick()
                    }
                    .padding(
                        horizontal = 18.dp,
                        vertical = 12.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mağaza başvurusu başlat",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun SellerLandingHeroPill(
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
private fun SellerLandingStatSection(
    summary: RetailSellerLandingSummary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SellerLandingStatCard(
            modifier = Modifier.weight(1f),
            title = summary.applicationTimeText,
            subtitle = "başvuru"
        )

        SellerLandingStatCard(
            modifier = Modifier.weight(1f),
            title = summary.supportText,
            subtitle = "destek"
        )

        SellerLandingStatCard(
            modifier = Modifier.weight(1f),
            title = summary.paymentText,
            subtitle = "ödeme"
        )
    }
}

@Composable
private fun SellerLandingStatCard(
    modifier: Modifier,
    title: String,
    subtitle: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SellerLandingTabSection(
    tabs: List<String>,
    selectedTab: String,
    onTabChange: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        tabs.forEach { tab ->
            FilterChip(
                selected = selectedTab == tab,
                onClick = {
                    onTabChange(tab)
                },
                label = {
                    Text(text = tab)
                }
            )
        }
    }
}

@Composable
private fun SellerLandingInfoCard(
    infoItem: RetailSellerInfoItem,
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space12)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = infoItem.iconText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = infoItem.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = infoItem.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SellerLandingBottomCta(
    onStartSellerApplicationClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onStartSellerApplicationClick()
            },
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Hazırsan başvurunu başlat",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Mağaza bilgilerini, şirket bilgilerini ve ürün satış tercihlerini tamamlayarak başvuru sürecine geçebilirsin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Başvuruyu başlat",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun SellerLandingSectionTitle(
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

data class RetailSellerLandingScreenData(
    val summary: RetailSellerLandingSummary,
    val tabs: List<String>,
    val infoItems: List<RetailSellerInfoItem>
)

data class RetailSellerLandingSummary(
    val storeCountText: String,
    val categoryCountText: String,
    val modeText: String,
    val applicationTimeText: String,
    val supportText: String,
    val paymentText: String
)

data class RetailSellerInfoItem(
    val id: Int,
    val groupName: String,
    val title: String,
    val description: String,
    val iconText: String
)

private fun getRetailSellerLandingScreenData(): RetailSellerLandingScreenData {
    return RetailSellerLandingScreenData(
        summary = RetailSellerLandingSummary(
            storeCountText = "19K+",
            categoryCountText = "1000+",
            modeText = "B2B+B2C",
            applicationTimeText = "5 dk",
            supportText = "Rehberli",
            paymentText = "Güvenli"
        ),
        tabs = listOf(
            "Avantajlar",
            "Başvuru",
            "Satış kanalları"
        ),
        infoItems = listOf(
            RetailSellerInfoItem(
                id = 1,
                groupName = "Avantajlar",
                title = "Tek ekosistemde satış",
                description = "Perakende ve toptan satış kanallarına aynı Bulbulustur çatısı altında hazırlanırsın.",
                iconText = "TE"
            ),
            RetailSellerInfoItem(
                id = 2,
                groupName = "Avantajlar",
                title = "Kategori bazlı görünürlük",
                description = "Ürünlerin kategori, mağaza ve kampanya akışlarında keşfedilebilir hale gelir.",
                iconText = "KG"
            ),
            RetailSellerInfoItem(
                id = 3,
                groupName = "Avantajlar",
                title = "Güven veren mağaza profili",
                description = "Mağaza vitrini, puanlama, ürün listeleri ve sipariş akışıyla profesyonel görünüm sağlanır.",
                iconText = "GP"
            ),
            RetailSellerInfoItem(
                id = 4,
                groupName = "Başvuru",
                title = "Mağaza bilgileri",
                description = "Mağaza adı, iletişim bilgileri ve temel ticari bilgiler başvuru sırasında alınır.",
                iconText = "MB"
            ),
            RetailSellerInfoItem(
                id = 5,
                groupName = "Başvuru",
                title = "Şirket doğrulama",
                description = "Satıcı güvenliği için şirket ve yetkili bilgileri doğrulama sürecine alınır.",
                iconText = "ŞD"
            ),
            RetailSellerInfoItem(
                id = 6,
                groupName = "Başvuru",
                title = "Ürün hazırlığı",
                description = "Ürünlerin kategori, fiyat, stok ve görsel bilgileri satışa hazır hale getirilir.",
                iconText = "ÜH"
            ),
            RetailSellerInfoItem(
                id = 7,
                groupName = "Satış kanalları",
                title = "Perakende satış",
                description = "B2C ürün listeleme, ürün detayı, sepet ve ödeme akışıyla müşteriye ulaşılır.",
                iconText = "PS"
            ),
            RetailSellerInfoItem(
                id = 8,
                groupName = "Satış kanalları",
                title = "Toptan satış",
                description = "Toptan ürün, tedarikçi görünürlüğü ve RFQ akışlarıyla işletmelere erişim sağlanır.",
                iconText = "TS"
            ),
            RetailSellerInfoItem(
                id = 9,
                groupName = "Satış kanalları",
                title = "Kampanya ve vitrinler",
                description = "Seçili ürünleri ve mağaza fırsatlarını kampanya alanlarında öne çıkarabilirsin.",
                iconText = "KV"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SellerLandingScreenPreview() {
    MaterialTheme {
        SellerLandingScreen()
    }
}
