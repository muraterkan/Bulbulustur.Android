package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun AccountSettingsScreen(
    languageName: String,
    themeName: String,
    countryName: String,
    currencyCode: String,
    onBackClick: () -> Unit = {},
    onAccountSecurityClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onPermissionsClick: () -> Unit = {},
    onHelpCenterClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onRegionClick: () -> Unit = {},
    onCurrencyClick: () -> Unit = {},
    onCommunicationPreferenceClick: () -> Unit = {},
    onAboutThisAppClick: () -> Unit = {},
    onLegalPoliciesClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGap
            )
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
                    subtitle = "Dil, görünüm ve bölgesel tercihlerini yönet."
                )
            }

            item {
                SettingsMenuGroup {
                    SettingsMenuRow(
                        title = "Dil",
                        value = languageName,
                        icon = Icons.Outlined.Language,
                        onClick = onLanguageClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "Görünüm",
                        value = themeName,
                        icon = Icons.Outlined.Palette,
                        onClick = onAppearanceClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "Ülke ve Bölge",
                        value = countryName,
                        icon = Icons.Outlined.Public,
                        onClick = onRegionClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = BBLocalization.Current.Get(key = "47942374-ab80-47b3-af0f-c8a6aaf728e3", fallback = ""),
                        value = currencyCode,
                        icon = Icons.Outlined.Payments,
                        onClick = onCurrencyClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "Bildirim ve İzinler",
                        value = null,
                        icon = Icons.Outlined.Notifications,
                        onClick = onCommunicationPreferenceClick
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
                        title = "Uygulama Hakkında",
                        value = null,
                        icon = Icons.Outlined.Info,
                        onClick = onAboutThisAppClick
                    )

                    SettingsDashedDivider()

                    SettingsMenuRow(
                        title = "Yasal Metinler ve Politikalar",
                        value = null,
                        icon = Icons.Outlined.RequestQuote,
                        onClick = onLegalPoliciesClick
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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space4
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Text(
                text = "Hesabın Güvende",
                style = BbTypography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Hesap, gizlilik ve izin ayarlarını tek merkezden yönet.",
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                SettingsQuickCard(
                    modifier = Modifier.weight(1f),
                    title = BBLocalization.Current.Get(key = "2547b5db-8c29-48cb-9d91-d9376f88c45c", fallback = ""),
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
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
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
        modifier = modifier.height(
            BBIcon.Box5Xl
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = BBColors.Success.copy(
                            alpha = 0.10f
                        ),
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BBColors.Success,
                    modifier = Modifier.size(
                        BBIcon.Ui
                    )
                )
            }

            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
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
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Text(
            text = title,
            style = BbTypography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = subtitle,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                .background(
                    MaterialTheme.colorScheme.surface
                )
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
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.CardPadding
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(
                        BBIcon.Ui
                    )
                )
            }

            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            if (!value.isNullOrBlank()) {
                Text(
                    text = value,
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(
                    BBIcon.Ui
                )
            )
        }
    }
}

@Composable
private fun SettingsDashedDivider() {
    val dividerColor =
        MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.Space16,
                end = BBSpacing.Space4
            )
            .height(
                BBSpacing.BorderThin
            )
    ) {
        drawLine(
            color = dividerColor,
            start = Offset(
                0f,
                0f
            ),
            end = Offset(
                size.width,
                0f
            ),
            strokeWidth = BBSpacing.BorderThin.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(
                    10f,
                    8f
                ),
                phase = 0f
            )
        )
    }

}
