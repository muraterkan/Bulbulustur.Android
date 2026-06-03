package com.bulbulustur.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

enum class BbCardVariant {
    Default,
    Elevated,
    Outlined
}

enum class BbCardPadding {
    None,
    Small,
    Medium,
    Large
}

@Composable
fun BbCard(
    modifier: Modifier = Modifier,
    variant: BbCardVariant = BbCardVariant.Default,
    padding: BbCardPadding = BbCardPadding.Medium,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val cardShape = RoundedCornerShape(BbRadius.lg)
    val cardModifier = if (onClick != null) {
        modifier.clickable(enabled = enabled) {
            onClick()
        }
    } else {
        modifier
    }

    when (variant) {
        BbCardVariant.Default -> {
            Card(
                modifier = cardModifier,
                shape = cardShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                BbCardContent(
                    padding = padding,
                    content = content
                )
            }
        }

        BbCardVariant.Elevated -> {
            ElevatedCard(
                modifier = cardModifier,
                shape = cardShape,
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                BbCardContent(
                    padding = padding,
                    content = content
                )
            }
        }

        BbCardVariant.Outlined -> {
            OutlinedCard(
                modifier = cardModifier,
                shape = cardShape,
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            ) {
                BbCardContent(
                    padding = padding,
                    content = content
                )
            }
        }
    }
}

@Composable
private fun BbCardContent(
    padding: BbCardPadding,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.padding(bbCardPaddingValues(padding))
    ) {
        content()
    }
}

private fun bbCardPaddingValues(
    padding: BbCardPadding
): PaddingValues {
    return when (padding) {
        BbCardPadding.None -> PaddingValues(0.dp)
        BbCardPadding.Small -> PaddingValues(BbSpacing.sm)
        BbCardPadding.Medium -> PaddingValues(BbSpacing.md)
        BbCardPadding.Large -> PaddingValues(BbSpacing.lg)
    }
}