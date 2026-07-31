package com.bulbulustur.android.Application.Views.Profile.Components

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun BbProfileSaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = BBLocalization.Current.Get(key = "c70b208b-86cd-4955-b8ef-f5b5ee7bc0cc", fallback = "Kaydet"),
    enabled: Boolean = true,
    isSaving: Boolean = false
) {
    BbButton(
        text = text,
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        variant = BbButtonVariant.Primary,
        size = BbButtonSize.Large,
        enabled = enabled,
        isLoading = isSaving
    )
}

@Composable
fun BbProfileStickySaveBar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = BBLocalization.Current.Get(key = "c70b208b-86cd-4955-b8ef-f5b5ee7bc0cc", fallback = "Kaydet"),
    enabled: Boolean = true,
    isSaving: Boolean = false
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .imePadding()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = BBSpacing.Space5
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.BorderThin)
                    .background(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = BBSpacing.PageHorizontal,
                        vertical = BBSpacing.Space3
                    )
            ) {
                BbProfileSaveButton(
                    text = text,
                    enabled = enabled,
                    isSaving = isSaving,
                    onClick = onClick
                )
            }
        }
    }
}
