package com.bulbulustur.android.features.account.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun AboutThisAppScreen(
    onBackClick: () -> Unit = {},
    onAboutBulbulusturClick: () -> Unit = {},
    onCompanyPageClick: () -> Unit = {},
    onContactUsClick: () -> Unit = {},
    onLegalPoliciesClick: () -> Unit = {}
) {
    AccountPageScaffold(
        title = "Uygulama Hakkında",
        kicker = "Bulbulustur Mobil",
        description = "Platform, sürüm ve şirket bilgilerini görüntüleyin.",
        backButtonText = "Ayarlara Dön",
        onBackClick = onBackClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Large
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    Text(
                        text = "bulbulustur.",
                        style = BbTypography.displaySmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = "Bulbulustur B2B ve B2C ticaret platformudur.",
                        style = BbTypography.bodyMedium,
                        color = BbColors.TextMuted
                    )

                    Text(
                        text = "Sürüm 1.0.0",
                        style = BbTypography.labelMedium,
                        color = BbColors.Primary
                    )
                }
            }

            AboutMenuCard(
                icon = Icons.Outlined.Info,
                title = "Bulbulustur Hakkında",
                description = "Platform hikayesi ve genel bilgiler",
                onClick = onAboutBulbulusturClick
            )

            AboutMenuCard(
                icon = Icons.Outlined.Business,
                title = "Şirket Sayfası",
                description = "Şirket ve kurumsal bilgiler",
                onClick = onCompanyPageClick
            )

            AboutMenuCard(
                icon = Icons.Outlined.MailOutline,
                title = "İletişim",
                description = "Bizimle iletişime geçin",
                onClick = onContactUsClick
            )

            AboutMenuCard(
                icon = Icons.Outlined.Description,
                title = "Yasal Metinler",
                description = "Kullanım şartları ve politikalar",
                onClick = onLegalPoliciesClick
            )
        }
    }
}

@Composable
private fun AboutMenuCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong
            )

            Text(
                text = description,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}