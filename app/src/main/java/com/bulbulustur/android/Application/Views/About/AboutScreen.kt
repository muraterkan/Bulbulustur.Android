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
                    title = BBLocalization.Current.Get(key = "c08b2887-d9b2-4671-b9ad-8e59f296b7c1", fallback = "Bulbulustur Hakkında"),
                    subtitle = BBLocalization.Current.Get(key = "be6a58d7-0e72-4152-8149-bfed9b34d70f", fallback = "Platformun hikayesi, amacı ve ticaret yaklaşımı")
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
                    subtitle = BBLocalization.Current.Get(key = "c844897e-56f7-47c7-889d-88477ee78133", fallback = "Toptan, perakende ve dijital ticaret altyapısını aynı omurgada topluyoruz")
                )
            }

            item {
                AboutCapabilityGrid()
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(key = "91e62b3b-1eab-4cee-adc5-3d2a59c507b0", fallback = "Platform Yönleri"),
                    subtitle = BBLocalization.Current.Get(key = "4dc7b679-009a-434d-b02a-85bff5423c11", fallback = "Alıcı, satıcı, tedarikçi ve geliştirici akışları")
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
                    title = BBLocalization.Current.Get(key = "671d4eda-fc74-4a28-a7d8-e9742907ccd7", fallback = "Devam Edin"),
                    subtitle = BBLocalization.Current.Get(key = "31104221-83bd-4463-8cd6-9a2f515b042b", fallback = "Kurumsal sayfalara hızlı geçiş")
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
            text = BBLocalization.Current.Get(key = "1141c100-94e5-463d-9651-8c4b6129bb86", fallback = "Bulbulustur; toptan tedarik, perakende satış ve dijital ticaret altyapısını aynı çatı altında birleştiren teknoloji odaklı bir ticaret ekosistemidir."),
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
                text = BBLocalization.Current.Get(key = "7d5d1683-30c3-4132-8b20-d1752168a4b3", fallback = "Bulbulustur Ekosistemi"),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = BBLocalization.Current.Get(key = "afc3cb3f-6a52-42bb-8d83-ccd9c26628bf", fallback = "Toptan tedarik, perakende satış ve dijital ticaret altyapısı tek çatı altında."),
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
                    title = BBLocalization.Current.Get(key = "9f45decf-04e1-47c9-a23f-c4b26a4935f2", fallback = "Tedarikçi ve Mağaza Ağı"),
                    icon = Icons.Outlined.Storefront
                )

                AboutTrustRow(
                    title = BBLocalization.Current.Get(key = "46b9b4a0-fa02-4bdc-8d82-998650ee7614", fallback = "Dijital Ticaret Altyapısı"),
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
                title = BBLocalization.Current.Get(key = "cd90e72e-8745-4543-836b-ca914c3640f8", fallback = "Toptan"),
                description = BBLocalization.Current.Get(key = "b478963a-972c-4cee-b575-a099f8fe076f", fallback = "Tedarikçi, RFQ ve toptan ürün Keşfi."),
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f)
            )

            AboutCapabilityCard(
                title = BBLocalization.Current.Get(key = "f98fc2e2-635a-4404-831f-4c1fdca3885e", fallback = "Perakende"),
                description = BBLocalization.Current.Get(key = "276e3beb-f3e8-467c-a23f-f2313496d669", fallback = "Mağaza, ürün ve sipariş deneyimi."),
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
                description = BBLocalization.Current.Get(key = "ccfdd98f-562d-46f1-9d6f-7cee976c2352", fallback = "Dijital ticaret sitesi altyapısı."),
                icon = Icons.Outlined.RocketLaunch,
                modifier = Modifier.weight(1f)
            )

            AboutCapabilityCard(
                title = BBLocalization.Current.Get(key = "2bd2ebe9-4ec6-4cdd-8f90-7507fc03e819", fallback = "Global"),
                description = BBLocalization.Current.Get(key = "071eb5cd-2731-403f-b468-815e89ad079f", fallback = "Çok dilli ticaret omurgası."),
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
            description = BBLocalization.Current.Get(key = "9a7d2613-36dc-4b0b-a1b6-ef6d0faa57cf", fallback = "Kurumsal bilgi ve yatırımcı iletişim alanına geç."),
            icon = Icons.Outlined.Business,
            onClick = onInvestorClick
        )

        AboutActionCard(
            title = BBLocalization.Current.Get(key = "d0402efd-1de4-4014-a781-a12d16a2f43d", fallback = "Kariyer"),
            description = BBLocalization.Current.Get(key = "f550d558-0ce9-40bb-83d2-70571606e343", fallback = "Bulbulustur ekosisteminde açık roller ve ekip kültürü."),
            icon = Icons.Outlined.Business,
            onClick = onCareerClick
        )

        AboutActionCard(
            title = BBLocalization.Current.Get(key = "9e514dba-077f-4b79-95ce-5f2743895544", fallback = "Yol Haritası"),
            description = BBLocalization.Current.Get(key = "ec286261-463b-4b1f-8d77-84eb31223afd", fallback = "Platformun gelişim adımlarını incele."),
            icon = Icons.Outlined.RocketLaunch,
            onClick = onRoadmapClick
        )

        AboutActionCard(
            title = BBLocalization.Current.Get(key = "0cf2cda1-7cf6-4d8b-ab56-8918e3a260fd", fallback = ""),
            description = BBLocalization.Current.Get(key = "440c7c15-cc18-439f-8c72-489c57a58003", fallback = "Doğru ekibe hızlıca ulaş."),
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
            description = BBLocalization.Current.Get(key = "c7281430-f7e9-40a8-8c42-87063ecbbfdc", fallback = "Bulbulustur, üretici, tedarikçi, mağaza ve alıcıları aynı dijital omurgada buluşturmak için geliştirilen bir ticaret altyapısıdır."),
            icon = Icons.Outlined.HistoryEdu
        ),
        AboutStoryItem(
            title = BBLocalization.Current.Get(key = "0c4c1853-cf4b-4a2a-b718-12c4cfb0b4d1", fallback = "Neler Yapıyoruz?"),
            description = BBLocalization.Current.Get(key = "e864b08a-4e3a-41c6-8c18-c3e5a0d0c690", fallback = "Toptan tedarik, perakende satış, RFQ, mağaza, marka, ödeme, kargo ve dijital site altyapılarını tek ekosistemde birleştiriyoruz."),
            icon = Icons.Outlined.Lightbulb
        ),
        AboutStoryItem(
            title = BBLocalization.Current.Get(key = "8aa642d5-8035-4bb3-99fc-eb8d685e4692", fallback = "Neye İnanıyoruz?"),
            description = BBLocalization.Current.Get(key = "17332f9d-3a55-4370-bb9e-6f75622deda7", fallback = "Ticaretin daha görünür, izlenebilir, erişilebilir ve dijital altyapılarla daha güçlü hale gelmesi gerektiğine inanıyoruz."),
            icon = Icons.Outlined.AutoAwesome
        ),
        AboutStoryItem(
            title = BBLocalization.Current.Get(key = "83e55f8c-1284-4ef8-a364-13aa257fee84", fallback = "Vizyon"),
            description = BBLocalization.Current.Get(key = "2ae9d3d8-4328-4ed5-a2bb-58b4e0de8a9a", fallback = "Türkiye merkezli güçlü bir dijital ticaret altyapısından başlayıp, çok dilli ve çok pazarlı bir ekosistem kurmak."),
            icon = Icons.Outlined.Public
        )
    )
}

