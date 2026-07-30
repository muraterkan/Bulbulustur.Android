package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.bulbulustur.android.R
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha

@Composable
fun CompanyDetailScreen(
    companyId: Int = 1,
    onBackClick: () -> Unit = {},
    onHomeClick: (Int) -> Unit = {},
    onProductListClick: (Int) -> Unit = {},
    onMessageClick: (Int) -> Unit = {},
    onContactClick: (Int) -> Unit = onMessageClick,
    onGalleryClick: (Int) -> Unit = {},
    onCertificateClick: (Int) -> Unit = {},
    onWebsiteClick: (String) -> Unit = {}
) {
    val company = remember(companyId) {
        getCompanyDetail(companyId)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Firma Profili",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                CompanyDetailHero(
                    company = company
                )
            }

            item {
                CompanyDetailMainActions(
                    company = company,
                    onProductListClick = onProductListClick,
                    onContactClick = onContactClick
                )
            }

            item {
                CompanyDetailTabs(
                    company = company,
                    onHomeClick = onHomeClick,
                    onProductListClick = onProductListClick,
                    onContactClick = onContactClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Firma Vitrini",
                    subtitle = "Üretim, tesis, showroom veya kurumsal görseller"
                )
            }

            item {
                CompanyGalleryPreview(
                    company = company,
                    onGalleryClick = {
                        onGalleryClick(company.companyId)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Detaylı Firma Profili",
                    subtitle = "Firma hakkında, iş modeli ve ticari bilgiler"
                )
            }

            item {
                CompanyAboutCard(
                    company = company
                )
            }

            item {
                CompanyInfoGrid(
                    company = company,
                    onWebsiteClick = onWebsiteClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Ürün Grupları",
                    subtitle = "Firmanın öne çıkan toptan ürün aileleri"
                )
            }

            items(
                items = company.productGroups,
                key = { productGroup ->
                    "product-group-${productGroup.productGroupId}"
                }
            ) { productGroup ->
                CompanyProductGroupCard(
                    productGroup = productGroup,
                    onClick = {
                        onProductListClick(company.companyId)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Belgeler ve Sertifikalar",
                    subtitle = "Firma güveni için doğrulama bilgileri"
                )
            }

            items(
                items = company.certificates,
                key = { certificate ->
                    "certificate-${certificate.certificateId}"
                }
            ) { certificate ->
                CompanyCertificateCard(
                    certificate = certificate,
                    onClick = {
                        onCertificateClick(certificate.certificateId)
                    }
                )
            }

            item {
                CompanyTrustPanel(
                    company = company,
                    onContactClick = {
                        onContactClick(company.companyId)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@Composable
private fun CompanyDetailHero(
    company: CompanyDetail
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.onSurface,
        border = BorderStroke(
            width = BBSpacing.None,
            color = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.onSurface,
                            BBColors.Black
                        )
                    )
                )
                .padding(BBSpacing.Space5)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    CompanyLogoBox()

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(BBSpacing.IconTextGapSmall)
                        ) {
                            Text(
                                text = "Firma Profili",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )

                            if (company.isVerified) {
                                Icon(
                                    imageVector = Icons.Outlined.Verified,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Text(
                            text = company.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = BBColors.White
                        )
                    }
                }

                Text(
                    text = company.shortDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BBColors.White.copy(alpha = 0.78f)
                )

                CompanyDarkTagRow(
                    tags = buildList {
                        add(company.country)
                        add(company.city)
                        add(company.businessModel)

                        if (company.isVerified) {
                            add("Doğrulanmış")
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun CompanyLogoBox() {
    Surface(
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.None,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.company_logo_nexa
            ),
            contentDescription = "Company Logo",
            modifier = Modifier
                .padding(BBSpacing.Space2)
                .height(BBSpacing.Space12)
        )
    }
}

@Composable
private fun CompanyDetailMainActions(
    company: CompanyDetail,
    onProductListClick: (Int) -> Unit,
    onContactClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
        ) {
            CompanyActionCard(
                title = "Ürünleri Gör",
                description = "${company.productCount} ürün grubu",
                icon = Icons.Outlined.Inventory2,
                modifier = Modifier.weight(1f),
                onClick = {
                    onProductListClick(company.companyId)
                }
            )

            CompanyActionCard(
                title = "İletişime Geç",
                description = "Yetkili kişiye ulaş",
                icon = Icons.Outlined.Mail,
                modifier = Modifier.weight(1f),
                onClick = {
                    onContactClick(company.companyId)
                }
            )
        }

        BbButton(
            text = "İletişime Geç",
            onClick = {
                onContactClick(company.companyId)
            },
            modifier = Modifier.fillMaxWidth(),
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Medium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@Composable
private fun CompanyDetailTabs(
    company: CompanyDetail,
    onHomeClick: (Int) -> Unit,
    onProductListClick: (Int) -> Unit,
    onContactClick: (Int) -> Unit
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
            CompanyTabChip(
                text = "Ana Sayfa",
                icon = Icons.Outlined.Home,
                selected = false,
                onClick = {
                    onHomeClick(company.companyId)
                }
            )

            CompanyTabChip(
                text = "Profil",
                icon = Icons.Outlined.Business,
                selected = true,
                onClick = {}
            )

            CompanyTabChip(
                text = "Ürünler",
                icon = Icons.Outlined.Inventory2,
                selected = false,
                onClick = {
                    onProductListClick(company.companyId)
                }
            )

            CompanyTabChip(
                text = BBLocalization.Current.Get(key = "0cf2cda1-7cf6-4d8b-ab56-8918e3a260fd", fallback = ""),
                icon = Icons.Outlined.Email,
                selected = false,
                onClick = {
                    onContactClick(company.companyId)
                }
            )
        }
    }
}

@Composable
private fun CompanyTabChip(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clip(BBRadius.PillShape),
        onClick = onClick,
        shape = BBRadius.PillShape,
        color = if (selected) BBColors.Blue.Blue50 else MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.None,
            color = if (selected) BBColors.Blue.Blue200 else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) BBColors.Blue.Blue700 else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.height(BBIcon.SizeSm)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CompanyActionCard(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

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
    }
}

@Composable
private fun CompanyGalleryPreview(
    company: CompanyDetail,
    onGalleryClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onGalleryClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            CompanyIconTitleRow(
                icon = Icons.Outlined.PhotoLibrary,
                title = "${company.galleryImageCount} görsel"
            )

            Text(
                text = "Firma Vitrini API sonrası gerçek görsellerle yatay galeri olarak beslenecek.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            CompanyTagRow(
                tags = company.galleryTags
            )
        }
    }
}

@Composable
private fun CompanyAboutCard(
    company: CompanyDetail
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = "Şirket Hakkında",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = company.about,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = BBLocalization.Current.Get(key = "34ad3e1b-96a7-4933-83c5-88b55d3cee6e", fallback = ""),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = company.whyUs,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CompanyInfoGrid(
    company: CompanyDetail,
    onWebsiteClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
        ) {
            CompanyInfoCard(
                title = BBLocalization.Current.Get(key = "2439777a-0431-4929-9600-07df5586ad67", fallback = ""),
                value = company.foundationYear,
                icon = Icons.Outlined.Factory,
                modifier = Modifier.weight(1f)
            )

            CompanyInfoCard(
                title = "Çalışan",
                value = company.employeeCount,
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
        ) {
            CompanyInfoCard(
                title = BBLocalization.Current.Get(key = "481f21a7-ac4c-4135-8b49-d3e022095f71", fallback = ""),
                value = company.exportMarkets,
                icon = Icons.Outlined.Language,
                modifier = Modifier.weight(1f)
            )

            CompanyInfoCard(
                title = "Adres",
                value = company.addressSummary,
                icon = Icons.Outlined.LocationOn,
                modifier = Modifier.weight(1f)
            )
        }

        CompanyInfoCard(
            title = "Web Sitesi",
            value = company.website,
            icon = Icons.Outlined.Home,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onWebsiteClick(company.website)
            }
        )
    }
}

