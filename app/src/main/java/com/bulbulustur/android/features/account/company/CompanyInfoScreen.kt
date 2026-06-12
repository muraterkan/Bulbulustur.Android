package com.bulbulustur.android.features.account.company

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
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbInnerPageHeader
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun CompanyInfoScreen(
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onB2BIndexClick: () -> Unit = {},
    onB2CStoreClick: () -> Unit = {}
) {
    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
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
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                CompanyHeroCard()
            }

            item {
                CompanyStatsGrid()
            }

            item {
                CompanyInfoSection(
                    title = "Şirket Kimliği",
                    description = "Ünvan, şirket tipi ve kurumsal profil bilgileri.",
                    icon = Icons.Outlined.Badge
                ) {
                    CompanyInfoRow("Şirket Ünvanı", "Türkiye Global Ticaret Limited Şirketi")
                    CompanyDashedDivider()
                    CompanyInfoRow("Şirket Tipi", "Limited Şirket")
                    CompanyDashedDivider()
                    CompanyInfoRow("Abonelik Planı", "B2B e-marketplace / Free")
                    CompanyDashedDivider()
                    CompanyInfoRow("Kuruluş Yılı", "2025")
                }
            }

            item {
                CompanyInfoSection(
                    title = "Adres Bilgileri",
                    description = "Şirketin kayıtlı lokasyon bilgileri.",
                    icon = Icons.Outlined.LocationOn
                ) {
                    CompanyInfoRow("Adres", "Fulya Mah., Aytekin Kotil Cad., No: 11/1")
                    CompanyDashedDivider()
                    CompanyInfoRow("Ülke", "Türkiye")
                    CompanyDashedDivider()
                    CompanyInfoRow("Şehir", "İstanbul")
                    CompanyDashedDivider()
                    CompanyInfoRow("İlçe", "Şişli")
                    CompanyDashedDivider()
                    CompanyInfoRow("Posta Kodu", "34394")
                }
            }

            item {
                CompanyInfoSection(
                    title = "Vergi Ve Resmi Bilgiler",
                    description = "Fatura ve resmi kayıt süreçlerinde kullanılan bilgiler.",
                    icon = Icons.Outlined.ReceiptLong
                ) {
                    CompanyInfoRow("Vergi Dairesi", "Şişli Vergi Dairesi")
                    CompanyDashedDivider()
                    CompanyInfoRow("Vergi Numarası", "789456123")
                    CompanyDashedDivider()
                    CompanyInfoRow("MERSİS", "0188129921700001")
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
                shape = BbRadius.XlShape
            )
            .padding(BbSpacing.CardPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxXl)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = BbRadius.XlShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Business,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.Section)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Kurumsal Hesap",
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Türkiye Global Ticaret Limited Şirketi",
                        style = BbTypography.titleLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )

                    Text(
                        text = "Limited Şirket",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.72f)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompanyHeroBadge(
                    text = "Doğrulanmış Firma",
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
                color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.12f),
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            ),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(BbIcon.Size2Xs)
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            CompanyStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.LocationOn,
                label = "Ülke / Şehir",
                value = "Türkiye / İstanbul"
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxMd)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BbIcon.Ui)
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
                    .padding(BbSpacing.CardPadding),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BbRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BbIcon.Ui)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
                horizontal = BbSpacing.CardPadding,
                vertical = BbSpacing.Space3
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
        title = "Kurumsal İşlemler",
        description = "Şirket profilini ticaret akışlarına bağla.",
        icon = Icons.Outlined.Business
    ) {
        CompanyActionRow(
            title = "Şirketimi B2B Index’e Dahil Et",
            description = "Tedarikçiler ve toptan alıcılar tarafından keşfedil.",
            icon = Icons.Outlined.Storefront,
            onClick = onB2BIndexClick
        )

        CompanyDashedDivider()

        CompanyActionRow(
            title = "B2C Mağazamı Yönet",
            description = "Perakende mağaza görünürlüğünü ve satış akışını yönet.",
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
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BbRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BbIcon.Ui)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
                .size(BbIcon.BoxSm)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BbRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BbIcon.SizeSm)
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
                start = BbSpacing.Space4,
                end = BbSpacing.Space4
            )
            .size(height = 1.dp, width = 1.dp)
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