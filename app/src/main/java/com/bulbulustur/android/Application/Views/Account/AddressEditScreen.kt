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
import androidx.compose.runtime.LaunchedEffect
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
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel

@Composable
fun AddressEditScreen(
    address: MemberAddressUpdateModel?,
    addressCascadeState: AddressCascadeState,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onCountrySelected: (Int) -> Unit = {},
    onCountryStateSelected: (Int) -> Unit = {},
    onCountryDepartmentSelected: (Int?) -> Unit = {},
    onCitySelected: (Int) -> Unit = {},
    onDistrictSelected: (Int?) -> Unit = {},
    onSaveClick: (MemberAddressUpdateModel) -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var postCode by remember { mutableStateOf("") }
    var addressText by remember { mutableStateOf("") }
    var addressTitle by remember { mutableStateOf("") }
    var isDefault by remember { mutableStateOf(false) }

    LaunchedEffect(address) {
        val currentAddress = address ?: return@LaunchedEffect

        name = currentAddress.Name
        surname = currentAddress.Surname
        phone = currentAddress.Phone
        postCode = currentAddress.PostCode
        addressText = currentAddress.Address
        addressTitle = currentAddress.AddressTitle
        isDefault = currentAddress.IsDefault
    }

    val selection = addressCascadeState.Selection

    val canSubmit =
        address != null &&
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
                title = BBLocalization.Current.Get(key = "25a000ba-1002-437a-b38f-ef07416bdfc7", fallback = ""),
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
            AddressEditSection(
                title = "Alıcı Bilgileri",
                description = "Teslimatı alacak kişinin bilgilerini düzenleyin."
            ) {
                AddressEditTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Ad",
                    placeholder = BBLocalization.Current.Get(key = "de47456e-91cb-47d5-8882-458fe0cf0b5e", fallback = "Adınız"),
                    enabled = address != null && !isLoading
                )

                AddressEditTextField(
                    value = surname,
                    onValueChange = { surname = it },
                    label = BBLocalization.Current.Get(key = "43b07485-278d-4633-9404-bf6a30a28222", fallback = ""),
                    placeholder = BBLocalization.Current.Get(key = "d3e38a79-6cd1-415b-89d2-2893719c54e8", fallback = "Soyadınız"),
                    enabled = address != null && !isLoading
                )

                AddressEditTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = BBLocalization.Current.Get(key = "cf948c6a-2e6a-4f1e-b77b-13f8d15a1a67", fallback = "Telefon"),
                    placeholder = "5xx xxx xx xx",
                    keyboardType = KeyboardType.Phone,
                    enabled = address != null && !isLoading
                )

                AddressEditTextField(
                    value = postCode,
                    onValueChange = { postCode = it },
                    label = BBLocalization.Current.Get(key = "fff66b6e-cf51-4dde-a421-b8ce3df436d0", fallback = "Posta Kodu"),
                    placeholder = "Posta kodu",
                    keyboardType = KeyboardType.Number,
                    enabled = address != null && !isLoading
                )
            }

            AddressEditSection(
                title = "Konum Bilgileri",
                description = "Adresin ülke, bölge, şehir ve ilçe bilgilerini düzenleyin."
            ) {
                AddressCascadeFields(
                    state = addressCascadeState,
                    onCountrySelected = onCountrySelected,
                    onCountryStateSelected = onCountryStateSelected,
                    onCountryDepartmentSelected = onCountryDepartmentSelected,
                    onCitySelected = onCitySelected,
                    onDistrictSelected = onDistrictSelected,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = address != null && !isLoading
                )
            }

            AddressEditSection(
                title = "Adres Detayı",
                description = "Açık adresi ve adres başlığını düzenleyin."
            ) {
                AddressEditTextArea(
                    value = addressText,
                    onValueChange = { addressText = it },
                    label = "Açık Adres",
                    placeholder = "Mahalle, cadde, sokak, bina, kat ve daire bilgisi",
                    enabled = address != null && !isLoading
                )

                AddressEditTextField(
                    value = addressTitle,
                    onValueChange = { addressTitle = it },
                    label = BBLocalization.Current.Get(key = "a9620a09-6461-4898-9b6f-9b4116bb1594", fallback = "Adres Başlığı"),
                    placeholder = "Ev Adresim",
                    enabled = address != null && !isLoading
                )

                AddressEditDefaultField(
                    checked = isDefault,
                    onCheckedChange = { isDefault = it },
                    enabled = address != null && !isLoading
                )
            }

            if (!errorMessage.isNullOrBlank()) {
                AddressEditError(message = errorMessage)
            }

            BbButton(
                text = BBLocalization.Current.Get(key = "b373accc-fed2-49b0-bf96-1c3aee8511a9", fallback = "Adresi Güncelle"),
                onClick = {
                    val currentAddress = address

                    if (currentAddress != null) {
                        onSaveClick(
                            currentAddress.copy(
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
                    }
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
private fun AddressEditSection(
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
private fun AddressEditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
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
private fun AddressEditTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    enabled: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
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
private fun AddressEditDefaultField(
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
                    text = BBLocalization.Current.Get(key = "7fac1179-ab8e-4bb8-9ca0-92369db1597e", fallback = "Varsayılan Adres"),
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
private fun AddressEditError(message: String) {
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