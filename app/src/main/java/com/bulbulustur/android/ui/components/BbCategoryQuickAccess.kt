package com.bulbulustur.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.*

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
        contentPadding = PaddingValues(horizontal = BbSpacing.md),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        items(items) { item ->

            Surface(
                modifier = Modifier
                    .width(92.dp)
                    .height(92.dp)
                    .clickable {
                        onItemClick(item)
                    },
                shape = RoundedCornerShape(BbRadius.lg),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(BbSpacing.sm),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Category,
                        contentDescription = item.title,
                        tint = BbColors.Primary
                    )

                    Spacer(modifier = Modifier.height(BbSpacing.xs))

                    Text(
                        text = item.title,
                        textAlign = TextAlign.Center,
                        style = BbTypography.labelSmall
                    )
                }
            }
        }
    }
}