package com.bulbulustur.android.Application.Areas.b2b.Views.Product

import com.bulbulustur.android.Application.Localization.BBLocalization

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
        color = MaterialTheme.colorScheme.surfaceVariant
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
            text = BBLocalization.Current.Get(key = "19785118-e034-45bc-9f8d-067e4f2325ab", fallback = "Toptan Kategori Keşfi")
        )

        Spacer(
            modifier = Modifier.height(BBSpacing.Space3)
        )

        Text(
            text = BBLocalization.Current.Get(key = "80b58e1f-101b-430b-8168-ad978ff042b3", fallback = "Sektörleri, tedarikçileri ve toptan ürün gruplarını keşfet"),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(BBSpacing.Space2)
        )

        Text(
            text = BBLocalization.Current.Get(key = "73907a4f-cb17-46f7-90c2-0feb546193ec", fallback = "Ana kategoriye gir, kategori ana sayfasında alt kırılımlar, tedarikçiler, ürün Vitrinleri ve teklif akışlarıyla devam et."),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            title = BBLocalization.Current.Get(key = "9346820b-56b1-4be1-9ac2-4d6f22fa18b5", fallback = "Tedarikçiler"),
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Factory,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(18.dp)
                )
            },
            onClick = onCompanyListClick
        )

        WholesaleMenuQuickActionCard(
            title = BBLocalization.Current.Get(key = "9aa9e9a4-18b3-427b-943f-36170e46cb37", fallback = "Teklif Al"),
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Description,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(18.dp)
                )
            },
            onClick = onRfqClick
        )

        WholesaleMenuQuickActionCard(
            title = BBLocalization.Current.Get(key = "b62c3162-72af-439f-a20b-c4ca58f51035", fallback = "Ara"),
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
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
            color = MaterialTheme.colorScheme.outlineVariant
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
                color = MaterialTheme.colorScheme.onSurface,
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
            text = BBLocalization.Current.Get(key = "c0b1dbee-2ead-42f0-9803-724dc48c055c", fallback = "Ana Kategoriler"),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(BBSpacing.Space1)
        )

        Text(
            text = BBLocalization.Current.Get(key = "9fa20011-37a2-4328-95a2-9bad52bbf8b4", fallback = "Alt Kategoriler kategori ana sayfasında listelenecek."),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            color = MaterialTheme.colorScheme.outlineVariant
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
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )

                Spacer(
                    modifier = Modifier.height(BBSpacing.Space1)
                )

                Text(
                    text = BBLocalization.Current.Get(key = "eb772521-85ff-4a4e-89da-363c53e2d712", fallback = "Sektöre gir"),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
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
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = BBRadius.PillShape
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
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
        WholesaleMenuCategory(2, BBLocalization.Current.Get(key = "fafed4f2-263a-4555-816b-3ccc58c31b77", fallback = "Tüketici Elektroniği")),
        WholesaleMenuCategory(3, BBLocalization.Current.Get(key = "3fef55c6-116c-43de-bc54-9d81d571e7dd", fallback = "Elektronik Parçalar")),
        WholesaleMenuCategory(4, "Moda Aksesuarları ve Ayakkabılar"),
        WholesaleMenuCategory(5, "Moda Giyim ve Kumaşlar"),
        WholesaleMenuCategory(6, BBLocalization.Current.Get(key = "30b90139-2535-4203-8f21-2899850dd34e", fallback = "Yiyecek, Ev ve Evcil Hayvanlar")),
        WholesaleMenuCategory(7, "Mobilya ve Ev Dekorasyonu"),
        WholesaleMenuCategory(8, BBLocalization.Current.Get(key = "6d690260-bfdc-4076-8f76-6ba927051199", fallback = "Hediyeler ve Primer")),
        WholesaleMenuCategory(9, BBLocalization.Current.Get(key = "e2d9ee4a-f543-4223-a2a9-729651759f4a", fallback = "Donanım")),
        WholesaleMenuCategory(10, "Sağlık ve Kişisel Bakım"),
        WholesaleMenuCategory(11, BBLocalization.Current.Get(key = "ef6ae962-4218-4763-83be-64fd949ff2af", fallback = "Ev Aletleri")),
        WholesaleMenuCategory(12, BBLocalization.Current.Get(key = "2ac5d1f3-1bf3-4f8d-b873-c05e097d643c", fallback = "Endüstriyel Malzemeler")),
        WholesaleMenuCategory(13, BBLocalization.Current.Get(key = "0ad29899-bac8-4bc2-88f7-cc2af6be001e", fallback = "Makine ve Ekipman")),
        WholesaleMenuCategory(14, "Mobil Elektronik"),
        WholesaleMenuCategory(15, "Anne, Çocuk ve Oyuncaklar"),
        WholesaleMenuCategory(16, "Baskı ve Paketleme"),
        WholesaleMenuCategory(17, "Akıllı Yaşam Elektroniği"),
        WholesaleMenuCategory(18, BBLocalization.Current.Get(key = "5deb432b-d645-40d7-abc6-d4334d58312e", fallback = "Spor ve Dış Mekan"))
    )
}

