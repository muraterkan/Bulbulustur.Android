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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
                title = BBLocalization.Current.Get(key = "ace0031a-6e70-445a-b319-d5a20536e669", fallback = "Sistem Durumu"),
                subtitle = BBLocalization.Current.Get(key = "0ecd4997-38a2-415a-8747-39cd26c84baa", fallback = "Bulbulustur servislerinin güncel çalışma durumu."),
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
                    message = BBLocalization.Current.Get(key = "7876417b-370f-4747-8e4e-268a7356546c", fallback = "Sistem durumu şu anda alınamıyor. Lütfen biraz sonra tekrar deneyin."),
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
                title = BBLocalization.Current.Get(key = "479c3d5b-b12e-4619-b00c-3125547e6fe0", fallback = "Aktif Olaylar"),
                subtitle = BBLocalization.Current.Get(key = "bea8c8c3-547e-434b-bebd-60e878bae104", fallback = "Şu anda kullanıcıları etkileyen olaylar."),
                emptyText = BBLocalization.Current.Get(key = "263f9ab8-6c85-45ab-8b87-94e0805f1a6b", fallback = "Aktif olay bulunmuyor."),
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
                title = BBLocalization.Current.Get(key = "a236800c-25c4-4377-9a7b-688e6f688834", fallback = "Son Olaylar"),
                subtitle = BBLocalization.Current.Get(key = "cc1223c8-2ffa-4d21-bfb0-5c2063eb45ea", fallback = "Yakın dönemde kapatılmış operasyon kayıtları."),
                emptyText = BBLocalization.Current.Get(key = "1b98d9bd-7ef7-403b-9857-8b6495bccc7c", fallback = "Yakın dönemde olay kaydı bulunmuyor."),
                incidents = overview.HistoryIncidents.take(3)
            )
        }

        item {
            BbButton(
                text = BBLocalization.Current.Get(key = "1d59e1e3-6fd3-4ee1-aab7-19c6d1f6c30e", fallback = "Detaylı Durum Sayfasını Aç"),
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
                        BBLocalization.Current.Get(key = "81ab9591-e8e0-45b1-9b4e-78b102769dbf", fallback = "Sistem Durumu Bilinmiyor")
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
        title = BBLocalization.Current.Get(key = "128c1b4d-49d9-44e1-b7c6-e20b9639db14", fallback = "Servis Bileşenleri"),
        subtitle = BBLocalization.Current.Get(key = "4a587ece-e9bc-49cd-8605-b9d462251cb4", fallback = "Temel Bulbulustur servislerinin operasyon durumu.")
    ) {
        if (components.isEmpty()) {
            SystemStatusEmptyRow(
                text = BBLocalization.Current.Get(key = "13c9fecf-c151-41fd-89c5-538a10657795", fallback = "Servis bileşeni bulunmuyor."),
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
                    BBLocalization.Current.Get(key = "5ab61b6d-8d0f-44ce-a619-bc9f7caf2328", fallback = "Durum Kaydı")
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
        title = BBLocalization.Current.Get(key = "d2efe553-3848-474f-8467-b12ca9fc186a", fallback = "Planlı Bakım"),
        subtitle = BBLocalization.Current.Get(key = "6709eb80-5a24-43c9-b32e-c94a8dd8e28f", fallback = "Yaklaşan bakım ve sürüm geçişleri.")
    ) {
        if (maintenances.isEmpty()) {
            SystemStatusEmptyRow(
                text = BBLocalization.Current.Get(key = "13073d42-3401-44da-a23f-ec379b9944c6", fallback = "Planlı bakım bulunmuyor."),
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
                    BBLocalization.Current.Get(key = "d2efe553-3848-474f-8467-b12ca9fc186a", fallback = "Planlı Bakım")
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
                BBLocalization.Current.Get(key = "fc041a2b-1878-49b7-87c2-e9c3315e0df2", fallback = "Planlandı")
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
            text = BBLocalization.Current.Get(key = "15bced5a-fca8-462f-a307-fc7a09356deb", fallback = "Sistem durumu kontrol ediliyor..."),
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
            text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"),
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
                normalizedState.contains(BBLocalization.Current.Get(key = "44569caa-1902-4a11-a28a-0e12a00168e6", fallback = "çalışıyor")) ||
                normalizedState.contains("resolved") ||
                normalizedState.contains(BBLocalization.Current.Get(key = "2e6dd7be-5cda-4e00-a3aa-c1e9a13ca394", fallback = "çözüldü")) -> {
            SystemStatusColors(
                Background = MaterialTheme.colorScheme.secondaryContainer,
                Content = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        normalizedState.contains("degraded") ||
                normalizedState.contains("maintenance") ||
                normalizedState.contains(BBLocalization.Current.Get(key = "ca4c8560-c7d5-4e11-a156-f4b19ca58e3d", fallback = "bakım")) ||
                normalizedState.contains(BBLocalization.Current.Get(key = "fc041a2b-1878-49b7-87c2-e9c3315e0df2", fallback = "planlandı")) -> {
            SystemStatusColors(
                Background = MaterialTheme.colorScheme.tertiaryContainer,
                Content = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }

        normalizedState.contains("outage") ||
                normalizedState.contains("kesinti") ||
                normalizedState.contains("critical") ||
                normalizedState.contains("major") ||
                normalizedState.contains(BBLocalization.Current.Get(key = "30aee7b0-b131-4f63-8659-f78378ac20f3", fallback = "")) -> {
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