package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileStickySaveBar as CommonProfileStickySaveBar

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

data class ProfileRelationshipFormData(
    val MaritalStatusId: Int?,
    val RelationshipTypeId: Int?,
    val ChildrenPreferenceId: Int?
)

data class ProfileRelationshipOption(
    val Id: Int,
    val Content: String
)

@Composable
fun ProfileRelationshipScreen(
    initialMaritalStatusId: Int? = null,
    initialRelationshipTypeId: Int? = null,
    initialChildrenPreferenceId: Int? = null,
    maritalStatuses: List<ProfileRelationshipOption> = defaultMaritalStatuses(),
    relationshipTypes: List<ProfileRelationshipOption> = defaultRelationshipTypes(),
    childrenPreferences: List<ProfileRelationshipOption> = defaultChildrenPreferences(),
    isSaving: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (ProfileRelationshipFormData) -> Unit = {}
) {
    var selectedMaritalStatusId by rememberSaveable {
        mutableStateOf(initialMaritalStatusId)
    }

    var selectedRelationshipTypeId by rememberSaveable {
        mutableStateOf(initialRelationshipTypeId)
    }

    var selectedChildrenPreferenceId by rememberSaveable {
        mutableStateOf(initialChildrenPreferenceId)
    }

    LaunchedEffect(
        initialMaritalStatusId,
        initialRelationshipTypeId,
        initialChildrenPreferenceId
    ) {
        selectedMaritalStatusId = initialMaritalStatusId
        selectedRelationshipTypeId = initialRelationshipTypeId
        selectedChildrenPreferenceId = initialChildrenPreferenceId
    }

    val hasChanged =
        selectedMaritalStatusId != initialMaritalStatusId ||
                selectedRelationshipTypeId != initialRelationshipTypeId ||
                selectedChildrenPreferenceId != initialChildrenPreferenceId

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "İlişki ve Aile",
                onBackClick = onBackClick
            )
        },
        
        bottomBar = {
            CommonProfileStickySaveBar(
    
                enabled = hasChanged && !isSaving,
                isSaving = isSaving,
                onClick = {
                    onSaveClick(
                        ProfileRelationshipFormData(
                            MaritalStatusId = selectedMaritalStatusId,
                            RelationshipTypeId = selectedRelationshipTypeId,
                            ChildrenPreferenceId = selectedChildrenPreferenceId
                        )
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.PageTopCompact,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.Space6
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            ProfileRelationshipIntroductionCard()

            ProfileRelationshipSelectionCard(
                title = "Medeni Durum",
                description = "Mevcut medeni durumunuzu seçin.",
                icon = Icons.Outlined.FavoriteBorder,
                options = maritalStatuses,
                selectedId = selectedMaritalStatusId,
                onSelected = {
                    selectedMaritalStatusId = it
                }
            )

            ProfileRelationshipSelectionCard(
                title = "İlişki Türü",
                description = "Profilinizde görünmesini istediğiniz ilişki türünü seçin.",
                icon = Icons.Outlined.VolunteerActivism,
                options = relationshipTypes,
                selectedId = selectedRelationshipTypeId,
                onSelected = {
                    selectedRelationshipTypeId = it
                }
            )

            ProfileRelationshipSelectionCard(
                title = "Çocuk Tercihi",
                description = "Çocuk sahibi olma konusundaki tercihinizi seçin.",
                icon = Icons.Outlined.ChildCare,
                options = childrenPreferences,
                selectedId = selectedChildrenPreferenceId,
                onSelected = {
                    selectedChildrenPreferenceId = it
                }
            )

            if (!errorMessage.isNullOrBlank()) {
                ProfileRelationshipErrorCard(
                    message = errorMessage
                )
            }
        }
    }
}

@Composable
private fun ProfileRelationshipIntroductionCard() {
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
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "İlişki ve Aile Bilgileri",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "İlişki durumunuzu ve aile tercihlerinizi profilinize ekleyin.",
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfileRelationshipSelectionCard(
    title: String,
    description: String,
    icon: ImageVector,
    options: List<ProfileRelationshipOption>,
    selectedId: Int?,
    onSelected: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(BBIcon.BoxMd)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BBRadius.LgShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BBIcon.Ui)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = title,
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = description,
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Column {
                options.forEach { option ->
                    ProfileRelationshipOptionRow(
                        title = option.Content,
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

@Composable
private fun ProfileRelationshipOptionRow(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            
            .profileClickable(onClick = onClick)
        
            .padding(
                horizontal = BBSpacing.CardPadding,
                vertical = BBSpacing.Space3
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = BbTypography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
        )
    }
}

@Composable
private fun ProfileRelationshipErrorCard(
    message: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = message,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun ProfileRelationshipSaveBar(
    enabled: Boolean,
    isSaving: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                horizontal = BBSpacing.PageHorizontal,
                vertical = BBSpacing.Space3
            )
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            shape = BBRadius.LgShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(
                horizontal = BBSpacing.Space6,
                vertical = BBSpacing.Space4
            )
        ) {
            if (isSaving) {
                CircularProgressIndicator(
                    modifier = Modifier.size(BBIcon.SizeMd),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Save,
                    contentDescription = null,
                    modifier = Modifier.size(BBIcon.SizeMd)
                )

                Text(
                    text = "Kaydet",
                    modifier = Modifier.padding(start = BBSpacing.Space2),
                    style = BbTypography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private fun defaultMaritalStatuses(): List<ProfileRelationshipOption> {
    return listOf(
        ProfileRelationshipOption(1, "Bekar"),
        ProfileRelationshipOption(2, "Evli"),
        ProfileRelationshipOption(3, "Boşanmış"),
        ProfileRelationshipOption(4, "Dul"),
        ProfileRelationshipOption(5, "Açık ilişki"),
        ProfileRelationshipOption(6, "Karışık")
    )
}

private fun defaultRelationshipTypes(): List<ProfileRelationshipOption> {
    return listOf(
        ProfileRelationshipOption(1, "Uzun süreli ilişki"),
        ProfileRelationshipOption(2, "Kısa süreli ilişki"),
        ProfileRelationshipOption(3, "Arkadaşlık"),
        ProfileRelationshipOption(4, "Açık ilişki"),
        ProfileRelationshipOption(5, "Evlilik odaklı"),
        ProfileRelationshipOption(6, "Henüz karar vermedim")
    )
}

private fun defaultChildrenPreferences(): List<ProfileRelationshipOption> {
    return listOf(
        ProfileRelationshipOption(1, "Çocuğum yok"),
        ProfileRelationshipOption(2, "Çocuğum var"),
        ProfileRelationshipOption(3, "Bir gün istiyorum"),
        ProfileRelationshipOption(4, "İstemiyorum"),
        ProfileRelationshipOption(5, "Kararsızım")
    )
}