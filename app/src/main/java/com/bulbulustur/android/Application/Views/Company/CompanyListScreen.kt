package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
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
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO

@Composable
fun CompanyListScreen(
    companies: List<CompanyDTO> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onCompanyClick: (Int) -> Unit = {},
    onProductListClick: (Int) -> Unit = {},
    onMessageClick: (Int) -> Unit = {},
    onRfqCreateClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "a4d349d0-5340-4075-b2bb-1e900584f3b7", fallback = "Firmalar"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                CompanyListHero(onRfqCreateClick = onRfqCreateClick)
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(key = "eb3cd91b-fc17-480e-8113-4ec331f83352", fallback = "Firma Listesi"),
                    subtitle = BBLocalization.Current.Get(key = "eea989bd-387d-4f99-9000-bd297f22ed89", fallback = "Ürünleri, firma yetkinliklerini ve güven bilgilerini karşılaştır.")
                )
            }

            if (isLoading) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (!errorMessage.isNullOrBlank()) {
                item {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            items(
                items = companies,
                key = { company -> company.CompanyId }
            ) { company ->
                CompanyListCard(
                    company = company,
                    onCompanyClick = { onCompanyClick(company.CompanyId) },
                    onProductListClick = { onProductListClick(company.CompanyId) },
                    onMessageClick = { onMessageClick(company.CompanyId) }
                )
            }

            item {
                CompanyListBottomCallout(onRfqCreateClick = onRfqCreateClick)
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@Composable
private fun CompanyListHero(onRfqCreateClick: () -> Unit) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            CompanyIconTitleRow(
                icon = Icons.Outlined.Business,
                title = BBLocalization.Current.Get(key = "a4d349d0-5340-4075-b2bb-1e900584f3b7", fallback = "Firmalar")
            )

            Text(
                text = BBLocalization.Current.Get(key = "3a94e44b-9ffc-48c3-89b9-96442c086013", fallback = "Güvenilir Firmaları Keşfedin"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "aef4747b-5201-4412-979f-feaab62c5bee", fallback = "Bulbulustur ekosistemindeki üretici, tedarikçi ve şirket profillerini tek ekranda inceleyin."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "fcb264e8-a984-415f-b971-69ea0a531bd9", fallback = "Talep oluştur"),
                onClick = onRfqCreateClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.RequestQuote,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CompanyListCard(
    company: CompanyDTO,
    onCompanyClick: () -> Unit,
    onProductListClick: () -> Unit,
    onMessageClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onCompanyClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                CompanyLogoBox(
                    logoText = getCompanyInitials(company.CompanyName),
                    icon = Icons.Outlined.Business
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.IconTextGapSmall)
                    ) {
                        Text(
                            text = company.CompanyName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        if (company.Verified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = BBLocalization.Current.Get(key = "c00be3e3-90d4-4f66-ac51-db9a38bac686", fallback = "Doğrulanmış firma"),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Text(
                        text = company.Slogan.ifBlank { company.SeoDescription.ifBlank { "-" } },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "${company.CountryName} • ${company.CityName}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = BBLocalization.Current.Get(key = "6a210c63-bf1c-4c78-829d-176e3c6f73be", fallback = "Firma profilini aç"),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
            ) {
                BbChip(
                    text = company.BusinessTypes.ifBlank { company.CompanyType.ifBlank { "-" } },
                    selected = false,
                    onClick = onCompanyClick
                )

                BbChip(
                    text = "${company.CompanyProducts.size} ürün",
                    selected = false,
                    onClick = onProductListClick
                )

                BbChip(
                    text = "Puan ${company.Rating.ifBlank { "0.0" }}",
                    selected = false,
                    onClick = onCompanyClick
                )
            }

            CompanyInfoGrid(company = company)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
            ) {
                CompanySmallActionCard(
                    title = BBLocalization.Current.Get(key = "6cf7b92f-05e7-4ac7-be8c-ce98d8bf20c5", fallback = "Ürünler"),
                    icon = Icons.Outlined.Inventory2,
                    modifier = Modifier.weight(1f),
                    onClick = onProductListClick
                )

                CompanySmallActionCard(
                    title = BBLocalization.Current.Get(key = "a439130c-b2cf-496f-9868-93ef084d9aec", fallback = "İletişime geç"),
                    icon = Icons.Outlined.Mail,
                    modifier = Modifier.weight(1f),
                    onClick = onMessageClick
                )
            }
        }
    }
}

@Composable
private fun CompanyLogoBox(logoText: String, icon: ImageVector) {
    BbCard(
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = logoText,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CompanyInfoGrid(company: CompanyDTO) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
        ) {
            CompanyInfoBox(
                title = BBLocalization.Current.Get(key = "e7adcad3-f164-48d5-bc4d-7d4bc7cb6956", fallback = "Sıralama"),
                value = company.Rating.ifBlank { "0.0" },
                modifier = Modifier.weight(1f)
            )

            CompanyInfoBox(
                title = BBLocalization.Current.Get(key = "6c84ad65-9bba-4795-a376-efebd24c842f", fallback = "Ticaret sicil"),
                value = company.TradeRegisterNumber.ifBlank { "-" },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
        ) {
            CompanyInfoBox(
                title = BBLocalization.Current.Get(key = "4f1ad3c8-8df8-48f2-b2c2-7b128d0f0e28", fallback = "Yetenekler"),
                value = company.CompanyCapabilities.ifBlank { "-" },
                modifier = Modifier.weight(1f)
            )

            CompanyInfoBox(
                title = BBLocalization.Current.Get(key = "557dd35f-dc39-4228-a30f-72adb3b29d32", fallback = "İş modeli"),
                value = company.BusinessTypes.ifBlank { company.CompanyType.ifBlank { "-" } },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun CompanyInfoBox(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CompanySmallActionCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CompanyListBottomCallout(onRfqCreateClick: () -> Unit) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large,
        onClick = onRfqCreateClick
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
                    text = BBLocalization.Current.Get(key = "360424c5-f619-4309-a1db-0660afc515ef", fallback = "Aradığınız firmayı bulamadınız mı?"),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "33ab398d-8105-4689-b221-8e9ae1b7d800", fallback = "Talep oluşturun, uygun firmalar size ulaşsın."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = BBLocalization.Current.Get(key = "fcb264e8-a984-415f-b971-69ea0a531bd9", fallback = "Talep oluştur"),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CompanyIconTitleRow(icon: ImageVector, title: String) {
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
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

private fun getCompanyInitials(companyName: String): String {
    return companyName
        .trim()
        .split(Regex("\\s+"))
        .filter { it.isNotBlank() }
        .take(2)
        .mapNotNull { it.firstOrNull()?.uppercase() }
        .joinToString("")
        .ifBlank { "C" }
}

@Preview(showBackground = true)
@Composable
private fun CompanyListScreenPreview() {
    BbTheme {
        CompanyListScreen()
    }
}