package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Views.Profile.Components.BbProfileStickySaveBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

private const val PROFILE_BIO_MAX_LENGTH = 500

@Composable
fun ProfileBioScreen(
    value: String,
    initialValue: String = "",
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    val normalizedValue = value.trim()
    val normalizedInitialValue = initialValue.trim()

    val hasChanges =
        normalizedValue != normalizedInitialValue

    val canSave =
        hasChanges &&
            normalizedValue.isNotBlank() &&
            normalizedValue.length <= PROFILE_BIO_MAX_LENGTH &&
            !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Hakkımda",
                onBackClick = onBackClick
            )
        },
        
        bottomBar = {
            BbProfileStickySaveBar(
                enabled = canSave,
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
                .verticalScroll(rememberScrollState())
                .padding(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.PageTopCompact,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.PageBottomWithCta
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            Text(
                text = "Kendinizden bahsedin",
                style = BbTypography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Bu metin profilinizde görünür. Kendinizi, ilgi alanlarınızı ve sizi tanımayı kolaylaştıracak bilgileri kısaca paylaşın.",
                style = BbTypography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.None
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 280.dp),
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= PROFILE_BIO_MAX_LENGTH) {
                            onValueChange(newValue)
                        }
                    },
                    enabled = !isLoading,
                    placeholder = {
                        Text(
                            text = "Kendiniz, ilgi alanlarınız ve profilinizde paylaşmak istediğiniz bilgiler hakkında yazın...",
                            style = BbTypography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    textStyle = BbTypography.bodyLarge,
                    minLines = 10,
                    maxLines = 16,
                    shape = BBRadius.LgShape,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Default
                    ),
                    keyboardActions = KeyboardActions.Default,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                        disabledIndicatorColor = MaterialTheme.colorScheme.outlineVariant
                    ),
                    supportingText = {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                text = "${value.length} / $PROFILE_BIO_MAX_LENGTH",
                                style = BbTypography.labelMedium,
                                color = if (value.length >= PROFILE_BIO_MAX_LENGTH) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            )
                        }
                    }
                )
            }

            if (!errorMessage.isNullOrBlank()) {
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Text(
                        text = errorMessage,
                        style = BbTypography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            Text(
                text = "İpucu: Kişisel iletişim bilgilerinizi, adresinizi veya güvenlik açısından hassas bilgileri paylaşmayın.",
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
