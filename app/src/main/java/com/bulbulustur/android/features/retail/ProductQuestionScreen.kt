package com.bulbulustur.android.features.retail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
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

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 14.dp,
                end = 16.dp,
                bottom = 28.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProductQuestionTopBar(
                    onBackClick = onBackClick
                )
            }

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

            items(filteredQuestions) { question ->
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
private fun ProductQuestionTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Ürün soru & cevapları",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Ürün hakkında sorulan sorular ve yanıtlar.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductQuestionProductSummary(
    product: RetailProductQuestionProductSummary
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(9.dp))

                Text(
                    text = product.variantText,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onAskQuestionClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Satıcıya soru sor",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${product.storeName} mağazasına ürün hakkında soru gönderebilirsin.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
        horizontalArrangement = Arrangement.spacedBy(10.dp)
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
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
        modifier = Modifier.fillMaxWidth()
    ) {
        ProductQuestionSectionTitle(
            title = "Soru filtresi",
            description = "Cevap durumuna veya konuya göre daralt."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = question.customerInitials,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = question.customerName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = question.dateText,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                ProductQuestionStatusBadge(
                    text = question.statusText,
                    isAnswered = question.answer.isNotBlank()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = question.question,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (question.answer.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))

                ProductQuestionAnswerBox(
                    storeName = question.storeName,
                    answer = question.answer,
                    answerDateText = question.answerDateText
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = "$storeName yanıtladı",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = answer,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = answerDateText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val contentColor = if (isAnswered) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(containerColor)
            .padding(
                horizontal = 9.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
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
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = 9.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProductQuestionSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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

private fun getRetailProductQuestionScreenData(productId: Int): RetailProductQuestionScreenData {
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
    MaterialTheme {
        ProductQuestionScreen()
    }
}
