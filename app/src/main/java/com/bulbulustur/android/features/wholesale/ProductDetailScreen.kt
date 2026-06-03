package com.bulbulustur.android.features.wholesale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun ProductDetailScreen(
    productId: Int = 1,
    onSupplierClick: (Int) -> Unit = {},
    onCategoryClick: (Int) -> Unit = {},
    onQuoteRequestClick: (Int) -> Unit = {},
    onFavoriteClick: (Int) -> Unit = {},
    onShareClick: (Int) -> Unit = {}
) {
    val product = wholesaleProductDetail(productId)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        item {
            WholesaleProductDetailHero(
                product = product
            )
        }

        item {
            WholesaleProductDetailActions(
                product = product,
                onQuoteRequestClick = onQuoteRequestClick,
                onFavoriteClick = onFavoriteClick,
                onShareClick = onShareClick
            )
        }

        item {
            BbSectionHeader(
                title = "Toptan satın alma bilgisi",
                subtitle = "Minimum sipariş, fiyat ve tedarik şartları"
            )
        }

        item {
            WholesaleProductTradeInfo(
                product = product
            )
        }

        item {
            BbSectionHeader(
                title = "Tedarikçi",
                subtitle = "Bu ürünü sağlayan firma"
            )
        }

        item {
            WholesaleProductSupplierCard(
                product = product,
                onSupplierClick = {
                    onSupplierClick(product.supplierId)
                }
            )
        }

        item {
            BbSectionHeader(
                title = "Ürün açıklaması",
                subtitle = "Dummy içerik, API sonrası gerçek açıklama basılacak"
            )
        }

        item {
            WholesaleProductDescriptionCard(
                product = product
            )
        }

        item {
            BbSectionHeader(
                title = "Ürün özellikleri",
                subtitle = "Toptan ürün için teknik ve ticari bilgiler"
            )
        }

        item {
            WholesaleProductPropertyList(
                properties = product.properties
            )
        }

        item {
            BbSectionHeader(
                title = "Kategori ve etiketler",
                subtitle = "Ürünün bağlı olduğu B2B kategori izi"
            )
        }

        item {
            WholesaleProductCategoryTags(
                product = product,
                onCategoryClick = {
                    onCategoryClick(product.categoryId)
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(BbSpacing.xl))
        }
    }
}

