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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha

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
                ProfileHeroCard()
            }

            item {
                ProfileCompletionCard()
            }

            item {
                ProfileInfoSection(
                    title = "Temel Bilgiler",
                    description = "Hesabınızın görünen temel bilgileri.",
                    icon = Icons.Outlined.PermIdentity
                ) {
                    ProfileInfoRow(
                        title = "Hesap ID",
                        value = "ME-10000",
                        icon = Icons.Outlined.Badge,
                        onClick = onEditClick
                    )

                    ProfileDashedDivider()

                    ProfileInfoRow(
                        title = "Ad Soyad",
                        value = "Murat Erkan",
                        icon = Icons.Outlined.PermIdentity,
                        onClick = onEditClick
                    )

                    ProfileDashedDivider()

                    ProfileInfoRow(
                        title = "Cinsiyet",
                        value = "Erkek",
                        icon = Icons.Outlined.Man,
                        onClick = onEditClick
                    )

                    ProfileDashedDivider()

                    ProfileInfoRow(
                        title = "Ülke / �?ehir",
                        value = "Türkiye / Ankara",
                        icon = Icons.Outlined.LocationOn,
                        onClick = onEditClick
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
                        value = "muraterkan500@gmail.com",
                        icon = Icons.Outlined.Email,
                        onClick = onEmailClick
                    )

                    ProfileDashedDivider()

                    ProfileInfoRow(
                        title = "Telefonlarım",
                        value = "1 telefon kayıtlı · doğrulama bekliyor",
                        icon = Icons.Outlined.PhoneIphone,
                        onClick = onPhonesClick
                    )

                    ProfileDashedDivider()

                    ProfileInfoRow(
                        title = "E-Posta Doğrulama",
                        value = "Doğrulanmış",
                        icon = Icons.Outlined.Verified,
                        onClick = onEmailClick
                    )
                }
            }

            item {
                ProfileInfoSection(
                    title = "Bulbulustur Kullanımı",
                    description = "Uygulama deneyimini şekillendiren bilgiler.",
                    icon = Icons.Outlined.Tune
                ) {
                    ProfileInfoRow(
                        title = "Kullanım Amacı",
                        value = "Toptan ve perakende alışveriş",
                        icon = Icons.Outlined.Tune,
                        onClick = onUsagePurposeClick
                    )

                    ProfileDashedDivider()

                    ProfileInfoRow(
                        title = "Hesap Modu",
                        value = "Toptan / Perakende",
                        icon = Icons.Outlined.Storefront,
                        onClick = onUsagePurposeClick
                    )
                }
            }

            item {
                ProfileInfoSection(
                    title = "Kurumsal Bağlantı",
                    description = "�?irket ve B2B görünürlük bağlantılarınız.",
                    icon = Icons.Outlined.Business
                ) {
                    ProfileInfoRow(
                        title = "�?irket Bilgileri",
                        value = "Türkiye Global Ticaret Limited �?irketi",
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
private fun ProfileHeroCard() {
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
            Box(
                modifier = Modifier
                    .size(BBIcon.Box2Xl)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = BBRadius.XlShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ME",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

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
private fun ProfileCompletionCard() {
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
                    text = "%70",
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            LinearProgressIndicator(
                progress = {
                    0.70f
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

