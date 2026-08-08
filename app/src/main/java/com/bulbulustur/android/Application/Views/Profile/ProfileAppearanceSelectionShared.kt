package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Localization.BBLocalization

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileHeroCard

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileStickySaveBar

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileSaveButton

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

data class ProfileAppearanceSelectionOption(
    val Id: Int,
    val Content: String
)

@Composable
fun ProfileAppearanceSelectionScreen(
    title: String,
    description: String,
    options: List<ProfileAppearanceSelectionOption>,
    selectedId: Int?,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onSelected: (Int) -> Unit,
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
                enabled = selectedId != null && !isLoading,
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
                title = " Bilginizi Seçin",
                description = description
            )
    

            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                !errorMessage.isNullOrBlank() -> {
                    BbCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbCardVariant.Outlined,
                        padding = BbCardPadding.Medium
                    ) {
                        Text(
                            text = errorMessage,
                            style = BbTypography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                options.isEmpty() -> {
                    BbCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbCardVariant.Outlined,
                        padding = BbCardPadding.Medium
                    ) {
                        Text(
                            text = BBLocalization.Current.Get(key = "f4d40646-a0a2-411f-915e-663d388772fb", fallback = "Seçenek bulunamadı."),
                            style = BbTypography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(
                            bottom = BBSpacing.Space3
                        ),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        items(
                            items = options,
                            key = { it.Id }
                        ) { option ->
                            BbCard(
                                modifier = Modifier
                                    
        .fillMaxWidth()
                                    .profileClickable {
                                        onSelected(option.Id)
                                    }
    ,
                                variant = BbCardVariant.Outlined,
                                padding = BbCardPadding.Medium
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = option.Content,
                                        style = BbTypography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = if (selectedId == option.Id) {
                                            FontWeight.SemiBold
                                        } else {
                                            FontWeight.Normal
                                        }
                                    )

                                    RadioButton(
                                        selected = selectedId == option.Id,
                                        onClick = {
                                            onSelected(option.Id)
                                        }
                                    )
                                }
                            }
                        }
                    }

                    
    
                }
            }
        }
    }
}

@Composable
fun ProfileBooleanPreferenceScreen(
    title: String,
    description: String,
    selectedValue: Boolean?,
    onBackClick: () -> Unit,
    onSelected: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {
    val options = listOf(
        ProfileAppearanceSelectionOption(
            Id = 1,
            Content = "Evet"
        ),
        ProfileAppearanceSelectionOption(
            Id = 0,
            Content = BBLocalization.Current.Get(key = "b4dbac50-7754-4131-82da-aefda288ca90", fallback = "Hayır")
        )
    )

    ProfileAppearanceSelectionScreen(
        title = title,
        description = description,
        options = options,
        selectedId = when (selectedValue) {
            true -> 1
            false -> 0
            null -> null
        },
        onBackClick = onBackClick,
        onSelected = {
            onSelected(it == 1)
        },
        onSaveClick = onSaveClick
    )
}
