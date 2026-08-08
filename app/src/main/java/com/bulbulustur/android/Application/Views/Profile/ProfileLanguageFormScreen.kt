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
                text = BBLocalization.Current.Get(key = "0f22cd2d-a7c6-4a2b-bf8d-657e9dc0800b", fallback = "Dili Kaydet"),
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
                            text = BBLocalization.Current.Get(key = "eff02c15-d71a-4071-9120-fd02a711a869", fallback = "Konuştuğun Dili Ekle"),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = BBLocalization.Current.Get(key = "3afa32a0-fbbf-4c6f-b896-282ce832bb03", fallback = "Dili ve o dildeki konuşma seviyeni seç."),
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
                            placeholder = BBLocalization.Current.Get(key = "22c536ea-5c90-4b74-8f76-7830c149cff1", fallback = "Konuştuğun dili seç"),
                            searchPlaceholder = BBLocalization.Current.Get(key = "70b6f5f1-b451-46a5-a170-c6857eb72f7d", fallback = "Dil ara..."),
                            helperText = BBLocalization.Current.Get(key = "75447192-1caa-4d5f-b18f-213c195026e2", fallback = "Listede arama yaparak bir dil seçebilirsin."),
                            enabled = !isLoading,
                            maximumVisibleOptionCount = 100
                        )

                        BbSelectInput(
                            selectedValue = selectedLanguageLevelId,
                            options = levelOptions,
                            onValueChange = onLanguageLevelSelected,
                            label = BBLocalization.Current.Get(key = "f233c7c9-99a3-4ec6-9938-31aaf7317518", fallback = "Dil Seviyesi"),
                            placeholder = BBLocalization.Current.Get(key = "70a1ba8c-e8be-443c-8042-3e6a16e32abf", fallback = "Seviye seç"),
                            helperText = BBLocalization.Current.Get(key = "079482a4-146e-4ef8-ace9-d1b97f57c9f9", fallback = "Bu dili ne düzeyde konuştuğunu belirt."),
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
                            text = BBLocalization.Current.Get(key = "5b9009c0-156a-481c-b423-6d2aaf3ebf51", fallback = "Dil listesi sunucudan boş döndü."),
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
                                text = BBLocalization.Current.Get(key = "2e88e080-2056-4ed1-9a91-517a18180a21", fallback = "Dil seviyesi listesi sunucudan boş döndü."),
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
