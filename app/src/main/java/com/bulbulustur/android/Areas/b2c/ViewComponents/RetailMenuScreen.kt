package com.bulbulustur.android.Areas.b2c.ViewComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.LocalOffer
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Areas.b2c.Views.components.RetailBottomNavigation
import com.bulbulustur.android.Areas.b2c.Views.components.RetailBottomNavigationItem
import com.bulbulustur.android.Areas.b2c.Views.components.RetailSearchHeader
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun RetailMenuScreen(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBasketClick: () -> Unit,
    onAccountClick: () -> Unit,
    onCategoryClick: (Int) -> Unit,
    onCampaignsClick: () -> Unit = {},
    onStoresClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BbColors.SurfaceMuted
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = {
                    searchText = it
                },
                onMenuClick = {},
                onFavoriteClick = onFavoriteClick,
                onSearchClick = {
                    onSearchClick()
                },
                onClearClick = {
                    searchText = ""
                }
            )

            RetailMenuContent(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onCategoryClick = onCategoryClick,
                onCampaignsClick = onCampaignsClick,
                onStoresClick = onStoresClick,
                onSearchClick = onSearchClick
            )

            RetailBottomNavigation(
                selectedItem = RetailBottomNavigationItem.Menu,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        RetailBottomNavigationItem.Home -> onHomeClick()
                        RetailBottomNavigationItem.Menu -> Unit
                        RetailBottomNavigationItem.ModeSwitch -> Unit
                        RetailBottomNavigationItem.Basket -> onBasketClick()
                        RetailBottomNavigationItem.Account -> onAccountClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun RetailMenuContent(
    modifier: Modifier,
    onCategoryClick: (Int) -> Unit,
    onCampaignsClick: () -> Unit,
    onStoresClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = BbSpacing.PageHorizontal,
            top = BbSpacing.PageTopCompact,
            end = BbSpacing.PageHorizontal,
            bottom = BbSpacing.PageBottomWithCta
        ),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
    ) {
        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            RetailMenuHero()
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            RetailMenuQuickActions(
                onCampaignsClick = onCampaignsClick,
                onStoresClick = onStoresClick,
                onSearchClick = onSearchClick
            )
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            RetailMenuSectionTitle()
        }

        items(
            items = retailMenuCategories(),
            key = {
                it.categoryId
            }
        ) { category ->
            RetailMenuCategoryCard(
                category = category,
                onClick = {
                    onCategoryClick(category.categoryId)
                }
            )
        }
    }
}

@Composable
private fun RetailMenuHero() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(BbRadius.XlShape)
            .background(BbColors.White)
            .padding(BbSpacing.Space5)
    ) {
        RetailMenuBadge(
            text = "Perakende kategori keşfi"
        )

        Spacer(
            modifier = Modifier.height(BbSpacing.Space3)
        )

        Text(
            text = "Alışveriş dünyasına kategori kapısından gir",
            style = MaterialTheme.typography.headlineSmall,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(BbSpacing.Space2)
        )

        Text(
            text = "Ana kategorileri keşfet, kategori ana sayfasında alt kırılımlar, ürün vitrinleri ve kampanyalara ulaş.",
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextMuted
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RetailMenuQuickActions(
    onCampaignsClick: () -> Unit,
    onStoresClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        RetailMenuQuickActionCard(
            title = "Kampanyalar",
            icon = {
                Icon(
                    imageVector = Icons.Rounded.LocalOffer,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(18.dp)
                )
            },
            onClick = onCampaignsClick
        )

        RetailMenuQuickActionCard(
            title = "Mağazalar",
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Storefront,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(18.dp)
                )
            },
            onClick = onStoresClick
        )

        RetailMenuQuickActionCard(
            title = "Ara",
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(18.dp)
                )
            },
            onClick = onSearchClick
        )
    }
}

@Composable
private fun RetailMenuQuickActionCard(
    title: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        color = BbColors.White,
        shape = BbRadius.PillShape,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            icon()

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun RetailMenuSectionTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BbSpacing.Space2)
    ) {
        Text(
            text = "Ana Kategoriler",
            style = MaterialTheme.typography.titleLarge,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(BbSpacing.Space1)
        )

        Text(
            text = "Alt kategoriler kategori ana sayfasında listelenecek.",
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextMuted
        )
    }
}

@Composable
private fun RetailMenuCategoryCard(
    category: RetailMenuCategory,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.05f)
            .clip(BbRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.XlShape,
        color = BbColors.White,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BbSpacing.Space3),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space11)
                    .clip(BbRadius.IconBoxSoft)
                    .background(BbColors.PrimarySoft),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Category,
                    contentDescription = null,
                    tint = BbColors.TextStrong,
                    modifier = Modifier.size(22.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = BbColors.TextStrong,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )

                Spacer(
                    modifier = Modifier.height(BbSpacing.Space1)
                )

                Text(
                    text = "Kategoriye gir",
                    style = MaterialTheme.typography.labelSmall,
                    color = BbColors.TextMuted,
                    textAlign = TextAlign.Center
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = null,
                tint = BbColors.TextSubtle,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun RetailMenuBadge(
    text: String
) {
    Surface(
        color = BbColors.PrimarySoft,
        shape = BbRadius.PillShape
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextStrong,
            fontWeight = FontWeight.Bold
        )
    }
}

@Immutable
private data class RetailMenuCategory(
    val categoryId: Int,
    val title: String
)

private fun retailMenuCategories(): List<RetailMenuCategory> {
    return listOf(
        RetailMenuCategory(1, "Otomobil Parça ve Aksesuarları"),
        RetailMenuCategory(2, "Tüketici Elektroniği"),
        RetailMenuCategory(3, "Elektronik Parçalar"),
        RetailMenuCategory(4, "Moda Aksesuarları ve Ayakkabılar"),
        RetailMenuCategory(5, "Moda Giyim ve Kumaşlar"),
        RetailMenuCategory(6, "Yiyecek, Ev ve Evcil Hayvanlar"),
        RetailMenuCategory(7, "Mobilya ve Ev Dekorasyonu"),
        RetailMenuCategory(8, "Hediyeler ve Primer"),
        RetailMenuCategory(9, "Donanım"),
        RetailMenuCategory(10, "Sağlık ve Kişisel Bakım"),
        RetailMenuCategory(11, "Ev Aletleri"),
        RetailMenuCategory(12, "Endüstriyel Malzemeler"),
        RetailMenuCategory(13, "Makine ve Ekipman"),
        RetailMenuCategory(14, "Mobil Elektronik"),
        RetailMenuCategory(15, "Anne, Çocuk ve Oyuncaklar"),
        RetailMenuCategory(16, "Baskı ve Paketleme"),
        RetailMenuCategory(17, "Akıllı Yaşam Elektroniği"),
        RetailMenuCategory(18, "Spor ve Dış Mekan")
    )
}
