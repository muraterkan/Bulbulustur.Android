package com.bulbulustur.android.features.account

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ProductionQuantityLimits
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Reviews
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.TransitEnterexit
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.Wallet
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
import com.bulbulustur.android.features.retail.components.RetailBottomNavigation
import com.bulbulustur.android.features.retail.components.RetailBottomNavigationItem
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
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
    onSettingsClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onQuestionsClick: () -> Unit = {},
    onUsagePurposeClick: () -> Unit = {},

    // Bottom navigation
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {}
) {
    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.80f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
        )
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        bottomBar = {
            RetailBottomNavigation(
                selectedItem = RetailBottomNavigationItem.Account,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        RetailBottomNavigationItem.Home -> onHomeClick()
                        RetailBottomNavigationItem.Menu -> onMenuClick()
                        RetailBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        RetailBottomNavigationItem.Basket -> onBasketClick()
                        RetailBottomNavigationItem.Account -> Unit
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(pageBackground)
                .statusBarsPadding()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.PageTopCompact
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                AccountHeaderCard(
                    userName = "Murat Erkan",
                    subtitle = "Bulbulustur hesabın hazır",
                    initials = "ME",
                    onProfileClick = onSecurityClick,
                    onNotificationClick = onNotificationClick,
                    onMessagesClick = onMessagesClick,
                    onSettingsClick = onSettingsClick
                )
            }

            item {
                AccountStatsRow(
                    onOrdersClick = onOrdersClick,
                    onFavoritesClick = onFavoritesClick,
                    onQuotationRequestsClick = onQuotationRequestsClick,
                    onAddressClick = onAddressClick
                )
            }

            item {
                AccountMiniActionScroller(
                    onQuotationRequestsClick = onQuotationRequestsClick,
                    onCompanyInfoClick = onCompanyInfoClick,
                    onOrdersClick = onOrdersClick,
                    onBankAccountsClick = onBankAccountsClick
                )
            }

            item {
                AccountUsagePurposeCard(
                    onClick = onUsagePurposeClick
                )
            }

            item {
                AccountCommerceHeroCard(
                    onOrdersClick = onOrdersClick,
                    onQuotationRequestsClick = onQuotationRequestsClick,
                    onBankAccountsClick = onBankAccountsClick
                )
            }

            item {
                AccountMenuSection(
                    title = "Kişisel"
                ) {
                    AccountMenuRow(
                        title = "Hesap ve Güvenlik",
                        description = "Profil, şifre ve güvenlik ayarlarını yönet",
                        icon = Icons.Outlined.Security,
                        onClick = onSecurityClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Adreslerim",
                        description = "Teslimat ve fatura adreslerini düzenle",
                        icon = Icons.Outlined.Home,
                        onClick = onAddressClick
                    )
                }
            }

            item {
                AccountMenuSection(
                    title = "Ticari"
                ) {
                    AccountMenuRow(
                        title = "Şirket Bilgileri",
                        description = "Firma ve ticari hesap bilgilerini yönet",
                        icon = Icons.Outlined.Business,
                        onClick = onCompanyInfoClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Takip Ettiğim Mağazalar",
                        description = "Takip ettiğin mağaza ve firmaları görüntüle",
                        icon = Icons.Outlined.Storefront,
                        onClick = onFollowedStoresClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Fiyat Teklifi İstekleri",
                        description = "Toptan teklif ve RFQ süreçlerini takip et",
                        icon = Icons.Outlined.RequestQuote,
                        onClick = onQuotationRequestsClick
                    )
                }
            }

            item {
                AccountMenuSection(
                    title = "Alışveriş"
                ) {
                    AccountMenuRow(
                        title = "Siparişlerim",
                        description = "Geçmiş ve aktif siparişlerini görüntüle",
                        icon = Icons.Outlined.ProductionQuantityLimits,
                        onClick = onOrdersClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Favorilerim",
                        description = "Perakende ve toptan favorilerini yönet",
                        icon = Icons.Outlined.FavoriteBorder,
                        onClick = onFavoritesClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Soru ve Cevaplarım",
                        description = "Ürün sorularını ve satıcı cevaplarını takip et",
                        icon = Icons.Outlined.QuestionAnswer,
                        onClick = onQuestionsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Değerlendirmelerim",
                        description = "Yorum ve ürün değerlendirmelerini görüntüle",
                        icon = Icons.Outlined.Reviews,
                        onClick = onReviewsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Kuponlarım",
                        description = "Tanımlı kampanya ve kuponlarını incele",
                        icon = Icons.Outlined.LocalOffer,
                        onClick = onCouponsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Taleplerim",
                        description = "İade, destek ve işlem taleplerini takip et",
                        icon = Icons.Outlined.HelpOutline,
                        onClick = onRequestsClick
                    )
                }
            }

            item {
                AccountMenuSection(
                    title = "Finansal"
                ) {
                    AccountMenuRow(
                        title = "Abonelikler",
                        description = "Paket ve üyelik süreçlerini görüntüle",
                        icon = Icons.Outlined.CreditCard,
                        onClick = onSubscriptionsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Banka Hesaplarım",
                        description = "IBAN ve banka hesap bilgilerini yönet",
                        icon = Icons.Outlined.AccountBalance,
                        onClick = onBankAccountsClick
                    )
                }
            }

            item {
                AccountMenuSection(
                    title = "Ayarlar"
                ) {
                    AccountMenuRow(
                        title = "Uygulama Ayarları",
                        description = "Dil, görünüm, bildirim ve uygulama tercihleri",
                        icon = Icons.Outlined.Settings,
                        onClick = onSettingsClick
                    )
                }
            }

            item {
                AccountSupportCard(
                    onSupportClick = onSupportClick
                )
            }

            item {
                AccountLogoutCard(
                    onLogoutClick = onLogoutClick
                )
            }

            item {
                Spacer(
                    modifier = Modifier.size(BbSpacing.Space4)
                )
            }
        }
    }
}

