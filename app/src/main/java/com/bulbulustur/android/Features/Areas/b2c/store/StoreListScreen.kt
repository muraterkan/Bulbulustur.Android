package com.bulbulustur.android.Features.areas.b2c.store

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
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing
import com.bulbulustur.android.Ui.theme.BbTheme

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
        containerColor = BbColors.SurfaceMuted,
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
                .background(BbColors.SurfaceMuted)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.SectionGapCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
            .height(BbSpacing.Space10)
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = BbColors.Primary,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.SizeSm)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.TextStrong,
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            StoreListStatusPill(
                text = "Mağaza Rehberi"
            )

            Text(
                text = "Bulbulustur Mağazalarını Keşfet",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Bulbulustur’da yer alan perakende mağazaları inceleyin, ürünlerini keşfedin ve favori satıcılarınıza hızlıca ulaşın.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextMuted
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                StoreListHeroMiniCard(
                    modifier = Modifier.weight(1f),
                    title = "Güvenilir Satıcılar",
                    subtitle = "Doğrulanmış mağaza vitrinlerini inceleyin.",
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
        shape = BbRadius.XlShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Row(
            modifier = Modifier.padding(BbSpacing.Space3),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Surface(
                modifier = Modifier.size(BbIcon.BoxMd),
                shape = BbRadius.LgShape,
                color = BbColors.PrimarySoft
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
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
        shape = BbRadius.XlShape,
        color = BbColors.Surface,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.Space2),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = BbColors.TextMuted
                )
            },
            placeholder = {
                Text(
                    text = "Mağaza, kategori veya ürün ara",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )
            },
            shape = BbRadius.Input,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BbColors.Primary,
                unfocusedBorderColor = BbColors.Border,
                focusedLabelColor = BbColors.TextStrong,
                unfocusedLabelColor = BbColors.TextMuted,
                focusedTextColor = BbColors.TextStrong,
                unfocusedTextColor = BbColors.TextStrong,
                focusedContainerColor = BbColors.Surface,
                unfocusedContainerColor = BbColors.Surface,
                cursorColor = BbColors.Primary
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = if (selected) {
            BbColors.Primary
        } else {
            BbColors.Surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                BbColors.Primary
            } else {
                BbColors.Border
            }
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            ),
            style = MaterialTheme.typography.labelMedium,
            color = BbColors.TextStrong,
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = "Mağaza Listesi",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )

        Text(
            text = if (selectedFilter == "Tümü") {
                "$storeCount mağaza listeleniyor"
            } else {
                "$selectedFilter filtresinde $storeCount mağaza"
            },
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextMuted
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(64.dp),
                    shape = BbRadius.XlShape,
                    color = BbColors.PrimarySoft,
                    border = BorderStroke(
                        width = 1.dp,
                        color = BbColors.Primary.copy(alpha = 0.35f)
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
                            color = BbColors.TextStrong
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                    ) {
                        Text(
                            text = store.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = BbColors.TextStrong,
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
                        color = BbColors.TextMuted,
                        maxLines = 2
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = BbColors.TextMuted,
                    modifier = Modifier.size(BbIcon.SizeMd)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BbIcon.BoxLg),
                shape = BbRadius.XlShape,
                color = BbColors.SurfaceMuted,
                border = BorderStroke(
                    width = 1.dp,
                    color = BbColors.Border
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
                        color = BbColors.Primary
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
                color = BbColors.TextStrong
            )

            Text(
                text = "Daha sonra tekrar kontrol edebilir veya kendi mağazanızı açarak ürünlerinizi listeleyebilirsiniz.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextMuted
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BbIcon.BoxMd),
                shape = BbRadius.LgShape,
                color = BbColors.Primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "Bulbulustur’da Mağaza Açın",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = "Ürünlerinizi listeleyin, mağazanızı yönetin ve müşterilere tek platformdan ulaşın.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextMuted
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = BbColors.TextMuted,
                modifier = Modifier.size(BbIcon.SizeMd)
            )
        }
    }
}

@Composable
private fun StoreListStatusPill(
    text: String
) {
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary.copy(alpha = 0.35f)
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )
    }
}

@Composable
private fun StoreListMiniBadge(
    text: String
) {
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.PrimarySoft,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space2,
                vertical = BbSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = BbColors.Primary,
                modifier = Modifier.size(BbIcon.Size2Xs)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )
        }
    }
}

@Composable
private fun StoreListMetaPill(
    text: String
) {
    Surface(
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextMuted
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
            .height(BbSpacing.Space11)
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = BbColors.Primary,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Primary
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.TextStrong,
                modifier = Modifier.size(BbIcon.SizeSm)
            )

            Spacer(modifier = Modifier.width(BbSpacing.Space1))

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
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
            .height(BbSpacing.Space11)
            .clip(BbRadius.PillShape)
            .clickable {
                onClick()
            },
        shape = BbRadius.PillShape,
        color = BbColors.SurfaceMuted,
        border = BorderStroke(
            width = 1.dp,
            color = BbColors.Border
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
                color = BbColors.TextStrong
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