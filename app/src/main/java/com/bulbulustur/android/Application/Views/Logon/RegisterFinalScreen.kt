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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.MarkEmailUnread
import androidx.compose.material.icons.outlined.PendingActions
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
fun RegisterFinalScreen(
    email: String = "muraterkan500@gmail.com",
    finalState: RegisterFinalState = RegisterFinalState.WaitingEmailVerification,
    onGoToLogonClick: () -> Unit = {},
    onResendVerificationClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        }
    ) {
        RegisterFinalStatusIcon(
            finalState = finalState
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space5))

        LogonPublicPageTitle(
            eyebrow = finalState.badgeText,
            title = finalState.title,
            description = finalState.description
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space6))

        RegisterFinalEmailBox(
            email = email
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space6))

        RegisterFinalStepList(
            finalState = finalState
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space7))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Giriş Ekranına Dön",
            onClick = onGoToLogonClick,
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        if (finalState == RegisterFinalState.WaitingEmailVerification) {
            Spacer(modifier = Modifier.height(BBSpacing.Space3))

            BbButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Doğrulama E-postasını Tekrar Gönder",
                onClick = onResendVerificationClick,
                variant = BbButtonVariant.Outline,
                size = BbButtonSize.Large
            )
        }

        Spacer(modifier = Modifier.height(BBSpacing.Space6))

        RegisterFinalInfoBox()
    }
}

@Composable
private fun RegisterFinalStatusIcon(
    finalState: RegisterFinalState
) {
    Surface(
        color = when (finalState) {
            RegisterFinalState.Completed -> BBColors.Green.Green50
            RegisterFinalState.WaitingEmailVerification -> MaterialTheme.colorScheme.surfaceVariant
            RegisterFinalState.WaitingApproval -> MaterialTheme.colorScheme.primaryContainer
        },
        shape = BBRadius.PillShape
    ) {
        Box(
            modifier = Modifier.size(BBSpacing.Space20),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = when (finalState) {
                    RegisterFinalState.Completed -> Icons.Outlined.CheckCircle
                    RegisterFinalState.WaitingEmailVerification -> Icons.Outlined.MarkEmailUnread
                    RegisterFinalState.WaitingApproval -> Icons.Outlined.PendingActions
                },
                contentDescription = null,
                tint = when (finalState) {
                    RegisterFinalState.Completed -> BBColors.Green.Green700
                    RegisterFinalState.WaitingEmailVerification -> MaterialTheme.colorScheme.onSurface
                    RegisterFinalState.WaitingApproval -> MaterialTheme.colorScheme.onSurface
                },
                modifier = Modifier.size(BBIcon.Size3Xl)
            )
        }
    }
}

@Composable
private fun RegisterFinalEmailBox(
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
                text = "Doğrulama adresi",
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
private fun RegisterFinalStepList(
    finalState: RegisterFinalState
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        RegisterFinalStepItem(
            number = "1",
            title = "Hesap bilgileri alındı",
            description = "Üyelik başlangıç bilgileriniz başarıyla kaydedildi.",
            isCompleted = true
        )

        RegisterFinalStepItem(
            number = "2",
            title = when (finalState) {
                RegisterFinalState.Completed -> "E-posta doğrulandı"
                RegisterFinalState.WaitingEmailVerification -> "E-posta doğrulaması bekleniyor"
                RegisterFinalState.WaitingApproval -> "Firma kontrolü bekleniyor"
            },
            description = when (finalState) {
                RegisterFinalState.Completed -> "Hesabınız giriş için hazır."
                RegisterFinalState.WaitingEmailVerification -> "Size gönderilen bağlantıya tıklayarak hesabınızı doğrulayın."
                RegisterFinalState.WaitingApproval -> "Kurumsal hesap bilgileriniz kontrol edildikten sonra aktifleşir."
            },
            isCompleted = finalState == RegisterFinalState.Completed
        )
    }
}

@Composable
private fun RegisterFinalStepItem(
    number: String,
    title: String,
    description: String,
    isCompleted: Boolean
) {
    val numberBackground = if (isCompleted) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surface
    }

    val numberColor = if (isCompleted) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

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
                color = numberBackground,
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
                        color = numberColor
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
private fun RegisterFinalInfoBox() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = BBRadius.Card
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.CardPaddingCompact)
        ) {
            Text(
                text = "Küçük not",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(BBSpacing.Space1))

            Text(
                text = "E-postayı görmüyorsanız spam veya gereksiz klasörünü kontrol edin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

enum class RegisterFinalState(
    val badgeText: String,
    val title: String,
    val description: String
) {
    Completed(
        badgeText = "Üyelik Tamamlandı",
        title = "Hesabınız Hazır",
        description = "Bulbulustur hesabınız başarıyla oluşturuldu. Artık giriş yapabilirsiniz."
    ),

    WaitingEmailVerification(
        badgeText = "Doğrulama Bekleniyor",
        title = "E-postanızı Kontrol Edin",
        description = "Hesabınızı aktifleştirmek için e-posta adresinize gönderilen doğrulama bağlantısını kullanın."
    ),

    WaitingApproval(
        badgeText = "Kontrol Bekleniyor",
        title = "Kurumsal Hesap İnceleniyor",
        description = "Firma hesabınız kontrol sürecine alındı. OnaylandıĞında giriş yapabilirsiniz."
    )
}

@Preview(showBackground = true)
@Composable
private fun RegisterFinalScreenPreview() {
    BbTheme {
        RegisterFinalScreen()
    }
}

