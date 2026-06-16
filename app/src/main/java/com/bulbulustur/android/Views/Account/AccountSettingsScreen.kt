package com.bulbulustur.android.Views.Account

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
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
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTypography

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
    onSignOutClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Ayarlar",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                SettingsProtectionBlock(
                    onAccountSecurityClick = onAccountSecurityClick,
                    onPrivacyClick = onPrivacyClick,
                    onPermissionsClick = onPermissionsClick,
                    onHelpCenterClick = onHelpCenterClick
                )
            }

            item {
                SettingsSectionTitle(
                    title = "Tercihler",
                    subtitle = "Dil, gÃ¶rÃ¼nÃ¼m ve bÃ¶lgesel tercihlerini yÃ¶net."
                )
            }

            item {
                SettingsMenuGroup {
                    SettingsMenuRow(
                        title = "Dil",
                        value = "TÃ¼rkÃ§e",
                        icon = Icons.Outlined.Language,
                        onClick = onLanguageClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "GÃ¶rÃ¼nÃ¼m",
                        value = "AÃ§Ä±k tema",
                        icon = Icons.Outlined.Palette,
                        onClick = onAppearanceClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "Ãœlke ve BÃ¶lge",
                        value = "TÃ¼rkiye",
                        icon = Icons.Outlined.Public,
                        onClick = onRegionClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "Para Birimi",
                        value = "TRY",
                        icon = Icons.Outlined.Payments,
                        onClick = onCurrencyClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "Bildirim ve Ä°zinler",
                        value = null,
                        icon = Icons.Outlined.Notifications,
                        onClick = onPermissionsClick
                    )
                }
            }

            item {
                SettingsSectionTitle(
                    title = "Platform",
                    subtitle = "Uygulama bilgileri ve yasal metinler."
                )
            }

            item {
                SettingsMenuGroup {
                    SettingsMenuRow(
                        title = "Uygulama HakkÄ±nda",
                        value = null,
                        icon = Icons.Outlined.Info,
                        onClick = onAboutThisAppClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "Yasal Metinler ve Politikalar",
                        value = null,
                        icon = Icons.Outlined.Description,
                        onClick = onLegalPoliciesClick
                    )
                }
            }

            item {
                SettingsSectionTitle(
                    title = "Oturum",
                    subtitle = "HesabÄ±ndan gÃ¼venli ÅŸekilde Ã§Ä±kÄ±ÅŸ yap."
                )
            }

            item {
                SettingsMenuGroup {
                    SettingsMenuRow(
                        title = "Ã‡Ä±kÄ±ÅŸ Yap",
                        value = null,
                        icon = Icons.Outlined.Logout,
                        danger = true,
                        onClick = onSignOutClick
                    )
                }
            }
        }
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
                text = "HesabÄ±n GÃ¼vende",
                style = BbTypography.headlineSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Hesap, gizlilik ve izin ayarlarÄ±nÄ± tek merkezden yÃ¶net.",
                style = BbTypography.bodyMedium,
                color = BbColors.TextMuted
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                SettingsQuickCard(
                    modifier = Modifier.weight(1f),
                    title = "Hesap GÃ¼venliÄŸi",
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                SettingsQuickCard(
                    modifier = Modifier.weight(1f),
                    title = "Ä°zinler",
                    icon = Icons.Outlined.Lock,
                    onClick = onPermissionsClick
                )

                SettingsQuickCard(
                    modifier = Modifier.weight(1f),
                    title = "YardÄ±m Merkezi",
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
        modifier = modifier
            .height(124.dp)
            .clickable {
                onClick()
            },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = BbColors.Success.copy(alpha = 0.10f),
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BbColors.Success,
                    modifier = Modifier.size(BbIcon.Ui)
                )
            }

            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Composable
private fun SettingsSectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = BbTypography.titleMedium,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = subtitle,
            style = BbTypography.bodySmall,
            color = BbColors.TextMuted
        )
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
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = if (danger) {
                        BbColors.Red.Red50
                    } else {
                        BbColors.SurfaceMuted
                    },
                    shape = BbRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (danger) {
                    BbColors.Red.Red600
                } else {
                    BbColors.TextStrong
                },
                modifier = Modifier.size(BbIcon.Ui)
            )
        }

        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = if (danger) {
                BbColors.Red.Red700
            } else {
                BbColors.TextStrong
            },
            fontWeight = FontWeight.SemiBold,
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

@Composable
private fun SettingsDashedDivider() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.Space16,
                end = BbSpacing.Space4
            )
            .height(BbSpacing.BorderThin)
    ) {
        drawLine(
            color = BbColors.Border,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(10f, 8f),
                phase = 0f
            )
        )
    }
}
