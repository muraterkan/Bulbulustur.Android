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
                title = "Firma Bilgilerim",
                onBackClick = onBackClick,
                actionIcon = if (company != null) Icons.Outlined.Edit else null,
                actionContentDescription = "Firma Bilgilerini Düzenle",
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
                        CompanyInfoSection(title = "Şirket Kimliği", description = "Ünvan, şirket tipi ve kurumsal profil bilgileri.", icon = Icons.Outlined.Badge) {
                            CompanyInfoRow("Şirket Ünvanı", company.CompanyName.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("Şirket Tipi", company.CompanyType.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("Abonelik Planı", subscription.GetSubscriptionTitle())
                            CompanyDivider()
                            CompanyInfoRow("Kuruluş Yılı", company.YearEstablished.OrDash())
                        }
                    }
                    item {
                        CompanyInfoSection(title = "Adres Bilgileri", description = "Şirketin kayıtlı lokasyon bilgileri.", icon = Icons.Outlined.LocationOn) {
                            CompanyInfoRow("Adres", company.Address.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("Ülke", company.CountryName.OrDash())
                            if (company.CountryState.isNotBlank()) {
                                CompanyDivider()
                                CompanyInfoRow("Eyalet / Bölge", company.CountryState)
                            }
                            if (company.CountryDepartment.isNotBlank()) {
                                CompanyDivider()
                                CompanyInfoRow("Departman", company.CountryDepartment)
                            }
                            CompanyDivider()
                            CompanyInfoRow("Şehir", company.CityName.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("İlçe", company.DistrictName.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("Posta Kodu", company.PostCode.OrDash())
                        }
                    }
                    item {
                        CompanyInfoSection(title = "Vergi ve Resmi Bilgiler", description = "Fatura ve resmi kayıt süreçlerinde kullanılan bilgiler.", icon = Icons.Outlined.ReceiptLong) {
                            CompanyInfoRow("Vergi Dairesi", company.TaxOffice.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("Vergi Numarası", company.TaxId.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("MERSİS", company.MersisNo.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("KEP", company.KepAddress.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("Web Sitesi", company.Url.OrDash())
                            CompanyDivider()
                            CompanyInfoRow("E-Posta", company.Email.OrDash())
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
    Box(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.inverseSurface, shape = BBRadius.XlShape).padding(BBSpacing.CardPadding)) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(BBIcon.BoxXl).background(color = MaterialTheme.colorScheme.primary, shape = BBRadius.XlShape), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Outlined.Business, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(BBIcon.Section))
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                    Text(text = "Kurumsal Hesap", style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = company.CompanyName.OrDash(), style = BbTypography.titleLarge, color = MaterialTheme.colorScheme.inverseOnSurface)
                    Text(text = company.CompanyType.OrDash(), style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Muted))
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2), verticalAlignment = Alignment.CenterVertically) {
                CompanyHeroBadge(text = if (company.Verified) "Doğrulanmış Firma" else "Doğrulama Bekliyor", icon = Icons.Outlined.Verified)
                CompanyHeroBadge(text = if (company.B2bIndex) "B2B Aktif" else "B2B Kapalı", icon = Icons.Outlined.Storefront)
            }
        }
    }
}

@Composable
private fun CompanyHeroBadge(text: String, icon: ImageVector) {
    Row(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Overlay), shape = BBRadius.Badge).padding(horizontal = BBSpacing.Space3, vertical = BBSpacing.Space2),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(BBIcon.Size2Xs))
        Text(text = text, style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.inverseOnSurface)
    }
}

@Composable
private fun CompanyStatsGrid(company: CompanyDTO, subscription: MemberSubscriptionDTO?) {
    Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyStatCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.Storefront, label = "Abonelik Planı", value = subscription?.SubscriptionPlanTypeName?.OrDash() ?: "-")
            CompanyStatCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.HomeWork, label = "Kuruluş Yılı", value = company.YearEstablished.OrDash())
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyStatCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.LocationOn, label = "Ülke / Şehir", value = listOf(company.CountryName, company.CityName).filter { it.isNotBlank() }.joinToString(" / ").ifBlank { "-" })
            CompanyStatCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.Verified, label = "Profil Durumu", value = if (company.StatusId > 0) "Aktif" else "Pasif")
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
    CompanyInfoSection(title = "Kurumsal İşlemler", description = "Şirket profilini ticaret akışlarına bağla.", icon = Icons.Outlined.Business) {
        CompanyActionRow(
            title = if (b2bActive) "B2B Index Durumunu Gör" else "Şirketimi B2B Index'e Dahil Et",
            description = if (b2bActive) "B2B görünürlüğünüzü ve abonelik durumunuzu görüntüleyin." else "Tedarikçiler ve toptan alıcılar tarafından keşfedilin.",
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
            BbButton(text = "Tekrar Dene", onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyNotFoundState(onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.Business, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.Section))
            Text(text = "Firma bilgisi bulunamadı", style = BbTypography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            Text(text = "Hesabınıza bağlı şirket kaydı bulunamadı veya görüntülenemiyor.", style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            BbButton(text = "Tekrar Dene", onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
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
    if (Subscription.isNotBlank()) return Subscription
    if (SubscriptionTypeName.isNotBlank() && SubscriptionPlanTypeName.isNotBlank()) return "$SubscriptionTypeName / $SubscriptionPlanTypeName"
    if (SubscriptionTypeName.isNotBlank()) return SubscriptionTypeName
    if (SubscriptionPlanTypeName.isNotBlank()) return SubscriptionPlanTypeName
    return "-"
}


private fun StoreRequestDTO?.GetB2CActionTitle(): String {
    return when (this?.StoreConfirmation) {
        1, 2 -> "Başvuru Durumum"
        3 -> "B2C Mağazamı Yönet"
        else -> "Perakende Mağaza Talebi Oluştur"
    }
}

private fun StoreRequestDTO?.GetB2CActionDescription(): String {
    return when (this?.StoreConfirmation) {
        1, 2 -> "Mağaza başvurunuzun güncel durumunu görüntüleyin."
        3 -> "Perakende mağazanızı ve satış operasyonlarınızı yönetin."
        else -> "Bulbulustur üzerinde perakende mağaza açmak için başvuru oluşturun."
    }
}