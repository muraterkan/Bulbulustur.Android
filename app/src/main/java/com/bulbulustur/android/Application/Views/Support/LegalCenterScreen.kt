package com.bulbulustur.android.Application.Views.Support

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun LegalCenterScreen(
    onLegalDocumentClick: (Int) -> Unit = {},
    onSupportClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.md)
    ) {
        item {
            LegalCenterHeader()
        }

        item {
            LegalCenterContactCard(
                onSupportClick = onSupportClick
            )
        }

        item {
            BbSectionHeader(
                title = BBLocalization.Current.Get(key = "1331ee8c-b5ea-49f0-9d59-297679f21fbb", fallback = "Yasal metinler"),
                subtitle = BBLocalization.Current.Get(key = "57f75cab-2724-455b-bd4b-f3b330dd4709", fallback = "Kullanım, gizlilik, KVKK ve satış süreçlerine ait metinler")
            )
        }

        items(legalDocumentItems()) { document ->
            LegalDocumentCard(
                document = document,
                onClick = {
                    onLegalDocumentClick(document.legalDocumentId)
                }
            )
        }

        item {
            BbSectionHeader(
                title = BBLocalization.Current.Get(key = "5250cfc4-6d41-4adb-b6eb-0cbf0c837d62", fallback = "Platform kuralları"),
                subtitle = BBLocalization.Current.Get(key = "559b5d99-df2a-4b54-a00b-8569a04a7949", fallback = "Satıcı, alıcı ve ziyaretçiler için geçerli temel metinler")
            )
        }

        items(platformRuleItems()) { document ->
            LegalDocumentCard(
                document = document,
                onClick = {
                    onLegalDocumentClick(document.legalDocumentId)
                }
            )
        }

        item {
            LegalCenterInfoCard()
        }

        item {
            Spacer(modifier = Modifier.height(BBSpacing.xl))
        }
    }
}

