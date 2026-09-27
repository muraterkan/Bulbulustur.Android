package com.bulbulustur.android.Application.Views.Shared.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun BbPageSection(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = BBSpacing.PageHorizontal),
        content = content
    )
}

fun LazyListScope.bbPageItem(
    key: Any? = null,
    contentType: Any? = null,
    content: @Composable LazyItemScope.() -> Unit
) {
    item(
        key = key,
        contentType = contentType
    ) {
        val lazyItemScope = this

        BbPageSection {
            content(lazyItemScope)
        }
    }
}

inline fun <T> LazyListScope.bbPageItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) {
    items(
        items = items,
        key = key,
        contentType = contentType
    ) { item ->
        val lazyItemScope = this

        BbPageSection {
            itemContent(lazyItemScope, item)
        }
    }
}
