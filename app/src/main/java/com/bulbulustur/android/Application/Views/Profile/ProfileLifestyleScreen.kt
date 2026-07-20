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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.SmokingRooms
import androidx.compose.material.icons.outlined.Spa
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

data class ProfileLifestyleFormData(
    val ReligionId: Int?,
    val DietTypeId: Int?,
    val ExerciseHabitId: Int?,
    val AlcoholHabitId: Int?,
    val SmokingHabitId: Int?
)

data class ProfileLifestyleOption(
    val Id: Int,
    val Content: String
)

@Composable
fun ProfileLifestyleScreen(
    initialReligionId: Int? = null,
    initialDietTypeId: Int? = null,
    initialExerciseHabitId: Int? = null,
    initialAlcoholHabitId: Int? = null,
    initialSmokingHabitId: Int? = null,
    religions: List<ProfileLifestyleOption> = defaultReligions(),
    dietTypes: List<ProfileLifestyleOption> = defaultDietTypes(),
    exerciseHabits: List<ProfileLifestyleOption> = defaultExerciseHabits(),
    alcoholHabits: List<ProfileLifestyleOption> = defaultAlcoholHabits(),
    smokingHabits: List<ProfileLifestyleOption> = defaultSmokingHabits(),
    isSaving: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (ProfileLifestyleFormData) -> Unit = {}
) {
    var selectedReligionId by rememberSaveable {
        mutableStateOf(initialReligionId)
    }

    var selectedDietTypeId by rememberSaveable {
        mutableStateOf(initialDietTypeId)
    }

    var selectedExerciseHabitId by rememberSaveable {
        mutableStateOf(initialExerciseHabitId)
    }

    var selectedAlcoholHabitId by rememberSaveable {
        mutableStateOf(initialAlcoholHabitId)
    }

    var selectedSmokingHabitId by rememberSaveable {
        mutableStateOf(initialSmokingHabitId)
    }

    LaunchedEffect(
        initialReligionId,
        initialDietTypeId,
        initialExerciseHabitId,
        initialAlcoholHabitId,
        initialSmokingHabitId
    ) {
        selectedReligionId = initialReligionId
        selectedDietTypeId = initialDietTypeId
        selectedExerciseHabitId = initialExerciseHabitId
        selectedAlcoholHabitId = initialAlcoholHabitId
        selectedSmokingHabitId = initialSmokingHabitId
    }

    val hasChanged =
        selectedReligionId != initialReligionId ||
                selectedDietTypeId != initialDietTypeId ||
                selectedExerciseHabitId != initialExerciseHabitId ||
                selectedAlcoholHabitId != initialAlcoholHabitId ||
                selectedSmokingHabitId != initialSmokingHabitId

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Yaşam Tarzı",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ProfileLifestyleSaveBar(
                enabled = hasChanged && !isSaving,
                isSaving = isSaving,
                onClick = {
                    onSaveClick(
                        ProfileLifestyleFormData(
                            ReligionId = selectedReligionId,
                            DietTypeId = selectedDietTypeId,
                            ExerciseHabitId = selectedExerciseHabitId,
                            AlcoholHabitId = selectedAlcoholHabitId,
                            SmokingHabitId = selectedSmokingHabitId
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
            ProfileLifestyleIntroductionCard()

            ProfileLifestyleSelectionCard(
                title = "İnanç",
                description = "Size en yakın seçeneği belirleyin.",
                icon = Icons.Outlined.SelfImprovement,
                options = religions,
                selectedId = selectedReligionId,
                onSelected = {
                    selectedReligionId = it
                }
            )

            ProfileLifestyleSelectionCard(
                title = "Beslenme",
                description = "Beslenme alışkanlığınızı seçin.",
                icon = Icons.Outlined.Restaurant,
                options = dietTypes,
                selectedId = selectedDietTypeId,
                onSelected = {
                    selectedDietTypeId = it
                }
            )

            ProfileLifestyleSelectionCard(
                title = "Spor ve Egzersiz",
                description = "Ne sıklıkla egzersiz yaptığınızı seçin.",
                icon = Icons.Outlined.FitnessCenter,
                options = exerciseHabits,
                selectedId = selectedExerciseHabitId,
                onSelected = {
                    selectedExerciseHabitId = it
                }
            )

            ProfileLifestyleSelectionCard(
                title = "Alkol",
                description = "Alkol kullanım alışkanlığınızı seçin.",
                icon = Icons.Outlined.LocalBar,
                options = alcoholHabits,
                selectedId = selectedAlcoholHabitId,
                onSelected = {
                    selectedAlcoholHabitId = it
                }
            )

            ProfileLifestyleSelectionCard(
                title = "Sigara",
                description = "Sigara kullanım alışkanlığınızı seçin.",
                icon = Icons.Outlined.SmokingRooms,
                options = smokingHabits,
                selectedId = selectedSmokingHabitId,
                onSelected = {
                    selectedSmokingHabitId = it
                }
            )

            if (!errorMessage.isNullOrBlank()) {
                ProfileLifestyleErrorCard(
                    message = errorMessage
                )
            }
        }
    }
}

@Composable
private fun ProfileLifestyleIntroductionCard() {
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
                    text = "Yaşam Tarzınız",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Günlük alışkanlıklarınızı ve yaşam tercihlerinizi profilinize ekleyin.",
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfileLifestyleSelectionCard(
    title: String,
    description: String,
    icon: ImageVector,
    options: List<ProfileLifestyleOption>,
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
                    ProfileLifestyleOptionRow(
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
private fun ProfileLifestyleOptionRow(
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
private fun ProfileLifestyleErrorCard(
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
private fun ProfileLifestyleSaveBar(
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

private fun defaultReligions(): List<ProfileLifestyleOption> {
    return listOf(
        ProfileLifestyleOption(1, "Belirtmek istemiyorum"),
        ProfileLifestyleOption(2, "Müslüman"),
        ProfileLifestyleOption(3, "Hristiyan"),
        ProfileLifestyleOption(4, "Yahudi"),
        ProfileLifestyleOption(5, "Ateist"),
        ProfileLifestyleOption(6, "Agnostik"),
        ProfileLifestyleOption(7, "Diğer")
    )
}

private fun defaultDietTypes(): List<ProfileLifestyleOption> {
    return listOf(
        ProfileLifestyleOption(1, "Her şeyi yerim"),
        ProfileLifestyleOption(2, "Vejetaryen"),
        ProfileLifestyleOption(3, "Vegan"),
        ProfileLifestyleOption(4, "Pesketaryen"),
        ProfileLifestyleOption(5, "Glütensiz"),
        ProfileLifestyleOption(6, "Diğer")
    )
}

private fun defaultExerciseHabits(): List<ProfileLifestyleOption> {
    return listOf(
        ProfileLifestyleOption(1, "Aktif"),
        ProfileLifestyleOption(2, "Sık sık"),
        ProfileLifestyleOption(3, "Bazen"),
        ProfileLifestyleOption(4, "Neredeyse hiç")
    )
}

private fun defaultAlcoholHabits(): List<ProfileLifestyleOption> {
    return listOf(
        ProfileLifestyleOption(1, "Kullanmıyorum"),
        ProfileLifestyleOption(2, "Sosyal olarak"),
        ProfileLifestyleOption(3, "Bazen"),
        ProfileLifestyleOption(4, "Sık sık")
    )
}

private fun defaultSmokingHabits(): List<ProfileLifestyleOption> {
    return listOf(
        ProfileLifestyleOption(1, "Kullanmıyorum"),
        ProfileLifestyleOption(2, "Bazen"),
        ProfileLifestyleOption(3, "Düzenli"),
        ProfileLifestyleOption(4, "Bırakmaya çalışıyorum")
    )
}