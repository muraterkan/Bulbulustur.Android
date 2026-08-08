package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import android.content.pm.PackageManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutThisAppScreen(
    onBackClick: () -> Unit = {},
    onAboutBulbulusturClick: () -> Unit = {},
    onCompanyPageClick: () -> Unit = {},
    onContactUsClick: () -> Unit = {},
    onLegalPoliciesClick: () -> Unit = {},
    onCheckUpdatesClick: () -> Unit = {},
    onSystemStatusClick: () -> Unit = {},
    onClearCacheClick: () -> Unit = {},
    onShareAppClick: () -> Unit = {},
    onRateAppClick: () -> Unit = {}
) {
    val context = LocalContext.current

    val appVersion = remember {
        getAppVersionLabel(
            packageManager = context.packageManager,
            packageName = context.packageName
        )
    }

    var activeSheet by remember {
        mutableStateOf<AboutThisAppSheetType?>(null)
    }

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "fc0a3fe5-4123-4ec5-8499-fccd3efd88d3", fallback = "Uygulama Hakkında"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                AboutAppHero(
                    versionLabel = appVersion.versionName,
                    onLogoClick = {
                        activeSheet = AboutThisAppSheetType.BrandLinks
                    }
                )
            }

            item {
                AboutMenuGroup {
                    AboutMenuRow(
                        icon = Icons.Outlined.Cached,
                        title = BBLocalization.Current.Get(key = "46e78f90-865d-4e0c-8f28-0fa5e001cee1", fallback = "Güncellemeleri Kontrol Et"),
                        value = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
                        showArrow = true,
                        enabled = true,
                        onClick = onCheckUpdatesClick
                    )

                    AboutDashedDivider()

                    AboutMenuRow(
                        icon = Icons.Outlined.Dns,
                        title = BBLocalization.Current.Get(key = "ace0031a-6e70-445a-b319-d5a20536e669", fallback = "Sistem Durumu"),
                        value = BBLocalization.Current.Get(key = "81a0f528-96b8-45e9-892e-461b524cc16e", fallback = "Tüm sistemler çalışıyor"),
                        showArrow = true,
                        enabled = true,
                        onClick = onSystemStatusClick
                    )

                    AboutDashedDivider()

                    AboutMenuRow(
                        icon = Icons.Outlined.Cached,
                        title = BBLocalization.Current.Get(key = "ec31421d-7f8c-4b5a-94f2-37da5ef4e4ca", fallback = "Önbelleği Temizle"),
                        value = null,
                        showArrow = true,
                        enabled = true,
                        onClick = {
                            activeSheet = AboutThisAppSheetType.ClearCache
                        }
                    )

                    AboutDashedDivider()

                    AboutMenuRow(
                        icon = Icons.Outlined.StarRate,
                        title = BBLocalization.Current.Get(key = "849aeaa5-c640-414d-ad0a-14af0e50deaa", fallback = "Bizi Değerlendir"),
                        value = null,
                        showArrow = true,
                        enabled = true,
                        onClick = {
                            activeSheet = AboutThisAppSheetType.RateApp
                        }
                    )
                }
            }
        }

        activeSheet?.let { sheetType ->
            ModalBottomSheet(
                onDismissRequest = {
                    activeSheet = null
                },
                sheetState = bottomSheetState,
                containerColor = MaterialTheme.colorScheme.surface,
                shape = BBRadius.XxlShape
            ) {
                when (sheetType) {
                    AboutThisAppSheetType.BrandLinks -> {
                        AboutBrandLinksSheet(
                            onAboutBulbulusturClick = {
                                activeSheet = null
                                onAboutBulbulusturClick()
                            },
                            onCompanyPageClick = {
                                activeSheet = null
                                onCompanyPageClick()
                            },
                            onContactUsClick = {
                                activeSheet = null
                                onContactUsClick()
                            },
                            onLegalPoliciesClick = {
                                activeSheet = null
                                onLegalPoliciesClick()
                            }
                        )
                    }

                    AboutThisAppSheetType.ClearCache -> {
                        AboutClearCacheSheet(
                            onDismissClick = {
                                activeSheet = null
                            },
                            onConfirmClick = {
                                activeSheet = null
                                onClearCacheClick()
                            }
                        )
                    }

                    AboutThisAppSheetType.RateApp -> {
                        AboutRateAppSheet(
                            onDismissClick = {
                                activeSheet = null
                            },
                            onRateAppClick = {
                                activeSheet = null
                                onRateAppClick()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AboutAppHero(
    versionLabel: String,
    onLogoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = BBSpacing.Space8,
                bottom = BBSpacing.Space6
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        AboutLogoShowcase(
            onLogoClick = onLogoClick
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "4a6e572d-68fd-4f1c-84cc-9bf8bf96f1f7", fallback = "Toptan ve Perakende Ticaret Platformu"),
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Sürüm $versionLabel",
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun AboutLogoShowcase(
    onLogoClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BBLayout.AboutAppLogoOuterSize)
            .clickable {
                onLogoClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.size(102.dp),
            shape = BBRadius.XxlShape,
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(BBSpacing.Space4),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_black),
                    contentDescription = "Bulbulustur Logo",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun AboutMenuGroup(
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
                .background(MaterialTheme.colorScheme.surface)
        ) {
            content()
        }
    }
}

@Composable
private fun AboutMenuRow(
    icon: ImageVector,
    title: String,
    value: String?,
    showArrow: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (enabled) {
                    Modifier.clickable {
                        onClick()
                    }
                } else {
                    Modifier
                }
            )
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
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
                modifier = Modifier.size(BBIcon.Ui)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (!value.isNullOrBlank()) {
                Text(
                    text = value,
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (showArrow) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Ui)
            )
        }
    }
}

@Composable
private fun AboutBrandLinksSheet(
    onAboutBulbulusturClick: () -> Unit,
    onCompanyPageClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onLegalPoliciesClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.Space6
            ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        AboutBottomSheetHeader(
            title = "Bulbulustur",
            subtitle = BBLocalization.Current.Get(key = "0377a7fd-70dc-4087-b569-1d399cbfe85c", fallback = "Platform bilgileri ve güven bağlantıları.")
        )

        AboutMenuGroup {
            AboutSheetLinkRow(
                icon = Icons.Outlined.Info,
                title = BBLocalization.Current.Get(key = "c08b2887-d9b2-4671-b9ad-8e59f296b7c1", fallback = "Bulbulustur Hakkında"),
                onClick = onAboutBulbulusturClick
            )

            AboutDashedDivider()

            AboutSheetLinkRow(
                icon = Icons.Outlined.Language,
                title = BBLocalization.Current.Get(key = "192a4f4b-1552-4ade-a4c4-f66382827366", fallback = "Kurumsal Sayfa"),
                onClick = onCompanyPageClick
            )

            AboutDashedDivider()

            AboutSheetLinkRow(
                icon = Icons.Outlined.SupportAgent,
                title = BBLocalization.Current.Get(key = "2acff229-060f-4f8a-9232-e2dfa9c4f75b", fallback = "Yardım ve İletişim"),
                onClick = onContactUsClick
            )

            AboutDashedDivider()

            AboutSheetLinkRow(
                icon = Icons.Outlined.Policy,
                title = BBLocalization.Current.Get(key = "8aa96ea1-dff6-4eda-86b6-81d12a74cbb6", fallback = "Yasal Metinler ve Politikalar"),
                onClick = onLegalPoliciesClick
            )
        }
    }
}

@Composable
private fun AboutClearCacheSheet(
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.Space6
            ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        AboutBottomSheetTopBar(
            title = BBLocalization.Current.Get(key = "ec31421d-7f8c-4b5a-94f2-37da5ef4e4ca", fallback = "Önbelleği Temizle"),
            onCloseClick = onDismissClick
        )

        Text(
            text = BBLocalization.Current.Get(key = "081ea7ae-003f-4a85-8a8d-0ee20a17a187", fallback = "Bu işlem geçici uygulama verilerini temizler. Hesabınız, siparişleriniz, favorileriniz ve kayıtlı bilgileriniz etkilenmez."),
            style = BbTypography.bodyMedium.copy(
                lineHeight = 20.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            AboutSheetButton(
                text = BBLocalization.Current.Get(key = "92ebe8f3-c0b3-48a9-88a5-bb431ba27bf8", fallback = "İptal"),
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurface,
                borderColor = MaterialTheme.colorScheme.outlineVariant,
                onClick = onDismissClick
            )

            AboutSheetButton(
                text = BBLocalization.Current.Get(key = "0bff2074-48a3-4ba4-808f-2583f63aa227", fallback = "Temizle"),
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSurface,
                borderColor = MaterialTheme.colorScheme.primary,
                onClick = onConfirmClick
            )
        }
    }
}

@Composable
private fun AboutRateAppSheet(
    onDismissClick: () -> Unit,
    onRateAppClick: () -> Unit
) {
    var selectedRating by remember {
        mutableIntStateOf(0)
    }

    var feedbackText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.Space6
            ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
    ) {
        AboutBottomSheetTopBar(
            title = BBLocalization.Current.Get(key = "849aeaa5-c640-414d-ad0a-14af0e50deaa", fallback = "Bizi Değerlendir"),
            onCloseClick = onDismissClick
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "52735531-2e7b-445c-a873-a2937716f0c2", fallback = "Bulbulustur deneyimini nasıl buldun?"),
                style = BbTypography.titleLarge.copy(
                    lineHeight = 28.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = BBLocalization.Current.Get(key = "e593a59b-2e4f-4ce2-89c2-1131c1b8379d", fallback = "Görüşün, uygulamayı daha iyi hale getirmemize yardımcı olur."),
                style = BbTypography.bodyMedium.copy(
                    lineHeight = 20.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = BBRadius.XlShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = BBAlpha.Muted)
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = BBSpacing.Space4,
                    vertical = BBSpacing.Space5
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { index ->
                        val starIndex = index + 1

                        Icon(
                            imageVector = if (selectedRating >= starIndex) {
                                Icons.Outlined.Star
                            } else {
                                Icons.Outlined.StarBorder
                            },
                            contentDescription = "$starIndex yıldız",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(38.dp)
                                .clickable {
                                    selectedRating = starIndex
                                }
                        )
                    }
                }

                Text(
                    text = when {
                        selectedRating == 0 -> BBLocalization.Current.Get(key = "9b28baed-94c3-4dce-af33-ac9fc1d86c8c", fallback = "Bir puan seçerek devam edebilirsin.")
                        selectedRating <= 3 -> BBLocalization.Current.Get(key = "981b925c-0893-429c-ad3a-1f523b490a8f", fallback = "Neyi daha iyi yapabileceğimizi yazabilirsin.")
                        else -> "Teşekkürler. İstersen Google Play'de değerlendirebilirsin."
                    },
                    style = BbTypography.bodySmall.copy(
                        lineHeight = 18.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (selectedRating in 1..3) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = feedbackText,
                onValueChange = {
                    feedbackText = it
                },
                label = {
                    Text(BBLocalization.Current.Get(key = "834ed3a0-81fc-413c-bc92-7818d4ab2945", fallback = "Neyi daha iyi yapabiliriz?"))
                },
                minLines = 3
            )

            AboutSheetButton(
                text = BBLocalization.Current.Get(key = "be6a9448-d0f1-4487-a852-1ec522ad362b", fallback = "Geri Bildirim Gönder"),
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSurface,
                borderColor = MaterialTheme.colorScheme.primary,
                onClick = onDismissClick
            )
        }

        if (selectedRating >= 4) {
            AboutSheetButton(
                text = "Google Playy'de Değerlendir",
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSurface,
                borderColor = MaterialTheme.colorScheme.primary,
                onClick = onRateAppClick
            )
        }

        AboutSheetButton(
            text = BBLocalization.Current.Get(key = "0291a585-1ee1-459e-b4cc-0475784f8646", fallback = "Şimdi Değil"),
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface,
            borderColor = MaterialTheme.colorScheme.onSurface,
            onClick = onDismissClick
        )
    }
}

