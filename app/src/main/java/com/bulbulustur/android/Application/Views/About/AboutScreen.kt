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
                title = "Hakkımızda",
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
                    title = "Ne Yapıyoruz?",
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
                    title = "Güvenli Ticaret",
                    icon = Icons.Outlined.Verified
                )

                AboutTrustRow(
                    title = "Tedarikçi ve MaĞaza AĞı",
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
                description = "MaĞaza, ürün ve sipariş deneyimi.",
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
            title = "Yatırımcı İlişkileri",
            description = "Kurumsal bilgi ve yatırımcı iletişim alanına geç.",
            icon = Icons.Outlined.Business,
            onClick = onInvestorClick
        )

        AboutActionCard(
            title = "Kariyer",
            description = "Bulbulustur ekosisteminde açık roller ve ekip kültürü.",
            icon = Icons.Outlined.Business,
            onClick = onCareerClick
        )

        AboutActionCard(
            title = "Yol Haritası",
            description = "Platformun gelişim adımlarını incele.",
            icon = Icons.Outlined.RocketLaunch,
            onClick = onRoadmapClick
        )

        AboutActionCard(
            title = "İletişim",
            description = "DoĞru ekibe hızlıca ulaş.",
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
            title = "Bizim Hikayemiz",
            description = "Bulbulustur, üretici, tedarikçi, maĞaza ve alıcıları aynı dijital omurgada buluşturmak için geliştirilen bir ticaret altyapısıdır.",
            icon = Icons.Outlined.HistoryEdu
        ),
        AboutStoryItem(
            title = "Neler Yapıyoruz?",
            description = "Toptan tedarik, perakende satış, RFQ, maĞaza, marka, ödeme, kargo ve dijital site altyapılarını tek ekosistemde birleştiriyoruz.",
            icon = Icons.Outlined.Lightbulb
        ),
        AboutStoryItem(
            title = "Neye İnanıyoruz?",
            description = "Ticaretin daha görünür, izlenebilir, erişilebilir ve dijital altyapılarla daha güçlü hale gelmesi gerektiĞine inanıyoruz.",
            icon = Icons.Outlined.AutoAwesome
        ),
        AboutStoryItem(
            title = "Vizyon",
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
            title = "Satıcılar",
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

