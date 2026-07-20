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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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

data class ProfileBreastSizeOption(
    val Value: String,
    val Content: String
)

@Composable
fun ProfileBreastSizeScreen(
    initialValue: String = "",
    options: List<ProfileBreastSizeOption> = DefaultBreastSizeOptions(),
    isLoading: Boolean = false,
    isSaving: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (String?) -> Unit = {}
) {
    val normalizedInitialValue = initialValue.trim()

    var selectedValue by rememberSaveable {
        mutableStateOf(normalizedInitialValue)
    }

    LaunchedEffect(normalizedInitialValue) {
        selectedValue = normalizedInitialValue
    }

    val hasChanged = selectedValue != normalizedInitialValue
    val canSave = hasChanged && !isLoading && !isSaving

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Göğüs Ölçüsü",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ProfileBreastSizeSaveBar(
                enabled = canSave,
                isSaving = isSaving,
                onClick = {
                    onSaveClick(
                        selectedValue.ifBlank {
                            null
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.Space6
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                ProfileBreastSizeIntroductionCard()
            }

            item {
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.None
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        options.forEachIndexed { index, option ->
                            ProfileBreastSizeOptionRow(
                                option = option,
                                selected = selectedValue == option.Value,
                                onClick = {
                                    selectedValue = option.Value
                                }
                            )

                            if (index < options.lastIndex) {
                                ProfilePrivateInformationDivider()
                            }
                        }
                    }
                }
            }

            if (!errorMessage.isNullOrBlank()) {
                item {
                    ProfilePrivateInformationMessageCard(
                        message = errorMessage,
                        isError = true
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileBreastSizeIntroductionCard() {
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
                    imageVector = Icons.Outlined.Badge,
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
                    text = "Size uygun ölçüyü seçin",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Bu alan isteğe bağlıdır. Seçiminizi profilinizde daha sonra değiştirebilirsiniz.",
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfileBreastSizeOptionRow(
    option: ProfileBreastSizeOption,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                if (selected) {
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                } else {
                    MaterialTheme.colorScheme.surface
                }
            )
            .padding(
                horizontal = BBSpacing.CardPadding,
                vertical = BBSpacing.Space4
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = option.Content,
            modifier = Modifier.weight(1f),
            style = BbTypography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }
        )

        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Composable
private fun ProfileBreastSizeSaveBar(
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

private fun DefaultBreastSizeOptions(): List<ProfileBreastSizeOption> {
    return listOf(
        ProfileBreastSizeOption(
            Value = "",
            Content = "Belirtmek istemiyorum"
        ),
        ProfileBreastSizeOption(Value = "AA", Content = "AA"),
        ProfileBreastSizeOption(Value = "A", Content = "A"),
        ProfileBreastSizeOption(Value = "B", Content = "B"),
        ProfileBreastSizeOption(Value = "C", Content = "C"),
        ProfileBreastSizeOption(Value = "D", Content = "D"),
        ProfileBreastSizeOption(Value = "DD", Content = "DD"),
        ProfileBreastSizeOption(Value = "E", Content = "E"),
        ProfileBreastSizeOption(Value = "F", Content = "F")
    )
}
