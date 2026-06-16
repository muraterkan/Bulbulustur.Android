package com.bulbulustur.android.Views.Account

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
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.components.BbInnerPageHeader
import com.bulbulustur.android.wwwroot.components.form.BbFormSection
import com.bulbulustur.android.wwwroot.components.form.BbSelectInput
import com.bulbulustur.android.wwwroot.components.form.BbSelectOption
import com.bulbulustur.android.wwwroot.components.form.BbSwitchRow
import com.bulbulustur.android.wwwroot.components.form.BbTextarea
import com.bulbulustur.android.wwwroot.components.form.BbTextInput
import com.bulbulustur.android.wwwroot.theme.BbSpacing

data class AddressCreateFormState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val postalCode: String = "",
    val countryName: String = "TÃ¼rkiye",
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
                        text = "AlÄ±cÄ± bilgileri, adres detayÄ± ve konum bilgisini bu formdan yÃ¶netebilirsin.",
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
            text = "SipariÅŸlerinde kullanmak Ã¼zere yeni bir teslimat adresi oluÅŸtur. TeslimatÄ±n doÄŸru ilerlemesi iÃ§in adres bilgilerini eksiksiz gir.",
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
            text = "AlÄ±cÄ± Bilgileri",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "TeslimatÄ± alacak kiÅŸiye ait temel bilgileri gir.",
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
            label = "AdÄ±nÄ±z",
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
            label = "SoyadÄ±nÄ±z",
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
            text = "Ãœlke, ÅŸehir ve ilÃ§e bilgisini seÃ§.",
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
            label = "Ãœlke",
            placeholder = "Ãœlke seÃ§iniz",
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
            label = "Åehir",
            placeholder = "Åehir seÃ§iniz",
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
            label = "Ä°lÃ§e",
            placeholder = "Ä°lÃ§e seÃ§iniz",
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
            text = "Adres DetayÄ±",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "AÃ§Ä±k adresinizi ÅŸehir ve ilÃ§e bilgisi tekrar etmeden girin.",
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
            label = "AÃ§Ä±k Adresiniz",
            placeholder = "Mahalle, cadde, sokak, bina no, kat, daire gibi bilgileri yazÄ±n"
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
            label = "Adres BaÅŸlÄ±ÄŸÄ±",
            placeholder = "Ev adresim, iÅŸ adresim gibi"
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
            title = "VarsayÄ±lan Adresim Olarak Kaydet",
            description = "SipariÅŸlerinde bu adres ilk seÃ§enek olarak kullanÄ±labilir."
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
            text = "Adres bilgileriniz, sipariÅŸlerinizin teslimatÄ±nÄ±n saÄŸlanmasÄ± amacÄ±yla iÅŸlenir. Detaylar iÃ§in gizlilik politikasÄ±nÄ± inceleyebilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun getAddressCreateValidationMessage(
    formState: AddressCreateFormState
): String {
    if (formState.firstName.isBlank()) {
        return "Ad alanÄ± gereklidir."
    }

    if (formState.lastName.isBlank()) {
        return "Soyad alanÄ± gereklidir."
    }

    if (formState.phoneNumber.isBlank()) {
        return "Telefon alanÄ± gereklidir."
    }

    if (formState.postalCode.isBlank()) {
        return "Posta kodu alanÄ± gereklidir."
    }

    if (formState.countryName.isBlank()) {
        return "Ãœlke seÃ§ilmelidir."
    }

    if (formState.cityName.isBlank()) {
        return "Åehir seÃ§ilmelidir."
    }

    if (formState.districtName.isBlank()) {
        return "Ä°lÃ§e seÃ§ilmelidir."
    }

    if (formState.addressLine.isBlank()) {
        return "AÃ§Ä±k adres alanÄ± gereklidir."
    }

    if (formState.addressTitle.isBlank()) {
        return "Adres baÅŸlÄ±ÄŸÄ± gereklidir."
    }

    return "Adres bilgilerini kontrol etmelisin."
}

private fun addressCountryOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("TÃ¼rkiye", "TÃ¼rkiye"),
        BbSelectOption("Almanya", "Almanya"),
        BbSelectOption("Amerika BirleÅŸik Devletleri", "Amerika BirleÅŸik Devletleri"),
        BbSelectOption("Ã‡in", "Ã‡in"),
        BbSelectOption("BirleÅŸik KrallÄ±k", "BirleÅŸik KrallÄ±k")
    )
}

private fun addressCityOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("Ä°stanbul", "Ä°stanbul"),
        BbSelectOption("Ankara", "Ankara"),
        BbSelectOption("Ä°zmir", "Ä°zmir"),
        BbSelectOption("Bursa", "Bursa"),
        BbSelectOption("Kayseri", "Kayseri"),
        BbSelectOption("Konya", "Konya")
    )
}

private fun addressDistrictOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("ÅiÅŸli", "ÅiÅŸli"),
        BbSelectOption("KadÄ±kÃ¶y", "KadÄ±kÃ¶y"),
        BbSelectOption("ÃœskÃ¼dar", "ÃœskÃ¼dar"),
        BbSelectOption("Ã‡ankaya", "Ã‡ankaya"),
        BbSelectOption("NilÃ¼fer", "NilÃ¼fer"),
        BbSelectOption("Melikgazi", "Melikgazi")
    )
}