@Composable
private fun WholesaleProductDetailHero(
    product: WholesaleProductDetail
) {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Inventory2,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Toptan Ürün",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = product.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                BbChip(
                    text = product.categoryName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Min. ${product.minimumOrderQuantity} adet",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = product.city,
                    selected = false,
                    onClick = {}
                )

                if (product.isVerifiedSupplier) {
                    BbChip(
                        text = "Doğrulanmış tedarikçi",
                        selected = false,
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
private fun WholesaleProductDetailActions(
    product: WholesaleProductDetail,
    onQuoteRequestClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    onShareClick: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        WholesaleProductMainActionCard(
            title = "Teklif iste",
            description = "Bu ürün için tedarikçiden teklif talebi oluştur",
            icon = Icons.Outlined.RequestQuote,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onQuoteRequestClick(product.productId)
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            WholesaleProductMainActionCard(
                title = "Favorilere ekle",
                description = "Sonra incele",
                icon = Icons.Outlined.FavoriteBorder,
                modifier = Modifier.weight(1f),
                onClick = {
                    onFavoriteClick(product.productId)
                }
            )

            WholesaleProductMainActionCard(
                title = "Paylaş",
                description = "Ürün linki",
                icon = Icons.Outlined.Share,
                modifier = Modifier.weight(1f),
                onClick = {
                    onShareClick(product.productId)
                }
            )
        }
    }
}

@Composable
private fun WholesaleProductMainActionCard(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    BbCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
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
                tint = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun WholesaleProductTradeInfo(
    product: WholesaleProductDetail
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            WholesaleTradeInfoCard(
                title = "Fiyat",
                value = product.priceLabel,
                icon = Icons.Outlined.RequestQuote,
                modifier = Modifier.weight(1f)
            )

            WholesaleTradeInfoCard(
                title = "Minimum",
                value = "${product.minimumOrderQuantity} adet",
                icon = Icons.Outlined.Inventory2,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            WholesaleTradeInfoCard(
                title = "Termin",
                value = product.deliveryTimeLabel,
                icon = Icons.Outlined.LocalShipping,
                modifier = Modifier.weight(1f)
            )

            WholesaleTradeInfoCard(
                title = "Konum",
                value = product.city,
                icon = Icons.Outlined.Business,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun WholesaleTradeInfoCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.md),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.xs),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WholesaleProductSupplierCard(
    product: WholesaleProductDetail,
    onSupplierClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onSupplierClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.xs)
                ) {
                    Text(
                        text = product.supplierName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (product.isVerifiedSupplier) {
                        Icon(
                            imageVector = Icons.Outlined.Verified,
                            contentDescription = null,
                            tint = BbColors.Primary
                        )
                    }
                }

                Text(
                    text = product.supplierDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${product.city} • ${product.supplierProductCount} ürün",
                    style = MaterialTheme.typography.labelMedium,
                    color = BbColors.TextMuted
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun WholesaleProductDescriptionCard(
    product: WholesaleProductDetail
) {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.md),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Text(
                text = product.longDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Security,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Toptan işlem detayları teklif sürecinde tedarikçiyle netleştirilir.",
                    style = MaterialTheme.typography.labelMedium,
                    color = BbColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun WholesaleProductPropertyList(
    properties: List<WholesaleProductProperty>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        properties.forEach { property ->
            WholesaleProductPropertyCard(
                property = property
            )
        }
    }
}

@Composable
private fun WholesaleProductPropertyCard(
    property: WholesaleProductProperty
) {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Text(
                text = property.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = property.value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun WholesaleProductCategoryTags(
    product: WholesaleProductDetail,
    onCategoryClick: () -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        BbChip(
            text = product.categoryName,
            selected = false,
            onClick = onCategoryClick
        )

        product.tags.forEach { tag ->
            BbChip(
                text = tag,
                selected = false,
                onClick = {}
            )
        }
    }
}

private data class WholesaleProductDetail(
    val productId: Int,
    val supplierId: Int,
    val categoryId: Int,
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val supplierName: String,
    val supplierDescription: String,
    val supplierProductCount: Int,
    val categoryName: String,
    val city: String,
    val minimumOrderQuantity: Int,
    val priceLabel: String,
    val deliveryTimeLabel: String,
    val isVerifiedSupplier: Boolean,
    val tags: List<String>,
    val properties: List<WholesaleProductProperty>
)

private data class WholesaleProductProperty(
    val name: String,
    val value: String
)

private fun wholesaleProductDetail(productId: Int): WholesaleProductDetail {
    return WholesaleProductDetail(
        productId = productId,
        supplierId = 101,
        categoryId = 1,
        name = "E-ticaret Kargo Kolisi",
        shortDescription = "Farklı ölçü seçenekleriyle dayanıklı toptan koli grubu.",
        longDescription = "E-ticaret, depo ve sevkiyat operasyonlarında kullanılmak üzere üretilen dayanıklı kargo kolisi ürün grubudur. Ürün ölçüleri, baskı seçenekleri, adet bazlı fiyatlandırma ve teslimat koşulları teklif sürecinde netleştirilebilir.",
        supplierName = "Anadolu Ambalaj Sanayi",
        supplierDescription = "Koli, kutu, poşet ve endüstriyel ambalaj ürünleri alanında çalışan toptan tedarikçi.",
        supplierProductCount = 42,
        categoryName = "Ambalaj",
        city = "İstanbul",
        minimumOrderQuantity = 100,
        priceLabel = "Teklif ile fiyat",
        deliveryTimeLabel = "3-7 gün",
        isVerifiedSupplier = true,
        tags = listOf(
            "Koli",
            "E-ticaret",
            "Sevkiyat",
            "Toptan"
        ),
        properties = listOf(
            WholesaleProductProperty(
                name = "Malzeme",
                value = "Oluklu mukavva"
            ),
            WholesaleProductProperty(
                name = "Baskı",
                value = "Opsiyonel"
            ),
            WholesaleProductProperty(
                name = "Satış tipi",
                value = "Toptan"
            ),
            WholesaleProductProperty(
                name = "Minimum sipariş",
                value = "100 adet"
            ),
            WholesaleProductProperty(
                name = "Teslimat",
                value = "Anlaşmalı kargo"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailScreenPreview() {
    BbTheme {
        ProductDetailScreen()
    }
}