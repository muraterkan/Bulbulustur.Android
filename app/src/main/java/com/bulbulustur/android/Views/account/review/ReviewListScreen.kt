package com.bulbulustur.android.Views.account.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun ReviewListScreen(
    onBackClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onEditReviewClick: (Int) -> Unit = {},
    onDeleteReviewClick: (Int) -> Unit = {}
) {
    val reviews = getDemoReviews()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Değerlendirmelerim",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            if (reviews.isEmpty()) {
                item {
                    ReviewEmptyState()
                }
            }

            items(
                items = reviews,
                key = { review -> review.reviewId }
            ) { review ->
                ReviewCard(
                    review = review,
                    onProductClick = onProductClick,
                    onEditReviewClick = onEditReviewClick,
                    onDeleteReviewClick = onDeleteReviewClick
                )
            }
        }
    }
}

@Composable
private fun ReviewCard(
    review: ReviewUiModel,
    onProductClick: (Int) -> Unit,
    onEditReviewClick: (Int) -> Unit,
    onDeleteReviewClick: (Int) -> Unit
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ReviewProductImagePlaceholder()

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = review.productName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    ReviewRatingRow(
                        rating = review.rating
                    )

                    ReviewStatusBadge(
                        statusText = review.statusText,
                        isApproved = review.isApproved
                    )
                }
            }

            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = review.insertedDate,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "Ürünü Gör",
                    onClick = {
                        onProductClick(review.productId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                BbButton(
                    text = "Düzenle",
                    onClick = {
                        onEditReviewClick(review.reviewId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Small
                )
            }

            BbButton(
                text = "Yorumu Sil",
                onClick = {
                    onDeleteReviewClick(review.reviewId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Danger,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun ReviewEmptyState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            ReviewIconBox()

            Text(
                text = "Henüz değerlendirmeniz yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Siparişleriniz tamamlandıktan sonra ürünler için yorum ve puan bırakabilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReviewRatingRow(
    rating: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Puan:",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = "$rating / 5",
            style = MaterialTheme.typography.labelLarge,
            color = BbColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun ReviewStatusBadge(
    statusText: String,
    isApproved: Boolean
) {
    val backgroundColor = if (isApproved) {
        BbColors.Green.Green50
    } else {
        BbColors.Orange.Orange50
    }

    val textColor = if (isApproved) {
        BbColors.Green.Green700
    } else {
        BbColors.Orange.Orange700
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = statusText,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

@Composable
private fun ReviewProductImagePlaceholder() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space16)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Ürün",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ReviewIconBox() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space12)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.Space2),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "★",
            style = MaterialTheme.typography.headlineSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

private fun getDemoReviews(): List<ReviewUiModel> {
    return listOf(
        ReviewUiModel(
            reviewId = 1,
            productId = 101,
            productName = "Ortobella Comfort Hakiki Deri Topuk Dikeni Terlik M13",
            rating = 5,
            comment = "Ürün rahat, teslimat hızlıydı. Numara tam oldu.",
            statusText = "Yayında",
            isApproved = true,
            insertedDate = "22 Mayıs 2026"
        ),
        ReviewUiModel(
            reviewId = 2,
            productId = 102,
            productName = "Kadın Siyah Kışlık Bot",
            rating = 4,
            comment = "Kalitesi güzel, paketleme daha iyi olabilir.",
            statusText = "İncelemede",
            isApproved = false,
            insertedDate = "18 Mayıs 2026"
        )
    )
}

private data class ReviewUiModel(
    val reviewId: Int,
    val productId: Int,
    val productName: String,
    val rating: Int,
    val comment: String,
    val statusText: String,
    val isApproved: Boolean,
    val insertedDate: String
)