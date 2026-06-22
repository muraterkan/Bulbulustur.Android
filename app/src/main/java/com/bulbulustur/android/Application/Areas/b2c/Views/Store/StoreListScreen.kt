package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun StoreListScreen(
    onBackClick: () -> Unit = {},
    onStoreClick: (StoreListItem) -> Unit = {},
    onOpenStoreClick: () -> Unit = {},
    onHowItWorksClick: () -> Unit = {}
) {
    val stores = remember {
        getStoreListItems()
    }

    val alphabetFilters = remember {
        listOf(
            "Tümü",
            "0-9",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "İ",
            "K",
            "M",
            "O",
            "P",
            "S",
            "T",
            "U",
            "V",
            "Z"
        )
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedAlphabetFilter by remember {
        mutableStateOf("Tümü")
    }

    val filteredStores = remember(
        searchText,
        selectedAlphabetFilter,
        stores
    ) {
        val searchFilteredStores = if (searchText.isBlank()) {
            stores
        } else {
            stores.filter {
                it.name.contains(searchText, ignoreCase = true) ||
                        it.description.contains(searchText, ignoreCase = true) ||
                        it.categoryName.contains(searchText, ignoreCase = true)
            }
        }

        if (selectedAlphabetFilter == "Tümü") {
            searchFilteredStores
        } else if (selectedAlphabetFilter == "0-9") {
            searchFilteredStores.filter {
                it.name.firstOrNull()?.isDigit() == true
            }
        } else {
            searchFilteredStores.filter {
                it.name.startsWith(selectedAlphabetFilter, ignoreCase = true)
            }
        }
    }

    Scaffold(
        containerColor = BBColors.SurfaceMuted,
        topBar = {
            BbInnerPageHeader(
                title = "Mağazalar",
                onBackClick = onBackClick,
                actionContent = {
                    StoreListHeaderActionButton(
                        text = "Mağaza Aç",
                        icon = Icons.Outlined.Storefront,
                        onClick = onOpenStoreClick
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BBColors.SurfaceMuted)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.SectionGapCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                StoreListHeroCard(
                    onOpenStoreClick = onOpenStoreClick,
                    onHowItWorksClick = onHowItWorksClick
                )
            }

            item {
                StoreListSearchCard(
                    searchText = searchText,
                    onSearchTextChange = {
                        searchText = it
                    }
                )
            }

            item {
                StoreAlphabetFilterRow(
                    filters = alphabetFilters,
                    selectedFilter = selectedAlphabetFilter,
                    onFilterClick = {
                        selectedAlphabetFilter = it
                    }
                )
            }

            item {
                StoreListResultHeader(
                    storeCount = filteredStores.size,
                    selectedFilter = selectedAlphabetFilter
                )
            }

            if (filteredStores.isEmpty()) {
                item {
                    StoreListEmptyState(
                        onOpenStoreClick = onOpenStoreClick
                    )
                }
            } else {
                items(
                    items = filteredStores,
                    key = { store -> store.id }
                ) { store ->
                    StoreListCard(
                        store = store,
                        onClick = {
                            onStoreClick(store)
                        }
                    )
                }

                item {
                    StoreListOpenStoreBanner(
                        onOpenStoreClick = onOpenStoreClick
                    )
                }
            }
        }
    }
}

@Composable
private fun StoreListHeaderActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(BBSpacing.Space10)
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = BBColors.Primary,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Primary
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BBColors.TextStrong,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun StoreListHeroCard(
    onOpenStoreClick: () -> Unit,
    onHowItWorksClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            StoreListStatusPill(
                text = "Mağaza Rehberi"
            )

            Text(
                text = "Bulbulustur Mağazalarını Keşfet",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = BBColors.TextStrong
            )

            Text(
                text = "Bulbulustur’da yer alan perakende mağazaları inceleyin, ürünlerini keşfedin ve favori satıcılarınıza hızlıca ulaşın.",
                style = MaterialTheme.typography.bodyMedium,
                color = BBColors.TextMuted
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                StoreListPrimaryButton(
                    text = "Ücretsiz Mağaza Aç",
                    icon = Icons.Outlined.Storefront,
                    modifier = Modifier.weight(1f),
                    onClick = onOpenStoreClick
                )

                StoreListSecondaryButton(
                    text = "Nasıl Çalışır?",
                    modifier = Modifier.weight(1f),
                    onClick = onHowItWorksClick
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                StoreListHeroMiniCard(
                    modifier = Modifier.weight(1f),
                    title = "Güvenilir Satıcılar",
                    subtitle = "Doğrulanmış mağaza Vitrinlerini inceleyin.",
                    icon = Icons.Outlined.Verified
                )

                StoreListHeroMiniCard(
                    modifier = Modifier.weight(1f),
                    title = "Favori Mağazalar",
                    subtitle = "Beğendiğiniz mağazaları takip edin.",
                    icon = Icons.Outlined.FavoriteBorder
                )
            }
        }
    }
}

@Composable
private fun StoreListHeroMiniCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector
) {
    Surface(
        modifier = modifier,
        shape = BBRadius.XlShape,
        color = BBColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Row(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = BBColors.PrimarySoft
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = BBColors.TextStrong,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BBColors.TextStrong
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun StoreListSearchCard(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = BBColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space2),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = BBColors.TextMuted
                )
            },
            placeholder = {
                Text(
                    text = "Mağaza, kategori veya ürün ara",
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted
                )
            },
            shape = BBRadius.Input,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BBColors.Primary,
                unfocusedBorderColor = BBColors.Border,
                focusedLabelColor = BBColors.TextStrong,
                unfocusedLabelColor = BBColors.TextMuted,
                focusedTextColor = BBColors.TextStrong,
                unfocusedTextColor = BBColors.TextStrong,
                focusedContainerColor = BBColors.Surface,
                unfocusedContainerColor = BBColors.Surface,
                cursorColor = BBColors.Primary
            )
        )
    }
}

