package com.bulbulustur.android.Views.Account

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
import com.bulbulustur.android.wwwroot.components.commerce.BbProductGrid
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing
import com.bulbulustur.android.wwwroot.theme.BbTheme

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
            "TÃ¼mÃ¼",
            "B2C GeÃ§miÅŸi",
            "AyakkabÄ±",
            "Giyim",
            "BugÃ¼n",
            "Bu Hafta"
        )
    }

    var selectedFilter by remember {
        mutableStateOf("TÃ¼mÃ¼")
    }

    val filteredItems = remember(
        selectedFilter,
        historyItems.toList()
    ) {
        if (selectedFilter == "TÃ¼mÃ¼") {
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
                title = "ÃœrÃ¼n GeÃ§miÅŸi",
                onBackClick = onBackClick,
                actionIcon = if (historyItems.isNotEmpty()) {
                    Icons.Outlined.DeleteSweep
                } else {
                    null
                },
                actionContentDescription = "GeÃ§miÅŸi Temizle",
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            Text(
                text = "Daha Ã–nce BaktÄ±ÄŸÄ±nÄ±z ÃœrÃ¼nler Burada",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "ÃœrÃ¼nleri tekrar inceleyebilir, favorilerinize ekleyebilir veya Ã¼rÃ¼n detayÄ±na geri dÃ¶nebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                BbButton(
                    text = "ÃœrÃ¼nleri Ä°ncele",
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
                text = if (selectedFilter == "TÃ¼mÃ¼") {
                    "Son GÃ¶rÃ¼ntÃ¼lenenler"
                } else {
                    selectedFilter
                },
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "$itemCount Ã¼rÃ¼n listeleniyor",
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
                text = "ÃœrÃ¼ne Git",
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
                text = "Ã—",
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
                        text = "â†º",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                ProductHistoryStatusPill(
                    text = "GeÃ§miÅŸ BulunamadÄ±"
                )

                Text(
                    text = "Listelenecek ÃœrÃ¼n Bulunmuyor",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "ÃœrÃ¼nleri inceledikÃ§e geÃ§miÅŸiniz burada gÃ¶rÃ¼nÃ¼r. AlÄ±ÅŸveriÅŸe baÅŸlayarak Ã¼rÃ¼nleri keÅŸfedebilirsiniz.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbButton(
                    text = "ÃœrÃ¼nleri Ä°ncele",
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
            productName = "Ortobella Confort KadÄ±n Hakiki Deri AyakkabÄ±",
            description = "Daha Ã¶nce gÃ¶rÃ¼ntÃ¼lediÄŸin Ã¼rÃ¼n. ÃœrÃ¼nÃ¼ tekrar inceleyebilirsin.",
            categoryName = "AyakkabÄ±",
            viewedDateText = "Daha Ã–nce BaktÄ±n",
            historyTypeText = "GeÃ§miÅŸ",
            imageText = "P1",
            filterTags = listOf("B2C GeÃ§miÅŸi", "AyakkabÄ±", "BugÃ¼n")
        ),
        ProductHistoryItem(
            id = 2,
            productId = 2,
            productName = "Ortobella Comfort Hakiki Deri Topuk Dikeni Terlik M13",
            description = "Daha Ã¶nce gÃ¶rÃ¼ntÃ¼lediÄŸin Ã¼rÃ¼n. ÃœrÃ¼nÃ¼ tekrar inceleyebilirsin.",
            categoryName = "AyakkabÄ±",
            viewedDateText = "DÃ¼n BaktÄ±n",
            historyTypeText = "GeÃ§miÅŸ",
            imageText = "P2",
            filterTags = listOf("B2C GeÃ§miÅŸi", "AyakkabÄ±", "Bu Hafta")
        ),
        ProductHistoryItem(
            id = 3,
            productId = 3,
            productName = "Oversize Pamuklu Basic TiÅŸÃ¶rt",
            description = "Daha Ã¶nce gÃ¶rÃ¼ntÃ¼lediÄŸin Ã¼rÃ¼n. ÃœrÃ¼nÃ¼ tekrar inceleyebilirsin.",
            categoryName = "Giyim",
            viewedDateText = "3 GÃ¼n Ã–nce BaktÄ±n",
            historyTypeText = "GeÃ§miÅŸ",
            imageText = "P3",
            filterTags = listOf("B2C GeÃ§miÅŸi", "Giyim", "Bu Hafta")
        ),
        ProductHistoryItem(
            id = 4,
            productId = 4,
            productName = "GÃ¼nlÃ¼k KullanÄ±m Omuz Ã‡antasÄ±",
            description = "Daha Ã¶nce gÃ¶rÃ¼ntÃ¼lediÄŸin Ã¼rÃ¼n. ÃœrÃ¼nÃ¼ tekrar inceleyebilirsin.",
            categoryName = "Ã‡anta",
            viewedDateText = "Bu Hafta BaktÄ±n",
            historyTypeText = "GeÃ§miÅŸ",
            imageText = "P4",
            filterTags = listOf("B2C GeÃ§miÅŸi", "Bu Hafta")
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
