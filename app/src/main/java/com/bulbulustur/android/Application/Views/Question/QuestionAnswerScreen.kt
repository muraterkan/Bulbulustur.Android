package com.bulbulustur.android.Application.Views.Question

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.CircularProgressIndicator
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
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCustomerQuestionDTO

@Composable
fun QuestionAnswerScreen(
    questions: List<ProductCustomerQuestionDTO>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onProductClick: (productId: Int, storeId: Int, variantId: Int) -> Unit = { _, _, _ -> }
) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Soru ve Cevaplarım",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && questions.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            errorMessage != null && questions.isEmpty() -> {
                QuestionAnswerMessageState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    title = "Sorular alınamadı",
                    message = errorMessage,
                    buttonText = "Tekrar Dene",
                    onButtonClick = onRetryClick
                )
            }

            questions.isEmpty() -> {
                QuestionAnswerMessageState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    title = "Henüz soru sormadın",
                    message = "Ürünler hakkında sorduğun sorular ve satıcı cevapları burada görüntülenir."
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
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
                        QuestionAnswerIntroCard()
                    }

                    items(
                        items = questions,
                        key = { question -> question.ProductCustomerQuestionId }
                    ) { question ->
                        QuestionAnswerCard(
                            item = question,
                            onClick = {
                                onProductClick(
                                    question.ProductId,
                                    question.StoreId,
                                    question.VariantId
                                )
                            }
                        )
                    }
                }
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.XlShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.QuestionAnswer,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Ürün Soruları ve Satıcı Cevapları",
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
    item: ProductCustomerQuestionDTO,
    onClick: () -> Unit
) {
    val isAnswered = item.IsAnswered && !item.Message.isNullOrBlank()
    val statusText = if (isAnswered) "Cevaplandı" else "Cevap Bekliyor"
    val statusIcon = if (isAnswered) Icons.Outlined.CheckCircle else Icons.Outlined.Schedule

    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = BBRadius.PillShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.Ui)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = item.ProductName.orEmpty().ifBlank { "Ürün" },
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = item.StoreName.orEmpty().ifBlank { "Mağaza" },
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (item.InsertedDate.isNotBlank()) {
                        Text(
                            text = item.InsertedDate,
                            style = BbTypography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                QuestionStatusBadge(
                    text = statusText,
                    icon = statusIcon
                )
            }

            AccountQuestionDivider()

            QuestionTextBlock(
                label = "Soru",
                text = item.Question
            )

            if (isAnswered) {
                QuestionTextBlock(
                    label = "Cevap",
                    text = item.Message.orEmpty()
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = BBRadius.LgShape
                        )
                        .padding(BBSpacing.CardPaddingCompact)
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
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(BBIcon.Size2Xs)
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
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
private fun QuestionAnswerMessageState(
    modifier: Modifier,
    title: String,
    message: String,
    buttonText: String? = null,
    onButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.padding(BBSpacing.PageHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.QuestionAnswer,
            contentDescription = null,
            modifier = Modifier.size(BBIcon.Empty),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = title,
            modifier = Modifier.padding(top = BBSpacing.Space4),
            style = BbTypography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = message,
            modifier = Modifier.padding(top = BBSpacing.Space2),
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (buttonText != null) {
            BbButton(
                text = buttonText,
                onClick = onButtonClick,
                modifier = Modifier.padding(top = BBSpacing.Space4),
                variant = BbButtonVariant.Primary
            )
        }
    }
}

@Composable
private fun AccountQuestionDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 1.dp, width = BBSpacing.BorderThin)
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