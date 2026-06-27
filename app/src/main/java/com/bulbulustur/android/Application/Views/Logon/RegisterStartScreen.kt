package com.bulbulustur.android.Application.Views.Logon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Views.Shared.LogonPublicFieldLabel
import com.bulbulustur.android.Application.Views.Shared.LogonPublicPageTitle
import com.bulbulustur.android.Application.Views.Shared.LogonPublicRegisterLegalFooter
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.Views.Shared.LogonPublicTextField
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectOption
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO

@Composable
fun RegisterStartScreen(
    verifiedEmail: String = "",
    countries: List<AddressCountryDTO> = emptyList(),
    cities: List<AddressCityDTO> = emptyList(),
    selectedCountryId: Int = 0,
    selectedCityId: Int = 0,
    isCountriesLoading: Boolean = false,
    isCitiesLoading: Boolean = false,
    countryError: String? = null,
    cityError: String? = null,
    onCountrySelected: (countryId: Int) -> Unit = {},
    onCitySelected: (cityId: Int) -> Unit = {},
    onContinueClick: (registerStartForm: RegisterStartForm) -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var name by remember {
        mutableStateOf("")
    }

    var surname by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordAgain by remember {
        mutableStateOf("")
    }

    val countryOptions =
        remember(
            countries
        ) {
            countries.map { country ->
                BbSelectOption(
                    value =
                        country.AddressCountryId.toString(),
                    text =
                        country.Content
                )
            }
        }

    val cityOptions =
        remember(
            cities
        ) {
            cities.map { city ->
                BbSelectOption(
                    value =
                        city.AddressCityId.toString(),
                    text =
                        city.Content
                )
            }
        }

    val isFormReady =
        name.isNotBlank() &&
                surname.isNotBlank() &&
                verifiedEmail.isNotBlank() &&
                selectedCountryId > 0 &&
                selectedCityId > 0 &&
                password.length >= 8 &&
                passwordAgain.isNotBlank() &&
                password == passwordAgain

    LogonPublicScaffold(
        onLanguageSelected = {
            onLanguageClick()
        },
        footer = {
            LogonPublicRegisterLegalFooter()
        }
    ) {
        LogonPublicPageTitle(
            eyebrow =
                "Üyelik Tamamlama",
            title =
                "Kayıt Ol",
            description =
                "Bulbulustur hesabınızı oluşturmak için bilgilerinizi tamamlayın."
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space8
                )
        )

        LogonPublicFieldLabel(
            text =
                "Ad"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value =
                name,
            onValueChange = {
                name =
                    it
            },
            placeholder =
                "Adınız"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        LogonPublicFieldLabel(
            text =
                "Soyad"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value =
                surname,
            onValueChange = {
                surname =
                    it
            },
            placeholder =
                "Soyadınız"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        LogonPublicFieldLabel(
            text =
                "E-posta"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value =
                verifiedEmail,
            onValueChange = {
            },
            placeholder =
                "Doğrulanmış e-posta adresiniz",
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Email
                )
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space6
                )
        )

        Text(
            text =
                "Konum Bilgileri",
            style =
                MaterialTheme.typography.titleMedium,
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onSurface
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        Text(
            text =
                "Ülke ve şehir bilgileri platform deneyiminizi hazırlamak için kullanılır.",
            style =
                MaterialTheme.typography.bodySmall,
            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        BbSelectInput(
            selectedValue =
                selectedCountryId
                    .takeIf {
                        it > 0
                    }
                    ?.toString()
                    .orEmpty(),
            options =
                countryOptions,
            onValueChange = { value ->
                value
                    .toIntOrNull()
                    ?.let { countryId ->
                        onCountrySelected(
                            countryId
                        )
                    }
            },
            label =
                "Ülke",
            placeholder =
                if (isCountriesLoading) {
                    "Ülkeler yükleniyor..."
                } else {
                    "Ülke seçiniz"
                },
            helperText =
                if (
                    !isCountriesLoading &&
                    countryError == null &&
                    countries.isEmpty()
                ) {
                    "Ülke verisi bulunamadı."
                } else {
                    null
                },
            errorText =
                countryError,
            enabled =
                !isCountriesLoading &&
                        countries.isNotEmpty()
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        BbSelectInput(
            selectedValue =
                selectedCityId
                    .takeIf {
                        it > 0
                    }
                    ?.toString()
                    .orEmpty(),
            options =
                cityOptions,
            onValueChange = { value ->
                value
                    .toIntOrNull()
                    ?.let { cityId ->
                        onCitySelected(
                            cityId
                        )
                    }
            },
            label =
                "Şehir",
            placeholder =
                when {
                    selectedCountryId <= 0 ->
                        "Önce ülke seçiniz"

                    isCitiesLoading ->
                        "Şehirler yükleniyor..."

                    else ->
                        "Şehir seçiniz"
                },
            helperText =
                if (
                    selectedCountryId > 0 &&
                    !isCitiesLoading &&
                    cityError == null &&
                    cities.isEmpty()
                ) {
                    "Bu ülke için şehir verisi bulunamadı."
                } else {
                    null
                },
            errorText =
                cityError,
            enabled =
                selectedCountryId > 0 &&
                        !isCitiesLoading &&
                        cities.isNotEmpty()
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space6
                )
        )

        Text(
            text =
                "Güvenlik",
            style =
                MaterialTheme.typography.titleMedium,
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onSurface
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        LogonPublicFieldLabel(
            text =
                "Şifre"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value =
                password,
            onValueChange = {
                password =
                    it
            },
            placeholder =
                "En az 8 karakter",
            visualTransformation =
                PasswordVisualTransformation(),
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password
                )
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        LogonPublicFieldLabel(
            text =
                "Şifre Tekrar"
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space2
                )
        )

        LogonPublicTextField(
            value =
                passwordAgain,
            onValueChange = {
                passwordAgain =
                    it
            },
            placeholder =
                "Şifrenizi tekrar girin",
            visualTransformation =
                PasswordVisualTransformation(),
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password
                )
        )

        if (
            passwordAgain.isNotBlank() &&
            password != passwordAgain
        ) {
            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space2
                    )
            )

            Text(
                text =
                    "Şifreler birbiriyle eşleşmiyor.",
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.error
            )
        }

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space7
                )
        )

        BbButton(
            modifier =
                Modifier.fillMaxWidth(),
            text =
                if (isFormReady) {
                    "Hesap Oluştur"
                } else {
                    "Bilgileri Tamamlayın"
                },
            onClick = {
                if (!isFormReady) {
                    return@BbButton
                }

                onContinueClick(
                    RegisterStartForm(
                        Name =
                            name.trim(),
                        Surname =
                            surname.trim(),
                        Email =
                            verifiedEmail.trim(),
                        CountryId =
                            selectedCountryId,
                        CityId =
                            selectedCityId,
                        Password =
                            password,
                        PasswordAgain =
                            passwordAgain
                    )
                )
            },
            variant =
                BbButtonVariant.Primary,
            size =
                BbButtonSize.Large
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space5
                )
        )

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.Center,
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Text(
                text =
                    "Zaten hesabınız var mı?",
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            TextButton(
                onClick =
                    onBackToLogonClick
            ) {
                Text(
                    text =
                        "Giriş Yap",
                    style =
                        MaterialTheme.typography.labelLarge,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

data class RegisterStartForm(
    val Name: String,
    val Surname: String,
    val Email: String,
    val CountryId: Int,
    val CityId: Int,
    val Password: String,
    val PasswordAgain: String
)

@Preview(
    showBackground =
        true
)
@Composable
private fun RegisterStartScreenPreview() {
    BbTheme {
        RegisterStartScreen(
            verifiedEmail =
                "test@bulbulustur.com",
            countries =
                listOf(
                    AddressCountryDTO(
                        AddressCountryId =
                            209,
                        Content =
                            "Türkiye"
                    )
                ),
            cities =
                listOf(
                    AddressCityDTO(
                        AddressCityId =
                            34,
                        CountryId =
                            209,
                        Content =
                            "İstanbul"
                    )
                ),
            selectedCountryId =
                209,
            selectedCityId =
                34
        )
    }
}