package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun AccountScreen(
    onSecurityClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onCompanyInfoClick: () -> Unit = {},
    onFollowedStoresClick: () -> Unit = {},
    onQuotationRequestsClick: () -> Unit = {},
    onOrdersClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {},
    onReviewsClick: () -> Unit = {},
    onCouponsClick: () -> Unit = {},
    onRequestsClick: () -> Unit = {},
    onSubscriptionsClick: () -> Unit = {},
    onBankAccountsClick: () -> Unit = {},
    onCommunicationPreferencesClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    AccountHomeScreen(
        onSecurityClick = onSecurityClick,
        onAddressClick = onAddressClick,
        onNotificationClick = onNotificationClick,
        onCompanyInfoClick = onCompanyInfoClick,
        onFollowedStoresClick = onFollowedStoresClick,
        onQuotationRequestsClick = onQuotationRequestsClick,
        onOrdersClick = onOrdersClick,
        onFavoritesClick = onFavoritesClick,
        onReviewsClick = onReviewsClick,
        onCouponsClick = onCouponsClick,
        onRequestsClick = onRequestsClick,
        onSubscriptionsClick = onSubscriptionsClick,
        onBankAccountsClick = onBankAccountsClick,
        onCommunicationPreferencesClick = onCommunicationPreferencesClick,
        onSupportClick = onSupportClick,
        onLogoutClick = onLogoutClick
    )
}

@Composable
private fun AccountHomeScreen(
    onSecurityClick: () -> Unit,
    onAddressClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onCompanyInfoClick: () -> Unit,
    onFollowedStoresClick: () -> Unit,
    onQuotationRequestsClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onReviewsClick: () -> Unit,
    onCouponsClick: () -> Unit,
    onRequestsClick: () -> Unit,
    onSubscriptionsClick: () -> Unit,
    onBankAccountsClick: () -> Unit,
    onCommunicationPreferencesClick: () -> Unit,
    onSupportClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
    ) {
        item {
            AccountHeaderCard()
        }

        item {
            AccountSummaryRow()
        }

        item {
            AccountInfoCard()
        }

        item {
            AccountMenuSection(
                title = "Kişisel",
                items = listOf(
                    AccountMenuItem(
                        title = "Hesap ve Güvenlik",
                        subtitle = "Profil, şifre ve güvenlik ayarlarını yönet",
                        icon = Icons.Outlined.Lock,
                        onClick = onSecurityClick
                    ),
                    AccountMenuItem(
                        title = "Adreslerim",
                        subtitle = "Teslimat ve fatura adreslerini düzenle",
                        icon = Icons.Outlined.LocationOn,
                        onClick = onAddressClick
                    ),
                    AccountMenuItem(
                        title = "Bildirimler",
                        subtitle = "Kampanya ve sistem bildirimlerini yönet",
                        icon = Icons.Outlined.Notifications,
                        onClick = onNotificationClick
                    ),
                    AccountMenuItem(
                        title = "Dil ve Tema",
                        subtitle = "Uygulama dili ve görünüm tercihleri",
                        icon = Icons.Outlined.Language,
                        onClick = onCommunicationPreferencesClick
                    )
                )
            )
        }

        item {
            AccountMenuSection(
                title = "Ticari",
                items = listOf(
                    AccountMenuItem(
                        title = "Şirket Bilgileri",
                        subtitle = "Firma ve ticari hesap bilgilerini yönet",
                        icon = Icons.Outlined.Business,
                        onClick = onCompanyInfoClick
                    ),
                    AccountMenuItem(
                        title = "Takip Ettiğim Mağazalar",
                        subtitle = "Takip ettiğin mağaza ve firmaları görüntüle",
                        icon = Icons.Outlined.Store,
                        onClick = onFollowedStoresClick
                    ),
                    AccountMenuItem(
                        title = "Fiyat Teklifi İstekleri",
                        subtitle = "Toptan teklif ve RFQ süreçlerini takip et",
                        icon = Icons.Outlined.RequestQuote,
                        onClick = onQuotationRequestsClick
                    )
                )
            )
        }

        item {
            AccountMenuSection(
                title = "Alışveriş",
                items = listOf(
                    AccountMenuItem(
                        title = "Siparişlerim",
                        subtitle = "Geçmiş ve aktif siparişlerini görüntüle",
                        icon = Icons.Outlined.ReceiptLong,
                        onClick = onOrdersClick
                    ),
                    AccountMenuItem(
                        title = "Favorilerim",
                        subtitle = "Perakende ve toptan favorilerini yönet",
                        icon = Icons.Outlined.FavoriteBorder,
                        onClick = onFavoritesClick
                    ),
                    AccountMenuItem(
                        title = "Değerlendirmelerim",
                        subtitle = "Yorum ve ürün değerlendirmelerini görüntüle",
                        icon = Icons.Outlined.RateReview,
                        onClick = onReviewsClick
                    ),
                    AccountMenuItem(
                        title = "Kuponlarım",
                        subtitle = "Tanımlı kampanya ve kuponlarını incele",
                        icon = Icons.Outlined.ConfirmationNumber,
                        onClick = onCouponsClick
                    ),
                    AccountMenuItem(
                        title = "Taleplerim",
                        subtitle = "İade, destek ve işlem taleplerini takip et",
                        icon = Icons.Outlined.Assignment,
                        onClick = onRequestsClick
                    )
                )
            )
        }

        item {
            AccountMenuSection(
                title = "Finansal",
                items = listOf(
                    AccountMenuItem(
                        title = "Abonelikler",
                        subtitle = "Paket ve üyelik süreçlerini görüntüle",
                        icon = Icons.Outlined.Subscriptions,
                        onClick = onSubscriptionsClick
                    ),
                    AccountMenuItem(
                        title = "Banka Hesaplarım",
                        subtitle = "IBAN ve banka hesap bilgilerini yönet",
                        icon = Icons.Outlined.AccountBalance,
                        onClick = onBankAccountsClick
                    )
                )
            )
        }

        item {
            AccountMenuSection(
                title = "Destek",
                items = listOf(
                    AccountMenuItem(
                        title = "Yardım Merkezi",
                        subtitle = "Sık sorulan sorular ve destek içerikleri",
                        icon = Icons.Outlined.SupportAgent,
                        onClick = onSupportClick
                    ),
                    AccountMenuItem(
                        title = "Tema Tercihi",
                        subtitle = "Light, navy veya dark görünüm",
                        icon = Icons.Outlined.Palette,
                        onClick = onCommunicationPreferencesClick
                    ),
                    AccountMenuItem(
                        title = "Ayarlar",
                        subtitle = "Hesap tercihleri ve uygulama ayarları",
                        icon = Icons.Outlined.Settings,
                        onClick = onCommunicationPreferencesClick
                    )
                )
            )
        }

        item {
            AccountLogoutButton(
                onClick = onLogoutClick
            )
        }

        item {
            Spacer(modifier = Modifier.height(BbSpacing.md))
        }
    }
}

