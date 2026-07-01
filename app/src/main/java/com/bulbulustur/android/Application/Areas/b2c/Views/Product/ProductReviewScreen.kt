package com.bulbulustur.android.Application.Areas.b2c.Views.Product

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
import androidx.compose.material3.CircularProgressIndicator
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
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductReviewControllerState
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import java.util.Locale
import kotlin.math.roundToInt
import androidx.compose.ui.unit.dp


@Composable
fun ProductReviewScreen(
    State: ProductReviewControllerState =
        ProductReviewControllerState(),
    product: ProductDTO? = null,
    onBackClick: () -> Unit = {},
    onLoadMoreClick: () -> Unit = {},
    onReviewClick: (ReviewDTO) -> Unit = {}
) {
    var selectedFilter by
    remember {
        mutableStateOf(
            "Tümü"
        )
    }

    val filters =
        remember {
            listOf(
                "Tümü",
                "5 Yıldız",
                "4 Yıldız",
                "3 Yıldız",
                "2 Yıldız",
                "1 Yıldız"
            )
        }

    val filteredReviews =
        remember(
            selectedFilter,
            State.Reviews
        ) {
            if (selectedFilter == "Tümü") {
                State.Reviews
            } else {
                val selectedRating =
                    selectedFilter
                        .substringBefore(
                            " "
                        )
                        .toIntOrNull()

                State.Reviews.filter {
                    it.Rating
                        .roundToInt() ==
                            selectedRating
                }
            }
        }

    val summary =
        remember(
            State.Reviews
        ) {
            State.Reviews.ToReviewSummary()
        }

    Scaffold(
        containerColor =
            MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title =
                    "Ürün Yorumları",
                onBackClick =
                    onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                    .padding(
                        innerPadding
                    )
                    .navigationBarsPadding(),
            contentPadding =
                PaddingValues(
                    start =
                        BBSpacing.PageHorizontal,
                    top =
                        BBSpacing.SectionGapCompact,
                    end =
                        BBSpacing.PageHorizontal,
                    bottom =
                        BBSpacing.PageBottom
                ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.CardGap
                )
        ) {
            item {
                ProductReviewProductSummary(
                    product =
                        product
                )
            }

            item {
                ProductReviewScoreSummary(
                    summary =
                        summary
                )
            }

            item {
                ProductReviewFilterSection(
                    filters =
                        filters,
                    selectedFilter =
                        selectedFilter,
                    onFilterChange = {
                        selectedFilter =
                            it
                    }
                )
            }

            when {
                State.IsLoading &&
                        State.Reviews.isEmpty() -> {
                    item {
                        ProductReviewLoadingCard()
                    }
                }

                State.ErrorMessage != null &&
                        State.Reviews.isEmpty() -> {
                    item {
                        ProductReviewMessageCard(
                            title =
                                "Değerlendirmeler alınamadı",
                            description =
                                State.ErrorMessage
                        )
                    }
                }

                State.Reviews.isEmpty() -> {
                    item {
                        ProductReviewMessageCard(
                            title =
                                "Henüz değerlendirme yok",
                            description =
                                "Bu ürün için yayınlanmış bir müşteri değerlendirmesi bulunmuyor."
                        )
                    }
                }

                filteredReviews.isEmpty() -> {
                    item {
                        ProductReviewMessageCard(
                            title =
                                "Bu filtrede yorum yok",
                            description =
                                "Seçtiğiniz puana ait değerlendirme bulunamadı."
                        )
                    }
                }

                else -> {
                    item {
                        ProductReviewSectionTitle(
                            title =
                                "Müşteri yorumları",
                            description =
                                "${filteredReviews.size} değerlendirme gösteriliyor."
                        )
                    }

                    items(
                        items =
                            filteredReviews,
                        key = {
                            it.ReviewId
                        }
                    ) { review ->
                        ProductReviewCard(
                            review =
                                review,
                            onClick = {
                                onReviewClick(
                                    review
                                )
                            }
                        )
                    }

                    if (
                        State.HasNextPage
                    ) {
                        item {
                            BbButton(
                                text =
                                    if (State.IsLoading) {
                                        "Yükleniyor"
                                    } else {
                                        "Daha Fazla Göster"
                                    },
                                onClick =
                                    onLoadMoreClick,
                                modifier =
                                    Modifier.fillMaxWidth(),
                                variant =
                                    BbButtonVariant.Secondary,
                                size =
                                    BbButtonSize.Medium,
                                enabled =
                                    !State.IsLoading
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductReviewProductSummary(
    product: ProductDTO?
) {
    val productName =
        product
            ?.ProductName
            ?.takeIf {
                it.isNotBlank()
            }
            ?: "Ürün Değerlendirmeleri"

    val storeName =
        product
            ?.Store
            ?.takeIf {
                it.isNotBlank()
            }
            ?: "Satıcı bilgisi"

    val variantText =
        listOfNotNull(
            product
                ?.Color
                ?.takeIf {
                    it.isNotBlank()
                },
            product
                ?.Size
                ?.takeIf {
                    it.isNotBlank()
                }
        ).joinToString(
            separator =
                " · "
        )

    val imageText =
        productName
            .ToInitials(
                fallback =
                    "Ü"
            )

    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            BBRadius.XxlShape,
        color =
            MaterialTheme.colorScheme.primaryContainer,
        border =
            BorderStroke(
                width =
                    1.dp,
                color =
                    MaterialTheme.colorScheme.primary
                        .copy(
                            alpha =
                                0.35f
                        )
            )
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        BBSpacing.CardPadding
                    ),
            verticalAlignment =
                Alignment.CenterVertically,
            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                )
        ) {
            Box(
                modifier =
                    Modifier
                        .size(
                            BBSpacing.Space18
                        )
                        .clip(
                            BBRadius.XlShape
                        )
                        .background(
                            MaterialTheme.colorScheme.primary
                        ),
                contentAlignment =
                    Alignment.Center
            ) {
                Text(
                    text =
                        imageText,
                    style =
                        MaterialTheme.typography.titleMedium,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier =
                    Modifier.weight(
                        1f
                    ),
                verticalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
            ) {
                Text(
                    text =
                        productName,
                    style =
                        MaterialTheme.typography.titleMedium,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text =
                        storeName,
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (
                    variantText.isNotBlank()
                ) {
                    Text(
                        text =
                            variantText,
                        style =
                            MaterialTheme.typography.labelMedium,
                        fontWeight =
                            FontWeight.SemiBold,
                        color =
                            MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductReviewScoreSummary(
    summary: RetailProductReviewSummary
) {
    BbCard(
        modifier =
            Modifier.fillMaxWidth(),
        variant =
            BbCardVariant.Outlined,
        padding =
            BbCardPadding.Medium
    ) {
        Row(
            modifier =
                Modifier.fillMaxWidth(),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Column(
                modifier =
                    Modifier.weight(
                        1f
                    )
            ) {
                Text(
                    text =
                        summary.averageScoreText,
                    style =
                        MaterialTheme.typography.headlineLarge,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.primary
                )

                Text(
                    text =
                        summary.starText,
                    style =
                        MaterialTheme.typography.titleMedium,
                    color =
                        MaterialTheme.colorScheme.primary
                )
            }

            Column(
                horizontalAlignment =
                    Alignment.End
            ) {
                Text(
                    text =
                        "${summary.reviewCount} yorum",
                    style =
                        MaterialTheme.typography.titleSmall,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text =
                        "Yayınlanmış değerlendirmeler",
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(
    ExperimentalLayoutApi::class
)
@Composable
private fun ProductReviewFilterSection(
    filters: List<String>,
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    Column(
        modifier =
            Modifier.fillMaxWidth(),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        ProductReviewSectionTitle(
            title =
                "Puan Filtresi",
            description =
                "Değerlendirmeleri verilen puana göre filtreleyin."
        )

        FlowRow(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {
            filters.forEach {
                FilterChip(
                    selected =
                        selectedFilter == it,
                    onClick = {
                        onFilterChange(
                            it
                        )
                    },
                    label = {
                        Text(
                            text =
                                it
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductReviewCard(
    review: ReviewDTO,
    onClick: () -> Unit
) {
    val customerName =
        review.ResolveCustomerName()

    val locationText =
        listOfNotNull(
            review.CountryName
                .takeIf {
                    it.isNotBlank()
                },
            review.CityName
                .takeIf {
                    it.isNotBlank()
                }
        ).joinToString(
            separator =
                " · "
        )

    val variantText =
        review.VariantId
            ?.takeIf {
                it > 0
            }
            ?.let {
                "Varyant #$it"
            }
            .orEmpty()

    BbCard(
        modifier =
            Modifier.fillMaxWidth(),
        variant =
            BbCardVariant.Outlined,
        padding =
            BbCardPadding.Medium,
        onClick =
            onClick
    ) {
        Column(
            modifier =
                Modifier.fillMaxWidth(),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                )
        ) {
            Row(
                verticalAlignment =
                    Alignment.CenterVertically,
                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space3
                    )
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(
                                BBSpacing.Space12
                            )
                            .clip(
                                BBRadius.IconBoxSoft
                            )
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text =
                            customerName.ToInitials(
                                fallback =
                                    "M"
                            ),
                        style =
                            MaterialTheme.typography.labelLarge,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme.colorScheme.onSurface
                    )
                }

                Column(
                    modifier =
                        Modifier.weight(
                            1f
                        )
                ) {
                    Text(
                        text =
                            customerName,
                        style =
                            MaterialTheme.typography.titleSmall,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text =
                            review.InsertedDate.ToReadableDate(),
                        style =
                            MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text =
                        String.format(
                            Locale.getDefault(),
                            "%.1f",
                            review.Rating
                        ),
                    style =
                        MaterialTheme.typography.titleMedium,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text =
                    review.Content.ifBlank {
                        "Değerlendirme metni bulunmuyor."
                    },
                style =
                    MaterialTheme.typography.bodyMedium,
                color =
                    MaterialTheme.colorScheme.onSurface
            )

            FlowRow(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space2
                    ),
                verticalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
            ) {
                if (
                    locationText.isNotBlank()
                ) {
                    ProductReviewBadge(
                        text =
                            locationText
                    )
                }

                if (
                    variantText.isNotBlank()
                ) {
                    ProductReviewBadge(
                        text =
                            variantText
                    )
                }

                if (
                    review.ReviewPictures.isNotEmpty()
                ) {
                    ProductReviewBadge(
                        text =
                            "${review.ReviewPictures.size} fotoğraf"
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
        shape =
            BBRadius.PillShape,
        color =
            MaterialTheme.colorScheme.surfaceVariant,
        border =
            BorderStroke(
                width =
                    1.dp,
                color =
                    MaterialTheme.colorScheme.outlineVariant
            )
    ) {
        Text(
            text =
                text,
            modifier =
                Modifier.padding(
                    horizontal =
                        BBSpacing.Space3,
                    vertical =
                        BBSpacing.Space1
                ),
            style =
                MaterialTheme.typography.labelSmall,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProductReviewLoadingCard() {
    BbCard(
        modifier =
            Modifier.fillMaxWidth(),
        variant =
            BbCardVariant.Outlined,
        padding =
            BbCardPadding.Large
    ) {
        Box(
            modifier =
                Modifier.fillMaxWidth(),
            contentAlignment =
                Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun ProductReviewMessageCard(
    title: String,
    description: String
) {
    BbCard(
        modifier =
            Modifier.fillMaxWidth(),
        variant =
            BbCardVariant.Outlined,
        padding =
            BbCardPadding.Large
    ) {
        Column(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalAlignment =
                Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {
            Text(
                text =
                    title,
                style =
                    MaterialTheme.typography.titleMedium,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onSurface
            )

            Text(
                text =
                    description,
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductReviewSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier =
            Modifier.fillMaxWidth(),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space1
            )
    ) {
        Text(
            text =
                title,
            style =
                MaterialTheme.typography.titleMedium,
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onSurface
        )

        Text(
            text =
                description,
            style =
                MaterialTheme.typography.bodySmall,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class RetailProductReviewSummary(
    val averageScoreText: String,
    val starText: String,
    val reviewCount: Int
)

private fun List<ReviewDTO>.ToReviewSummary():
        RetailProductReviewSummary {
    val averageRating =
        if (isEmpty()) {
            0.0
        } else {
            map {
                it.Rating
            }.average()
        }

    val fullStarCount =
        averageRating
            .roundToInt()
            .coerceIn(
                0,
                5
            )

    return RetailProductReviewSummary(
        averageScoreText =
            String.format(
                Locale.getDefault(),
                "%.1f",
                averageRating
            ),
        starText =
            buildString {
                repeat(
                    fullStarCount
                ) {
                    append(
                        "★"
                    )
                }

                repeat(
                    5 -
                            fullStarCount
                ) {
                    append(
                        "☆"
                    )
                }
            },
        reviewCount =
            size
    )
}

private fun ReviewDTO.ResolveCustomerName(): String {
    return Fullname
        .takeIf {
            it.isNotBlank()
        }
        ?: listOf(
            Name,
            Surname
        )
            .filter {
                it.isNotBlank()
            }
            .joinToString(
                separator =
                    " "
            )
            .takeIf {
                it.isNotBlank()
            }
        ?: "Müşteri"
}

private fun String.ToInitials(
    fallback: String
): String {
    return trim()
        .split(
            " "
        )
        .filter {
            it.isNotBlank()
        }
        .take(
            2
        )
        .mapNotNull {
            it.firstOrNull()
        }
        .joinToString(
            separator =
                ""
        )
        .uppercase()
        .ifBlank {
            fallback
        }
}

private fun String.ToReadableDate(): String {
    if (isBlank()) {
        return ""
    }

    return substringBefore(
        "T"
    )
        .split(
            "-"
        )
        .takeIf {
            it.size == 3
        }
        ?.let {
            "${it[2]}.${it[1]}.${it[0]}"
        }
        ?: this
}

@Preview(
    showBackground =
        true
)
@Composable
private fun ProductReviewScreenPreview() {
    BbTheme {
        ProductReviewScreen()
    }
}