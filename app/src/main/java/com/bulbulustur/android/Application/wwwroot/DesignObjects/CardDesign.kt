package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
    variant: BbCardVariant = BbCardVariant.Outlined,
    padding: BbCardPadding = BbCardPadding.Medium,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
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
                shape = BBRadius.Card,
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
                shape = BBRadius.Card,
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = BBSpacing.ElevationSm
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
                shape = BBRadius.Card,
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = BBSpacing.BorderThin,
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
    Box(
        modifier = Modifier.padding(bbCardPaddingValues(padding))
    ) {
        content()
    }
}

private fun bbCardPaddingValues(
    padding: BbCardPadding
): PaddingValues {
    return when (padding) {
        BbCardPadding.None -> PaddingValues(BBSpacing.None)
        BbCardPadding.Small -> PaddingValues(BBSpacing.CardPaddingCompact)
        BbCardPadding.Medium -> PaddingValues(BBSpacing.CardPadding)
        BbCardPadding.Large -> PaddingValues(BBSpacing.CardPaddingLoose)
    }
}
