package com.bulbulustur.android.Application.Views.Logon

import com.bulbulustur.android.Application.Localization.BBLocalization

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
            text = BBLocalization.Current.Get(key = "6777bc00-123b-4116-9093-08c80fb9d405", fallback = "Giriş Ekranına Dön"),
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
                text = BBLocalization.Current.Get(key = "67037435-b650-46e6-a6b1-71ce65dbf9ac", fallback = "İşlem yapılan adres"),
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
            title = BBLocalization.Current.Get(key = "6d47f6ba-98f2-42d2-a5a1-1d36d5eff1bc", fallback = "Bağlantı süresi doldu"),
            description = expiredType.firstReason
        )

        ExpiredReasonItem(
            number = "2",
            title = BBLocalization.Current.Get(key = "25ab6b18-3c65-4a01-95e7-77cb810eb699", fallback = "Yeni bağlantı gerekli"),
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
                    text = BBLocalization.Current.Get(key = "5d7f21b2-95cd-4e9c-8ab7-5ef4707e3f6b", fallback = "Güvenlik nedeniyle"),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))

                Text(
                    text = BBLocalization.Current.Get(key = "1c735ded-2619-49a7-805c-ca93387c20c1", fallback = "Süresi dolan bağlantılar tekrar kullanılamaz. Yeni bağlantı oluşturmanız gerekir."),
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
        badgeText = BBLocalization.Current.Get(key = "09cddbc9-e031-4af2-87b6-1c196a87eaea", fallback = "Doğrulama Süresi Doldu"),
        title = BBLocalization.Current.Get(key = "7ce3e6da-8535-4467-af0f-de421cc64555", fallback = "E-posta Bağlantısı Geçersiz"),
        description = BBLocalization.Current.Get(key = "3f97af29-5fc1-4de3-8ca2-225abf829f56", fallback = "E-posta doğrulama bağlantınızın süresi dolmuş olabilir."),
        primaryButtonText = BBLocalization.Current.Get(key = "82ddb53c-927e-4ee2-9f36-2f8e5fb5ebb7", fallback = "Doğrulama E-postasını Yeniden Gönder"),
        firstReason = BBLocalization.Current.Get(key = "b44d9f40-135b-4e55-a0ab-51e2e1981fd5", fallback = "E-posta doğrulama bağlantıları güvenlik için sınırlı süre geçerlidir."),
        secondReason = BBLocalization.Current.Get(key = "9b4ba9d0-4f28-429d-a24a-1700030845bc", fallback = "Yeni doğrulama e-postası göndererek hesabınızı aktifleştirebilirsiniz.")
    ),

    PasswordReset(
        badgeText = BBLocalization.Current.Get(key = "3098b83f-1d41-4663-89c8-6297174df1dd", fallback = "Şifre Bağlantısı Geçersiz"),
        title = BBLocalization.Current.Get(key = "05f52967-5844-4682-8cc2-b0a135a6d052", fallback = "Şifre Yenileme Süresi Doldu"),
        description = BBLocalization.Current.Get(key = "d3d9d5e6-7752-41ab-be64-5336ceeb6a77", fallback = "Şifre yenileme bağlantınız artık kullanılamıyor."),
        primaryButtonText = BBLocalization.Current.Get(key = "1e1c1bf4-726c-4878-be9d-68cde19c5642", fallback = "Yeni Şifre Bağlantısı Gönder"),
        firstReason = BBLocalization.Current.Get(key = "93198ba0-8532-4ffa-be45-5fbde1ad0455", fallback = "Şifre yenileme bağlantıları tek kullanımlık ve süre sınırlıdır."),
        secondReason = BBLocalization.Current.Get(key = "8a1d9c3f-5c2a-49f8-bad7-94328c39413f", fallback = "Yeni bağlantı göndererek şifre yenileme işlemini tekrar başlatabilirsiniz.")
    ),

    RegisterSession(
        badgeText = BBLocalization.Current.Get(key = "b938efe8-5997-44f0-9cda-2f713c5d1372", fallback = "Kayıt Oturumu Kapandı"),
        title = BBLocalization.Current.Get(key = "439d6e23-c6ff-442a-b999-b190a3e8dabd", fallback = "Kayıt Bağlantısı Geçersiz"),
        description = BBLocalization.Current.Get(key = "9e413861-6f4a-4e17-a3c1-3824bf311eb0", fallback = "Kayıt işleminiz yarıda kalmış veya bağlantı süresi dolmuş olabilir."),
        primaryButtonText = BBLocalization.Current.Get(key = "559f7334-8594-478c-a734-3d1ca647dd7e", fallback = "Kayıt İşlemini Yeniden Başlat"),
        firstReason = BBLocalization.Current.Get(key = "6c7cad27-e9cd-4da9-a40e-8d978ccf0661", fallback = "Yarım kalan kayıt oturumları güvenlik için otomatik kapatılır."),
        secondReason = BBLocalization.Current.Get(key = "ab232814-d654-4de7-b210-ea526b4e229b", fallback = "Yeni kayıt akışı başlatarak hesabınızı güvenli şekilde oluşturabilirsiniz.")
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpiredScreenPreview() {
    BbTheme {
        ExpiredScreen()
    }
}

