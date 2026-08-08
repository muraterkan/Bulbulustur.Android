package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.HomeWork
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberSubscriptionDTO
import com.bulbulustur.android.businesslayer.Core.DTO.StoreRequestDTO

@Composable
fun CompanyInfoScreen(
    company: CompanyDTO?,
    storeRequest: StoreRequestDTO?,
    subscription: MemberSubscriptionDTO?,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onB2BIndexClick: () -> Unit = {},
    onB2CStoreClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "23e2ad69-3c04-4e82-834b-ed4779f0130f", fallback = "Firma Bilgilerim"),
                onBackClick = onBackClick,
                actionIcon = if (company != null) Icons.Outlined.Edit else null,
                actionContentDescription = BBLocalization.Current.Get(key = "761ceb0a-2578-4b71-8c50-5b0d0998aefe", fallback = "Firma Bilgilerini Düzenle"),
                onActionClick = onEditClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant).padding(innerPadding),
            contentPadding = PaddingValues(start = BBSpacing.PageHorizontal, top = BBSpacing.PageTopCompact, end = BBSpacing.PageHorizontal, bottom = BBSpacing.PageBottom),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            when {
                isLoading -> item { CompanyLoadingState() }
                !errorMessage.isNullOrBlank() -> item { CompanyErrorState(message = errorMessage, onRetryClick = onRetryClick) }
                company == null -> item { CompanyNotFoundState(onRetryClick = onRetryClick) }
                else -> {
                    item { CompanyHeroCard(company = company) }
                    item { CompanyStatsGrid(company = company, subscription = subscription) }
                    item {
                        CompanyInfoSection(title = BBLocalization.Current.Get(key = "b05816cf-38fa-4a75-8d66-bf800fb4a8d7", fallback = "Şirket Kimliği"), description = BBLocalization.Current.Get(key = "9f84fd03-250c-48de-b322-b89b5602922c", fallback = "Ünvan, şirket tipi ve kurumsal profil bilgileri."), icon = Icons.Outlined.Badge) {
                            CompanyInfoRow(BBLocalization.Current.Get(key = "295bef89-1d52-45bf-8076-0cb215d76c41", fallback = ""), company.CompanyName.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "ce4087e5-47b4-4b6a-a853-d02fc65dfe4f", fallback = "Şirket Tipi"), company.CompanyType.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "bf84d3d3-4da3-41f0-bb97-5f7952d7d373", fallback = "Abonelik Planı"), subscription.GetSubscriptionTitle())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "2439777a-0431-4929-9600-07df5586ad67", fallback = ""), company.YearEstablished.OrDash())
                        }
                    }
                    item {
                        CompanyInfoSection(title = BBLocalization.Current.Get(key = "80196c43-5833-4c0a-8c7b-d2906837956e", fallback = "Adres Bilgileri"), description = BBLocalization.Current.Get(key = "1e9670ca-c13f-4f26-969d-b981946c44dd", fallback = "Şirketin kayıtlı lokasyon bilgileri."), icon = Icons.Outlined.LocationOn) {
                            CompanyInfoRow(BBLocalization.Current.Get(key = "af1da4df-7298-4cd9-b256-371d098b59f7", fallback = "Adres"), company.Address.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "8b04cc2a-5e86-4d4e-bf8c-7dc7bf1be325", fallback = "Ülke"), company.CountryName.OrDash())
                            if (company.CountryState.orEmpty().isNotBlank()) {
                                CompanyDivider()
                                CompanyInfoRow(BBLocalization.Current.Get(key = "e59fd16b-4f44-42cb-9488-de3a719b46dd", fallback = "Eyalet / Bölge"), company.CountryState.orEmpty())
                            }
                            if (company.CountryDepartment.orEmpty().isNotBlank()) {
                                CompanyDivider()
                                CompanyInfoRow("Departman", company.CountryDepartment.orEmpty())
                            }
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "a4936d53-1fc1-4e87-a255-2a4906748a61", fallback = "Şehir"), company.CityName.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "843fedae-4923-4542-9341-9832b4a5f773", fallback = "İlçe"), company.DistrictName.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "fff66b6e-cf51-4dde-a421-b8ce3df436d0", fallback = "Posta Kodu"), company.PostCode.OrDash())
                        }
                    }
                    item {
                        CompanyInfoSection(title = BBLocalization.Current.Get(key = "320c5446-e42b-4ac9-ad81-ca0d3053b6b8", fallback = "Vergi ve Resmi Bilgiler"), description = BBLocalization.Current.Get(key = "a096e615-ea46-46f5-9318-15c113cb0193", fallback = "Fatura ve resmi kayıt süreçlerinde kullanılan bilgiler."), icon = Icons.Outlined.ReceiptLong) {
                            CompanyInfoRow(BBLocalization.Current.Get(key = "8c42e65e-d7a4-4ff2-9dce-e4073d4dc335", fallback = "Vergi Dairesi"), company.TaxOffice.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "0f94c70f-fe11-4d18-8561-64d8499637df", fallback = "Vergi Numarası"), company.TaxId.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "abc9ea53-da8a-4a53-bcc1-46dc5f9f9461", fallback = "MERSİS"), company.MersisNo.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("KEP", company.KepAddress.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "a8fcc3ce-6d1a-40be-b752-974c9b774d7b", fallback = "Web Sitesi"), company.Url.OrDash())
                            CompanyDivider()
                            CompanyInfoRow(BBLocalization.Current.Get(key = "1246f9ff-205d-4d92-84ee-7c8c7a3f2d46", fallback = "E-Posta"), company.Email.OrDash())
                        }
                    }
                    item {
                        CompanyActionSection(
                            b2bActive = company.B2bIndex,
                            storeRequest = storeRequest,
                            onB2BIndexClick = onB2BIndexClick,
                            onB2CStoreClick = onB2CStoreClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CompanyHeroCard(company: CompanyDTO) {
    Box(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.surface, shape = BBRadius.XlShape).padding(BBSpacing.CardPadding)) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(BBIcon.BoxXl).background(color = MaterialTheme.colorScheme.primary, shape = BBRadius.XlShape), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Outlined.Business, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(BBIcon.Section))
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                    Text(text = BBLocalization.Current.Get(key = "91d82f76-65ad-4d7d-a755-6acb5e469217", fallback = "Kurumsal Hesap"), style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = company.CompanyName.OrDash(), style = BbTypography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = company.CompanyType.OrDash(), style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = BBAlpha.Muted))
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2), verticalAlignment = Alignment.CenterVertically) {
                CompanyHeroBadge(text = if (company.Verified) BBLocalization.Current.Get(key = "c6a0ff62-8828-475f-b553-37effb42efe6", fallback = "Doğrulanmış Firma") else "Doğrulama Bekliyor", icon = Icons.Outlined.Verified)
                CompanyHeroBadge(text = if (company.B2bIndex) BBLocalization.Current.Get(key = "2f53034e-311b-4679-9b3b-ac800e6119f1", fallback = "B2B Aktif") else BBLocalization.Current.Get(key = "7562d6aa-4829-4939-9219-2cade650e115", fallback = "B2B Kapalı"), icon = Icons.Outlined.Storefront)
            }
        }
    }
}

