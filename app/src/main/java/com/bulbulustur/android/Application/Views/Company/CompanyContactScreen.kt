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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
                title = BBLocalization.Current.Get(key = "8f762765-a4f3-41b4-8b1c-95537a8a0cd7", fallback = "Firma iletişim"),
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
                            text = BBLocalization.Current.Get(key = "78b448f9-43d1-4d49-ac5e-80d4382c98a1", fallback = "Tedarikçi iletişimi"),
                            selected = false,
                            onClick = onCompanyProfileClick
                        )

                        if (company.isVerified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = BBLocalization.Current.Get(key = "c00be3e3-90d4-4f66-ac51-db9a38bac686", fallback = "Doğrulanmış firma"),
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
                        text = BBLocalization.Current.Get(key = "0d617433-ca0e-4ef9-aa49-8500cc28bcd4", fallback = "Firma yetkilisine mesaj gönderin; ürün, teklif, numune veya özel üretim talepleriniz için doğrudan bağlantı kurun."),
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
                    text = BBLocalization.Current.Get(key = "ab200e4f-1f9e-45f4-90a6-7d5d21d33953", fallback = "Profil"),
                    onClick = onCompanyProfileClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Medium
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "6cf7b92f-05e7-4ac7-be8c-ce98d8bf20c5", fallback = "Ürünler"),
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
                        text = BBLocalization.Current.Get(key = "90899dcf-7f66-4cf3-917f-3eeea2d497b2", fallback = "Firma yetkilisi"),
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
                text = BBLocalization.Current.Get(key = "ace8f221-fd2b-43d4-ada9-de36f2bf5af0", fallback = "Adres ve kurumsal iletişim"),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.Language,
                title = BBLocalization.Current.Get(key = "a8fcc3ce-6d1a-40be-b752-974c9b774d7b", fallback = "Web sitesi"),
                value = company.website,
                onClick = onWebsiteClick
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.LocationOn,
                title = BBLocalization.Current.Get(key = "af1da4df-7298-4cd9-b256-371d098b59f7", fallback = "Adres"),
                value = company.address,
                onClick = onAddressClick
            )

            CompanyContactInfoRow(
                icon = Icons.Outlined.Email,
                title = BBLocalization.Current.Get(key = "1246f9ff-205d-4d92-84ee-7c8c7a3f2d46", fallback = ""),
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
                        text = BBLocalization.Current.Get(key = "c294574b-e9e3-4820-b0b2-23b326a7aeb3", fallback = "Mesaj gönder"),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$companyName firmasına teklif, ürün bilgisi, numune veya iş birliği mesajı bırakabilirsiniz.",
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
                        text = BBLocalization.Current.Get(key = "f1b79206-6b85-4636-a67d-636fdb2488d6", fallback = "Mesajınız")
                    )
                },
                placeholder = {
                    Text(
                        text = BBLocalization.Current.Get(key = "428f590d-8666-4b00-9841-7233221c0d93", fallback = "")
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
                text = BBLocalization.Current.Get(key = "1bba90af-aa63-41f8-bd0d-b51c4477afd7", fallback = ""),
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
            title = BBLocalization.Current.Get(key = "d5930a30-ebd5-4391-9488-52649aa50e19", fallback = "Toptan satış ve kurumsal iletişim"),
            isAuthorized = true,
            imageResId = R.drawable.murat_erkan
        ),
        website = "www.ortobella.com",
        address = "Yeni Mah. Çarşamba Cad. No:52 Piazza AVM -1 Kat / Canik / Samsun",
        email = "sales@ortobella.com",
        chips = listOf(
            BBLocalization.Current.Get(key = "5365b492-6a1c-4b46-b5c0-b50cbfdd17a8", fallback = "Türkiye"),
            "Samsun",
            BBLocalization.Current.Get(key = "c6a0ff62-8828-475f-b553-37effb42efe6", fallback = "Doğrulanmış"),
            BBLocalization.Current.Get(key = "95ab742c-6bb3-47da-bb8b-c37b3a979c24", fallback = "Tedarikçi"),
            BBLocalization.Current.Get(key = "a24a620e-b3de-4086-b384-e036e77074cb", fallback = "Hızlı yanıt")
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
