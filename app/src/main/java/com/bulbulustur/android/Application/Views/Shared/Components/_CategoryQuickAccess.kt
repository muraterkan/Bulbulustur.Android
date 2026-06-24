package com.bulbulustur.android.Application.Views.Shared.Components

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
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
        contentPadding = PaddingValues(horizontal = BBSpacing.PageHorizontal),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        items(items) { item ->
            Surface(
                modifier = Modifier
                    .width(BBLayout.FixedWidth92)
                    .height(BBLayout.FixedWidth92)
                    .clickable {
                        onItemClick(item)
                    },
                shape = BBRadius.Card,
                color = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shadowElevation = BBSpacing.ElevationXs
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(BBSpacing.CardPaddingCompact),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Category,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(BBIcon.Section)
                    )

                    Spacer(modifier = Modifier.height(BBSpacing.IconTextGapSmall))

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

