package com.bulbulustur.android.Application.Views.Account

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.HomeWork
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha

@Composable
fun CompanyInfoScreen(
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onB2BIndexClick: () -> Unit = {},
    onB2CStoreClick: () -> Unit = {}
) {
    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = BBAlpha.DisabledLabel),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.80f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
        )
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Firma Bilgilerim",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Edit,
                actionContentDescription = "Firma Bilgilerini Düzenle",
                onActionClick = onEditClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(pageBackground)
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
                CompanyHeroCard()
            }

            item {
                CompanyStatsGrid()
            }

            item {
                CompanyInfoSection(
                    title = "Åirket KimliĞi",
                    description = "Ünvan, şirket tipi ve kurumsal profil bilgileri.",
                    icon = Icons.Outlined.Badge
                ) {
                    CompanyInfoRow("Åirket Ünvanı", "Türkiye Global Ticaret Limited Åirketi")
                    CompanyDashedDivider()
                    CompanyInfoRow("Åirket Tipi", "Limited Åirket")
                    CompanyDashedDivider()
                    CompanyInfoRow("Abonelik Planı", "B2B e-marketplace / Free")
                    CompanyDashedDivider()
                    CompanyInfoRow("Kuruluş Yılı", "2025")
                }
            }

            item {
                CompanyInfoSection(
                    title = "Adres Bilgileri",
                    description = "Åirketin kayıtlı lokasyon bilgileri.",
                    icon = Icons.Outlined.LocationOn
                ) {
                    CompanyInfoRow("Adres", "Fulya Mah., Aytekin Kotil Cad., No: 11/1")
                    CompanyDashedDivider()
                    CompanyInfoRow("Ülke", "Türkiye")
                    CompanyDashedDivider()
                    CompanyInfoRow("Åehir", "Ä°stanbul")
                    CompanyDashedDivider()
                    CompanyInfoRow("Ä°lçe", "Åişli")
                    CompanyDashedDivider()
                    CompanyInfoRow("Posta Kodu", "34394")
                }
            }

            item {
                CompanyInfoSection(
                    title = "Vergi ve Resmi Bilgiler",
                    description = "Fatura ve resmi kayıt süreçlerinde kullanılan bilgiler.",
                    icon = Icons.Outlined.ReceiptLong
                ) {
                    CompanyInfoRow("Vergi Dairesi", "Åişli Vergi Dairesi")
                    CompanyDashedDivider()
                    CompanyInfoRow("Vergi Numarası", "789456123")
                    CompanyDashedDivider()
                    CompanyInfoRow("MERSÄ°S", "0188129921700001")
                    CompanyDashedDivider()
                    CompanyInfoRow("KEP", "tglobal@bulbulustur.com")
                    CompanyDashedDivider()
                    CompanyInfoRow("Web Sitesi", "www.turkiyeglobal.com")
                }
            }

            item {
                CompanyActionSection(
                    onB2BIndexClick = onB2BIndexClick,
                    onB2CStoreClick = onB2CStoreClick
                )
            }
        }
    }
}

@Composable
private fun CompanyHeroCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = BBRadius.XlShape
            )
            .padding(BBSpacing.CardPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxXl)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = BBRadius.XlShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Business,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BBIcon.Section)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = "Kurumsal Hesap",
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Türkiye Global Ticaret Limited Åirketi",
                        style = BbTypography.titleLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )

                    Text(
                        text = "Limited Åirket",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Muted)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompanyHeroBadge(
                    text = "DoĞrulanmış Firma",
                    icon = Icons.Outlined.Verified
                )

                CompanyHeroBadge(
                    text = "B2B Aktif",
                    icon = Icons.Outlined.Storefront
                )
            }
        }
    }
}

@Composable
private fun CompanyHeroBadge(
    text: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Overlay),
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(BBIcon.Size2Xs)
        )

        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}

@Composable
private fun CompanyStatsGrid() {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            CompanyStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Storefront,
                label = "Abonelik Planı",
                value = "Free"
            )

            CompanyStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.HomeWork,
                label = "Kuruluş Yılı",
                value = "2025"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            CompanyStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.LocationOn,
                label = "Ülke / Åehir",
                value = "Türkiye / Ä°stanbul"
            )

            CompanyStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Verified,
                label = "Profil Durumu",
                value = "Aktif"
            )
        }
    }
}

@Composable
private fun CompanyStatCard(
    modifier: Modifier,
    icon: ImageVector,
    label: String,
    value: String
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Ui)
                )
            }

            Text(
                text = label,
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CompanyInfoSection(
    title: String,
    description: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BBRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BBIcon.Ui)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = title,
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = description,
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            CompanyDashedDivider()

            Column {
                content()
            }
        }
    }
}

@Composable
private fun CompanyInfoRow(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = BBSpacing.CardPadding,
                vertical = BBSpacing.Space3
            ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = label,
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CompanyActionSection(
    onB2BIndexClick: () -> Unit,
    onB2CStoreClick: () -> Unit
) {
    CompanyInfoSection(
        title = "Kurumsal Ä°şlemler",
        description = "Åirket profilini ticaret akışlarına baĞla.",
        icon = Icons.Outlined.Business
    ) {
        CompanyActionRow(
            title = "Åirketimi B2B Indexâ€™e Dahil Et",
            description = "Tedarikçiler ve toptan alıcılar tarafından Keşfedil.",
            icon = Icons.Outlined.Storefront,
            onClick = onB2BIndexClick
        )

        CompanyDashedDivider()

        CompanyActionRow(
            title = "B2C MaĞazamı Yönet",
            description = "Perakende maĞaza görünürlüĞünü ve satış akışını yönet.",
            icon = Icons.Outlined.HomeWork,
            onClick = onB2CStoreClick
        )
    }
}

@Composable
private fun CompanyActionRow(
    title: String,
    description: String,
    icon: ImageVector,
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
                    shape = BBRadius.PillShape
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

            Text(
                text = description,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Box(
            modifier = Modifier
                .size(BBIcon.BoxSm)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BBRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.SizeSm)
            )
        }
    }
}

@Composable
private fun CompanyDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.Space4,
                end = BBSpacing.Space4
            )
            .size(height = 1.dp, width = BBSpacing.BorderThin)
    ) {
        drawLine(
            color = dividerColor,
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


