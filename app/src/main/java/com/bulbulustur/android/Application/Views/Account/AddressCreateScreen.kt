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
                title = BBLocalization.Current.Get(key = "1d0cb70f-85fe-425d-b8b8-08399ee986c3", fallback = "Alıcı Bilgileri"),
                description = BBLocalization.Current.Get(key = "38877473-61f3-4524-8a16-e1569a151155", fallback = "Teslimatı alacak kişinin bilgilerini girin.")
            ) {
                AddressCreateTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = BBLocalization.Current.Get(key = "9b9cf3d0-0463-4f28-83de-c750f16963e4", fallback = "Ad"),
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
                    label = BBLocalization.Current.Get(key = "074c5091-b376-4dc7-9898-b77fddaae9d8", fallback = "Posta Kodu"),
                    placeholder = "Posta kodu",
                    keyboardType = KeyboardType.Number
                )
            }

            AddressCreateSection(
                title = BBLocalization.Current.Get(key = "73a67a8a-8c74-40a7-ab6e-09100c4918f0", fallback = "Konum Bilgileri"),
                description = BBLocalization.Current.Get(key = "b18f476f-2281-4cfc-8198-3d0edc3ed1f6", fallback = "Adresin ülke, bölge, şehir ve ilçe bilgilerini seçin.")
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
                title = BBLocalization.Current.Get(key = "6ec6d9c7-1640-4941-a059-742f80d10a5a", fallback = "Adres Detayı"),
                description = BBLocalization.Current.Get(key = "968fee6c-8498-4f79-8dea-dfc5bee7ec9e", fallback = "Açık adresi ve adres başlığını girin.")
            ) {
                AddressCreateTextArea(
                    value = addressText,
                    onValueChange = { addressText = it },
                    label = BBLocalization.Current.Get(key = "7dd4d1d8-64bd-448a-848e-8bfadd0553aa", fallback = "Açık Adres"),
                    placeholder = BBLocalization.Current.Get(key = "0f207740-61e2-47e1-bca3-90bcb3926066", fallback = "Mahalle, cadde, sokak, bina, kat ve daire bilgisi")
                )

                AddressCreateTextField(
                    value = addressTitle,
                    onValueChange = { addressTitle = it },
                    label = "Adres Başlığı",
                    placeholder = BBLocalization.Current.Get(key = "8d02a590-e33e-4798-adea-4794c5a4a590", fallback = "Ev Adresim")
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
                text = BBLocalization.Current.Get(key = "858ae44b-ea3e-4b73-a73f-28158cb354fa", fallback = "Adresi Kaydet"),
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
                    text = BBLocalization.Current.Get(key = "40e684d5-e4ec-4e72-ac8d-293fce44664f", fallback = "Siparişlerde öncelikli olarak bu adres kullanılır."),
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