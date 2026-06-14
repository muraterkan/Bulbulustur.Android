package com.bulbulustur.android.Features.account.phone

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing

@Composable
fun PhoneListScreen(
    onBackClick: () -> Unit = {},
    onCreatePhoneClick: () -> Unit = {},
    onVerifyPhoneClick: (Int) -> Unit = {},
    onDeletePhoneClick: (Int) -> Unit = {}
) {
    val phones = getDemoPhones()

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
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            if (phones.isEmpty()) {
                item {
                    PhoneEmptyState(
                        onCreatePhoneClick = onCreatePhoneClick
                    )
                }
            }

            items(
                items = phones,
                key = { phone -> phone.memberPhoneId }
            ) { phone ->
                PhoneCard(
                    phone = phone,
                    onVerifyPhoneClick = onVerifyPhoneClick,
                    onDeletePhoneClick = onDeletePhoneClick
                )
            }
        }
    }
}

@Composable
private fun PhoneCard(
    phone: AccountPhoneUiModel,
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PhoneIconBox(
                    text = "☎"
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Telefon",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = phone.phone,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    PhoneStatusBadge(
                        verified = phone.verified
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                if (!phone.verified) {
                    BbButton(
                        text = "Doğrula",
                        onClick = {
                            onVerifyPhoneClick(phone.memberPhoneId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small
                    )
                }

                BbButton(
                    text = "Sil",
                    onClick = {
                        onDeletePhoneClick(phone.memberPhoneId)
                    },
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Danger,
                    size = BbButtonSize.Small
                )
            }
        }
    }
}

@Composable
private fun PhoneEmptyState(
    onCreatePhoneClick: () -> Unit
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
            PhoneIconBox(
                text = "☎"
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

            Spacer(modifier = Modifier.height(BbSpacing.Space1))

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
private fun PhoneStatusBadge(
    verified: Boolean
) {
    val backgroundColor = if (verified) {
        BbColors.Green.Green50
    } else {
        BbColors.Orange.Orange50
    }

    val textColor = if (verified) {
        BbColors.Green.Green700
    } else {
        BbColors.Orange.Orange700
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
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
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
private fun PhoneIconBox(
    text: String
) {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space10)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = BbColors.Yellow.Yellow800
        )
    }
}

private fun getDemoPhones(): List<AccountPhoneUiModel> {
    return listOf(
        AccountPhoneUiModel(
            memberPhoneId = 1,
            phone = "+90 555 710 64 17",
            verified = true
        ),
        AccountPhoneUiModel(
            memberPhoneId = 2,
            phone = "+90 532 000 00 00",
            verified = false
        )
    )
}

private data class AccountPhoneUiModel(
    val memberPhoneId: Int,
    val phone: String,
    val verified: Boolean
)