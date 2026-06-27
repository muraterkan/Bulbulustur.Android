package com.bulbulustur.android.Application.Controllers

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberTempDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCityRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IAuthenticationRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberTempRepository
import com.bulbulustur.android.businesslayer.Core.Model.AuthResponse
import com.bulbulustur.android.businesslayer.Core.Model.GoogleLoginRequest
import com.bulbulustur.android.businesslayer.Core.Model.MemberAuthModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberForgotModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberSetPasswordModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberTempFistdoorModel
import com.bulbulustur.android.businesslayer.Core.Model.RevokeTokenRequest
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LogonControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<*>? = null,
    val ErrorMessage: String? = null,

    val IsLoginSuccessful: Boolean = false,
    val IsLogoutCompleted: Boolean = false,

    val IsFirstDoorSuccessful: Boolean = false,
    val FirstDoorEmail: String = "",

    val IsForgotPasswordSuccessful: Boolean = false,
    val ForgotPasswordEmail: String = "",
    val ForgotPasswordMessage: String? = null,

    val IsSetNewPasswordSuccessful: Boolean = false,
    val SetNewPasswordMessage: String? = null,

    val IsMemberTempLoading: Boolean = false,
    val IsMemberTempLoaded: Boolean = false,
    val MemberTemp: MemberTempDTO? = null,

    val Countries: List<AddressCountryDTO> = emptyList(),
    val Cities: List<AddressCityDTO> = emptyList(),

    val SelectedCountryId: Int = 0,
    val SelectedCityId: Int = 0,

    val IsCountriesLoading: Boolean = false,
    val IsCitiesLoading: Boolean = false,

    val CountryError: String? = null,
    val CityError: String? = null
) {

    val IsLoggingOut: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "LogoutPost"

    val IsSendingFirstDoorEmail: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "FirstDoorPost"

    val IsSendingForgotPasswordLink: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "ForgotPasswordPost"

    val IsUpdatingPassword: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "SetNewPasswordPost"

    val IsGoogleLoginLoading: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "GoogleLoginPost"
}

sealed interface LogonControllerEvent {

    data object Refresh : LogonControllerEvent

    data class Load(
        val Parameters: Map<String, Any?> = emptyMap()
    ) : LogonControllerEvent

    data class Submit(
        val Body: Any? = null
    ) : LogonControllerEvent
}

