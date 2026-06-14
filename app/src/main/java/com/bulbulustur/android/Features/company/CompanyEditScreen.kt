package com.bulbulustur.android.Features.company

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
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbChip
import com.bulbulustur.android.Ui.components.form.BbFormSection
import com.bulbulustur.android.Ui.components.form.BbSelectInput
import com.bulbulustur.android.Ui.components.form.BbSelectOption
import com.bulbulustur.android.Ui.components.form.BbTextarea
import com.bulbulustur.android.Ui.components.form.BbTextInput
import com.bulbulustur.android.Ui.theme.BbSpacing

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
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.PageTop
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGap)
    ) {
        BbButton(
            text = "Şirket Bilgilerime Dön",
            onClick = onBackClick,
            variant = BbButtonVariant.Outline
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            BbChip(
                text = "Şirket Profili"
            )

            Text(
                text = "Şirketi Düzenle",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Şirket hesabınla ilişkili temel kurumsal, adres ve resmi bilgileri güncelle.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        BbFormSection(
            title = "Kurumsal bilgiler"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Bu bilgiler şirket profilin, resmi kayıtların ve platform üzerindeki kurumsal işlemlerin için kullanılır.",
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
                    label = "Şirketinizin Resmi Ünvanı",
                    placeholder = "Şirket resmi ünvanı"
                )

                BbSelectInput(
                    selectedValue = formState.value.companyType,
                    onValueChange = { companyType ->
                        formState.value = formState.value.copy(
                            companyType = companyType,
                            validationMessage = null
                        )
                    },
                    label = "Şirketinizin Tipi",
                    placeholder = "Şirket tipi seç",
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
                    label = "Kuruluş Yılı",
                    placeholder = "Yıl seç",
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
                    label = "Şirket E-postası",
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
                    label = "Şirket Web Sitesi",
                    placeholder = "www.firma.com"
                )
            }
        }

        BbFormSection(
            title = "Adres ve Konum"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Şirket kayıtlı adres ve lokasyon bilgilerini düzenle.",
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
                    label = "Şirketinizin Adresi",
                    placeholder = "Açık şirket adresi"
                )

                BbSelectInput(
                    selectedValue = formState.value.countryName,
                    onValueChange = { countryName ->
                        formState.value = formState.value.copy(
                            countryName = countryName,
                            validationMessage = null
                        )
                    },
                    label = "Ülke",
                    placeholder = "Ülke seç",
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
                    label = "Şehir",
                    placeholder = "Şehir seç",
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
                    label = "İlçe",
                    placeholder = "İlçe seç",
                    options = districtOptions()
                )

                BbTextInput(
                    value = formState.value.postalCode,
                    onValueChange = { postalCode ->
                        formState.value = formState.value.copy(
                            postalCode = postalCode
                        )
                    },
                    label = "Posta Kodu",
                    placeholder = "Posta kodu"
                )
            }
        }

        BbFormSection(
            title = "Vergi ve Resmi Kayıtlar"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Fatura, vergi ve resmi şirket kayıtları için kullanılan bilgileri düzenle.",
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
                    label = "Vergi Dairesi",
                    placeholder = "Vergi dairesi"
                )

                BbTextInput(
                    value = formState.value.taxNumber,
                    onValueChange = { taxNumber ->
                        formState.value = formState.value.copy(
                            taxNumber = taxNumber,
                            validationMessage = null
                        )
                    },
                    label = "Vergi Numarası",
                    placeholder = "Vergi numarası"
                )

                BbTextInput(
                    value = formState.value.mersisNumber,
                    onValueChange = { mersisNumber ->
                        formState.value = formState.value.copy(
                            mersisNumber = mersisNumber
                        )
                    },
                    label = "Mersis No",
                    placeholder = "Mersis numarası"
                )

                BbTextInput(
                    value = formState.value.kepAddress,
                    onValueChange = { kepAddress ->
                        formState.value = formState.value.copy(
                            kepAddress = kepAddress
                        )
                    },
                    label = "Kep Adresi",
                    placeholder = "kep@firma.com"
                )

                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Text(
                        text = "Şirket bilgilerin kurumsal profil, fatura ve platform doğrulama süreçlerinde kullanılabilir.",
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
                    text = "Gönder",
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
                    text = "Vazgeç",
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
        return "Şirket resmi ünvanı gereklidir."
    }

    if (formState.companyType.isBlank()) {
        return "Şirket tipi seçilmelidir."
    }

    if (formState.establishmentYear.isBlank()) {
        return "Kuruluş yılı seçilmelidir."
    }

    if (!formState.hasValidEmailFormat) {
        return "Geçerli bir şirket e-postası girilmelidir."
    }

    if (formState.companyWebsiteUrl.isBlank()) {
        return "Şirket web sitesi gereklidir."
    }

    if (formState.companyAddress.isBlank()) {
        return "Şirket adresi gereklidir."
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

    if (formState.taxOffice.isBlank()) {
        return "Vergi dairesi gereklidir."
    }

    if (formState.taxNumber.isBlank()) {
        return "Vergi numarası gereklidir."
    }

    return "Şirket bilgilerini kontrol etmelisin."
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
        BbSelectOption("Türkiye", "Türkiye"),
        BbSelectOption("Almanya", "Almanya"),
        BbSelectOption("Amerika Birleşik Devletleri", "Amerika Birleşik Devletleri"),
        BbSelectOption("Çin", "Çin"),
        BbSelectOption("Birleşik Krallık", "Birleşik Krallık")
    )
}

private fun cityOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("İstanbul", "İstanbul"),
        BbSelectOption("Ankara", "Ankara"),
        BbSelectOption("İzmir", "İzmir"),
        BbSelectOption("Bursa", "Bursa"),
        BbSelectOption("Kayseri", "Kayseri"),
        BbSelectOption("Konya", "Konya")
    )
}

private fun districtOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption("Şişli", "Şişli"),
        BbSelectOption("Kadıköy", "Kadıköy"),
        BbSelectOption("Üsküdar", "Üsküdar"),
        BbSelectOption("Çankaya", "Çankaya"),
        BbSelectOption("Nilüfer", "Nilüfer"),
        BbSelectOption("Melikgazi", "Melikgazi")
    )
}

private fun createSampleCompanyEditFormState(): CompanyEditFormState {
    return CompanyEditFormState(
        companyId = 2,
        companyOfficialName = "Türkiye Global Ticaret Limited Şirketi",
        companyType = "limited",
        establishmentYear = "2025",
        companyEmailAddress = "muraterkan500@yandex.com",
        companyWebsiteUrl = "www.turkiyeglobal.com",
        companyAddress = "Fulya Mah., Aytekin Kotil Cad., No: 1/1",
        countryName = "Türkiye",
        cityName = "İstanbul",
        districtName = "Şişli",
        postalCode = "34394",
        taxOffice = "Şişli Vergi Dairesi",
        taxNumber = "789456123",
        mersisNumber = "01881 299217 0001",
        kepAddress = "tglobal@bulbulustur.com"
    )
}