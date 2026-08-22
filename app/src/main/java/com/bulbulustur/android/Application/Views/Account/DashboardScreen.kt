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
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalOffer
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
import androidx.compose.material.icons.outlined.Person
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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
            profileSubtitle = BBLocalization.Current.Get(key = "c8f04521-ab0d-4701-ad2b-6b4882092d37", fallback = "Profili Görüntüle veya Düzenle"),
            initials = "ME"
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
                    title = BBLocalization.Current.Get(key = "d7296566-8cda-48a0-8a11-44e5f928c65c", fallback = "Alışveriş İşlemleri")
                ) {
                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "2c20dfd9-18d2-44a2-8298-95d7f91ea8e8", fallback = ""),
                        description = BBLocalization.Current.Get(key = "a3cfa134-70ee-4ff9-800d-e70df322459b", fallback = "Geçmiş ve aktif siparişlerini görüntüle."),
                        icon = Icons.Outlined.ProductionQuantityLimits,
                        onClick = onOrdersClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "655d2dca-997f-41c6-aafe-479e03712a0a", fallback = "Favorilerim"),
                        description = BBLocalization.Current.Get(key = "7efa0cb7-b45e-44b7-821b-936915323aaa", fallback = "Perakende ve toptan favorilerini yönet."),
                        icon = Icons.Outlined.FavoriteBorder,
                        onClick = onFavoritesClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "d497388f-3bab-40fd-af5e-e6294231b04f", fallback = "Fiyat Teklifi İstekleri"),
                        description = BBLocalization.Current.Get(key = "5b9bdd17-f7a7-4554-9dbe-da0aedb1c6c3", fallback = "Toptan teklif ve RFQ süreçlerini takip et."),
                        icon = Icons.Outlined.RequestQuote,
                        onClick = onQuotationRequestsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "a5c6a1af-5860-4dc6-9f86-c80c8631f063", fallback = "Adreslerim"),
                        description = BBLocalization.Current.Get(key = "b790df2b-f39f-4b0f-a551-e78c00eceba8", fallback = "Teslimat ve fatura adreslerini düzenle."),
                        icon = Icons.Outlined.Home,
                        onClick = onAddressClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "56bb1452-c8f8-4bb0-8501-0947118999df", fallback = "Banka Hesaplarım"),
                        description = BBLocalization.Current.Get(key = "e0c413ce-ef4d-405c-ae44-6addaab2ce40", fallback = "IBAN ve banka hesap bilgilerini yönet."),
                        icon = Icons.Outlined.AccountBalance,
                        onClick = onBankAccountsClick
                    )
                }
            }

            item {
                AccountMenuSection(
                    title = BBLocalization.Current.Get(key = "b883599e-fd54-4f60-a8bf-7be6ee723dcf", fallback = "Ticari Profil")
                ) {
                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "5d3c17c2-d063-4757-9940-62331a540e23", fallback = "Şirket Bilgileri"),
                        description = BBLocalization.Current.Get(key = "a89a7786-dfa0-4e8f-a3a1-fe2e3bce7e3a", fallback = "Firma ve ticari hesap bilgilerini yönet."),
                        icon = Icons.Outlined.Business,
                        onClick = onCompanyInfoClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "ebf4cd36-c030-48c3-81e5-cdf9e66e1f28", fallback = "Takip Ettiğim Mağazalar"),
                        description = BBLocalization.Current.Get(key = "d36c3f02-ea52-47ec-8bc2-f07b5ed7130a", fallback = "Takip ettiğin mağaza ve firmaları görüntüle."),
                        icon = Icons.Outlined.Storefront,
                        onClick = onFollowedStoresClick
                    )

                }
            }

            item {
                AccountMenuSection(
                    title = BBLocalization.Current.Get(key = "2d3a9e98-76d1-471c-83ef-3d08e86f5982", fallback = "Alışveriş ve Etkileşim")
                ) {
                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "4881f11f-fde1-4813-9924-64c21b61980c", fallback = "Soru ve Cevaplarım"),
                        description = BBLocalization.Current.Get(key = "8212c06b-d32d-4fd2-9a0e-5f239e753bc4", fallback = "Sorularını ve satıcı cevaplarını görüntüle."),
                        icon = Icons.Outlined.QuestionAnswer,
                        onClick = onQuestionsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "e6bb3a6c-5706-4ecd-8cab-8cb37ba1359b", fallback = "Değerlendirmelerim"),
                        description = BBLocalization.Current.Get(key = "fb1e4bfc-29e9-4bbc-ad77-d641fe384046", fallback = "Yorum ve değerlendirmelerini görüntüle."),
                        icon = Icons.Outlined.Reviews,
                        onClick = onReviewsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "fd0f23de-98a8-4544-ac77-1d2ce02750af", fallback = "Kuponlarım"),
                        description = BBLocalization.Current.Get(key = "6a4ba614-9b5b-4440-bff4-ed218f256df1", fallback = "Tanımlı kampanya ve kuponlarını incele."),
                        icon = Icons.Outlined.LocalOffer,
                        onClick = onCouponsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "edf6011a-a790-4d23-8a93-c539be6986ae", fallback = "Taleplerim"),
                        description = BBLocalization.Current.Get(key = "4aa8ba5f-4a2e-44db-8487-937f48674f45", fallback = "İade, destek ve işlem taleplerini takip et."),
                        icon = Icons.Outlined.HelpOutline,
                        onClick = onRequestsClick
                    )
                }
            }

            item {
                AccountMenuSection(
                    title = BBLocalization.Current.Get(key = "af673a72-7193-42a3-bc6f-c0d18fc17797", fallback = "Finansal")
                ) {
                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "1ef8a30c-4837-40d1-b6eb-c0493e23e740", fallback = ""),
                        description = BBLocalization.Current.Get(key = "aeb6d29e-ad2e-4ba2-b764-a2c2f75f0cfb", fallback = "Paket ve üyelik süreçlerini görüntüle."),
                        icon = Icons.Outlined.CreditCard,
                        onClick = onSubscriptionsClick
                    )

                    AccountDashedDivider()

                    AccountMenuRow(
                        title = BBLocalization.Current.Get(key = "08e11cee-13cd-4f6f-91b0-fe3c169015c3", fallback = "Cüzdan ve Bakiye"),
                        description = BBLocalization.Current.Get(key = "a41524fb-c788-469f-9e2e-d50f995ba91e", fallback = "Bakiye, ödeme ve finansal hareketlerini incele."),
                        icon = Icons.Outlined.Wallet,
                        onClick = onWalletBalanceClick
                    )
                }
            }

            item {
                AccountSupportAndSettingsGroup(
                    onSupportClick = onSupportClick,
                    onSettingsClick = onSettingsClick,
                    onUsagePurposeClick = onUsagePurposeClick
                )
            }

            item {
                AccountLogoutSection(
                    onLogoutClick = onLogoutClick,
                    isLogoutLoading = isLogoutLoading
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
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            AccountAvatar(
                useProfilePhoto = useProfilePhoto,
                profilePictureUrl = profilePictureUrl,
                initials = data.initials,
                onClick = onProfileClick
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
                contentDescription = BBLocalization.Current.Get(key = "b97518f9-86d4-46f0-9f2e-5e3f96aa7440", fallback = "Profil fotoğrafı"),
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
private fun AccountTopIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space9)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(BBIcon.SizeXl)
        )
    }
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
            title = BBLocalization.Current.Get(key = "9aa9e9a4-18b3-427b-943f-36170e46cb37", fallback = "Toptan Teklif Al"),
            description = BBLocalization.Current.Get(key = "6ca25794-a98d-4677-9991-d621b3ee525a", fallback = "Özel fiyat iste."),
            icon = Icons.Outlined.RequestQuote,
            accent = DashboardActionAccent.Primary,
            onClick = onQuotationRequestsClick
        )

        AccountPrimaryActionCard(
            modifier = Modifier.weight(1f),
            title = BBLocalization.Current.Get(key = "35b7fb34-7182-4d71-99e2-5e5e43f2b0c8", fallback = "Şirketini Kaydet"),
            description = BBLocalization.Current.Get(key = "6f82915a-8f3e-4c7f-b561-6609acc9be0f", fallback = "Ticari profilini güçlendir."),
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
        DashboardActionAccent.Secondary -> MaterialTheme.colorScheme.tertiaryContainer
    }

    val contentColor = when (accent) {
        DashboardActionAccent.Primary -> MaterialTheme.colorScheme.onPrimaryContainer
        DashboardActionAccent.Secondary -> MaterialTheme.colorScheme.onTertiaryContainer
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
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(BBSpacing.BorderThin, MaterialTheme.colorScheme.outlineVariant)
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
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "b32ffec3-51c2-403d-beda-5770383c4d2b", fallback = "Hesabım"),
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = BBLocalization.Current.Get(key = "1bc010f5-a7b0-453e-acba-b75b7456b7f7", fallback = "Kişisel bilgilerini, güvenlik ayarlarını ve hesap tercihlerini yönet."),
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

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
    onSettingsClick: () -> Unit,
    onUsagePurposeClick: () -> Unit
) {
    AccountMenuSection(
        title = BBLocalization.Current.Get(key = "8f2c8ee8-2a9e-4546-862c-1c2abc43b78b", fallback = "Destek ve Ayarlar")
    ) {
        AccountMenuRow(
            title = BBLocalization.Current.Get(key = "10fa5c5e-ee88-4d0b-9527-d8355adbb063", fallback = "Yardıma mı İhtiyacın Var?"),
            description = BBLocalization.Current.Get(key = "e48f7e6f-8bcd-4627-8e35-353cd0b93f19", fallback = "Destek merkezi, sipariş, iade ve hesap yardımına ulaş."),
            icon = Icons.Outlined.SupportAgent,
            onClick = onSupportClick
        )

        AccountDashedDivider()

        AccountMenuRow(
            title = BBLocalization.Current.Get(key = "0922dd57-03f9-47a3-a4de-8aaddad92aba", fallback = "Uygulama Ayarları"),
            description = BBLocalization.Current.Get(key = "afef7635-1a94-4481-99a5-bde9954a09c6", fallback = "Dil, görünüm, bildirim ve uygulama tercihleri."),
            icon = Icons.Outlined.Settings,
            onClick = onSettingsClick
        )

        AccountDashedDivider()

        AccountMenuRow(
            title = BBLocalization.Current.Get(key = "81ebee8c-44e5-40c8-b5e3-b88450002783", fallback = "Bulbulustur'u Hangi Amaçla Kullanıyorsun?"),
            description = BBLocalization.Current.Get(key = "e2a4ff9c-4e86-4bfb-adbe-ebd470f78ea0", fallback = "Toptan, perakende veya her ikisi için deneyimini kişiselleştirelim."),
            icon = Icons.Outlined.Tune,
            onClick = onUsagePurposeClick
        )
    }
}

@Composable
private fun AccountLogoutSection(
    onLogoutClick: () -> Unit,
    isLogoutLoading: Boolean
) {
    AccountMenuSection(
        title = BBLocalization.Current.Get(key = "28bb1e37-ebdd-47f2-a0ec-a375202065df", fallback = "Oturum")
    ) {
        AccountMenuRow(
            title =
                if (isLogoutLoading) {
                    BBLocalization.Current.Get(key = "b283d6c6-4d79-42e6-9ff9-05010869e0c0", fallback = "Çıkış Yapılıyor")
                } else {
                    BBLocalization.Current.Get(key = "2449e852-d554-48ff-b7a5-e29a3e096a81", fallback = "")
                },
            description =
                if (isLogoutLoading) {
                    BBLocalization.Current.Get(key = "1f0c7629-3521-4351-b8d0-eabedd262fe7", fallback = "Oturumunuz güvenli şekilde kapatılıyor.")
                } else {
                    BBLocalization.Current.Get(key = "f358ef0e-73ee-47eb-a964-2f9451c33b73", fallback = "Bu cihazdaki Bulbulustur oturumunu kapat.")
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
    val initials: String
)

private enum class DashboardActionAccent {
    Primary,
    Secondary
}