package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Bildirim Ve İzinler",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    PaddingValues(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
        ) {
            CommunicationDescriptionCard()

            CommunicationPreferenceCard(
                emailAllowed = emailAllowedState.value,
                smsAllowed = smsAllowedState.value,
                phoneAllowed = phoneAllowedState.value,
                appNotificationAllowed = appNotificationAllowedState.value,
                onEmailAllowedChange = { value ->
                    emailAllowedState.value = value
                },
                onSmsAllowedChange = { value ->
                    smsAllowedState.value = value
                },
                onPhoneAllowedChange = { value ->
                    phoneAllowedState.value = value
                },
                onAppNotificationAllowedChange = { value ->
                    appNotificationAllowedState.value = value
                }
            )

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
private fun CommunicationDescriptionCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "E-posta, SMS, telefon ve uygulama bildirimleri için tercihlerinizi buradan yönetebilirsiniz. Zorunlu hesap, güvenlik ve sipariş bildirimleri yasal süreçler kapsamında ayrıca gönderilebilir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CommunicationPreferenceCard(
    emailAllowed: Boolean,
    smsAllowed: Boolean,
    phoneAllowed: Boolean,
    appNotificationAllowed: Boolean,
    onEmailAllowedChange: (Boolean) -> Unit,
    onSmsAllowedChange: (Boolean) -> Unit,
    onPhoneAllowedChange: (Boolean) -> Unit,
    onAppNotificationAllowedChange: (Boolean) -> Unit
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
            CommunicationPreferenceRow(
                title = "E-Posta Bildirimleri",
                description = "Sipariş, kampanya ve hesap bilgilendirmeleri e-posta ile gönderilebilir.",
                checked = emailAllowed,
                onCheckedChange = onEmailAllowedChange
            )

            CommunicationDashedDivider()

            CommunicationPreferenceRow(
                title = "SMS Bildirimleri",
                description = "Kısa bilgilendirme ve doğrulama mesajları SMS ile gönderilebilir.",
                checked = smsAllowed,
                onCheckedChange = onSmsAllowedChange
            )

            CommunicationDashedDivider()

            CommunicationPreferenceRow(
                title = "Telefon Araması",
                description = "Gerekli durumlarda hesabınızla ilgili telefonla iletişim kurulabilir.",
                checked = phoneAllowed,
                onCheckedChange = onPhoneAllowedChange
            )

            CommunicationDashedDivider()

            CommunicationPreferenceRow(
                title = "Uygulama Bildirimleri",
                description = "Mobil uygulama üzerinden bildirim almayı yönetebilirsiniz.",
                checked = appNotificationAllowed,
                onCheckedChange = onAppNotificationAllowedChange
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
                checkedThumbColor = BBColors.White,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                checkedBorderColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                uncheckedBorderColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Composable
private fun CommunicationDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.CardPadding,
                end = BBSpacing.CardPadding
            )
            .size(
                width = 1.dp,
                height = 1.dp
            )
    ) {
        drawLine(
            color = dividerColor,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(8f, 8f),
                phase = 0f
            )
        )
    }
}

