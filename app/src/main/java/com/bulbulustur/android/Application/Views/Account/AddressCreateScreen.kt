package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeFields
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeState
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel

@Composable
fun AddressCreateScreen(
    addressCascadeState: AddressCascadeState,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onCountrySelected: (Int) -> Unit = {},
    onCountryStateSelected: (Int) -> Unit = {},
    onCountryDepartmentSelected: (Int?) -> Unit = {},
    onCitySelected: (Int) -> Unit = {},
    onDistrictSelected: (Int?) -> Unit = {},
    onSaveClick: (MemberAddressInsertModel) -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var postCode by remember { mutableStateOf("") }
    var addressText by remember { mutableStateOf("") }
    var addressTitle by remember { mutableStateOf("") }
    var isDefault by remember { mutableStateOf(false) }

    val selection = addressCascadeState.Selection

    val canSubmit =
        name.isNotBlank() &&
                surname.isNotBlank() &&
                phone.trim().length >= 10 &&
                addressText.trim().length >= 10 &&
                addressTitle.isNotBlank() &&
                addressCascadeState.IsValid &&
                !isLoading &&
                !addressCascadeState.IsLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Yeni Adres Ekle",
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
            AddressCreateSection(
                title = "Alıcı Bilgileri",
                description = "Teslimatı alacak kişinin bilgilerini girin."
            ) {
                AddressCreateTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Ad",
                    placeholder = "Adınız"
                )

                AddressCreateTextField(
                    value = surname,
                    onValueChange = { surname = it },
                    label = BBLocalization.Current.Get(key = "43b07485-278d-4633-9404-bf6a30a28222", fallback = ""),
                    placeholder = "Soyadınız"
                )

                AddressCreateTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = "Telefon",
                    placeholder = "5xx xxx xx xx",
                    keyboardType = KeyboardType.Phone
                )

                AddressCreateTextField(
                    value = postCode,
                    onValueChange = { postCode = it },
                    label = "Posta Kodu",
                    placeholder = "Posta kodu",
                    keyboardType = KeyboardType.Number
                )
            }

            AddressCreateSection(
                title = "Konum Bilgileri",
                description = "Adresin ülke, bölge, şehir ve ilçe bilgilerini seçin."
            ) {
                AddressCascadeFields(
                    state = addressCascadeState,
                    onCountrySelected = onCountrySelected,
                    onCountryStateSelected = onCountryStateSelected,
                    onCountryDepartmentSelected = onCountryDepartmentSelected,
                    onCitySelected = onCitySelected,
                    onDistrictSelected = onDistrictSelected,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )
            }

            AddressCreateSection(
                title = "Adres Detayı",
                description = "Açık adresi ve adres başlığını girin."
            ) {
                AddressCreateTextArea(
                    value = addressText,
                    onValueChange = { addressText = it },
                    label = "Açık Adres",
                    placeholder = "Mahalle, cadde, sokak, bina, kat ve daire bilgisi"
                )

                AddressCreateTextField(
                    value = addressTitle,
                    onValueChange = { addressTitle = it },
                    label = "Adres Başlığı",
                    placeholder = "Ev Adresim"
                )

                AddressCreateDefaultField(
                    checked = isDefault,
                    onCheckedChange = { isDefault = it },
                    enabled = !isLoading
                )
            }

            if (!errorMessage.isNullOrBlank()) {
                AddressCreateError(message = errorMessage)
            }

            BbButton(
                text = "Adresi Kaydet",
                onClick = {
                    onSaveClick(
                        MemberAddressInsertModel(
                            AddressTitle = addressTitle.trim(),
                            Name = name.trim(),
                            Surname = surname.trim(),
                            CountryId = selection.CountryId,
                            CountryStateId = selection.CountryStateId,
                            CountryDepartmentId = selection.CountryDepartmentId,
                            CityId = selection.CityId,
                            DistrictId = selection.DistrictId,
                            PostCode = postCode.trim(),
                            Address = addressText.trim(),
                            Phone = phone.trim(),
                            IsDefault = isDefault
                        )
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
private fun AddressCreateSection(
    title: String,
    description: String,
    content: @Composable ColumnScope.() -> Unit
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    )
                    .padding(BBSpacing.CardPaddingCompact),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = description,
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            content()
        }
    }
}

@Composable
private fun AddressCreateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                style = BbTypography.labelSmall
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = BbTypography.bodySmall
            )
        },
        singleLine = true,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        textStyle = BbTypography.bodyMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            disabledIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun AddressCreateTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                style = BbTypography.labelSmall
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = BbTypography.bodySmall
            )
        },
        minLines = 4,
        shape = BBRadius.Input,
        textStyle = BbTypography.bodyMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            disabledIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun AddressCreateDefaultField(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Default,
        padding = BbCardPadding.Small
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Varsayılan Adres",
                    style = BbTypography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Siparişlerde öncelikli olarak bu adres kullanılır.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun AddressCreateError(message: String) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = message,
            style = BbTypography.bodySmall,
            color = BBColors.Red.Red600
        )
    }
}