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
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeFields
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeState
import com.bulbulustur.android.Application.Views.Shared.LogonPublicFieldLabel
import com.bulbulustur.android.Application.Views.Shared.LogonPublicPageTitle
import com.bulbulustur.android.Application.Views.Shared.LogonPublicRegisterLegalFooter
import com.bulbulustur.android.Application.Views.Shared.LogonPublicScaffold
import com.bulbulustur.android.Application.Views.Shared.LogonPublicTextField
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun RegisterStartScreen(
    verifiedEmail: String = "",
    addressCascadeState: AddressCascadeState =
        AddressCascadeState(),
    isRegisterLoading: Boolean = false,
    registerErrorMessage: String? = null,
    onCountrySelected: (countryId: Int) -> Unit = {},
    onCountryStateSelected: (countryStateId: Int) -> Unit = {},
    onCountryDepartmentSelected: (countryDepartmentId: Int?) -> Unit = {},
    onCitySelected: (cityId: Int) -> Unit = {},
    onDistrictSelected: (districtId: Int?) -> Unit = {},
    onContinueClick: (registerStartForm: RegisterStartForm) -> Unit = {},
    onBackToLogonClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {}
) {
    var name by
    remember {
        mutableStateOf("")
    }

    var surname by
    remember {
        mutableStateOf("")
    }

    var password by
    remember {
        mutableStateOf("")
    }

    var passwordAgain by
    remember {
        mutableStateOf("")
    }

    val selection =
        addressCascadeState.Selection

    val isFormReady =
        name.isNotBlank() &&
                surname.isNotBlank() &&
                verifiedEmail.isNotBlank() &&
                addressCascadeState.IsValid &&
                password.length >= 8 &&
                passwordAgain.isNotBlank() &&
                password == passwordAgain &&
                !isRegisterLoading

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
                BBLocalization.Current.Get(key = "149f95c3-6cf0-4726-9cf9-1ad7cd2e6ac4", fallback = "Üyelik Tamamlama"),
            title =
                BBLocalization.Current.Get(key = "2f3f4ea5-b7c1-4ebc-b42d-f81b4f84abec", fallback = ""),
            description =
                BBLocalization.Current.Get(key = "0f550629-d738-4ef0-b5b6-493a39254c81", fallback = "Bulbulustur hesabınızı oluşturmak için bilgilerinizi tamamlayın.")
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space8
                )
        )

        LogonPublicFieldLabel(
            text =
                BBLocalization.Current.Get(key = "9b9cf3d0-0463-4f28-83de-c750f16963e4", fallback = "Ad")
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
                BBLocalization.Current.Get(key = "de47456e-91cb-47d5-8882-458fe0cf0b5e", fallback = "Adınız")
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        LogonPublicFieldLabel(
            text =
                BBLocalization.Current.Get(key = "43b07485-278d-4633-9404-bf6a30a28222", fallback = "")
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
                BBLocalization.Current.Get(key = "d3e38a79-6cd1-415b-89d2-2893719c54e8", fallback = "Soyadınız")
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space4
                )
        )

        LogonPublicFieldLabel(
            text =
                BBLocalization.Current.Get(key = "1246f9ff-205d-4d92-84ee-7c8c7a3f2d46", fallback = "")
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
                BBLocalization.Current.Get(key = "457d3d09-532e-4d44-b7fc-b6f18f43d5f7", fallback = ""),
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
                BBLocalization.Current.Get(key = "73a67a8a-8c74-40a7-ab6e-09100c4918f0", fallback = "Konum Bilgileri"),
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
                BBLocalization.Current.Get(key = "1d3d2b7f-b77c-4a63-a824-2ba8bb672194", fallback = "Ülke ve şehir bilgileri platform deneyiminizi hazırlamak için kullanılır."),
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

        AddressCascadeFields(
            state =
                addressCascadeState,
            onCountrySelected =
                onCountrySelected,
            onCountryStateSelected =
                onCountryStateSelected,
            onCountryDepartmentSelected =
                onCountryDepartmentSelected,
            onCitySelected =
                onCitySelected,
            onDistrictSelected =
                onDistrictSelected,
            enabled =
                !isRegisterLoading
        )

        Spacer(
            modifier =
                Modifier.height(
                    BBSpacing.Space6
                )
        )

        Text(
            text =
                BBLocalization.Current.Get(key = "aba99f7e-0b0a-45aa-96b2-6ac03f36582a", fallback = "Güvenlik"),
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
                BBLocalization.Current.Get(key = "f3bfe2a5-b25a-45b4-99f9-fbe3fb89bef5", fallback = "Şifre")
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
                BBLocalization.Current.Get(key = "2de0628e-e31a-4d82-b6e7-c8a48fcd348e", fallback = "En az 8 karakter"),
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
                BBLocalization.Current.Get(key = "22e6bc04-5fef-4eda-8932-fc4a39accc77", fallback = "Şifre Tekrar")
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
                BBLocalization.Current.Get(key = "22e6bc04-5fef-4eda-8932-fc4a39accc77", fallback = "Şifrenizi tekrar girin"),
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
                    BBLocalization.Current.Get(key = "b7421a1d-7c44-4d9f-9ab1-58e3e0ad4f91", fallback = "Şifreler birbiriyle eşleşmiyor."),
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.error
            )
        }

        if (!registerErrorMessage.isNullOrBlank()) {
            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )

            Text(
                text =
                    registerErrorMessage,
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
                when {
                    isRegisterLoading ->
                        BBLocalization.Current.Get(key = "7ec94929-68f0-4cf6-8411-c2ea8f567d9d", fallback = "Hesap oluşturuluyor...")

                    isFormReady ->
                        BBLocalization.Current.Get(key = "0877fc97-9071-4a25-af7a-eb202ed15731", fallback = "")

                    else ->
                        BBLocalization.Current.Get(key = "bc8dd3f3-103e-4810-a790-f729d1bb1923", fallback = "Bilgileri Tamamlayın")
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
                            selection.CountryId,
                        CountryStateId =
                            selection.CountryStateId,
                        CountryDepartmentId =
                            selection.CountryDepartmentId,
                        CityId =
                            selection.CityId,
                        DistrictId =
                            selection.DistrictId,
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
                    BBLocalization.Current.Get(key = "450a3be3-a073-4365-a1a5-5d8a0e7ae4e7", fallback = "Zaten hesabınız var mı?"),
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            TextButton(
                onClick =
                    onBackToLogonClick,
                enabled =
                    !isRegisterLoading
            ) {
                Text(
                    text =
                        BBLocalization.Current.Get(key = "72289e9d-49e1-4c2a-8b0b-3ab5a67610a6", fallback = "Giriş Yap"),
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
    val CountryStateId: Int,
    val CountryDepartmentId: Int?,
    val CityId: Int,
    val DistrictId: Int?,
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
                "test@bulbulustur.com"
        )
    }
}