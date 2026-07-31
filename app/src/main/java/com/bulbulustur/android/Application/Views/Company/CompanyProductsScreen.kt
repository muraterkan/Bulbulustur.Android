package com.bulbulustur.android.Application.Views.Company

import com.bulbulustur.android.Application.Localization.BBLocalization

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.R
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
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
    onProductClick: (Int) -> Unit = {},
    onProductFavoriteClick: (Int) -> Unit = {},
    onRfqCreateClick: (Int) -> Unit = {}
) {
    val company = remember(companyId) {
        getCompanyProducts(companyId)
    }

    var selectedFilter by remember {
        mutableStateOf(BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü"))
    }

    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val filteredProducts = remember(
        company.Products,
        selectedFilter
    ) {
        when (selectedFilter) {
            BBLocalization.Current.Get(key = "557ea0c9-948d-4e62-8ddc-948294a55b11", fallback = "Yeni") -> {
                company.Products.filter { product ->
                    product.BadgeText == BBLocalization.Current.Get(key = "557ea0c9-948d-4e62-8ddc-948294a55b11", fallback = "Yeni")
                }
            }

            BBLocalization.Current.Get(key = "c45d05c5-c097-40c4-9379-a7ec77726c36", fallback = "Popüler") -> {
                company.Products.filter { product ->
                    product.BadgeText == BBLocalization.Current.Get(key = "c45d05c5-c097-40c4-9379-a7ec77726c36", fallback = "Popüler")
                }
            }

            BBLocalization.Current.Get(key = "3c83d1c5-3b8b-4664-8ab3-985b45790a4f", fallback = "Düşük MOQ") -> {
                company.Products.filter { product ->
                    product.IsLowMoq
                }
            }

            BBLocalization.Current.Get(key = "0f7c5558-348e-471a-8424-d096c7de94cc", fallback = "Hızlı teklif") -> {
                company.Products.filter { product ->
                    product.HasFastQuote
                }
            }

            else -> {
                company.Products
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Firma ürünleri",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottom
            ),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            item(
                span = {
                    GridItemSpan(maxLineSpan)
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
                    GridItemSpan(maxLineSpan)
                }
            ) {
                CompanyProductsFilterHeader(
                    productCount = filteredProducts.size,
                    filters = company.Filters,
                    selectedFilter = selectedFilter,
                    onFilterClick = { filter ->
                        selectedFilter = filter
                    }
                )
            }

            items(
                items = filteredProducts,
                key = { product ->
                    product.Id
                }
            ) { product ->
                val isFavorite = favoriteProductIds.contains(
                    product.Id
                )

                WholesaleProductCard(
                    product = WholesaleProductCardModel(
                        Id = product.Id,
                        Title = product.Title,
                        Category = product.Category,
                        PriceText = product.PriceText,
                        MoqText = product.MoqText,
                        SupplierText = product.SupplierText,
                        BadgeText = product.BadgeText,
                        ImageResId = product.ImageResId,
                        IsFavorite = isFavorite
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onProductClick(
                            product.Id
                        )
                    },
                    onFavoriteClick = {
                        favoriteProductIds = if (isFavorite) {
                            favoriteProductIds - product.Id
                        } else {
                            favoriteProductIds + product.Id
                        }

                        onProductFavoriteClick(
                            product.Id
                        )
                    },
                    onRfqClick = {
                        onRfqCreateClick(
                            product.Id
                        )
                    }
                )
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
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
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                CompanyProductsLogo(
                    logoText = company.LogoText
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
                            BBSpacing.Space1
                        )
                    ) {
                        BbChip(
                            text = "Tedarikçi Ürünleri",
                            selected = false,
                            onClick = onCompanyProfileClick
                        )

                        if (company.IsVerified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = "Doğrulanmış firma",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(
                                    BBIcon.SizeSm
                                )
                            )
                        }
                    }

                    Text(
                        text = "${company.Name} ürünleri",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = company.Description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                ),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                company.Chips.forEach { chip ->
                    BbChip(
                        text = chip,
                        selected = false,
                        onClick = {}
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(key = "ab200e4f-1f9e-45f4-90a6-7d5d21d33953", fallback = "Profil"),
                    onClick = onCompanyProfileClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "0cf2cda1-7cf6-4d8b-ab56-8918e3a260fd", fallback = "İletişim"),
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
        modifier = Modifier.size(
            BBIcon.BoxXl
        ),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(
                    BBIcon.SizeLg
                )
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
private fun CompanyProductsFilterHeader(
    productCount: Int,
    filters: List<String>,
    selectedFilter: String,
    onFilterClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "79b4a21a-b9a4-47e9-8931-f5d66750cea0", fallback = "Ürün Listesi"),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$productCount ürün listeleniyor",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "bb4d65cd-d8cf-485d-9689-4f44c7353dfa", fallback = "Filtrele"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space2
            )
        ) {
            filters.forEach { filter ->
                BbChip(
                    text = filter,
                    selected = selectedFilter == filter,
                    onClick = {
                        onFilterClick(filter)
                    }
                )
            }
        }
    }
}

