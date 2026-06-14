package com.bulbulustur.android.Features.company

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbChip
import com.bulbulustur.android.Ui.components.BbSectionHeader
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTheme

@Composable
fun CompanyListScreen(
    onCompanyClick: (Int) -> Unit = {},
    onProductListClick: (Int) -> Unit = {},
    onMessageClick: (Int) -> Unit = {},
    onRfqCreateClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val companies = remember {
        getCompanyListItems()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGapCompact)
        ) {
            item {
                CompanyListHero(
                    onRfqCreateClick = onRfqCreateClick
                )
            }

            item {

            }

            item {
                CompanyFilterChips()
            }

            item {
                BbSectionHeader(
                    title = "Firma listesi",
                    subtitle = "Ürünleri, firma yetkinliklerini ve güven bilgilerini karşılaştır"
                )
            }

            items(
                items = companies,
                key = { company ->
                    company.companyId
                }
            ) { company ->
                CompanyListCard(
                    company = company,
                    onCompanyClick = {
                        onCompanyClick(company.companyId)
                    },
                    onProductListClick = {
                        onProductListClick(company.companyId)
                    },
                    onMessageClick = {
                        onMessageClick(company.companyId)
                    }
                )
            }

            item {
                CompanyListBottomCallout(
                    onRfqCreateClick = onRfqCreateClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(BbSpacing.Space4))
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
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
private fun CompanyFilterChips() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
    ) {
        getCompanyFilterNames().forEach { filterName ->
            BbChip(
                text = filterName,
                selected = false,
                onClick = {}
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                CompanyLogoBox(
                    logoText = company.logoText,
                    icon = company.icon
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGapSmall)
                    ) {
                        Text(
                            text = company.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
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
                        text = company.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "${company.country} • ${company.city}",
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

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
            ) {
                BbChip(
                    text = company.businessModel,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "${company.productCount} ürün",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Puan ${company.rating}",
                    selected = false,
                    onClick = {}
                )
            }

            CompanyInfoGrid(
                company = company
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
            ) {
                CompanySmallActionCard(
                    title = "Ürünleri Gör",
                    icon = Icons.Outlined.Inventory2,
                    modifier = Modifier.weight(1f),
                    onClick = onProductListClick
                )

                CompanySmallActionCard(
                    title = "İletişime Geç",
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
        ) {
            CompanyInfoBox(
                title = "Sıralama",
                value = company.rating,
                modifier = Modifier.weight(1f)
            )

            CompanyInfoBox(
                title = "Ticaret Sicil",
                value = company.tradeRegistryNumber,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
        ) {
            CompanyInfoBox(
                title = "Yetenekler",
                value = company.capability,
                modifier = Modifier.weight(1f)
            )

            CompanyInfoBox(
                title = "İş Modeli",
                value = company.businessModel,
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.RequestQuote,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
                contentDescription = null,
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
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

data class CompanyListItem(
    val companyId: Int,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val rating: String,
    val tradeRegistryNumber: String,
    val capability: String,
    val businessModel: String,
    val productCount: Int,
    val logoText: String,
    val isVerified: Boolean,
    val icon: ImageVector
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
            companyId = 1,
            name = "Bulbulustur İnternet Teknolojileri ve Tic. A.Ş.",
            description = "Yazılım, dijital dönüşüm ve ticaret altyapıları alanında çözüm sağlayan firma.",
            country = "Türkiye",
            city = "İstanbul",
            rating = "4,2",
            tradeRegistryNumber = "-",
            capability = "Tasarım kaynaklı özelleştirme, yurt dışı mühendis hizmeti",
            businessModel = "Perakendeci, Toptancı",
            productCount = 12,
            logoText = "BB",
            isVerified = true,
            icon = Icons.Outlined.Business
        ),
        CompanyListItem(
            companyId = 2,
            name = "Anadolu Ambalaj Sanayi",
            description = "Koli, kutu, poşet ve endüstriyel ambalaj ürünleri tedarikçisi.",
            country = "Türkiye",
            city = "Kocaeli",
            rating = "4,6",
            tradeRegistryNumber = "245981",
            capability = "Özel üretim, baskılı ambalaj, hızlı sevkiyat",
            businessModel = "Üretici, Toptancı",
            productCount = 42,
            logoText = "AA",
            isVerified = true,
            icon = Icons.Outlined.Factory
        ),
        CompanyListItem(
            companyId = 3,
            name = "Marmara Endüstriyel Tedarik",
            description = "Sanayi, depo, bakım ve üretim hattı sarf malzemeleri sağlar.",
            country = "Türkiye",
            city = "Bursa",
            rating = "4,1",
            tradeRegistryNumber = "118204",
            capability = "Toplu tedarik, sözleşmeli satış, hızlı termin",
            businessModel = "Tedarikçi",
            productCount = 31,
            logoText = "MET",
            isVerified = false,
            icon = Icons.Outlined.Inventory2
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