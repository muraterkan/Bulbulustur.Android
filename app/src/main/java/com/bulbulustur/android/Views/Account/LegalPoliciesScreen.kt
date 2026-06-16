package com.bulbulustur.android.Views.Account

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
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTypography

@Composable
fun LegalPoliciesScreen(
    onBackClick: () -> Unit = {},
    onPolicyClick: (LegalPolicyItem) -> Unit = {}
) {
    val groups = remember {
        getLegalPolicyGroups()
    }

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
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
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbIcon.BoxLg)
                    .background(
                        color = BbColors.Success.copy(alpha = 0.10f),
                        shape = BbRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Policy,
                    contentDescription = null,
                    tint = BbColors.Success,
                    modifier = Modifier.size(BbIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Bulbulustur Yasal Merkezi",
                    style = BbTypography.titleMedium,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Åeffaf platform kurallarÄ±, kullanÄ±cÄ± haklarÄ± ve yasal sÃ¼reÃ§ler tek merkezde toplanÄ±r.",
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
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
            modifier = Modifier.background(BbColors.Surface)
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
                            color = BbColors.PrimarySoft,
                            shape = BbRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbIcon.Ui)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = title,
                        style = BbTypography.titleSmall,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = description,
                        style = BbTypography.labelSmall,
                        color = BbColors.TextMuted
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
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .background(
                    color = BbColors.SurfaceMuted,
                    shape = BbRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.Ui)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = item.title,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = item.description,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )
        }

        Box(
            modifier = Modifier
                .size(BbIcon.BoxSm)
                .background(
                    color = BbColors.SurfaceMuted,
                    shape = BbRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.size(BbIcon.SizeSm)
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
                start = BbSpacing.Space16,
                end = BbSpacing.Space4
            )
            .size(
                height = 1.dp,
                width = 1.dp
            )
    ) {
        drawLine(
            color = BbColors.Border,
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
            description = "Gizlilik, veri iÅŸleme ve platform iÃ§erik kurallarÄ±.",
            icon = Icons.Outlined.Policy,
            items = listOf(
                LegalPolicyItem(
                    key = "privacy-policy",
                    title = "Gizlilik PolitikasÄ±",
                    description = "KiÅŸisel verilerin nasÄ±l iÅŸlendiÄŸini ve korunduÄŸunu inceleyin.",
                    icon = Icons.Outlined.PrivacyTip
                ),
                LegalPolicyItem(
                    key = "cookie-policy",
                    title = "Ã‡erez PolitikasÄ±",
                    description = "Ã‡erez ve benzeri teknolojilerin kullanÄ±m detaylarÄ±.",
                    icon = Icons.Outlined.Cookie
                ),
                LegalPolicyItem(
                    key = "kvkk",
                    title = "KVKK AydÄ±nlatma Metni",
                    description = "KiÅŸisel verilerinizle ilgili yasal bilgilendirme.",
                    icon = Icons.Outlined.Security
                ),
                LegalPolicyItem(
                    key = "review-policy",
                    title = "DeÄŸerlendirme PolitikasÄ±",
                    description = "ÃœrÃ¼n yorumlarÄ± ve deÄŸerlendirme sÃ¼reÃ§leri.",
                    icon = Icons.Outlined.VerifiedUser
                ),
                LegalPolicyItem(
                    key = "content-policy",
                    title = "Ä°Ã§erik YayÄ±nlama PolitikasÄ±",
                    description = "Platformda yayÄ±nlanan iÃ§erikler iÃ§in temel kurallar.",
                    icon = Icons.Outlined.Article
                )
            )
        ),
        LegalPolicyGroup(
            title = "KoÅŸullar",
            description = "KullanÄ±cÄ±, alÄ±cÄ± ve satÄ±cÄ± sÃ¼reÃ§lerine ait kurallar.",
            icon = Icons.Outlined.Rule,
            items = listOf(
                LegalPolicyItem(
                    key = "terms-of-use",
                    title = "KullanÄ±m KoÅŸullarÄ±",
                    description = "Bulbulustur hizmetlerini kullanÄ±rken geÃ§erli temel koÅŸullar.",
                    icon = Icons.Outlined.Description
                ),
                LegalPolicyItem(
                    key = "buyer-rules",
                    title = "AlÄ±cÄ± KurallarÄ±",
                    description = "SipariÅŸ, Ã¶deme, iade ve alÄ±cÄ± sorumluluklarÄ±.",
                    icon = Icons.Outlined.Balance
                ),
                LegalPolicyItem(
                    key = "seller-rules",
                    title = "SatÄ±cÄ± KurallarÄ±",
                    description = "SatÄ±cÄ± hesaplarÄ±, Ã¼rÃ¼n yayÄ±nlama ve ticari sorumluluklar.",
                    icon = Icons.Outlined.Gavel
                )
            )
        ),
        LegalPolicyGroup(
            title = "ProsedÃ¼rler",
            description = "Destek, ÅŸikayet ve uyuÅŸmazlÄ±k sÃ¼reÃ§leri.",
            icon = Icons.Outlined.SupportAgent,
            items = listOf(
                LegalPolicyItem(
                    key = "question-policy",
                    title = "Soru Sorma PolitikasÄ±",
                    description = "ÃœrÃ¼n ve satÄ±cÄ± sorularÄ±nda geÃ§erli iletiÅŸim kurallarÄ±.",
                    icon = Icons.Outlined.SupportAgent
                ),
                LegalPolicyItem(
                    key = "complaint-dispute",
                    title = "Åikayet ve UyuÅŸmazlÄ±k SÃ¼reci",
                    description = "SipariÅŸ veya platform iÅŸlemlerindeki uyuÅŸmazlÄ±k akÄ±ÅŸÄ±.",
                    icon = Icons.Outlined.ReportProblem
                ),
                LegalPolicyItem(
                    key = "secure-payment",
                    title = "GÃ¼venli Ã–deme SÃ¼reci",
                    description = "Ã–deme, koruma ve iÅŸlem gÃ¼venliÄŸi hakkÄ±nda bilgiler.",
                    icon = Icons.Outlined.Security
                )
            )
        )
    )
}
