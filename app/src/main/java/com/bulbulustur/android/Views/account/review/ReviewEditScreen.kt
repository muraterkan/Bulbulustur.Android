package com.bulbulustur.android.Views.account.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun ReviewEditScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    var rating by remember { mutableIntStateOf(5) }
    var comment by remember {
        mutableStateOf(
            "Makineyi 7 gündür kullanıyorum. Bu süre içinde hem deneyim kazandım hem de internetten okuduğum birçok bilgiyle karşılaştırdım. Ürünü almayı düşünenler için detaylı bir deneyim paylaşmak istedim."
        )
    }

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Değerlendirmeyi Düzenle",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                ReviewEditProductCard()
            }

            item {
                ReviewEditRatingCard(
                    rating = rating,
                    onRatingChange = { rating = it }
                )
            }

            item {
                ReviewEditCommentCard(
                    comment = comment,
                    onCommentChange = { comment = it }
                )
            }

            item {
                ReviewEditActionCard(
                    onSaveClick = onSaveClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}

@Composable
private fun ReviewEditProductCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = BbColors.TextMuted,
                    modifier = Modifier.size(BbIcon.Inline)
                )

                Text(
                    text = "Ortobella",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "Ortobella Comfort Genç Garson Bot 8028",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bu değerlendirme ürün detayında ve sipariş deneyimi alanlarında görünebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReviewEditRatingCard(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Text(
                text = "Puanınız",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                repeat(5) { index ->
                    val starValue = index + 1

                    androidx.compose.material3.IconButton(
                        onClick = {
                            onRatingChange(starValue)
                        },
                        modifier = Modifier.size(BbIcon.BoxMd)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = null,
                            tint = if (starValue <= rating) {
                                BbColors.Yellow.Yellow800
                            } else {
                                BbColors.BorderStrong
                            },
                            modifier = Modifier.size(BbIcon.TopBarIcon)
                        )
                    }
                }
            }

            Text(
                text = "$rating / 5",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReviewEditCommentCard(
    comment: String,
    onCommentChange: (String) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Text(
                text = "Değerlendirme Metni",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            OutlinedTextField(
                value = comment,
                onValueChange = onCommentChange,
                modifier = Modifier.fillMaxWidth(),
                minLines = 6,
                shape = BbRadius.Input,
                textStyle = MaterialTheme.typography.bodySmall,
                label = {
                    Text(text = "Yorumunuz")
                }
            )
        }
    }
}

@Composable
private fun ReviewEditActionCard(
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            BbButton(
                text = "Değerlendirmeyi Kaydet",
                onClick = onSaveClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )

            BbButton(
                text = "Değerlendirmeyi Sil",
                onClick = onDeleteClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Danger,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onError,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}