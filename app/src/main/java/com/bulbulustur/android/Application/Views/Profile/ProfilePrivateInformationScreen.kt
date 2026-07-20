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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Straighten
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

data class ProfilePrivateInformationFormData(
    val PenisSize: Int?,
    val BreastSize: String?
)

@Composable
fun ProfilePrivateInformationScreen(
    initialPenisSize: Int? = null,
    initialBreastSize: String = "",
    breastSizeOptions: List<String> = defaultBreastSizeOptions(),
    isSaving: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (ProfilePrivateInformationFormData) -> Unit = {}
) {
    var penisSizeText by rememberSaveable {
        mutableStateOf(initialPenisSize?.toString().orEmpty())
    }

    var selectedBreastSize by rememberSaveable {
        mutableStateOf(initialBreastSize.trim())
    }

    LaunchedEffect(
        initialPenisSize,
        initialBreastSize
    ) {
        penisSizeText = initialPenisSize?.toString().orEmpty()
        selectedBreastSize = initialBreastSize.trim()
    }

    val penisSize = penisSizeText.toIntOrNull()
    val penisSizeValid = penisSize == null || penisSize in 1..40

    val normalizedInitialBreastSize = initialBreastSize.trim()
    val normalizedBreastSize = selectedBreastSize.trim()

    val hasChanged =
        penisSize != initialPenisSize ||
                normalizedBreastSize != normalizedInitialBreastSize

    val canSave =
        hasChanged &&
                penisSizeValid &&
                !isSaving

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Özel Bilgiler",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ProfilePrivateInformationSaveBar(
                enabled = canSave,
                isSaving = isSaving,
                onClick = {
                    onSaveClick(
                        ProfilePrivateInformationFormData(
                            PenisSize = penisSize,
                            BreastSize = normalizedBreastSize.ifBlank {
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
            ProfilePrivateInformationIntroductionCard()

            ProfilePenisSizeCard(
                value = penisSizeText,
                isValid = penisSizeValid,
                onValueChange = { value ->
                    penisSizeText = value
                        .filter { it.isDigit() }
                        .take(2)
                }
            )

            ProfileBreastSizeCard(
                options = breastSizeOptions,
                selectedValue = selectedBreastSize,
                onSelected = { value ->
                    selectedBreastSize = value
                }
            )

            ProfilePrivateInformationPrivacyCard()

            if (!errorMessage.isNullOrBlank()) {
                ProfilePrivateInformationErrorCard(
                    message = errorMessage
                )
            }
        }
    }
}

@Composable
private fun ProfilePrivateInformationIntroductionCard() {
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
                    imageVector = Icons.Outlined.Lock,
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
                    text = "Size Özel Bilgiler",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Bu alanlar isteğe bağlıdır. Yalnızca paylaşmak istediğiniz bilgileri ekleyin.",
                    style = BbTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfilePenisSizeCard(
    value: String,
    isValid: Boolean,
    onValueChange: (String) -> Unit
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
                        imageVector = Icons.Outlined.Straighten,
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
                        text = "Penis Ölçüsü",
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Santimetre cinsinden değer girebilirsiniz.",
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Ölçü")
                },
                placeholder = {
                    Text(text = "Örnek: 16")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Straighten,
                        contentDescription = null
                    )
                },
                suffix = {
                    Text(text = "cm")
                },
                singleLine = true,
                isError = !isValid,
                supportingText = {
                    if (!isValid) {
                        Text(
                            text = "Değer 1 ile 40 cm arasında olmalıdır."
                        )
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
private fun ProfileBreastSizeCard(
    options: List<String>,
    selectedValue: String,
    onSelected: (String) -> Unit
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
                        imageVector = Icons.Outlined.Badge,
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
                        text = "Göğüs Ölçüsü",
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Size uygun ölçüyü seçin.",
                        style = BbTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            ProfileBreastSizeOptionRow(
                title = "Belirtilmemiş",
                value = "",
                selectedValue = selectedValue,
                onSelected = onSelected
            )

            options.forEach { option ->
                ProfileBreastSizeOptionRow(
                    title = option,
                    value = option,
                    selectedValue = selectedValue,
                    onSelected = onSelected
                )
            }
        }
    }
}

@Composable
private fun ProfileBreastSizeOptionRow(
    title: String,
    value: String,
    selectedValue: String,
    onSelected: (String) -> Unit
) {
    val selected = selectedValue == value

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelected(value)
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
            onClick = {
                onSelected(value)
            },
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
private fun ProfilePrivateInformationPrivacyCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.SizeMd)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Gizlilik",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Bu bilgilerin kimler tarafından görülebileceği ileride profil görünürlük ayarlarından yönetilecektir.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfilePrivateInformationErrorCard(
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
private fun ProfilePrivateInformationSaveBar(
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

private fun defaultBreastSizeOptions(): List<String> {
    return listOf(
        "AA",
        "A",
        "B",
        "C",
        "D",
        "DD",
        "E",
        "F"
    )
}