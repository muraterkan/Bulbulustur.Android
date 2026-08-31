package com.bulbulustur.android.Application.Views.Shared.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO

@Composable
fun RetailCategorySheet(
    categories: List<ProductCategoryDTO>,
    isLoading: Boolean,
    errorMessage: String?,
    onDismissRequest: () -> Unit,
    onCategoryClick: (Int) -> Unit
) {
    BuyerCategorySheet(
        title = "Kategoriler",
        categories = categories,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onDismissRequest = onDismissRequest,
        onCategoryClick = onCategoryClick
    )
}

@Composable
fun WholesaleCategorySheet(
    categories: List<ProductCategoryDTO>,
    isLoading: Boolean,
    errorMessage: String?,
    onDismissRequest: () -> Unit,
    onCategoryClick: (Int) -> Unit
) {
    BuyerCategorySheet(
        title = "Toptan Kategoriler",
        categories = categories,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onDismissRequest = onDismissRequest,
        onCategoryClick = onCategoryClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BuyerCategorySheet(
    title: String,
    categories: List<ProductCategoryDTO>,
    isLoading: Boolean,
    errorMessage: String?,
    onDismissRequest: () -> Unit,
    onCategoryClick: (Int) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val listState = rememberLazyListState()

    val level1Categories = remember(categories) {
        categories
            .asSequence()
            .filter {
                it.ProductCategoryId > 0 &&
                        it.CategoryLevel == 1 &&
                        it.CategoryName.isNotBlank()
            }
            .distinctBy { it.ProductCategoryId }
            .sortedBy { it.CategoryName }
            .toList()
    }

    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        shape = BBRadius.BottomSheet
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = BBSpacing.PageHorizontal,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.Space4
                    ),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Bir kategori seçin",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            HorizontalDivider()

            when {
                isLoading && level1Categories.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                !errorMessage.isNullOrBlank() && level1Categories.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(BBSpacing.PageHorizontal),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Category,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = errorMessage,
                            modifier = Modifier.padding(top = BBSpacing.Space2),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                level1Categories.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(BBSpacing.PageHorizontal),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Category,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "Kategori bulunamadı.",
                            modifier = Modifier.padding(top = BBSpacing.Space2),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            state = listState,
                            contentPadding = PaddingValues(
                                start = BBSpacing.PageHorizontal,
                                top = BBSpacing.Space3,
                                end = BBSpacing.PageHorizontal,
                                bottom = BBSpacing.PageBottom
                            ),
                            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                        ) {
                            items(
                                items = level1Categories,
                                key = { it.ProductCategoryId }
                            ) { category ->
                                BuyerCategorySheetRow(
                                    category = category,
                                    onClick = {
                                        onCategoryClick(category.ProductCategoryId)
                                    }
                                )
                            }
                        }

                        BuyerCategoryScrollbar(
                            state = listState,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .fillMaxHeight()
                                .padding(
                                    top = BBSpacing.Space3,
                                    bottom = BBSpacing.PageBottom,
                                    end = 4.dp
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BuyerCategorySheetRow(
    category: ProductCategoryDTO,
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
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                BbMaterialSymbol(
                    iconClass = category.IconClass,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    size = BBIcon.Section
                )
            }

            Text(
                text = category.CategoryName,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BuyerCategoryScrollbar(
    state: LazyListState,
    modifier: Modifier = Modifier
) {
    val layoutInfo by rememberUpdatedState(state.layoutInfo)
    val totalItemsCount = layoutInfo.totalItemsCount
    val visibleItems = layoutInfo.visibleItemsInfo

    if (totalItemsCount <= 0 || visibleItems.isEmpty()) {
        return
    }

    val visibleItemsCount = visibleItems.size

    if (visibleItemsCount >= totalItemsCount) {
        return
    }

    BoxWithConstraints(
        modifier = modifier.width(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        val availableHeight = maxHeight
        val minimumThumbHeight = 36.dp

        val visibleFraction = (
                visibleItemsCount.toFloat() /
                        totalItemsCount.toFloat()
                ).coerceIn(0f, 1f)

        val calculatedThumbHeight = availableHeight * visibleFraction

        val thumbHeight = calculatedThumbHeight.coerceAtLeast(
            minimumThumbHeight
        )

        val maximumOffset = (
                availableHeight - thumbHeight
                ).coerceAtLeast(0.dp)

        val maximumFirstVisibleIndex = (
                totalItemsCount - visibleItemsCount
                ).coerceAtLeast(1)

        val scrollFraction = (
                state.firstVisibleItemIndex.toFloat() /
                        maximumFirstVisibleIndex.toFloat()
                ).coerceIn(0f, 1f)

        val thumbOffset = maximumOffset * scrollFraction

        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BBRadius.LgShape
                )
        )

        Box(
            modifier = Modifier
                .offset(y = thumbOffset)
                .width(4.dp)
                .height(thumbHeight)
                .background(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f),
                    shape = BBRadius.LgShape
                )
        )
    }
}