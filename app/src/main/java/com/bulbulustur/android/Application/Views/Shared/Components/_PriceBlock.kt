package com.bulbulustur.android.Application.Views.Shared.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@Composable
fun BbPriceBlock(
    price: Double,
    modifier: Modifier = Modifier,
    oldPrice: Double? = null,
    discountPercent: Int? = null,
    currencySymbol: String = "TL"
) {
    Column(
        modifier = modifier
    ) {
        if (oldPrice != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = bbFormatPrice(oldPrice, currencySymbol),
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.LineThrough
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (discountPercent != null && discountPercent > 0) {
                    Spacer(modifier = Modifier.width(BBSpacing.IconTextGapSmall))

                    Surface(
                        shape = BBRadius.Badge,
                        color = BBColors.Success,
                        contentColor = BBColors.White
                    ) {
                        Text(
                            text = "%$discountPercent",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(
                                horizontal = BBSpacing.BadgePaddingHorizontal,
                                vertical = BBSpacing.BadgePaddingVertical
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(BBSpacing.IconTextGapSmall))
        }

        Text(
            text = bbFormatPrice(price, currencySymbol),
            style = MaterialTheme.typography.titleMedium,
            color = BBColors.Success
        )
    }
}

private fun bbFormatPrice(
    price: Double,
    currencySymbol: String
): String {
    val symbols = DecimalFormatSymbols(Locale("tr", "TR"))
    symbols.decimalSeparator = ','
    symbols.groupingSeparator = '.'

    val pattern = if (price % 1.0 == 0.0) {
        "#,##0"
    } else {
        "#,##0.00"
    }

    val formatter = DecimalFormat(pattern, symbols)
    return formatter.format(price) + currencySymbol
}

