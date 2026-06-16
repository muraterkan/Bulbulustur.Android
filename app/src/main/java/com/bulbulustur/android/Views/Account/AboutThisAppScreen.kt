package com.bulbulustur.android.Views.Account

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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulbulustur.android.R
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTypography
import com.bulbulustur.android.wwwroot.theme.BbLayout
import com.bulbulustur.android.wwwroot.theme.BbAlpha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutThisAppScreen(
    onBackClick: () -> Unit = {},
    onAboutBulbulusturClick: () -> Unit = {},
    onCompanyPageClick: () -> Unit = {},
    onContactUsClick: () -> Unit = {},
    onLegalPoliciesClick: () -> Unit = {},
    onVersionClick: () -> Unit = {},
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
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Uygulama HakkÄ±nda",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
                        icon = Icons.Outlined.Info,
                        title = "SÃ¼rÃ¼m",
                        value = appVersion.fullLabel,
                        showArrow = false,
                        enabled = false,
                        onClick = onVersionClick
                    )

                    AboutDashedDivider()

                    AboutMenuRow(
                        icon = Icons.Outlined.Cached,
                        title = "Ã–nbelleÄŸi Temizle",
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
                        title = "Bizi DeÄŸerlendir",
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
                containerColor = BbColors.Surface,
                shape = BbRadius.XxlShape
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
                top = BbSpacing.Space8,
                bottom = BbSpacing.Space6
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
    ) {
        AboutLogoShowcase(
            onLogoClick = onLogoClick
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "Toptan ve perakende ticaret platformu",
                style = BbTypography.bodyMedium,
                color = BbColors.TextMuted
            )

            Text(
                text = "SÃ¼rÃ¼m $versionLabel",
                style = BbTypography.labelLarge,
                color = BbColors.Yellow.Yellow800,
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
            .size(BbLayout.AboutAppLogoOuterSize)
            .clickable {
                onLogoClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(102.dp)
                .shadow(
                    elevation = 22.dp,
                    shape = BbRadius.XxlShape,
                    ambientColor = BbColors.Primary.copy(alpha = 0.36f),
                    spotColor = BbColors.Primary.copy(alpha = 0.50f)
                )
                .background(
                    color = BbColors.Primary.copy(alpha = 0.08f),
                    shape = BbRadius.XxlShape
                )
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = BbRadius.XxlShape,
                color = BbColors.Surface,
                shadowElevation = 8.dp,
                border = BorderStroke(
                    width = 1.dp,
                    color = BbColors.Border
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(BbSpacing.Space4),
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
                .background(BbColors.Surface)
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
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = BbColors.SurfaceMuted,
                    shape = BbRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.Ui)
            )
        }

        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = BbColors.TextStrong,
            modifier = Modifier.weight(1f)
        )

        if (!value.isNullOrBlank()) {
            Text(
                text = value,
                style = BbTypography.bodyMedium,
                color = BbColors.TextMuted
            )
        }

        if (showArrow) {
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
                start = BbSpacing.PageHorizontal,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.Space6
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
    ) {
        AboutBottomSheetHeader(
            title = "Bulbulustur",
            subtitle = "Platform bilgileri ve gÃ¼ven baÄŸlantÄ±larÄ±."
        )

        AboutMenuGroup {
            AboutSheetLinkRow(
                icon = Icons.Outlined.Info,
                title = "Bulbulustur HakkÄ±nda",
                onClick = onAboutBulbulusturClick
            )

            AboutDashedDivider()

            AboutSheetLinkRow(
                icon = Icons.Outlined.Language,
                title = "Kurumsal Sayfa",
                onClick = onCompanyPageClick
            )

            AboutDashedDivider()

            AboutSheetLinkRow(
                icon = Icons.Outlined.SupportAgent,
                title = "YardÄ±m ve Ä°letiÅŸim",
                onClick = onContactUsClick
            )

            AboutDashedDivider()

            AboutSheetLinkRow(
                icon = Icons.Outlined.Policy,
                title = "Yasal Metinler ve Politikalar",
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
                start = BbSpacing.PageHorizontal,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.Space6
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
    ) {
        AboutBottomSheetTopBar(
            title = "Ã–nbelleÄŸi Temizle",
            onCloseClick = onDismissClick
        )

        Text(
            text = "Bu iÅŸlem geÃ§ici uygulama verilerini temizler. HesabÄ±nÄ±z, sipariÅŸleriniz, favorileriniz ve kayÄ±tlÄ± bilgileriniz etkilenmez.",
            style = BbTypography.bodyMedium.copy(
                lineHeight = 20.sp
            ),
            color = BbColors.TextSubtle
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            AboutSheetButton(
                text = "Ä°ptal",
                modifier = Modifier.weight(1f),
                containerColor = BbColors.SurfaceMuted,
                contentColor = BbColors.TextStrong,
                borderColor = BbColors.Border,
                onClick = onDismissClick
            )

            AboutSheetButton(
                text = "Temizle",
                modifier = Modifier.weight(1f),
                containerColor = BbColors.Primary,
                contentColor = BbColors.TextStrong,
                borderColor = BbColors.Primary,
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
                start = BbSpacing.PageHorizontal,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.Space6
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
    ) {
        AboutBottomSheetTopBar(
            title = "Bizi DeÄŸerlendir",
            onCloseClick = onDismissClick
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "Bulbulustur deneyimini nasÄ±l buldun?",
                style = BbTypography.titleLarge.copy(
                    lineHeight = 28.sp
                ),
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "GÃ¶rÃ¼ÅŸÃ¼n, uygulamayÄ± daha iyi hale getirmemize yardÄ±mcÄ± olur.",
                style = BbTypography.bodyMedium.copy(
                    lineHeight = 20.sp
                ),
                color = BbColors.TextMuted
            )
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = BbRadius.XlShape,
            color = BbColors.PrimarySoft,
            border = BorderStroke(
                width = 1.dp,
                color = BbColors.Primary.copy(alpha = BbAlpha.Muted)
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = BbSpacing.Space4,
                    vertical = BbSpacing.Space5
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
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
                            contentDescription = "$starIndex yÄ±ldÄ±z",
                            tint = BbColors.Primary,
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
                        selectedRating == 0 -> "Bir puan seÃ§erek devam edebilirsin."
                        selectedRating <= 3 -> "Neyi daha iyi yapabileceÄŸimizi yazabilirsin."
                        else -> "TeÅŸekkÃ¼rler. Ä°stersen Google Playâ€™de de deÄŸerlendirebilirsin."
                    },
                    style = BbTypography.bodySmall.copy(
                        lineHeight = 18.sp
                    ),
                    color = BbColors.TextSubtle
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
                    Text("Neyi daha iyi yapabiliriz?")
                },
                minLines = 3
            )

            AboutSheetButton(
                text = "Geri Bildirim GÃ¶nder",
                modifier = Modifier.fillMaxWidth(),
                containerColor = BbColors.Primary,
                contentColor = BbColors.TextStrong,
                borderColor = BbColors.Primary,
                onClick = onDismissClick
            )
        }

        if (selectedRating >= 4) {
            AboutSheetButton(
                text = "Google Playâ€™de DeÄŸerlendir",
                modifier = Modifier.fillMaxWidth(),
                containerColor = BbColors.Primary,
                contentColor = BbColors.TextStrong,
                borderColor = BbColors.Primary,
                onClick = onRateAppClick
            )
        }

        AboutSheetButton(
            text = "Åimdi DeÄŸil",
            modifier = Modifier.fillMaxWidth(),
            containerColor = BbColors.TextStrong,
            contentColor = Color.White,
            borderColor = BbColors.TextStrong,
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = BbTypography.titleLarge,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = subtitle,
            style = BbTypography.bodyMedium.copy(
                lineHeight = 20.sp
            ),
            color = BbColors.TextMuted
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
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .clip(BbRadius.IconBoxSoft)
                .background(BbColors.SurfaceMuted)
                .clickable {
                    onCloseClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "Kapat",
                tint = BbColors.TextMuted,
                modifier = Modifier.size(BbIcon.Ui)
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
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = BbColors.SurfaceMuted,
                    shape = BbRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
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
            .height(BbSpacing.Space12)
            .clip(BbRadius.XxlShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.XxlShape,
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
