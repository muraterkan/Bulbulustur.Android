package com.bulbulustur.android.ui.commercecomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography
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
                    style = BbTypography.bodySmall.copy(
                        textDecoration = TextDecoration.LineThrough
                    ),
                    color = BbColors.TextMuted
                )

                if (discountPercent != null && discountPercent > 0) {
                    Spacer(modifier = Modifier.width(BbSpacing.xs))

                    Surface(
                        shape = RoundedCornerShape(BbRadius.sm),
                        color = BbColors.Success
                    ) {
                        Text(
                            text = "%$discountPercent",
                            style = BbTypography.labelSmall,
                            color = BbColors.White,
                            modifier = Modifier
                                .height(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(BbSpacing.xs))
        }

        Text(
            text = bbFormatPrice(price, currencySymbol),
            style = BbTypography.titleMedium,
            color = BbColors.Success
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