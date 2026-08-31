package com.bulbulustur.android.Application.Views.Shared.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.R

private val BbMaterialSymbolsOutlined = FontFamily(
    Font(
        resId = R.font.material_symbols_outlined,
        weight = FontWeight.Normal
    )
)

private val BbMaterialSymbolNameRegex = Regex("^[a-z0-9_]+$")

@Composable
fun BbMaterialSymbol(
    iconClass: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    size: Dp = BBIcon.Ui
) {
    val symbolName = remember(iconClass) { iconClass?.trim()?.lowercase()?.takeIf { it.isNotBlank() && BbMaterialSymbolNameRegex.matches(it) } ?: "category" }
    val fontSize = with(LocalDensity.current) { size.toSp() }

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbolName,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Clip,
            style = TextStyle(
                fontFamily = BbMaterialSymbolsOutlined,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                lineHeight = fontSize,
                color = tint,
                fontFeatureSettings = "liga",
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.Both
                )
            )
        )
    }
}