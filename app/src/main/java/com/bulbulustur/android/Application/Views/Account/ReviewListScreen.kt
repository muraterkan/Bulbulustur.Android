package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Domain
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun ReviewListScreen(
    onBackClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onEditReviewClick: (Int) -> Unit = {},
    onDeleteReviewClick: (Int) -> Unit = {}
) {
    var selectedTab by remember {
        mutableStateOf(AccountReviewTab.Product)
    }

    val reviews = getDemoReviews(selectedTab)

    Scaffold(
        containerColor = BBColors.SurfaceMuted,
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
                .background(BBColors.SurfaceMuted)
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
                    onTabSelected = { tab ->
                        selectedTab = tab
                    }
                )
            }

            if (reviews.isEmpty()) {
                item {
                    ReviewEmptyState(selectedTab = selectedTab)
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
                text = "Alışveriş deneyimi",
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.Yellow.Yellow800
            )

            Text(
                text = "Ürün, mağaza ve firma değerlendirmelerinizi burada görüntüleyebilir, gerektiğinde düzenleyebilir veya silebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReviewTabContainer(
    selectedTab: AccountReviewTab,
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
                count = 5,
                icon = Icons.Outlined.Comment,
                onClick = onTabSelected
            )

            ReviewTabItem(
                modifier = Modifier.weight(1f),
                tab = AccountReviewTab.Store,
                selected = selectedTab == AccountReviewTab.Store,
                count = 5,
                icon = Icons.Outlined.Storefront,
                onClick = onTabSelected
            )

            ReviewTabItem(
                modifier = Modifier.weight(1f),
                tab = AccountReviewTab.Company,
                selected = selectedTab == AccountReviewTab.Company,
                count = 5,
                icon = Icons.Outlined.Domain,
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
    icon: ImageVector,
    onClick: (AccountReviewTab) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = if (selected) BBColors.Surface else MaterialTheme.colorScheme.surfaceVariant,
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
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) BBColors.Yellow.Yellow800 else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Action)
            )

            Text(
                text = tab.title,
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
    review: ReviewUiModel,
    onProductClick: (Int) -> Unit,
    onEditReviewClick: (Int) -> Unit,
    onDeleteReviewClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ReviewMediaArea(review = review)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                ReviewTypeBadge(text = review.badgeText)

                Text(
                    text = review.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                ReviewRatingRow(rating = review.rating)

                Text(
                    text = review.comment,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                ReviewDateBox(dateText = review.dateText)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    BbButton(
                        text = review.primaryActionText,
                        onClick = {
                            onProductClick(review.targetId)
                        },
                        modifier = Modifier.weight(1f),
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

                    BbButton(
                        text = "Düzenle",
                        onClick = {
                            onEditReviewClick(review.reviewId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(BBIcon.ButtonIcon)
                            )
                        }
                    )
                }

                BbButton(
                    text = "Değerlendirmeyi Sil",
                    onClick = {
                        onDeleteReviewClick(review.reviewId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Danger,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.DeleteOutline,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onError,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ReviewMediaArea(
    review: ReviewUiModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.65f)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        if (review.imageResId != null) {
            Image(
                painter = painterResource(id = review.imageResId),
                contentDescription = review.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .size(BBSpacing.Space20)
                    .background(
                        color = review.mediaBackgroundColor,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Image,
                    contentDescription = null,
                    tint = review.mediaTextColor,
                    modifier = Modifier.size(BBIcon.Feature)
                )
            }
        }
    }
}

@Composable
private fun ReviewTypeBadge(
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                color = BBColors.Yellow.Yellow100,
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
private fun ReviewRatingRow(
    rating: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if (index < rating) BBColors.Yellow.Yellow800 else BBColors.BorderStrong,
                modifier = Modifier.size(BBIcon.Inline)
            )
        }

        Text(
            text = "$rating / 5",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ReviewDateBox(
    dateText: String
) {
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
private fun ReviewEmptyState(
    selectedTab: AccountReviewTab
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
            Box(
                modifier = Modifier
                    .size(BBSpacing.Space12)
                    .background(
                        color = BBColors.Yellow.Yellow100,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = selectedTab.icon,
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
                text = "Henüz ${selectedTab.emptyText} bulunmuyor. Değerlendirme yaptığınızda burada listelenecek.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getDemoReviews(
    selectedTab: AccountReviewTab
): List<ReviewUiModel> {
    return when (selectedTab) {
        AccountReviewTab.Product -> getDemoProductReviews()
        AccountReviewTab.Store -> getDemoStoreReviews()
        AccountReviewTab.Company -> getDemoCompanyReviews()
    }
}

private fun getDemoProductReviews(): List<ReviewUiModel> {
    return listOf(
        ReviewUiModel(
            reviewId = 1,
            targetId = 101,
            type = AccountReviewTab.Product,
            title = "Ortobella Comfort Genç Garson Bot 8028",
            badgeText = "ÜRÜN DE�?ERLENDİRMESİ",
            comment = "Makineyi 7 gündür kullanıyorum. Bu süre içinde hem deneyim kazandım hem de internetten okuduğum birçok bilgiyle karşılaştırdım. Ürünü almayı düşünenler için detaylı bir deneyim paylaşmak istedim.",
            rating = 5,
            dateText = "22 Eylül 2025 00:31 tarihinde eklendi",
            mediaBackgroundColor = BBColors.Beige.Beige200,
            mediaTextColor = BBColors.Beige.Beige900,
            imageResId = null
        )
    )
}

private fun getDemoStoreReviews(): List<ReviewUiModel> {
    return listOf(
        ReviewUiModel(
            reviewId = 3,
            targetId = 501,
            type = AccountReviewTab.Store,
            title = "Ortobella Comfort",
            badgeText = "MA�?AZA DE�?ERLENDİRMESİ",
            comment = "Mağaza iletişimi hızlıydı. Sipariş süreci ve kargo bilgilendirmesi anlaşılır şekilde ilerledi.",
            rating = 5,
            dateText = "12 Ekim 2025 14:20 tarihinde eklendi",
            mediaBackgroundColor = BBColors.Green.Green700,
            mediaTextColor = BBColors.White,
            imageResId = null
        )
    )
}

private fun getDemoCompanyReviews(): List<ReviewUiModel> {
    return listOf(
        ReviewUiModel(
            reviewId = 4,
            targetId = 701,
            type = AccountReviewTab.Company,
            title = "Citrix Tedarik",
            badgeText = "FİRMA DE�?ERLENDİRMESİ",
            comment = "Firma bilgileri netti, ürün açıklamaları yeterliydi. Toptan iletişim tarafında hızlı dönüş aldım.",
            rating = 5,
            dateText = "10 Ekim 2025 09:12 tarihinde eklendi",
            mediaBackgroundColor = BBColors.Green.Green700,
            mediaTextColor = BBColors.White,
            imageResId = null
        )
    )
}

private enum class AccountReviewTab(
    val title: String,
    val emptyText: String,
    val icon: ImageVector
) {
    Product(
        title = "Ürün",
        emptyText = "ürün değerlendirmesi",
        icon = Icons.Outlined.Comment
    ),
    Store(
        title = "Mağaza",
        emptyText = "mağaza değerlendirmesi",
        icon = Icons.Outlined.Storefront
    ),
    Company(
        title = "Firma",
        emptyText = "firma değerlendirmesi",
        icon = Icons.Outlined.Domain
    )
}

private val ReviewUiModel.primaryActionText: String
    get() = when (type) {
        AccountReviewTab.Product -> "Ürünü Gör"
        AccountReviewTab.Store -> "Mağazayı Gör"
        AccountReviewTab.Company -> "Firmayı Gör"
    }

private data class ReviewUiModel(
    val reviewId: Int,
    val targetId: Int,
    val type: AccountReviewTab,
    val title: String,
    val badgeText: String,
    val comment: String,
    val rating: Int,
    val dateText: String,
    val mediaBackgroundColor: Color,
    val mediaTextColor: Color,
    val imageResId: Int?
)

