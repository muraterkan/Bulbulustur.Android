package com.bulbulustur.android.features.account.request

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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbInnerPageHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun QuotationRequestListScreen(
    onBackClick: () -> Unit = {},
    onQuotationDetailClick: (Int) -> Unit = {},
    onCreateQuotationClick: () -> Unit = {}
) {
    val quotationRequests = getDemoQuotationRequests()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Teklif Taleplerim",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Add,
                actionContentDescription = "Yeni Teklif Talebi",
                onActionClick = onCreateQuotationClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            if (quotationRequests.isEmpty()) {
                item {
                    QuotationRequestEmptyState(
                        onCreateQuotationClick = onCreateQuotationClick
                    )
                }
            }

            items(
                items = quotationRequests,
                key = { quotation -> quotation.quotationRequestId }
            ) { quotation ->
                QuotationRequestCard(
                    quotation = quotation,
                    onQuotationDetailClick = onQuotationDetailClick
                )
            }
        }
    }
}

@Composable
private fun QuotationRequestCard(
    quotation: QuotationRequestUiModel,
    onQuotationDetailClick: (Int) -> Unit
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuotationIconBox()

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = quotation.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = quotation.requestNumber,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    QuotationStatusBadge(
                        statusText = quotation.statusText,
                        statusType = quotation.statusType
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Kategori: ${quotation.categoryName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Adet: ${quotation.quantityText}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Oluşturulma: ${quotation.insertedDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            BbButton(
                text = "Teklif Talebini Gör",
                onClick = {
                    onQuotationDetailClick(quotation.quotationRequestId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun QuotationRequestEmptyState(
    onCreateQuotationClick: () -> Unit
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
            QuotationEmptyIconBox()

            Text(
                text = "Henüz Teklif Talebiniz Yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Toptan ürün ihtiyaçlarınız için teklif talebi oluşturarak firmalardan dönüş alabilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Yeni Teklif Talebi",
                onClick = onCreateQuotationClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun QuotationStatusBadge(
    statusText: String,
    statusType: QuotationStatusType
) {
    val backgroundColor = when (statusType) {
        QuotationStatusType.Open -> BbColors.Blue.Blue50
        QuotationStatusType.Answered -> BbColors.Green.Green50
        QuotationStatusType.Expired -> BbColors.Red.Red50
    }

    val textColor = when (statusType) {
        QuotationStatusType.Open -> BbColors.Blue.Blue700
        QuotationStatusType.Answered -> BbColors.Green.Green700
        QuotationStatusType.Expired -> BbColors.Red.Red700
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = statusText,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

@Composable
private fun QuotationIconBox() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space14)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.XlShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "₺",
            style = MaterialTheme.typography.titleMedium,
            color = BbColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun QuotationEmptyIconBox() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space12)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.Space2),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "RFQ",
            style = MaterialTheme.typography.titleSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

private fun getDemoQuotationRequests(): List<QuotationRequestUiModel> {
    return listOf(
        QuotationRequestUiModel(
            quotationRequestId = 1,
            requestNumber = "RFQ-2026-0001",
            title = "Toplu Ayakkabı Alımı",
            categoryName = "Ayakkabı Ve Terlik",
            quantityText = "500 Adet",
            statusText = "Açık",
            statusType = QuotationStatusType.Open,
            insertedDate = "22 Mayıs 2026"
        ),
        QuotationRequestUiModel(
            quotationRequestId = 2,
            requestNumber = "RFQ-2026-0002",
            title = "Ambalaj Ürünleri İçin Teklif",
            categoryName = "Ambalaj Ve Paketleme",
            quantityText = "10.000 Adet",
            statusText = "Yanıtlandı",
            statusType = QuotationStatusType.Answered,
            insertedDate = "19 Mayıs 2026"
        )
    )
}

private enum class QuotationStatusType {
    Open,
    Answered,
    Expired
}

private data class QuotationRequestUiModel(
    val quotationRequestId: Int,
    val requestNumber: String,
    val title: String,
    val categoryName: String,
    val quantityText: String,
    val statusText: String,
    val statusType: QuotationStatusType,
    val insertedDate: String
)