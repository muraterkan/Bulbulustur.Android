package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography

@Composable
fun AddressFormScreen(
    addressId: Int? = null,
    isLoading: Boolean = false,
    onBackClick: () -> Unit = {},
    onSaveClick: (AddressFormSubmitModel) -> Unit = {}
) {
    val isEditMode = addressId != null

    val nameState = remember {
        mutableStateOf(if (isEditMode) "Murat" else "")
    }

    val surnameState = remember {
        mutableStateOf(if (isEditMode) "Erkan" else "")
    }

    val phoneState = remember {
        mutableStateOf(if (isEditMode) "5557106417" else "")
    }

    val postalCodeState = remember {
        mutableStateOf(if (isEditMode) "34394" else "")
    }

    val countryState = remember {
        mutableStateOf("Türkiye")
    }

    val cityState = remember {
        mutableStateOf(if (isEditMode) "Ä°stanbul" else "")
    }

    val districtState = remember {
        mutableStateOf(if (isEditMode) "Åişli" else "")
    }

    val addressTextState = remember {
        mutableStateOf(if (isEditMode) "Fulya mah. Aytekinkotil cad. No: 11/1" else "")
    }

    val addressTitleState = remember {
        mutableStateOf(if (isEditMode) "Ev Adresim" else "")
    }

    val defaultAddressState = remember {
        mutableStateOf(isEditMode)
    }

    val validationState = remember(
        nameState.value,
        surnameState.value,
        phoneState.value,
        cityState.value,
        districtState.value,
        addressTextState.value,
        addressTitleState.value
    ) {
        derivedStateOf {
            validateAddressForm(
                name = nameState.value,
                surname = surnameState.value,
                phone = phoneState.value,
                city = cityState.value,
                district = districtState.value,
                addressText = addressTextState.value,
                addressTitle = addressTitleState.value
            )
        }
    }

    val canSubmit = validationState.value.canSubmit && !isLoading

    val pageTitle = if (isEditMode) {
        "Adresi Düzenle"
    } else {
        "Yeni Adres Ekle"
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = pageTitle,
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
            AddressFormIntroCard()

            AddressFormSectionCard(
                title = "Alıcı Bilgileri",
                description = "Teslimat alıcısı için ad, soyad ve iletişim bilgilerini girin."
            ) {
                BbAddressTextField(
                    value = nameState.value,
                    onValueChange = { value ->
                        nameState.value = value
                    },
                    label = "Adınız",
                    placeholder = "Ad"
                )

                BbAddressTextField(
                    value = surnameState.value,
                    onValueChange = { value ->
                        surnameState.value = value
                    },
                    label = "Soyadınız",
                    placeholder = "Soyad"
                )

                BbAddressTextField(
                    value = phoneState.value,
                    onValueChange = { value ->
                        phoneState.value = value
                    },
                    label = "Telefon",
                    placeholder = "5xx xxx xx xx",
                    keyboardType = KeyboardType.Phone
                )

                BbAddressTextField(
                    value = postalCodeState.value,
                    onValueChange = { value ->
                        postalCodeState.value = value
                    },
                    label = "Posta Kodu",
                    placeholder = "Posta kodu",
                    keyboardType = KeyboardType.Number
                )
            }

            AddressFormSectionCard(
                title = "Konum Bilgileri",
                description = "Ülke, şehir ve ilçe bilgilerini seçin."
            ) {
                BbAddressTextField(
                    value = countryState.value,
                    onValueChange = { value ->
                        countryState.value = value
                    },
                    label = "Ülke",
                    placeholder = "Ülke"
                )

                BbAddressTextField(
                    value = cityState.value,
                    onValueChange = { value ->
                        cityState.value = value
                    },
                    label = "Åehir",
                    placeholder = "Åehir seçiniz"
                )

                BbAddressTextField(
                    value = districtState.value,
                    onValueChange = { value ->
                        districtState.value = value
                    },
                    label = "Ä°lçe",
                    placeholder = "Ä°lçe seçiniz"
                )
            }

            AddressFormSectionCard(
                title = "Adres Detayı",
                description = "Açık adresinizi ve bu adres için kullanacaĞınız başlıĞı yazın."
            ) {
                BbAddressTextArea(
                    value = addressTextState.value,
                    onValueChange = { value ->
                        addressTextState.value = value
                    },
                    label = "Açık Adresiniz",
                    placeholder = "Mahalle, cadde, sokak, bina, kat ve daire bilgisi"
                )

                BbAddressTextField(
                    value = addressTitleState.value,
                    onValueChange = { value ->
                        addressTitleState.value = value
                    },
                    label = "Bu Adrese Bir Başlık Verin",
                    placeholder = "Ev Adresim"
                )

                DefaultAddressBox(
                    checked = defaultAddressState.value,
                    onCheckedChange = { value ->
                        defaultAddressState.value = value
                    }
                )
            }

            AddressFormInfoBox(
                validation = validationState.value
            )

            BbButton(
                text = if (isEditMode) "Adresi Güncelle" else "Adresi Kaydet",
                onClick = {
                    onSaveClick(
                        AddressFormSubmitModel(
                            addressId = addressId,
                            name = nameState.value,
                            surname = surnameState.value,
                            phone = phoneState.value,
                            postalCode = postalCodeState.value,
                            country = countryState.value,
                            city = cityState.value,
                            district = districtState.value,
                            addressText = addressTextState.value,
                            addressTitle = addressTitleState.value,
                            isDefault = defaultAddressState.value
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
private fun AddressFormIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Siparişlerinizde kullanmak üzere teslimat adresi oluşturun. Teslimatın doĞru ilerlemesi için adres bilgilerini eksiksiz girin.",
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AddressFormSectionCard(
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
            SectionHeaderBox(
                title = title,
                description = description
            )

            content()
        }
    }
}

@Composable
private fun SectionHeaderBox(
    title: String,
    description: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BBColors.Yellow.Yellow50,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = BBColors.TextStrong
            )

            Text(
                text = description,
                style = BbTypography.bodySmall,
                color = BBColors.TextMuted
            )
        }
    }
}

@Composable
private fun BbAddressTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
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
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        textStyle = BbTypography.bodyMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BBColors.Surface,
            unfocusedContainerColor = BBColors.Surface,
            disabledContainerColor = BBColors.SurfaceMuted,
            focusedIndicatorColor = BBColors.Primary,
            unfocusedIndicatorColor = BBColors.Border,
            disabledIndicatorColor = BBColors.Border,
            errorIndicatorColor = BBColors.Red.Red500,
            focusedLabelColor = BBColors.Primary,
            unfocusedLabelColor = BBColors.TextMuted,
            disabledLabelColor = BBColors.TextMuted,
            errorLabelColor = BBColors.Red.Red600,
            focusedTextColor = BBColors.TextStrong,
            unfocusedTextColor = BBColors.TextStrong,
            disabledTextColor = BBColors.TextMuted,
            focusedPlaceholderColor = BBColors.TextMuted,
            unfocusedPlaceholderColor = BBColors.TextMuted,
            disabledPlaceholderColor = BBColors.TextMuted,
            cursorColor = BBColors.Primary
        )
    )
}

