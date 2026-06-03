package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddLocationAlt
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun AddressListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        BbSectionHeader(
            title = "Adreslerim",
            subtitle = "Kayıtlı adreslerini görüntüle, düzenle veya yeni adres ekle"
        )

        BbButton(
            text = "Yeni Adres Ekle",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )

        AddressCard(
            title = "Ev Adresim",
            ownerName = "Murat Erkan",
            address = "Fulya mah., Aytekinkotil cad., No: 11/1"
        )
    }
}

@Composable
private fun AddressCard(
    title: String,
    ownerName: String,
    address: String
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
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Column(
                    modifier = Modifier
                        .padding(start = BbSpacing.md)
                        .weight(1f)
                ) {
                    Text(
                        text = title,
                        style = BbTypography.titleSmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = ownerName,
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = BbColors.TextMuted
                )

                Text(
                    text = address,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextStrong,
                    modifier = Modifier.padding(start = BbSpacing.sm)
                )
            }

            Spacer(modifier = Modifier.height(BbSpacing.xs))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                BbButton(
                    text = "Düzenle",
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = null
                        )
                    }
                )

                BbButton(
                    text = "Sil",
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}