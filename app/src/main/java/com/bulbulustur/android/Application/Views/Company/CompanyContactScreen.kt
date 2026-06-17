package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun CompanyContactScreen(
    companyId: Int = 1,
    onBackClick: () -> Unit = {},
    onCompanyProfileClick: () -> Unit = {},
    onCompanyProductsClick: () -> Unit = {},
    onSendClick: () -> Unit = {}
) {
    val company = remember(companyId) {
        getCompanyContact(companyId)
    }

    val message = remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = BBColors.SurfaceSoft,
        topBar = {
            BbInnerPageHeader(
                title = "Firma İletişim",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                CompanyContactHero(
                    company = company,
                    onCompanyProfileClick = onCompanyProfileClick,
                    onCompanyProductsClick = onCompanyProductsClick
                )
            }

            item {
                CompanyContactPersonCard(
                    company = company
                )
            }

            item {
                CompanyContactInfoCard(
                    company = company
                )
            }

            item {
                CompanyMessageCard(
                    companyName = company.name,
                    message = message.value,
                    onMessageChange = {
                        message.value = it
                    },
                    onSendClick = onSendClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@Composable
private fun CompanyContactHero(
    company: CompanyContact,
    onCompanyProfileClick: () -> Unit,
    onCompanyProductsClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                CompanyContactLogo(
                    logoText = company.logoText
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        BbChip(
                            text = "Tedarikçi İletişimi",
                            selected = false,
                            onClick = {}
                        )

                        Icon(
                            imageVector = Icons.Outlined.Verified,
                            contentDescription = null,
                            tint = BBColors.Primary,
                            modifier = Modifier.size(BBIcon.SizeSm)
                        )
                    }

                    Text(
                        text = "${company.name} İle İletişime Geç",
                        style = MaterialTheme.typography.headlineSmall,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Firma yetkilisine mesaj gönderin, ürün, teklif, numune veya özel üretim talepleriniz için doğrudan bağlantı kurun.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BBColors.TextMuted
                    )
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                company.chips.forEach { chip ->
                    BbChip(
                        text = chip,
                        selected = false,
                        onClick = {}
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = "Profil",
                    onClick = onCompanyProfileClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = "Ürünler",
                    onClick = onCompanyProductsClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun CompanyContactLogo(
    logoText: String
) {
    Surface(
        modifier = Modifier.size(72.dp),
        shape = BBRadius.XlShape,
        color = BBColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = BBColors.Primary,
                modifier = Modifier.size(BBIcon.SizeLg)
            )

            Text(
                text = logoText,
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CompanyContactPersonCard(
    company: CompanyContact
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = BBRadius.XlShape,
                color = BBColors.PrimarySoft,
                border = BorderStroke(
                    width = 1.dp,
                    color = BBColors.Border
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        tint = BBColors.Primary,
                        modifier = Modifier.size(BBIcon.SizeLg)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Yetkili Kişi",
                    style = MaterialTheme.typography.labelMedium,
                    color = BBColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = company.contactPerson,
                    style = MaterialTheme.typography.titleMedium,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = company.contactTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun CompanyContactInfoCard(
    company: CompanyContact
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = "Adres Ve Kurumsal İletişim",
                style = MaterialTheme.typography.titleMedium,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.Language,
                title = "Web Sitesi",
                value = company.website
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.LocationOn,
                title = "Adres",
                value = company.address
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.Email,
                title = "E-Posta",
                value = company.email
            )
        }
    }
}

@Composable
private fun CompanyContactInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.LgShape,
        color = BBColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Spacer(modifier = Modifier.size(BBSpacing.Space3))

            Surface(
                modifier = Modifier.size(42.dp),
                shape = BBRadius.LgShape,
                color = BBColors.PrimarySoft
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BBColors.Primary,
                    modifier = Modifier.size(BBIcon.SizeMd)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(72.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = BBColors.TextMuted
                )

                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.size(BBSpacing.Space3))
        }
    }
}

@Composable
private fun CompanyMessageCard(
    companyName: String,
    message: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Surface(
                    modifier = Modifier.size(42.dp),
                    shape = BBRadius.LgShape,
                    color = BBColors.Primary
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        tint = BBColors.TextStrong,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }

                Column {
                    Text(
                        text = "Mesaj Gönder",
                        style = MaterialTheme.typography.titleMedium,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$companyName firmasına teklif, ürün bilgisi, numune veya iş birliği mesajı bırakabilirsiniz.",
                        style = MaterialTheme.typography.bodySmall,
                        color = BBColors.TextMuted
                    )
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                value = message,
                onValueChange = onMessageChange,
                label = {
                    Text(text = "Mesajınız")
                },
                placeholder = {
                    Text(text = "Ürün gereksinimlerinizi ve şirket bilgilerinizi burada detaylandırabilirsiniz.")
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = BBColors.TextStrong,
                    unfocusedTextColor = BBColors.TextStrong,
                    focusedContainerColor = BBColors.Surface,
                    unfocusedContainerColor = BBColors.Surface,
                    focusedIndicatorColor = BBColors.Primary,
                    unfocusedIndicatorColor = BBColors.Border,
                    focusedLabelColor = BBColors.Primary,
                    unfocusedLabelColor = BBColors.TextMuted,
                    cursorColor = BBColors.Primary
                )
            )

            BbButton(
                text = "Gönder",
                onClick = onSendClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Large,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@Immutable
private data class CompanyContact(
    val companyId: Int,
    val name: String,
    val logoText: String,
    val contactPerson: String,
    val contactTitle: String,
    val website: String,
    val address: String,
    val email: String,
    val chips: List<String>
)

private fun getCompanyContact(
    companyId: Int
): CompanyContact {
    return CompanyContact(
        companyId = companyId,
        name = "Ortobella Comfort",
        logoText = "OC",
        contactPerson = "Yetkili Kişi",
        contactTitle = "Toptan Satış Ve Kurumsal İletişim",
        website = "www.ortobella.com",
        address = "Yeni Mah. Çarşamba Cad. No:52 Piazza AVM -1 Kat / Canik / Samsun",
        email = "sales@ortobella.com",
        chips = listOf(
            "Türkiye",
            "Samsun",
            "Doğrulanmış",
            "Tedarikçi",
            "Hızlı Yanıt"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CompanyContactScreenPreview() {
    BbTheme {
        CompanyContactScreen()
    }
}
