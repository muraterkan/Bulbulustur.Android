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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductQuestionControllerState
import com.bulbulustur.android.Application.Localization.BBLocalization
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
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun ProductQuestionScreen(
    State: ProductQuestionControllerState = ProductQuestionControllerState(),
    productId: Int = 0,
    productName: String = "",
    productPicture: String = "",
    storeName: String = "",
    variantId: Int = 0,
    isAuthenticated: Boolean = false,
    onBackClick: () -> Unit = {},
    onLoginRequired: () -> Unit = {},
    onInsertQuestion: (String) -> Unit = {},
    onQuestionClick: (ProductCustomerQuestionDTO) -> Unit = {}
) {
    var questionText by remember(productId) { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "a72573eb-7c00-42d1-8489-8302f0f33a23", fallback = "Soru & Cevap"),
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.AddComment,
                actionContentDescription = BBLocalization.Current.Get(key = "d1af1899-4d10-4568-a72a-1fd2028063d1", fallback = "Soru sor"),
                onActionClick = {
                    if (!isAuthenticated) {
                        onLoginRequired()
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.SectionGapCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                ProductQuestionProductSummary(
                    productId = productId,
                    productName = productName,
                    productPicture = productPicture,
                    storeName = storeName,
                    variantId = variantId
                )
            }

            item {
                ProductQuestionAskCard(
                    storeName = storeName,
                    questionText = questionText,
                    isAuthenticated = isAuthenticated,
                    isSubmitting = State.IsSubmitting,
                    onQuestionTextChange = {
                        questionText = it
                    },
                    onLoginRequired = onLoginRequired,
                    onSubmitClick = {
                        val normalizedQuestion = questionText.trim()

                        if (normalizedQuestion.isNotBlank()) {
                            onInsertQuestion(normalizedQuestion)
                        }
                    }
                )
            }

            State.SuccessMessage
                ?.takeIf { it.isNotBlank() }
                ?.let { message ->
                    item {
                        ProductQuestionFeedbackCard(
                            message = message,
                            isError = false
                        )
                    }
                }

            State.ErrorMessage
                ?.takeIf { it.isNotBlank() }
                ?.let { message ->
                    item {
                        ProductQuestionFeedbackCard(
                            message = message,
                            isError = true
                        )
                    }
                }

            when {
                State.IsLoading && State.Questions.isEmpty() -> {
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
                                BBLocalization.Current.Get(
                                    key = "7d6fdd0d-289b-4313-894c-df3ea6942b0d",
                                    fallback = "Müşterilerin bu ürün hakkında gönderdiği sorular."
                                )
                        )
                    }

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
    productPicture: String,
    storeName: String,
    variantId: Int
) {
    val resolvedProductName =
        productName.ifBlank {
            BBLocalization.Current.Get(
                key = "67267643-9c81-4171-8dae-74ef3a05ee24",
                fallback = "Ürün Soruları"
            )
        }

    val resolvedStoreName =
        storeName.ifBlank {
            BBLocalization.Current.Get(
                key = "2ac4c8be-0d5d-4c84-afe8-628839892727",
                fallback = ""
            )
        }

    val productImageUrl =
        ImageUrlResolver.Resolve(
            imagePath =
                productPicture
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
                width = 1.dp,
                color =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.35f
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
            Surface(
                modifier =
                    Modifier.size(
                        BBSpacing.Space18
                    ),
                shape =
                    BBRadius.XlShape,
                color =
                    MaterialTheme.colorScheme.surface,
                border =
                    BorderStroke(
                        width = 1.dp,
                        color =
                            MaterialTheme.colorScheme.outlineVariant
                    )
            ) {
                Box(
                    modifier =
                        Modifier.fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {
                    if (
                        productImageUrl.isNotBlank()
                    ) {
                        AsyncImage(
                            model =
                                productImageUrl,
                            contentDescription =
                                resolvedProductName,
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(
                                        BBSpacing.Space1
                                    ),
                            contentScale =
                                ContentScale.Fit
                        )
                    } else {
                        Text(
                            text =
                                resolvedProductName.ToInitials(
                                    fallback = "Ü"
                                ),
                            style =
                                MaterialTheme.typography.titleMedium,
                            fontWeight =
                                FontWeight.Bold,
                            color =
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Column(
                modifier =
                    Modifier.weight(1f),
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
                        if (variantId > 0) {
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
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "4bf83152-23e0-438c-adf8-cbf2d545bdba", fallback = "Satıcıya Soru Sor"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "${storeName.ifBlank { BBLocalization.Current.Get(key = "2ac4c8be-0d5d-4c84-afe8-628839892727", fallback = "") }} mağazasına ürün hakkında soru gönderebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (isAuthenticated) {
                OutlinedTextField(
                    value = questionText,
                    onValueChange = onQuestionTextChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = BBLocalization.Current.Get(key = "7f62bd9f-01a0-4cd2-b208-8c84fe01adae", fallback = "Sorunuz"))
                    },
                    placeholder = {
                        Text(text = BBLocalization.Current.Get(key = "bf3aea33-2824-459f-b7aa-a5f8298bf412", fallback = "Ürün hakkında merak ettiğiniz konuyu yazın."))
                    },
                    minLines = 3,
                    maxLines = 6,
                    enabled = !isSubmitting
                )

                BbButton(
                    text = if (isSubmitting) BBLocalization.Current.Get(key = "747533e0-13c8-4b49-82ca-ebf9fea6b37f", fallback = "Gönderiliyor") else BBLocalization.Current.Get(key = "03bc5c9c-accd-41d0-9c88-7bb2ad53590e", fallback = "Soruyu Gönder"),
                    onClick = onSubmitClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium,
                    enabled = questionText.isNotBlank() && !isSubmitting
                )
            } else {
                BbButton(
                    text = BBLocalization.Current.Get(key = "d28572e7-8179-42f1-a4af-60e1b93cf602", fallback = "Giriş Yap ve Soru Sor"),
                    onClick = onLoginRequired,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun ProductQuestionSummaryCard(totalQuestionCount: Int) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = totalQuestionCount.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = BBLocalization.Current.Get(key = "5d089966-6ab3-4cce-bf89-24c9e209dca9", fallback = "yayınlanmış ürün sorusu"),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "4f158bce-3d8d-4984-a9ca-9afa65b06f5f", fallback = "Satıcı cevapları eklendiğinde burada gösterilecektir."),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
    val customerName = question.Questioner
        .orEmpty()
        .takeIf { it.isNotBlank() }
        ?: BBLocalization.Current.Get(key = "3a8d29be-870c-414f-bb7c-221b560b299e", fallback = "")

    val storeName = question.StoreName
        .orEmpty()
        .takeIf { it.isNotBlank() }
        ?: fallbackStoreName.ifBlank { BBLocalization.Current.Get(key = "2ac4c8be-0d5d-4c84-afe8-628839892727", fallback = "") }

    val answer = question.Message
        .orEmpty()
        .takeIf { it.isNotBlank() }

    val readableDate = question.InsertedDate.ToReadableDate()
    val isAnswered = question.IsAnswered && answer != null

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = customerName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = storeName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                ProductQuestionStatusBadge(
                    text = if (isAnswered) BBLocalization.Current.Get(key = "3f8f8f72-2596-4eaa-a2b2-743abb1655ee", fallback = "Cevaplandı") else "Cevap bekliyor"
                )
            }

            if (readableDate.isNotBlank()) {
                ProductQuestionMetaBadge(text = readableDate)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "d1af1899-4d10-4568-a72a-1fd2028063d1", fallback = "Soru"),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = question.Question,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (isAnswered) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = BBRadius.LgShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(BBSpacing.CardPaddingCompact),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        Text(
                            text = BBLocalization.Current.Get(key = "3ba680c9-4dd6-4dfa-8302-5b3ff263aefb", fallback = ""),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Text(
                            text = answer.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            } else {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = BBRadius.LgShape,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "d407b3e7-2260-466c-9949-efaf495bc575", fallback = "Satıcı cevabı bekleniyor."),
                        modifier = Modifier.padding(BBSpacing.CardPaddingCompact),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductQuestionStatusBadge(text: String) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProductQuestionMetaBadge(text: String) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProductQuestionFeedbackCard(
    message: String,
    isError: Boolean
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun ProductQuestionLoadingCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun ProductQuestionEmptyCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "3bd4a97b-88e5-4f8d-afe4-a7534dfe98e7", fallback = "Henüz soru sorulmamış"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "b48eeefb-045f-4cd3-8c1a-3c7b3780046d", fallback = "Bu ürün hakkında ilk soruyu siz gönderebilirsiniz."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun String.ToInitials(fallback: String): String {
    return trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .mapNotNull { it.firstOrNull() }
        .joinToString(separator = "")
        .uppercase()
        .ifBlank { fallback }
}

private fun String.ToReadableDate(): String {
    if (isBlank()) {
        return ""
    }

    return substringBefore("T")
        .split("-")
        .takeIf { it.size == 3 }
        ?.let { "${it[2]}.${it[1]}.${it[0]}" }
        ?: this
}

@Preview(showBackground = true)
@Composable
private fun ProductQuestionScreenPreview() {
    BbTheme {
        ProductQuestionScreen()
    }
}