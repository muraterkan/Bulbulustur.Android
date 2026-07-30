package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel

@Composable
fun AccountEditScreen(
    member: MemberUpdateModel?,

    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},

    onSaveClick: (
        name: String,
        surname: String,
        profession: String
    ) -> Unit = { _, _, _ -> }
) {
    val nameState = remember {
        mutableStateOf("")
    }

    val surnameState = remember {
        mutableStateOf("")
    }

    val professionState = remember {
        mutableStateOf("")
    }

    LaunchedEffect(member?.MemberId) {
        val currentMember = member ?: return@LaunchedEffect

        nameState.value = currentMember.Name
        surnameState.value = currentMember.Surname
        professionState.value = currentMember.Profession

    }

    val canSubmit = member != null &&
            nameState.value.isNotBlank() &&
            surnameState.value.isNotBlank() &&

            !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Kullanıcı Bilgileri",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    PaddingValues(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
        ) {
            ProfileEditIntroCard()

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                ) {
                    ProfileEditSectionHeader(
                        title = BBLocalization.Current.Get(key = "2adcb241-fb30-4326-a81b-78b4fc025a82", fallback = ""),
                        description = "Ad, soyad ve meslek bilgilerinizi güncelleyin."
                    )

                    ProfileEditTextField(
                        value = nameState.value,
                        onValueChange = {
                            nameState.value = it
                        },
                        label = "İsim",
                        placeholder = "İsminiz",
                        enabled = !isLoading
                    )

                    ProfileEditTextField(
                        value = surnameState.value,
                        onValueChange = {
                            surnameState.value = it
                        },
                        label = BBLocalization.Current.Get(key = "15a15b53-974d-413d-9cee-f8622397c2d8", fallback = ""),
                        placeholder = "Soyisminiz",
                        enabled = !isLoading
                    )

                    ProfileEditTextField(
                        value = professionState.value,
                        onValueChange = {
                            professionState.value = it
                        },
                        label = "Meslek",
                        placeholder = "Mesleğiniz",
                        enabled = !isLoading
                    )
                }
            }
            errorMessage
                ?.takeIf { it.isNotBlank() }
                ?.let { message ->
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

            BbButton(
                text = "Güncelle",
                onClick = {
                    onSaveClick(
                        nameState.value,
                        surnameState.value,
                        professionState.value
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = canSubmit,
                isLoading = isLoading
            )
        }
    }
}

@Composable
private fun ProfileEditIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Ad, soyad ve meslek bilgilerinizi buradan güncelleyebilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProfileEditSectionHeader(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProfileEditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        singleLine = true,
        enabled = enabled,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}