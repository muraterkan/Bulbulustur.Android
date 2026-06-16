package com.bulbulustur.android.wwwroot.commercecomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun BbProductGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = BbSpacing.PageHorizontal,
        vertical = BbSpacing.SectionContentGap
    ),
    horizontalSpacing: Dp = BbSpacing.ProductCardGap,
    verticalSpacing: Dp = BbSpacing.CardGap,
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columns),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        content = content
    )
}