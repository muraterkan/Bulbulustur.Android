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
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Balance
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.ReportProblem
import androidx.compose.material.icons.outlined.Rule
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun LegalPoliciesScreen(
    onBackClick: () -> Unit = {},
    onPolicyClick: (LegalPolicyItem) -> Unit = {}
) {
    val groups = remember {
        getLegalPolicyGroups()
    }

    Scaffold(
        containerColor = BBColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Yasal Metinler ve Politikalar",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BBColors.SurfaceMuted)
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
                LegalNoticeCard()
            }

            groups.forEach { group ->
                item(
                    key = group.title
                ) {
                    LegalPolicySection(
                        title = group.title,
                        description = group.description,
                        icon = group.icon
                    ) {
                        group.items.forEachIndexed { index, item ->
                            LegalPolicyRow(
                                item = item,
                                onClick = {
                                    onPolicyClick(item)
                                }
                            )

                            if (index != group.items.lastIndex) {
                                LegalDashedDivider()
                            }
                        }
                    }
                }
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = BBColors.Success.copy(alpha = 0.10f),
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Policy,
                    contentDescription = null,
                    tint = BBColors.Success,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Bulbulustur Yasal Merkezi",
                    style = BbTypography.titleMedium,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Åeffaf platform kuralları, kullanıcı hakları ve yasal süreçler tek merkezde toplanır.",
                    style = BbTypography.bodySmall,
                    color = BBColors.TextMuted
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
            modifier = Modifier.background(BBColors.Surface)
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
                            color = BBColors.PrimarySoft,
                            shape = BBRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = BBColors.TextStrong,
                        modifier = Modifier.size(BBIcon.Ui)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = title,
                        style = BbTypography.titleSmall,
                        color = BBColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = description,
                        style = BbTypography.labelSmall,
                        color = BBColors.TextMuted
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
    item: LegalPolicyItem,
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
                    color = BBColors.SurfaceMuted,
                    shape = BBRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = BBColors.TextStrong,
                modifier = Modifier.size(BBIcon.Ui)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = item.title,
                style = BbTypography.titleSmall,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = item.description,
                style = BbTypography.bodySmall,
                color = BBColors.TextMuted
            )
        }

        Box(
            modifier = Modifier
                .size(BBIcon.BoxSm)
                .background(
                    color = BBColors.SurfaceMuted,
                    shape = BBRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BBColors.TextMuted,
                modifier = Modifier.size(BBIcon.SizeSm)
            )
        }
    }
}

@Composable
private fun LegalDashedDivider() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.Space16,
                end = BBSpacing.Space4
            )
            .size(
                height = 1.dp,
                width = 1.dp
            )
    ) {
        drawLine(
            color = BBColors.Border,
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

@Immutable
data class LegalPolicyItem(
    val key: String,
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Immutable
private data class LegalPolicyGroup(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val items: List<LegalPolicyItem>
)

private fun getLegalPolicyGroups(): List<LegalPolicyGroup> {
    return listOf(
        LegalPolicyGroup(
            title = "Politikalar",
            description = "Gizlilik, veri işleme ve platform içerik kuralları.",
            icon = Icons.Outlined.Policy,
            items = listOf(
                LegalPolicyItem(
                    key = "privacy-policy",
                    title = "Gizlilik Politikası",
                    description = "Kişisel verilerin nasıl işlendiĞini ve korunduĞunu inceleyin.",
                    icon = Icons.Outlined.PrivacyTip
                ),
                LegalPolicyItem(
                    key = "cookie-policy",
                    title = "Ã‡erez Politikası",
                    description = "Ã‡erez ve benzeri teknolojilerin kullanım detayları.",
                    icon = Icons.Outlined.Cookie
                ),
                LegalPolicyItem(
                    key = "kvkk",
                    title = "KVKK Aydınlatma Metni",
                    description = "Kişisel verilerinizle ilgili yasal bilgilendirme.",
                    icon = Icons.Outlined.Security
                ),
                LegalPolicyItem(
                    key = "review-policy",
                    title = "DeĞerlendirme Politikası",
                    description = "Ürün yorumları ve deĞerlendirme süreçleri.",
                    icon = Icons.Outlined.VerifiedUser
                ),
                LegalPolicyItem(
                    key = "content-policy",
                    title = "Ä°çerik Yayınlama Politikası",
                    description = "Platformda yayınlanan içerikler için temel kurallar.",
                    icon = Icons.Outlined.Article
                )
            )
        ),
        LegalPolicyGroup(
            title = "Koşullar",
            description = "Kullanıcı, alıcı ve satıcı süreçlerine ait kurallar.",
            icon = Icons.Outlined.Rule,
            items = listOf(
                LegalPolicyItem(
                    key = "terms-of-use",
                    title = "Kullanım Koşulları",
                    description = "Bulbulustur hizmetlerini kullanırken geçerli temel koşullar.",
                    icon = Icons.Outlined.RequestQuote
                ),
                LegalPolicyItem(
                    key = "buyer-rules",
                    title = "Alıcı Kuralları",
                    description = "Sipariş, ödeme, iade ve alıcı sorumlulukları.",
                    icon = Icons.Outlined.Balance
                ),
                LegalPolicyItem(
                    key = "seller-rules",
                    title = "Satıcı Kuralları",
                    description = "Satıcı hesapları, ürün yayınlama ve ticari sorumluluklar.",
                    icon = Icons.Outlined.Gavel
                )
            )
        ),
        LegalPolicyGroup(
            title = "Prosedürler",
            description = "Destek, şikayet ve uyuşmazlık süreçleri.",
            icon = Icons.Outlined.SupportAgent,
            items = listOf(
                LegalPolicyItem(
                    key = "question-policy",
                    title = "Soru Sorma Politikası",
                    description = "Ürün ve satıcı sorularında geçerli iletişim kuralları.",
                    icon = Icons.Outlined.SupportAgent
                ),
                LegalPolicyItem(
                    key = "complaint-dispute",
                    title = "Åikayet ve Uyuşmazlık Süreci",
                    description = "Sipariş veya platform işlemlerindeki uyuşmazlık akışı.",
                    icon = Icons.Outlined.ReportProblem
                ),
                LegalPolicyItem(
                    key = "secure-payment",
                    title = "Güvenli Ã–deme Süreci",
                    description = "Ã–deme, koruma ve işlem güvenliĞi hakkında bilgiler.",
                    icon = Icons.Outlined.Security
                )
            )
        )
    )
}


