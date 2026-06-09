package com.bulbulustur.android.features.store

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun StoreListScreen(
    onBackClick: () -> Unit = {},
    onStoreClick: (StoreListItem) -> Unit = {},
    onOpenStoreClick: () -> Unit = {},
    onHowItWorksClick: () -> Unit = {},
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
                        it.description.contains(searchText, ignoreCase = true)
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
        bottomBar = {
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = BbSpacing.md,
                    top = BbSpacing.md,
                    end = BbSpacing.md,
                    bottom = BbSpacing.xl
                ),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
            ) {
                item {
                    StoreListTopBar(
                        onBackClick = onBackClick
                    )
                }

                item {
                    StoreListHeroCard(
                        onOpenStoreClick = onOpenStoreClick,
                        onHowItWorksClick = onHowItWorksClick
                    )
                }

                item {

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
                    items(filteredStores) { store ->
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
}

@Composable
private fun StoreListTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbSpacing.xl)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong
            )
        }

        Spacer(modifier = Modifier.width(BbSpacing.md))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Mağazalar",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Perakende mağazaları keşfet",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextStrong.copy(alpha = 0.62f)
            )
        }
    }
}

@Composable
private fun StoreListHeroCard(
    onOpenStoreClick: () -> Unit,
    onHowItWorksClick: () -> Unit
) {
    BbCard {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            StoreListStatusPill(
                text = "Mağaza rehberi"
            )

            Text(
                text = "Bulbulustur mağazalarını keşfet",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Bulbulustur’da yer alan perakende mağazaları inceleyin, ürünlerini keşfedin ve favori satıcılarınıza hızlıca ulaşın.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextStrong.copy(alpha = 0.68f)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                StoreListPrimaryButton(
                    text = "Ücretsiz mağaza aç",
                    onClick = onOpenStoreClick
                )

                StoreListSecondaryButton(
                    text = "Nasıl çalışır?",
                    onClick = onHowItWorksClick
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                StoreListHeroMiniCard(
                    title = "Güvenilir",
                    subtitle = "Mağazaları incele"
                )

                StoreListHeroMiniCard(
                    title = "Favori",
                    subtitle = "Takip et"
                )
            }
        }
    }
}

@Composable
private fun StoreListHeroMiniCard(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.lg))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(BbSpacing.md)
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextStrong.copy(alpha = 0.62f)
            )
        }
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
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.xs)
    ) {
        filters.forEach { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = {
                    onFilterClick(filter)
                },
                label = {
                    Text(text = filter)
                }
            )
        }
    }
}

@Composable
private fun StoreListResultHeader(
    storeCount: Int,
    selectedFilter: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            BbSectionHeader(
                title = "Mağaza listesi",
                subtitle = if (selectedFilter == "Tümü") {
                    "$storeCount mağaza listeleniyor"
                } else {
                    "$selectedFilter filtresinde $storeCount mağaza"
                }
            )
        }
    }
}

@Composable
private fun StoreListCard(
    store: StoreListItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BbSpacing.Space16)
                        .clip(RoundedCornerShape(BbRadius.lg))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = store.logoText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = BbColors.TextStrong
                    )
                }

                Spacer(modifier = Modifier.width(BbSpacing.md))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = store.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = BbColors.TextStrong
                        )

                        if (store.isVerified) {
                            Spacer(modifier = Modifier.width(BbSpacing.sm))

                            StoreListMiniBadge(
                                text = "Doğrulanmış"
                            )
                        }
                    }

                    Text(
                        text = store.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = BbColors.TextStrong.copy(alpha = 0.62f),
                        maxLines = 2
                    )
                }

                Text(
                    text = "›",
                    style = MaterialTheme.typography.headlineSmall,
                    color = BbColors.TextStrong.copy(alpha = 0.52f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
            ) {
                StoreListMetaPill(
                    text = "${store.productCount} ürün"
                )

                StoreListMetaPill(
                    text = store.ratingText
                )

                StoreListMetaPill(
                    text = store.categoryName
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(BbRadius.pill))
                    .background(BbColors.Success)
                    .padding(
                        horizontal = BbSpacing.md,
                        vertical = BbSpacing.sm
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mağazayı incele",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
private fun StoreListEmptyState(
    onOpenStoreClick: () -> Unit
) {
    BbCard {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.Space16)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "∅",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Success
                )
            }

            StoreListStatusPill(
                text = "Mağaza bulunamadı"
            )

            Text(
                text = "Listelenecek mağaza bulunmuyor",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Daha sonra tekrar kontrol edebilir veya kendi mağazanızı açarak ürünlerinizi listeleyebilirsiniz.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextStrong.copy(alpha = 0.68f)
            )

            StoreListPrimaryButton(
                text = "Mağaza aç",
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
        modifier = Modifier.clickable {
            onOpenStoreClick()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Bulbulustur’da mağaza açın",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = "Ürünlerinizi listeleyin, mağazanızı yönetin ve müşterilere tek platformdan ulaşın.",
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextStrong.copy(alpha = 0.62f)
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(BbRadius.pill))
                    .background(BbColors.Success)
                    .padding(
                        horizontal = BbSpacing.md,
                        vertical = BbSpacing.sm
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mağaza aç",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
private fun StoreListStatusPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.sm,
                vertical = BbSpacing.xs
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = BbColors.Success
        )
    }
}

@Composable
private fun StoreListMiniBadge(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.sm,
                vertical = BbSpacing.xs
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = BbColors.Success
        )
    }
}

@Composable
private fun StoreListMetaPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.sm,
                vertical = BbSpacing.xs
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.TextStrong.copy(alpha = 0.68f)
        )
    }
}

@Composable
private fun StoreListPrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(BbColors.Success)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = BbSpacing.md,
                vertical = BbSpacing.sm
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
private fun StoreListSecondaryButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = BbSpacing.md,
                vertical = BbSpacing.sm
            ),
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
    StoreListScreen()
}