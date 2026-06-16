package com.bulbulustur.android.Views.account.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun AccountSecurityScreen(
    onBackClick: () -> Unit = {},
    onChangeEmailClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onLoginActivitiesClick: () -> Unit = {},
    onDeactivateAccountClick: () -> Unit = {},
    onPhoneListClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Güvenlik",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                SecuritySummaryCard()
            }

            item {
                SecurityActionCard(
                    title = "E-Posta Değiştir",
                    description = "Hesabınızın bağlı olduğu e-posta adresini güncelleyin.",
                    shortCode = "@",
                    actionText = "Değiştir",
                    onClick = onChangeEmailClick
                )
            }

            item {
                SecurityActionCard(
                    title = "Şifre Değiştir",
                    description = "Hesap şifrenizi güvenli şekilde yenileyin.",
                    shortCode = "••",
                    actionText = "Güncelle",
                    onClick = onChangePasswordClick
                )
            }

            item {
                SecurityActionCard(
                    title = "Telefonlarım",
                    description = "Hesabınıza bağlı telefonları ve doğrulama durumlarını yönetin.",
                    shortCode = "☎",
                    actionText = "Yönet",
                    onClick = onPhoneListClick
                )
            }

            item {
                SecurityActionCard(
                    title = "Giriş Hareketleri",
                    description = "Hesabınıza yapılan son girişleri ve cihaz hareketlerini inceleyin.",
                    shortCode = "IP",
                    actionText = "İncele",
                    onClick = onLoginActivitiesClick
                )
            }

            item {
                SecurityDangerCard(
                    onDeactivateAccountClick = onDeactivateAccountClick
                )
            }
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
                    text = "Hesabınız Aktif Durumda",
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
                    color = MaterialTheme.colorScheme.onSurface,
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