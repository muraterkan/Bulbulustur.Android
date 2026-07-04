package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Domain
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.CircularProgressIndicator
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
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

private enum class AccountReviewTab(
    val SourceType: String,
    val Title: String,
    val EmptyText: String,
    val Icon: ImageVector
) {
    Product(
        SourceType = "PRODUCT",
        Title = "Ürün",
        EmptyText = "ürün değerlendirmeniz",
        Icon = Icons.Outlined.Comment
    ),
    Store(
        SourceType = "STORE",
        Title = "Mağaza",
        EmptyText = "mağaza değerlendirmeniz",
        Icon = Icons.Outlined.Storefront
    ),
    Company(
        SourceType = "COMPANY",
        Title = "Firma",
        EmptyText = "firma değerlendirmeniz",
        Icon = Icons.Outlined.Domain
    )
}

@Composable
fun ReviewListScreen(
    reviews: List<ReviewDTO>,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onProductClick: (productId: Int, variantId: Int, reviewId: Int) -> Unit = { _, _, _ -> },
    onStoreClick: (storeId: Int) -> Unit = {},
    onCompanyClick: (companyId: Int) -> Unit = {},
    onEditReviewClick: (reviewId: Int) -> Unit = {},
    onDeleteReviewClick: (reviewId: Int) -> Unit = {}
) {
    var selectedTab by remember {
        mutableStateOf(AccountReviewTab.Product)
    }

    val productCount = reviews.count {
        it.SourceType.equals(AccountReviewTab.Product.SourceType, ignoreCase = true)
    }

    val storeCount = reviews.count {
        it.SourceType.equals(AccountReviewTab.Store.SourceType, ignoreCase = true)
    }

    val companyCount = reviews.count {
        it.SourceType.equals(AccountReviewTab.Company.SourceType, ignoreCase = true)
    }

    val filteredReviews = reviews.filter {
        it.SourceType.equals(selectedTab.SourceType, ignoreCase = true)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                ReviewIntroCard()
            }

            item {
                ReviewTabContainer(
                    selectedTab = selectedTab,
                    productCount = productCount,
                    storeCount = storeCount,
                    companyCount = companyCount,
                    onTabSelected = {
                        selectedTab = it
                    }
                )
            }

            when {
                isLoading -> {
                    item {
                        ReviewLoadingState()
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    item {
                        ReviewErrorState(
                            message = errorMessage,
                            onRetryClick = onRetryClick
                        )
                    }
                }

                filteredReviews.isEmpty() -> {
                    item {
                        ReviewEmptyState(selectedTab = selectedTab)
                    }
                }

                else -> {
                    items(
                        items = filteredReviews,
                        key = { it.ReviewId }
                    ) { review ->
                        ReviewCard(
                            review = review,
                            onProductClick = onProductClick,
                            onStoreClick = onStoreClick,
                            onCompanyClick = onCompanyClick,
                            onEditReviewClick = onEditReviewClick,
                            onDeleteReviewClick = onDeleteReviewClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ReviewIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = "Yorum Geçmişi",
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.Yellow.Yellow800
            )

            Text(
                text = "Ürünler, mağazalar ve firmalar hakkında yaptığınız değerlendirmeleri burada görüntüleyebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReviewTabContainer(
    selectedTab: AccountReviewTab,
    productCount: Int,
    storeCount: Int,
    companyCount: Int,
    onTabSelected: (AccountReviewTab) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            ReviewTabItem(
                modifier = Modifier.weight(1f),
                tab = AccountReviewTab.Product,
                selected = selectedTab == AccountReviewTab.Product,
                count = productCount,
                onClick = onTabSelected
            )

            ReviewTabItem(
                modifier = Modifier.weight(1f),
                tab = AccountReviewTab.Store,
                selected = selectedTab == AccountReviewTab.Store,
                count = storeCount,
                onClick = onTabSelected
            )

            ReviewTabItem(
                modifier = Modifier.weight(1f),
                tab = AccountReviewTab.Company,
                selected = selectedTab == AccountReviewTab.Company,
                count = companyCount,
                onClick = onTabSelected
            )
        }
    }
}

@Composable
private fun ReviewTabItem(
    modifier: Modifier,
    tab: AccountReviewTab,
    selected: Boolean,
    count: Int,
    onClick: (AccountReviewTab) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = if (selected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .clickable {
                onClick(tab)
            }
            .padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space3
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = tab.Icon,
                contentDescription = null,
                tint = if (selected) BBColors.Yellow.Yellow800 else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Action)
            )

            Text(
                text = tab.Title,
                style = MaterialTheme.typography.labelSmall,
                color = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = count.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.Yellow.Yellow800
            )
        }
    }
}

