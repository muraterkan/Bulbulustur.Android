package com.bulbulustur.android.Application.Views.Support

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun LegalDocumentDetailScreen(
    legalDocumentId: Int = 1,
    onBackClick: () -> Unit = {}
) {
    val document = remember(legalDocumentId) {
        getLegalDocumentDetail(legalDocumentId)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                LegalDocumentDetailHeader(
                    document = document,
                    onBackClick = onBackClick
                )
            }

            item {
                LegalDocumentMetaCard(
                    document = document
                )
            }

            item {
                BbSectionHeader(
                    title = "İçerik",
                    subtitle = "Bu metin API baĞlantısından sonra gerçek içerikle beslenecek"
                )
            }

            items(
                items = document.sections,
                key = { section ->
                    section.title
                }
            ) { section ->
                LegalDocumentSectionCard(
                    section = section
                )
            }

            item {
                LegalDocumentInfoCard()
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LegalDocumentDetailHeader(
    document: LegalDocumentDetail,
    onBackClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                BbButton(
                    text = "",
                    onClick = onBackClick,
                    variant = BbButtonVariant.Outline,
                    size = BbButtonSize.Small,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = document.categoryName,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = document.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Icon(
                    imageVector = document.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = document.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
            ) {
                BbChip(
                    text = document.categoryName,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = document.updatedDateLabel,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "${document.sectionCount} bölüm",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun LegalDocumentMetaCard(
    document: LegalDocumentDetail
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            LegalDocumentMetaRow(
                title = "Son güncelleme",
                value = document.updatedDateLabel,
                icon = Icons.Outlined.CalendarMonth
            )

            LegalDocumentMetaRow(
                title = "Metin türü",
                value = document.categoryName,
                icon = Icons.Outlined.RequestQuote
            )

            LegalDocumentMetaRow(
                title = "Durum",
                value = "Güncel",
                icon = Icons.Outlined.VerifiedUser
            )
        }
    }
}

@Composable
private fun LegalDocumentMetaRow(
    title: String,
    value: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.IconTextGap)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun LegalDocumentSectionCard(
    section: LegalDocumentSection
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = section.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = section.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LegalDocumentInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Policy,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Yasal bilgilendirme",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Bu ekrandaki metinler mobil API baĞlantısından sonra web tarafındaki güncel içeriklerle eşleşecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

data class LegalDocumentDetail(
    val legalDocumentId: Int,
    val title: String,
    val description: String,
    val categoryName: String,
    val updatedDateLabel: String,
    val sectionCount: Int,
    val icon: ImageVector,
    val sections: List<LegalDocumentSection>
)

data class LegalDocumentSection(
    val title: String,
    val body: String
)

private fun getLegalDocumentDetail(
    legalDocumentId: Int
): LegalDocumentDetail {
    return when (legalDocumentId) {
        2 -> LegalDocumentDetail(
            legalDocumentId = legalDocumentId,
            title = "KVKK Aydınlatma Metni",
            description = "Kişisel verilerin korunması kapsamında kullanıcı bilgilendirme metni.",
            categoryName = "KVKK",
            updatedDateLabel = "Güncel",
            sectionCount = 4,
            icon = Icons.Outlined.Security,
            sections = getLegalKvkkSections()
        )

        3 -> LegalDocumentDetail(
            legalDocumentId = legalDocumentId,
            title = "Kullanım Koşulları",
            description = "Bulbulustur uygulaması ve platform kullanımına ilişkin temel koşullar.",
            categoryName = "Koşullar",
            updatedDateLabel = "Güncel",
            sectionCount = 4,
            icon = Icons.Outlined.Article,
            sections = getLegalTermsSections()
        )

        4 -> LegalDocumentDetail(
            legalDocumentId = legalDocumentId,
            title = "Mesafeli Satış Sözleşmesi",
            description = "Perakende Alışveriş süreçlerinde geçerli satış ve teslimat hükümleri.",
            categoryName = "Satış",
            updatedDateLabel = "Güncel",
            sectionCount = 4,
            icon = Icons.Outlined.RequestQuote,
            sections = getLegalDistanceSalesSections()
        )

        else -> LegalDocumentDetail(
            legalDocumentId = legalDocumentId,
            title = "Gizlilik Politikası",
            description = "Bulbulustur üzerinde alınan, işlenen ve saklanan kişisel verilere ilişkin açıklamalar.",
            categoryName = "Politikalar",
            updatedDateLabel = "Güncel",
            sectionCount = 4,
            icon = Icons.Outlined.Lock,
            sections = getLegalPrivacySections()
        )
    }
}

private fun getLegalPrivacySections(): List<LegalDocumentSection> {
    return listOf(
        LegalDocumentSection(
            title = "Gizlilik politikası nedir?",
            body = "Bu bölümde Bulbulustur üzerinde kullanılan kişisel veriler, işlem süreçleri ve kullanıcı sorumlulukları hakkında özet bilgi yer alacak."
        ),
        LegalDocumentSection(
            title = "TopladıĞımız kişisel veriler",
            body = "Hesap, iletişim, sipariş, ödeme, teslimat ve platform kullanımına ilişkin veriler gerçek içerik API baĞlantısından sonra gösterilecek."
        ),
        LegalDocumentSection(
            title = "Verilerin kullanım amacı",
            body = "Veriler hizmet sunumu, güvenlik, iletişim, sipariş yönetimi ve yasal yükümlülükler kapsamında işlenebilir."
        ),
        LegalDocumentSection(
            title = "Bize nasıl ulaşabilirsiniz?",
            body = "Gizlilik ve veri koruma talepleri için destek kanalları üzerinden Bulbulustur ekibine ulaşabilirsiniz."
        )
    )
}

private fun getLegalKvkkSections(): List<LegalDocumentSection> {
    return listOf(
        LegalDocumentSection(
            title = "Veri sorumlusu",
            body = "KVKK kapsamında veri sorumlusu bilgileri ve iletişim kanalları gerçek metinle birlikte gösterilecek."
        ),
        LegalDocumentSection(
            title = "İşlenen veri kategorileri",
            body = "Kimlik, iletişim, işlem güvenliĞi, sipariş ve müşteri işlem verileri gibi kategoriler burada listelenecek."
        ),
        LegalDocumentSection(
            title = "İşleme amaçları",
            body = "Platform hizmetlerinin sunulması, güvenlik, destek, ticari süreç ve yasal yükümlülükler kapsamında veri işlenebilir."
        ),
        LegalDocumentSection(
            title = "Haklarınız",
            body = "KVKK kapsamındaki başvuru ve bilgi edinme haklarınız bu bölümde açıklanacak."
        )
    )
}

private fun getLegalTermsSections(): List<LegalDocumentSection> {
    return listOf(
        LegalDocumentSection(
            title = "Platform kullanımı",
            body = "Kullanıcıların Bulbulustur uygulamasını kullanırken uyması gereken temel kurallar burada yer alacak."
        ),
        LegalDocumentSection(
            title = "Üyelik ve hesap güvenliĞi",
            body = "Hesap bilgilerinin korunması, yetkisiz kullanım ve kullanıcı sorumlulukları bu bölümde açıklanacak."
        ),
        LegalDocumentSection(
            title = "Ticari işlemler",
            body = "Alıcı, satıcı, firma ve platform arasındaki temel işlem prensipleri burada gösterilecek."
        ),
        LegalDocumentSection(
            title = "DeĞişiklikler",
            body = "Kullanım koşullarında yapılabilecek güncellemeler ve kullanıcı bilgilendirme süreçleri açıklanacak."
        )
    )
}

private fun getLegalDistanceSalesSections(): List<LegalDocumentSection> {
    return listOf(
        LegalDocumentSection(
            title = "Sözleşmenin konusu",
            body = "Mesafeli satış sözleşmesinin konusu, kapsamı ve taraflara ilişkin bilgiler burada yer alacak."
        ),
        LegalDocumentSection(
            title = "Ürün ve ödeme bilgileri",
            body = "Satın alınan ürün, fiyat, ödeme, teslimat ve fatura süreçlerine ilişkin bilgiler gösterilecek."
        ),
        LegalDocumentSection(
            title = "Teslimat ve iade",
            body = "Teslimat süreci, cayma hakkı, iade şartları ve istisnalar bu bölümde açıklanacak."
        ),
        LegalDocumentSection(
            title = "Uyuşmazlık çözümü",
            body = "Tüketici hakem heyeti, yetkili merciler ve başvuru süreçleri burada yer alacak."
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun LegalDocumentDetailScreenPreview() {
    BbTheme {
        LegalDocumentDetailScreen()
    }
}

