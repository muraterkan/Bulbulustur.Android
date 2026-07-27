package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material.icons.outlined.PermIdentity
import androidx.compose.material.icons.outlined.PhoneIphone
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeState
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Network.MemberPictureUrlResolver

@Composable
fun AccountScreen(
    member: MemberDTO?,
    addressCascadeState: AddressCascadeState,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onGenderClick: () -> Unit = {},
    onBirthDateClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPhonesClick: () -> Unit = {},
    onEmailClick: () -> Unit = {},
    onCompanyInfoClick: () -> Unit = {},
    onB2BStatusClick: () -> Unit = {}
) {
    val fullName = listOfNotNull(
        member?.Name?.trim()?.takeIf { it.isNotBlank() },
        member?.Surname?.trim()?.takeIf { it.isNotBlank() }
    ).joinToString(" ").ifBlank {
        "Hesap Bilgisi Bulunamadı"
    }

    val email = member?.Email.orEmpty().ifBlank {
        "E-posta bilgisi bulunamadı"
    }

    val countryName = addressCascadeState.Countries
        .firstOrNull {
            it.AddressCountryId == member?.CountryId
        }
        ?.Content
        .orEmpty()

    val cityName = addressCascadeState.Cities
        .firstOrNull {
            it.AddressCityId == member?.CityId
        }
        ?.Content
        .orEmpty()

    val locationText = listOf(countryName, cityName)
        .filter { it.isNotBlank() }
        .joinToString(" / ")
        .ifBlank {
            "Konum bilgisi bulunamadı"
        }

    val genderText = member
        ?.GenderId
        ?.takeIf { it > 0 }
        ?.toString()
        ?: "Belirtilmemiş"

    val birthDateText = member
        ?.BirthDate
        ?.trim()
        ?.take(10)
        ?.takeIf { it.isNotBlank() }
        ?: "Belirtilmemiş"

    val activationText = when (member?.Activation) {
        true -> "Doğrulanmış"
        false -> "Doğrulanmamış"
        null -> "Bilinmiyor"
    }

    val memberPictureUrl =
        MemberPictureUrlResolver.Resolve(
            member?.Picture
        )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Hesabım",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            when {
                isLoading || (member == null && errorMessage.isNullOrBlank()) -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(BBSpacing.Space6),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    item {
                        ProfileErrorCard(
                            message = errorMessage
                        )
                    }
                }

                member == null -> {
                    item {
                        ProfileNotFoundCard()
                    }
                }

                else -> {
                    item {
                        ProfileHeroCard(
                            fullName = fullName,
                            onClick = onProfileClick
                        )
                    }

                    item {
                        ProfileInfoSection(
                            title = "Temel Bilgiler",
                            description = "Hesabınızın görünen temel bilgileri.",
                            icon = Icons.Outlined.PermIdentity
                        ) {

                            ProfileDashedDivider()

                            ProfileInfoRow(
                                title = "Ad Soyad",
                                value = fullName,
                                icon = Icons.Outlined.PermIdentity,
                                onClick = onEditClick
                            )

                            ProfileDashedDivider()

                            ProfileInfoRow(
                                title = "Cinsiyet",
                                value = genderText,
                                icon = Icons.Outlined.Man,
                                onClick = onGenderClick
                            )

                            ProfileDashedDivider()

                            ProfileInfoRow(
                                title = "Doğum Tarihi",
                                value = birthDateText,
                                icon = Icons.Outlined.Badge,
                                onClick = onBirthDateClick
                            )

                            ProfileDashedDivider()

                            ProfileInfoRow(
                                title = "Ülke / Şehir",
                                value = locationText,
                                icon = Icons.Outlined.LocationOn,
                                onClick = onAddressClick
                            )
                        }
                    }

                    item {
                        ProfileInfoSection(
                            title = "Doğrulama",
                            description = "Güvenlik ve hesap doğrulama bilgileri.",
                            icon = Icons.Outlined.Security
                        ) {
                            ProfileInfoRow(
                                title = "E-Posta",
                                value = email,
                                icon = Icons.Outlined.Email,
                                onClick = onEmailClick
                            )

                            ProfileDashedDivider()

                            ProfileInfoRow(
                                title = "Telefonlarım",
                                value = "Telefon bilgilerini yönetin",
                                icon = Icons.Outlined.PhoneIphone,
                                onClick = onPhonesClick
                            )

                            ProfileDashedDivider()

                            ProfileInfoRow(
                                title = "E-Posta Doğrulama",
                                value = activationText,
                                icon = Icons.Outlined.Verified,
                                onClick = onEmailClick
                            )
                        }
                    }

                    item {
                        ProfileInfoSection(
                            title = "Kurumsal Bağlantı",
                            description = "Şirket ve B2B görünürlük bağlantılarınız.",
                            icon = Icons.Outlined.Business
                        ) {
                            ProfileInfoRow(
                                title = "Şirket Bilgileri",
                                value = "Türkiye Global Ticaret Limited Şirketi",
                                icon = Icons.Outlined.Business,
                                onClick = onCompanyInfoClick
                            )

                            ProfileDashedDivider()

                            ProfileInfoRow(
                                title = "B2B Index",
                                value = "Aktif",
                                icon = Icons.Outlined.Verified,
                                onClick = onB2BStatusClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileHeroCard(
    fullName: String,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxXl)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.XlShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.PermIdentity,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = fullName,
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Profili görüntüle ve düzenle",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Box(
                modifier = Modifier
                    .size(BBIcon.BoxSm)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "Profile git",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )
            }
        }
    }
}
@Composable
private fun ProfileInfoSection(
    title: String,
    description: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BBRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BBIcon.Ui)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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

            ProfileDashedDivider()

            Column {
                content()
            }
        }
    }
}

@Composable
private fun ProfileInfoRow(
    title: String,
    value: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BBIcon.BoxMd)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BBRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.Ui)
            )
        }


        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Box(
            modifier = Modifier
                .size(BBIcon.BoxSm)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BBRadius.PillShape
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

@Composable
private fun ProfileErrorCard(
    message: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = message,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun ProfileNotFoundCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Profil bilgisi bulunamadı",
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProfileDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.Space4,
                end = BBSpacing.Space4
            )
            .size(
                height = 1.dp,
                width = BBSpacing.BorderThin
            )
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