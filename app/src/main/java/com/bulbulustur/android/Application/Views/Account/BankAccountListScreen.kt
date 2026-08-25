package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.MemberBankAccountDTO
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Localization.BBLocalization

@Composable
fun BankAccountListScreen(
    bankAccounts: List<MemberBankAccountDTO>,
    isLoading: Boolean = false,
    currentAction: String? = null,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onCreateBankAccountClick: () -> Unit = {},
    onEditBankAccountClick: (Int) -> Unit = {},
    onDeleteBankAccountClick: (Int) -> Unit = {},
    onCopyIbanClick: (String) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    var selectedBankAccountId by remember {
        mutableStateOf<Int?>(null)
    }

    val isDeleting = isLoading && currentAction == "DeleteBankAccount"

    LaunchedEffect(bankAccounts, selectedBankAccountId) {
        val selectedId = selectedBankAccountId ?: return@LaunchedEffect

        if (bankAccounts.none { it.MemberBankAccountId == selectedId }) {
            selectedBankAccountId = null
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "56bb1452-c8f8-4bb0-8501-0947118999df", fallback = "Banka Hesaplarım"),
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Add,
                actionContentDescription = BBLocalization.Current.Get(key = "42468515-8191-418a-a245-72ecf9a06558", fallback = ""),
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

            if (isLoading && currentAction == "GetBankAccounts" && bankAccounts.isEmpty()) {
                item {
                    BankAccountLoadingState()
                }
            } else if (!errorMessage.isNullOrBlank() && bankAccounts.isEmpty()) {
                item {
                    BankAccountErrorState(
                        errorMessage = errorMessage,
                        onRetryClick = onRetryClick
                    )
                }
            } else if (bankAccounts.isEmpty()) {
                item {
                    BankAccountEmptyState(
                        onCreateBankAccountClick = onCreateBankAccountClick
                    )
                }
            } else {
                items(
                    items = bankAccounts,
                    key = { item -> item.MemberBankAccountId }
                ) { item ->
                    BankAccountCard(
                        item = item,
                        isDeleting = isDeleting,
                        onEditBankAccountClick = onEditBankAccountClick,
                        onDeleteBankAccountClick = { bankAccountId ->
                            selectedBankAccountId = bankAccountId
                        },
                        onCopyIbanClick = onCopyIbanClick
                    )
                }
            }

            if (!errorMessage.isNullOrBlank() && bankAccounts.isNotEmpty()) {
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

    selectedBankAccountId?.let { bankAccountId ->
        BankAccountDeleteSheet(
            isDeleting = isDeleting,
            onDismissClick = {
                if (!isDeleting) {
                    selectedBankAccountId = null
                }
            },
            onConfirmClick = {
                if (!isDeleting) {
                    onDeleteBankAccountClick(bankAccountId)
                }
            }
        )
    }
}

@Composable
private fun BankAccountCard(
    item: MemberBankAccountDTO,
    isDeleting: Boolean,
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
            BankAccountCardHeader(item)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                BankAccountIbanBox(
                    iban = FormatIban(item.BankIban)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    BbButton(
                        text = "Kopyala",
                        onClick = {
                            onCopyIbanClick(item.BankIban)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Light,
                        size = BbButtonSize.Small,
                        enabled = item.BankIban.isNotBlank() && !isDeleting,
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
                        text = BBLocalization.Current.Get(key = "6a23f3ad-9109-471d-a670-7b5a40cf3cd9", fallback = "Düzenle"),
                        onClick = {
                            onEditBankAccountClick(item.MemberBankAccountId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Small,
                        enabled = item.MemberBankAccountId > 0 && !isDeleting,
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
                        text = BBLocalization.Current.Get(key = "e38050df-62e1-4b83-97ee-2643ad73390c", fallback = "Sil"),
                        onClick = {
                            onDeleteBankAccountClick(item.MemberBankAccountId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Danger,
                        size = BbButtonSize.Small,
                        enabled = item.MemberBankAccountId > 0 && !isDeleting,
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
private fun BankAccountCardHeader(item: MemberBankAccountDTO) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BankAccountIconBox(
            picture = item.Picture
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "5a4bd077-2a76-4789-966c-a292d3a38902", fallback = "Banka Hesabı"),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = item.BankName.ifBlank {
                    BBLocalization.Current.Get(key = "68cf94b3-e7e0-4777-82ee-dd89495433a0", fallback = "Banka bilgisi bulunamadı")
                },
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = item.BankHolder.ifBlank {
                    BBLocalization.Current.Get(key = "8a8839e0-2eb3-4bf5-8ec4-e5b12928165e", fallback = "Hesap sahibi bilgisi bulunamadı")
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun BankAccountIbanBox(iban: String) {
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
                text = iban.ifBlank {
                    BBLocalization.Current.Get(key = "8a5a9f13-66c3-4e81-a21d-0cb5684727a4", fallback = "IBAN bilgisi bulunamadı")
                },
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
                color = MaterialTheme.colorScheme.errorContainer,
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
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.size(BBIcon.Ui)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "fa4bc144-97cf-4b31-9961-f141c7bb8e90", fallback = "Geri Ödeme İçin Doğru IBAN Bilgisi Kullanılır."),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )

                Text(
                    text = BBLocalization.Current.Get(key = "2bb22256-f908-477b-9956-19bf346a7420", fallback = "Sipariş iptali veya ürün iadesi nedeniyle yapılacak geri ödemelerde kayıtlı banka hesaplarınızdan uygun olan IBAN kullanılabilir."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun BankAccountLoadingState() {
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
            CircularProgressIndicator(
                modifier = Modifier.size(BBIcon.BoxMd),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = BBLocalization.Current.Get(key = "52abfd9f-9df9-453e-b4ef-876dc4e7a448", fallback = "Banka hesapları yükleniyor."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BankAccountErrorState(
    errorMessage: String,
    onRetryClick: () -> Unit
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
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(BBIcon.EmptyStateIcon)
            )

            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"),
                onClick = onRetryClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
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
                text = BBLocalization.Current.Get(key = "1bf1d23b-76a3-424f-bf58-9054748887f3", fallback = ""),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "49e5c046-3a73-4e9a-bfc0-6f135bfcf376", fallback = "Henüz kayıtlı banka hesabınız bulunmuyor. Geri ödeme süreçleri için IBAN ekleyebilirsiniz."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "42468515-8191-418a-a245-72ecf9a06558", fallback = ""),
                onClick = onCreateBankAccountClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun BankAccountIconBox(
    picture: String = ""
) {
    var imageLoadFailed by remember(picture) {
        mutableStateOf(false)
    }

    val pictureUrl = ImageUrlResolver.Resolve(picture)

    Box(
        modifier = Modifier
            .size(BBIcon.BoxLg)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (pictureUrl.isNotBlank() && !imageLoadFailed) {
            AsyncImage(
                model = pictureUrl,
                contentDescription = BBLocalization.Current.Get(key = "9b887f37-1dce-414f-be28-b15c3dbc9e08", fallback = "Banka logosu"),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(BBSpacing.Space2),
                contentScale = ContentScale.Fit,
                onError = {
                    imageLoadFailed = true
                }
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.AccountBalance,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Section)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BankAccountDeleteSheet(
    isDeleting: Boolean,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissClick,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    start = BBSpacing.PageHorizontal,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.PageBottom
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "d69b7f33-6fcb-4239-89de-f922f817a429", fallback = "Banka Hesabını Sil"),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "d80b81ba-b06f-47d2-8cf5-401acbb5783d", fallback = "Bu banka hesabını silmek istediğinize emin misiniz? Silinen hesap geri ödeme ve para aktarımı işlemlerinde kullanılamaz."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = BBLocalization.Current.Get(key = "18a6f5c0-ab35-483d-8691-fad99e9680f2", fallback = "Vazgeç"),
                    onClick = onDismissClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Medium,
                    enabled = !isDeleting
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "5516ed8d-a97c-4587-bdcf-5b9a91b74d6e", fallback = "Hesabı Sil"),
                    onClick = onConfirmClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Danger,
                    size = BbButtonSize.Medium,
                    enabled = !isDeleting,
                    isLoading = isDeleting
                )
            }
        }
    }
}

private fun FormatIban(iban: String): String {
    return iban
        .replace(" ", "")
        .uppercase()
        .chunked(4)
        .joinToString(" ")
}