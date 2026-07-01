package com.bulbulustur.android.Application.Areas.b2c.Views.Product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddComment
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductQuestionControllerState
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
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCustomerQuestionDTO
import androidx.compose.ui.unit.dp

@Composable
fun ProductQuestionScreen(
    State: ProductQuestionControllerState =
        ProductQuestionControllerState(),
    productId: Int = 0,
    productName: String = "",
    storeName: String = "",
    variantId: Int = 0,
    isAuthenticated: Boolean = false,
    onBackClick: () -> Unit = {},
    onLoginRequired: () -> Unit = {},
    onInsertQuestion: (String) -> Unit = {},
    onQuestionClick:
        (ProductCustomerQuestionDTO) -> Unit = {}
) {
    var questionText by
    remember(
        productId
    ) {
        mutableStateOf(
            ""
        )
    }

    Scaffold(
        containerColor =
            MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title =
                    "Soru & Cevap",
                onBackClick =
                    onBackClick,
                actionIcon =
                    Icons.Outlined.AddComment,
                actionContentDescription =
                    "Soru sor",
                onActionClick = {
                    if (
                        !isAuthenticated
                    ) {
                        onLoginRequired()
                    }
                }
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
                ProductQuestionProductSummary(
                    productId =
                        productId,
                    productName =
                        productName,
                    storeName =
                        storeName,
                    variantId =
                        variantId
                )
            }

            item {
                ProductQuestionAskCard(
                    storeName =
                        storeName,
                    questionText =
                        questionText,
                    isAuthenticated =
                        isAuthenticated,
                    isSubmitting =
                        State.IsSubmitting,
                    onQuestionTextChange = {
                        questionText =
                            it
                    },
                    onLoginRequired =
                        onLoginRequired,
                    onSubmitClick = {
                        val normalizedQuestion =
                            questionText.trim()

                        if (
                            normalizedQuestion.isNotBlank()
                        ) {
                            onInsertQuestion(
                                normalizedQuestion
                            )
                        }
                    }
                )
            }

            State.SuccessMessage
                ?.takeIf {
                    it.isNotBlank()
                }
                ?.let {
                    item {
                        ProductQuestionFeedbackCard(
                            message =
                                it,
                            isError =
                                false
                        )
                    }
                }

            State.ErrorMessage
                ?.takeIf {
                    it.isNotBlank()
                }
                ?.let {
                    item {
                        ProductQuestionFeedbackCard(
                            message =
                                it,
                            isError =
                                true
                        )
                    }
                }

            item {
                ProductQuestionSummaryCard(
                    totalQuestionCount =
                        State.Questions.size
                )
            }

            item {
                ProductQuestionSectionTitle(
                    title =
                        "Ürün soruları",
                    description =
                        "Müşterilerin bu ürün hakkında gönderdiği sorular."
                )
            }

            when {
                State.IsLoading &&
                        State.Questions.isEmpty() -> {
                    item {
                        ProductQuestionLoadingCard()
                    }
                }

                State.Questions.isEmpty() -> {
                    item {
                        ProductQuestionEmptyCard()
                    }
                }

                else -> {
                    items(
                        items =
                            State.Questions,
                        key = {
                            it.ProductCustomerQuestionId
                        }
                    ) { question ->
                        ProductQuestionCard(
                            question =
                                question,
                            fallbackStoreName =
                                storeName,
                            onClick = {
                                onQuestionClick(
                                    question
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
private fun ProductQuestionProductSummary(
    productId: Int,
    productName: String,
    storeName: String,
    variantId: Int
) {
    val resolvedProductName =
        productName.ifBlank {
            "Ürün Soruları"
        }

    val resolvedStoreName =
        storeName.ifBlank {
            "Satıcı"
        }

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
                        resolvedProductName
                            .ToInitials(
                                fallback =
                                    "Ü"
                            ),
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
                        resolvedProductName,
                    style =
                        MaterialTheme.typography.titleMedium,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text =
                        resolvedStoreName,
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text =
                        if (
                            variantId > 0
                        ) {
                            "Varyant #$variantId"
                        } else {
                            "Ürün #$productId"
                        },
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

@Composable
private fun ProductQuestionAskCard(
    storeName: String,
    questionText: String,
    isAuthenticated: Boolean,
    isSubmitting: Boolean,
    onQuestionTextChange: (String) -> Unit,
    onLoginRequired: () -> Unit,
    onSubmitClick: () -> Unit
) {
    BbCard(
        modifier =
            Modifier.fillMaxWidth(),
        variant =
            BbCardVariant.Outlined,
        padding =
            BbCardPadding.Medium
    ) {
        Column(
            modifier =
                Modifier.fillMaxWidth(),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                )
        ) {
            Text(
                text =
                    "Satıcıya Soru Sor",
                style =
                    MaterialTheme.typography.titleMedium,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onSurface
            )

            Text(
                text =
                    "${storeName.ifBlank { "Satıcı" }} mağazasına ürün hakkında soru gönderebilirsiniz.",
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (
                isAuthenticated
            ) {
                OutlinedTextField(
                    value =
                        questionText,
                    onValueChange =
                        onQuestionTextChange,
                    modifier =
                        Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text =
                                "Sorunuz"
                        )
                    },
                    placeholder = {
                        Text(
                            text =
                                "Ürün hakkında merak ettiğiniz konuyu yazın."
                        )
                    },
                    minLines =
                        3,
                    maxLines =
                        6,
                    enabled =
                        !isSubmitting
                )

                BbButton(
                    text =
                        if (
                            isSubmitting
                        ) {
                            "Gönderiliyor"
                        } else {
                            "Soruyu Gönder"
                        },
                    onClick =
                        onSubmitClick,
                    modifier =
                        Modifier.fillMaxWidth(),
                    variant =
                        BbButtonVariant.Primary,
                    size =
                        BbButtonSize.Medium,
                    enabled =
                        questionText.isNotBlank() &&
                                !isSubmitting
                )
            } else {
                BbButton(
                    text =
                        "Giriş Yap ve Soru Sor",
                    onClick =
                        onLoginRequired,
                    modifier =
                        Modifier.fillMaxWidth(),
                    variant =
                        BbButtonVariant.Primary,
                    size =
                        BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun ProductQuestionSummaryCard(
    totalQuestionCount: Int
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
                        totalQuestionCount.toString(),
                    style =
                        MaterialTheme.typography.headlineMedium,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.primary
                )

                Text(
                    text =
                        "yayınlanmış ürün sorusu",
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text =
                    "Satıcı cevapları eklendiğinde burada gösterilecektir.",
                style =
                    MaterialTheme.typography.labelSmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductQuestionCard(
    question: ProductCustomerQuestionDTO,
    fallbackStoreName: String,
    onClick: () -> Unit
) {
    val customerName =
        question.Questioner
            .takeIf {
                it.isNotBlank()
            }
            ?: "Müşteri"

    val storeName =
        question.StoreName
            .takeIf {
                it.isNotBlank()
            }
            ?: fallbackStoreName
                .ifBlank {
                    "Satıcı"
                }

    val resolvedVariantId =
        question.VariantId
            .takeIf {
                it > 0
            }

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
                            question.InsertedDate.ToReadableDate(),
                        style =
                            MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                ProductQuestionStatusBadge(
                    text =
                        "Yanıt bekleniyor"
                )
            }

            Text(
                text =
                    question.Question,
                style =
                    MaterialTheme.typography.bodyMedium,
                fontWeight =
                    FontWeight.SemiBold,
                color =
                    MaterialTheme.colorScheme.onSurface
            )

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
            ) {
                ProductQuestionMetaBadge(
                    text =
                        storeName
                )

                resolvedVariantId
                    ?.let {
                        ProductQuestionMetaBadge(
                            text =
                                "Varyant #$it"
                        )
                    }
            }
        }
    }
}

@Composable
private fun ProductQuestionStatusBadge(
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
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProductQuestionMetaBadge(
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
private fun ProductQuestionFeedbackCard(
    message: String,
    isError: Boolean
) {
    BbCard(
        modifier =
            Modifier.fillMaxWidth(),
        variant =
            BbCardVariant.Outlined,
        padding =
            BbCardPadding.Medium
    ) {
        Text(
            text =
                message,
            style =
                MaterialTheme.typography.bodyMedium,
            color =
                if (
                    isError
                ) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
        )
    }
}

@Composable
private fun ProductQuestionLoadingCard() {
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
private fun ProductQuestionEmptyCard() {
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
                    "Henüz soru sorulmamış",
                style =
                    MaterialTheme.typography.titleMedium,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onSurface
            )

            Text(
                text =
                    "Bu ürün hakkında ilk soruyu siz gönderebilirsiniz.",
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductQuestionSectionTitle(
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
private fun ProductQuestionScreenPreview() {
    BbTheme {
        ProductQuestionScreen()
    }
}