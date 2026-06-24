package com.bulbulustur.android.Application.Views.Account

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
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun RequestListScreen(
    onBackClick: () -> Unit = {},
    onRequestDetailClick: (Int) -> Unit = {},
    onOrderListClick: () -> Unit = {}
) {
    val requests = getDemoRequests()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Taleplerim",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            if (requests.isEmpty()) {
                item {
                    RequestEmptyState(
                        onOrderListClick = onOrderListClick
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
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            RequestCardHeader(
                request = request
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    RequestInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "Satıcı",
                        value = request.sellerName,
                        icon = Icons.Outlined.Storefront,
                        iconColor = BBColors.Blue.Blue600
                    )

                    RequestInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "Talep Nedeni",
                        value = request.reason,
                        icon = Icons.Outlined.ReceiptLong,
                        iconColor = BBColors.Orange.Orange600
                    )
                }

                RequestInfoBox(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Ürün",
                    value = request.productName,
                    icon = Icons.Outlined.Inventory2,
                    iconColor = BBColors.Yellow.Yellow800,
                    highlighted = true
                )

                RequestDescriptionBox(
                    description = request.description
                )

                RequestPhotoBox(
                    photoCount = request.photoCount
                )

                BbButton(
                    text = "Detaylar",
                    onClick = {
                        onRequestDetailClick(request.requestId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Visibility,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(BBSpacing.Space5)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun RequestCardHeader(
    request: RequestUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BBColors.Yellow.Yellow50)
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RequestIconBox(
            icon = Icons.Outlined.ReceiptLong
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = "Talep No",
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = request.requestNumber,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBSpacing.Space4)
                )

                Text(
                    text = request.createdDate,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        RequestStatusBadge(
            status = request.status
        )
    }
}

@Composable
private fun RequestInfoBox(
    modifier: Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color,
    highlighted: Boolean = false
) {
    val backgroundColor = if (highlighted) {
        BBColors.Yellow.Yellow50
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(BBSpacing.Space5)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = value,
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun RequestDescriptionBox(
    description: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = "Açıklama",
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = description,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun RequestPhotoBox(
    photoCount: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.PhotoLibrary,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBSpacing.Space5)
                )

                Text(
                    text = "İade Edilen Ürünün FotoĞrafları",
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (photoCount > 0) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    repeat(photoCount) { index ->
                        RequestPhotoThumbnail(
                            index = index
                        )
                    }
                }
            } else {
                Text(
                    text = "FotoĞraf bulunmuyor.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun RequestPhotoThumbnail(
    index: Int
) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space12)
            .background(
                color = if (index % 2 == 0) {
                    BBColors.Orange.Orange100
                } else {
                    BBColors.Blue.Blue100
                },
                shape = BBRadius.MdShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "IMG",
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun RequestStatusBadge(
    status: RequestStatus
) {
    val backgroundColor = when (status) {
        RequestStatus.DamagedProduct -> BBColors.Red.Red50
        RequestStatus.WaitingReview -> BBColors.Orange.Orange50
        RequestStatus.Approved -> BBColors.Green.Green50
        RequestStatus.Rejected -> BBColors.Gray.Gray100
    }

    val textColor = when (status) {
        RequestStatus.DamagedProduct -> BBColors.Red.Red700
        RequestStatus.WaitingReview -> BBColors.Orange.Orange700
        RequestStatus.Approved -> BBColors.Green.Green700
        RequestStatus.Rejected -> BBColors.Gray.Gray700
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            )
    ) {
        Text(
            text = status.title,
            style = BbTypography.labelSmall,
            color = textColor
        )
    }
}

@Composable
private fun RequestIconBox(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space12)
            .background(
                color = BBColors.Yellow.Yellow100,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBSpacing.Space6)
        )
    }
}

@Composable
private fun RequestEmptyState(
    onOrderListClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            RequestIconBox(
                icon = Icons.Outlined.ReceiptLong
            )

            Text(
                text = "Kayıt Bulunamadı",
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Henüz oluşturulmuş iade veya sipariş talebiniz bulunmuyor. Sipariş detayından yeni talep Oluşturabilirsiniz.",
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Siparişlerime Git",
                onClick = onOrderListClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

private fun getDemoRequests(): List<RequestUiModel> {
    return listOf(
        RequestUiModel(
            requestId = 1,
            requestNumber = "#1",
            createdDate = "10 Mayıs 2026",
            sellerName = "Base & Quality Store",
            reason = "Ürün Defolu/Hasarlı Geldi",
            productName = "Ortobella Comfort Genç Garson Bot 8028",
            description = "Ürünün saĞ tarafında dikiş hatası mevcut, deĞişim veya iade talep ediyorum.",
            photoCount = 1,
            status = RequestStatus.DamagedProduct
        ),
        RequestUiModel(
            requestId = 2,
            requestNumber = "#2",
            createdDate = "14 Mayıs 2026",
            sellerName = "Ortobella Comfort",
            reason = "Yanlış Ürün Gönderildi",
            productName = "Ortobella Comfort Hakiki Deri Terlik M13",
            description = "Sipariş ettiĞim varyanttan farklı bir ürün teslim edildi. DeĞişim sürecini başlatmak istiyorum.",
            photoCount = 2,
            status = RequestStatus.WaitingReview
        )
    )
}

private enum class RequestStatus(
    val title: String
) {
    DamagedProduct(
        title = "Ürün Defolu/Hasarlı Geldi"
    ),
    WaitingReview(
        title = "İncelemede"
    ),
    Approved(
        title = "Onaylandı"
    ),
    Rejected(
        title = "Reddedildi"
    )
}

private data class RequestUiModel(
    val requestId: Int,
    val requestNumber: String,
    val createdDate: String,
    val sellerName: String,
    val reason: String,
    val productName: String,
    val description: String,
    val photoCount: Int,
    val status: RequestStatus
)


