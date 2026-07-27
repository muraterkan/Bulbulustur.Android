package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileHeroCard

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileStickySaveBar

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileSaveButton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun ProfileTextValueScreen(
    title: String,
    description: String,
    label: String,
    value: String,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        
        topBar = {
            BbInnerPageHeader(
                title = title,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            BbProfileStickySaveBar(
                enabled = value.isNotBlank() && !isLoading,
                isSaving = isLoading,
                onClick = onSaveClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
                .padding(
                    horizontal = BBSpacing.PageHorizontal,
                    vertical = BBSpacing.PageTopCompact
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            
        BbProfileHeroCard(
                title = " Bilgisi",
                description = description
            )
    

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                label = {
                    Text(text = label)
                },
                singleLine = true,
                enabled = !isLoading
            )

            if (!errorMessage.isNullOrBlank()) {
                Text(
                    text = errorMessage,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

        }
    }
}
