package com.bulbulustur.android.features.logon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun ExpiredScreen(
    expiredType: ExpiredType = ExpiredType.EmailVerification,
    email: String = "muraterkan500@gmail.com",
    onSendAgainClick: () -> Unit = {},
    onGoToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = BbSpacing.PageHorizontalWide),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(BbSpacing.PageTop))

            ExpiredTopBar(
                onLanguageClick = onLanguageClick
            )

            Spacer(modifier = Modifier.size(BbSpacing.Space10))

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Elevated,
                padding = BbCardPadding.Large
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ExpiredIcon(
                        expiredType = expiredType
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    ExpiredBadge(
                        text = expiredType.badgeText
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Text(
                        text = expiredType.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    Text(
                        text = expiredType.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BbRadius.Card
                    ) {
                        Column(
                            modifier = Modifier.padding(BbSpacing.CardPadding),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "İşlem yapılan adres",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.size(BbSpacing.Space1))

                            Text(
                                text = email,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    ExpiredReasonList(
                        expiredType = expiredType
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    BbButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = expiredType.primaryButtonText,
                        onClick = onSendAgainClick,
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Large
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space3))

                    BbButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Giriş Ekranına Dön",
                        onClick = onGoToLogonClick,
                        variant = BbButtonVariant.Outline,
                        size = BbButtonSize.Large
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BbRadius.Card
                    ) {
                        Row(
                            modifier = Modifier.padding(BbSpacing.CardPaddingCompact),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(BbSpacing.IconTextGap)
                        ) {
                            Text(
                                text = "🛡️",
                                style = MaterialTheme.typography.titleSmall
                            )

                            Column {
                                Text(
                                    text = "Güvenlik nedeniyle",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )

                                Spacer(modifier = Modifier.size(BbSpacing.Space1))

                                Text(
                                    text = "Süresi dolan bağlantılar tekrar kullanılamaz. Yeni bağlantı oluşturmanız gerekir.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(BbSpacing.Space12))

            Text(
                text = "© 2026 Bulbulustur - Tüm hakları saklıdır",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.size(BbSpacing.PageBottomCompact))
        }
    }
}

@Composable
private fun ExpiredTopBar(
    onLanguageClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                append("bulbulustur")

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append(".")
                }
            },
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onLanguageClick,
            shape = BbRadius.Button
        ) {
            Text(
                text = "🌐",
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.size(BbSpacing.IconTextGap))

            Text(
                text = "Türkçe",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
private fun ExpiredIcon(
    expiredType: ExpiredType
) {
    Surface(
        color = MaterialTheme.colorScheme.errorContainer,
        shape = BbRadius.PillShape
    ) {
        Box(
            modifier = Modifier.size(BbSpacing.Space20),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = expiredType.iconText,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Composable
private fun ExpiredBadge(
    text: String
) {
    Surface(
        color = MaterialTheme.colorScheme.errorContainer,
        shape = BbRadius.Badge
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            ),
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onErrorContainer,
            fontWeight = FontWeight.SemiBold
        )
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
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = BbRadius.Card
    ) {
        Row(
            modifier = Modifier.padding(BbSpacing.CardPaddingCompact),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.errorContainer,
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
                        color = MaterialTheme.colorScheme.onErrorContainer
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

                Spacer(modifier = Modifier.size(BbSpacing.Space1))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

enum class ExpiredType(
    val badgeText: String,
    val iconText: String,
    val title: String,
    val description: String,
    val primaryButtonText: String,
    val firstReason: String,
    val secondReason: String
) {
    EmailVerification(
        badgeText = "Doğrulama Süresi Doldu",
        iconText = "⏳",
        title = "E-posta Bağlantısı Geçersiz",
        description = "E-posta doğrulama bağlantınızın süresi dolmuş olabilir.",
        primaryButtonText = "Doğrulama E-postasını Yeniden Gönder",
        firstReason = "E-posta doğrulama bağlantıları güvenlik için sınırlı süre geçerlidir.",
        secondReason = "Yeni doğrulama e-postası göndererek hesabınızı aktifleştirebilirsiniz."
    ),

    PasswordReset(
        badgeText = "Şifre Bağlantısı Geçersiz",
        iconText = "🔑",
        title = "Şifre Yenileme Süresi Doldu",
        description = "Şifre yenileme bağlantınız artık kullanılamıyor.",
        primaryButtonText = "Yeni Şifre Bağlantısı Gönder",
        firstReason = "Şifre yenileme bağlantıları tek kullanımlık ve süre sınırlıdır.",
        secondReason = "Yeni bağlantı göndererek şifre yenileme işlemini tekrar başlatabilirsiniz."
    ),

    RegisterSession(
        badgeText = "Kayıt Oturumu Kapandı",
        iconText = "🚪",
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