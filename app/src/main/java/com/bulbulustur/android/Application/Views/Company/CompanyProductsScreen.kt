package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Inventory2
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
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

@Composable
fun CompanyProductsScreen(
    companyId: Int = 1,
    onBackClick: () -> Unit = {},
    onCompanyProfileClick: () -> Unit = {},
    onCompanyContactClick: () -> Unit = {},
    onProductClick: () -> Unit = {}
) {
    val company = remember(companyId) {
        getCompanyProducts(companyId)
    }

    Scaffold(
        containerColor = BBColors.SurfaceSoft,
        topBar = {
            BbInnerPageHeader(
                title = "Firma Ürünleri",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            item(
                span = {
                    androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan)
                }
            ) {
                CompanyProductsHero(
                    company = company,
                    onCompanyProfileClick = onCompanyProfileClick,
                    onCompanyContactClick = onCompanyContactClick
                )
            }

            item(
                span = {
                    androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan)
                }
            ) {
                CompanyProductsFilterHeader(
                    company = company
                )
            }

            items(
                items = company.products,
                key = { product ->
                    product.productId
                }
            ) { product ->
                CompanyProductCard(
                    product = product,
                    onClick = onProductClick
                )
            }

            item(
                span = {
                    androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan)
                }
            ) {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@Composable
private fun CompanyProductsHero(
    company: CompanyProducts,
    onCompanyProfileClick: () -> Unit,
    onCompanyContactClick: () -> Unit
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
                CompanyProductsLogo(
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
                            text = "Tedarikçi Ürünleri",
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
                        text = "${company.name} Ürünleri",
                        style = MaterialTheme.typography.headlineSmall,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = company.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BBColors.TextMuted,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
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
                    text = "Profil",
                    onClick = onCompanyProfileClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "İletişim",
                    onClick = onCompanyContactClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Secondary,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun CompanyProductsLogo(
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
private fun CompanyProductsFilterHeader(
    company: CompanyProducts
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Ürün Listesi",
                    style = MaterialTheme.typography.titleLarge,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${company.products.size} ürün grubu listeleniyor",
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted
                )
            }

            Text(
                text = "Filtrele",
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.Primary,
                fontWeight = FontWeight.Bold
            )
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            company.filters.forEachIndexed { index, filter ->
                BbChip(
                    text = filter,
                    selected = index == 0,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun CompanyProductCard(
    product: CompanyProduct,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.15f)
                    .background(BBColors.Gray.Gray50),
                contentAlignment = Alignment.Center
            ) {
                if (product.imageRes != null) {
                    Image(
                        painter = painterResource(id = product.imageRes),
                        contentDescription = product.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Inventory2,
                        contentDescription = null,
                        tint = BBColors.TextMuted,
                        modifier = Modifier.size(BBIcon.BoxLg)
                    )
                }

                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clip(BBRadius.PillShape),
                    shape = BBRadius.PillShape,
                    color = BBColors.Primary
                ) {
                    Text(
                        text = product.badge,
                        modifier = Modifier.padding(
                            horizontal = BBSpacing.Space2,
                            vertical = BBSpacing.Space1
                        ),
                        style = MaterialTheme.typography.labelSmall,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier.padding(BBSpacing.Space3),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.company,
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleMedium,
                    color = BBColors.Primary,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.moq,
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    product.tags.forEach { tag ->
                        CompanySmallPill(text = tag)
                    }
                }
            }
        }
    }
}

@Composable
private fun CompanySmallPill(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = BBColors.SurfaceMuted
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.TextMuted,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Immutable
private data class CompanyProducts(
    val companyId: Int,
    val name: String,
    val logoText: String,
    val description: String,
    val isVerified: Boolean,
    val chips: List<String>,
    val filters: List<String>,
    val products: List<CompanyProduct>
)

@Immutable
private data class CompanyProduct(
    val productId: Int,
    val name: String,
    val company: String,
    val price: String,
    val moq: String,
    val badge: String,
    val tags: List<String>,
    val imageRes: Int?
)

private fun getCompanyProducts(
    companyId: Int
): CompanyProducts {
    val image = R.drawable.h3ff3b33d6a1447c898cee6e336867bach

    return CompanyProducts(
        companyId = companyId,
        name = "Ortobella Comfort",
        logoText = "OC",
        description = "Firmanın toptan satışa sunduğu ürünleri kategori bazında inceleyin, fiyat aralıklarını karşılaştırın ve ürün detaylarından tedarik sürecine geçin.",
        isVerified = true,
        chips = listOf(
            "Türkiye",
            "Samsun",
            "Doğrulanmış",
            "120+ Ürün",
            "Hızlı Teklif"
        ),
        filters = listOf(
            "Tümü",
            "Yeni",
            "Popüler",
            "Düşük MOQ",
            "Hızlı Teklif"
        ),
        products = listOf(
            CompanyProduct(
                productId = 1,
                name = "Endüstriyel Karton Koli Seti",
                company = "Ortobella Comfort",
                price = "₺12,40 / Adet",
                moq = "Min. Sipariş: 1.000 Adet",
                badge = "Toptan",
                tags = listOf("İstanbul", "Hızlı Teklif"),
                imageRes = image
            ),
            CompanyProduct(
                productId = 2,
                name = "Paslanmaz Makine Yedek Parçası",
                company = "Ortobella Comfort",
                price = "Teklif İste",
                moq = "Min. Sipariş: 50 Adet",
                badge = "RFQ",
                tags = listOf("Konya", "Hızlı Teklif"),
                imageRes = image
            ),
            CompanyProduct(
                productId = 3,
                name = "Elektronik Güç Modülü",
                company = "Ortobella Comfort",
                price = "₺420,00 / Adet",
                moq = "Min. Sipariş: 200 Adet",
                badge = "Yeni",
                tags = listOf("Ankara", "Stoklu"),
                imageRes = image
            ),
            CompanyProduct(
                productId = 4,
                name = "Toptan Pamuklu Kumaş Rulosu",
                company = "Ortobella Comfort",
                price = "₺78,50 / Metre",
                moq = "Min. Sipariş: 500 Metre",
                badge = "Popüler",
                tags = listOf("Bursa", "Üretici"),
                imageRes = image
            ),
            CompanyProduct(
                productId = 5,
                name = "Özel Baskılı Promosyon Çanta",
                company = "Ortobella Comfort",
                price = "Teklif İste",
                moq = "Min. Sipariş: 300 Adet",
                badge = "OEM",
                tags = listOf("Samsun", "Özel Üretim"),
                imageRes = image
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CompanyProductsScreenPreview() {
    BbTheme {
        CompanyProductsScreen()
    }
}
