package com.bulbulustur.android.Views.Bank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun BankAccountListScreen(
    onBackClick: () -> Unit = {},
    onCopyIbanClick: (String) -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Banka Hesapları",
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
            item {
                BankAccountIntroCard()
            }

            item {
                BankAccountWarningCard()
            }

            item {
                BbSectionHeader(
                    title = "Bulbulustur Banka Hesapları",
                    subtitle = "Ã–deme yapmadan önce açıklama alanına sipariş numaranızı yazmayı unutmayın."
                )
            }

            items(
                items = bankAccountItems(),
                key = { bankAccount -> bankAccount.bankAccountId }
            ) { bankAccount ->
                BankAccountCard(
                    bankAccount = bankAccount,
                    onCopyIbanClick = {
                        onCopyIbanClick(bankAccount.iban)
                    }
                )
            }

            item {
                BankAccountInfoCard()
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space8))
            }
        }
    }
}

@Composable
private fun BankAccountIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Security,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Ã–demenizi Güvenle Tamamlayın",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Havale veya EFT ile ödeme yapmak için aşaĞıdaki Bulbulustur banka hesaplarını kullanabilirsiniz.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun BankAccountWarningCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Açıklama Alanına Sipariş Numaranızı Yazın",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Havale / EFT sonrası ödeme eşleşmesinin hızlı yapılabilmesi için açıklama kısmına sipariş numaranızı ekleyin.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun BankAccountCard(
    bankAccount: BankAccountItem,
    onCopyIbanClick: () -> Unit
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Icon(
                    imageVector = bankAccount.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = bankAccount.bankName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = bankAccount.branchName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.Verified,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            BankAccountInfoBox(
                title = "Hesap Sahibi",
                value = bankAccount.accountOwner,
                icon = Icons.Outlined.AccountBalance
            )

            BankAccountInfoBox(
                title = "IBAN",
                value = bankAccount.iban,
                icon = Icons.Outlined.CreditCard
            )

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium,
                onClick = onCopyIbanClick
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.padding(BBSpacing.Space1))

                    Text(
                        text = "IBAN Kopyala",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = "Ã–deme açıklamasına sipariş numaranızı yazmanız önerilir.",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BankAccountInfoBox(
    title: String,
    value: String,
    icon: ImageVector
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
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
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun BankAccountInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Ã–deme Kontrolü",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Ã–demeniz banka hesabına ulaştıktan sonra siparişinizin ödeme durumu kontrol edilerek güncellenir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private data class BankAccountItem(
    val bankAccountId: Int,
    val bankName: String,
    val branchName: String,
    val accountOwner: String,
    val iban: String,
    val icon: ImageVector
)

private fun bankAccountItems(): List<BankAccountItem> {
    return listOf(
        BankAccountItem(
            bankAccountId = 1,
            bankName = "Ziraat Bankası",
            branchName = "Kartal Åubesi",
            accountOwner = "BULBULUSTUR Ä°NTERNET TEKNOLOJÄ°LERÄ° VE TÄ°CARET ANONÄ°M ÅÄ°RKETÄ°",
            iban = "TR12 0000 0000 0000 0000 0000 01",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 2,
            bankName = "Garanti Bankası",
            branchName = "Kartal Åubesi",
            accountOwner = "BULBULUSTUR Ä°NTERNET TEKNOLOJÄ°LERÄ° VE TÄ°CARET ANONÄ°M ÅÄ°RKETÄ°",
            iban = "TR12 0000 0000 0000 0000 0000 02",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 3,
            bankName = "Kuveyt Türk",
            branchName = "Kartal Åubesi",
            accountOwner = "BULBULUSTUR Ä°NTERNET TEKNOLOJÄ°LERÄ° VE TÄ°CARET ANONÄ°M ÅÄ°RKETÄ°",
            iban = "TR12 0000 0000 0000 0000 0000 03",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 4,
            bankName = "Yapı Kredi Bankası",
            branchName = "Kartal Åubesi",
            accountOwner = "BULBULUSTUR Ä°NTERNET TEKNOLOJÄ°LERÄ° VE TÄ°CARET ANONÄ°M ÅÄ°RKETÄ°",
            iban = "TR12 0000 0000 0000 0000 0000 04",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 5,
            bankName = "Ä°ş Bankası",
            branchName = "Kartal Åubesi",
            accountOwner = "BULBULUSTUR Ä°NTERNET TEKNOLOJÄ°LERÄ° VE TÄ°CARET ANONÄ°M ÅÄ°RKETÄ°",
            iban = "TR12 0000 0000 0000 0000 0000 05",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 6,
            bankName = "Halk Bankası",
            branchName = "Kartal Åubesi",
            accountOwner = "BULBULUSTUR Ä°NTERNET TEKNOLOJÄ°LERÄ° VE TÄ°CARET ANONÄ°M ÅÄ°RKETÄ°",
            iban = "TR12 0000 0000 0000 0000 0000 06",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 7,
            bankName = "Akbank",
            branchName = "Kartal Åubesi",
            accountOwner = "BULBULUSTUR Ä°NTERNET TEKNOLOJÄ°LERÄ° VE TÄ°CARET ANONÄ°M ÅÄ°RKETÄ°",
            iban = "TR12 0000 0000 0000 0000 0000 07",
            icon = Icons.Outlined.AccountBalance
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun BankAccountListScreenPreview() {
    BbTheme {
        BankAccountListScreen()
    }
}

