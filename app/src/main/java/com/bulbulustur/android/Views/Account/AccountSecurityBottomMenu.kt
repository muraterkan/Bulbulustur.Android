package com.bulbulustur.android.Views.Account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.theme.BbSpacing

enum class AccountSecurityMenuItem {
    LoginActivities,
    ChangePassword,
    ChangeEmail,
    DeactivateAccount
}

@Composable
fun AccountSecurityBottomMenu(
    selectedItem: AccountSecurityMenuItem,
    onLoginActivitiesClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onChangeEmailClick: () -> Unit,
    onDeactivateAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            Text(
                text = "Hesap GÃ¼venliÄŸi",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Åifre, e-posta, giriÅŸ hareketleri ve hesap durumu iÅŸlemlerini buradan yÃ¶netebilirsin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            AccountSecurityBottomMenuRow(
                title = "GiriÅŸ Etkinlikleri",
                description = "Son giriÅŸ hareketlerini ve cihaz bilgilerini gÃ¶rÃ¼ntÃ¼le.",
                selected = selectedItem == AccountSecurityMenuItem.LoginActivities,
                onClick = onLoginActivitiesClick
            )

            AccountSecurityBottomMenuRow(
                title = "Åifre DeÄŸiÅŸtir",
                description = "HesabÄ±n iÃ§in yeni ve gÃ¼Ã§lÃ¼ bir ÅŸifre belirle.",
                selected = selectedItem == AccountSecurityMenuItem.ChangePassword,
                onClick = onChangePasswordClick
            )

            AccountSecurityBottomMenuRow(
                title = "E-Posta DeÄŸiÅŸtir",
                description = "HesabÄ±na baÄŸlÄ± e-posta adresini gÃ¼ncelle.",
                selected = selectedItem == AccountSecurityMenuItem.ChangeEmail,
                onClick = onChangeEmailClick
            )

            AccountSecurityBottomMenuRow(
                title = "HesabÄ± Devre DÄ±ÅŸÄ± BÄ±rak",
                description = "HesabÄ±nÄ± geÃ§ici olarak eriÅŸime kapat.",
                selected = selectedItem == AccountSecurityMenuItem.DeactivateAccount,
                onClick = onDeactivateAccountClick
            )
        }
    }
}

@Composable
private fun AccountSecurityBottomMenuRow(
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        variant = if (selected) {
            BbCardVariant.Elevated
        } else {
            BbCardVariant.Outlined
        },
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
