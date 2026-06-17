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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun AddressListScreen(
    onBackClick: () -> Unit = {},
    onCreateAddressClick: () -> Unit = {},
    onEditAddressClick: (Int) -> Unit = {},
    onDeleteAddressClick: (Int) -> Unit = {}
) {
    val addresses = getDemoAddresses()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Adreslerim",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.AddLocationAlt,
                actionContentDescription = "Yeni Adres Ekle",
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
            if (addresses.isEmpty()) {
                item {
                    AddressEmptyState(
                        onCreateAddressClick = onCreateAddressClick
                    )
                }
            }

            items(
                items = addresses,
                key = { address -> address.addressId }
            ) { address ->
                AddressCard(
                    address = address,
                    onEditAddressClick = onEditAddressClick,
                    onDeleteAddressClick = onDeleteAddressClick
                )
            }
        }
    }
}

@Composable
private fun AddressCard(
    address: AddressUiModel,
    onEditAddressClick: (Int) -> Unit,
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
                .background(BBColors.Surface)
        ) {
            AddressCardHeader(
                address = address
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                AddressInfoRow(
                    icon = Icons.Outlined.Person,
                    title = "Alıcı",
                    value = address.receiverName
                )

                AddressInfoRow(
                    icon = Icons.Outlined.LocationOn,
                    title = "Adres",
                    value = address.fullAddress
                )

                if (address.isDefault) {
                    AddressDefaultBadge()
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    BbButton(
                        text = "Düzenle",
                        onClick = {
                            onEditAddressClick(address.addressId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Medium,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = null,
                                tint = BBColors.TextStrong,
                                modifier = Modifier.size(BBIcon.ButtonIcon)
                            )
                        }
                    )

                    BbButton(
                        text = "Sil",
                        onClick = {
                            onDeleteAddressClick(address.addressId)
                        },
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Danger,
                        size = BbButtonSize.Medium,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null,
                                tint = BBColors.White,
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
private fun AddressCardHeader(
    address: AddressUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BBColors.Yellow.Yellow50)
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AddressIconBox()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = address.title,
                style = BbTypography.titleMedium,
                color = BBColors.TextStrong
            )

            Text(
                text = address.receiverName,
                style = BbTypography.bodySmall,
                color = BBColors.TextMuted
            )
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
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BBColors.SurfaceMuted,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.TextMuted,
            modifier = Modifier.size(BBIcon.Ui)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.labelSmall,
                color = BBColors.TextMuted
            )

            Text(
                text = value,
                style = BbTypography.bodyMedium,
                color = BBColors.TextStrong
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
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            )
    ) {
        Text(
            text = "Varsayılan Adres",
            style = BbTypography.labelSmall,
            color = BBColors.Green.Green700
        )
    }
}

@Composable
private fun AddressEmptyState(
    onCreateAddressClick: () -> Unit
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
            AddressIconBox()

            Text(
                text = "Kayıt Bulunamadı",
                style = BbTypography.titleMedium,
                color = BBColors.TextStrong
            )

            Text(
                text = "Henüz kayıtlı adresiniz bulunmuyor. Teslimat ve fatura süreçleri için yeni adres ekleyebilirsiniz.",
                style = BbTypography.bodySmall,
                color = BBColors.TextMuted
            )

            BbButton(
                text = "Yeni Adres Ekle",
                onClick = onCreateAddressClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun AddressIconBox() {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxLg)
            .background(
                color = BBColors.Yellow.Yellow100,
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
}

private fun getDemoAddresses(): List<AddressUiModel> {
    return listOf(
        AddressUiModel(
            addressId = 1,
            title = "Ev Adresim",
            receiverName = "Murat Erkan",
            fullAddress = "Fulya mah. Aytekinkotil cad., No: 11/1",
            isDefault = true
        )
    )
}

private data class AddressUiModel(
    val addressId: Int,
    val title: String,
    val receiverName: String,
    val fullAddress: String,
    val isDefault: Boolean
)