@Composable
private fun CompanyHeroBadge(text: String, icon: ImageVector) {
    Row(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = BBAlpha.Overlay), shape = BBRadius.Badge).padding(horizontal = BBSpacing.Space3, vertical = BBSpacing.Space2),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(BBIcon.Size2Xs))
        Text(text = text, style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
private fun CompanyStatsGrid(company: CompanyDTO, subscription: MemberSubscriptionDTO?) {
    Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyStatCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.Storefront, label = BBLocalization.Current.Get(key = "bf84d3d3-4da3-41f0-bb97-5f7952d7d373", fallback = "Abonelik Planı"), value = subscription?.SubscriptionPlanTypeName?.OrDash() ?: "-")
            CompanyStatCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.HomeWork, label = BBLocalization.Current.Get(key = "2439777a-0431-4929-9600-07df5586ad67", fallback = ""), value = company.YearEstablished.OrDash())
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyStatCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.LocationOn, label = BBLocalization.Current.Get(key = "538ba8f5-1132-4e1b-ace9-86562c7970c7", fallback = "Ülke / Şehir"), value = listOf(company.CountryName.orEmpty(), company.CityName.orEmpty()).filter { it.isNotBlank() }.joinToString(" / ").ifBlank { "-" })
            CompanyStatCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.Verified, label = BBLocalization.Current.Get(key = "9e5eef88-553b-43a2-b0d2-25609b8132af", fallback = "Profil Durumu"), value = if (company.StatusId > 0) "Aktif" else "Pasif")
        }
    }
}

@Composable
private fun CompanyStatCard(modifier: Modifier, icon: ImageVector, label: String, value: String) {
    BbCard(modifier = modifier, variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            Box(modifier = Modifier.size(BBIcon.BoxMd).background(color = MaterialTheme.colorScheme.primaryContainer, shape = BBRadius.PillShape), contentAlignment = Alignment.Center) {
                Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(BBIcon.Ui))
            }
            Text(text = label, style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = value, style = BbTypography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
private fun CompanyInfoSection(title: String, description: String, icon: ImageVector, content: @Composable ColumnScope.() -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.None) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            Row(modifier = Modifier.fillMaxWidth().padding(BBSpacing.CardPadding), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(BBIcon.BoxMd).background(color = MaterialTheme.colorScheme.primaryContainer, shape = BBRadius.LgShape), contentAlignment = Alignment.Center) {
                    Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(BBIcon.Ui))
                }
                Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                    Text(text = title, style = BbTypography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = description, style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            CompanyDivider()
            Column { content() }
        }
    }
}