@Composable
private fun StoreAlphabetFilterRow(
    filters: List<String>,
    selectedFilter: String,
    onFilterClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        filters.forEach { filter ->
            StoreAlphabetChip(
                text = filter,
                selected = selectedFilter == filter,
                onClick = {
                    onFilterClick(filter)
                }
            )
        }
    }
}

@Composable
private fun StoreAlphabetChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = if (selected) {
            BBColors.Primary
        } else {
            BBColors.Surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                BBColors.Primary
            } else {
                BBColors.Border
            }
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            style = MaterialTheme.typography.labelMedium,
            color = BBColors.TextStrong,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun StoreListResultHeader(
    storeCount: Int,
    selectedFilter: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = "Mağaza Listesi",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BBColors.TextStrong
        )

        Text(
            text = if (selectedFilter == "Tümü") {
                "$storeCount mağaza listeleniyor"
            } else {
                "$selectedFilter filtresinde $storeCount mağaza"
            },
            style = MaterialTheme.typography.bodySmall,
            color = BBColors.TextMuted
        )
    }
}

@Composable
private fun StoreListCard(
    store: StoreListItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(64.dp),
                    shape = BBRadius.XlShape,
                    color = BBColors.PrimarySoft,
                    border = BorderStroke(
                        width = 1.dp,
                        color = BBColors.Primary.copy(alpha = 0.35f)
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = store.logoText,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = BBColors.TextStrong
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        Text(
                            text = store.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = BBColors.TextStrong,
                            maxLines = 1
                        )

                        if (store.isVerified) {
                            StoreListMiniBadge(
                                text = "Doğrulanmış"
                            )
                        }
                    }

                    Text(
                        text = store.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = BBColors.TextMuted,
                        maxLines = 2
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = BBColors.TextMuted,
                    modifier = Modifier.size(BBIcon.SizeMd)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                StoreListMetaPill(
                    text = "${store.productCount} Ürün"
                )

                StoreListMetaPill(
                    text = store.ratingText
                )

                StoreListMetaPill(
                    text = store.categoryName
                )
            }

            StoreListPrimaryButton(
                text = "Mağazayı İncele",
                icon = Icons.Outlined.Storefront,
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick
            )
        }
    }
}

