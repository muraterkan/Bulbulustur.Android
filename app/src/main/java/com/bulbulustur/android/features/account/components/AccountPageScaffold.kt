package com.bulbulustur.android.features.account.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun AccountPageScaffold(
    title: String,
    subtitle: String? = null,
    showBackButton: Boolean = true,
    onBackClick: () -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(horizontal = BbSpacing.md),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(BbSpacing.md),
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.SurfaceMuted)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(contentPadding)
    ) {
        AccountPageTopBar(
            title = title,
            subtitle = subtitle,
            showBackButton = showBackButton,
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(BbSpacing.md))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = verticalArrangement
        ) {
            item {
                content()
            }

            item {
                Spacer(modifier = Modifier.height(BbSpacing.md))
            }
        }
    }
}

@Composable
private fun AccountPageTopBar(
    title: String,
    subtitle: String?,
    showBackButton: Boolean,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BbSpacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackButton) {
            AccountBackButton(
                onBackClick = onBackClick
            )
        }

        AccountPageTitleBlock(
            title = title,
            subtitle = subtitle,
            hasBackButton = showBackButton
        )
    }
}

@Composable
private fun AccountBackButton(
    onBackClick: () -> Unit
) {
    Surface(
        shape = CircleShape,
        color = BbColors.SurfaceMuted,
        modifier = Modifier.clickable {
            onBackClick()
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "Geri dön",
            tint = BbColors.TextStrong,
            modifier = Modifier.padding(BbSpacing.sm)
        )
    }
}

@Composable
private fun AccountPageTitleBlock(
    title: String,
    subtitle: String?,
    hasBackButton: Boolean
) {
    val titleModifier = if (hasBackButton) {
        Modifier.padding(start = BbSpacing.md)
    } else {
        Modifier
    }

    Column(
        modifier = titleModifier
    ) {
        Text(
            text = title,
            style = BbTypography.titleMedium,
            color = BbColors.TextStrong
        )

        if (!subtitle.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(BbSpacing.xs))

            Text(
                text = subtitle,
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )
        }
    }
}