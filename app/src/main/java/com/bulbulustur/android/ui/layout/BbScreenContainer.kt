package com.bulbulustur.android.ui.layout

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
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbSpacing

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
        horizontal = BbSpacing.PageHorizontal
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
        BbScreenContainerWidth.Compact -> 520.dp
        BbScreenContainerWidth.Default -> 720.dp
        BbScreenContainerWidth.Wide -> 960.dp
        BbScreenContainerWidth.Full -> null
    }
}