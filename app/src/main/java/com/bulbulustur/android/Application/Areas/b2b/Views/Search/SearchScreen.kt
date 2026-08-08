package com.bulbulustur.android.Application.Areas.b2b.Views.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO

@Composable
fun SearchScreen(
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onProductSearchClick: (String) -> Unit = {},
    onCompanySearchClick: (String) -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onRfqCreateClick: (String) -> Unit = {},
    productResults: List<WholesaleProductDTO> = emptyList(),
    hasProductSearch: Boolean = false,
    bottomBar: @Composable () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val displayedProducts = remember(productResults) {
        productResults.map { product ->
            product.toWholesaleSearchProductResult()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            WholesaleSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = onBackClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                placeholder = BBLocalization.Current.Get(
                    key = "8d009caa-1db4-42e9-b394-dc818277d259",
                    fallback = "Toptan ürün, kategori veya tedarikçi ara"
                ),
                onSearchClick = {
                    onProductSearchClick(searchText)
                },
                onClearClick = {
                    searchText = ""
                },
                leadingAction = WholesaleSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick
            )
        },
        bottomBar = bottomBar
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                WholesaleSearchModeCards(
                    searchText = searchText,
                    onProductSearchClick = onProductSearchClick,
                    onCompanySearchClick = onCompanySearchClick,
                    onRfqCreateClick = onRfqCreateClick
                )
            }

            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "1c7c6ac9-2b6d-46ec-90f0-3f88b65beb11",
                        fallback = "Ürün sonuçları"
                    ),
                    subtitle = if (hasProductSearch) {
                        BBLocalization.Current.Get(
                            key = "5a8d3281-5a12-4a83-b707-43bd1c2c81ac",
                            fallback = "Arama sonucunda bulunan toptan ürünler"
                        )
                    } else {
                        BBLocalization.Current.Get(
                            key = "72c68813-7e27-4db8-a39c-39585ab73352",
                            fallback = "Toptan pazaryerinde ürün aramak için üstteki arama alanını kullanın"
                        )
                    }
                )
            }

            when {
                !hasProductSearch -> {
                    item {
                        WholesaleSearchInfoCard(
                            title = BBLocalization.Current.Get(
                                key = "0e2dc829-9eb6-4c30-a0d8-321e3a6d4b89",
                                fallback = "Arama yapın"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "52984df1-44f0-45b4-8a7f-8c51e33ef720",
                                fallback = "Toptan ürünleri bulmak için en az 3 karakterlik bir arama terimi girin."
                            ),
                            icon = Icons.Outlined.Inventory2
                        )
                    }
                }

                displayedProducts.isEmpty() -> {
                    item {
                        WholesaleSearchInfoCard(
                            title = BBLocalization.Current.Get(
                                key = "827ac3ff-d105-4f4c-9fa3-cf6b891a7f4e",
                                fallback = "Ürün bulunamadı"
                            ),
                            description = BBLocalization.Current.Get(
                                key = "0f4521b0-f999-4f8c-9351-d50cc71c2a19",
                                fallback = "Arama kriterine uygun toptan ürün bulunamadı."
                            ),
                            icon = Icons.Outlined.Inventory2
                        )
                    }
                }

                else -> {
                    itemsIndexed(
                        items = displayedProducts,
                        key = { index, product ->
                            "wholesale-search-product-${product.productId}-$index"
                        }
                    ) { _, product ->
                        WholesaleSearchResultCard(
                            title = product.name,
                            description = product.description,
                            meta = product.meta,
                            icon = product.icon,
                            onClick = {
                                onProductClick(product.productId)
                            }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@Composable
private fun WholesaleSearchModeCards(
    searchText: String,
    onProductSearchClick: (String) -> Unit,
    onCompanySearchClick: (String) -> Unit,
    onRfqCreateClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
        ) {
            WholesaleSearchModeCard(
                title = BBLocalization.Current.Get(
                    key = "1060807b-bfd6-4a32-af48-585becf37a8e",
                    fallback = "Ürünlerde ara"
                ),
                description = BBLocalization.Current.Get(
                    key = "79b4a21a-b9a4-47e9-8931-f5d66750cea0",
                    fallback = "Toptan ürün listesi"
                ),
                icon = Icons.Outlined.Inventory2,
                modifier = Modifier.weight(1f),
                onClick = {
                    onProductSearchClick(searchText)
                }
            )

            WholesaleSearchModeCard(
                title = BBLocalization.Current.Get(
                    key = "3238ddc7-c996-4b0e-af73-73c5ed68586b",
                    fallback = "Firmalarda ara"
                ),
                description = BBLocalization.Current.Get(
                    key = "e8a21e2e-de5d-4682-b64a-7cdd977fc29e",
                    fallback = "Firma Vitrini"
                ),
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f),
                onClick = {
                    onCompanySearchClick(searchText)
                }
            )
        }

        BbButton(
            text = BBLocalization.Current.Get(
                key = "fb037e3d-5dfd-4da8-b23d-3f7995cb8acd",
                fallback = "Aradığını bulamadın mı? Teklif talebi oluştur"
            ),
            onClick = {
                onRfqCreateClick(searchText)
            },
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

@Composable
private fun WholesaleSearchModeCard(
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
private fun WholesaleSearchResultCard(
    title: String,
    description: String,
    meta: String,
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
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = meta,
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
private fun WholesaleSearchInfoCard(
    title: String,
    description: String,
    icon: ImageVector
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
        }
    }
}

data class WholesaleSearchProductResult(
    val productId: Int,
    val name: String,
    val description: String,
    val meta: String,
    val icon: ImageVector
)

private fun WholesaleProductDTO.toWholesaleSearchProductResult(): WholesaleSearchProductResult {
    val name = ProductName
        .takeIf { it.isNotBlank() }
        ?: SeoTitle.takeIf { it.isNotBlank() }
        ?: BBLocalization.Current.Get(
            key = "45ac0832-bf5d-4c76-a747-f544863260cb",
            fallback = "Toptan ürün"
        )

    val description = Description
        .takeIf { it.isNotBlank() }
        ?: Category.takeIf { it.isNotBlank() }
        ?: BBLocalization.Current.Get(
            key = "4b06bbbe-2824-4b75-81e9-a3796bc59339",
            fallback = "Ürün açıklaması bulunmuyor."
        )

    val companyName = CompanyName
        .takeIf { it.isNotBlank() }
        ?: BBLocalization.Current.Get(
            key = "e3af0e39-0a77-4184-b981-905315dbd1c",
            fallback = "Firma bilgisi yok"
        )

    val quantityText = if (MinimumOrderQuantity > 0) {
        "Min. $MinimumOrderQuantity adet"
    } else {
        BBLocalization.Current.Get(
            key = "e2256c25-f471-4083-8f20-57650248c7a7",
            fallback = "Minimum sipariş bilgisi yok"
        )
    }

    return WholesaleSearchProductResult(
        productId = WholesaleProductId,
        name = name,
        description = description,
        meta = "$quantityText • $companyName",
        icon = Icons.Outlined.Inventory2
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    BbTheme {
        SearchScreen()
    }
}