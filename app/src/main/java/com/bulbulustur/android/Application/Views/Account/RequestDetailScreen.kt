package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material3.HorizontalDivider
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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha

@Composable
fun RequestDetailScreen(
    requestId: Int = 1,
    onBackClick: () -> Unit = {},
    onOrderClick: () -> Unit = {},
    onStoreClick: () -> Unit = {}
) {
    val request = getDemoRequestDetail(requestId)

    Scaffold(
        containerColor = BBColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Talep Detayı",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BBColors.SurfaceMuted)
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
                RequestDetailSummaryCard(request = request)
            }

            item {
                RequestDetailProductCard(
                    request = request,
                    onStoreClick = onStoreClick
                )
            }

            item {
                RequestDetailReasonCard(request = request)
            }

            item {
                RequestDetailDescriptionCard(request = request)
            }

            item {
                RequestDetailPhotosCard(request = request)
            }

            item {
                RequestDetailTimelineCard(request = request)
            }

            item {
                RequestDetailActionsCard(
                    onOrderClick = onOrderClick,
                    onStoreClick = onStoreClick
                )
            }
        }
    }
}

@Composable
private fun RequestDetailSummaryCard(
    request: RequestDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RequestDetailIconBox(
                icon = Icons.Outlined.ReceiptLong,
                backgroundColor = BBColors.Yellow.Yellow100,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = request.requestNumber,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.Inline)
                    )

                    Text(
                        text = request.createdDate,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            RequestDetailStatusBadge(
                text = request.statusText,
                color = request.statusColor
            )
        }
    }
}

@Composable
private fun RequestDetailProductCard(
    request: RequestDetailUiModel,
    onStoreClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            RequestDetailSectionTitle(
                title = "Ürün Bilgileri",
                subtitle = "Talebe konu ürün ve satıcı bilgileri"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RequestDetailIconBox(
                    icon = Icons.Outlined.Inventory2,
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = request.productName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Ürün Tutarı: ${request.productPriceText}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider(color = BBColors.Border)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.LgShape
                    )
                    .padding(BBSpacing.CardPaddingCompact),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RequestDetailIconBox(
                    icon = Icons.Outlined.Storefront,
                    backgroundColor = BBColors.Surface,
                    iconColor = MaterialTheme.colorScheme.onSurface
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = request.sellerName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Satıcı maĞaza bilgileri",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BbButton(
                    text = "MaĞaza",
                    onClick = onStoreClick,
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )
            }
        }
    }
}

@Composable
private fun RequestDetailReasonCard(
    request: RequestDetailUiModel
) {
    RequestDetailSimpleCard(
        icon = Icons.Outlined.ReceiptLong,
        iconColor = BBColors.Orange.Orange600,
        title = "Talep Nedeni",
        value = request.reason
    )
}

@Composable
private fun RequestDetailDescriptionCard(
    request: RequestDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = "Açıklama",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = request.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RequestDetailPhotosCard(
    request: RequestDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            RequestDetailSectionTitle(
                title = "FotoĞraflar",
                subtitle = "Talep sırasında eklenen ürün fotoĞrafları"
            )

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                repeat(request.photoCount) { index ->
                    RequestPhotoThumbnail(index = index)
                }
            }
        }
    }
}

@Composable
private fun RequestDetailTimelineCard(
    request: RequestDetailUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            RequestDetailSectionTitle(
                title = "Talep Süreci",
                subtitle = "Talebinizin işlem adımları"
            )

            request.steps.forEachIndexed { index, step ->
                RequestDetailTimelineRow(step = step)

                if (index != request.steps.lastIndex) {
                    HorizontalDivider(color = BBColors.Border)
                }
            }
        }
    }
}

@Composable
private fun RequestDetailActionsCard(
    onOrderClick: () -> Unit,
    onStoreClick: () -> Unit
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
            RequestDetailSectionTitle(
                title = "Ä°şlemler",
                subtitle = "Talebe baĞlı hızlı aksiyonlar"
            )

            BbButton(
                text = "Siparişe Git",
                onClick = onOrderClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )

            BbButton(
                text = "MaĞazaya Git",
                onClick = onStoreClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RequestDetailSimpleCard(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    value: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RequestDetailIconBox(
                icon = icon,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = iconColor
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = value,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun RequestDetailTimelineRow(
    step: RequestStepUiModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RequestDetailIconBox(
            icon = step.icon,
            backgroundColor = step.color.copy(alpha = if (step.isCompleted) 0.16f else 0.08f),
            iconColor = step.color
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = step.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = step.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RequestDetailSectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RequestDetailStatusBadge(
    text: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .background(
                color = color.copy(alpha = BBAlpha.Overlay),
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@Composable
private fun RequestDetailIconBox(
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color
) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Action)
        )
    }
}

@Composable
private fun RequestPhotoThumbnail(
    index: Int
) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space16)
            .background(
                color = if (index % 2 == 0) {
                    BBColors.Orange.Orange100
                } else {
                    BBColors.Blue.Blue100
                },
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = null,
            tint = BBColors.TextStrong,
            modifier = Modifier.size(BBIcon.Feature)
        )
    }
}

private fun getDemoRequestDetail(
    requestId: Int
): RequestDetailUiModel {
    return RequestDetailUiModel(
        requestId = requestId,
        requestNumber = "Talep #$requestId",
        createdDate = "10 Mayıs 2026",
        statusText = "Ä°ncelemede",
        statusColor = BBColors.Orange.Orange700,
        sellerName = "Base & Quality Store",
        reason = "Ürün Defolu / Hasarlı Geldi",
        productName = "Ortobella Comfort Genç Garson Bot 8028",
        productPriceText = "1.250 Amerikan Doları",
        description = "Ürünün saĞ tarafında dikiş hatası mevcut, deĞişim veya iade talep ediyorum.",
        photoCount = 3,
        steps = listOf(
            RequestStepUiModel(
                title = "Talep Oluşturuldu",
                description = "Talebiniz başarıyla oluşturuldu.",
                icon = Icons.Outlined.CheckCircle,
                color = BBColors.Green.Green600,
                isCompleted = true
            ),
            RequestStepUiModel(
                title = "FotoĞraflar Eklendi",
                description = "Talebe ait ürün fotoĞrafları kaydedildi.",
                icon = Icons.Outlined.Image,
                color = BBColors.Green.Green600,
                isCompleted = true
            ),
            RequestStepUiModel(
                title = "Satıcı Ä°ncelemesi",
                description = "Satıcının talebi incelemesi bekleniyor.",
                icon = Icons.Outlined.Timeline,
                color = BBColors.Orange.Orange600,
                isCompleted = false
            )
        )
    )
}

private data class RequestDetailUiModel(
    val requestId: Int,
    val requestNumber: String,
    val createdDate: String,
    val statusText: String,
    val statusColor: Color,
    val sellerName: String,
    val reason: String,
    val productName: String,
    val productPriceText: String,
    val description: String,
    val photoCount: Int,
    val steps: List<RequestStepUiModel>
)

private data class RequestStepUiModel(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val isCompleted: Boolean
)


