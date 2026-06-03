package com.bulbulustur.android.features.account.security

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbSpacing

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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Hesap Güvenliği",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Şifre, e-posta, giriş hareketleri ve hesap durumu işlemlerini buradan yönetebilirsin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            AccountSecurityBottomMenuRow(
                title = "Giriş Etkinlikleri",
                description = "Son giriş hareketlerini ve cihaz bilgilerini görüntüle.",
                selected = selectedItem == AccountSecurityMenuItem.LoginActivities,
                onClick = onLoginActivitiesClick
            )

            AccountSecurityBottomMenuRow(
                title = "Şifre Değiştir",
                description = "Hesabın için yeni ve güçlü bir şifre belirle.",
                selected = selectedItem == AccountSecurityMenuItem.ChangePassword,
                onClick = onChangePasswordClick
            )

            AccountSecurityBottomMenuRow(
                title = "E-posta Değiştir",
                description = "Hesabına bağlı e-posta adresini güncelle.",
                selected = selectedItem == AccountSecurityMenuItem.ChangeEmail,
                onClick = onChangeEmailClick
            )

            AccountSecurityBottomMenuRow(
                title = "Hesabı Devre Dışı Bırak",
                description = "Hesabını geçici olarak erişime kapat.",
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
        variant = if (selected) BbCardVariant.Elevated else BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}