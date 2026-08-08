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
            text = BBLocalization.Current.Get(key = "6777bc00-123b-4116-9093-08c80fb9d405", fallback = "Giriş Ekranına Dön"),
            onClick = onGoToLogonClick,
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        if (finalState == RegisterFinalState.WaitingEmailVerification) {
            Spacer(modifier = Modifier.height(BBSpacing.Space3))

            BbButton(
                modifier = Modifier.fillMaxWidth(),
                text = BBLocalization.Current.Get(key = "82ddb53c-927e-4ee2-9f36-2f8e5fb5ebb7", fallback = "Doğrulama E-postasını Tekrar Gönder"),
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
                text = BBLocalization.Current.Get(key = "85ebb70e-40ae-42ab-a43b-db9fc1b6ba78", fallback = "Doğrulama adresi"),
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
            title = BBLocalization.Current.Get(key = "e6f43d8c-aeec-4dfd-9140-f4d5a50fc413", fallback = "Hesap bilgileri alındı"),
            description = BBLocalization.Current.Get(key = "5763f532-b0b8-46de-b9cf-43f4a1b2b438", fallback = "Üyelik başlangıç bilgileriniz başarıyla kaydedildi."),
            isCompleted = true
        )

        RegisterFinalStepItem(
            number = "2",
            title = when (finalState) {
                RegisterFinalState.Completed -> BBLocalization.Current.Get(key = "c02eda05-5650-4aae-aac4-f7e7dc87d696", fallback = "E-posta doğrulandı")
                RegisterFinalState.WaitingEmailVerification -> BBLocalization.Current.Get(key = "42e70163-f34e-407a-b0f7-0f227f176efa", fallback = "E-posta doğrulaması bekleniyor")
                RegisterFinalState.WaitingApproval -> BBLocalization.Current.Get(key = "c25bd5dd-9430-4e3f-9da5-8b4defa28760", fallback = "Firma kontrolü bekleniyor")
            },
            description = when (finalState) {
                RegisterFinalState.Completed -> BBLocalization.Current.Get(key = "7d49c907-0cc4-43a7-8550-d1f574b70c80", fallback = "Hesabınız giriş için hazır.")
                RegisterFinalState.WaitingEmailVerification -> BBLocalization.Current.Get(key = "08a108c0-b73a-4d98-bec9-6225964bac11", fallback = "Size gönderilen bağlantıya tıklayarak hesabınızı doğrulayın.")
                RegisterFinalState.WaitingApproval -> BBLocalization.Current.Get(key = "f95784ca-5c6f-4fb4-8383-ca088dba3778", fallback = "Kurumsal hesap bilgileriniz kontrol edildikten sonra aktifleşir.")
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
                text = BBLocalization.Current.Get(key = "7e065f34-1b8c-4b78-a29b-7eff0de8dde3", fallback = "Küçük not"),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(BBSpacing.Space1))

            Text(
                text = BBLocalization.Current.Get(key = "7adbe37b-b969-48a2-9896-f0a5da9f6698", fallback = "E-postayı görmüyorsanız spam veya gereksiz klasörünü kontrol edin."),
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
        badgeText = BBLocalization.Current.Get(key = "60ae9048-3404-4ea6-a789-f75e02e0b4ea", fallback = "Üyelik Tamamlandı"),
        title = BBLocalization.Current.Get(key = "f969d0dc-eb33-45fb-b9b7-059fae97a5b4", fallback = "Hesabınız Hazır"),
        description = BBLocalization.Current.Get(key = "6fc27b1f-c786-4fe8-aca1-dec1dd833167", fallback = "Bulbulustur hesabınız başarıyla oluşturuldu. Artık giriş yapabilirsiniz.")
    ),

    WaitingEmailVerification(
        badgeText = BBLocalization.Current.Get(key = "87939038-b2b6-42b6-8168-1b162c1a4b8b", fallback = "Doğrulama Bekleniyor"),
        title = BBLocalization.Current.Get(key = "ebea7a31-bc45-4257-9ff7-e6ae5cec63bf", fallback = "E-postanızı Kontrol Edin"),
        description = BBLocalization.Current.Get(key = "c343e7cd-ba99-4000-8371-46a0ed4bddba", fallback = "Hesabınızı aktifleştirmek için e-posta adresinize gönderilen doğrulama bağlantısını kullanın.")
    ),

    WaitingApproval(
        badgeText = BBLocalization.Current.Get(key = "69b0d82d-d772-4a8d-97df-e21e332a05f0", fallback = "Kontrol Bekleniyor"),
        title = BBLocalization.Current.Get(key = "765654e1-4e7b-4ad2-9599-d4b0d616dbce", fallback = "Kurumsal Hesap İnceleniyor"),
        description = BBLocalization.Current.Get(key = "85615cba-65b6-41ba-8d00-2990f5a9f487", fallback = "Firma hesabınız kontrol sürecine alındı. Onaylandığında giriş yapabilirsiniz.")
    )
}

@Preview(showBackground = true)
@Composable
private fun RegisterFinalScreenPreview() {
    BbTheme {
        RegisterFinalScreen()
    }
}

