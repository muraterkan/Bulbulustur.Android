package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import coil3.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.ProductionQuantityLimits
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Reviews
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.R
import com.bulbulustur.android.businesslayer.Core.Network.MemberPictureUrlResolver

@Composable
fun DashboardScreen(
    memberPicture: String = "",
    isLogoutLoading: Boolean = false,
    onSecurityClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
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
    onWalletBalanceClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onQuestionsClick: () -> Unit = {},
    onUsagePurposeClick: () -> Unit = {},

    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {}
) {
    val profilePictureUrl =
        MemberPictureUrlResolver.Resolve(
            memberPicture
        )

    val useProfilePhoto = profilePictureUrl.isNotBlank()

    val accountData = remember {
        DashboardHomeData(
            userName = "Murat Erkan",
            profileSubtitle = "Profili Görüntüle veya Düzenle",
            initials = "ME",
            profileId = "ID: 54108878",
            city = "İstanbul",
            orderCount = "0",
            favoriteCount = "0",
            quotationCount = "2"
        )
    }

    val pageBackgroundColor = MaterialTheme.colorScheme.surfaceVariant

    Scaffold(
        containerColor = pageBackgroundColor,
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
                .background(pageBackgroundColor)
                .statusBarsPadding()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.Space2,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.Space5
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                AccountProfileHero(
                    data = accountData,
                    useProfilePhoto = useProfilePhoto,
                    profilePictureUrl = profilePictureUrl,
                    onProfileClick = onProfileClick,
                    onMessagesClick = onMessagesClick,
                    onSettingsClick = onSettingsClick
                )
            }

            item {
                AccountStatsStrip(
                    orderCount = accountData.orderCount,
                    favoriteCount = accountData.favoriteCount,
                    quotationCount = accountData.quotationCount,
                    onOrdersClick = onOrdersClick,
                    onFavoritesClick = onFavoritesClick,
                    onQuotationRequestsClick = onQuotationRequestsClick
                )
            }

            item {
                AccountPrimaryActions(
                    onQuotationRequestsClick = onQuotationRequestsClick,
                    onCompanyInfoClick = onCompanyInfoClick
                )
            }

            item {
                DashboardAccountCard(
                    onClick = onAccountClick
                )
            }

            item {
                AccountMenuSection(
                    title = "Alışveriş İşlemleri"
                ) {
                    AccountMenuRow(
                        title = "Siparişlerim",
                        description = "Geçmiş ve aktif siparişlerini görüntüle.",
                        icon = Icons.Outlined.ProductionQuantityLimits,
                        onClick = onOrdersClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Favorilerim",
                        description = "Perakende ve toptan favorilerini yönet.",
                        icon = Icons.Outlined.FavoriteBorder,
                        onClick = onFavoritesClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Fiyat Teklifi İstekleri",
                        description = "Toptan teklif ve RFQ süreçlerini takip et.",
                        icon = Icons.Outlined.RequestQuote,
                        onClick = onQuotationRequestsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Adreslerim",
                        description = "Teslimat ve fatura adreslerini düzenle.",
                        icon = Icons.Outlined.Home,
                        onClick = onAddressClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Banka Hesaplarım",
                        description = "IBAN ve banka hesap bilgilerini yönet.",
                        icon = Icons.Outlined.AccountBalance,
                        onClick = onBankAccountsClick
                    )
                }
            }

            item {
                AccountMenuSection(
                    title = "Ticari Profil"
                ) {
                    AccountMenuRow(
                        title = "Şirket Bilgileri",
                        description = "Firma ve ticari hesap bilgilerini yönet.",
                        icon = Icons.Outlined.Business,
                        onClick = onCompanyInfoClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Takip Ettiğim Mağazalar",
                        description = "Takip ettiğin mağaza ve firmaları görüntüle.",
                        icon = Icons.Outlined.Storefront,
                        onClick = onFollowedStoresClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Kullanım Amacı",
                        description = "Toptan, perakende veya karma kullanım tercihini düzenle.",
                        icon = Icons.Outlined.Tune,
                        onClick = onUsagePurposeClick
                    )
                }
            }

            item {
                AccountMenuSection(
                    title = "Alışveriş ve Etkileşim"
                ) {
                    AccountMenuRow(
                        title = "Soru ve Cevaplarım",
                        description = "Sorularını ve satıcı cevaplarını görüntüle.",
                        icon = Icons.Outlined.QuestionAnswer,
                        onClick = onQuestionsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Değerlendirmelerim",
                        description = "Yorum ve değerlendirmelerini görüntüle.",
                        icon = Icons.Outlined.Reviews,
                        onClick = onReviewsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Kuponlarım",
                        description = "Tanımlı kampanya ve kuponlarını incele.",
                        icon = Icons.Outlined.LocalOffer,
                        onClick = onCouponsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Taleplerim",
                        description = "İade, destek ve işlem taleplerini takip et.",
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
                        description = "Paket ve üyelik süreçlerini görüntüle.",
                        icon = Icons.Outlined.CreditCard,
                        onClick = onSubscriptionsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Cüzdan ve Bakiye",
                        description = "Bakiye, ödeme ve finansal hareketlerini incele.",
                        icon = Icons.Outlined.Wallet,
                        onClick = onWalletBalanceClick
                    )
                }
            }

            item {
                AccountSupportAndSettingsGroup(
                    onSupportClick = onSupportClick,
                    onSettingsClick = onSettingsClick
                )
            }

            item {
                AccountLogoutSection(
                    onLogoutClick = onLogoutClick,
                    isLogoutLoading = isLogoutLoading
                )
            }

            item {
                AccountUsagePurposeDarkCard(
                    onClick = onUsagePurposeClick
                )
            }
        }
    }
}

