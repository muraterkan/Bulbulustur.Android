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
import androidx.compose.material.icons.outlined.AddLocationAlt
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Localization.BBLocalization
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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO

@Composable
fun AddressListScreen(
    addresses: List<MemberAddressDTO>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    deletingAddressId: Int? = null,
    onBackClick: () -> Unit = {},
    onCreateAddressClick: () -> Unit = {},
    onEditAddressClick: (String) -> Unit = {},
    onDeleteAddressClick: (Int) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "a5c6a1af-5860-4dc6-9f86-c80c8631f063", fallback = "Adreslerim"),
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.AddLocationAlt,
                actionContentDescription = BBLocalization.Current.Get(key = "a1b99907-afe9-4bd9-abfd-58846f4e9252", fallback = "Yeni Adres Ekle"),
                onActionClick = onCreateAddressClick
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
            if (isLoading && addresses.isEmpty()) {
                item {
                    AddressLoadingState()
                }

                return@LazyColumn
            }

            if (!errorMessage.isNullOrBlank() && addresses.isEmpty()) {
                item {
                    AddressErrorState(
                        message = errorMessage,
                        onRetryClick = onRetryClick
                    )
                }

                return@LazyColumn
            }

            if (addresses.isEmpty()) {
                item {
                    AddressEmptyState(
                        onCreateAddressClick = onCreateAddressClick
                    )
                }

                return@LazyColumn
            }

            items(
                items = addresses,
                key = { address -> address.MemberAddressId }
            ) { address ->
                AddressCard(
                    address = address,
                    isDeleting = deletingAddressId == address.MemberAddressId,
                    onEditAddressClick = onEditAddressClick,
                    onDeleteAddressClick = onDeleteAddressClick
                )
            }
        }
    }
}

@Composable
private fun AddressCard(
    address: MemberAddressDTO,
    isDeleting: Boolean,
    onEditAddressClick: (String) -> Unit,
    onDeleteAddressClick: (Int) -> Unit
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
            AddressCardHeader(address = address)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                AddressInfoRow(
                    icon = Icons.Outlined.Person,
                    title = BBLocalization.Current.Get(key = "d8fb620c-aaae-404d-ac48-5d6b6f3d93ed", fallback = "Alıcı"),
                    value = "${address.Name} ${address.Surname}".trim()
                )

                AddressInfoRow(
                    icon = Icons.Outlined.LocationOn,
                    title = BBLocalization.Current.Get(key = "af1da4df-7298-4cd9-b256-371d098b59f7", fallback = "Adres"),
                    value = address.Address
                )

                if (address.Phone.isNotBlank()) {
                    AddressInfoRow(
                        icon = Icons.Outlined.Person,
                        title = BBLocalization.Current.Get(key = "cf948c6a-2e6a-4f1e-b77b-13f8d15a1a67", fallback = "Telefon"),
                        value = address.Phone
                    )
                }

                if (address.IsDefault) {
                    AddressDefaultBadge()
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    BbButton(
                        text = BBLocalization.Current.Get(key = "6a23f3ad-9109-471d-a670-7b5a40cf3cd9", fallback = "Düzenle"),
                        onClick = {
                            onEditAddressClick(address.AddressKey)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Medium,
                        enabled = !isDeleting,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = null,
                                modifier = Modifier.size(BBIcon.ButtonIcon)
                            )
                        }
                    )

                    BbButton(
                        text = BBLocalization.Current.Get(key = "e38050df-62e1-4b83-97ee-2643ad73390c", fallback = "Sil"),
                        onClick = {
                            onDeleteAddressClick(address.MemberAddressId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Outline,
                        size = BbButtonSize.Medium,
                        enabled = !isDeleting,
                        isLoading = isDeleting,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null,
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
private fun AddressCardHeader(address: MemberAddressDTO) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BBIcon.BoxMd)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = BBRadius.MdShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = null,
                tint = BBColors.Yellow.Yellow800,
                modifier = Modifier.size(BBIcon.Section)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = address.AddressTitle.ifBlank { BBLocalization.Current.Get(key = "af1da4df-7298-4cd9-b256-371d098b59f7", fallback = "Adres") },
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (address.PostCode.isNotBlank()) {
                Text(
                    text = "Posta Kodu: ${address.PostCode}",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun AddressInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BBIcon.SizeMd)
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
                text = value.ifBlank { "-" },
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun AddressDefaultBadge() {
    Box(
        modifier = Modifier
            .background(
                color = BBColors.Green.Green50,
                shape = BBRadius.PillShape
            )
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            )
    ) {
        Text(
            text = BBLocalization.Current.Get(key = "7fac1179-ab8e-4bb8-9ca0-92369db1597e", fallback = "Varsayılan Adres"),
            style = BbTypography.labelSmall,
            color = BBColors.Green.Green700
        )
    }
}

@Composable
private fun AddressLoadingState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Adresler yükleniyor...",
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AddressErrorState(
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
                color = BBColors.Red.Red600
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
private fun AddressEmptyState(
    onCreateAddressClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
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
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null,
                    tint = BBColors.Yellow.Yellow800,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "1bf1d23b-76a3-424f-bf58-9054748887f3", fallback = ""),
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Henüz kayıtlı adresiniz bulunmuyor. Teslimat ve fatura süreçleri için yeni adres ekleyebilirsiniz.",
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "a1b99907-afe9-4bd9-abfd-58846f4e9252", fallback = "Yeni Adres Ekle"),
                onClick = onCreateAddressClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}