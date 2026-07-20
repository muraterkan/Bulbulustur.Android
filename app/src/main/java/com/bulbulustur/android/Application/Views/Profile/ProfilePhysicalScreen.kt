package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessibilityNew
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

data class ProfilePhysicalFormData(
    val Height: Int?,
    val Weight: Int?,
    val BodyTypeId: Int?,
    val SkinToneId: Int?,
    val HasPiercing: Boolean,
    val HasTattoo: Boolean
)

data class ProfilePhysicalOption(
    val Id: Int,
    val Content: String
)

@Composable
fun ProfilePhysicalScreen(
    initialHeight: Int? = null,
    initialWeight: Int? = null,
    initialBodyTypeId: Int? = null,
    initialSkinToneId: Int? = null,
    initialHasPiercing: Boolean = false,
    initialHasTattoo: Boolean = false,
    bodyTypes: List<ProfilePhysicalOption> = defaultBodyTypes(),
    skinTones: List<ProfilePhysicalOption> = defaultSkinTones(),
    isSaving: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (ProfilePhysicalFormData) -> Unit = {}
) {
    var heightText by rememberSaveable {
        mutableStateOf(initialHeight?.toString().orEmpty())
    }

    var weightText by rememberSaveable {
        mutableStateOf(initialWeight?.toString().orEmpty())
    }

    var selectedBodyTypeId by rememberSaveable {
        mutableStateOf(initialBodyTypeId)
    }

    var selectedSkinToneId by rememberSaveable {
        mutableStateOf(initialSkinToneId)
    }

    var hasPiercing by rememberSaveable {
        mutableStateOf(initialHasPiercing)
    }

    var hasTattoo by rememberSaveable {
        mutableStateOf(initialHasTattoo)
    }

    LaunchedEffect(
        initialHeight,
        initialWeight,
        initialBodyTypeId,
        initialSkinToneId,
        initialHasPiercing,
        initialHasTattoo
    ) {
        heightText = initialHeight?.toString().orEmpty()
        weightText = initialWeight?.toString().orEmpty()
        selectedBodyTypeId = initialBodyTypeId
        selectedSkinToneId = initialSkinToneId
        hasPiercing = initialHasPiercing
        hasTattoo = initialHasTattoo
    }

    val height = heightText.toIntOrNull()
    val weight = weightText.toIntOrNull()

    val heightValid = height == null || height in 100..250
    val weightValid = weight == null || weight in 30..300

    val hasChanged =
        height != initialHeight ||
                weight != initialWeight ||
                selectedBodyTypeId != initialBodyTypeId ||
                selectedSkinToneId != initialSkinToneId ||
                hasPiercing != initialHasPiercing ||
                hasTattoo != initialHasTattoo

    val canSave =
        hasChanged &&
                heightValid &&
                weightValid &&
                !isSaving

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Fiziksel Özellikler",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ProfilePhysicalSaveBar(
                enabled = canSave,
                isSaving = isSaving,
                onClick = {
                    onSaveClick(
                        ProfilePhysicalFormData(
                            Height = height,
                            Weight = weight,
                            BodyTypeId = selectedBodyTypeId,
                            SkinToneId = selectedSkinToneId,
                            HasPiercing = hasPiercing,
                            HasTattoo = hasTattoo
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
            ProfilePhysicalIntroductionCard()

            ProfileMeasurementCard(
                heightText = heightText,
                weightText = weightText,
                heightValid = heightValid,
                weightValid = weightValid,
                onHeightChange = { value ->
                    heightText = value
                        .filter { it.isDigit() }
                        .take(3)
                },
                onWeightChange = { value ->
                    weightText = value
                        .filter { it.isDigit() }
                        .take(3)
                }
            )

            ProfileSelectionSection(
                title = "Vücut Tipi",
                description = "Size en yakın vücut tipini seçin.",
                icon = Icons.Outlined.AccessibilityNew
            ) {
                bodyTypes.forEach { option ->
                    ProfileRadioRow(
                        title = option.Content,
                        selected = selectedBodyTypeId == option.Id,
                        onClick = {
                            selectedBodyTypeId = option.Id
                        }
                    )
                }
            }

            ProfileSelectionSection(
                title = "Ten Rengi",
                description = "Profilinizde gösterilecek ten rengini seçin.",
                icon = Icons.Outlined.Palette
            ) {
                skinTones.forEach { option ->
                    ProfileRadioRow(
                        title = option.Content,
                        selected = selectedSkinToneId == option.Id,
                        onClick = {
                            selectedSkinToneId = option.Id
                        }
                    )
                }
            }

            ProfileBooleanSection(
                hasPiercing = hasPiercing,
                hasTattoo = hasTattoo,
                onPiercingChange = {
                    hasPiercing = it
                },
                onTattooChange = {
                    hasTattoo = it
                }
            )

            if (!errorMessage.isNullOrBlank()) {
                ProfilePhysicalErrorCard(
                    message = errorMessage
                )
            }
        }
    }
}

@Composable
private fun ProfilePhysicalIntroductionCard() {
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
                    imageVector = Icons.Outlined.AccessibilityNew,
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
                    text = "Fiziksel Bilgileriniz",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Boy, kilo, vücut tipi ve diğer fiziksel özelliklerinizi yönetin.",
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfileMeasurementCard(
    heightText: String,
    weightText: String,
    heightValid: Boolean,
    weightValid: Boolean,
    onHeightChange: (String) -> Unit,
    onWeightChange: (String) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Text(
                text = "Ölçüler",
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = heightText,
                onValueChange = onHeightChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Boy")
                },
                placeholder = {
                    Text(text = "Örnek: 180")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Height,
                        contentDescription = null
                    )
                },
                suffix = {
                    Text(text = "cm")
                },
                singleLine = true,
                isError = !heightValid,
                supportingText = {
                    if (!heightValid) {
                        Text(text = "Boy 100 ile 250 cm arasında olmalıdır.")
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                shape = BBRadius.LgShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )

            OutlinedTextField(
                value = weightText,
                onValueChange = onWeightChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Kilo")
                },
                placeholder = {
                    Text(text = "Örnek: 75")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.MonitorWeight,
                        contentDescription = null
                    )
                },
                suffix = {
                    Text(text = "kg")
                },
                singleLine = true,
                isError = !weightValid,
                supportingText = {
                    if (!weightValid) {
                        Text(text = "Kilo 30 ile 300 kg arasında olmalıdır.")
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                shape = BBRadius.LgShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
private fun ProfileSelectionSection(
    title: String,
    description: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
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
                content()
            }
        }
    }
}

@Composable
private fun ProfileRadioRow(
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

        if (selected) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(BBIcon.SizeSm)
            )
        }
    }
}

