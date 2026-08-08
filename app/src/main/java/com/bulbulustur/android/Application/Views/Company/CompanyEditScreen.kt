package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbFormSection
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectOption
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextarea
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextInput
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

data class CompanyEditFormState(
    val companyId: Int? = null,
    val companyOfficialName: String = "",
    val companyType: String = "",
    val establishmentYear: String = "",
    val companyEmailAddress: String = "",
    val companyWebsiteUrl: String = "",
    val companyAddress: String = "",
    val countryName: String = "",
    val cityName: String = "",
    val districtName: String = "",
    val postalCode: String = "",
    val taxOffice: String = "",
    val taxNumber: String = "",
    val mersisNumber: String = "",
    val kepAddress: String = "",
    val validationMessage: String? = null
) {
    val hasValidEmailFormat: Boolean
        get() {
            if (companyEmailAddress.isBlank()) {
                return false
            }

            return companyEmailAddress.contains("@") &&
                    companyEmailAddress.contains(".") &&
                    companyEmailAddress.length >= 6
        }

    val canSubmit: Boolean
        get() {
            return companyOfficialName.isNotBlank() &&
                    companyType.isNotBlank() &&
                    establishmentYear.isNotBlank() &&
                    hasValidEmailFormat &&
                    companyWebsiteUrl.isNotBlank() &&
                    companyAddress.isNotBlank() &&
                    countryName.isNotBlank() &&
                    cityName.isNotBlank() &&
                    districtName.isNotBlank() &&
                    taxOffice.isNotBlank() &&
                    taxNumber.isNotBlank()
        }
}

