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
import androidx.compose.material.icons.outlined.RequestQuote
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Localization.LocalizationState
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
                    title = BBLocalization.Current.Get(key = "89c528e0-4de6-4ad6-ad4b-c933fd87b176", fallback = "Doğru Alandan Başlayın"),
                    subtitle = BBLocalization.Current.Get(key = "905cc9c9-e211-4909-a02a-84fc5f98b5d1", fallback = "Kullandığınız alana göre yardım kategorisini seçin")
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
                    title = BBLocalization.Current.Get(key = "c8244897-d3be-4c63-8f5e-ea505a080a38", fallback = "Video Rehberler"),
                    subtitle = BBLocalization.Current.Get(key = "9588b46a-9197-4e68-99fd-6df86367371d", fallback = "Sık kullanılan işlemleri hızlıca öğrenin")
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
                    title = BBLocalization.Current.Get(key = "85f5b9bd-1298-4460-a2df-60aedf0d6b99", fallback = "Popüler Yardım Başlıkları"),
                    subtitle = BBLocalization.Current.Get(key = "d719bc4c-2fd5-41f8-a9a4-bd5d5820338b", fallback = "En çok aranan destek konuları")
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
                title = BBLocalization.Current.Get(key = "dbb30895-e93b-465a-baaa-9f563d6b0588", fallback = "Yardım ve Destek")
            )

            Text(
                text = BBLocalization.Current.Get(key = "d1353a3f-8cd1-4c6f-aa0a-020cb1345927", fallback = "Sorular, işlem rehberleri, ürün bilgileri ve destek başlıklarını tek yerden bulun."),
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
    val localization = BBLocalization.Current
    val searchTerms = remember(localization.Resources) {
        getSupportQuickSearchTerms(localization)
    }

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
    ) {
        searchTerms.forEach { searchTerm ->
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

private fun getSupportQuickSearchTerms(
    localization: LocalizationState
): List<String> {
    return listOf(
        "Sipariş",
        localization.Get(
            key = "0d5a9dc6-b4eb-4f81-b5f1-d9d09a40cf40",
            fallback = ""
        ),
        BBLocalization.Current.Get(key = "591d8037-832d-49e9-9562-ebd3a9b91c45", fallback = "Entegrasyon"),
        localization.Get(
            key = "269fa827-0814-48a5-8f53-085a5db9cab7",
            fallback = ""
        ),
        "Tedarik",
        "Draugr"
    )
}

private fun getSupportCategoryItems(): List<SupportCategoryItem> {
    return listOf(
        SupportCategoryItem(
            categoryId = 1,
            title = BBLocalization.Current.Get(key = "cd90e72e-8745-4543-836b-ca914c3640f8", fallback = "Toptan"),
            description = BBLocalization.Current.Get(key = "a4003b44-21fc-479b-bcfd-3da67e3bceef", fallback = "Toptan satış, teklif işlemleri ve işletme odaklı süreçler."),
            articleCount = 18,
            icon = Icons.Outlined.Business
        ),
        SupportCategoryItem(
            categoryId = 2,
            title = BBLocalization.Current.Get(key = "f98fc2e2-635a-4404-831f-4c1fdca3885e", fallback = "Perakende"),
            description = BBLocalization.Current.Get(key = "4612dcbe-8b18-4be6-9db0-c31e62327e3d", fallback = "Perakende satış, mağaza yönetimi ve sipariş destekleri."),
            articleCount = 22,
            icon = Icons.Outlined.Storefront
        ),
        SupportCategoryItem(
            categoryId = 3,
            title = "Draugr",
            description = BBLocalization.Current.Get(key = "b63534db-3b0b-4cef-bc99-f793a5f1edd5", fallback = "Kendi e-ticaret altyapınızı kurma, yönetme ve geliştirme."),
            articleCount = 12,
            icon = Icons.Outlined.Security
        ),
        SupportCategoryItem(
            categoryId = 4,
            title = BBLocalization.Current.Get(key = "591d8037-832d-49e9-9562-ebd3a9b91c45", fallback = "Entegrasyon"),
            description = BBLocalization.Current.Get(key = "22999940-f1ec-4cd1-891c-7de621d50619", fallback = "API, kargo, ödeme ve dış sistem bağlantıları."),
            articleCount = 15,
            icon = Icons.Outlined.IntegrationInstructions
        )
    )
}

private fun getSupportVideoGuideItems(): List<SupportVideoGuideItem> {
    return listOf(
        SupportVideoGuideItem(
            videoGuideId = 1,
            title = BBLocalization.Current.Get(key = "b3c9acda-edcf-49f0-9134-7903f7086fda", fallback = "Bulbulustur Panele Giriş"),
            description = BBLocalization.Current.Get(key = "7e615055-a371-4b91-9260-bd94d56447b2", fallback = "Paneli açma, temel ekranları tanıma ve ilk işlem adımları."),
            durationLabel = "3 dk",
            categoryName = BBLocalization.Current.Get(key = "e99a6b9f-b1f9-40e8-a1c8-ac92c9bd649e", fallback = "Başlangıç")
        ),
        SupportVideoGuideItem(
            videoGuideId = 2,
            title = BBLocalization.Current.Get(key = "7a53f61b-6496-459d-a465-2035ad1a30eb", fallback = "Hesap Bilgilerini Güncelleme"),
            description = BBLocalization.Current.Get(key = "f44ba084-f19d-45dd-894d-a7c965cc5dd4", fallback = "Firma, kullanıcı ve iletişim bilgilerinizi nasıl güncellersiniz."),
            durationLabel = "4 dk",
            categoryName = BBLocalization.Current.Get(key = "12cf0c4a-1b66-4a2e-800c-dfe75644a6bc", fallback = "")
        ),
        SupportVideoGuideItem(
            videoGuideId = 3,
            title = BBLocalization.Current.Get(key = "62a9cd84-2eb9-4da4-8e13-7fee1d6e6e5f", fallback = "Yeni Ürün Ekleme"),
            description = BBLocalization.Current.Get(key = "adc093f9-427c-419d-9eec-1696925097ce", fallback = "Ürün oluşturma, görsel ekleme ve temel ürün bilgileri."),
            durationLabel = "5 dk",
            categoryName = BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = "")
        )
    )
}

private fun getSupportPopularArticleItems(): List<SupportArticleItem> {
    return listOf(
        SupportArticleItem(
            articleId = 1,
            title = BBLocalization.Current.Get(key = "3cf09908-6dc6-4077-a726-c2816e317905", fallback = "KVKK Aydınlatma Metni"),
            description = BBLocalization.Current.Get(key = "eaeeee53-cb52-4ee6-bb79-8098b97c55c3", fallback = "Kişisel verilerle ilgili bilgilendirme metinleri."),
            categoryName = "Yardım Merkezi",
            icon = Icons.Outlined.RequestQuote
        ),
        SupportArticleItem(
            articleId = 2,
            title = BBLocalization.Current.Get(key = "3f385b75-8a6e-46dc-9a38-f41e2691b82d", fallback = "Ürün Statüleri Nelerdir?"),
            description = BBLocalization.Current.Get(key = "60876af4-6c5d-4874-b375-0f2a8a3af427", fallback = "Ürünlerin yayın, onay ve görünürlük durumları."),
            categoryName = "Ürün Merkezi",
            icon = Icons.Outlined.Article
        ),
        SupportArticleItem(
            articleId = 3,
            title = BBLocalization.Current.Get(key = "9b94c04a-473e-42c3-841f-776a40c68c65", fallback = "Fiyat ve Teslimat Şartlarının Görünmesi"),
            description = BBLocalization.Current.Get(key = "8cb7c32b-8f3a-43d3-8960-2b213cfd2964", fallback = "Toptan teklif ve teslimat bilgilerinin yönetimi."),
            categoryName = BBLocalization.Current.Get(key = "2cd35f2b-e49a-4d02-bc3e-9068b3033291", fallback = "Ticaret Merkezi"),
            icon = Icons.Outlined.Business
        ),
        SupportArticleItem(
            articleId = 4,
            title = BBLocalization.Current.Get(key = "0830919e-bacb-4d31-9c6f-f68fdccf4af1", fallback = "Şifre Yönetimi"),
            description = BBLocalization.Current.Get(key = "fc6a02da-707b-414c-a179-28ea19f06789", fallback = "Hesap güvenliği ve şifre değiştirme adımları."),
            categoryName = BBLocalization.Current.Get(key = "561573fe-5a81-4f18-bb3f-90bdc1dda47e", fallback = "Hesap Merkezi"),
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

