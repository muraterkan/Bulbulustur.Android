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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun CompanyB2BIndexScreen(
    onBackClick: () -> Unit = {},
    onActivateClick: () -> Unit = {}
) {
    val agreementAcceptedState = remember {
        mutableStateOf(false)
    }

    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.80f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(pageBackground)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentPadding = PaddingValues(
            horizontal = BbSpacing.PageHorizontal,
            vertical = BbSpacing.PageTopCompact
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        item {
            CompanyB2BIndexHeroCard(
                onBackClick = onBackClick
            )
        }

        item {
            CompanyB2BIndexSummaryCard()
        }

        item {
            CompanyB2BIndexStatsGrid()
        }

        item {
            CompanyB2BIndexSection(
                title = "B2B Index ne sağlar?",
                description = "Firmanızın toptan ticaret akışlarında daha görünür olmasına yardımcı olur.",
                icon = Icons.Outlined.WorkspacePremium
            ) {
                CompanyB2BIndexBenefitRow(
                    title = "Global Görünürlük",
                    description = "Şirket profiliniz uluslararası alıcılar için daha keşfedilebilir hale gelir.",
                    icon = Icons.Outlined.Public
                )

                CompanyDashedDivider()

                CompanyB2BIndexBenefitRow(
                    title = "RFQ Fırsatları",
                    description = "Potansiyel alıcılardan gelen fiyat teklifi süreçlerine daha yakın olursunuz.",
                    icon = Icons.Outlined.RequestQuote
                )

                CompanyDashedDivider()

                CompanyB2BIndexBenefitRow(
                    title = "Kurumsal Vitrin",
                    description = "Şirket bilgileriniz daha düzenli ve güven veren bir B2B profilinde sunulur.",
                    icon = Icons.Outlined.Storefront
                )
            }
        }

        item {
            CompanyB2BIndexAgreementCard(
                isAccepted = agreementAcceptedState.value,
                onAcceptedChange = { isAccepted ->
                    agreementAcceptedState.value = isAccepted
                }
            )
        }

        item {
            BbButton(
                text = "Şirketimi B2B Index’e Dahil Et",
                onClick = {
                    if (agreementAcceptedState.value) {
                        onActivateClick()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                variant = if (agreementAcceptedState.value) {
                    BbButtonVariant.Primary
                } else {
                    BbButtonVariant.Light
                },
                size = BbButtonSize.Large
            )
        }
    }
}

@Composable
private fun CompanyB2BIndexHeroCard(
    onBackClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            BbButton(
                text = "Şirket Bilgilerime Dön",
                onClick = onBackClick,
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxXl)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BbRadius.XlShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Language,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BbIcon.Section)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "B2B Index",
                        style = BbTypography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Şirketinizi global alıcılara açın",
                        style = BbTypography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Toptan alıcıların firmanızı keşfetmesi, teklif süreçlerinize ulaşması ve kurumsal profilinizi görmesi için şirketinizi B2B Index’e dahil edin.",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun CompanyB2BIndexSummaryCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = BbRadius.XlShape
            )
            .padding(BbSpacing.CardPadding)
    ) {
        Row(
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
                    imageVector = Icons.Outlined.Storefront,
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
                    text = "Türkiye Global Ticaret Limited Şirketi",
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )

                Text(
                    text = "Limited Şirket · İstanbul / Türkiye",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.72f)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompanyB2BIndexStatusPill(
                        text = "B2B Index Kapalı",
                        icon = Icons.Outlined.Verified
                    )

                    CompanyB2BIndexStatusPill(
                        text = "Profil Aktif",
                        icon = Icons.Outlined.CheckCircle
                    )
                }
            }
        }
    }
}

@Composable
private fun CompanyB2BIndexStatusPill(
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
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
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
private fun CompanyB2BIndexStatsGrid() {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            CompanyB2BIndexStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Storefront,
                label = "Firma Kimliği",
                value = "2-FGA0IBO7EGAZ5nB"
            )

            CompanyB2BIndexStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.LocationOn,
                label = "Lokasyon",
                value = "İstanbul"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            CompanyB2BIndexStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Public,
                label = "Görünürlük",
                value = "Kapalı"
            )

            CompanyB2BIndexStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.RequestQuote,
                label = "RFQ",
                value = "Hazır"
            )
        }
    }
}

@Composable
private fun CompanyB2BIndexStatCard(
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
private fun CompanyB2BIndexSection(
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
private fun CompanyB2BIndexBenefitRow(
    title: String,
    description: String,
    icon: ImageVector
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
    }
}

@Composable
private fun CompanyB2BIndexAgreementCard(
    isAccepted: Boolean,
    onAcceptedChange: (Boolean) -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = isAccepted,
                role = Role.Checkbox,
                onValueChange = { checked ->
                    onAcceptedChange(checked)
                }
            ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = isAccepted,
                onCheckedChange = { checked ->
                    onAcceptedChange(checked)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.outline,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Bulbulustur Kullanıcı Sözleşmesi’ni okudum ve kabul ediyorum.",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "B2B Index’e dahil olduğunuzda şirket profiliniz ve uygun kurumsal bilgileriniz platform üzerinde görünür olabilir.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
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