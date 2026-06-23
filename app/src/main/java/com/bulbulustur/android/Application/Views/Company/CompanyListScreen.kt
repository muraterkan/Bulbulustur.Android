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
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun CompanyListScreen(
    onBackClick: () -> Unit = {},
    onCompanyClick: (Int) -> Unit = {},
    onProductListClick: (Int) -> Unit = {},
    onMessageClick: (Int) -> Unit = {},
    onRfqCreateClick: () -> Unit = {},
    onFilterClick: (String) -> Unit = {}
) {
    val companies = remember {
        getCompanyListItems()
    }

    var selectedFilter by remember {
        mutableStateOf("Tüm firmalar")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Firmalar",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.SectionGapCompact
            )
        ) {
            item {
                CompanyListHero(
                    onRfqCreateClick = onRfqCreateClick
                )
            }

            item {
                CompanyFilterChips(
                    selectedFilter = selectedFilter,
                    onFilterClick = { filterName ->
                        selectedFilter = filterName
                        onFilterClick(filterName)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Firma Listesi",
                    subtitle = "Ürünleri, firma yetkinliklerini ve güven bilgilerini karşılaştır."
                )
            }

            items(
                items = companies,
                key = { company ->
                    company.CompanyId
                }
            ) { company ->
                CompanyListCard(
                    company = company,
                    onCompanyClick = {
                        onCompanyClick(
                            company.CompanyId
                        )
                    },
                    onProductListClick = {
                        onProductListClick(
                            company.CompanyId
                        )
                    },
                    onMessageClick = {
                        onMessageClick(
                            company.CompanyId
                        )
                    }
                )
            }

            item {
                CompanyListBottomCallout(
                    onRfqCreateClick = onRfqCreateClick
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(
                        BBSpacing.Space4
                    )
                )
            }
        }
    }
}

