package com.bulbulustur.android.features.retail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProductReviewScreen(
    productId: Int = 1,
    onBackClick: () -> Unit = {},
    onReviewClick: (RetailProductReviewItem) -> Unit = {}
) {
    val screenData = remember(productId) {
        getRetailProductReviewScreenData(productId)
    }

    var selectedFilter by remember {
        mutableStateOf("Tümü")
    }

    val filteredReviews = remember(selectedFilter, screenData.reviews) {
        if (selectedFilter == "Tümü") {
            screenData.reviews
        } else {
            screenData.reviews.filter {
                it.filterTags.contains(selectedFilter)
            }
        }
    }

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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProductReviewTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                ProductReviewProductSummary(
                    product = screenData.product
                )
            }

            item {
                ProductReviewScoreSummary(
                    summary = screenData.summary
                )
            }

            item {
                ProductReviewFilterSection(
                    filters = screenData.filters,
                    selectedFilter = selectedFilter,
                    onFilterChange = {
                        selectedFilter = it
                    }
                )
            }

            item {
                ProductReviewSectionTitle(
                    title = "Müşteri yorumları",
                    description = "Ürün deneyimleri, puanlar ve satın alma sonrası notlar."
                )
            }

            items(filteredReviews) { review ->
                ProductReviewCard(
                    review = review,
                    onClick = {
                        onReviewClick(review)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductReviewTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
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
                text = "Ürün yorumları",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Satın alan kullanıcıların ürün deneyimleri.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductReviewProductSummary(
    product: RetailProductReviewProductSummary
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(9.dp))

                Text(
                    text = product.variantText,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
private fun ProductReviewScoreSummary(
    summary: RetailProductReviewSummary
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = summary.averageScoreText,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = summary.starText,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${summary.reviewCount} yorum",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "${summary.verifiedBuyerCount} doğrulanmış alışveriş",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProductReviewSummaryPill(
                    text = "Kalite: ${summary.qualityScoreText}"
                )

                ProductReviewSummaryPill(
                    text = "Kargo: ${summary.cargoScoreText}"
                )
            }
        }
    }
}

@Composable
private fun ProductReviewSummaryPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = 10.dp,
                vertical = 6.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProductReviewFilterSection(
    filters: List<String>,
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ProductReviewSectionTitle(
            title = "Yorum filtresi",
            description = "Yorumları deneyim türüne göre daralt."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEach { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = {
                        onFilterChange(filter)
                    },
                    label = {
                        Text(text = filter)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductReviewCard(
    review: RetailProductReviewItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = review.customerInitials,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = review.customerName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = review.dateText,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = review.ratingText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (review.isVerifiedBuyer) {
                    ProductReviewBadge(
                        text = "Doğrulanmış alışveriş"
                    )
                }

                if (review.variantText.isNotBlank()) {
                    ProductReviewBadge(
                        text = review.variantText
                    )
                }

                if (review.helpfulCount > 0) {
                    ProductReviewBadge(
                        text = "${review.helpfulCount} kişi faydalı buldu"
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductReviewBadge(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = 9.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProductReviewSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class RetailProductReviewScreenData(
    val product: RetailProductReviewProductSummary,
    val summary: RetailProductReviewSummary,
    val filters: List<String>,
    val reviews: List<RetailProductReviewItem>
)

data class RetailProductReviewProductSummary(
    val id: Int,
    val name: String,
    val storeName: String,
    val variantText: String,
    val imageText: String
)

data class RetailProductReviewSummary(
    val averageScoreText: String,
    val starText: String,
    val reviewCount: Int,
    val verifiedBuyerCount: Int,
    val qualityScoreText: String,
    val cargoScoreText: String
)

data class RetailProductReviewItem(
    val id: Int,
    val customerName: String,
    val customerInitials: String,
    val dateText: String,
    val ratingText: String,
    val comment: String,
    val variantText: String,
    val helpfulCount: Int,
    val isVerifiedBuyer: Boolean,
    val filterTags: List<String>
)

private fun getRetailProductReviewScreenData(productId: Int): RetailProductReviewScreenData {
    return RetailProductReviewScreenData(
        product = RetailProductReviewProductSummary(
            id = productId,
            name = "Kadın klasik sneaker ayakkabı",
            storeName = "Ortobella Store",
            variantText = "Beyaz · 38 numara",
            imageText = "P1"
        ),
        summary = RetailProductReviewSummary(
            averageScoreText = "4.8",
            starText = "★★★★★",
            reviewCount = 126,
            verifiedBuyerCount = 98,
            qualityScoreText = "4.7",
            cargoScoreText = "4.6"
        ),
        filters = listOf(
            "Tümü",
            "Doğrulanmış",
            "Fotoğraflı",
            "Yüksek puan",
            "Beden yorumu",
            "Kargo yorumu"
        ),
        reviews = listOf(
            RetailProductReviewItem(
                id = 1,
                customerName = "Ayşe K.",
                customerInitials = "AK",
                dateText = "2 gün önce",
                ratingText = "5.0",
                comment = "Ürün çok rahat. Normalde 38 giyiyorum, 38 tam oldu. Kargo da hızlı geldi.",
                variantText = "38 numara",
                helpfulCount = 14,
                isVerifiedBuyer = true,
                filterTags = listOf("Doğrulanmış", "Yüksek puan", "Beden yorumu", "Kargo yorumu")
            ),
            RetailProductReviewItem(
                id = 2,
                customerName = "Merve T.",
                customerInitials = "MT",
                dateText = "1 hafta önce",
                ratingText = "4.5",
                comment = "Günlük kullanım için güzel. Kalıbı biraz dar, yarım numara büyük alınabilir.",
                variantText = "37 numara",
                helpfulCount = 8,
                isVerifiedBuyer = true,
                filterTags = listOf("Doğrulanmış", "Beden yorumu")
            ),
            RetailProductReviewItem(
                id = 3,
                customerName = "Selin A.",
                customerInitials = "SA",
                dateText = "2 hafta önce",
                ratingText = "5.0",
                comment = "Rengi fotoğraftaki gibi. Paketleme temizdi, satıcı hızlı gönderdi.",
                variantText = "Beyaz",
                helpfulCount = 21,
                isVerifiedBuyer = true,
                filterTags = listOf("Doğrulanmış", "Fotoğraflı", "Yüksek puan", "Kargo yorumu")
            ),
            RetailProductReviewItem(
                id = 4,
                customerName = "Ece D.",
                customerInitials = "ED",
                dateText = "3 hafta önce",
                ratingText = "4.0",
                comment = "Ürün güzel ama kalıp dar. Değişim süreci sorunsuz ilerledi.",
                variantText = "39 numara",
                helpfulCount = 5,
                isVerifiedBuyer = true,
                filterTags = listOf("Doğrulanmış", "Beden yorumu")
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductReviewScreenPreview() {
    MaterialTheme {
        ProductReviewScreen()
    }
}
