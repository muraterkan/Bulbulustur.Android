package com.bulbulustur.android.features.bank

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun BankAccountListScreen(
    onCopyIbanClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        item {
            BankAccountHeader()
        }

        item {
            BankAccountWarningCard()
        }

        item {
            BbSectionHeader(
                title = "Bulbulustur banka hesapları",
                subtitle = "Lütfen ödeme yapmadan önce açıklama alanına sipariş numaranızı yazmayı unutmayın"
            )
        }

        items(bankAccountItems()) { bankAccount ->
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
            Spacer(modifier = Modifier.height(BbSpacing.xl))
        }
    }
}

@Composable
private fun BankAccountHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Security,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Havale / EFT bilgileri",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Ödemenizi güvenle tamamlayın",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Havale veya EFT ile ödeme yapmak için aşağıdaki Bulbulustur banka hesaplarını kullanabilirsiniz.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                BbChip(
                    text = "Güvenli ödeme",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Sipariş açıklaması",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "IBAN kopyala",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun BankAccountWarningCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = "Açıklama alanına sipariş numaranızı yazın",
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.md),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
            ) {
                Icon(
                    imageVector = bankAccount.icon,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
                ) {
                    Text(
                        text = bankAccount.bankName,
                        style = MaterialTheme.typography.titleMedium,
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
                    tint = BbColors.Primary
                )
            }

            BankAccountInfoBox(
                title = "Hesap sahibi",
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
                onClick = onCopyIbanClick
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BbSpacing.md),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = null,
                        tint = BbColors.Primary
                    )

                    Spacer(modifier = Modifier.padding(BbSpacing.xs))

                    Text(
                        text = "IBAN kopyala",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = "Ödeme açıklamasına sipariş numaranızı yazmanız önerilir.",
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.TextMuted
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.TextMuted
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.lg),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = "Ödeme kontrolü",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Ödemeniz banka hesabına ulaştıktan sonra siparişinizin ödeme durumu kontrol edilerek güncellenir.",
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
            branchName = "Kartal Şubesi",
            accountOwner = "BULBULUSTUR İNTERNET TEKNOLOJİLERİ VE TİCARET ANONİM ŞİRKETİ",
            iban = "TR12 0000 0000 0000 0000 0000 01",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 2,
            bankName = "Garanti Bankası",
            branchName = "Kartal Şubesi",
            accountOwner = "BULBULUSTUR İNTERNET TEKNOLOJİLERİ VE TİCARET ANONİM ŞİRKETİ",
            iban = "TR12 0000 0000 0000 0000 0000 02",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 3,
            bankName = "Kuveyt Türk",
            branchName = "Kartal Şubesi",
            accountOwner = "BULBULUSTUR İNTERNET TEKNOLOJİLERİ VE TİCARET ANONİM ŞİRKETİ",
            iban = "TR12 0000 0000 0000 0000 0000 03",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 4,
            bankName = "Yapı Kredi Bankası",
            branchName = "Kartal Şubesi",
            accountOwner = "BULBULUSTUR İNTERNET TEKNOLOJİLERİ VE TİCARET ANONİM ŞİRKETİ",
            iban = "TR12 0000 0000 0000 0000 0000 04",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 5,
            bankName = "İş Bankası",
            branchName = "Kartal Şubesi",
            accountOwner = "BULBULUSTUR İNTERNET TEKNOLOJİLERİ VE TİCARET ANONİM ŞİRKETİ",
            iban = "TR12 0000 0000 0000 0000 0000 05",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 6,
            bankName = "Halk Bankası",
            branchName = "Kartal Şubesi",
            accountOwner = "BULBULUSTUR İNTERNET TEKNOLOJİLERİ VE TİCARET ANONİM ŞİRKETİ",
            iban = "TR12 0000 0000 0000 0000 0000 06",
            icon = Icons.Outlined.AccountBalance
        ),
        BankAccountItem(
            bankAccountId = 7,
            bankName = "Akbank",
            branchName = "Kartal Şubesi",
            accountOwner = "BULBULUSTUR İNTERNET TEKNOLOJİLERİ VE TİCARET ANONİM ŞİRKETİ",
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