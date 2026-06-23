package com.bulbulustur.android.Application.Areas.b2b.Views.Product

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
import androidx.compose.material.icons.rounded.BusinessCenter
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Factory
import androidx.compose.material.icons.rounded.Search
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
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigation
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleSearchHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun WholesaleMenuScreen(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBasketClick: () -> Unit,
    onAccountClick: () -> Unit,
    onCategoryClick: (Int) -> Unit,
    onCompanyListClick: () -> Unit = {},
    onRfqClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BBColors.SurfaceMuted
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            WholesaleSearchHeader(
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

            WholesaleMenuContent(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onCategoryClick = onCategoryClick,
                onCompanyListClick = onCompanyListClick,
                onRfqClick = onRfqClick,
                onSearchClick = onSearchClick
            )

            WholesaleBottomNavigation(
                selectedItem = WholesaleBottomNavigationItem.Menu,
                onItemClick = { selectedItem ->
                    when (selectedItem) {
                        WholesaleBottomNavigationItem.Home -> onHomeClick()
                        WholesaleBottomNavigationItem.Menu -> Unit
                        WholesaleBottomNavigationItem.ModeSwitch -> Unit
                        WholesaleBottomNavigationItem.Basket -> onBasketClick()
                        WholesaleBottomNavigationItem.Account -> onAccountClick()
                    }
                }
            )
        }
    }
}

@Composable
private fun WholesaleMenuContent(
    modifier: Modifier,
    onCategoryClick: (Int) -> Unit,
    onCompanyListClick: () -> Unit,
    onRfqClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.PageTopCompact,
            end = BBSpacing.PageHorizontal,
            bottom = BBSpacing.PageBottomWithCta
        ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            WholesaleMenuHero()
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            WholesaleMenuQuickActions(
                onCompanyListClick = onCompanyListClick,
                onRfqClick = onRfqClick,
                onSearchClick = onSearchClick
            )
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            WholesaleMenuSectionTitle()
        }

        items(
            items = wholesaleMenuCategories(),
            key = {
                it.categoryId
            }
        ) { category ->
            WholesaleMenuCategoryCard(
                category = category,
                onClick = {
                    onCategoryClick(category.categoryId)
                }
            )
        }
    }
}

@Composable
private fun WholesaleMenuHero() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(BBRadius.XlShape)
            .background(BBColors.White)
            .padding(BBSpacing.Space5)
    ) {
        WholesaleMenuBadge(
            text = "Toptan Kategori Keşfi"
        )

        Spacer(
            modifier = Modifier.height(BBSpacing.Space3)
        )

        Text(
            text = "Sektörleri, tedarikçileri ve toptan ürün gruplarını keşfet",
            style = MaterialTheme.typography.headlineSmall,
            color = BBColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(BBSpacing.Space2)
        )

        Text(
            text = "Ana kategoriye gir, kategori ana sayfasında alt kırılımlar, tedarikçiler, ürün Vitrinleri ve teklif akışlarıyla devam et.",
            style = MaterialTheme.typography.bodySmall,
            color = BBColors.TextMuted
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WholesaleMenuQuickActions(
    onCompanyListClick: () -> Unit,
    onRfqClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        WholesaleMenuQuickActionCard(
            title = "Tedarikçiler",
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Factory,
                    contentDescription = null,
                    tint = BBColors.TextStrong,
                    modifier = Modifier.size(18.dp)
                )
            },
            onClick = onCompanyListClick
        )

        WholesaleMenuQuickActionCard(
            title = "Teklif İste",
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Description,
                    contentDescription = null,
                    tint = BBColors.TextStrong,
                    modifier = Modifier.size(18.dp)
                )
            },
            onClick = onRfqClick
        )

        WholesaleMenuQuickActionCard(
            title = "Ara",
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = BBColors.TextStrong,
                    modifier = Modifier.size(18.dp)
                )
            },
            onClick = onSearchClick
        )
    }
}

@Composable
private fun WholesaleMenuQuickActionCard(
    title: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        color = BBColors.White,
        shape = BBRadius.PillShape,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            icon()

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun WholesaleMenuSectionTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BBSpacing.Space2)
    ) {
        Text(
            text = "Ana Kategoriler",
            style = MaterialTheme.typography.titleLarge,
            color = BBColors.TextStrong,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(BBSpacing.Space1)
        )

        Text(
            text = "Alt Kategoriler kategori ana sayfasında listelenecek.",
            style = MaterialTheme.typography.bodySmall,
            color = BBColors.TextMuted
        )
    }
}

@Composable
private fun WholesaleMenuCategoryCard(
    category: WholesaleMenuCategory,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.05f)
            .clip(BBRadius.XlShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.XlShape,
        color = BBColors.White,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BBSpacing.Space3),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(BBSpacing.Space11)
                    .clip(BBRadius.IconBoxSoft)
                    .background(BBColors.Navy.Navy50),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.BusinessCenter,
                    contentDescription = null,
                    tint = BBColors.Navy.Navy700,
                    modifier = Modifier.size(22.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = BBColors.TextStrong,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )

                Spacer(
                    modifier = Modifier.height(BBSpacing.Space1)
                )

                Text(
                    text = "Sektöre gir",
                    style = MaterialTheme.typography.labelSmall,
                    color = BBColors.TextMuted,
                    textAlign = TextAlign.Center
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = null,
                tint = BBColors.TextSubtle,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun WholesaleMenuBadge(
    text: String
) {
    Surface(
        color = BBColors.PrimarySoft,
        shape = BBRadius.PillShape
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.TextStrong,
            fontWeight = FontWeight.Bold
        )
    }
}

@Immutable
private data class WholesaleMenuCategory(
    val categoryId: Int,
    val title: String
)

private fun wholesaleMenuCategories(): List<WholesaleMenuCategory> {
    return listOf(
        WholesaleMenuCategory(1, "Otomobil Parça ve Aksesuarları"),
        WholesaleMenuCategory(2, "Tüketici Elektroniği"),
        WholesaleMenuCategory(3, "Elektronik Parçalar"),
        WholesaleMenuCategory(4, "Moda Aksesuarları ve Ayakkabılar"),
        WholesaleMenuCategory(5, "Moda Giyim ve Kumaşlar"),
        WholesaleMenuCategory(6, "Yiyecek, Ev ve Evcil Hayvanlar"),
        WholesaleMenuCategory(7, "Mobilya ve Ev Dekorasyonu"),
        WholesaleMenuCategory(8, "Hediyeler ve Primer"),
        WholesaleMenuCategory(9, "Donanım"),
        WholesaleMenuCategory(10, "Sağlık ve Kişisel Bakım"),
        WholesaleMenuCategory(11, "Ev Aletleri"),
        WholesaleMenuCategory(12, "Endüstriyel Malzemeler"),
        WholesaleMenuCategory(13, "Makine ve Ekipman"),
        WholesaleMenuCategory(14, "Mobil Elektronik"),
        WholesaleMenuCategory(15, "Anne, Çocuk ve Oyuncaklar"),
        WholesaleMenuCategory(16, "Baskı ve Paketleme"),
        WholesaleMenuCategory(17, "Akıllı Yaşam Elektroniği"),
        WholesaleMenuCategory(18, "Spor ve Dış Mekan")
    )
}
