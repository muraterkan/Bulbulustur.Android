package com.bulbulustur.android.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing

enum class BbPageSurface {
    Default,
    White,
    Soft,
    Muted,
    Accent,
    Technical
}

enum class BbPageSpacing {
    Default,
    Compact,
    Loose,
    Flush
}

@Composable
fun BbPageScaffold(
    modifier: Modifier = Modifier,
    surface: BbPageSurface = BbPageSurface.Default,
    spacing: BbPageSpacing = BbPageSpacing.Default,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable (PaddingValues) -> Unit
) {
    val backgroundBrush = getBbPageBackgroundBrush(surface)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .padding(
                    bottom = getBbPageBottomPadding(spacing)
                )
        ) {
            content(
                PaddingValues(
                    top = getBbPageTopPadding(spacing),
                    bottom = getBbPageBottomPadding(spacing)
                )
            )
        }
    }
}

@Composable
private fun getBbPageBackgroundBrush(
    surface: BbPageSurface
): Brush {
    val colorScheme = MaterialTheme.colorScheme

    return when (surface) {
        BbPageSurface.Default -> Brush.verticalGradient(
            colors = listOf(
                colorScheme.background,
                colorScheme.background
            )
        )

        BbPageSurface.White -> Brush.verticalGradient(
            colors = listOf(
                BbColors.White,
                BbColors.White
            )
        )

        BbPageSurface.Soft -> Brush.verticalGradient(
            colors = listOf(
                colorScheme.surfaceVariant,
                colorScheme.background
            )
        )

        BbPageSurface.Muted -> Brush.verticalGradient(
            colors = listOf(
                colorScheme.surfaceVariant,
                colorScheme.surface
            )
        )

        BbPageSurface.Accent -> Brush.verticalGradient(
            colors = listOf(
                colorScheme.primaryContainer.copy(alpha = 0.45f),
                colorScheme.background
            )
        )

        BbPageSurface.Technical -> Brush.verticalGradient(
            colors = listOf(
                BbColors.Turquoise.Turquoise50.copy(alpha = 0.65f),
                colorScheme.background
            )
        )
    }
}

private fun getBbPageTopPadding(
    spacing: BbPageSpacing
) = when (spacing) {
    BbPageSpacing.Default -> BbSpacing.PageTop
    BbPageSpacing.Compact -> BbSpacing.PageTopCompact
    BbPageSpacing.Loose -> BbSpacing.PageTopSpaced
    BbPageSpacing.Flush -> 0.dp
}

private fun getBbPageBottomPadding(
    spacing: BbPageSpacing
) = when (spacing) {
    BbPageSpacing.Default -> BbSpacing.PageBottom
    BbPageSpacing.Compact -> BbSpacing.PageBottomCompact
    BbPageSpacing.Loose -> BbSpacing.PageBottomLoose
    BbPageSpacing.Flush -> 0.dp
}