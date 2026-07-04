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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.CircularProgressIndicator
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
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestPictureDTO
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun RequestDetailScreen(
    request: ReturnRequestDTO?,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onStoreClick: (Int) -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Talep Detayı",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && request == null -> {
                RequestDetailLoadingState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            !errorMessage.isNullOrBlank() && request == null -> {
                RequestDetailErrorState(
                    message = errorMessage,
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            request == null -> {
                RequestDetailNotFoundState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            else -> {
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

                    if (request.Description.isNotBlank()) {
                        item {
                            RequestDetailDescriptionCard(request = request)
                        }
                    }

                    item {
                        RequestDetailPropertiesCard(request = request)
                    }

                    item {
                        RequestDetailPhotosCard(pictures = request.Pictures)
                    }

                    if (request.StoreId > 0) {
                        item {
                            RequestDetailActionsCard(
                                request = request,
                                onStoreClick = onStoreClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RequestDetailSummaryCard(request: ReturnRequestDTO) {
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
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Talep #${request.ReturnRequestId}",
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
                        text = FormatReturnRequestDetailDate(request.InsertedDate),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            RequestDetailStatusBadge(
                text = request.ReturnRequestStatus.ifBlank {
                    GetReturnRequestDetailStatusFallback(request.ReturnRequestStatusId)
                },
                statusId = request.ReturnRequestStatusId
            )
        }
    }
}

@Composable
private fun RequestDetailProductCard(
    request: ReturnRequestDTO,
    onStoreClick: (Int) -> Unit
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
                        text = request.ProductName.ifBlank { "-" },
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    val price = request.Price

                    if (price != null) {
                        Text(
                            text = "Ürün Tutarı: ${FormatReturnRequestPrice(price, request.Currency)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

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
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    iconColor = MaterialTheme.colorScheme.onSurface
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = request.StoreName.ifBlank { "-" },
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Satıcı mağaza bilgileri",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (request.StoreId > 0) {
                    BbButton(
                        text = "Mağaza",
                        onClick = {
                            onStoreClick(request.StoreId)
                        },
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small
                    )
                }
            }
        }
    }
}

@Composable
private fun RequestDetailReasonCard(request: ReturnRequestDTO) {
    RequestDetailSimpleCard(
        icon = Icons.Outlined.ReceiptLong,
        iconColor = BBColors.Orange.Orange600,
        title = "Talep Nedeni",
        value = request.ReturnRequestType.ifBlank { "-" }
    )
}

@Composable
private fun RequestDetailDescriptionCard(request: ReturnRequestDTO) {
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
                text = request.Description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RequestDetailPropertiesCard(request: ReturnRequestDTO) {
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
                title = "Talep Bilgileri",
                subtitle = "Ürün varyantı ve teslim edilen içerikler"
            )

            RequestDetailPropertyRow(
                title = "Renk",
                value = request.Color.ifBlank { "-" }
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            RequestDetailPropertyRow(
                title = "Beden",
                value = request.Size.ifBlank { "-" }
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            RequestDetailPropertyRow(
                title = "Fatura",
                value = if (request.HaveInvoice == 1) "Mevcut" else "Mevcut Değil"
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            RequestDetailPropertyRow(
                title = "Aksesuar",
                value = if (request.HaveAccessory == 1) "Mevcut" else "Mevcut Değil"
            )
        }
    }
}

@Composable
private fun RequestDetailPhotosCard(pictures: List<ReturnRequestPictureDTO>) {
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
                title = "Fotoğraflar",
                subtitle = "Talep sırasında eklenen ürün fotoğrafları"
            )

            if (pictures.isEmpty()) {
                Text(
                    text = "Bu talebe ait fotoğraf bulunmuyor.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    pictures.forEachIndexed { index, picture ->
                        RequestDetailPhotoThumbnail(
                            picture = picture,
                            index = index
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RequestDetailPhotoThumbnail(
    picture: ReturnRequestPictureDTO,
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = Icons.Outlined.Image,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.Action)
            )

            Text(
                text = if (picture.PictureName.isBlank()) {
                    "Fotoğraf"
                } else {
                    "Görsel ${index + 1}"
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun RequestDetailActionsCard(
    request: ReturnRequestDTO,
    onStoreClick: (Int) -> Unit
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
                title = "İşlemler",
                subtitle = "Talebe bağlı hızlı aksiyonlar"
            )

            BbButton(
                text = "Mağazaya Git",
                onClick = {
                    onStoreClick(request.StoreId)
                },
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
private fun RequestDetailPropertyRow(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
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
    statusId: Int
) {
    val color = when (statusId) {
        2 -> BBColors.Orange.Orange700
        3 -> BBColors.Green.Green700
        4 -> BBColors.Red.Red700
        5 -> BBColors.Green.Green700
        else -> BBColors.Blue.Blue700
    }

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
private fun RequestDetailLoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun RequestDetailErrorState(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(BBSpacing.PageHorizontal),
        contentAlignment = Alignment.Center
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
                RequestDetailIconBox(
                    icon = Icons.Outlined.ErrorOutline,
                    backgroundColor = BBColors.Red.Red50,
                    iconColor = BBColors.Red.Red700
                )

                Text(
                    text = "Talep Yüklenemedi",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbButton(
                    text = "Tekrar Dene",
                    onClick = onRetryClick,
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun RequestDetailNotFoundState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(BBSpacing.PageHorizontal),
        contentAlignment = Alignment.Center
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
                RequestDetailIconBox(
                    icon = Icons.Outlined.ReceiptLong,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Text(
                    text = "Talep Bulunamadı",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "İade talebi silinmiş veya erişime kapatılmış olabilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun GetReturnRequestDetailStatusFallback(statusId: Int): String {
    return when (statusId) {
        1 -> "Talep Alındı"
        2 -> "İncelemede"
        3 -> "Onaylandı"
        4 -> "Reddedildi"
        5 -> "Tamamlandı"
        else -> "Talep Alındı"
    }
}

private fun FormatReturnRequestDetailDate(value: String): String {
    if (value.isBlank() || value.startsWith("0001-01-01")) {
        return "-"
    }

    val formatter = DateTimeFormatter.ofPattern(
        "dd MMMM yyyy HH:mm",
        Locale("tr", "TR")
    )

    return runCatching {
        OffsetDateTime.parse(value).format(formatter)
    }.recoverCatching {
        LocalDateTime.parse(value).format(formatter)
    }.getOrElse {
        value
    }
}

private fun FormatReturnRequestPrice(
    price: Double,
    currency: String
): String {
    val symbols = DecimalFormatSymbols(Locale("tr", "TR"))
    val formatter = DecimalFormat("#,##0.00", symbols)
    val currencyText = currency.ifBlank { "₺" }

    return "${formatter.format(price)} $currencyText"
}