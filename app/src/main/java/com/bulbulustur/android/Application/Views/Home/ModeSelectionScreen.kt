package com.bulbulustur.android.Application.Views.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.R

@Composable
fun ModeSelectionScreen(
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit,
    onRfqClick: () -> Unit = onWholesaleClick
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BBColors.Yellow.Yellow50)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.Space6,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.Space4
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ModeSelectionHeader()

        Spacer(modifier = Modifier.weight(1f))

        ModeSelectionContent(
            onRetailClick = onRetailClick,
            onWholesaleClick = onWholesaleClick,
            onRfqClick = onRfqClick
        )

        Spacer(modifier = Modifier.weight(1f))

        ModeSelectionLegalFooter()
    }
}

@Composable
private fun ModeSelectionHeader() {
    var expanded by remember { mutableStateOf(false) }

    var selectedLanguage by remember {
        mutableStateOf(
            ModeSelectionLanguage(
                code = "tr",
                label = "Türkçe"
            )
        )
    }

    val languages = remember {
        listOf(
            ModeSelectionLanguage("tr", "Türkçe"),
            ModeSelectionLanguage("en", "English")
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(BBLayout.LogoHeightLarge),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_black),
                contentDescription = "Bulbulustur",
                modifier = Modifier
                    .width(BBLayout.LogoWidthLarge)
                    .height(BBLayout.LogoHeightLarge),
                contentScale = ContentScale.Fit
            )
        }

        Box {
            Surface(
                modifier = Modifier
                    .defaultMinSize(minHeight = BBIcon.BoxLg)
                    .clickable { expanded = true },
                shape = BBRadius.XlShape,
                color = BBColors.Yellow.Yellow500,
                border = BorderStroke(
                    width = BBSpacing.Divider,
                    color = BBColors.Yellow.Yellow400
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.Space4,
                        vertical = BBSpacing.Space2
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Language,
                        contentDescription = null,
                        tint = BBColors.Ink.Ink900,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )

                    Text(
                        text = selectedLanguage.label,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = BBColors.Ink.Ink900
                    )

                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = BBColors.Ink.Ink900,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = language.label,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            selectedLanguage = language
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = BBColors.TextStrong
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ModeSelectionContent(
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit,
    onRfqClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Alışveriş Modunu Seç",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = BBColors.Ink.Ink900,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Perakende veya toptan dünyasına hızlıca giriş yap.",
            modifier = Modifier.padding(top = BBSpacing.Space2),
            style = MaterialTheme.typography.bodyMedium,
            color = BBColors.Gray.Gray700,
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BBSpacing.Space8),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            ModeSelectionCommerceCard(
                title = "Perakende Alışveriş",
                description = "Ürünleri keşfet, favorilerine ekle ve güvenle sepetine taşı.",
                icon = Icons.Outlined.LocalMall,
                containerColor = BBColors.Ink.Ink900,
                iconContainerColor = BBColors.Yellow.Yellow500,
                iconColor = BBColors.Ink.Ink900,
                titleColor = BBColors.White,
                descriptionColor = BBColors.Gray.Gray300,
                arrowColor = BBColors.Yellow.Yellow500,
                borderColor = BBColors.Ink.Ink100,
                onClick = onRetailClick
            )

            ModeSelectionCommerceCard(
                title = "Toptan Ticaret",
                description = "Tedarikçileri, toplu ürünleri ve teklif süreçlerini keşfet.",
                icon = Icons.Outlined.Business,
                containerColor = BBColors.Ink.Ink900,
                iconContainerColor = BBColors.Yellow.Yellow500,
                iconColor = BBColors.Ink.Ink900,
                titleColor = BBColors.White,
                descriptionColor = BBColors.Gray.Gray300,
                arrowColor = BBColors.Yellow.Yellow500,
                borderColor = BBColors.Ink.Ink100,
                onClick = onWholesaleClick
            )

            ModeSelectionCommerceCard(
                title = "RFQ Talebi Gönder",
                description = "Toptan alım için tedarikçilerden son fiyat iste.",
                icon = Icons.Outlined.RequestQuote,
                containerColor = BBColors.Yellow.Yellow50,
                iconContainerColor = BBColors.Yellow.Yellow500,
                iconColor = BBColors.Ink.Ink900,
                titleColor = BBColors.Ink.Ink900,
                descriptionColor = BBColors.Gray.Gray700,
                arrowColor = BBColors.Yellow.Yellow800,
                borderColor = BBColors.Yellow.Yellow300,
                onClick = onRfqClick
            )
        }
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
            .defaultMinSize(minHeight = BBSpacing.Space24)
            .clickable { onClick() },
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
                .padding(BBSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Surface(
                shape = BBRadius.XlShape,
                color = iconContainerColor
            ) {
                Box(
                    modifier = Modifier.size(BBIcon.BoxXl),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.SizeXl),
                        tint = iconColor
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = titleColor,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = descriptionColor
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(BBIcon.Size2Xl),
                tint = arrowColor
            )
        }
    }
}

@Composable
private fun ModeSelectionLegalFooter() {
    val uriHandler = LocalUriHandler.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = {
                uriHandler.openUri(
                    "https://www.bulbulustur.com/support/condition/1/kosullar"
                )
            }
        ) {
            Text(
                text = "Kullanım Koşulları",
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.TextSubtle,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = "·",
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.TextMuted
        )

        TextButton(
            onClick = {
                uriHandler.openUri(
                    "https://www.bulbulustur.com/support/condition/2/politikalar"
                )
            }
        ) {
            Text(
                text = "Gizlilik Politikası",
                style = MaterialTheme.typography.labelSmall,
                color = BBColors.TextSubtle,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

private data class ModeSelectionLanguage(
    val code: String,
    val label: String
)