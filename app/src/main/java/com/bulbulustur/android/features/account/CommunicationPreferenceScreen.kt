package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun CommunicationPreferenceScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (
        emailAllowed: Boolean,
        smsAllowed: Boolean,
        phoneAllowed: Boolean,
        appNotificationAllowed: Boolean
    ) -> Unit = { _, _, _, _ -> }
) {
    val emailAllowedState = remember {
        mutableStateOf(true)
    }

    val smsAllowedState = remember {
        mutableStateOf(false)
    }

    val phoneAllowedState = remember {
        mutableStateOf(false)
    }

    val appNotificationAllowedState = remember {
        mutableStateOf(true)
    }

    AccountPageScaffold(
        title = "İletişim Tercihlerim",
        kicker = "Bildirim ve İzinler",
        description = "E-posta, SMS, telefon ve uygulama bildirimleri için iletişim tercihlerinizi buradan yönetebilirsiniz.",
        backButtonText = "Hesabıma Dön",
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            CommunicationInfoBox()

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
                ) {
                    CommunicationPreferenceRow(
                        title = "E-posta Bildirimleri",
                        description = "Sipariş, kampanya ve hesap bilgilendirmeleri e-posta ile gönderilebilir.",
                        checked = emailAllowedState.value,
                        onCheckedChange = { value ->
                            emailAllowedState.value = value
                        }
                    )

                    CommunicationPreferenceRow(
                        title = "SMS Bildirimleri",
                        description = "Kısa bilgilendirme ve doğrulama mesajları SMS ile gönderilebilir.",
                        checked = smsAllowedState.value,
                        onCheckedChange = { value ->
                            smsAllowedState.value = value
                        }
                    )

                    CommunicationPreferenceRow(
                        title = "Telefon Araması",
                        description = "Gerekli durumlarda hesabınızla ilgili telefonla iletişim kurulabilir.",
                        checked = phoneAllowedState.value,
                        onCheckedChange = { value ->
                            phoneAllowedState.value = value
                        }
                    )

                    CommunicationPreferenceRow(
                        title = "Uygulama Bildirimleri",
                        description = "Mobil uygulama üzerinden bildirim almayı yönetebilirsiniz.",
                        checked = appNotificationAllowedState.value,
                        onCheckedChange = { value ->
                            appNotificationAllowedState.value = value
                        }
                    )
                }
            }

            BbButton(
                text = "Tercihleri Kaydet",
                onClick = {
                    onSaveClick(
                        emailAllowedState.value,
                        smsAllowedState.value,
                        phoneAllowedState.value,
                        appNotificationAllowedState.value
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun CommunicationPreferenceRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = { value ->
                onCheckedChange(value)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                uncheckedTrackColor = MaterialTheme.colorScheme.outlineVariant
            )
        )
    }
}

@Composable
private fun CommunicationInfoBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.Blue.Blue50,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "İletişim İzinleri",
                style = MaterialTheme.typography.labelLarge,
                color = BbColors.Blue.Blue700
            )

            Text(
                text = "Zorunlu hesap, güvenlik ve sipariş bildirimleri yasal süreçler kapsamında ayrıca gönderilebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}