@Composable
private fun ProfileBooleanSection(
    hasPiercing: Boolean,
    hasTattoo: Boolean,
    onPiercingChange: (Boolean) -> Unit,
    onTattooChange: (Boolean) -> Unit
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
                text = "Diğer Özellikler",
                modifier = Modifier.padding(BBSpacing.CardPadding),
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            ProfileSwitchRow(
                title = "Piercing",
                description = if (hasPiercing) "Var" else "Yok",
                icon = Icons.Outlined.StarBorder,
                checked = hasPiercing,
                onCheckedChange = onPiercingChange
            )

            ProfileSwitchRow(
                title = "Dövme",
                description = if (hasTattoo) "Var" else "Yok",
                icon = Icons.Outlined.ColorLens,
                checked = hasTattoo,
                onCheckedChange = onTattooChange
            )
        }
    }
}

@Composable
private fun ProfileSwitchRow(
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
private fun ProfilePhysicalErrorCard(
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
private fun ProfilePhysicalSaveBar(
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

private fun defaultBodyTypes(): List<ProfilePhysicalOption> {
    return listOf(
        ProfilePhysicalOption(
            Id = 1,
            Content = "İnce"
        ),
        ProfilePhysicalOption(
            Id = 2,
            Content = "Normal"
        ),
        ProfilePhysicalOption(
            Id = 3,
            Content = "Atletik"
        ),
        ProfilePhysicalOption(
            Id = 4,
            Content = "Kaslı"
        ),
        ProfilePhysicalOption(
            Id = 5,
            Content = "Dolgun"
        )
    )
}

private fun defaultSkinTones(): List<ProfilePhysicalOption> {
    return listOf(
        ProfilePhysicalOption(
            Id = 1,
            Content = "Açık"
        ),
        ProfilePhysicalOption(
            Id = 2,
            Content = "Buğday"
        ),
        ProfilePhysicalOption(
            Id = 3,
            Content = "Esmer"
        ),
        ProfilePhysicalOption(
            Id = 4,
            Content = "Koyu"
        )
    )
}