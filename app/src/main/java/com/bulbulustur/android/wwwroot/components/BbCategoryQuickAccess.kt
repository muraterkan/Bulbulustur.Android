package com.bulbulustur.android.wwwroot.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbLayout
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

data class BbCategoryQuickAccessItem(
    val id: Int,
    val title: String
)

@Composable
fun BbCategoryQuickAccess(
    items: List<BbCategoryQuickAccessItem>,
    onItemClick: (BbCategoryQuickAccessItem) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = BbSpacing.PageHorizontal),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGapCompact)
    ) {
        items(items) { item ->
            Surface(
                modifier = Modifier
                    .width(BbLayout.FixedWidth92)
                    .height(BbLayout.FixedWidth92)
                    .clickable {
                        onItemClick(item)
                    },
                shape = BbRadius.Card,
                color = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shadowElevation = BbSpacing.ElevationXs
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(BbSpacing.CardPaddingCompact),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Category,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(BbIcon.Section)
                    )

                    Spacer(modifier = Modifier.height(BbSpacing.IconTextGapSmall))

                    Text(
                        text = item.title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}