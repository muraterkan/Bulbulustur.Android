package com.bulbulustur.android.features.account.bank

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
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbInnerPageHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun BankAccountListScreen(
    onBackClick: () -> Unit = {},
    onCreateBankAccountClick: () -> Unit = {},
    onEditBankAccountClick: (Int) -> Unit = {},
    onDeleteBankAccountClick: (Int) -> Unit = {},
    onCopyIbanClick: (String) -> Unit = {}
) {
    val bankAccounts = getDemoBankAccounts()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Banka Hesaplarım",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Add,
                actionContentDescription = "Banka Hesabı Ekle",
                onActionClick = onCreateBankAccountClick
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
            item {
                BankAccountWarningBox()
            }

            if (bankAccounts.isEmpty()) {
                item {
                    BankAccountEmptyState(
                        onCreateBankAccountClick = onCreateBankAccountClick
                    )
                }
            }

            items(
                items = bankAccounts,
                key = { item -> item.bankAccountId }
            ) { item ->
                BankAccountCard(
                    item = item,
                    onEditBankAccountClick = onEditBankAccountClick,
                    onDeleteBankAccountClick = onDeleteBankAccountClick,
                    onCopyIbanClick = onCopyIbanClick
                )
            }
        }
    }
}

@Composable
private fun BankAccountCard(
    item: BankAccountUiModel,
    onEditBankAccountClick: (Int) -> Unit,
    onDeleteBankAccountClick: (Int) -> Unit,
    onCopyIbanClick: (String) -> Unit
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
            BankAccountCardHeader(
                item = item
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                BankAccountIbanBox(
                    iban = item.iban
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    BbButton(
                        text = "Kopyala",
                        onClick = {
                            onCopyIbanClick(item.iban)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.ContentCopy,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(BbIcon.ButtonIcon)
                            )
                        }
                    )

                    BbButton(
                        text = "Düzenle",
                        onClick = {
                            onEditBankAccountClick(item.bankAccountId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(BbIcon.ButtonIcon)
                            )
                        }
                    )

                    BbButton(
                        text = "Sil",
                        onClick = {
                            onDeleteBankAccountClick(item.bankAccountId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Danger,
                        size = BbButtonSize.Small,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onError,
                                modifier = Modifier.size(BbIcon.ButtonIcon)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BankAccountCardHeader(
    item: BankAccountUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Yellow.Yellow50)
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BankAccountIconBox()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "Banka Hesabı",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = item.bankName,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = item.accountOwner,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun BankAccountIbanBox(
    iban: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "IBAN",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = iban,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun BankAccountWarningBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.Orange.Orange50,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                tint = BbColors.Orange.Orange700,
                modifier = Modifier.size(BbIcon.Ui)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Geri Ödeme İçin Doğru IBAN Bilgisi Kullanılır.",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Orange.Orange700
                )

                Text(
                    text = "Sipariş iptali veya ürün iadesi nedeniyle yapılacak geri ödemelerde kayıtlı banka hesaplarınızdan uygun olan IBAN kullanılabilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun BankAccountEmptyState(
    onCreateBankAccountClick: () -> Unit
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
            BankAccountIconBox()

            Text(
                text = "Kayıt Bulunamadı",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Henüz kayıtlı banka hesabınız bulunmuyor. Geri ödeme süreçleri için IBAN ekleyebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Banka Hesabı Ekle",
                onClick = onCreateBankAccountClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun BankAccountIconBox() {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxLg)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountBalance,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.Section)
        )
    }
}

private fun getDemoBankAccounts(): List<BankAccountUiModel> {
    return listOf(
        BankAccountUiModel(
            bankAccountId = 1,
            bankName = "Türkiye İş Bankası",
            accountOwner = "Murat Erkan",
            iban = "TR20 0001 0023 1798 0076 4950 01"
        ),
        BankAccountUiModel(
            bankAccountId = 2,
            bankName = "Garanti BBVA",
            accountOwner = "Murat Erkan",
            iban = "TR00 1111 1111 1111 1111 1111 11"
        )
    )
}

private data class BankAccountUiModel(
    val bankAccountId: Int,
    val bankName: String,
    val accountOwner: String,
    val iban: String
)