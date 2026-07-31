package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Localization.BBLocalization

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileHeroCard

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileStickySaveBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCategorySearchSelectInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectOption
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLevelDTO

@Composable
fun ProfileLanguageFormScreen(
    languages: List<SystemDescLanguageDTO>,
    languageLevels: List<SystemDescLanguageLevelDTO>,
    selectedLanguageId: String,
    selectedLanguageLevelId: String,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onLanguageSelected: (String) -> Unit,
    onLanguageLevelSelected: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    val languageOptions = languages
        .filter {
            it.SystemDescLanguageId > 0 &&
                it.Content.isNotBlank()
        }
        .distinctBy {
            it.SystemDescLanguageId
        }
        .sortedBy {
            it.Content
        }
        .map {
            BbSelectOption(
                value = it.SystemDescLanguageId.toString(),
                text = it.Content.trim()
            )
        }

    val levelOptions = languageLevels
        .filter {
            it.SystemDescLanguageLevelId > 0 &&
                it.Content.isNotBlank()
        }
        .distinctBy {
            it.SystemDescLanguageLevelId
        }
        .map {
            BbSelectOption(
                value = it.SystemDescLanguageLevelId.toString(),
                text = it.Content.trim()
            )
        }

    val canSave =
        selectedLanguageId.toIntOrNull()?.let { it > 0 } == true &&
            selectedLanguageLevelId.toIntOrNull()?.let { it > 0 } == true &&
            !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "4bbb0259-3a57-4199-ae6b-725fdba47179", fallback = "Dil Ekle"),
                onBackClick = onBackClick
            )
        },
        
        bottomBar = {
            BbProfileStickySaveBar(
                text = "Dili Kaydet",
                enabled = canSave,
                isSaving = isLoading,
                onClick = onSaveClick
            )
        }
    
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottomWithCta
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Language,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "Konuştuğun Dili Ekle",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Dili ve o dildeki konuşma seviyeni seç.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item {
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                    ) {
                        BbCategorySearchSelectInput(
                            selectedValue = selectedLanguageId,
                            options = languageOptions,
                            onValueChange = onLanguageSelected,
                            label = BBLocalization.Current.Get(key = "5259eecf-5b93-46fb-bf7c-34acd890bf9a", fallback = "Dil"),
                            placeholder = "Konuştuğun dili seç",
                            searchPlaceholder = "Dil ara...",
                            helperText = "Listede arama yaparak bir dil seçebilirsin.",
                            enabled = !isLoading,
                            maximumVisibleOptionCount = 100
                        )

                        BbSelectInput(
                            selectedValue = selectedLanguageLevelId,
                            options = levelOptions,
                            onValueChange = onLanguageLevelSelected,
                            label = "Dil Seviyesi",
                            placeholder = "Seviye seç",
                            helperText = "Bu dili ne düzeyde konuştuğunu belirt.",
                            enabled = !isLoading
                        )
                    }
                }
            }

            if (!errorMessage.isNullOrBlank()) {
                item {
                    BbCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbCardVariant.Outlined,
                        padding = BbCardPadding.Medium
                    ) {
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            if (!isLoading && languages.isEmpty()) {
                item {
                    BbCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbCardVariant.Outlined,
                        padding = BbCardPadding.Medium
                    ) {
                        Text(
                            text = "Dil listesi sunucudan boş döndü.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            if (!isLoading && languageLevels.isEmpty()) {
                item {
                    BbCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbCardVariant.Outlined,
                        padding = BbCardPadding.Medium
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.School,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(bottom = BBSpacing.Space1)
                            )

                            Text(
                                text = "Dil seviyesi listesi sunucudan boş döndü.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}
