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
                "Üyelik Tamamlama",
            title =
                BBLocalization.Current.Get(key = "2f3f4ea5-b7c1-4ebc-b42d-f81b4f84abec", fallback = ""),
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
                        "Hesap oluşturuluyor..."

                    isFormReady ->
                        BBLocalization.Current.Get(key = "0877fc97-9071-4a25-af7a-eb202ed15731", fallback = "")

                    else ->
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
                    "Zaten hesabınız var mı?",
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