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
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.WorkspacePremium
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
fun CompanyB2BStatusScreen(
    onBackClick: () -> Unit = {},
    onB2BManagementPanelClick: () -> Unit = {}
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
                title = "B2B Index Durumu",
                onBackClick = onBackClick
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
                CompanyB2BStatusIntroCard()
            }

            item {
                CompanyB2BActiveSummaryCard()
            }

            item {
                CompanyB2BStatusStatsGrid()
            }

            item {
                CompanyB2BStatusSection(
                    title = "B2B Listeleme Bilgileri",
                    description = "Bu bilgiler şirketinizin Bulbulustur toptan satış tarafındaki görünürlüĞünü gösterir.",
                    icon = Icons.Outlined.WorkspacePremium
                ) {
                    CompanyB2BStatusInfoRow(
                        title = "Şirket Adı",
                        value = "Bulbulustur İnternet Teknolojileri ve Tic. A.Ş.",
                        icon = Icons.Outlined.Business
                    )

                    CompanyStatusDashedDivider()

                    CompanyB2BStatusInfoRow(
                        title = "Listeleme Durumu",
                        value = "B2B Index Aktif",
                        icon = Icons.Outlined.Verified
                    )

                    CompanyStatusDashedDivider()

                    CompanyB2BStatusInfoRow(
                        title = "Abonelik Tipi",
                        value = "B2B e-marketplace / Free",
                        icon = Icons.Outlined.Storefront
                    )

                    CompanyStatusDashedDivider()

                    CompanyB2BStatusInfoRow(
                        title = "Firma KimliĞi",
                        value = "FGA0IBO7EGAZ5nB",
                        icon = Icons.Outlined.Language
                    )
                }
            }

            item {
                CompanyB2BManagementPanelCard(
                    onClick = onB2BManagementPanelClick
                )
            }
        }
    }
}

@Composable
private fun CompanyB2BStatusIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxXl)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.XlShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Verified,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Şirketiniz Toptan Ticaret GörünürlüĞünde",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Bulbulustur B2B Index üzerinde şirketiniz aktif olarak listeleniyor. Yönetim panelinden profilinizi, ürünlerinizi ve toptan satış operasyonlarınızı yönetebilirsiniz.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CompanyB2BActiveSummaryCard() {
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
                        imageVector = Icons.Outlined.Storefront,
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
                        text = "Bulbulustur İnternet Teknolojileri ve Tic. A.Ş.",
                        style = BbTypography.titleMedium,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )

                    Text(
                        text = "B2B e-marketplace / Free",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Muted)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompanyB2BStatusPill(
                    text = "B2B Index Aktif",
                    icon = Icons.Outlined.Verified
                )

                CompanyB2BStatusPill(
                    text = "Yönetim Hazır",
                    icon = Icons.Outlined.WorkspacePremium
                )
            }
        }
    }
}

@Composable
private fun CompanyB2BStatusPill(
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
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
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
private fun CompanyB2BStatusStatsGrid() {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            CompanyB2BStatusStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Verified,
                label = "Listeleme",
                value = "Aktif"
            )

            CompanyB2BStatusStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Storefront,
                label = "Abonelik",
                value = "Free"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            CompanyB2BStatusStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.RequestQuote,
                label = "RFQ",
                value = "Açık"
            )

            CompanyB2BStatusStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.DateRange,
                label = "Oluşturma",
                value = "2025"
            )
        }
    }
}

@Composable
private fun CompanyB2BStatusStatCard(
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
private fun CompanyB2BStatusSection(
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

            CompanyStatusDashedDivider()

            Column {
                content()
            }
        }
    }
}

@Composable
private fun CompanyB2BStatusInfoRow(
    title: String,
    value: String,
    icon: ImageVector
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
private fun CompanyB2BManagementPanelCard(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.XlShape
            )
            .clickable {
                onClick()
            }
            .padding(BBSpacing.CardPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.OpenInNew,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "B2B Yönetim Paneline Git",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    text = "Şirket profilinizi, ürünlerinizi ve toptan satış operasyonlarınızı yönetmek için panel tarafına geçin.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.78f)
                )
            }

            Box(
                modifier = Modifier
                    .size(BBIcon.BoxSm)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
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
}

@Composable
private fun CompanyStatusDashedDivider() {
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


