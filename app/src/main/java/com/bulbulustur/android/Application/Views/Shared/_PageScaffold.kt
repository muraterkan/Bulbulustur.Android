package com.bulbulustur.android.Application.Views.Shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLayoutDirection
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = BBColors.Transparent,
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(layoutDirection),
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
                colorScheme.surface,
                colorScheme.surface
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
                colorScheme.primaryContainer.copy(alpha = BBAlpha.DisabledContainer),
                colorScheme.background
            )
        )

        BbPageSurface.Technical -> Brush.verticalGradient(
            colors = listOf(
                BBColors.Turquoise.Turquoise50.copy(alpha = BBAlpha.DisabledContent),
                colorScheme.background
            )
        )
    }
}

private fun getBbPageTopPadding(
    spacing: BbPageSpacing
) = when (spacing) {
    BbPageSpacing.Default -> BBSpacing.PageTop
    BbPageSpacing.Compact -> BBSpacing.PageTopCompact
    BbPageSpacing.Loose -> BBSpacing.PageTopSpaced
    BbPageSpacing.Flush -> BBSpacing.None
}

private fun getBbPageBottomPadding(
    spacing: BbPageSpacing
) = when (spacing) {
    BbPageSpacing.Default -> BBSpacing.PageBottom
    BbPageSpacing.Compact -> BBSpacing.PageBottomCompact
    BbPageSpacing.Loose -> BBSpacing.PageBottomLoose
    BbPageSpacing.Flush -> BBSpacing.None
}

