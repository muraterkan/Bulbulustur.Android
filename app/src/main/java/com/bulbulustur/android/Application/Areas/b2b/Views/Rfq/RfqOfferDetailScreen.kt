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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
                title = BBLocalization.Current.Get(key = "b3f5f4a8-42dc-45ca-8394-7d1919d9a101", fallback = "Teklif Detayı"),
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
                    text = BBLocalization.Current.Get(key = "c057af1a-9235-4700-8f57-a8ea89556bf7", fallback = "Satıcıya mesaj gönder"),
                    style = BbTypography.titleMedium
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "4302c892-bd4d-4361-a77f-72c1805de7bf", fallback = "Teklif hakkında satıcıya iletmek istediğiniz mesajı yazın."),
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    BbTextarea(
                        value = messageText,
                        onValueChange = {
                            messageText = it
                        },
                        label = BBLocalization.Current.Get(key = "74d3bcb0-a3c9-477f-82f1-c091809c5a00", fallback = "Mesaj"),
                        placeholder = BBLocalization.Current.Get(key = "aeee1dfb-9298-4919-950b-833a9e982240", fallback = "Mesajınızı yazın..."),
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
                            BBLocalization.Current.Get(key = "747533e0-13c8-4b49-82ca-ebf9fea6b37f", fallback = "Gönderiliyor...")
                        } else {
                            BBLocalization.Current.Get(key = "1bba90af-aa63-41f8-bd0d-b51c4477afd7", fallback = "")
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
                    Text(text = BBLocalization.Current.Get(key = "18a6f5c0-ab35-483d-8691-fad99e9680f2", fallback = "Vazgeç"))
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
                    text = BBLocalization.Current.Get(key = "f12ff8d3-fd4d-4197-b9a2-7399b8c2d803", fallback = "Satıcı Teklifi"),
                    style = BbTypography.labelSmall,
                    color = BBColors.Yellow.Yellow800
                )

                Text(
                    text = offer.Seller.ifBlank { BBLocalization.Current.Get(key = "2ac4c8be-0d5d-4c84-afe8-628839892727", fallback = "") },
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
                title = BBLocalization.Current.Get(key = "2ac4c8be-0d5d-4c84-afe8-628839892727", fallback = ""),
                value = offer.Seller.ifBlank { "-" }
            )

            RfqOfferInfoRow(
                icon = Icons.Outlined.CalendarMonth,
                title = BBLocalization.Current.Get(key = "3863686d-6463-4211-bcbd-983e5968738e", fallback = ""),
                value = offer.InsertedDate.ifBlank { "-" }
            )

            RfqOfferInfoRow(
                icon = Icons.Outlined.RequestQuote,
                title = BBLocalization.Current.Get(key = "71d5c915-7070-4504-8038-de18d51c4437", fallback = "Bağlı RFQ"),
                value = "RFQ-${offer.BuyerRequestId}"
            )

            RfqOfferInfoRow(
                icon = Icons.Outlined.Handshake,
                title = BBLocalization.Current.Get(key = "12b0ea44-d114-423f-a6a8-19785c4c0c5c", fallback = ""),
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
                text = BBLocalization.Current.Get(key = "d0f64b7c-a2f8-4479-8b25-39b21a907219", fallback = "Teklif Açıklaması"),
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = offer.OfferDetail.ifBlank {
                    BBLocalization.Current.Get(key = "0dc47331-859b-4760-98fb-04624b4dc27f", fallback = "Teklif açıklaması bulunmuyor.")
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
                text = BBLocalization.Current.Get(key = "cbbc18bc-5a0a-4dee-9d39-c470aba8a51b", fallback = "Satıcıyı Gör"),
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
                text = BBLocalization.Current.Get(key = "c294574b-e9e3-4820-b0b2-23b326a7aeb3", fallback = "Mesaj Gönder"),
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
            text = BBLocalization.Current.Get(key = "6fa8b424-23e4-4e5c-8ca5-025841b0d7b2", fallback = "Teklif detayı yükleniyor..."),
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
                text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"),
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
            text = BBLocalization.Current.Get(key = "610d11e9-739f-4470-be8c-2efc1e41560d", fallback = "Teklif kaydı bulunamadı."),
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}