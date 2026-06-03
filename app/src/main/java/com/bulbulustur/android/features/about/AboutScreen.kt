package com.bulbulustur.android.features.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun AboutScreen(
    onInvestorClick: () -> Unit = {},
    onCareerClick: () -> Unit = {},
    onContactClick: () -> Unit = {},
    onRoadmapClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        item {
            AboutHeader()
        }

        item {
            AboutEcosystemCard()
        }

        item {
            BbSectionHeader(
                title = "Bulbulustur hakkında",
                subtitle = "Platformun hikayesi, amacı ve ticaret yaklaşımı"
            )
        }

        items(aboutStoryItems()) { item ->
            AboutStoryCard(
                item = item
            )
        }

        item {
            BbSectionHeader(
                title = "Ne yapıyoruz?",
                subtitle = "Toptan, perakende ve dijital ticaret altyapısını aynı omurgada topluyoruz"
            )
        }

        item {
            AboutCapabilityGrid()
        }

        item {
            BbSectionHeader(
                title = "Platform yönleri",
                subtitle = "Alıcı, satıcı, tedarikçi ve geliştirici akışları"
            )
        }

        items(aboutPlatformItems()) { item ->
            AboutPlatformCard(
                item = item
            )
        }

        item {
            BbSectionHeader(
                title = "Devam edin",
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
            Spacer(modifier = Modifier.height(BbSpacing.xl))
        }
    }
}

@Composable
private fun AboutHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Business,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Hakkımızda",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Biz kimiz?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bulbulustur; toptan tedarik, perakende satış ve dijital ticaret altyapısını aynı çatı altında birleştiren teknoloji odaklı bir ticaret ekosistemidir.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                BbChip(
                    text = "Toptan",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Perakende",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Dijital altyapı",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun AboutEcosystemCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Text(
                text = "Bulbulustur ekosistemi",
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.Primary,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Toptan tedarik, perakende satış ve dijital ticaret altyapısı tek çatı altında.",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                AboutTrustRow(
                    title = "Güvenli ticaret",
                    icon = Icons.Outlined.Verified
                )

                AboutTrustRow(
                    title = "Tedarikçi ve mağaza ağı",
                    icon = Icons.Outlined.Storefront
                )

                AboutTrustRow(
                    title = "Dijital ticaret altyapısı",
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.Primary
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun AboutCapabilityGrid() {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            AboutCapabilityCard(
                title = "Toptan",
                description = "Tedarikçi, RFQ ve toptan ürün keşfi.",
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
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
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.md),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Primary
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        AboutActionCard(
            title = "Yatırımcı ilişkileri",
            description = "Kurumsal bilgi ve yatırımcı iletişim alanına geç.",
            icon = Icons.Outlined.Business,
            onClick = onInvestorClick
        )

        AboutActionCard(
            title = "Kariyer",
            description = "Bulbulustur ekosisteminde açık roller ve ekip kültürü.",
            icon = Icons.Outlined.Groups,
            onClick = onCareerClick
        )

        AboutActionCard(
            title = "Yol haritası",
            description = "Platformun gelişim adımlarını incele.",
            icon = Icons.Outlined.RocketLaunch,
            onClick = onRoadmapClick
        )

        AboutActionCard(
            title = "İletişim",
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
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
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
                tint = BbColors.TextMuted
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
            title = "Bizim hikayemiz",
            description = "Bulbulustur, üretici, tedarikçi, mağaza ve alıcıları aynı dijital omurgada buluşturmak için geliştirilen bir ticaret altyapısıdır.",
            icon = Icons.Outlined.HistoryEdu
        ),
        AboutStoryItem(
            title = "Neler yapıyoruz?",
            description = "Toptan tedarik, perakende satış, RFQ, mağaza, marka, ödeme, kargo ve dijital site altyapılarını tek ekosistemde birleştiriyoruz.",
            icon = Icons.Outlined.Lightbulb
        ),
        AboutStoryItem(
            title = "Neye inanıyoruz?",
            description = "Ticaretin daha görünür, izlenebilir, erişilebilir ve dijital altyapılarla daha güçlü hale gelmesi gerektiğine inanıyoruz.",
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
            description = "Perakende ürünleri, toptan ürünleri, tedarikçileri ve teklif akışlarını tek uygulamada keşfeder.",
            icon = Icons.Outlined.Storefront
        ),
        AboutPlatformItem(
            title = "Tedarikçiler ve şirketler",
            description = "Firma profili, ürün vitrinleri, belgeler ve RFQ akışlarıyla görünürlük kazanır.",
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