package com.bulbulustur.android.Application.Views.Profile

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
import androidx.compose.material.icons.outlined.AccessibilityNew
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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

data class ProfileAppearanceFormData(
    val BodyHairId: Int?,
    val PubicHairId: Int?,
    val LovesArmpitHair: Boolean,
    val LovesBodyHair: Boolean
)

data class ProfileAppearanceOption(
    val Id: Int,
    val Content: String
)

@Composable
fun ProfileAppearanceScreen(
    initialBodyHairId: Int? = null,
    initialPubicHairId: Int? = null,
    initialLovesArmpitHair: Boolean = false,
    initialLovesBodyHair: Boolean = false,
    bodyHairOptions: List<ProfileAppearanceOption> = defaultBodyHairOptions(),
    pubicHairOptions: List<ProfileAppearanceOption> = defaultPubicHairOptions(),
    isSaving: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (ProfileAppearanceFormData) -> Unit = {}
) {
    var selectedBodyHairId by rememberSaveable {
        mutableStateOf(initialBodyHairId)
    }

    var selectedPubicHairId by rememberSaveable {
        mutableStateOf(initialPubicHairId)
    }

    var lovesArmpitHair by rememberSaveable {
        mutableStateOf(initialLovesArmpitHair)
    }

    var lovesBodyHair by rememberSaveable {
        mutableStateOf(initialLovesBodyHair)
    }

    LaunchedEffect(
        initialBodyHairId,
        initialPubicHairId,
        initialLovesArmpitHair,
        initialLovesBodyHair
    ) {
        selectedBodyHairId = initialBodyHairId
        selectedPubicHairId = initialPubicHairId
        lovesArmpitHair = initialLovesArmpitHair
        lovesBodyHair = initialLovesBodyHair
    }

    val hasChanged =
        selectedBodyHairId != initialBodyHairId ||
                selectedPubicHairId != initialPubicHairId ||
                lovesArmpitHair != initialLovesArmpitHair ||
                lovesBodyHair != initialLovesBodyHair

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Bakım ve Görünüm",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ProfileAppearanceSaveBar(
                enabled = hasChanged && !isSaving,
                isSaving = isSaving,
                onClick = {
                    onSaveClick(
                        ProfileAppearanceFormData(
                            BodyHairId = selectedBodyHairId,
                            PubicHairId = selectedPubicHairId,
                            LovesArmpitHair = lovesArmpitHair,
                            LovesBodyHair = lovesBodyHair
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
            ProfileAppearanceIntroductionCard()

            ProfileAppearanceSelectionCard(
                title = "Vücut Kılları",
                description = "Vücut kıllarınızı en iyi tanımlayan seçeneği belirleyin.",
                icon = Icons.Outlined.AccessibilityNew,
                options = bodyHairOptions,
                selectedId = selectedBodyHairId,
                onSelected = {
                    selectedBodyHairId = it
                }
            )

            ProfileAppearanceSelectionCard(
                title = "Mahrem Bölge Kılları",
                description = "Bakım biçiminizi en iyi tanımlayan seçeneği belirleyin.",
                icon = Icons.Outlined.ContentCut,
                options = pubicHairOptions,
                selectedId = selectedPubicHairId,
                onSelected = {
                    selectedPubicHairId = it
                }
            )

            ProfileAppearancePreferenceCard(
                lovesArmpitHair = lovesArmpitHair,
                lovesBodyHair = lovesBodyHair,
                onArmpitHairChange = {
                    lovesArmpitHair = it
                },
                onBodyHairChange = {
                    lovesBodyHair = it
                }
            )

            if (!errorMessage.isNullOrBlank()) {
                ProfileAppearanceErrorCard(
                    message = errorMessage
                )
            }
        }
    }
}

@Composable
private fun ProfileAppearanceIntroductionCard() {
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
                    imageVector = Icons.Outlined.Spa,
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
                    text = "Bakım ve Görünüm Tercihleri",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Kişisel bakım alışkanlıklarınızı ve görünüm tercihlerinizi profilinize ekleyin.",
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfileAppearanceSelectionCard(
    title: String,
    description: String,
    icon: ImageVector,
    options: List<ProfileAppearanceOption>,
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

            options.forEach { option ->
                ProfileAppearanceOptionRow(
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

@Composable
private fun ProfileAppearanceOptionRow(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
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
private fun ProfileAppearancePreferenceCard(
    lovesArmpitHair: Boolean,
    lovesBodyHair: Boolean,
    onArmpitHairChange: (Boolean) -> Unit,
    onBodyHairChange: (Boolean) -> Unit
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
            Text(
                text = "Görünüm Tercihleri",
                modifier = Modifier.padding(BBSpacing.CardPadding),
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            ProfileAppearanceSwitchRow(
                title = "Koltuk altı kıllarını seviyorum",
                description = if (lovesArmpitHair) {
                    "Evet"
                } else {
                    "Hayır"
                },
                icon = Icons.Outlined.FavoriteBorder,
                checked = lovesArmpitHair,
                onCheckedChange = onArmpitHairChange
            )

            ProfileAppearanceSwitchRow(
                title = "Vücut kıllarını seviyorum",
                description = if (lovesBodyHair) {
                    "Evet"
                } else {
                    "Hayır"
                },
                icon = Icons.Outlined.FavoriteBorder,
                checked = lovesBodyHair,
                onCheckedChange = onBodyHairChange
            )
        }
    }
}

@Composable
private fun ProfileAppearanceSwitchRow(
    title: String,
    description: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCheckedChange(!checked)
            }
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BBIcon.BoxMd)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = BBRadius.LgShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
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
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = description,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}

@Composable
private fun ProfileAppearanceErrorCard(
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
private fun ProfileAppearanceSaveBar(
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

private fun defaultBodyHairOptions(): List<ProfileAppearanceOption> {
    return listOf(
        ProfileAppearanceOption(
            Id = 1,
            Content = "Kılsız"
        ),
        ProfileAppearanceOption(
            Id = 2,
            Content = "Az kıllı"
        ),
        ProfileAppearanceOption(
            Id = 3,
            Content = "Orta"
        ),
        ProfileAppearanceOption(
            Id = 4,
            Content = "Kıllı"
        ),
        ProfileAppearanceOption(
            Id = 5,
            Content = "Çok kıllı"
        ),
        ProfileAppearanceOption(
            Id = 6,
            Content = "Belirtmek istemiyorum"
        )
    )
}

private fun defaultPubicHairOptions(): List<ProfileAppearanceOption> {
    return listOf(
        ProfileAppearanceOption(
            Id = 1,
            Content = "Tamamen tıraşlı"
        ),
        ProfileAppearanceOption(
            Id = 2,
            Content = "Kısa ve bakımlı"
        ),
        ProfileAppearanceOption(
            Id = 3,
            Content = "Doğal"
        ),
        ProfileAppearanceOption(
            Id = 4,
            Content = "Değişken"
        ),
        ProfileAppearanceOption(
            Id = 5,
            Content = "Belirtmek istemiyorum"
        )
    )
}