@Composable
private fun CompanyInfoRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = BBSpacing.CardPadding, vertical = BBSpacing.Space3), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
        Text(text = label, style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
private fun CompanyActionSection(
    b2bActive: Boolean,
    storeRequest: StoreRequestDTO?,
    onB2BIndexClick: () -> Unit,
    onB2CStoreClick: () -> Unit
) {
    CompanyInfoSection(title = BBLocalization.Current.Get(key = "0390d4dd-b129-4a48-859c-cf70e4a976c8", fallback = "Kurumsal İşlemler"), description = BBLocalization.Current.Get(key = "31ff8031-2352-4b43-9301-e110b1b964e1", fallback = "Şirket profilini ticaret akışlarına bağla."), icon = Icons.Outlined.Business) {
        CompanyActionRow(
            title = if (b2bActive) BBLocalization.Current.Get(key = "098a51d6-d8a7-4261-aa23-711a10f8e897", fallback = "B2B Index Durumunu Gör") else "Şirketimi B2B Index'e Dahil Et",
            description = if (b2bActive) BBLocalization.Current.Get(key = "3089962b-3e14-4f43-b560-b708dd3cd2bd", fallback = "B2B görünürlüğünüzü ve abonelik durumunuzu görüntüleyin.") else BBLocalization.Current.Get(key = "66874157-a31d-471c-a014-a9476f2afec2", fallback = "Tedarikçiler ve toptan alıcılar tarafından keşfedilin."),
            icon = Icons.Outlined.Storefront,
            onClick = onB2BIndexClick
        )
        CompanyDivider()
        CompanyActionRow(
            title = storeRequest.GetB2CActionTitle(),
            description = storeRequest.GetB2CActionDescription(),
            icon = Icons.Outlined.HomeWork,
            onClick = onB2CStoreClick
        )
    }
}

@Composable
private fun CompanyActionRow(title: String, description: String, icon: ImageVector, onClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(BBSpacing.CardPadding), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(BBIcon.BoxMd).background(color = MaterialTheme.colorScheme.surfaceVariant, shape = BBRadius.PillShape), contentAlignment = Alignment.Center) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(BBIcon.Ui))
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
            Text(text = title, style = BbTypography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
            Text(text = description, style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Box(modifier = Modifier.size(BBIcon.BoxSm).background(color = MaterialTheme.colorScheme.surfaceVariant, shape = BBRadius.PillShape), contentAlignment = Alignment.Center) {
            Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.SizeSm))
        }
    }
}

@Composable
private fun CompanyLoadingState() {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = MaterialTheme.colorScheme.primary) }
    }
}

@Composable
private fun CompanyErrorState(message: String, onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.ErrorOutline, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(BBIcon.Section))
            Text(text = message, style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            BbButton(text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"), onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyNotFoundState(onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.Business, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.Section))
            Text(text = BBLocalization.Current.Get(key = "4f467a63-d0ab-423f-ae06-65c598e5a641", fallback = "Firma bilgisi bulunamadı"), style = BbTypography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            Text(text = BBLocalization.Current.Get(key = "9ed3edcb-bacb-47f7-8582-e196be438014", fallback = "Hesabınıza bağlı şirket kaydı bulunamadı veya görüntülenemiyor."), style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            BbButton(text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"), onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyDivider() {
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
}

private fun String.OrDash(): String = ifBlank { "-" }

private fun MemberSubscriptionDTO?.GetSubscriptionTitle(): String {
    if (this == null) return "-"

    val subscription = Subscription
    val typeName = SubscriptionTypeName
    val planTypeName = SubscriptionPlanTypeName

    if (!subscription.isNullOrBlank()) return subscription
    if (!typeName.isNullOrBlank() && !planTypeName.isNullOrBlank()) return "$typeName / $planTypeName"
    if (!typeName.isNullOrBlank()) return typeName
    if (!planTypeName.isNullOrBlank()) return planTypeName

    return "-"
}


private fun StoreRequestDTO?.GetB2CActionTitle(): String {
    return when (this?.StoreConfirmation) {
        1, 2 -> BBLocalization.Current.Get(key = "00b7c24a-f7aa-46d3-9678-70aa0eda3160", fallback = "Başvuru Durumum")
        3 -> BBLocalization.Current.Get(key = "12ecf0e9-2d9f-4706-a767-62557c45aeab", fallback = "B2C Mağazamı Yönet")
        else -> BBLocalization.Current.Get(key = "3f419ff5-f1cf-485c-a731-bcf0c0f4c202", fallback = "Perakende Mağaza Talebi Oluştur")
    }
}

private fun StoreRequestDTO?.GetB2CActionDescription(): String {
    return when (this?.StoreConfirmation) {
        1, 2 -> BBLocalization.Current.Get(key = "919d47a1-23ca-4803-beaf-92d317fcd534", fallback = "Mağaza başvurunuzun güncel durumunu görüntüleyin.")
        3 -> BBLocalization.Current.Get(key = "9423105f-68b8-437b-8924-8d9fdcabb399", fallback = "Perakende mağazanızı ve satış operasyonlarınızı yönetin.")
        else -> BBLocalization.Current.Get(key = "a74ce447-c9ce-484f-866a-63b67a1c33ee", fallback = "Bulbulustur üzerinde perakende mağaza açmak için başvuru oluşturun.")
    }
}