package com.bulbulustur.android.features.account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
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
    onProfileClick: () -> Unit = {},
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
    val useProfilePhoto = true

    val accountData = remember {
        AccountHomeData(
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

    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            BbColors.PrimarySoft.copy(alpha = 0.62f),
            BbColors.SurfaceMuted,
            BbColors.SurfaceMuted
        )
    )

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
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
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.Space2,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.Space5
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                AccountProfileHero(
                    data = accountData,
                    useProfilePhoto = useProfilePhoto,
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
                AccountMenuSection(
                    title = "Hesap İşlemleri"
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
                        description = "Ürün sorularını ve satıcı cevaplarını takip et.",
                        icon = Icons.Outlined.QuestionAnswer,
                        onClick = onQuestionsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = "Değerlendirmelerim",
                        description = "Yorum ve ürün değerlendirmelerini görüntüle.",
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
                        onClick = onBankAccountsClick
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
                AccountUsagePurposeDarkCard(
                    onClick = onUsagePurposeClick
                )
            }
        }
    }
}

@Composable
private fun AccountProfileHero(
    data: AccountHomeData,
    useProfilePhoto: Boolean,
    onProfileClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BbSpacing.Space2),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AccountTopIconButton(
                icon = Icons.Outlined.MailOutline,
                onClick = onMessagesClick
            )

            Spacer(
                modifier = Modifier.width(BbSpacing.Space3)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            AccountAvatar(
                useProfilePhoto = useProfilePhoto,
                initials = data.initials,
                onClick = onProfileClick
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = data.userName,
                    style = BbTypography.headlineSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier.clickable {
                        onProfileClick()
                    },
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data.profileSubtitle,
                        style = BbTypography.bodyMedium,
                        color = BbColors.TextMuted,
                        textAlign = TextAlign.Center
                    )

                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = BbColors.TextMuted,
                        modifier = Modifier.size(BbIcon.SizeSm)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
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
    initials: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(106.dp)
            .background(
                color = BbColors.Primary,
                shape = CircleShape
            )
            .padding(4.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        if (useProfilePhoto) {
            Image(
                painter = painterResource(id = R.drawable.murat_erkan),
                contentDescription = "Profil fotoğrafı",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = BbColors.PrimarySoft,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    style = BbTypography.titleLarge,
                    color = BbColors.TextStrong,
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
        shape = BbRadius.PillShape,
        color = BbColors.Surface.copy(alpha = 0.76f),
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            ),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.size(BbIcon.SizeSm)
            )

            Text(
                text = text,
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted,
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
            .size(44.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.TextStrong,
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
            .padding(vertical = BbSpacing.Space1),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = value,
            style = BbTypography.titleLarge,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = label,
            style = BbTypography.bodySmall,
            color = BbColors.TextMuted
        )
    }
}

@Composable
private fun AccountVerticalDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(36.dp)
            .background(BbColors.Border)
    )
}

@Composable
private fun AccountPrimaryActions(
    onQuotationRequestsClick: () -> Unit,
    onCompanyInfoClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        AccountPrimaryActionCard(
            modifier = Modifier.weight(1f),
            title = "Toptan Teklif Al",
            description = "Özel fiyat iste.",
            icon = Icons.Outlined.RequestQuote,
            accent = AccountActionAccent.Red,
            onClick = onQuotationRequestsClick
        )

        AccountPrimaryActionCard(
            modifier = Modifier.weight(1f),
            title = "Şirketini Kaydet",
            description = "Ticari profilini güçlendir.",
            icon = Icons.Outlined.Business,
            accent = AccountActionAccent.Green,
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
    accent: AccountActionAccent,
    onClick: () -> Unit
) {
    val containerColor = when (accent) {
        AccountActionAccent.Red -> BbColors.Red.Red50
        AccountActionAccent.Green -> BbColors.Success.copy(alpha = 0.08f)
    }

    val borderColor = when (accent) {
        AccountActionAccent.Red -> BbColors.Red.Red600.copy(alpha = 0.16f)
        AccountActionAccent.Green -> BbColors.Success.copy(alpha = 0.24f)
    }

    val iconContainer = when (accent) {
        AccountActionAccent.Red -> BbColors.Red.Red600.copy(alpha = 0.10f)
        AccountActionAccent.Green -> BbColors.Success.copy(alpha = 0.12f)
    }

    val iconTint = when (accent) {
        AccountActionAccent.Red -> BbColors.Red.Red600
        AccountActionAccent.Green -> BbColors.Success
    }

    Surface(
        modifier = modifier
            .height(132.dp)
            .clickable {
                onClick()
            },
        shape = BbRadius.XlShape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        )
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.Space3),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxLg)
                        .background(
                            color = iconContainer,
                            shape = BbRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(BbIcon.Section)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxSm)
                        .background(
                            color = BbColors.Surface.copy(alpha = 0.80f),
                            shape = BbRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = BbColors.TextMuted,
                        modifier = Modifier.size(BbIcon.SizeSm)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = BbTypography.titleMedium,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = description,
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun AccountUsagePurposeDarkCard(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.TextStrong,
                shape = BbRadius.XlShape
            )
            .clickable {
                onClick()
            }
            .padding(BbSpacing.CardPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = BbColors.Primary.copy(alpha = 0.72f),
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Tune,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(BbIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Bulbulustur’u Hangi Amaçla Kullanıyorsun?",
                    style = BbTypography.titleMedium,
                    color = BbColors.Surface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Toptan, perakende veya her ikisi için deneyimini kişiselleştirelim.",
                    style = BbTypography.bodySmall,
                    color = BbColors.Surface.copy(alpha = 0.72f)
                )
            }

            Box(
                modifier = Modifier
                    .size(BbIcon.BoxSm)
                    .background(
                        color = BbColors.Surface.copy(alpha = 0.10f),
                        shape = BbRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = BbColors.Surface,
                    modifier = Modifier.size(BbIcon.SizeSm)
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold,
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
                    .background(BbColors.Surface)
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
            .padding(
                start = BbSpacing.Space4,
                top = BbSpacing.Space4,
                end = BbSpacing.Space4,
                bottom = BbSpacing.Space4
            ),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = BbColors.SurfaceMuted,
                    shape = BbRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
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
                color = BbColors.TextStrong,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = description,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
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
private fun AccountChevron() {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxSm)
            .background(
                color = BbColors.SurfaceMuted,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = BbColors.TextMuted,
            modifier = Modifier.size(BbIcon.SizeSm)
        )
    }
}

@Composable
private fun AccountDashedDivider() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.Space16,
                end = BbSpacing.Space4
            )
            .height(1.dp)
    ) {
        drawLine(
            color = BbColors.Border,
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
private data class AccountHomeData(
    val userName: String,
    val profileSubtitle: String,
    val initials: String,
    val profileId: String,
    val city: String,
    val orderCount: String,
    val favoriteCount: String,
    val quotationCount: String
)

private enum class AccountActionAccent {
    Red,
    Green
}