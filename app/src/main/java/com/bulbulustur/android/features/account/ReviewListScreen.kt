package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Domain
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun ReviewListScreen(
    onBackClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    onEditReviewClick: (Int) -> Unit = {},
    onDeleteReviewClick: (Int) -> Unit = {}
) {
    val selectedTabState = remember {
        mutableStateOf(AccountReviewTab.Product)
    }

    val reviews = getDemoReviews(
        selectedTab = selectedTabState.value
    )

    AccountPageScaffold(
        title = "Değerlendirmelerim",
        kicker = "Alışveriş Deneyimi",
        description = "Daha önce yaptığınız ürün, mağaza ve firma değerlendirmelerini inceleyin.",
        backButtonText = "Hesabıma Dön",
        onBackClick = onBackClick
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                ReviewTabContainer(
                    selectedTab = selectedTabState.value,
                    onTabSelected = { tab ->
                        selectedTabState.value = tab
                    }
                )
            }

            if (reviews.isEmpty()) {
                item {
                    ReviewEmptyState(
                        selectedTab = selectedTabState.value
                    )
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
    val backgroundColor = if (selected) {
        BbColors.Surface
    } else {
        BbColors.SurfaceMuted
    }

    val titleColor = if (selected) {
        BbColors.TextStrong
    } else {
        BbColors.TextMuted
    }

    val iconColor = if (selected) {
        BbColors.Yellow.Yellow800
    } else {
        BbColors.TextMuted
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = BbRadius.LgShape
            )
            .clickable {
                onClick(tab)
            }
            .padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space3
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(BbSpacing.Space5)
            )

            Text(
                text = tab.title,
                style = BbTypography.labelSmall,
                color = titleColor
            )

            Text(
                text = count.toString(),
                style = BbTypography.labelSmall,
                color = BbColors.Yellow.Yellow800
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
            ReviewMediaArea(
                review = review
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                ReviewTypeBadge(
                    text = review.badgeText
                )

                Text(
                    text = review.title,
                    style = BbTypography.titleSmall,
                    color = BbColors.TextStrong
                )

                ReviewRatingRow(
                    rating = review.rating
                )

                Text(
                    text = review.comment,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )

                ReviewDateBox(
                    dateText = review.dateText
                )

                if (review.type == AccountReviewTab.Product) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                    ) {
                        BbButton(
                            text = "Ürünü Gör",
                            onClick = {
                                onProductClick(review.targetId)
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
            .background(
                color = BbColors.SurfaceMuted
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(BbSpacing.Space20)
                .background(
                    color = review.mediaBackgroundColor,
                    shape = BbRadius.MdShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = review.mediaText,
                style = BbTypography.titleMedium,
                color = review.mediaTextColor
            )
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
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = BbColors.Yellow.Yellow800
        )
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
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if (index < rating) {
                    BbColors.Yellow.Yellow800
                } else {
                    BbColors.BorderStrong
                },
                modifier = Modifier.size(BbSpacing.Space4)
            )
        }

        Text(
            text = "$rating / 5",
            style = BbTypography.labelSmall,
            color = BbColors.TextMuted
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
                color = BbColors.SurfaceMuted,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.CalendarMonth,
                contentDescription = null,
                tint = BbColors.Yellow.Yellow800,
                modifier = Modifier.size(BbSpacing.Space4)
            )

            Text(
                text = dateText,
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space12)
                    .background(
                        color = BbColors.Yellow.Yellow100,
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = selectedTab.icon,
                    contentDescription = null,
                    tint = BbColors.Yellow.Yellow800,
                    modifier = Modifier.size(BbSpacing.Space7)
                )
            }

            Text(
                text = "Kayıt bulunamadı!",
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            Text(
                text = "Henüz ${selectedTab.emptyText} bulunmuyor. Değerlendirme yaptığınızda burada listelenecek.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
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
            title = "Ürün #1",
            badgeText = "ÜRÜN DEĞERLENDİRMESİ",
            comment = "Makineyi 7 gündür kullanıyorum. Bu süre içinde hem deneyim kazandım hem de internetten okuduğum birçok bilgiyle karşılaştırdım. Ürünü almayı düşünenler için detaylı bir deneyim paylaşmak istedim.",
            rating = 5,
            dateText = "22 Eylül 2025 00:31 tarihinde eklendi",
            mediaText = "BOT",
            mediaBackgroundColor = BbColors.Beige.Beige200,
            mediaTextColor = BbColors.Beige.Beige900
        ),
        ReviewUiModel(
            reviewId = 2,
            targetId = 102,
            type = AccountReviewTab.Product,
            title = "Ürün #1",
            badgeText = "ÜRÜN DEĞERLENDİRMESİ",
            comment = "Ürün genel olarak beklentimi karşıladı. Teslimat hızlıydı, paketleme iyiydi. Bir süre daha kullandıktan sonra yorumumu güncelleyebilirim.",
            rating = 4,
            dateText = "22 Eylül 2025 00:31 tarihinde eklendi",
            mediaText = "BOT",
            mediaBackgroundColor = BbColors.Beige.Beige200,
            mediaTextColor = BbColors.Beige.Beige900
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
            badgeText = "MAĞAZA DEĞERLENDİRMESİ",
            comment = "Mağaza iletişimi hızlıydı. Sipariş süreci ve kargo bilgilendirmesi anlaşılır şekilde ilerledi.",
            rating = 5,
            dateText = "12 Ekim 2025 14:20 tarihinde eklendi",
            mediaText = "O",
            mediaBackgroundColor = BbColors.Green.Green700,
            mediaTextColor = BbColors.White
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
            badgeText = "FİRMA DEĞERLENDİRMESİ",
            comment = "Firma bilgileri netti, ürün açıklamaları yeterliydi. Toptan iletişim tarafında hızlı dönüş aldım.",
            rating = 5,
            dateText = "10 Ekim 2025 09:12 tarihinde eklendi",
            mediaText = "C",
            mediaBackgroundColor = BbColors.Green.Green700,
            mediaTextColor = BbColors.White
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

private data class ReviewUiModel(
    val reviewId: Int,
    val targetId: Int,
    val type: AccountReviewTab,
    val title: String,
    val badgeText: String,
    val comment: String,
    val rating: Int,
    val dateText: String,
    val mediaText: String,
    val mediaBackgroundColor: Color,
    val mediaTextColor: Color
)