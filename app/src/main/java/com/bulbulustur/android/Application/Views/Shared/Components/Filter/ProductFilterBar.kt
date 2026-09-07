package com.bulbulustur.android.Application.Views.Shared.Components.Filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Immutable
data class ProductFilterChipModel(
    val Key: String,
    val Text: String,
    val SelectedCount: Int = 0,
    val IsSelected: Boolean = false,
    val ShowDropdownIcon: Boolean = true
)

@Composable
fun ProductFilterBar(
    filterText: String,
    selectedFilterCount: Int,
    chips: List<ProductFilterChipModel>,
    onFilterClick: () -> Unit,
    onChipClick: (ProductFilterChipModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = BBSpacing.PageHorizontal,
                end = BBSpacing.PageHorizontal
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProductMainFilterButton(
            text = filterText,
            selectedCount = selectedFilterCount,
            onClick = onFilterClick
        )

        Spacer(
            modifier = Modifier.width(BBSpacing.Space2)
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            chips.forEach { chip ->
                ProductQuickFilterChip(
                    model = chip,
                    onClick = {
                        onChipClick(chip)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductMainFilterButton(
    text: String,
    selectedCount: Int,
    onClick: () -> Unit
) {
    Box {
        Surface(
            onClick = onClick,
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(
                width = if (selectedCount > 0) 2.dp else 1.dp,
                color = if (selectedCount > 0) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.outlineVariant
                }
            ),
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = BBSpacing.Space3,
                    vertical = BBSpacing.Space2
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                Icon(
                    imageVector = Icons.Outlined.FilterAlt,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        if (selectedCount > 0) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(22.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.onSurface
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = selectedCount.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductQuickFilterChip(
    model: ProductFilterChipModel,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(
            width = if (model.IsSelected) 2.dp else 1.dp,
            color = if (model.IsSelected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
        ),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = if (model.SelectedCount > 0) {
                    "${model.Text} (${model.SelectedCount})"
                } else {
                    model.Text
                },
                style = MaterialTheme.typography.labelLarge,
                fontWeight = if (model.IsSelected) {
                    FontWeight.SemiBold
                } else {
                    FontWeight.Medium
                },
                color = MaterialTheme.colorScheme.onSurface
            )

            if (model.ShowDropdownIcon) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
