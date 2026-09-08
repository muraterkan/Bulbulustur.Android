package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.MemberNotificationDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNotificationTypeDTO

@Composable
fun NotificationListScreen(
    notificationTypes: List<SystemDescNotificationTypeDTO> = emptyList(),
    notifications: List<MemberNotificationDTO> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onNotificationClick: (MemberNotificationDTO) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var selectedNotificationTypeId by remember {
        mutableIntStateOf(0)
    }

    val visibleTypes = remember(notificationTypes) {
        notificationTypes
            .filter {
                it.SystemDescNotificationTypeId > 0 &&
                        it.Content.isNotBlank()
            }
            .distinctBy {
                it.SystemDescNotificationTypeId
            }
    }

    val visibleNotifications = remember(
        notifications,
        selectedNotificationTypeId
    ) {
        notifications.filter { notification ->
            selectedNotificationTypeId == 0 ||
                    notification.NotificationTypeId == selectedNotificationTypeId
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(
                    key = "9bc9cd06-7971-4d1c-9082-85a6bdaf77c2",
                    fallback = "Bildirimler"
                ),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            item {
                Text(
                    text = BBLocalization.Current.Get(
                        key = "50040e90-e2d9-4d76-89f1-4b9969712653",
                        fallback = "Sipariş, teklif, kargo ve hesap bildirimlerini buradan takip edebilirsin."
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    contentPadding = PaddingValues(
                        end = BBSpacing.Space2
                    )
                ) {
                    item {
                        NotificationTypeChip(
                            text = BBLocalization.Current.Get(
                                key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced",
                                fallback = "Tümü"
                            ),
                            selected = selectedNotificationTypeId == 0,
                            onClick = {
                                selectedNotificationTypeId = 0
                            }
                        )
                    }

                    items(
                        items = visibleTypes,
                        key = {
                            it.SystemDescNotificationTypeId
                        }
                    ) { type ->
                        NotificationTypeChip(
                            text = type.Content,
                            selected =
                                selectedNotificationTypeId ==
                                        type.SystemDescNotificationTypeId,
                            onClick = {
                                selectedNotificationTypeId =
                                    type.SystemDescNotificationTypeId
                            }
                        )
                    }
                }
            }

            when {
                isLoading && notifications.isEmpty() -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = BBSpacing.Space6),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(28.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }

                !errorMessage.isNullOrBlank() &&
                        notifications.isEmpty() -> {
                    item {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.errorContainer
                        ) {
                            Text(
                                modifier = Modifier.padding(BBSpacing.Space4),
                                text = errorMessage,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                    }
                }

                visibleNotifications.isEmpty() -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = BBSpacing.Space6),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }

                else -> {
                    items(
                        items = visibleNotifications,
                        key = {
                            it.MemberNotificationId
                        }
                    ) { notification ->
                        MemberNotificationRow(
                            notification = notification,
                            notificationType =
                                visibleTypes.firstOrNull {
                                    it.SystemDescNotificationTypeId ==
                                            notification.NotificationTypeId
                                },
                            onClick = {
                                onNotificationClick(notification)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationTypeChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable {
            onClick()
        },
        shape = RoundedCornerShape(999.dp),
        color = if (selected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
        )
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight =
                if (selected) FontWeight.SemiBold
                else FontWeight.Normal,
            color = if (selected) {
                MaterialTheme.colorScheme.onPrimaryContainer
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
    }
}

@Composable
private fun MemberNotificationRow(
    notification: MemberNotificationDTO,
    notificationType: SystemDescNotificationTypeDTO?,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        color = if (notification.IsRead) {
            MaterialTheme.colorScheme.surface
        } else {
            MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.28f
            )
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (notification.IsRead) {
                MaterialTheme.colorScheme.outlineVariant
            } else {
                MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.32f
                )
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space4),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .padding(top = BBSpacing.Space1)
                    .size(9.dp)
                    .background(
                        color = if (notification.IsRead) {
                            MaterialTheme.colorScheme.outlineVariant
                        } else {
                            MaterialTheme.colorScheme.primary
                        },
                        shape = CircleShape
                    )
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                notificationType
                    ?.Content
                    ?.takeIf {
                        it.isNotBlank()
                    }
                    ?.let { typeText ->
                        Text(
                            text = typeText,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                Text(
                    text = notification.Notification,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight =
                        if (notification.IsRead) {
                            FontWeight.Normal
                        } else {
                            FontWeight.SemiBold
                        }
                )

                FormatNotificationDate(
                    notification.InsertedDate
                )
                    .takeIf {
                        it.isNotBlank()
                    }
                    ?.let { dateText ->
                        Text(
                            text = dateText,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
            }
        }
    }
}

private fun FormatNotificationDate(
    value: String
): String {
    if (value.isBlank()) return ""

    return value
        .trim()
        .replace("T", " ")
        .removeSuffix("Z")
        .take(16)
}
