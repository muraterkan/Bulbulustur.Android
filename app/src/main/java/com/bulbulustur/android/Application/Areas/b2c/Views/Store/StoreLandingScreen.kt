package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import com.bulbulustur.android.Application.Localization.BBLocalization

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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun StoreLandingScreen(
    onBackClick: () -> Unit = {},
    onStartStoreApplicationClick: () -> Unit = {},
    onStoreInfoClick: (RetailStoreInfoItem) -> Unit = {}
) {
    val screenData = remember {
        getRetailStoreLandingScreenData()
    }

    val uriHandler = LocalUriHandler.current
    val openStoreApplicationPage = {
        uriHandler.openUri("https://www.bulbulustur.com/home/now")
    }

    var selectedTab by remember {
        mutableStateOf(BBLocalization.Current.Get(key = "ed566da7-8c17-4c61-a08e-e7a24db4c901", fallback = "Avantajlar"))
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            item {
                StoreLandingTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                StoreLandingHero(
                    summary = screenData.summary,
                    onStartStoreApplicationClick = openStoreApplicationPage
                )
            }

            item {
                StoreLandingStatSection(
                    summary = screenData.summary
                )
            }

            item {
                StoreLandingTabSection(
                    tabs = screenData.tabs,
                    selectedTab = selectedTab,
                    onTabChange = {
                        selectedTab = it
                    }
                )
            }

            item {
                StoreLandingSectionTitle(
                    title = selectedTab,
                    description = BBLocalization.Current.Get(key = "e08c8c8a-1d27-4858-b380-88a1dc302d13", fallback = "Bulbulustur'da satışa başlamak isteyen mağazalar için kısa bilgiler.")
                )
            }

            items(visibleItems) { infoItem ->
                StoreLandingInfoCard(
                    infoItem = infoItem,
                    onClick = {
                        onStoreInfoClick(infoItem)
                    }
                )
            }

            item {
                StoreLandingBottomCta(
                    onStartStoreApplicationClick = openStoreApplicationPage
                )
            }
        }
    }
}

