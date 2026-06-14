package com.bulbulustur.android.Features.account.address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbInnerPageHeader
import com.bulbulustur.android.Ui.components.form.BbFormSection
import com.bulbulustur.android.Ui.components.form.BbSelectInput
import com.bulbulustur.android.Ui.components.form.BbSelectOption
import com.bulbulustur.android.Ui.components.form.BbSwitchRow
import com.bulbulustur.android.Ui.components.form.BbTextarea
import com.bulbulustur.android.Ui.components.form.BbTextInput
import com.bulbulustur.android.Ui.theme.BbSpacing

data class AddressCreateFormState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val postalCode: String = "",
    val countryName: String = "Türkiye",
    val cityName: String = "",
    val districtName: String = "",
    val addressLine: String = "",
    val addressTitle: String = "",
    val isDefaultAddress: Boolean = false,
    val validationMessage: String? = null
) {
    val canSubmit: Boolean
        get() {
            return firstName.isNotBlank() &&
                    lastName.isNotBlank() &&
                    phoneNumber.isNotBlank() &&
                    postalCode.isNotBlank() &&
                    countryName.isNotBlank() &&
                    cityName.isNotBlank() &&
                    districtName.isNotBlank() &&
                    addressLine.isNotBlank() &&
                    addressTitle.isNotBlank()
        }
}

