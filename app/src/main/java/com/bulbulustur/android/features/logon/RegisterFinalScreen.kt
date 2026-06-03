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
fun RegisterFinalScreen(
    email: String = "muraterkan500@gmail.com",
    finalState: RegisterFinalState = RegisterFinalState.WaitingEmailVerification,
    onGoToLogonClick: () -> Unit = {},
    onResendVerificationClick: () -> Unit = {},
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

            RegisterFinalTopBar(
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
                    RegisterFinalIcon(
                        finalState = finalState
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    RegisterFinalBadge(
                        text = finalState.badgeText
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Text(
                        text = finalState.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    Text(
                        text = finalState.description,
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
                                text = "Doğrulama adresi",
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

                    RegisterFinalStepList(
                        finalState = finalState
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    BbButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Giriş Ekranına Dön",
                        onClick = onGoToLogonClick,
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Large
                    )

                    if (finalState == RegisterFinalState.WaitingEmailVerification) {
                        Spacer(modifier = Modifier.size(BbSpacing.Space3))

                        BbButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Doğrulama E-postasını Tekrar Gönder",
                            onClick = onResendVerificationClick,
                            variant = BbButtonVariant.Outline,
                            size = BbButtonSize.Large
                        )
                    }

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
                                text = "💡",
                                style = MaterialTheme.typography.titleSmall
                            )

                            Column {
                                Text(
                                    text = "Küçük not",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )

                                Spacer(modifier = Modifier.size(BbSpacing.Space1))

                                Text(
                                    text = "E-postayı görmüyorsanız spam veya gereksiz klasörünü kontrol edin.",
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
private fun RegisterFinalTopBar(
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
private fun RegisterFinalIcon(
    finalState: RegisterFinalState
) {
    Surface(
        color = when (finalState) {
            RegisterFinalState.Completed -> MaterialTheme.colorScheme.primaryContainer
            RegisterFinalState.WaitingEmailVerification -> MaterialTheme.colorScheme.surfaceVariant
            RegisterFinalState.WaitingApproval -> MaterialTheme.colorScheme.secondaryContainer
        },
        shape = BbRadius.PillShape
    ) {
        Box(
            modifier = Modifier
                .size(BbSpacing.Space20),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = finalState.iconText,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Composable
private fun RegisterFinalBadge(
    text: String
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = BbRadius.Badge
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            ),
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun RegisterFinalStepList(
    finalState: RegisterFinalState
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
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
        MaterialTheme.colorScheme.surfaceVariant
    }

    val numberColor = if (isCompleted) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

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
                color = numberBackground,
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

enum class RegisterFinalState(
    val badgeText: String,
    val iconText: String,
    val title: String,
    val description: String
) {
    Completed(
        badgeText = "Üyelik Tamamlandı",
        iconText = "✅",
        title = "Hesabınız Hazır",
        description = "Bulbulustur hesabınız başarıyla oluşturuldu. Artık giriş yapabilirsiniz."
    ),

    WaitingEmailVerification(
        badgeText = "Doğrulama Bekleniyor",
        iconText = "✉️",
        title = "E-postanızı Kontrol Edin",
        description = "Hesabınızı aktifleştirmek için e-posta adresinize gönderilen doğrulama bağlantısını kullanın."
    ),

    WaitingApproval(
        badgeText = "Kontrol Bekleniyor",
        iconText = "🏢",
        title = "Kurumsal Hesap İnceleniyor",
        description = "Firma hesabınız kontrol sürecine alındı. Onaylandığında giriş yapabilirsiniz."
    )
}

@Preview(showBackground = true)
@Composable
private fun RegisterFinalScreenPreview() {
    BbTheme {
        RegisterFinalScreen()
    }
}