@Composable
private fun ReviewCard(
    review: ReviewDTO,
    onProductClick: (productId: Int, variantId: Int, reviewId: Int) -> Unit,
    onStoreClick: (storeId: Int) -> Unit,
    onCompanyClick: (companyId: Int) -> Unit,
    onEditReviewClick: (reviewId: Int) -> Unit,
    onDeleteReviewClick: (reviewId: Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            ReviewMediaArea()

            ReviewTypeBadge(
                text = review.SourceType.ToReviewBadgeText()
            )

            Text(
                text = review.GetReviewTitle(),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            ReviewRatingRow(rating = review.Rating)

            Text(
                text = review.Content.orEmpty().ifBlank {
                    "Değerlendirme metni bulunmuyor."
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            ReviewDateBox(
                dateText = review.InsertedDate.ToReviewDateText()
            )

            BbButton(
                text = review.SourceType.ToTargetActionText(),
                onClick = {
                    when {
                        review.SourceType.equals("PRODUCT", ignoreCase = true) -> {
                            onProductClick(
                                review.ItemId,
                                review.VariantId ?: 0,
                                review.ReviewId
                            )
                        }

                        review.SourceType.equals("STORE", ignoreCase = true) -> {
                            onStoreClick(review.ItemId)
                        }

                        review.SourceType.equals("COMPANY", ignoreCase = true) -> {
                            onCompanyClick(review.ItemId)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Visibility,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )

            /*
            Düzenleme ve silme endpointleri kesinleştikten sonra açılacak.

            BbButton(
                text = "Düzenle",
                onClick = {
                    onEditReviewClick(review.ReviewId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Small
            )

            BbButton(
                text = "Değerlendirmeyi Sil",
                onClick = {
                    onDeleteReviewClick(review.ReviewId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Danger,
                size = BbButtonSize.Small
            )
            */
        }
    }
}

@Composable
private fun ReviewMediaArea() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.Space8),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BBIcon.Feature)
        )
    }
}

@Composable
private fun ReviewTypeBadge(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun ReviewRatingRow(rating: Double) {
    val normalizedRating = rating
        .coerceIn(0.0, 5.0)
        .roundToInt()

    Row(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if (index < normalizedRating) BBColors.Yellow.Yellow800 else MaterialTheme.colorScheme.outlineVariant,
                modifier = Modifier.size(BBIcon.Inline)
            )
        }

        Text(
            text = "${reviewRatingText(rating)} / 5",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ReviewDateBox(dateText: String) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.CalendarMonth,
                contentDescription = null,
                tint = BBColors.Yellow.Yellow800,
                modifier = Modifier.size(BBIcon.Inline)
            )

            Text(
                text = dateText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReviewLoadingState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun ReviewErrorState(
    message: String,
    onRetryClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.ErrorOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(BBIcon.Empty)
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Tekrar Dene",
                onClick = onRetryClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun ReviewEmptyState(selectedTab: AccountReviewTab) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BBSpacing.Space12)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = selectedTab.Icon,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBIcon.Feature)
                )
            }

            Text(
                text = "Kayıt bulunamadı",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Henüz ${selectedTab.EmptyText} bulunmuyor.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun ReviewDTO.GetReviewTitle(): String {
    return ProductName
        ?.takeIf { it.isNotBlank() }
        ?: Fullname
            ?.takeIf { it.isNotBlank() }
        ?: listOfNotNull(Name, Surname)
            .joinToString(" ")
            .trim()
            .takeIf { it.isNotBlank() }
        ?: when {
            SourceType.equals("PRODUCT", ignoreCase = true) -> "Ürün değerlendirmesi"
            SourceType.equals("STORE", ignoreCase = true) -> "Mağaza değerlendirmesi"
            SourceType.equals("COMPANY", ignoreCase = true) -> "Firma değerlendirmesi"
            else -> "Değerlendirme"
        }
}

private fun String?.ToReviewBadgeText(): String {
    return when {
        equals("PRODUCT", ignoreCase = true) -> "ÜRÜN DEĞERLENDİRMESİ"
        equals("STORE", ignoreCase = true) -> "MAĞAZA DEĞERLENDİRMESİ"
        equals("COMPANY", ignoreCase = true) -> "FİRMA DEĞERLENDİRMESİ"
        else -> "DEĞERLENDİRME"
    }
}

private fun String?.ToTargetActionText(): String {
    return when {
        equals("PRODUCT", ignoreCase = true) -> "Ürünü Gör"
        equals("STORE", ignoreCase = true) -> "Mağazayı Gör"
        equals("COMPANY", ignoreCase = true) -> "Firmayı Gör"
        else -> "İlgili Kaydı Gör"
    }
}

private fun String?.ToReviewDateText(): String {
    if (isNullOrBlank()) return "-"

    return runCatching {
        OffsetDateTime
            .parse(this)
            .format(
                DateTimeFormatter.ofPattern(
                    "dd MMMM yyyy",
                    Locale("tr", "TR")
                )
            )
    }.getOrElse {
        substringBefore("T")
    }
}

private fun reviewRatingText(rating: Double): String {
    return if (rating % 1.0 == 0.0) {
        rating.toInt().toString()
    } else {
        String.format(Locale("tr", "TR"), "%.1f", rating)
    }
}