package com.bulbulustur.android.features.account.settings

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Balance
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.ReportProblem
import androidx.compose.material.icons.outlined.Rule
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun LegalPoliciesScreen(
    onBackClick: () -> Unit = {}
) {
    AccountPageScaffold(
        title = "Yasal Metinler",
        kicker = "Politikalar",
        description = "Kullanım şartları, gizlilik politikası ve platform kurallarını buradan inceleyin.",
        backButtonText = "Ayarlara Dön",
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            LegalNoticeCard()

            LegalPolicySection(
                title = "Politikalar",
                description = "Gizlilik, veri işleme ve platform içerik kuralları.",
                icon = Icons.Outlined.Policy
            ) {
                LegalPolicyRow(
                    title = "Gizlilik Politikası",
                    description = "Kişisel verilerin nasıl işlendiğini ve korunduğunu inceleyin.",
                    icon = Icons.Outlined.PrivacyTip
                )

                LegalDashedDivider()

                LegalPolicyRow(
                    title = "Çerez Politikası",
                    description = "Çerez ve benzeri teknolojilerin kullanım detayları.",
                    icon = Icons.Outlined.Cookie
                )

                LegalDashedDivider()

                LegalPolicyRow(
                    title = "KVKK Aydınlatma Metni",
                    description = "Kişisel verilerinizle ilgili yasal bilgilendirme.",
                    icon = Icons.Outlined.Security
                )

                LegalDashedDivider()

                LegalPolicyRow(
                    title = "Değerlendirme Politikası",
                    description = "Ürün yorumları ve değerlendirme süreçleri.",
                    icon = Icons.Outlined.VerifiedUser
                )

                LegalDashedDivider()

                LegalPolicyRow(
                    title = "İçerik Yayınlama Politikası",
                    description = "Platformda yayınlanan içerikler için temel kurallar.",
                    icon = Icons.Outlined.Article
                )
            }

            LegalPolicySection(
                title = "Koşullar",
                description = "Kullanıcı, alıcı ve satıcı süreçlerine ait kurallar.",
                icon = Icons.Outlined.Rule
            ) {
                LegalPolicyRow(
                    title = "Kullanım Koşulları",
                    description = "Bulbulustur hizmetlerini kullanırken geçerli temel koşullar.",
                    icon = Icons.Outlined.Description
                )

                LegalDashedDivider()

                LegalPolicyRow(
                    title = "Alıcı Kuralları",
                    description = "Sipariş, ödeme, iade ve alıcı sorumlulukları.",
                    icon = Icons.Outlined.Balance
                )

                LegalDashedDivider()

                LegalPolicyRow(
                    title = "Satıcı Kuralları",
                    description = "Satıcı hesapları, ürün yayınlama ve ticari sorumluluklar.",
                    icon = Icons.Outlined.Gavel
                )
            }

            LegalPolicySection(
                title = "Prosedürler",
                description = "Destek, şikayet ve uyuşmazlık süreçleri.",
                icon = Icons.Outlined.SupportAgent
            ) {
                LegalPolicyRow(
                    title = "Soru Sorma Politikası",
                    description = "Ürün ve satıcı sorularında geçerli iletişim kuralları.",
                    icon = Icons.Outlined.SupportAgent
                )

                LegalDashedDivider()

                LegalPolicyRow(
                    title = "Şikayet ve Uyuşmazlık Süreci",
                    description = "Sipariş veya platform işlemlerindeki uyuşmazlık akışı.",
                    icon = Icons.Outlined.ReportProblem
                )

                LegalDashedDivider()

                LegalPolicyRow(
                    title = "Güvenli Ödeme Süreci",
                    description = "Ödeme, koruma ve işlem güvenliği hakkında bilgiler.",
                    icon = Icons.Outlined.Security
                )
            }
        }
    }
}

@Composable
private fun LegalNoticeCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Policy,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BbIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Bulbulustur Legal Center",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Şeffaf platform kuralları, kullanıcı hakları ve yasal süreçler tek merkezde toplanır.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun LegalPolicySection(
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

            LegalDashedDivider()

            Column {
                content()
            }
        }
    }
}

@Composable
private fun LegalPolicyRow(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit = {}
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
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
private fun LegalDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.Space16,
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