@Composable
private fun AccountHeaderCard() {
    BbCard(
        padding = BbCardPadding.Medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = BbColors.PrimarySoft
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = BbColors.Primary,
                    modifier = Modifier.padding(BbSpacing.md)
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = BbSpacing.md)
                    .weight(1f)
            ) {
                Text(
                    text = "Murat Erkan",
                    style = BbTypography.titleMedium,
                    color = BbColors.TextStrong
                )

                Spacer(modifier = Modifier.height(BbSpacing.xs))

                Text(
                    text = "Bulbulustur hesabın hazır",
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun AccountSummaryRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        AccountSummaryCard(
            title = "Sipariş",
            value = "0",
            modifier = Modifier.weight(1f)
        )

        AccountSummaryCard(
            title = "Favori",
            value = "0",
            modifier = Modifier.weight(1f)
        )

        AccountSummaryCard(
            title = "Teklif",
            value = "2",
            modifier = Modifier.weight(1f)
        )

        AccountSummaryCard(
            title = "Adres",
            value = "1",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AccountSummaryCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    BbCard(
        modifier = modifier,
        padding = BbCardPadding.Small
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            Spacer(modifier = Modifier.height(BbSpacing.xs))

            Text(
                text = title,
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun AccountInfoCard() {
    BbCard(
        padding = BbCardPadding.Medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = BbColors.PrimarySoft
            ) {
                Icon(
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = null,
                    tint = BbColors.Primary,
                    modifier = Modifier.padding(BbSpacing.sm)
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = BbSpacing.md)
                    .weight(1f)
            ) {
                Text(
                    text = "Ticaret merkezini buradan yönet",
                    style = BbTypography.titleSmall,
                    color = BbColors.TextStrong
                )

                Spacer(modifier = Modifier.height(BbSpacing.xs))

                Text(
                    text = "Siparişlerini, tekliflerini, favorilerini ve hesap ayarlarını tek yerden takip et.",
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun AccountMenuSection(
    title: String,
    items: List<AccountMenuItem>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        Text(
            text = title,
            style = BbTypography.labelMedium,
            color = BbColors.TextMuted,
            modifier = Modifier.padding(horizontal = BbSpacing.xs)
        )

        BbCard(
            padding = BbCardPadding.None
        ) {
            Column {
                items.forEachIndexed { index, item ->
                    AccountMenuRow(
                        item = item
                    )

                    if (index < items.lastIndex) {
                        AccountMenuDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun AccountMenuRow(
    item: AccountMenuItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                item.onClick()
            }
            .padding(BbSpacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            color = BbColors.SurfaceMuted
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = BbColors.Primary,
                modifier = Modifier.padding(BbSpacing.sm)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = BbSpacing.md)
                .weight(1f)
        ) {
            Text(
                text = item.title,
                style = BbTypography.bodyMedium,
                color = BbColors.TextStrong
            )

            if (item.subtitle.isNotBlank()) {
                Spacer(modifier = Modifier.height(BbSpacing.xs))

                Text(
                    text = item.subtitle,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = BbColors.TextMuted
        )
    }
}

@Composable
private fun AccountMenuDivider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(BbSpacing.xs)
    )
}

@Composable
private fun AccountLogoutButton(
    onClick: () -> Unit
) {
    BbCard(
        padding = BbCardPadding.None
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = BbColors.SurfaceMuted
            ) {
                Icon(
                    imageVector = Icons.Outlined.Logout,
                    contentDescription = null,
                    tint = BbColors.TextMuted,
                    modifier = Modifier.padding(BbSpacing.sm)
                )
            }

            Text(
                text = "Çıkış Yap",
                style = BbTypography.bodyMedium,
                color = BbColors.TextStrong,
                modifier = Modifier
                    .padding(start = BbSpacing.md)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted
            )
        }
    }
}

private data class AccountMenuItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)