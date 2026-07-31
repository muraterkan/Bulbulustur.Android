package com.bulbulustur.android.Application.Views.About

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.Hub
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun AboutScreen(
    onBackClick: () -> Unit = {},
    onInvestorClick: () -> Unit = {},
    onCareerClick: () -> Unit = {},
    onContactClick: () -> Unit = {},
    onRoadmapClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "a980d1d3-0c97-4b45-b342-98d4157dc379", fallback = "Hakkımızda"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
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
                AboutIntroCard()
            }

            item {
                AboutEcosystemCard()
            }

            item {
                BbSectionHeader(
                    title = "Bulbulustur Hakkında",
                    subtitle = "Platformun hikayesi, amacı ve ticaret yaklaşımı"
                )
            }

            items(
                items = aboutStoryItems(),
                key = { item -> item.title }
            ) { item ->
                AboutStoryCard(
                    item = item
                )
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(key = "c58e1d2d-c0e4-4ea0-a4f7-6158e988a819", fallback = "Ne Yapıyoruz?"),
                    subtitle = "Toptan, perakende ve dijital ticaret altyapısını aynı omurgada topluyoruz"
                )
            }

            item {
                AboutCapabilityGrid()
            }

            item {
                BbSectionHeader(
                    title = "Platform Yönleri",
                    subtitle = "Alıcı, satıcı, tedarikçi ve geliştirici akışları"
                )
            }

            items(
                items = aboutPlatformItems(),
                key = { item -> item.title }
            ) { item ->
                AboutPlatformCard(
                    item = item
                )
            }

            item {
                BbSectionHeader(
                    title = "Devam Edin",
                    subtitle = "Kurumsal sayfalara hızlı geçiş"
                )
            }

            item {
                AboutActionArea(
                    onInvestorClick = onInvestorClick,
                    onCareerClick = onCareerClick,
                    onContactClick = onContactClick,
                    onRoadmapClick = onRoadmapClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space8))
            }
        }
    }
}

@Composable
private fun AboutIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Bulbulustur; toptan tedarik, perakende satış ve dijital ticaret altyapısını aynı çatı altında birleştiren teknoloji odaklı bir ticaret ekosistemidir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AboutEcosystemCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Text(
                text = "Bulbulustur Ekosistemi",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Toptan tedarik, perakende satış ve dijital ticaret altyapısı tek çatı altında.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                AboutTrustRow(
                    BBLocalization.Current.Get(key = "02040c6a-eb63-407c-9116-1348d6a4cec4", fallback = ""),
                    icon = Icons.Outlined.Verified
                )

                AboutTrustRow(
                    title = "Tedarikçi ve Mağaza Ağı",
                    icon = Icons.Outlined.Storefront
                )

                AboutTrustRow(
                    title = "Dijital Ticaret Altyapısı",
                    icon = Icons.Outlined.Hub
                )
            }
        }
    }
}

