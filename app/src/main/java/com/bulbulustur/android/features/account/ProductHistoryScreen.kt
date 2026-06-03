package com.bulbulustur.android.features.account

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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.commercecomponents.BbProductGrid
import com.bulbulustur.android.ui.components.BbBottomNavigation
import com.bulbulustur.android.ui.components.BbBottomNavigationItem
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTheme

@Composable
fun ProductHistoryScreen(
    onBackClick: () -> Unit = {},
    onProductClick: (ProductHistoryItem) -> Unit = {},
    onGoProductsClick: () -> Unit = {},
    onBottomNavigationClick: (BbBottomNavigationItem) -> Unit = {}
) {
    val historyItems = remember {
        mutableStateListOf<ProductHistoryItem>().apply {
            addAll(getProductHistoryItems())
        }
    }

    val filters = remember {
        listOf(
            "Tümü",
            "B2C geçmişi",
            "Ayakkabı",
            "Giyim",
            "Bugün",
            "Bu hafta"
        )
    }

    var selectedFilter by remember {
        mutableStateOf("Tümü")
    }

    val filteredItems = remember(
        selectedFilter,
        historyItems.toList()
    ) {
        if (selectedFilter == "Tümü") {
            historyItems.toList()
        } else {
            historyItems.filter { historyItem ->
                historyItem.filterTags.contains(selectedFilter)
            }
        }
    }

    Scaffold(
        bottomBar = {
            BbBottomNavigation(
                selectedItem = BbBottomNavigationItem.Account,
                onItemClick = {
                    onBottomNavigationClick(it)
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ProductHistoryHeaderArea(
                itemCount = historyItems.size,
                filters = filters,
                selectedFilter = selectedFilter,
                onBackClick = onBackClick,
                onGoProductsClick = onGoProductsClick,
                onClearHistoryClick = {
                    historyItems.clear()
                },
                onFilterClick = {
                    selectedFilter = it
                }
            )

            if (historyItems.isEmpty()) {
                ProductHistoryEmptyState(
                    onGoProductsClick = onGoProductsClick
                )
            } else {
                ProductHistoryResultHeader(
                    itemCount = filteredItems.size,
                    selectedFilter = selectedFilter
                )

                BbProductGrid(
                    contentPadding = PaddingValues(
                        start = BbSpacing.PageHorizontal,
                        top = BbSpacing.Space2,
                        end = BbSpacing.PageHorizontal,
                        bottom = BbSpacing.PageBottomWithCta
                    ),
                    horizontalSpacing = BbSpacing.CardGapCompact,
                    verticalSpacing = BbSpacing.CardGap
                ) {
                    items(
                        items = filteredItems,
                        key = { historyItem ->
                            historyItem.id
                        }
                    ) { historyItem ->
                        ProductHistoryCard(
                            historyItem = historyItem,
                            onProductClick = {
                                onProductClick(historyItem)
                            },
                            onRemoveClick = {
                                historyItems.removeAll { item ->
                                    item.id == historyItem.id
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductHistoryHeaderArea(
    itemCount: Int,
    filters: List<String>,
    selectedFilter: String,
    onBackClick: () -> Unit,
    onGoProductsClick: () -> Unit,
    onClearHistoryClick: () -> Unit,
    onFilterClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGapCompact)
    ) {
        ProductHistoryTopBar(
            onBackClick = onBackClick
        )

        ProductHistoryHeroCard(
            itemCount = itemCount,
            onGoProductsClick = onGoProductsClick,
            onClearHistoryClick = onClearHistoryClick
        )

        ProductHistoryFilterRow(
            filters = filters,
            selectedFilter = selectedFilter,
            onFilterClick = onFilterClick
        )

        BbSectionHeader(
            title = "Geçmiş ürünleriniz",
            subtitle = "Daha önce incelediğiniz ürünlere hızlıca geri dönün."
        )
    }
}

@Composable
private fun ProductHistoryTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbIcon.BoxMd)
                .clip(BbRadius.PillShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(BbSpacing.Space3))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Arama geçmişiniz",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "İncelediğiniz ürünlere kaldığınız yerden dönün.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductHistoryHeroCard(
    itemCount: Int,
    onGoProductsClick: () -> Unit,
    onClearHistoryClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            ProductHistoryStatusPill(
                text = "Geçmiş"
            )

            Text(
                text = "Daha önce baktığınız ürünler burada",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Ürünleri tekrar inceleyebilir, favorilerinize ekleyebilir veya ürün detayına geri dönebilirsiniz.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "Ürünleri incele",
                    onClick = onGoProductsClick,
                    modifier = Modifier.weight(1f),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )

                if (itemCount > 0) {
                    BbButton(
                        text = "Temizle",
                        onClick = onClearHistoryClick,
                        modifier = Modifier.weight(1f),
                        variant = BbButtonVariant.Outline,
                        size = BbButtonSize.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductHistoryFilterRow(
    filters: List<String>,
    selectedFilter: String,
    onFilterClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.ChipGap)
    ) {
        filters.forEach { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = {
                    onFilterClick(filter)
                },
                label = {
                    Text(
                        text = filter,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            )
        }
    }
}

@Composable
private fun ProductHistoryResultHeader(
    itemCount: Int,
    selectedFilter: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BbSpacing.PageHorizontal,
                end = BbSpacing.PageHorizontal,
                top = BbSpacing.Space3,
                bottom = BbSpacing.Space1
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = if (selectedFilter == "Tümü") {
                    "Son görüntülenenler"
                } else {
                    selectedFilter
                },
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "$itemCount ürün listeleniyor",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductHistoryCard(
    historyItem: ProductHistoryItem,
    onProductClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            ProductHistoryImageBox(
                historyItem = historyItem,
                onProductClick = onProductClick,
                onRemoveClick = onRemoveClick
            )

            Text(
                text = historyItem.viewedDateText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = historyItem.productName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2
            )

            Text(
                text = historyItem.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )

            ProductHistoryMiniBadge(
                text = historyItem.categoryName
            )

            BbButton(
                text = "Ürüne git",
                onClick = onProductClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Small
            )
        }
    }
}

@Composable
private fun ProductHistoryImageBox(
    historyItem: ProductHistoryItem,
    onProductClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BbSpacing.Space24)
            .clip(BbRadius.LgShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onProductClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = historyItem.imageText,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        ProductHistoryMiniBadge(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(BbSpacing.Space1),
            text = historyItem.historyTypeText
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(BbSpacing.Space1)
                .size(BbIcon.BoxXs)
                .clip(BbRadius.PillShape)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    onRemoveClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "×",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductHistoryEmptyState(
    onGoProductsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(BbSpacing.PageHorizontal),
        contentAlignment = Alignment.Center
    ) {
        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Large
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
            ) {
                Box(
                    modifier = Modifier
                        .size(BbIcon.Box2Xl)
                        .clip(BbRadius.PillShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "↺",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                ProductHistoryStatusPill(
                    text = "Geçmiş bulunamadı"
                )

                Text(
                    text = "Listelenecek ürün bulunmuyor",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Ürünleri inceledikçe geçmişiniz burada görünür. Alışverişe başlayarak ürünleri keşfedebilirsiniz.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbButton(
                    text = "Ürünleri incele",
                    onClick = onGoProductsClick,
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun ProductHistoryMiniBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(BbRadius.PillShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProductHistoryStatusPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(BbRadius.PillShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

data class ProductHistoryItem(
    val id: Int,
    val productId: Int,
    val productName: String,
    val description: String,
    val categoryName: String,
    val viewedDateText: String,
    val historyTypeText: String,
    val imageText: String,
    val filterTags: List<String>
)

private fun getProductHistoryItems(): List<ProductHistoryItem> {
    return listOf(
        ProductHistoryItem(
            id = 1,
            productId = 1,
            productName = "Ortobella Confort Kadın Hakiki Deri Ayakkabı",
            description = "Daha önce görüntülediğin ürün. Ürünü tekrar inceleyebilirsin.",
            categoryName = "Ayakkabı",
            viewedDateText = "Daha önce baktın",
            historyTypeText = "Geçmiş",
            imageText = "P1",
            filterTags = listOf("B2C geçmişi", "Ayakkabı", "Bugün")
        ),
        ProductHistoryItem(
            id = 2,
            productId = 2,
            productName = "Ortobella Comfort Hakiki Deri Topuk Dikeni Terlik M13",
            description = "Daha önce görüntülediğin ürün. Ürünü tekrar inceleyebilirsin.",
            categoryName = "Ayakkabı",
            viewedDateText = "Dün baktın",
            historyTypeText = "Geçmiş",
            imageText = "P2",
            filterTags = listOf("B2C geçmişi", "Ayakkabı", "Bu hafta")
        ),
        ProductHistoryItem(
            id = 3,
            productId = 3,
            productName = "Oversize pamuklu basic tişört",
            description = "Daha önce görüntülediğin ürün. Ürünü tekrar inceleyebilirsin.",
            categoryName = "Giyim",
            viewedDateText = "3 gün önce baktın",
            historyTypeText = "Geçmiş",
            imageText = "P3",
            filterTags = listOf("B2C geçmişi", "Giyim", "Bu hafta")
        ),
        ProductHistoryItem(
            id = 4,
            productId = 4,
            productName = "Günlük kullanım omuz çantası",
            description = "Daha önce görüntülediğin ürün. Ürünü tekrar inceleyebilirsin.",
            categoryName = "Çanta",
            viewedDateText = "Bu hafta baktın",
            historyTypeText = "Geçmiş",
            imageText = "P4",
            filterTags = listOf("B2C geçmişi", "Bu hafta")
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductHistoryScreenPreview() {
    BbTheme {
        ProductHistoryScreen()
    }
}