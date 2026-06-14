package com.bulbulustur.android.Ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbLayout
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing

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
            .height(BbLayout.CampaignHeroHeight)
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            },
        shape = BbRadius.Card,
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(BbSpacing.CardPaddingLoose),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Campaign,
                contentDescription = null,
                modifier = Modifier.size(BbIcon.Size4Xl)
            )

            Spacer(modifier = Modifier.width(BbSpacing.IconTextGapLarge))

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(BbSpacing.IconTextGapSmall))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}