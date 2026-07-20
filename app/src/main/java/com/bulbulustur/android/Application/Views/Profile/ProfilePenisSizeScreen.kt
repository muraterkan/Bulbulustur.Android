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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Straighten
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

data class ProfilePenisSizeOption(
    val Value: Int?,
    val Content: String
)

@Composable
fun ProfilePenisSizeScreen(
    initialValue: Int? = null,
    options: List<ProfilePenisSizeOption> = DefaultPenisSizeOptions(),
    isLoading: Boolean = false,
    isSaving: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (Int?) -> Unit = {}
) {
    var selectedValue by rememberSaveable {
        mutableStateOf(initialValue)
    }

    LaunchedEffect(initialValue) {
        selectedValue = initialValue
    }

    val hasChanged = selectedValue != initialValue
    val canSave = hasChanged && !isLoading && !isSaving

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Penis Ölçüsü",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ProfilePenisSizeSaveBar(
                enabled = canSave,
                isSaving = isSaving,
                onClick = {
                    onSaveClick(selectedValue)
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
                ProfilePenisSizeIntroductionCard()
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
                            ProfilePenisSizeOptionRow(
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
private fun ProfilePenisSizeIntroductionCard() {
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
                    imageVector = Icons.Outlined.Straighten,
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
                    text = "Ölçü aralığınızı seçin",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Bu alan isteğe bağlıdır. Serbest sayı yerine size uygun aralığı seçebilirsiniz.",
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfilePenisSizeOptionRow(
    option: ProfilePenisSizeOption,
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
private fun ProfilePenisSizeSaveBar(
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

private fun DefaultPenisSizeOptions(): List<ProfilePenisSizeOption> {
    return listOf(
        ProfilePenisSizeOption(
            Value = null,
            Content = "Belirtmek istemiyorum"
        ),
        ProfilePenisSizeOption(
            Value = 9,
            Content = "10 cm'den küçük"
        ),
        ProfilePenisSizeOption(
            Value = 11,
            Content = "10–12 cm"
        ),
        ProfilePenisSizeOption(
            Value = 14,
            Content = "13–15 cm"
        ),
        ProfilePenisSizeOption(
            Value = 17,
            Content = "16–18 cm"
        ),
        ProfilePenisSizeOption(
            Value = 20,
            Content = "19–21 cm"
        ),
        ProfilePenisSizeOption(
            Value = 22,
            Content = "22 cm ve üzeri"
        )
    )
}
