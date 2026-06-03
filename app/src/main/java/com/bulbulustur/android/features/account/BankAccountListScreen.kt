package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun BankAccountListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        BbSectionHeader(
            title = "Banka Hesaplarım",
            subtitle = "Ödeme ve iade süreçlerinde kullanılacak banka hesaplarını görüntüle"
        )

        BankAccountCard(
            bankName = "Türkiye İş Bankası",
            accountOwner = "Murat Erkan",
            iban = "TR00 0000 0000 0000 0000 0000 00"
        )

        BankAccountCard(
            bankName = "Garanti BBVA",
            accountOwner = "Murat Erkan",
            iban = "TR00 1111 1111 1111 1111 1111 11"
        )
    }
}

@Composable
private fun BankAccountCard(
    bankName: String,
    accountOwner: String,
    iban: String
) {
    BbCard(
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountBalance,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Column(
                    modifier = Modifier
                        .padding(start = BbSpacing.md)
                        .weight(1f)
                ) {
                    Text(
                        text = bankName,
                        style = BbTypography.titleSmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = accountOwner,
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ContentCopy,
                    contentDescription = "Kopyala",
                    tint = BbColors.TextMuted
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.CreditCard,
                    contentDescription = null,
                    tint = BbColors.TextMuted
                )

                Text(
                    text = iban,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextStrong,
                    modifier = Modifier.padding(start = BbSpacing.sm)
                )
            }
        }
    }
}