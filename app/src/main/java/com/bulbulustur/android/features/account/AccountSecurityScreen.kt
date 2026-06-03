package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun AccountSecurityScreen(
    onBackClick: () -> Unit = {},
    onProfileInfoClick: () -> Unit = {},
    onEmailChangeClick: () -> Unit = {},
    onPasswordChangeClick: () -> Unit = {},
    onLoginActivitiesClick: () -> Unit = {},
    onCommunicationPreferencesClick: () -> Unit = {}
) {
    AccountPageScaffold(
        title = "Hesap ve Güvenlik",
        subtitle = "Ad, e-posta, şifre ve iletişim tercihlerini yönet",
        onBackClick = onBackClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            AccountSecuritySection(
                title = "Profil Bilgileri",
                subtitle = "Ad soyad ve temel profil bilgilerini yönetin.",
                icon = Icons.Outlined.Person,
                rows = listOf(
                    AccountSecurityRowModel(
                        title = "İsim Soyisim",
                        value = "Murat Erkan",
                        actionText = "Düzenle",
                        icon = Icons.Outlined.Edit,
                        onClick = onProfileInfoClick
                    )
                )
            )

            AccountSecuritySection(
                title = "Güvenlik",
                subtitle = "E-posta, şifre ve oturum güvenliği ayarlarını güncel tutun.",
                icon = Icons.Outlined.Security,
                rows = listOf(
                    AccountSecurityRowModel(
                        title = "E-Posta",
                        value = "muraterkan500@gmail.com",
                        actionText = "Değiştir",
                        icon = Icons.Outlined.Email,
                        onClick = onEmailChangeClick
                    ),
                    AccountSecurityRowModel(
                        title = "Şifre",
                        value = "Şifre hesabınıza giriş yapmak için kullanılır",
                        actionText = "Değiştir",
                        icon = Icons.Outlined.Lock,
                        onClick = onPasswordChangeClick
                    ),
                    AccountSecurityRowModel(
                        title = "Giriş Etkinlikleri",
                        value = "Oturum hareketlerinizi ve hesap erişim geçmişinizi inceleyin.",
                        actionText = "Tüm liste",
                        icon = Icons.Outlined.History,
                        onClick = onLoginActivitiesClick
                    )
                )
            )

            AccountSecuritySection(
                title = "Tercihler",
                subtitle = "İletişim izinleri ve bildirim tercihlerinizi düzenleyin.",
                icon = Icons.Outlined.Tune,
                rows = listOf(
                    AccountSecurityRowModel(
                        title = "İletişim Tercihleri",
                        value = "E-posta, SMS ve platform bildirim tercihlerinizi yönetin.",
                        actionText = "Düzenle",
                        icon = Icons.Outlined.Notifications,
                        onClick = onCommunicationPreferencesClick
                    )
                )
            )
        }
    }
}

@Composable
private fun AccountSecuritySection(
    title: String,
    subtitle: String,
    icon: ImageVector,
    rows: List<AccountSecurityRowModel>
) {
    BbCard(
        padding = BbCardPadding.Medium
    ) {
        Column {
            AccountSecuritySectionHeader(
                title = title,
                subtitle = subtitle,
                icon = icon
            )

            Spacer(modifier = Modifier.height(BbSpacing.md))

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                rows.forEach { row ->
                    AccountSecurityRow(
                        row = row
                    )
                }
            }
        }
    }
}

@Composable
private fun AccountSecuritySectionHeader(
    title: String,
    subtitle: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            shape = CircleShape,
            color = BbColors.PrimarySoft
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Primary,
                modifier = Modifier.padding(BbSpacing.sm)
            )
        }

        Column(
            modifier = Modifier.padding(start = BbSpacing.md)
        ) {
            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong
            )

            Spacer(modifier = Modifier.height(BbSpacing.xs))

            Text(
                text = subtitle,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun AccountSecurityRow(
    row: AccountSecurityRowModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.SurfaceMuted)
            .clickable {
                row.onClick()
            }
            .padding(
                horizontal = BbSpacing.md,
                vertical = BbSpacing.md
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = row.icon,
            contentDescription = null,
            tint = BbColors.TextMuted
        )

        Column(
            modifier = Modifier
                .padding(start = BbSpacing.md)
                .weight(1f)
        ) {
            Text(
                text = row.title,
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )

            Spacer(modifier = Modifier.height(BbSpacing.xs))

            Text(
                text = row.value,
                style = BbTypography.bodyMedium,
                color = BbColors.TextStrong
            )
        }

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = BbColors.Primary
        )
    }
}

private data class AccountSecurityRowModel(
    val title: String,
    val value: String,
    val actionText: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)