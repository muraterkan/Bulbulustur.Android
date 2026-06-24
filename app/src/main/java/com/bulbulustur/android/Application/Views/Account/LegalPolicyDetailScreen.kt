package com.bulbulustur.android.Application.Views.Account

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
import androidx.compose.material3.MaterialTheme
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
fun LegalPolicyDetailScreen(
    policyKey: String,
    onBackClick: () -> Unit = {},
    onOpenWebClick: (String) -> Unit = {}
) {
    val policy = remember(policyKey) {
        getLegalPolicyDetail(policyKey)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = BBColors.Success.copy(alpha = 0.10f),
                            shape = BBRadius.PillShape
                        )
                        .padding(BBSpacing.Space3),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Policy,
                        contentDescription = null,
                        tint = BBColors.Success,
                        modifier = Modifier.height(BBIcon.Section)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = policy.category,
                        style = BbTypography.labelLarge,
                        color = BBColors.Success,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = policy.title,
                        style = BbTypography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = policy.summary,
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
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
                title = "Güncelleme Tarihi",
                value = policy.updatedAt
            )

            LegalDetailDashedDivider()

            LegalPolicyMetaRow(
                icon = Icons.Outlined.Segment,
                title = "Bölüm Sayısı",
                value = "${policy.sections.size} bölüm"
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
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BBRadius.LgShape
                )
                .padding(BBSpacing.Space2),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.height(BBIcon.Ui)
            )
        }

        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space5)
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
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = number.toString(),
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = section.title,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = section.body,
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    )
                    .padding(BBSpacing.Space2),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.height(BBIcon.Ui)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Weby'de Aç",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Metnin güncel web sürümünü tarayıcıda inceleyin.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.height(BBIcon.Ui)
            )
        }
    }
}

@Composable
private fun LegalDetailDashedDivider() {
    val dividerColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(BBSpacing.BorderThin)
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
            body = "Bu bölüm, Bulbulustur platformunda kullanıcı, alıcı, satıcı ve ziyaretçi süreçlerinde geçerli temel kuralları açıklar. Mobil uygulamada gösterilen metinler bilgilendirme amaçlıdır ve API baĞlantısı tamamlandıĞında güncel içerikle beslenecektir."
        ),
        LegalPolicyDetailSection(
            title = "Kullanıcı Sorumlulukları",
            body = "Kullanıcılar platformu kullanırken doĞru bilgi paylaşmak, hesap güvenliĞini korumak ve platform kurallarına uygun hareket etmekle yükümlüdür."
        ),
        LegalPolicyDetailSection(
            title = "Veri ve İşlem GüvenliĞi",
            body = "Bulbulustur, kullanıcı verilerinin korunması ve işlem güvenliĞinin saĞlanması için teknik ve idari önlemler alır. Detaylı içerik ilgili politika metninde açıklanır."
        ),
        LegalPolicyDetailSection(
            title = "Güncellemeler",
            body = "Platform kuralları, mevzuat veya hizmet kapsamındaki deĞişikliklere göre güncellenebilir. Güncel ve baĞlayıcı metinler web üzerindeki yasal merkezde yayınlanır."
        )
    )

    return when (key) {
        "privacy-policy" -> LegalPolicyDetail(
            key = key,
            title = "Gizlilik Politikası",
            category = "Politikalar",
            summary = "Kişisel verilerin nasıl işlendiĞini ve korunduĞunu açıklayan temel politika.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/2/politikalar",
            sections = listOf(
                LegalPolicyDetailSection(
                    title = "Gizlilik Politikası Nedir?",
                    body = "Gizlilik politikası, platformda kişisel verilerin hangi amaçlarla toplandıĞını, nasıl işlendiĞini, kimlerle paylaşılabileceĞini ve hangi güvenlik önlemleriyle korunduĞunu açıklar."
                ),
                LegalPolicyDetailSection(
                    title = "TopladıĞımız Kişisel Veriler",
                    body = "Hesap bilgileri, iletişim bilgileri, sipariş ve talep hareketleri, cihaz ve kullanım verileri gibi platform hizmetlerinin sunulması için gerekli bilgiler işlenebilir."
                ),
                LegalPolicyDetailSection(
                    title = "Kişisel Verilerin Kullanım Amaçları",
                    body = "Veriler; üyelik, sipariş, teklif, destek, güvenlik, dolandırıcılık önleme, bildirim ve platform deneyimini iyileştirme amaçlarıyla kullanılabilir."
                ),
                LegalPolicyDetailSection(
                    title = "Yasal Haklarınız",
                    body = "Kullanıcılar ilgili mevzuat kapsamında kişisel verileri hakkında bilgi talep etme, düzeltme, silme ve işleme faaliyetlerine itiraz etme haklarına sahiptir."
                )
            )
        )

        "cookie-policy" -> LegalPolicyDetail(
            key = key,
            title = "Çerez Politikası",
            category = "Politikalar",
            summary = "Çerez ve benzeri teknolojilerin kullanım detayları.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/2/politikalar",
            sections = baseSections
        )

        "kvkk" -> LegalPolicyDetail(
            key = key,
            title = "KVKK Aydınlatma Metni",
            category = "Politikalar",
            summary = "Kişisel verilerinizle ilgili yasal bilgilendirme.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/2/politikalar",
            sections = baseSections
        )

        "terms-of-use" -> LegalPolicyDetail(
            key = key,
            title = "Kullanım Koşulları",
            category = "Koşullar",
            summary = "Bulbulustur hizmetlerini kullanırken geçerli temel koşullar.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/1/kosullar",
            sections = baseSections
        )

        else -> LegalPolicyDetail(
            key = key,
            title = "Yasal Metin",
            category = "Yasal Merkez",
            summary = "Bulbulustur yasal merkezi içinde yer alan bilgilendirme metni.",
            updatedAt = "01/30/2025",
            webUrl = "https://www.bulbulustur.com/support/condition/2/politikalar",
            sections = baseSections
        )
    }
}


