package com.bulbulustur.android.ViewComponents

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
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

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
        horizontal = BbSpacing.PageHorizontal,
        vertical = BbSpacing.PageTopCompact
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGapCompact)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            if (backButtonText != null && onBackClick != null) {
                BbButton(
                    text = backButtonText,
                    onClick = onBackClick,
                    variant = BbButtonVariant.Light,
                    size = BbButtonSize.Small
                )

                Spacer(modifier = Modifier.height(BbSpacing.Space1))
            }

            if (kicker != null) {
                AccountPageKicker(
                    text = kicker
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
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
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.Chip
            )
            .padding(
                horizontal = BbSpacing.ChipPaddingHorizontal,
                vertical = BbSpacing.ChipPaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}