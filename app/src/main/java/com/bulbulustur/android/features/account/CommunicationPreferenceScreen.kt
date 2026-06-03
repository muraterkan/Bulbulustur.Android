package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
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
fun CommunicationPreferenceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        BbSectionHeader(
            title = "İletişim Tercihleri",
            subtitle = "Bulbulustur’un sizinle hangi kanallar üzerinden iletişime geçebileceğini yönetin"
        )

        BbCard(
            padding = BbCardPadding.Medium
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
            ) {
                CommunicationPreferenceHeader()

                CommunicationPreferenceRow(
                    title = "E-posta",
                    description = "Kampanya, hesap, sipariş ve platform duyuruları için e-posta iletişimini yönetir.",
                    checked = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = null,
                            tint = BbColors.Primary
                        )
                    }
                )

                CommunicationPreferenceRow(
                    title = "SMS",
                    description = "Kısa bilgilendirme, güvenlik ve işlem mesajları için SMS iletişim tercihinizi belirler.",
                    checked = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Sms,
                            contentDescription = null,
                            tint = BbColors.Primary
                        )
                    }
                )

                CommunicationPreferenceRow(
                    title = "Telefon",
                    description = "Gerekli durumlarda telefon üzerinden iletişim kurulup kurulamayacağını belirler.",
                    checked = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = null,
                            tint = BbColors.Primary
                        )
                    }
                )

                CommunicationPreferenceInfoBox()

                BbButton(
                    text = "Güncelle",
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
private fun CommunicationPreferenceHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Tune,
            contentDescription = null,
            tint = BbColors.Primary
        )

        Column(
            modifier = Modifier.padding(start = BbSpacing.md)
        ) {
            Text(
                text = "Bildirim kanalları",
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            Text(
                text = "Bulbulustur’un sizinle hangi kanallar üzerinden iletişime geçebileceğini buradan yönetebilirsiniz.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun CommunicationPreferenceRow(
    title: String,
    description: String,
    checked: Boolean,
    icon: @Composable () -> Unit
) {
    BbCard(
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()

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
                    text = description,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Switch(
                checked = checked,
                onCheckedChange = {}
            )
        }
    }
}

@Composable
private fun CommunicationPreferenceInfoBox() {
    BbCard(
        padding = BbCardPadding.Medium
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.Security,
                contentDescription = null,
                tint = BbColors.Warning
            )

            Text(
                text = "Bu tercihler hesabınıza ait bilgilendirme kanallarını yönetir. Zorunlu güvenlik ve işlem bildirimleri ayrıca gönderilebilir.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted,
                modifier = Modifier.padding(start = BbSpacing.md)
            )
        }
    }
}