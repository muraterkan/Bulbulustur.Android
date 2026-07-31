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
                    text = "Bulbulustur Legal Center",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
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
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                BbChip(
                    text = "Politikalar",
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
                    text = "Yasal metinler veya platform kuralları hakkında destek almak için yardım merkezine geçin.",
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
            title = BBLocalization.Current.Get(key = "786cbd9d-09d8-4c72-97ff-17ead1eff098", fallback = "Kullanım Koşulları"),
            description = "Bulbulustur uygulaması ve platform kullanımına ilişkin temel koşullar.",
            categoryName = BBLocalization.Current.Get(key = "4a78c2ab-57cb-41d3-9831-b6bf045ad4b8", fallback = "Koşullar"),
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.Article
        ),
        LegalDocumentItem(
            legalDocumentId = 4,
            title = BBLocalization.Current.Get(key = "cbadbcf8-3e92-481d-b7bd-c002a9003abb", fallback = "Mesafeli Satış Sözleşmesi"),
            description = "Perakende Alışveriş süreçlerinde geçerli satış ve teslimat hükümleri.",
            categoryName = "Satış",
            updatedDateLabel = "Güncel",
            icon = Icons.Outlined.RequestQuote
        )
    )
}

private fun platformRuleItems(): List<LegalDocumentItem> {
    return listOf(
        LegalDocumentItem(
            legalDocumentId = 5,
            title = BBLocalization.Current.Get(key = "9dbf472e-78f6-43a6-97cd-b748c738b6d7", fallback = "Çerez Politikası"),
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
            title = BBLocalization.Current.Get(key = "0ed62f3c-69a1-4aa7-befd-92ae1ce292c2", fallback = ""),
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