class LogonController(
    private val executeService: IExecuteService,
    private val authenticationRepository: IAuthenticationRepository,
    private val memberTempRepository: IMemberTempRepository,
    private val addressCountryRepository: IAddressCountryRepository,
    private val addressCityRepository: IAddressCityRepository,
    private val userSessionManager: UserSessionManager
) : BaseController() {

    private val _state =
        MutableStateFlow(
            LogonControllerState()
        )

    val State: StateFlow<LogonControllerState> =
        _state.asStateFlow()

    fun Login() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction =
                    "Login",
                ErrorMessage =
                    null,
                IsLoginSuccessful =
                    false
            )
        }
    }

    fun LoginPost(
        email: String,
        password: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val model =
            MemberAuthModel(
                Email =
                    email.trim(),
                Password =
                    password
            )

        LoginPost(
            model =
                model,
            languageId =
                languageId
        )
    }

    fun LoginPost(
        model: MemberAuthModel,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedModel =
            model.copy(
                Email =
                    model.Email.trim()
            )

        val validationMessage =
            ValidateLogin(
                email =
                    normalizedModel.Email,
                password =
                    normalizedModel.Password
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "LoginPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsLoginSuccessful =
                        false
                )
            }

            return
        }

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "LoginPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.LoginPost"
                ) {
                    authenticationRepository.LoginAsync(
                        languageId =
                            languageId,
                        model =
                            normalizedModel
                    )
                }

            HandleLoginResponse(
                response =
                    response
            )
        }
    }

    fun GoogleLoginPost(
        idToken: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedIdToken =
            idToken.trim()

        if (normalizedIdToken.isBlank()) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GoogleLoginPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        "Google kimlik doğrulama bilgisi alınamadı.",
                    IsLoginSuccessful =
                        false
                )
            }

            return
        }

        val model =
            GoogleLoginRequest(
                IdToken =
                    normalizedIdToken,
                LanguageId =
                    languageId,
                DeviceType =
                    "Android",
                Platform =
                    "Android",
                Browser =
                    "Mobile App",
                IPAddress =
                    ""
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "GoogleLoginPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.GoogleLoginPost"
                ) {
                    authenticationRepository.GoogleLoginAsync(
                        model =
                            model
                    )
                }

            HandleLoginResponse(
                response =
                    response
            )
        }
    }

    fun SetGoogleLoginError(
        message: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading = false,
                CurrentAction = "GoogleLoginPost",
                LastResult = null,
                ErrorMessage =
                    message.ifBlank {
                        "Google ile giriş başlatılamadı."
                    },
                IsLoginSuccessful = false
            )
        }
    }

    fun LogoutPost(
        languageId: Int,
        onCompleted: () -> Unit
    ) {
        if (_state.value.IsLoading) {
            return
        }

        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    true,
                CurrentAction =
                    "LogoutPost",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsLoginSuccessful =
                    false,
                IsLogoutCompleted =
                    false
            )
        }

        val refreshToken =
            userSessionManager.GetRefreshToken()

        val localTokensCleared =
            userSessionManager.ClearAuthentication()

        onCompleted()

        viewModelScope.launch {
            var logoutResponse: Result<Boolean>? =
                null

            try {
                if (!refreshToken.isNullOrBlank()) {
                    val model =
                        RevokeTokenRequest(
                            RefreshToken =
                                refreshToken
                        )

                    logoutResponse =
                        executeService.PostAsync(
                            operationType =
                                "App.Logon.LogoutPost"
                        ) {
                            authenticationRepository.LogoutAsync(
                                languageId =
                                    languageId,
                                model =
                                    model
                            )
                        }
                }
            } finally {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "LogoutPost",
                        LastResult =
                            logoutResponse,
                        ErrorMessage =
                            if (localTokensCleared) {
                                null
                            } else {
                                "Oturum bilgileri cihazdan tamamen temizlenemedi."
                            },
                        IsLoginSuccessful =
                            false,
                        IsLogoutCompleted =
                            false
                    )
                }
            }
        }
    }

    fun ConsumeLogoutCompleted() {
        _state.update { currentState ->
            currentState.copy(
                IsLogoutCompleted =
                    false
            )
        }
    }

    fun FirstDoor() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction =
                    "FirstDoor",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsFirstDoorSuccessful =
                    false
            )
        }
    }

    fun FirstDoorPost(
        email: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedEmail =
            email.trim()

        val validationMessage =
            ValidateEmail(
                email =
                    normalizedEmail
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "FirstDoorPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsFirstDoorSuccessful =
                        false
                )
            }

            return
        }

        val model =
            MemberTempFistdoorModel(
                Email =
                    normalizedEmail
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "FirstDoorPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.FirstDoorPost"
                ) {
                    memberTempRepository.FirstDoorAsync(
                        languageId =
                            languageId,
                        model =
                            model
                    )
                }

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "FirstDoorPost",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                "Doğrulama e-postası gönderilemedi."
                            },
                        IsFirstDoorSuccessful =
                            false
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "FirstDoorPost",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsFirstDoorSuccessful =
                        true,
                    FirstDoorEmail =
                        normalizedEmail
                )
            }
        }
    }

    fun ConsumeFirstDoorSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsFirstDoorSuccessful =
                    false
            )
        }
    }

    fun GetMemberTempByActivationCode(
        activationCode: String,
        languageId: Int
    ) {
        if (
            _state.value.IsLoading ||
            _state.value.IsMemberTempLoading
        ) {
            return
        }

        val normalizedActivationCode =
            activationCode.trim()

        if (normalizedActivationCode.isBlank()) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GetMemberTempByActivationCode",
                    LastResult =
                        null,
                    ErrorMessage =
                        "Kayıt bağlantısı geçersiz.",
                    IsMemberTempLoading =
                        false,
                    IsMemberTempLoaded =
                        false,
                    MemberTemp =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GetMemberTempByActivationCode",
                    LastResult =
                        null,
                    ErrorMessage =
                        null,
                    IsMemberTempLoading =
                        true,
                    IsMemberTempLoaded =
                        false,
                    MemberTemp =
                        null
                )
            }

            val response =
                executeService.GetAsync {
                    memberTempRepository
                        .GetMemberTempByActivationCodeAsync(
                            languageId =
                                languageId,
                            uuid =
                                normalizedActivationCode
                        )
                }

            val memberTemp =
                response.Data

            if (
                !response.Success ||
                memberTemp == null ||
                memberTemp.Email.isBlank() ||
                memberTemp.ActivationCode.isBlank()
            ) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "GetMemberTempByActivationCode",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                "Kayıt bağlantısı geçersiz veya süresi dolmuş."
                            },
                        IsMemberTempLoading =
                            false,
                        IsMemberTempLoaded =
                            false,
                        MemberTemp =
                            null
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GetMemberTempByActivationCode",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsMemberTempLoading =
                        false,
                    IsMemberTempLoaded =
                        true,
                    MemberTemp =
                        memberTemp
                )
            }
        }
    }

    fun ClearMemberTemp() {
        _state.update { currentState ->
            currentState.copy(
                IsMemberTempLoading =
                    false,
                IsMemberTempLoaded =
                    false,
                MemberTemp =
                    null,
                ErrorMessage =
                    null
            )
        }
    }

    fun LoadCountries(
        languageId: Int
    ) {
        if (_state.value.IsCountriesLoading) {
            return
        }

        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsCountriesLoading =
                        true,
                    CountryError =
                        null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey =
                        ""
                ) {
                    addressCountryRepository.GetAddressCountriesAsync(
                        languageId =
                            languageId,
                        count =
                            300
                    )
                }

            val countries =
                response.Data
                    .orEmpty()
                    .sortedWith(
                        compareBy<AddressCountryDTO> {
                            it.DisplayOrder
                                ?: Int.MAX_VALUE
                        }.thenBy {
                            it.Content
                        }
                    )

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        Countries =
                            emptyList(),
                        SelectedCountryId =
                            0,
                        Cities =
                            emptyList(),
                        SelectedCityId =
                            0,
                        IsCountriesLoading =
                            false,
                        CountryError =
                            response.Message.ifBlank {
                                "Ülke listesi alınamadı."
                            }
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    Countries =
                        countries,
                    IsCountriesLoading =
                        false,
                    CountryError =
                        if (countries.isEmpty()) {
                            "Ülke verisi bulunamadı."
                        } else {
                            null
                        }
                )
            }
        }
    }

    fun SelectCountry(
        countryId: Int
    ) {
        if (countryId <= 0) {
            _state.update { currentState ->
                currentState.copy(
                    SelectedCountryId =
                        0,
                    SelectedCityId =
                        0,
                    Cities =
                        emptyList(),
                    CityError =
                        null
                )
            }

            return
        }

        if (_state.value.SelectedCountryId == countryId) {
            return
        }

        _state.update { currentState ->
            currentState.copy(
                SelectedCountryId =
                    countryId,
                SelectedCityId =
                    0,
                Cities =
                    emptyList(),
                CityError =
                    null
            )
        }

        LoadCities(
            countryId =
                countryId
        )
    }

    fun LoadCities(
        countryId: Int
    ) {
        if (
            countryId <= 0 ||
            _state.value.IsCitiesLoading
        ) {
            return
        }

        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsCitiesLoading =
                        true,
                    CityError =
                        null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey =
                        ""
                ) {
                    addressCityRepository.GetAddressCitiesAsync(
                        countryId =
                            countryId,
                        count =
                            10000
                    )
                }

            val cities =
                response.Data
                    .orEmpty()
                    .sortedWith(
                        compareBy<AddressCityDTO> {
                            it.DisplayOrder
                                ?: Int.MAX_VALUE
                        }.thenBy {
                            it.Content
                        }
                    )

            if (_state.value.SelectedCountryId != countryId) {
                _state.update { currentState ->
                    currentState.copy(
                        IsCitiesLoading =
                            false
                    )
                }

                return@launch
            }

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        Cities =
                            emptyList(),
                        SelectedCityId =
                            0,
                        IsCitiesLoading =
                            false,
                        CityError =
                            response.Message.ifBlank {
                                "Şehir listesi alınamadı."
                            }
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    Cities =
                        cities,
                    SelectedCityId =
                        0,
                    IsCitiesLoading =
                        false,
                    CityError =
                        if (cities.isEmpty()) {
                            "Bu ülke için şehir verisi bulunamadı."
                        } else {
                            null
                        }
                )
            }
        }
    }

    fun SelectCity(
        cityId: Int
    ) {
        val cityBelongsToSelectedCountry =
            _state.value.Cities.any { city ->
                city.AddressCityId == cityId &&
                        city.CountryId == _state.value.SelectedCountryId
            }

        _state.update { currentState ->
            currentState.copy(
                SelectedCityId =
                    if (cityBelongsToSelectedCountry) {
                        cityId
                    } else {
                        0
                    },
                CityError =
                    null
            )
        }
    }

    fun RegisterStart() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction =
                    "RegisterStart",
                ErrorMessage =
                    null
            )
        }
    }

    fun RegisterFinal() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction =
                    "RegisterFinal",
                ErrorMessage =
                    null
            )
        }
    }

    fun RegisterFinalPost(
        body: Any? = null
    ) {
        SetPendingOperation(
            currentAction =
                "RegisterFinalPost"
        )
    }

    fun ForgotPassword() {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                CurrentAction =
                    "ForgotPassword",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsForgotPasswordSuccessful =
                    false,
                ForgotPasswordEmail =
                    "",
                ForgotPasswordMessage =
                    null
            )
        }
    }

    fun ForgotPasswordPost(
        email: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedEmail =
            email.trim()

        val validationMessage =
            ValidateEmail(
                email =
                    normalizedEmail
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "ForgotPasswordPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsForgotPasswordSuccessful =
                        false,
                    ForgotPasswordMessage =
                        null
                )
            }

            return
        }

        val model =
            MemberForgotModel(
                Email =
                    normalizedEmail
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "ForgotPasswordPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.ForgotPasswordPost"
                ) {
                    authenticationRepository.SendLinkForForgotAsync(
                        languageId =
                            languageId,
                        model =
                            model
                    )
                }

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "ForgotPasswordPost",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                "Şifre yenileme bağlantısı gönderilemedi."
                            },
                        IsForgotPasswordSuccessful =
                            false,
                        ForgotPasswordEmail =
                            normalizedEmail,
                        ForgotPasswordMessage =
                            null
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "ForgotPasswordPost",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsForgotPasswordSuccessful =
                        true,
                    ForgotPasswordEmail =
                        normalizedEmail,
                    ForgotPasswordMessage =
                        response.Message.ifBlank {
                            "Şifre yenileme bağlantısı e-posta adresinize gönderildi."
                        }
                )
            }
        }
    }

    fun SetNewPassword() {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                CurrentAction =
                    "SetNewPassword",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsSetNewPasswordSuccessful =
                    false,
                SetNewPasswordMessage =
                    null
            )
        }
    }

    fun SetNewPasswordPost(
        activationCode: String,
        newPassword: String,
        reNewPassword: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedActivationCode =
            activationCode.trim()

        val validationMessage =
            ValidateSetNewPassword(
                activationCode =
                    normalizedActivationCode,
                newPassword =
                    newPassword,
                reNewPassword =
                    reNewPassword
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "SetNewPasswordPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsSetNewPasswordSuccessful =
                        false,
                    SetNewPasswordMessage =
                        null
                )
            }

            return
        }

        val model =
            MemberSetPasswordModel(
                NewPassword =
                    newPassword,
                ReNewPassword =
                    reNewPassword,
                ActivationCode =
                    normalizedActivationCode,
                DeviceType =
                    "Android",
                IPAddress =
                    "",
                Browser =
                    "",
                Platform =
                    "Android",
                Location =
                    "",
                LanguageId =
                    languageId
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "SetNewPasswordPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.SetNewPasswordPost"
                ) {
                    authenticationRepository.UpdatePasswordAsync(
                        languageId =
                            languageId,
                        model =
                            model
                    )
                }

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "SetNewPasswordPost",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                "Şifreniz güncellenemedi."
                            },
                        IsSetNewPasswordSuccessful =
                            false,
                        SetNewPasswordMessage =
                            null
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "SetNewPasswordPost",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsSetNewPasswordSuccessful =
                        true,
                    SetNewPasswordMessage =
                        response.Message.ifBlank {
                            "Şifreniz başarıyla güncellendi."
                        }
                )
            }
        }
    }

    fun Expired() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction =
                    "Expired",
                ErrorMessage =
                    null
            )
        }
    }

    fun ClearError() {
        _state.update { currentState ->
            currentState.copy(
                ErrorMessage =
                    null
            )
        }
    }

    fun ClearForgotPasswordFeedback() {
        _state.update { currentState ->
            currentState.copy(
                ErrorMessage =
                    null,
                IsForgotPasswordSuccessful =
                    false,
                ForgotPasswordMessage =
                    null
            )
        }
    }

    fun ClearSetNewPasswordFeedback() {
        _state.update { currentState ->
            currentState.copy(
                ErrorMessage =
                    null,
                IsSetNewPasswordSuccessful =
                    false,
                SetNewPasswordMessage =
                    null
            )
        }
    }

    fun ConsumeSetNewPasswordSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsSetNewPasswordSuccessful =
                    false,
                SetNewPasswordMessage =
                    null
            )
        }
    }

    fun ConsumeLoginSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsLoginSuccessful =
                    false
            )
        }
    }

    private fun HandleLoginResponse(
        response: Result<AuthResponse>
    ) {
        val authResponse =
            response.Data



        Log.d(
            "LogonController",
            """
        HandleLoginResponse:
        Success=${response.Success}
        Message=${response.Message}
        HasData=${authResponse != null}
        HasToken=${!authResponse?.Token.isNullOrBlank()}
        HasRefreshToken=${!authResponse?.RefreshToken.isNullOrBlank()}
        Expiration=${authResponse?.Expiration}
        """.trimIndent()
        )





        if (
            !response.Success ||
            authResponse == null
        ) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    LastResult =
                        response,
                    ErrorMessage =
                        response.Message.ifBlank {
                            "Giriş işlemi başarısız oldu."
                        },
                    IsLoginSuccessful =
                        false
                )
            }

            return
        }

        val sessionSaved =
            userSessionManager.SetAuthenticated(
                authResponse =
                    authResponse
            )

        if (!sessionSaved) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    LastResult =
                        response,
                    ErrorMessage =
                        "Oturum bilgileri güvenli şekilde kaydedilemedi.",
                    IsLoginSuccessful =
                        false
                )
            }

            return
        }

        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                LastResult =
                    response,
                ErrorMessage =
                    null,
                IsLoginSuccessful =
                    true
            )
        }
    }

    private fun SetLoading(
        currentAction: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    true,
                CurrentAction =
                    currentAction,
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsLoginSuccessful =
                    false,
                IsLogoutCompleted =
                    false,
                IsFirstDoorSuccessful =
                    false,
                IsForgotPasswordSuccessful =
                    false,
                ForgotPasswordMessage =
                    null,
                IsSetNewPasswordSuccessful =
                    false,
                SetNewPasswordMessage =
                    null
            )
        }
    }

    private fun SetPendingOperation(
        currentAction: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                CurrentAction =
                    currentAction,
                LastResult =
                    null,
                ErrorMessage =
                    "$currentAction işlemi henüz Authentication API'ye bağlanmadı.",
                IsLoginSuccessful =
                    false,
                IsLogoutCompleted =
                    false
            )
        }
    }

    private fun ValidateLogin(
        email: String,
        password: String
    ): String? {
        val emailValidation =
            ValidateEmail(
                email =
                    email
            )

        if (emailValidation != null) {
            return emailValidation
        }

        if (password.isBlank()) {
            return "Şifrenizi girin."
        }

        return null
    }

    private fun ValidateEmail(
        email: String
    ): String? {
        if (email.isBlank()) {
            return "E-posta adresinizi girin."
        }

        if (email.contains(" ")) {
            return "Geçerli bir e-posta adresi girin."
        }

        val emailParts =
            email.split("@")

        if (
            emailParts.size != 2 ||
            emailParts[0].isBlank() ||
            emailParts[1].isBlank() ||
            !emailParts[1].contains(".")
        ) {
            return "Geçerli bir e-posta adresi girin."
        }

        return null
    }

    private fun ValidateSetNewPassword(
        activationCode: String,
        newPassword: String,
        reNewPassword: String
    ): String? {
        if (activationCode.isBlank()) {
            return "Şifre yenileme bağlantısı geçersiz veya süresi dolmuş."
        }

        if (newPassword.isBlank()) {
            return "Yeni şifrenizi girin."
        }

        if (
            newPassword.length < 8 ||
            newPassword.length > 16
        ) {
            return "Şifre 8 ile 16 karakter arasında olmalıdır."
        }

        if (reNewPassword.isBlank()) {
            return "Yeni şifrenizi tekrar girin."
        }

        if (
            reNewPassword.length < 8 ||
            reNewPassword.length > 16
        ) {
            return "Şifre 8 ile 16 karakter arasında olmalıdır."
        }

        if (newPassword != reNewPassword) {
            return "Şifreler birbiriyle eşleşmiyor."
        }

        return null
    }
}