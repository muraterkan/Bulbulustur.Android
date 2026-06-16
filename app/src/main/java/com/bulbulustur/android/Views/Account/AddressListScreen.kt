package com.bulbulustur.android.Views.Account

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
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTypography

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
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
                .background(BbColors.Surface)
        ) {
            AddressCardHeader(
                address = address
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                AddressInfoRow(
                    icon = Icons.Outlined.Person,
                    title = "AlÄ±cÄ±",
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
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    BbButton(
                        text = "DÃ¼zenle",
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
                                tint = BbColors.TextStrong,
                                modifier = Modifier.size(BbIcon.ButtonIcon)
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
                                tint = BbColors.White,
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
private fun AddressCardHeader(
    address: AddressUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Yellow.Yellow50)
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AddressIconBox()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = address.title,
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            Text(
                text = address.receiverName,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
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
                color = BbColors.SurfaceMuted,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.TextMuted,
            modifier = Modifier.size(BbIcon.Ui)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )

            Text(
                text = value,
                style = BbTypography.bodyMedium,
                color = BbColors.TextStrong
            )
        }
    }
}

@Composable
private fun AddressDefaultBadge() {
    Box(
        modifier = Modifier
            .background(
                color = BbColors.Green.Green50,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            )
    ) {
        Text(
            text = "VarsayÄ±lan Adres",
            style = BbTypography.labelSmall,
            color = BbColors.Green.Green700
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            AddressIconBox()

            Text(
                text = "KayÄ±t BulunamadÄ±",
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            Text(
                text = "HenÃ¼z kayÄ±tlÄ± adresiniz bulunmuyor. Teslimat ve fatura sÃ¼reÃ§leri iÃ§in yeni adres ekleyebilirsiniz.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
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
            .size(BbIcon.BoxLg)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.Section)
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