@Composable
private fun CompanyInfoCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CompanyProductGroupCard(
    productGroup: CompanyProductGroup,
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
                imageVector = productGroup.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = productGroup.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = productGroup.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${productGroup.productCount} ürün",
                    style = MaterialTheme.typography.labelMedium,
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

@Composable
private fun CompanyCertificateCard(
    certificate: CompanyCertificate,
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
                imageVector = Icons.Outlined.RequestQuote,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = certificate.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = certificate.description,
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

@Composable
private fun CompanyTrustPanel(
    company: CompanyDetail,
    onContactClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large,
        onClick = onContactClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            CompanyIconTitleRow(
                icon = Icons.Outlined.Security,
                title = "Firma Güveni"
            )

            Text(
                text = "Firma profili, ürünleri ve belgeleri üzerinden firmayı daha yakından değerlendirin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            CompanyTagRow(
                tags = company.trustTags
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.IconTextGap)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "İletişime Geç",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun CompanyIconTitleRow(
    icon: ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.IconTextGap)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CompanyTagRow(
    tags: List<String>
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
    ) {
        tags.forEach { tag ->
            BbChip(
                text = tag,
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
private fun CompanyDarkTagRow(
    tags: List<String>
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
    ) {
        tags.forEach { tag ->
            Surface(
                shape = BBRadius.PillShape,
                color = BBColors.White.copy(alpha = BBAlpha.Overlay),
                border = BorderStroke(
                    width = BBSpacing.None,
                    color = BBColors.White.copy(alpha = BBAlpha.OverlayStrong)
                )
            ) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.Space3,
                        vertical = BBSpacing.Space2
                    ),
                    text = tag,
                    style = MaterialTheme.typography.labelMedium,
                    color = BBColors.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

data class CompanyDetail(
    val companyId: Int,
    val name: String,
    val shortDescription: String,
    val about: String,
    val whyUs: String,
    val country: String,
    val city: String,
    val businessModel: String,
    val productCount: Int,
    val foundationYear: String,
    val employeeCount: String,
    val exportMarkets: String,
    val addressSummary: String,
    val website: String,
    val galleryImageCount: Int,
    val isVerified: Boolean,
    val galleryTags: List<String>,
    val trustTags: List<String>,
    val productGroups: List<CompanyProductGroup>,
    val certificates: List<CompanyCertificate>
)

data class CompanyProductGroup(
    val productGroupId: Int,
    val name: String,
    val description: String,
    val productCount: Int,
    val icon: ImageVector
)

data class CompanyCertificate(
    val certificateId: Int,
    val name: String,
    val description: String
)

private fun getCompanyDetail(companyId: Int): CompanyDetail {
    return CompanyDetail(
        companyId = companyId,
        name = "Bulbulustur İnternet Teknolojileri ve Tic. A.Ş.",
        shortDescription = "Yazılım, dijital dönüşüm ve ticaret altyapıları alanında çözüm sağlayan firma.",
        about = "Firma; üretim, tedarik, satış ve e-ticaret operasyonlarını dijital altyapılarla güçlendiren çözümler geliştirir. Mobil uygulamada bu alan ileride gerçek firma açıklaması, yetenekler ve ticari profil bilgileriyle beslenecek.",
        whyUs = "Firma profili, ürünleri, belgeleri ve iletişim bilgileri tek ekranda sunularak alıcının daha hızlı karar vermesine yardımcı olur.",
        country = "Türkiye",
        city = "İstanbul",
        businessModel = "Perakendeci, Toptancı",
        productCount = 12,
        foundationYear = "2025",
        employeeCount = "1-5 kişi",
        exportMarkets = "Southern Africa, South America",
        addressSummary = "İçerenköy / İstanbul",
        website = "www.bulbulustur.com",
        galleryImageCount = 11,
        isVerified = true,
        galleryTags = listOf(
            "Ofis",
            "Showroom",
            "Üretim",
            "Galeri"
        ),
        trustTags = listOf(
            "Firma Bilgileri",
            "Ürün Portföyü",
            "Sertifika",
            "Mesaj"
        ),
        productGroups = getCompanyProductGroups(),
        certificates = getCompanyCertificates()
    )
}

private fun getCompanyProductGroups(): List<CompanyProductGroup> {
    return listOf(
        CompanyProductGroup(
            productGroupId = 1,
            name = "Philips Antifreeze",
            description = "Endüstriyel ürün grubu için dummy ürün Vitrini.",
            productCount = 4,
            icon = Icons.Outlined.Inventory2
        ),
        CompanyProductGroup(
            productGroupId = 2,
            name = "Tovolo Mikrodalga Ürünleri",
            description = "Toptan ürün ailesi örnek gösterimi.",
            productCount = 6,
            icon = Icons.Outlined.Storefront
        ),
        CompanyProductGroup(
            productGroupId = 3,
            name = "Elektrolux Ekipman",
            description = "Firma profilinde listelenecek ürün grubu.",
            productCount = 2,
            icon = Icons.Outlined.Factory
        )
    )
}

private fun getCompanyCertificates(): List<CompanyCertificate> {
    return listOf(
        CompanyCertificate(
            certificateId = 1,
            name = "ISO 9001",
            description = "Kalite yönetim sistemi sertifikası."
        ),
        CompanyCertificate(
            certificateId = 2,
            name = "ISO 14001",
            description = "Çevre yönetim sistemi sertifikası."
        ),
        CompanyCertificate(
            certificateId = 3,
            name = "ISO 45001",
            description = "İş sağlığı ve güvenliği yönetim sistemi sertifikası."
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CompanyDetailScreenPreview() {
    BbTheme {
        CompanyDetailScreen()
    }
}

