package com.bulbulustur.android.Features.account.notification

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTypography
import com.bulbulustur.android.Ui.theme.BbAlpha

@Composable
fun NotificationListScreen(
    onBackClick: () -> Unit = {}
) {
    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = BbAlpha.DisabledLabel),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.80f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
        )
    )

    val notifications = getDemoNotifications()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Bildirimler",
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
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                NotificationIntroCard()
            }

            items(
                items = notifications,
                key = { item -> "${item.title}-${item.timeText}" }
            ) { item ->
                NotificationCard(
                    item = item
                )
            }
        }
    }
}

@Composable
private fun NotificationIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
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

            Text(
                text = "Sipariş, teklif, kargo ve hesap bildirimlerini buradan takip edebilirsin.",
                modifier = Modifier.weight(1f),
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = item.description,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
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

private fun getDemoNotifications(): List<NotificationItem> {
    return listOf(
        NotificationItem(
            title = "Siparişiniz Hazırlanıyor",
            description = "BB-2026-0001 numaralı siparişiniz satıcı tarafından hazırlanıyor.",
            timeText = "Bugün",
            icon = Icons.Outlined.ShoppingBag,
            isUnread = true
        ),
        NotificationItem(
            title = "Yeni RFQ Cevabı Geldi",
            description = "Toptan fiyat teklifi talebiniz için yeni bir satıcı cevabı var.",
            timeText = "Dün",
            icon = Icons.Outlined.RequestQuote,
            isUnread = true
        ),
        NotificationItem(
            title = "Kargo Durumu Güncellendi",
            description = "Siparişiniz kargo hazırlık aşamasına geçti.",
            timeText = "2 gün önce",
            icon = Icons.Outlined.LocalShipping,
            isUnread = false
        ),
        NotificationItem(
            title = "Güvenlik Önerisi",
            description = "Telefon doğrulamasını tamamlayarak hesabınızı daha güvenli hale getirebilirsiniz.",
            timeText = "Bu hafta",
            icon = Icons.Outlined.Security,
            isUnread = false
        )
    )
}

private data class NotificationItem(
    val title: String,
    val description: String,
    val timeText: String,
    val icon: ImageVector,
    val isUnread: Boolean
)