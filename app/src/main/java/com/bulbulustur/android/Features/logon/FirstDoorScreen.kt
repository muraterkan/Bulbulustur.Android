package com.bulbulustur.android.Features.logon

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTheme

@Composable
fun FirstDoorScreen(
    onContinueClick: (selectedDoor: FirstDoorType) -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var selectedDoor by remember {
        mutableStateOf(FirstDoorType.IndividualBuyer)
    }

    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        }
    ) {
        LogonPublicPageTitle(
            eyebrow = "Üyelik Kapısı",
            title = "Nasıl Devam Edelim?",
            description = "Bulbulustur hesabınızı doğru akışla oluşturalım. Seçiminize göre sonraki adımı hazırlayacağız."
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space8))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            FirstDoorOptionCard(
                marker = "B",
                title = "Bireysel Alıcıyım",
                description = "Perakende alışveriş yapmak ve siparişlerinizi takip etmek için devam edin.",
                isSelected = selectedDoor == FirstDoorType.IndividualBuyer,
                onClick = {
                    selectedDoor = FirstDoorType.IndividualBuyer
                }
            )

            FirstDoorOptionCard(
                marker = "F",
                title = "Firma Adına Alıcıyım",
                description = "Toptan talepler, teklif toplama ve firma alışverişleri için devam edin.",
                isSelected = selectedDoor == FirstDoorType.CompanyBuyer,
                onClick = {
                    selectedDoor = FirstDoorType.CompanyBuyer
                }
            )

            FirstDoorOptionCard(
                marker = "G",
                title = "Zaten Hesabım Var",
                description = "Yeni kayıt oluşturmak yerine mevcut hesabınızla giriş yapın.",
                isSelected = selectedDoor == FirstDoorType.ExistingAccount,
                onClick = {
                    selectedDoor = FirstDoorType.ExistingAccount
                }
            )
        }

        Spacer(modifier = Modifier.height(BbSpacing.Space7))

        BbButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Devam Et",
            onClick = {
                onContinueClick(selectedDoor)
            },
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Large
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space5))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Giriş ekranına dönmek ister misiniz?",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextMuted
            )

            TextButton(
                onClick = onBackToLogonClick
            ) {
                Text(
                    text = "Giriş Yap",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )
            }
        }
    }
}

@Composable
private fun FirstDoorOptionCard(
    marker: String,
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        BbColors.Border
    }

    val backgroundColor = if (isSelected) {
        BbColors.PrimarySoft
    } else {
        BbColors.SurfaceMuted
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
                color = if (isSelected) {
                    BbColors.Surface
                } else {
                    BbColors.White
                },
                shape = BbRadius.PillShape
            ) {
                Box(
                    modifier = Modifier.size(BbSpacing.Space10),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = marker,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Black,
                        color = BbColors.TextStrong
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