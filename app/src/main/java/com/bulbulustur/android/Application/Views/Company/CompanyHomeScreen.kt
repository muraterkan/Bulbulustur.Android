package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ContactMail
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha

@Composable
fun CompanyHomeScreen(
    companyId: Int = 1,
    onBackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onProductsClick: () -> Unit = {},
    onContactClick: () -> Unit = {}
) {
    val company = remember(companyId) {
        getCompanyHome(companyId)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "aa4d6776-e3ee-4f98-aaec-846aaf364323", fallback = "Tedarikçi Ana Sayfası"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                CompanyHomeHero(
                    company = company,
                    onProductsClick = onProductsClick,
                    onProfileClick = onProfileClick,
                    onContactClick = onContactClick
                )
            }

            item {
                CompanyHomeShowcaseCard()
            }

            item {
                CompanyHomeTabs(
                    onProfileClick = onProfileClick,
                    onProductsClick = onProductsClick,
                    onContactClick = onContactClick
                )
            }

            item {
                CompanyHomeSectionCard(
                    title = "Ortobella Comfort Ürün Vitrinleri",
                    subtitle = "Firmanın öne çıkardığı özel ürün gruplarını ve toptan alıma uygun koleksiyonlarını Keşfedin.",
                    items = listOf(
                        BBLocalization.Current.Get(key = "eec14366-a992-4dad-89a1-0b04e54af995", fallback = "Yeni Ürünler"),
                        BBLocalization.Current.Get(key = "7bd92f2e-dbdb-42b1-a9f6-8e5211feb9a4", fallback = "Popüler Ürünler"),
                        "Kurumsal Alıma Uygun Ürünler"
                    ),
                    icon = Icons.Outlined.Inventory2,
                    onClick = onProductsClick
                )
            }

            item {
                CompanyHomeSectionCard(
                    title = "Şirket Vitrini",
                    subtitle = "Firmanın öne çıkardığı ürün gruplarını, özel koleksiyonlarını ve ticari Vitrinlerini inceleyin.",
                    items = listOf(
                        "Öne Çıkan Ürün Koleksiyonları",
                        "Kurumsal Tedarikçi Profili",
                        "Hızlı İletişim ve Teklif Süreci"
                    ),
                    icon = Icons.Outlined.Security,
                    onClick = onProfileClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@Composable
private fun CompanyHomeHero(
    company: CompanyHome,
    onProductsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onContactClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                CompanyLogoMark(
                    logoText = company.logoText
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        BbChip(
                            text = "Tedarikçi Mağazası",
                            selected = false,
                            onClick = {}
                        )

                        if (company.isVerified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(BBIcon.SizeSm)
                            )
                        }
                    }

                    Text(
                        text = company.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = company.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                company.chips.forEach { chip ->
                    BbChip(
                        text = chip,
                        selected = false,
                        onClick = {}
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(key = "5e09b1c8-93e6-4e9a-a055-2f556f57d6dc", fallback = "Ürünleri Gör"),
                    onClick = onProductsClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "ab200e4f-1f9e-45f4-90a6-7d5d21d33953", fallback = "Profil"),
                    onClick = onProfileClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium
                )
            }

            BbButton(
                text = BBLocalization.Current.Get(key = "a439130c-b2cf-496f-9868-93ef084d9aec", fallback = "İletişime Geç"),
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Secondary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun CompanyLogoMark(
    logoText: String
) {
    Surface(
        modifier = Modifier.size(72.dp),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(BBIcon.SizeLg)
            )

            Text(
                text = logoText,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CompanyHomeShowcaseCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Surface(
                    modifier = Modifier.size(42.dp),
                    shape = BBRadius.LgShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Security,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }

                Column {
                    Text(
                        text = "Şirket Vitrini",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Firma güveni, tedarik yapısı ve öne çıkan ticari bilgiler.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            CompanyHomeInfoLine("Öne Çıkan Ürün Koleksiyonları")
            CompanyHomeInfoLine("Kurumsal Tedarikçi Profili")
            CompanyHomeInfoLine("Hızlı İletişim ve Teklif Süreci")
        }
    }
}

@Composable
private fun CompanyHomeInfoLine(
    text: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(BBIcon.SizeMd)
                    .weight(BBAlpha.Overlay)
            )

            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun CompanyHomeTabs(
    onProfileClick: () -> Unit,
    onProductsClick: () -> Unit,
    onContactClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            CompanyHomeTabChip(
                text = BBLocalization.Current.Get(key = "fe9c56ac-dbc2-4fc6-afe0-bb3f7cf1f8f7", fallback = "Ana Sayfa"),
                icon = Icons.Outlined.Business,
                selected = true,
                onClick = {}
            )

            CompanyHomeTabChip(
                text = BBLocalization.Current.Get(key = "ab200e4f-1f9e-45f4-90a6-7d5d21d33953", fallback = "Profil"),
                icon = Icons.Outlined.Business,
                selected = false,
                onClick = onProfileClick
            )

            CompanyHomeTabChip(
                text = BBLocalization.Current.Get(key = "6cf7b92f-05e7-4ac7-be8c-ce98d8bf20c5", fallback = "Ürünler"),
                icon = Icons.Outlined.Inventory2,
                selected = false,
                onClick = onProductsClick
            )

            CompanyHomeTabChip(
                text = BBLocalization.Current.Get(key = "0cf2cda1-7cf6-4d8b-ab56-8918e3a260fd", fallback = ""),
                icon = Icons.Outlined.ContactMail,
                selected = false,
                onClick = onContactClick
            )
        }
    }
}

@Composable
private fun CompanyHomeTabChip(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = BBRadius.PillShape,
        color = if (selected) BBColors.Blue.Blue50 else MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) BBColors.Blue.Blue200 else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier.height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Spacer(modifier = Modifier.size(BBSpacing.Space2))

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) BBColors.Blue.Blue700 else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(BBSpacing.Space2))
        }
    }
}

@Composable
private fun CompanyHomeSectionCard(
    title: String,
    subtitle: String,
    items: List<String>,
    icon: ImageVector,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
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
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            items.forEach { item ->
                CompanyHomeInfoLine(text = item)
            }
        }
    }
}

@Immutable
private data class CompanyHome(
    val companyId: Int,
    val name: String,
    val logoText: String,
    val description: String,
    val isVerified: Boolean,
    val chips: List<String>
)

private fun getCompanyHome(
    companyId: Int
): CompanyHome {
    return CompanyHome(
        companyId = companyId,
        name = "Ortobella Comfort",
        logoText = "OC",
        description = "Çanta, tekstil ve promosyon ürünleri alanında çalışan doğrulanmış tedarikçi.",
        isVerified = true,
        chips = listOf(
            BBLocalization.Current.Get(key = "5365b492-6a1c-4b46-b5c0-b50cbfdd17a8", fallback = "Türkiye"),
            "Samsun",
            BBLocalization.Current.Get(key = "c6a0ff62-8828-475f-b553-37effb42efe6", fallback = "Doğrulanmış"),
            "Toptancı",
            "120+ Ürün"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CompanyHomeScreenPreview() {
    BbTheme {
        CompanyHomeScreen()
    }
}

