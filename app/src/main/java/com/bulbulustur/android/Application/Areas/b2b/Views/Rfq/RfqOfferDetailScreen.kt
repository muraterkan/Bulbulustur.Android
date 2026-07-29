package com.bulbulustur.android.Application.Areas.b2b.Views.Rfq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextarea
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.SendedOfferDTO

@Composable
fun RfqOfferDetailScreen(
    offer: SendedOfferDTO?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    isSendingMessage: Boolean = false,
    messageError: String? = null,
    onBackClick: () -> Unit = {},
    onSellerClick: () -> Unit = {},
    onMessageClick: (String) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    var showMessageDialog by remember { mutableStateOf(false) }
    var messageText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Teklif Detayı",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            when {
                isLoading && offer == null -> {
                    item {
                        RfqOfferDetailLoadingCard()
                    }
                }

                !errorMessage.isNullOrBlank() && offer == null -> {
                    item {
                        RfqOfferDetailErrorCard(
                            message = errorMessage,
                            onRetryClick = onRetryClick
                        )
                    }
                }

                offer == null -> {
                    item {
                        RfqOfferDetailEmptyCard()
                    }
                }

                else -> {
                    item {
                        RfqOfferSummaryCard(offer = offer)
                    }

                    item {
                        RfqOfferInfoCard(offer = offer)
                    }

                    item {
                        RfqOfferMessageCard(offer = offer)
                    }

                    item {
                        RfqOfferActionCard(
                            onSellerClick = onSellerClick,
                            onMessageClick = {
                                messageText = ""
                                showMessageDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    if (showMessageDialog) {
        AlertDialog(
            onDismissRequest = {
                if (!isSendingMessage) {
                    showMessageDialog = false
                }
            },
            title = {
                Text(
                    text = "Satıcıya mesaj gönder",
                    style = BbTypography.titleMedium
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    Text(
                        text = "Teklif hakkında satıcıya iletmek istediğiniz mesajı yazın.",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    BbTextarea(
                        value = messageText,
                        onValueChange = {
                            messageText = it
                        },
                        label = "Mesaj",
                        placeholder = "Mesajınızı yazın...",
                        minLines = 4,
                        maxLines = 8
                    )

                    if (!messageError.isNullOrBlank()) {
                        Text(
                            text = messageError,
                            style = BbTypography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    enabled = messageText.isNotBlank() && !isSendingMessage,
                    onClick = {
                        val body = messageText.trim()

                        if (body.isNotEmpty()) {
                            onMessageClick(body)
                        }
                    }
                ) {
                    Text(
                        text = if (isSendingMessage) {
                            "Gönderiliyor..."
                        } else {
                            "Gönder"
                        }
                    )
                }
            },
            dismissButton = {
                TextButton(
                    enabled = !isSendingMessage,
                    onClick = {
                        showMessageDialog = false
                    }
                ) {
                    Text(text = "Vazgeç")
                }
            }
        )
    }
}

@Composable
private fun RfqOfferSummaryCard(
    offer: SendedOfferDTO
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.Handshake,
                contentDescription = null,
                tint = BBColors.Yellow.Yellow800,
                modifier = Modifier.size(BBIcon.Section)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Satıcı Teklifi",
                    style = BbTypography.labelSmall,
                    color = BBColors.Yellow.Yellow800
                )

                Text(
                    text = offer.Seller.ifBlank { "Satıcı" },
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "RFQ No: ${offer.BuyerRequestId} · Teklif No: ${offer.SendedOfferId}",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun RfqOfferInfoCard(
    offer: SendedOfferDTO
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
            RfqOfferInfoRow(
                icon = Icons.Outlined.Business,
                title = "Satıcı",
                value = offer.Seller.ifBlank { "-" }
            )

            RfqOfferInfoRow(
                icon = Icons.Outlined.CalendarMonth,
                title = "Gönderim Tarihi",
                value = offer.InsertedDate.ifBlank { "-" }
            )

            RfqOfferInfoRow(
                icon = Icons.Outlined.RequestQuote,
                title = "Bağlı RFQ",
                value = "RFQ-${offer.BuyerRequestId}"
            )

            RfqOfferInfoRow(
                icon = Icons.Outlined.Handshake,
                title = "Teklif Durumu",
                value = offer.StatusId.toString()
            )
        }
    }
}

@Composable
private fun RfqOfferMessageCard(
    offer: SendedOfferDTO
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
                text = "Teklif Açıklaması",
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = offer.OfferDetail.ifBlank {
                    "Teklif açıklaması bulunmuyor."
                },
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RfqOfferInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Ui)
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

@Composable
private fun RfqOfferActionCard(
    onSellerClick: () -> Unit,
    onMessageClick: () -> Unit
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
            BbButton(
                text = "Satıcıyı Gör",
                onClick = onSellerClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Business,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )

            BbButton(
                text = "Mesaj Gönder",
                onClick = onMessageClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Message,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun RfqOfferDetailLoadingCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Teklif detayı yükleniyor...",
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RfqOfferDetailErrorCard(
    message: String,
    onRetryClick: () -> Unit
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
                text = message,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )

            BbButton(
                text = "Tekrar Dene",
                onClick = onRetryClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Outline,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun RfqOfferDetailEmptyCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Teklif kaydı bulunamadı.",
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}