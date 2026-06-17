package com.bulbulustur.android.Application.Views.Shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun AccountPageScaffold(
    title: String,
    modifier: Modifier = Modifier,
    kicker: String? = null,
    description: String? = null,
    backButtonText: String? = null,
    onBackClick: (() -> Unit)? = null,
    actionContent: (@Composable () -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = BBSpacing.PageHorizontal,
        vertical = BBSpacing.PageTopCompact
    ),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            AccountPageHeader(
                title = title,
                kicker = kicker,
                description = description,
                backButtonText = backButtonText,
                onBackClick = onBackClick,
                actionContent = actionContent
            )

            content()
        }
    }
}

@Composable
private fun AccountPageHeader(
    title: String,
    kicker: String?,
    description: String?,
    backButtonText: String?,
    onBackClick: (() -> Unit)?,
    actionContent: (@Composable () -> Unit)?
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            if (backButtonText != null && onBackClick != null) {
                BbButton(
                    text = backButtonText,
                    onClick = onBackClick,
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))
            }

            if (kicker != null) {
                AccountPageKicker(
                    text = kicker
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (description != null) {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (actionContent != null) {
                    actionContent()
                }
            }
        }
    }
}

@Composable
private fun AccountPageKicker(
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                color = BBColors.Yellow.Yellow100,
                shape = BBRadius.Chip
            )
            .padding(
                horizontal = BBSpacing.ChipPaddingHorizontal,
                vertical = BBSpacing.ChipPaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BBColors.Yellow.Yellow800
        )
    }
}