@Composable
private fun StoreLandingTopBar(
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
                text = BBLocalization.Current.Get(key = "37c64a4d-b74c-4474-bfa0-e8a07d376be8", fallback = "Bulbulustur’da Satış"),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = BBLocalization.Current.Get(key = "2eba3b33-ee20-4b6d-ac08-d1a85fb44ed8", fallback = "Mağaza açma ve satış başlangıç rehberi."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StoreLandingHero(
    summary: RetailStoreLandingSummary,
    onStartStoreApplicationClick: () -> Unit
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
                text = BBLocalization.Current.Get(key = "b3c12fd8-1f0e-4f4c-8f3e-c1c4e24f8d21", fallback = "Sadece mağaza açmayın, ticaret ekosistemine katılın."),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = BBLocalization.Current.Get(key = "25fda1cc-0c96-4a41-a5b1-358813707de0", fallback = "Ürünlerinizi perakende ve toptan satış kanallarına açın. RFQ taleplerine yanıt verin, markanızı yönetin ve satış kanallarınızı Bulbulustur ekosistemiyle birlikte büyütün."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                StoreLandingHeroPill(
                    title = summary.storeCountText,
                    subtitle = BBLocalization.Current.Get(key = "f6b3d225-42e2-4668-95b8-195ec74bb3d7", fallback = "satış kanalı")
                )

                StoreLandingHeroPill(
                    title = summary.categoryCountText,
                    subtitle = BBLocalization.Current.Get(key = "be65a706-5b1a-417e-a05f-8266bbdbdc48", fallback = "teklif sistemi")
                )

                StoreLandingHeroPill(
                    title = summary.modeText,
                    subtitle = BBLocalization.Current.Get(key = "a7f4f1c8-3522-4dd4-848e-dc98b971aa72", fallback = "bağımsız kanal")
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        onStartStoreApplicationClick()
                    }
                    .padding(
                        horizontal = 18.dp,
                        vertical = 12.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "c8c9ae14-5fd3-4e8a-8f28-1b4b270d13c8", fallback = "Mağaza başvurusu başlat"),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun StoreLandingHeroPill(
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
private fun StoreLandingStatSection(
    summary: RetailStoreLandingSummary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StoreLandingStatCard(
            modifier = Modifier.weight(1f),
            title = summary.applicationTimeText,
            subtitle = BBLocalization.Current.Get(key = "a47ec7b5-d822-436e-a834-f198b1c4fdd2", fallback = "başvuru")
        )

        StoreLandingStatCard(
            modifier = Modifier.weight(1f),
            title = summary.supportText,
            subtitle = BBLocalization.Current.Get(key = "bbec2ea2-e11e-4950-a035-93864f9a9209", fallback = "süreç")
        )

        StoreLandingStatCard(
            modifier = Modifier.weight(1f),
            title = summary.paymentText,
            subtitle = BBLocalization.Current.Get(key = "acba3cb8-5e99-4f21-a53f-d940cf132772", fallback = "altyapı")
        )
    }
}

@Composable
private fun StoreLandingStatCard(
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
private fun StoreLandingTabSection(
    tabs: List<String>,
    selectedTab: String,
    onTabChange: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
private fun StoreLandingInfoCard(
    infoItem: RetailStoreInfoItem,
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
                    .size(BBSpacing.Space12)
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
private fun StoreLandingBottomCta(
    onStartStoreApplicationClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onStartStoreApplicationClick()
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
                text = BBLocalization.Current.Get(key = "e1eb83c2-1f3f-4d8a-9b8c-4adcb9c57d31", fallback = "Satışa başlamak için hazır mısınız?"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = BBLocalization.Current.Get(key = "cbfa4b35-f7b8-4417-9a2d-9e6ec3fb5c46", fallback = "Mağaza, şirket ve satış kanalı bilgilerinizi tamamlayarak Bulbulustur ekosistemine katılma sürecini başlatın."),
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
                    text = BBLocalization.Current.Get(key = "e2ac8873-888f-43fd-9423-9cf5586be8d7", fallback = "Mağazamı Açıyorum"),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun StoreLandingSectionTitle(
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

data class RetailStoreLandingScreenData(
    val summary: RetailStoreLandingSummary,
    val tabs: List<String>,
    val infoItems: List<RetailStoreInfoItem>
)

data class RetailStoreLandingSummary(
    val storeCountText: String,
    val categoryCountText: String,
    val modeText: String,
    val applicationTimeText: String,
    val supportText: String,
    val paymentText: String
)

data class RetailStoreInfoItem(
    val id: Int,
    val groupName: String,
    val title: String,
    val description: String,
    val iconText: String
)

private fun getRetailStoreLandingScreenData(): RetailStoreLandingScreenData {
    return RetailStoreLandingScreenData(
        summary = RetailStoreLandingSummary(
            storeCountText = BBLocalization.Current.Get(key = "a8b0e4c9-11f1-4a51-9c24-79d4f0e7b101", fallback = "B2B + B2C"),
            categoryCountText = BBLocalization.Current.Get(key = "d8e91c6a-0e83-4e68-baf6-15667bb71202", fallback = "RFQ"),
            modeText = BBLocalization.Current.Get(key = "a74d0aa5-0a08-4fe4-b3cf-2e7b05a72003", fallback = "Draugr"),
            applicationTimeText = BBLocalization.Current.Get(key = "e67603d6-2489-4548-a879-577451be2311", fallback = "Kolay"),
            supportText = BBLocalization.Current.Get(key = "c6a5f9b4-2e10-43f0-a482-2350b4f65f12", fallback = "Rehberli"),
            paymentText = BBLocalization.Current.Get(key = "aba99f7e-0b0a-45aa-96b2-6ac03f36582a", fallback = "Güvenli")
        ),
        tabs = listOf(
            BBLocalization.Current.Get(key = "ed566da7-8c17-4c61-a08e-e7a24db4c901", fallback = "Avantajlar"),
            BBLocalization.Current.Get(key = "72592026-f7ec-41cb-aaae-7f8c86402fba", fallback = "Başvuru"),
            BBLocalization.Current.Get(key = "e930b5cb-d469-4ddc-81d0-a3b6232ef783", fallback = "Satış kanalları")
        ),
        infoItems = listOf(
            RetailStoreInfoItem(
                id = 1,
                groupName = BBLocalization.Current.Get(key = "ed566da7-8c17-4c61-a08e-e7a24db4c901", fallback = BBLocalization.Current.Get(key = "ed566da7-8c17-4c61-a08e-e7a24db4c901", fallback = "Avantajlar")),
                title = BBLocalization.Current.Get(key = "2c148a15-5b59-4d4b-a12d-4c84233ed125", fallback = "Tek ekosistemde satış"),
                description = BBLocalization.Current.Get(key = "6995eec9-c2fb-4614-ac6b-13690aa85277", fallback = "Perakende, toptan ve RFQ fırsatlarını aynı Bulbulustur çatısı altında yönetin."),
                iconText = "TE"
            ),
            RetailStoreInfoItem(
                id = 2,
                groupName = BBLocalization.Current.Get(key = "ed566da7-8c17-4c61-a08e-e7a24db4c901", fallback = BBLocalization.Current.Get(key = "ed566da7-8c17-4c61-a08e-e7a24db4c901", fallback = "Avantajlar")),
                title = BBLocalization.Current.Get(key = "c57c0745-93d3-4820-b17a-bd0ee91cd979", fallback = "Kategori bazlı görünürlük"),
                description = BBLocalization.Current.Get(key = "d99da23a-3200-4164-bc55-0540b22e48ec", fallback = "Ürünlerinizi kategori, mağaza, marka ve kampanya akışlarında daha görünür hale getirin."),
                iconText = "KG"
            ),
            RetailStoreInfoItem(
                id = 3,
                groupName = BBLocalization.Current.Get(key = "ed566da7-8c17-4c61-a08e-e7a24db4c901", fallback = BBLocalization.Current.Get(key = "ed566da7-8c17-4c61-a08e-e7a24db4c901", fallback = "Avantajlar")),
                title = BBLocalization.Current.Get(key = "da74bcdc-3e3e-4d65-a165-042ee4e38f8b", fallback = "Güven veren mağaza profili"),
                description = BBLocalization.Current.Get(key = "5fa7210f-ed7c-4579-b061-111b12aea422", fallback = "Mağaza vitrini, şirket bilgileri, ürün listeleri ve iletişim akışıyla profesyonel görünüm sağlayın."),
                iconText = "GP"
            ),
            RetailStoreInfoItem(
                id = 4,
                groupName = BBLocalization.Current.Get(key = "72592026-f7ec-41cb-aaae-7f8c86402fba", fallback = "Başvuru"),
                title = BBLocalization.Current.Get(key = "fb16c1c7-9aed-4fc9-a104-d7e8925fd672", fallback = "Başvurunuzu oluşturun"),
                description = BBLocalization.Current.Get(key = "f2021668-cde7-41b2-aa51-e783b399abed", fallback = "Şirket, mağaza ve iletişim bilgilerinizi tamamlayarak süreci başlatın."),
                iconText = "MB"
            ),
            RetailStoreInfoItem(
                id = 5,
                groupName = BBLocalization.Current.Get(key = "72592026-f7ec-41cb-aaae-7f8c86402fba", fallback = "Başvuru"),
                title = BBLocalization.Current.Get(key = "87a211bb-7ba1-4607-966a-a877da8184f8", fallback = "Profilinizi hazırlayın"),
                description = BBLocalization.Current.Get(key = "e1937497-c3d9-47ed-8c1a-317fcb1e4d4e", fallback = "Mağaza, marka ve tedarikçi görünümünüzü güven veren bir yapıya taşıyın."),
                iconText = "ŞD"
            ),
            RetailStoreInfoItem(
                id = 6,
                groupName = BBLocalization.Current.Get(key = "72592026-f7ec-41cb-aaae-7f8c86402fba", fallback = "Başvuru"),
                title = BBLocalization.Current.Get(key = "abe9aa28-4aa6-435f-b5cc-a5b57336bc91", fallback = "Ürün ve kanalları seçin"),
                description = BBLocalization.Current.Get(key = "149a7abd-b841-4553-9540-0b3e1d3450b7", fallback = "B2C, B2B, RFQ veya Draugr gibi uygun satış alanlarını belirleyin."),
                iconText = "ÜH"
            ),
            RetailStoreInfoItem(
                id = 7,
                groupName = BBLocalization.Current.Get(key = "e930b5cb-d469-4ddc-81d0-a3b6232ef783", fallback = "Satış kanalları"),
                title = BBLocalization.Current.Get(key = "4e4bd400-c8c7-4da5-b676-7809722b1218", fallback = "Perakende satış"),
                description = BBLocalization.Current.Get(key = "44b8f9ca-8f02-474b-9754-e6ef87b01fae", fallback = "B2C ürün listeleme, ürün detayı, sepet ve sipariş akışıyla müşterilere ulaşın."),
                iconText = "PS"
            ),
            RetailStoreInfoItem(
                id = 8,
                groupName = BBLocalization.Current.Get(key = "e930b5cb-d469-4ddc-81d0-a3b6232ef783", fallback = "Satış kanalları"),
                title = BBLocalization.Current.Get(key = "9c8ef1bf-b73f-453c-9e24-0de721db23bc", fallback = "Toptan satış"),
                description = BBLocalization.Current.Get(key = "2eaae511-2abf-475b-b0e3-188629ff3052", fallback = "Kurumsal alıcılar, toplu alım talepleri ve kategori bazlı B2B görünürlük için ürünlerinizi açın."),
                iconText = "TS"
            ),
            RetailStoreInfoItem(
                id = 9,
                groupName = BBLocalization.Current.Get(key = "e930b5cb-d469-4ddc-81d0-a3b6232ef783", fallback = "Satış kanalları"),
                title = BBLocalization.Current.Get(key = "bfc3945d-66e9-4b14-a6ea-7f7c2fd48a36", fallback = "RFQ sistemi"),
                description = BBLocalization.Current.Get(key = "52a9358d-bc16-4415-96c1-0d45e0da73c1", fallback = "Alıcılardan gelen özel fiyat ve toplu alım taleplerine teklif vererek yeni fırsatlar yakalayın."),
                iconText = "RFQ"
            ),
            RetailStoreInfoItem(
                id = 10,
                groupName = BBLocalization.Current.Get(key = "e930b5cb-d469-4ddc-81d0-a3b6232ef783", fallback = "Satış kanalları"),
                title = BBLocalization.Current.Get(key = "a9a4d3be-7b26-4c49-8970-e889b248ba01", fallback = "Draugr bağlantısı"),
                description = BBLocalization.Current.Get(key = "edb80e7d-6d7c-4f09-9af4-8489090168b7", fallback = "Kendi satış kanalınızı Bulbulustur ekosistemiyle birlikte yönetin."),
                iconText = "DR"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun StoreLandingScreenPreview() {
    MaterialTheme {
        StoreLandingScreen()
    }
}


