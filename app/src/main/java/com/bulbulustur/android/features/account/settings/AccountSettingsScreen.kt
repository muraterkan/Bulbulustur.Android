package com.bulbulustur.android.features.account.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.SwitchAccount
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun AccountSettingsScreen(
    onBackClick: () -> Unit = {},
    onAccountSecurityClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onPermissionsClick: () -> Unit = {},
    onHelpCenterClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onRegionClick: () -> Unit = {},
    onCurrencyClick: () -> Unit = {},
    onAboutThisAppClick: () -> Unit = {},
    onLegalPoliciesClick: () -> Unit = {},
    onShareThisAppClick: () -> Unit = {},
    onSwitchAccountClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.SurfaceMuted)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentPadding = PaddingValues(
            horizontal = BbSpacing.PageHorizontal,
            vertical = BbSpacing.PageTopCompact
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        item {
            SettingsHeader(
                onBackClick = onBackClick
            )
        }

        item {
            SettingsProtectionBlock(
                onAccountSecurityClick = onAccountSecurityClick,
                onPrivacyClick = onPrivacyClick,
                onPermissionsClick = onPermissionsClick,
                onHelpCenterClick = onHelpCenterClick
            )
        }

        item {
            SettingsMenuGroup {
                SettingsMenuRow(
                    title = "Dil",
                    value = "Türkçe",
                    icon = Icons.Outlined.Language,
                    onClick = onLanguageClick
                )

                SettingsMenuRow(
                    title = "Görünüm",
                    value = "Açık tema",
                    icon = Icons.Outlined.Palette,
                    onClick = onAppearanceClick
                )

                SettingsMenuRow(
                    title = "Ülke ve Bölge",
                    value = "Türkiye",
                    icon = Icons.Outlined.Public,
                    onClick = onRegionClick
                )

                SettingsMenuRow(
                    title = "Para Birimi",
                    value = "TRY",
                    icon = Icons.Outlined.Payments,
                    onClick = onCurrencyClick
                )

                SettingsMenuRow(
                    title = "Bildirim ve İzinler",
                    value = null,
                    icon = Icons.Outlined.Notifications,
                    onClick = onPermissionsClick
                )
            }
        }

        item {
            SettingsMenuGroup {
                SettingsMenuRow(
                    title = "Uygulama Hakkında",
                    value = null,
                    icon = Icons.Outlined.Info,
                    onClick = onAboutThisAppClick
                )

                SettingsMenuRow(
                    title = "Yasal Metinler ve Politikalar",
                    value = null,
                    icon = Icons.Outlined.Description,
                    onClick = onLegalPoliciesClick
                )

                SettingsMenuRow(
                    title = "Uygulamayı Paylaş",
                    value = null,
                    icon = Icons.Outlined.Share,
                    onClick = onShareThisAppClick
                )
            }
        }

        item {
            SettingsMenuGroup {
                SettingsMenuRow(
                    title = "Hesap Değiştir",
                    value = null,
                    icon = Icons.Outlined.SwitchAccount,
                    onClick = onSwitchAccountClick
                )

                SettingsMenuRow(
                    title = "Çıkış Yap",
                    value = null,
                    icon = Icons.Outlined.Logout,
                    danger = true,
                    onClick = onSignOutClick
                )
            }
        }
    }
}

@Composable
private fun SettingsHeader(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = BbColors.Surface,
                    shape = BbRadius.LgShape
                )
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.Ui)
            )
        }

        Text(
            text = "Ayarlar",
            style = BbTypography.titleLarge,
            color = BbColors.TextStrong,
            modifier = Modifier
                .weight(1f)
                .padding(end = BbIcon.BoxMd),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SettingsProtectionBlock(
    onAccountSecurityClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onPermissionsClick: () -> Unit,
    onHelpCenterClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "Hesabın güvende",
                style = BbTypography.headlineSmall,
                color = BbColors.Green.Green700
            )

            Text(
                text = "Hesap, gizlilik ve izin ayarlarını tek merkezden yönet.",
                style = BbTypography.bodyMedium,
                color = BbColors.TextMuted
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                SettingsQuickCard(
                    modifier = Modifier.weight(1f),
                    title = "Hesap Güvenliği",
                    icon = Icons.Outlined.Security,
                    onClick = onAccountSecurityClick
                )

                SettingsQuickCard(
                    modifier = Modifier.weight(1f),
                    title = "Gizlilik",
                    icon = Icons.Outlined.Visibility,
                    onClick = onPrivacyClick
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                SettingsQuickCard(
                    modifier = Modifier.weight(1f),
                    title = "İzinler",
                    icon = Icons.Outlined.Lock,
                    onClick = onPermissionsClick
                )

                SettingsQuickCard(
                    modifier = Modifier.weight(1f),
                    title = "Yardım Merkezi",
                    icon = Icons.Outlined.SupportAgent,
                    onClick = onHelpCenterClick
                )
            }
        }
    }
}

@Composable
private fun SettingsQuickCard(
    modifier: Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    BbCard(
        modifier = modifier.clickable {
            onClick()
        },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxMd)
                    .background(
                        color = BbColors.Green.Green50,
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BbColors.Green.Green700,
                    modifier = Modifier.size(BbIcon.Ui)
                )
            }

            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.size(BbIcon.Ui)
            )
        }
    }
}

@Composable
private fun SettingsMenuGroup(
    content: @Composable ColumnScope.() -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BbColors.Surface)
        ) {
            content()
        }
    }
}

@Composable
private fun SettingsMenuRow(
    title: String,
    value: String?,
    icon: ImageVector,
    danger: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(BbSpacing.CardPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (danger) {
                BbColors.Red.Red600
            } else {
                BbColors.Yellow.Yellow800
            },
            modifier = Modifier.size(BbIcon.Ui)
        )

        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = if (danger) {
                BbColors.Red.Red700
            } else {
                BbColors.TextStrong
            },
            modifier = Modifier.weight(1f)
        )

        if (!value.isNullOrBlank()) {
            Text(
                text = value,
                style = BbTypography.bodyMedium,
                color = BbColors.TextMuted
            )
        }

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = BbColors.TextMuted,
            modifier = Modifier.size(BbIcon.Ui)
        )
    }
}