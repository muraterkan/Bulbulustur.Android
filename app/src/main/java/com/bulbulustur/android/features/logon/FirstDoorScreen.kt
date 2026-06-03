package com.bulbulustur.android.features.logon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun FirstDoorScreen(
    onContinueClick: (selectedDoor: FirstDoorType) -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var selectedDoor by remember { mutableStateOf(FirstDoorType.IndividualBuyer) }

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

            FirstDoorTopBar(
                onLanguageClick = onLanguageClick
            )

            Spacer(modifier = Modifier.size(BbSpacing.Space8))

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Elevated,
                padding = BbCardPadding.Large
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FirstDoorBadge(
                        text = "Üyelik Kapısı"
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space5))

                    Text(
                        text = "Nasıl Devam Edelim?",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space2))

                    Text(
                        text = "Bulbulustur hesabınızı doğru akışla oluşturalım. Seçiminize göre sonraki adımı hazırlayacağız.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                    ) {
                        FirstDoorOptionCard(
                            iconText = "🛒",
                            title = "Bireysel Alıcıyım",
                            description = "Perakende alışveriş yapmak ve siparişlerinizi takip etmek için devam edin.",
                            isSelected = selectedDoor == FirstDoorType.IndividualBuyer,
                            onClick = {
                                selectedDoor = FirstDoorType.IndividualBuyer
                            }
                        )

                        FirstDoorOptionCard(
                            iconText = "🏢",
                            title = "Firma Adına Alıcıyım",
                            description = "Toptan talepler, teklif toplama ve firma alışverişleri için devam edin.",
                            isSelected = selectedDoor == FirstDoorType.CompanyBuyer,
                            onClick = {
                                selectedDoor = FirstDoorType.CompanyBuyer
                            }
                        )

                        FirstDoorOptionCard(
                            iconText = "🔑",
                            title = "Zaten Hesabım Var",
                            description = "Yeni kayıt oluşturmak yerine mevcut hesabınızla giriş yapın.",
                            isSelected = selectedDoor == FirstDoorType.ExistingAccount,
                            onClick = {
                                selectedDoor = FirstDoorType.ExistingAccount
                            }
                        )
                    }

                    Spacer(modifier = Modifier.size(BbSpacing.Space6))

                    BbButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Devam Et",
                        onClick = {
                            onContinueClick(selectedDoor)
                        },
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Large
                    )

                    Spacer(modifier = Modifier.size(BbSpacing.Space4))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Giriş ekranına dönmek ister misiniz?",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        TextButton(
                            onClick = onBackToLogonClick
                        ) {
                            Text(
                                text = "Giriş Yap",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
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
private fun FirstDoorTopBar(
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
private fun FirstDoorBadge(
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
private fun FirstDoorOptionCard(
    iconText: String,
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }

    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val titleColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val descriptionColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = borderColor,
                shape = BbRadius.Card
            )
            .clickable {
                onClick()
            },
        color = backgroundColor,
        shape = BbRadius.Card
    ) {
        Row(
            modifier = Modifier.padding(BbSpacing.CardPadding),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = BbRadius.Badge
            ) {
                Box(
                    modifier = Modifier
                        .size(BbSpacing.Space10),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = iconText,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = titleColor
                )

                Spacer(modifier = Modifier.size(BbSpacing.Space1))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = descriptionColor
                )
            }
        }
    }
}

enum class FirstDoorType {
    IndividualBuyer,
    CompanyBuyer,
    ExistingAccount
}

@Preview(showBackground = true)
@Composable
private fun FirstDoorScreenPreview() {
    BbTheme {
        FirstDoorScreen()
    }
}