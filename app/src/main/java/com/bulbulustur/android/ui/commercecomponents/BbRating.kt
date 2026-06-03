package com.bulbulustur.android.ui.commercecomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.floor

enum class BbRatingSize {
    Small,
    Medium
}

@Composable
fun BbRating(
    rating: Double,
    modifier: Modifier = Modifier,
    reviewCount: Int? = null,
    showStars: Boolean = true,
    showRatingText: Boolean = true,
    size: BbRatingSize = BbRatingSize.Small
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if (showStars) {
            BbRatingStars(
                rating = rating,
                size = size
            )

            Spacer(modifier = Modifier.width(BbSpacing.xs))
        }

        if (showRatingText) {
            Text(
                text = bbFormatRating(rating),
                style = BbTypography.labelSmall,
                color = BbColors.TextStrong
            )
        }

        if (reviewCount != null) {
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "($reviewCount)",
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )
        }
    }
}

@Composable
private fun BbRatingStars(
    rating: Double,
    size: BbRatingSize
) {
    val fullStars = floor(rating).toInt().coerceIn(0, 5)
    val hasHalfStar = rating - fullStars >= 0.5
    val starSize = when (size) {
        BbRatingSize.Small -> 16.dp
        BbRatingSize.Medium -> 20.dp
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (index in 1..5) {
            val icon = when {
                index <= fullStars -> Icons.Filled.Star
                index == fullStars + 1 && hasHalfStar -> Icons.Outlined.StarHalf
                else -> Icons.Outlined.StarBorder
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BbColors.Warning,
                modifier = Modifier
                    .width(starSize)
            )
        }
    }
}

private fun bbFormatRating(rating: Double): String {
    val symbols = DecimalFormatSymbols(Locale("tr", "TR"))
    symbols.decimalSeparator = ','
    symbols.groupingSeparator = '.'

    val formatter = DecimalFormat("0.0", symbols)
    return formatter.format(rating)
}