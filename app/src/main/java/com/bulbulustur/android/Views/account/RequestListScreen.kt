package com.bulbulustur.android.Views.account

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
import com.bulbulustur.android.wwwroot.theme.BbTypography

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
                .background(BbColors.Surface)
        ) {
            RequestCardHeader(
                request = request
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                ) {
                    RequestInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "Satıcı",
                        value = request.sellerName,
                        icon = Icons.Outlined.Storefront,
                        iconColor = BbColors.Blue.Blue600
                    )

                    RequestInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "Talep Nedeni",
                        value = request.reason,
                        icon = Icons.Outlined.ReceiptLong,
                        iconColor = BbColors.Orange.Orange600
                    )
                }

                RequestInfoBox(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Ürün",
                    value = request.productName,
                    icon = Icons.Outlined.Inventory2,
                    iconColor = BbColors.Yellow.Yellow800,
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
                            tint = BbColors.TextStrong,
                            modifier = Modifier.size(BbSpacing.Space5)
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
            .background(BbColors.Yellow.Yellow50)
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RequestIconBox(
            icon = Icons.Outlined.ReceiptLong
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "Talep No",
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )

            Text(
                text = request.requestNumber,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = BbColors.Yellow.Yellow800,
                    modifier = Modifier.size(BbSpacing.Space4)
                )

                Text(
                    text = request.createdDate,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
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
        BbColors.Yellow.Yellow50
    } else {
        BbColors.SurfaceMuted
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(BbSpacing.Space5)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = BbTypography.labelSmall,
                    color = BbColors.TextMuted
                )

                Text(
                    text = value,
                    style = BbTypography.bodyMedium,
                    color = BbColors.TextStrong
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
                color = BbColors.SurfaceMuted,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Text(
                text = "Açıklama",
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )

            Text(
                text = description,
                style = BbTypography.bodySmall,
                color = BbColors.TextStrong
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
                color = BbColors.SurfaceMuted,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.PhotoLibrary,
                    contentDescription = null,
                    tint = BbColors.Yellow.Yellow800,
                    modifier = Modifier.size(BbSpacing.Space5)
                )

                Text(
                    text = "İade Edilen Ürünün Fotoğrafları",
                    style = BbTypography.labelSmall,
                    color = BbColors.TextMuted
                )
            }

            if (photoCount > 0) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    repeat(photoCount) { index ->
                        RequestPhotoThumbnail(
                            index = index
                        )
                    }
                }
            } else {
                Text(
                    text = "Fotoğraf bulunmuyor.",
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
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
            .size(BbSpacing.Space12)
            .background(
                color = if (index % 2 == 0) {
                    BbColors.Orange.Orange100
                } else {
                    BbColors.Blue.Blue100
                },
                shape = BbRadius.MdShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "IMG",
            style = BbTypography.labelSmall,
            color = BbColors.TextStrong
        )
    }
}

@Composable
private fun RequestStatusBadge(
    status: RequestStatus
) {
    val backgroundColor = when (status) {
        RequestStatus.DamagedProduct -> BbColors.Red.Red50
        RequestStatus.WaitingReview -> BbColors.Orange.Orange50
        RequestStatus.Approved -> BbColors.Green.Green50
        RequestStatus.Rejected -> BbColors.Gray.Gray100
    }

    val textColor = when (status) {
        RequestStatus.DamagedProduct -> BbColors.Red.Red700
        RequestStatus.WaitingReview -> BbColors.Orange.Orange700
        RequestStatus.Approved -> BbColors.Green.Green700
        RequestStatus.Rejected -> BbColors.Gray.Gray700
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
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
            .size(BbSpacing.Space12)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbSpacing.Space6)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            RequestIconBox(
                icon = Icons.Outlined.ReceiptLong
            )

            Text(
                text = "Kayıt Bulunamadı",
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            Text(
                text = "Henüz oluşturulmuş iade veya sipariş talebiniz bulunmuyor. Sipariş detayından yeni talep oluşturabilirsiniz.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
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
            description = "Ürünün sağ tarafında dikiş hatası mevcut, değişim veya iade talep ediyorum.",
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
            description = "Sipariş ettiğim varyanttan farklı bir ürün teslim edildi. Değişim sürecini başlatmak istiyorum.",
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