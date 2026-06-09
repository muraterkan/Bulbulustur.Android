package com.bulbulustur.android.features.account.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
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
fun NotificationListScreen(
    onBackClick: () -> Unit = {}
) {
    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.80f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
        )
    )

    val notifications = listOf(
        NotificationItem(
            title = "Siparişiniz hazırlanıyor",
            description = "BB-2026-0001 numaralı siparişiniz satıcı tarafından hazırlanıyor.",
            timeText = "Bugün",
            icon = Icons.Outlined.ShoppingBag,
            isUnread = true
        ),
        NotificationItem(
            title = "Yeni RFQ cevabı geldi",
            description = "Toptan fiyat teklifi talebiniz için yeni bir satıcı cevabı var.",
            timeText = "Dün",
            icon = Icons.Outlined.RequestQuote,
            isUnread = true
        ),
        NotificationItem(
            title = "Kargo durumu güncellendi",
            description = "Siparişiniz kargo hazırlık aşamasına geçti.",
            timeText = "2 gün önce",
            icon = Icons.Outlined.LocalShipping,
            isUnread = false
        ),
        NotificationItem(
            title = "Güvenlik önerisi",
            description = "Telefon doğrulamasını tamamlayarak hesabınızı daha güvenli hale getirebilirsiniz.",
            timeText = "Bu hafta",
            icon = Icons.Outlined.Security,
            isUnread = false
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
            NotificationHeaderCard(
                onBackClick = onBackClick
            )
        }

        items(
            count = notifications.size
        ) { index ->
            NotificationCard(
                item = notifications[index]
            )
        }
    }
}

@Composable
private fun NotificationHeaderCard(
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
                text = "Hesabıma Dön",
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
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BbIcon.Section)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Bildirimler",
                        style = BbTypography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Sipariş, teklif, kargo ve hesap bildirimlerini buradan takip edin.",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun NotificationCard(
    item: NotificationItem
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = if (item.isUnread) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        },
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = if (item.isUnread) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    modifier = Modifier.size(BbIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = item.title,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.description,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = item.timeText,
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private data class NotificationItem(
    val title: String,
    val description: String,
    val timeText: String,
    val icon: ImageVector,
    val isUnread: Boolean
)