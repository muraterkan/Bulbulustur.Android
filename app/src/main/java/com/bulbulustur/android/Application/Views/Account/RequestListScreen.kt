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
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun RequestListScreen(
    requests: List<ReturnRequestDTO>,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onRequestDetailClick: (Int) -> Unit = {},
    onOrderListClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "edf6011a-a790-4d23-8a93-c539be6986ae", fallback = "Taleplerim"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && requests.isEmpty() -> {
                RequestLoadingState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            !errorMessage.isNullOrBlank() && requests.isEmpty() -> {
                RequestErrorState(
                    message = errorMessage,
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            requests.isEmpty() -> {
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
                    )
                ) {
                    item {
                        RequestEmptyState(
                            onOrderListClick = onOrderListClick
                        )
                    }
                }
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
                    items(
                        items = requests,
                        key = { request -> request.ReturnRequestId }
                    ) { request ->
                        RequestCard(
                            request = request,
                            onRequestDetailClick = onRequestDetailClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RequestCard(
    request: ReturnRequestDTO,
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
            RequestCardHeader(request = request)

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
                        title = BBLocalization.Current.Get(key = "2ac4c8be-0d5d-4c84-afe8-628839892727", fallback = ""),
                        value = request.StoreName.OrDash(),
                        icon = Icons.Outlined.Storefront,
                        iconColor = BBColors.Blue.Blue600
                    )

                    RequestInfoBox(
                        modifier = Modifier.weight(1f),
                        title = BBLocalization.Current.Get(key = "09298210-5271-4572-b02e-3b328d7e9cbf", fallback = ""),
                        value = request.ReturnRequestType.OrDash(),
                        icon = Icons.Outlined.ReceiptLong,
                        iconColor = BBColors.Orange.Orange600
                    )
                }

                RequestInfoBox(
                    modifier = Modifier.fillMaxWidth(),
                    title = BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = ""),
                    value = request.ProductName.OrDash(),
                    icon = Icons.Outlined.Inventory2,
                    iconColor = BBColors.Yellow.Yellow800,
                    highlighted = true
                )

                if (!request.Description.isNullOrBlank()) {
                    RequestDescriptionBox(
                        description = request.Description.orEmpty()
                    )
                }

                RequestPhotoBox(
                    photoCount = request.Pictures.orEmpty().size
                )

                BbButton(
                    text = "Detaylar",
                    onClick = {
                        onRequestDetailClick(request.ReturnRequestId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Visibility,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(BBSpacing.Space5)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun RequestCardHeader(request: ReturnRequestDTO) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
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
                text = BBLocalization.Current.Get(key = "a521eeff-a1a3-4e23-b5b2-234acc43cb7a", fallback = "Talep No"),
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "#${request.ReturnRequestId}",
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
                    text = FormatReturnRequestDate(request.InsertedDate),
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        RequestStatusBadge(
            statusText = request.ReturnRequestStatus?.takeIf { it.isNotBlank() } ?: GetReturnRequestStatusFallback(request.ReturnRequestStatusId),
            statusId = request.ReturnRequestStatusId
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
        MaterialTheme.colorScheme.primaryContainer
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
private fun RequestDescriptionBox(description: String) {
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
                text = BBLocalization.Current.Get(key = "db0a3356-2fa4-4c1f-9432-2c299ac52b92", fallback = "Açıklama"),
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
private fun RequestPhotoBox(photoCount: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.PhotoLibrary,
                contentDescription = null,
                tint = BBColors.Yellow.Yellow800,
                modifier = Modifier.size(BBSpacing.Space5)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "874c44f2-1af0-4ddb-b8b9-7bf162436c47", fallback = "İade Edilen Ürünün Fotoğrafları"),
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = if (photoCount > 0) {
                        "$photoCount fotoğraf eklendi"
                    } else {
                        BBLocalization.Current.Get(key = "3f3c7c3d-754f-480b-aa36-7937b4b1fbe7", fallback = "Fotoğraf bulunmuyor")
                    },
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun RequestStatusBadge(
    statusText: String,
    statusId: Int
) {
    val backgroundColor = when (statusId) {
        2 -> BBColors.Orange.Orange50
        3 -> BBColors.Green.Green50
        4 -> BBColors.Red.Red50
        5 -> BBColors.Green.Green50
        else -> BBColors.Blue.Blue50
    }

    val textColor = when (statusId) {
        2 -> BBColors.Orange.Orange700
        3 -> BBColors.Green.Green700
        4 -> BBColors.Red.Red700
        5 -> BBColors.Green.Green700
        else -> BBColors.Blue.Blue700
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
            text = statusText,
            style = BbTypography.labelSmall,
            color = textColor
        )
    }
}

@Composable
private fun RequestIconBox(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space12)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
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
private fun RequestLoadingState(modifier: Modifier = Modifier) {
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
private fun RequestErrorState(
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
                RequestIconBox(
                    icon = Icons.Outlined.ErrorOutline
                )

                Text(
                    text = BBLocalization.Current.Get(key = "1f53e12e-26f2-4f2c-8906-a60d32b3b490", fallback = "Talepler Yüklenemedi"),
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = message,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"),
                    onClick = onRetryClick,
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(BBSpacing.Space5)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun RequestEmptyState(onOrderListClick: () -> Unit) {
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
                text = BBLocalization.Current.Get(key = "1bf1d23b-76a3-424f-bf58-9054748887f3", fallback = ""),
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "d3e06bd9-052d-420a-813f-e89b70a64217", fallback = "Henüz oluşturulmuş iade talebiniz bulunmuyor. Sipariş detayından yeni bir iade talebi oluşturabilirsiniz."),
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "4a966b67-add3-4ad0-8a8b-a3e5e7318297", fallback = "Siparişlerime Git"),
                onClick = onOrderListClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

private fun String?.OrDash(): String = this?.takeIf { it.isNotBlank() } ?: "-"

private fun GetReturnRequestStatusFallback(statusId: Int): String {
    return when (statusId) {
        1 -> BBLocalization.Current.Get(key = "6b04a632-6a4a-4b5a-bc8c-235753a8c8b2", fallback = "Talep Alındı")
        2 -> BBLocalization.Current.Get(key = "968498ba-6375-489a-9ece-b790e6cb4975", fallback = "İncelemede")
        3 -> BBLocalization.Current.Get(key = "f160b198-ab2d-4515-8126-2ee3143329ec", fallback = "Onaylandı")
        4 -> "Reddedildi"
        5 -> BBLocalization.Current.Get(key = "60ae9048-3404-4ea6-a789-f75e02e0b4ea", fallback = "Tamamlandı")
        else -> BBLocalization.Current.Get(key = "6b04a632-6a4a-4b5a-bc8c-235753a8c8b2", fallback = "Talep Alındı")
    }
}

private fun FormatReturnRequestDate(value: String?): String {
    if (value.isNullOrBlank() || value.startsWith("0001-01-01")) {
        return "-"
    }

    val outputFormatter = DateTimeFormatter.ofPattern(
        "dd MMMM yyyy",
        Locale("tr", "TR")
    )

    return runCatching {
        OffsetDateTime.parse(value).format(outputFormatter)
    }.recoverCatching {
        LocalDateTime.parse(value).format(outputFormatter)
    }.getOrElse {
        value
    }
}