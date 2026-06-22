package com.bulbulustur.android.Application.Views.Support

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.HelpCenter
import androidx.compose.material.icons.outlined.IntegrationInstructions
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun SupportHomeScreen(
    onSearchClick: (String) -> Unit = {},
    onSupportCategoryClick: (Int) -> Unit = {},
    onSupportArticleClick: (Int) -> Unit = {},
    onVideoGuideClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Yardım Merkezi",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                SupportIntroCard()
            }

            item {
                SupportQuickSearchChips(
                    onSearchClick = onSearchClick
                )
            }

            item {
                BbSectionHeader(
                    title = "Doğru Alandan Başlayın",
                    subtitle = "Kullandığınız alana göre yardım kategorisini seçin"
                )
            }

            items(
                items = getSupportCategoryItems(),
                key = { category ->
                    category.categoryId
                }
            ) { category ->
                SupportCategoryCard(
                    category = category,
                    onClick = {
                        onSupportCategoryClick(category.categoryId)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Video Rehberler",
                    subtitle = "Sık kullanılan işlemleri hızlıca öğrenin"
                )
            }

            items(
                items = getSupportVideoGuideItems(),
                key = { videoGuide ->
                    videoGuide.videoGuideId
                }
            ) { videoGuide ->
                SupportVideoGuideCard(
                    videoGuide = videoGuide,
                    onClick = {
                        onVideoGuideClick(videoGuide.videoGuideId)
                    }
                )
            }

            item {
                BbSectionHeader(
                    title = "Popüler Yardım Başlıkları",
                    subtitle = "En çok aranan destek konuları"
                )
            }

            items(
                items = getSupportPopularArticleItems(),
                key = { article ->
                    article.articleId
                }
            ) { article ->
                SupportArticleCard(
                    article = article,
                    onClick = {
                        onSupportArticleClick(article.articleId)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(BBSpacing.Space4))
            }
        }
    }
}

@Composable
private fun SupportIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            SupportIconTitleRow(
                icon = Icons.Outlined.HelpCenter,
                title = "Yardım ve Destek"
            )

            Text(
                text = "Sorular, işlem rehberleri, ürün bilgileri ve destek başlıklarını tek yerden bulun.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SupportQuickSearchChips(
    onSearchClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
    ) {
        getSupportQuickSearchTerms().forEach { searchTerm ->
            BbChip(
                text = searchTerm,
                selected = false,
                onClick = {
                    onSearchClick(searchTerm)
                }
            )
        }
    }
}

@Composable
private fun SupportCategoryCard(
    category: SupportCategoryItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${category.articleCount} yardım başlığı",
                    style = MaterialTheme.typography.labelMedium,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SupportVideoGuideCard(
    videoGuide: SupportVideoGuideItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            SupportIconTitleRow(
                icon = Icons.Outlined.PlayCircle,
                title = "Video Rehber"
            )

            Text(
                text = videoGuide.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = videoGuide.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
            ) {
                BbChip(
                    text = videoGuide.durationLabel,
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = videoGuide.categoryName,
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun SupportArticleCard(
    article: SupportArticleItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = article.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = article.categoryName,
                    style = MaterialTheme.typography.labelMedium,
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
private fun SupportIconTitleRow(
    icon: ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.IconTextGap)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

data class SupportCategoryItem(
    val categoryId: Int,
    val title: String,
    val description: String,
    val articleCount: Int,
    val icon: ImageVector
)

data class SupportVideoGuideItem(
    val videoGuideId: Int,
    val title: String,
    val description: String,
    val durationLabel: String,
    val categoryName: String
)

data class SupportArticleItem(
    val articleId: Int,
    val title: String,
    val description: String,
    val categoryName: String,
    val icon: ImageVector
)

private fun getSupportQuickSearchTerms(): List<String> {
    return listOf(
        "Sipariş",
        "Ödeme",
        "Entegrasyon",
        "Hesap Yönetimi",
        "Tedarik",
        "Draugr"
    )
}

private fun getSupportCategoryItems(): List<SupportCategoryItem> {
    return listOf(
        SupportCategoryItem(
            categoryId = 1,
            title = "Toptan",
            description = "Toptan satış, teklif işlemleri ve işletme odaklı süreçler.",
            articleCount = 18,
            icon = Icons.Outlined.Business
        ),
        SupportCategoryItem(
            categoryId = 2,
            title = "Perakende",
            description = "Perakende satış, mağaza yönetimi ve sipariş destekleri.",
            articleCount = 22,
            icon = Icons.Outlined.Storefront
        ),
        SupportCategoryItem(
            categoryId = 3,
            title = "Draugr",
            description = "Kendi e-ticaret altyapınızı kurma, yönetme ve geliştirme.",
            articleCount = 12,
            icon = Icons.Outlined.Security
        ),
        SupportCategoryItem(
            categoryId = 4,
            title = "Entegrasyon",
            description = "API, kargo, ödeme ve dış sistem bağlantıları.",
            articleCount = 15,
            icon = Icons.Outlined.IntegrationInstructions
        )
    )
}

private fun getSupportVideoGuideItems(): List<SupportVideoGuideItem> {
    return listOf(
        SupportVideoGuideItem(
            videoGuideId = 1,
            title = "Bulbulustur Panele Giriş",
            description = "Paneli açma, temel ekranları tanıma ve ilk işlem adımları.",
            durationLabel = "3 dk",
            categoryName = "Başlangıç"
        ),
        SupportVideoGuideItem(
            videoGuideId = 2,
            title = "Hesap Bilgilerini Güncelleme",
            description = "Firma, kullanıcı ve iletişim bilgilerinizi nasıl güncellersiniz.",
            durationLabel = "4 dk",
            categoryName = "Hesap"
        ),
        SupportVideoGuideItem(
            videoGuideId = 3,
            title = "Yeni Ürün Ekleme",
            description = "Ürün oluşturma, görsel ekleme ve temel ürün bilgileri.",
            durationLabel = "5 dk",
            categoryName = "Ürün"
        )
    )
}

private fun getSupportPopularArticleItems(): List<SupportArticleItem> {
    return listOf(
        SupportArticleItem(
            articleId = 1,
            title = "KVKK Aydınlatma Metni",
            description = "Kişisel verilerle ilgili bilgilendirme metinleri.",
            categoryName = "Yardım Merkezi",
            icon = Icons.Outlined.Description
        ),
        SupportArticleItem(
            articleId = 2,
            title = "Ürün Statüleri Nelerdir?",
            description = "Ürünlerin yayın, onay ve görünürlük durumları.",
            categoryName = "Ürün Merkezi",
            icon = Icons.Outlined.Article
        ),
        SupportArticleItem(
            articleId = 3,
            title = "Fiyat ve Teslimat Şartlarının Görünmesi",
            description = "Toptan teklif ve teslimat bilgilerinin yönetimi.",
            categoryName = "Ticaret Merkezi",
            icon = Icons.Outlined.Business
        ),
        SupportArticleItem(
            articleId = 4,
            title = "Şifre Yönetimi",
            description = "Hesap güvenliği ve şifre değiştirme adımları.",
            categoryName = "Hesap Merkezi",
            icon = Icons.Outlined.Security
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SupportHomeScreenPreview() {
    BbTheme {
        SupportHomeScreen()
    }
}