@Composable
private fun AccountProfileHero(
    data: DashboardHomeData,
    useProfilePhoto: Boolean,
    profilePictureUrl: String,
    onProfileClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BBSpacing.Space2),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AccountTopIconButton(
                icon = Icons.Outlined.MailOutline,
                onClick = onMessagesClick
            )

            Spacer(
                modifier = Modifier.width(BBSpacing.Space3)
            )

            AccountTopIconButton(
                icon = Icons.Outlined.Settings,
                onClick = onSettingsClick
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onProfileClick()
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            AccountAvatar(
                useProfilePhoto = useProfilePhoto,
                profilePictureUrl = profilePictureUrl,
                initials = data.initials,
                onClick = onProfileClick
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = data.userName,
                    style = BbTypography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier.clickable {
                        onProfileClick()
                    },
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data.profileSubtitle,
                        style = BbTypography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )

                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.SizeSm)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AccountProfileChip(
                        icon = Icons.Outlined.Badge,
                        text = data.profileId
                    )

                    AccountProfileChip(
                        icon = Icons.Outlined.LocationOn,
                        text = data.city
                    )
                }
            }
        }
    }
}

@Composable
private fun AccountAvatar(
    useProfilePhoto: Boolean,
    profilePictureUrl: String,
    initials: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BBLayout.AccountAvatarSize)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = BBRadius.IconBoxSoft
            )
            .padding(BBSpacing.Space1)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        if (useProfilePhoto) {
            AsyncImage(
                model = profilePictureUrl,
                contentDescription = "Profil fotoğrafı",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(BBRadius.IconBoxSoft),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.IconBoxSoft
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun AccountProfileChip(
    icon: ImageVector,
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Text(
                text = text,
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun AccountTopIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space11)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
private fun AccountStatsStrip(
    orderCount: String,
    favoriteCount: String,
    quotationCount: String,
    onOrdersClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onQuotationRequestsClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Default,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AccountProfileStat(
                modifier = Modifier.weight(1f),
                value = orderCount,
                label = "Sipariş",
                onClick = onOrdersClick
            )

            AccountVerticalDivider()

            AccountProfileStat(
                modifier = Modifier.weight(1f),
                value = favoriteCount,
                label = "Favori",
                onClick = onFavoritesClick
            )

            AccountVerticalDivider()

            AccountProfileStat(
                modifier = Modifier.weight(1f),
                value = quotationCount,
                label = "Teklif",
                onClick = onQuotationRequestsClick
            )
        }
    }
}

@Composable
private fun AccountProfileStat(
    modifier: Modifier,
    value: String,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(vertical = BBSpacing.Space1),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = value,
            style = BbTypography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = label,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AccountVerticalDivider() {
    Box(
        modifier = Modifier
            .width(BBSpacing.BorderThin)
            .height(BBSpacing.Space9)
            .background(MaterialTheme.colorScheme.outlineVariant)
    )
}

@Composable
private fun AccountPrimaryActions(
    onQuotationRequestsClick: () -> Unit,
    onCompanyInfoClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        AccountPrimaryActionCard(
            modifier = Modifier.weight(1f),
            title = "Toptan Teklif Al",
            description = "Özel fiyat iste.",
            icon = Icons.Outlined.RequestQuote,
            accent = DashboardActionAccent.Primary,
            onClick = onQuotationRequestsClick
        )

        AccountPrimaryActionCard(
            modifier = Modifier.weight(1f),
            title = "Şirketini Kaydet",
            description = "Ticari profilini güçlendir.",
            icon = Icons.Outlined.Business,
            accent = DashboardActionAccent.Secondary,
            onClick = onCompanyInfoClick
        )
    }
}

@Composable
private fun AccountPrimaryActionCard(
    modifier: Modifier,
    title: String,
    description: String,
    icon: ImageVector,
    accent: DashboardActionAccent,
    onClick: () -> Unit
) {
    val containerColor = when (accent) {
        DashboardActionAccent.Primary -> MaterialTheme.colorScheme.primaryContainer
        DashboardActionAccent.Secondary -> MaterialTheme.colorScheme.secondaryContainer
    }

    val contentColor = when (accent) {
        DashboardActionAccent.Primary -> MaterialTheme.colorScheme.onPrimaryContainer
        DashboardActionAccent.Secondary -> MaterialTheme.colorScheme.onSecondaryContainer
    }

    Surface(
        modifier = modifier
            .height(BBLayout.ProductCardWidthSmall)
            .clickable {
                onClick()
            },
        shape = BBRadius.XlShape,
        color = containerColor,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxLg)
                        .background(
                            color = contentColor.copy(alpha = BBAlpha.Overlay),
                            shape = BBRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(BBIcon.Section)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxSm)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(
                                alpha = BBAlpha.OverlayHeavy
                            ),
                            shape = BBRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.SizeSm)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = BbTypography.titleMedium,
                    color = contentColor,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = description,
                    style = BbTypography.bodySmall,
                    color = contentColor.copy(alpha = BBAlpha.Muted)
                )
            }
        }
    }
}

