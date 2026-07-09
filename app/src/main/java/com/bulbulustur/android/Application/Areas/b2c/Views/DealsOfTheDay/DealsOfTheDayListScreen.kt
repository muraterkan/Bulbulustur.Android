package com.bulbulustur.android.Application.Areas.b2c.Views.DealsOfTheDay

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import java.util.Locale

@Composable
fun DealsOfTheDayListScreen(
    dealsOfTheDays: List<DealsOfTheDayDTO>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onProductClick: (DealsOfTheDayDTO) -> Unit = {}
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
                DealsTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                DealsHero(
                    count = dealsOfTheDays.size
                )
            }

            if (isLoading) {
                item {
                    DealsInfoCard(
                        title = "Fırsatlar yükleniyor",
                        description = "Bugünün fırsatları getiriliyor.",
                        showProgress = true
                    )
                }
            } else if (!errorMessage.isNullOrBlank() && dealsOfTheDays.isEmpty()) {
                item {
                    DealsInfoCard(
                        title = "Fırsatlar alınamadı",
                        description = errorMessage
                    )
                }
            } else if (dealsOfTheDays.isEmpty()) {
                item {
                    DealsInfoCard(
                        title = "Fırsat bulunamadı",
                        description = "Şu anda listelenecek fırsat yok."
                    )
                }
            } else {
                items(
                    items = dealsOfTheDays,
                    key = { deal ->
                        deal.DealsOfTheDayId
                    }
                ) { deal ->
                    DealListCard(
                        deal = deal,
                        onClick = {
                            onProductClick(deal)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DealsTopBar(
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
                text = "Öne Çıkan Fırsatlar",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Bugünün seçilmiş perakende fırsatları.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DealsHero(
    count: Int
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
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Sınırlı süreli fiyatlar",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "$count fırsat listeleniyor.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun DealListCard(
    deal: DealsOfTheDayDTO,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
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
                text = deal.ProductName.ifBlank { "Ürün" },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (deal.Store.isNotBlank()) {
                Text(
                    text = deal.Store,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = deal.CategoryName.ifBlank { "Kategori yok" },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (deal.Brand.isNotBlank()) {
                        Text(
                            text = deal.Brand,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Text(
                    text = formatDealPrice(deal),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (deal.Rating > 0.0 || deal.ReviewNumber > 0) {
                Text(
                    text = "Puan ${String.format(Locale.US, "%.1f", deal.Rating)} • ${deal.ReviewNumber} yorum",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun DealsInfoCard(
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

private fun formatDealPrice(
    deal: DealsOfTheDayDTO
): String {
    val price = if (deal.CampaignPrice > 0.0) {
        deal.CampaignPrice
    } else {
        deal.Price
    }

    val symbol = deal.CurrencySymbol.ifBlank {
        "₺"
    }

    return symbol + String.format(Locale.US, "%.2f", price)
}