@Composable
fun CompanyEditScreen(
    initialCompanyEditFormState: CompanyEditFormState = createSampleCompanyEditFormState(),
    onBackClick: () -> Unit = {},
    onCompanyUpdateClick: (CompanyEditFormState) -> Unit = {},
    isSubmitting: Boolean = false
) {
    val formState = remember(initialCompanyEditFormState.companyId) {
        mutableStateOf(initialCompanyEditFormState)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = BBSpacing.PageHorizontal,
                vertical = BBSpacing.PageTop
            ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
    ) {
        BbButton(
            text = BBLocalization.Current.Get(key = "5d3c17c2-d063-4757-9940-62331a540e23", fallback = "Şirket Bilgilerime Dön"),
            onClick = onBackClick,
            variant = BbButtonVariant.Outline
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            BbChip(
                text = BBLocalization.Current.Get(key = "a779cbdb-80a3-469b-aff2-f71e76a661e7", fallback = "Şirket Profili")
            )

            Text(
                text = BBLocalization.Current.Get(key = "3ec5dfa9-46c0-40ea-adf8-fd66e633b153", fallback = ""),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "ecc14023-049f-4411-9d89-190d8ba505ee", fallback = "Şirket hesabınla ilişkili temel kurumsal, adres ve resmi bilgileri güncelle."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        BbFormSection(
            title = BBLocalization.Current.Get(key = "20c6eae9-995c-4a7e-a692-2c6fffac3fa9", fallback = "Kurumsal bilgiler")
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "4264e5f5-cd35-48f8-a204-eb373edd506e", fallback = "Bu bilgiler şirket profilin, resmi kayıtların ve platform üzerindeki kurumsal işlemlerin için kullanılır."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbTextInput(
                    value = formState.value.companyOfficialName,
                    onValueChange = { companyOfficialName ->
                        formState.value = formState.value.copy(
                            companyOfficialName = companyOfficialName,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "1da8884c-e11c-469d-99f2-a02e4bc01b15", fallback = ""),
                    placeholder = BBLocalization.Current.Get(key = "1da8884c-e11c-469d-99f2-a02e4bc01b15", fallback = "")
                )

                BbSelectInput(
                    selectedValue = formState.value.companyType,
                    onValueChange = { companyType ->
                        formState.value = formState.value.copy(
                            companyType = companyType,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "9a39b2de-3986-4850-91ce-fd81d21062e8", fallback = "Şirketinizin Tipi"),
                    placeholder = BBLocalization.Current.Get(key = "ce4087e5-47b4-4b6a-a853-d02fc65dfe4f", fallback = "Şirket tipi seç"),
                    options = companyTypeOptions()
                )

                BbSelectInput(
                    selectedValue = formState.value.establishmentYear,
                    onValueChange = { establishmentYear ->
                        formState.value = formState.value.copy(
                            establishmentYear = establishmentYear,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "2439777a-0431-4929-9600-07df5586ad67", fallback = ""),
                    placeholder = BBLocalization.Current.Get(key = "1bbc4d05-7a6e-4042-bdcc-9db1dab3dd1f", fallback = "Yıl seç"),
                    options = establishmentYearOptions()
                )

                BbTextInput(
                    value = formState.value.companyEmailAddress,
                    onValueChange = { companyEmailAddress ->
                        formState.value = formState.value.copy(
                            companyEmailAddress = companyEmailAddress,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "df1e83e4-fd95-41ce-b4a6-d2bbb1300e2a", fallback = "Şirket E-postası"),
                    placeholder = "ornek@firma.com"
                )

                BbTextInput(
                    value = formState.value.companyWebsiteUrl,
                    onValueChange = { companyWebsiteUrl ->
                        formState.value = formState.value.copy(
                            companyWebsiteUrl = companyWebsiteUrl,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "42dedc34-458a-4284-89d4-a4950dff356a", fallback = ""),
                    placeholder = "www.firma.com"
                )
            }
        }

        BbFormSection(
            title = BBLocalization.Current.Get(key = "0c1f27ba-139d-42ae-8682-ad08ddcc96a5", fallback = "Adres ve Konum")
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "04abd2a4-3bde-4fb7-8bac-f42d94c31ef9", fallback = "Şirket kayıtlı adres ve lokasyon bilgilerini düzenle."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbTextarea(
                    value = formState.value.companyAddress,
                    onValueChange = { companyAddress ->
                        formState.value = formState.value.copy(
                            companyAddress = companyAddress,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "e8ec1405-952b-4a9e-bd42-72133f29c4ad", fallback = "Şirketinizin Adresi"),
                    placeholder = BBLocalization.Current.Get(key = "3aba46b3-4286-4e64-8a75-1bd8a06091c2", fallback = "Açık şirket adresi")
                )

                BbSelectInput(
                    selectedValue = formState.value.countryName,
                    onValueChange = { countryName ->
                        formState.value = formState.value.copy(
                            countryName = countryName,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "8b04cc2a-5e86-4d4e-bf8c-7dc7bf1be325", fallback = "Ülke"),
                    placeholder = BBLocalization.Current.Get(key = "b12d8b12-9029-4c5a-946c-3a5a7c5652d4", fallback = "Ülke seç"),
                    options = countryOptions()
                )

                BbSelectInput(
                    selectedValue = formState.value.cityName,
                    onValueChange = { cityName ->
                        formState.value = formState.value.copy(
                            cityName = cityName,
                            districtName = "",
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "a4936d53-1fc1-4e87-a255-2a4906748a61", fallback = "Şehir"),
                    placeholder = BBLocalization.Current.Get(key = "a4936d53-1fc1-4e87-a255-2a4906748a61", fallback = "Şehir seç"),
                    options = cityOptions()
                )

                BbSelectInput(
                    selectedValue = formState.value.districtName,
                    onValueChange = { districtName ->
                        formState.value = formState.value.copy(
                            districtName = districtName,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "843fedae-4923-4542-9341-9832b4a5f773", fallback = "İlçe"),
                    placeholder = BBLocalization.Current.Get(key = "a9ead615-a1eb-41b9-bcad-4af8ced15696", fallback = "İlçe seç"),
                    options = districtOptions()
                )

                BbTextInput(
                    value = formState.value.postalCode,
                    onValueChange = { postalCode ->
                        formState.value = formState.value.copy(
                            postalCode = postalCode
                        )
                    },
                    label = BBLocalization.Current.Get(key = "fff66b6e-cf51-4dde-a421-b8ce3df436d0", fallback = "Posta Kodu"),
                    placeholder = "Posta kodu"
                )
            }
        }

        BbFormSection(
            title = BBLocalization.Current.Get(key = "1d19905f-6efa-400a-bd42-961f7d62889e", fallback = "Vergi ve Resmi Kayıtlar")
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "96cf2139-bf33-4b70-aad1-532184e2cefc", fallback = "Fatura, vergi ve resmi şirket kayıtları için kullanılan bilgileri düzenle."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbTextInput(
                    value = formState.value.taxOffice,
                    onValueChange = { taxOffice ->
                        formState.value = formState.value.copy(
                            taxOffice = taxOffice,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "8c42e65e-d7a4-4ff2-9dce-e4073d4dc335", fallback = "Vergi Dairesi"),
                    placeholder = BBLocalization.Current.Get(key = "8c42e65e-d7a4-4ff2-9dce-e4073d4dc335", fallback = "Vergi dairesi")
                )

                BbTextInput(
                    value = formState.value.taxNumber,
                    onValueChange = { taxNumber ->
                        formState.value = formState.value.copy(
                            taxNumber = taxNumber,
                            validationMessage = null
                        )
                    },
                    label = BBLocalization.Current.Get(key = "0f94c70f-fe11-4d18-8561-64d8499637df", fallback = "Vergi Numarası"),
                    placeholder = "Vergi numarası"
                )

                BbTextInput(
                    value = formState.value.mersisNumber,
                    onValueChange = { mersisNumber ->
                        formState.value = formState.value.copy(
                            mersisNumber = mersisNumber
                        )
                    },
                    label = BBLocalization.Current.Get(key = "cb3df3f8-c596-444a-b920-9240350f757d", fallback = "Mersis No"),
                    placeholder = BBLocalization.Current.Get(key = "fed2a554-44e4-45c0-8f18-5564cff3cf11", fallback = "Mersis numarası")
                )

                BbTextInput(
                    value = formState.value.kepAddress,
                    onValueChange = { kepAddress ->
                        formState.value = formState.value.copy(
                            kepAddress = kepAddress
                        )
                    },
                    label = BBLocalization.Current.Get(key = "4f33b03a-913c-4ea8-9640-a58bd2f68dac", fallback = "Kep Adresi"),
                    placeholder = BBLocalization.Current.Get(key = "7cfdeb0f-8d70-4332-93c3-966713d49645", fallback = "kep@firma.com")
                )

                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "95069312-9aba-4bd6-913f-785870045f82", fallback = "Şirket bilgilerin kurumsal profil, fatura ve platform doğrulama süreçlerinde kullanılabilir."),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (formState.value.validationMessage != null) {
                    Text(
                        text = formState.value.validationMessage.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                BbButton(
                    text = BBLocalization.Current.Get(key = "1bba90af-aa63-41f8-bd0d-b51c4477afd7", fallback = ""),
                    onClick = {
                        if (formState.value.canSubmit) {
                            onCompanyUpdateClick(formState.value)
                        } else {
                            formState.value = formState.value.copy(
                                validationMessage = getCompanyEditValidationMessage(formState.value)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    enabled = formState.value.canSubmit,
                    isLoading = isSubmitting
                )

                BbButton(
                    text = BBLocalization.Current.Get(key = "18a6f5c0-ab35-483d-8691-fad99e9680f2", fallback = "Vazgeç"),
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Outline,
                    enabled = !isSubmitting
                )
            }
        }
    }
}

private fun getCompanyEditValidationMessage(
    formState: CompanyEditFormState
): String {
    if (formState.companyOfficialName.isBlank()) {
        return BBLocalization.Current.Get(key = "3681e958-afbe-4ab5-9cac-064a1a454803", fallback = "Şirket resmi ünvanı gereklidir.")
    }

    if (formState.companyType.isBlank()) {
        return BBLocalization.Current.Get(key = "829c2222-6c66-4be6-a051-b01e0d999004", fallback = "Şirket tipi seçilmelidir.")
    }

    if (formState.establishmentYear.isBlank()) {
        return BBLocalization.Current.Get(key = "3e52f324-c532-446f-8305-45e59817f207", fallback = "Kuruluş yılı seçilmelidir.")
    }

    if (!formState.hasValidEmailFormat) {
        return BBLocalization.Current.Get(key = "85e18680-4a5c-4a53-9a45-f6d6990b0d76", fallback = "Geçerli bir şirket e-postası girilmelidir.")
    }

    if (formState.companyWebsiteUrl.isBlank()) {
        return BBLocalization.Current.Get(key = "7231e541-baf0-4ce4-95a1-c132a26b6bcc", fallback = "Şirket web sitesi gereklidir.")
    }

    if (formState.companyAddress.isBlank()) {
        return BBLocalization.Current.Get(key = "28e50920-d745-4569-b89d-2a0ecb110e5e", fallback = "Şirket adresi gereklidir.")
    }

    if (formState.countryName.isBlank()) {
        return BBLocalization.Current.Get(key = "f44ac382-8375-442f-a026-663a02acb0a4", fallback = "Ülke seçilmelidir.")
    }

    if (formState.cityName.isBlank()) {
        return BBLocalization.Current.Get(key = "a0e9dac4-d6b5-487a-97ef-68496b9655a0", fallback = "Şehir seçilmelidir.")
    }

    if (formState.districtName.isBlank()) {
        return BBLocalization.Current.Get(key = "3f16e77e-b73d-4692-9a6c-9cc9de128948", fallback = "İlçe seçilmelidir.")
    }

    if (formState.taxOffice.isBlank()) {
        return BBLocalization.Current.Get(key = "d1811be7-a437-4688-907b-de94d2e1bb9f", fallback = "Vergi dairesi gereklidir.")
    }

    if (formState.taxNumber.isBlank()) {
        return BBLocalization.Current.Get(key = "08ec8ba8-f749-4434-8126-94913cc60579", fallback = "Vergi numarası gereklidir.")
    }

    return BBLocalization.Current.Get(key = "e196e317-ac94-4825-961b-9f0fa043112f", fallback = "Şirket bilgilerini kontrol etmelisin.")
}

private fun companyTypeOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption(
            "limited",
            "Limited Şirket"
        ),
        BbSelectOption(
            "anonim",
            "Anonim Şirket"
        ),
        BbSelectOption(
            "sahis",
            "Şahıs Şirketi"
        ),
        BbSelectOption(
            "kooperatif",
            "Kooperatif"
        )
    )
}

private fun establishmentYearOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("2026", "2026"),
        BbSelectOption("2025", "2025"),
        BbSelectOption("2024", "2024"),
        BbSelectOption("2023", "2023"),
        BbSelectOption("2022", "2022"),
        BbSelectOption("2021", "2021"),
        BbSelectOption("2020", "2020"),
        BbSelectOption("2019", "2019"),
        BbSelectOption("2018", "2018"),
        BbSelectOption("2017", "2017"),
        BbSelectOption("2016", "2016"),
        BbSelectOption("2015", "2015")
    )
}

private fun countryOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption(BBLocalization.Current.Get(key = "5365b492-6a1c-4b46-b5c0-b50cbfdd17a8", fallback = "Türkiye"), BBLocalization.Current.Get(key = "5365b492-6a1c-4b46-b5c0-b50cbfdd17a8", fallback = "Türkiye")),
        BbSelectOption("Almanya", "Almanya"),
        BbSelectOption("Amerika Birleşik Devletleri", "Amerika Birleşik Devletleri"),
        BbSelectOption("Çin", "Çin"),
        BbSelectOption("Birleşik Krallık", "Birleşik Krallık")
    )
}

private fun cityOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption(BBLocalization.Current.Get(key = "0f7353b3-7eb8-4195-970a-08a0d0bc7531", fallback = "İstanbul"), BBLocalization.Current.Get(key = "0f7353b3-7eb8-4195-970a-08a0d0bc7531", fallback = "İstanbul")),
        BbSelectOption("Ankara", "Ankara"),
        BbSelectOption(BBLocalization.Current.Get(key = "a1c30bf4-3ee1-48bd-a853-f97b6144455f", fallback = "İzmir"), BBLocalization.Current.Get(key = "a1c30bf4-3ee1-48bd-a853-f97b6144455f", fallback = "İzmir")),
        BbSelectOption("Bursa", "Bursa"),
        BbSelectOption("Kayseri", "Kayseri"),
        BbSelectOption("Konya", "Konya")
    )
}

private fun districtOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("Şişli", "Şişli"),
        BbSelectOption(BBLocalization.Current.Get(key = "6225cbb5-bea9-4970-b7e9-7cd9930d16d6", fallback = "Kadıköy"), BBLocalization.Current.Get(key = "6225cbb5-bea9-4970-b7e9-7cd9930d16d6", fallback = "Kadıköy")),
        BbSelectOption("Üsküdar", "Üsküdar"),
        BbSelectOption("Çankaya", "Çankaya"),
        BbSelectOption("Nilüfer", "Nilüfer"),
        BbSelectOption("Melikgazi", "Melikgazi")
    )
}

private fun createSampleCompanyEditFormState(): CompanyEditFormState {
    return CompanyEditFormState(
        companyId = 2,
        companyOfficialName = BBLocalization.Current.Get(key = "796d73f2-bf9e-4b2a-82e4-d74a8ba96608", fallback = "Türkiye Global Ticaret Limited Şirketi"),
        companyType = "limited",
        establishmentYear = "2025",
        companyEmailAddress = "muraterkan500@yandex.com",
        companyWebsiteUrl = "www.turkiyeglobal.com",
        companyAddress = "Fulya Mah., Aytekin Kotil Cad., No: 1/1",
        countryName = BBLocalization.Current.Get(key = "5365b492-6a1c-4b46-b5c0-b50cbfdd17a8", fallback = "Türkiye"),
        cityName = BBLocalization.Current.Get(key = "0f7353b3-7eb8-4195-970a-08a0d0bc7531", fallback = "İstanbul"),
        districtName = "Şişli",
        postalCode = "34394",
        taxOffice = "Şişli Vergi Dairesi",
        taxNumber = "789456123",
        mersisNumber = "01881 299217 0001",
        kepAddress = "tglobal@bulbulustur.com"
    )
}