@Composable
private fun BbAddressTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
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
            focusedContainerColor = BBColors.Surface,
            unfocusedContainerColor = BBColors.Surface,
            disabledContainerColor = BBColors.SurfaceMuted,
            focusedIndicatorColor = BBColors.Primary,
            unfocusedIndicatorColor = BBColors.Border,
            disabledIndicatorColor = BBColors.Border,
            errorIndicatorColor = BBColors.Red.Red500,
            focusedLabelColor = BBColors.Primary,
            unfocusedLabelColor = BBColors.TextMuted,
            disabledLabelColor = BBColors.TextMuted,
            errorLabelColor = BBColors.Red.Red600,
            focusedTextColor = BBColors.TextStrong,
            unfocusedTextColor = BBColors.TextStrong,
            disabledTextColor = BBColors.TextMuted,
            focusedPlaceholderColor = BBColors.TextMuted,
            unfocusedPlaceholderColor = BBColors.TextMuted,
            disabledPlaceholderColor = BBColors.TextMuted,
            cursorColor = BBColors.Primary
        )
    )
}

@Composable
private fun DefaultAddressBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BBColors.SurfaceMuted,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { value ->
                    onCheckedChange(value)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = BBColors.Primary,
                    uncheckedColor = BBColors.BorderStrong,
                    checkmarkColor = BBColors.TextStrong
                )
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Varsayılan Adresim Olarak Kaydet",
                    style = BbTypography.labelLarge,
                    color = BBColors.TextStrong
                )

                Text(
                    text = "Siparişlerde bu adres öncelikli olarak kullanılabilir.",
                    style = BbTypography.bodySmall,
                    color = BBColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun AddressFormInfoBox(
    validation: AddressFormValidationState
) {
    val backgroundColor = if (validation.canSubmit) {
        BBColors.Green.Green50
    } else {
        BBColors.Yellow.Yellow50
    }

    val titleColor = if (validation.canSubmit) {
        BBColors.Green.Green700
    } else {
        BBColors.Yellow.Yellow800
    }

    val title = if (validation.canSubmit) {
        "Adres Bilgileri Uygun Görünüyor"
    } else {
        "Zorunlu Adres Bilgilerini Tamamlayın"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.labelLarge,
                color = titleColor
            )

            Text(
                text = "Bu bilgiler sipariş teslimatı ve fatura süreçlerinde kullanılacaktır.",
                style = BbTypography.bodySmall,
                color = BBColors.TextMuted
            )
        }
    }
}

private fun validateAddressForm(
    name: String,
    surname: String,
    phone: String,
    city: String,
    district: String,
    addressText: String,
    addressTitle: String
): AddressFormValidationState {
    return AddressFormValidationState(
        hasName = name.isNotBlank(),
        hasSurname = surname.isNotBlank(),
        hasPhone = phone.trim().length >= 10,
        hasCity = city.isNotBlank(),
        hasDistrict = district.isNotBlank(),
        hasAddressText = addressText.trim().length >= 10,
        hasAddressTitle = addressTitle.isNotBlank()
    )
}

data class AddressFormSubmitModel(
    val addressId: Int?,
    val name: String,
    val surname: String,
    val phone: String,
    val postalCode: String,
    val country: String,
    val city: String,
    val district: String,
    val addressText: String,
    val addressTitle: String,
    val isDefault: Boolean
)

private data class AddressFormValidationState(
    val hasName: Boolean,
    val hasSurname: Boolean,
    val hasPhone: Boolean,
    val hasCity: Boolean,
    val hasDistrict: Boolean,
    val hasAddressText: Boolean,
    val hasAddressTitle: Boolean
) {
    val canSubmit: Boolean
        get() {
            return hasName &&
                    hasSurname &&
                    hasPhone &&
                    hasCity &&
                    hasDistrict &&
                    hasAddressText &&
                    hasAddressTitle
        }
}


