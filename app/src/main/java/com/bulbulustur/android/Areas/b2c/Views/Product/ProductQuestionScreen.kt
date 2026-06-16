package com.bulbulustur.android.Areas.b2c.Views.Product

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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

@Composable
fun ProductQuestionScreen(
    productId: Int = 1,
    onBackClick: () -> Unit = {},
    onAskQuestionClick: (RetailProductQuestionProductSummary) -> Unit = {},
    onQuestionClick: (RetailProductQuestionItem) -> Unit = {}
) {
    val screenData = remember(productId) {
        getRetailProductQuestionScreenData(productId)
    }

    var selectedFilter by remember {
        mutableStateOf("Tümü")
    }

    val filteredQuestions = remember(selectedFilter, screenData.questions) {
        if (selectedFilter == "Tümü") {
            screenData.questions
        } else {
            screenData.questions.filter {
                it.filterTags.contains(selectedFilter)
            }
        }
    }

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Soru & Cevap",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Settings,
                actionContentDescription = "Soru sor",
                onActionClick = {
                    onAskQuestionClick(screenData.product)
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.SectionGapCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                ProductQuestionProductSummary(
                    product = screenData.product
                )
            }

            item {
                ProductQuestionAskCard(
                    product = screenData.product,
                    onAskQuestionClick = {
                        onAskQuestionClick(screenData.product)
                    }
                )
            }

            item {
                ProductQuestionSummaryCard(
                    summary = screenData.summary
                )
            }

            item {
                ProductQuestionFilterSection(
                    filters = screenData.filters,
                    selectedFilter = selectedFilter,
                    onFilterChange = {
                        selectedFilter = it
                    }
                )
            }

            item {
                ProductQuestionSectionTitle(
                    title = "Ürün soruları",
                    description = "Satıcı ve kullanıcı yanıtlarıyla ürün hakkında merak edilenler."
                )
            }

            items(
                items = filteredQuestions,
                key = { question -> question.id }
            ) { question ->
                ProductQuestionCard(
                    question = question,
                    onClick = {
                        onQuestionClick(question)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductQuestionProductSummary(
    product: RetailProductQuestionProductSummary
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BbRadius.XxlShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.CardPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space18)
                    .clip(BbRadius.XlShape)
                    .background(BbColors.Primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextSubtle
                )

                Text(
                    text = product.variantText,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BbColors.TextStrong
                )
            }
        }
    }
}

@Composable
private fun ProductQuestionAskCard(
    product: RetailProductQuestionProductSummary,
    onAskQuestionClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onAskQuestionClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space12)
                    .clip(BbRadius.LgShape)
                    .background(BbColors.PrimarySoft),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Satıcıya soru sor",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = "${product.storeName} mağazasına ürün hakkında soru gönderebilirsin.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun ProductQuestionSummaryCard(
    summary: RetailProductQuestionSummary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        ProductQuestionStatCard(
            modifier = Modifier.weight(1f),
            title = "${summary.totalQuestionCount}",
            subtitle = "soru"
        )

        ProductQuestionStatCard(
            modifier = Modifier.weight(1f),
            title = "${summary.answeredQuestionCount}",
            subtitle = "cevaplı"
        )

        ProductQuestionStatCard(
            modifier = Modifier.weight(1f),
            title = summary.averageAnswerTimeText,
            subtitle = "yanıt"
        )
    }
}

