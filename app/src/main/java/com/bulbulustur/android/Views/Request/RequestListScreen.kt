package com.bulbulustur.android.Views.Account.request

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
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun RequestListScreen(
    onBackClick: () -> Unit = {},
    onRequestDetailClick: (Int) -> Unit = {},
    onCreateRequestClick: () -> Unit = {}
) {
    val requests = getDemoRequests()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Taleplerim",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Add,
                actionContentDescription = "Talep Aç",
                onActionClick = onCreateRequestClick
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
            if (requests.isEmpty()) {
                item {
                    RequestEmptyState(
                        onCreateRequestClick = onCreateRequestClick
                    )
                }
            }

            items(
                items = requests,
                key = { request -> request.requestId }
            ) { request ->
                RequestCard(
                    request = request,
                    onRequestDetailClick = onRequestDetailClick
                )
            }
        }
    }
}

@Composable
private fun RequestCard(
    request: RequestUiModel,
    onRequestDetailClick: (Int) -> Unit
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
                RequestIconBox(
                    text = request.shortCode
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = request.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = request.requestNumber,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    RequestStatusBadge(
                        statusText = request.statusText,
                        statusType = request.statusType
                    )
                }
            }

            Text(
                text = request.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = request.insertedDate,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Talebi Gör",
                onClick = {
                    onRequestDetailClick(request.requestId)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun RequestEmptyState(
    onCreateRequestClick: () -> Unit
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
            RequestEmptyIconBox()

            Text(
                text = "Henüz Talebiniz Yok",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Destek veya işlem talepleriniz oluştuğunda burada listelenir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Talep Aç",
                onClick = onCreateRequestClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun RequestStatusBadge(
    statusText: String,
    statusType: RequestStatusType
) {
    val backgroundColor = when (statusType) {
        RequestStatusType.Open -> BbColors.Blue.Blue50
        RequestStatusType.Waiting -> BbColors.Orange.Orange50
        RequestStatusType.Closed -> BbColors.Green.Green50
    }

    val textColor = when (statusType) {
        RequestStatusType.Open -> BbColors.Blue.Blue700
        RequestStatusType.Waiting -> BbColors.Orange.Orange700
        RequestStatusType.Closed -> BbColors.Green.Green700
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
private fun RequestIconBox(
    text: String
) {
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
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun RequestEmptyIconBox() {
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
            text = "?",
            style = MaterialTheme.typography.headlineSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

private fun getDemoRequests(): List<RequestUiModel> {
    return listOf(
        RequestUiModel(
            requestId = 1,
            requestNumber = "REQ-2026-0001",
            title = "Sipariş Teslimat Sorusu",
            description = "Teslimat süreciyle ilgili bilgi talebi oluşturuldu.",
            statusText = "Açık",
            statusType = RequestStatusType.Open,
            shortCode = "R",
            insertedDate = "22 Mayıs 2026"
        ),
        RequestUiModel(
            requestId = 2,
            requestNumber = "REQ-2026-0002",
            title = "Fatura Talebi",
            description = "Sipariş faturasının yeniden gönderilmesi talep edildi.",
            statusText = "Kapandı",
            statusType = RequestStatusType.Closed,
            shortCode = "F",
            insertedDate = "18 Mayıs 2026"
        )
    )
}

private enum class RequestStatusType {
    Open,
    Waiting,
    Closed
}

private data class RequestUiModel(
    val requestId: Int,
    val requestNumber: String,
    val title: String,
    val description: String,
    val statusText: String,
    val statusType: RequestStatusType,
    val shortCode: String,
    val insertedDate: String
)