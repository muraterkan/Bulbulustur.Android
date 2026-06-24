package com.bulbulustur.android.Application.Views.Logon

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
import com.bulbulustur.android.Application.Views.Shared.LogonPublicPageTitle
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

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

        Spacer(modifier = Modifier.height(BBSpacing.Space5))

        LogonPublicPageTitle(
            eyebrow = expiredType.badgeText,
            title = expiredType.title,
            description = expiredType.description
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space6))

        ExpiredEmailBox(
            email = email
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space6))

        ExpiredReasonList(
            expiredType = expiredType
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space7))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = expiredType.primaryButtonText,
            onClick = onSendAgainClick,
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space3))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Giriş Ekranına Dön",
            onClick = onGoToLogonClick,
            variant = BbButtonVariant.Outline,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space6))

        ExpiredSecurityInfoBox()
    }
}

@Composable
private fun ExpiredStatusIcon(
    expiredType: ExpiredType
) {
    Surface(
        color = BBColors.Red.Red50,
        shape = BBRadius.PillShape
    ) {
        Box(
            modifier = Modifier.size(BBSpacing.Space20),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = when (expiredType) {
                    ExpiredType.EmailVerification -> Icons.Outlined.MarkEmailUnread
                    ExpiredType.PasswordReset -> Icons.Outlined.KeyOff
                    ExpiredType.RegisterSession -> Icons.Outlined.Schedule
                },
                contentDescription = null,
                tint = BBColors.Red.Red600,
                modifier = Modifier.size(BBIcon.Size3Xl)
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
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = BBRadius.Card
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.CardPadding),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "İşlem yapılan adres",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(BBSpacing.Space1))

            Text(
                text = email,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
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
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        ExpiredReasonItem(
            number = "1",
            title = "BaĞlantı süresi doldu",
            description = expiredType.firstReason
        )

        ExpiredReasonItem(
            number = "2",
            title = "Yeni baĞlantı gerekli",
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
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = BBRadius.Card
    ) {
        Row(
            modifier = Modifier.padding(BBSpacing.CardPaddingCompact),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                color = BBColors.Red.Red50,
                shape = BBRadius.PillShape
            ) {
                Box(
                    modifier = Modifier.size(BBSpacing.Space8),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = number,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = BBColors.Red.Red700
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
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ExpiredSecurityInfoBox() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = BBRadius.Card
    ) {
        Row(
            modifier = Modifier.padding(BBSpacing.CardPaddingCompact),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                color = BBColors.White,
                shape = BBRadius.PillShape
            ) {
                Box(
                    modifier = Modifier.size(BBSpacing.Space8),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeMd)
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
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))

                Text(
                    text = "Süresi dolan baĞlantılar tekrar kullanılamaz. Yeni baĞlantı oluşturmanız gerekir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
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
        badgeText = "DoĞrulama Süresi Doldu",
        title = "E-posta BaĞlantısı Geçersiz",
        description = "E-posta doĞrulama baĞlantınızın süresi dolmuş olabilir.",
        primaryButtonText = "DoĞrulama E-postasını Yeniden Gönder",
        firstReason = "E-posta doĞrulama baĞlantıları güvenlik için sınırlı süre geçerlidir.",
        secondReason = "Yeni doĞrulama e-postası göndererek hesabınızı aktifleştirebilirsiniz."
    ),

    PasswordReset(
        badgeText = "Şifre BaĞlantısı Geçersiz",
        title = "Şifre Yenileme Süresi Doldu",
        description = "Şifre yenileme baĞlantınız artık kullanılamıyor.",
        primaryButtonText = "Yeni Şifre BaĞlantısı Gönder",
        firstReason = "Şifre yenileme baĞlantıları tek kullanımlık ve süre sınırlıdır.",
        secondReason = "Yeni baĞlantı göndererek şifre yenileme işlemini tekrar başlatabilirsiniz."
    ),

    RegisterSession(
        badgeText = "Kayıt Oturumu Kapandı",
        title = "Kayıt BaĞlantısı Geçersiz",
        description = "Kayıt işleminiz yarıda kalmış veya baĞlantı süresi dolmuş olabilir.",
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

