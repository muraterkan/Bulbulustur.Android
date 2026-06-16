package com.bulbulustur.android.Views.Account

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Segment
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
fun LegalPolicyDetailScreen(
    policyKey: String,
    onBackClick: () -> Unit = {},
    onOpenWebClick: (String) -> Unit = {}
) {
    val policy = remember(policyKey) {
        getLegalPolicyDetail(policyKey)
    }

    Scaffold(
        containerColor = BbColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = policy.title,
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
                LegalPolicyDetailHeroCard(
                    policy = policy
                )
            }

            item {
                LegalPolicyDetailMetaCard(
                    policy = policy
                )
            }

            item {
                LegalPolicyDetailContentCard(
                    sections = policy.sections
                )
            }

            item {
                LegalPolicyOpenWebCard(
                    onClick = {
                        onOpenWebClick(policy.webUrl)
                    }
                )
            }
        }
    }
}

@Composable
private fun LegalPolicyDetailHeroCard(
    policy: LegalPolicyDetail
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = BbColors.Success.copy(alpha = 0.10f),
                            shape = BbRadius.PillShape
                        )
                        .padding(BbSpacing.Space3),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Policy,
                        contentDescription = null,
                        tint = BbColors.Success,
                        modifier = Modifier.height(BbIcon.Section)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = policy.category,
                        style = BbTypography.labelLarge,
                        color = BbColors.Success,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = policy.title,
                        style = BbTypography.titleLarge,
                        color = BbColors.TextStrong,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = policy.summary,
                style = BbTypography.bodyMedium,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun LegalPolicyDetailMetaCard(
    policy: LegalPolicyDetail
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LegalPolicyMetaRow(
                icon = Icons.Outlined.CalendarMonth,
                title = "GÃ¼ncelleme Tarihi",
                value = policy.updatedAt
            )

            LegalDetailDashedDivider()

            LegalPolicyMetaRow(
                icon = Icons.Outlined.Segment,
                title = "BÃ¶lÃ¼m SayÄ±sÄ±",
                value = "${policy.sections.size} bÃ¶lÃ¼m"
            )
        }
    }
}

@Composable
private fun LegalPolicyMetaRow(
    icon: ImageVector,
    title: String,
    value: String
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
                .background(
                    color = BbColors.SurfaceMuted,
                    shape = BbRadius.LgShape
                )
                .padding(BbSpacing.Space2),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.height(BbIcon.Ui)
            )
        }

        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = BbColors.TextStrong,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            style = BbTypography.bodyMedium,
            color = BbColors.TextMuted
        )
    }
}

@Composable
private fun LegalPolicyDetailContentCard(
    sections: List<LegalPolicyDetailSection>
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space5)
        ) {
            sections.forEachIndexed { index, section ->
                LegalPolicyTextSection(
                    number = index + 1,
                    section = section
                )

                if (index != sections.lastIndex) {
                    LegalDetailDashedDivider()
                }
            }
        }
    }
}

@Composable
private fun LegalPolicyTextSection(
    number: Int,
    section: LegalPolicyDetailSection
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = number.toString(),
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = section.title,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = section.body,
            style = BbTypography.bodyMedium,
            color = BbColors.TextSubtle
        )
    }
}

@Composable
private fun LegalPolicyOpenWebCard(
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = BbColors.PrimarySoft,
                        shape = BbRadius.LgShape
                    )
                    .padding(BbSpacing.Space2),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.height(BbIcon.Ui)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Webâ€™de AÃ§",
                    style = BbTypography.titleSmall,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Metnin gÃ¼ncel web sÃ¼rÃ¼mÃ¼nÃ¼ tarayÄ±cÄ±da inceleyin.",
                    style = BbTypography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.height(BbIcon.Ui)
            )
        }
    }
}

@Composable
private fun LegalDetailDashedDivider() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(BbSpacing.BorderThin)
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
private data class LegalPolicyDetail(
    val key: String,
    val title: String,
    val category: String,
    val summary: String,
    val updatedAt: String,
    val webUrl: String,
    val sections: List<LegalPolicyDetailSection>
)

@Immutable
private data class LegalPolicyDetailSection(
    val title: String,
    val body: String
)

