package com.bulbulustur.android.ui.commercecomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbSpacing
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

            Spacer(modifier = Modifier.width(BbSpacing.IconTextGapSmall))
        }

        if (showRatingText) {
            Text(
                text = bbFormatRating(rating),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        if (reviewCount != null) {
            Spacer(modifier = Modifier.width(BbSpacing.IconTextGapSmall))

            Text(
                text = "($reviewCount)",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
        BbRatingSize.Small -> BbIcon.SizeXs
        BbRatingSize.Medium -> BbIcon.SizeMd
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
                modifier = Modifier.size(starSize)
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