@Composable
private fun AccountHeaderCard(
    userName: String,
    subtitle: String,
    initials: String,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxXl)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BbRadius.XlShape
                    )
                    .clickable {
                        onProfileClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProfileClick()
                    },
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = userName,
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = subtitle,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            HeaderIconButton(
                icon = Icons.Outlined.MailOutline,
                onClick = onMessagesClick
            )

            HeaderIconButton(
                icon = Icons.Outlined.Notifications,
                onClick = onNotificationClick
            )

            HeaderIconButton(
                icon = Icons.Outlined.Settings,
                onClick = onSettingsClick
            )
        }
    }
}

@Composable
private fun HeaderIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxLg)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(BbIcon.SizeLg)
        )
    }
}

@Composable
private fun AccountStatsRow(
    onOrdersClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onQuotationRequestsClick: () -> Unit,
    onAddressClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        AccountStatCard(
            modifier = Modifier.weight(1f),
            value = "0",
            label = "Sipariş",
            icon = Icons.Outlined.ShoppingBag,
            isFeatured = true,
            onClick = onOrdersClick
        )

        AccountStatCard(
            modifier = Modifier.weight(1f),
            value = "0",
            label = "Favori",
            icon = Icons.Outlined.FavoriteBorder,
            isFeatured = false,
            onClick = onFavoritesClick
        )

        AccountStatCard(
            modifier = Modifier.weight(1f),
            value = "2",
            label = "Teklif",
            icon = Icons.Outlined.RequestQuote,
            isFeatured = false,
            onClick = onQuotationRequestsClick
        )

        AccountStatCard(
            modifier = Modifier.weight(1f),
            value = "1",
            label = "Adres",
            icon = Icons.Outlined.Home,
            isFeatured = false,
            onClick = onAddressClick
        )
    }
}

