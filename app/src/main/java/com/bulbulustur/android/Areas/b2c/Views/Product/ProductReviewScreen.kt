package com.bulbulustur.android.Areas.b2c.Views.Product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

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

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Ürün Yorumları",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.SectionGapCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
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

            items(
                items = filteredReviews,
                key = { review -> review.id }
            ) { review ->
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
private fun ProductReviewProductSummary(
    product: RetailProductReviewProductSummary
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BbRadius.XxlShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.CardPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space18)
                    .clip(BbRadius.XlShape)
                    .background(BbColors.Primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextSubtle
                )

                Text(
                    text = product.variantText,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BbColors.TextStrong
                )
            }
        }
    }
}

@Composable
private fun ProductReviewScoreSummary(
    summary: RetailProductReviewSummary
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
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
                        color = BbColors.Primary
                    )

                    Text(
                        text = summary.starText,
                        style = MaterialTheme.typography.titleMedium,
                        color = BbColors.Primary
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${summary.reviewCount} yorum",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = "${summary.verifiedBuyerCount} doğrulanmış alışveriş",
                        style = MaterialTheme.typography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextMuted
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
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        ProductReviewSectionTitle(
            title = "Yorum filtresi",
            description = "Yorumları deneyim türüne göre daralt."
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(BbRadius.IconBoxSoft)
                        .background(BbColors.SurfaceMuted),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = review.customerInitials,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.TextStrong
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = review.customerName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = review.dateText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.TextMuted
                    )
                }

                Text(
                    text = review.ratingText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Primary
                )
            }

            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextStrong
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextMuted
        )
    }
}

@Composable
private fun ProductReviewSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextMuted
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

private fun getRetailProductReviewScreenData(
    productId: Int
): RetailProductReviewScreenData {
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
    BbTheme {
        ProductReviewScreen()
    }
}