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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material.icons.outlined.PermIdentity
import androidx.compose.material.icons.outlined.PhoneIphone
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxIcon
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onPhonesClick: () -> Unit = {},
    onEmailClick: () -> Unit = {},
    onUsagePurposeClick: () -> Unit = {},
    onCompanyInfoClick: () -> Unit = {},
    onB2BStatusClick: () -> Unit = {}
) {
    var showProfilePhotoSheet by remember {
        mutableStateOf(false)
    }

    var hasProfilePhoto by remember {
        mutableStateOf(false)
    }

    val profileCompletion = if (hasProfilePhoto) {
        0.90f
    } else {
        0.70f
    }

    if (showProfilePhotoSheet) {
        ProfilePhotoSheet(
            hasProfilePhoto = hasProfilePhoto,
            onDismiss = {
                showProfilePhotoSheet = false
            },
            onTakePhotoClick = {
                hasProfilePhoto = true
                showProfilePhotoSheet = false
            },
            onSelectFromGalleryClick = {
                hasProfilePhoto = true
                showProfilePhotoSheet = false
            },
            onRemovePhotoClick = {
                hasProfilePhoto = false
                showProfilePhotoSheet = false
            }
        )
    }

    val pageBackground = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = BBAlpha.DisabledLabel),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.80f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
        )
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Profil Bilgileri",
                onBackClick = onBackClick,
                actionIcon = Icons.Outlined.Edit,
                actionContentDescription = "Profili Düzenle",
                onActionClick = onEditClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(pageBackground)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                ProfileHeroCard(
                    hasProfilePhoto = hasProfilePhoto,
                    onProfilePhotoClick = {
                        showProfilePhotoSheet = true
                    }
                )
            }

            item {
                ProfileCompletionCard(
                    progress = profileCompletion,
                    percentText = "%${(profileCompletion * 100).toInt()}"
                )
            }

            item {
                ProfileInfoSection(
                    title = "Temel Bilgiler",
                    description = "Hesabınızın görünen temel bilgileri.",
                    icon = Icons.Outlined.PermIdentity
                ) {
                    ProfileInfoRow("Hesap ID", "ME-10000", Icons.Outlined.Badge, onEditClick)
                    ProfileDashedDivider()
                    ProfileInfoRow("Ad Soyad", "Murat Erkan", Icons.Outlined.PermIdentity, onEditClick)
                    ProfileDashedDivider()
                    ProfileInfoRow("Cinsiyet", "Erkek", Icons.Outlined.Man, onEditClick)
                    ProfileDashedDivider()
                    ProfileInfoRow("Ülke / Şehir", "Türkiye / Ankara", Icons.Outlined.LocationOn, onEditClick)
                }
            }

            item {
                ProfileInfoSection(
                    title = "Doğrulama",
                    description = "Güvenlik ve hesap doğrulama bilgileri.",
                    icon = Icons.Outlined.Security
                ) {
                    ProfileInfoRow("E-Posta", "muraterkan500@gmail.com", Icons.Outlined.Email, onEmailClick)
                    ProfileDashedDivider()
                    ProfileInfoRow("Telefonlarım", "1 telefon kayıtlı · doğrulama bekliyor", Icons.Outlined.PhoneIphone, onPhonesClick)
                    ProfileDashedDivider()
                    ProfileInfoRow("E-Posta Doğrulama", "Doğrulanmış", Icons.Outlined.Verified, onEmailClick)
                }
            }

            item {
                ProfileInfoSection(
                    title = "Bulbulustur Kullanımı",
                    description = "Uygulama deneyimini şekillendiren bilgiler.",
                    icon = Icons.Outlined.Tune
                ) {
                    ProfileInfoRow("Kullanım Amacı", "Toptan ve perakende alışveriş", Icons.Outlined.Tune, onUsagePurposeClick)
                    ProfileDashedDivider()
                    ProfileInfoRow("Hesap Modu", "Toptan / Perakende", Icons.Outlined.Storefront, onUsagePurposeClick)
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

@Composable
private fun ProfileHeroCard(
    hasProfilePhoto: Boolean,
    onProfilePhotoClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = BBRadius.XlShape
            )
            .padding(BBSpacing.CardPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileAvatarBox(
                hasProfilePhoto = hasProfilePhoto,
                onClick = onProfilePhotoClick
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Profil Bilgileri",
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Murat Erkan",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )

                Text(
                    text = "ME-10000 · muraterkan500@gmail.com",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Muted)
                )
            }
        }
    }
}

@Composable
private fun ProfileAvatarBox(
    hasProfilePhoto: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(BBIcon.Box2Xl),
        contentAlignment = Alignment.Center
    ) {
        BbCard(
            modifier = Modifier.size(BBIcon.Box2Xl),
            variant = BbCardVariant.Default,
            padding = BbCardPadding.None,
            onClick = onClick
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = BBRadius.XlShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (hasProfilePhoto) {
                    Icon(
                        imageVector = Icons.Outlined.PermIdentity,
                        contentDescription = "Profil fotoğrafı",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BBIcon.Size3Xl)
                    )
                } else {
                    Text(
                        text = "ME",
                        style = BbTypography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        BbIconBoxIcon(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(
                    x = BBSpacing.Space1,
                    y = BBSpacing.Space1
                ),
            icon = Icons.Outlined.CameraAlt,
            contentDescription = "Profil fotoğrafı değiştir",
            size = BbIconBoxSize.Small,
            backgroundColor = MaterialTheme.colorScheme.surface,
            iconColor = MaterialTheme.colorScheme.onSurface,
            borderColor = MaterialTheme.colorScheme.outlineVariant,
            borderWidth = BBSpacing.BorderThin,
            bordered = true,
            radius = BBRadius.md,
            onClick = onClick
        )
    }
}

@Composable
private fun ProfileCompletionCard(
    progress: Float,
    percentText: String
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BBRadius.PillShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Verified,
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
                        text = "Profil Tamamlanma Durumu",
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Telefon doğrulaması ve profil fotoğrafı ile hesabınızı güçlendirebilirsiniz.",
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = percentText,
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            LinearProgressIndicator(
                progress = {
                    progress
                },
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
            )
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
private fun ProfileDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.Space4,
                end = BBSpacing.Space4
            )
            .size(height = 1.dp, width = BBSpacing.BorderThin)
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