@Composable
private fun CompanyListHero(
    onRfqCreateClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            CompanyIconTitleRow(
                icon = Icons.Outlined.Business,
                title = "Firmalar"
            )

            Text(
                text = "Güvenilir firmaları keşfedin",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bulbulustur ekosistemindeki üretici, tedarikçi ve şirket profillerini tek ekranda inceleyin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Talep oluştur",
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
private fun CompanyFilterChips(
    selectedFilter: String,
    onFilterClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.ChipGap
        ),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.ChipGap
        )
    ) {
        getCompanyFilterNames().forEach { filterName ->
            BbChip(
                text = filterName,
                selected = selectedFilter == filterName,
                onClick = {
                    onFilterClick(filterName)
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CompanyListCard(
    company: CompanyListItem,
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                CompanyLogoBox(
                    logoText = company.LogoText,
                    icon = company.Icon
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            BBSpacing.IconTextGapSmall
                        )
                    ) {
                        Text(
                            text = company.Name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        if (company.IsVerified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = "Doğrulanmış firma",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Text(
                        text = company.Description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "${company.Country} • ${company.City}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "Firma profilini aç",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.ChipGap
                ),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.ChipGap
                )
            ) {
                BbChip(
                    text = company.BusinessModel,
                    selected = false,
                    onClick = onCompanyClick
                )

                BbChip(
                    text = "${company.ProductCount} ürün",
                    selected = false,
                    onClick = onProductListClick
                )

                BbChip(
                    text = "Puan ${company.Rating}",
                    selected = false,
                    onClick = onCompanyClick
                )
            }

            CompanyInfoGrid(
                company = company
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.CardGapCompact
                )
            ) {
                CompanySmallActionCard(
                    title = "Ürünleri gör",
                    icon = Icons.Outlined.Inventory2,
                    modifier = Modifier.weight(1f),
                    onClick = onProductListClick
                )

                CompanySmallActionCard(
                    title = "İletişime geç",
                    icon = Icons.Outlined.Mail,
                    modifier = Modifier.weight(1f),
                    onClick = onMessageClick
                )
            }
        }
    }
}

@Composable
private fun CompanyLogoBox(
    logoText: String,
    icon: ImageVector
) {
    BbCard(
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
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
private fun CompanyInfoGrid(
    company: CompanyListItem
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.CardGapCompact
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGapCompact
            )
        ) {
            CompanyInfoBox(
                title = "Sıralama",
                value = company.Rating,
                modifier = Modifier.weight(1f)
            )

            CompanyInfoBox(
                title = "Ticaret sicil",
                value = company.TradeRegistryNumber,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGapCompact
            )
        ) {
            CompanyInfoBox(
                title = "Yetenekler",
                value = company.Capability,
                modifier = Modifier.weight(1f)
            )

            CompanyInfoBox(
                title = "İş modeli",
                value = company.BusinessModel,
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
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
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.IconTextGap
            )
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
private fun CompanyListBottomCallout(
    onRfqCreateClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large,
        onClick = onRfqCreateClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.RequestQuote,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = "Aradığınız firmayı bulamadınız mı?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Talep oluşturun, uygun firmalar size ulaşsın.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = "Talep oluştur",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.IconTextGap
        )
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

@Immutable
data class CompanyListItem(
    val CompanyId: Int,
    val Name: String,
    val Description: String,
    val Country: String,
    val City: String,
    val Rating: String,
    val TradeRegistryNumber: String,
    val Capability: String,
    val BusinessModel: String,
    val ProductCount: Int,
    val LogoText: String,
    val IsVerified: Boolean,
    val Icon: ImageVector
)

private fun getCompanyFilterNames(): List<String> {
    return listOf(
        "Tüm firmalar",
        "Doğrulanmış",
        "Üretici",
        "Toptancı",
        "İhracatçı",
        "İstanbul",
        "Ankara",
        "İzmir"
    )
}

private fun getCompanyListItems(): List<CompanyListItem> {
    return listOf(
        CompanyListItem(
            CompanyId = 1,
            Name = "Bulbulustur İnternet Teknolojileri ve Tic. A.Ş.",
            Description = "Yazılım, dijital dönüşüm ve ticaret altyapıları alanında çözüm sağlayan firma.",
            Country = "Türkiye",
            City = "İstanbul",
            Rating = "4,2",
            TradeRegistryNumber = "-",
            Capability = "Tasarım kaynaklı özelleştirme, yurt dışı mühendis hizmeti",
            BusinessModel = "Perakendeci, Toptancı",
            ProductCount = 12,
            LogoText = "BB",
            IsVerified = true,
            Icon = Icons.Outlined.Business
        ),
        CompanyListItem(
            CompanyId = 2,
            Name = "Anadolu Ambalaj Sanayi",
            Description = "Koli, kutu, poşet ve endüstriyel ambalaj ürünleri tedarikçisi.",
            Country = "Türkiye",
            City = "Kocaeli",
            Rating = "4,6",
            TradeRegistryNumber = "245981",
            Capability = "Özel üretim, baskılı ambalaj, hızlı sevkiyat",
            BusinessModel = "Üretici, Toptancı",
            ProductCount = 42,
            LogoText = "AA",
            IsVerified = true,
            Icon = Icons.Outlined.Factory
        ),
        CompanyListItem(
            CompanyId = 3,
            Name = "Marmara Endüstriyel Tedarik",
            Description = "Sanayi, depo, bakım ve üretim hattı sarf malzemeleri sağlar.",
            Country = "Türkiye",
            City = "Bursa",
            Rating = "4,1",
            TradeRegistryNumber = "118204",
            Capability = "Toplu tedarik, sözleşmeli satış, hızlı termin",
            BusinessModel = "Tedarikçi",
            ProductCount = 31,
            LogoText = "MET",
            IsVerified = false,
            Icon = Icons.Outlined.Inventory2
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CompanyListScreenPreview() {
    BbTheme {
        CompanyListScreen()
    }
}