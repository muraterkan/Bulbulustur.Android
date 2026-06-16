package com.bulbulustur.android.Views.Splash

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
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalMall
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbLayout
@Composable
fun ModeSelectionScreen(
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit,
    onRfqClick: () -> Unit = onWholesaleClick
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.Primary)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(
                start = BbSpacing.Space7,
                end = BbSpacing.Space7,
                top = BbSpacing.Space8,
                bottom = BbSpacing.Space5
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ModeSelectionHeader()

        Spacer(modifier = Modifier.weight(0.62f))

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
    var expanded by remember {
        mutableStateOf(false)
    }

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
            ModeSelectionLanguage(
                code = "tr",
                label = "Türkçe"
            ),
            ModeSelectionLanguage(
                code = "en",
                label = "English"
            )
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_black),
            contentDescription = "Bulbulustur",
            modifier = Modifier
                .width(BbLayout.LogoWidthMedium)
                .height(42.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(1f))

        Box {
            Surface(
                modifier = Modifier
                    .defaultMinSize(minHeight = 42.dp)
                    .clickable {
                        expanded = true
                    },
                shape = BbRadius.Button,
                color = BbColors.White.copy(alpha = 0.72f),
                border = BorderStroke(
                    width = 1.dp,
                    color = BbColors.White.copy(alpha = 0.90f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = BbSpacing.Space3,
                        vertical = BbSpacing.Space2
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Language,
                        contentDescription = null,
                        tint = BbColors.Black,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )

                    Text(
                        text = selectedLanguage.label,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = BbColors.TextStrong
                    )

                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = BbColors.Black,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = language.label,
                                style = MaterialTheme.typography.bodyMedium,
                                color = BbColors.TextStrong
                            )
                        },
                        onClick = {
                            selectedLanguage = language
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = BbColors.TextStrong
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
            color = BbColors.Gray.Gray900,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

        Text(
            text = "Perakende veya toptan dünyasına hızlıca giriş yap.",
            color = BbColors.Gray.Gray800,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space10))

        ModeSelectionCommerceCard(
            title = "Perakende Alışveriş",
            description = "Ürünleri keşfet, favorilerine ekle ve güvenle sepetine taşı.",
            iconBackground = BbColors.Primary,
            iconColor = BbColors.Gray.Gray900,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.LocalMall,
                    contentDescription = null,
                    tint = BbColors.Gray.Gray900,
                    modifier = Modifier.size(BbIcon.SizeXl)
                )
            },
            onClick = onRetailClick
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space4))

        ModeSelectionCommerceCard(
            title = "Toptan Ticaret",
            description = "Tedarikçileri, toplu ürünleri ve teklif süreçlerini keşfet.",
            iconBackground = BbColors.Primary,
            iconColor = BbColors.Gray.Gray900,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Groups,
                    contentDescription = null,
                    tint = BbColors.Gray.Gray900,
                    modifier = Modifier.size(BbIcon.SizeXl)
                )
            },
            onClick = onWholesaleClick
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space4))

        ModeSelectionRfqShortcut(
            onClick = onRfqClick
        )
    }
}

@Composable
private fun ModeSelectionCommerceCard(
    title: String,
    description: String,
    iconBackground: Color,
    iconColor: Color,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = BbRadius.XxlShape,
        color = BbColors.Navy.Navy900,
        shadowElevation = BbSpacing.Space2
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.Space5),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = BbRadius.XlShape,
                color = iconBackground
            ) {
                Box(
                    modifier = Modifier.size(BbIcon.BoxXl),
                    contentAlignment = Alignment.Center
                ) {
                    icon()
                }
            }

            Spacer(modifier = Modifier.width(BbSpacing.Space4))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BbColors.White,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(BbSpacing.Space1))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.Gray.Gray300
                )
            }
        }
    }
}

@Composable
private fun ModeSelectionRfqShortcut(
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = BbColors.White.copy(alpha = 0.78f),
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.White.copy(alpha = 0.95f)
        ),
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BbSpacing.Space4,
                    vertical = BbSpacing.Space3
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = BbRadius.PillShape,
                color = BbColors.PrimarySoft
            ) {
                Box(
                    modifier = Modifier.size(BbIcon.BoxMd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Description,
                        contentDescription = null,
                        tint = BbColors.Gray.Gray900,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }
            }

            Spacer(modifier = Modifier.width(BbSpacing.Space3))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "RFQ Talebi Gönder",
                    style = MaterialTheme.typography.titleSmall,
                    color = BbColors.Gray.Gray900,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Toptan alım için tedarikçilerden son fiyat iste.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.Gray.Gray700
                )
            }
        }
    }
}

@Composable
private fun ModeSelectionLegalFooter() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Devam ederek Bulbulustur politikalarını kabul etmiş olursunuz.",
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.Gray.Gray800,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space1))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    uriHandler.openUri("https://www.bulbulustur.com/support/condition/1/kosullar")
                }
            ) {
                Text(
                    text = "Kullanım Koşulları",
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.Gray.Gray900,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "•",
                style = MaterialTheme.typography.labelSmall,
                color = BbColors.Gray.Gray800
            )

            TextButton(
                onClick = {
                    uriHandler.openUri("https://www.bulbulustur.com/support/condition/2/politikalar")
                }
            ) {
                Text(
                    text = "Gizlilik Politikası",
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.Gray.Gray900,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private data class ModeSelectionLanguage(
    val code: String,
    val label: String
)