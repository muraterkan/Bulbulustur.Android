package com.bulbulustur.android.Views.legal

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
import androidx.compose.material.icons.outlined.Description
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
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbChip
import com.bulbulustur.android.wwwroot.components.BbSectionHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

@Composable
fun LegalCenterScreen(
    onLegalDocumentClick: (Int) -> Unit = {},
    onSupportClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BbSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
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
                title = "Yasal metinler",
                subtitle = "Kullanım, gizlilik, KVKK ve satış süreçlerine ait metinler"
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
                title = "Platform kuralları",
                subtitle = "Satıcı, alıcı ve ziyaretçiler için geçerli temel metinler"
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
            Spacer(modifier = Modifier.height(BbSpacing.xl))
        }
    }
}

@Composable
private fun LegalCenterHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BbSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Gavel,
                    contentDescription = null,
                    tint = BbColors.Primary
                )

                Text(
                    text = "Bulbulustur Legal Center",
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Yasal ve platform metinleri",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Kullanım koşulları, gizlilik politikaları, platform kuralları ve satış süreçlerine ait metinleri buradan inceleyin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                BbChip(
                    text = "Politikalar",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Koşullar",
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
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.SupportAgent,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = "Yardım merkezi",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Yasal metinler veya platform kuralları hakkında destek almak için yardım merkezine geçin.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted
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
                .padding(BbSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = document.icon,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
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
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
                ) {
                    Text(
                        text = document.categoryName,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = BbColors.Primary
                    )

                    Text(
                        text = document.updatedDateLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = BbColors.TextMuted
                    )
                }
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BbColors.TextMuted
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
                .padding(BbSpacing.lg),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.VerifiedUser,
                contentDescription = null,
                tint = BbColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                Text(
                    text = "Metinler API ile beslenecek",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Bu ekran şimdilik yasal metin listesi skeleton’ıdır. Detay ekranında gerçek içerikler servis üzerinden gösterilecek.",
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
            title = "Gizlilik Politikası",
            description = "Bulbulustur üzerinde alınan, işlenen ve saklanan kişisel verilere ilişkin açıklamalar.",
            categoryName = "Politikalar",
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.Lock
        ),
        LegalDocumentItem(
            legalDocumentId = 2,
            title = "KVKK Aydınlatma Metni",
            description = "Kişisel verilerin korunması kapsamında bilgilendirme metni.",
            categoryName = "KVKK",
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.Security
        ),
        LegalDocumentItem(
            legalDocumentId = 3,
            title = "Kullanım Koşulları",
            description = "Bulbulustur uygulaması ve platform kullanımına ilişkin temel koşullar.",
            categoryName = "Koşullar",
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.Article
        ),
        LegalDocumentItem(
            legalDocumentId = 4,
            title = "Mesafeli Satış Sözleşmesi",
            description = "Perakende alışveriş süreçlerinde geçerli satış ve teslimat hükümleri.",
            categoryName = "Satış",
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.Description
        )
    )
}

private fun platformRuleItems(): List<LegalDocumentItem> {
    return listOf(
        LegalDocumentItem(
            legalDocumentId = 5,
            title = "Çerez Politikası",
            description = "Uygulama ve web deneyiminde kullanılan çerez ve benzeri teknolojilere ilişkin bilgiler.",
            categoryName = "Politikalar",
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.Policy
        ),
        LegalDocumentItem(
            legalDocumentId = 6,
            title = "İçerik Yayınlama Politikası",
            description = "Platformda yayınlanan ürün, firma ve içeriklere ilişkin temel kurallar.",
            categoryName = "Platform",
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.Article
        ),
        LegalDocumentItem(
            legalDocumentId = 7,
            title = "Destek Politikası",
            description = "Destek süreçleri, başvuru kanalları ve kullanıcı bilgilendirme kuralları.",
            categoryName = "Destek",
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.SupportAgent
        ),
        LegalDocumentItem(
            legalDocumentId = 8,
            title = "İptal ve İade Şartları",
            description = "Sipariş iptali, iade süreci ve kullanıcı haklarına ilişkin bilgiler.",
            categoryName = "Satış",
            updatedDateLabel = "Güncel",
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