package com.bulbulustur.android.Application.Areas.b2c.Views.Product

import com.bulbulustur.android.Application.Localization.BBLocalization

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
import androidx.compose.ui.layout.ContentScale
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
import coil3.compose.AsyncImage
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver



@Composable
fun ProductReviewScreen(
    State: ProductReviewControllerState = ProductReviewControllerState(),
    product: ProductDTO? = null,
    productPicture: String = "",
    onBackClick: () -> Unit = {},
    onLoadMoreClick: () -> Unit = {},
    onReviewClick: (ReviewDTO) -> Unit = {}
) {
    var selectedFilter by
    remember {
        mutableStateOf(
            BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü")
        )
    }

    val filters =
        remember {
            listOf(
                BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü"),
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
            if (selectedFilter == BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü")) {
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
                    BBLocalization.Current.Get(key = "47459db5-2c10-463e-9c78-aba51e39f219", fallback = "Ürün Değerlendirmeleri"),
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
                    product = product,
                    productPicture = productPicture
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
                                BBLocalization.Current.Get(key = "4a26c4e4-5c82-4c19-9e36-08cb0dadf54f", fallback = "Değerlendirmeler alınamadı"),
                            description =
                                State.ErrorMessage
                        )
                    }
                }

                State.Reviews.isEmpty() -> {
                    item {
                        ProductReviewMessageCard(
                            title =
                                BBLocalization.Current.Get(key = "fec90366-5731-4eb2-8dae-e72c6401e863", fallback = "Henüz değerlendirme yok"),
                            description =
                                BBLocalization.Current.Get(key = "7618039b-9a62-4817-8fec-2ee5d0ec57e0", fallback = "Bu ürün için yayınlanmış bir müşteri değerlendirmesi bulunmuyor.")
                        )
                    }
                }

                filteredReviews.isEmpty() -> {
                    item {
                        ProductReviewMessageCard(
                            title =
                                BBLocalization.Current.Get(key = "f794ffc2-c7b8-4fbe-ba85-7d5a3d5d2414", fallback = "Bu filtrede yorum yok"),
                            description =
                                BBLocalization.Current.Get(key = "8f062c74-33b1-450f-a1dc-dc1cc2b0be91", fallback = "Seçtiğiniz puana ait değerlendirme bulunamadı.")
                        )
                    }
                }

                else -> {
                    item {
                        ProductReviewSectionTitle(
                            title =
                                BBLocalization.Current.Get(key = "64cd190f-4f96-4edf-85be-b1ef1778e3a9", fallback = "Müşteri Değerlendirmeleri"),
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
                                        BBLocalization.Current.Get(key = "3d602cfc-e863-482d-8f87-e76b8fa32f0c", fallback = "Yükleniyor")
                                    } else {
                                        BBLocalization.Current.Get(key = "e67b3c5d-fbdd-4f49-bb8f-72be40be9086", fallback = "Daha Fazla Göster")
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
    product: ProductDTO?,
    productPicture: String
) {
    val productName = product?.ProductName?.takeIf { it.isNotBlank() } ?: BBLocalization.Current.Get(key = "47459db5-2c10-463e-9c78-aba51e39f219", fallback = "Ürün Değerlendirmeleri")
    val storeName = product?.Store?.takeIf { it.isNotBlank() } ?: BBLocalization.Current.Get(key = "2132e096-e397-4fc1-bb82-793c20e3fee2", fallback = "Satıcı bilgisi")
    val variantText = listOfNotNull(product?.Color?.takeIf { it.isNotBlank() }, product?.Size?.takeIf { it.isNotBlank() }).joinToString(separator = " · ")
    val imageText = productName.ToInitials(fallback = "Ü")

    val productImageUrl = ImageUrlResolver.Resolve(
        imagePath = productPicture
            .ifBlank { product?.DefaultPicture.orEmpty() }
            .ifBlank { product?.Picture.orEmpty() }
    )

    var imageLoadFailed by remember(productImageUrl) { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(BBSpacing.CardPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBSpacing.Space18),
                shape = BBRadius.XlShape,
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (productImageUrl.isNotBlank() && !imageLoadFailed) {
                        AsyncImage(
                            model = productImageUrl,
                            contentDescription = productName,
                            modifier = Modifier.fillMaxSize().padding(BBSpacing.Space1),
                            contentScale = ContentScale.Fit,
                            onError = { imageLoadFailed = true }
                        )
                    } else {
                        Text(
                            text = imageText,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = productName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (variantText.isNotBlank()) {
                    Text(
                        text = variantText,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
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
                        BBLocalization.Current.Get(key = "0337270f-cfb6-4adc-9302-7c5c49587f91", fallback = "Yayınlanmış değerlendirmeler"),
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
            title = BBLocalization.Current.Get(key = "05bdf6dd-a76d-47be-bc76-1e1b75dfc5b0", fallback = "Puan Filtresi"),
            description = BBLocalization.Current.Get(key = "c63bb455-d4d9-428d-bcf0-97eda989a627", fallback = "Değerlendirmeleri verilen puana göre filtreleyin.")
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
                        BBLocalization.Current.Get(key = "75868752-adb9-4e92-af8f-945eac590db7", fallback = "Değerlendirme metni bulunmuyor.")
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
        ?.takeIf {
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
                separator = " "
            )
            .takeIf {
                it.isNotBlank()
            }
        ?: BBLocalization.Current.Get(key = "3a8d29be-870c-414f-bb7c-221b560b299e", fallback = "Müşteri")
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