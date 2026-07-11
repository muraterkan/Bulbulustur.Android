package com.bulbulustur.android.Application.Views.Home

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowCompat
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Config.LegalPolicyUrls
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.R
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO

private const val MODE_SELECTION_TURKISH_FLAG = "file:///android_asset/flags/turkey.svg"
private const val MODE_SELECTION_ENGLISH_FLAG = "file:///android_asset/flags/uk.svg"
private const val MODE_SELECTION_FALLBACK_FLAG = "file:///android_asset/flags/flag.svg"

@Composable
fun ModeSelectionScreen(
    languages: List<SystemDescLanguageDTO> = emptyList(),
    selectedLanguageId: Int = 1,
    isLanguageLoading: Boolean = false,
    languageErrorMessage: String? = null,
    onLanguageSelected: (Int) -> Unit = {},
    onRetailClick: () -> Unit,
    onWholesaleClick: () -> Unit,
    onRfqClick: () -> Unit = onWholesaleClick
) {
    var isLanguageDialogVisible by remember { mutableStateOf(false) }

    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isDark) {
        BBColors.Ink.Ink900
    } else {
        BBColors.Gray.Gray50
    }

    ModeSelectionSystemBars(
        backgroundColor = backgroundColor,
        useLightIcons = isDark
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.Space5,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.Space4
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ModeSelectionHeader(
                languages = languages,
                selectedLanguageId = selectedLanguageId,
                isDark = isDark,
                onLanguageClick = {
                    isLanguageDialogVisible = true
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            ModeSelectionContent(
                isDark = isDark,
                onRetailClick = onRetailClick,
                onWholesaleClick = onWholesaleClick,
                onRfqClick = onRfqClick
            )

            Spacer(modifier = Modifier.weight(1f))

            ModeSelectionLegalFooter(isDark = isDark)
        }
    }

    if (isLanguageDialogVisible) {
        ModeSelectionLanguageDialog(
            languages = languages,
            selectedLanguageId = selectedLanguageId,
            isLoading = isLanguageLoading,
            onDismissRequest = {
                isLanguageDialogVisible = false
            },
            onLanguageSelected = { languageId ->
                onLanguageSelected(languageId)
                isLanguageDialogVisible = false
            }
        )
    }
}

