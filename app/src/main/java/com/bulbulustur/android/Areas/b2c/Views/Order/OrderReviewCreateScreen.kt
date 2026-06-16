package com.bulbulustur.android.Areas.b2c.Views.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun OrderReviewCreateScreen(
    onBackClick: () -> Unit = {},
    onSubmitClick: () -> Unit = {}
) {
    var rating by remember {
        mutableIntStateOf(5)
    }

    var content by remember {
        mutableStateOf("")
    }

    var accepted by remember {
        mutableStateOf(false)
    }

    val canSubmit = content.isNotBlank() && accepted

    OrderReviewPageScaffold(
        title = "Ürün Değerlendirme",
        subtitle = "Satın aldığınız ürün için yorum yazın.",
        onBackClick = onBackClick
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                OrderReviewProductCard()
            }

            item {
                OrderReviewRatingCard(
                    rating = rating,
                    onRatingChange = { value ->
                        rating = value
                    }
                )
            }

            item {
                OrderReviewContentCard(
                    content = content,
                    onContentChange = { value ->
                        content = value
                    }
                )
            }

            item {
                OrderReviewAgreementCard(
                    accepted = accepted,
                    onAcceptedChange = { value ->
                        accepted = value
                    }
                )
            }

            item {
                OrderReviewActionCard(
                    canSubmit = canSubmit,
                    onBackClick = onBackClick,
                    onSubmitClick = onSubmitClick
                )
            }
        }
    }
}

@Composable
private fun OrderReviewPageScaffold(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.SurfaceMuted)
            .navigationBarsPadding()
    ) {
        OrderReviewTopHeader(
            title = title,
            subtitle = subtitle,
            onBackClick = onBackClick
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            content()
        }
    }
}

@Composable
private fun OrderReviewTopHeader(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Surface)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BbSpacing.PageHorizontal,
                    vertical = BbSpacing.Space3
                ),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(BbIcon.BoxMd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Geri dön",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BbIcon.TopBarIcon)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        HorizontalDivider(
            color = BbColors.Border
        )
    }
}

@Composable
private fun OrderReviewProductCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxXl)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BbIcon.Feature)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Değerlendirilecek Ürün",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Kadın Sneaker Günlük Ayakkabı",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Bu ürünü satın aldığınız için yorum yazabilirsiniz.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun OrderReviewRatingCard(
    rating: Int,
    onRatingChange: (Int) -> Unit
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
            OrderReviewSectionTitle(
                title = "Puanınız",
                subtitle = "Bu ürüne kaç yıldız verirsiniz?"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                (1..5).forEach { value ->
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "$value yıldız",
                        tint = if (value <= rating) BbColors.Yellow.Yellow600 else MaterialTheme.colorScheme.outlineVariant,
                        modifier = Modifier
                            .size(BbIcon.Size2Xl)
                            .clickable {
                                onRatingChange(value)
                            }
                    )
                }

                Text(
                    text = "$rating/5",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun OrderReviewContentCard(
    content: String,
    onContentChange: (String) -> Unit
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
            OrderReviewSectionTitle(
                title = "Yorumunuz",
                subtitle = "Ürün deneyiminizi birkaç cümleyle anlatın."
            )

            OutlinedTextField(
                value = content,
                onValueChange = onContentChange,
                modifier = Modifier.fillMaxWidth(),
                minLines = 5,
                shape = BbRadius.Input,
                placeholder = {
                    Text(
                        text = "Aldığınız ürün için bir değerlendirme yazarak başkalarına yardımcı olabilirsiniz."
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Description,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderReviewAgreementCard(
    accepted: Boolean,
    onAcceptedChange: (Boolean) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onAcceptedChange(!accepted)
                },
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = accepted,
                onCheckedChange = onAcceptedChange
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Değerlendirme kurallarını kabul ediyorum.",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Yorumunuzun ürün deneyimine dayalı, doğru ve diğer kullanıcılara yardımcı olacak şekilde yazılması gerekir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun OrderReviewActionCard(
    canSubmit: Boolean,
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit
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
                text = "Değerlendir",
                onClick = onSubmitClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = canSubmit,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )

            BbButton(
                text = "Sipariş Detayına Dön",
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun OrderReviewSectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}