private fun getLegalPolicyDetail(
    key: String
): LegalPolicyDetail {
    val baseSections = listOf(
        LegalPolicyDetailSection(
            title = "Bu Metin Neyi Kapsar?",
            body = "Bu bÃ¶lÃ¼m, Bulbulustur platformunda kullanÄ±cÄ±, alÄ±cÄ±, satÄ±cÄ± ve ziyaretÃ§i sÃ¼reÃ§lerinde geÃ§erli temel kurallarÄ± aÃ§Ä±klar. Mobil uygulamada gÃ¶sterilen metinler bilgilendirme amaÃ§lÄ±dÄ±r ve API baÄŸlantÄ±sÄ± tamamlandÄ±ÄŸÄ±nda gÃ¼ncel iÃ§erikle beslenecektir."
        ),
        LegalPolicyDetailSection(
            title = "KullanÄ±cÄ± SorumluluklarÄ±",
            body = "KullanÄ±cÄ±lar platformu kullanÄ±rken doÄŸru bilgi paylaÅŸmak, hesap gÃ¼venliÄŸini korumak ve platform kurallarÄ±na uygun hareket etmekle yÃ¼kÃ¼mlÃ¼dÃ¼r."
        ),
        LegalPolicyDetailSection(
            title = "Veri ve Ä°ÅŸlem GÃ¼venliÄŸi",
            body = "Bulbulustur, kullanÄ±cÄ± verilerinin korunmasÄ± ve iÅŸlem gÃ¼venliÄŸinin saÄŸlanmasÄ± iÃ§in teknik ve idari Ã¶nlemler alÄ±r. DetaylÄ± iÃ§erik ilgili politika metninde aÃ§Ä±klanÄ±r."
        ),
        LegalPolicyDetailSection(
            title = "GÃ¼ncellemeler",
            body = "Platform kurallarÄ±, mevzuat veya hizmet kapsamÄ±ndaki deÄŸiÅŸikliklere gÃ¶re gÃ¼ncellenebilir. GÃ¼ncel ve baÄŸlayÄ±cÄ± metinler web Ã¼zerindeki yasal merkezde yayÄ±nlanÄ±r."
        )
    )

    return when (key) {
        "privacy-policy" -> LegalPolicyDetail(
            key = key,
            title = "Gizlilik PolitikasÄ±",
            category = "Politikalar",
            summary = "KiÅŸisel verilerin nasÄ±l iÅŸlendiÄŸini ve korunduÄŸunu aÃ§Ä±klayan temel politika.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/2/politikalar",
            sections = listOf(
                LegalPolicyDetailSection(
                    title = "Gizlilik PolitikasÄ± Nedir?",
                    body = "Gizlilik politikasÄ±, platformda kiÅŸisel verilerin hangi amaÃ§larla toplandÄ±ÄŸÄ±nÄ±, nasÄ±l iÅŸlendiÄŸini, kimlerle paylaÅŸÄ±labileceÄŸini ve hangi gÃ¼venlik Ã¶nlemleriyle korunduÄŸunu aÃ§Ä±klar."
                ),
                LegalPolicyDetailSection(
                    title = "TopladÄ±ÄŸÄ±mÄ±z KiÅŸisel Veriler",
                    body = "Hesap bilgileri, iletiÅŸim bilgileri, sipariÅŸ ve talep hareketleri, cihaz ve kullanÄ±m verileri gibi platform hizmetlerinin sunulmasÄ± iÃ§in gerekli bilgiler iÅŸlenebilir."
                ),
                LegalPolicyDetailSection(
                    title = "KiÅŸisel Verilerin KullanÄ±m AmaÃ§larÄ±",
                    body = "Veriler; Ã¼yelik, sipariÅŸ, teklif, destek, gÃ¼venlik, dolandÄ±rÄ±cÄ±lÄ±k Ã¶nleme, bildirim ve platform deneyimini iyileÅŸtirme amaÃ§larÄ±yla kullanÄ±labilir."
                ),
                LegalPolicyDetailSection(
                    title = "Yasal HaklarÄ±nÄ±z",
                    body = "KullanÄ±cÄ±lar ilgili mevzuat kapsamÄ±nda kiÅŸisel verileri hakkÄ±nda bilgi talep etme, dÃ¼zeltme, silme ve iÅŸleme faaliyetlerine itiraz etme haklarÄ±na sahiptir."
                )
            )
        )

        "cookie-policy" -> LegalPolicyDetail(
            key = key,
            title = "Ã‡erez PolitikasÄ±",
            category = "Politikalar",
            summary = "Ã‡erez ve benzeri teknolojilerin kullanÄ±m detaylarÄ±.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/2/politikalar",
            sections = baseSections
        )

        "kvkk" -> LegalPolicyDetail(
            key = key,
            title = "KVKK AydÄ±nlatma Metni",
            category = "Politikalar",
            summary = "KiÅŸisel verilerinizle ilgili yasal bilgilendirme.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/2/politikalar",
            sections = baseSections
        )

        "terms-of-use" -> LegalPolicyDetail(
            key = key,
            title = "KullanÄ±m KoÅŸullarÄ±",
            category = "KoÅŸullar",
            summary = "Bulbulustur hizmetlerini kullanÄ±rken geÃ§erli temel koÅŸullar.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/1/kosullar",
            sections = baseSections
        )

        else -> LegalPolicyDetail(
            key = key,
            title = "Yasal Metin",
            category = "Yasal Merkez",
            summary = "Bulbulustur yasal merkezi iÃ§inde yer alan bilgilendirme metni.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/2/politikalar",
            sections = baseSections
        )
    }
}
