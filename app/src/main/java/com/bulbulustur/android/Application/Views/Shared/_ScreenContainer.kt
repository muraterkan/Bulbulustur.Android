package com.bulbulustur.android.Application.Views.Shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

enum class BbScreenContainerWidth {
    Compact,
    Default,
    Wide,
    Full
}

@Composable
fun BbScreenContainer(
    modifier: Modifier = Modifier,
    width: BbScreenContainerWidth = BbScreenContainerWidth.Default,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = BBSpacing.PageHorizontal
    ),
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
) {
    val maxWidth = getBbScreenContainerMaxWidth(width)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (maxWidth == null) {
                        Modifier
                    } else {
                        Modifier.widthIn(max = maxWidth)
                    }
                ),
            contentAlignment = contentAlignment,
            content = content
        )
    }
}

private fun getBbScreenContainerMaxWidth(
    width: BbScreenContainerWidth
): Dp? {
    return when (width) {
        BbScreenContainerWidth.Compact -> BBLayout.ScreenContainerWidthCompact
        BbScreenContainerWidth.Default -> BBLayout.ScreenContainerWidthDefault
        BbScreenContainerWidth.Wide -> BBLayout.ScreenContainerWidthWide
        BbScreenContainerWidth.Full -> null
    }
}

