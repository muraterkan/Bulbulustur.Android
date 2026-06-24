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
import androidx.compose.material.icons.outlined.Api
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CloudDone
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Storage
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun SystemStatusScreen(
    onBackClick: () -> Unit = {},
    onOpenStatusPageClick: () -> Unit = {}
) {
    val components = getDemoSystemStatusComponents()
    val activeIncidents = getDemoActiveIncidents()
    val recentIncidents = getDemoRecentIncidents()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BBColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Sistem Durumu",
                subtitle = "Bulbulustur servislerinin güncel çalışma durumu.",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BBColors.SurfaceMuted)
                .padding(innerPadding)
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
                SystemStatusHeroCard()
            }

            item {
                SystemStatusComponentCard(
                    components = components
                )
            }

            item {
                SystemStatusIncidentCard(
                    title = "Aktif Olaylar",
                    subtitle = "Åu anda kullanıcıları etkileyen olaylar.",
                    emptyText = "Aktif olay bulunmuyor.",
                    incidents = activeIncidents
                )
            }

            item {
                SystemStatusMaintenanceCard()
            }

            item {
                SystemStatusIncidentCard(
                    title = "Son Olaylar",
                    subtitle = "Yakın dönemde kapatılmış operasyon kayıtları.",
                    emptyText = "Yakın dönemde olay kaydı bulunmuyor.",
                    incidents = recentIncidents
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
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun SystemStatusHeroCard() {
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
                icon = Icons.Outlined.CloudDone,
                backgroundColor = BBColors.Green.Green50,
                iconColor = BBColors.Green.Green600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Tüm Sistemler Ã‡alışıyor",
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "API, web platformu, ödeme, bildirim ve durum servisleri normal çalışıyor.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Son kontrol: Bugün 14:32",
                    style = BbTypography.labelSmall,
                    color = BBColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun SystemStatusComponentCard(
    components: List<SystemStatusComponentUiModel>
) {
    SystemStatusSectionCard(
        title = "Servis Bileşenleri",
        subtitle = "Temel Bulbulustur servislerinin operasyon durumu."
    ) {
        components.forEachIndexed { index, component ->
            SystemStatusComponentRow(
                component = component
            )

            if (index != components.lastIndex) {
                HorizontalDivider(
                    color = BBColors.Border
                )
            }
        }
    }
}

@Composable
private fun SystemStatusIncidentCard(
    title: String,
    subtitle: String,
    emptyText: String,
    incidents: List<SystemStatusIncidentUiModel>
) {
    SystemStatusSectionCard(
        title = title,
        subtitle = subtitle
    ) {
        if (incidents.isEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPaddingCompact),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SystemStatusIconBox(
                    icon = Icons.Outlined.CheckCircle,
                    backgroundColor = BBColors.Green.Green50,
                    iconColor = BBColors.Green.Green600
                )

                Text(
                    text = emptyText,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            incidents.forEachIndexed { index, incident ->
                SystemStatusIncidentRow(
                    incident = incident
                )

                if (index != incidents.lastIndex) {
                    HorizontalDivider(
                        color = BBColors.Border
                    )
                }
            }
        }
    }
}

@Composable
private fun SystemStatusMaintenanceCard() {
    SystemStatusSectionCard(
        title = "Planlı Bakım",
        subtitle = "Yaklaşan bakım ve sürüm geçişleri."
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.CardPaddingCompact),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SystemStatusIconBox(
                icon = Icons.Outlined.Schedule,
                backgroundColor = BBColors.Blue.Blue50,
                iconColor = BBColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Planlı bakım bulunmuyor",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Yaklaşan bakım olduĞunda burada gösterilecek.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SystemStatusComponentRow(
    component: SystemStatusComponentUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SystemStatusIconBox(
            icon = component.icon,
            backgroundColor = component.status.backgroundColor,
            iconColor = component.status.contentColor
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = component.title,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = component.description,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        SystemStatusBadge(
            text = component.status.label,
            backgroundColor = component.status.backgroundColor,
            contentColor = component.status.contentColor
        )
    }
}

@Composable
private fun SystemStatusIncidentRow(
    incident: SystemStatusIncidentUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        SystemStatusIconBox(
            icon = incident.icon,
            backgroundColor = incident.status.backgroundColor,
            iconColor = incident.status.contentColor
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = incident.title,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = incident.description,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = incident.dateText,
                style = BbTypography.labelSmall,
                color = BBColors.TextMuted
            )
        }

        SystemStatusBadge(
            text = incident.status.label,
            backgroundColor = incident.status.backgroundColor,
            contentColor = incident.status.contentColor
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
                color = BBColors.Border
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                content()
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
            .background(
                color = backgroundColor,
                shape = BBRadius.Badge
            )
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
private fun SystemStatusIconBox(
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color
) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Ui)
        )
    }
}

private fun getDemoSystemStatusComponents(): List<SystemStatusComponentUiModel> {
    return listOf(
        SystemStatusComponentUiModel(
            title = "Bulbulustur API",
            description = "Mobil uygulama ve platform servisleri.",
            icon = Icons.Outlined.Api,
            status = SystemStatusLevel.Operational
        ),
        SystemStatusComponentUiModel(
            title = "Web Platformu",
            description = "bulbulustur.com ve ticaret arayüzleri.",
            icon = Icons.Outlined.Language,
            status = SystemStatusLevel.Operational
        ),
        SystemStatusComponentUiModel(
            title = "Ã–deme Servisleri",
            description = "Sepet, ödeme ve sipariş operasyonları.",
            icon = Icons.Outlined.Payments,
            status = SystemStatusLevel.Operational
        ),
        SystemStatusComponentUiModel(
            title = "Bildirimler",
            description = "E-posta, SMS ve uygulama bildirimleri.",
            icon = Icons.Outlined.Notifications,
            status = SystemStatusLevel.Operational
        ),
        SystemStatusComponentUiModel(
            title = "Veri ve Dosya Servisleri",
            description = "Veritabanı, medya ve doküman servisleri.",
            icon = Icons.Outlined.Storage,
            status = SystemStatusLevel.Operational
        ),
        SystemStatusComponentUiModel(
            title = "Status Platformu",
            description = "status.bulbulustur.com izleme servisi.",
            icon = Icons.Outlined.Dns,
            status = SystemStatusLevel.Operational
        )
    )
}

private fun getDemoActiveIncidents(): List<SystemStatusIncidentUiModel> {
    return emptyList()
}

private fun getDemoRecentIncidents(): List<SystemStatusIncidentUiModel> {
    return listOf(
        SystemStatusIncidentUiModel(
            title = "Bildirim kuyruĞu gecikmesi",
            description = "Kısa süreli e-posta gecikmesi izlendi ve kapatıldı.",
            dateText = "22 Haziran 2026",
            icon = Icons.Outlined.History,
            status = SystemStatusLevel.Resolved
        ),
        SystemStatusIncidentUiModel(
            title = "Web arayüz yavaşlaması",
            description = "Bölgesel aĞ gecikmesi sonrası servis normal duruma döndü.",
            dateText = "18 Haziran 2026",
            icon = Icons.Outlined.Info,
            status = SystemStatusLevel.Resolved
        )
    )
}

private data class SystemStatusComponentUiModel(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val status: SystemStatusLevel
)

private data class SystemStatusIncidentUiModel(
    val title: String,
    val description: String,
    val dateText: String,
    val icon: ImageVector,
    val status: SystemStatusLevel
)

private enum class SystemStatusLevel(
    val label: String,
    val backgroundColor: Color,
    val contentColor: Color
) {
    Operational(
        label = "Ã‡alışıyor",
        backgroundColor = BBColors.Green.Green50,
        contentColor = BBColors.Green.Green600
    ),
    Degraded(
        label = "Yavaş",
        backgroundColor = BBColors.Yellow.Yellow50,
        contentColor = BBColors.Yellow.Yellow800
    ),
    PartialOutage(
        label = "Kısmi Kesinti",
        backgroundColor = BBColors.Orange.Orange50,
        contentColor = BBColors.Orange.Orange600
    ),
    MajorOutage(
        label = "Kesinti",
        backgroundColor = BBColors.Red.Red50,
        contentColor = BBColors.Red.Red500
    ),
    Maintenance(
        label = "Bakım",
        backgroundColor = BBColors.Blue.Blue50,
        contentColor = BBColors.Blue.Blue600
    ),
    Resolved(
        label = "Ã‡özüldü",
        backgroundColor = BBColors.Green.Green50,
        contentColor = BBColors.Green.Green600
    )
}
