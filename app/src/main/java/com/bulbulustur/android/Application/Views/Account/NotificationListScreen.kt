package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

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
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun NotificationListScreen(
    onBackClick: () -> Unit = {}
) {

    val notifications = getDemoNotifications()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "9bc9cd06-7971-4d1c-9082-85a6bdaf77c2", fallback = "Bildirimler"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.XlShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "50040e90-e2d9-4d76-89f1-4b9969712653", fallback = "Sipariş, teklif, kargo ve hesap bildirimlerini buradan takip edebilirsin."),
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = if (item.isUnread) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        },
                        shape = BBRadius.PillShape
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
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
            title = BBLocalization.Current.Get(key = "617cb03c-6e55-40b4-a433-8a48be65aa90", fallback = "Siparişiniz Hazırlanıyor"),
            description = "BB-2026-0001 numaralı siparişiniz satıcı tarafından hazırlanıyor.",
            timeText = BBLocalization.Current.Get(key = "5df01635-64c2-45a2-95e2-8b37bae9b423", fallback = "Bugün"),
            icon = Icons.Outlined.ShoppingBag,
            isUnread = true
        ),
        NotificationItem(
            title = BBLocalization.Current.Get(key = "d957d27e-30fe-4662-b5e3-56314e6b61bb", fallback = "Yeni RFQ Cevabı Geldi"),
            description = BBLocalization.Current.Get(key = "f5660c27-7712-45f1-9f35-d15abf68b256", fallback = "Toptan fiyat teklifi talebiniz için yeni bir satıcı cevabı var."),
            timeText = BBLocalization.Current.Get(key = "b9a3d24b-8625-4ac6-937a-52a97cc4f96a", fallback = "Dün"),
            icon = Icons.Outlined.RequestQuote,
            isUnread = true
        ),
        NotificationItem(
            title = BBLocalization.Current.Get(key = "cfbba0e2-2b0c-4eb8-90a4-5c8fbafc192c", fallback = "Kargo Durumu Güncellendi"),
            description = BBLocalization.Current.Get(key = "387d0d3c-3f72-4a51-8b00-c5debce88159", fallback = "Siparişiniz kargo hazırlık aşamasına geçti."),
            timeText = "2 gün önce",
            icon = Icons.Outlined.LocalShipping,
            isUnread = false
        ),
        NotificationItem(
            title = BBLocalization.Current.Get(key = "662354a4-8349-47a9-a519-2b9f6accc5a2", fallback = "Güvenlik Önerisi"),
            description = BBLocalization.Current.Get(key = "3f346546-5d93-4771-99d9-40bb7e327851", fallback = "Telefon doğrulamasını tamamlayarak hesabınızı daha güvenli hale getirebilirsiniz."),
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


