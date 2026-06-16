package com.bulbulustur.android.Views.logon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyOff
import androidx.compose.material.icons.outlined.MarkEmailUnread
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

@Composable
fun ExpiredScreen(
    expiredType: ExpiredType = ExpiredType.EmailVerification,
    email: String = "muraterkan500@gmail.com",
    onSendAgainClick: () -> Unit = {},
    onGoToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        }
    ) {
        ExpiredStatusIcon(
            expiredType = expiredType
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space5))

        LogonPublicPageTitle(
            eyebrow = expiredType.badgeText,
            title = expiredType.title,
            description = expiredType.description
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space6))

        ExpiredEmailBox(
            email = email
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space6))

        ExpiredReasonList(
            expiredType = expiredType
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space7))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = expiredType.primaryButtonText,
            onClick = onSendAgainClick,
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space3))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Giriş Ekranına Dön",
            onClick = onGoToLogonClick,
            variant = BbButtonVariant.Outline,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space6))

        ExpiredSecurityInfoBox()
    }
}

@Composable
private fun ExpiredStatusIcon(
    expiredType: ExpiredType
) {
    Surface(
        color = BbColors.Red.Red50,
        shape = BbRadius.PillShape
    ) {
        Box(
            modifier = Modifier.size(BbSpacing.Space20),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = when (expiredType) {
                    ExpiredType.EmailVerification -> Icons.Outlined.MarkEmailUnread
                    ExpiredType.PasswordReset -> Icons.Outlined.KeyOff
                    ExpiredType.RegisterSession -> Icons.Outlined.Schedule
                },
                contentDescription = null,
                tint = BbColors.Red.Red600,
                modifier = Modifier.size(BbIcon.Size3Xl)
            )
        }
    }
}

@Composable
private fun ExpiredEmailBox(
    email: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = BbColors.SurfaceMuted,
        shape = BbRadius.Card
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.CardPadding),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "İşlem yapılan adres",
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.TextMuted
            )

            Spacer(modifier = Modifier.height(BbSpacing.Space1))

            Text(
                text = email,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )
        }
    }
}

@Composable
private fun ExpiredReasonList(
    expiredType: ExpiredType
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        ExpiredReasonItem(
            number = "1",
            title = "Bağlantı süresi doldu",
            description = expiredType.firstReason
        )

        ExpiredReasonItem(
            number = "2",
            title = "Yeni bağlantı gerekli",
            description = expiredType.secondReason
        )
    }
}

@Composable
private fun ExpiredReasonItem(
    number: String,
    title: String,
    description: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = BbColors.SurfaceMuted,
        shape = BbRadius.Card
    ) {
        Row(
            modifier = Modifier.padding(BbSpacing.CardPaddingCompact),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                color = BbColors.Red.Red50,
                shape = BbRadius.PillShape
            ) {
                Box(
                    modifier = Modifier.size(BbSpacing.Space8),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = number,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.Red.Red700
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Spacer(modifier = Modifier.height(BbSpacing.Space1))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextSubtle
                )
            }
        }
    }
}

@Composable
private fun ExpiredSecurityInfoBox() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = BbColors.PrimarySoft,
        shape = BbRadius.Card
    ) {
        Row(
            modifier = Modifier.padding(BbSpacing.CardPaddingCompact),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                color = BbColors.White,
                shape = BbRadius.PillShape
            ) {
                Box(
                    modifier = Modifier.size(BbSpacing.Space8),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Güvenlik nedeniyle",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Spacer(modifier = Modifier.height(BbSpacing.Space1))

                Text(
                    text = "Süresi dolan bağlantılar tekrar kullanılamaz. Yeni bağlantı oluşturmanız gerekir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextSubtle
                )
            }
        }
    }
}

enum class ExpiredType(
    val badgeText: String,
    val title: String,
    val description: String,
    val primaryButtonText: String,
    val firstReason: String,
    val secondReason: String
) {
    EmailVerification(
        badgeText = "Doğrulama Süresi Doldu",
        title = "E-posta Bağlantısı Geçersiz",
        description = "E-posta doğrulama bağlantınızın süresi dolmuş olabilir.",
        primaryButtonText = "Doğrulama E-postasını Yeniden Gönder",
        firstReason = "E-posta doğrulama bağlantıları güvenlik için sınırlı süre geçerlidir.",
        secondReason = "Yeni doğrulama e-postası göndererek hesabınızı aktifleştirebilirsiniz."
    ),

    PasswordReset(
        badgeText = "Şifre Bağlantısı Geçersiz",
        title = "Şifre Yenileme Süresi Doldu",
        description = "Şifre yenileme bağlantınız artık kullanılamıyor.",
        primaryButtonText = "Yeni Şifre Bağlantısı Gönder",
        firstReason = "Şifre yenileme bağlantıları tek kullanımlık ve süre sınırlıdır.",
        secondReason = "Yeni bağlantı göndererek şifre yenileme işlemini tekrar başlatabilirsiniz."
    ),

    RegisterSession(
        badgeText = "Kayıt Oturumu Kapandı",
        title = "Kayıt Bağlantısı Geçersiz",
        description = "Kayıt işleminiz yarıda kalmış veya bağlantı süresi dolmuş olabilir.",
        primaryButtonText = "Kayıt İşlemini Yeniden Başlat",
        firstReason = "Yarım kalan kayıt oturumları güvenlik için otomatik kapatılır.",
        secondReason = "Yeni kayıt akışı başlatarak hesabınızı güvenli şekilde oluşturabilirsiniz."
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpiredScreenPreview() {
    BbTheme {
        ExpiredScreen()
    }
}