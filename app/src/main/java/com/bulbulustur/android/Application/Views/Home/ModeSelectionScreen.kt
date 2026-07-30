package com.bulbulustur.android.Application.Views.Home

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.view.WindowCompat
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO

@Composable
fun ModeSelectionScreen(
    languages: List<SystemDescLanguageDTO> = emptyList(),
    selectedLanguageId: Int = 1,
    isLanguageLoading: Boolean = false,
    languageErrorMessage: String? = null,
    onLanguageSelected: (Int) -> Unit = {},
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit,
    onRfqClick: () -> Unit
) {
    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f

    val systemBarColor =
        MaterialTheme.colorScheme.background

    ModeSelectionSystemBars(
        backgroundColor = systemBarColor,
        useLightIcons = isDark
    )

    LogonPublicScaffold(
        languages = languages,
        selectedLanguageId = selectedLanguageId,
        isLanguageLoading = isLanguageLoading,
        languageErrorMessage = languageErrorMessage,
        horizontalPadding = BBSpacing.PageHorizontal,
        headerTopSpace = BBSpacing.Space5,
        headerBottomSpace = BBSpacing.Space12,
        onLanguageIdSelected = onLanguageSelected
    ) {
        ModeSelectionContent(
            isDark = isDark,
            onRetailClick = onRetailClick,
            onWholesaleClick = onWholesaleClick,
            onRfqClick = onRfqClick
        )
    }
}

@Composable
private fun ModeSelectionContent(
    isDark: Boolean,
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit,
    onRfqClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ModeSelectionEyebrow(
            isDark = isDark
        )

        Spacer(
            modifier = Modifier.height(
                BBSpacing.Space4
            )
        )

        Text(
            text = "Alışveriş Modunu Seç",
            style = BbTypography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = if (isDark) {
                BBColors.White
            } else {
                BBColors.Ink.Ink900
            },
            textAlign = TextAlign.Center
        )

        Text(
            text = "Perakende alışverişe veya toptan ticaret akışına hızlıca giriş yap.",
            modifier = Modifier.padding(
                top = BBSpacing.Space2
            ),
            style = BbTypography.bodyMedium,
            color = if (isDark) {
                BBColors.Gray.Gray300
            } else {
                BBColors.Gray.Gray700
            },
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = BBSpacing.Space7
                ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            ModeSelectionCommerceCard(
                title = "Perakende Alışveriş",
                description = "Ürünleri keşfet, favorilerine ekle ve güvenle sepetine taşı.",
                icon = Icons.Outlined.LocalMall,
                containerColor = if (isDark) {
                    BBColors.Ink.Ink800
                } else {
                    BBColors.Ink.Ink900
                },
                iconContainerColor =
                    BBColors.Yellow.Yellow100,
                iconColor =
                    BBColors.Ink.Ink900,
                titleColor =
                    BBColors.White,
                descriptionColor =
                    BBColors.Gray.Gray300,
                arrowColor =
                    BBColors.Yellow.Yellow300,
                borderColor = if (isDark) {
                    BBColors.Ink.Ink100
                } else {
                    BBColors.Ink.Ink200
                },
                onClick = onRetailClick
            )

            ModeSelectionCommerceCard(
                title = "Toptan Ticaret",
                description = "Tedarikçileri, toplu ürünleri ve teklif süreçlerini keşfet.",
                icon = Icons.Outlined.Business,
                containerColor = if (isDark) {
                    BBColors.Ink.Ink800
                } else {
                    BBColors.Ink.Ink900
                },
                iconContainerColor =
                    BBColors.Yellow.Yellow100,
                iconColor =
                    BBColors.Ink.Ink900,
                titleColor =
                    BBColors.White,
                descriptionColor =
                    BBColors.Gray.Gray300,
                arrowColor =
                    BBColors.Yellow.Yellow300,
                borderColor = if (isDark) {
                    BBColors.Ink.Ink100
                } else {
                    BBColors.Ink.Ink200
                },
                onClick = onWholesaleClick
            )

            ModeSelectionCommerceCard(
                title = BBLocalization.Current.Get(key = "203882aa-6872-41de-a0db-26b13a6389e3", fallback = ""),
                description = "Toptan alım ihtiyacını belirt, tedarikçilerden teklif al.",
                icon = Icons.Outlined.RequestQuote,
                containerColor = if (isDark) {
                    BBColors.Ink.Ink900
                } else {
                    MaterialTheme.colorScheme.surface
                },
                iconContainerColor = if (isDark) {
                    BBColors.Ink.Ink800
                } else {
                    BBColors.Yellow.Yellow100
                },
                iconColor = if (isDark) {
                    BBColors.Yellow.Yellow300
                } else {
                    BBColors.Ink.Ink900
                },
                titleColor = if (isDark) {
                    BBColors.White
                } else {
                    BBColors.Ink.Ink900
                },
                descriptionColor = if (isDark) {
                    BBColors.Gray.Gray400
                } else {
                    BBColors.Gray.Gray700
                },
                arrowColor =
                    BBColors.Yellow.Yellow300,
                borderColor = if (isDark) {
                    BBColors.Ink.Ink200
                } else {
                    BBColors.Yellow.Yellow300
                },
                onClick = onRfqClick
            )
        }
    }
}