@Composable
private fun ModeSelectionHeader(
    languages: List<SystemDescLanguageDTO>,
    selectedLanguageId: Int,
    isDark: Boolean,
    onLanguageClick: () -> Unit
) {
    val selectedLanguage = ModeSelectionResolveLanguage(
        languages = languages,
        selectedLanguageId = selectedLanguageId
    )

    val logoResource = if (isDark) {
        R.drawable.logo_white
    } else {
        R.drawable.logo_black
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(BBLayout.LogoHeightLarge - BBSpacing.Space2),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = logoResource),
                contentDescription = "Bulbulustur",
                modifier = Modifier
                    .width(BBLayout.LogoWidthLarge - BBSpacing.Space8)
                    .height(BBLayout.LogoHeightLarge - BBSpacing.Space2),
                contentScale = ContentScale.Fit
            )
        }

        Surface(
            modifier = Modifier
                .defaultMinSize(minHeight = BBIcon.BoxLg)
                .clickable { onLanguageClick() },
            shape = BBRadius.XlShape,
            color = if (isDark) BBColors.Ink.Ink800 else BBColors.White,
            border = BorderStroke(
                width = BBSpacing.Divider,
                color = if (isDark) BBColors.Ink.Ink200 else BBColors.Yellow.Yellow400
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
                AsyncImage(
                    model = selectedLanguage.flagAssetPath,
                    contentDescription = selectedLanguage.title,
                    modifier = Modifier.size(BBIcon.SizeMd),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = selectedLanguage.title,
                    style = BbTypography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (isDark) BBColors.Gray.Gray100 else BBColors.Ink.Ink900
                )

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    tint = if (isDark) BBColors.Gray.Gray100 else BBColors.Ink.Ink900,
                    modifier = Modifier.size(BBIcon.SizeMd)
                )
            }
        }
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
        Surface(
            shape = BBRadius.PillShape,
            color = if (isDark) BBColors.Ink.Ink800 else BBColors.Yellow.Yellow100,
            border = BorderStroke(
                width = BBSpacing.Divider,
                color = if (isDark) BBColors.Ink.Ink200 else BBColors.Yellow.Yellow300
            )
        ) {
            Text(
                text = "Bulbulustur Alıcı Uygulaması",
                modifier = Modifier.padding(
                    horizontal = BBSpacing.Space4,
                    vertical = BBSpacing.Space2
                ),
                style = BbTypography.labelMedium,
                color = if (isDark) BBColors.Yellow.Yellow300 else BBColors.Yellow.Yellow800,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(BBSpacing.Space4))

        Text(
            text = "Alışveriş Modunu Seç",
            style = BbTypography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = if (isDark) BBColors.White else BBColors.Ink.Ink900,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Perakende alışverişe veya toptan ticaret akışına hızlıca giriş yap.",
            modifier = Modifier.padding(top = BBSpacing.Space2),
            style = BbTypography.bodyMedium,
            color = if (isDark) BBColors.Gray.Gray300 else BBColors.Gray.Gray700,
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BBSpacing.Space7),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            ModeSelectionCommerceCard(
                title = "Perakende Alışveriş",
                description = "Ürünleri keşfet, favorilerine ekle ve güvenle sepetine taşı.",
                icon = Icons.Outlined.LocalMall,
                containerColor = if (isDark) BBColors.Ink.Ink800 else BBColors.Ink.Ink900,
                iconContainerColor = BBColors.Yellow.Yellow100,
                iconColor = BBColors.Ink.Ink900,
                titleColor = BBColors.White,
                descriptionColor = BBColors.Gray.Gray300,
                arrowColor = BBColors.Yellow.Yellow300,
                borderColor = if (isDark) BBColors.Ink.Ink100 else BBColors.Ink.Ink200,
                onClick = onRetailClick
            )

            ModeSelectionCommerceCard(
                title = "Toptan Ticaret",
                description = "Tedarikçileri, toplu ürünleri ve teklif süreçlerini keşfet.",
                icon = Icons.Outlined.Business,
                containerColor = if (isDark) BBColors.Ink.Ink800 else BBColors.Ink.Ink900,
                iconContainerColor = BBColors.Yellow.Yellow100,
                iconColor = BBColors.Ink.Ink900,
                titleColor = BBColors.White,
                descriptionColor = BBColors.Gray.Gray300,
                arrowColor = BBColors.Yellow.Yellow300,
                borderColor = if (isDark) BBColors.Ink.Ink100 else BBColors.Ink.Ink200,
                onClick = onWholesaleClick
            )

            ModeSelectionCommerceCard(
                title = "RFQ Talebi Gönder",
                description = "Toptan alım için tedarikçilerden son fiyat iste.",
                icon = Icons.Outlined.RequestQuote,
                containerColor = if (isDark) BBColors.Ink.Ink900 else BBColors.White,
                iconContainerColor = if (isDark) BBColors.Ink.Ink800 else BBColors.Yellow.Yellow100,
                iconColor = if (isDark) BBColors.Yellow.Yellow300 else BBColors.Ink.Ink900,
                titleColor = if (isDark) BBColors.White else BBColors.Ink.Ink900,
                descriptionColor = if (isDark) BBColors.Gray.Gray400 else BBColors.Gray.Gray700,
                arrowColor = BBColors.Yellow.Yellow300,
                borderColor = if (isDark) BBColors.Ink.Ink200 else BBColors.Yellow.Yellow300,
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
            .defaultMinSize(minHeight = BBSpacing.Space20)
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
                    style = BbTypography.titleMedium,
                    color = titleColor,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = description,
                    style = BbTypography.bodySmall,
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
private fun ModeSelectionLegalFooter(
    isDark: Boolean
) {
    val uriHandler = LocalUriHandler.current
    val textColor = if (isDark) BBColors.Gray.Gray400 else BBColors.Gray.Gray700
    val linkColor = if (isDark) BBColors.Yellow.Yellow300 else BBColors.Ink.Ink900

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = "Devam ederek Bulbulustur kullanım şartlarını kabul etmiş olursunuz.",
            style = BbTypography.labelSmall,
            color = textColor,
            textAlign = TextAlign.Center
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    uriHandler.openUri(LegalPolicyUrls.Terms)
                }
            ) {
                Text(
                    text = "Kullanım Koşulları",
                    style = BbTypography.labelSmall,
                    color = linkColor,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "·",
                style = BbTypography.labelSmall,
                color = textColor
            )

            TextButton(
                onClick = {
                    uriHandler.openUri(LegalPolicyUrls.Privacy)
                }
            ) {
                Text(
                    text = "Gizlilik Politikası",
                    style = BbTypography.labelSmall,
                    color = linkColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun ModeSelectionLanguageDialog(
    languages: List<SystemDescLanguageDTO>,
    selectedLanguageId: Int,
    isLoading: Boolean,
    onDismissRequest: () -> Unit,
    onLanguageSelected: (Int) -> Unit
) {
    val visibleLanguages = ModeSelectionResolveLanguages(languages)

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.PageHorizontal),
            shape = BBRadius.XxlShape,
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(
                width = BBSpacing.Divider,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.Space5),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                Text(
                    text = "Dil Seçimi",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Uygulamada kullanmak istediğiniz dili seçin.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                when {
                    isLoading && languages.isEmpty() -> {
                        ModeSelectionLanguageLoading()
                    }

                    else -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                        ) {
                            visibleLanguages.forEach { language ->
                                ModeSelectionLanguageRow(
                                    item = language,
                                    isSelected = language.id == selectedLanguageId,
                                    onClick = {
                                        if (language.id != selectedLanguageId) {
                                            onLanguageSelected(language.id)
                                        } else {
                                            onDismissRequest()
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ModeSelectionLanguageRow(
    item: ModeSelectionLanguageItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }

    val containerColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surface
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = BBRadius.XlShape,
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
                    horizontal = BBSpacing.Space4,
                    vertical = BBSpacing.Space3
                ),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = BBRadius.PillShape,
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Box(
                    modifier = Modifier.size(BBIcon.BoxMd),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = item.flagAssetPath,
                        contentDescription = item.title,
                        modifier = Modifier.size(BBIcon.SizeLg),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = item.title,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = item.code.uppercase(),
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Seçili dil",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.SizeLg)
                )
            }
        }
    }
}

@Composable
private fun ModeSelectionLanguageLoading() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(BBIcon.SizeLg),
            strokeWidth = BBSpacing.Space1 / 2,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(BBSpacing.Space3))

        Text(
            text = "Diller yükleniyor...",
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun ModeSelectionResolveLanguages(
    languages: List<SystemDescLanguageDTO>
): List<ModeSelectionLanguageItem> {
    return languages
        .filter {
            it.SystemDescLanguageId == 1 ||
                    it.SystemDescLanguageId == 2
        }
        .map {
            ModeSelectionLanguageItem(
                id = it.SystemDescLanguageId,
                title = it.Content.ifBlank {
                    when (it.SystemDescLanguageId) {
                        2 -> "English"
                        else -> "Türkçe"
                    }
                },
                code = it.LanguageIsoCode.ifBlank {
                    when (it.SystemDescLanguageId) {
                        2 -> "en"
                        else -> "tr"
                    }
                },
                flagAssetPath = ModeSelectionLanguageFlagPath(it.SystemDescLanguageId)
            )
        }
        .ifEmpty {
            listOf(
                ModeSelectionLanguageItem(
                    id = 1,
                    title = "Türkçe",
                    code = "tr",
                    flagAssetPath = MODE_SELECTION_TURKISH_FLAG
                ),
                ModeSelectionLanguageItem(
                    id = 2,
                    title = "English",
                    code = "en",
                    flagAssetPath = MODE_SELECTION_ENGLISH_FLAG
                )
            )
        }
}

private fun ModeSelectionResolveLanguage(
    languages: List<SystemDescLanguageDTO>,
    selectedLanguageId: Int
): ModeSelectionLanguageItem {
    return ModeSelectionResolveLanguages(languages)
        .firstOrNull { it.id == selectedLanguageId }
        ?: when (selectedLanguageId) {
            2 -> ModeSelectionLanguageItem(
                id = 2,
                title = "English",
                code = "en",
                flagAssetPath = MODE_SELECTION_ENGLISH_FLAG
            )

            else -> ModeSelectionLanguageItem(
                id = 1,
                title = "Türkçe",
                code = "tr",
                flagAssetPath = MODE_SELECTION_TURKISH_FLAG
            )
        }
}

private fun ModeSelectionLanguageFlagPath(
    languageId: Int
): String {
    return when (languageId) {
        1 -> MODE_SELECTION_TURKISH_FLAG
        2 -> MODE_SELECTION_ENGLISH_FLAG
        else -> MODE_SELECTION_FALLBACK_FLAG
    }
}

private data class ModeSelectionLanguageItem(
    val id: Int,
    val title: String,
    val code: String,
    val flagAssetPath: String
)

@Composable
private fun ModeSelectionSystemBars(
    backgroundColor: Color,
    useLightIcons: Boolean
) {
    val view = LocalView.current

    DisposableEffect(backgroundColor, useLightIcons) {
        val activity = view.context as? Activity

        if (activity == null) {
            onDispose { }
        } else {
            val window = activity.window
            val previousStatusBarColor = window.statusBarColor
            val previousNavigationBarColor = window.navigationBarColor
            val controller = WindowCompat.getInsetsController(window, view)
            val previousLightStatusBars = controller.isAppearanceLightStatusBars
            val previousLightNavigationBars = controller.isAppearanceLightNavigationBars

            window.statusBarColor = backgroundColor.toArgb()
            window.navigationBarColor = backgroundColor.toArgb()
            controller.isAppearanceLightStatusBars = !useLightIcons
            controller.isAppearanceLightNavigationBars = !useLightIcons

            onDispose {
                window.statusBarColor = previousStatusBarColor
                window.navigationBarColor = previousNavigationBarColor
                controller.isAppearanceLightStatusBars = previousLightStatusBars
                controller.isAppearanceLightNavigationBars = previousLightNavigationBars
            }
        }
    }
}