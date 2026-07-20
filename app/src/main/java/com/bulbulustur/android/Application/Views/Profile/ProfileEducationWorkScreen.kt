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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.WorkOutline
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

private const val PROFILE_JOB_TITLE_MAX_LENGTH = 100

data class ProfileEducationWorkFormData(
    val EducationId: Int?,
    val JobTitle: String?
)

data class ProfileEducationOption(
    val Id: Int,
    val Content: String
)

@Composable
fun ProfileEducationWorkScreen(
    initialEducationId: Int? = null,
    initialJobTitle: String = "",
    educationOptions: List<ProfileEducationOption> = defaultEducationOptions(),
    isSaving: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (ProfileEducationWorkFormData) -> Unit = {}
) {
    var selectedEducationId by rememberSaveable {
        mutableStateOf(initialEducationId)
    }

    var jobTitle by rememberSaveable {
        mutableStateOf(initialJobTitle.take(PROFILE_JOB_TITLE_MAX_LENGTH))
    }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(
        initialEducationId,
        initialJobTitle
    ) {
        selectedEducationId = initialEducationId
        jobTitle = initialJobTitle.take(PROFILE_JOB_TITLE_MAX_LENGTH)
    }

    val normalizedInitialJobTitle = initialJobTitle.trim()
    val normalizedJobTitle = jobTitle.trim()

    val hasChanged =
        selectedEducationId != initialEducationId ||
                normalizedJobTitle != normalizedInitialJobTitle

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Eğitim ve İş",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ProfileEducationWorkSaveBar(
                enabled = hasChanged && !isSaving,
                isSaving = isSaving,
                onClick = {
                    focusManager.clearFocus()

                    onSaveClick(
                        ProfileEducationWorkFormData(
                            EducationId = selectedEducationId,
                            JobTitle = normalizedJobTitle.ifBlank {
                                null
                            }
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
            ProfileEducationWorkIntroductionCard()

            ProfileEducationSelectionCard(
                options = educationOptions,
                selectedId = selectedEducationId,
                onSelected = { educationId ->
                    selectedEducationId = educationId
                }
            )

            ProfileJobTitleCard(
                jobTitle = jobTitle,
                onJobTitleChange = { value ->
                    jobTitle = value.take(PROFILE_JOB_TITLE_MAX_LENGTH)
                },
                onKeyboardDone = {
                    focusManager.clearFocus()
                }
            )

            if (!errorMessage.isNullOrBlank()) {
                ProfileEducationWorkErrorCard(
                    message = errorMessage
                )
            }
        }
    }
}

@Composable
private fun ProfileEducationWorkIntroductionCard() {
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
                    imageVector = Icons.Outlined.School,
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
                    text = "Eğitim ve Çalışma Hayatı",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Eğitim düzeyinizi ve iş unvanınızı profilinize ekleyin.",
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfileEducationSelectionCard(
    options: List<ProfileEducationOption>,
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
                        imageVector = Icons.Outlined.School,
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
                        text = "Eğitim Düzeyi",
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Tamamladığınız veya devam ettiğiniz eğitim düzeyini seçin.",
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            options.forEach { option ->
                ProfileEducationOptionRow(
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
private fun ProfileEducationOptionRow(
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
private fun ProfileJobTitleCard(
    jobTitle: String,
    onJobTitleChange: (String) -> Unit,
    onKeyboardDone: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                        imageVector = Icons.Outlined.WorkOutline,
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
                        text = "İş Unvanı",
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Mevcut görevinizi veya profesyonel unvanınızı yazın.",
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            OutlinedTextField(
                value = jobTitle,
                onValueChange = onJobTitleChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "İş Unvanı")
                },
                placeholder = {
                    Text(text = "Örnek: Yazılım Geliştirici")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Badge,
                        contentDescription = null
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onKeyboardDone()
                    }
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

            Text(
                text = "${jobTitle.length} / $PROFILE_JOB_TITLE_MAX_LENGTH",
                modifier = Modifier.align(Alignment.End),
                style = BbTypography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProfileEducationWorkErrorCard(
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
private fun ProfileEducationWorkSaveBar(
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

private fun defaultEducationOptions(): List<ProfileEducationOption> {
    return listOf(
        ProfileEducationOption(
            Id = 1,
            Content = "İlköğretim"
        ),
        ProfileEducationOption(
            Id = 2,
            Content = "Lise"
        ),
        ProfileEducationOption(
            Id = 3,
            Content = "Ön Lisans"
        ),
        ProfileEducationOption(
            Id = 4,
            Content = "Lisans"
        ),
        ProfileEducationOption(
            Id = 5,
            Content = "Yüksek Lisans"
        ),
        ProfileEducationOption(
            Id = 6,
            Content = "Doktora"
        ),
        ProfileEducationOption(
            Id = 7,
            Content = "Belirtmek istemiyorum"
        )
    )
}