@Composable
private fun ModeSelectionEyebrow(
    isDark: Boolean
) {
    Surface(
        shape = BBRadius.PillShape,
        color = if (isDark) {
            BBColors.Ink.Ink800
        } else {
            BBColors.Yellow.Yellow100
        },
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = if (isDark) {
                BBColors.Ink.Ink200
            } else {
                BBColors.Yellow.Yellow300
            }
        )
    ) {
        Text(
            text = "Bulbulustur Alıcı Uygulaması",
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space4,
                vertical = BBSpacing.Space2
            ),
            style = BbTypography.labelMedium,
            color = if (isDark) {
                BBColors.Yellow.Yellow300
            } else {
                BBColors.Yellow.Yellow800
            },
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ModeSelectionCommerceCard(
    title: String,
    description: String,
    icon: ImageVector,
    containerColor: Color,
    iconContainerColor: Color,
    iconColor: Color,
    titleColor: Color,
    descriptionColor: Color,
    arrowColor: Color,
    borderColor: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(
                minHeight = BBSpacing.Space20
            )
            .clickable {
                onClick()
            },
        shape = BBRadius.XxlShape,
        color = containerColor,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.Space4
                ),
            verticalAlignment =
                Alignment.CenterVertically,
            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space4
                )
        ) {
            Surface(
                shape = BBRadius.XlShape,
                color = iconContainerColor
            ) {
                Box(
                    modifier = Modifier.size(
                        BBIcon.BoxXl
                    ),
                    contentAlignment =
                        Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(
                            BBIcon.SizeXl
                        ),
                        tint = iconColor
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
            ) {
                Text(
                    text = title,
                    style =
                        BbTypography.titleMedium,
                    color = titleColor,
                    fontWeight =
                        FontWeight.ExtraBold
                )

                Text(
                    text = description,
                    style = BbTypography.bodySmall,
                    color = descriptionColor
                )
            }

            Icon(
                imageVector =
                    Icons.AutoMirrored.Outlined
                        .ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(
                    BBIcon.Size2Xl
                ),
                tint = arrowColor
            )
        }
    }
}

@Composable
private fun ModeSelectionSystemBars(
    backgroundColor: Color,
    useLightIcons: Boolean
) {
    val view = LocalView.current

    DisposableEffect(
        backgroundColor,
        useLightIcons
    ) {
        val activity =
            view.context as? Activity

        if (activity == null) {
            onDispose {}
        } else {
            val window =
                activity.window

            val previousStatusBarColor =
                window.statusBarColor

            val previousNavigationBarColor =
                window.navigationBarColor

            val controller =
                WindowCompat.getInsetsController(
                    window,
                    view
                )

            val previousLightStatusBars =
                controller.isAppearanceLightStatusBars

            val previousLightNavigationBars =
                controller
                    .isAppearanceLightNavigationBars

            window.statusBarColor =
                backgroundColor.toArgb()

            window.navigationBarColor =
                backgroundColor.toArgb()

            controller.isAppearanceLightStatusBars =
                !useLightIcons

            controller.isAppearanceLightNavigationBars =
                !useLightIcons

            onDispose {
                window.statusBarColor =
                    previousStatusBarColor

                window.navigationBarColor =
                    previousNavigationBarColor

                controller.isAppearanceLightStatusBars =
                    previousLightStatusBars

                controller
                    .isAppearanceLightNavigationBars =
                    previousLightNavigationBars
            }
        }
    }
}