@Immutable
private data class CompanyProducts(
    val CompanyId: Int,
    val Name: String,
    val LogoText: String,
    val Description: String,
    val IsVerified: Boolean,
    val Chips: List<String>,
    val Filters: List<String>,
    val Products: List<CompanyWholesaleProduct>
)

@Immutable
private data class CompanyWholesaleProduct(
    val Id: Int,
    val Title: String,
    val Category: String,
    val PriceText: String,
    val MoqText: String,
    val SupplierText: String,
    val BadgeText: String,
    val ImageResId: Int,
    val IsLowMoq: Boolean,
    val HasFastQuote: Boolean
)

private fun getCompanyProducts(
    companyId: Int
): CompanyProducts {
    return CompanyProducts(
        CompanyId = companyId,
        Name = "Ortobella Comfort",
        LogoText = "OC",
        Description = "Firmanın toptan satışa sunduğu ürünleri kategori bazında inceleyin, fiyat aralıklarını karşılaştırın ve ürün detaylarından tedarik sürecine geçin.",
        IsVerified = true,
        Chips = listOf(
            BBLocalization.Current.Get(key = "5365b492-6a1c-4b46-b5c0-b50cbfdd17a8", fallback = "Türkiye"),
            "Samsun",
            BBLocalization.Current.Get(key = "c6a0ff62-8828-475f-b553-37effb42efe6", fallback = "Doğrulanmış"),
            "120+ ürün",
            BBLocalization.Current.Get(key = "0f7c5558-348e-471a-8424-d096c7de94cc", fallback = "Hızlı teklif")
        ),
        Filters = listOf(
            BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü"),
            BBLocalization.Current.Get(key = "557ea0c9-948d-4e62-8ddc-948294a55b11", fallback = "Yeni"),
            BBLocalization.Current.Get(key = "c45d05c5-c097-40c4-9379-a7ec77726c36", fallback = "Popüler"),
            BBLocalization.Current.Get(key = "3c83d1c5-3b8b-4664-8ab3-985b45790a4f", fallback = "Düşük MOQ"),
            BBLocalization.Current.Get(key = "0f7c5558-348e-471a-8424-d096c7de94cc", fallback = "Hızlı teklif")
        ),
        Products = listOf(
            CompanyWholesaleProduct(
                Id = 1,
                Title = "Endüstriyel karton koli seti",
                Category = "Ambalaj ve paketleme",
                PriceText = "₺12,40 / adet",
                MoqText = "MOQ 1.000",
                SupplierText = "Ortobella",
                BadgeText = "Toptan",
                ImageResId =
                    R.drawable.h3ff3b33d6a1447c898cee6e336867bach,
                IsLowMoq = false,
                HasFastQuote = true
            ),
            CompanyWholesaleProduct(
                Id = 2,
                Title = "Paslanmaz makine yedek parçası",
                Category = "Makine ve sanayi",
                PriceText = BBLocalization.Current.Get(key = "dbb6b4e4-1a23-423d-9b87-5b0c8cbeb64d", fallback = "Teklif iste"),
                MoqText = "MOQ 50",
                SupplierText = "Ortobella",
                BadgeText = "RFQ",
                ImageResId =
                    R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar1,
                IsLowMoq = true,
                HasFastQuote = true
            ),
            CompanyWholesaleProduct(
                Id = 3,
                Title = "Elektronik güç modülü",
                Category = "Elektronik bileşen",
                PriceText = "₺420,00 / adet",
                MoqText = "MOQ 200",
                SupplierText = "Ortobella",
                BadgeText = BBLocalization.Current.Get(key = "557ea0c9-948d-4e62-8ddc-948294a55b11", fallback = "Yeni"),
                ImageResId =
                    R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar2,
                IsLowMoq = false,
                HasFastQuote = false
            ),
            CompanyWholesaleProduct(
                Id = 4,
                Title = "Toptan pamuklu kumaş rulosu",
                Category = "Tekstil ve giyim",
                PriceText = "₺78,50 / metre",
                MoqText = "MOQ 500",
                SupplierText = "Ortobella",
                BadgeText = BBLocalization.Current.Get(key = "c45d05c5-c097-40c4-9379-a7ec77726c36", fallback = "Popüler"),
                ImageResId =
                    R.drawable.h3ff3b33d6a1447c898cee6e336867bachvar3,
                IsLowMoq = false,
                HasFastQuote = true
            ),
            CompanyWholesaleProduct(
                Id = 5,
                Title = "Özel baskılı promosyon çanta",
                Category = "Promosyon ürünleri",
                PriceText = BBLocalization.Current.Get(key = "dbb6b4e4-1a23-423d-9b87-5b0c8cbeb64d", fallback = "Teklif iste"),
                MoqText = "MOQ 300",
                SupplierText = "Ortobella",
                BadgeText = "OEM",
                ImageResId =
                    R.drawable.h3ff3b33d6a1447c898cee6e336867bach,
                IsLowMoq = false,
                HasFastQuote = true
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
