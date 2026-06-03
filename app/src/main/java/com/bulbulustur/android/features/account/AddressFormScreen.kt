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
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
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
fun AddressFormScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        BbSectionHeader(
            title = "Adresinizi Düzenleyin",
            subtitle = "Teslimatın doğru ilerlemesi için adres bilgilerini güncelle"
        )

        BbCard(
            padding = BbCardPadding.Medium
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
            ) {
                AddressFormSectionTitle(
                    title = "Alıcı Bilgileri",
                    description = "Teslimatı alacak kişi ve iletişim bilgileri"
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
                ) {
                    AddressTextField(
                        label = "Adınız",
                        value = "Murat",
                        modifier = Modifier.weight(1f)
                    )

                    AddressTextField(
                        label = "Soyadınız",
                        value = "Erkan",
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
                ) {
                    AddressTextField(
                        label = "Telefon",
                        value = "5323779918",
                        modifier = Modifier.weight(1f)
                    )

                    AddressTextField(
                        label = "Posta Kodu",
                        value = "34394",
                        modifier = Modifier.weight(1f)
                    )
                }

                AddressFormSectionTitle(
                    title = "Konum Bilgileri",
                    description = "Ülke, şehir ve ilçe bilgilerini seç"
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
                ) {
                    AddressTextField(
                        label = "Ülke",
                        value = "Türkiye",
                        modifier = Modifier.weight(1f)
                    )

                    AddressTextField(
                        label = "Şehir",
                        value = "İstanbul",
                        modifier = Modifier.weight(1f)
                    )
                }

                AddressTextField(
                    label = "İlçe",
                    value = "Şişli",
                    modifier = Modifier.fillMaxWidth()
                )

                AddressFormSectionTitle(
                    title = "Adres Detayı",
                    description = "Açık adresinizi girin"
                )

                AddressTextField(
                    label = "Açık Adresiniz",
                    value = "Fulya mah., Aytekinkotil cad., No: 11/1",
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )

                AddressTextField(
                    label = "Adres Başlığı",
                    value = "Ev Adresim",
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Varsayılan adresim olarak kaydet",
                        style = BbTypography.bodySmall,
                        color = BbColors.TextStrong,
                        modifier = Modifier.weight(1f)
                    )

                    Switch(
                        checked = true,
                        onCheckedChange = {}
                    )
                }

                Spacer(modifier = Modifier.height(BbSpacing.sm))

                BbButton(
                    text = "Adresi Güncelle",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Save,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun AddressFormSectionTitle(
    title: String,
    description: String
) {
    Column {
        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = BbColors.TextStrong
        )

        Text(
            text = description,
            style = BbTypography.bodySmall,
            color = BbColors.TextMuted
        )
    }
}

@Composable
private fun AddressTextField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    minLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = {
            Text(text = label)
        },
        modifier = modifier,
        minLines = minLines,
        singleLine = minLines == 1
    )
}