package com.bulbulustur.android.Application.Views.Company

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.R
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
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
    onWebsiteClick: (String) -> Unit = {},
    onAddressClick: (String) -> Unit = {},
    onEmailClick: (String) -> Unit = {},
    onSendClick: (String) -> Unit = {}
) {
    val company = remember(companyId) {
        getCompanyContact(companyId)
    }

    var message by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Firma iletişim",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() +
                        BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() +
                        BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.SectionGapCompact
            )
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
                    contactPerson = company.contactPerson
                )
            }

            item {
                CompanyContactInfoCard(
                    company = company,
                    onWebsiteClick = {
                        onWebsiteClick(company.website)
                    },
                    onAddressClick = {
                        onAddressClick(company.address)
                    },
                    onEmailClick = {
                        onEmailClick(company.email)
                    }
                )
            }

            item {
                CompanyMessageCard(
                    companyName = company.name,
                    message = message,
                    onMessageChange = {
                        message = it
                    },
                    onSendClick = {
                        if (message.isNotBlank()) {
                            onSendClick(message)
                        }
                    }
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(
                        BBSpacing.Space4
                    )
                )
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
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                CompanyContactLogo(
                    logoText = company.logoText
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            BBSpacing.Space1
                        )
                    ) {
                        BbChip(
                            text = "Tedarikçi iletişimi",
                            selected = false,
                            onClick = onCompanyProfileClick
                        )

                        if (company.isVerified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = "DoĞrulanmış firma",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(
                                    BBIcon.SizeSm
                                )
                            )
                        }
                    }

                    Text(
                        text = "${company.name} ile iletişime geç",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Firma yetkilisine mesaj gönderin; ürün, teklif, numune veya özel üretim talepleriniz için doĞrudan baĞlantı kurun.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                ),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
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
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
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
        modifier = Modifier.size(
            BBIcon.BoxXl
        ),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(
                    BBIcon.SizeLg
                )
            )

            Text(
                text = logoText,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CompanyContactPersonCard(
    contactPerson: CompanyContactPerson
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            CompanyContactPersonAvatar(
                fullName = contactPerson.fullName,
                imageResId = contactPerson.imageResId
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = contactPerson.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = contactPerson.title,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (contactPerson.isAuthorized) {
                    Text(
                        text = "Firma yetkilisi",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun CompanyContactPersonAvatar(
    fullName: String,
    @DrawableRes imageResId: Int?
) {
    Surface(
        modifier = Modifier.size(
            BBIcon.BoxXl
        ),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        if (imageResId != null) {
            Image(
                painter = painterResource(
                    id = imageResId
                ),
                contentDescription = fullName,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(BBRadius.XlShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getInitials(fullName),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun CompanyContactInfoCard(
    company: CompanyContact,
    onWebsiteClick: () -> Unit,
    onAddressClick: () -> Unit,
    onEmailClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Text(
                text = "Adres ve kurumsal iletişim",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.Language,
                title = "Web sitesi",
                value = company.website,
                onClick = onWebsiteClick
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.LocationOn,
                title = "Adres",
                value = company.address,
                onClick = onAddressClick
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.Email,
                title = "E-posta",
                value = company.email,
                onClick = onEmailClick
            )
        }
    }
}

@Composable
private fun CompanyContactInfoRow(
    icon: ImageVector,
    title: String,
    value: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.BorderThin,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Surface(
                modifier = Modifier.size(
                    BBIcon.BoxMd
                ),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(
                            BBIcon.SizeMd
                        )
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
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
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space2
                )
            ) {
                Surface(
                    modifier = Modifier.size(
                        BBIcon.BoxMd
                    ),
                    shape = BBRadius.LgShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Send,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(
                                BBIcon.SizeMd
                            )
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
                ) {
                    Text(
                        text = "Mesaj gönder",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$companyName firmasına teklif, ürün bilgisi, numune veya iş birliĞi mesajı bırakabilirsiniz.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        BBSpacing.Space24 +
                                BBSpacing.Space16
                    ),
                value = message,
                onValueChange = onMessageChange,
                label = {
                    Text(
                        text = "Mesajınız"
                    )
                },
                placeholder = {
                    Text(
                        text = "Ürün gereksinimlerinizi ve şirket bilgilerinizi burada detaylandırabilirsiniz."
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )

            BbButton(
                text = "Gönder",
                onClick = onSendClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Large,
                enabled = message.isNotBlank(),
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

private fun getInitials(
    fullName: String
): String {
    return fullName
        .trim()
        .split(Regex("\\s+"))
        .filter { part ->
            part.isNotBlank()
        }
        .take(2)
        .mapNotNull { part ->
            part.firstOrNull()?.uppercaseChar()
        }
        .joinToString(separator = "")
        .ifBlank {
            "?"
        }
}

@Immutable
private data class CompanyContact(
    val companyId: Int,
    val name: String,
    val logoText: String,
    val isVerified: Boolean,
    val contactPerson: CompanyContactPerson,
    val website: String,
    val address: String,
    val email: String,
    val chips: List<String>
)

@Immutable
private data class CompanyContactPerson(
    val fullName: String,
    val title: String,
    val isAuthorized: Boolean,
    @DrawableRes val imageResId: Int?
)

private fun getCompanyContact(
    companyId: Int
): CompanyContact {
    return CompanyContact(
        companyId = companyId,
        name = "Ortobella Comfort",
        logoText = "OC",
        isVerified = true,
        contactPerson = CompanyContactPerson(
            fullName = "Murat Erkan",
            title = "Toptan satış ve kurumsal iletişim",
            isAuthorized = true,
            imageResId = R.drawable.murat_erkan
        ),
        website = "www.ortobella.com",
        address = "Yeni Mah. Çarşamba Cad. No:52 Piazza AVM -1 Kat / Canik / Samsun",
        email = "sales@ortobella.com",
        chips = listOf(
            "Türkiye",
            "Samsun",
            "DoĞrulanmış",
            "Tedarikçi",
            "Hızlı yanıt"
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