@Composable
private fun LegalCenterHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Gavel,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = BBLocalization.Current.Get(key = "69b609a0-a577-4784-a0b8-0660c4b11649", fallback = "Bulbulustur Legal Center"),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "33cee08b-0744-4a26-9d90-6eee0b882da0", fallback = "Yasal ve platform metinleri"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "79805e01-78b6-4c23-b62f-c1df3c360894", fallback = "Kullanım koşulları, gizlilik politikaları, platform kuralları ve satış süreçlerine ait metinleri buradan inceleyin."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                BbChip(
                    text = BBLocalization.Current.Get(key = "2f764267-3dba-4df6-a6ff-842f0b9aa254", fallback = "Politikalar"),
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = BBLocalization.Current.Get(key = "4a78c2ab-57cb-41d3-9831-b6bf045ad4b8", fallback = "Koşullar"),
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "KVKK",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun LegalCenterContactCard(
    onSupportClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onSupportClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.SupportAgent,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "e106f2d1-7b71-435a-be85-22f0c8262d05", fallback = "Yardım merkezi"),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "1f9eabbe-3b17-4688-8a4c-c4e9d2b6ea99", fallback = "Yasal metinler veya platform kuralları hakkında destek almak için yardım merkezine geçin."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LegalDocumentCard(
    document: LegalDocumentItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
        ) {
            Icon(
                imageVector = document.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = document.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = document.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm)
                ) {
                    Text(
                        text = document.categoryName,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = document.updatedDateLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LegalCenterInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.lg),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.VerifiedUser,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = "Metinler API ile beslenecek",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Bu ekran şimdilik yasal metin listesi skeletony'ıdır. Detay ekranında gerçek içerikler servis üzerinden gösterilecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private data class LegalDocumentItem(
    val legalDocumentId: Int,
    val title: String,
    val description: String,
    val categoryName: String,
    val updatedDateLabel: String,
    val icon: ImageVector
)

private fun legalDocumentItems(): List<LegalDocumentItem> {
    return listOf(
        LegalDocumentItem(
            legalDocumentId = 1,
            title = BBLocalization.Current.Get(key = "2b5d9eab-d37e-4a08-b2ff-635ccf71620e", fallback = ""),
            description = BBLocalization.Current.Get(key = "06207047-a72b-4ef3-abf8-cc34cba13c86", fallback = "Bulbulustur üzerinde alınan, işlenen ve saklanan kişisel verilere ilişkin açıklamalar."),
            categoryName = BBLocalization.Current.Get(key = "2f764267-3dba-4df6-a6ff-842f0b9aa254", fallback = "Politikalar"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            icon = Icons.Outlined.Lock
        ),
        LegalDocumentItem(
            legalDocumentId = 2,
            title = BBLocalization.Current.Get(key = "3cf09908-6dc6-4077-a726-c2816e317905", fallback = "KVKK Aydınlatma Metni"),
            description = BBLocalization.Current.Get(key = "e8d1c32d-b934-4dfc-a8f9-57edd1402752", fallback = "Kişisel verilerin korunması kapsamında bilgilendirme metni."),
            categoryName = "KVKK",
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            icon = Icons.Outlined.Security
        ),
        LegalDocumentItem(
            legalDocumentId = 3,
            title = BBLocalization.Current.Get(key = "786cbd9d-09d8-4c72-97ff-17ead1eff098", fallback = "Kullanım Koşulları"),
            description = BBLocalization.Current.Get(key = "18e55844-f9f0-48b8-a6e0-86c7426137d6", fallback = "Bulbulustur uygulaması ve platform kullanımına ilişkin temel koşullar."),
            categoryName = BBLocalization.Current.Get(key = "4a78c2ab-57cb-41d3-9831-b6bf045ad4b8", fallback = "Koşullar"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            icon = Icons.Outlined.Article
        ),
        LegalDocumentItem(
            legalDocumentId = 4,
            title = BBLocalization.Current.Get(key = "cbadbcf8-3e92-481d-b7bd-c002a9003abb", fallback = "Mesafeli Satış Sözleşmesi"),
            description = BBLocalization.Current.Get(key = "5000bc13-2d57-441f-86d8-95144725d7ac", fallback = "Perakende Alışveriş süreçlerinde geçerli satış ve teslimat hükümleri."),
            categoryName = BBLocalization.Current.Get(key = "f1a8c905-ab33-42bf-a5b5-4406092e01c2", fallback = "Satış"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            icon = Icons.Outlined.RequestQuote
        )
    )
}

private fun platformRuleItems(): List<LegalDocumentItem> {
    return listOf(
        LegalDocumentItem(
            legalDocumentId = 5,
            title = BBLocalization.Current.Get(key = "9dbf472e-78f6-43a6-97cd-b748c738b6d7", fallback = "Çerez Politikası"),
            description = BBLocalization.Current.Get(key = "a7fa2607-3364-4162-95cd-ff31c18f6de0", fallback = "Uygulama ve web deneyiminde kullanılan çerez ve benzeri teknolojilere ilişkin bilgiler."),
            categoryName = BBLocalization.Current.Get(key = "2f764267-3dba-4df6-a6ff-842f0b9aa254", fallback = "Politikalar"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            icon = Icons.Outlined.Policy
        ),
        LegalDocumentItem(
            legalDocumentId = 6,
            title = BBLocalization.Current.Get(key = "0c25ffe7-9d4c-45f1-bb52-13f82822dddd", fallback = "İçerik Yayınlama Politikası"),
            description = BBLocalization.Current.Get(key = "d9711039-605e-4758-b0f4-ec69d655a550", fallback = "Platformda yayınlanan ürün, firma ve içeriklere ilişkin temel kurallar."),
            categoryName = BBLocalization.Current.Get(key = "41fe178c-0054-4c06-8afd-86c7ad29b815", fallback = "Platform"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            icon = Icons.Outlined.Article
        ),
        LegalDocumentItem(
            legalDocumentId = 7,
            title = BBLocalization.Current.Get(key = "0ed62f3c-69a1-4aa7-befd-92ae1ce292c2", fallback = ""),
            description = BBLocalization.Current.Get(key = "14e9e8ce-c601-4b10-8b2a-79ce1e0078a9", fallback = "Destek süreçleri, başvuru kanalları ve kullanıcı bilgilendirme kuralları."),
            categoryName = "Destek",
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            icon = Icons.Outlined.SupportAgent
        ),
        LegalDocumentItem(
            legalDocumentId = 8,
            title = BBLocalization.Current.Get(key = "13b2abdf-5eb6-4f50-9d79-56a93fe9fc71", fallback = "İptal ve İade Şartları"),
            description = BBLocalization.Current.Get(key = "eb364789-1b3d-4a84-8158-a8096b62b522", fallback = "Sipariş iptali, iade süreci ve kullanıcı haklarına ilişkin bilgiler."),
            categoryName = BBLocalization.Current.Get(key = "f1a8c905-ab33-42bf-a5b5-4406092e01c2", fallback = "Satış"),
            updatedDateLabel = BBLocalization.Current.Get(key = "ce184222-2c25-4e57-ba10-80c9e6da557c", fallback = "Güncel"),
            icon = Icons.Outlined.Gavel
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun LegalCenterScreenPreview() {
    BbTheme {
        LegalCenterScreen()
    }
}

