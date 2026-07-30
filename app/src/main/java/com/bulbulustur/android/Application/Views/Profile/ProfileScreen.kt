package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import androidx.compose.foundation.layout.Row



import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
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
import com.bulbulustur.android.businesslayer.Core.DTO.MemberProfileDTO
import com.bulbulustur.android.businesslayer.Core.Network.MemberPictureUrlResolver

@Suppress("UNUSED_PARAMETER")
@Composable
fun ProfileScreen(
    member: MemberDTO?,
    memberProfile: MemberProfileDTO?,
    memberPicture: String = "",
    displayValues: ProfileDisplayValues = ProfileDisplayValues(),
    languagesSummary: String = "Belirtilmemiş",
    addressCascadeState: AddressCascadeState,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onBioClick: () -> Unit = {},
    onLanguagesClick: () -> Unit = {},
    onLifestyleClick: () -> Unit = {},
    onRelationshipClick: () -> Unit = {},

    onEducationWorkClick: () -> Unit = {},
    onEducationClick: () -> Unit = {},
    onProfessionClick: () -> Unit = {},
    onJobTitleClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onPhotoClick: () -> Unit = {},
    onGenderClick: () -> Unit = {},
    onBirthDateClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPhonesClick: () -> Unit = {},
    onEmailClick: () -> Unit = {},
    onCompanyInfoClick: () -> Unit = {},
    onB2BStatusClick: () -> Unit = {}
) {
    val unspecified = "Belirtilmemiş"

    val fullName = listOfNotNull(
        member?.Name?.trim()?.takeIf { it.isNotBlank() },
        member?.Surname?.trim()?.takeIf { it.isNotBlank() }
    ).joinToString(" ").ifBlank {
        "Profil bilgisi bulunamadı"
    }

    val profession = member
        ?.Profession
        ?.trim()
        ?.takeIf { it.isNotBlank() }
        ?: unspecified

    val memberPictureUrl =
        MemberPictureUrlResolver.Resolve(
            member?.Picture
                ?.takeIf { it.isNotBlank() }
                ?: memberPicture
        )

    val bio = memberProfile
        ?.Bio
        ?.trim()
        ?.takeIf { it.isNotBlank() }
        ?: unspecified

    val jobTitle = memberProfile
        ?.JobTitle
        ?.trim()
        ?.takeIf { it.isNotBlank() }
        ?: unspecified

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Profilim",
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
                isLoading || (
                    member == null &&
                        memberProfile == null &&
                        errorMessage.isNullOrBlank()
                    ) -> {
                    item {
                        ProfileLoadingState()
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    item {
                        ProfileMessageCard(
                            message = errorMessage,
                            isError = true
                        )
                    }
                }

                member == null -> {
                    item {
                        ProfileMessageCard(
                            message = "Profil bilgisi bulunamadı."
                        )
                    }
                }

                else -> {
                    item {
                        ProfileHeroCard(
                            fullName = fullName,
                            profession = profession,
                            pictureUrl = memberPictureUrl,
                            onClick = onEditClick
                        )
                    }

                    item {
                        ProfileSectionCard(
                            title = "Profil Özeti",
                            description = "Profilinizde görünen kısa tanıtım bilginiz.",
                            icon = Icons.Outlined.Person
                        ) {
                            ProfileFieldRow(
                                title = "Hakkımda",
                                value = bio,
                                icon = Icons.Outlined.Description,
                                onClick = onBioClick
                            )
                        }
                    }

                    item {
                        ProfileSectionCard(
                            title = "Diller",
                            description = "Konuştuğunuz dilleri ve seviyelerini yönetin.",
                            icon = Icons.Outlined.Translate
                        ) {
                            ProfileFieldRow(
                                title = "Dillerim",
                                value = languagesSummary,
                                icon = Icons.Outlined.Translate,
                                onClick = onLanguagesClick
                            )
                        }
                    }

                   item {
                        ProfileSectionCard(
                            title = "Eğitim ve İş",
                            description = "Eğitim ve çalışma hayatınız.",
                            icon = Icons.Outlined.School
                        ) {
                            ProfileFieldRow(
                                title = "Eğitim Düzeyi",
                                value = displayValues.Education,
                                icon = Icons.Outlined.School,
                                onClick = onEducationClick
                            )

                            ProfileDivider()

                            ProfileFieldRow(
                                title = "Meslek",
                                value = profession,
                                icon = Icons.Outlined.WorkOutline,
                                onClick = onProfessionClick
                            )

                            ProfileDivider()

                            ProfileFieldRow(
                                title = "İş Unvanı",
                                value = jobTitle,
                                icon = Icons.Outlined.Badge,
                                onClick = onJobTitleClick
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun Boolean?.toDisplayText(unspecified: String): String {
    return when (this) {
        true -> "Evet"
        false -> "Hayır"
        null -> unspecified
    }
}

@Composable
private fun ProfileHeroCard(
    fullName: String,
    profession: String,
    pictureUrl: String,
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
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.XlShape
                    )
                    .clip(BBRadius.XlShape),
                contentAlignment = Alignment.Center
            ) {
                if (pictureUrl.isNotBlank()) {
                    AsyncImage(
                        model = pictureUrl,
                        contentDescription = "Profil fotoğrafı",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BBIcon.Section)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = fullName,
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = profession,
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            ProfileChevron()
        }
    }
}

@Composable
private fun ProfileSectionCard(
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
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
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
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = title,
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = description,
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            ProfileDivider()

            Column {
                content()
            }
        }
    }
}

@Composable
private fun ProfileFieldRow(
    title: String,
    value: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
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

        ProfileChevron()
    }
}

@Composable
private fun ProfileChevron() {
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

@Composable
private fun ProfileDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.Space4,
                end = BBSpacing.Space4
            )
            .height(1.dp)
    ) {
        drawLine(
            color = dividerColor,
            start = Offset.Zero,
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(10f, 8f),
                phase = 0f
            )
        )
    }
}

@Composable
private fun ProfileLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.Space8),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ProfileMessageCard(
    message: String,
    isError: Boolean = false
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = message,
            style = BbTypography.bodySmall,
            color = if (isError) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    }
}
