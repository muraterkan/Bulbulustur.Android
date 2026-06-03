package com.bulbulustur.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.ui.theme.*

@Composable
fun BbAdvertBanner(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            },
        shape = RoundedCornerShape(BbRadius.lg),
        color = BbColors.Primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(BbSpacing.lg),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Campaign,
                contentDescription = null,
                tint = BbColors.White,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(BbSpacing.md))

            Column {
                Text(
                    text = title,
                    style = BbTypography.titleLarge,
                    color = BbColors.White
                )

                Spacer(modifier = Modifier.height(BbSpacing.xs))

                Text(
                    text = description,
                    style = BbTypography.bodyMedium,
                    color = BbColors.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}