package com.bulbulustur.android.Application.Areas.b2c.Views.Campaign

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignProductDTO

@Composable
fun CampaignDetailScreen(
    campaign: CampaignDTO?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onProductClick: (CampaignProductDTO) -> Unit = {},
    onCategoryClick: (Int) -> Unit = {},
    onStoreClick: (Int) -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 14.dp,
                end = 16.dp,
                bottom = 28.dp
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            item {
                CampaignDetailTopBar(
                    onBackClick = onBackClick
                )
            }

            if (isLoading) {
                item {
                    CampaignDetailInfoCard(
                        title = "Kampanya yükleniyor",
                        description = "Kampanya detayları getiriliyor.",
                        showProgress = true
                    )
                }
            } else if (campaign == null) {
                item {
                    CampaignDetailInfoCard(
                        title = "Kampanya bulunamadı",
                        description = errorMessage ?: "Kampanya detayı alınamadı."
                    )
                }
            } else {
                item {
                    CampaignDetailHero(
                        campaign = campaign
                    )
                }

                item {
                    CampaignDetailSummary(
                        campaign = campaign,
                        onCategoryClick = {
                            if (campaign.ProductCategoryId > 0) {
                                onCategoryClick(campaign.ProductCategoryId)
                            }
                        }
                    )
                }

                if (campaign.CampaignCondition.isNotBlank()) {
                    item {
                        CampaignConditionCard(
                            condition = campaign.CampaignCondition
                        )
                    }
                }

                item {
                    BbSectionHeader(
                        title = "Kampanya Ürünleri",
                        subtitle = "Bu kampanyaya dahil seçili ürünler."
                    )
                }

                if (campaign.CampaignProducts.isEmpty()) {
                    item {
                        CampaignDetailInfoCard(
                            title = "Ürün bulunamadı",
                            description = "Bu kampanyaya bağlı ürün listesi boş."
                        )
                    }
                } else {
                    items(
                        items = campaign.CampaignProducts,
                        key = { product ->
                            product.CampaignProductId
                        }
                    ) { product ->
                        CampaignProductRow(
                            product = product,
                            onProductClick = {
                                onProductClick(product)
                            },
                            onStoreClick = {
                                if (product.StoreId > 0) {
                                    onStoreClick(product.StoreId)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CampaignDetailTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BbIconBox(
            modifier = Modifier.clickable {
                onBackClick()
            },
            size = BbIconBoxSize.Medium,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Kampanya Detayları",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Kapsam, koşullar ve kampanya ürünleri.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CampaignDetailHero(
    campaign: CampaignDTO
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = campaign.CampaignName.ifBlank { "Kampanya" },
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            if (campaign.Description.isNotBlank()) {
                Text(
                    text = campaign.Description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Text(
                text = "${campaign.CampaignStartDate.take(10)} - ${campaign.CampaignEndDate.take(10)}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun CampaignDetailSummary(
    campaign: CampaignDTO,
    onCategoryClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CampaignSummaryLine(
                title = "Kategori",
                value = campaign.CategoryName.ifBlank { "Genel" }
            )

            CampaignSummaryLine(
                title = "Maksimum ürün",
                value = campaign.MaximumProducts.toString()
            )

            CampaignSummaryLine(
                title = "Kampanya ürünü",
                value = campaign.CampaignProducts.size.toString()
            )

            if (campaign.ProductCategoryId > 0) {
                BbButton(
                    text = "Kategoriye Git",
                    variant = BbButtonVariant.Outline,
                    onClick = onCategoryClick
                )
            }
        }
    }
}

@Composable
private fun CampaignSummaryLine(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CampaignConditionCard(
    condition: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Kampanya Koşulu",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = condition,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CampaignProductRow(
    product: CampaignProductDTO,
    onProductClick: () -> Unit,
    onStoreClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onProductClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = product.ProductName.ifBlank { "Ürün" },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (product.StoreName.isNotBlank()) {
                Text(
                    modifier = Modifier.clickable {
                        onStoreClick()
                    },
                    text = product.StoreName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.CategoryName.ifBlank { "Kategori yok" },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = formatCampaignPrice(product),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (product.Color.isNotBlank() || product.Size.isNotBlank()) {
                Text(
                    text = listOf(product.Color, product.Size).filter { it.isNotBlank() }.joinToString(" / "),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CampaignDetailInfoCard(
    title: String,
    description: String,
    showProgress: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (showProgress) {
                CircularProgressIndicator()
            }

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
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

private fun formatCampaignPrice(
    product: CampaignProductDTO
): String {
    val price = if (product.CampaignPrice > 0.0) {
        product.CampaignPrice
    } else {
        product.Price
    }

    return "₺" + String.format("%.2f", price)
}