@Composable
private fun StoreListEmptyState(
    onOpenStoreClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxLg),
                shape = BBRadius.XlShape,
                color = BBColors.SurfaceMuted,
                border = BorderStroke(
                    width = 1.dp,
                    color = BBColors.Border
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "∅",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = BBColors.Primary
                    )
                }
            }

            StoreListStatusPill(
                text = "Mağaza Bulunamadı"
            )

            Text(
                text = "Listelenecek Mağaza Bulunmuyor",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = BBColors.TextStrong
            )

            Text(
                text = "Daha sonra tekrar kontrol edebilir veya kendi mağazanızı açarak ürünlerinizi listeleyebilirsiniz.",
                style = MaterialTheme.typography.bodyMedium,
                color = BBColors.TextMuted
            )

            StoreListPrimaryButton(
                text = "Mağaza Aç",
                icon = Icons.Outlined.Storefront,
                modifier = Modifier.fillMaxWidth(),
                onClick = onOpenStoreClick
            )
        }
    }
}

@Composable
private fun StoreListOpenStoreBanner(
    onOpenStoreClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onOpenStoreClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = BBColors.Primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = BBColors.TextStrong,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Bulbulustur’da Mağaza Açın",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BBColors.TextStrong
                )

                Text(
                    text = "Ürünlerinizi listeleyin, mağazanızı yönetin ve müşterilere tek platformdan ulaşın.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BBColors.TextMuted
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = BBColors.TextMuted,
                modifier = Modifier.size(BBIcon.SizeMd)
            )
        }
    }
}

@Composable
private fun StoreListStatusPill(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = BBColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Primary.copy(alpha = 0.35f)
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = BBColors.TextStrong
        )
    }
}

@Composable
private fun StoreListMiniBadge(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = BBColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Primary.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = BBColors.Primary,
                modifier = Modifier.size(BBIcon.Size2Xs)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = BBColors.TextStrong
            )
        }
    }
}

@Composable
private fun StoreListMetaPill(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = BBColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.TextMuted
        )
    }
}

@Composable
private fun StoreListPrimaryButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(BBSpacing.Space11)
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = BBColors.Primary,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Primary
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BBColors.TextStrong,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Spacer(modifier = Modifier.width(BBSpacing.Space1))

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = BBColors.TextStrong
            )
        }
    }
}

@Composable
private fun StoreListSecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(BBSpacing.Space11)
            .clip(BBRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BBRadius.PillShape,
        color = BBColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BBColors.Border
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = BBColors.TextStrong
            )
        }
    }
}

@Immutable
data class StoreListItem(
    val id: Int,
    val name: String,
    val description: String,
    val logoText: String,
    val categoryName: String,
    val productCount: Int,
    val ratingText: String,
    val isVerified: Boolean
)

private fun getStoreListItems(): List<StoreListItem> {
    return listOf(
        StoreListItem(
            id = 1,
            name = "Ortobella Comfort",
            description = "Ayakkabı ve günlük konfor ürünleri mağazası.",
            logoText = "OC",
            categoryName = "Ayakkabı",
            productCount = 48,
            ratingText = "★ 4.8",
            isVerified = true
        ),
        StoreListItem(
            id = 2,
            name = "Moda Nova",
            description = "Giyim, basic ürünler ve sezonluk parçalar.",
            logoText = "MN",
            categoryName = "Giyim",
            productCount = 124,
            ratingText = "★ 4.6",
            isVerified = true
        ),
        StoreListItem(
            id = 3,
            name = "Urban Touch",
            description = "Çanta, aksesuar ve şehir yaşamı ürünleri.",
            logoText = "UT",
            categoryName = "Aksesuar",
            productCount = 72,
            ratingText = "★ 4.5",
            isVerified = false
        ),
        StoreListItem(
            id = 4,
            name = "Casa Liva",
            description = "Ev, yaşam ve mutfak düzenleyici ürünleri.",
            logoText = "CL",
            categoryName = "Ev & Yaşam",
            productCount = 96,
            ratingText = "★ 4.7",
            isVerified = true
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun StoreListScreenPreview() {
    BbTheme {
        StoreListScreen()
    }
}