@Composable
private fun AboutBottomSheetHeader(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = BbTypography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = subtitle,
            style = BbTypography.bodyMedium.copy(
                lineHeight = 20.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AboutBottomSheetTopBar(
    title: String,
    onCloseClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = BbTypography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(BBIcon.BoxMd)
                .clip(BBRadius.IconBoxSoft)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onCloseClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = BBLocalization.Current.Get(key = "ca9452ab-b39e-4b65-b19b-c7e2b287bfaf", fallback = "Kapat"),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Ui)
            )
        }
    }
}

@Composable
private fun AboutSheetLinkRow(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
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
                modifier = Modifier.size(BBIcon.Ui)
            )
        }

        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BBIcon.Ui)
        )
    }
}

@Composable
private fun AboutSheetButton(
    text: String,
    modifier: Modifier,
    containerColor: Color,
    contentColor: Color,
    borderColor: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(BBSpacing.Space12)
            .clip(BBRadius.XxlShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.XxlShape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = BbTypography.labelLarge,
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun AboutDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.Space16,
                end = BBSpacing.Space4
            )
            .height(BBSpacing.BorderThin)
    ) {
        drawLine(
            color = dividerColor,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(8f, 8f),
                phase = 0f
            )
        )
    }
}

private enum class AboutThisAppSheetType {
    BrandLinks,
    ClearCache,
    RateApp
}

private data class AboutAppVersionLabel(
    val versionName: String,
    val versionCode: Long,
    val fullLabel: String
)

private fun getAppVersionLabel(
    packageManager: PackageManager,
    packageName: String
): AboutAppVersionLabel {
    return runCatching {
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val versionName = packageInfo.versionName ?: "1.0.0"
        val versionCode = packageInfo.longVersionCode

        AboutAppVersionLabel(
            versionName = versionName,
            versionCode = versionCode,
            fullLabel = "$versionName ($versionCode)"
        )
    }.getOrElse {
        AboutAppVersionLabel(
            versionName = "1.0.0",
            versionCode = 1,
            fullLabel = "1.0.0 (1)"
        )
    }
}
