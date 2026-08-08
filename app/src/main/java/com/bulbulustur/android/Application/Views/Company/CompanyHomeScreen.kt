package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ContactMail
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO

@Composable
fun CompanyHomeScreen(
    company: CompanyDTO?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onProductsClick: () -> Unit = {},
    onContactClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { BbInnerPageHeader(title = BBLocalization.Current.Get(key = "aa4d6776-e3ee-4f98-aaec-846aaf364323", fallback = "Tedarikçi Ana Sayfası"), onBackClick = onBackClick) }
    ) { innerPadding ->
        when {
            isLoading && company == null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            company == null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CompanyHomeErrorState(message = errorMessage ?: BBLocalization.Current.Get(key = "4f467a63-d0ab-423f-ae06-65c598e5a641", fallback = "Firma bilgisi bulunamadı"))
                }
            }

            else -> {
                val home = company.toCompanyHome()

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = BBSpacing.PageHorizontal, top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact, end = BBSpacing.PageHorizontal, bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
                ) {
                    item {
                        CompanyHomeHero(company = home, onProductsClick = onProductsClick, onProfileClick = onProfileClick, onContactClick = onContactClick)
                    }

                    item {
                        CompanyHomeShowcaseCard(company = home)
                    }

                    item {
                        CompanyHomeTabs(onProfileClick = onProfileClick, onProductsClick = onProductsClick, onContactClick = onContactClick)
                    }

                    item {
                        CompanyHomeSectionCard(
                            title = "${home.name} Ürün Vitrinleri",
                            subtitle = BBLocalization.Current.Get(key = "5360cc71-4ee0-467a-ba6b-3974b3906012", fallback = "Firmanın öne çıkardığı özel ürün gruplarını ve toptan alıma uygun koleksiyonlarını keşfedin."),
                            items = listOf(BBLocalization.Current.Get(key = "eec14366-a992-4dad-89a1-0b04e54af995", fallback = "Yeni Ürünler"), BBLocalization.Current.Get(key = "7bd92f2e-dbdb-42b1-a9f6-8e5211feb9a4", fallback = "Popüler Ürünler"), BBLocalization.Current.Get(key = "c411d699-8ec1-48ed-be9c-4b500ec2ee30", fallback = "Kurumsal Alıma Uygun Ürünler")),
                            icon = Icons.Outlined.Inventory2,
                            onClick = onProductsClick
                        )
                    }

                    item {
                        CompanyHomeSectionCard(
                            title = BBLocalization.Current.Get(key = "18c05241-483b-4955-8456-000521014ae8", fallback = "Şirket Vitrini"),
                            subtitle = BBLocalization.Current.Get(key = "84f38c3a-2eea-4ca1-9e66-e2db4f857e0b", fallback = "Firmanın öne çıkardığı ürün gruplarını, özel koleksiyonlarını ve ticari vitrinlerini inceleyin."),
                            items = listOf(BBLocalization.Current.Get(key = "418a0a57-7b63-4385-bd80-8b0dbf7a5b46", fallback = "Öne Çıkan Ürün Koleksiyonları"), BBLocalization.Current.Get(key = "21501fa8-971e-43fa-bb3b-c001f2ad2a5b", fallback = "Kurumsal Tedarikçi Profili"), BBLocalization.Current.Get(key = "8289fee7-c637-44af-8fff-1c0c52fe5543", fallback = "Hızlı İletişim ve Teklif Süreci")),
                            icon = Icons.Outlined.Security,
                            onClick = onProfileClick
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(BBSpacing.Space4))
                    }
                }
            }
        }
    }
}

@Composable
private fun CompanyHomeHero(
    company: CompanyHome,
    onProductsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onContactClick: () -> Unit
) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
                CompanyLogoMark(logoText = company.logoText)

                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                        BbChip(text = BBLocalization.Current.Get(key = "f6817d81-c444-414a-8088-5c2fd38099e1", fallback = "Tedarikçi Mağazası"), selected = false, onClick = {})
                        if (company.isVerified) {
                            Icon(imageVector = Icons.Outlined.Verified, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(BBIcon.SizeSm))
                        }
                    }

                    Text(text = company.name, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
                    Text(text = company.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
                company.chips.forEach { chip -> BbChip(text = chip, selected = false, onClick = {}) }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
                BbButton(text = BBLocalization.Current.Get(key = "5e09b1c8-93e6-4e9a-a055-2f556f57d6dc", fallback = "Ürünleri Gör"), onClick = onProductsClick, modifier = Modifier.weight(1f), variant = BbButtonVariant.Primary, size = BbButtonSize.Medium)
                BbButton(text = BBLocalization.Current.Get(key = "ab200e4f-1f9e-45f4-90a6-7d5d21d33953", fallback = "Profil"), onClick = onProfileClick, modifier = Modifier.weight(1f), variant = BbButtonVariant.Outline, size = BbButtonSize.Medium)
            }

            BbButton(text = BBLocalization.Current.Get(key = "a439130c-b2cf-496f-9868-93ef084d9aec", fallback = "İletişime Geç"), onClick = onContactClick, modifier = Modifier.fillMaxWidth(), variant = BbButtonVariant.Secondary, size = BbButtonSize.Medium)
        }
    }
}