@Composable
private fun AboutTrustRow(
    title: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun AboutStoryCard(
    item: AboutStoryItem
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun AboutCapabilityGrid() {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            AboutCapabilityCard(
                title = "Toptan",
                description = "Tedarikçi, RFQ ve toptan ürün Keşfi.",
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f)
            )

            AboutCapabilityCard(
                title = "Perakende",
                description = "Mağaza, ürün ve sipariş deneyimi.",
                icon = Icons.Outlined.Storefront,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            AboutCapabilityCard(
                title = "Draugr",
                description = "Dijital ticaret sitesi altyapısı.",
                icon = Icons.Outlined.RocketLaunch,
                modifier = Modifier.weight(1f)
            )

            AboutCapabilityCard(
                title = "Global",
                description = "Çok dilli ticaret omurgası.",
                icon = Icons.Outlined.Public,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun AboutCapabilityCard(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun AboutPlatformCard(
    item: AboutPlatformItem
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun AboutActionArea(
    onInvestorClick: () -> Unit,
    onCareerClick: () -> Unit,
    onContactClick: () -> Unit,
    onRoadmapClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        AboutActionCard(
            title = BBLocalization.Current.Get(key = "d70791d0-68e8-487c-94b5-d4002ff4b135", fallback = "Yatırımcı İlişkileri"),
            description = "Kurumsal bilgi ve yatırımcı iletişim alanına geç.",
            icon = Icons.Outlined.Business,
            onClick = onInvestorClick
        )

        AboutActionCard(
            title = BBLocalization.Current.Get(key = "d0402efd-1de4-4014-a781-a12d16a2f43d", fallback = "Kariyer"),
            description = "Bulbulustur ekosisteminde açık roller ve ekip kültürü.",
            icon = Icons.Outlined.Business,
            onClick = onCareerClick
        )

        AboutActionCard(
            title = BBLocalization.Current.Get(key = "9e514dba-077f-4b79-95ce-5f2743895544", fallback = "Yol Haritası"),
            description = "Platformun gelişim adımlarını incele.",
            icon = Icons.Outlined.RocketLaunch,
            onClick = onRoadmapClick
        )

        AboutActionCard(
            title = BBLocalization.Current.Get(key = "0cf2cda1-7cf6-4d8b-ab56-8918e3a260fd", fallback = ""),
            description = "Doğru ekibe hızlıca ulaş.",
            icon = Icons.Outlined.ChevronRight,
            onClick = onContactClick
        )
    }
}

@Composable
private fun AboutActionCard(
    title: String,
    description: String,
    icon: ImageVector,
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private data class AboutStoryItem(
    val title: String,
    val description: String,
    val icon: ImageVector
)

private data class AboutPlatformItem(
    val title: String,
    val description: String,
    val icon: ImageVector
)

private fun aboutStoryItems(): List<AboutStoryItem> {
    return listOf(
        AboutStoryItem(
            title = BBLocalization.Current.Get(key = "6833a4a3-9f65-4be6-9c7d-13501943a3ab", fallback = "Bizim Hikayemiz"),
            description = "Bulbulustur, üretici, tedarikçi, mağaza ve alıcıları aynı dijital omurgada buluşturmak için geliştirilen bir ticaret altyapısıdır.",
            icon = Icons.Outlined.HistoryEdu
        ),
        AboutStoryItem(
            title = "Neler Yapıyoruz?",
            description = "Toptan tedarik, perakende satış, RFQ, mağaza, marka, ödeme, kargo ve dijital site altyapılarını tek ekosistemde birleştiriyoruz.",
            icon = Icons.Outlined.Lightbulb
        ),
        AboutStoryItem(
            title = BBLocalization.Current.Get(key = "8aa642d5-8035-4bb3-99fc-eb8d685e4692", fallback = "Neye İnanıyoruz?"),
            description = "Ticaretin daha görünür, izlenebilir, erişilebilir ve dijital altyapılarla daha güçlü hale gelmesi gerektiğine inanıyoruz.",
            icon = Icons.Outlined.AutoAwesome
        ),
        AboutStoryItem(
            title = BBLocalization.Current.Get(key = "83e55f8c-1284-4ef8-a364-13aa257fee84", fallback = "Vizyon"),
            description = "Türkiye merkezli güçlü bir dijital ticaret altyapısından başlayıp, çok dilli ve çok pazarlı bir ekosistem kurmak.",
            icon = Icons.Outlined.Public
        )
    )
}

private fun aboutPlatformItems(): List<AboutPlatformItem> {
    return listOf(
        AboutPlatformItem(
            title = "Alıcılar",
            description = "Perakende Ürünleri, toptan ürünleri, tedarikçileri ve teklif akışlarını tek uygulamada keşfeder.",
            icon = Icons.Outlined.Storefront
        ),
        AboutPlatformItem(
            title = "Tedarikçiler ve Şirketler",
            description = "Firma profili, ürün Vitrinleri, belgeler ve RFQ akışlarıyla görünürlük kazanır.",
            icon = Icons.Outlined.Business
        ),
        AboutPlatformItem(
            title = BBLocalization.Current.Get(key = "ad7115b0-2a8e-4c7f-830b-4ebadec8f0c1", fallback = "Satıcılar"),
            description = "Perakende satış, sipariş, ödeme ve operasyon süreçlerini Bulbulustur altyapısı ile yönetir.",
            icon = Icons.Outlined.Verified
        ),
        AboutPlatformItem(
            title = "Geliştiriciler",
            description = "API, entegrasyon ve altyapı servisleriyle ticaret operasyonlarını genişletebilir.",
            icon = Icons.Outlined.Hub
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun AboutScreenPreview() {
    BbTheme {
        AboutScreen()
    }
}

