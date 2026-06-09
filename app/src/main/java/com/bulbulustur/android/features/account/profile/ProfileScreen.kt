package com.bulbulustur.android.features.account.profile

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

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
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.80f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(pageBackground)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentPadding = PaddingValues(
            horizontal = BbSpacing.PageHorizontal,
            vertical = BbSpacing.PageTopCompact
        ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        item {
            ProfileHeroCard(
                onBackClick = onBackClick,
                onEditClick = onEditClick
            )
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
                    title = "Ülke / Şehir",
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
                    title = "E-posta",
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
                    title = "E-posta Doğrulama",
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

@Composable
private fun ProfileHeroCard(
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = BbRadius.XlShape
            )
            .padding(BbSpacing.CardPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            BbButton(
                text = "Hesabıma Dön",
                onClick = onBackClick,
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.Box2Xl)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = BbRadius.XlShape
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
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
                        color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.72f)
                    )
                }
            }

            BbButton(
                text = "Profili Düzenle",
                onClick = onEditClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(BbIcon.SizeSm)
                    )
                }
            )
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BbRadius.PillShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Verified,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BbIcon.Ui)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Profil tamamlanma durumu",
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
                    .padding(BbSpacing.CardPadding),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BbRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BbIcon.Ui)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BbRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BbIcon.Ui)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
                .size(BbIcon.BoxSm)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BbRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BbIcon.SizeSm)
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
                start = BbSpacing.Space4,
                end = BbSpacing.Space4
            )
            .size(height = 1.dp, width = 1.dp)
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