private fun aboutPlatformItems(): List<AboutPlatformItem> {
    return listOf(
        AboutPlatformItem(
            title = BBLocalization.Current.Get(key = "3fcecde8-4f88-4416-b2ba-857047515ed8", fallback = "Alıcılar"),
            description = BBLocalization.Current.Get(key = "f6a36c39-eb9d-4e15-8c56-0d141a148365", fallback = "Perakende Ürünleri, toptan ürünleri, tedarikçileri ve teklif akışlarını tek uygulamada keşfeder."),
            icon = Icons.Outlined.Storefront
        ),
        AboutPlatformItem(
            title = BBLocalization.Current.Get(key = "4dac38b6-9b9f-4ec9-aab2-7f1b419ff0bc", fallback = "Tedarikçiler ve Şirketler"),
            description = BBLocalization.Current.Get(key = "025119c5-b883-46da-b612-0d6fc463c99b", fallback = "Firma profili, ürün Vitrinleri, belgeler ve RFQ akışlarıyla görünürlük kazanır."),
            icon = Icons.Outlined.Business
        ),
        AboutPlatformItem(
            title = BBLocalization.Current.Get(key = "ad7115b0-2a8e-4c7f-830b-4ebadec8f0c1", fallback = "Satıcılar"),
            description = BBLocalization.Current.Get(key = "de6d9dc1-3882-4b99-b82a-cb91a5fbee56", fallback = "Perakende satış, sipariş, ödeme ve operasyon süreçlerini Bulbulustur altyapısı ile yönetir."),
            icon = Icons.Outlined.Verified
        ),
        AboutPlatformItem(
            title = BBLocalization.Current.Get(key = "43575c4e-b70e-4f95-95cd-2d2040a108ab", fallback = "Geliştiriciler"),
            description = BBLocalization.Current.Get(key = "cd095035-6ee4-4b5f-ab77-01a97d75f2a0", fallback = "API, entegrasyon ve altyapı servisleriyle ticaret operasyonlarını genişletebilir."),
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