@Composable
private fun ProductQuestionStatCard(
    modifier: Modifier,
    title: String,
    subtitle: String
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.Primary
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProductQuestionFilterSection(
    filters: List<String>,
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        ProductQuestionSectionTitle(
            title = "Soru filtresi",
            description = "Cevap durumuna veya konuya göre daralt."
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            filters.forEach { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = {
                        onFilterChange(filter)
                    },
                    label = {
                        Text(text = filter)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductQuestionCard(
    question: RetailProductQuestionItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(BbRadius.IconBoxSoft)
                        .background(BbColors.SurfaceMuted),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = question.customerInitials,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.TextStrong
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = question.customerName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = question.dateText,
                        style = MaterialTheme.typography.labelSmall,
                        color = BbColors.TextMuted
                    )
                }

                ProductQuestionStatusBadge(
                    text = question.statusText,
                    isAnswered = question.answer.isNotBlank()
                )
            }

            Text(
                text = question.question,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = BbColors.TextStrong
            )

            if (question.answer.isNotBlank()) {
                ProductQuestionAnswerBox(
                    storeName = question.storeName,
                    answer = question.answer,
                    answerDateText = question.answerDateText
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                if (question.variantText.isNotBlank()) {
                    ProductQuestionMetaBadge(
                        text = question.variantText
                    )
                }

                if (question.topicText.isNotBlank()) {
                    ProductQuestionMetaBadge(
                        text = question.topicText
                    )
                }

                if (question.helpfulCount > 0) {
                    ProductQuestionMetaBadge(
                        text = "${question.helpfulCount} faydalı"
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductQuestionAnswerBox(
    storeName: String,
    answer: String,
    answerDateText: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BbRadius.LgShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space3),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Text(
                text = "$storeName yanıtladı",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = answer,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )

            Text(
                text = answerDateText,
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun ProductQuestionStatusBadge(
    text: String,
    isAnswered: Boolean
) {
    val containerColor = if (isAnswered) {
        BbColors.Green.Green50
    } else {
        BbColors.SurfaceMuted
    }

    val contentColor = if (isAnswered) {
        BbColors.Green.Green700
    } else {
        BbColors.TextMuted
    }

    Surface(
        shape = BbRadius.PillShape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )
    }
}

@Composable
private fun ProductQuestionMetaBadge(
    text: String
) {
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextMuted
        )
    }
}

@Composable
private fun ProductQuestionSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextMuted
        )
    }
}

data class RetailProductQuestionScreenData(
    val product: RetailProductQuestionProductSummary,
    val summary: RetailProductQuestionSummary,
    val filters: List<String>,
    val questions: List<RetailProductQuestionItem>
)

data class RetailProductQuestionProductSummary(
    val id: Int,
    val name: String,
    val storeName: String,
    val variantText: String,
    val imageText: String
)

data class RetailProductQuestionSummary(
    val totalQuestionCount: Int,
    val answeredQuestionCount: Int,
    val averageAnswerTimeText: String
)

data class RetailProductQuestionItem(
    val id: Int,
    val customerName: String,
    val customerInitials: String,
    val dateText: String,
    val question: String,
    val answer: String,
    val answerDateText: String,
    val storeName: String,
    val statusText: String,
    val variantText: String,
    val topicText: String,
    val helpfulCount: Int,
    val filterTags: List<String>
)

private fun getRetailProductQuestionScreenData(
    productId: Int
): RetailProductQuestionScreenData {
    return RetailProductQuestionScreenData(
        product = RetailProductQuestionProductSummary(
            id = productId,
            name = "Kadın klasik sneaker ayakkabı",
            storeName = "Ortobella Store",
            variantText = "Beyaz · 38 numara",
            imageText = "P1"
        ),
        summary = RetailProductQuestionSummary(
            totalQuestionCount = 18,
            answeredQuestionCount = 15,
            averageAnswerTimeText = "1 gün"
        ),
        filters = listOf(
            "Tümü",
            "Cevaplı",
            "Cevapsız",
            "Beden",
            "Kargo",
            "Stok"
        ),
        questions = listOf(
            RetailProductQuestionItem(
                id = 1,
                customerName = "Ayşe K.",
                customerInitials = "AK",
                dateText = "2 gün önce",
                question = "Kalıbı dar mı? Normalde 38 giyiyorum, 38 olur mu?",
                answer = "Merhaba, ürün standart kalıptır. Ayağınız taraklı değilse kendi numaranızı tercih edebilirsiniz.",
                answerDateText = "1 gün önce yanıtlandı",
                storeName = "Ortobella Store",
                statusText = "Cevaplı",
                variantText = "38 numara",
                topicText = "Beden",
                helpfulCount = 12,
                filterTags = listOf("Cevaplı", "Beden")
            ),
            RetailProductQuestionItem(
                id = 2,
                customerName = "Merve T.",
                customerInitials = "MT",
                dateText = "4 gün önce",
                question = "Bugün sipariş verirsem ne zaman kargoya verilir?",
                answer = "Merhaba, hafta içi 14:00 öncesi siparişler aynı gün kargoya teslim edilmektedir.",
                answerDateText = "3 gün önce yanıtlandı",
                storeName = "Ortobella Store",
                statusText = "Cevaplı",
                variantText = "",
                topicText = "Kargo",
                helpfulCount = 8,
                filterTags = listOf("Cevaplı", "Kargo")
            ),
            RetailProductQuestionItem(
                id = 3,
                customerName = "Selin A.",
                customerInitials = "SA",
                dateText = "1 hafta önce",
                question = "Beyaz rengi tekrar 39 numarada stoklara gelecek mi?",
                answer = "",
                answerDateText = "",
                storeName = "Ortobella Store",
                statusText = "Cevapsız",
                variantText = "39 numara",
                topicText = "Stok",
                helpfulCount = 3,
                filterTags = listOf("Cevapsız", "Stok")
            ),
            RetailProductQuestionItem(
                id = 4,
                customerName = "Ece D.",
                customerInitials = "ED",
                dateText = "2 hafta önce",
                question = "İade sürecinde kutunun zarar görmemiş olması gerekiyor mu?",
                answer = "Merhaba, ürünün kullanılmamış olması ve orijinal kutusuyla gönderilmesi yeterlidir.",
                answerDateText = "2 hafta önce yanıtlandı",
                storeName = "Ortobella Store",
                statusText = "Cevaplı",
                variantText = "",
                topicText = "İade",
                helpfulCount = 6,
                filterTags = listOf("Cevaplı")
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductQuestionScreenPreview() {
    BbTheme {
        ProductQuestionScreen()
    }
}