@Composable
private fun AccountStatCard(
    modifier: Modifier,
    value: String,
    label: String,
    icon: ImageVector,
    isFeatured: Boolean,
    onClick: () -> Unit
) {
    val containerColor = if (isFeatured) {
        MaterialTheme.colorScheme.inverseSurface
    } else {
        MaterialTheme.colorScheme.surface
    }

    val contentColor = if (isFeatured) {
        MaterialTheme.colorScheme.inverseOnSurface
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val iconContainerColor = if (isFeatured) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    BbCard(
        modifier = modifier.clickable {
            onClick()
        },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Small
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(containerColor, BbRadius.LgShape)
                .padding(BbSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxSm)
                    .background(
                        color = iconContainerColor,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isFeatured) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    modifier = Modifier.size(BbIcon.SizeSm)
                )
            }

            Text(
                text = value,
                style = BbTypography.titleMedium,
                color = contentColor
            )

            Text(
                text = label,
                style = BbTypography.labelSmall,
                color = if (isFeatured) {
                    MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.75f)
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

@Composable
private fun AccountMiniActionScroller(
    onQuotationRequestsClick: () -> Unit,
    onCompanyInfoClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onBankAccountsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        AccountMiniActionCard(
            title = "RFQ oluştur",
            description = "Toptan teklif al",
            icon = Icons.Outlined.RequestQuote,
            onClick = onQuotationRequestsClick
        )

        AccountMiniActionCard(
            title = "Şirketini tamamla",
            description = "Ticari profilini güçlendir",
            icon = Icons.Outlined.Business,
            onClick = onCompanyInfoClick
        )

        AccountMiniActionCard(
            title = "Siparişlerini izle",
            description = "Kargo ve ödeme durumu",
            icon = Icons.Outlined.ShoppingBag,
            onClick = onOrdersClick
        )

        AccountMiniActionCard(
            title = "Banka bilgileri",
            description = "IBAN ve hesap yönetimi",
            icon = Icons.Outlined.Wallet,
            onClick = onBankAccountsClick
        )
    }
}

@Composable
private fun AccountMiniActionCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .width(188.dp)
            .clickable {
                onClick()
            },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
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
    }
}

@Composable
private fun AccountUsagePurposeCard(
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
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
                    imageVector = Icons.Outlined.Tune,
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
                    text = "Bulbulustur’u en çok hangi amaçla kullanıyorsunuz?",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Toptan, perakende veya her ikisi için deneyimini kişiselleştirelim.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            AccountChevron()
        }
    }
}

@Composable
private fun AccountCommerceHeroCard(
    onOrdersClick: () -> Unit,
    onQuotationRequestsClick: () -> Unit,
    onBankAccountsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = BbRadius.XlShape
            )
            .padding(BbSpacing.CardPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxSm)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = BbRadius.PillShape
                        )
                )

                Text(
                    text = "Ticaret merkezini buradan yönet",
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }

            Text(
                text = "Siparişlerini, tekliflerini, favorilerini ve hesap ayarlarını tek yerden takip et.",
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.72f)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                CommerceMiniAction(
                    text = "Siparişler",
                    onClick = onOrdersClick
                )

                CommerceMiniAction(
                    text = "Teklifler",
                    onClick = onQuotationRequestsClick
                )

                CommerceMiniAction(
                    text = "Banka",
                    onClick = onBankAccountsClick
                )
            }
        }
    }
}

@Composable
private fun CommerceMiniAction(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = BbRadius.Badge
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            )
    ) {
        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun AccountMenuSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Text(
            text = title,
            style = BbTypography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = BbSpacing.Space1)
        )

        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.None
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun AccountMenuRow(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
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

        AccountChevron()
    }
}

@Composable
private fun AccountDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.Space16,
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

@Composable
private fun AccountSupportCard(
    onSupportClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BbRadius.XlShape
            )
            .clickable {
                onSupportClick()
            }
            .padding(BbSpacing.CardPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.SupportAgent,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BbIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Yardıma mı ihtiyacın var?",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    text = "Sipariş, iade, ödeme veya hesap işlemleri için destek merkezine ulaş.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.78f)
                )
            }

            AccountChevron()
        }
    }
}

@Composable
private fun AccountLogoutCard(
    onLogoutClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onLogoutClick()
            },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
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
                    imageVector = Icons.Outlined.TransitEnterexit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BbIcon.Ui)
                )
            }

            Text(
                text = "Çıkış Yap",
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )

            AccountChevron()
        }
    }
}

@Composable
private fun AccountChevron() {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxSm)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.PillShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BbIcon.SizeSm)
        )
    }
}