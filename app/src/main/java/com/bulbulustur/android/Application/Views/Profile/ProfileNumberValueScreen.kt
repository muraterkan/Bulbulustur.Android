package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileHeroCard

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileStickySaveBar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import kotlin.math.roundToInt

@Composable
fun ProfileNumberValueScreen(
    title: String,
    description: String,
    label: String,
    suffix: String,
    value: String,
    minimumValue: Int,
    maximumValue: Int,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    val parsedValue = value.toIntOrNull()
    val selectedValue = parsedValue?.coerceIn(
        minimumValue = minimumValue,
        maximumValue = maximumValue
    ) ?: minimumValue

    val isValid = parsedValue != null &&
            parsedValue in minimumValue..maximumValue

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
                enabled = isValid && !isLoading,
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
    

            ProfileMeasurementPickerCard(
                selectedValue = selectedValue,
                minimumValue = minimumValue,
                maximumValue = maximumValue,
                suffix = suffix,
                isLoading = isLoading,
                onValueChange = { newValue ->
                    onValueChange(newValue.toString())
                }
            )

            ProfileMeasurementRangeCard(
                minimumValue = minimumValue,
                maximumValue = maximumValue,
                suffix = suffix
            )

            if (!errorMessage.isNullOrBlank()) {
                ProfileMeasurementMessageCard(
                    message = errorMessage,
                    isError = true
                )
            }

            Spacer(
                modifier = Modifier.height(BBSpacing.Space4)
            )
        }
    }
}

@Composable
private fun ProfileMeasurementIntro(
    label: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        Text(
            text = "$label bilginizi seçin",
            style = BbTypography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProfileMeasurementPickerCard(
    selectedValue: Int,
    minimumValue: Int,
    maximumValue: Int,
    suffix: String,
    isLoading: Boolean,
    onValueChange: (Int) -> Unit
) {
    val previousValue = (selectedValue - 1)
        .takeIf { it >= minimumValue }

    val nextValue = (selectedValue + 1)
        .takeIf { it <= maximumValue }

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(BBSpacing.CardPaddingLoose),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            ProfileMeasurementUnitBadge(
                suffix = suffix
            )

            ProfileMeasurementNeighbourValue(
                value = previousValue,
                suffix = suffix
            )

            ProfileMeasurementSelectedValue(
                value = selectedValue,
                suffix = suffix
            )

            ProfileMeasurementNeighbourValue(
                value = nextValue,
                suffix = suffix
            )

            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = selectedValue.toFloat(),
                onValueChange = { sliderValue ->
                    onValueChange(
                        sliderValue
                            .roundToInt()
                            .coerceIn(minimumValue, maximumValue)
                    )
                },
                valueRange = minimumValue.toFloat()..maximumValue.toFloat(),
                enabled = !isLoading,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.outlineVariant,
                    disabledThumbColor = MaterialTheme.colorScheme.outline,
                    disabledActiveTrackColor = MaterialTheme.colorScheme.outlineVariant,
                    disabledInactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileMeasurementStepButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Remove,
                    contentDescription = "Azalt",
                    enabled = !isLoading && selectedValue > minimumValue,
                    onClick = {
                        onValueChange(selectedValue - 1)
                    }
                )

                Box(
                    modifier = Modifier
                        .weight(1.4f)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = BBRadius.LgShape
                        )
                        .padding(
                            horizontal = BBSpacing.Space3,
                            vertical = BBSpacing.Space3
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$selectedValue $suffix",
                        style = BbTypography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                ProfileMeasurementStepButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Add,
                    contentDescription = "Artır",
                    enabled = !isLoading && selectedValue < maximumValue,
                    onClick = {
                        onValueChange(selectedValue + 1)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProfileMeasurementUnitBadge(
    suffix: String
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.PillShape
            )
            .padding(
                horizontal = BBSpacing.Space4,
                vertical = BBSpacing.Space2
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = suffix.uppercase(),
            style = BbTypography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProfileMeasurementNeighbourValue(
    value: Int?,
    suffix: String
) {
    Text(
        text = value?.let {
            "$it $suffix"
        }.orEmpty(),
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp),
        style = BbTypography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ProfileMeasurementSelectedValue(
    value: Int,
    suffix: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.XlShape
            )
            .padding(
                horizontal = BBSpacing.Space4,
                vertical = BBSpacing.Space5
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = value.toString(),
                style = BbTypography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = suffix,
                modifier = Modifier.padding(
                    bottom = BBSpacing.Space2
                ),
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ProfileMeasurementStepButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val containerColor = if (enabled) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
    }

    val contentColor = if (enabled) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
    }

    Box(
        modifier = modifier
            .height(52.dp)
            .background(
                color = containerColor,
                shape = BBRadius.LgShape
            )
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = contentColor,
            modifier = Modifier.size(BBIcon.Section)
        )
    }
}

@Composable
private fun ProfileMeasurementRangeCard(
    minimumValue: Int,
    maximumValue: Int,
    suffix: String
) {
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
                    .size(BBIcon.BoxSm)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Seçim aralığı",
                    style = BbTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "$minimumValue – $maximumValue $suffix",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun ProfileMeasurementMessageCard(
    message: String,
    isError: Boolean
) {
    val containerColor = if (isError) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        MaterialTheme.colorScheme.surface
    }

    val contentColor = if (isError) {
        MaterialTheme.colorScheme.onErrorContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = containerColor,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPadding)
    ) {
        Text(
            text = message,
            style = BbTypography.bodySmall,
            color = contentColor
        )
    }
}

@Composable
private fun ProfileMeasurementBottomBar(
    enabled: Boolean,
    isLoading: Boolean,
    onSaveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .navigationBarsPadding()
    ) {
        Divider(
            color = MaterialTheme.colorScheme.outlineVariant
        )

        BbButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BBSpacing.PageHorizontal,
                    vertical = BBSpacing.Space4
                ),
            text = "Kaydet",
            variant = BbButtonVariant.Primary,
            enabled = enabled,
            isLoading = isLoading,
            onClick = onSaveClick
        )
    }
}