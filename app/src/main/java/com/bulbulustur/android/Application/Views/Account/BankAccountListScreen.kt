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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
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
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                BankAccountIbanBox(
                    iban = item.iban
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
                                modifier = Modifier.size(BBIcon.ButtonIcon)
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
                                modifier = Modifier.size(BBIcon.ButtonIcon)
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
                                modifier = Modifier.size(BBIcon.ButtonIcon)
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
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BankAccountIconBox()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
                color = BBColors.Orange.Orange50,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                tint = BBColors.Orange.Orange700,
                modifier = Modifier.size(BBIcon.Ui)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Geri Ödeme İçin DoĞru IBAN Bilgisi Kullanılır.",
                    style = MaterialTheme.typography.labelLarge,
                    color = BBColors.Orange.Orange700
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
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
            .size(BBIcon.BoxLg)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountBalance,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.Section)
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


