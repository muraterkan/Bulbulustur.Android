package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CloudDone
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Storage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.StatusComponentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.StatusIncidentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.StatusMaintenanceDTO
import com.bulbulustur.android.businesslayer.Core.DTO.StatusOverviewDTO
import java.util.Locale

@Composable
fun SystemStatusScreen(
    overview: StatusOverviewDTO?,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onOpenStatusPageClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Sistem Durumu",
                subtitle = "Bulbulustur servislerinin güncel çalışma durumu.",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && overview == null -> {
                SystemStatusLoadingContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            overview == null -> {
                SystemStatusErrorContent(
                    message = "Sistem durumu şu anda alınamıyor. Lütfen biraz sonra tekrar deneyin.",
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            else -> {
                SystemStatusContent(
                    overview = overview,
                    onOpenStatusPageClick = onOpenStatusPageClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun SystemStatusContent(
    overview: StatusOverviewDTO,
    onOpenStatusPageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .navigationBarsPadding(),
        contentPadding = PaddingValues(
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.PageTopCompact,
            end = BBSpacing.PageHorizontal,
            bottom = BBSpacing.PageBottom
        ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
    ) {
        item {
            SystemStatusHeroCard(overview = overview)
        }

        item {
            SystemStatusComponentCard(components = overview.Components)
        }

        item {
            SystemStatusIncidentCard(
                title = "Aktif Olaylar",
                subtitle = "Şu anda kullanıcıları etkileyen olaylar.",
                emptyText = "Aktif olay bulunmuyor.",
                incidents = overview.ActiveIncidents
            )
        }

        item {
            SystemStatusMaintenanceCard(
                maintenances = overview.ScheduledMaintenances
            )
        }

        item {
            SystemStatusIncidentCard(
                title = "Son Olaylar",
                subtitle = "Yakın dönemde kapatılmış operasyon kayıtları.",
                emptyText = "Yakın dönemde olay kaydı bulunmuyor.",
                incidents = overview.HistoryIncidents.take(3)
            )
        }

        item {
            BbButton(
                text = "Detaylı Durum Sayfasını Aç",
                onClick = onOpenStatusPageClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun SystemStatusHeroCard(overview: StatusOverviewDTO) {
    val statusColors = systemStatusColors(overview.OverallState)

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SystemStatusIconBox(
                backgroundColor = statusColors.Background,
                contentColor = statusColors.Content
            ) {
                Icon(
                    imageVector = Icons.Outlined.CloudDone,
                    contentDescription = null,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = overview.OverallStateText.ifBlank {
                        "Sistem Durumu Bilinmiyor"
                    },
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Son 90 gün erişilebilirlik: %${formatPercentage(overview.Last90DaysUptimePercentage)}",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Son kontrol: ${formatStatusDate(overview.LastCheckedDate)}",
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SystemStatusComponentCard(components: List<StatusComponentDTO>) {
    SystemStatusSectionCard(
        title = "Servis Bileşenleri",
        subtitle = "Temel Bulbulustur servislerinin operasyon durumu."
    ) {
        if (components.isEmpty()) {
            SystemStatusEmptyRow(
                text = "Servis bileşeni bulunmuyor.",
                icon = Icons.Outlined.Storage
            )
        } else {
            components.forEachIndexed { index, component ->
                SystemStatusComponentRow(component = component)

                if (index != components.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun SystemStatusComponentRow(component: StatusComponentDTO) {
    val statusColors = systemStatusColors(component.CurrentStateKey)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SystemStatusIconBox(
            backgroundColor = statusColors.Background,
            contentColor = statusColors.Content
        ) {
            Icon(
                imageVector = Icons.Outlined.Storage,
                contentDescription = null,
                modifier = Modifier.size(BBIcon.Section)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = component.PublicName.ifBlank {
                    component.ComponentKey
                },
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (component.Description.isNotBlank()) {
                Text(
                    text = component.Description,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "90 gün: %${formatPercentage(component.Last90DaysUptimePercentage)}",
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        SystemStatusBadge(
            text = component.CurrentStateName.ifBlank {
                "Bilinmiyor"
            },
            backgroundColor = statusColors.Background,
            contentColor = statusColors.Content
        )
    }
}

@Composable
private fun SystemStatusIncidentCard(
    title: String,
    subtitle: String,
    emptyText: String,
    incidents: List<StatusIncidentDTO>
) {
    SystemStatusSectionCard(
        title = title,
        subtitle = subtitle
    ) {
        if (incidents.isEmpty()) {
            SystemStatusEmptyRow(
                text = emptyText,
                icon = Icons.Outlined.CheckCircle
            )
        } else {
            incidents.forEachIndexed { index, incident ->
                SystemStatusIncidentRow(incident = incident)

                if (index != incidents.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun SystemStatusIncidentRow(incident: StatusIncidentDTO) {
    val statusText = incident.IncidentStateName
        ?.takeIf { it.isNotBlank() }
        ?: incident.SeverityName
            ?.takeIf { it.isNotBlank() }
        ?: "Bilinmiyor"

    val statusColors = systemStatusColors(statusText)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        SystemStatusIconBox(
            backgroundColor = statusColors.Background,
            contentColor = statusColors.Content
        ) {
            Icon(
                imageVector = Icons.Outlined.History,
                contentDescription = null,
                modifier = Modifier.size(BBIcon.Section)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = incident.Title.ifBlank {
                    "Durum Kaydı"
                },
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (incident.Summary.isNotBlank()) {
                Text(
                    text = incident.Summary,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = formatStatusDate(incident.StartedDate),
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        SystemStatusBadge(
            text = statusText.ifBlank {
                "Bilgi"
            },
            backgroundColor = statusColors.Background,
            contentColor = statusColors.Content
        )
    }
}

@Composable
private fun SystemStatusMaintenanceCard(
    maintenances: List<StatusMaintenanceDTO>
) {
    SystemStatusSectionCard(
        title = "Planlı Bakım",
        subtitle = "Yaklaşan bakım ve sürüm geçişleri."
    ) {
        if (maintenances.isEmpty()) {
            SystemStatusEmptyRow(
                text = "Planlı bakım bulunmuyor.",
                icon = Icons.Outlined.Schedule
            )
        } else {
            maintenances.forEachIndexed { index, maintenance ->
                SystemStatusMaintenanceRow(
                    maintenance = maintenance
                )

                if (index != maintenances.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun SystemStatusMaintenanceRow(
    maintenance: StatusMaintenanceDTO
) {
    val statusColors = systemStatusColors(
        maintenance.MaintenanceStateName
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        SystemStatusIconBox(
            backgroundColor = statusColors.Background,
            contentColor = statusColors.Content
        ) {
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = null,
                modifier = Modifier.size(BBIcon.Section)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = maintenance.Title.ifBlank {
                    "Planlı Bakım"
                },
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (maintenance.Description.isNotBlank()) {
                Text(
                    text = maintenance.Description,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "${formatStatusDate(maintenance.ScheduledStart)} - ${formatStatusDate(maintenance.ScheduledEnd)}",
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        SystemStatusBadge(
            text = maintenance.MaintenanceStateName.ifBlank {
                "Planlandı"
            },
            backgroundColor = statusColors.Background,
            contentColor = statusColors.Content
        )
    }
}

@Composable
private fun SystemStatusSectionCard(
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = subtitle,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            content()
        }
    }
}

@Composable
private fun SystemStatusEmptyRow(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SystemStatusIconBox(
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(BBIcon.Section)
            )
        }

        Text(
            text = text,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SystemStatusIconBox(
    backgroundColor: Color,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BBIcon.ActionBox)
            .clip(BBRadius.MdShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material3.ProvideTextStyle(
                value = BbTypography.bodyMedium
            ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.runtime.CompositionLocalProvider(
                        androidx.compose.material3.LocalContentColor provides contentColor
                    ) {
                        content()
                    }
                }
            }
        }
    }
}

@Composable
private fun SystemStatusBadge(
    text: String,
    backgroundColor: Color,
    contentColor: Color
) {
    Box(
        modifier = Modifier
            .clip(BBRadius.Badge)
            .background(backgroundColor)
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = contentColor
        )
    }
}

@Composable
private fun SystemStatusLoadingContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BBSpacing.PageHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()

        Text(
            text = "Sistem durumu kontrol ediliyor...",
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(
                top = BBSpacing.Space3
            )
        )
    }
}

@Composable
private fun SystemStatusErrorContent(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BBSpacing.PageHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.ErrorOutline,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(BBIcon.ErrorStateIcon)
        )

        Text(
            text = message,
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(
                top = BBSpacing.Space3
            )
        )

        BbButton(
            text = "Tekrar Dene",
            onClick = onRetryClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BBSpacing.Space4),
            variant = BbButtonVariant.Light,
            size = BbButtonSize.Medium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(BBIcon.ButtonIcon)
                )
            }
        )
    }
}

@Composable
private fun systemStatusColors(
    state: String
): SystemStatusColors {
    val normalizedState = state.lowercase(Locale.getDefault())

    return when {
        normalizedState.contains("operational") ||
                normalizedState.contains("çalışıyor") ||
                normalizedState.contains("resolved") ||
                normalizedState.contains("çözüldü") -> {
            SystemStatusColors(
                Background = MaterialTheme.colorScheme.secondaryContainer,
                Content = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        normalizedState.contains("degraded") ||
                normalizedState.contains("maintenance") ||
                normalizedState.contains("bakım") ||
                normalizedState.contains("planlandı") -> {
            SystemStatusColors(
                Background = MaterialTheme.colorScheme.tertiaryContainer,
                Content = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }

        normalizedState.contains("outage") ||
                normalizedState.contains("kesinti") ||
                normalizedState.contains("critical") ||
                normalizedState.contains("major") ||
                normalizedState.contains("hata") -> {
            SystemStatusColors(
                Background = MaterialTheme.colorScheme.errorContainer,
                Content = MaterialTheme.colorScheme.onErrorContainer
            )
        }

        else -> {
            SystemStatusColors(
                Background = MaterialTheme.colorScheme.surfaceVariant,
                Content = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private data class SystemStatusColors(
    val Background: Color,
    val Content: Color
)

private fun formatPercentage(value: Double): String {
    return if (value % 1.0 == 0.0) {
        value.toInt().toString()
    } else {
        String.format(
            Locale.getDefault(),
            "%.2f",
            value
        )
    }
}

private fun formatStatusDate(value: String?): String {
    if (value.isNullOrBlank()) {
        return "Bilinmiyor"
    }

    val normalizedValue = value.replace("T", " ")

    return if (normalizedValue.length >= 16) {
        normalizedValue.take(16)
    } else {
        normalizedValue
    }
}