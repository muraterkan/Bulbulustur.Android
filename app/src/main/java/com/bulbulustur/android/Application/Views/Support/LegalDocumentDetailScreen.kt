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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
                    title = BBLocalization.Current.Get(key = "2b91fb3e-ca32-47dd-99ab-3f018ee89c7d", fallback = ""),
                    subtitle = BBLocalization.Current.Get(key = "57504c0e-4863-470a-9911-c1f2fce8fed0", fallback = "Bu metin API bağlantısından sonra gerçek içerikle beslenecek")
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
                title = BBLocalization.Current.Get(key = "d2316031-462e-4096-80fc-adc677838943", fallback = "Son güncelleme"),
                value = document.updatedDateLabel,
                icon = Icons.Outlined.CalendarMonth
            )

            LegalDocumentMetaRow(
                title = BBLocalization.Current.Get(key = "d1e25968-69b3-473d-9140-f702e9712bad", fallback = "Metin türü"),
                value = document.categoryName,
                icon = Icons.Outlined.RequestQuote
            )

            LegalDocumentMetaRow(
                title = BBLocalization.Current.Get(key = "965b0535-3674-4e76-95ca-4eeafde300fb", fallback = "Durum"),
                value = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
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
                    text = BBLocalization.Current.Get(key = "6987ef3b-9bbe-42c2-99a1-be61ac69c2d1", fallback = "Yasal bilgilendirme"),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Bu ekrandaki metinler mobil API bağlantısından sonra web tarafındaki güncel içeriklerle eşleşecek.",
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
            title = BBLocalization.Current.Get(key = "3cf09908-6dc6-4077-a726-c2816e317905", fallback = "KVKK Aydınlatma Metni"),
            description = BBLocalization.Current.Get(key = "d955fe3c-9fa1-4371-bd2e-615e516ea383", fallback = "Kişisel verilerin korunması kapsamında kullanıcı bilgilendirme metni."),
            categoryName = "KVKK",
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            sectionCount = 4,
            icon = Icons.Outlined.Security,
            sections = getLegalKvkkSections()
        )

        3 -> LegalDocumentDetail(
            legalDocumentId = legalDocumentId,
            title = BBLocalization.Current.Get(key = "786cbd9d-09d8-4c72-97ff-17ead1eff098", fallback = "Kullanım Koşulları"),
            description = BBLocalization.Current.Get(key = "18e55844-f9f0-48b8-a6e0-86c7426137d6", fallback = "Bulbulustur uygulaması ve platform kullanımına ilişkin temel koşullar."),
            categoryName = BBLocalization.Current.Get(key = "4a78c2ab-57cb-41d3-9831-b6bf045ad4b8", fallback = "Koşullar"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            sectionCount = 4,
            icon = Icons.Outlined.Article,
            sections = getLegalTermsSections()
        )

        4 -> LegalDocumentDetail(
            legalDocumentId = legalDocumentId,
            title = BBLocalization.Current.Get(key = "cbadbcf8-3e92-481d-b7bd-c002a9003abb", fallback = "Mesafeli Satış Sözleşmesi"),
            description = BBLocalization.Current.Get(key = "5000bc13-2d57-441f-86d8-95144725d7ac", fallback = "Perakende Alışveriş süreçlerinde geçerli satış ve teslimat hükümleri."),
            categoryName = BBLocalization.Current.Get(key = "f1a8c905-ab33-42bf-a5b5-4406092e01c2", fallback = "Satış"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            sectionCount = 4,
            icon = Icons.Outlined.RequestQuote,
            sections = getLegalDistanceSalesSections()
        )

        else -> LegalDocumentDetail(
            legalDocumentId = legalDocumentId,
            title = BBLocalization.Current.Get(key = "2b5d9eab-d37e-4a08-b2ff-635ccf71620e", fallback = ""),
            description = BBLocalization.Current.Get(key = "06207047-a72b-4ef3-abf8-cc34cba13c86", fallback = "Bulbulustur üzerinde alınan, işlenen ve saklanan kişisel verilere ilişkin açıklamalar."),
            categoryName = BBLocalization.Current.Get(key = "2f764267-3dba-4df6-a6ff-842f0b9aa254", fallback = "Politikalar"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            sectionCount = 4,
            icon = Icons.Outlined.Lock,
            sections = getLegalPrivacySections()
        )
    }
}

private fun getLegalPrivacySections(): List<LegalDocumentSection> {
    return listOf(
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "2d4af99e-5233-4ef1-bc03-1e545701510d", fallback = "Gizlilik politikası nedir?"),
            body = BBLocalization.Current.Get(key = "bfa47f0c-67d5-41e1-bede-ced2c120b415", fallback = "Bu bölümde Bulbulustur üzerinde kullanılan kişisel veriler, işlem süreçleri ve kullanıcı sorumlulukları hakkında özet bilgi yer alacak.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "cffaae86-6de3-43b5-9ebd-feb070db4f3e", fallback = "Topladığımız kişisel veriler"),
            body = BBLocalization.Current.Get(key = "630fbf03-b7b4-4b73-b1cb-7a6a9122467e", fallback = "Hesap, iletişim, sipariş, ödeme, teslimat ve platform kullanımına ilişkin veriler gerçek içerik API bağlantısından sonra gösterilecek.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "bc66cab0-7f25-4f49-a7ef-2570525742ad", fallback = "Verilerin kullanım amacı"),
            body = BBLocalization.Current.Get(key = "a18cdbae-55a4-4539-84ab-9a74c7c2513d", fallback = "Veriler hizmet sunumu, güvenlik, iletişim, sipariş yönetimi ve yasal yükümlülükler kapsamında işlenebilir.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "2cc9ef82-ef09-4997-96b0-6aed87e43d8e", fallback = "Bize nasıl ulaşabilirsiniz?"),
            body = BBLocalization.Current.Get(key = "f5909e44-9e6f-4645-bcd4-f37357f3eeb9", fallback = "Gizlilik ve veri koruma talepleri için destek kanalları üzerinden Bulbulustur ekibine ulaşabilirsiniz.")
        )
    )
}

private fun getLegalKvkkSections(): List<LegalDocumentSection> {
    return listOf(
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "42db1c28-65a8-4f74-a3db-6ff9a088c7a3", fallback = "Veri sorumlusu"),
            body = BBLocalization.Current.Get(key = "8e0270b7-7137-4849-ae71-4750812e9592", fallback = "KVKK kapsamında veri sorumlusu bilgileri ve iletişim kanalları gerçek metinle birlikte gösterilecek.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "d67d99c7-3422-401b-a0a2-b821b92f0d13", fallback = "İşlenen veri kategorileri"),
            body = BBLocalization.Current.Get(key = "6f83c724-06db-4a0f-83ac-2d70dd0552ab", fallback = "Kimlik, iletişim, işlem güvenliği, sipariş ve müşteri işlem verileri gibi kategoriler burada listelenecek.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "b19561bf-2be7-4977-8b93-c8ff25c33591", fallback = "İşleme amaçları"),
            body = BBLocalization.Current.Get(key = "02fe7903-736c-4f89-b283-0a1704124e47", fallback = "Platform hizmetlerinin sunulması, güvenlik, destek, ticari süreç ve yasal yükümlülükler kapsamında veri işlenebilir.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "1257a0c3-4add-4610-8f74-81f2bbd10bc0", fallback = "Haklarınız"),
            body = BBLocalization.Current.Get(key = "3ba4a3d7-6aef-4cb6-8a23-b82134235050", fallback = "KVKK kapsamındaki başvuru ve bilgi edinme haklarınız bu bölümde açıklanacak.")
        )
    )
}

private fun getLegalTermsSections(): List<LegalDocumentSection> {
    return listOf(
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "e6be0994-6bda-476d-b979-91cc18be9c30", fallback = "Platform kullanımı"),
            body = BBLocalization.Current.Get(key = "0475128b-d0e8-46c9-8c5c-dfbf9293ff0d", fallback = "Kullanıcıların Bulbulustur uygulamasını kullanırken uyması gereken temel kurallar burada yer alacak.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "2547b5db-8c29-48cb-9d91-d9376f88c45c", fallback = ""),
            body = BBLocalization.Current.Get(key = "9bc71e0c-6ecb-43ce-b15e-b529d1dda4e6", fallback = "Hesap bilgilerinin korunması, yetkisiz kullanım ve kullanıcı sorumlulukları bu bölümde açıklanacak.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "dbbafc97-f264-4652-85da-1febfe285c03", fallback = "Ticari işlemler"),
            body = BBLocalization.Current.Get(key = "3e2d9336-08be-4031-bd87-76ce3fcec91d", fallback = "Alıcı, satıcı, firma ve platform arasındaki temel işlem prensipleri burada gösterilecek.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "ab9ad0bd-c238-4413-b71e-e7b7d0fbd185", fallback = "Değişiklikler"),
            body = BBLocalization.Current.Get(key = "0d1551e4-2d7c-4f6c-81af-3bb2ffb068bf", fallback = "Kullanım koşullarında yapılabilecek güncellemeler ve kullanıcı bilgilendirme süreçleri açıklanacak.")
        )
    )
}

private fun getLegalDistanceSalesSections(): List<LegalDocumentSection> {
    return listOf(
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "d1c40b42-f385-4cb9-bf60-8f69efbc4afa", fallback = "Sözleşmenin konusu"),
            body = BBLocalization.Current.Get(key = "ccd2d63b-4b41-4b54-b426-53930a5ea1fc", fallback = "Mesafeli satış sözleşmesinin konusu, kapsamı ve taraflara ilişkin bilgiler burada yer alacak.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "ce405549-84d6-4e64-b4ba-65bdc12697c8", fallback = "Ürün ve ödeme bilgileri"),
            body = BBLocalization.Current.Get(key = "8122f5f2-c626-4de1-a025-bc34812c49db", fallback = "Satın alınan ürün, fiyat, ödeme, teslimat ve fatura süreçlerine ilişkin bilgiler gösterilecek.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "334932f4-0feb-4afc-a4a7-17cb7598354a", fallback = "Teslimat ve iade"),
            body = BBLocalization.Current.Get(key = "f68616a2-50b0-42b9-8675-63f85b520959", fallback = "Teslimat süreci, cayma hakkı, iade şartları ve istisnalar bu bölümde açıklanacak.")
        ),
        LegalDocumentSection(
            title = BBLocalization.Current.Get(key = "25564573-568c-4f79-bf86-b5995f05e993", fallback = "Uyuşmazlık çözümü"),
            body = BBLocalization.Current.Get(key = "8339e36e-2217-4352-bad9-b71c6ad439a6", fallback = "Tüketici hakem heyeti, yetkili merciler ve başvuru süreçleri burada yer alacak.")
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

