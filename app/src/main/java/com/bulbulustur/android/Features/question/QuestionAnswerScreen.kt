package com.bulbulustur.android.Features.account.question

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTypography
import com.bulbulustur.android.Ui.theme.BbAlpha

@Composable
fun QuestionAnswerScreen(
    onBackClick: () -> Unit = {}
) {
    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = BbAlpha.DisabledLabel),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.80f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
        )
    )

    val questions = listOf(
        QuestionAnswerItem(
            productName = "Ortopedik Spor Ayakkabı",
            storeName = "Ortobella",
            question = "Bu ürünün 42 numarası ne zaman stokta olur?",
            answer = "Merhaba, 42 numara için yeni stok girişi bu hafta içinde bekleniyor.",
            statusText = "Cevaplandı",
            statusIcon = Icons.Outlined.CheckCircle
        ),
        QuestionAnswerItem(
            productName = "Pamuklu Tişört",
            storeName = "Bulbulustur Store",
            question = "Toptan alımda farklı renkleri karıştırabilir miyim?",
            answer = null,
            statusText = "Cevap Bekliyor",
            statusIcon = Icons.Outlined.Schedule
        )
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Soru Ve Cevaplarım",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(pageBackground)
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
                QuestionAnswerIntroCard()
            }

            items(
                items = questions,
                key = { question -> "${question.productName}-${question.question}" }
            ) { question ->
                QuestionAnswerCard(
                    item = question
                )
            }
        }
    }
}

@Composable
private fun QuestionAnswerIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BbRadius.XlShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.QuestionAnswer,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BbIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Ürün Soruları Ve Satıcı Cevapları",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Ürün sorularını, satıcı cevaplarını ve bekleyen yanıtlarını buradan takip edebilirsin.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun QuestionAnswerCard(
    item: QuestionAnswerItem
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = BbRadius.PillShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BbIcon.Ui)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = item.productName,
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = item.storeName,
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                QuestionStatusBadge(
                    text = item.statusText,
                    icon = item.statusIcon
                )
            }

            AccountQuestionDivider()

            QuestionTextBlock(
                label = "Soru",
                text = item.question
            )

            if (item.answer != null) {
                QuestionTextBlock(
                    label = "Cevap",
                    text = item.answer
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = BbRadius.LgShape
                        )
                        .padding(BbSpacing.CardPaddingCompact)
                ) {
                    Text(
                        text = "Satıcı cevabı bekleniyor.",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun QuestionStatusBadge(
    text: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            ),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(BbIcon.Size2Xs)
        )

        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun QuestionTextBlock(
    label: String,
    text: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = label,
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = text,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun AccountQuestionDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 1.dp, width = BbSpacing.BorderThin)
    ) {
        drawLine(
            color = dividerColor,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(10f, 8f),
                phase = 0f
            )
        )
    }
}

private data class QuestionAnswerItem(
    val productName: String,
    val storeName: String,
    val question: String,
    val answer: String?,
    val statusText: String,
    val statusIcon: ImageVector
)