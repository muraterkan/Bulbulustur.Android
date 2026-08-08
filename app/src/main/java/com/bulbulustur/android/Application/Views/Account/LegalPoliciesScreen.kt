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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
                title = BBLocalization.Current.Get(key = "8aa96ea1-dff6-4eda-86b6-81d12a74cbb6", fallback = "Yasal Metinler ve Politikalar"),
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
                    text = BBLocalization.Current.Get(key = "69b609a0-a577-4784-a0b8-0660c4b11649", fallback = "Bulbulustur Yasal Merkezi"),
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = BBLocalization.Current.Get(key = "1e212806-038c-47e5-b255-be647c0fbaf3", fallback = "Şeffaf platform kuralları, kullanıcı hakları ve yasal süreçler tek merkezde toplanır."),
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
            title = BBLocalization.Current.Get(key = "2f764267-3dba-4df6-a6ff-842f0b9aa254", fallback = "Politikalar"),
            description = BBLocalization.Current.Get(key = "558ba2bd-604d-4743-b6f9-08e3eb7d9bd3", fallback = "Gizlilik, veri işleme ve platform içerik kuralları."),
            icon = Icons.Outlined.Policy,
            items = listOf(
                LegalPolicyItem(
                    url = LegalPolicyUrls.Privacy,
                    title = BBLocalization.Current.Get(key = "2b5d9eab-d37e-4a08-b2ff-635ccf71620e", fallback = ""),
                    description = BBLocalization.Current.Get(key = "c8d874c0-d374-43bf-99a2-554dd30df97c", fallback = "Kişisel verilerin nasıl işlendiğini ve korunduğunu inceleyin."),
                    icon = Icons.Outlined.PrivacyTip
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.Cookie,
                    title = BBLocalization.Current.Get(key = "9dbf472e-78f6-43a6-97cd-b748c738b6d7", fallback = "Çerez Politikası"),
                    description = BBLocalization.Current.Get(key = "bc357ab1-a4d5-452d-9592-efd38110d493", fallback = "Çerez ve benzeri teknolojilerin kullanım detayları."),
                    icon = Icons.Outlined.Cookie
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.Kvkk,
                    title = BBLocalization.Current.Get(key = "3cf09908-6dc6-4077-a726-c2816e317905", fallback = "KVKK Aydınlatma Metni"),
                    description = BBLocalization.Current.Get(key = "666604ce-1230-40b6-9089-885ed01f92a1", fallback = "Kişisel verilerinizle ilgili yasal bilgilendirme."),
                    icon = Icons.Outlined.Security
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.Review,
                    title = BBLocalization.Current.Get(key = "1dd77bb1-5544-4d8b-8c6d-b43214923852", fallback = "Değerlendirme Politikası"),
                    description = BBLocalization.Current.Get(key = "f1b28670-67b5-4687-ba10-d7052354af3b", fallback = "Ürün Değerlendirme Süreçleri."),
                    icon = Icons.Outlined.VerifiedUser
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.ContentPublishing,
                    title = BBLocalization.Current.Get(key = "0c25ffe7-9d4c-45f1-bb52-13f82822dddd", fallback = "İçerik Yayınlama Politikası"),
                    description = BBLocalization.Current.Get(key = "bfd367d0-59de-4b1d-a702-4783eb8f56a6", fallback = "Platformda yayınlanan içerikler için temel kurallar."),
                    icon = Icons.Outlined.Article
                )
            )
        ),
        LegalPolicyGroup(
            title = BBLocalization.Current.Get(key = "4a78c2ab-57cb-41d3-9831-b6bf045ad4b8", fallback = "Koşullar"),
            description = BBLocalization.Current.Get(key = "8cf34d48-72b5-4eb8-bcd1-a04fc607424a", fallback = "Platform kullanımına ait temel koşullar."),
            icon = Icons.Outlined.Rule,
            items = listOf(
                LegalPolicyItem(
                    url = LegalPolicyUrls.Terms,
                    title = BBLocalization.Current.Get(key = "786cbd9d-09d8-4c72-97ff-17ead1eff098", fallback = "Kullanım Koşulları"),
                    description = BBLocalization.Current.Get(key = "a01d9192-8f52-4c90-8118-5ab1534d2843", fallback = "Bulbulustur hizmetlerini kullanırken geçerli temel koşullar."),
                    icon = Icons.Outlined.RequestQuote
                )
            )
        ),
        LegalPolicyGroup(
            title = BBLocalization.Current.Get(key = "037e70c7-3c1e-479b-a993-93bb337463a4", fallback = "Prosedürler"),
            description = BBLocalization.Current.Get(key = "ea0d5a7f-f0af-4f34-916f-b1066b9f73c2", fallback = "Soru ve destek süreçlerine ilişkin kurallar."),
            icon = Icons.Outlined.SupportAgent,
            items = listOf(
                LegalPolicyItem(
                    url = LegalPolicyUrls.Question,
                    title = BBLocalization.Current.Get(key = "37d567db-8b1b-419c-88fe-07c9629df8c5", fallback = "Soru Sorma Politikası"),
                    description = BBLocalization.Current.Get(key = "3e5f8ef5-2b5d-4d89-89b7-f95b6fdfac37", fallback = "Ürün ve satıcı sorularında geçerli iletişim kuralları."),
                    icon = Icons.Outlined.SupportAgent
                ),
                LegalPolicyItem(
                    url = LegalPolicyUrls.Support,
                    title = BBLocalization.Current.Get(key = "0ed62f3c-69a1-4aa7-befd-92ae1ce292c2", fallback = ""),
                    description = BBLocalization.Current.Get(key = "dd7dc382-3aeb-461e-93b5-8acc1f1f9a45", fallback = "Destek süreçleri ve kullanıcı iletişim kuralları."),
                    icon = Icons.Outlined.SupportAgent
                )
            )
        )
    )
}