@Composable
private fun DashboardAccountCard(
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.inverseSurface
    ) {
        Row(
            modifier = Modifier.padding(BBSpacing.CardPadding),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Badge,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Hesabım",
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Kişisel bilgilerini, güvenlik ayarlarını ve hesap tercihlerini yönet.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface.copy(
                        alpha = BBAlpha.Muted
                    )
                )
            }

            Box(
                modifier = Modifier
                    .size(BBIcon.BoxSm)
                    .background(
                        color = MaterialTheme.colorScheme.inverseOnSurface.copy(
                            alpha = BBAlpha.Overlay
                        ),
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )
            }
        }
    }
}

@Composable
private fun AccountUsagePurposeDarkCard(
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.inverseSurface
    ) {
        Row(
            modifier = Modifier.padding(BBSpacing.CardPadding),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Tune,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Bulbulustur'u Hangi Amaçla Kullanıyorsun?",
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Toptan, perakende veya her ikisi için deneyimini kişiselleştirelim.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface.copy(
                        alpha = BBAlpha.Muted
                    )
                )
            }

            Box(
                modifier = Modifier
                    .size(BBIcon.BoxSm)
                    .background(
                        color = MaterialTheme.colorScheme.inverseOnSurface.copy(
                            alpha = BBAlpha.Overlay
                        ),
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )
            }
        }
    }
}

@Composable
private fun AccountMenuSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = BBSpacing.Space1)
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
    enabled: Boolean = true,
    isDanger: Boolean = false,
    onClick: () -> Unit
) {
    val iconContainerColor =
        if (isDanger) {
            MaterialTheme.colorScheme.errorContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }

    val iconColor =
        if (isDanger) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.onSurface
        }

    val titleColor =
        if (isDanger) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.onSurface
        }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = enabled
            ) {
                onClick()
            }
            .padding(
                start = BBSpacing.Space4,
                top = BBSpacing.Space4,
                end = BBSpacing.Space4,
                bottom = BBSpacing.Space4
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BBIcon.BoxMd)
                .background(
                    color = iconContainerColor,
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

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = titleColor,
                fontWeight = FontWeight.SemiBold
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
private fun AccountSupportAndSettingsGroup(
    onSupportClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    AccountMenuSection(
        title = "Destek ve Ayarlar"
    ) {
        AccountMenuRow(
            title = "Yardıma mı İhtiyacın Var?",
            description = "Destek merkezi, sipariş, iade ve hesap yardımına ulaş.",
            icon = Icons.Outlined.SupportAgent,
            onClick = onSupportClick
        )

        AccountDashedDivider()

        AccountMenuRow(
            title = "Uygulama Ayarları",
            description = "Dil, görünüm, bildirim ve uygulama tercihleri.",
            icon = Icons.Outlined.Settings,
            onClick = onSettingsClick
        )
    }
}

@Composable
private fun AccountLogoutSection(
    onLogoutClick: () -> Unit,
    isLogoutLoading: Boolean
) {
    AccountMenuSection(
        title = "Oturum"
    ) {
        AccountMenuRow(
            title =
                if (isLogoutLoading) {
                    "Çıkış Yapılıyor"
                } else {
                    "Çıkış Yap"
                },
            description =
                if (isLogoutLoading) {
                    "Oturumunuz güvenli şekilde kapatılıyor."
                } else {
                    "Bu cihazdaki Bulbulustur oturumunu kapat."
                },
            icon = Icons.Outlined.Logout,
            enabled = !isLogoutLoading,
            isDanger = true,
            onClick = onLogoutClick
        )
    }
}

@Composable
private fun AccountChevron() {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxSm)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BBIcon.SizeSm)
        )
    }
}

@Composable
private fun AccountDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.Space16,
                end = BBSpacing.Space4
            )
            .height(BBSpacing.BorderThin)
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

@Immutable
private data class DashboardHomeData(
    val userName: String,
    val profileSubtitle: String,
    val initials: String,
    val profileId: String,
    val city: String,
    val orderCount: String,
    val favoriteCount: String,
    val quotationCount: String
)

private enum class DashboardActionAccent {
    Primary,
    Secondary
}