package com.bulbulustur.android.Application.Views.Shared.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

enum class AccountSecurityMenuItem {
    LoginActivities,
    ChangePasswordAsync,
    ChangeEmail,
    DeactivateAccount
}

@Composable
fun AccountSecurityBottomMenu(
    selectedItem: AccountSecurityMenuItem,
    onLoginActivitiesClick: () -> Unit,
    onChangePasswordAsyncClick: () -> Unit,
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "2547b5db-8c29-48cb-9d91-d9376f88c45c", fallback = ""),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "5425a6de-02b8-4375-bf6f-0f4ac05c9f67", fallback = "Şifre, e-posta, giriş hareketleri ve hesap durumu işlemlerini buradan yönetebilirsin."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            AccountSecurityBottomMenuRow(
                title = BBLocalization.Current.Get(key = "7c9dea88-25ba-4bbe-97bd-de9d27c119d2", fallback = "Giriş Etkinlikleri"),
                description = BBLocalization.Current.Get(key = "f9ea608b-b0a6-4be3-ac7e-41143f01f42b", fallback = "Son giriş hareketlerini ve cihaz bilgilerini görüntüle."),
                selected = selectedItem == AccountSecurityMenuItem.LoginActivities,
                onClick = onLoginActivitiesClick
            )

            AccountSecurityBottomMenuRow(
                title = BBLocalization.Current.Get(key = "cee2df6f-2392-41ab-92f5-ab1a30499bc7", fallback = "Şifre Değiştir"),
                description = BBLocalization.Current.Get(key = "60379a27-205a-490f-9baa-161628e3ee6c", fallback = "Hesabın için yeni ve güçlü bir şifre belirle."),
                selected = selectedItem == AccountSecurityMenuItem.ChangePasswordAsync,
                onClick = onChangePasswordAsyncClick
            )

            AccountSecurityBottomMenuRow(
                title = BBLocalization.Current.Get(key = "56b7b2b0-d2da-4d53-a361-88b4890ebb6c", fallback = "E-Posta Değiştir"),
                description = BBLocalization.Current.Get(key = "b211dcc0-ad58-42e8-9e3d-6cbe372b81fd", fallback = "Hesabına bağlı e-posta adresini güncelle."),
                selected = selectedItem == AccountSecurityMenuItem.ChangeEmail,
                onClick = onChangeEmailClick
            )

            AccountSecurityBottomMenuRow(
                title = BBLocalization.Current.Get(key = "64052773-9d1d-48ee-b933-7c387c42147d", fallback = "Hesabı Devre Dışı Bırak"),
                description = BBLocalization.Current.Get(key = "fbc34488-a02d-4668-abd8-2d35af43fd35", fallback = "Hesabını geçici olarak erişime kapat."),
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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


