package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileStickySaveBar
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.MemberLanguageDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLevelDTO

@Composable
fun ProfileLanguageListScreen(
    languages: List<MemberLanguageDTO>,
    languageLevels: List<SystemDescLanguageLevelDTO>,
    isLoading: Boolean = false,
    deletingMemberLanguageId: Int? = null,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    onLanguageClick: (MemberLanguageDTO) -> Unit,
    onDeleteClick: (MemberLanguageDTO) -> Unit
) {
    var pendingDeleteMemberLanguageId by rememberSaveable { mutableStateOf<Int?>(null) }

    val pendingDeleteLanguage = languages.firstOrNull {
        it.MemberLanguageId == pendingDeleteMemberLanguageId
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "fb9900fe-d66a-4aee-a030-f41b58100722", fallback = "Dillerim"),
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            BbProfileStickySaveBar(
                text = BBLocalization.Current.Get(key = "4bbb0259-3a57-4199-ae6b-725fdba47179", fallback = "Dil Ekle"),
                enabled = !isLoading,
                onClick = onAddClick
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
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Translate,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(BBIcon.SizeLg)
                        )

                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                        ) {
                            Text(
                                text = "Konuştuğun Diller",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Her dil için konuşma seviyeni ayrı ayrı belirleyebilirsin.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            when {
                isLoading && languages.isEmpty() -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(BBSpacing.Space8),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                !errorMessage.isNullOrBlank() -> {
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

                languages.isEmpty() -> {
                    item {
                        BbCard(
                            modifier = Modifier.fillMaxWidth(),
                            variant = BbCardVariant.Outlined,
                            padding = BbCardPadding.Large
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Language,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(BBIcon.Size3Xl)
                                )

                                Text(
                                    text = "Henüz dil eklenmedi",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Profiline konuştuğun ilk dili ekleyerek başlayabilirsin.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                else -> {
                    items(
                        items = languages,
                        key = { it.MemberLanguageId }
                    ) { language ->
                        val levelContent = languageLevels
                            .firstOrNull {
                                it.SystemDescLanguageLevelId == language.LanguageLevelId
                            }
                            ?.Content
                            ?.trim()
                            ?.takeIf { it.isNotBlank() }
                            ?: BBLocalization.Current.Get(key = "e23c524e-fedd-4486-ac5e-25721a402156", fallback = "Seviye belirtilmemiş")

                        val isDeleting = deletingMemberLanguageId == language.MemberLanguageId

                        BbCard(
                            modifier = Modifier.fillMaxWidth(),
                            variant = BbCardVariant.Outlined,
                            padding = BbCardPadding.Medium
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable(enabled = !isLoading && !isDeleting) {
                                            onLanguageClick(language)
                                        },
                                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier.size(BBIcon.BoxLg),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Language,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(BBIcon.SizeLg)
                                        )
                                    }

                                    Column(
                                        modifier = Modifier.weight(1f),
                                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                                    ) {
                                        Text(
                                            text = language.Language.trim().ifBlank {
                                                "Dil bilgisi bulunamadı"
                                            },
                                            style = MaterialTheme.typography.titleSmall,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                            text = levelContent,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    Icon(
                                        imageVector = Icons.Outlined.ChevronRight,
                                        contentDescription = BBLocalization.Current.Get(key = "6a23f3ad-9109-471d-a670-7b5a40cf3cd9", fallback = "Düzenle"),
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(BBIcon.SizeMd)
                                    )
                                }

                                if (isDeleting) {
                                    Box(
                                        modifier = Modifier.size(BBIcon.BoxLg),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(BBIcon.SizeMd),
                                            strokeWidth = BBSpacing.Space1
                                        )
                                    }
                                } else {
                                    IconButton(
                                        enabled = !isLoading,
                                        onClick = {
                                            pendingDeleteMemberLanguageId = language.MemberLanguageId
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.DeleteOutline,
                                            contentDescription = "${language.Language} dilini sil",
                                            tint = MaterialTheme.colorScheme.error,
                                            modifier = Modifier.size(BBIcon.SizeMd)
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

    if (pendingDeleteLanguage != null) {
        AlertDialog(
            onDismissRequest = {
                if (!isLoading) pendingDeleteMemberLanguageId = null
            },
            title = {
                Text(
                    text = "Dil silinsin mi?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "${pendingDeleteLanguage.Language.trim().ifBlank { "Bu dil" }} profilinden silinecek. Bu işlemi onaylıyor musun?",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    enabled = !isLoading,
                    onClick = {
                        pendingDeleteMemberLanguageId = null
                        onDeleteClick(pendingDeleteLanguage)
                    }
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "e38050df-62e1-4b83-97ee-2643ad73390c", fallback = "Sil"),
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    enabled = !isLoading,
                    onClick = {
                        pendingDeleteMemberLanguageId = null
                    }
                ) {
                    Text(BBLocalization.Current.Get(key = "92ebe8f3-c0b3-48a9-88a5-bb431ba27bf8", fallback = "İptal"))
                }
            }
        )
    }
}