@Composable
fun AddressCreateScreen(
    onBackClick: () -> Unit = {},
    onAddressCreateClick: (AddressCreateFormState) -> Unit = {},
    isSubmitting: Boolean = false
) {
    val formState = remember {
        mutableStateOf(AddressCreateFormState())
    }

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
                        start = BbSpacing.PageHorizontal,
                        top = BbSpacing.PageTopCompact,
                        end = BbSpacing.PageHorizontal,
                        bottom = BbSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGap)
        ) {
            AddressCreateIntroCard()

            BbFormSection(
                title = "Teslimat Adresi"
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
                ) {
                    Text(
                        text = "Alıcı bilgileri, adres detayı ve konum bilgisini bu formdan yönetebilirsin.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    AddressCreateReceiverFields(
                        formState = formState.value,
                        onStateChange = { changedFormState ->
                            formState.value = changedFormState
                        }
                    )

                    AddressCreateLocationFields(
                        formState = formState.value,
                        onStateChange = { changedFormState ->
                            formState.value = changedFormState
                        }
                    )

                    AddressCreateDetailFields(
                        formState = formState.value,
                        onStateChange = { changedFormState ->
                            formState.value = changedFormState
                        }
                    )

                    AddressCreateInfoCard()

                    if (formState.value.validationMessage != null) {
                        Text(
                            text = formState.value.validationMessage.orEmpty(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    BbButton(
                        text = "Kaydet",
                        onClick = {
                            if (formState.value.canSubmit) {
                                onAddressCreateClick(formState.value)
                            } else {
                                formState.value = formState.value.copy(
                                    validationMessage = getAddressCreateValidationMessage(formState.value)
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Primary,
                        enabled = formState.value.canSubmit,
                        isLoading = isSubmitting
                    )
                }
            }
        }
    }
}

@Composable
private fun AddressCreateIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Siparişlerinde kullanmak üzere yeni bir teslimat adresi oluştur. Teslimatın doğru ilerlemesi için adres bilgilerini eksiksiz gir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AddressCreateReceiverFields(
    formState: AddressCreateFormState,
    onStateChange: (AddressCreateFormState) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        Text(
            text = "Alıcı Bilgileri",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Teslimatı alacak kişiye ait temel bilgileri gir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        BbTextInput(
            value = formState.firstName,
            onValueChange = { firstName ->
                onStateChange(
                    formState.copy(
                        firstName = firstName,
                        validationMessage = null
                    )
                )
            },
            label = "Adınız",
            placeholder = "Ad"
        )

        BbTextInput(
            value = formState.lastName,
            onValueChange = { lastName ->
                onStateChange(
                    formState.copy(
                        lastName = lastName,
                        validationMessage = null
                    )
                )
            },
            label = "Soyadınız",
            placeholder = "Soyad"
        )

        BbTextInput(
            value = formState.phoneNumber,
            onValueChange = { phoneNumber ->
                onStateChange(
                    formState.copy(
                        phoneNumber = phoneNumber,
                        validationMessage = null
                    )
                )
            },
            label = "Telefon",
            placeholder = "05xx xxx xx xx"
        )

        BbTextInput(
            value = formState.postalCode,
            onValueChange = { postalCode ->
                onStateChange(
                    formState.copy(
                        postalCode = postalCode,
                        validationMessage = null
                    )
                )
            },
            label = "Posta Kodu",
            placeholder = "Posta kodu"
        )
    }
}

@Composable
private fun AddressCreateLocationFields(
    formState: AddressCreateFormState,
    onStateChange: (AddressCreateFormState) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        Text(
            text = "Konum Bilgileri",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Ülke, şehir ve ilçe bilgisini seç.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        BbSelectInput(
            selectedValue = formState.countryName,
            onValueChange = { countryName ->
                onStateChange(
                    formState.copy(
                        countryName = countryName,
                        validationMessage = null
                    )
                )
            },
            label = "Ülke",
            placeholder = "Ülke seçiniz",
            options = addressCountryOptions()
        )

        BbSelectInput(
            selectedValue = formState.cityName,
            onValueChange = { cityName ->
                onStateChange(
                    formState.copy(
                        cityName = cityName,
                        districtName = "",
                        validationMessage = null
                    )
                )
            },
            label = "Şehir",
            placeholder = "Şehir seçiniz",
            options = addressCityOptions()
        )

        BbSelectInput(
            selectedValue = formState.districtName,
            onValueChange = { districtName ->
                onStateChange(
                    formState.copy(
                        districtName = districtName,
                        validationMessage = null
                    )
                )
            },
            label = "İlçe",
            placeholder = "İlçe seçiniz",
            options = addressDistrictOptions()
        )
    }
}

@Composable
private fun AddressCreateDetailFields(
    formState: AddressCreateFormState,
    onStateChange: (AddressCreateFormState) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
    ) {
        Text(
            text = "Adres Detayı",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Açık adresinizi şehir ve ilçe bilgisi tekrar etmeden girin.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        BbTextarea(
            value = formState.addressLine,
            onValueChange = { addressLine ->
                onStateChange(
                    formState.copy(
                        addressLine = addressLine,
                        validationMessage = null
                    )
                )
            },
            label = "Açık Adresiniz",
            placeholder = "Mahalle, cadde, sokak, bina no, kat, daire gibi bilgileri yazın"
        )

        BbTextInput(
            value = formState.addressTitle,
            onValueChange = { addressTitle ->
                onStateChange(
                    formState.copy(
                        addressTitle = addressTitle,
                        validationMessage = null
                    )
                )
            },
            label = "Adres Başlığı",
            placeholder = "Ev adresim, iş adresim gibi"
        )

        BbSwitchRow(
            checked = formState.isDefaultAddress,
            onCheckedChange = { isDefaultAddress ->
                onStateChange(
                    formState.copy(
                        isDefaultAddress = isDefaultAddress
                    )
                )
            },
            title = "Varsayılan Adresim Olarak Kaydet",
            description = "Siparişlerinde bu adres ilk seçenek olarak kullanılabilir."
        )
    }
}

@Composable
private fun AddressCreateInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Adres bilgileriniz, siparişlerinizin teslimatının sağlanması amacıyla işlenir. Detaylar için gizlilik politikasını inceleyebilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun getAddressCreateValidationMessage(
    formState: AddressCreateFormState
): String {
    if (formState.firstName.isBlank()) {
        return "Ad alanı gereklidir."
    }

    if (formState.lastName.isBlank()) {
        return "Soyad alanı gereklidir."
    }

    if (formState.phoneNumber.isBlank()) {
        return "Telefon alanı gereklidir."
    }

    if (formState.postalCode.isBlank()) {
        return "Posta kodu alanı gereklidir."
    }

    if (formState.countryName.isBlank()) {
        return "Ülke seçilmelidir."
    }

    if (formState.cityName.isBlank()) {
        return "Şehir seçilmelidir."
    }

    if (formState.districtName.isBlank()) {
        return "İlçe seçilmelidir."
    }

    if (formState.addressLine.isBlank()) {
        return "Açık adres alanı gereklidir."
    }

    if (formState.addressTitle.isBlank()) {
        return "Adres başlığı gereklidir."
    }

    return "Adres bilgilerini kontrol etmelisin."
}

private fun addressCountryOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("Türkiye", "Türkiye"),
        BbSelectOption("Almanya", "Almanya"),
        BbSelectOption("Amerika Birleşik Devletleri", "Amerika Birleşik Devletleri"),
        BbSelectOption("Çin", "Çin"),
        BbSelectOption("Birleşik Krallık", "Birleşik Krallık")
    )
}

private fun addressCityOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("İstanbul", "İstanbul"),
        BbSelectOption("Ankara", "Ankara"),
        BbSelectOption("İzmir", "İzmir"),
        BbSelectOption("Bursa", "Bursa"),
        BbSelectOption("Kayseri", "Kayseri"),
        BbSelectOption("Konya", "Konya")
    )
}

private fun addressDistrictOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("Şişli", "Şişli"),
        BbSelectOption("Kadıköy", "Kadıköy"),
        BbSelectOption("Üsküdar", "Üsküdar"),
        BbSelectOption("Çankaya", "Çankaya"),
        BbSelectOption("Nilüfer", "Nilüfer"),
        BbSelectOption("Melikgazi", "Melikgazi")
    )
}