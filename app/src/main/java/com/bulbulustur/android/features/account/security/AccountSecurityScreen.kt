package com.bulbulustur.android.features.account.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun AccountSecurityScreen(
    onBackClick: () -> Unit = {},
    onChangeEmailClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onLoginActivitiesClick: () -> Unit = {},
    onDeactivateAccountClick: () -> Unit = {},
    onPhoneListClick: () -> Unit = {}
) {
    AccountPageScaffold(
        title = "Güvenlik",
        kicker = "Hesap Koruması",
        description = "E-posta, şifre, telefon doğrulama ve giriş hareketlerinizi buradan yönetebilirsiniz.",
        backButtonText = "Hesabıma Dön",
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            SecuritySummaryCard()

            SecurityActionCard(
                title = "E-posta Değiştir",
                description = "Hesabınızın bağlı olduğu e-posta adresini güncelleyin.",
                shortCode = "@",
                actionText = "Değiştir",
                onClick = onChangeEmailClick
            )

            SecurityActionCard(
                title = "Şifre Değiştir",
                description = "Hesap şifrenizi güvenli şekilde yenileyin.",
                shortCode = "••",
                actionText = "Güncelle",
                onClick = onChangePasswordClick
            )

            SecurityActionCard(
                title = "Telefonlarım",
                description = "Hesabınıza bağlı telefonları ve doğrulama durumlarını yönetin.",
                shortCode = "☎",
                actionText = "Yönet",
                onClick = onPhoneListClick
            )

            SecurityActionCard(
                title = "Giriş Hareketleri",
                description = "Hesabınıza yapılan son girişleri ve cihaz hareketlerini inceleyin.",
                shortCode = "IP",
                actionText = "İncele",
                onClick = onLoginActivitiesClick
            )

            SecurityDangerCard(
                onDeactivateAccountClick = onDeactivateAccountClick
            )
        }
    }
}

@Composable
private fun SecuritySummaryCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Text(
                text = "Hesap Güvenliği",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Güvenlik ayarlarınızı düzenli kontrol etmek hesabınızı daha sağlam tutar. Telefon doğrulama ve güçlü şifre kullanımı önerilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = BbColors.Green.Green50,
                        shape = BbRadius.LgShape
                    )
                    .padding(BbSpacing.CardPaddingCompact)
            ) {
                Text(
                    text = "Hesabınız aktif durumda",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Green.Green700
                )
            }
        }
    }
}

@Composable
private fun SecurityActionCard(
    title: String,
    description: String,
    shortCode: String,
    actionText: String,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SecurityIconBox(
                text = shortCode
            )

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

            BbButton(
                text = actionText,
                onClick = onClick,
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun SecurityDangerCard(
    onDeactivateAccountClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Text(
                text = "Hesabı Devre Dışı Bırak",
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.Red.Red700
            )

            Text(
                text = "Hesabınızı kullanmayı bırakmak istiyorsanız devre dışı bırakma akışını başlatabilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = "Devre Dışı Bırak",
                onClick = onDeactivateAccountClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Danger,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun SecurityIconBox(
    text: String
) {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space14)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.XlShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}