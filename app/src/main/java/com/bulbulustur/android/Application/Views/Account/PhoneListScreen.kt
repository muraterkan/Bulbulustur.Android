package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.bulbulustur.android.businesslayer.Core.DTO.MemberPhoneDTO

@Composable
fun PhoneListScreen(
    phones: List<MemberPhoneDTO>,
    isLoading: Boolean = false,
    currentAction: String? = null,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onCreatePhoneClick: () -> Unit = {},
    onVerifyPhoneClick: (Int) -> Unit = {},
    onDeletePhoneClick: (Int) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Telefonlarım",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Add,
                actionContentDescription = "Telefon Ekle",
                onActionClick = onCreatePhoneClick
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
            if (isLoading && currentAction == "GetPhones" && phones.isEmpty()) {
                item {
                    PhoneLoadingState()
                }
            } else if (!errorMessage.isNullOrBlank() && phones.isEmpty()) {
                item {
                    PhoneErrorState(
                        message = errorMessage,
                        onRetryClick = onRetryClick
                    )
                }
            } else if (phones.isEmpty()) {
                item {
                    PhoneEmptyState(
                        onCreatePhoneClick = onCreatePhoneClick
                    )
                }
            } else {
                items(
                    items = phones,
                    key = { phone -> phone.MemberPhoneId }
                ) { phone ->
                    PhoneCard(
                        phone = phone,
                        isDeleting = isLoading &&
                                currentAction == "DeletePhone",
                        isSendingSms = isLoading &&
                                currentAction == "SendPhoneVerificationSms",
                        onVerifyPhoneClick = onVerifyPhoneClick,
                        onDeletePhoneClick = onDeletePhoneClick
                    )
                }
            }

            if (!errorMessage.isNullOrBlank() && phones.isNotEmpty()) {
                item {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun PhoneCard(
    phone: MemberPhoneDTO,
    isDeleting: Boolean,
    isSendingSms: Boolean,
    onVerifyPhoneClick: (Int) -> Unit,
    onDeletePhoneClick: (Int) -> Unit
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PhoneIconBox(
                    text = "T"
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = if (phone.IsDefault) {
                            "Varsayılan Telefon"
                        } else {
                            "Telefon"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = phone.Phone.ifBlank {
                            "Telefon bilgisi bulunamadı"
                        },
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    PhoneStatusBadge(
                        verified = phone.Verified
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                if (!phone.Verified) {
                    BbButton(
                        text = "Doğrula",
                        onClick = {
                            onVerifyPhoneClick(phone.MemberPhoneId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small,
                        enabled = !isSendingSms && !isDeleting,
                        isLoading = isSendingSms
                    )
                }

                BbButton(
                    text = "Sil",
                    onClick = {
                        onDeletePhoneClick(phone.MemberPhoneId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Danger,
                    size = BbButtonSize.Small,
                    enabled = !isSendingSms && !isDeleting,
                    isLoading = isDeleting
                )
            }
        }
    }
}

@Composable
private fun PhoneLoadingState() {
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
            CircularProgressIndicator()

            Text(
                text = "Telefonlar yükleniyor...",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PhoneErrorState(message: String, onRetryClick: () -> Unit) {
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
            Text(
                text = "Telefonlar Alınamadı",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )

            BbButton(
                text = "Tekrar Dene",
                onClick = onRetryClick,
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun PhoneEmptyState(onCreatePhoneClick: () -> Unit) {
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
            PhoneIconBox(
                text = "T"
            )

            Text(
                text = "Telefon Numarası Bulunmuyor",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Telefon ekleyerek doğrulama ve güvenlik süreçlerini daha sağlam hale getirebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(BBSpacing.Space1)
            )

            BbButton(
                text = "Telefon Ekle",
                onClick = onCreatePhoneClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun PhoneStatusBadge(verified: Boolean) {
    val backgroundColor = if (verified) {
        BBColors.Green.Green50
    } else {
        BBColors.Orange.Orange50
    }

    val textColor = if (verified) {
        BBColors.Green.Green700
    } else {
        BBColors.Orange.Orange700
    }

    val text = if (verified) {
        "Doğrulandı"
    } else {
        "Doğrulanmadı"
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
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
            color = textColor
        )
    }
}

@Composable
private fun PhoneIconBox(text: String) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space10)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = BBColors.Yellow.Yellow800
        )
    }
}