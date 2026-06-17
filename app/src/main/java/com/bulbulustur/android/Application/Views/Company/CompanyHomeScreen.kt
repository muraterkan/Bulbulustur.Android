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
        containerColor = BBColors.SurfaceSoft,
        topBar = {
            BbInnerPageHeader(
                title = "Tedarikçi Ana Sayfası",
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
                    subtitle = "Firmanın öne çıkardığı özel ürün gruplarını ve toptan alıma uygun koleksiyonlarını keşfedin.",
                    items = listOf(
                        "Yeni Ürünler",
                        "Popüler Ürünler",
                        "Kurumsal Alıma Uygun Ürünler"
                    ),
                    icon = Icons.Outlined.Inventory2,
                    onClick = onProductsClick
                )
            }

            item {
                CompanyHomeSectionCard(
                    title = "�?irket Vitrini",
                    subtitle = "Firmanın öne çıkardığı ürün gruplarını, özel koleksiyonlarını ve ticari vitrinlerini inceleyin.",
                    items = listOf(
                        "Öne Çıkan Ürün Koleksiyonları",
                        "Kurumsal Tedarikçi Profili",
                        "Hızlı İletişim Ve Teklif Süreci"
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
                                tint = BBColors.Primary,
                                modifier = Modifier.size(BBIcon.SizeSm)
                            )
                        }
                    }

                    Text(
                        text = company.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = company.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BBColors.TextMuted
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
                    text = "Ürünleri Gör",
                    onClick = onProductsClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "Profil",
                    onClick = onProfileClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium
                )
            }

            BbButton(
                text = "İletişime Geç",
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
        color = BBColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = BBColors.Primary,
                modifier = Modifier.size(BBIcon.SizeLg)
            )

            Text(
                text = logoText,
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.TextStrong,
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
                    color = BBColors.Primary
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Security,
                        contentDescription = null,
                        tint = BBColors.TextStrong,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }

                Column {
                    Text(
                        text = "�?irket Vitrini",
                        style = MaterialTheme.typography.titleMedium,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Firma güveni, tedarik yapısı ve öne çıkan ticari bilgiler.",
                        style = MaterialTheme.typography.bodySmall,
                        color = BBColors.TextMuted
                    )
                }
            }

            CompanyHomeInfoLine("Öne Çıkan Ürün Koleksiyonları")
            CompanyHomeInfoLine("Kurumsal Tedarikçi Profili")
            CompanyHomeInfoLine("Hızlı İletişim Ve Teklif Süreci")
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
        color = BBColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
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
                tint = BBColors.Primary,
                modifier = Modifier
                    .size(BBIcon.SizeMd)
                    .weight(BBAlpha.Overlay)
            )

            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextStrong,
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
                text = "Ana Sayfa",
                icon = Icons.Outlined.Business,
                selected = true,
                onClick = {}
            )

            CompanyHomeTabChip(
                text = "Profil",
                icon = Icons.Outlined.Business,
                selected = false,
                onClick = onProfileClick
            )

            CompanyHomeTabChip(
                text = "Ürünler",
                icon = Icons.Outlined.Inventory2,
                selected = false,
                onClick = onProductsClick
            )

            CompanyHomeTabChip(
                text = "İletişim",
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
        color = if (selected) BBColors.Blue.Blue50 else BBColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) BBColors.Blue.Blue200 else BBColors.Border
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
                tint = if (selected) BBColors.Blue.Blue700 else BBColors.TextStrong,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = BBColors.TextStrong,
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
                    tint = BBColors.Primary
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = BBColors.TextMuted
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
            "Türkiye",
            "Samsun",
            "Doğrulanmış",
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
