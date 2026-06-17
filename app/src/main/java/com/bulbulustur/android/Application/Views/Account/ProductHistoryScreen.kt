package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteSweep
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
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductGrid
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun ProductHistoryScreen(
    onBackClick: () -> Unit = {},
    onProductClick: (ProductHistoryItem) -> Unit = {},
    onGoProductsClick: () -> Unit = {}
) {
    val historyItems = remember {
        mutableStateListOf<ProductHistoryItem>().apply {
            addAll(getProductHistoryItems())
        }
    }

    val filters = remember {
        listOf(
            "Tümü",
            "B2C Geçmişi",
            "Ayakkabı",
            "Giyim",
            "Bugün",
            "Bu Hafta"
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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Ürün Geçmişi",
                onBackClick = onBackClick,
                actionIcon = if (historyItems.isNotEmpty()) {
                    Icons.Outlined.DeleteSweep
                } else {
                    null
                },
                actionContentDescription = "Geçmişi Temizle",
                onActionClick = {
                    historyItems.clear()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            ProductHistoryHeaderArea(
                itemCount = historyItems.size,
                filters = filters,
                selectedFilter = selectedFilter,
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
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.Space2,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottomWithCta
                    ),
                    horizontalSpacing = BBSpacing.CardGapCompact,
                    verticalSpacing = BBSpacing.CardGap
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
    onGoProductsClick: () -> Unit,
    onClearHistoryClick: () -> Unit,
    onFilterClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal
            ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
    ) {
        ProductHistoryIntroCard(
            itemCount = itemCount,
            onGoProductsClick = onGoProductsClick,
            onClearHistoryClick = onClearHistoryClick
        )

        ProductHistoryFilterRow(
            filters = filters,
            selectedFilter = selectedFilter,
            onFilterClick = onFilterClick
        )
    }
}

@Composable
private fun ProductHistoryIntroCard(
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = "Daha Önce Baktığınız Ürünler Burada",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Ürünleri tekrar inceleyebilir, favorilerinize ekleyebilir veya ürün detayına geri dönebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                BbButton(
                    text = "Ürünleri İncele",
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
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
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
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal,
                top = BBSpacing.Space3,
                bottom = BBSpacing.Space1
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = if (selectedFilter == "Tümü") {
                    "Son Görüntülenenler"
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
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
                text = "Ürüne Git",
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
            .height(BBSpacing.Space24)
            .clip(BBRadius.LgShape)
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
                .padding(BBSpacing.Space1),
            text = historyItem.historyTypeText
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(BBSpacing.Space1)
                .size(BBIcon.BoxXs)
                .clip(BBRadius.PillShape)
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
            .padding(BBSpacing.PageHorizontal),
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
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.Box2Xl)
                        .clip(BBRadius.PillShape)
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
                    text = "Geçmiş Bulunamadı"
                )

                Text(
                    text = "Listelenecek Ürün Bulunmuyor",
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
                    text = "Ürünleri İncele",
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
            .clip(BBRadius.PillShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
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
            .clip(BBRadius.PillShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
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
            viewedDateText = "Daha Önce Baktın",
            historyTypeText = "Geçmiş",
            imageText = "P1",
            filterTags = listOf("B2C Geçmişi", "Ayakkabı", "Bugün")
        ),
        ProductHistoryItem(
            id = 2,
            productId = 2,
            productName = "Ortobella Comfort Hakiki Deri Topuk Dikeni Terlik M13",
            description = "Daha önce görüntülediğin ürün. Ürünü tekrar inceleyebilirsin.",
            categoryName = "Ayakkabı",
            viewedDateText = "Dün Baktın",
            historyTypeText = "Geçmiş",
            imageText = "P2",
            filterTags = listOf("B2C Geçmişi", "Ayakkabı", "Bu Hafta")
        ),
        ProductHistoryItem(
            id = 3,
            productId = 3,
            productName = "Oversize Pamuklu Basic Tişört",
            description = "Daha önce görüntülediğin ürün. Ürünü tekrar inceleyebilirsin.",
            categoryName = "Giyim",
            viewedDateText = "3 Gün Önce Baktın",
            historyTypeText = "Geçmiş",
            imageText = "P3",
            filterTags = listOf("B2C Geçmişi", "Giyim", "Bu Hafta")
        ),
        ProductHistoryItem(
            id = 4,
            productId = 4,
            productName = "Günlük Kullanım Omuz Çantası",
            description = "Daha önce görüntülediğin ürün. Ürünü tekrar inceleyebilirsin.",
            categoryName = "Çanta",
            viewedDateText = "Bu Hafta Baktın",
            historyTypeText = "Geçmiş",
            imageText = "P4",
            filterTags = listOf("B2C Geçmişi", "Bu Hafta")
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

