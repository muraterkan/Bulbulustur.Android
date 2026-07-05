package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Config.LegalPolicyUrls

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
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.Rule
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.VerifiedUser
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
fun LegalPoliciesScreen(
    onBackClick: () -> Unit = {},
    onPolicyClick: (LegalPolicyItem) -> Unit = {}
) {
    val groups = remember {
        getLegalPolicyGroups()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
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
                    .padding(BBSpacing.CardPadding),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BBRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.Ui)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = title,
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
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
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BBRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
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
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = item.description,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Box(
            modifier = Modifier
                .size(BBIcon.BoxSm)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BBRadius.PillShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.SizeSm)
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
                start = BBSpacing.Space16,
                end = BBSpacing.Space4
            )
            .size(
                height = 1.dp,
                width = 1.dp
            )
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
data class LegalPolicyItem(
    val url: String,
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
                    url = LegalPolicyUrls.Privacy,
                    title = "Gizlilik Politikası",
                    description = "Kişisel verilerin nasıl işlendiğini ve korunduğunu inceleyin.",
                    icon = Icons.Outlined.PrivacyTip
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.Cookie,
                    title = "Çerez Politikası",
                    description = "Çerez ve benzeri teknolojilerin kullanım detayları.",
                    icon = Icons.Outlined.Cookie
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.Kvkk,
                    title = "KVKK Aydınlatma Metni",
                    description = "Kişisel verilerinizle ilgili yasal bilgilendirme.",
                    icon = Icons.Outlined.Security
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.Review,
                    title = "Değerlendirme Politikası",
                    description = "Ürün yorumları ve değerlendirme süreçleri.",
                    icon = Icons.Outlined.VerifiedUser
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.ContentPublishing,
                    title = "İçerik Yayınlama Politikası",
                    description = "Platformda yayınlanan içerikler için temel kurallar.",
                    icon = Icons.Outlined.Article
                )
            )
        ),
        LegalPolicyGroup(
            title = "Koşullar",
            description = "Platform kullanımına ait temel koşullar.",
            icon = Icons.Outlined.Rule,
            items = listOf(
                LegalPolicyItem(
                    url = LegalPolicyUrls.Terms,
                    title = "Kullanım Koşulları",
                    description = "Bulbulustur hizmetlerini kullanırken geçerli temel koşullar.",
                    icon = Icons.Outlined.RequestQuote
                )
            )
        ),
        LegalPolicyGroup(
            title = "Prosedürler",
            description = "Soru ve destek süreçlerine ilişkin kurallar.",
            icon = Icons.Outlined.SupportAgent,
            items = listOf(
                LegalPolicyItem(
                    url = LegalPolicyUrls.Question,
                    title = "Soru Sorma Politikası",
                    description = "Ürün ve satıcı sorularında geçerli iletişim kuralları.",
                    icon = Icons.Outlined.SupportAgent
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.Support,
                    title = "Destek Politikası",
                    description = "Destek süreçleri ve kullanıcı iletişim kuralları.",
                    icon = Icons.Outlined.SupportAgent
                )
            )
        )
    )
}