@Composable
private fun CompanyLogoMark(logoText: String) {
    Surface(modifier = Modifier.size(72.dp), shape = BBRadius.XlShape, color = MaterialTheme.colorScheme.surface, border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(imageVector = Icons.Outlined.Business, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(BBIcon.SizeLg))
            Text(text = logoText, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun CompanyHomeShowcaseCard(company: CompanyHome) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
                Surface(modifier = Modifier.size(42.dp), shape = BBRadius.LgShape, color = MaterialTheme.colorScheme.primary) {
                    Icon(imageVector = Icons.Outlined.Security, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(BBIcon.SizeMd))
                }

                Column {
                    Text(text = BBLocalization.Current.Get(key = "18c05241-483b-4955-8456-000521014ae8", fallback = "Şirket Vitrini"), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
                    Text(text = company.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            CompanyHomeInfoLine(BBLocalization.Current.Get(key = "418a0a57-7b63-4385-bd80-8b0dbf7a5b46", fallback = "Öne Çıkan Ürün Koleksiyonları"))
            CompanyHomeInfoLine(BBLocalization.Current.Get(key = "21501fa8-971e-43fa-bb3b-c001f2ad2a5b", fallback = "Kurumsal Tedarikçi Profili"))
            CompanyHomeInfoLine(BBLocalization.Current.Get(key = "8289fee7-c637-44af-8fff-1c0c52fe5543", fallback = "Hızlı İletişim ve Teklif Süreci"))
        }
    }
}

@Composable
private fun CompanyHomeInfoLine(text: String) {
    Surface(modifier = Modifier.fillMaxWidth(), shape = BBRadius.LgShape, color = MaterialTheme.colorScheme.primaryContainer, border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            Icon(imageVector = Icons.Outlined.Verified, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(BBIcon.SizeMd).weight(BBAlpha.Overlay))
            Text(modifier = Modifier.weight(1f), text = text, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun CompanyHomeTabs(onProfileClick: () -> Unit, onProductsClick: () -> Unit, onContactClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyHomeTabChip(text = BBLocalization.Current.Get(key = "fe9c56ac-dbc2-4fc6-afe0-bb3f7cf1f8f7", fallback = "Ana Sayfa"), icon = Icons.Outlined.Business, selected = true, onClick = {})
            CompanyHomeTabChip(text = BBLocalization.Current.Get(key = "ab200e4f-1f9e-45f4-90a6-7d5d21d33953", fallback = "Profil"), icon = Icons.Outlined.Business, selected = false, onClick = onProfileClick)
            CompanyHomeTabChip(text = BBLocalization.Current.Get(key = "6cf7b92f-05e7-4ac7-be8c-ce98d8bf20c5", fallback = "Ürünler"), icon = Icons.Outlined.Inventory2, selected = false, onClick = onProductsClick)
            CompanyHomeTabChip(text = BBLocalization.Current.Get(key = "0cf2cda1-7cf6-4d8b-ab56-8918e3a260fd", fallback = "İletişim"), icon = Icons.Outlined.ContactMail, selected = false, onClick = onContactClick)
        }
    }
}

@Composable
private fun CompanyHomeTabChip(text: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit) {
    Surface(onClick = onClick, shape = BBRadius.PillShape, color = if (selected) BBColors.Blue.Blue50 else MaterialTheme.colorScheme.surface, border = BorderStroke(width = 1.dp, color = if (selected) BBColors.Blue.Blue200 else MaterialTheme.colorScheme.outlineVariant)) {
        Row(modifier = Modifier.height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
            Spacer(modifier = Modifier.size(BBSpacing.Space2))
            Icon(imageVector = icon, contentDescription = null, tint = if (selected) BBColors.Blue.Blue700 else MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(BBIcon.SizeSm))
            Text(text = text, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(BBSpacing.Space2))
        }
    }
}

@Composable
private fun CompanyHomeSectionCard(title: String, subtitle: String, items: List<String>, icon: ImageVector, onClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium, onClick = onClick) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
                Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
            }

            Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            items.forEach { item -> CompanyHomeInfoLine(text = item) }
        }
    }
}

@Composable
private fun CompanyHomeErrorState(message: String) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.ErrorOutline, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(BBIcon.Section))
            Text(text = message, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Immutable
private data class CompanyHome(
    val companyId: Int,
    val name: String,
    val logoText: String,
    val description: String,
    val isVerified: Boolean,
    val chips: List<String>
)

private fun CompanyDTO.toCompanyHome(): CompanyHome {
    val name = CompanyName.trim().ifBlank { "Tedarikçi" }
    val description = firstNotBlank(Slogan, SeoDescription, CompanyType, "Firma güveni, tedarik yapısı ve öne çıkan ticari bilgiler.")
    val chips = listOfNotNull(blankToNull(CountryName), blankToNull(CityName), if (Verified) BBLocalization.Current.Get(key = "c6a0ff62-8828-475f-b553-37effb42efe6", fallback = "Doğrulanmış") else null, blankToNull(CompanyType))
    return CompanyHome(companyId = CompanyId, name = name, logoText = name.toLogoText(), description = description, isVerified = Verified, chips = chips)
}

private fun blankToNull(value: String?): String? = value?.trim()?.takeIf { it.isNotEmpty() }

private fun firstNotBlank(vararg values: String?): String {
    return values.firstOrNull { !it.isNullOrBlank() }?.trim().orEmpty()
}

private fun String.toLogoText(): String {
    val parts = trim().split(" ").filter { it.isNotBlank() }
    return parts.take(2).mapNotNull { it.firstOrNull()?.uppercaseChar()?.toString() }.joinToString("").ifBlank { "B" }
}

@Preview(showBackground = true)
@Composable
private fun CompanyHomeScreenPreview() {
    BbTheme {
        CompanyHomeScreen(
            company = CompanyDTO(CompanyId = 1, CompanyName = "Bulbulustur İnternet Teknolojileri ve Tic. A.Ş.", CountryName = "Türkiye", CityName = "İstanbul", CompanyType = "Tedarikçi", Verified = true, Slogan = "Firma güveni, tedarik yapısı ve öne çıkan ticari bilgiler.")
        )
    }
}
